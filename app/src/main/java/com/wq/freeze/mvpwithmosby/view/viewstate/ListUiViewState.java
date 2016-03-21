package com.wq.freeze.mvpwithmosby.view.viewstate;

import android.os.Bundle;
import android.os.Parcel;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.ParcelableLceViewState;
import com.wq.freeze.mvpwithmosby.view.ListUiView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public class ListUiViewState implements ParcelableLceViewState<List<String>, ListUiView> {

    public static final int STATE_SHOW_LOAD_MORE = 2;
    public static final int STATE_SHOW_LOAD_MORE_ERROR = 3;

    private ArrayList<String> loadedData = new ArrayList<>();
    private int state = STATE_SHOW_LOADING;
    private boolean[] isInPullToRefresh = new boolean[1];

    public static final Creator CREATOR = new Creator() {
        @Override
        public ListUiViewState createFromParcel(Parcel source) {
            return new ListUiViewState(source);
        }

        @Override
        public ListUiViewState[] newArray(int size) {
            return new ListUiViewState[size];
        }
    };

    public ListUiViewState() {

    }

    public ListUiViewState(Parcel source) {
        readFromParcel(source);
    }

    @Override
    public void setStateShowContent(List<String> loadedData) {
        this.loadedData = (ArrayList<String>) loadedData;
        state = STATE_SHOW_CONTENT;
    }

    @Override
    public void setStateShowError(Throwable e, boolean pullToRefresh) {
        this.isInPullToRefresh[0] = pullToRefresh;
        state = STATE_SHOW_ERROR;
    }

    @Override
    public void setStateShowLoading(boolean pullToRefresh) {
        this.isInPullToRefresh[0] = pullToRefresh;
        state = STATE_SHOW_LOADING;
    }

    public void setStateShowLoadMore() {
        state = STATE_SHOW_LOAD_MORE;
    }

    public void setStateShowLoadMoreError() {
        state = STATE_SHOW_LOAD_MORE_ERROR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(loadedData);
        dest.writeInt(state);
        dest.writeBooleanArray(isInPullToRefresh);
    }

    @Override
    public void saveInstanceState(Bundle out) {
        out.putStringArrayList("loaded_data", loadedData);
        out.putInt("state", state);
        out.putBooleanArray("isInPullToRefresh", isInPullToRefresh);
    }

    @Override
    public RestorableViewState<ListUiView> restoreInstanceState(Bundle in) {
        this.loadedData = in.getStringArrayList("loaded_data");
        this.state = in.getInt("state");
        this.isInPullToRefresh = in.getBooleanArray("isInPullToRefresh");
        return this;
    }

    @Override
    public void apply(ListUiView view, boolean retained) {

        switch (state) {
            case STATE_SHOW_CONTENT:
                view.setData(loadedData);
                view.showContent();
                break;
            case STATE_SHOW_ERROR:
                view.showError(null, isInPullToRefresh[0]);
                break;
            case STATE_SHOW_LOADING:
                if (isInPullToRefresh[0]) {
                    view.setData(loadedData);
                }
                view.showLoading(isInPullToRefresh[0]);
                if (!isInPullToRefresh[0] && (loadedData == null || loadedData.size() == 0)) {
                    view.loadData(false);
                }
                break;
            case STATE_SHOW_LOAD_MORE:
                view.setData(loadedData);
                view.showContent();
                view.showLoadMore();
                break;
            case STATE_SHOW_LOAD_MORE_ERROR:
                view.showLoadMoreError();
                break;
        }

    }

    protected void readFromParcel(Parcel source) {
        loadedData.clear();
        source.readStringList(loadedData);
        state = source.readInt();
        source.readBooleanArray(isInPullToRefresh);
    }
}

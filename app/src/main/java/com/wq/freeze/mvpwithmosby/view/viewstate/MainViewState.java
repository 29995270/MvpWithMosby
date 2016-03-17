package com.wq.freeze.mvpwithmosby.view.viewstate;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableParcelableViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.wq.freeze.mvpwithmosby.view.MainView;


/**
 * Created by wangqi on 2016/3/16.
 */
public class MainViewState implements RestorableParcelableViewState<MainView> {

    public String colorString = null;
    public String messageString = null;
    private boolean isLoginShow = false;

    public static Creator<MainViewState> CREATOR = new Creator<MainViewState>(){

        @Override
        public MainViewState createFromParcel(Parcel source) {
            MainViewState mainViewState = new MainViewState();
            mainViewState.colorString = source.readString();
            mainViewState.messageString = source.readString();
            boolean[] isLoginShowArray = new boolean[1];
            source.readBooleanArray(isLoginShowArray);
            mainViewState.isLoginShow = isLoginShowArray[0];
            return mainViewState;
        }

        @Override
        public MainViewState[] newArray(int size) {
            return new MainViewState[size];
        }
    };

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(colorString);
        dest.writeString(messageString);
    }

    @Override
    public void saveInstanceState(@NonNull Bundle out) {
        out.putString("color", colorString);
        out.putString("message", messageString);
        out.putBoolean("isShowLogin", isLoginShow);
    }

    @Override
    public RestorableViewState<MainView> restoreInstanceState(Bundle in) {
        this.colorString  = in.getString("color");
        this.messageString = in.getString("message");
        this.isLoginShow = in.getBoolean("isShowLogin");
        return this;
    }

    @Override
    public void apply(MainView view, boolean retained) {
        if (colorString != null) {
            view.changeBgColor(colorString);
        }

        if (isLoginShow) {
            view.showLoginDialog();
        }
    }

    public void setColorState(String colorString) {
        this.colorString = colorString;
    }

    public void setIsLoginShow(boolean isShow) {
        this.isLoginShow = isShow;
    }
}

package com.wq.freeze.mvpwithmosby.presenter;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.wq.freeze.mvpwithmosby.model.ListUiModel;
import com.wq.freeze.mvpwithmosby.model.Model;
import com.wq.freeze.mvpwithmosby.model.ModelFactory;
import com.wq.freeze.mvpwithmosby.model.impl.ListUiModelImpl;
import com.wq.freeze.mvpwithmosby.view.ListUiView;

import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public class ListPresenter extends MvpNullObjectBasePresenter<ListUiView> {

    private final ListUiModel listUiModel;

    public ListPresenter() {
        listUiModel = ModelFactory.createModel(ListUiModelImpl.class);
    }

    public void initScreen() {
        getView().showLoading(false);
        listUiModel.getRandomStringList(new Model.ResultCallback<List<String>>() {
            @Override
            public void onResult(List<String> result) {
                getView().setData(result);
                getView().showContent();
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }
        });
    }

    public void refresh() {
        listUiModel.getRandomSingleString(new Model.ResultCallback<String>() {
            @Override
            public void onResult(String result) {
                getView().moreDataFromRefresh(result);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e, true);
            }
        });
    }

    public void loadMore() {
        getView().showLoadMore();
        listUiModel.getRandomStringList(new Model.ResultCallback<List<String>>() {
            @Override
            public void onResult(List<String> result) {
                getView().moreDataFromLoadMore(result);
            }

            @Override
            public void onError(Throwable e) {
                getView().showLoadMoreError();
            }
        });
    }

}

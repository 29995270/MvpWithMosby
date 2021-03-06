package com.wq.freeze.mvpwithmosby.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public interface ListUiView extends MvpLceView<List<String>> {
    void showLoadMore();
    void dismissLoadMore();
    void showLoadMoreError();
    void moreDataFromRefresh(String extra);
    void moreDataFromLoadMore(List<String> extra);
}

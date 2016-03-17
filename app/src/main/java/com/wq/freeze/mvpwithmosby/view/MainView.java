package com.wq.freeze.mvpwithmosby.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by wangqi on 2016/3/16.
 */
public interface MainView extends MvpView{
    void showSnackBar(String text);

    void changeBgColor(String color);

    void showLoginDialog();

    void showUserInvalid();
    void gotoListUi();
}

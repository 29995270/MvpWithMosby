package com.wq.freeze.mvpwithmosby.presenter;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.wq.freeze.mvpwithmosby.model.MainModel;
import com.wq.freeze.mvpwithmosby.model.ModelFactory;
import com.wq.freeze.mvpwithmosby.model.impl.MainModelImpl;
import com.wq.freeze.mvpwithmosby.view.MainView;

/**
 * Created by wangqi on 2016/3/16.
 */
public class MainPresenter extends MvpNullObjectBasePresenter<MainView> {

    private final MainModel model;

    public MainPresenter() {
        model = ModelFactory.createModel(MainModelImpl.class);
    }

    public void changeColor(){
        String randomColorString = model.getRandomColorString();
        getView().changeBgColor(randomColorString);
    }

    public void showMessage() {
        String randomMessageString = model.getRandomMessageString();
        getView().showSnackBar(randomMessageString);
    }
}

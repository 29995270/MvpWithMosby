package com.wq.freeze.mvpwithmosby.view.widget;

/**
 * Created by wangqi on 2016/3/18.
 */
public abstract class LoadMoreListener {
    public boolean isLoading = false;
    public void onLoad(){
        isLoading = true;
    };
}

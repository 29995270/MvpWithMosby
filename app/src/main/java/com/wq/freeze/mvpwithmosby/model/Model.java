package com.wq.freeze.mvpwithmosby.model;

import android.content.Context;

import com.wq.freeze.mvpwithmosby.App;

/**
 * Created by wangqi on 2016/3/16.
 */
public interface Model {

    Context sContext = App.APP_CONTEXT;

    interface ResultCallback<T>{
        void onResult(T result);
        void onError(Throwable e);
    }
}

package com.wq.freeze.mvpwithmosby.model;

/**
 * Created by wangqi on 2016/3/16.
 */
public interface Model {

    interface ResultCallback<T>{
        void onResult(T result);
        void onError(Throwable e);
    }
}

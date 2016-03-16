package com.wq.freeze.mvpwithmosby.model;

/**
 * Created by wangqi on 2016/3/16.
 */
public class ModelFactory {

    public static <T> T createModel(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

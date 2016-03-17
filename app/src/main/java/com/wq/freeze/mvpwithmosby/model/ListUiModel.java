package com.wq.freeze.mvpwithmosby.model;

import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public interface ListUiModel extends Model{
    void getRandomSingleString(ResultCallback<String> callback);

    void getRandomStringList(ResultCallback<List<String>> callback);
}

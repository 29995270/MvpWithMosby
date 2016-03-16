package com.wq.freeze.mvpwithmosby.model.impl;

import com.wq.freeze.mvpwithmosby.model.MainModel;

import java.util.Random;

/**
 * Created by wangqi on 2016/3/16.
 */
public class MainModelImpl implements MainModel {

    private String[] colors = {
        "#782610",
        "#783510",
        "#782010",
        "#432610",
        "#781910",
        "#782320",
        "#782510",
        "#082610",
        "#724610",
        "#724632",
    };

    private String[] messages = {
            "111111",
            "222222",
            "333333",
            "444444",
            "555555",
            "666666",
            "777777",
            "888888",
            "999999",
            "000000"
    };

    private Random random = new Random();


    @Override
    public String getRandomColorString() {
        return colors[random.nextInt(10)];
    }

    @Override
    public String getRandomMessageString() {
        return messages[random.nextInt(10)];
    }
}

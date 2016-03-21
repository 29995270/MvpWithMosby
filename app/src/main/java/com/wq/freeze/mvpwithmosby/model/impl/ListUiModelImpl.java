package com.wq.freeze.mvpwithmosby.model.impl;

import android.os.SystemClock;

import com.wq.freeze.mvpwithmosby.model.ListUiModel;
import com.wq.freeze.mvpwithmosby.model.ModelCommonHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by wangqi on 2016/3/17.
 */
public class ListUiModelImpl implements ListUiModel {

    private Random random  = new Random();

    @Override
    public void getRandomSingleString(final ResultCallback<String> callback) {

        ModelCommonHelper.EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                ModelCommonHelper.MAIN_HANDLE.post(new Runnable() {
                    @Override
                    public void run() {

                        if (System.currentTimeMillis() % 2 == 0) {
                            callback.onResult(String.valueOf(System.currentTimeMillis() + random.nextInt(1000000000)));
                        } else {
                            callback.onError(null);
                        }

                    }
                });
            }
        });
    }

    private String[] strings = new String[]{
        "dummy message 11111",
        "dummy message 22222",
        "dummy message 33333",
        "dummy message 44444",
        "dummy message 55555",
        "dummy message 66666",
        "dummy message 77777",
        "dummy message 88888",
        "dummy message 99999",
        "dummy message 00000",
        "dummy message aaaaa",
        "dummy message bbbbb",
        "dummy message ccccc",
        "dummy message ddddd",
        "dummy message eeeee",
        "dummy message fffff",
        "dummy message ggggg",
        "dummy message hhhhh",
        "dummy message iiiii",
        "dummy message jjjjj",
        "dummy message kkkkk",
        "dummy message lllll",
        "dummy message mmmmm",
        "dummy message nnnnn"
    };

    @Override
    public void getRandomStringList(final ResultCallback<List<String>> callback) {

        ModelCommonHelper.EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                ModelCommonHelper.MAIN_HANDLE.post(new Runnable() {
                    @Override
                    public void run() {

                        if (System.currentTimeMillis() % 2 == 0) {
                            ArrayList<String> list = new ArrayList<>();
                            Collections.addAll(list, strings);
                            callback.onResult(list);
                        } else {
                            callback.onError(null);
                        }

                    }
                });
            }
        });
    }
}

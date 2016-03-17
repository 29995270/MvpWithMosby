package com.wq.freeze.mvpwithmosby.model.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.wq.freeze.mvpwithmosby.model.ListUiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangqi on 2016/3/17.
 */
public class ListUiModelImpl implements ListUiModel {

    private Random random  = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getRandomSingleString(final ResultCallback<String> callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResult(String.valueOf(System.currentTimeMillis() + random.nextInt(100000)));
                    }
                });
            }
        }).start();
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
    };

    @Override
    public void getRandomStringList(final ResultCallback<List<String>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<String> list = new ArrayList<>();
                        for (String string : strings) {
                            list.add(string);
                        }
                        callback.onResult(list);
                    }
                });
            }
        }).start();
    }
}

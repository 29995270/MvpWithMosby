package com.wq.freeze.mvpwithmosby.model;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangqi on 2016/3/18.
 */
public class ModelCommonHelper {
    public static final Handler MAIN_HANDLE = new Handler(Looper.getMainLooper());

    public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4));
}

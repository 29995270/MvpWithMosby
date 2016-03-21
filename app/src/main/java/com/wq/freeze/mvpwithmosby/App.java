package com.wq.freeze.mvpwithmosby;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangqi on 2016/3/21.
 */
public class App extends Application {

    public static Context APP_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        APP_CONTEXT = this.getApplicationContext();
    }
}

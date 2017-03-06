package com;

import android.app.Application;

import com.wolearn.mvpframelib.frame.Mvp;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mvp.getInstance().init(this);
    }
}

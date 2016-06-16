package com.test.litmitedsizeiv;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 13798 on 2016/6/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

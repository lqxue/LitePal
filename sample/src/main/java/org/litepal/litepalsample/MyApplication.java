package org.litepal.litepalsample;

import android.app.Application;

import org.litepal.Operator;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Operator.initialize(this);
    }
}

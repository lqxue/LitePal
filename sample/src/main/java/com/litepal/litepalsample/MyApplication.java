package com.litepal.litepalsample;import android.app.Application;import com.litepal.Operator;public class MyApplication extends Application{@Override
    public void onCreate() {
        super.onCreate();
        Operator.initialize(this);
    }
}

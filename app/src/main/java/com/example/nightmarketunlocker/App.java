package com.example.nightmarketunlocker;

import android.app.Application;

import com.yechaoa.yutils.ActivityUtil;
import com.yechaoa.yutils.LogUtil;
import com.yechaoa.yutils.YUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        YUtils.initialize(this);
        LogUtil.setIsLog(true);
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());

    }

}

package com.z.zmapmarker;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

public class ZMapMarkerApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }

}
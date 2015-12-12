package com.z.zmapmarker;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

public class SDKReceiver extends BroadcastReceiver {

    //private static final String LTAG = BMapApiDemoMain.class.getSimpleName();
    private String verifyRespons = "";
    public void onReceive(Context context, Intent intent) {
        String s = intent.getAction();
        if (s
                .equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            verifyRespons = "key error:01";
            Toast.makeText(context,verifyRespons, Toast.LENGTH_LONG)
                    .show();
        } else if (s
                .equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
            verifyRespons = "Key for BaiduMap is verified OK!";
            Toast.makeText(context, verifyRespons, Toast.LENGTH_LONG).show();
        }
        else if (s
                .equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
            verifyRespons = "error:00 Fail to connect to Internet!";
            Toast.makeText(context, verifyRespons, Toast.LENGTH_LONG).show();
        }
    }

    public String getResponse(){
        return this.verifyRespons;
    }
}
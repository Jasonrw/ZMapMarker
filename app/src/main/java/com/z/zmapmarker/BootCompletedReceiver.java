package com.z.zmapmarker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/12/5.
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent newIntent = new Intent(context,TraceZ.class);
            context.startService(newIntent);
        }
    }
}
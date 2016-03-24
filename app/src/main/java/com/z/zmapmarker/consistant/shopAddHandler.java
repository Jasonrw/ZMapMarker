package com.z.zmapmarker.consistant;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/3/14.
 */
public class shopAddHandler extends android.os.Handler {
    String status = new String();
    @Override
    //处理baidulbs返回的消息
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Bundle data = msg.getData();
        String val = data.getString("response");
        Log.e("BaiduLbsCloudResponse", val);
        try {
            JSONObject responseJson = new JSONObject(val);
            this.status = responseJson.getString("status");
        }catch (Exception e){
            Log.e("shopAddHandler","Baidu's response is not a JsonObject!!!");
        }

    }
}

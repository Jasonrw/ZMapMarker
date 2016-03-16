package com.z.zmapmarker;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.*;

/**
 * Created by Administrator on 2016/3/14.
 */
public class shopUploadThread implements Runnable {
    Shop shop = new Shop();
    public shopUploadThread(Shop shop){
        this.shop = shop;
    }
    OkHttpClient mOkHttpClient = new OkHttpClient();
    shopAddHandler handler = new shopAddHandler();
    @Override
    public void run(){
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "ak=itPld5GN1OVDZfluEUmAECoL&" +
                "id="+shop.getId() + "&" +
                "title="+shop.getName() + "&" +
                "address="+shop.getRefLocation() + "&" +
                "longitude="+shop.getLongitude() + "&" +
                "latitude="+shop.getLatitude() + "&" +
                "coord_type=1&" +
                "geotable_id=134665");
        Request request = new Request.Builder()
                .url("http://api.map.baidu.com/geodata/v3/poi/create")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "02c10913-3c23-cb89-7b69-bab3be7966f2")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Log.e("Request", request.toString());
        try{
            Response response = mOkHttpClient.newCall(request).execute();
            Log.e("responseByOkhttp",response.body().string());
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("OkHttpResponse", response.body().string());
            msg.setData(bundle);
            handler.sendMessage(msg);
        }catch (Exception e){
            Log.e("errorHttp",e.toString());
        }
    }

}

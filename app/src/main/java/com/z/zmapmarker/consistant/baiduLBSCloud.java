package com.z.zmapmarker.consistant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.z.zmapmarker.*;

import org.apache.http.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/1.
 */
public class baiduLBSCloud {
    /** ??LBS Track??? */
    public final static String BAIDU_LBS_CLOUD = "http://api.map.baidu.com/geodata/v3";

    // POST
    public final static String BAIDU_LBS_CLOUD_TABLE_CREATE_API = BAIDU_LBS_CLOUD + "/geotable/create";
    public final static String BAIDU_LBS_CLOUD_TABLE_UPDATE_API = BAIDU_LBS_CLOUD + "/geotable/delete";
    public final static String BAIDU_LBS_CLOUD_TABLE_DELETE_API = BAIDU_LBS_CLOUD + "/geotable/update";

    // GET
    public final static String BAIDU_LBS_CLOUD_TABLE_DETAIL_API = BAIDU_LBS_CLOUD + "/geotable/detail";
    public final static String BAIDU_LBS_CLOUD_TABLE_LIST_API = BAIDU_LBS_CLOUD + "/geotable/list";

    public final static String BAIDU_LBS_CLOUD_COLUMN_CREATE_API = BAIDU_LBS_CLOUD + "/column/create";
    public final static String BAIDU_LBS_CLOUD_COLUMN_DETAIL_API = BAIDU_LBS_CLOUD + "/column/detail";
    public final static String BAIDU_LBS_CLOUD_COLUMN_UPDATE_API = BAIDU_LBS_CLOUD + "/column/update";
    public final static String BAIDU_LBS_CLOUD_COLUMN_DELETE_API = BAIDU_LBS_CLOUD + "/column/delete";
    public final static String BAIDU_LBS_CLOUD_COLUMN_LIST_API = BAIDU_LBS_CLOUD + "/column/list";

    public final static String BAIDU_LBS_CLOUD_POI_CREATE_API = BAIDU_LBS_CLOUD + "/poi/create";
    public final static String BAIDU_LBS_CLOUD_POI_DETAIL_API = BAIDU_LBS_CLOUD + "/poi/detail";
    public final static String BAIDU_LBS_CLOUD_POI_UPDATE_API = BAIDU_LBS_CLOUD + "/poi/update";
    public final static String BAIDU_LBS_CLOUD_POI_DELETE_API = BAIDU_LBS_CLOUD + "/poi/delete";
    public final static String BAIDU_LBS_CLOUD_POI_LIST_API = BAIDU_LBS_CLOUD + "/poi/list";
    /*public void addShopInfo(Shop shop, RequestQueue requestQueue) {
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.e("Log", shop.toJson().toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,BAIDU_LBS_CLOUD_POI_CREATE_API, shop.toJson(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if (jsonObject.getString("status").equals("0")) {
                        Log.e("LOG", "??success");
                    } else {
                        Log.e("LOG", "failure::");
                        try {
                            Log.e("Log", URLDecoder.decode(jsonObject.toString(),"UTF-8"));
                        }catch (Exception e){

                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Log", "volley??");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("LOG", "????");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }*/
    OkHttpClient mOkHttpClient = new OkHttpClient();
        Runnable addShop = new Runnable() {
            @Override
            public void run() {
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "ak=itPld5GN1OVDZfluEUmAECoL&longitude=111.763068&latitude=29.64162&coord_type=1&geotable_id=134665");
                Request request = new Request.Builder()
                        .url("http://api.map.baidu.com/geodata/v3/poi/create")
                        .post(body)
                        .addHeader("cache-control", "no-cache")
                                //.addHeader("postman-token", "02c10913-3c23-cb89-7b69-bab3be7966f2")
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
        };








    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("OkHttpResponse");
            Log.e("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
        }
    };
}

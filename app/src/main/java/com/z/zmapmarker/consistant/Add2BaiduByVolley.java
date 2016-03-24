package com.z.zmapmarker.consistant;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.MultipartRequest;
import com.google.gson.JsonObject;
import com.z.zmapmarker.entity.Shop;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/3/19.
 */
public class Add2BaiduByVolley {
    public final static String BAIDU_LBS_CLOUD = "http://api.map.baidu.com/geodata/v3";
    public final static String BAIDU_LBS_CLOUD_POI_CREATE_API = BAIDU_LBS_CLOUD + "/poi/create";
    public static void addShopInfo(Shop shop, RequestQueue requestQueue) {
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.e("Log", shop.toJson().toString());
        MultipartRequest multipartRequest = new MultipartRequest(Request.Method.POST,BAIDU_LBS_CLOUD_POI_CREATE_API, shop.toJson(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                    Log.e("Response",jsonObject.toString());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("LOG", "request error");
            }
        });
        requestQueue.add(multipartRequest);
    }
}

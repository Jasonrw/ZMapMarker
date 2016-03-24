package com.z.zmapmarker;

import android.test.InstrumentationTestCase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.z.zmapmarker.consistant.Add2BaiduByVolley;
import com.z.zmapmarker.entity.Shop;
import com.z.zmapmarker.entity.ShopManager;

/**
 * Created by Administrator on 2016/3/21.
 */
public class addShopInfoByVolleyTest extends InstrumentationTestCase {
    public void testAdd2BaiduByVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(ZMapMarkerApplication.getContext());
        Shop shop = ShopManager.genShop();
        Add2BaiduByVolley.addShopInfo(shop,requestQueue);
    }
}

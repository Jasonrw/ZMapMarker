package com.z.zmapmarker;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.z.zmapmarker.entity.Shop;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/3/2.
 */
public class ShopTest extends InstrumentationTestCase {

    public void testShop() throws Exception{
        Shop newShop = new Shop();
        newShop.setId(888);
        newShop.setLatitude(12.34567);
        newShop.setLongitude(23.45678);
        newShop.setName("叫啥名字好呢");
        newShop.setQuantity(2);
        newShop.setRefLocation("澧州大饭店附近");
        JSONObject jsonObject = newShop.toJson();
        Log.e("json:",jsonObject.toString());
    }


}



package com.z.zmapmarker;
import android.test.InstrumentationTestCase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Random;

/**
 * Created by Administrator on 2016/3/2.
 */
public class AddShopInfo2BaiduLbsCloudTest extends InstrumentationTestCase {
    public Shop generateShop(){
        Shop newShop = new Shop();
        int randamId = new Random().nextInt();
        newShop.setId(randamId);
        newShop.setLatitude(29.64162);
        newShop.setLongitude(111.763068);
        newShop.setName("叫啥名字好呢2");
        newShop.setQuantity(2);
        newShop.setRefLocation("澧州大饭店附近");
        return newShop;
    }
    public void testAddShopInfo2Baidu(){
        Shop shop = generateShop();
        ShopManager.uploadShop(shop);

    }
}

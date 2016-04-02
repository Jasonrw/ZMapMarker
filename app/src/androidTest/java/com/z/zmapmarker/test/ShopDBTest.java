package com.z.zmapmarker.test;

import com.z.zmapmarker.Consistant.Local.Dao.GreenShop;
import com.z.zmapmarker.Consistant.Local.ShopDB;

import junit.framework.TestCase;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ShopDBTest extends TestCase {

    public void testGetUnUpload() throws Exception {
        ShopDB shopDB = new ShopDB();
        //shopDB.init();
        GreenShop shop = new GreenShop((long) 1,253,"澧州大饭店","澧县",2,111.2036,29.64162,false);


    }

    public void testGetAll() throws Exception {

    }
}
package com.z.zmapmarker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.z.zmapmarker.R;
import com.z.zmapmarker.entity.ShopManager;


public class ZMapMarkerMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        testBaiduCloud();
    }
    public void testBaiduCloud(){
        /*baiduLBSCloud m_baiduLBSCloud = new baiduLBSCloud();
        //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Shop newShop = new Shop();
        newShop.setId(888);
        newShop.setLatitude(29.64162);
        newShop.setLongitude(111.763068);
        newShop.setName("js2");
        newShop.setQuantity(2);
        newShop.setRefLocation("lzdfdfj");
        new Thread(new baiduLBSCloud().addShop).start();*/
        ShopManager.uploadShop(ShopManager.genShop());
    }


}

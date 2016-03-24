package com.z.zmapmarker.entity;

import android.util.Log;

import com.z.zmapmarker.consistant.shopUploadThread;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/1.
 */
public class ShopManager {
    private ArrayList allShops = new ArrayList<Shop>();
    //初始化
    public boolean init(){
        return true;
    }
    //用所有的shops来初始化
    public boolean init(ArrayList<Shop> shops){
        try {
            allShops = (ArrayList) shops.clone();
            return true;
        }
        catch (Exception e){
            Log.e("error1:",e.toString());
        }
        return false;
    }

    public boolean add(Shop newShop){
        if(newShop == null){
            return false;
        }
        if(newShop.getName().equals(null)){
            return false;
        }
        allShops.add(newShop);
        return true;
    }
    public static boolean uploadShop(Shop shop){
        try {
            new Thread(new shopUploadThread(shop)).start();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static Shop genShop(){
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

}

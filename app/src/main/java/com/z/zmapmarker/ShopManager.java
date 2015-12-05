package com.z.zmapmarker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ShopManager {
    private ArrayList<Shop> shops = new ArrayList();
    private static Context context;
    private ShopInfoDBHelper mDBHelper;
    private SQLiteDatabase db;
    public ShopManager(Context context){
        //init the ShopManager sync the database
        this.context = context;
        this.mDBHelper = new ShopInfoDBHelper(context);
        this.db = mDBHelper.getWritableDatabase();
        String[] projection ={
                ShopInfoDBContract.ShopDb._ID,
                ShopInfoDBContract.ShopDb.COLUMN_NAME_SHOPNAME,
                ShopInfoDBContract.ShopDb.COLUMN_NAME_LAT,
                ShopInfoDBContract.ShopDb.COLUMN_NAME_LON,
                ShopInfoDBContract.ShopDb.COLUMN_NAME_DISTQUANTITY
        };
        Cursor cursor = this.db.query(
                ShopInfoDBContract.ShopDb.TABLENAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        //如果cursor不为空就同步数据库中的结果
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do{
                String tempID = cursor.getString(cursor.getColumnIndexOrThrow(ShopInfoDBContract.ShopDb._ID));
                String tempShopName = cursor.getString(cursor.getColumnIndexOrThrow(ShopInfoDBContract.ShopDb.COLUMN_NAME_SHOPNAME));
                double tempLat = cursor.getDouble(cursor.getColumnIndexOrThrow(ShopInfoDBContract.ShopDb.COLUMN_NAME_LAT));
                double tempLon = cursor.getDouble(cursor.getColumnIndexOrThrow(ShopInfoDBContract.ShopDb.COLUMN_NAME_LON));
                int tempDistQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(ShopInfoDBContract.ShopDb.COLUMN_NAME_DISTQUANTITY));
                Shop tempShop = new Shop(tempID, tempShopName, tempLat, tempLon, tempDistQuantity);
                this.shops.add(tempShop);


            }while(cursor.moveToNext());
        }
    }
    public int add(Shop shop){
        //To add single shopInfo to the manager and database
        //验证是否是同一个点
        LatLng pt = shop.getPt();
        Iterator<Shop> iterShop = this.shops.iterator();
        while(iterShop.hasNext()){
            Shop cShop = iterShop.next();
            if(cShop.getPt().equals(shop.getPt()));
                return 1;
        }
        this.shops.add(shop);
        //Insert into Database
        this.db.insertOrThrow(ShopInfoDBContract.ShopDb.TABLENAME, ShopInfoDBContract.ShopDb.COLUMN_NAME_NULLABLE, shop.toContentValues());
        return 0;
    }
    public Shop getShop(String id){
        //To return the specific Shop according to the id
        Iterator<Shop> iterShop = this.shops.iterator();
        while(iterShop.hasNext()){
            Shop result = iterShop.next();
            if(result.getId().equals(id))
                return result;
        }
        return null;//return null when can not find the id
    }
    public ArrayList<Shop> getAllShop(){
        //To return all Shop Info
        return shops;
    }

    public boolean deleteShop(String id){
        //To delete the specific Shop
        for(int i = 0;i<shops.size();i++){
            if(shops.get(i).getId().equals(id)){
                shops.remove(i);
                String[] ids = {id};
                this.db.delete(ShopInfoDBContract.ShopDb.TABLENAME, ShopInfoDBContract.ShopDb._ID+"=",ids);
                return true;
            }
        }
        return false;
    }
    public boolean deleteAll(){
        return true;
    }
    public boolean updateShop(String id, Shop shop){
        //To update the specific Shop
        return true;
    }
    public void destroy(){
        //To destroy the implement
    }
    public static ShopManager getInstance(){
        return new ShopManager(context);
    }
}

package com.z.zmapmarker;


import android.content.ContentValues;

import com.baidu.mapapi.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/30.
 */
public class Shop {
    private String id;
    private String ShopName;
    private double Lat;
    private double Lon;
    private int DistributionQuantity;
    public Shop(String id, String ShopName, double Lat, double Lon, int DistriibutionQuantity){
        this.id = id;
        this.ShopName = ShopName;
        this.Lat = Lat;
        this.Lon = Lon;
        this.DistributionQuantity = DistriibutionQuantity;
    }
    public Shop(){}
    public void setDistributionQuantity(int DistributionQuantity){
        this.DistributionQuantity = DistributionQuantity;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setShopName(String ShopName){
        this.ShopName = ShopName;
    }
    public void setLat(double Lat){
        this.Lat = Lat;
    }
    public void setLon(double Lon){
        this.Lon = Lon;
    }
    public String getId(){
        return this.id;
    }
    public int getDistributionQuantity(){
        return this.DistributionQuantity;
    }
    public String getShopName(){
        return this.ShopName;
    }
    public double getLat(){
        return this.Lat;
    }
    public double getLon(){
        return this.Lon;
    }
    public ContentValues toContentValues(){
        ContentValues CV = new ContentValues();
        CV.put(ShopInfoDBContract.ShopDb._ID, this.id);
        CV.put(ShopInfoDBContract.ShopDb.COLUMN_NAME_SHOPNAME, this.ShopName);
        CV.put(ShopInfoDBContract.ShopDb.COLUMN_NAME_LAT, this.Lat);
        CV.put(ShopInfoDBContract.ShopDb.COLUMN_NAME_LON, this.Lon);
        CV.put(ShopInfoDBContract.ShopDb.COLUMN_NAME_DISTQUANTITY, this.DistributionQuantity);
        return CV;
    }

    public LatLng getPt(){
        LatLng pt = new LatLng(this.Lat,this.Lon);
        return pt;
    }
}

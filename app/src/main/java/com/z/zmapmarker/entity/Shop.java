package com.z.zmapmarker.entity;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;


/**
 * Created by Administrator on 2016/3/1.
 */
public class Shop {
    private int id;
    private String name;
    private String refLocation;
    private int quantity;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefLocation() {
        return refLocation;
    }

    public void setRefLocation(String refLocation) {
        this.refLocation = refLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public JSONObject toJson(){
        JSONObject shopJson = new JSONObject();
        try{
            shopJson.put("ak","itPld5GN1OVDZfluEUmAECoL");
            shopJson.put("title", this.getName());
            shopJson.put("id",Integer.toString(this.getId()));
            shopJson.put("address",this.getRefLocation());
            shopJson.put("latitude", this.getLatitude());
            shopJson.put("longitude", this.getLongitude());
            shopJson.put("quantity", this.getQuantity());
            shopJson.put("coord_type", 1);
            shopJson.put("geotable_id", "134665");
            return shopJson;
        }
        catch (Exception e){
            Log.e(e.toString(),"the name is not a string");
        }
        return null;
    }
}

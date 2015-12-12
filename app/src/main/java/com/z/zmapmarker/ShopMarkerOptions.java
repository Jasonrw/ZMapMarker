package com.z.zmapmarker;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/11.
 */
public class ShopMarkerOptions {
    //Private Params
    private Shop mShop;
    private TextOptions mTextOptions = new TextOptions();
    private MarkerOptions mMarkerOptions = new MarkerOptions();

    private int txtFontSize = 30;
    private Bundle idInfo = new Bundle();
    private ArrayList<BitmapDescriptor> MarkerIcons = new ArrayList<BitmapDescriptor>();
    public ShopMarkerOptions(Shop shop){
        initMakerIcon();
        mShop = shop;
        initIdInfo();//初始化id Bundle
        initMarkerOption();
        initTxtOptions();

    }

    public  ShopMarkerOptions(String shopId){
        mShop = ShopManager.getInstance().getShop(shopId);
        initMakerIcon();
        initIdInfo();//初始化id Bundle
        initMarkerOption();
        initTxtOptions();
    }

    public TextOptions getTextOptions(){
        return mTextOptions;
    }

    public MarkerOptions getMarkerOptions(){
        return mMarkerOptions;
    }

    private void initTxtOptions(){
        mTextOptions.text("  "+mShop.getShopName());
        mTextOptions.position(mShop.getPt());
        mTextOptions.fontSize(txtFontSize);
        mTextOptions.rotate(30);
        mTextOptions.align(TextOptions.ALIGN_LEFT, TextOptions.ALIGN_CENTER_VERTICAL);
        mTextOptions.extraInfo(idInfo);
    }

    public void initMarkerOption(){
        mMarkerOptions.position(mShop.getPt());
        mMarkerOptions.icon(MarkerIcons.get(mShop.getDistributionQuantity()));
        mMarkerOptions.extraInfo(idInfo);
        mMarkerOptions.anchor(0.5f, 0.5f);
        //mMarkerOptions.title("test");
        //mMarkerOptions.perspective(false);
    }

    private void initMakerIcon(){
        MarkerIcons.add(BitmapDescriptorFactory.fromResource(R.drawable.circleempty));//0
        MarkerIcons.add(BitmapDescriptorFactory.fromResource(R.drawable.square));//1
        MarkerIcons.add(BitmapDescriptorFactory.fromResource(R.drawable.circle));//2
        MarkerIcons.add(BitmapDescriptorFactory.fromResource(R.drawable.star));//3
    }

    private void initIdInfo(){
        idInfo.putString("id",mShop.getId());
    }



}

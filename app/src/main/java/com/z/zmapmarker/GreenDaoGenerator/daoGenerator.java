package com.z.zmapmarker.GreenDaoGenerator;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/4/2.
 */
public class daoGenerator {
    public static void main(String[] arg) throws Exception{
        Schema schema = new Schema(1000,"com.z.zmapmarker.Consistant.Local.Dao");
        addShop(schema);
        new DaoGenerator().generateAll(schema, "F:\\workshop\\zMapMarker\\ZMapMarker\\app\\src\\main\\java");
    }
    private static void addShop(Schema schema){
        Entity shop = schema.addEntity("GreenShop");
        shop.addIdProperty().primaryKey();
        shop.addIntProperty("BD_ID");
        shop.addStringProperty("title");
        shop.addStringProperty("address");
        shop.addIntProperty("quantity");
        shop.addDoubleProperty("longitude");
        shop.addDoubleProperty("latitude");
        shop.addBooleanProperty("uploadState");
    }
}

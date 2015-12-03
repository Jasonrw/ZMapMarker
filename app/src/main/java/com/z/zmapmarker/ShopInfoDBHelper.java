package com.z.zmapmarker;
import android.database.sqlite.*;
import android.content.*;
import com.z.zmapmarker.ShopInfoDBContract.*;

public class ShopInfoDBHelper extends SQLiteOpenHelper
{
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ZMapMarker.db";

    public ShopInfoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShopDb.SQL_CREATE_SHOPINFO);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ShopDb.SQL_DELETE_SHOPINFO);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
}

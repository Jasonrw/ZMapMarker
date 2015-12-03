package com.z.zmapmarker;
import android.provider.*;

public class ShopInfoDBContract
{
	//to prevent someone accidentlly from implementing this
	public ShopInfoDBContract(){}
	
	public static abstract class ShopDb implements BaseColumns{
		public final  static String TABLENAME = "ShopInfo";
		public final static String COLUMN_NAME_SHOPNAME = "name";
		public final static String COLUMN_NAME_LAT = "lat";
		public final static String COLUMN_NAME_LON = "lon";
		public final static String COLUMN_NAME_DISTQUANTITY = "distribution_quantity";
		public final static String TYPE_TEXT = " text";
		public final static String TYPE_INT = " int";
		public final static String TYPE_FLOAT = " float(10,10)";
		public final static String COMMA_SEP = ",";
		public final static String COLUMN_NAME_NULLABLE = "null";
		
		public final static String SQL_CREATE_SHOPINFO = 
			"CREATE TABLE " + ShopDb.TABLENAME + " (" +
			ShopDb._ID + " TEXT PRIMARY KEY" + ShopDb.COMMA_SEP +
			ShopDb.COLUMN_NAME_SHOPNAME + ShopDb.TYPE_TEXT + ShopDb.COMMA_SEP +
			ShopDb.COLUMN_NAME_LAT + ShopDb.TYPE_FLOAT + ShopDb.COMMA_SEP +
			ShopDb.COLUMN_NAME_LON + ShopDb.TYPE_FLOAT + ShopDb.COMMA_SEP +
			ShopDb.COLUMN_NAME_DISTQUANTITY + ShopDb.TYPE_INT +
			");";

		public static final String SQL_DELETE_SHOPINFO =
		"DROP TABLE IF EXISTS " + ShopDb.TABLENAME;

	}
	
}

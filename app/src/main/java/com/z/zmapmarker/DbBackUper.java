package com.z.zmapmarker;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * backing up the dataBase in the background
 * Created by JasonHu on 2015/12/12.
 */
public class DbBackUper {
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    Context context;


    public DbBackUper(Context context){
        this.context = context;
    }

    public int BackUp(){
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();
        File dbFile = context.getDatabasePath("ZMapMarker.db");
// 创建目录
        File exportDir = new File(Environment.getExternalStorageDirectory(),
                "ZMapMarker");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File backup = new File(exportDir, dbFile.getName()+dateFormat.format(cal.getTime()));
        try {
            if(!backup.exists()){
                backup.createNewFile();
                fileCopy(dbFile, backup);
                Toast.makeText(context, "SD卡备份完成！(*^‧^*)",Toast.LENGTH_LONG).show();
            }else {
                //Toast.makeText(context, "今天的备份已经完成过了！O(∩_∩)O",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    private void fileCopy(File dbFile, File backup) throws IOException {
        FileChannel inChannel = new FileInputStream(dbFile).getChannel();
        FileChannel outChannel = new FileOutputStream(backup).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }
}

package com.z.zmapmarker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.z.zmapmarker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UnUploadListActivity extends Activity{
    //private ListView m_unUploadList;
    private List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle saveInstanseState){
        super.onCreate(saveInstanseState);
        setContentView(R.layout.un_upload_list);
        ListView m_unUploadList = (ListView) findViewById(R.id.unUploadList);
        SimpleAdapter m_listAdapter = new SimpleAdapter(this,getmData(),R.layout.sample_un_upload_list_item,
                new String[]{"title","button","address"},
                new int[]{R.id.shopName, R.id.reUpload, R.id.unUploadListItemAddr});
        m_unUploadList.setAdapter(m_listAdapter);
    }
    private List<Map<String, Object>> getmData(){

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title","ShopName");
        map.put("address","address1");
        map.put("button","重新上传");
        List<Map<String, Object>> UnUploadList = new ArrayList<Map<String, Object>>();
        for(int i=0;i<20;i++){
            UnUploadList.add(map);

        }
        return UnUploadList;
    }
}

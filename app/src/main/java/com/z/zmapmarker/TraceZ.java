package com.z.zmapmarker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnEntityListener;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;

public class TraceZ extends Service {
    protected Context mContext;
    /**
     * 轨迹服务
     */
    protected static Trace trace = null;

    /**
     * entity标识
     */
    protected static String entityName = null;

    /**
     * 鹰眼服务ID，开发者创建的鹰眼服务对应的服务ID
     */
    protected static long serviceId = 105756; // serviceId为开发者创建的鹰眼服务ID

    /**
     * 轨迹服务类型（0 : 不建立socket长连接， 1 : 建立socket长连接但不上传位置数据，2 : 建立socket长连接并上传位置数据）
     */
    private int traceType = 2;

    /**
     * 轨迹服务客户端
     */
    protected static LBSTraceClient client = null;

    /**
     * Entity监听器
     */
    protected static OnEntityListener entityListener = null;

    protected static OnStartTraceListener startTraceListener = null;

    protected boolean isTraceStart = false;

    /**
     * 停止轨迹服务监听器
     */
    protected static OnStopTraceListener stopTraceListener = null;

    /**
     * 打包周期（单位 : 秒）
     */
    private int packInterval = 30;
    public TraceZ() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        mContext = getApplicationContext();

        // 初始化轨迹服务客户端
        client = new LBSTraceClient(mContext);

        // 初始化entity标识
        entityName = getImei(mContext);

        // 初始化轨迹服务
        trace = new Trace(getApplicationContext(), serviceId, entityName, traceType);

        showMessage("service is creating", 0);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int serviceId){
        startTrace();

        // 初始化监听器
        initListener();

        // 设置采集周期
        setInterval();

        // 设置http请求协议类型
        setRequestType();
        //showMessage("service started", 1);
        return 0;
    }


    protected static String getImei(Context context) {
        String mImei = "NULL";
        try {
            mImei = ((TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) {
            System.out.println("获取IMEI码失败");
            mImei = "NULL";
        }
        return mImei;
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        client.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void startTrace() {
        // 通过轨迹服务客户端client开启轨迹服务
        this.client.startTrace(this.trace, this.startTraceListener);
    }

    private void initListener() {
        // 初始化开启轨迹服务监听器
        initOnStartTraceListener();

        // 初始化停止轨迹服务监听器
        initOnStopTraceListener();
    }

    private void initOnStartTraceListener() {
        // 初始化startTraceListener
        //showMessage("init OnstartTraceListener!", 2);
        startTraceListener = new OnStartTraceListener() {

            // 开启轨迹服务回调接口（arg0 : 消息编码，arg1 : 消息内容，详情查看类参考）
            public void onTraceCallback(int arg0, String arg1) {
                // TODO Auto-generated method stub
                showMessage("开启轨迹服务回调接口消息 [消息编码 : " + arg0 + "，消息内容 : " + arg1 + "]", Integer.valueOf(arg0));
                if (0 == arg0 || 10006 == arg0) {
                    isTraceStart = true;
                }
            }

            // 轨迹服务推送接口（用于接收服务端推送消息，arg0 : 消息类型，arg1 : 消息内容，详情查看类参考）
            public void onTracePushCallback(byte arg0, String arg1) {
                // TODO Auto-generated method stub
                showMessage("轨迹服务推送接口消息 [消息类型 : " + arg0 + "，消息内容 : " + arg1 + "]", null);
            }

        };
    }

    /**
     * 初始化OnStopTraceListener
     */
    private void initOnStopTraceListener() {
        // 初始化stopTraceListener
        stopTraceListener = new OnStopTraceListener() {

            // 轨迹服务停止成功
            public void onStopTraceSuccess() {
                // TODO Auto-generated method stub
                showMessage("停止轨迹服务成功", Integer.valueOf(1));
                isTraceStart = false;
            }

            // 轨迹服务停止失败（arg0 : 错误编码，arg1 : 消息内容，详情查看类参考）
            public void onStopTraceFailed(int arg0, String arg1) {
                // TODO Auto-generated method stub
                showMessage("停止轨迹服务接口消息 [错误编码 : " + arg0 + "，消息内容 : " + arg1 + "]", null);
            }
        };
    }

    private void showMessage(final String message, final Integer errorNo) {

        new Handler(mContext.getMainLooper()).post(new Runnable() {
            public void run() {
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 设置采集周期和打包周期
     *
     */
    private void setInterval() {
        // 位置采集周期
        int gatherInterval = 5;
        // 打包周期
        packInterval = 10;
        client.setInterval(gatherInterval, packInterval);
    }

    /**
     * 设置请求协议
     */
    private void setRequestType() {
        int type = 0;
        client.setProtocolType(type);
    }




}

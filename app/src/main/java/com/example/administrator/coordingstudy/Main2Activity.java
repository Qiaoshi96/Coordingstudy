package com.example.administrator.coordingstudy;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.coordingstudy.updata.TwoActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.example.administrator.coordingstudy.SharedpreferenceUtils.MAC_ID_KEY;

public class Main2Activity extends BaseActivity {
    private static final int BAIDU_READ_PHONE_STATE = 101;//定位权限请求
    private SharedpreferenceUtils instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        instance = SharedpreferenceUtils.getInstance(Main2Activity.this);
        /**
         * 测试一下回退好不好使
         * GGGG
         */
        /**
         * 获取手机系统的安卓版本
         * 一个月上传一次请求
         */
        long time = System.currentTimeMillis();
        String sp = instance.getSP(MAC_ID_KEY);
        if (sp.equals("") || sp == null) {
            instance.putSP(MAC_ID_KEY, String.valueOf(time));
        } else {
            int i = daysBetween(sp, String.valueOf(time));
            long t = time - Long.valueOf(sp);
            if (i>=30){
                Log.e("MYTAG", "请求了接口" + t + "------" + sp+"============"+i);//超过一个月发送安卓系统版本
            }
        }

        Log.e("Mobile", android.os.Build.MODEL + "-----" + android.os.Build.VERSION.SDK + "-------------" + android.os.Build.VERSION.RELEASE);//获取Android系统版本号
        //判断是否打开定位 6.0以上判断定位信息
        LocationManager lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);

                } else {
                    Location location = LocationUtils.getInstance(Main2Activity.this).showLocation();
                    if (location != null) {
                        String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
                        Log.e("FLY.onCreate", address);
                    }
                }
            } else {
                Location location = LocationUtils.getInstance(Main2Activity.this).showLocation();
                if (location != null) {
                    String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
                    Log.e("FLY.onCreate", address);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //有权限
        //if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED){
        if (requestCode == 101) {
            Location location = LocationUtils.getInstance(Main2Activity.this).showLocation();
            if (location != null) {
                String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
                Log.e("FLY.onCreate", address);
            }
        }
      /*  if (grantResults[0] == PERMISSION_GRANTED && grantResults.length > 0) {  //有权限
            Location location = LocationUtils.getInstance(Main2Activity.this).showLocation();
            if (location != null) {
                String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
                Log.e("FLY.onCreate", address);
            }
             }*/
    }

    /**
     * 返回两个string类型日期之间相差的天数
     */
    public static int daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public void onClick(View view) {
        startActivity(new Intent(this, TwoActivity.class));
    }
}

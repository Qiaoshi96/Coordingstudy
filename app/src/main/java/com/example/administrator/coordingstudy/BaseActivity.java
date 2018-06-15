package com.example.administrator.coordingstudy;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity {
    private boolean isStopCount = false;

    private boolean isPause = true;
    private Button button;
    private Handler mHandler = new Handler();
    private long timer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //对用户可见时开始计时
    @Override
    protected void onResume() {
        super.onResume();
        //启动计时

        isPause = true;
        isStopCount = false;
        countTimer();

    }
    //页面对用户不可见时停止倒计时
    @Override
    protected void onPause() {
        super.onPause();
        isPause = false;
        isStopCount = true;
    }
    //销毁时停止计时并保存该Activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(TimerRunnable);
    }

    private void countTimer(){
        mHandler.postDelayed(TimerRunnable, 1000);
    }
    private Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {
            if (!isStopCount){
                timer += 1000;
                Log.e("TAG___",timer+"---"+getRunningActivityName());
                countTimer();
            }

        }
    };


    private String getRunningActivityName(){
        ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}

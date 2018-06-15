package com.example.administrator.coordingstudy;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 创建协调布局的工具类
 */


public class MainActivity extends AppCompatActivity {
    private boolean isStopCount = false;

    private boolean isPause = true;
    private Button button;
    private Handler mHandler = new Handler();

    private long timer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.otherActivity);
        //创建集合获取map集合中的对象
        List<List<String>> mainList=new ArrayList<>();

        //创建集合对象并存入值
        HashMap<Integer, List<String>> list = new HashMap<>();
        List<String> one = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            one.add("one" + i);
        }
        List<String> two = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            two.add("two" + i);
        }
        List<String> three = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            three.add("three" + i);
        }
        //存入对象
        list.put(0, one);
        list.put(1, two);
        list.put(2, three);

        Set set = list.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer it = (Integer) iterator.next();//key值
            List<String> value = list.get(it);//通过key值拿到value
            Log.e("MYTAG",value.size()+"================");
            mainList.add(value);
        }
        //遍历mainlist
        for (int i=0;i<mainList.size();i++){
            String s = mainList.get(i).toString();
            Log.e("MainList",s);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });


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

    private void countTimer() {
        mHandler.postDelayed(TimerRunnable, 1000);
    }

    private Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {
            if (!isStopCount) {
                timer += 1000;
                Log.e("TAG___", timer + "---" + getRunningActivityName());
                countTimer();
            }

        }
    };


    private String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}

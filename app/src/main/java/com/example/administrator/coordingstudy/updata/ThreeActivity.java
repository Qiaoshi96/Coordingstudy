package com.example.administrator.coordingstudy.updata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.coordingstudy.BaseActivity;
import com.example.administrator.coordingstudy.Main2Activity;
import com.example.administrator.coordingstudy.R;

public class ThreeActivity extends BaseActivity {
    /**
     * 第二次上传
     * @param savedInstanceState
     *
     * 这个是本地发
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}

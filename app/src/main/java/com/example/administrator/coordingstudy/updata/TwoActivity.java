package com.example.administrator.coordingstudy.updata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.coordingstudy.BaseActivity;
import com.example.administrator.coordingstudy.R;

public class TwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //加载Fragmentfram
        //再次提交
        OneFragment oneFragment = new OneFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fram,oneFragment).commit();

    }

    public void onClick(View view) {
        startActivity(new Intent(this,ThreeActivity.class));
    }
}

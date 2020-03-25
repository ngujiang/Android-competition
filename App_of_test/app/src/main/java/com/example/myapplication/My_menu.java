package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class My_menu extends AppCompatActivity {
    private Button B1;
    private Button B2;
    private Button B3;
    private Button B4;

    protected boolean statusBarCompat = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        if (statusBarCompat) {

            StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimary));

            transparent19and20();

        }

        B1=(Button)findViewById(R.id.button);
        B2=(Button)findViewById(R.id.button2);
        B3=(Button)findViewById(R.id.button3);
        B4=(Button)findViewById(R.id.button4);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++跳转到自定义网络界面
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(My_menu.this,choose_parameters.class));
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++跳转到快速上手指南界面
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(My_menu.this,quick_start_guide.class));
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++跳转到学习相关知识界面
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(My_menu.this,learn_knowledge.class));
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++跳转到关于我们界面
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(My_menu.this, about_us.class));
            }
        });
    }
    protected void transparent19and20() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

}





















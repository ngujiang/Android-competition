package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;


public class choose_parameters extends AppCompatActivity {
    private NumberPicker N1;
    private Button B1;
    private ImageView I1;
    public static int layer_number;
    public static int L1_number;
    public static int L2_number;
    public static int L3_number;
    public static int L4_number;
    public static double learning_rate;
    public static int sample_number;
    public static int trains_number;
    public static int activation_funcation;
    public static int gradient_descent;
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static double right_rate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_parameters);
        N1=(NumberPicker)findViewById(R.id.numberPicker);
        B1=(Button)findViewById(R.id.button5);
        I1=(ImageView)findViewById(R.id.imageview);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++设置神经层数的范围
        N1.setMaxValue(4);
        N1.setMinValue(2);


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++跳转到选择神经元的界面并且保存神经元层数的数据
       I1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new AlertDialog.Builder(choose_parameters.this)
                       .setTitle("选择神经网络层数")
                       .setMessage("请结合您手机的性能来选择神经网络层数")
                       .setPositiveButton("我知道了", null)
                       .show();
           }
       });


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layer_number=N1.getValue();
                if(layer_number==2)
                {
                    startActivity(new Intent(choose_parameters.this,choose_parameters2_1.class));
                }

                if(layer_number==3)
                {
                    startActivity(new Intent(choose_parameters.this,choose_parameters2_2.class));
                }

                if (layer_number==4)
                {
                    startActivity(new Intent(choose_parameters.this,choose_parameters2_3.class));
                }
            }
        });


    }
}
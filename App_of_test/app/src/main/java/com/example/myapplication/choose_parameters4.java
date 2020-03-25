package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class choose_parameters4 extends AppCompatActivity{
    private Spinner spinner;
    private List<String> data_list_;
    private ArrayAdapter<String> arr_adapter_;
    private Switch S1;
    private Switch S2;
    private Switch S3;
    private Button B1;
    private ImageView I1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_parameters4);
        spinner = (Spinner) findViewById(R.id.spinner2);
        S1=(Switch)findViewById(R.id.switch1);
        S2=(Switch)findViewById(R.id.switch2);
        S3=(Switch)findViewById(R.id.switch3);
        B1=(Button)findViewById(R.id.button9);
        I1=(ImageView)findViewById(R.id.imageView);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++选择梯度下降的方法

        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(choose_parameters4.this)
                        .setTitle("选择神经网络的相关方法")
                        .setMessage("您可以在这个界面选择一些神经网络的相关学习方法，以此来增加学习的速度，或防止过饱和或参数更新过慢等现象")
                        .setPositiveButton("我知道了", null)
                        .show();
            }
        });


        data_list_ = new ArrayList<String>();
        data_list_.add("SGD");
        data_list_.add("Momentoum");
        data_list_.add("AdaGrad");
        data_list_.add("Adam");
        arr_adapter_= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list_);
        arr_adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter_);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++存储数据转到训练界面
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem()=="SGD")
                {
                    choose_parameters.gradient_descent=0;
                }
                else if (spinner.getSelectedItem()=="Momentoum")
                {
                    choose_parameters.gradient_descent=1;
                }
                else
                {
                    choose_parameters.gradient_descent=2;
                }
                choose_parameters.a=S1.isChecked();
                choose_parameters.b=S2.isChecked();
                choose_parameters.c=S3.isChecked();
                startActivity(new Intent(choose_parameters4.this,trains_and_test.class));
            }
        });
    }
}
package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class choose_parameters3 extends AppCompatActivity {

    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private EditText E1;
    private EditText E2;
    private EditText E3;
    private Button B1;
    private ImageView I1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_parameters3);
        spinner = (Spinner) findViewById(R.id.spinner);
        E1=(EditText)findViewById(R.id.editText6);
        E2=(EditText)findViewById(R.id.editText7);
        E3=(EditText)findViewById(R.id.editText8);
        B1=(Button)findViewById(R.id.button8_1);
        I1=(ImageView)findViewById(R.id.imageView2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++添加激活函数
        data_list = new ArrayList<String>();
        data_list.add("sigmoid(推荐)");
        data_list.add("ReLU(严重不推荐)");
        data_list.add("loglog");
        data_list.add("Hardsigmoid");
        data_list.add("Gaussian");
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter);
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(choose_parameters3.this)
                        .setTitle("选择网络学习的相关属性")
                        .setMessage("您可以在此界面自定义一些网路学习的基本属性，您需要权衡样本数量的多少，并且结合您手机的实际性能来合理的选择这些基本属性")
                        .setPositiveButton("我知道了", null)
                        .show();
            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*choose_parameters.learning_rate=Double.parseDouble(E1.getText().toString());
                choose_parameters.sample_number=Integer.parseInt(E2.getText().toString());
                choose_parameters.trains_number=Integer.parseInt(E3.getText().toString());*/
                if (spinner.getSelectedItem()=="sigmoid(推荐)")
                {
                    choose_parameters.activation_funcation=0;
                }
                else if(spinner.getSelectedItem()=="ReLU(严重不推荐)")
                {
                    choose_parameters.activation_funcation=1;
                }
                else if(spinner.getSelectedItem()=="loglog")
                {
                    choose_parameters.activation_funcation=2;
                }
                else if(spinner.getSelectedItem()=="Hardsigmoid")
                {
                    choose_parameters.activation_funcation=3;
                }
                else
                {
                    choose_parameters.activation_funcation=4;
                }

                if (E1.getText().toString().length()!=0 && E2.getText().toString().length()!=0&&E3.getText().toString().length()!=0)
                {
                    choose_parameters.learning_rate=Double.parseDouble(E1.getText().toString());
                    choose_parameters.sample_number=Integer.parseInt(E2.getText().toString());
                    choose_parameters.trains_number=Integer.parseInt(E3.getText().toString());
                    startActivity(new Intent(choose_parameters3.this,choose_parameters4.class));}

                else{
                    Toast.makeText(choose_parameters3.this, "请输入完整信息", Toast.LENGTH_SHORT).show();}
            }
        });

    }
}
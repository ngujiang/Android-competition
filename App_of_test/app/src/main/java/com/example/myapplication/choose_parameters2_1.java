package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class choose_parameters2_1 extends AppCompatActivity{

    private EditText E1;
    private EditText E2;
    private Button B1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_parameters2_1);

        E1=(EditText)findViewById(R.id.editText);
        E2=(EditText)findViewById(R.id.editText2);
        B1=(Button)findViewById(R.id.button6);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++获取两层神经元的个数并且跳转到下一个界面
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*choose_parameters.L1_number=Integer.parseInt(E1.getText().toString());
                choose_parameters.L2_number=Integer.parseInt(E2.getText().toString());*/
               if (E1.getText().toString().length()!=0 && E2.getText().toString().length()!=0)
               { choose_parameters.L1_number=Integer.parseInt(E1.getText().toString());
                    choose_parameters.L2_number=Integer.parseInt(E2.getText().toString());
                startActivity(new Intent(choose_parameters2_1.this,choose_parameters3.class));}

               else{

                    Toast.makeText(choose_parameters2_1.this, "请输入完整信息", Toast.LENGTH_SHORT).show();}
           }
        });
    }
}
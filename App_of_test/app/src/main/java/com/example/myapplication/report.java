package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class report extends AppCompatActivity {
    private Button B1;
    private TextView T1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        T1=(TextView)findViewById(R.id.textView21);
        B1=(Button)findViewById(R.id.button12);
        if (choose_parameters.right_rate<=35)
        {
            T1.setText("您创造的神经网络训练数据的正确率为："+choose_parameters.right_rate+"%"
                    +"\n\n"+"检测到您使用了"+choose_parameters.layer_number+"层神经网络"+
                    "\n\n"+"训练样本为"+choose_parameters.trains_number+
                    "\n\n"+"恭喜你获得称号 ———— 机器学习菜鸡！！");
        }

        else if(choose_parameters.right_rate>35 && choose_parameters.right_rate<=60)
        {
            T1.setText("您创造的神经网络训练数据的正确率为："+choose_parameters.right_rate+"%"
                    +"\n\n"+"检测到您使用了"+choose_parameters.layer_number+"层神经网络"+
                    "\n\n"+"训练样本为"+choose_parameters.trains_number+
                    "\n\n"+"恭喜你获得称号 ———— 机器学习小天才！！");
        }

        if (choose_parameters.right_rate>60)
        {
            T1.setText("您创造的神经网络训练数据的正确率为："+choose_parameters.right_rate+"%"
                    +"\n\n"+"检测到您使用了"+choose_parameters.layer_number+"层神经网络"+
                    "\n\n"+"训练样本为"+choose_parameters.trains_number+
                    "\n\n"+"恭喜你获得称号 ———— 机器学习大师");
        }
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trains_and_test.N1.testDigitalImage();
                trains_and_test.N1.testDigitalImage();
                startActivity(new Intent(report.this, paint_control.class));

            }
        });
    }
}
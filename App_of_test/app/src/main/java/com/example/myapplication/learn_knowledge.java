package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class learn_knowledge extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_knowledge);

        b1=(Button)findViewById(R.id.button14);
        b2=(Button)findViewById(R.id.button15);
        b3=(Button)findViewById(R.id.button16);
        b4=(Button)findViewById(R.id.button17);
        b5=(Button)findViewById(R.id.button18);
        b6=(Button)findViewById(R.id.button19);
        b7=(Button)findViewById(R.id.button20);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn1.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn2.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn3.class));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn4.class));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn5.class));
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,learn6.class));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_knowledge.this,My_menu.class));
            }
        });
    }
}
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class start_image extends AppCompatActivity {
    private TextView T1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_image);

        T1=(TextView)findViewById(R.id.textView29);

        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(start_image.this, My_menu.class));
            }
        });


    }
}
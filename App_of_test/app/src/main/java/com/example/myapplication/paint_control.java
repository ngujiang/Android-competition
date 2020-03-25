package com.example.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class paint_control extends AppCompatActivity {

    public static ImageView mIVSign;
    public static TextView mTVSign;
    public static Bitmap mSignBitmap;
    private TextView T1;
    private Button B1;


    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint_control);

        mIVSign = (ImageView) findViewById(R.id.iv_sign);
        mTVSign = (TextView) findViewById(R.id.tv_sign);
        T1=(TextView)findViewById(R.id.textView20);
        B1=(Button)findViewById(R.id.button13);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(paint_control.this,My_menu.class));

            }
        });



        mTVSign.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view) {

                WritePadDialog mWritePadDialog = new WritePadDialog(

                        paint_control.this, new WriteDialogListener() {

                    @Override

                    public void onPaintDone(Object object) {

                        mSignBitmap = (Bitmap) object;



                        mIVSign.setImageBitmap(mSignBitmap);

                        Toast.makeText(paint_control.this,"非常好",Toast.LENGTH_SHORT).show();
                       int i=predict();


                        if (i==-1)
                        {T1.setText("结果偏差过大，请重新输入");}
                        else {

                        T1.setText("预计的结果是"+i);
                        }
                    }
                });

                mWritePadDialog.show();
                ;
            }
        });
    }




    private int predict()
    {
        double[] argb=new double[784];
        for (int weight=0;weight<28;weight++)
        {
            for (int height=0;height<28;height++)
            {
                argb[height*28+weight]=(double)mSignBitmap.getPixel(weight*10,height*10);
            }

        }
        Matrix m=new Matrix();
        m.postScale((float)0.1,(float)0.1);
        /*mSignBitmap=Bitmap.createBitmap(mSignBitmap,0,0,mSignBitmap.getWidth(),mSignBitmap.getHeight(),m,true);
       // mSignBitmap=mSignBitmap.createBitmap(mSignBitmap,0,0,28,28);
        for(int weight=0;weight<28;weight++)
        {
            for (int height=0;height<28;height++)
            {
                int clr = mSignBitmap.getPixel(weight,height);
                int red = (clr & 0x00ff0000) >> 16; // 取高两位
                int green = (clr & 0x0000ff00) >> 8; // 取中两位
                int blue = clr & 0x000000ff;
                argb[height*28+weight]=(double)((red+green+blue)/3);
            }
        }*/

      for (int n=0;n<argb.length;n++)
        {
            if (argb[n]!=0)
            {
                argb[n]=200;
            }
        }

        for(int n=0;n<argb.length;n++)
        {
            if(argb[n]!=0) {
                System.out.print('*');
            }
            else {
                System.out.print(' ');
            }
            if((n+1)%28==0)
            {System.out.print('\n');}


        }

        trains_and_test.N1.setInput(argb);
        System.out.println("预测试"+trains_and_test.N1.testDigitalImage());
        return 2;//trains_and_test.N1.testDigitalImage();
    }
    //创建签名文件

 /*  private int createSignFile() {

        ByteArrayOutputStream baos = null;
       FileOutputStream fos = null;
      String path = null;
        File file = null;
       try {
            path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
            mTVSign.setText(path);
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            if (b != null) {
                fos.write(b);
            }
          // return b.length;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();

                }

                if (baos != null) {

                    baos.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }*/

}


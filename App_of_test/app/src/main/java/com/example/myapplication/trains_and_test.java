package com.example.myapplication;



import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;





//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经元


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经层

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经网络

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++读取文件


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++主函数++++++++++++++++++++++
public class trains_and_test extends AppCompatActivity {
    private TextView T1;
    private TextView T2;
    private Button B1;
    private Button B2;
    private ScrollView S1;
    public static NeuralNetwork N1;
    static List<DigitImage> trains = null ;
    static List<DigitImage> tests = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trains_and_test);
        T1=(TextView)findViewById(R.id.textView19);
        T2=(TextView)findViewById(R.id.textView5);
        B1=(Button)findViewById(R.id.button10);
        B2=(Button)findViewById(R.id.button11);

        T1.setMovementMethod(ScrollingMovementMethod.getInstance());
        int offset=T1.getLineCount()*T1.getLineHeight();
        if(offset>T1.getLineHeight())
        {
            T1.scrollTo(0,offset-T1.getHeight());
        }

       B2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(choose_parameters.layer_number==2)
               {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           //Looper.prepare();
                           output1(choose_parameters.L1_number,choose_parameters.L2_number,
                                   choose_parameters.sample_number,choose_parameters.learning_rate,choose_parameters.trains_number);
                           //N1.testDigitalImage();
                           //Looper.loop();
                       }
                   }).start();

               }
               else if (choose_parameters.layer_number==3)
               {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           //Looper.prepare();
                           output2(choose_parameters.L1_number,choose_parameters.L2_number,choose_parameters.L3_number,
                                   choose_parameters.sample_number,choose_parameters.learning_rate,choose_parameters.trains_number);
                           //Looper.loop();
                       }
                   }).start();

               }
               else
               {
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           //Looper.prepare();
                           output3(choose_parameters.L1_number, choose_parameters.L2_number, choose_parameters.L3_number, choose_parameters.L4_number,
                                   choose_parameters.sample_number, choose_parameters.learning_rate, choose_parameters.trains_number);
                           //Looper.loop();
                       }
                   }).start();
               }
           }
       });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(trains_and_test.this,report.class));
            }
        });


    }





    void output1(int first,int second,int trainsnumber,double updatenumber,int time) {
        //load mnist
        ReadFile rf1=new ReadFile("train_labels.idx1-ubyte","train_images.idx3-ubyte",this);
        ReadFile rf2=new ReadFile("t10k_labels.idx1-ubyte","t10k_images.idx3-ubyte",this);
        try {
            tests = rf2.loadDigitImages();
            trains =rf1.loadDigitImages();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        int size[]={784,first,second,10};
        N1=new NeuralNetwork(size);
       // NeuralNetwork network = new NeuralNetwork(size);
       N1 .radomInitWandB();

        double image[] = new double[784];

        String string = "finished train count: ";
        for(int kk=0;kk<time;kk++){
            //first: set input
            for(int count=0;count<trainsnumber;count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(trains.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                double[] output = N1.forwardProc();
                //third: backward cal delta
                double[] y = new double[10];
                for(int i=0;i<y.length;i++){
                    if(i==trains.get(count).label){
                        y[i] = 1;
                    }else{
                        y[i] = 0;
                    }
                }
                N1.backwarkProc(y);
                //fouth: update w and b
                N1.updateWAndB(updatenumber);
            }
            string+=kk;

            //Toast.makeText(this,"finished train count: "+kk,Toast.LENGTH_SHORT).show();
            System.out.println("finished train count: " + kk);
            Bundle bundle = new Bundle();
            bundle.putString("data","\n"+kk+"是本次训练次数\n请耐心等待.........\n现在已经完成"+kk+"/"+time);
            Message message = Message.obtain();
            message.what = 1;
            message.setData(bundle);
            handler.sendMessage(message);
        }




        boolean isTest = true;
        int countCorrect=0;
        //test
        if(isTest){

            for(int count=0;count<tests.size();count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(tests.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                int number = N1.testDigitalImage();
                if(number==tests.get(count).label)countCorrect++;
                //System.out.println("count is : "+count+"  number is: "+number+"  label is:  "+tests.get(count).label);
            }
            System.out.println("countCorrect: "+countCorrect);
            choose_parameters.right_rate=(countCorrect/10000.0)*100;
        }
        Bundle bundle = new Bundle();
        bundle.putString("data", "正确率是："+String.valueOf((countCorrect/10000.0)*100)+"%");
        Message message = Message.obtain();
        message.what = 1;
        message.setData(bundle);
        handler.sendMessage(message);
    }


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void output2(int first,int second,int third,int trainsnumber,double updatenumber,int time) {
        //load mnist
        ReadFile rf1=new ReadFile("train_labels.idx1-ubyte","train_images.idx3-ubyte",this);
        ReadFile rf2=new ReadFile("t10k_labels.idx1-ubyte","t10k_images.idx3-ubyte",this);
        try {
            tests = rf2.loadDigitImages();
            trains =rf1.loadDigitImages();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        int size[]={784,first,second,third,10};
        N1=new NeuralNetwork(size);
        //NeuralNetwork network = new NeuralNetwork(size);
        N1.radomInitWandB();

        double image[] = new double[784];

        String string = "finished train count: ";
        for(int kk=0;kk<time;kk++){
            //first: set input
            for(int count=0;count<trainsnumber;count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(trains.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                double[] output = N1.forwardProc();
                //third: backward cal delta
                double[] y = new double[10];
                for(int i=0;i<y.length;i++){
                    if(i==trains.get(count).label){
                        y[i] = 1;
                    }else{
                        y[i] = 0;
                    }
                }
                N1.backwarkProc(y);
                //fouth: update w and b
                N1.updateWAndB(updatenumber);
            }
            string+=kk;


            //Toast.makeText(this,"finished train count: "+kk,Toast.LENGTH_SHORT).show();
            System.out.println("finished train count: " + kk);
            Bundle bundle = new Bundle();
            bundle.putString("data","\n"+kk+"是本次训练次数\n请耐心等待.........\n现在已经完成"+kk+"/"+time);
            Message message = Message.obtain();
            message.what = 1;
            message.setData(bundle);
            handler.sendMessage(message);
        }




        boolean isTest = true;
        int countCorrect=0;
        //test
        if(isTest){

            for(int count=0;count<tests.size();count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(tests.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                int number = N1.testDigitalImage();
                if(number==tests.get(count).label)countCorrect++;
                //System.out.println("count is : "+count+"  number is: "+number+"  label is:  "+tests.get(count).label);
            }
            System.out.println("countCorrect: "+countCorrect);
            choose_parameters.right_rate=(countCorrect/10000.0)*100;
        }
        // output.setText("正确率是："+String.valueOf((countCorrect/10000.0)*100)+"%");
        Bundle bundle = new Bundle();
        bundle.putString("data", "正确率是："+String.valueOf((countCorrect/10000.0)*100)+"%");
        Message message = Message.obtain();
        message.what = 1;
        message.setData(bundle);
        handler.sendMessage(message);
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    void output3(int first,int second,int third,int forth ,int trainsnumber,double updatenumber,int time) {
        //load mnist
        ReadFile rf1=new ReadFile("train_labels.idx1-ubyte","train_images.idx3-ubyte",this);
        ReadFile rf2=new ReadFile("t10k_labels.idx1-ubyte","t10k_images.idx3-ubyte",this);
        try {
            tests = rf2.loadDigitImages();
            trains =rf1.loadDigitImages();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        int size[]={784,first,second,third,forth,10};
        N1=new NeuralNetwork(size);
        //NeuralNetwork network = new NeuralNetwork(size);
        N1.radomInitWandB();

        double image[] = new double[784];

        String string = "finished train count: ";
        for(int kk=0;kk<time;kk++){
            //first: set input
            for(int count=0;count<trainsnumber;count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(trains.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                double[] output = N1.forwardProc();
                //third: backward cal delta
                double[] y = new double[10];
                for(int i=0;i<y.length;i++){
                    if(i==trains.get(count).label){
                        y[i] = 1;
                    }else{
                        y[i] = 0;
                    }
                }
                N1.backwarkProc(y);
                //fouth: update w and b
                N1.updateWAndB(updatenumber);
            }
            string+=kk;


            //Toast.makeText(this,"finished train count: "+kk,Toast.LENGTH_SHORT).show();
            System.out.println("finished train count: " + kk);
            Bundle bundle = new Bundle();
            bundle.putString("data","第"+kk+"次训练完成\n请耐心等待.........\n\n现在已经完成"+kk+"/"+time);
            Message message = Message.obtain();
            message.what = 1;
            message.setData(bundle);
            handler.sendMessage(message);


        }




        boolean isTest = true;
        int countCorrect=0;
        //test
        if(isTest){

            for(int count=0;count<tests.size();count++){
                for(int i=0;i<784;i++){
                    image[i] = (int)(tests.get(count).imageData[i]&0xff);
                }
                N1.setInput(image);
                //second: forward cal output
                int number = N1.testDigitalImage();
                if(number==tests.get(count).label)countCorrect++;
                //System.out.println("count is : "+count+"  number is: "+number+"  label is:  "+tests.get(count).label);
            }
            System.out.println("countCorrect: "+countCorrect);
            choose_parameters.right_rate=(countCorrect/10000.0)*100;
        }
        // output.setText("正确率是："+String.valueOf((countCorrect/10000.0)*100)+"%");
        Bundle bundle = new Bundle();
        bundle.putString("data", "正确率是："+String.valueOf((countCorrect/10000.0)*100)+"%");
        Message message = Message.obtain();
        message.what = 1;
        message.setData(bundle);
        handler.sendMessage(message);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().getString("data");
            System.out.println(data);

                T1.append(data);
               /* if (data.charAt(0)=='第')
                {T2.setText("正在训练数据" +
                        "请勿重复点击开始按钮");}*/



            //Toast.makeText(getApplication(),data+"",Toast.LENGTH_SHORT).show();
        }
    };
}



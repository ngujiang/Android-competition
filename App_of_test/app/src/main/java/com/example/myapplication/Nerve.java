package com.example.myapplication;


import java.util.Random;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经元


public class Nerve {
    private double []xs;
    private double []ws;
    private double b;
    private double a;
    private double delta;
    private double z;
    public Nerve(int inputSize, int outputSize){
        xs=new double[inputSize];
        ws=new double[inputSize];
    }

    public void setFirstInput(double in){
        if(xs.length>1){
            System.out.println("init xs error");
            return;
        }
        xs[0]=in;
        //System.out.println("ws[i] is : "+ws[0] + " xs[i] is "+xs[0]);
    }
    public void setXs(double[] lxs){
        if(lxs.length != xs.length){
            return;
        }
        for(int i=0;i<xs.length;i++){
            xs[i] = lxs[i];
        }
    }
    public double[] getXs(){
        return xs;
    }
    public void setWs(double[] lws){
        if(lws.length != ws.length){
            return;
        }
        for(int i=0;i<ws.length;i++){
            ws[i] = lws[i];
        }
    }
    public double getWByK(int k){
        return ws[k];
    }
    public void setB(double lb){
        b=lb;
    }
    public double getB(){
        return b;
    }
    private void calZ(){
        if(xs.length != ws.length){
            return;
        }
        z=0;
        for(int i=0;i<xs.length;i++){
            z += ws[i]*xs[i];
            //System.out.println("ws[i] is : "+ws[i] + " xs[i] is "+xs[i]);
        }
        z += b;

    }
    public double getZ(){
        return z;
    }
    public void setZ(double lz){
        z = lz;
    }
    public double calAndGetA(){
        calZ();
        a = 1/(1+Math.exp(-z));
        //System.out.println("z is : "+z + " a is "+a);
        return a;
    }
    public void setDelta(double delta){
        this.delta = delta;
    }
    public double getDelta(){
        return delta;
    }
    public void randomInitWandB(){
        Random random = new Random();
        b=random.nextGaussian();
        for(int i=0;i<ws.length;i++){
            ws[i]=random.nextGaussian();
        }
    }
    public void updateWandB(double eta){
        for(int i=0;i<ws.length;i++){
            ws[i] = ws[i] - eta*xs[i]*delta;
            //System.out.println("ws"+"["+i+"]"+ ws[i]);
        }
        b = b - eta*delta;
    }
}




//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经层

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经网络

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++读取文件



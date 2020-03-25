package com.example.myapplication;


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经元


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经层

public class Layer {
    private Nerve[] nerves;
    private int preSize;
    private int backSize;
    private double [] layerAs;
    private double [] layerZs;
    private double [] layerDelta;
    private double [] layerWks;
    public Layer(int preSize, int size, int backSize){
        nerves = new Nerve[size];
        layerAs = new double[size];
        if(preSize==0)preSize=1;
        if(backSize==0)backSize=1;
        for(int i=0;i<size;i++){
            nerves[i] = new Nerve(preSize,backSize);
        }
        this.preSize = preSize;
        this.backSize = backSize;
        layerAs = new double[size];
        layerZs = new double[size];
        layerDelta = new double[size];
        layerWks = new double[size];
    }
    public void randomInitWandB(){
        for(Nerve nerve:nerves){
            nerve.randomInitWandB();
        }
    }
    public void setZs(double[] zs){
        if(zs.length!=nerves.length){
            System.out.println("zs.length!=nerves.length,zs.length: "+zs.length + " nerves.length: "+nerves.length);
            return;
        }
        for(int i=0;i<zs.length;i++){
            nerves[i].setZ(zs[i]);
        }
    }
    public void setXs(double[] xs){
        for(int i=0;i<nerves.length;i++){
            nerves[i].setXs(xs);
        }
    }
    public double [] getXs(){
        return nerves[0].getXs();
    }
    public void setFirstInput(double[] xs){
        for(int i=0;i<nerves.length;i++){
            nerves[i].setFirstInput(xs[i]);
        }
    }
    public double[] calAndGetlayerAs(){
        for(int i=0;i<nerves.length;i++){
            layerAs[i] = nerves[i].calAndGetA();
        }
        return layerAs;
    }

    public double[] getZs(){
        for(int i=0;i<nerves.length;i++){
            layerZs[i] = nerves[i].getZ();
        }
        return layerZs;
    }

    public double getWByJK(int j,int k){
        return nerves[j].getWByK(k);
    }
    public double[] calDeltas(boolean isOutputLayer,double[] deltas,double[] zs,double[] y){
        if(isOutputLayer){
            for(int i=0;i<nerves.length;i++){
                double z = nerves[i].getZ();
                double a = nerves[i].calAndGetA();
                double delta = (a-y[i])*(Math.exp(-z))/((1+Math.exp(-z))*(1+Math.exp(-z)));
                nerves[i].setDelta(delta);
                //System.out.println("y"+"["+i+"]"+" is "+y[i]+" ,a is "+a+" ,delta is "+delta+"diff: "+(Math.abs(y[i]-a)));
            }
            return null;

        }else{
            double preLayerDelta[] = new double[preSize];
            for(int i=0;i<preSize;i++){
                for(int k=0;k<nerves.length;k++){
                    layerWks[k]=nerves[k].getWByK(i);
                }
                double delta = 0;
                for(int k=0;k<nerves.length;k++){
                    delta += deltas[k]*layerWks[k]*(Math.exp(-zs[i]))/((1+Math.exp(-zs[i]))*(1+Math.exp(-zs[i])));
                }
                //System.out.println("delta: "+delta);
                preLayerDelta[i]=delta;
            }
            return preLayerDelta;
        }
    }
    public double[] getLayerDeltas(){
        for(int i=0;i<nerves.length;i++){
            layerDelta[i] = nerves[i].getDelta();
        }
        return layerDelta;
    }

    public void setLayerDeltas(double[] deltas){
        for(int i=0;i<nerves.length;i++){
            nerves[i].setDelta(deltas[i]);
        }
    }
    public double[] getLayerWsByK(int k){
        for(int j=0;j<nerves.length;j++){
            layerWks[j] = nerves[j].getWByK(k);
        }
        return layerWks;
    }
    public void updateWandB(double eta){
        for(int i=0;i<nerves.length;i++){
            nerves[i].updateWandB(eta);
        }
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经网络

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++读取文件



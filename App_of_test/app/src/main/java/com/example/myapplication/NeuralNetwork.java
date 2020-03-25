package com.example.myapplication;


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经元


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经层

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++神经网络

public class NeuralNetwork {
    private Layer[] layers;
    public NeuralNetwork(int[] size){
        layers = new Layer[size.length];
        for(int i=0;i<layers.length;i++){
            if(i==0){
                layers[i]=new Layer(0,size[i],size[i+1]);
            }else if(i==layers.length-1){
                layers[i]=new Layer(size[i-1],size[i],0);
            }else{
                layers[i]=new Layer(size[i-1],size[i],size[i+1]);
            }

        }
    }
    public void radomInitWandB(){
        for(Layer layer:layers){
            layer.randomInitWandB();
        }
    }
    public void setInput(double[] xs){
        layers[0].setFirstInput(xs);
    }
    public double[] forwardProc(){
        for(int i=0;i<layers.length-1;i++){
            double layerAs[] = layers[i].calAndGetlayerAs();
            layers[i+1].setXs(layerAs);
//			for(int z=0;z<layerAs.length;z++){
//				System.out.println("layer"+"["+i+"]: "+"  as"+"["+z+"]="+layerAs[z]);
//			}
            double[] las = layers[i+1].getXs();
//			for(int z=0;z<las.length;z++){
//				System.out.println("layer"+"["+i+"]: "+"  xs"+"["+z+"]="+las[z]);
//			}
        }

        double layerOutputAs[] = layers[layers.length-1].calAndGetlayerAs();
        return layerOutputAs;
    }

    public int testDigitalImage(){
        for(int i=0;i<layers.length-1;i++){
            double layerAs[] = layers[i].calAndGetlayerAs();
            layers[i+1].setXs(layerAs);
        }
        double layerOutputAs[] = layers[layers.length-1].calAndGetlayerAs();
        for(int i=0;i<layerOutputAs.length;i++){
            if(Math.abs(1-layerOutputAs[i])<0.5)return i;
        }
        return -1;
    }

    public void backwarkProc(double[] y){
        layers[layers.length-1].calDeltas(true, null,null,y);
        for(int k=0;k<layers.length-2;k++){
            double[] deltas = layers[layers.length-k-1].getLayerDeltas();
//			for(int z=0;z<deltas.length;z++){
//				System.out.println("layer"+"["+k+"]: "+"  delta"+"["+z+"]="+deltas[z]);
//			}
            double[] zs = layers[layers.length-k-2].getZs();
//			for(int z=0;z<zs.length;z++){
//				System.out.println("layer"+"["+k+"]: "+"  zs"+"["+z+"]="+zs[z]);
//			}
            double[] preDeltas = layers[layers.length-k-1].calDeltas(false, deltas,zs, y);
//			for(int z=0;z<preDeltas.length;z++){
//				System.out.println("layer"+"["+k+"]: "+"  preDeltas"+"["+z+"]="+preDeltas[z]);
//			}
            layers[layers.length-k-2].setLayerDeltas(preDeltas);
        }
    }
    public void updateWAndB(double eta){//learn speed
        for(int i=0;i<layers.length;i++){
            layers[i].updateWandB(eta);
        }
    }
}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++读取文件



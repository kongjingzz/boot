package com.crell.common.test;

/**
 * Created by crell on 17/2/8.
 */
public class SubThread extends Thread{

    private final int i;
    public SubThread(int i){
        this.i = i;
    }
    @Override
    public void run(){
        System.out.println(i);
    }
}

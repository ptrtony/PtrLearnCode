package com.zhaofan.more_thread_execute;

public class Synchronized2Demo implements TestDemo{
    private int x = 0;
    public synchronized void count(){
        x++;
    }
    @Override
    public void runTest() {
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000_000_000;i++){
                    count();
                }
                System.out.println("final x from 1:"+x);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<1000_000_000;i++){
                    count();
                }
                System.out.println("final x from 2:" +x);
            }
        }.start();
    }
}

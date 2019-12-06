package com.zhaofan.inter_thread_communication;

public class WaitDemo implements TestDemo{

    private String shareString;
    private synchronized void initString(){
        shareString = "hello android thread";
        notify();
    }

    private synchronized void printlnString(){
        while (shareString == null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("String:"+shareString);
    }
    @Override
    public void runTest() {
        final  Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printlnString();
            }
        });
        thread1.start();

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                initString();
            }
        });
        thread2.start();

    }
}

package com.zhaofan.inter_thread_communication;

public class CustomizableThreadDemo implements TestDemo{
    private CustomizableThread thread = new CustomizableThread();
    @Override
    public void runTest() {
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello ptr");
            }
        });

        try {
            Thread.sleep(2000);
            thread.looper.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    class CustomizableThread extends Thread {
        Looper looper = new Looper();
        @Override
        public void run() {
            looper.loop();
        }
    }

    class Looper{

        void loop(){
            while (!quit){
                synchronized (this){
                    if (task!=null){
                        task.run();
                    }
                }
            }
        }
        private Runnable task;
        private volatile boolean quit;
        synchronized void setTask(Runnable task){
            this.task = task;
        }

        void quit(){
            quit = true;
        }

    }
}

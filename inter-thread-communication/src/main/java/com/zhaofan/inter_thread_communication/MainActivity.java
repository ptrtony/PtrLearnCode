package com.zhaofan.inter_thread_communication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private static final String MONITOR = "MainActivity";
    private ThreadLocal<Integer> threadLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadLocal = new ThreadLocal<>();


        thread1().start();
        thread2().start();


        Handler handler = new Handler();
        handler.getLooper();

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

    }

    private class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }



    synchronized void methodA(){
        //......
    }

    void methodB(){
        synchronized (MainActivity.class){

        }
    }

    synchronized void methodC(){
        //....
    }

    private Thread thread1(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                //线程1
                threadLocal.set(1);
                threadLocal.get();
            }
        });
    }


    private Thread thread2(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                //线程2
                threadLocal.set(2);
                threadLocal.get();
            }
        });
    }



}

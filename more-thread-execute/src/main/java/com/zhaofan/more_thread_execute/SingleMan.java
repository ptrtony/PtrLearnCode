package com.zhaofan.more_thread_execute;

public class SingleMan {
    private static volatile SingleMan singleMan;
    public static SingleMan newInstance(){
        if (singleMan == null){
            synchronized (SingleMan.class){
                if (singleMan == null){
                    singleMan = new SingleMan();
                }
            }
        }
        return singleMan;
    }
}

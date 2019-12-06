package com.zhaofan.inter_thread_communication;

import android.os.HandlerThread;

public class HandleThreadDemo extends HandlerThread {
    public HandleThreadDemo(String name) {
        super(name);
    }

    public HandleThreadDemo(String name, int priority) {
        super(name, priority);
    }
}

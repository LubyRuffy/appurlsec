package com.baidu.mobstat;

import com.baidu.mobstat.a.c;

class s implements Runnable {
    final /* synthetic */ q a;

    s(q r1_q) {
        this.a = r1_q;
    }

    public void run() {
        if (!j.a().c()) {
            synchronized (j.a()) {
                try {
                    j.a().wait();
                } catch (InterruptedException e) {
                    c.a("stat", e);
                }
            }
        }
    }
}
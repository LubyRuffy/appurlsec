package com.tencent.plus;

// compiled from: ProGuard
class d implements Runnable {
    final /* synthetic */ TouchView a;

    d(TouchView r1_TouchView) {
        this.a = r1_TouchView;
    }

    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a.post(new c(this));
        this.a.i = false;
    }
}
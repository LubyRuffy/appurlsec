package qsbk.app.activity;

// compiled from: SingleArticle.java
class cq implements Runnable {
    final /* synthetic */ SingleArticle a;

    cq(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void run() {
        this.a.f();
        this.a.ac = true;
    }
}
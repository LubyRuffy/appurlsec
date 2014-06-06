package qsbk.app.activity;

// compiled from: ImageViewer.java
class ao extends Thread {
    final /* synthetic */ ImageViewer a;

    ao(ImageViewer r1_ImageViewer, String r2_String) {
        this.a = r1_ImageViewer;
        super(r2_String);
    }

    public void run() {
        this.a.g();
    }
}
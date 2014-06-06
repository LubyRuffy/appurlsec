package qsbk.app.utils;

// compiled from: FileUtils.java
final class e extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    e(String r1_String, String r2_String, String r3_String, String r4_String) {
        this.a = r2_String;
        this.b = r3_String;
        this.c = r4_String;
        super(r1_String);
    }

    public void run() {
        if (this.a != null) {
            DebugUtil.debug("\u7f13\u5b58\u5230\u5185\u5b58");
        }
        if (this.b != null) {
            DebugUtil.debug("\u7f13\u5b58\u5230SDcard");
            FileUtils.saveContent(this.b, this.c);
        }
        super.run();
    }
}
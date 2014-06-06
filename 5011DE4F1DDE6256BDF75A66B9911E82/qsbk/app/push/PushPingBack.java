package qsbk.app.push;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class PushPingBack implements Serializable {
    public static int messageState;
    Handler a;
    private final Timer b;
    private int c;
    private TimerTask d;

    static {
        messageState = 0;
    }

    public PushPingBack() {
        this.b = new Timer();
        this.c = -1;
        this.a = new b(this);
        this.d = new c(this);
    }

    public static boolean isRunningForeground(Context r4_Context) {
        String r0_String = ((RunningTaskInfo) ((ActivityManager) r4_Context.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
        return !TextUtils.isEmpty(r0_String) && r0_String.equals(r4_Context.getPackageName());
    }

    public void receiveMessage() {
        new d(this, "pushPingBack").start();
    }

    public void runningReport(int r3i) {
        new e(this, "runningPushPingBack", r3i).start();
    }

    public void start(int r7i) {
        this.c = r7i;
        this.b.schedule(this.d, 0, 1200000);
    }

    public void stopTimer() {
        this.b.cancel();
    }
}
package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;
import com.tencent.mm.sdk.platformtools.Util;

public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final SparseArray<WakeLock> a;
    private static int b;

    static {
        a = new SparseArray();
        b = 1;
    }

    public static boolean completeWakefulIntent(Intent r6_Intent) {
        int r2i = r6_Intent.getIntExtra("android.support.content.wakelockid", 0);
        if (r2i == 0) {
            return false;
        }
        synchronized (a) {
            WakeLock r0_WakeLock = (WakeLock) a.get(r2i);
            if (r0_WakeLock != null) {
                r0_WakeLock.release();
                a.remove(r2i);
                return true;
            } else {
                Log.w("WakefulBroadcastReceiver", "No active wake lock id #" + r2i);
                return true;
            }
        }
    }

    public static ComponentName startWakefulService(Context r7_Context, Intent r8_Intent) {
        ComponentName r0_ComponentName;
        synchronized (a) {
            int r3i = b;
            b++;
            if (b <= 0) {
                b = 1;
            }
            r8_Intent.putExtra("android.support.content.wakelockid", r3i);
            ComponentName r1_ComponentName = r7_Context.startService(r8_Intent);
            if (r1_ComponentName == null) {
                r0_ComponentName = null;
            } else {
                WakeLock r0_WakeLock = ((PowerManager) r7_Context.getSystemService("power")).newWakeLock(1, "wake:" + r1_ComponentName.flattenToShortString());
                r0_WakeLock.setReferenceCounted(false);
                r0_WakeLock.acquire(Util.MILLSECONDS_OF_MINUTE);
                a.put(r3i, r0_WakeLock);
                r0_ComponentName = r1_ComponentName;
            }
        }
        return r0_ComponentName;
    }
}
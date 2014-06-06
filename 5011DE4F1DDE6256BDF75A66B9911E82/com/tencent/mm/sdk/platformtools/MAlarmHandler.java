package com.tencent.mm.sdk.platformtools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import junit.framework.Assert;
import qsbk.app.utils.image.Utils;

public class MAlarmHandler {
    public static final long NEXT_FIRE_INTERVAL = 9223372036854775807L;
    private static int a;
    private static Map<Integer, MAlarmHandler> f;
    private static IBumper h;
    private static boolean i;
    private final int b;
    private final boolean c;
    private long d;
    private long e;
    private final CallBack g;

    public static interface CallBack {
        public boolean onTimerExpired();
    }

    public static interface IBumper {
        public void cancel();

        public void prepare();
    }

    static {
        f = new HashMap();
        i = false;
    }

    public MAlarmHandler(CallBack r3_CallBack, boolean r4z) {
        this.d = 0;
        this.e = 0;
        Assert.assertTrue("bumper not initialized", i);
        this.g = r3_CallBack;
        this.c = r4z;
        if (a >= Utils.IO_BUFFER_SIZE) {
            a = 0;
        }
        int r0i = a + 1;
        a = r0i;
        this.b = r0i;
    }

    public static long fire() {
        List r10_List = new LinkedList();
        Set r0_Set = new HashSet();
        r0_Set.addAll(f.keySet());
        Iterator r11_Iterator = r0_Set.iterator();
        long r2j = 9223372036854775807L;
        while (r11_Iterator.hasNext()) {
            long r0j;
            Integer r0_Integer = (Integer) r11_Iterator.next();
            MAlarmHandler r1_MAlarmHandler = (MAlarmHandler) f.get(r0_Integer);
            if (r1_MAlarmHandler != null) {
                long r6j = Util.ticksToNow(r1_MAlarmHandler.d);
                if (r6j < 0) {
                    r6j = 0;
                }
                if (r6j > r1_MAlarmHandler.e) {
                    if (r1_MAlarmHandler.g.onTimerExpired() && r1_MAlarmHandler.c) {
                        r2j = r1_MAlarmHandler.e;
                    } else {
                        r10_List.add(r0_Integer);
                    }
                    r1_MAlarmHandler.d = Util.currentTicks();
                } else if (r1_MAlarmHandler.e - r6j < r2j) {
                    r0j = r1_MAlarmHandler.e - r6j;
                    r2j = r0j;
                }
            }
            r0j = r2j;
            r2j = r0j;
        }
        int r0i = 0;
        while (r0i < r10_List.size()) {
            f.remove(r10_List.get(r0i));
            r0i++;
        }
        if (r2j != 9223372036854775807L || h == null) {
            return r2j;
        }
        h.cancel();
        Log.v("MicroMsg.MAlarmHandler", "cancel bumper for no more handler");
        return r2j;
    }

    public static void initAlarmBumper(IBumper r1_IBumper) {
        i = true;
        h = r1_IBumper;
    }

    protected void finalize() {
        stopTimer();
        super.finalize();
    }

    public void startTimer(long r13j) {
        this.e = r13j;
        this.d = Util.currentTicks();
        long r7j = this.e;
        Log.d("MicroMsg.MAlarmHandler", new StringBuilder("check need prepare: check=").append(r7j).toString());
        Iterator r9_Iterator = f.entrySet().iterator();
        long r1j = 9223372036854775807L;
        while (r9_Iterator.hasNext()) {
            long r0j;
            MAlarmHandler r0_MAlarmHandler = (MAlarmHandler) ((Entry) r9_Iterator.next()).getValue();
            if (r0_MAlarmHandler != null) {
                long r3j = Util.ticksToNow(r0_MAlarmHandler.d);
                if (r3j < 0) {
                    r3j = 0;
                }
                if (r3j > r0_MAlarmHandler.e) {
                    r1j = r0_MAlarmHandler.e;
                } else if (r0_MAlarmHandler.e - r3j < r1j) {
                    r0j = r0_MAlarmHandler.e - r3j;
                    r1j = r0j;
                }
            }
            r0j = r1j;
            r1j = r0j;
        }
        int r0i = (r1j > r7j ? 1 : (r1j == r7j? 0 : -1)) > 0 ? 1 : 0;
        stopTimer();
        f.put(Integer.valueOf(this.b), this);
        if (h == null || r0i == 0) {
        } else {
            Log.v("MicroMsg.MAlarmHandler", "prepare bumper");
            h.prepare();
        }
    }

    public void stopTimer() {
        f.remove(Integer.valueOf(this.b));
    }

    public boolean stopped() {
        return !f.containsKey(Integer.valueOf(this.b));
    }
}
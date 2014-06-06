package com.tencent.mm.sdk.platformtools;

import android.os.SystemClock;
import java.util.ArrayList;

public class TimeLogger {
    ArrayList<Long> a;
    ArrayList<String> b;
    private String c;
    private String d;
    private boolean e;

    public TimeLogger(String r1_String, String r2_String) {
        reset(r1_String, r2_String);
    }

    public void addSplit(String r4_String) {
        if (this.e) {
        } else {
            this.a.add(Long.valueOf(SystemClock.elapsedRealtime()));
            this.b.add(r4_String);
        }
    }

    public void dumpToLog() {
        if (this.e) {
        } else {
            Log.d(this.c, this.d + ": begin");
            long r3j = ((Long) this.a.get(0)).longValue();
            int r2i = 1;
            long r0j = r3j;
            while (r2i < this.a.size()) {
                long r5j = ((Long) this.a.get(r2i)).longValue();
                Log.d(this.c, this.d + ":      " + (r5j - ((Long) this.a.get(r2i - 1)).longValue()) + " ms, " + ((String) this.b.get(r2i)));
                r2i++;
                r0j = r5j;
            }
            Log.d(this.c, this.d + ": end, " + (r0j - r3j) + " ms");
        }
    }

    public void reset() {
        this.e = false;
        if (this.e) {
        } else {
            if (this.a == null) {
                this.a = new ArrayList();
                this.b = new ArrayList();
            } else {
                this.a.clear();
                this.b.clear();
            }
            addSplit(null);
        }
    }

    public void reset(String r1_String, String r2_String) {
        this.c = r1_String;
        this.d = r2_String;
        reset();
    }
}
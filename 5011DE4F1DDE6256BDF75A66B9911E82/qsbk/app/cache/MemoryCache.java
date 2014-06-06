package qsbk.app.cache;

import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryCache {
    static MemoryCache a;
    private Map<String, String> b;
    private long c;
    private long d;

    static {
        a = null;
    }

    public MemoryCache() {
        this.b = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
        this.c = 0;
        this.d = 100000;
        setLimit(Runtime.getRuntime().maxMemory() / 20);
    }

    private void a() {
        Log.i("MemoryCache", "cache size=" + this.c + " length=" + this.b.size());
        if (this.c > this.d) {
            Iterator r1_Iterator = this.b.entrySet().iterator();
            while (r1_Iterator.hasNext()) {
                this.c -= a((String) ((Entry) r1_Iterator.next()).getValue());
                r1_Iterator.remove();
                if (this.c <= this.d) {
                    break;
                }
            }
            Log.i("MemoryCache", "Clean cache. New size " + this.b.size());
        }
    }

    public static MemoryCache findOrCreateMemoryCache() {
        if (a == null) {
            a = new MemoryCache();
        }
        return a;
    }

    long a(String r3_String) {
        return r3_String == null ? 0 : (long) r3_String.toString().getBytes().length;
    }

    public void clear() {
        this.b.clear();
    }

    public String get(String r3_String) {
        String r0_String;
        try {
            if (this.b.containsKey(r3_String)) {
                r0_String = (String) this.b.get(r3_String);
                return r0_String;
            } else {
                r0_String = null;
                return r0_String;
            }
        } catch (NullPointerException e) {
            r0_String = null;
        }
    }

    public void put(String r6_String, String r7_String) {
        try {
            if (this.b.containsKey(r6_String)) {
                this.c -= a((String) this.b.get(r6_String));
            }
            this.b.put(r6_String, r7_String);
            this.c += a(r7_String);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLimit(long r7j) {
        this.d = r7j;
        Log.i("MemoryCache", "MemoryCache will use up to " + ((((double) this.d) / 1024.0d) / 1024.0d) + "MB");
    }
}
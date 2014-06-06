package com.tencent.mm.sdk.storage;

import android.os.Handler;
import android.os.Looper;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class MStorageEvent<T, E> {
    private int a;
    private final Hashtable<T, Object> b;
    private final CopyOnWriteArraySet<E> c;

    public MStorageEvent() {
        this.a = 0;
        this.b = new Hashtable();
        this.c = new CopyOnWriteArraySet();
    }

    private void a() {
        Set r0_Set = new HashSet(this.b.keySet());
        if (r0_Set.size() <= 0) {
        } else {
            Map r3_Map = new HashMap();
            Iterator r4_Iterator = r0_Set.iterator();
            while (r4_Iterator.hasNext()) {
                Object r5_Object = r4_Iterator.next();
                Object r1_Object = this.b.get(r5_Object);
                Iterator r6_Iterator = this.c.iterator();
                while (r6_Iterator.hasNext()) {
                    Object r7_Object = r6_Iterator.next();
                    if (r7_Object != null) {
                        if (r1_Object == null) {
                            Log.f("MicroMsg.SDK.MStorageEvent", "handle listener fatal unknown bug");
                        } else if (r1_Object instanceof Looper) {
                            Looper r0_Looper = (Looper) r1_Object;
                            Handler r2_Handler = (Handler) r3_Map.get(r0_Looper);
                            if (r2_Handler == null) {
                                r2_Handler = new Handler(r0_Looper);
                                r3_Map.put(r0_Looper, r2_Handler);
                            }
                            r2_Handler.post(new d(this, r5_Object, r7_Object));
                        } else {
                            a(r5_Object, r7_Object);
                        }
                    }
                }
            }
            this.c.clear();
        }
    }

    protected abstract void a(T r1_T, E r2_E);

    public void add(T r3_T, Looper r4_Looper) {
        if (!this.b.containsKey(r3_T)) {
            if (r4_Looper != null) {
                this.b.put(r3_T, r4_Looper);
            } else {
                this.b.put(r3_T, new Object());
            }
        }
    }

    public void doNotify() {
        if (!isLocked()) {
            a();
        }
    }

    public boolean event(E r2_E) {
        return this.c.add(r2_E);
    }

    public boolean isLocked() {
        return this.a > 0;
    }

    public void lock() {
        this.a++;
    }

    public void remove(T r2_T) {
        this.b.remove(r2_T);
    }

    public void removeAll() {
        this.b.clear();
    }

    public void unlock() {
        this.a--;
        if (this.a <= 0) {
            this.a = 0;
            a();
        }
    }
}
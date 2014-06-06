package com.tencent.mm.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LRUMap<K, O> {
    private Map<K, TimeVal<O>> a;
    private int b;
    private int c;
    private PreRemoveCallback<K, O> d;

    public static interface OnClearListener<K, O> {
        public void onClear(K r1_K, O r2_O);
    }

    public static interface PreRemoveCallback<K, O> {
        public void preRemoveCallback(K r1_K, O r2_O);
    }

    public class TimeVal<OO> {
        public OO obj;
        public Long t;

        public TimeVal(OO r2_OO) {
            this.obj = r2_OO;
            UpTime();
        }

        public void UpTime() {
            this.t = Long.valueOf(System.currentTimeMillis());
        }
    }

    public LRUMap(int r2i) {
        this(r2i, null);
    }

    public LRUMap(int r2i, PreRemoveCallback<K, O> r3_PreRemoveCallback_K__O) {
        this.a = null;
        this.d = null;
        this.b = r2i;
        this.c = 0;
        this.d = r3_PreRemoveCallback_K__O;
        this.a = new HashMap();
    }

    public boolean check(K r2_K) {
        return this.a.containsKey(r2_K);
    }

    public boolean checkAndUpTime(K r2_K) {
        if (!this.a.containsKey(r2_K)) {
            return false;
        }
        ((TimeVal) this.a.get(r2_K)).UpTime();
        return true;
    }

    public void clear() {
        this.a.clear();
    }

    public void clear(OnClearListener<K, O> r4_OnClearListener_K__O) {
        if (this.a != null) {
            if (r4_OnClearListener_K__O != null) {
                Iterator r1_Iterator = this.a.entrySet().iterator();
                while (r1_Iterator.hasNext()) {
                    Entry r0_Entry = (Entry) r1_Iterator.next();
                    r4_OnClearListener_K__O.onClear(r0_Entry.getKey(), ((TimeVal) r0_Entry.getValue()).obj);
                }
            }
            this.a.clear();
        }
    }

    public O get(K r2_K) {
        TimeVal r0_TimeVal = (TimeVal) this.a.get(r2_K);
        return r0_TimeVal == null ? null : r0_TimeVal.obj;
    }

    public O getAndUptime(K r3_K) {
        TimeVal r0_TimeVal = (TimeVal) this.a.get(r3_K);
        if (r0_TimeVal == null) {
            return null;
        }
        ((TimeVal) this.a.get(r3_K)).UpTime();
        return r0_TimeVal.obj;
    }

    public void remove(K r3_K) {
        if (this.a.containsKey(r3_K)) {
            if (this.d != null) {
                this.d.preRemoveCallback(r3_K, ((TimeVal) this.a.get(r3_K)).obj);
            }
            this.a.remove(r3_K);
        }
    }

    public void setMaxSize(int r1i) {
        if (r1i > 0) {
            this.b = r1i;
        }
    }

    public void setPerDeleteSize(int r1i) {
        if (r1i > 0) {
            this.c = r1i;
        }
    }

    public int size() {
        return this.a.size();
    }

    public void update(K r4_K, O r5_O) {
        if (((TimeVal) this.a.get(r4_K)) == null) {
            this.a.put(r4_K, new TimeVal(r5_O));
            if (this.a.size() > this.b) {
                int r0i;
                ArrayList r1_ArrayList = new ArrayList(this.a.entrySet());
                Collections.sort(r1_ArrayList, new a(this));
                if (this.c <= 0) {
                    r0i = this.b / 10;
                    if (r0i <= 0) {
                        r0i = 1;
                    }
                } else {
                    r0i = this.c;
                }
                Iterator r2_Iterator = r1_ArrayList.iterator();
                int r1i = r0i;
                while (r2_Iterator.hasNext()) {
                    remove(((Entry) r2_Iterator.next()).getKey());
                    r0i = r1i - 1;
                    if (r0i <= 0) {
                    } else {
                        r1i = r0i;
                    }
                }
            }
        } else {
            ((TimeVal) this.a.get(r4_K)).UpTime();
            ((TimeVal) this.a.get(r4_K)).obj = r5_O;
        }
    }
}
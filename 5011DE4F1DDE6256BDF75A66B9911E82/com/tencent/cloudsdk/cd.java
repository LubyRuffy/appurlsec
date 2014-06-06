package com.tencent.cloudsdk;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: SourceFile
public class cd extends LinkedHashMap {
    private static int a = 0;
    private static final long serialVersionUID = -6940751117906094384L;
    private int b;
    private Object c;

    static {
        a = 8;
    }

    public cd(int r4i) {
        super(a, 0.75f, true);
        this.b = a;
        this.c = new Object();
        if (r4i > a) {
            this.b = r4i;
        }
    }

    public cd(int r3i, int r4i) {
        super(r3i, 0.75f, true);
        this.b = a;
        this.c = new Object();
        if (r4i > r3i) {
            this.b = r4i;
        }
    }

    public void clear() {
        synchronized (this.c) {
            super.clear();
        }
    }

    public boolean containsKey(Object r3_Object) {
        boolean r0z;
        synchronized (this.c) {
            r0z = super.containsKey(r3_Object);
        }
        return r0z;
    }

    public Object get(Object r3_Object) {
        Object r0_Object;
        synchronized (this.c) {
            r0_Object = super.get(r3_Object);
        }
        return r0_Object;
    }

    public Object put(Object r3_Object, Object r4_Object) {
        Object r0_Object;
        synchronized (this.c) {
            r0_Object = super.put(r3_Object, r4_Object);
        }
        return r0_Object;
    }

    public void putAll(Map r3_Map) {
        synchronized (this.c) {
            super.putAll(r3_Map);
        }
    }

    public Object remove(Object r3_Object) {
        Object r0_Object;
        synchronized (this.c) {
            r0_Object = super.remove(r3_Object);
        }
        return r0_Object;
    }

    protected boolean removeEldestEntry(Entry r3_Entry) {
        return size() > this.b;
    }

    public int size() {
        int r0i;
        synchronized (this.c) {
            r0i = super.size();
        }
        return r0i;
    }
}
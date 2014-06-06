package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

// compiled from: SourceFile
public class b {
    public static final String a = "default";
    private final ConcurrentMap<Class<?>, Set<cd>> b;
    private final ConcurrentMap<Class<?>, ce> c;
    private final String d;
    private final m e;
    private final cf f;
    private final ThreadLocal<ConcurrentLinkedQueue<cc>> g;
    private final ThreadLocal<Boolean> h;
    private final Map<Class<?>, Set<Class<?>>> i;

    public b() {
        this(a);
    }

    public b(m r2_m) {
        this(r2_m, a);
    }

    public b(m r2_m, String r3_String) {
        this(r2_m, r3_String, cf.a);
    }

    private b(m r2_m, String r3_String, cf r4_cf) {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.g = new ca(this);
        this.h = new cb(this);
        this.i = new HashMap();
        this.e = r2_m;
        this.d = r3_String;
        this.f = r4_cf;
    }

    public b(String r2_String) {
        this(m.b, r2_String);
    }

    private Set<cd> a(Class<?> r2_Class_) {
        return (Set) this.b.get(r2_Class_);
    }

    private void a() {
        boolean r2z = false;
        if (((Boolean) this.h.get()).booleanValue()) {
        } else {
            this.h.set(Boolean.valueOf(true));
            while (true) {
                cc r0_cc = (cc) ((ConcurrentLinkedQueue) this.g.get()).poll();
                if (r0_cc != null) {
                    if (r0_cc.b.a()) {
                        a(r0_cc.a, r0_cc.b);
                    }
                } else {
                    this.h.set(Boolean.valueOf(false));
                }
            }
        }
    }

    private void a(cd r5_cd, ce r6_ce) {
        Object r0_Object = null;
        try {
            r0_Object = r6_ce.c();
        } catch (InvocationTargetException e) {
            a(new StringBuilder("Producer ").append(r6_ce).append(" threw an exception.").toString(), e);
        }
        if (r0_Object == null) {
        } else {
            a(r0_Object, r5_cd);
        }
    }

    private static void a(Object r3_Object, cd r4_cd) {
        try {
            r4_cd.a(r3_Object);
        } catch (InvocationTargetException e) {
            a(new StringBuilder("Could not dispatch event: ").append(r3_Object.getClass()).append(" to handler ").append(r4_cd).toString(), e);
        }
    }

    private static void a(String r2_String, InvocationTargetException r3_InvocationTargetException) {
        Throwable r0_Throwable = r3_InvocationTargetException.getCause();
        if (r0_Throwable != null) {
            throw new RuntimeException(r2_String, r0_Throwable);
        } else {
            throw new RuntimeException(r2_String);
        }
    }

    public void a(Object r7_Object) {
        Class r0_Class;
        ce r1_ce;
        Iterator r2_Iterator;
        this.e.a(this);
        Map r3_Map = this.f.a(r7_Object);
        Iterator r4_Iterator = r3_Map.keySet().iterator();
        while (r4_Iterator.hasNext()) {
            r0_Class = (Class) r4_Iterator.next();
            r1_ce = (ce) r3_Map.get(r0_Class);
            ce r2_ce = (ce) this.c.putIfAbsent(r0_Class, r1_ce);
            if (r2_ce != null) {
                throw new IllegalArgumentException(new StringBuilder("Producer method for type ").append(r0_Class).append(" found on type ").append(r1_ce.a.getClass()).append(", but already registered by type ").append(r2_ce.a.getClass()).append(".").toString());
            } else {
                Set r0_Set = (Set) this.b.get(r0_Class);
                if (r0_Set == null || r0_Set.isEmpty()) {
                } else {
                    r2_Iterator = r0_Set.iterator();
                    while (r2_Iterator.hasNext()) {
                        a((cd) r2_Iterator.next(), r1_ce);
                    }
                }
            }
        }
        r3_Map = this.f.b(r7_Object);
        r4_Iterator = r3_Map.keySet().iterator();
        while (r4_Iterator.hasNext()) {
            r0_Class = (Class) r4_Iterator.next();
            Object r1_Object = (Set) this.b.get(r0_Class);
            if (r1_Object == null) {
                CopyOnWriteArraySet r2_CopyOnWriteArraySet = new CopyOnWriteArraySet();
                r1_Object = (Set) this.b.putIfAbsent(r0_Class, r2_CopyOnWriteArraySet);
                if (r1_Object == null) {
                    r1_Object = r2_CopyOnWriteArraySet;
                }
            }
            r1_Object.addAll((Set) r3_Map.get(r0_Class));
        }
        r2_Iterator = r3_Map.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r2_Iterator.next();
            r1_ce = (ce) this.c.get((Class) r0_Entry.getKey());
            if (r1_ce == null || (!r1_ce.a())) {
            } else {
                Iterator r3_Iterator = ((Set) r0_Entry.getValue()).iterator();
                while (r3_Iterator.hasNext()) {
                    cd r0_cd = (cd) r3_Iterator.next();
                    if (r1_ce.a() && r0_cd.a()) {
                        a(r0_cd, r1_ce);
                    }
                }
            }
        }
    }

    public void b_(Object r7_Object) {
        Entry r0_Entry;
        this.e.a(this);
        Iterator r3_Iterator = this.f.a(r7_Object).entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            r0_Entry = (Entry) r3_Iterator.next();
            Class r1_Class = (Class) r0_Entry.getKey();
            ce r2_ce = (ce) this.c.get(r1_Class);
            ce r0_ce = (ce) r0_Entry.getValue();
            if (r0_ce == null || (!r0_ce.equals(r2_ce))) {
                throw new IllegalArgumentException(new StringBuilder("Missing event producer for an annotated method. Is ").append(r7_Object.getClass()).append(" registered?").toString());
            } else {
                ((ce) this.c.remove(r1_Class)).b();
            }
        }
        Iterator r2_Iterator = this.f.b(r7_Object).entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            r0_Entry = (Entry) r2_Iterator.next();
            Set r3_Set = a((Class) r0_Entry.getKey());
            Collection r1_Collection = (Collection) r0_Entry.getValue();
            if (r3_Set == null || (!r3_Set.containsAll(r1_Collection))) {
                throw new IllegalArgumentException(new StringBuilder("Missing event handler for an annotated method. Is ").append(r7_Object.getClass()).append(" registered?").toString());
            } else {
                Iterator r4_Iterator = r3_Set.iterator();
                while (r4_Iterator.hasNext()) {
                    cd r0_cd = (cd) r4_Iterator.next();
                    if (r1_Collection.contains(r0_cd)) {
                        r0_cd.b();
                    }
                }
                r3_Set.removeAll(r1_Collection);
            }
        }
    }

    public void c(Object r7_Object) {
        this.e.a(this);
        Class r3_Class = r7_Object.getClass();
        Set r0_Set = (Set) this.i.get(r3_Class);
        if (r0_Set == null) {
            List r4_List = new LinkedList();
            Set r1_Set = new HashSet();
            r4_List.add(r3_Class);
            while (!r4_List.isEmpty()) {
                Class r0_Class = (Class) r4_List.remove(0);
                r1_Set.add(r0_Class);
                r0_Class = r0_Class.getSuperclass();
                if (r0_Class != null) {
                    r4_List.add(r0_Class);
                }
            }
            this.i.put(r3_Class, r1_Set);
            r0_Set = r1_Set;
        }
        Iterator r3_Iterator = r0_Set.iterator();
        int r1i = 0;
        while (r3_Iterator.hasNext()) {
            int r0i;
            r0_Set = a((Class) r3_Iterator.next());
            if (r0_Set == null || r0_Set.isEmpty()) {
                r0i = r1i;
            } else {
                Iterator r4_Iterator = r0_Set.iterator();
                while (r4_Iterator.hasNext()) {
                    ((ConcurrentLinkedQueue) this.g.get()).offer(new cc(r7_Object, (cd) r4_Iterator.next()));
                }
                r0i = 1;
            }
            r1i = r0i;
        }
        if (r1i != 0 || r7_Object instanceof f) {
            a();
        } else {
            c(new f(this, r7_Object));
            a();
        }
    }

    public String toString() {
        return new StringBuilder("[Bus \"").append(this.d).append("\"]").toString();
    }
}
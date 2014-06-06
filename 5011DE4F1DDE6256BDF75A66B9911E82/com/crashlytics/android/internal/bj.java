package com.crashlytics.android.internal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// compiled from: SourceFile
final class bj {
    private static final Map<Class<?>, Map<Class<?>, Method>> a;
    private static final Map<Class<?>, Map<Class<?>, Set<Method>>> b;

    static {
        a = new HashMap();
        b = new HashMap();
    }

    static Map<Class<?>, ce> a(Object r5_Object) {
        Class r0_Class = r5_Object.getClass();
        Map<Class<?>, ce> r2_Map_Class___ce = new HashMap();
        if (!a.containsKey(r0_Class)) {
            a(r0_Class);
        }
        Map r0_Map = (Map) a.get(r0_Class);
        if (!r0_Map.isEmpty()) {
            Iterator r3_Iterator = r0_Map.entrySet().iterator();
            while (r3_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r3_Iterator.next();
                r2_Map_Class___ce.put(r0_Entry.getKey(), new ce(r5_Object, (Method) r0_Entry.getValue()));
            }
        }
        return r2_Map_Class___ce;
    }

    private static void a(Class<?> r10_Class_) {
        Map r3_Map = new HashMap();
        Map r4_Map = new HashMap();
        Method[] r5_MethodA = r10_Class_.getDeclaredMethods();
        int r6i = r5_MethodA.length;
        int r1i = 0;
        while (r1i < r6i) {
            Method r7_Method = r5_MethodA[r1i];
            Class[] r0_ClassA;
            if (r7_Method.isAnnotationPresent(l.class)) {
                r0_ClassA = r7_Method.getParameterTypes();
                if (r0_ClassA.length != 1) {
                    throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Subscribe annotation but requires ").append(r0_ClassA.length).append(" arguments.  Methods must require a single argument.").toString());
                } else {
                    Class r8_Class = r0_ClassA[0];
                    if (r8_Class.isInterface()) {
                        throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Subscribe annotation on ").append(r8_Class).append(" which is an interface.  Subscription must be on a concrete class type.").toString());
                    } else if ((r7_Method.getModifiers() & 1) == 0) {
                        throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Subscribe annotation on ").append(r8_Class).append(" but is not 'public'.").toString());
                    } else {
                        Set r0_Set = (Set) r3_Map.get(r8_Class);
                        if (r0_Set == null) {
                            r0_Set = new HashSet();
                            r3_Map.put(r8_Class, r0_Set);
                        }
                        r0_Set.add(r7_Method);
                    }
                }
            } else if (r7_Method.isAnnotationPresent(k.class)) {
                r0_ClassA = r7_Method.getParameterTypes();
                if (r0_ClassA.length != 0) {
                    throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append("has @Produce annotation but requires ").append(r0_ClassA.length).append(" arguments.  Methods must require zero arguments.").toString());
                } else if (r7_Method.getReturnType() == Void.class) {
                    throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has a return type of void.  Must declare a non-void type.").toString());
                } else {
                    Class r0_Class = r7_Method.getReturnType();
                    if (r0_Class.isInterface()) {
                        throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Produce annotation on ").append(r0_Class).append(" which is an interface.  Producers must return a concrete class type.").toString());
                    } else if (r0_Class.equals(Void.TYPE)) {
                        throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Produce annotation but has no return type.").toString());
                    } else if ((r7_Method.getModifiers() & 1) == 0) {
                        throw new IllegalArgumentException(new StringBuilder("Method ").append(r7_Method).append(" has @Produce annotation on ").append(r0_Class).append(" but is not 'public'.").toString());
                    } else if (r4_Map.containsKey(r0_Class)) {
                        throw new IllegalArgumentException(new StringBuilder("Producer for type ").append(r0_Class).append(" has already been registered.").toString());
                    } else {
                        r4_Map.put(r0_Class, r7_Method);
                    }
                }
            }
            r1i++;
        }
        a.put(r10_Class_, r4_Map);
        b.put(r10_Class_, r3_Map);
    }

    static Map<Class<?>, Set<cd>> b(Object r7_Object) {
        Class r0_Class = r7_Object.getClass();
        Map<Class<?>, Set<cd>> r2_Map_Class___Set_cd = new HashMap();
        if (!b.containsKey(r0_Class)) {
            a(r0_Class);
        }
        Map r0_Map = (Map) b.get(r0_Class);
        if (!r0_Map.isEmpty()) {
            Iterator r3_Iterator = r0_Map.entrySet().iterator();
            while (r3_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r3_Iterator.next();
                Set r4_Set = new HashSet();
                Iterator r5_Iterator = ((Set) r0_Entry.getValue()).iterator();
                while (r5_Iterator.hasNext()) {
                    r4_Set.add(new cd(r7_Object, (Method) r5_Iterator.next()));
                }
                r2_Map_Class___Set_cd.put(r0_Entry.getKey(), r4_Set);
            }
        }
        return r2_Map_Class___Set_cd;
    }
}
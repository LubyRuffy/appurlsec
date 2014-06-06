package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// compiled from: SourceFile
final class cd {
    private final Object a;
    private final Method b;
    private final int c;
    private boolean d;

    cd(Object r3_Object, Method r4_Method) {
        boolean r0z = true;
        this.d = true;
        if (r3_Object == null) {
            throw new NullPointerException("EventHandler target cannot be null.");
        } else if (r4_Method == null) {
            throw new NullPointerException("EventHandler method cannot be null.");
        } else {
            this.a = r3_Object;
            this.b = r4_Method;
            r4_Method.setAccessible(r0z);
            this.c = (r4_Method.hashCode() + 31) * 31 + r3_Object.hashCode();
        }
    }

    public final void a(Object r5_Object) throws InvocationTargetException {
        if (this.d) {
            try {
                Method r0_Method = this.b;
                Object r1_Object = this.a;
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r5_Object;
                r0_Method.invoke(r1_Object, r2_ObjectA);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e_2) {
                InvocationTargetException r0_InvocationTargetException = e_2;
                if (r0_InvocationTargetException.getCause() instanceof Error) {
                    throw ((Error) r0_InvocationTargetException.getCause());
                } else {
                    throw r0_InvocationTargetException;
                }
            }
        } else {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
        }
    }

    public final boolean a() {
        return this.d;
    }

    public final void b() {
        this.d = false;
    }

    public final boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        cd r5_cd = (cd) r5_Object;
        return this.b.equals(r5_cd.b) && this.a == r5_cd.a;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return new StringBuilder("[EventHandler ").append(this.b).append("]").toString();
    }
}
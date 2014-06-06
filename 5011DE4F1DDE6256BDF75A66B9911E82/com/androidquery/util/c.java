package com.androidquery.util;

// compiled from: AQUtility.java
class c implements Runnable {
    private final /* synthetic */ Object a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Class[] c;
    private final /* synthetic */ Object[] d;

    c(Object r1_Object, String r2_String, Class[] r3_ClassA, Object[] r4_ObjectA) {
        this.a = r1_Object;
        this.b = r2_String;
        this.c = r3_ClassA;
        this.d = r4_ObjectA;
    }

    public void run() {
        AQUtility.invokeHandler(this.a, this.b, false, true, this.c, this.d);
    }
}
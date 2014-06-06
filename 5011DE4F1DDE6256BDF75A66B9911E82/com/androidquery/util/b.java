package com.androidquery.util;

import android.os.AsyncTask;

// compiled from: AQUtility.java
class b extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ Runnable a;

    b(Runnable r1_Runnable) {
        this.a = r1_Runnable;
    }

    protected String a(Void ... r2_VoidA) {
        try {
            this.a.run();
        } catch (Exception e) {
            AQUtility.report(e);
        }
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object ... r2_ObjectA) {
        return a((Void[]) r2_ObjectA);
    }
}
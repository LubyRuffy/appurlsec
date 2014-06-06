package qsbk.app.loader;

import qsbk.app.core.AsyncTask;

public class AsyncDataLoader extends AsyncTask<Void, Long, String> {
    private OnAsyncLoadListener a;
    private String b;

    public AsyncDataLoader() {
        this.a = null;
        this.b = null;
    }

    public AsyncDataLoader(String r2_String) {
        this.a = null;
        this.b = null;
        this.b = r2_String;
    }

    public AsyncDataLoader(OnAsyncLoadListener r2_OnAsyncLoadListener) {
        this.a = null;
        this.b = null;
        this.a = r2_OnAsyncLoadListener;
    }

    public AsyncDataLoader(OnAsyncLoadListener r2_OnAsyncLoadListener, String r3_String) {
        this.a = null;
        this.b = null;
        this.b = r3_String;
        this.a = r2_OnAsyncLoadListener;
    }

    protected String a(Void ... r3_VoidA) {
        try {
            Thread.currentThread().setName(this.b);
        } catch (SecurityException e) {
        }
        return this.a != null ? this.a.onStartListener() : null;
    }

    protected void a() {
        super.a();
        if (this.a != null) {
            this.a.onPrepareListener();
        }
    }

    protected void a(String r2_String) {
        super.a((Object)r2_String);
        if (this.a != null) {
            this.a.onFinishListener(r2_String);
        }
    }

    public void setOnAsyncLoadListener(OnAsyncLoadListener r1_OnAsyncLoadListener) {
        this.a = r1_OnAsyncLoadListener;
    }
}
package com.androidquery.auth;

import android.content.Context;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.http.HttpRequest;

public abstract class AccountHandle {
    private LinkedHashSet<AbstractAjaxCallback<?, ?>> a;

    protected abstract void a();

    protected synchronized void a(Context r3_Context) {
        if (this.a != null) {
            Iterator r1_Iterator = this.a.iterator();
            while (r1_Iterator.hasNext()) {
                ((AbstractAjaxCallback) r1_Iterator.next()).async(r3_Context);
            }
            this.a = null;
        }
    }

    protected synchronized void a(Context r3_Context, int r4i, String r5_String) {
        if (this.a != null) {
            Iterator r1_Iterator = this.a.iterator();
            while (r1_Iterator.hasNext()) {
                ((AbstractAjaxCallback) r1_Iterator.next()).failure(r4i, r5_String);
            }
            this.a = null;
        }
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___, HttpURLConnection r2_HttpURLConnection) {
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___, HttpRequest r2_HttpRequest) {
    }

    public synchronized void auth(AbstractAjaxCallback<?, ?> r2_AbstractAjaxCallback___) {
        if (this.a == null) {
            this.a = new LinkedHashSet();
            this.a.add(r2_AbstractAjaxCallback___);
            a();
        } else {
            this.a.add(r2_AbstractAjaxCallback___);
        }
    }

    public abstract boolean authenticated();

    public abstract boolean expired(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___, AjaxStatus r2_AjaxStatus);

    public String getCacheUrl(String r1_String) {
        return r1_String;
    }

    public String getNetworkUrl(String r1_String) {
        return r1_String;
    }

    public abstract boolean reauth(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___);

    public void unauth() {
    }
}
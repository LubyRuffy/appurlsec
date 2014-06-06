package com.androidquery.callback;

import java.net.Proxy;
import org.apache.http.HttpRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class ProxyHandle {
    public abstract void applyProxy(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___, HttpRequest r2_HttpRequest, DefaultHttpClient r3_DefaultHttpClient);

    public abstract Proxy makeProxy(AbstractAjaxCallback<?, ?> r1_AbstractAjaxCallback___);
}
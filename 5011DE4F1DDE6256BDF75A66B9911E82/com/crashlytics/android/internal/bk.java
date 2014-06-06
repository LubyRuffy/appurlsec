package com.crashlytics.android.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// compiled from: SourceFile
final class bk implements aB {
    bk() {
    }

    public final HttpURLConnection a(URL r2_URL) throws IOException {
        return (HttpURLConnection) r2_URL.openConnection();
    }
}
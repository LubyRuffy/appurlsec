package com.androidquery.util;

import android.graphics.Picture;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;

// compiled from: WebImage.java
class d implements PictureListener {
    final /* synthetic */ WebImage a;

    d(WebImage r1_WebImage) {
        this.a = r1_WebImage;
    }

    public void onNewPicture(WebView r3_WebView, Picture r4_Picture) {
        this.a.b.setPictureListener(null);
        this.a.b();
    }
}
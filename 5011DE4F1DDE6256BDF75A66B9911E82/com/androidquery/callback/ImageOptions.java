package com.androidquery.callback;

import android.graphics.Bitmap;

public class ImageOptions {
    public float anchor;
    public int animation;
    public int fallback;
    public boolean fileCache;
    public boolean memCache;
    public int policy;
    public Bitmap preset;
    public float ratio;
    public int round;
    public int targetWidth;

    public ImageOptions() {
        this.memCache = true;
        this.fileCache = true;
        this.anchor = 3.4028235E38f;
    }
}
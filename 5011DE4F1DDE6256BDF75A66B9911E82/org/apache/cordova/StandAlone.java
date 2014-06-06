package org.apache.cordova;

import android.os.Bundle;

public class StandAlone extends DroidGap {
    public void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}
package com.google.analytics.tracking.android;

import android.app.Activity;

public class TrackedActivity extends Activity {
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }
}
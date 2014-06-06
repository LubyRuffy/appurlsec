package com.google.analytics.tracking.android;

import android.app.TabActivity;

public class TrackedTabActivity extends TabActivity {
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }
}
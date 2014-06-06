package com.google.analytics.tracking.android;

import android.app.ListActivity;

public class TrackedListActivity extends ListActivity {
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }
}
package com.google.analytics.tracking.android;

import android.app.ExpandableListActivity;

public class TrackedExpandableListActivity extends ExpandableListActivity {
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }
}
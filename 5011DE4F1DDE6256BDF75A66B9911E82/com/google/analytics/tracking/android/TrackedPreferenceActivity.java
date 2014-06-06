package com.google.analytics.tracking.android;

import android.preference.PreferenceActivity;

public class TrackedPreferenceActivity extends PreferenceActivity {
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }
}
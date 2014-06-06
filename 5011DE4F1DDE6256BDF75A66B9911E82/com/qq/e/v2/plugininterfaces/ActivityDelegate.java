package com.qq.e.v2.plugininterfaces;

import android.os.Bundle;

public interface ActivityDelegate {
    public static final String DelegateKeyName = "gdt_activity_delegate_name";

    public void onAfterCreate(Bundle r1_Bundle);

    public void onBackPressed();

    public void onBeforeCreate(Bundle r1_Bundle);

    public void onDestroy();

    public void onResume();

    public void onStop();
}
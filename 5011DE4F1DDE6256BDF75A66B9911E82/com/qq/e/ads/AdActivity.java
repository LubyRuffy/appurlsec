package com.qq.e.ads;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.plugininterfaces.ActivityDelegate;
import com.qq.e.v2.util.GDTLogger;

public class AdActivity extends Activity {
    private ActivityDelegate a;

    public void onBackPressed() {
        super.onBackPressed();
        if (this.a != null) {
            this.a.onBackPressed();
        }
    }

    protected void onCreate(Bundle r5_Bundle) {
        String r1_String = getIntent().getExtras().getString(ActivityDelegate.DelegateKeyName);
        try {
            Bundle r2_Bundle = getPackageManager().getApplicationInfo(getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData;
            String r0_String = "--UNKNOWN--";
            if (r2_Bundle == null || (!r2_Bundle.containsKey("GDT_AD_APPID"))) {
                if (GDTADManager.getInstance().initWith(getApplicationContext(), r0_String)) {
                    GDTLogger.report("Init GDTADManager fail in AdActivity");
                    if (this.a != null) {
                        finish();
                    } else {
                        this.a.onBeforeCreate(r5_Bundle);
                    }
                    super.onCreate(r5_Bundle);
                    if (this.a != null) {
                    } else {
                        this.a.onAfterCreate(r5_Bundle);
                    }
                } else {
                    this.a = GDTADManager.getInstance().getPM().getActivityDelegateFactory().getDelegate(r1_String, this);
                    if (this.a != null) {
                        GDTLogger.report(new StringBuilder("Init ADActivity Delegate return null,delegateName").append(r1_String).toString());
                    }
                    if (this.a != null) {
                        this.a.onBeforeCreate(r5_Bundle);
                    } else {
                        finish();
                    }
                    super.onCreate(r5_Bundle);
                    if (this.a != null) {
                        this.a.onAfterCreate(r5_Bundle);
                    }
                }
            } else {
                r0_String = r2_Bundle.get("GDT_AD_APPID");
                if (GDTADManager.getInstance().initWith(getApplicationContext(), r0_String)) {
                    GDTLogger.report("Init GDTADManager fail in AdActivity");
                    if (this.a != null) {
                        this.a.onBeforeCreate(r5_Bundle);
                    } else {
                        finish();
                    }
                    super.onCreate(r5_Bundle);
                    if (this.a != null) {
                        this.a.onAfterCreate(r5_Bundle);
                    }
                } else {
                    this.a = GDTADManager.getInstance().getPM().getActivityDelegateFactory().getDelegate(r1_String, this);
                    if (this.a != null) {
                        if (this.a != null) {
                            finish();
                        } else {
                            this.a.onBeforeCreate(r5_Bundle);
                        }
                        super.onCreate(r5_Bundle);
                        if (this.a != null) {
                        } else {
                            this.a.onAfterCreate(r5_Bundle);
                        }
                    } else {
                        GDTLogger.report(new StringBuilder("Init ADActivity Delegate return null,delegateName").append(r1_String).toString());
                        if (this.a != null) {
                            this.a.onBeforeCreate(r5_Bundle);
                        } else {
                            finish();
                        }
                        super.onCreate(r5_Bundle);
                        if (this.a != null) {
                            this.a.onAfterCreate(r5_Bundle);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            GDTLogger.report(new StringBuilder("Init ADActivity Delegate Faile,DelegateName:").append(r1_String).toString(), th);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.onResume();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.a != null) {
            this.a.onStop();
        }
    }
}
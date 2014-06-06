package com.qq.e.gridappwall;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.qq.e.comm.a;
import com.qq.e.v2.managers.GDTADManager;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;

public final class GridAppWall {
    public static final int ERROR_INTERNAL = 1;
    public static final int ERROR_LOAD_AD_FAILED = 2;
    public static final int ERROR_PERMISSION_UNGRATIFIED = 3;
    private GridAppWallView a;

    public GridAppWall(String r3_String, String r4_String, Activity r5_Activity, GridAppWallListener r6_GridAppWallListener) {
        if (r5_Activity == null) {
            GDTLogger.e("activity is null");
            a(r6_GridAppWallListener);
        } else if (StringUtil.isEmpty(r3_String) || StringUtil.isEmpty(r4_String)) {
            GDTLogger.e("appId or positionId is empty");
            a(r6_GridAppWallListener);
        } else if (a.a((Context)r5_Activity)) {
            try {
                if (GDTADManager.getInstance().initWith(r5_Activity.getApplicationContext(), r3_String)) {
                    this.a = GDTADManager.getInstance().getPM().getGridAdViewFactory().createGridAppWallView(r5_Activity, r3_String, r4_String);
                    if (this.a != null) {
                        this.a.setAdListener(r6_GridAppWallListener);
                    } else {
                        GDTLogger.e("Fail to INIT GDT SDK");
                        a(r6_GridAppWallListener);
                    }
                } else {
                    GDTLogger.e("Fail to Init GDT AD SDK,report logcat info filter by gdt_ad_mob");
                    a(r6_GridAppWallListener);
                }
            } catch (Exception e) {
                GDTLogger.report("Fail to init new appwall plugin", e);
                a(r6_GridAppWallListener);
            } catch (Throwable th) {
                GDTLogger.report("Unknown Exception", th);
                a(r6_GridAppWallListener);
            }
        } else {
            GDTLogger.e("check Required Activity and permission in application manifset");
            a(r6_GridAppWallListener);
        }
    }

    private static void a(GridAppWallListener r1_GridAppWallListener) {
        if (r1_GridAppWallListener != null) {
            r1_GridAppWallListener.onAdFailed(ERROR_INTERNAL);
        }
    }

    public final void show() {
        if (this.a != null) {
            this.a.show();
        }
    }

    public final void showRelativeTo(int r2i, int r3i) {
        if (this.a != null) {
            this.a.showRelativeTo(r2i, r3i);
        }
    }

    public final void showRelativeTo(View r2_View) {
        if (this.a != null) {
            this.a.showRelativeTo(r2_View);
        }
    }
}
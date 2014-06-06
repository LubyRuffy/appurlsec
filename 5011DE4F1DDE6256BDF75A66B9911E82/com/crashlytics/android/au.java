package com.crashlytics.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.internal.aQ;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class au implements Runnable {
    final /* synthetic */ az a;
    final /* synthetic */ Crashlytics b;
    private /* synthetic */ Activity c;
    private /* synthetic */ x d;
    private /* synthetic */ aQ e;

    au(Crashlytics r1_Crashlytics, Activity r2_Activity, az r3_az, x r4_x, aQ r5_aQ) {
        this.b = r1_Crashlytics;
        this.c = r2_Activity;
        this.a = r3_az;
        this.d = r4_x;
        this.e = r5_aQ;
    }

    public final void run() {
        Builder r0_Builder = new Builder(this.c);
        OnClickListener r1_OnClickListener = new av(this);
        float r2f = this.c.getResources().getDisplayMetrics().density;
        int r3i = ((int) (((float) ShareUtils.SHARE_SMS) * r2f));
        View r4_View = new TextView(this.c);
        r4_View.setAutoLinkMask(NearbySelectView.TIME_15MIN);
        r4_View.setText(this.d.b());
        r4_View.setTextAppearance(this.c, 16973892);
        r4_View.setPadding(r3i, r3i, r3i, r3i);
        r4_View.setFocusable(false);
        View r3_View = new ScrollView(this.c);
        r3_View.setPadding(((int) (((float) REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION) * r2f)), ((int) (((float) XListViewHeader.STATE_REFRESHING) * r2f)), ((int) (((float) REQUEST_CODE.REQUEST_CODE_EDIT_INTRO) * r2f)), ((int) (((float) REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH) * r2f)));
        r3_View.addView(r4_View);
        r0_Builder.setView(r3_View).setTitle(this.d.a()).setCancelable(false).setNeutralButton(this.d.c(), r1_OnClickListener);
        if (this.e.d) {
            r0_Builder.setNegativeButton(this.d.e(), new aw(this));
        }
        if (this.e.f) {
            r0_Builder.setPositiveButton(this.d.d(), new ax(this));
        }
        r0_Builder.show();
    }
}
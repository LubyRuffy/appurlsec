package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

// compiled from: SourceFile
final class aa extends RelativeLayout {
    private ag a;
    private Context b;
    private String c;
    private int d;
    private boolean e;
    private boolean f;

    aa(ag r1_ag, Context r2_Context, String r3_String, int r4i) {
        super(r2_Context);
        this.a = r1_ag;
        this.b = r2_Context;
        this.c = r3_String;
        this.d = r4i;
    }

    private static LayoutParams b() {
        return new LayoutParams(-1, -1);
    }

    private synchronized ak c() {
        ak r0_ak;
        ag r0_ag = this.a;
        Context r1_Context = this.b;
        String[] r2_StringA = new String[1];
        r2_StringA[0] = this.c;
        List r0_List = r0_ag.a(r1_Context, Arrays.asList(r2_StringA), null, this.d, false);
        if (r0_List.isEmpty()) {
            r0_ak = null;
        } else {
            r0_ak = (ak) r0_List.get(0);
            r0_ak.setOnClickListener(this.a);
        }
        return r0_ak;
    }

    final void a() {
        if (!this.e) {
            View r0_View = c();
            if (r0_View != null) {
                removeAllViews();
                addView(r0_View, b());
                r0_View.a().a(new r((byte) 3, this.a.k()));
                this.e = true;
            } else if (!this.f) {
                r0_View = new TextView(this.b);
                r0_View.setText(ag.b);
                r0_View.setTextSize(1, 20.0f);
                addView(r0_View, b());
            }
            this.f = true;
        }
    }
}
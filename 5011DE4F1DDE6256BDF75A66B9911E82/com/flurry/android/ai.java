package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.baidu.android.pushservice.PushConstants;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class ai extends LinearLayout {
    private View a;
    private List b;
    private boolean c;
    private /* synthetic */ CatalogActivity d;

    public ai(CatalogActivity r3_CatalogActivity, Context r4_Context) {
        this.d = r3_CatalogActivity;
        super(r4_Context);
        this.b = new ArrayList();
        this.c = true;
        setOrientation(1);
        setGravity(AdViewUtil.NETWORK_TYPE_ADUU);
        this.a = new ae(r3_CatalogActivity, r4_Context);
        this.a.setId(PushConstants.ERROR_SERVICE_NOT_AVAILABLE);
        this.a.setOnClickListener(r3_CatalogActivity);
        a(a(r4_Context), this.c);
    }

    private void a(List r8_List, boolean r9z) {
        removeAllViews();
        LayoutParams r1_LayoutParams = new LinearLayout.LayoutParams(-1, -2);
        r1_LayoutParams.setMargins(0, 0, 0, 0);
        addView(this.a, r1_LayoutParams);
        if (r8_List != null) {
            this.b.clear();
            this.b.addAll(r8_List);
        }
        if (r9z) {
            Iterator r2_Iterator = this.b.iterator();
            while (r2_Iterator.hasNext()) {
                ak r0_ak = (ak) r2_Iterator.next();
                addView(r0_ak, r1_LayoutParams);
                r0_ak.a().a(new r((byte) 3, this.d.e.k()));
            }
        }
        refreshDrawableState();
    }

    final List a(Context r7_Context) {
        ah r0_ah;
        Long r3_Long = null;
        List r2_List = new ArrayList();
        int r0i = 1;
        while (r0i <= 3) {
            r2_List.add("Flurry_Canvas_Hook_" + r0i);
            r0i++;
        }
        r0_ah = this.d.f == null ? null : this.d.f.b;
        List r1_List;
        Iterator r2_Iterator;
        if (r0_ah == null) {
            r1_List = this.d.e.a(r7_Context, r2_List, r3_Long, 1, true);
            r2_Iterator = r1_List.iterator();
            while (r2_Iterator.hasNext()) {
                ((ak) r2_Iterator.next()).setOnClickListener(this.d);
            }
            return r1_List;
        } else {
            r3_Long = Long.valueOf(r0_ah.a);
            r1_List = this.d.e.a(r7_Context, r2_List, r3_Long, 1, true);
            r2_Iterator = r1_List.iterator();
            while (r2_Iterator.hasNext()) {
                ((ak) r2_Iterator.next()).setOnClickListener(this.d);
            }
            return r1_List;
        }
    }

    final void a() {
        this.c = !(this.c);
        a(null, this.c);
    }

    final void a(List r2_List) {
        a(r2_List, this.c);
    }

    final List b() {
        return this.b;
    }
}
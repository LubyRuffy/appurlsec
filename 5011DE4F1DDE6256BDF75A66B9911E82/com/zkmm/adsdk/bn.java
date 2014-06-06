package com.zkmm.adsdk;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class bn implements OnTouchListener {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private /* synthetic */ bj i;

    bn(bj r1_bj) {
        this.i = r1_bj;
    }

    public final boolean onTouch(View r9_View, MotionEvent r10_MotionEvent) {
        int r2i = 1;
        switch (r10_MotionEvent.getAction()) {
            case XListViewHeader.STATE_READY:
                if (r10_MotionEvent.getPointerCount() < 2) {
                    this.a = -1.0f;
                    this.b = -1.0f;
                    this.c = -1.0f;
                    this.d = -1.0f;
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                if (r10_MotionEvent.getPointerCount() == 2) {
                    if (!(this.a == -1.0f && this.c == -1.0f)) {
                        this.e = r10_MotionEvent.getX(0);
                        this.f = r10_MotionEvent.getY(0);
                        this.g = r10_MotionEvent.getX(r2i);
                        this.h = r10_MotionEvent.getY(r2i);
                        float r0f = (float) Math.sqrt(Math.pow((double) (this.c - this.a), 2.0d) + Math.pow((double) (this.d - this.b), 2.0d));
                        float r1f = (float) Math.sqrt(Math.pow((double) (this.g - this.e), 2.0d) + Math.pow((double) (this.h - this.f), 2.0d));
                        if (r0f - r1f >= 25.0f) {
                            this.i.c.zoomOut();
                        } else if (r1f - r0f >= 25.0f) {
                            this.i.c.zoomIn();
                        }
                        this.a = this.e;
                        this.c = this.g;
                        this.b = this.f;
                        this.d = this.h;
                    }
                }
                break;
            case 261:
                if (r10_MotionEvent.getPointerCount() == 2) {
                    this.a = r10_MotionEvent.getX(0);
                    this.b = r10_MotionEvent.getY(0);
                    this.c = r10_MotionEvent.getX(1);
                    this.d = r10_MotionEvent.getY(1);
                }
                break;
        }
        return false;
    }
}
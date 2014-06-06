package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.baidu.location.LocationClientOption;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class GestureDetectorCompat {
    private final a a;

    static interface a {
        public boolean isLongpressEnabled();

        public boolean onTouchEvent(MotionEvent r1_MotionEvent);

        public void setIsLongpressEnabled(boolean r1z);

        public void setOnDoubleTapListener(OnDoubleTapListener r1_OnDoubleTapListener);
    }

    static class b implements a {
        private static final int e;
        private static final int f;
        private static final int g;
        private int a;
        private int b;
        private int c;
        private int d;
        private final Handler h;
        private final OnGestureListener i;
        private OnDoubleTapListener j;
        private boolean k;
        private boolean l;
        private boolean m;
        private boolean n;
        private boolean o;
        private MotionEvent p;
        private MotionEvent q;
        private boolean r;
        private float s;
        private float t;
        private float u;
        private float v;
        private boolean w;
        private VelocityTracker x;

        private class a extends Handler {
            a() {
            }

            a(Handler r3_Handler) {
                super(r3_Handler.getLooper());
            }

            public void handleMessage(Message r4_Message) {
                switch (r4_Message.what) {
                    case XListViewHeader.STATE_READY:
                        b.this.i.onShowPress(b.this.p);
                        return;
                    case XListViewHeader.STATE_REFRESHING:
                        b.this.c();
                        return;
                    case XListViewFooter.STATE_NOMORE:
                        if (b.this.j != null) {
                            if (b.this.k) {
                                b.this.l = true;
                            } else {
                                b.this.j.onSingleTapConfirmed(b.this.p);
                            }
                        }
                        return;
                }
                throw new RuntimeException("Unknown message " + r4_Message);
            }
        }

        static {
            e = ViewConfiguration.getLongPressTimeout();
            f = ViewConfiguration.getTapTimeout();
            g = ViewConfiguration.getDoubleTapTimeout();
        }

        public b(Context r2_Context, OnGestureListener r3_OnGestureListener, Handler r4_Handler) {
            if (r4_Handler != null) {
                this.h = new a(r4_Handler);
            } else {
                this.h = new a();
            }
            this.i = r3_OnGestureListener;
            if (r3_OnGestureListener instanceof OnDoubleTapListener) {
                setOnDoubleTapListener((OnDoubleTapListener) r3_OnGestureListener);
            }
            a(r2_Context);
        }

        private void a() {
            this.h.removeMessages(1);
            this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
            this.h.removeMessages(XListViewFooter.STATE_NOMORE);
            this.x.recycle();
            this.x = null;
            this.r = false;
            this.k = false;
            this.n = false;
            this.o = false;
            this.l = false;
            if (this.m) {
                this.m = false;
            }
        }

        private void a(Context r5_Context) {
            if (r5_Context == null) {
                throw new IllegalArgumentException("Context must not be null");
            } else if (this.i == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            } else {
                this.w = true;
                ViewConfiguration r0_ViewConfiguration = ViewConfiguration.get(r5_Context);
                int r1i = r0_ViewConfiguration.getScaledTouchSlop();
                int r2i = r0_ViewConfiguration.getScaledDoubleTapSlop();
                this.c = r0_ViewConfiguration.getScaledMinimumFlingVelocity();
                this.d = r0_ViewConfiguration.getScaledMaximumFlingVelocity();
                this.a = r1i * r1i;
                this.b = r2i * r2i;
            }
        }

        private boolean a(MotionEvent r6_MotionEvent, MotionEvent r7_MotionEvent, MotionEvent r8_MotionEvent) {
            if (!(this.o) || r8_MotionEvent.getEventTime() - r7_MotionEvent.getEventTime() > ((long) g)) {
                return false;
            }
            int r1i = ((int) r6_MotionEvent.getX()) - ((int) r8_MotionEvent.getX());
            int r2i = ((int) r6_MotionEvent.getY()) - ((int) r8_MotionEvent.getY());
            return (r1i * r1i) + (r2i * r2i) < this.b;
        }

        private void b_() {
            this.h.removeMessages(1);
            this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
            this.h.removeMessages(XListViewFooter.STATE_NOMORE);
            this.r = false;
            this.n = false;
            this.o = false;
            this.l = false;
            if (this.m) {
                this.m = false;
            }
        }

        private void c() {
            this.h.removeMessages(XListViewFooter.STATE_NOMORE);
            this.l = false;
            this.m = true;
            this.i.onLongPress(this.p);
        }

        public boolean isLongpressEnabled() {
            return this.w;
        }

        public boolean onTouchEvent(MotionEvent r14_MotionEvent) {
            int r7i;
            int r0i;
            boolean r3z = false;
            int r9i = r14_MotionEvent.getAction();
            if (this.x == null) {
                this.x = VelocityTracker.obtain();
            }
            this.x.addMovement(r14_MotionEvent);
            r7i = (r9i & 255) == 6 ? 1 : 0;
            r0i = r7i != 0 ? MotionEventCompat.getActionIndex(r14_MotionEvent) : -1;
            int r4i = MotionEventCompat.getPointerCount(r14_MotionEvent);
            int r5i = 0;
            float r1f = 0.0f;
            float r2f = 0.0f;
            while (r5i < r4i) {
                if (r0i == r5i) {
                    r5i++;
                } else {
                    r2f += MotionEventCompat.getX(r14_MotionEvent, r5i);
                    r1f += MotionEventCompat.getY(r14_MotionEvent, r5i);
                    r5i++;
                }
            }
            r0i = r7i != 0 ? r4i - 1 : r4i;
            r2f /= (float) r0i;
            r1f /= (float) r0i;
            boolean r0z;
            float r4f;
            float r0f;
            switch ((r9i & 255)) {
                case XListViewHeader.STATE_NORMAL:
                    if (this.j != null) {
                        r0z = this.h.hasMessages(XListViewFooter.STATE_NOMORE);
                        if (r0z) {
                            this.h.removeMessages(XListViewFooter.STATE_NOMORE);
                        }
                        if (this.p == null || this.q == null || (!r0z) || (!a(this.p, this.q, r14_MotionEvent))) {
                            this.h.sendEmptyMessageDelayed(XListViewFooter.STATE_NOMORE, (long) g);
                            r0i = 0;
                        } else {
                            this.r = true;
                            r0i = (this.j.onDoubleTap(this.p) | 0) | this.j.onDoubleTapEvent(r14_MotionEvent);
                        }
                    } else {
                        r0i = 0;
                    }
                    this.s = r2f;
                    this.u = r2f;
                    this.t = r1f;
                    this.v = r1f;
                    if (this.p != null) {
                        this.p.recycle();
                    }
                    this.p = MotionEvent.obtain(r14_MotionEvent);
                    this.n = true;
                    this.o = true;
                    this.k = true;
                    this.m = false;
                    this.l = false;
                    if (this.w) {
                        this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                        this.h.sendEmptyMessageAtTime(XListViewHeader.STATE_REFRESHING, this.p.getDownTime() + ((long) f) + ((long) e));
                    }
                    this.h.sendEmptyMessageAtTime(1, this.p.getDownTime() + ((long) f));
                    return r0i | this.i.onDown(r14_MotionEvent);
                case XListViewHeader.STATE_READY:
                    this.k = false;
                    MotionEvent r1_MotionEvent = MotionEvent.obtain(r14_MotionEvent);
                    if (this.r) {
                        r0z = this.j.onDoubleTapEvent(r14_MotionEvent) | false;
                    } else if (this.m) {
                        this.h.removeMessages(XListViewFooter.STATE_NOMORE);
                        this.m = false;
                        r0z = false;
                    } else if (this.n) {
                        r0z = this.i.onSingleTapUp(r14_MotionEvent);
                        if (this.l) {
                            if (this.j != null) {
                                this.j.onSingleTapConfirmed(r14_MotionEvent);
                            }
                        }
                    } else {
                        VelocityTracker r0_VelocityTracker = this.x;
                        int r2i = MotionEventCompat.getPointerId(r14_MotionEvent, 0);
                        r0_VelocityTracker.computeCurrentVelocity(LocationClientOption.MIN_SCAN_SPAN, (float) this.d);
                        r4f = VelocityTrackerCompat.getYVelocity(r0_VelocityTracker, r2i);
                        r0f = VelocityTrackerCompat.getXVelocity(r0_VelocityTracker, r2i);
                        r0z = ((Math.abs(r4f) > ((float) this.c) ? 1 : (Math.abs(r4f) == ((float) this.c)? 0 : -1)) > 0 || Math.abs(r0f) > ((float) this.c)) ? this.i.onFling(this.p, r14_MotionEvent, r0f, r4f) : false;
                        if (this.q == null) {
                            this.q.recycle();
                        }
                        this.q = r1_MotionEvent;
                        if (this.x != null) {
                            this.x.recycle();
                            this.x = null;
                        }
                        this.r = r3z;
                        this.l = r3z;
                        this.h.removeMessages(1);
                        this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                        return r0z;
                    }
                    if (this.q == null) {
                        this.q = r1_MotionEvent;
                        if (this.x != null) {
                            this.r = r3z;
                            this.l = r3z;
                            this.h.removeMessages(1);
                            this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                            return r0z;
                        } else {
                            this.x.recycle();
                            this.x = null;
                            this.r = r3z;
                            this.l = r3z;
                            this.h.removeMessages(1);
                            this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                            return r0z;
                        }
                    } else {
                        this.q.recycle();
                        this.q = r1_MotionEvent;
                        if (this.x != null) {
                            this.x.recycle();
                            this.x = null;
                        }
                        this.r = r3z;
                        this.l = r3z;
                        this.h.removeMessages(1);
                        this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                        return r0z;
                    }
                case XListViewHeader.STATE_REFRESHING:
                    if (this.m) {
                        return false;
                    }
                    r0f = this.s - r2f;
                    r4f = this.t - r1f;
                    if (this.r) {
                        return r3z | this.j.onDoubleTapEvent(r14_MotionEvent);
                    }
                    if (this.n) {
                        r5i = (int) (r2f - this.u);
                        int r6i = (int) (r1f - this.v);
                        r5i = r5i * r5i + r6i * r6i;
                        if (r5i > this.a) {
                            r0z = this.i.onScroll(this.p, r14_MotionEvent, r0f, r4f);
                            this.s = r2f;
                            this.t = r1f;
                            this.n = false;
                            this.h.removeMessages(XListViewFooter.STATE_NOMORE);
                            this.h.removeMessages(1);
                            this.h.removeMessages(XListViewHeader.STATE_REFRESHING);
                        } else {
                            r0z = false;
                        }
                        if (r5i > this.a) {
                            this.o = false;
                        }
                        return r0z;
                    } else {
                        if (Math.abs(r0f) < 1.0f && Math.abs(r4f) < 1.0f) {
                            return false;
                        }
                        r3z = this.i.onScroll(this.p, r14_MotionEvent, r0f, r4f);
                        this.s = r2f;
                        this.t = r1f;
                        return r3z;
                    }
                case XListViewFooter.STATE_NOMORE:
                    a();
                    return false;
                case ShareUtils.SHARE_SMS:
                    this.s = r2f;
                    this.u = r2f;
                    this.t = r1f;
                    this.v = r1f;
                    b();
                    return false;
                case ShareUtils.SHARE_COPY:
                    this.s = r2f;
                    this.u = r2f;
                    this.t = r1f;
                    this.v = r1f;
                    this.x.computeCurrentVelocity(LocationClientOption.MIN_SCAN_SPAN, (float) this.d);
                    int r1i = MotionEventCompat.getActionIndex(r14_MotionEvent);
                    r0i = MotionEventCompat.getPointerId(r14_MotionEvent, r1i);
                    r2f = VelocityTrackerCompat.getXVelocity(this.x, r0i);
                    float r5f = VelocityTrackerCompat.getYVelocity(this.x, r0i);
                    r0i = 0;
                    while (r0i < r4i) {
                        if (r0i == r1i) {
                            r0i++;
                        } else {
                            r7i = MotionEventCompat.getPointerId(r14_MotionEvent, r0i);
                            if (VelocityTrackerCompat.getYVelocity(this.x, r7i) * r5f + VelocityTrackerCompat.getXVelocity(this.x, r7i) * r2f < 0.0f) {
                                this.x.clear();
                                return false;
                            }
                            r0i++;
                        }
                    }
                    return false;
            }
            return false;
        }

        public void setIsLongpressEnabled(boolean r1z) {
            this.w = r1z;
        }

        public void setOnDoubleTapListener(OnDoubleTapListener r1_OnDoubleTapListener) {
            this.j = r1_OnDoubleTapListener;
        }
    }

    static class c implements a {
        private final GestureDetector a;

        public c(Context r2_Context, OnGestureListener r3_OnGestureListener, Handler r4_Handler) {
            this.a = new GestureDetector(r2_Context, r3_OnGestureListener, r4_Handler);
        }

        public boolean isLongpressEnabled() {
            return this.a.isLongpressEnabled();
        }

        public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
            return this.a.onTouchEvent(r2_MotionEvent);
        }

        public void setIsLongpressEnabled(boolean r2z) {
            this.a.setIsLongpressEnabled(r2z);
        }

        public void setOnDoubleTapListener(OnDoubleTapListener r2_OnDoubleTapListener) {
            this.a.setOnDoubleTapListener(r2_OnDoubleTapListener);
        }
    }

    public GestureDetectorCompat(Context r2_Context, OnGestureListener r3_OnGestureListener) {
        this(r2_Context, r3_OnGestureListener, null);
    }

    public GestureDetectorCompat(Context r3_Context, OnGestureListener r4_OnGestureListener, Handler r5_Handler) {
        if (VERSION.SDK_INT > 17) {
            this.a = new c(r3_Context, r4_OnGestureListener, r5_Handler);
        } else {
            this.a = new b(r3_Context, r4_OnGestureListener, r5_Handler);
        }
    }

    public boolean isLongpressEnabled() {
        return this.a.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        return this.a.onTouchEvent(r2_MotionEvent);
    }

    public void setIsLongpressEnabled(boolean r2z) {
        this.a.setIsLongpressEnabled(r2z);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener r2_OnDoubleTapListener) {
        this.a.setOnDoubleTapListener(r2_OnDoubleTapListener);
    }
}
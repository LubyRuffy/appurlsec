package qsbk.app.widget.imageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import qsbk.app.widget.listview.XListViewHeader;

public class MultiTouchImageView extends ImageViewTouchBaseView {
    protected static final float MIN_ZOOM = 0.9f;
    private OnClickListener clickListener;
    Context context;
    protected float mCurrentScaleFactor;
    protected int mDoubleTapDirection;
    protected GestureDetector mGestureDetector;
    protected a mGestureListener;
    protected float mScaleFactor;
    protected ScaleGestureDetector mScaleGestureDetector;
    protected b mScaleListener;
    protected int mTouchSlop;

    class a extends SimpleOnGestureListener {
        a() {
        }

        public boolean onDoubleTap(MotionEvent r6_MotionEvent) {
            float r0f = Math.min(MultiTouchImageView.this.maxZoom(), Math.max(MultiTouchImageView.this.onDoubleTapPost(MultiTouchImageView.this.getScale(), MultiTouchImageView.this.getMaxZoom()), MIN_ZOOM));
            MultiTouchImageView.this.mCurrentScaleFactor = r0f;
            MultiTouchImageView.this.zoomTo(r0f, r6_MotionEvent.getX(), r6_MotionEvent.getY(), 200.0f);
            MultiTouchImageView.this.invalidate();
            return super.onDoubleTap(r6_MotionEvent);
        }

        public boolean onFling(MotionEvent r6_MotionEvent, MotionEvent r7_MotionEvent, float r8f, float r9f) {
            float r3f = 2.0f;
            if (r6_MotionEvent.getPointerCount() > 1 || r7_MotionEvent.getPointerCount() > 1 || MultiTouchImageView.this.mScaleGestureDetector.isInProgress()) {
                return false;
            }
            float r0f = r7_MotionEvent.getX() - r6_MotionEvent.getX();
            float r1f = r7_MotionEvent.getY() - r6_MotionEvent.getY();
            if (Math.abs(r8f) <= 800.0f && Math.abs(r9f) <= 800.0f) {
                return super.onFling(r6_MotionEvent, r7_MotionEvent, r8f, r9f);
            }
            MultiTouchImageView.this.scrollBy(r0f / r3f, r1f / r3f, 100.0f);
            MultiTouchImageView.this.invalidate();
            return super.onFling(r6_MotionEvent, r7_MotionEvent, r8f, r9f);
        }

        public boolean onScroll(MotionEvent r4_MotionEvent, MotionEvent r5_MotionEvent, float r6f, float r7f) {
            if (r4_MotionEvent == null || r5_MotionEvent == null || r4_MotionEvent.getPointerCount() > 1 || r5_MotionEvent.getPointerCount() > 1 || MultiTouchImageView.this.mScaleGestureDetector.isInProgress() || MultiTouchImageView.this.getScale() == 1.0f) {
                return false;
            }
            MultiTouchImageView.this.scrollBy(-r6f, -r7f);
            MultiTouchImageView.this.invalidate();
            return super.onScroll(r4_MotionEvent, r5_MotionEvent, r6f, r7f);
        }

        public boolean onSingleTapConfirmed(MotionEvent r3_MotionEvent) {
            if (MultiTouchImageView.this.clickListener != null) {
                MultiTouchImageView.this.clickListener.onClick(MultiTouchImageView.this);
            }
            return super.onSingleTapConfirmed(r3_MotionEvent);
        }
    }

    class b extends SimpleOnScaleGestureListener {
        b() {
        }

        public boolean onScale(ScaleGestureDetector r7_ScaleGestureDetector) {
            float r0f = Math.min(MultiTouchImageView.this.getMaxZoom(), Math.max(MultiTouchImageView.this.mCurrentScaleFactor * r7_ScaleGestureDetector.getScaleFactor(), MIN_ZOOM));
            MultiTouchImageView.this.zoomTo(r0f, r7_ScaleGestureDetector.getFocusX(), r7_ScaleGestureDetector.getFocusY());
            MultiTouchImageView.this.mCurrentScaleFactor = Math.min(MultiTouchImageView.this.getMaxZoom(), Math.max(r0f, MIN_ZOOM));
            MultiTouchImageView.this.mDoubleTapDirection = 1;
            MultiTouchImageView.this.invalidate();
            return true;
        }
    }

    public MultiTouchImageView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        this.context = r1_Context;
    }

    protected void init() {
        super.init();
        this.mTouchSlop = ViewConfiguration.getTouchSlop();
        this.mGestureListener = new a();
        this.mGestureDetector = new GestureDetector(getContext(), this.mGestureListener, null, true);
        this.mScaleListener = new b();
        this.mScaleGestureDetector = new ScaleGestureDetector(getContext(), this.mScaleListener);
        this.mCurrentScaleFactor = 1.0f;
        this.mDoubleTapDirection = 1;
    }

    protected float onDoubleTapPost(float r3f, float r4f) {
        if (this.mDoubleTapDirection == 1) {
            if (this.mScaleFactor * 2.0f + r3f <= r4f) {
                return r3f + this.mScaleFactor;
            }
            this.mDoubleTapDirection = -1;
            return r4f;
        } else {
            this.mDoubleTapDirection = 1;
            return 1.0f;
        }
    }

    public boolean onTouchEvent(MotionEvent r3_MotionEvent) {
        this.mScaleGestureDetector.onTouchEvent(r3_MotionEvent);
        if (!this.mScaleGestureDetector.isInProgress()) {
            this.mGestureDetector.onTouchEvent(r3_MotionEvent);
        }
        switch ((r3_MotionEvent.getAction() & 255)) {
            case XListViewHeader.STATE_READY:
                if (getScale() < 1.0f) {
                    zoomTo(1.0f);
                }
                break;
        }
        return true;
    }

    protected void onZoom(float r2f) {
        super.onZoom(r2f);
        if (!this.mScaleGestureDetector.isInProgress()) {
            this.mCurrentScaleFactor = r2f;
        }
    }

    public void setOnClickListener(OnClickListener r1_OnClickListener) {
        this.clickListener = r1_OnClickListener;
        super.setOnClickListener(r1_OnClickListener);
    }
}
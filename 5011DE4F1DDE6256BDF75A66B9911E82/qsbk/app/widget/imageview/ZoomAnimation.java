package qsbk.app.widget.imageview;

import android.graphics.PointF;

public class ZoomAnimation implements Animation {
    private long animationLengthMS;
    private boolean firstFrame;
    private float scaleDiff;
    private float startScale;
    private float startX;
    private float startY;
    private long totalTime;
    private float touchX;
    private float touchY;
    private float xDiff;
    private float yDiff;
    private float zoom;
    private ZoomAnimationListener zoomAnimationListener;

    public ZoomAnimation() {
        this.firstFrame = true;
        this.animationLengthMS = 200;
        this.totalTime = 0;
    }

    public long getAnimationLengthMS() {
        return this.animationLengthMS;
    }

    public float getTouchX() {
        return this.touchX;
    }

    public float getTouchY() {
        return this.touchY;
    }

    public float getZoom() {
        return this.zoom;
    }

    public ZoomAnimationListener getZoomAnimationListener() {
        return this.zoomAnimationListener;
    }

    public void reset() {
        this.firstFrame = true;
        this.totalTime = 0;
    }

    public void setAnimationLengthMS(long r1j) {
        this.animationLengthMS = r1j;
    }

    public void setTouchX(float r1f) {
        this.touchX = r1f;
    }

    public void setTouchY(float r1f) {
        this.touchY = r1f;
    }

    public void setZoom(float r1f) {
        this.zoom = r1f;
    }

    public void setZoomAnimationListener(ZoomAnimationListener r1_ZoomAnimationListener) {
        this.zoomAnimationListener = r1_ZoomAnimationListener;
    }

    public boolean update(GestureImageView r7_GestureImageView, long r8j) {
        if (this.firstFrame) {
            this.firstFrame = false;
            this.startX = r7_GestureImageView.getImageX();
            this.startY = r7_GestureImageView.getImageY();
            this.startScale = r7_GestureImageView.getScale();
            this.scaleDiff = this.zoom * this.startScale - this.startScale;
            if (this.scaleDiff > 0.0f) {
                VectorF r1_VectorF = new VectorF();
                r1_VectorF.setStart(new PointF(this.touchX, this.touchY));
                r1_VectorF.setEnd(new PointF(this.startX, this.startY));
                r1_VectorF.calculateAngle();
                r1_VectorF.length = r1_VectorF.calculateLength() * this.zoom;
                r1_VectorF.calculateEndPoint();
                this.xDiff = r1_VectorF.end.x - this.startX;
                this.yDiff = r1_VectorF.end.y - this.startY;
            } else {
                this.xDiff = r7_GestureImageView.getCenterX() - this.startX;
                this.yDiff = r7_GestureImageView.getCenterY() - this.startY;
            }
        }
        this.totalTime += r8j;
        float r1f = ((float) this.totalTime) / ((float) this.animationLengthMS);
        float r2f;
        if (r1f < 1.0f) {
            if (r1f > 0.0f) {
                float r0f = this.scaleDiff * r1f + this.startScale;
                r2f = this.xDiff * r1f + this.startX;
                r1f = r1f * this.yDiff + this.startY;
                if (this.zoomAnimationListener != null) {
                    this.zoomAnimationListener.onZoom(r0f, r2f, r1f);
                }
            }
            return true;
        } else {
            r1f = this.scaleDiff + this.startScale;
            r2f = this.xDiff + this.startX;
            float r3f = this.yDiff + this.startY;
            if (this.zoomAnimationListener == null) {
                return false;
            }
            this.zoomAnimationListener.onZoom(r1f, r2f, r3f);
            this.zoomAnimationListener.onComplete();
            return false;
        }
    }
}
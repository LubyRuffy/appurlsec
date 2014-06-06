package qsbk.app.widget.imageview;

public class MoveAnimation implements Animation {
    private long animationTimeMS;
    private boolean firstFrame;
    private MoveAnimationListener moveAnimationListener;
    private float startX;
    private float startY;
    private float targetX;
    private float targetY;
    private long totalTime;

    public MoveAnimation() {
        this.firstFrame = true;
        this.animationTimeMS = 100;
        this.totalTime = 0;
    }

    public long getAnimationTimeMS() {
        return this.animationTimeMS;
    }

    public float getTargetX() {
        return this.targetX;
    }

    public float getTargetY() {
        return this.targetY;
    }

    public void reset() {
        this.firstFrame = true;
        this.totalTime = 0;
    }

    public void setAnimationTimeMS(long r1j) {
        this.animationTimeMS = r1j;
    }

    public void setMoveAnimationListener(MoveAnimationListener r1_MoveAnimationListener) {
        this.moveAnimationListener = r1_MoveAnimationListener;
    }

    public void setTargetX(float r1f) {
        this.targetX = r1f;
    }

    public void setTargetY(float r1f) {
        this.targetY = r1f;
    }

    public boolean update(GestureImageView r6_GestureImageView, long r7j) {
        this.totalTime += r7j;
        if (this.firstFrame) {
            this.firstFrame = false;
            this.startX = r6_GestureImageView.getImageX();
            this.startY = r6_GestureImageView.getImageY();
        }
        if (this.totalTime < this.animationTimeMS) {
            float r0f = ((float) this.totalTime) / ((float) this.animationTimeMS);
            float r1f = (this.targetX - this.startX) * r0f + this.startX;
            r0f = r0f * (this.targetY - this.startY) + this.startY;
            if (this.moveAnimationListener != null) {
                this.moveAnimationListener.onMove(r1f, r0f);
            }
            return true;
        } else {
            if (this.moveAnimationListener == null) {
                return false;
            }
            this.moveAnimationListener.onMove(this.targetX, this.targetY);
            return false;
        }
    }
}
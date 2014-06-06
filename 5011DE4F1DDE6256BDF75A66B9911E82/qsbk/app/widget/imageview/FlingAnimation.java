package qsbk.app.widget.imageview;

public class FlingAnimation implements Animation {
    private float factor;
    private FlingAnimationListener listener;
    private float threshold;
    private float velocityX;
    private float velocityY;

    public FlingAnimation() {
        this.factor = 0.85f;
        this.threshold = 10.0f;
    }

    public void setFactor(float r1f) {
        this.factor = r1f;
    }

    public void setListener(FlingAnimationListener r1_FlingAnimationListener) {
        this.listener = r1_FlingAnimationListener;
    }

    public void setVelocityX(float r1f) {
        this.velocityX = r1f;
    }

    public void setVelocityY(float r1f) {
        this.velocityY = r1f;
    }

    public boolean update(GestureImageView r5_GestureImageView, long r6j) {
        boolean r0z;
        float r0f = ((float) r6j) / 1000.0f;
        float r1f = this.velocityX * r0f;
        float r2f = this.velocityY * r0f;
        this.velocityX *= this.factor;
        this.velocityY *= this.factor;
        r0z = (Math.abs(this.velocityX) > this.threshold ? 1 : (Math.abs(this.velocityX) == this.threshold? 0 : -1)) > 0 && Math.abs(this.velocityY) > this.threshold;
        if (this.listener != null) {
            this.listener.onMove(r1f, r2f);
            if (!r0z) {
                this.listener.onComplete();
            }
        }
        return r0z;
    }
}
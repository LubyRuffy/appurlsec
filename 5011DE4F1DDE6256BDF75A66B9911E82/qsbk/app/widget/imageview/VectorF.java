package qsbk.app.widget.imageview;

import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;

public class VectorF {
    public float angle;
    public final PointF end;
    public float length;
    public final PointF start;

    public VectorF() {
        this.start = new PointF();
        this.end = new PointF();
    }

    public float calculateAngle() {
        this.angle = MathUtils.angle(this.start, this.end);
        return this.angle;
    }

    public void calculateEndPoint() {
        this.end.x = FloatMath.cos(this.angle) * this.length + this.start.x;
        this.end.y = FloatMath.sin(this.angle) * this.length + this.start.y;
    }

    public float calculateLength() {
        this.length = MathUtils.distance(this.start, this.end);
        return this.length;
    }

    public void set(MotionEvent r5_MotionEvent) {
        this.start.x = r5_MotionEvent.getX(0);
        this.start.y = r5_MotionEvent.getY(0);
        this.end.x = r5_MotionEvent.getX(1);
        this.end.y = r5_MotionEvent.getY(1);
    }

    public void setEnd(PointF r3_PointF) {
        this.end.x = r3_PointF.x;
        this.end.y = r3_PointF.y;
    }

    public void setStart(PointF r3_PointF) {
        this.start.x = r3_PointF.x;
        this.start.y = r3_PointF.y;
    }
}
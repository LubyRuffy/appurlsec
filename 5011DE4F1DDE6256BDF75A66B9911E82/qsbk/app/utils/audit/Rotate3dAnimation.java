package qsbk.app.utils.audit;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3dAnimation extends Animation {
    public static final String ROTATE_X = "x";
    public static final String ROTATE_Y = "y";
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final boolean f;
    private final String g;
    private Camera h;

    public Rotate3dAnimation(float r1f, float r2f, float r3f, float r4f, float r5f, boolean r6z, String r7_String) {
        this.a = r1f;
        this.b = r2f;
        this.c = r3f;
        this.d = r4f;
        this.e = r5f;
        this.f = r6z;
        this.g = r7_String;
    }

    protected void applyTransformation(float r9f, Transformation r10_Transformation) {
        float r0f = this.a;
        r0f += (this.b - r0f) * r9f;
        float r1f = this.c;
        float r2f = this.d;
        Camera r3_Camera = this.h;
        Matrix r4_Matrix = r10_Transformation.getMatrix();
        r3_Camera.save();
        if (this.f) {
            r3_Camera.translate(0.0f, 0.0f, this.e * r9f);
        } else {
            r3_Camera.translate(0.0f, 0.0f, this.e * (1.0f - r9f));
        }
        if (ROTATE_X.equalsIgnoreCase(this.g)) {
            r3_Camera.rotateX(r0f);
        } else {
            r3_Camera.rotateY(r0f);
        }
        r3_Camera.getMatrix(r4_Matrix);
        r3_Camera.restore();
        r4_Matrix.preTranslate(-r1f, -r2f);
        r4_Matrix.postTranslate(r1f, r2f);
    }

    public void initialize(int r2i, int r3i, int r4i, int r5i) {
        super.initialize(r2i, r3i, r4i, r5i);
        this.h = new Camera();
    }
}
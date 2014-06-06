package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

public class EdgeEffectCompat {
    private static final c b;
    private Object a;

    static interface c {
        public boolean draw(Object r1_Object, Canvas r2_Canvas);

        public void finish(Object r1_Object);

        public boolean isFinished(Object r1_Object);

        public Object newEdgeEffect(Context r1_Context);

        public boolean onAbsorb(Object r1_Object, int r2i);

        public boolean onPull(Object r1_Object, float r2f);

        public boolean onRelease(Object r1_Object);

        public void setSize(Object r1_Object, int r2i, int r3i);
    }

    static class a implements c {
        a() {
        }

        public boolean draw(Object r2_Object, Canvas r3_Canvas) {
            return false;
        }

        public void finish(Object r1_Object) {
        }

        public boolean isFinished(Object r2_Object) {
            return true;
        }

        public Object newEdgeEffect(Context r2_Context) {
            return null;
        }

        public boolean onAbsorb(Object r2_Object, int r3i) {
            return false;
        }

        public boolean onPull(Object r2_Object, float r3f) {
            return false;
        }

        public boolean onRelease(Object r2_Object) {
            return false;
        }

        public void setSize(Object r1_Object, int r2i, int r3i) {
        }
    }

    static class b implements c {
        b() {
        }

        public boolean draw(Object r2_Object, Canvas r3_Canvas) {
            return f.draw(r2_Object, r3_Canvas);
        }

        public void finish(Object r1_Object) {
            f.finish(r1_Object);
        }

        public boolean isFinished(Object r2_Object) {
            return f.isFinished(r2_Object);
        }

        public Object newEdgeEffect(Context r2_Context) {
            return f.newEdgeEffect(r2_Context);
        }

        public boolean onAbsorb(Object r2_Object, int r3i) {
            return f.onAbsorb(r2_Object, r3i);
        }

        public boolean onPull(Object r2_Object, float r3f) {
            return f.onPull(r2_Object, r3f);
        }

        public boolean onRelease(Object r2_Object) {
            return f.onRelease(r2_Object);
        }

        public void setSize(Object r1_Object, int r2i, int r3i) {
            f.setSize(r1_Object, r2i, r3i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            b = new b();
        } else {
            b = new a();
        }
    }

    public EdgeEffectCompat(Context r2_Context) {
        this.a = b.newEdgeEffect(r2_Context);
    }

    public boolean draw(Canvas r3_Canvas) {
        return b.draw(this.a, r3_Canvas);
    }

    public void finish() {
        b.finish(this.a);
    }

    public boolean isFinished() {
        return b.isFinished(this.a);
    }

    public boolean onAbsorb(int r3i) {
        return b.onAbsorb(this.a, r3i);
    }

    public boolean onPull(float r3f) {
        return b.onPull(this.a, r3f);
    }

    public boolean onRelease() {
        return b.onRelease(this.a);
    }

    public void setSize(int r3i, int r4i) {
        b.setSize(this.a, r3i, r4i);
    }
}
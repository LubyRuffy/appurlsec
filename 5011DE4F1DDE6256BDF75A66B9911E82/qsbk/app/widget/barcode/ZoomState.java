package qsbk.app.widget.barcode;

import java.util.Observable;

public class ZoomState extends Observable {
    private static float f;
    private static float g;
    private static float h;
    private static float i;
    private float a;
    private float b;
    protected float c;
    protected float d;
    private float e;

    static {
        f = -100.0f;
        g = 100.0f;
        h = -100.0f;
        i = 100.0f;
    }

    public ZoomState() {
        this.c = 0.2f;
        this.d = 0.4f;
    }

    private float a(float r2f) {
        if (r2f > getMaxZoom()) {
            return getMaxZoom();
        }
        if (r2f < getMinZoom()) {
            return getMinZoom();
        }
        return r2f;
    }

    public float getMaxZoom() {
        return this.d;
    }

    public float getMinZoom() {
        return this.c;
    }

    public float getPanX() {
        return this.b;
    }

    public float getPanY() {
        return this.e;
    }

    public float getZoom() {
        return this.a;
    }

    public float getZoomX(float r3f) {
        return Math.min(this.a, this.a * r3f);
    }

    public float getZoomY(float r3f) {
        return Math.min(this.a, this.a / r3f);
    }

    public void setPanX(float r2f) {
        if (r2f != this.b) {
            this.b = r2f;
            setChanged();
        }
    }

    public void setPanY(float r2f) {
        if (r2f != this.e) {
            this.e = r2f;
            setChanged();
        }
    }

    public void setZoom(float r3f) {
        float r0f = a(r3f);
        if (r0f != this.a) {
            this.a = r0f;
            setChanged();
        }
    }
}
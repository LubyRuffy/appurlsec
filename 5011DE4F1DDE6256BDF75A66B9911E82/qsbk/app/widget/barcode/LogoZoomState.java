package qsbk.app.widget.barcode;

public class LogoZoomState extends ZoomState {
    protected float a;
    protected float b;

    public LogoZoomState() {
        this.a = 0.1f;
        this.b = 0.25f;
    }

    public float getMaxZoom() {
        return this.b;
    }

    public float getMinZoom() {
        return this.a;
    }
}
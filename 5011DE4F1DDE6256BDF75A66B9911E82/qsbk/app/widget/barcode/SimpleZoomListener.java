package qsbk.app.widget.barcode;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.widget.FrameLayout;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;
import qsbk.app.widget.listview.XListViewHeader;

public class SimpleZoomListener implements OnTouchListener {
    private ZoomState a;
    private ControlType b;
    private float c;
    private float d;

    public static enum ControlType {
        PAN,
        ZOOM;


        static {
            PAN = new qsbk.app.widget.barcode.SimpleZoomListener.ControlType("PAN", 0);
            ZOOM = new qsbk.app.widget.barcode.SimpleZoomListener.ControlType("ZOOM", 1);
            qsbk.app.widget.barcode.SimpleZoomListener.ControlType[] r0_qsbk_app_widget_barcode_SimpleZoomListener_ControlTypeA = new qsbk.app.widget.barcode.SimpleZoomListener.ControlType[2];
            r0_qsbk_app_widget_barcode_SimpleZoomListener_ControlTypeA[0] = PAN;
            r0_qsbk_app_widget_barcode_SimpleZoomListener_ControlTypeA[1] = ZOOM;
            a = r0_qsbk_app_widget_barcode_SimpleZoomListener_ControlTypeA;
        }
    }

    public SimpleZoomListener() {
        this.b = ControlType.ZOOM;
    }

    protected float a() {
        return 1.32f;
    }

    protected void a(View r7_View, float r8f, float r9f) {
        BarcodeView r0_BarcodeView = (BarcodeView) r7_View;
        Rect r1_Rect = r0_BarcodeView.getInnerRect();
        Bitmap r2_Bitmap = r0_BarcodeView.getLeftTopImage();
        Bitmap r3_Bitmap = r0_BarcodeView.getRightBottomImage();
        ViewParent r0_ViewParent = r7_View.getParent();
        if (r8f < ((float) (r1_Rect.left - r2_Bitmap.getWidth() / 2)) || r8f > ((float) (r1_Rect.left + r2_Bitmap.getWidth())) || r9f < ((float) (r1_Rect.top - r2_Bitmap.getHeight() / 2))) {
            if (r8f > ((float) r1_Rect.right) + ((float) r3_Bitmap.getWidth()) * this.a.getZoom() || r8f < ((float) r1_Rect.right) - ((float) r3_Bitmap.getWidth()) * this.a.getZoom() || r9f > ((float) (r1_Rect.bottom + r3_Bitmap.getHeight() / 2)) || r9f < ((float) (r1_Rect.bottom - r3_Bitmap.getHeight() * 2))) {
                if (((BarcodeView) r7_View).getRightBottomRect() == null || (!((BarcodeView) r7_View).getRightBottomRect().contains((int) r8f, (int) r9f))) {
                    this.b = ControlType.PAN;
                } else {
                    this.b = ControlType.ZOOM;
                }
            } else {
                this.b = ControlType.ZOOM;
            }
        } else {
            if (r9f <= ((float) (r2_Bitmap.getHeight() + r1_Rect.top))) {
                if (r0_ViewParent == null || (!r0_ViewParent instanceof FrameLayout)) {
                    return;
                }
                ((FrameLayout) r0_ViewParent).removeView(r7_View);
                r7_View.setOnTouchListener(null);
                ((BarcodeView) r7_View).getZoomState().deleteObservers();
                OnCloseListener r0_OnCloseListener = ((BarcodeView) r7_View).getOnCloseListener();
                if (r0_OnCloseListener != null) {
                    r0_OnCloseListener.onClose();
                }
                return;
            }
            if (r8f > ((float) r1_Rect.right) + ((float) r3_Bitmap.getWidth()) * this.a.getZoom() || r8f < ((float) r1_Rect.right) - ((float) r3_Bitmap.getWidth()) * this.a.getZoom() || r9f > ((float) (r1_Rect.bottom + r3_Bitmap.getHeight() / 2)) || r9f < ((float) (r1_Rect.bottom - r3_Bitmap.getHeight() * 2))) {
                if (((BarcodeView) r7_View).getRightBottomRect() == null || ((BarcodeView) r7_View).getRightBottomRect().contains((int) r8f, (int) r9f)) {
                    this.b = ControlType.PAN;
                } else {
                    this.b = ControlType.ZOOM;
                }
            } else {
                this.b = ControlType.ZOOM;
            }
        }
    }

    public boolean onTouch(View r12_View, MotionEvent r13_MotionEvent) {
        int r0i = r13_MotionEvent.getAction();
        float r1f = r13_MotionEvent.getX();
        float r2f = r13_MotionEvent.getY();
        switch (r0i) {
            case XListViewHeader.STATE_NORMAL:
                this.c = r1f;
                this.d = r2f;
                a(r12_View, r1f, r2f);
                break;
            case XListViewHeader.STATE_REFRESHING:
                float r0f = (r1f - this.c) / ((float) r12_View.getWidth());
                float r3f = (r2f - this.d) / ((float) r12_View.getHeight());
                if (this.b == ControlType.ZOOM) {
                    this.a.setZoom(((float) Math.pow(20.0d, ((double) r3f) * 2.5d)) * this.a.getZoom());
                    this.a.notifyObservers();
                } else {
                    float r4f = 1.0f / this.a.getZoom();
                    this.a.setPanX(this.a.getPanX() - r0f * r4f);
                    this.a.setPanY(this.a.getPanY() - r3f * (a() * r4f));
                    this.a.notifyObservers();
                }
                this.c = r1f;
                this.d = r2f;
                break;
        }
        return true;
    }

    public void setControlType(ControlType r1_ControlType) {
        this.b = r1_ControlType;
    }

    public void setZoomState(ZoomState r1_ZoomState) {
        this.a = r1_ZoomState;
    }
}
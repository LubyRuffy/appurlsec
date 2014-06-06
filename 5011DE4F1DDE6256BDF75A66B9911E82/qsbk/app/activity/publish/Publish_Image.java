package qsbk.app.activity.publish;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.File;
import java.util.Random;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ImageUtils;
import qsbk.app.widget.barcode.BarcodeView;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;
import qsbk.app.widget.barcode.Layer;
import qsbk.app.widget.barcode.LogoMoveListener;
import qsbk.app.widget.barcode.LogoView;
import qsbk.app.widget.barcode.LogoZoomState;
import qsbk.app.widget.barcode.SimpleZoomListener;
import qsbk.app.widget.barcode.ZoomState;

public class Publish_Image extends FragmentActivity implements OnClickListener, OnCloseListener {
    private static int x;
    private Bitmap A;
    private BarcodeView B;
    private ZoomState C;
    private SimpleZoomListener D;
    private Paint E;
    private int F;
    private Button G;
    private Toast H;
    private final Handler I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private float P;
    private BarcodeView Q;
    ImageView n;
    FrameLayout o;
    DisplayMetrics p;
    Button q;
    Button r;
    Button s;
    Bitmap t;
    private Bitmap u;
    private Bitmap v;
    private boolean w;
    private Bitmap y;
    private Bitmap z;

    static {
        x = 0;
    }

    public Publish_Image() {
        this.w = false;
        this.E = new Paint(2);
        this.F = 0;
        this.I = new Handler();
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0.0f;
    }

    private void a(Bitmap r5_Bitmap) {
        if (r5_Bitmap == null || r5_Bitmap.isRecycled()) {
        } else {
            boolean r0z;
            Log.e(Publish_Image.class.getSimpleName(), r5_Bitmap.getWidth() + "==" + r5_Bitmap.getHeight());
            r0z = r5_Bitmap.getWidth() > 250 && r5_Bitmap.getHeight() > 250 && this.v != null;
            this.w = r0z;
        }
    }

    private void a(ZoomState r6_ZoomState) {
        Random r0_Random = new Random(System.currentTimeMillis());
        r6_ZoomState.setPanX((r0_Random.nextFloat() + 0.8f) / 2.0f);
        r6_ZoomState.setPanY((r0_Random.nextFloat() + 0.8f) / 2.0f);
        r6_ZoomState.setZoom(0.3f);
        r6_ZoomState.notifyObservers();
    }

    private void a(ZoomState r6_ZoomState, Rect r7_Rect) {
        int r0i = this.M;
        r0i = this.L;
        int r1i = Math.round(5.0f * this.p.density + 0.5f);
        r6_ZoomState.setZoom(Math.min(((float) this.N) / ((float) this.L), ((float) this.O) / ((float) this.M)) * (r6_ZoomState.getMaxZoom() - r6_ZoomState.getMinZoom()) + r6_ZoomState.getMinZoom());
        float r2f = (r6_ZoomState.getZoomX(this.P) * ((float) this.M)) / ((float) this.K);
        r6_ZoomState.setPanX(((((float) this.M) / (2.0f * r2f)) - (((float) (r7_Rect.left + r1i)) / r2f)) / ((float) this.K));
        r6_ZoomState.setPanY((((((float) (r0i - r7_Rect.bottom)) - ((((float) r1i) * ((float) this.L)) / ((float) this.N))) + ((float) this.J)) - ((float) (this.L / 2))) / (((float) this.J) * ((r6_ZoomState.getZoomY(this.P) * ((float) this.L)) / ((float) this.J))));
        r6_ZoomState.notifyObservers();
    }

    private void b(Bitmap r4_Bitmap) {
        if (r4_Bitmap == null) {
            Toast r0_Toast = Toast.makeText(this, "\u83b7\u53d6\u56fe\u7247\u5931\u8d25,\u8bf7\u91cd\u8bd5", 1);
            r0_Toast.setGravity(R.styleable.ActionBar_progressBarPadding, 0, 0);
            r0_Toast.show();
            setResult(0);
            finish();
        }
    }

    private void g() {
        if (this.t == null || this.t.getWidth() < 240 || this.t.getHeight() < 240) {
            this.G.setEnabled(false);
        }
    }

    private void h() {
        this.y = BitmapFactory.decodeResource(getResources(), R.drawable.qb_mask);
        this.z = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cancel);
        this.A = BitmapFactory.decodeResource(getResources(), R.drawable.icon_zoom);
    }

    private void i() {
        this.v = ((BitmapDrawable) getResources().getDrawable(R.drawable.head_logo)).getBitmap();
        this.J = this.v.getHeight();
        this.K = this.v.getWidth();
    }

    private void j() {
        this.I.postDelayed(new j(this, new i(this)), 100);
    }

    private void k() {
        this.P = (((float) this.K) / ((float) this.J)) / (((float) this.M) / ((float) this.L));
    }

    private void l() {
        ZoomState r1_ZoomState = new LogoZoomState();
        OnTouchListener r2_OnTouchListener = new LogoMoveListener();
        r2_OnTouchListener.setZoomState(r1_ZoomState);
        View r3_View = LayoutInflater.from(this).inflate(R.layout.logo, null);
        this.Q = (LogoView) r3_View.findViewById(R.id.logoview);
        this.Q.setZoomState(r1_ZoomState);
        this.Q.setImage(this.v);
        m();
        this.Q.setOnTouchListener(r2_OnTouchListener);
        this.o.addView(r3_View);
    }

    private void m() {
        if (this.Q != null) {
            Rect r0_Rect = new Rect(this.n.getLeft(), this.n.getTop(), this.n.getRight(), this.n.getBottom());
            this.Q.setBoundsRect(r0_Rect);
            ZoomState r1_ZoomState = this.Q.getZoomState();
            if (r1_ZoomState != null) {
                a(r1_ZoomState, r0_Rect);
            }
        }
    }

    private Bitmap n() {
        Bitmap r7_Bitmap = Bitmap.createBitmap(this.t.copy(Config.RGB_565, true));
        Canvas r8_Canvas = new Canvas(r7_Bitmap);
        int r9i = this.o.getChildCount();
        int r3i = 0;
        Rect r2_Rect = null;
        int r4i = -1;
        while (r3i < r9i) {
            Rect r0_Rect;
            int r1i;
            View r1_View = this.o.getChildAt(r3i);
            if (r1_View instanceof BarcodeView) {
                r0_Rect = ((BarcodeView) r1_View).getInnerRect();
                Rect r10_Rect = new Rect();
                r0_Rect.left -= this.n.getLeft();
                r0_Rect.top -= this.n.getTop();
                r10_Rect.right = r0_Rect.right - r0_Rect.left + r10_Rect.left;
                r10_Rect.bottom = r0_Rect.bottom - r0_Rect.top + r10_Rect.top;
                if (r1_View instanceof LogoView) {
                    r0_Rect = new Rect(r10_Rect);
                    r1i = r3i;
                } else {
                    r8_Canvas.drawBitmap(this.y, null, r10_Rect, this.E);
                    r0_Rect = r2_Rect;
                    r1i = r4i;
                }
            } else {
                r0_Rect = r2_Rect;
                r1i = r4i;
            }
            r3i++;
            r2_Rect = r0_Rect;
            r4i = r1i;
        }
        if (r4i == -1 || r2_Rect == null) {
            r8_Canvas.save(AdViewUtil.NETWORK_TYPE_VPON);
            r8_Canvas.restore();
            return r7_Bitmap;
        } else {
            r8_Canvas.drawBitmap(this.v, null, r2_Rect, this.E);
            r8_Canvas.save(AdViewUtil.NETWORK_TYPE_VPON);
            r8_Canvas.restore();
            return r7_Bitmap;
        }
    }

    private void o() {
        int r0i = this.o.getChildCount();
        if (r0i > this.F) {
            if (this.Q == null || r0i > this.F + 1) {
                if (this.H == null) {
                    this.H = Toast.makeText(this, "\u6253\u7801\u4e4b\u540e\u5c31\u4e0d\u80fd\u65cb\u8f6c\u4e86\uff0c\u4eb2~", 0);
                } else {
                    this.H.setView(this.H.getView());
                }
                this.H.show();
                return;
            }
        }
        f();
    }

    protected void c() {
        String r0_String = getIntent().getStringExtra("picpath");
        int r1i = this.M;
        int r2i = this.L;
        if (TextUtils.isEmpty(r0_String)) {
            setResult(0);
            finish();
        } else {
            this.u = ImageUtils.decodeBitmap(this, r0_String, r1i, r2i, null);
            b(this.u);
            if (this.u != null) {
                this.t = ImageUtils.scaleBitmapIfNecessary(this.u, r1i, r2i, false);
            }
            b(this.t);
        }
    }

    protected void d() {
        int r2i;
        View r1_View;
        if (this.y == null || this.y.isRecycled()) {
            if (this.z == null || this.z.isRecycled()) {
                if (this.A == null || this.A.isRecycled()) {
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (!r1_View instanceof BarcodeView) {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                        }
                        r2i--;
                    }
                } else {
                    this.A.recycle();
                    this.A = null;
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r2i--;
                        } else {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                            r2i--;
                        }
                    }
                }
            } else {
                this.z.recycle();
                this.z = null;
                if (this.A == null || this.A.isRecycled()) {
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                        }
                        r2i--;
                    }
                } else {
                    this.A.recycle();
                    this.A = null;
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r2i--;
                        } else {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                            r2i--;
                        }
                    }
                }
            }
        } else {
            this.y.recycle();
            this.y = null;
            if (this.z == null || this.z.isRecycled()) {
                if (this.A == null || this.A.isRecycled()) {
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                        }
                        r2i--;
                    }
                } else {
                    this.A.recycle();
                    this.A = null;
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r2i--;
                        } else {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                            r2i--;
                        }
                    }
                }
            } else {
                this.z.recycle();
                this.z = null;
                if (this.A == null || this.A.isRecycled()) {
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                        }
                        r2i--;
                    }
                } else {
                    this.A.recycle();
                    this.A = null;
                    r2i = this.o.getChildCount() - 1;
                    while (r2i >= this.F) {
                        r1_View = this.o.getChildAt(r2i);
                        if (r1_View instanceof BarcodeView) {
                            r2i--;
                        } else {
                            r1_View.setOnTouchListener(null);
                            ((BarcodeView) r1_View).getZoomState().deleteObservers();
                            ((BarcodeView) r1_View).setOnCloseListener(null);
                            this.o.removeView(r1_View);
                            r2i--;
                        }
                    }
                }
            }
        }
    }

    protected void e() {
        this.C = new ZoomState();
        this.D = new SimpleZoomListener();
        this.D.setZoomState(this.C);
        View r1_View = LayoutInflater.from(this).inflate(R.layout.barcode_image, null);
        this.B = (BarcodeView) r1_View.findViewById(R.id.zoomview);
        this.B.setZoomState(this.C);
        this.B.setImage(this.y);
        this.B.setLeftTopImage(this.z);
        this.B.setRightBottomImage(this.A);
        this.B.setOnCloseListener(this);
        this.B.setBoundsRect(new Rect(this.n.getLeft(), this.n.getTop(), this.n.getRight(), this.n.getBottom()));
        this.B.setOnTouchListener(this.D);
        a(this.C);
        this.o.addView(r1_View);
    }

    protected void f() {
        this.t = ImageUtils.fitRotate(this.t, 90, this.M, this.L, false);
        this.n.setImageBitmap(this.t);
        this.I.postDelayed(new k(this), 100);
    }

    public void initView() {
        this.n = (ImageView) findViewById(R.id.target_image);
        this.q = (Button) findViewById(R.id.leftBtn);
        this.q.setOnClickListener(this);
        this.r = (Button) findViewById(R.id.turn);
        this.r.setOnClickListener(this);
        this.s = (Button) findViewById(R.id.rightBtn);
        this.s.setOnClickListener(this);
        this.o = (Layer) findViewById(R.id.imageLayout);
        this.F = this.o.getChildCount();
        this.G = (Button) findViewById(R.id.add_btn);
        this.G.setOnClickListener(this);
    }

    public void onClick(View r5_View) {
        switch (r5_View.getId()) {
            case R.id.leftBtn:
                setResult(0);
                finish();
                break;
            case R.id.rightBtn:
                new File(DeviceUtils.getSDPath() + "/qsbk/send/send.jpg").delete();
                this.t = n();
                ((QsbkApp) getApplication()).setWaitSendBitmap(ImageUtils.createBitmapForWatermark(this.t));
                FileUtils.saveDrawable(this.t, "send.jpg", "qsbk/send", null);
                setResult(-1);
                finish();
                break;
            case R.id.add_btn:
                e();
                break;
            case R.id.turn:
                o();
                break;
        }
    }

    public void onClose() {
        int r0i = this.o.getChildCount();
        if (this.Q == null || r0i > this.F + 1) {
            if (r0i <= this.F) {
                this.r.setEnabled(true);
            }
        } else {
            this.r.setEnabled(true);
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        setContentView(R.layout.activity_publish_image);
        this.p = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.p);
        initView();
        h();
        i();
        j();
    }

    protected void onDestroy() {
        super.onDestroy();
        d();
    }
}
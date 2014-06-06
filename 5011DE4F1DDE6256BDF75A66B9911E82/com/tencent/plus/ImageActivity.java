package com.tencent.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.open.OpenApi;
import com.tencent.open.TContext;
import com.tencent.open.Util;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.utils.Base64;

// compiled from: ProGuard
public class ImageActivity extends Activity {
    RelativeLayout a;
    private TContext b;
    private OpenApi c;
    private String d;
    private Handler e;
    private TouchView f;
    private Button g;
    private Button h;
    private MaskView i;
    private TextView j;
    private ProgressBar k;
    private int l;
    private boolean m;
    private long n;
    private int o;
    private int p;
    private int q;
    private Rect r;
    private String s;
    private Bitmap t;
    private OnClickListener u;
    private OnClickListener v;
    private IRequestListener w;
    private IRequestListener x;

    public ImageActivity() {
        this.l = 0;
        this.m = false;
        this.n = 0;
        this.o = 0;
        this.p = 640;
        this.q = 640;
        this.r = new Rect();
        this.u = new i(this);
        this.v = new f(this);
        this.w = new h(this);
        this.x = new g(this);
    }

    private Bitmap a(String r9_String) {
        boolean r0z = true;
        Options r3_Options = new Options();
        r3_Options.inJustDecodeBounds = true;
        Uri r4_Uri = Uri.parse(r9_String);
        InputStream r1_InputStream = getContentResolver().openInputStream(r4_Uri);
        BitmapFactory.decodeStream(r1_InputStream, null, r3_Options);
        r1_InputStream.close();
        int r2i = r3_Options.outWidth;
        int r1i = r3_Options.outHeight;
        while (r2i * r1i > 4194304) {
            r2i /= 2;
            r1i /= 2;
            r0z *= 2;
        }
        r3_Options.inJustDecodeBounds = false;
        r3_Options.inSampleSize = r0z;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(r4_Uri), null, r3_Options);
    }

    private View a() {
        LayoutParams r0_LayoutParams = new LayoutParams(-1, -1);
        LayoutParams r1_LayoutParams = new LayoutParams(-1, -1);
        LayoutParams r2_LayoutParams = new LayoutParams(-2, -2);
        this.a = new RelativeLayout(this);
        this.a.setLayoutParams(r0_LayoutParams);
        this.a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        View r0_View = new RelativeLayout(this);
        r0_View.setLayoutParams(r2_LayoutParams);
        this.a.addView(r0_View);
        this.f = new TouchView(this);
        this.f.setLayoutParams(r1_LayoutParams);
        this.f.setScaleType(ScaleType.MATRIX);
        r0_View.addView(this.f);
        this.i = new MaskView(this);
        LayoutParams r3_LayoutParams = new RelativeLayout.LayoutParams(r1_LayoutParams);
        r3_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, -1);
        r3_LayoutParams.addRule(NearbySelectView.TIME_15MIN, -1);
        this.i.setLayoutParams(r3_LayoutParams);
        r0_View.addView(this.i);
        r0_View = new LinearLayout(this);
        r1_LayoutParams = new RelativeLayout.LayoutParams(-2, DensityUtil.dip2px(this, 80.0f));
        r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, -1);
        r0_View.setLayoutParams(r1_LayoutParams);
        r0_View.setOrientation(0);
        r0_View.setGravity(R.styleable.ActionBar_progressBarPadding);
        this.a.addView(r0_View);
        View r1_View = new ImageView(this);
        r1_View.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 24.0f), DensityUtil.dip2px(this, 24.0f)));
        r1_View.setImageDrawable(b("com.tencent.plus.logo.png"));
        r0_View.addView(r1_View);
        this.j = new TextView(this);
        r1_LayoutParams = new LinearLayout.LayoutParams(r2_LayoutParams);
        r1_LayoutParams.leftMargin = DensityUtil.dip2px(this, 7.0f);
        this.j.setLayoutParams(r1_LayoutParams);
        this.j.setEllipsize(TruncateAt.END);
        this.j.setSingleLine();
        this.j.setTextColor(-1);
        this.j.setTextSize(24.0f);
        this.j.setVisibility(Base64.DONT_BREAK_LINES);
        r0_View.addView(this.j);
        r0_View = new RelativeLayout(this);
        r1_LayoutParams = new RelativeLayout.LayoutParams(-1, DensityUtil.dip2px(this, 60.0f));
        r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, -1);
        r1_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY, -1);
        r0_View.setLayoutParams(r1_LayoutParams);
        r0_View.setBackgroundDrawable(b("com.tencent.plus.bar.png"));
        int r1i = DensityUtil.dip2px(this, 10.0f);
        r0_View.setPadding(r1i, r1i, r1i, 0);
        this.a.addView(r0_View);
        b r1_b = new b(this, this);
        int r3i = DensityUtil.dip2px(this, 14.0f);
        int r4i = DensityUtil.dip2px(this, 7.0f);
        this.h = new Button(this);
        this.h.setLayoutParams(new RelativeLayout.LayoutParams(DensityUtil.dip2px(this, 78.0f), DensityUtil.dip2px(this, 45.0f)));
        this.h.setText("\u53d6\u6d88");
        this.h.setTextColor(-1);
        this.h.setTextSize(18.0f);
        this.h.setPadding(r3i, r4i, r3i, r4i);
        r1_b.b(this.h);
        r0_View.addView(this.h);
        this.g = new Button(this);
        LayoutParams r5_LayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(this, 78.0f), DensityUtil.dip2px(this, 45.0f));
        r5_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE, -1);
        this.g.setLayoutParams(r5_LayoutParams);
        this.g.setTextColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setPadding(r3i, r4i, r3i, r4i);
        this.g.setText("\u9009\u53d6");
        r1_b.a(this.g);
        r0_View.addView(this.g);
        r1_View = new TextView(this);
        r3_LayoutParams = new RelativeLayout.LayoutParams(r2_LayoutParams);
        r3_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, -1);
        r1_View.setLayoutParams(r3_LayoutParams);
        r1_View.setText("\u79fb\u52a8\u548c\u7f29\u653e");
        r1_View.setPadding(0, DensityUtil.dip2px(this, 3.0f), 0, 0);
        r1_View.setTextSize(18.0f);
        r1_View.setTextColor(-1);
        r0_View.addView(r1_View);
        this.k = new ProgressBar(this);
        r0_LayoutParams = new RelativeLayout.LayoutParams(r2_LayoutParams);
        r0_LayoutParams.addRule(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, -1);
        r0_LayoutParams.addRule(NearbySelectView.TIME_15MIN, -1);
        this.k.setLayoutParams(r0_LayoutParams);
        this.k.setVisibility(Base64.DONT_BREAK_LINES);
        this.a.addView(this.k);
        return this.a;
    }

    private void a(int r3i, String r4_String, String r5_String, String r6_String) {
        Intent r0_Intent = new Intent();
        r0_Intent.putExtra(Constants.KEY_ERROR_CODE, r3i);
        r0_Intent.putExtra(Constants.KEY_ERROR_MSG, r5_String);
        r0_Intent.putExtra(Constants.KEY_ERROR_DETAIL, r6_String);
        r0_Intent.putExtra(Constants.KEY_RESPONSE, r4_String);
        setResult(-1, r0_Intent);
    }

    private void a(Bitmap r8_Bitmap) {
        Bundle r3_Bundle = new Bundle();
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        r8_Bitmap.compress(CompressFormat.JPEG, 40, r0_OutputStream);
        byte[] r0_byteA = r0_OutputStream.toByteArray();
        r8_Bitmap.recycle();
        r3_Bundle.putByteArray(Constants.PARAM_AVATAR_URI, r0_byteA);
        this.c.a(this.b.f(), Constants.GRAPH_SET_AVATAR, r3_Bundle, UsersAPI.HTTPMETHOD_POST, this.w, null);
    }

    private void a(String r3_String, int r4i) {
        this.e.post(new e(this, r3_String, r4i));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Drawable b(String r5_String) {
        /*
        r4_this = this;
        r0 = r4.getAssets();
        r1 = 0;
        r2 = r0.open(r5);	 //Catch:{ IOException -> 0x0011 }
        r0 = android.graphics.drawable.Drawable.createFromStream(r2, r5);	 //Catch:{ IOException -> 0x0011 }
        r2.close();	 //Catch:{ IOException -> 0x0019 }
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x0015:
        r1.printStackTrace();
        goto L_0x0010;
    L_0x0019:
        r1 = move-exception;
        goto L_0x0015;
        */

    }

    private void b() {
        try {
            this.t = a(this.s);
            if (this.t == null) {
                throw new IOException("cannot read picture: '" + this.s + "'!");
            } else {
                this.f.setImageBitmap(this.t);
                this.g.setOnClickListener(this.u);
                this.h.setOnClickListener(this.v);
                this.a.getViewTreeObserver().addOnGlobalLayoutListener(new j(this));
            }
        } catch (IOException e) {
            IOException r0_IOException = e;
            r0_IOException.printStackTrace();
            String r1_String = Constants.MSG_IMAGE_ERROR;
            b(r1_String, 1);
            a(Constants.ERROR_PARAM, null, r1_String, r0_IOException.getMessage());
            d();
        }
    }

    private void b(String r9_String, int r10i) {
        Toast r2_Toast = Toast.makeText(this, r9_String, 1);
        LinearLayout r0_LinearLayout = (LinearLayout) r2_Toast.getView();
        ((TextView) r0_LinearLayout.getChildAt(0)).setPadding(Base64.DONT_BREAK_LINES, 0, 0, 0);
        View r1_View = new ImageView(this);
        r1_View.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 16.0f), DensityUtil.dip2px(this, 16.0f)));
        if (r10i == 0) {
            r1_View.setImageDrawable(b("com.tencent.plus.ic_success.png"));
        } else {
            r1_View.setImageDrawable(b("com.tencent.plus.ic_error.png"));
        }
        r0_LinearLayout.addView(r1_View, 0);
        r0_LinearLayout.setOrientation(0);
        r0_LinearLayout.setGravity(R.styleable.ActionBar_progressBarPadding);
        r2_Toast.setView(r0_LinearLayout);
        r2_Toast.setGravity(R.styleable.ActionBar_progressBarPadding, 0, 0);
        r2_Toast.show();
    }

    private void c() {
        Matrix r3_Matrix = this.f.getImageMatrix();
        float[] r1_floatA = new float[9];
        r3_Matrix.getValues(r1_floatA);
        float r2f = r1_floatA[2];
        float r4f = r1_floatA[5];
        float r6f = r1_floatA[0];
        float r0f = ((float) this.p) / ((float) this.r.width());
        int r1i = (int) ((((float) this.r.left) - r2f) / r6f);
        int r2i = (int) ((((float) this.r.top) - r4f) / r6f);
        Matrix r5_Matrix = new Matrix();
        r5_Matrix.set(r3_Matrix);
        r5_Matrix.postScale(r0f, r0f);
        int r0i = (int) (650.0f / r6f);
        Bitmap r0_Bitmap = Bitmap.createBitmap(this.t, r1i, r2i, Math.min(this.t.getWidth() - r1i, r0i), Math.min(this.t.getHeight() - r2i, r0i), r5_Matrix, true);
        Bitmap r1_Bitmap = Bitmap.createBitmap(r0_Bitmap, 0, 0, this.p, this.q);
        r0_Bitmap.recycle();
        a(r1_Bitmap);
    }

    private void c(String r3_String) {
        CharSequence r0_CharSequence = d(r3_String);
        if (!RContactStorage.PRIMARY_KEY.equals(r0_CharSequence)) {
            this.j.setText(r0_CharSequence);
            this.j.setVisibility(0);
        }
    }

    private String d(String r4_String) {
        return r4_String.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", "&");
    }

    private void d() {
        finish();
        if (this.o != 0) {
            overridePendingTransition(0, this.o);
        }
    }

    private void e() {
        this.l++;
        this.c.a(this.b.f(), Constants.GRAPH_SIMPLE_USER_INFO, null, HttpManager.HTTPMETHOD_GET, this.x, null);
    }

    public void a(String r2_String, long r3j) {
        Util.a((Context)this, r2_String, r3j, this.b.d());
    }

    public void onBackPressed() {
        setResult(0);
        d();
    }

    public void onCreate(Bundle r9_Bundle) {
        super.onCreate(r9_Bundle);
        requestWindowFeature(1);
        setRequestedOrientation(1);
        setContentView(a());
        this.e = new Handler();
        Bundle r0_Bundle = getIntent().getBundleExtra(Constants.KEY_PARAMS);
        this.s = r0_Bundle.getString(Constants.PARAM_AVATAR_URI);
        this.d = r0_Bundle.getString(Constants.PARAM_AVATAR_RETURN_ACTIVITY);
        String r1_String = r0_Bundle.getString(Constants.PARAM_APP_ID);
        String r2_String = r0_Bundle.getString(ThirdParty.KEY_TOKEN);
        long r3j = r0_Bundle.getLong(ThirdParty.KEY_EXPIRES);
        String r5_String = r0_Bundle.getString(Constants.PARAM_OPEN_ID);
        this.o = r0_Bundle.getInt("exitAnim");
        this.b = new TContext(r1_String, getApplicationContext());
        this.b.a(r2_String, ((r3j - System.currentTimeMillis()) / 1000) + RContactStorage.PRIMARY_KEY);
        this.b.a(r5_String);
        this.c = new OpenApi(this.b);
        b();
        e();
        this.n = System.currentTimeMillis();
        a("10653", 0);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f.setImageBitmap(null);
        if (this.t == null || this.t.isRecycled()) {
        } else {
            this.t.recycle();
        }
    }
}
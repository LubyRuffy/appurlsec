package qsbk.app.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.cordova.Globalization;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.cache.ImageCache;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.Md5;
import qsbk.app.widget.imageview.GestureImageView;
import qsbk.app.widget.imageview.MultiTouchImageView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ImageViewer extends FragmentActivity implements OnClickListener {
    private String A;
    private String B;
    private String C;
    private Tracker D;
    private boolean E;
    private Display F;
    private Thread G;
    private Handler H;
    boolean n;
    Bitmap o;
    boolean p;
    boolean q;
    String r;
    int s;
    int t;
    MediaScannerConnection u;
    Handler v;
    private GestureImageView w;
    private MultiTouchImageView x;
    private ProgressBar y;
    private ImageView z;

    public ImageViewer() {
        this.n = false;
        this.p = false;
        this.q = false;
        this.r = RContactStorage.PRIMARY_KEY;
        this.E = false;
        this.s = 0;
        this.t = 10;
        this.H = new ap(this);
        this.u = null;
        this.v = new aq(this);
    }

    private void a(int r3i) {
        Message r0_Message = new Message();
        r0_Message.what = r3i;
        this.H.sendMessage(r0_Message);
    }

    private String b(String r5_String) {
        return new StringBuffer(getCacheDir().toString()).append(r5_String).append(File.separator).append(this.C.substring(this.C.lastIndexOf("/") + 1)).toString();
    }

    private Bitmap c(String r5_String) {
        InputStream r0_InputStream;
        Options r2_Options = new Options();
        r2_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(r5_String, r2_Options);
        r2_Options.inJustDecodeBounds = false;
        r2_Options.inPreferredConfig = Config.RGB_565;
        r2_Options.inPurgeable = true;
        r2_Options.inInputShareable = true;
        r2_Options.inSampleSize = calculateInSampleSize(r2_Options);
        try {
            r0_InputStream = new FileInputStream(r5_String);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            r0_InputStream = null;
        }
        return BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
    }

    private void c() {
        EasyTracker.getInstance().setContext(this);
        this.D = EasyTracker.getTracker();
        this.D.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.D);
    }

    private boolean d() {
        return new File(b(Constants.IMG_CACHE_PATH_MEDIUM)).exists();
    }

    private void e() {
        this.G = new ao(this, "qbk-ImageView");
        this.G.start();
    }

    private void f() {
        this.w.setClickable(true);
        this.x.setClickable(true);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.z.setOnClickListener(this);
    }

    private void g() {
        String r0_String = Constants.CONTENT_IMAGE_URL;
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(this.A).intValue() / 10000);
        r1_ObjectA[1] = this.A;
        r1_ObjectA[2] = Globalization.MEDIUM;
        r1_ObjectA[3] = this.B;
        r0_String = String.format(r0_String, r1_ObjectA);
        try {
            HttpClient r1_HttpClient = new DefaultHttpClient();
            HttpUriRequest r2_HttpUriRequest = new HttpGet(r0_String);
            r2_HttpUriRequest.addHeader("User-Agent", "qiushibalke_" + Constants.localVersionName);
            HttpEntity r0_HttpEntity = r1_HttpClient.execute(r2_HttpUriRequest).getEntity();
            this.s = (int) r0_HttpEntity.getContentLength();
            InputStream r0_InputStream = r0_HttpEntity.getContent();
            if (this.s < 1 || r0_InputStream == null) {
                a((int)XListViewFooter.STATE_NOMORE);
            } else {
                a(0);
                String r1_String = getCacheDir() + Constants.IMG_CACHE_PATH_MEDIUM;
                File r2_File = new File(r1_String);
                File r3_File = new File(r1_String, this.B);
                if (!r2_File.isDirectory()) {
                    r2_File.mkdirs();
                }
                if (!Thread.currentThread().isInterrupted()) {
                    FileOutputStream r1_FileOutputStream = new FileOutputStream(r3_File);
                    byte[] r2_byteA = new byte[1024];
                    while (true) {
                        int r3i = r0_InputStream.read(r2_byteA);
                        if (r3i == -1 || Thread.currentThread().isInterrupted()) {
                            if (!Thread.currentThread().isInterrupted()) {
                                a((int)XListViewHeader.STATE_REFRESHING);
                            }
                            r0_InputStream.close();
                            r1_FileOutputStream.close();
                        } else {
                            r1_FileOutputStream.write(r2_byteA, 0, r3i);
                            this.t = r3i + this.t;
                            a(1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Exception r0_Exception = e;
            a((int)XListViewFooter.STATE_NOMORE);
            r0_Exception.printStackTrace();
        }
    }

    private void h() {
        this.w = (GestureImageView) findViewById(R.id.multitouchimageview);
        this.x = (MultiTouchImageView) findViewById(R.id.multitouchimageview2);
        this.y = (ProgressBar) findViewById(R.id.progressbar);
        this.z = (ImageView) findViewById(R.id.saveBtn);
        if (this.E) {
            this.z.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    private void i() {
        if (this.s == this.t || this.G == null) {
        } else {
            File r0_File = new File(b(Constants.IMG_CACHE_PATH_MEDIUM));
            if (r0_File.exists()) {
                r0_File.delete();
            }
        }
    }

    public int calculateInSampleSize(Options r5_Options) {
        int r1i = r5_Options.outHeight;
        int r2i = r5_Options.outWidth;
        int r0i = 1;
        if (r1i <= this.F.getHeight() && r2i <= this.F.getWidth()) {
            return r0i;
        }
        r0i = r2i > r1i ? Math.round(((float) r1i) / ((float) this.F.getHeight())) : Math.round(((float) r2i) / ((float) this.F.getWidth()));
        while (((float) (r1i * r2i)) / ((float) (r0i * r0i)) > ((float) ((this.F.getWidth() * this.F.getHeight()) * 6))) {
            r0i++;
        }
        return r0i;
    }

    public void finish() {
        if (this.o != null) {
            this.o.recycle();
            this.o = null;
            this.x.setImageBitmap((Bitmap) null);
            System.gc();
        }
        if (this.G != null) {
            this.G.interrupt();
        }
        i();
        this.G = null;
        if (this.u == null || (!this.u.isConnected())) {
            super.finish();
        } else {
            this.u.disconnect();
            super.finish();
        }
    }

    public void onClick(View r5_View) {
        switch (r5_View.getId()) {
            case R.id.saveBtn:
                if (this.p) {
                    if (this.q) {
                        Toast.makeText(QsbkApp.mContext, this.r, 1).show();
                    } else {
                        if (this.o != null) {
                            if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
                                Toast.makeText(this, "\u672a\u53d1\u73b0SD\u5361,\u4fdd\u5b58\u5931\u8d25\uff01", 0).show();
                                r5_View.setVisibility(XListViewFooter.STATE_NODATA);
                            } else {
                                FileUtils.saveDrawable(this.o, this.B, "qsbk/qiushibaike", this.v);
                            }
                        }
                        this.z.setImageResource(R.drawable.icon_save_active);
                    }
                }
                break;
            case R.id.multitouchimageview:
            case R.id.multitouchimageview2:
                finish();
                break;
        }
    }

    protected void onCreate(Bundle r8_Bundle) {
        GestureImageView r1_GestureImageView = null;
        super.onCreate(r8_Bundle);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("audit"))) {
            setRequestedOrientation(0);
            this.E = true;
        }
        setContentView(R.layout.layout_imageviewer);
        this.F = getWindowManager().getDefaultDisplay();
        LayoutParams r0_LayoutParams = getWindow().getAttributes();
        r0_LayoutParams.width = this.F.getWidth();
        r0_LayoutParams.alpha = 1.0f;
        getWindow().setAttributes(r0_LayoutParams);
        this.A = getIntent().getStringExtra("contentId");
        this.B = getIntent().getStringExtra("imageName");
        String r0_String = Constants.CONTENT_IMAGE_URL;
        Object[] r2_ObjectA = new Object[4];
        r2_ObjectA[0] = Integer.valueOf(Integer.valueOf(this.A).intValue() / 10000);
        r2_ObjectA[1] = this.A;
        r2_ObjectA[2] = "small";
        r2_ObjectA[3] = this.B;
        this.C = String.format(r0_String, r2_ObjectA);
        h();
        f();
        Bitmap r0_Bitmap = ImageCache.findOrCreateCache((FragmentActivity)this, Constants.IMG_CACHE_PATH_PRE, (int) getResources().getDimension(R.dimen.image_thumbnail_size)).getBitmapFromDiskCache(Md5.MD5(String.valueOf(this.C)));
        this.w.setImageBitmap(r0_Bitmap);
        if (d()) {
            if (r0_Bitmap == null || r0_Bitmap.isRecycled()) {
                this.w.setImageBitmap((Bitmap) r1_GestureImageView);
                this.w.setVisibility(Base64.DONT_BREAK_LINES);
                this.w = r1_GestureImageView;
                this.x.setVisibility(0);
                r0_String = b(Constants.IMG_CACHE_PATH_MEDIUM);
                if (r0_String.endsWith(Util.PHOTO_DEFAULT_EXT) || r0_String.endsWith(".png")) {
                    this.o = c(r0_String);
                    if (this.o == null) {
                        this.x.setImageBitmap(this.o);
                        this.p = true;
                    }
                }
            } else {
                r0_Bitmap.recycle();
                this.w.setImageBitmap((Bitmap) r1_GestureImageView);
                this.w.setVisibility(Base64.DONT_BREAK_LINES);
                this.w = r1_GestureImageView;
                this.x.setVisibility(0);
                r0_String = b(Constants.IMG_CACHE_PATH_MEDIUM);
                if (r0_String.endsWith(Util.PHOTO_DEFAULT_EXT) || r0_String.endsWith(".png")) {
                    this.o = c(r0_String);
                    if (this.o == null) {
                        c();
                        this.D.trackView("\u67e5\u770b\u5927\u56fe/" + this.A);
                    } else {
                        this.x.setImageBitmap(this.o);
                        this.p = true;
                    }
                }
            }
        } else {
            this.y.setVisibility(0);
            e();
        }
        c();
        this.D.trackView("\u67e5\u770b\u5927\u56fe/" + this.A);
    }
}
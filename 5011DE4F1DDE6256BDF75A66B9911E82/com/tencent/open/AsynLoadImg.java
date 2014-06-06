package com.tencent.open;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
public class AsynLoadImg {
    private static String e;
    Activity a;
    private String b;
    private String c;
    private AsynLoadImgBack d;
    private long f;
    private Handler g;
    private Runnable h;

    public AsynLoadImg(Activity r3_Activity) {
        this.b = "share_qq_";
        this.h = new u(this);
        this.a = r3_Activity;
        this.g = new v(this, r3_Activity.getMainLooper());
    }

    public static Bitmap a(String r4_String) {
        Log.v("AsynLoadImg", "getbitmap:" + r4_String);
        try {
            HttpURLConnection r0_HttpURLConnection = (HttpURLConnection) new URL(r4_String).openConnection();
            r0_HttpURLConnection.setDoInput(true);
            r0_HttpURLConnection.connect();
            InputStream r1_InputStream = r0_HttpURLConnection.getInputStream();
            Bitmap r0_Bitmap = BitmapFactory.decodeStream(r1_InputStream);
            r1_InputStream.close();
            Log.v("AsynLoadImg", "image download finished." + r4_String);
            return r0_Bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }

    public void a(String r4_String, AsynLoadImgBack r5_AsynLoadImgBack) {
        Log.v("AsynLoadImg", "--save---");
        if (r4_String == null || r4_String.equals(RContactStorage.PRIMARY_KEY)) {
            r5_AsynLoadImgBack.saved(1, null);
        } else if (Util.b()) {
            e = Environment.getExternalStorageDirectory() + "/tmp/";
            this.f = System.currentTimeMillis();
            this.c = r4_String;
            this.d = r5_AsynLoadImgBack;
            new Thread(this.h).start();
        } else {
            r5_AsynLoadImgBack.saved(XListViewHeader.STATE_REFRESHING, null);
        }
    }

    public boolean a(Bitmap r5_Bitmap, String r6_String) {
        String r0_String = e;
        try {
            File r1_File = new File(r0_String);
            if (!r1_File.exists()) {
                r1_File.mkdir();
            }
            r0_String = r0_String + r6_String;
            Log.v("AsynLoadImg", "saveFile:" + r6_String);
            OutputStream r0_OutputStream = new BufferedOutputStream(new FileOutputStream(new File(r0_String)));
            r5_Bitmap.compress(CompressFormat.JPEG, 80, r0_OutputStream);
            r0_OutputStream.flush();
            r0_OutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("AsynLoadImg", "saveFile bmp fail---");
            return false;
        }
    }
}
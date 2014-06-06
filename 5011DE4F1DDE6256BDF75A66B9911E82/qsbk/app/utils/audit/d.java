package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import qsbk.app.utils.audit.SimpleImageLoader.onCallback;

// compiled from: SimpleImageLoader.java
class d implements RequestListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ProgressBar b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ Drawable e;
    final /* synthetic */ onCallback f;
    final /* synthetic */ String g;
    final /* synthetic */ Drawable h;
    final /* synthetic */ SimpleImageLoader i;

    d(SimpleImageLoader r1_SimpleImageLoader, ImageView r2_ImageView, ProgressBar r3_ProgressBar, int r4i, int r5i, Drawable r6_Drawable, onCallback r7_onCallback, String r8_String, Drawable r9_Drawable) {
        this.i = r1_SimpleImageLoader;
        this.a = r2_ImageView;
        this.b = r3_ProgressBar;
        this.c = r4i;
        this.d = r5i;
        this.e = r6_Drawable;
        this.f = r7_onCallback;
        this.g = r8_String;
        this.h = r9_Drawable;
    }

    public void onCanceled(Request r6_Request) {
        if (SimpleImageLoader.f) {
            Log.e(SimpleImageLoader.a, " Cancel invoke===========");
        }
        r6_Request.markFinished();
        this.i.e.remove(r6_Request.getUrl());
        this.i.a(this.a, this.b, this.e);
        if (this.f != null) {
            this.f.onFailed(this.a, this.g, new LoaderException("Download canceled."));
        }
    }

    public void onDownloading(Request r4_Request, int r5i, int r6i) {
        if (SimpleImageLoader.f) {
            Log.e(SimpleImageLoader.a, " Downloading invoke===========" + r6i + "/" + r5i);
        }
        this.i.a(this.b, r5i, r6i);
    }

    public void onError(Request r5_Request, LoaderException r6_LoaderException) {
        if (SimpleImageLoader.f) {
            Log.e(SimpleImageLoader.a, " Error invoke===========" + r6_LoaderException.toString());
        }
        r5_Request.markFinished();
        this.i.e.remove(r5_Request.getUrl());
        this.i.a(this.a, this.b, this.e);
        if (this.f != null) {
            this.f.onFailed(this.a, this.g, r6_LoaderException);
        }
    }

    public void onPrepare(Request r8_Request, int r9i) {
        int r4i = 1;
        int r5i = 0;
        if (SimpleImageLoader.f) {
            Log.e(SimpleImageLoader.a, " Prepare invoke, totalSize = " + r9i + ", request url == " + r8_Request.getUrl());
        }
        Object[] r2_ObjectA;
        if (this.i.e.contains(this.g)) {
            if (SimpleImageLoader.f) {
                String r0_String = SimpleImageLoader.a;
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = this.g;
                Log.e(r0_String, String.format("Contains url %1s , means loading it.", r2_ObjectA));
            }
        } else {
            StringBuffer r0_StringBuffer = new StringBuffer(" Not contain url ");
            byte[] r3_byteA = (byte[]) this.i.d.get(this.g);
            if (r3_byteA != null) {
                r2_ObjectA = new Object[r4i];
                r2_ObjectA[r5i] = this.g;
                r0_StringBuffer.append(String.format(" And get it from memory cache [%1s]", r2_ObjectA));
                r8_Request.markSuccess();
                this.i.a(this.a, null, r3_byteA, this.c, this.d, this.e);
                if (this.f != null) {
                    this.f.onSuccess(this.a, this.g);
                }
                return;
            } else if (SimpleImageLoader.f) {
                Log.e(SimpleImageLoader.a, r0_StringBuffer.toString());
            }
        }
        this.i.e.add(this.g);
        this.i.a(this.a, this.b, this.h, r9i);
    }

    public void onSuccess(Request r8_Request, int r9i, byte[] r10_byteA) {
        if (SimpleImageLoader.f) {
            Log.e(SimpleImageLoader.a, " Succuss invoke===========" + r10_byteA.length);
        }
        r8_Request.markSuccess();
        this.i.e.remove(r8_Request.getUrl());
        this.i.d.put(r8_Request.getUrl(), r10_byteA);
        this.i.a(this.a, this.b, r10_byteA, this.c, this.d, this.e);
        if (this.f != null) {
            this.f.onSuccess(this.a, this.g);
        }
    }
}
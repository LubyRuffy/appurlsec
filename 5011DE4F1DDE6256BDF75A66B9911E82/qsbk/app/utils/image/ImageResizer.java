package qsbk.app.utils.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build;
import qsbk.app.QsbkApp;

public class ImageResizer extends ImageWorker {
    protected int a;
    protected int b;

    public ImageResizer(Context r1_Context, int r2i) {
        super(r1_Context);
        setImageSize(r2i);
    }

    public ImageResizer(Context r1_Context, int r2i, int r3i) {
        super(r1_Context);
        setImageSize(r2i, r3i);
    }

    private Bitmap a(int r4i) {
        return decodeSampledBitmapFromResource(this.c.getResources(), r4i, this.a, this.b);
    }

    public static int calculateInSampleSize(Options r4_Options, int r5i, int r6i) {
        int r1i = r4_Options.outHeight;
        int r2i = r4_Options.outWidth;
        int r0i = 1;
        if (r1i <= r6i && r2i <= r5i) {
            return r0i;
        }
        r0i = r2i > r1i ? Math.round(((float) r2i) / ((float) r5i)) : Math.round(((float) r1i) / ((float) r6i));
        while (((float) (r1i * r2i)) / ((float) (r0i * r0i)) > ((float) ((r5i * r6i) * 6))) {
            r0i++;
        }
        return r0i;
    }

    public static synchronized Bitmap decodeSampledBitmapFromFile(String r4_String, int r5i, int r6i) {
        Bitmap r0_Bitmap;
        synchronized (ImageResizer.class) {
            Options r0_Options = new Options();
            r0_Options.inPreferredConfig = Config.RGB_565;
            r0_Options.inPurgeable = true;
            r0_Options.inInputShareable = true;
            r0_Options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r4_String, r0_Options);
            r0_Options.inSampleSize = 1;
            r0_Options.inJustDecodeBounds = false;
            if (!QsbkApp.isPad) {
                if (!Build.FINGERPRINT.toLowerCase().contains("huawei")) {
                    r0_Options.inDensity = 160;
                }
                r0_Options.inTargetDensity = QsbkApp.mContext.getResources().getDisplayMetrics().densityDpi;
                r0_Options.inScaled = true;
                r0_Options.inSampleSize = calculateInSampleSize(r0_Options, r5i, r6i);
            }
            r0_Bitmap = BitmapFactory.decodeFile(r4_String, r0_Options);
        }
        return r0_Bitmap;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources r3_Resources, int r4i, int r5i, int r6i) {
        Options r0_Options = new Options();
        r0_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(r3_Resources, r4i, r0_Options);
        r0_Options.inSampleSize = calculateInSampleSize(r0_Options, r5i, r6i);
        r0_Options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(r3_Resources.openRawResource(r4i), null, r0_Options);
    }

    protected Bitmap a(Object r2_Object) {
        return a(Integer.parseInt(String.valueOf(r2_Object)));
    }

    public void setImageSize(int r1i) {
        setImageSize(r1i, r1i);
    }

    public void setImageSize(int r1i, int r2i) {
        this.a = r1i;
        this.b = r2i;
    }
}
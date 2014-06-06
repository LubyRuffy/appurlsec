package qsbk.app.utils.audit;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public final class BitmapUtil {
    private static final String a;

    static {
        a = BitmapUtil.class.getName();
    }

    public static final int calDesiredHeight(int r3i, int r4i, int r5i, int r6i) {
        int r0i;
        r0i = r5i > 0 ? (int) (((float) r4i) * (((float) r5i) / ((float) r3i))) : r4i;
        return (r6i <= 0 || r0i <= r6i) ? r0i : r6i;
    }

    public static final int calDesiredHeight(byte[] r3_byteA, int r4i, int r5i) {
        Options r0_Options = new Options();
        r0_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(r3_byteA, 0, r3_byteA.length, r0_Options);
        return calDesiredHeight(r0_Options.outWidth, r0_Options.outHeight, r4i, r5i);
    }

    public static final Bitmap decodeBitmap(byte[] r7_byteA, int r8i, int r9i, boolean r10z) {
        Bitmap r0_Bitmap = null;
        Options r1_Options = new Options();
        r1_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(r7_byteA, 0, r7_byteA.length, r1_Options);
        int r2i = r1_Options.outWidth;
        int r3i = r1_Options.outHeight;
        int r4i = calDesiredHeight(r2i, r3i, r8i, r9i);
        r1_Options.inJustDecodeBounds = false;
        r1_Options.inPreferredConfig = Config.RGB_565;
        r1_Options.inInputShareable = true;
        r1_Options.inPurgeable = true;
        r1_Options.inSampleSize = findBestSampleSize(r2i, r3i, r8i, r4i);
        try {
            Bitmap r1_Bitmap = BitmapFactory.decodeByteArray(r7_byteA, 0, r7_byteA.length, r1_Options);
            if ((!r10z) || r1_Bitmap == null) {
                r0_Bitmap = r1_Bitmap;
            } else if (r1_Bitmap.getWidth() < r8i || r1_Bitmap.getHeight() < r4i) {
                r0_Bitmap = Bitmap.createScaledBitmap(r1_Bitmap, r8i, r4i, true);
            } else {
                r0_Bitmap = r1_Bitmap;
            }
            if (r0_Bitmap == r1_Bitmap || r1_Bitmap == null) {
                return r0_Bitmap;
            }
            r1_Bitmap.recycle();
            return r0_Bitmap;
        } catch (OutOfMemoryError e) {
        }
    }

    public static final int findBestSampleSize(int r7i, int r8i, int r9i, int r10i) {
        float r0f = 1.0f;
        while (((double) (r0f * 2.0f)) <= Math.min(((double) r7i) / ((double) r9i), ((double) r8i) / ((double) r10i))) {
            r0f *= 2.0f;
        }
        return (int) r0f;
    }
}
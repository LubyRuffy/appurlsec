package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintManager;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PrintHelperKitkat {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    final Context a;
    int b;
    int c;

    PrintHelperKitkat(Context r2_Context) {
        this.b = 2;
        this.c = 2;
        this.a = r2_Context;
    }

    private Bitmap a(Uri r7_Uri, int r8i) throws FileNotFoundException {
        if (r8i <= 0 || r7_Uri == null || this.a == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        } else {
            Options r1_Options = new Options();
            r1_Options.inJustDecodeBounds = true;
            a(r7_Uri, r1_Options);
            int r4i = r1_Options.outWidth;
            int r5i = r1_Options.outHeight;
            if (r4i <= 0 || r5i <= 0) {
                return null;
            }
            int r3i = Math.max(r4i, r5i);
            int r1i = 1;
            while (r3i > r8i) {
                r3i >>>= 1;
                r1i <<= 1;
            }
            if (r1i <= 0 || Math.min(r4i, r5i) / r1i <= 0) {
                return null;
            }
            Options r0_Options = new Options();
            r0_Options.inMutable = true;
            r0_Options.inSampleSize = r1i;
            return a(r7_Uri, r0_Options);
        }
    }

    private Bitmap a(Uri r5_Uri, Options r6_Options) throws FileNotFoundException {
        if (r5_Uri == null || this.a == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        } else {
            try {
                InputStream r1_InputStream = this.a.getContentResolver().openInputStream(r5_Uri);
                Bitmap r0_Bitmap = BitmapFactory.decodeStream(r1_InputStream, null, r6_Options);
                if (r1_InputStream != null) {
                    try {
                        r1_InputStream.close();
                    } catch (IOException e) {
                        Log.w("PrintHelperKitkat", "close fail ", e);
                    }
                }
                return r0_Bitmap;
            } catch (Throwable th) {
                if (0 != 0) {
                    null.close();
                }
            }
        }
    }

    public int getColorMode() {
        return this.c;
    }

    public int getScaleMode() {
        return this.b;
    }

    public void printBitmap(String r6_String, Bitmap r7_Bitmap) {
        if (r7_Bitmap == null) {
        } else {
            int r2i = this.b;
            PrintManager r0_PrintManager = (PrintManager) this.a.getSystemService("print");
            MediaSize r1_MediaSize = MediaSize.UNKNOWN_PORTRAIT;
            if (r7_Bitmap.getWidth() > r7_Bitmap.getHeight()) {
                r1_MediaSize = MediaSize.UNKNOWN_LANDSCAPE;
            }
            r0_PrintManager.print(r6_String, new a(this, r6_String, r7_Bitmap, r2i), new Builder().setMediaSize(r1_MediaSize).setColorMode(this.c).build());
        }
    }

    public void printBitmap(String r2_String, Uri r3_Uri) throws FileNotFoundException {
        printBitmap(r2_String, a(r3_Uri, 3500));
    }

    public void setColorMode(int r1i) {
        this.c = r1i;
    }

    public void setScaleMode(int r1i) {
        this.b = r1i;
    }
}
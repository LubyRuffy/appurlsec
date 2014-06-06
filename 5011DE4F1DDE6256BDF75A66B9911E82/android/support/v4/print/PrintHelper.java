package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import java.io.FileNotFoundException;

public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    c a;


    static interface c {
        public int getColorMode();

        public int getScaleMode();

        public void printBitmap(String r1_String, Bitmap r2_Bitmap);

        public void printBitmap(String r1_String, Uri r2_Uri) throws FileNotFoundException;

        public void setColorMode(int r1i);

        public void setScaleMode(int r1i);
    }

    private static final class a implements c {
        private final PrintHelperKitkat a;

        a(Context r2_Context) {
            this.a = new PrintHelperKitkat(r2_Context);
        }

        public int getColorMode() {
            return this.a.getColorMode();
        }

        public int getScaleMode() {
            return this.a.getScaleMode();
        }

        public void printBitmap(String r2_String, Bitmap r3_Bitmap) {
            this.a.printBitmap(r2_String, r3_Bitmap);
        }

        public void printBitmap(String r2_String, Uri r3_Uri) throws FileNotFoundException {
            this.a.printBitmap(r2_String, r3_Uri);
        }

        public void setColorMode(int r2i) {
            this.a.setColorMode(r2i);
        }

        public void setScaleMode(int r2i) {
            this.a.setScaleMode(r2i);
        }
    }

    private static final class b implements c {
        int a;
        int b;

        private b() {
            this.a = 2;
            this.b = 2;
        }

        public int getColorMode() {
            return this.b;
        }

        public int getScaleMode() {
            return this.a;
        }

        public void printBitmap(String r1_String, Bitmap r2_Bitmap) {
        }

        public void printBitmap(String r1_String, Uri r2_Uri) {
        }

        public void setColorMode(int r1i) {
            this.b = r1i;
        }

        public void setScaleMode(int r1i) {
            this.a = r1i;
        }
    }

    public PrintHelper(Context r3_Context) {
        if (systemSupportsPrint()) {
            this.a = new a(r3_Context);
        } else {
            this.a = new b();
        }
    }

    public static boolean systemSupportsPrint() {
        return VERSION.SDK_INT >= 19;
    }

    public int getColorMode() {
        return this.a.getColorMode();
    }

    public int getScaleMode() {
        return this.a.getScaleMode();
    }

    public void printBitmap(String r2_String, Bitmap r3_Bitmap) {
        this.a.printBitmap(r2_String, r3_Bitmap);
    }

    public void printBitmap(String r2_String, Uri r3_Uri) throws FileNotFoundException {
        this.a.printBitmap(r2_String, r3_Uri);
    }

    public void setColorMode(int r2i) {
        this.a.setColorMode(r2i);
    }

    public void setScaleMode(int r2i) {
        this.a.setScaleMode(r2i);
    }
}
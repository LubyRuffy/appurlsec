package com.tencent.mm.sdk.platformtools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ListView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

public class BackwardSupportUtil {

    public static class AnimationHelper {

        public static interface IHelper {
            public void cancelAnimation(View r1_View, Animation r2_Animation);
        }

        public static void cancelAnimation(View r2_View, Animation r3_Animation) {
            if (VERSION.SDK_INT >= 8) {
                new b().cancelAnimation(r2_View, r3_Animation);
            } else {
                new a().cancelAnimation(r2_View, r3_Animation);
            }
        }

        public static void overridePendingTransition(Activity r0_Activity, int r1i, int r2i) {
            r0_Activity.overridePendingTransition(r1i, r2i);
        }
    }

    public static class BitmapFactory {
        public static Bitmap decodeFile(String r3_String, float r4f) {
            Options r0_Options = new Options();
            float r1f = 160.0f * r4f;
            r0_Options.inDensity = (int) r1f;
            Bitmap r0_Bitmap = android.graphics.BitmapFactory.decodeFile(r3_String, r0_Options);
            if (r0_Bitmap != null) {
                r0_Bitmap.setDensity((int) r1f);
            }
            return r0_Bitmap;
        }

        public static Bitmap decodeStream(InputStream r2_InputStream) {
            Options r0_Options = new Options();
            r0_Options.inDensity = 160;
            r0_Options.inPreferredConfig = Config.ARGB_8888;
            return android.graphics.BitmapFactory.decodeStream(r2_InputStream, null, r0_Options);
        }

        public static Bitmap decodeStream(InputStream r2_InputStream, float r3f) {
            Options r0_Options = new Options();
            r0_Options.inDensity = (int) (160.0f * r3f);
            r0_Options.inPreferredConfig = Config.ARGB_8888;
            return android.graphics.BitmapFactory.decodeStream(r2_InputStream, null, r0_Options);
        }

        public static int fromDPToPix(Context r2_Context, float r3f) {
            DisplayMetrics r1_DisplayMetrics = new DisplayMetrics();
            ((WindowManager) r2_Context.getSystemService("window")).getDefaultDisplay().getMetrics(r1_DisplayMetrics);
            return Math.round((((float) r1_DisplayMetrics.densityDpi) * r3f) / 160.0f);
        }

        public static Bitmap getBitmapFromURL(String r3_String) {
            try {
                Log.d("MicroMsg.SDK.BackwardSupportUtil", new StringBuilder("get bitmap from url:").append(r3_String).toString());
                HttpURLConnection r0_HttpURLConnection = (HttpURLConnection) new URL(r3_String).openConnection();
                r0_HttpURLConnection.setDoInput(true);
                r0_HttpURLConnection.connect();
                InputStream r1_InputStream = r0_HttpURLConnection.getInputStream();
                Bitmap r0_Bitmap = decodeStream(r1_InputStream);
                r1_InputStream.close();
                return r0_Bitmap;
            } catch (IOException e) {
                IOException r0_IOException = e;
                Log.e("MicroMsg.SDK.BackwardSupportUtil", "get bitmap from url failed");
                r0_IOException.printStackTrace();
                return null;
            }
        }

        public static String getDisplayDensityType(Context r5_Context) {
            String r0_String;
            DisplayMetrics r0_DisplayMetrics = r5_Context.getResources().getDisplayMetrics();
            Configuration r1_Configuration = r5_Context.getResources().getConfiguration();
            String r2_String = RContactStorage.PRIMARY_KEY;
            if (r0_DisplayMetrics.density < 1.0f) {
                r0_String = r2_String + "LDPI";
            } else if (r0_DisplayMetrics.density >= 1.5f) {
                r0_String = r2_String + "HDPI";
            } else {
                r0_String = r2_String + "MDPI";
            }
            return r0_String + (r1_Configuration.orientation == 2 ? "_L" : "_P");
        }
    }

    public static class ExifHelper {
        public static int getExifOrientation(String r7_String) {
            ExifInterface r1_ExifInterface;
            try {
                r1_ExifInterface = new ExifInterface(r7_String);
            } catch (IOException e) {
                Log.e("MicroMsg.SDK.BackwardSupportUtil", new StringBuilder("cannot read exif").append(e).toString());
                r1_ExifInterface = null;
            }
            if (r1_ExifInterface == null) {
                return 0;
            }
            int r1i = r1_ExifInterface.getAttributeInt("Orientation", -1);
            if (r1i == -1) {
                return 0;
            }
            switch (r1i) {
                case XListViewFooter.STATE_NOMORE:
                    return 180;
                case ShareUtils.SHARE_COPY:
                    return 90;
                case Base64.DONT_BREAK_LINES:
                    return 270;
            }
            return 0;
        }
    }

    public static class SmoothScrollFactory {

        public static interface IScroll {
            public void doScroll(ListView r1_ListView);

            public void doScroll(ListView r1_ListView, int r2i);
        }

        public static void scrollTo(ListView r2_ListView, int r3i) {
            if (VERSION.SDK_INT >= 8) {
                new l().doScroll(r2_ListView, r3i);
            } else {
                new k().doScroll(r2_ListView, r3i);
            }
        }

        public static void scrollToTop(ListView r2_ListView) {
            if (VERSION.SDK_INT >= 8) {
                new l().doScroll(r2_ListView);
            } else {
                new k().doScroll(r2_ListView);
            }
        }
    }
}
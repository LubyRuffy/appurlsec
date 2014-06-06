package qsbk.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import qsbk.app.push.Utils;

public class ImageUtils {
    public static byte[] bmpToByteArray(Bitmap r7_Bitmap, boolean r8z) {
        int r1i;
        int r0i;
        Bitmap r2_Bitmap = Bitmap.createBitmap(80, 80, Config.RGB_565);
        Canvas r3_Canvas = new Canvas(r2_Bitmap);
        if (r7_Bitmap.getHeight() > r7_Bitmap.getWidth()) {
            r1i = r7_Bitmap.getWidth();
            r0i = r7_Bitmap.getWidth();
        } else {
            r1i = r7_Bitmap.getHeight();
            r0i = r7_Bitmap.getHeight();
        }
        while (true) {
            r3_Canvas.drawBitmap(r7_Bitmap, new Rect(0, 0, r1i, r0i), new Rect(0, 0, 80, 80), null);
            if (r8z) {
                r7_Bitmap.recycle();
            }
            OutputStream r0_OutputStream = new ByteArrayOutputStream();
            r2_Bitmap.compress(CompressFormat.JPEG, 100, r0_OutputStream);
            r2_Bitmap.recycle();
            byte[] r1_byteA = r0_OutputStream.toByteArray();
            try {
                r0_OutputStream.close();
                return r1_byteA;
            } catch (Exception e) {
                r1i = r7_Bitmap.getHeight();
                r0i = r7_Bitmap.getHeight();
            }
            r1i = r7_Bitmap.getHeight();
            r0i = r7_Bitmap.getHeight();
        }
    }

    public static int calculateInSampleSize(Options r3_Options, int r4i, int r5i) {
        int r1i = r3_Options.outHeight;
        int r2i = r3_Options.outWidth;
        return (r1i > r5i || r2i > r4i) ? Math.round(Math.max(((float) r1i) / ((float) r5i), ((float) r2i) / ((float) r4i))) : 1;
    }

    public static void compressBitmap(String r4_String) {
        boolean r0z = true;
        Options r2_Options = new Options();
        r2_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(r4_String, r2_Options);
        r2_Options.inJustDecodeBounds = false;
        boolean r1z = (int) (((float) r2_Options.outHeight) / 500.0f);
        Bitmap r0_Bitmap;
        OutputStream r2_OutputStream;
        if (r1z > false) {
            r2_Options.inSampleSize = r0z;
            r0_Bitmap = BitmapFactory.decodeFile(r4_String, r2_Options);
            try {
                r2_OutputStream = new FileOutputStream(new File(DeviceUtils.getSDPath() + "/qsbk/send/send.png"));
                if (!r0_Bitmap.compress(CompressFormat.JPEG, 100, r2_OutputStream)) {
                    r2_OutputStream.flush();
                    r2_OutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            r0z = r1z;
            r2_Options.inSampleSize = r0z;
            r0_Bitmap = BitmapFactory.decodeFile(r4_String, r2_Options);
            r2_OutputStream = new FileOutputStream(new File(DeviceUtils.getSDPath() + "/qsbk/send/send.png"));
            if (r0_Bitmap.compress(CompressFormat.JPEG, 100, r2_OutputStream)) {
            } else {
                r2_OutputStream.flush();
                r2_OutputStream.close();
            }
        }
    }

    public static Bitmap createBitmapForWatermark(Bitmap r7_Bitmap) {
        int r1i = 0;
        float r0f = 1.0f;
        float r2f = 500.0f;
        if (r7_Bitmap == null) {
            return null;
        }
        int r3i = r7_Bitmap.getWidth();
        int r4i = r7_Bitmap.getHeight();
        Matrix r5_Matrix;
        if (r3i <= r4i || r3i < 500) {
            if (r3i < r4i) {
                if (r3i >= 500) {
                    if (r4i >= 800) {
                        if (r3i / r4i >= 0) {
                            r0f = r2f / ((float) r3i);
                            r2f = r0f;
                            r5_Matrix = new Matrix();
                            r5_Matrix.postScale(r2f, r0f);
                            return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                        } else {
                            r0f = 800.0f / ((float) r4i);
                            r2f = r0f;
                            r5_Matrix = new Matrix();
                            r5_Matrix.postScale(r2f, r0f);
                            return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                        }
                    } else {
                        r0f = r2f / ((float) r3i);
                        r2f = r0f;
                        r5_Matrix = new Matrix();
                        r5_Matrix.postScale(r2f, r0f);
                        return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                    }
                } else if (r4i >= 800) {
                    r0f = 800.0f / ((float) r4i);
                    r2f = r0f;
                    r5_Matrix = new Matrix();
                    r5_Matrix.postScale(r2f, r0f);
                    return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                }
            } else if (r3i >= 500) {
                r0f = r2f / ((float) r3i);
                r2f = r0f;
                r5_Matrix = new Matrix();
                r5_Matrix.postScale(r2f, r0f);
                return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
            }
            r2f = 1.0f;
            r5_Matrix = new Matrix();
            r5_Matrix.postScale(r2f, r0f);
            return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
        } else {
            r0f = r2f / ((float) r3i);
            r2f = r0f;
            r5_Matrix = new Matrix();
            r5_Matrix.postScale(r2f, r0f);
            return new BitmapDrawable(Bitmap.createBitmap(r7_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
        }
    }

    public static Bitmap decodeBitmap(Context r5_Context, String r6_String, int r7i, int r8i, Config r9_Config) {
        Options r2_Options = new Options();
        r2_Options.inJustDecodeBounds = true;
        if (r9_Config != null) {
            r2_Options.inPreferredConfig = r9_Config;
        } else {
            r2_Options.inPreferredConfig = Config.RGB_565;
        }
        r2_Options.inPurgeable = true;
        r2_Options.inInputShareable = true;
        try {
            InputStream r0_InputStream;
            if (r6_String.indexOf(Utils.RESPONSE_CONTENT) == -1 && r6_String.indexOf("file") == -1) {
                r0_InputStream = new FileInputStream(r6_String);
                BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                r2_Options.inSampleSize = calculateInSampleSize(r2_Options, r7i, r8i);
                r2_Options.inJustDecodeBounds = false;
                if (r6_String.indexOf(Utils.RESPONSE_CONTENT) == -1 && r6_String.indexOf("file") == -1) {
                    r0_InputStream = new FileInputStream(r6_String);
                    return BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                } else {
                    r0_InputStream = r5_Context.getContentResolver().openInputStream(Uri.parse(r6_String));
                    return BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                }
            } else {
                r0_InputStream = r5_Context.getContentResolver().openInputStream(Uri.parse(r6_String));
                BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                r2_Options.inSampleSize = calculateInSampleSize(r2_Options, r7i, r8i);
                r2_Options.inJustDecodeBounds = false;
                try {
                    if (r6_String.indexOf(Utils.RESPONSE_CONTENT) == -1 || r6_String.indexOf("file") == -1) {
                        r0_InputStream = r5_Context.getContentResolver().openInputStream(Uri.parse(r6_String));
                        try {
                            return BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        r0_InputStream = new FileInputStream(r6_String);
                        return BitmapFactory.decodeStream(r0_InputStream, null, r2_Options);
                    }
                } catch (Exception e_2) {
                    return null;
                }
            }
        } catch (Exception e_3) {
            return null;
        }
    }

    public static Bitmap fitRotate(Bitmap r8_Bitmap, int r9i, int r10i, int r11i, boolean r12z) {
        if (r8_Bitmap == null || r8_Bitmap.isRecycled()) {
            throw new NullPointerException("the src bitmap is null or is recycled");
        } else {
            int r3i = r8_Bitmap.getWidth();
            int r4i = r8_Bitmap.getHeight();
            float r0f = Math.min(((float) r11i) / ((float) r3i), ((float) r10i) / ((float) r4i));
            Matrix r5_Matrix = new Matrix();
            r5_Matrix.postScale(r0f, r0f);
            r5_Matrix.postRotate((float) r9i);
            try {
                Bitmap r0_Bitmap = Bitmap.createBitmap(r8_Bitmap, 0, 0, r3i, r4i, r5_Matrix, true);
                if ((!r12z) || r0_Bitmap == null || r0_Bitmap == r8_Bitmap) {
                    return r0_Bitmap;
                }
                r8_Bitmap.recycle();
                return r0_Bitmap;
            } catch (OutOfMemoryError e) {
                if ((!r12z) || 0 == 0 || r8_Bitmap == null) {
                    return null;
                }
                r8_Bitmap.recycle();
                return null;
            } catch (Throwable th) {
                if ((!r12z) || 0 == 0 || r8_Bitmap == null) {
                } else {
                    r8_Bitmap.recycle();
                }
            }
        }
    }

    public static Bitmap picRotate(Bitmap r7_Bitmap, int r8i) {
        int r3i = r7_Bitmap.getWidth();
        int r4i = r7_Bitmap.getHeight();
        Matrix r5_Matrix = new Matrix();
        r5_Matrix.postScale(((float) r3i) / ((float) r3i), ((float) r4i) / ((float) r4i));
        r5_Matrix.postRotate((float) r8i);
        return Bitmap.createBitmap(r7_Bitmap, 0, 0, r3i, r4i, r5_Matrix, true);
    }

    public static Bitmap scaleBitmapIfNecessary(Bitmap r7_Bitmap, int r8i, int r9i, boolean r10z) {
        int r3i = r7_Bitmap.getWidth();
        int r4i = r7_Bitmap.getHeight();
        if (r3i <= r8i && r4i <= r9i) {
            return r7_Bitmap;
        }
        float r0f = Math.min(((float) r8i) / ((float) r3i), ((float) r9i) / ((float) r4i));
        Matrix r5_Matrix = new Matrix();
        r5_Matrix.postScale(r0f, r0f);
        try {
            Bitmap r0_Bitmap = Bitmap.createBitmap(r7_Bitmap, 0, 0, r3i, r4i, r5_Matrix, true);
            if ((!r10z) || r0_Bitmap == null || r7_Bitmap == r0_Bitmap || r7_Bitmap.isRecycled()) {
                return r0_Bitmap;
            }
            r7_Bitmap.recycle();
            return r0_Bitmap;
        } catch (OutOfMemoryError e) {
            if ((!r10z) || r7_Bitmap == null || r7_Bitmap == r7_Bitmap || r7_Bitmap.isRecycled()) {
                return r7_Bitmap;
            }
            r7_Bitmap.recycle();
            return r7_Bitmap;
        } catch (Throwable th) {
            if ((!r10z) || r7_Bitmap == null || r7_Bitmap == r7_Bitmap || r7_Bitmap.isRecycled()) {
            } else {
                r7_Bitmap.recycle();
            }
        }
    }

    public static Bitmap toRoundCorner(Bitmap r8_Bitmap, int r9i) {
        Bitmap r0_Bitmap = Bitmap.createBitmap(r8_Bitmap.getWidth(), r8_Bitmap.getHeight(), Config.RGB_565);
        Canvas r1_Canvas = new Canvas(r0_Bitmap);
        Paint r2_Paint = new Paint();
        Rect r3_Rect = new Rect(0, 0, r8_Bitmap.getWidth(), r8_Bitmap.getHeight());
        RectF r4_RectF = new RectF(r3_Rect);
        float r5f = (float) r9i;
        r2_Paint.setAntiAlias(true);
        r1_Canvas.drawARGB(0, 0, 0, 0);
        r2_Paint.setColor(-12434878);
        r1_Canvas.drawRoundRect(r4_RectF, r5f, r5f, r2_Paint);
        r2_Paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        r1_Canvas.drawBitmap(r8_Bitmap, r3_Rect, r3_Rect, r2_Paint);
        return r0_Bitmap;
    }

    public static Bitmap watermark(Bitmap r11_Bitmap, Bitmap r12_Bitmap, int r13i, int r14i) {
        int r1i = 0;
        if (r11_Bitmap == null) {
            return null;
        }
        float r7f;
        float r8f;
        Bitmap r1_Bitmap;
        int r3i = r11_Bitmap.getWidth();
        int r4i = r11_Bitmap.getHeight();
        float r0f;
        Matrix r5_Matrix;
        if (r3i <= r4i || r3i < r13i) {
            if (r3i < r4i) {
                if (r3i >= r13i) {
                    if (r4i >= r14i) {
                        if (r3i / r4i >= r13i / r14i) {
                            r0f = ((float) r13i) / ((float) r3i);
                            r7f = r0f;
                            r8f = r0f;
                            r5_Matrix = new Matrix();
                            r5_Matrix.postScale(r8f, r7f);
                            r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                        } else {
                            r0f = ((float) r14i) / ((float) r4i);
                            r7f = r0f;
                            r8f = r0f;
                            r5_Matrix = new Matrix();
                            r5_Matrix.postScale(r8f, r7f);
                            r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                        }
                    } else {
                        r0f = ((float) r13i) / ((float) r3i);
                        r7f = r0f;
                        r8f = r0f;
                        r5_Matrix = new Matrix();
                        r5_Matrix.postScale(r8f, r7f);
                        r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                    }
                } else if (r4i >= r14i) {
                    r0f = ((float) r14i) / ((float) r4i);
                    r7f = r0f;
                    r8f = r0f;
                    r5_Matrix = new Matrix();
                    r5_Matrix.postScale(r8f, r7f);
                    r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
                }
            } else if (r3i >= r13i) {
                r0f = ((float) r13i) / ((float) r3i);
                r7f = r0f;
                r8f = r0f;
                r5_Matrix = new Matrix();
                r5_Matrix.postScale(r8f, r7f);
                r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
            }
            r7f = 1.0f;
            r8f = 1.0f;
            r5_Matrix = new Matrix();
            r5_Matrix.postScale(r8f, r7f);
            r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
        } else {
            r0f = ((float) r13i) / ((float) r3i);
            r7f = r0f;
            r8f = r0f;
            r5_Matrix = new Matrix();
            r5_Matrix.postScale(r8f, r7f);
            r1_Bitmap = new BitmapDrawable(Bitmap.createBitmap(r11_Bitmap, r1i, r1i, r3i, r4i, r5_Matrix, true)).getBitmap();
        }
        if (r1_Bitmap.getWidth() <= 250) {
            return r1_Bitmap;
        }
        int r2i = r12_Bitmap.getHeight();
        Bitmap r0_Bitmap = Bitmap.createBitmap((int) (((float) r3i) * r8f), (int) (((float) r4i) * r7f), Config.RGB_565);
        Canvas r3_Canvas = new Canvas(r0_Bitmap);
        r3_Canvas.drawBitmap(r1_Bitmap, 0.0f, 0.0f, null);
        r3_Canvas.drawBitmap(r12_Bitmap, 10.0f, (float) (r0_Bitmap.getHeight() - r2i - 10), null);
        r3_Canvas.save(AdViewUtil.NETWORK_TYPE_VPON);
        r3_Canvas.restore();
        return r0_Bitmap;
    }
}
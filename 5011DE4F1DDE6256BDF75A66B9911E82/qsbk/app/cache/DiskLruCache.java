package qsbk.app.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;
import qsbk.app.bean.Base;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.utils.image.Utils;

public class DiskLruCache {
    private static final FilenameFilter k;
    protected int a;
    protected int b;
    private final File c;
    private int d;
    private int e;
    private final int f;
    private long g;
    private CompressFormat h;
    private int i;
    private final Map<String, String> j;

    static {
        k = new a();
    }

    private DiskLruCache(File r5_File, long r6j) {
        this.d = 0;
        this.e = 0;
        this.f = 64;
        this.g = 5242880;
        this.h = CompressFormat.JPEG;
        this.i = 100;
        this.j = Collections.synchronizedMap(new LinkedHashMap(32, 0.75f, true));
        this.c = r5_File;
        this.g = r6j;
    }

    private void a() {
        int r2i = 0;
        while (r2i < 4) {
            if (this.d > 64 || ((long) this.e) > this.g) {
                Entry r0_Entry = (Entry) this.j.entrySet().iterator().next();
                File r3_File = new File((String) r0_Entry.getValue());
                long r4j = r3_File.length();
                this.j.remove(r0_Entry.getKey());
                r3_File.delete();
                this.d = this.j.size();
                this.e = (int) (((long) this.e) - r4j);
                r2i++;
            } else {
                return;
            }
        }
    }

    private static void a(File r3_File) {
        File[] r1_FileA = r3_File.listFiles(k);
        int r0i = 0;
        while (r0i < r1_FileA.length) {
            r1_FileA[r0i].delete();
            r0i++;
        }
    }

    private void a(String r5_String, String r6_String) {
        this.j.put(r5_String, r6_String);
        this.d = this.j.size();
        this.e = (int) (((long) this.e) + new File(r6_String).length());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(Bitmap r4_Bitmap, String r5_String) throws IOException, FileNotFoundException {
        /*
        r3_this = this;
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 //Catch:{ all -> 0x0021 }
        r1.<init>(r5);	 //Catch:{ all -> 0x0021 }
        r0 = r3.h;	 //Catch:{ all -> 0x0029 }
        r2 = r3.i;	 //Catch:{ all -> 0x0029 }
        r0 = r4.compress(r0, r2, r1);	 //Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x001a;
    L_0x0010:
        r1.flush();	 //Catch:{ all -> 0x0029 }
        r0 = 1;
        if (r1 == 0) goto L_0x0019;
    L_0x0016:
        r1.close();
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = 0;
        if (r1 == 0) goto L_0x0019;
    L_0x001d:
        r1.close();
        goto L_0x0019;
    L_0x0021:
        r0 = move-exception;
        r1 = r2;
    L_0x0023:
        if (r1 == 0) goto L_0x0028;
    L_0x0025:
        r1.close();
    L_0x0028:
        throw r0;
    L_0x0029:
        r0 = move-exception;
        goto L_0x0023;
        */

    }

    public static void clearCache(Context r1_Context, String r2_String) {
        a(getDiskCacheDir(r1_Context, r2_String));
    }

    public static String createFilePath(File r4_File, String r5_String) {
        try {
            return r4_File.getAbsolutePath() + File.separator + "cache_" + URLEncoder.encode(r5_String.replace("*", RContactStorage.PRIMARY_KEY), Base.UTF8);
        } catch (UnsupportedEncodingException e) {
            Log.e("DiskLruCache", "createFilePath - " + e);
            return null;
        }
    }

    public static File getDiskCacheDir(Context r3_Context, String r4_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            if ((!Environment.getExternalStorageState().equals("mounted")) || Utils.isExternalStorageRemovable()) {
                r0_String = r3_Context.getCacheDir().getPath();
                return new File(r0_String + File.separator + r4_String);
            } else {
                r0_String = Utils.getExternalCacheDir(r3_Context).getPath();
                return new File(r0_String + File.separator + r4_String);
            }
        } catch (Exception e) {
            r0_String = r3_Context.getCacheDir().getPath();
            e.printStackTrace();
        }
    }

    public static DiskLruCache openCache(Context r3_Context, File r4_File, long r5j, int r7i) {
        if (!r4_File.exists()) {
            r4_File.mkdirs();
        }
        if (r4_File.isDirectory() && r4_File.canWrite() && Utils.getUsableSpace(r4_File) > r5j) {
            DiskLruCache r0_DiskLruCache = new DiskLruCache(r4_File, r5j);
            r0_DiskLruCache.setImageSize(r7i);
            return r0_DiskLruCache;
        } else {
            LogUtil.d("cacheDir.isDirectory():" + r4_File.isDirectory());
            LogUtil.d("cacheDir.canWrite():" + r4_File.canWrite());
            LogUtil.d("Utils.getUsableSpace(cacheDir):" + Utils.getUsableSpace(r4_File));
            LogUtil.d("Utils.getUsableSpace(cacheDir):" + r5j);
            return null;
        }
    }

    public void clearCache() {
        a(this.c);
    }

    public boolean containsKey(String r4_String) {
        if (this.j.containsKey(r4_String)) {
            return true;
        }
        String r1_String = createFilePath(this.c, r4_String);
        if (!new File(r1_String).exists()) {
            return false;
        }
        a(r4_String, r1_String);
        return true;
    }

    public String createFilePath(String r2_String) {
        return createFilePath(this.c, r2_String);
    }

    public Bitmap get(String r7_String) {
        Bitmap r0_Bitmap;
        synchronized (this.j) {
            String r0_String = (String) this.j.get(r7_String);
            if (r0_String == null) {
                r0_String = createFilePath(this.c, r7_String);
                if (new File(r0_String).exists()) {
                    a(r7_String, r0_String);
                }
            }
            if (r0_String == null || (!new File(r0_String).exists())) {
                r0_Bitmap = null;
            } else {
                Options r3_Options = new Options();
                r3_Options.inJustDecodeBounds = true;
                r3_Options.inPreferredConfig = Config.RGB_565;
                BitmapFactory.decodeFile(r0_String, r3_Options);
                r3_Options.inSampleSize = 1;
                r3_Options.inJustDecodeBounds = false;
                if (!QsbkApp.isPad) {
                    if (!Build.FINGERPRINT.toLowerCase().contains("huawei")) {
                        r3_Options.inDensity = 160;
                    }
                    r3_Options.inTargetDensity = QsbkApp.mContext.getResources().getDisplayMetrics().densityDpi;
                    r3_Options.inScaled = true;
                    r3_Options.inSampleSize = ImageFetcher.calculateInSampleSize(r3_Options, this.a, this.b);
                }
                try {
                    r0_Bitmap = BitmapFactory.decodeFile(r0_String, r3_Options);
                } catch (OutOfMemoryError e) {
                    r0_Bitmap = null;
                    System.gc();
                }
            }
        }
        return r0_Bitmap;
    }

    public File getCacheDirectory() {
        return this.c;
    }

    public void put(String r6_String, Bitmap r7_Bitmap) {
        synchronized (this.j) {
            if (this.j.get(r6_String) == null) {
                try {
                    String r0_String = createFilePath(this.c, r6_String);
                    if (a(r7_Bitmap, r0_String)) {
                        a(r6_String, r0_String);
                        a();
                    }
                } catch (FileNotFoundException e) {
                    Log.e("DiskLruCache", "Error in put: " + e.getMessage());
                } catch (IOException e_2) {
                    Log.e("DiskLruCache", "Error in put: " + e_2.getMessage());
                }
            }
        }
    }

    public void setCompressParams(CompressFormat r1_CompressFormat, int r2i) {
        this.h = r1_CompressFormat;
        this.i = r2i;
    }

    public void setImageSize(int r1i) {
        this.a = r1i;
        this.b = r1i;
    }
}
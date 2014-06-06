package qsbk.app.cache;

import android.content.Context;
import android.os.Environment;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import qsbk.app.utils.image.Utils;

public class FileCache {
    private static int a;

    static {
        a = 0;
    }

    private static boolean b(Context r6_Context, String r7_String) {
        File r1_File = getDiskCacheDir(r6_Context, r7_String);
        if (!r1_File.exists()) {
            return true;
        }
        if (Math.abs(System.currentTimeMillis() - r1_File.lastModified()) <= 180000) {
            return false;
        }
        r1_File.delete();
        return true;
    }

    public static void cacheTextToDisk(Context r2_Context, String r3_String, String r4_String) {
        new b("qbk-FileCache", r2_Context, r3_String, r4_String).start();
    }

    public static void cacheTextToDiskImmediately(Context r2_Context, String r3_String, String r4_String) {
        new c("qbk-FileCache", r2_Context, r3_String, r4_String).start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String getContentFromDisk(Context r7_Context, String r8_String) {
        /*
        r0 = "";
        r1 = getDiskCacheDir(r7, r8);
        r2 = r1.exists();
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = new byte[r2];
        r5 = new java.io.ByteArrayOutputStream;
        r5.<init>();
        r3 = 0;
        r2 = new java.io.BufferedInputStream;	 //Catch:{ Exception -> 0x0077, all -> 0x0068 }
        r6 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0077, all -> 0x0068 }
        r6.<init>(r1);	 //Catch:{ Exception -> 0x0077, all -> 0x0068 }
        r2.<init>(r6);	 //Catch:{ Exception -> 0x0077, all -> 0x0068 }
    L_0x0021:
        r1 = r2.read(r4);	 //Catch:{ Exception -> 0x002d }
        r3 = -1;
        if (r1 == r3) goto L_0x0054;
    L_0x0028:
        r3 = 0;
        r5.write(r4, r3, r1);	 //Catch:{ Exception -> 0x002d }
        goto L_0x0021;
    L_0x002d:
        r1 = move-exception;
    L_0x002e:
        r3 = a;	 //Catch:{ all -> 0x0075 }
        r4 = r3 + 1;
        a = r4;	 //Catch:{ all -> 0x0075 }
        r4 = 20;
        if (r3 >= r4) goto L_0x0046;
    L_0x0038:
        r3 = qsbk.app.exception.CrashHandler.getInstance();	 //Catch:{ all -> 0x0075 }
        r4 = java.lang.Thread.currentThread();	 //Catch:{ all -> 0x0075 }
        r5 = 2;
        r6 = "read file cache";
        r3.reportGuessException(r4, r1, r5, r6);	 //Catch:{ all -> 0x0075 }
    L_0x0046:
        r1.printStackTrace();	 //Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x000c;
    L_0x004b:
        r2.close();	 //Catch:{ IOException -> 0x004f }
        goto L_0x000c;
    L_0x004f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000c;
    L_0x0054:
        r2.close();	 //Catch:{ Exception -> 0x002d }
        r1 = "utf8";
        r0 = r5.toString(r1);	 //Catch:{ Exception -> 0x002d }
        if (r2 == 0) goto L_0x000c;
    L_0x005f:
        r2.close();	 //Catch:{ IOException -> 0x0063 }
        goto L_0x000c;
    L_0x0063:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000c;
    L_0x0068:
        r0 = move-exception;
        r2 = r3;
    L_0x006a:
        if (r2 == 0) goto L_0x006f;
    L_0x006c:
        r2.close();	 //Catch:{ IOException -> 0x0070 }
    L_0x006f:
        throw r0;
    L_0x0070:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006f;
    L_0x0075:
        r0 = move-exception;
        goto L_0x006a;
    L_0x0077:
        r1 = move-exception;
        r2 = r3;
        goto L_0x002e;
        */

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
        }
    }

    public static String getDiskCacheDirPath(Context r2_Context) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            r0_String = ((!Environment.getExternalStorageState().equals("mounted")) || Utils.isExternalStorageRemovable()) ? r2_Context.getCacheDir().getPath() : Utils.getExternalCacheDir(r2_Context).getPath();
        } catch (Exception e) {
            r0_String = r2_Context.getCacheDir().getPath();
        }
        return r0_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFile(Context r7_Context, String r8_String, String r9_String) {
        /*
        r3 = getDiskCacheDir(r7, r8);
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0029, all -> 0x006a }
        r0 = 1;
        r1.<init>(r3, r0);	 //Catch:{ Exception -> 0x0029, all -> 0x006a }
        r0 = new java.io.OutputStreamWriter;	 //Catch:{ Exception -> 0x0079 }
        r2 = "utf8";
        r0.<init>(r1, r2);	 //Catch:{ Exception -> 0x0079 }
        r0.write(r9);	 //Catch:{ Exception -> 0x0079 }
        r0.flush();	 //Catch:{ Exception -> 0x0079 }
        r0.close();	 //Catch:{ Exception -> 0x0079 }
        r1.close();	 //Catch:{ Exception -> 0x0079 }
        if (r1 == 0) goto L_0x0023;
    L_0x0020:
        r1.close();	 //Catch:{ Exception -> 0x0024 }
    L_0x0023:
        return;
    L_0x0024:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0023;
    L_0x0029:
        r0 = move-exception;
        r1 = r2;
    L_0x002b:
        r2 = a;	 //Catch:{ all -> 0x0077 }
        r4 = r2 + 1;
        a = r4;	 //Catch:{ all -> 0x0077 }
        r4 = 20;
        if (r2 >= r4) goto L_0x0043;
    L_0x0035:
        r2 = qsbk.app.exception.CrashHandler.getInstance();	 //Catch:{ all -> 0x0077 }
        r4 = java.lang.Thread.currentThread();	 //Catch:{ all -> 0x0077 }
        r5 = 2;
        r6 = "write file cache";
        r2.reportGuessException(r4, r0, r5, r6);	 //Catch:{ all -> 0x0077 }
    L_0x0043:
        r0 = "FileCache";
        r2 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0077 }
        r2.<init>();	 //Catch:{ all -> 0x0077 }
        r4 = "\u6587\u4ef6\u7f13\u5b58\u51fa\u9519 path:";
        r2 = r2.append(r4);	 //Catch:{ all -> 0x0077 }
        r3 = r3.getPath();	 //Catch:{ all -> 0x0077 }
        r2 = r2.append(r3);	 //Catch:{ all -> 0x0077 }
        r2 = r2.toString();	 //Catch:{ all -> 0x0077 }
        android.util.Log.e(r0, r2);	 //Catch:{ all -> 0x0077 }
        if (r1 == 0) goto L_0x0023;
    L_0x0061:
        r1.close();	 //Catch:{ Exception -> 0x0065 }
        goto L_0x0023;
    L_0x0065:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0023;
    L_0x006a:
        r0 = move-exception;
        r1 = r2;
    L_0x006c:
        if (r1 == 0) goto L_0x0071;
    L_0x006e:
        r1.close();	 //Catch:{ Exception -> 0x0072 }
    L_0x0071:
        throw r0;
    L_0x0072:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0071;
    L_0x0077:
        r0 = move-exception;
        goto L_0x006c;
    L_0x0079:
        r0 = move-exception;
        goto L_0x002b;
        */

    }
}
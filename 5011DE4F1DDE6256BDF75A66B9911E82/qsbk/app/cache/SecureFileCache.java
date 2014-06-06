package qsbk.app.cache;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.utils.FileUtils;

public class SecureFileCache {
    private static int a;
    private static final String b;
    private static ExecutorService c;
    private static SecureFileCache d;
    private static final a g;
    private File e;
    private File f;

    public static interface Callback {
        public void onFinished(File r1_File, String r2_String);
    }

    private static final class a implements Comparator<File> {
        private a() {
        }

        public int compare(File r7_File, File r8_File) {
            long r0j = r7_File.lastModified() - r8_File.lastModified();
            if (r0j == 0) {
                return 0;
            }
            if (r0j > 0) {
                return 1;
            }
            return -1;
        }
    }

    static {
        a = 0;
        b = SecureFileCache.class.getSimpleName();
        c = Executors.newSingleThreadExecutor();
        g = new a();
    }

    private SecureFileCache(Context r4_Context) {
        this.e = new File(r4_Context.getCacheDir(), OneProfileActivity.USER);
        this.f = new File(r4_Context.getCacheDir(), "article");
    }

    private File a(String r3_String) {
        makeSureDirectoryExist(this.e);
        return new File(this.e, r3_String);
    }

    private File b(String r3_String) {
        makeSureDirectoryExist(this.f);
        return new File(this.f, r3_String);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String b(File r7_File) {
        /*
        r0 = "";
        r1 = r7.exists();
        if (r1 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>();
        r3 = 0;
        r2 = new java.io.BufferedInputStream;	 //Catch:{ Exception -> 0x0073, all -> 0x0064 }
        r5 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0073, all -> 0x0064 }
        r5.<init>(r7);	 //Catch:{ Exception -> 0x0073, all -> 0x0064 }
        r2.<init>(r5);	 //Catch:{ Exception -> 0x0073, all -> 0x0064 }
    L_0x001d:
        r3 = r2.read(r1);	 //Catch:{ Exception -> 0x0029 }
        r5 = -1;
        if (r3 == r5) goto L_0x004d;
    L_0x0024:
        r5 = 0;
        r4.write(r1, r5, r3);	 //Catch:{ Exception -> 0x0029 }
        goto L_0x001d;
    L_0x0029:
        r1 = move-exception;
    L_0x002a:
        r3 = a;	 //Catch:{ all -> 0x0071 }
        r4 = r3 + 1;
        a = r4;	 //Catch:{ all -> 0x0071 }
        r4 = 20;
        if (r3 >= r4) goto L_0x0042;
    L_0x0034:
        r3 = qsbk.app.exception.CrashHandler.getInstance();	 //Catch:{ all -> 0x0071 }
        r4 = java.lang.Thread.currentThread();	 //Catch:{ all -> 0x0071 }
        r5 = 2;
        r6 = "read file cache";
        r3.reportGuessException(r4, r1, r5, r6);	 //Catch:{ all -> 0x0071 }
    L_0x0042:
        if (r2 == 0) goto L_0x0008;
    L_0x0044:
        r2.close();	 //Catch:{ IOException -> 0x0048 }
        goto L_0x0008;
    L_0x0048:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0008;
    L_0x004d:
        r2.close();	 //Catch:{ Exception -> 0x0029 }
        r1 = "utf8";
        r0 = r4.toString(r1);	 //Catch:{ Exception -> 0x0029 }
        r4.close();	 //Catch:{ Exception -> 0x0029 }
        if (r2 == 0) goto L_0x0008;
    L_0x005b:
        r2.close();	 //Catch:{ IOException -> 0x005f }
        goto L_0x0008;
    L_0x005f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0008;
    L_0x0064:
        r0 = move-exception;
        r2 = r3;
    L_0x0066:
        if (r2 == 0) goto L_0x006b;
    L_0x0068:
        r2.close();	 //Catch:{ IOException -> 0x006c }
    L_0x006b:
        throw r0;
    L_0x006c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006b;
    L_0x0071:
        r0 = move-exception;
        goto L_0x0066;
    L_0x0073:
        r1 = move-exception;
        r2 = r3;
        goto L_0x002a;
        */

    }

    private String c(String r4_String) {
        int r0i = r4_String.length() / 2;
        StringBuffer r1_StringBuffer = new StringBuffer();
        r1_StringBuffer.append(String.valueOf(r4_String.substring(0, r0i).hashCode()));
        r1_StringBuffer.append(String.valueOf(r4_String.substring(r0i).hashCode()));
        return r1_StringBuffer.toString();
    }

    private static void c(File r9_File) {
        File[] r3_FileA = r9_File.listFiles();
        long r1j = FileUtils.getFileSize(r9_File, null);
        if (r1j > 1048576) {
            Arrays.sort(r3_FileA, g);
            int r0i = r3_FileA.length - 1;
            while (r1j > 1048576) {
                File r4_File = r3_FileA[r0i];
                r0i--;
                r1j -= r4_File.length();
                r4_File.delete();
            }
        }
    }

    private static void d(File r4_File) {
        if (r4_File == null || (!r4_File.isDirectory())) {
        } else {
            File[] r1_FileA = r4_File.listFiles();
            if (r1_FileA != null) {
                int r2i = r1_FileA.length;
                int r0i = 0;
                while (r0i < r2i) {
                    r1_FileA[r0i].delete();
                    r0i++;
                }
            }
        }
    }

    public static synchronized SecureFileCache getInstance(Context r2_Context) {
        SecureFileCache r0_SecureFileCache;
        synchronized (SecureFileCache.class) {
            if (d == null) {
                d = new SecureFileCache(r2_Context);
            }
            r0_SecureFileCache = d;
        }
        return r0_SecureFileCache;
    }

    public void cacheArticlesToDisk(String r3_String, String r4_String) {
        c.submit(new f(this, r4_String, r3_String));
    }

    public void cacheUserToDisk(String r3_String, String r4_String) {
        c.submit(new e(this, r4_String, r3_String));
    }

    public void clearArticle() {
        d(this.f);
    }

    public void clearOlderArticleIfNeed() {
        c(this.f);
    }

    public void clearOlderUserIfNeed() {
        c(this.e);
    }

    public void clearUser() {
        d(this.e);
    }

    public String getArticlesString(String r2_String) {
        return b(b(c(r2_String)));
    }

    public void getArticlesString(String r3_String, Callback r4_Callback) {
        if (r4_Callback == null) {
            Log.e(b, " Use getArticleString(String) instead .");
        } else {
            c.submit(new h(this, r3_String, r4_Callback));
        }
    }

    public String getUserString(String r2_String) {
        return b(a(c(r2_String)));
    }

    public void getUserString(String r3_String, Callback r4_Callback) {
        if (r4_Callback == null) {
            Log.e(b, " Use getUserString(String) instead .");
        } else {
            c.submit(new g(this, r3_String, r4_Callback));
        }
    }

    public boolean makeSureDirectoryExist(File r2_File) {
        return r2_File.exists() ? true : r2_File.mkdirs();
    }
}
package qsbk.app.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.io.File;

public class ImageFetcher extends ImageResizer {
    public static final int CONNECT_TIMEOUT = 12000;
    public static final String HTTP_CACHE_DIR = "http";
    public static final int READ_TIMEOUT = 40000;
    public static final int RETRY_COUNT = 1;

    public ImageFetcher(Context r1_Context, int r2i) {
        super(r1_Context, r2i);
        a(r1_Context);
    }

    public ImageFetcher(Context r1_Context, int r2i, int r3i) {
        super(r1_Context, r2i, r3i);
        a(r1_Context);
    }

    private Bitmap a(String r4_String) {
        File r0_File = downloadBitmap(this.c, r4_String, this.b);
        return r0_File != null ? decodeSampledBitmapFromFile(r0_File.toString(), this.a, this.b) : null;
    }

    private void a(Context r1_Context) {
        b(r1_Context);
    }

    private void b(Context r3_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null || (!r0_NetworkInfo.isConnectedOrConnecting())) {
            Log.e("ImageFetcher", "checkConnection - no connection found");
        }
    }

    protected Bitmap a(Object r2_Object) {
        return a(String.valueOf(r2_Object));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public File downloadBitmap(Context r13_Context, String r14_String, int r15i) {
        /*
        r12_this = this;
        r8 = -1;
        r2 = 0;
        r0 = 0;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "getImageCache():";
        r1 = r1.append(r3);
        r3 = r12.getImageCache();
        r1 = r1.append(r3);
        r1 = r1.toString();
        qsbk.app.utils.LogUtil.d(r1);
        r1 = new java.io.File;
        r3 = r12.getImageCache();
        r3 = r3.getDiskLruCache();
        r4 = qsbk.app.utils.Md5.MD5(r14);
        r3 = r3.createFilePath(r4);
        r1.<init>(r3);
        r3 = r12.getImageCache();
        r3 = r3.getDiskLruCache();
        r3 = r3.getCacheDirectory();
        r4 = r3.exists();
        if (r4 != 0) goto L_0x0047;
    L_0x0044:
        r3.mkdirs();
    L_0x0047:
        qsbk.app.utils.image.Utils.disableConnectionReuseIfNecessary();
        r7 = r0;
        r4 = r8;
        r6 = r2;
        r3 = r2;
    L_0x004e:
        r0 = 2;
        if (r7 >= r0) goto L_0x0119;
    L_0x0051:
        r0 = new java.net.URL;	 //Catch:{ IOException -> 0x013f, all -> 0x0136 }
        r0.<init>(r14);	 //Catch:{ IOException -> 0x013f, all -> 0x0136 }
        r0 = r0.openConnection();	 //Catch:{ IOException -> 0x013f, all -> 0x0136 }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ IOException -> 0x013f, all -> 0x0136 }
        r3 = 0;
        r0.setDoOutput(r3);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r3 = "GET";
        r0.setRequestMethod(r3);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r3 = 40000; // 0x9c40 float:5.6052E-41 double:1.97626E-319;
        r0.setReadTimeout(r3);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r3 = 12000; // 0x2ee0 float:1.6816E-41 double:5.929E-320;
        r0.setConnectTimeout(r3);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r4 = r0.getResponseCode();	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r3 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r5 = r0.getInputStream();	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r9 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3.<init>(r5, r9);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r5 = new java.io.BufferedOutputStream;	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r9 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r9.<init>(r1);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r10 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r5.<init>(r9, r10);	 //Catch:{ IOException -> 0x0145, all -> 0x013a }
        r6 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r6 = new byte[r6];	 //Catch:{ IOException -> 0x009a, all -> 0x0120 }
    L_0x008f:
        r9 = r3.read(r6);	 //Catch:{ IOException -> 0x009a, all -> 0x0120 }
        if (r9 == r8) goto L_0x00eb;
    L_0x0095:
        r10 = 0;
        r5.write(r6, r10, r9);	 //Catch:{ IOException -> 0x009a, all -> 0x0120 }
        goto L_0x008f;
    L_0x009a:
        r3 = move-exception;
        r11 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r0;
        r0 = r11;
    L_0x00a0:
        r6 = 1;
        if (r7 >= r6) goto L_0x0101;
    L_0x00a3:
        r6 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0134 }
        r6.<init>();	 //Catch:{ all -> 0x0134 }
        r6 = r6.append(r3);	 //Catch:{ all -> 0x0134 }
        r9 = "";
        r6 = r6.append(r9);	 //Catch:{ all -> 0x0134 }
        r6 = r6.toString();	 //Catch:{ all -> 0x0134 }
        r9 = "40";
        r6 = r6.startsWith(r9);	 //Catch:{ all -> 0x0134 }
        if (r6 != 0) goto L_0x0101;
    L_0x00be:
        r6 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0134 }
        r6.<init>();	 //Catch:{ all -> 0x0134 }
        r6 = r6.append(r3);	 //Catch:{ all -> 0x0134 }
        r9 = "";
        r6 = r6.append(r9);	 //Catch:{ all -> 0x0134 }
        r6 = r6.toString();	 //Catch:{ all -> 0x0134 }
        r9 = "50";
        r6 = r6.startsWith(r9);	 //Catch:{ all -> 0x0134 }
        if (r6 != 0) goto L_0x0101;
    L_0x00d9:
        if (r5 == 0) goto L_0x00de;
    L_0x00db:
        r5.disconnect();
    L_0x00de:
        if (r4 == 0) goto L_0x00e3;
    L_0x00e0:
        r4.close();	 //Catch:{ IOException -> 0x00fc }
    L_0x00e3:
        r0 = r7 + 1;
        r7 = r0;
        r6 = r4;
        r4 = r3;
        r3 = r5;
        goto L_0x004e;
    L_0x00eb:
        if (r0 == 0) goto L_0x00f0;
    L_0x00ed:
        r0.disconnect();
    L_0x00f0:
        if (r5 == 0) goto L_0x00f5;
    L_0x00f2:
        r5.close();	 //Catch:{ IOException -> 0x00f7 }
    L_0x00f5:
        r0 = r1;
    L_0x00f6:
        return r0;
    L_0x00f7:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00f5;
    L_0x00fc:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00e3;
    L_0x0101:
        r1 = qsbk.app.utils.image.issue.DisplayIssueManager.getInstance();	 //Catch:{ all -> 0x0134 }
        r6 = qsbk.app.QsbkApp.mContext;	 //Catch:{ all -> 0x0134 }
        r7 = new qsbk.app.utils.image.issue.IOExceptionWrapper;	 //Catch:{ all -> 0x0134 }
        r7.<init>(r3, r0);	 //Catch:{ all -> 0x0134 }
        r1.reportNewIssue(r6, r7, r14);	 //Catch:{ all -> 0x0134 }
        if (r5 == 0) goto L_0x0114;
    L_0x0111:
        r5.disconnect();
    L_0x0114:
        if (r4 == 0) goto L_0x0119;
    L_0x0116:
        r4.close();	 //Catch:{ IOException -> 0x011b }
    L_0x0119:
        r0 = r2;
        goto L_0x00f6;
    L_0x011b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0119;
    L_0x0120:
        r1 = move-exception;
        r4 = r5;
        r5 = r0;
        r0 = r1;
    L_0x0124:
        if (r5 == 0) goto L_0x0129;
    L_0x0126:
        r5.disconnect();
    L_0x0129:
        if (r4 == 0) goto L_0x012e;
    L_0x012b:
        r4.close();	 //Catch:{ IOException -> 0x012f }
    L_0x012e:
        throw r0;
    L_0x012f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x012e;
    L_0x0134:
        r0 = move-exception;
        goto L_0x0124;
    L_0x0136:
        r0 = move-exception;
        r4 = r6;
        r5 = r3;
        goto L_0x0124;
    L_0x013a:
        r1 = move-exception;
        r4 = r6;
        r5 = r0;
        r0 = r1;
        goto L_0x0124;
    L_0x013f:
        r0 = move-exception;
        r5 = r3;
        r3 = r4;
        r4 = r6;
        goto L_0x00a0;
    L_0x0145:
        r3 = move-exception;
        r5 = r0;
        r0 = r3;
        r3 = r4;
        r4 = r6;
        goto L_0x00a0;
        */

    }
}
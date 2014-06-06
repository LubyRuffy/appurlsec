package com.tencent.cloudsdk.common.utils;

import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.tencent.cloudsdk.au;
import com.tencent.cloudsdk.tsocket.GlobalContext;
import java.io.File;
import java.util.Random;
import qsbk.app.share.QQDialogAuthorizeActivity;

// compiled from: SourceFile
public class ClientIdManager {
    private static final String a;
    private static final byte[] b;
    private static final String c;
    private static ClientIdManager e;
    private int d;

    static {
        a = ClientIdManager.class.getSimpleName();
        b = new byte[0];
        c = new StringBuilder("Tencent").append(File.separator).append("CloudSdk").toString();
    }

    private int a() {
        return GlobalContext.getContext().getSharedPreferences("perf_name_client_id", 0).getInt("pref_client_id", 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(File r8_File) {
        /*
        r7_this = this;
        r0 = 0;
        if (r8 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "";
        r2 = 0;
        r4 = new com.tencent.cloudsdk.as;
        r4.<init>();
        r1 = r4.a(r8);	 //Catch:{ Exception -> 0x00af }
        if (r1 != 0) goto L_0x003e;
    L_0x0012:
        r1 = a;	 //Catch:{ Exception -> 0x00af }
        r3 = ">>> readClientIdFromFile I: \u83b7\u53d6\u6587\u4ef6\u9501\u5931\u8d25\uff01";
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r1, r3);	 //Catch:{ Exception -> 0x00af }
        if (r2 == 0) goto L_0x001e;
    L_0x001b:
        r2.close();	 //Catch:{ Exception -> 0x0024 }
    L_0x001e:
        if (r4 == 0) goto L_0x0003;
    L_0x0020:
        r4.a();	 //Catch:{ Exception -> 0x0024 }
        goto L_0x0003;
    L_0x0024:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> readClientIdFromFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x003e:
        r3 = new java.io.BufferedReader;	 //Catch:{ Exception -> 0x00af }
        r1 = new java.io.FileReader;	 //Catch:{ Exception -> 0x00af }
        r1.<init>(r8);	 //Catch:{ Exception -> 0x00af }
        r3.<init>(r1);	 //Catch:{ Exception -> 0x00af }
        r1 = r3.readLine();	 //Catch:{ Exception -> 0x0119, all -> 0x0116 }
        r1 = r1.trim();	 //Catch:{ Exception -> 0x0119, all -> 0x0116 }
        if (r1 == 0) goto L_0x005a;
    L_0x0052:
        r2 = "";
        r2 = r2.equals(r1);	 //Catch:{ Exception -> 0x0119, all -> 0x0116 }
        if (r2 == 0) goto L_0x007f;
    L_0x005a:
        if (r3 == 0) goto L_0x005f;
    L_0x005c:
        r3.close();	 //Catch:{ Exception -> 0x0065 }
    L_0x005f:
        if (r4 == 0) goto L_0x0003;
    L_0x0061:
        r4.a();	 //Catch:{ Exception -> 0x0065 }
        goto L_0x0003;
    L_0x0065:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> readClientIdFromFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x007f:
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ Exception -> 0x0119, all -> 0x0116 }
        r1 = r1.intValue();	 //Catch:{ Exception -> 0x0119, all -> 0x0116 }
        if (r3 == 0) goto L_0x008c;
    L_0x0089:
        r3.close();	 //Catch:{ Exception -> 0x0094 }
    L_0x008c:
        if (r4 == 0) goto L_0x0091;
    L_0x008e:
        r4.a();	 //Catch:{ Exception -> 0x0094 }
    L_0x0091:
        r0 = r1;
        goto L_0x0003;
    L_0x0094:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> readClientIdFromFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x00af:
        r1 = move-exception;
    L_0x00b0:
        r3 = a;	 //Catch:{ all -> 0x00ef }
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x00ef }
        r6 = ">>> readClientIdFromFile E: ";
        r5.<init>(r6);	 //Catch:{ all -> 0x00ef }
        r6 = r1.getMessage();	 //Catch:{ all -> 0x00ef }
        r5 = r5.append(r6);	 //Catch:{ all -> 0x00ef }
        r5 = r5.toString();	 //Catch:{ all -> 0x00ef }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r3, r5, r1);	 //Catch:{ all -> 0x00ef }
        if (r2 == 0) goto L_0x00cd;
    L_0x00ca:
        r2.close();	 //Catch:{ Exception -> 0x00d4 }
    L_0x00cd:
        if (r4 == 0) goto L_0x0003;
    L_0x00cf:
        r4.a();	 //Catch:{ Exception -> 0x00d4 }
        goto L_0x0003;
    L_0x00d4:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> readClientIdFromFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x00ef:
        r1 = move-exception;
    L_0x00f0:
        if (r2 == 0) goto L_0x00f5;
    L_0x00f2:
        r2.close();	 //Catch:{ Exception -> 0x00fb }
    L_0x00f5:
        if (r4 == 0) goto L_0x00fa;
    L_0x00f7:
        r4.a();	 //Catch:{ Exception -> 0x00fb }
    L_0x00fa:
        throw r1;
    L_0x00fb:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> readClientIdFromFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x0116:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00f0;
    L_0x0119:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00b0;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(File r8_File, int r9i) {
        /*
        r7_this = this;
        r0 = 0;
        if (r8 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r2 = 0;
        r4 = new com.tencent.cloudsdk.at;
        r4.<init>();
        r1 = r4.a(r8);	 //Catch:{ Exception -> 0x005c }
        if (r1 != 0) goto L_0x003c;
    L_0x0010:
        r1 = a;	 //Catch:{ Exception -> 0x005c }
        r3 = ">>> writeClientIdToFile I: \u6709\u5176\u5b83\u8fdb\u7a0b\u5728\u5199clientId!";
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r1, r3);	 //Catch:{ Exception -> 0x005c }
        if (r2 == 0) goto L_0x001c;
    L_0x0019:
        r2.close();	 //Catch:{ Exception -> 0x0022 }
    L_0x001c:
        if (r4 == 0) goto L_0x0003;
    L_0x001e:
        r4.a();	 //Catch:{ Exception -> 0x0022 }
        goto L_0x0003;
    L_0x0022:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> writeClientIdToFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x003c:
        r3 = new java.io.BufferedWriter;	 //Catch:{ Exception -> 0x005c }
        r1 = new java.io.FileWriter;	 //Catch:{ Exception -> 0x005c }
        r1.<init>(r8);	 //Catch:{ Exception -> 0x005c }
        r3.<init>(r1);	 //Catch:{ Exception -> 0x005c }
        r1 = java.lang.String.valueOf(r9);	 //Catch:{ Exception -> 0x00e0, all -> 0x00dd }
        r3.write(r1);	 //Catch:{ Exception -> 0x00e0, all -> 0x00dd }
        r3.flush();	 //Catch:{ Exception -> 0x00e0, all -> 0x00dd }
        if (r3 == 0) goto L_0x0055;
    L_0x0052:
        r3.close();	 //Catch:{ Exception -> 0x00c2 }
    L_0x0055:
        if (r4 == 0) goto L_0x005a;
    L_0x0057:
        r4.a();	 //Catch:{ Exception -> 0x00c2 }
    L_0x005a:
        r0 = 1;
        goto L_0x0003;
    L_0x005c:
        r1 = move-exception;
    L_0x005d:
        r3 = a;	 //Catch:{ all -> 0x009b }
        r5 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x009b }
        r6 = ">>> writeClientIdToFile E: ";
        r5.<init>(r6);	 //Catch:{ all -> 0x009b }
        r6 = r1.getMessage();	 //Catch:{ all -> 0x009b }
        r5 = r5.append(r6);	 //Catch:{ all -> 0x009b }
        r5 = r5.toString();	 //Catch:{ all -> 0x009b }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r3, r5, r1);	 //Catch:{ all -> 0x009b }
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.close();	 //Catch:{ Exception -> 0x0080 }
    L_0x007a:
        if (r4 == 0) goto L_0x0003;
    L_0x007c:
        r4.a();	 //Catch:{ Exception -> 0x0080 }
        goto L_0x0003;
    L_0x0080:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> writeClientIdToFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x009b:
        r1 = move-exception;
    L_0x009c:
        if (r2 == 0) goto L_0x00a1;
    L_0x009e:
        r2.close();	 //Catch:{ Exception -> 0x00a7 }
    L_0x00a1:
        if (r4 == 0) goto L_0x00a6;
    L_0x00a3:
        r4.a();	 //Catch:{ Exception -> 0x00a7 }
    L_0x00a6:
        throw r1;
    L_0x00a7:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> writeClientIdToFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x00c2:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = ">>> writeClientIdToFile E: ";
        r3.<init>(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r2, r3, r1);
        goto L_0x0003;
    L_0x00dd:
        r1 = move-exception;
        r2 = r3;
        goto L_0x009c;
    L_0x00e0:
        r1 = move-exception;
        r2 = r3;
        goto L_0x005d;
        */

    }

    private int b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File r0_File = Environment.getExternalStorageDirectory();
            if (r0_File != null) {
                String r0_String = r0_File.getAbsolutePath();
                if (!r0_String.endsWith(File.separator)) {
                    r0_String = new StringBuilder(String.valueOf(r0_String)).append(File.separator).toString();
                }
                File r2_File = new File(new StringBuilder(String.valueOf(r0_String)).append(c).append(File.separator).append(QQDialogAuthorizeActivity.CLIENT_ID).toString());
                return r2_File.exists() ? a(r2_File) : 0;
            }
        }
        return 0;
    }

    private void c() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File r0_File = Environment.getExternalStorageDirectory();
            if (r0_File != null) {
                String r0_String = r0_File.getAbsolutePath();
                if (!r0_String.endsWith(File.separator)) {
                    r0_String = new StringBuilder(String.valueOf(r0_String)).append(File.separator).toString();
                }
                r0_String = new StringBuilder(String.valueOf(r0_String)).append(c).toString();
                File r1_File = new File(new StringBuilder(String.valueOf(r0_String)).append(File.separator).append(QQDialogAuthorizeActivity.CLIENT_ID).toString());
                if (au.b(r0_String, QQDialogAuthorizeActivity.CLIENT_ID)) {
                    a(r1_File, this.d);
                }
            }
        }
    }

    private int d() {
        int r0i = new Random().nextInt();
        return r0i > 0 ? r0i : -r0i;
    }

    private void e() {
        Editor r0_Editor = GlobalContext.getContext().getSharedPreferences("perf_name_client_id", 0).edit();
        r0_Editor.putInt("pref_client_id", this.d);
        r0_Editor.commit();
    }

    public static synchronized ClientIdManager getInstance() {
        ClientIdManager r0_ClientIdManager;
        synchronized (ClientIdManager.class) {
            if (e == null) {
                e = new ClientIdManager();
            }
            r0_ClientIdManager = e;
        }
        return r0_ClientIdManager;
    }

    public int getClientId() {
        synchronized (b) {
            if (this.d == 0) {
                int r0i = a();
                if (Environment.getExternalStorageState().equals("mounted")) {
                    int r2i = b();
                    if (r2i != 0) {
                        this.d = r2i;
                        if (r2i != r0i) {
                            e();
                        }
                    } else if (r0i != 0) {
                        this.d = r0i;
                        c();
                    }
                } else {
                    this.d = r0i;
                }
            }
            if (this.d == 0) {
                this.d = d();
                e();
                c();
            }
        }
        return this.d;
    }
}
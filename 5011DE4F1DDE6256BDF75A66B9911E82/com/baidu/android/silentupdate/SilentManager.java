package com.baidu.android.silentupdate;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.conversation.RConversation;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONObject;

public class SilentManager {
    private static final String a;
    private static String b;
    private static boolean c;
    private static boolean d;

    static {
        a = SilentManager.class.getSimpleName();
        b = null;
        c = false;
        d = true;
    }

    private static String a(File r6_File) {
        if (!r6_File.isFile() || !r6_File.exists()) {
            return null;
        }
        byte[] r1_byteA = new byte[1024];
        try {
            MessageDigest r2_MessageDigest = MessageDigest.getInstance("MD5");
            FileInputStream r3_FileInputStream = new FileInputStream(r6_File);
            while (true) {
                int r4i = r3_FileInputStream.read(r1_byteA, 0, Util.BYTE_OF_KB);
                if (r4i != -1) {
                    r2_MessageDigest.update(r1_byteA, 0, r4i);
                } else {
                    r3_FileInputStream.close();
                    return a(r2_MessageDigest.digest()).toLowerCase();
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String a(String r2_String) {
        int r0i = r2_String.lastIndexOf(".");
        return (r2_String.lastIndexOf("/") <= r0i && r0i > 0 && r0i < r2_String.length()) ? r2_String.substring(0, r0i) : r2_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String a(String r5_String, String r6_String) {
        /*
        r0 = new java.security.spec.X509EncodedKeySpec;
        r1 = r5.getBytes();
        r1 = com.baidu.android.silentupdate.a.a(r1);
        r0.<init>(r1);
        r1 = "RSA";
        r1 = java.security.KeyFactory.getInstance(r1);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = r1.generatePublic(r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r1 = "RSA/ECB/PKCS1Padding";
        r1 = javax.crypto.Cipher.getInstance(r1);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r2 = 2;
        r1.init(r2, r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = r6.getBytes();	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = com.baidu.android.silentupdate.a.a(r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r1 = r1.doFinal(r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = new java.lang.String;	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r2 = "UTF8";
        r0.<init>(r1, r2);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r2 = c;	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        if (r2 == 0) goto L_0x0050;
    L_0x0038:
        r2 = a;	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r3.<init>();	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r4 = "RSA decrypt:";
        r3 = r3.append(r4);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = r3.append(r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r0 = r0.toString();	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        android.util.Log.d(r2, r0);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
    L_0x0050:
        r0 = new java.lang.String;	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
        r2 = "UTF8";
        r0.<init>(r1, r2);	 //Catch:{ NoSuchAlgorithmException -> 0x0072, InvalidKeySpecException -> 0x0070, NoSuchPaddingException -> 0x006e, InvalidKeyException -> 0x006c, IllegalBlockSizeException -> 0x006a, BadPaddingException -> 0x0068, UnsupportedEncodingException -> 0x0066, Exception -> 0x0058 }
    L_0x0057:
        return r0;
    L_0x0058:
        r0 = move-exception;
    L_0x0059:
        r0 = c;
        if (r0 == 0) goto L_0x0064;
    L_0x005d:
        r0 = a;
        r1 = "RSA decrypt fail";
        android.util.Log.d(r0, r1);
    L_0x0064:
        r0 = 0;
        goto L_0x0057;
    L_0x0066:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0068:
        r0 = move-exception;
        goto L_0x0059;
    L_0x006a:
        r0 = move-exception;
        goto L_0x0059;
    L_0x006c:
        r0 = move-exception;
        goto L_0x0059;
    L_0x006e:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0070:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0072:
        r0 = move-exception;
        goto L_0x0059;
        */

    }

    private static String a(byte[] r6_byteA) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = r1_String;
        int r1i = 0;
        while (r1i < r6_byteA.length) {
            String r2_String = Integer.toHexString(r6_byteA[r1i] & 255);
            r0_String = r2_String.length() == 1 ? r0_String + "0" + r2_String : r0_String + r2_String;
            r1i++;
        }
        return r0_String;
    }

    private static void a(Closeable r1_Closeable) {
        if (r1_Closeable != null) {
            try {
                r1_Closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static void a(InputStream r3_InputStream, OutputStream r4_OutputStream) throws IOException {
        if (!r3_InputStream instanceof BufferedInputStream) {
            r3_InputStream = new BufferedInputStream(r3_InputStream);
        }
        if (!r4_OutputStream instanceof BufferedOutputStream) {
            r4_OutputStream = new BufferedOutputStream(r4_OutputStream);
        }
        byte[] r0_byteA = new byte[512];
        while (true) {
            int r1i = r3_InputStream.read(r0_byteA);
            if (r1i == -1) {
                r4_OutputStream.flush();
                a((Closeable)r3_InputStream);
                a((Closeable)r4_OutputStream);
                return;
            } else {
                r4_OutputStream.write(r0_byteA, 0, r1i);
            }
        }
    }

    private static boolean a(Context r8_Context, File r9_File, File r10_File, String r11_String, String r12_String, String r13_String) {
        if (!(d)) {
            return true;
        }
        try {
            String r2_String = RContactStorage.PRIMARY_KEY;
            try {
                BufferedReader r3_BufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(r9_File)));
                String r4_String = RContactStorage.PRIMARY_KEY;
                while (true) {
                    r4_String = r3_BufferedReader.readLine();
                    if (r4_String != null) {
                        r2_String = r2_String + r4_String + "\r\n";
                    }
                    JSONObject r3_JSONObject = new JSONObject(a(r11_String, r2_String));
                    BufferedReader r5_BufferedReader = new BufferedReader(new InputStreamReader(r8_Context.getAssets().open(r12_String + "/" + a(r13_String) + ".key")));
                    r2_String = RContactStorage.PRIMARY_KEY;
                    r4_String = RContactStorage.PRIMARY_KEY;
                    while (true) {
                        r4_String = r5_BufferedReader.readLine();
                        if (r4_String != null) {
                            r2_String = r2_String + r4_String + "\r\n";
                        } else {
                            JSONObject r4_JSONObject = new JSONObject(a(r11_String, r2_String));
                            if (r4_JSONObject.getString(RConversation.COL_FLAG).equals(r3_JSONObject.getString(RConversation.COL_FLAG))) {
                                if (r4_JSONObject.getLong(AdViewUtil.PREFS_STRING_TIMESTAMP) > r3_JSONObject.getLong(AdViewUtil.PREFS_STRING_TIMESTAMP)) {
                                    if (c) {
                                        Log.d(a, "APK newer than dex!");
                                    }
                                    return false;
                                } else {
                                    r2_String = a(r10_File);
                                    if (c) {
                                        Log.d(a, "Lib MD5 : " + r2_String);
                                    }
                                    if (r3_JSONObject.getString("md5").equals(r2_String)) {
                                        if (!(c)) {
                                            return true;
                                        }
                                        Log.d(a, "RSA check ok");
                                        return true;
                                    } else {
                                        if (c) {
                                            Log.d(a, "RSA check fail");
                                        }
                                        return false;
                                    }
                                }
                            } else {
                                if (c) {
                                    Log.d(a, "Flag not equal!");
                                }
                                return false;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e_2) {
            }
        } catch (Exception e_3) {
            return false;
        }
    }

    private static boolean a(Context r11_Context, String r12_String, String r13_String, String r14_String, String r15_String) {
        if (b == null) {
            throw new RuntimeException("public key must init first!");
        } else if (r13_String == null) {
            throw new RuntimeException("Lib Name Must Not Null!");
        } else {
            String r4_String;
            Date r6_Date = new Date();
            r4_String = r12_String == null ? RContactStorage.PRIMARY_KEY : r12_String;
            File r2_File = new File(r11_Context.getDir("push_lib", 0).getAbsolutePath() + "/" + r13_String);
            String r7_String = a(r13_String) + ".key";
            File r1_File = new File(r11_Context.getDir("push_lib", 0).getAbsolutePath() + "/" + r7_String);
            if (r15_String != null) {
                File r3_File = new File(r15_String + "/" + r13_String);
                File r5_File = new File(r15_String + "/" + r7_String);
                if (r3_File.exists() && r5_File.exists()) {
                    int r8i = Process.myPid();
                    Iterator r9_Iterator = ((ActivityManager) r11_Context.getSystemService("activity")).getRunningAppProcesses().iterator();
                    while (r9_Iterator.hasNext()) {
                        RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r9_Iterator.next();
                        if (r0_RunningAppProcessInfo.pid == r8i && r0_RunningAppProcessInfo.processName.endsWith(r11_Context.getPackageName())) {
                            if (c) {
                                Log.d(a, "Found update");
                            }
                            r3_File.renameTo(r2_File);
                            r5_File.renameTo(r1_File);
                        }
                    }
                }
            }
            ClassLoader r0_ClassLoader;
            boolean r0z;
            if (r2_File.exists() && r1_File.exists() && a(r11_Context, r1_File, r2_File, b, r4_String, r13_String)) {
                if (c) {
                    Log.d(a, "Lib file check ok");
                }
                r0_ClassLoader = b.a(r2_File.getAbsolutePath(), r11_Context.getDir("push_dex", 0).getAbsolutePath(), r14_String, r11_Context);
                if (r0_ClassLoader != null) {
                    r0z = b.a(r0_ClassLoader, r11_Context.getClassLoader());
                    if (r0z) {
                        if (c) {
                            return r0z;
                        }
                        Log.d(a, "Load lib ok, cost " + (new Date().getTime() - r6_Date.getTime()) + LocaleUtil.MALAY);
                        return r0z;
                    } else {
                        if (c) {
                            return r0z;
                        }
                        Log.e(a, "Insert classloader fail");
                        return r0z;
                    }
                } else {
                    if (c) {
                        return false;
                    }
                    Log.e(a, "Create classloader fail");
                    return false;
                }
            } else {
                if (c) {
                    Log.d(a, "Need copy lib from assert");
                }
                try {
                    a(r11_Context.getAssets().open(r4_String + "/" + r13_String), new FileOutputStream(r2_File));
                    a(r11_Context.getAssets().open(r4_String + "/" + r7_String), new FileOutputStream(r1_File));
                } catch (IOException e) {
                    if (c) {
                        Log.e(a, "Copy from assert fail!");
                    }
                    return false;
                }
                r0_ClassLoader = b.a(r2_File.getAbsolutePath(), r11_Context.getDir("push_dex", 0).getAbsolutePath(), r14_String, r11_Context);
                if (r0_ClassLoader != null) {
                    if (!c) {
                        Log.e(a, "Create classloader fail");
                    }
                    return false;
                } else {
                    r0z = b.a(r0_ClassLoader, r11_Context.getClassLoader());
                    if (r0z) {
                        if (c) {
                            return r0z;
                        }
                        Log.d(a, "Load lib ok, cost " + (new Date().getTime() - r6_Date.getTime()) + LocaleUtil.MALAY);
                        return r0z;
                    } else {
                        if (c) {
                            return r0z;
                        }
                        Log.e(a, "Insert classloader fail");
                        return r0z;
                    }
                }
            }
        }
    }

    public static void enableDebugMode(boolean r0z) {
        c = r0z;
    }

    public static void enableRSA(boolean r0z) {
        d = r0z;
    }

    public static String getKey() {
        return b;
    }

    public static boolean loadLib(Context r2_Context, String r3_String, String r4_String) {
        return loadLib(r2_Context, r3_String, r4_String, "/data/data/" + r2_Context.getPackageName() + "/lib");
    }

    public static boolean loadLib(Context r2_Context, String r3_String, String r4_String, String r5_String) {
        return a(r2_Context, r3_String, r4_String, r5_String, r2_Context.getDir("push_update", 0).getAbsolutePath());
    }

    public static void setKey(String r0_String) {
        b = r0_String;
    }
}
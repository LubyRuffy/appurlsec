package qsbk.app.message.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.Base64;

public class ACache {
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, ACache> a;
    private ACacheManager b;


    public class ACacheManager {
        protected File a;
        private final AtomicLong c;
        private final AtomicInteger d;
        private final long e;
        private final int f;
        private final Map<File, Long> g;

        private ACacheManager(File r3_File, long r4j, int r6i) {
            this.g = Collections.synchronizedMap(new HashMap());
            this.a = r3_File;
            this.e = r4j;
            this.f = r6i;
            this.c = new AtomicLong();
            this.d = new AtomicInteger();
            a();
        }

        private File a(String r5_String) {
            File r0_File = b(r5_String);
            Long r1_Long = Long.valueOf(System.currentTimeMillis());
            r0_File.setLastModified(r1_Long.longValue());
            this.g.put(r0_File, r1_Long);
            return r0_File;
        }

        private void a() {
            new Thread(new a(this)).start();
        }

        private void a(File r7_File) {
            int r0i = this.d.get();
            while (r0i + 1 > this.f) {
                this.c.addAndGet(-c());
                r0i = this.d.addAndGet(-1);
            }
            this.d.addAndGet(1);
            long r2j = b(r7_File);
            long r0j = this.c.get();
            while (r0j + r2j > this.e) {
                r0j = this.c.addAndGet(-c());
            }
            this.c.addAndGet(r2j);
            Long r0_Long = Long.valueOf(System.currentTimeMillis());
            r7_File.setLastModified(r0_Long.longValue());
            this.g.put(r7_File, r0_Long);
        }

        private long b(File r3_File) {
            return r3_File.length();
        }

        private File b(String r5_String) {
            return new File(this.a, r5_String.hashCode() + RContactStorage.PRIMARY_KEY);
        }

        private void b() {
            this.g.clear();
            this.c.set(0);
            File[] r1_FileA = this.a.listFiles();
            if (r1_FileA != null) {
                int r2i = r1_FileA.length;
                int r0i = 0;
                while (r0i < r2i) {
                    r1_FileA[r0i].delete();
                    r0i++;
                }
            }
        }

        private long c() {
            File r2_File = null;
            if (this.g.isEmpty()) {
                return 0;
            }
            Set r0_Set = this.g.entrySet();
            synchronized (this.g) {
                Iterator r5_Iterator = r0_Set.iterator();
                Long r3_Long = null;
                while (r5_Iterator.hasNext()) {
                    File r1_File;
                    Long r0_Long;
                    Entry r0_Entry = (Entry) r5_Iterator.next();
                    if (r2_File == null) {
                        r1_File = (File) r0_Entry.getKey();
                        r0_Long = (Long) r0_Entry.getValue();
                    } else {
                        Long r1_Long = (Long) r0_Entry.getValue();
                        if (r1_Long.longValue() < r3_Long.longValue()) {
                            r0_Long = r1_Long;
                            r1_File = (File) r0_Entry.getKey();
                        } else {
                            r1_File = r2_File;
                            r0_Long = r3_Long;
                        }
                    }
                    r2_File = r1_File;
                    r3_Long = r0_Long;
                }
            }
            long r0j = b(r2_File);
            if (!r2_File.delete()) {
                return r0j;
            }
            this.g.remove(r2_File);
            return r0j;
        }

        private boolean c(String r2_String) {
            return a(r2_String).delete();
        }
    }

    private static class a {
        private static int a_(byte[] r2_byteA, char r3c) {
            int r0i = 0;
            while (r0i < r2_byteA.length) {
                if (r2_byteA[r0i] == r3c) {
                    return r0i;
                }
                r0i++;
            }
            return -1;
        }

        private static String a_(int r3i) {
            String r0_String = System.currentTimeMillis() + RContactStorage.PRIMARY_KEY;
            while (r0_String.length() < 13) {
                r0_String = "0" + r0_String;
            }
            return r0_String + "-" + r3i + ' ';
        }

        private static byte[] a_(byte[] r4_byteA, int r5i, int r6i) {
            int r0i = r6i - r5i;
            if (r0i < 0) {
                throw new IllegalArgumentException(r5i + " > " + r6i);
            } else {
                Object r1_Object = new Object[r0i];
                System.arraycopy(r4_byteA, r5i, r1_Object, 0, Math.min(r4_byteA.length - r5i, r0i));
                return r1_Object;
            }
        }

        private static Bitmap b(Drawable r5_Drawable) {
            if (r5_Drawable == null) {
                return null;
            }
            int r1i = r5_Drawable.getIntrinsicWidth();
            int r2i = r5_Drawable.getIntrinsicHeight();
            Bitmap r0_Bitmap = Bitmap.createBitmap(r1i, r2i, r5_Drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
            Canvas r3_Canvas = new Canvas(r0_Bitmap);
            r5_Drawable.setBounds(0, 0, r1i, r2i);
            r5_Drawable.draw(r3_Canvas);
            return r0_Bitmap;
        }

        private static String b(int r2i, String r3_String) {
            return a(r2i) + r3_String;
        }

        private static byte[] b(int r4i, byte[] r5_byteA) {
            Object r0_Object = a(r4i).getBytes();
            Object r1_Object = new Object[(r0_Object.length + r5_byteA.length)];
            System.arraycopy(r0_Object, 0, r1_Object, 0, r0_Object.length);
            System.arraycopy(r5_byteA, 0, r1_Object, r0_Object.length, r5_byteA.length);
            return r1_Object;
        }

        private static boolean c(String r1_String) {
            return d(r1_String.getBytes());
        }

        private static byte[] c(Bitmap r3_Bitmap) {
            if (r3_Bitmap == null) {
                return null;
            }
            OutputStream r0_OutputStream = new ByteArrayOutputStream();
            r3_Bitmap.compress(CompressFormat.PNG, 100, r0_OutputStream);
            return r0_OutputStream.toByteArray();
        }

        private static Drawable d(Bitmap r1_Bitmap) {
            return r1_Bitmap == null ? null : new BitmapDrawable(r1_Bitmap);
        }

        private static String d(String r2_String) {
            return (r2_String == null || (!f(r2_String.getBytes()))) ? r2_String : r2_String.substring(r2_String.indexOf(Base64.ORDERED) + 1, r2_String.length());
        }

        private static boolean d(byte[] r12_byteA) {
            String[] r3_StringA = g(r12_byteA);
            if (r3_StringA == null || r3_StringA.length != 2) {
                return false;
            }
            String r0_String = r3_StringA[0];
            while (r0_String.startsWith("0")) {
                r0_String = r0_String.substring(1, r0_String.length());
            }
            if (System.currentTimeMillis() > Long.valueOf(r0_String).longValue() + Long.valueOf(r3_StringA[1]).longValue() * 1000) {
                return true;
            }
            return false;
        }

        private static byte[] e(byte[] r2_byteA) {
            return f(r2_byteA) ? a(r2_byteA, a(r2_byteA, ' ') + 1, r2_byteA.length) : r2_byteA;
        }

        private static boolean f(byte[] r2_byteA) {
            return r2_byteA != null && r2_byteA.length > 15 && r2_byteA[13] == 45 && a(r2_byteA, ' ') > 14;
        }

        private static String[] g(byte[] r5_byteA) {
            if (!f(r5_byteA)) {
                return null;
            }
            String r1_String = new String(a(r5_byteA, 0, REQUEST_CODE.REQUEST_CODE_EDIT_GENDER));
            String r2_String = new String(a(r5_byteA, REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, a(r5_byteA, ' ')));
            String[] r0_StringA = new String[2];
            r0_StringA[0] = r1_String;
            r0_StringA[1] = r2_String;
            return r0_StringA;
        }

        private static Bitmap h(byte[] r2_byteA) {
            return r2_byteA.length == 0 ? null : BitmapFactory.decodeByteArray(r2_byteA, 0, r2_byteA.length);
        }
    }

    static {
        a = new HashMap();
    }

    private ACache(File r8_File, long r9j, int r11i) {
        if (r8_File.exists() || r8_File.mkdirs()) {
            this.b = new ACacheManager(r8_File, r9j, r11i, null);
        } else {
            throw new RuntimeException("can't make dirs in " + r8_File.getAbsolutePath());
        }
    }

    private static String a() {
        return "_" + Process.myPid();
    }

    public static ACache get(Context r1_Context) {
        return get(r1_Context, "ACache");
    }

    public static ACache get(Context r3_Context, long r4j, int r6i) {
        return get(new File(r3_Context.getCacheDir(), "ACache"), r4j, r6i);
    }

    public static ACache get(Context r4_Context, String r5_String) {
        return get(new File(r4_Context.getCacheDir(), r5_String), 50000000, (int)a.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static ACache get(File r3_File) {
        return get(r3_File, 50000000, (int)a.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static ACache get(File r4_File, long r5j, int r7i) {
        ACache r0_ACache = (ACache) a.get(r4_File.getAbsoluteFile() + a());
        if (r0_ACache != null) {
            return r0_ACache;
        }
        r0_ACache = new ACache(r4_File, r5j, r7i);
        a.put(r4_File.getAbsolutePath() + a(), r0_ACache);
        return r0_ACache;
    }

    public void clear() {
        this.b.b();
    }

    public File file(String r3_String) {
        File r0_File = this.b.b(r3_String);
        return r0_File.exists() ? r0_File : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getAsBinary(String r6_String) {
        /*
        r5_this = this;
        r0 = 0;
        r1 = 0;
        r2 = r5.b;	 //Catch:{ Exception -> 0x004d, all -> 0x005d }
        r3 = r2.a(r6);	 //Catch:{ Exception -> 0x004d, all -> 0x005d }
        r2 = r3.exists();	 //Catch:{ Exception -> 0x004d, all -> 0x005d }
        if (r2 != 0) goto L_0x0019;
    L_0x000e:
        if (r0 == 0) goto L_0x0013;
    L_0x0010:
        r1.close();	 //Catch:{ IOException -> 0x0014 }
    L_0x0013:
        return r0;
    L_0x0014:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
    L_0x0019:
        r2 = new java.io.RandomAccessFile;	 //Catch:{ Exception -> 0x004d, all -> 0x005d }
        r1 = "r";
        r2.<init>(r3, r1);	 //Catch:{ Exception -> 0x004d, all -> 0x005d }
        r3 = r2.length();	 //Catch:{ Exception -> 0x006d }
        r1 = (int) r3;	 //Catch:{ Exception -> 0x006d }
        r1 = new byte[r1];	 //Catch:{ Exception -> 0x006d }
        r2.read(r1);	 //Catch:{ Exception -> 0x006d }
        r3 = qsbk.app.message.util.ACache.a.d(r1);	 //Catch:{ Exception -> 0x006d }
        if (r3 != 0) goto L_0x003f;
    L_0x0030:
        r0 = qsbk.app.message.util.ACache.a.e(r1);	 //Catch:{ Exception -> 0x006d }
        if (r2 == 0) goto L_0x0013;
    L_0x0036:
        r2.close();	 //Catch:{ IOException -> 0x003a }
        goto L_0x0013;
    L_0x003a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
    L_0x003f:
        if (r2 == 0) goto L_0x0044;
    L_0x0041:
        r2.close();	 //Catch:{ IOException -> 0x0048 }
    L_0x0044:
        r5.remove(r6);
        goto L_0x0013;
    L_0x0048:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0044;
    L_0x004d:
        r1 = move-exception;
        r2 = r0;
    L_0x004f:
        r1.printStackTrace();	 //Catch:{ all -> 0x006b }
        if (r2 == 0) goto L_0x0013;
    L_0x0054:
        r2.close();	 //Catch:{ IOException -> 0x0058 }
        goto L_0x0013;
    L_0x0058:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
    L_0x005d:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0060:
        if (r2 == 0) goto L_0x0065;
    L_0x0062:
        r2.close();	 //Catch:{ IOException -> 0x0066 }
    L_0x0065:
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0065;
    L_0x006b:
        r0 = move-exception;
        goto L_0x0060;
    L_0x006d:
        r1 = move-exception;
        goto L_0x004f;
        */

    }

    public Bitmap getAsBitmap(String r2_String) {
        return getAsBinary(r2_String) == null ? null : a.h(getAsBinary(r2_String));
    }

    public Drawable getAsDrawable(String r2_String) {
        return getAsBinary(r2_String) == null ? null : a.d(a.h(getAsBinary(r2_String)));
    }

    public JSONArray getAsJSONArray(String r3_String) {
        try {
            return new JSONArray(getAsString(r3_String));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getAsJSONObject(String r3_String) {
        try {
            return new JSONObject(getAsString(r3_String));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Object getAsObject(String r5_String) {
        /*
        r4_this = this;
        r0 = 0;
        r1 = r4.getAsBinary(r5);
        if (r1 == 0) goto L_0x001f;
    L_0x0007:
        r3 = new java.io.ByteArrayInputStream;	 //Catch:{ Exception -> 0x002a, all -> 0x0045 }
        r3.<init>(r1);	 //Catch:{ Exception -> 0x002a, all -> 0x0045 }
        r2 = new java.io.ObjectInputStream;	 //Catch:{ Exception -> 0x0064, all -> 0x005e }
        r2.<init>(r3);	 //Catch:{ Exception -> 0x0064, all -> 0x005e }
        r0 = r2.readObject();	 //Catch:{ Exception -> 0x0067 }
        if (r3 == 0) goto L_0x001a;
    L_0x0017:
        r3.close();	 //Catch:{ IOException -> 0x0020 }
    L_0x001a:
        if (r2 == 0) goto L_0x001f;
    L_0x001c:
        r2.close();	 //Catch:{ IOException -> 0x0025 }
    L_0x001f:
        return r0;
    L_0x0020:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x0025:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001f;
    L_0x002a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x002d:
        r1.printStackTrace();	 //Catch:{ all -> 0x0062 }
        if (r3 == 0) goto L_0x0035;
    L_0x0032:
        r3.close();	 //Catch:{ IOException -> 0x0040 }
    L_0x0035:
        if (r2 == 0) goto L_0x001f;
    L_0x0037:
        r2.close();	 //Catch:{ IOException -> 0x003b }
        goto L_0x001f;
    L_0x003b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001f;
    L_0x0040:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0035;
    L_0x0045:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x0049:
        if (r3 == 0) goto L_0x004e;
    L_0x004b:
        r3.close();	 //Catch:{ IOException -> 0x0054 }
    L_0x004e:
        if (r2 == 0) goto L_0x0053;
    L_0x0050:
        r2.close();	 //Catch:{ IOException -> 0x0059 }
    L_0x0053:
        throw r0;
    L_0x0054:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004e;
    L_0x0059:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0053;
    L_0x005e:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0049;
    L_0x0062:
        r0 = move-exception;
        goto L_0x0049;
    L_0x0064:
        r1 = move-exception;
        r2 = r0;
        goto L_0x002d;
    L_0x0067:
        r1 = move-exception;
        goto L_0x002d;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String getAsString(String r6_String) {
        /*
        r5_this = this;
        r0 = 0;
        r1 = r5.b;
        r1 = r1.a(r6);
        r2 = r1.exists();
        if (r2 != 0) goto L_0x000e;
    L_0x000d:
        return r0;
    L_0x000e:
        r2 = new java.io.BufferedReader;	 //Catch:{ IOException -> 0x0055, all -> 0x0065 }
        r3 = new java.io.FileReader;	 //Catch:{ IOException -> 0x0055, all -> 0x0065 }
        r3.<init>(r1);	 //Catch:{ IOException -> 0x0055, all -> 0x0065 }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x0055, all -> 0x0065 }
        r1 = "";
    L_0x001a:
        r3 = r2.readLine();	 //Catch:{ IOException -> 0x0075 }
        if (r3 == 0) goto L_0x0032;
    L_0x0020:
        r4 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0075 }
        r4.<init>();	 //Catch:{ IOException -> 0x0075 }
        r1 = r4.append(r1);	 //Catch:{ IOException -> 0x0075 }
        r1 = r1.append(r3);	 //Catch:{ IOException -> 0x0075 }
        r1 = r1.toString();	 //Catch:{ IOException -> 0x0075 }
        goto L_0x001a;
    L_0x0032:
        r3 = qsbk.app.message.util.ACache.a.c(r1);	 //Catch:{ IOException -> 0x0075 }
        if (r3 != 0) goto L_0x0047;
    L_0x0038:
        r0 = qsbk.app.message.util.ACache.a.d(r1);	 //Catch:{ IOException -> 0x0075 }
        if (r2 == 0) goto L_0x000d;
    L_0x003e:
        r2.close();	 //Catch:{ IOException -> 0x0042 }
        goto L_0x000d;
    L_0x0042:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000d;
    L_0x0047:
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        r2.close();	 //Catch:{ IOException -> 0x0050 }
    L_0x004c:
        r5.remove(r6);
        goto L_0x000d;
    L_0x0050:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004c;
    L_0x0055:
        r1 = move-exception;
        r2 = r0;
    L_0x0057:
        r1.printStackTrace();	 //Catch:{ all -> 0x0073 }
        if (r2 == 0) goto L_0x000d;
    L_0x005c:
        r2.close();	 //Catch:{ IOException -> 0x0060 }
        goto L_0x000d;
    L_0x0060:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000d;
    L_0x0065:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0068:
        if (r2 == 0) goto L_0x006d;
    L_0x006a:
        r2.close();	 //Catch:{ IOException -> 0x006e }
    L_0x006d:
        throw r0;
    L_0x006e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006d;
    L_0x0073:
        r0 = move-exception;
        goto L_0x0068;
    L_0x0075:
        r1 = move-exception;
        goto L_0x0057;
        */

    }

    public void put(String r2_String, Bitmap r3_Bitmap) {
        put(r2_String, a.c(r3_Bitmap));
    }

    public void put(String r2_String, Bitmap r3_Bitmap, int r4i) {
        put(r2_String, a.c(r3_Bitmap), r4i);
    }

    public void put(String r2_String, Drawable r3_Drawable) {
        put(r2_String, a.b(r3_Drawable));
    }

    public void put(String r2_String, Drawable r3_Drawable, int r4i) {
        put(r2_String, a.b(r3_Drawable), r4i);
    }

    public void put(String r2_String, Serializable r3_Serializable) {
        put(r2_String, r3_Serializable, -1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(String r4_String, Serializable r5_Serializable, int r6i) {
        /*
        r3_this = this;
        r2 = 0;
        r0 = new java.io.ByteArrayOutputStream;	 //Catch:{ Exception -> 0x0036, all -> 0x002a }
        r0.<init>();	 //Catch:{ Exception -> 0x0036, all -> 0x002a }
        r1 = new java.io.ObjectOutputStream;	 //Catch:{ Exception -> 0x0036, all -> 0x002a }
        r1.<init>(r0);	 //Catch:{ Exception -> 0x0036, all -> 0x002a }
        r1.writeObject(r5);	 //Catch:{ Exception -> 0x0020 }
        r0 = r0.toByteArray();	 //Catch:{ Exception -> 0x0020 }
        r2 = -1;
        if (r6 == r2) goto L_0x001c;
    L_0x0015:
        r3.put(r4, r0, r6);	 //Catch:{ Exception -> 0x0020 }
    L_0x0018:
        r1.close();	 //Catch:{ IOException -> 0x0030 }
    L_0x001b:
        return;
    L_0x001c:
        r3.put(r4, r0);	 //Catch:{ Exception -> 0x0020 }
        goto L_0x0018;
    L_0x0020:
        r0 = move-exception;
    L_0x0021:
        r0.printStackTrace();	 //Catch:{ all -> 0x0034 }
        r1.close();	 //Catch:{ IOException -> 0x0028 }
        goto L_0x001b;
    L_0x0028:
        r0 = move-exception;
        goto L_0x001b;
    L_0x002a:
        r0 = move-exception;
        r1 = r2;
    L_0x002c:
        r1.close();	 //Catch:{ IOException -> 0x0032 }
    L_0x002f:
        throw r0;
    L_0x0030:
        r0 = move-exception;
        goto L_0x001b;
    L_0x0032:
        r1 = move-exception;
        goto L_0x002f;
    L_0x0034:
        r0 = move-exception;
        goto L_0x002c;
    L_0x0036:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0021;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(String r6_String, String r7_String) {
        /*
        r5_this = this;
        r0 = r5.b;
        r3 = r0.b(r6);
        r2 = 0;
        r1 = new java.io.BufferedWriter;	 //Catch:{ IOException -> 0x0029, all -> 0x0041 }
        r0 = new java.io.FileWriter;	 //Catch:{ IOException -> 0x0029, all -> 0x0041 }
        r0.<init>(r3);	 //Catch:{ IOException -> 0x0029, all -> 0x0041 }
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1.<init>(r0, r4);	 //Catch:{ IOException -> 0x0029, all -> 0x0041 }
        r1.write(r7);	 //Catch:{ IOException -> 0x0058 }
        if (r1 == 0) goto L_0x001e;
    L_0x0018:
        r1.flush();	 //Catch:{ IOException -> 0x0024 }
        r1.close();	 //Catch:{ IOException -> 0x0024 }
    L_0x001e:
        r0 = r5.b;
        r0.a(r3);
    L_0x0023:
        return;
    L_0x0024:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001e;
    L_0x0029:
        r0 = move-exception;
        r1 = r2;
    L_0x002b:
        r0.printStackTrace();	 //Catch:{ all -> 0x0055 }
        if (r1 == 0) goto L_0x0036;
    L_0x0030:
        r1.flush();	 //Catch:{ IOException -> 0x003c }
        r1.close();	 //Catch:{ IOException -> 0x003c }
    L_0x0036:
        r0 = r5.b;
        r0.a(r3);
        goto L_0x0023;
    L_0x003c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0036;
    L_0x0041:
        r0 = move-exception;
    L_0x0042:
        if (r2 == 0) goto L_0x004a;
    L_0x0044:
        r2.flush();	 //Catch:{ IOException -> 0x0050 }
        r2.close();	 //Catch:{ IOException -> 0x0050 }
    L_0x004a:
        r1 = r5.b;
        r1.a(r3);
        throw r0;
    L_0x0050:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004a;
    L_0x0055:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0042;
    L_0x0058:
        r0 = move-exception;
        goto L_0x002b;
        */

    }

    public void put(String r2_String, String r3_String, int r4i) {
        put(r2_String, a.b(r4i, r3_String));
    }

    public void put(String r2_String, JSONArray r3_JSONArray) {
        put(r2_String, r3_JSONArray.toString());
    }

    public void put(String r2_String, JSONArray r3_JSONArray, int r4i) {
        put(r2_String, r3_JSONArray.toString(), r4i);
    }

    public void put(String r2_String, JSONObject r3_JSONObject) {
        put(r2_String, r3_JSONObject.toString());
    }

    public void put(String r2_String, JSONObject r3_JSONObject, int r4i) {
        put(r2_String, r3_JSONObject.toString(), r4i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(String r5_String, byte[] r6_byteA) {
        /*
        r4_this = this;
        r0 = r4.b;
        r3 = r0.b(r5);
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0022, all -> 0x003a }
        r1.<init>(r3);	 //Catch:{ Exception -> 0x0022, all -> 0x003a }
        r1.write(r6);	 //Catch:{ Exception -> 0x0051 }
        if (r1 == 0) goto L_0x0017;
    L_0x0011:
        r1.flush();	 //Catch:{ IOException -> 0x001d }
        r1.close();	 //Catch:{ IOException -> 0x001d }
    L_0x0017:
        r0 = r4.b;
        r0.a(r3);
    L_0x001c:
        return;
    L_0x001d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0017;
    L_0x0022:
        r0 = move-exception;
        r1 = r2;
    L_0x0024:
        r0.printStackTrace();	 //Catch:{ all -> 0x004f }
        if (r1 == 0) goto L_0x002f;
    L_0x0029:
        r1.flush();	 //Catch:{ IOException -> 0x0035 }
        r1.close();	 //Catch:{ IOException -> 0x0035 }
    L_0x002f:
        r0 = r4.b;
        r0.a(r3);
        goto L_0x001c;
    L_0x0035:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
    L_0x003a:
        r0 = move-exception;
        r1 = r2;
    L_0x003c:
        if (r1 == 0) goto L_0x0044;
    L_0x003e:
        r1.flush();	 //Catch:{ IOException -> 0x004a }
        r1.close();	 //Catch:{ IOException -> 0x004a }
    L_0x0044:
        r1 = r4.b;
        r1.a(r3);
        throw r0;
    L_0x004a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0044;
    L_0x004f:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0024;
        */

    }

    public void put(String r2_String, byte[] r3_byteA, int r4i) {
        put(r2_String, a.b(r4i, r3_byteA));
    }

    public boolean remove(String r2_String) {
        return this.b.c(r2_String);
    }
}
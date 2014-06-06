package com.baidu.location;

import android.location.Location;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.baidu.location.c.a;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.tauth.Constants;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

class n {
    private static int A;
    private static int B;
    private static Location C;
    private static Location D;
    private static Location E;
    private static c F;
    private static String a;
    private static ArrayList b;
    private static ArrayList c;
    private static ArrayList d;
    private static ArrayList e;
    private static ArrayList f;
    private static ArrayList g;
    private static String h;
    private static final String i;
    private static final String j;
    private static final String k;
    private static final String l;
    private static File m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static double v;
    private static double w;
    private static double x;
    private static double y;
    private static int z;

    static {
        a = f.v;
        b = new ArrayList();
        c = new ArrayList();
        d = new ArrayList();
        e = new ArrayList();
        f = new ArrayList();
        g = new ArrayList();
        h = f.a + "/yo.dat";
        i = f.a + "/yoh.dat";
        j = f.a + "/yom.dat";
        k = f.a + "/yol.dat";
        l = f.a + "/yor.dat";
        m = null;
        n = 1024;
        o = 512;
        p = 8;
        q = 5;
        r = 8;
        s = 16;
        t = 1024;
        u = 256;
        v = 0.0d;
        w = 0.1d;
        x = 30.0d;
        y = 100.0d;
        z = 0;
        A = 64;
        B = 128;
        C = null;
        D = null;
        E = null;
        F = null;
    }

    private static int a(List r13_List, int r14i) {
        if (r13_List == null || r14i > 256 || r14i < 0) {
            return -1;
        }
        try {
            if (m == null) {
                m = new File(h);
                if (!m.exists()) {
                    j.if(a, "upload man readfile does not exist...");
                    m = null;
                    return RequestListener.DEFAULT_LOADED_SIZE;
                }
            }
            RandomAccessFile r7_RandomAccessFile = new RandomAccessFile(m, "rw");
            if (r7_RandomAccessFile.length() < 1) {
                r7_RandomAccessFile.close();
                return Constants.ERROR_URL;
            } else {
                r7_RandomAccessFile.seek((long) r14i);
                int r0i = r7_RandomAccessFile.readInt();
                int r1i = r7_RandomAccessFile.readInt();
                int r2i = r7_RandomAccessFile.readInt();
                int r3i = r7_RandomAccessFile.readInt();
                long r4j = r7_RandomAccessFile.readLong();
                if ((!a(r0i, r1i, r2i, r3i, r4j)) || r1i < 1) {
                    r7_RandomAccessFile.close();
                    return Constants.ERROR_JSON;
                } else {
                    byte[] r8_byteA = new byte[t];
                    int r6i = r1i;
                    r1i = p;
                    while (r1i > 0 && r6i > 0) {
                        r7_RandomAccessFile.seek(((long) ((((r0i + r6i) - 1) % r2i) * r3i)) + r4j);
                        int r9i = r7_RandomAccessFile.readInt();
                        if (r9i <= 0 || r9i >= r3i) {
                            r1i--;
                            r6i--;
                        } else {
                            r7_RandomAccessFile.read(r8_byteA, 0, r9i);
                            if (r8_byteA[r9i - 1] == 0) {
                                r13_List.add(new String(r8_byteA, 0, r9i - 1));
                            }
                            r1i--;
                            r6i--;
                        }
                    }
                    r7_RandomAccessFile.seek((long) r14i);
                    r7_RandomAccessFile.writeInt(r0i);
                    r7_RandomAccessFile.writeInt(r6i);
                    r7_RandomAccessFile.writeInt(r2i);
                    r7_RandomAccessFile.writeInt(r3i);
                    r7_RandomAccessFile.writeLong(r4j);
                    r7_RandomAccessFile.close();
                    return p - r1i;
                }
            }
        } catch (Exception e) {
            return Constants.ERROR_PARAM;
        }
    }

    public static String a(int r7i) {
        String r2_String;
        List r1_List;
        if (r7i == 1) {
            r2_String = i;
            r1_List = e;
        } else if (r7i == 2) {
            r2_String = j;
            r1_List = f;
        } else if (r7i == 3) {
            r2_String = k;
            r1_List = g;
        } else {
            if (r7i != 4) {
                return null;
            }
            r2_String = l;
            r1_List = g;
        }
        if (r1_List == null) {
            return null;
        }
        if (r1_List.size() < 1) {
            j.if(a, r2_String + ":get data from sd file...");
            a(r2_String, r1_List);
        }
        if (r1_List.size() <= 0) {
            return null;
        }
        String r0_String = (String) r1_List.get(0);
        r1_List.remove(0);
        return r0_String;
    }

    public static void a() {
        r = 0;
        j.if(a, "flush data...");
        a(1, false);
        a((int)XListViewHeader.STATE_REFRESHING, false);
        a((int)XListViewFooter.STATE_NOMORE, false);
        r = 8;
    }

    public static void a(double r2d, double r4d, double r6d, double r8d) {
        if (r2d > 0.0d) {
            v = r2d;
            w = r4d;
            if (r6d <= 20.0d) {
                x = r6d;
                y = r8d;
            } else {
                r6d = x;
                x = r6d;
                y = r8d;
            }
        } else {
            r2d = v;
            v = r2d;
            w = r4d;
            if (r6d <= 20.0d) {
                r6d = x;
            }
            x = r6d;
            y = r8d;
        }
    }

    public static void a(int r0i, int r1i) {
    }

    public static void a(int r0i, int r1i, boolean r2z) {
    }

    public static void a(int r13i, boolean r14z) {
        String r0_String;
        ArrayList r1_ArrayList;
        String r1_String;
        if (r13i == 1) {
            r1_String = i;
            if (r14z) {
                return;
            }
            r0_String = r1_String;
            r1_ArrayList = e;
        } else if (r13i == 2) {
            r1_String = j;
            if (r14z) {
                r0_String = r1_String;
                r1_ArrayList = e;
            } else {
                r0_String = r1_String;
                r1_ArrayList = f;
            }
        } else if (r13i == 3) {
            r1_String = k;
            if (r14z) {
                r0_String = r1_String;
                r1_ArrayList = f;
            } else {
                r0_String = r1_String;
                r1_ArrayList = g;
            }
        } else {
            if (r13i == 4) {
                r1_String = l;
                if (r14z) {
                    r0_String = r1_String;
                    r1_ArrayList = g;
                }
            }
            return;
        }
        File r2_File = new File(r0_String);
        if (!r2_File.exists()) {
            a(r0_String);
        }
        int r1i;
        try {
            int r1i_2;
            RandomAccessFile r7_RandomAccessFile = new RandomAccessFile(r2_File, "rw");
            r7_RandomAccessFile.seek(4);
            int r8i = r7_RandomAccessFile.readInt();
            int r9i = r7_RandomAccessFile.readInt();
            int r3i = r7_RandomAccessFile.readInt();
            int r2i = r7_RandomAccessFile.readInt();
            int r6i = r1_ArrayList.size();
            int r0i = r7_RandomAccessFile.readInt();
            while (r6i > r) {
                int r4i;
                j.if(a, "write new data...");
                r4i = r14z ? r0i + 1 : r0i;
                byte[] r0_byteA;
                if (r3i < r8i) {
                    r7_RandomAccessFile.seek((long) (r9i * r3i + 128));
                    r0_byteA = (((String) r1_ArrayList.get(0)) + '\u0000').getBytes();
                    r7_RandomAccessFile.writeInt(r0_byteA.length);
                    r7_RandomAccessFile.write(r0_byteA, 0, r0_byteA.length);
                    r1_ArrayList.remove(0);
                    r2i = r3i + 1;
                    r0i = r2i;
                } else if (r14z) {
                    r7_RandomAccessFile.seek((long) (r2i * r9i + 128));
                    r0_byteA = (((String) r1_ArrayList.get(0)) + '\u0000').getBytes();
                    r7_RandomAccessFile.writeInt(r0_byteA.length);
                    r7_RandomAccessFile.write(r0_byteA, 0, r0_byteA.length);
                    r1_ArrayList.remove(0);
                    r0i = r2i + 1;
                    if (r0i > r3i) {
                        r0i = 0;
                    }
                    r2i = r3i;
                } else {
                    r1i_2 = 1;
                    r0i = r4i;
                    break;
                }
                r6i--;
                r3i = r2i;
                r2i = r0i;
                r0i = r4i;
            }
            r1i_2 = 0;
            r7_RandomAccessFile.seek(12);
            r7_RandomAccessFile.writeInt(r3i);
            r7_RandomAccessFile.writeInt(r2i);
            r7_RandomAccessFile.writeInt(r0i);
            r7_RandomAccessFile.close();
            if (r1i_2 == 0 || r13i >= 4) {
            } else {
                a(r13i + 1, true);
            }
        } catch (Exception e) {
        }
    }

    public static void a(a r4_a, c r5_c, Location r6_Location, String r7_String) {
        Object r0_Object = null;
        if (j.O) {
            if (j.I != 3 || a(r6_Location, r5_c) || a(r6_Location, false)) {
                String r0_String;
                if (r4_a == null || (!r4_a.do())) {
                    if (r5_c != null && r5_c.if() && a(r6_Location, r5_c)) {
                        if (a(r6_Location)) {
                            r0_Object = r4_a;
                            r0_String = j.a(r0_Object, r5_c, r6_Location, r7_String, XListViewHeader.STATE_REFRESHING);
                            if (r0_String == null) {
                            } else {
                                r0_String = Jni.if(r0_String);
                                j.if(a, "upload size:" + r0_String.length());
                                c(r0_String);
                                E = r6_Location;
                                C = r6_Location;
                                if (r5_c == null) {
                                } else {
                                    F = r5_c;
                                }
                            }
                        } else {
                            r0_String = j.a(r0_Object, r5_c, r6_Location, r7_String, XListViewHeader.STATE_REFRESHING);
                            if (r0_String == null) {
                                r0_String = Jni.if(r0_String);
                                j.if(a, "upload size:" + r0_String.length());
                                c(r0_String);
                                E = r6_Location;
                                C = r6_Location;
                                if (r5_c == null) {
                                    F = r5_c;
                                }
                            }
                        }
                    } else {
                        if (!a(r6_Location)) {
                            r4_a = null;
                        }
                        String r1_String;
                        if (a(r6_Location, r5_c)) {
                            r0_Object = r5_c;
                            if (!(r4_a == null && r0_Object == null)) {
                                r1_String = j.a(r4_a, r0_Object, r6_Location, r7_String, XListViewFooter.STATE_NOMORE);
                                if (r1_String == null) {
                                } else {
                                    d(Jni.if(r1_String));
                                    C = r6_Location;
                                    if (r0_Object == null) {
                                    } else {
                                        F = r0_Object;
                                    }
                                }
                            }
                        } else if (r4_a == null || r0_Object == null) {
                            r1_String = j.a(r4_a, r0_Object, r6_Location, r7_String, XListViewFooter.STATE_NOMORE);
                            if (r1_String == null) {
                                d(Jni.if(r1_String));
                                C = r6_Location;
                                if (r0_Object == null) {
                                    F = r0_Object;
                                }
                            }
                        }
                    }
                } else {
                    if (!a(r6_Location, r5_c)) {
                        r5_c = null;
                    }
                    r0_String = j.a(r4_a, r5_c, r6_Location, r7_String, 1);
                    if (r0_String != null) {
                        b(Jni.if(r0_String));
                        D = r6_Location;
                        C = r6_Location;
                        if (r5_c != null) {
                            F = r5_c;
                        }
                    }
                }
            }
        }
    }

    public static void a(String r4_String) {
        try {
            File r0_File = new File(r4_String);
            if (!r0_File.exists()) {
                File r1_File = new File(f.a);
                if (!r1_File.exists()) {
                    r1_File.mkdirs();
                }
                if (!r0_File.createNewFile()) {
                    r0_File = null;
                }
                RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(r0_File, "rw");
                r1_RandomAccessFile.seek(0);
                r1_RandomAccessFile.writeInt(Base64.ORDERED);
                r1_RandomAccessFile.writeInt(LVBuffer.MAX_STRING_LENGTH);
                r1_RandomAccessFile.writeInt(1040);
                r1_RandomAccessFile.writeInt(0);
                r1_RandomAccessFile.writeInt(0);
                r1_RandomAccessFile.writeInt(0);
                r1_RandomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void a(String r0_String, int r1i) {
    }

    public static void a(String r0_String, int r1i, boolean r2z) {
    }

    private static boolean a(int r3i, int r4i, int r5i, int r6i, long r7j) {
        return r3i >= 0 && r3i < r5i && r4i >= 0 && r4i <= r5i && r5i >= 0 && r5i <= 1024 && r6i >= 128 && r6i <= 1024;
    }

    private static boolean a(Location r8_Location) {
        if (r8_Location == null) {
            return false;
        }
        if (D == null || C == null) {
            D = r8_Location;
            return true;
        } else {
            double r2d = (double) r8_Location.distanceTo(D);
            return (((double) r8_Location.distanceTo(C)) > (((r2d * ((double) j.s)) + ((((double) j.u) * r2d) * r2d)) + ((double) j.r)) ? 1 : (((double) r8_Location.distanceTo(C)) == (((r2d * ((double) j.s)) + ((((double) j.u) * r2d) * r2d)) + ((double) j.r))? 0 : -1)) > 0;
        }
    }

    private static boolean a(Location r3_Location, c r4_c) {
        if (r3_Location == null || r4_c == null || r4_c.for == null || r4_c.for.isEmpty() || r4_c.do(F)) {
            return false;
        }
        if (E != null) {
            return true;
        }
        E = r3_Location;
        return true;
    }

    public static boolean a(Location r1_Location, boolean r2z) {
        return b.a(C, r1_Location, r2z);
    }

    public static boolean a(String r11_String, List r12_List) {
        boolean r0z;
        File r0_File = new File(r11_String);
        if (!r0_File.exists()) {
            return false;
        }
        try {
            RandomAccessFile r5_RandomAccessFile = new RandomAccessFile(r0_File, "rw");
            r5_RandomAccessFile.seek(8);
            int r6i = r5_RandomAccessFile.readInt();
            int r3i = r5_RandomAccessFile.readInt();
            int r2i = r5_RandomAccessFile.readInt();
            byte[] r7_byteA = new byte[t];
            int r4i = r3i;
            r3i = r + 1;
            r0z = false;
            while (r3i > 0 && r4i > 0) {
                if (r4i < r2i) {
                    r2i = 0;
                }
                r5_RandomAccessFile.seek((long) ((r4i - 1) * r6i + 128));
                int r8i = r5_RandomAccessFile.readInt();
                if (r8i <= 0 || r8i >= r6i) {
                    r3i--;
                    r4i--;
                } else {
                    r5_RandomAccessFile.read(r7_byteA, 0, r8i);
                    if (r7_byteA[r8i - 1] == 0) {
                        r12_List.add(new String(r7_byteA, 0, r8i - 1));
                        r0z = true;
                    }
                    r3i--;
                    r4i--;
                }
            }
            r5_RandomAccessFile.seek(12);
            r5_RandomAccessFile.writeInt(r4i);
            r5_RandomAccessFile.writeInt(r2i);
            r5_RandomAccessFile.close();
            return r0z;
        } catch (Exception e) {
            return false;
        }
    }

    private static void b(String r0_String) {
        if(r0_String);
    }

    private static void c(String r0_String) {
        if(r0_String);
    }

    private static void d(String r0_String) {
        if(r0_String);
    }

    public static String do() {
        return int();
    }

    public static void for() {
    }

    public static String if() {
        RandomAccessFile r1_RandomAccessFile;
        int r2i;
        String r0_String;
        File r2_File = new File(j);
        if (r2_File.exists()) {
            try {
                r1_RandomAccessFile = new RandomAccessFile(r2_File, "rw");
                r1_RandomAccessFile.seek(20);
                r2i = r1_RandomAccessFile.readInt();
                if (r2i > AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                    r0_String = "&p1=" + r2i;
                    r1_RandomAccessFile.seek(20);
                    r1_RandomAccessFile.writeInt(0);
                    r1_RandomAccessFile.close();
                    return r0_String;
                }
            } catch (Exception e) {
            }
        }
        r2_File = new File(k);
        if (r2_File.exists()) {
            try {
                r1_RandomAccessFile = new RandomAccessFile(r2_File, "rw");
                r1_RandomAccessFile.seek(20);
                r2i = r1_RandomAccessFile.readInt();
                if (r2i > AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) {
                    r0_String = "&p2=" + r2i;
                    r1_RandomAccessFile.seek(20);
                    r1_RandomAccessFile.writeInt(0);
                    r1_RandomAccessFile.close();
                    return r0_String;
                }
            } catch (Exception e_2) {
            }
        }
        r2_File = new File(l);
        if (!r2_File.exists()) {
            return null;
        }
        try {
            r1_RandomAccessFile = new RandomAccessFile(r2_File, "rw");
            r1_RandomAccessFile.seek(20);
            r2i = r1_RandomAccessFile.readInt();
            if (r2i <= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY) {
                return null;
            }
            r0_String = "&p3=" + r2i;
            r1_RandomAccessFile.seek(20);
            r1_RandomAccessFile.writeInt(0);
            r1_RandomAccessFile.close();
            return r0_String;
        } catch (Exception e_3) {
            return null;
        }
    }

    public static void if(String r5_String) {
        List r0_List;
        int r1i = j.l;
        if (r1i == 1) {
            r0_List = e;
        } else if (r1i == 2) {
            r0_List = f;
        } else if (r1i == 3) {
            r0_List = g;
        } else {
            return;
        }
        if (r0_List == null) {
        } else {
            j.if(a, "insert2HighPriorityQueue...");
            if (r0_List.size() <= s) {
                r0_List.add(r5_String);
            }
            if (r0_List.size() >= s) {
                a(r1i, false);
            }
            while (r0_List.size() > s) {
                r0_List.remove(0);
            }
        }
    }

    public static String int() {
        String r0_String = null;
        int r1i = 1;
        while (r1i < 5) {
            r0_String = a(r1i);
            if (r0_String != null) {
                return r0_String;
            }
            r1i++;
        }
        j.if(a, "read the old file data...");
        a(g, A);
        if (g.size() > 0) {
            r0_String = (String) g.get(0);
            g.remove(0);
        }
        if (r0_String != null) {
            return r0_String;
        }
        a(g, z);
        if (g.size() > 0) {
            r0_String = (String) g.get(0);
            g.remove(0);
        }
        if (r0_String != null) {
            return r0_String;
        }
        a(g, B);
        if (g.size() <= 0) {
            return r0_String;
        }
        r0_String = (String) g.get(0);
        g.remove(0);
        return r0_String;
    }

    public static void new() {
    }
}
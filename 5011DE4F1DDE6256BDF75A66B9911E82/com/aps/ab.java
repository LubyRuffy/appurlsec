package com.aps;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ab {
    private Context a;
    private int b;
    private int c;
    private int d;

    protected ab(Context r2_Context) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a = r2_Context;
        a((int)AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
    }

    private static int a(int r0i, int r1i) {
        return r0i < r1i ? r0i : r1i;
    }

    protected static ao a(Location r15_Location, ae r16_ae, int r17i, byte r18b) {
        ao r6_ao = new ao();
        if (r17i <= 0 || r17i > 3 || r16_ae == null) {
            return null;
        }
        Object r1_Object;
        long r3j;
        int r1i;
        am r5_am;
        int r7i = (r17i == 1 || r17i == 3) ? 1 : 0;
        if (r17i == 2 || r17i == 3) {
            r2i = 1;
            r1_Object = r16_ae.p().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.c, 0, a(r1_Object.length, r6_ao.c.length));
            r1_Object = r16_ae.f().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.g, 0, a(r1_Object.length, r6_ao.g.length));
            r1_Object = r16_ae.g().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.a, 0, a(r1_Object.length, r6_ao.a.length));
            r1_Object = r16_ae.h().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.b, 0, a(r1_Object.length, r6_ao.b.length));
            r6_ao.d = (short) r16_ae.q();
            r6_ao.e = (short) r16_ae.r();
            r6_ao.f = (byte) r16_ae.s();
            r1_Object = r16_ae.t().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.h, 0, a(r1_Object.length, r6_ao.h.length));
            r3j = ((Long) r16_ae.n().get(0)).longValue();
        } else {
            r2i = 0;
            r1_Object = r16_ae.p().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.c, 0, a(r1_Object.length, r6_ao.c.length));
            r1_Object = r16_ae.f().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.g, 0, a(r1_Object.length, r6_ao.g.length));
            r1_Object = r16_ae.g().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.a, 0, a(r1_Object.length, r6_ao.a.length));
            r1_Object = r16_ae.h().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.b, 0, a(r1_Object.length, r6_ao.b.length));
            r6_ao.d = (short) r16_ae.q();
            r6_ao.e = (short) r16_ae.r();
            r6_ao.f = (byte) r16_ae.s();
            r1_Object = r16_ae.t().getBytes();
            System.arraycopy(r1_Object, 0, r6_ao.h, 0, a(r1_Object.length, r6_ao.h.length));
            r3j = ((Long) r16_ae.n().get(0)).longValue();
        }
        if (r3j > 2147483647L) {
            r3j /= 1000;
        }
        long r8j = System.currentTimeMillis() / 1000;
        if (r15_Location == null || (!r16_ae.e())) {
            r1i = 0;
            if (r1i != 0) {
                return null;
            }
            r5_am = new am();
        } else {
            r1i = 1;
            if (r1i != 0) {
                return null;
            }
        }
        r5_am = new am();
        if (r3j > 0) {
            r5_am.b = (int) r3j;
        } else {
            r5_am.b = (int) r8j;
        }
        an r1_an = new an();
        r1_an.a = (int) (r15_Location.getLongitude() * 1000000.0d);
        r1_an.b = (int) (r15_Location.getLatitude() * 1000000.0d);
        r1_an.c = (int) r15_Location.getAltitude();
        r1_an.d = (int) r15_Location.getAccuracy();
        r1_an.e = (int) r15_Location.getSpeed();
        r1_an.f = (short) ((int) r15_Location.getBearing());
        if (ae.b(r16_ae.x()) && t.b) {
            r1_an.g = (byte) 1;
            r1_an.h = r18b;
            r1_an.i = System.currentTimeMillis();
            r1_an.j = r16_ae.o();
            r5_am.c = r1_an;
            r1i = 1;
            r6_ao.j.add(r5_am);
        } else {
            r1_an.g = (byte) 0;
            r1_an.h = r18b;
            r1_an.i = System.currentTimeMillis();
            r1_an.j = r16_ae.o();
            r5_am.c = r1_an;
            r1i = 1;
            r6_ao.j.add(r5_am);
        }
        int r5i;
        am r7_am;
        v r3_v;
        List r4_List;
        am r4_am;
        bb r7_bb;
        List r8_List;
        int r3i;
        List r2_List;
        bc r9_bc;
        if ((!r16_ae.c()) || r16_ae.i() || r7i == 0) {
            r5i = r1i;
            if (r16_ae.c() && r16_ae.i() && r7i != 0) {
                r7_am = new am();
                if (r3j <= 0) {
                    r7_am.b = (int) r3j;
                } else {
                    r7_am.b = (int) r8j;
                }
                r3_v = new v();
                r4_List = r16_ae.v();
                if (r4_List == null || r4_List.size() < 5) {
                    r7_am.e = r3_v;
                    r5i++;
                    r6_ao.j.add(r7_am);
                    if ((!r16_ae.d()) || r2i == 0) {
                        r6_ao.i = (short) r5i;
                        return r5i >= XListViewHeader.STATE_REFRESHING ? null : r6_ao;
                    } else {
                        r4_am = new am();
                        r7_bb = new bb();
                        r8_List = r16_ae.w();
                        r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                        r7_bb.a = (byte) (r8_List.size() - 1);
                        r3i = 1;
                        while (r3i < r8_List.size()) {
                            r2_List = (List) r8_List.get(r3i);
                            if (r2_List == null || r2_List.size() < 3) {
                                r3i++;
                            } else {
                                r9_bc = new bc();
                                r1_Object = ((String) r2_List.get(0)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                                r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                                r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                                r7_bb.b.add(r9_bc);
                                r3i++;
                            }
                        }
                        r4_am.f = r7_bb;
                        r5i++;
                        r6_ao.j.add(r4_am);
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    }
                } else {
                    r3_v.a = ((Integer) r4_List.get(XListViewFooter.STATE_NOMORE)).intValue();
                    r3_v.b = ((Integer) r4_List.get(XListViewFooter.STATE_NODATA)).intValue();
                    r3_v.c = (short) ((Integer) r4_List.get(0)).intValue();
                    r3_v.d = (short) ((Integer) r4_List.get(1)).intValue();
                    r3_v.e = ((Integer) r4_List.get(XListViewHeader.STATE_REFRESHING)).intValue();
                    r3_v.f = r16_ae.l();
                    r7_am.e = r3_v;
                    r5i++;
                    r6_ao.j.add(r7_am);
                    if (r16_ae.d() || r2i == 0) {
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    } else {
                        r4_am = new am();
                        r7_bb = new bb();
                        r8_List = r16_ae.w();
                        r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                        r7_bb.a = (byte) (r8_List.size() - 1);
                        r3i = 1;
                        while (r3i < r8_List.size()) {
                            r2_List = (List) r8_List.get(r3i);
                            if (r2_List == null || r2_List.size() < 3) {
                                r3i++;
                            } else {
                                r9_bc = new bc();
                                r1_Object = ((String) r2_List.get(0)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                                r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                                r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                                r7_bb.b.add(r9_bc);
                                r3i++;
                            }
                        }
                        r4_am.f = r7_bb;
                        r5i++;
                        r6_ao.j.add(r4_am);
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    }
                }
            } else if (r16_ae.d() || r2i == 0) {
                r6_ao.i = (short) r5i;
                if (r5i >= XListViewHeader.STATE_REFRESHING) {
                }
            } else {
                r4_am = new am();
                r7_bb = new bb();
                r8_List = r16_ae.w();
                r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                r7_bb.a = (byte) (r8_List.size() - 1);
                r3i = 1;
                while (r3i < r8_List.size()) {
                    r2_List = (List) r8_List.get(r3i);
                    if (r2_List == null || r2_List.size() < 3) {
                        r3i++;
                    } else {
                        r9_bc = new bc();
                        r1_Object = ((String) r2_List.get(0)).getBytes();
                        System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                        r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                        r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                        System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                        r7_bb.b.add(r9_bc);
                        r3i++;
                    }
                }
                r4_am.f = r7_bb;
                r5i++;
                r6_ao.j.add(r4_am);
                r6_ao.i = (short) r5i;
                if (r5i >= XListViewHeader.STATE_REFRESHING) {
                }
            }
        } else {
            List r12_List;
            am r10_am = new am();
            if (r3j > 0) {
                r10_am.b = (int) r3j;
            } else {
                r10_am.b = (int) r8j;
            }
            al r11_al = new al();
            List r5_List = r16_ae.u();
            if (r5_List == null || r5_List.size() < 2) {
                r11_al.c = r16_ae.l();
                r12_List = r16_ae.m();
                r11_al.d = (byte) r12_List.size();
                r5i = 0;
            } else {
                r11_al.a = (short) ((Integer) r5_List.get(0)).intValue();
                r11_al.b = ((Integer) r5_List.get(1)).intValue();
                r11_al.c = r16_ae.l();
                r12_List = r16_ae.m();
                r11_al.d = (byte) r12_List.size();
                r5i = 0;
            }
            while (r5i < r12_List.size()) {
                w r13_w = new w();
                r13_w.a = (short) ((NeighboringCellInfo) r12_List.get(r5i)).getLac();
                r13_w.b = ((NeighboringCellInfo) r12_List.get(r5i)).getCid();
                r13_w.c = (byte) ((NeighboringCellInfo) r12_List.get(r5i)).getRssi();
                r11_al.e.add(r13_w);
                r5i++;
            }
            r10_am.d = r11_al;
            r1i = XListViewHeader.STATE_REFRESHING;
            r6_ao.j.add(r10_am);
            r5i = r1i;
            if (r16_ae.c() || r16_ae.i() || r7i != 0) {
                if (r16_ae.d() || r2i == 0) {
                    r6_ao.i = (short) r5i;
                    if (r5i >= XListViewHeader.STATE_REFRESHING) {
                    }
                } else {
                    r4_am = new am();
                    r7_bb = new bb();
                    r8_List = r16_ae.w();
                    r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                    r7_bb.a = (byte) (r8_List.size() - 1);
                    r3i = 1;
                    while (r3i < r8_List.size()) {
                        r2_List = (List) r8_List.get(r3i);
                        if (r2_List == null || r2_List.size() < 3) {
                            r3i++;
                        } else {
                            r9_bc = new bc();
                            r1_Object = ((String) r2_List.get(0)).getBytes();
                            System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                            r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                            r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                            System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                            r7_bb.b.add(r9_bc);
                            r3i++;
                        }
                    }
                    r4_am.f = r7_bb;
                    r5i++;
                    r6_ao.j.add(r4_am);
                    r6_ao.i = (short) r5i;
                    if (r5i >= XListViewHeader.STATE_REFRESHING) {
                    }
                }
            } else {
                r7_am = new am();
                if (r3j <= 0) {
                    r7_am.b = (int) r8j;
                } else {
                    r7_am.b = (int) r3j;
                }
                r3_v = new v();
                r4_List = r16_ae.v();
                if (r4_List == null || r4_List.size() < 5) {
                    r7_am.e = r3_v;
                    r5i++;
                    r6_ao.j.add(r7_am);
                    if (r16_ae.d() || r2i == 0) {
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    } else {
                        r4_am = new am();
                        r7_bb = new bb();
                        r8_List = r16_ae.w();
                        r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                        r7_bb.a = (byte) (r8_List.size() - 1);
                        r3i = 1;
                        while (r3i < r8_List.size()) {
                            r2_List = (List) r8_List.get(r3i);
                            if (r2_List == null || r2_List.size() < 3) {
                                r3i++;
                            } else {
                                r9_bc = new bc();
                                r1_Object = ((String) r2_List.get(0)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                                r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                                r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                                r7_bb.b.add(r9_bc);
                                r3i++;
                            }
                        }
                        r4_am.f = r7_bb;
                        r5i++;
                        r6_ao.j.add(r4_am);
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    }
                } else {
                    r3_v.a = ((Integer) r4_List.get(XListViewFooter.STATE_NOMORE)).intValue();
                    r3_v.b = ((Integer) r4_List.get(XListViewFooter.STATE_NODATA)).intValue();
                    r3_v.c = (short) ((Integer) r4_List.get(0)).intValue();
                    r3_v.d = (short) ((Integer) r4_List.get(1)).intValue();
                    r3_v.e = ((Integer) r4_List.get(XListViewHeader.STATE_REFRESHING)).intValue();
                    r3_v.f = r16_ae.l();
                    r7_am.e = r3_v;
                    r5i++;
                    r6_ao.j.add(r7_am);
                    if (r16_ae.d() || r2i == 0) {
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    } else {
                        r4_am = new am();
                        r7_bb = new bb();
                        r8_List = r16_ae.w();
                        r4_am.b = (int) (((Long) r8_List.get(0)).longValue() / 1000);
                        r7_bb.a = (byte) (r8_List.size() - 1);
                        r3i = 1;
                        while (r3i < r8_List.size()) {
                            r2_List = (List) r8_List.get(r3i);
                            if (r2_List == null || r2_List.size() < 3) {
                                r3i++;
                            } else {
                                r9_bc = new bc();
                                r1_Object = ((String) r2_List.get(0)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.a, 0, a(r1_Object.length, r9_bc.a.length));
                                r9_bc.b = (short) ((Integer) r2_List.get(1)).intValue();
                                r1_Object = ((String) r2_List.get(XListViewHeader.STATE_REFRESHING)).getBytes();
                                System.arraycopy(r1_Object, 0, r9_bc.c, 0, a(r1_Object.length, r9_bc.c.length));
                                r7_bb.b.add(r9_bc);
                                r3i++;
                            }
                        }
                        r4_am.f = r7_bb;
                        r5i++;
                        r6_ao.j.add(r4_am);
                        r6_ao.i = (short) r5i;
                        if (r5i >= XListViewHeader.STATE_REFRESHING) {
                        }
                    }
                }
            }
        }
    }

    protected static File a(Context r4_Context) {
        return new File(Environment.getExternalStorageDirectory().getPath() + new StringBuilder("/Android/data/").append(r4_Context.getPackageName()).append("/files/").toString());
    }

    private static ArrayList a(File[] r4_FileA) {
        ArrayList r1_ArrayList = new ArrayList();
        int r0i = 0;
        while (r0i < r4_FileA.length) {
            if (r4_FileA[r0i].isFile() && r4_FileA[r0i].getName().length() == 10 && TextUtils.isDigitsOnly(r4_FileA[r0i].getName())) {
                r1_ArrayList.add(r4_FileA[r0i]);
                r0i++;
            } else {
                r0i++;
            }
        }
        return r1_ArrayList;
    }

    protected static byte[] a(BitSet r7_BitSet) {
        byte[] r3_byteA = new byte[(r7_BitSet.size() / 8)];
        int r0i = 0;
        while (r0i < r7_BitSet.size()) {
            int r4i = r0i / 8;
            r3_byteA[r4i] = (byte) (((r7_BitSet.get(r0i) ? 1 : 0) << (7 - (r0i % 8))) | r3_byteA[r4i]);
            r0i++;
        }
        return r3_byteA;
    }

    protected static byte[] a(byte[] r3_byteA) {
        try {
            OutputStream r1_OutputStream = new ByteArrayOutputStream();
            GZIPOutputStream r2_GZIPOutputStream = new GZIPOutputStream(r1_OutputStream);
            r2_GZIPOutputStream.write(r3_byteA);
            r2_GZIPOutputStream.finish();
            r2_GZIPOutputStream.close();
            byte[] r0_byteA = r1_OutputStream.toByteArray();
            r1_OutputStream.close();
            return r0_byteA;
        } catch (Exception e) {
            return null;
        }
    }

    protected static byte[] a(byte[] r3_byteA, int r4i) {
        if (r3_byteA == null || r3_byteA.length == 0) {
            return null;
        }
        Object r0_Object;
        int r1i = new String(r3_byteA).indexOf(0);
        int r0i = 1;
        if (r1i > 0) {
            if (r1i + 1 > r4i) {
                r0_Object = new Object[r4i];
                System.arraycopy(r3_byteA, 0, r0_Object, 0, r4i);
                r0_Object[r4i - 1] = false;
            } else {
                r4i = r1i + 1;
            }
        } else {
            r4i = r0i;
        }
        r0_Object = new Object[r4i];
        System.arraycopy(r3_byteA, 0, r0_Object, 0, r4i);
        r0_Object[r4i - 1] = false;
        return r0_Object;
    }

    protected static BitSet b(byte[] r9_byteA) {
        BitSet r7_BitSet = new BitSet(r9_byteA.length << 3);
        int r0i = 0;
        int r2i = 0;
        while (r0i < r9_byteA.length) {
            int r5i = 7;
            while (r5i >= 0) {
                int r6i = r2i + 1;
                r7_BitSet.set(r2i, ((r9_byteA[r0i] & (1 << r5i)) >> r5i) == 1);
                r5i--;
                r2i = r6i;
            }
            r0i++;
        }
        return r7_BitSet;
    }

    private File c(long r8j) {
        boolean r1z = false;
        if (Process.myUid() == 1000) {
            return null;
        }
        File r1_File;
        boolean r2z;
        if ((!c()) || "mounted".equals(Environment.getExternalStorageState())) {
            StatFs r3_StatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (((long) r3_StatFs.getBlockSize()) * ((long) r3_StatFs.getAvailableBlocks()) <= ((long) (this.c / 2))) {
                return null;
            }
            File r2_File;
            File r3_File = new File(a(this.a).getPath() + File.separator + "carrierdata");
            if (r3_File.exists() && r3_File.isDirectory()) {
                r2_File = new File(r3_File.getPath() + File.separator + r8j);
            } else {
                r3_File.mkdirs();
                r2_File = new File(r3_File.getPath() + File.separator + r8j);
            }
            try {
                r1_File = r2_File;
                r2z = r2_File.createNewFile();
            } catch (IOException e) {
                r1_File = r2_File;
                r2z = r1z;
            }
            if (r2z) {
                return null;
            }
        } else {
            r2z = r1z;
            r1_File = null;
            if (r2z) {
                return null;
            }
        }
        return r1_File;
    }

    protected static boolean c() {
        if (VERSION.SDK_INT >= 9) {
            try {
                return ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", null).invoke(null, null)).booleanValue();
            } catch (Exception e) {
            }
        }
        return true;
    }

    private File d(long r5j) {
        boolean r1z = false;
        File r2_File = new File(this.a.getFilesDir().getPath() + File.separator + "carrierdata");
        File r0_File;
        if (r2_File.exists() && r2_File.isDirectory()) {
            r0_File = new File(r2_File.getPath() + File.separator + r5j);
            r1z = r0_File.createNewFile();
            return r1z ? r0_File : null;
        } else {
            r2_File.mkdirs();
            r0_File = new File(r2_File.getPath() + File.separator + r5j);
            try {
                r1z = r0_File.createNewFile();
            } catch (IOException e) {
            }
            if (r1z) {
            }
        }
    }

    protected int a() {
        return this.b;
    }

    protected File a(long r11j) {
        File r0_File;
        File r1_File;
        File[] r0_FileA;
        ArrayList r1_ArrayList;
        File r2_File = null;
        if (Process.myUid() == 1000) {
            r0_File = null;
        } else if ((!c()) || "mounted".equals(Environment.getExternalStorageState())) {
            r1_File = new File(a(this.a).getPath() + File.separator + "carrierdata");
            if (r1_File.exists() && r1_File.isDirectory()) {
                r0_FileA = r1_File.listFiles();
                if (r0_FileA == null || r0_FileA.length <= 0) {
                    r0_File = null;
                } else {
                    r1_ArrayList = a(r0_FileA);
                    if (r1_ArrayList.size() == 1) {
                        if (((File) r1_ArrayList.get(0)).length() < ((long) this.d)) {
                            r0_File = (File) r1_ArrayList.get(0);
                        }
                        r0_File = null;
                    } else {
                        if (r1_ArrayList.size() >= 2) {
                            r0_File = (File) r1_ArrayList.get(0);
                            r1_File = (File) r1_ArrayList.get(1);
                            if (r0_File.getName().compareTo(r1_File.getName()) <= 0) {
                                r0_File = r1_File;
                            }
                        }
                        r0_File = null;
                    }
                }
            } else {
                r0_File = null;
            }
        } else {
            r0_File = null;
        }
        if (r0_File == null) {
            r0_File = c(r11j);
        }
        if (r0_File == null) {
            r1_File = new File(this.a.getFilesDir().getPath() + File.separator + "carrierdata");
            if (r1_File.exists() && r1_File.isDirectory()) {
                r0_FileA = r1_File.listFiles();
                if (r0_FileA == null || r0_FileA.length <= 0) {
                    r0_File = r2_File;
                } else {
                    r1_ArrayList = a(r0_FileA);
                    if (r1_ArrayList.size() == 1) {
                        if (((File) r1_ArrayList.get(0)).length() < ((long) this.d)) {
                            r2_File = (File) r1_ArrayList.get(0);
                        }
                        r0_File = r2_File;
                    } else {
                        if (r1_ArrayList.size() >= 2) {
                            r0_File = (File) r1_ArrayList.get(0);
                            r1_File = (File) r1_ArrayList.get(1);
                            r2_File = r0_File.getName().compareTo(r1_File.getName()) > 0 ? r0_File : r1_File;
                        }
                        r0_File = r2_File;
                    }
                }
            } else {
                r0_File = r2_File;
            }
        }
        return r0_File == null ? d(r11j) : r0_File;
    }

    protected void a(int r3i) {
        this.b = r3i;
        this.c = (this.b << 3) * 1500 + this.b + 4;
        if (this.b == AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY || this.b == 768) {
            this.d = this.c / 100;
        } else {
            if (this.b == 8736) {
                this.d = this.c - 5000;
            }
        }
    }

    protected File b() {
        File r0_File;
        File r1_File;
        File[] r0_FileA;
        ArrayList r1_ArrayList;
        if (Process.myUid() == 1000) {
            r0_File = null;
        } else if ((!c()) || "mounted".equals(Environment.getExternalStorageState())) {
            r0_File = a(this.a);
            if (r0_File != null) {
                r1_File = new File(r0_File.getPath() + File.separator + "carrierdata");
                if (r1_File.exists() && r1_File.isDirectory()) {
                    r0_FileA = r1_File.listFiles();
                    if (r0_FileA == null || r0_FileA.length <= 0) {
                        r1_File = null;
                    } else {
                        r1_ArrayList = a(r0_FileA);
                        if (r1_ArrayList.size() >= 2) {
                            r0_File = (File) r1_ArrayList.get(0);
                            r1_File = (File) r1_ArrayList.get(1);
                            if (r0_File.getName().compareTo(r1_File.getName()) > 0) {
                                r0_File = r1_File;
                            } else {
                                r1_File = r0_File;
                                r0_File = r1_File;
                            }
                        }
                    }
                } else {
                    r1_File = null;
                }
            }
            r1_File = null;
            r0_File = r1_File;
        } else {
            r1_File = null;
            r0_File = r1_File;
        }
        if (r0_File != null) {
            return r0_File;
        }
        r1_File = new File(this.a.getFilesDir().getPath() + File.separator + "carrierdata");
        if (!r1_File.exists() || !r1_File.isDirectory()) {
            return null;
        }
        r0_FileA = r1_File.listFiles();
        if (r0_FileA == null || r0_FileA.length <= 0) {
            return null;
        }
        r1_ArrayList = a(r0_FileA);
        if (r1_ArrayList.size() < 2) {
            return null;
        }
        r0_File = (File) r1_ArrayList.get(0);
        r1_File = (File) r1_ArrayList.get(1);
        return r0_File.getName().compareTo(r1_File.getName()) > 0 ? r1_File : r0_File;
    }

    protected boolean b(long r10j) {
        int r0i;
        File r5_File;
        File[] r0_FileA;
        ArrayList r0_ArrayList;
        int r2i = REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
        if (Process.myUid() == 1000) {
            r0i = 0;
        } else if ((!c()) || "mounted".equals(Environment.getExternalStorageState())) {
            r5_File = new File(a(this.a).getPath() + File.separator + "carrierdata");
            if (r5_File.exists() && r5_File.isDirectory()) {
                r0_FileA = r5_File.listFiles();
                if (r0_FileA == null || r0_FileA.length <= 0) {
                    r0i = 0;
                } else {
                    r0_ArrayList = a(r0_FileA);
                    if (r0_ArrayList.size() == 1) {
                        r0i = (((File) r0_ArrayList.get(0)).length() > 0 ? 1 : (((File) r0_ArrayList.get(0)).length() == 0? 0 : -1)) <= 0 ? 10 : 1;
                    } else {
                        if (r0_ArrayList.size() >= 2) {
                            r0i = 2;
                        }
                        r0i = 0;
                    }
                }
            } else {
                r0i = 0;
            }
        } else {
            r0i = 0;
        }
        if (r0i == 0) {
            r5_File = new File(this.a.getFilesDir().getPath() + File.separator + "carrierdata");
            if (r5_File.exists() && r5_File.isDirectory()) {
                r0_FileA = r5_File.listFiles();
                if (r0_FileA == null || r0_FileA.length <= 0) {
                    r2i = 0;
                } else {
                    r0_ArrayList = a(r0_FileA);
                    if (r0_ArrayList.size() == 1) {
                        if (((File) r0_ArrayList.get(0)).length() <= 0) {
                            if (r2i == 0) {
                                return false;
                            }
                            if (r2i != 1) {
                                return d(r10j) == null;
                            } else if (r2i == 2) {
                            }
                        } else {
                            r2i = 1;
                        }
                    } else {
                        if (r0_ArrayList.size() >= 2) {
                            r2i = 2;
                        }
                        r2i = 0;
                    }
                }
            } else {
                r2i = 0;
            }
            if (r2i == 0) {
                return false;
            }
            if (r2i != 1) {
                return r2i == 2;
            } else if (d(r10j) == null) {
            }
        } else if (r0i == 1) {
            return c(r10j) != null;
        } else {
            if (r0i == 2) {
                return true;
            }
            return false;
        }
    }
}
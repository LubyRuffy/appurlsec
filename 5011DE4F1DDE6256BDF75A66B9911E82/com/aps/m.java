package com.aps;

import android.text.TextUtils;
import com.qq.e.comm.DownloadService;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.zip.CRC32;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Req.java
public class m {
    public byte[] A;
    public String a;
    public short b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    public m() {
        this.a = "1";
        this.b = (short) 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
    }

    private String a(String r3_String, int r4i) {
        String[] r0_StringA = this.x.split("\\*")[r4i].split(",");
        if (r3_String.equals("lac")) {
            return r0_StringA[0];
        }
        if (r3_String.equals("cellid")) {
            return r0_StringA[1];
        }
        if (r3_String.equals("signal")) {
            return r0_StringA[2];
        }
        return null;
    }

    private byte[] a(String r8_String) {
        String[] r0_StringA = r8_String.split(":");
        byte[] r3_byteA;
        int r2i;
        if (r0_StringA == null || r0_StringA.length != 6) {
            String[] r2_StringA = new String[6];
            int r0i = 0;
            while (r0i < r2_StringA.length) {
                r2_StringA[r0i] = "0";
                r0i++;
            }
            r0_StringA = r2_StringA;
            r3_byteA = new byte[6];
            r2i = 0;
            while (r2i < r0_StringA.length) {
                if (r0_StringA[r2i].length() <= 2) {
                    r0_StringA[r2i] = r0_StringA[r2i].substring(0, XListViewHeader.STATE_REFRESHING);
                }
                r3_byteA[r2i] = (byte) Integer.parseInt(r0_StringA[r2i], Base64.URL_SAFE);
                r2i++;
            }
            return r3_byteA;
        } else {
            r3_byteA = new byte[6];
            r2i = 0;
            while (r2i < r0_StringA.length) {
                if (r0_StringA[r2i].length() <= 2) {
                    r3_byteA[r2i] = (byte) Integer.parseInt(r0_StringA[r2i], Base64.URL_SAFE);
                    r2i++;
                } else {
                    r0_StringA[r2i] = r0_StringA[r2i].substring(0, XListViewHeader.STATE_REFRESHING);
                    r3_byteA[r2i] = (byte) Integer.parseInt(r0_StringA[r2i], Base64.URL_SAFE);
                    r2i++;
                }
            }
            return r3_byteA;
        }
    }

    private String b(String r5_String) {
        if (!this.w.contains(r5_String + ">")) {
            return "0";
        }
        int r0i = this.w.indexOf(r5_String + ">");
        return this.w.substring(r0i + r5_String.length() + 1, this.w.indexOf("</" + r5_String));
    }

    private void b() {
        if (this.a == null) {
            this.a = RContactStorage.PRIMARY_KEY;
        }
        if (this.c == null) {
            this.c = RContactStorage.PRIMARY_KEY;
        }
        if (this.d == null) {
            this.d = RContactStorage.PRIMARY_KEY;
        }
        if (this.e == null) {
            this.e = RContactStorage.PRIMARY_KEY;
        }
        if (this.f == null) {
            this.f = RContactStorage.PRIMARY_KEY;
        }
        if (this.g == null) {
            this.g = RContactStorage.PRIMARY_KEY;
        }
        if (this.h == null) {
            this.h = RContactStorage.PRIMARY_KEY;
        }
        if (this.i == null) {
            this.i = RContactStorage.PRIMARY_KEY;
        }
        if (this.j == null) {
            this.j = "0";
        } else if (!this.j.equals("1")) {
            if (!this.j.equals(DownloadService.V2)) {
                this.j = "0";
            }
        }
        if (this.k == null) {
            this.k = "0";
        } else if (!this.k.equals("0")) {
            if (!this.k.equals("1")) {
                this.k = "0";
            }
        }
        if (this.l == null) {
            this.l = RContactStorage.PRIMARY_KEY;
        } else {
            this.l = String.valueOf(Double.valueOf(Double.parseDouble(this.l) * 1200000.0d).intValue());
        }
        if (this.m == null) {
            this.m = RContactStorage.PRIMARY_KEY;
        } else {
            this.m = String.valueOf(Double.valueOf(Double.parseDouble(this.m) * 1000000.0d).intValue());
        }
        if (this.n == null) {
            this.n = RContactStorage.PRIMARY_KEY;
        }
        if (this.o == null) {
            this.o = RContactStorage.PRIMARY_KEY;
        }
        if (this.p == null) {
            this.p = RContactStorage.PRIMARY_KEY;
        }
        if (this.q == null) {
            this.q = RContactStorage.PRIMARY_KEY;
        }
        if (this.r == null) {
            this.r = RContactStorage.PRIMARY_KEY;
        }
        if (this.s == null) {
            this.s = RContactStorage.PRIMARY_KEY;
        }
        if (this.t == null) {
            this.t = RContactStorage.PRIMARY_KEY;
        }
        if (this.u == null) {
            this.u = "0";
        } else if (!this.u.equals("1")) {
            if (!this.u.equals(DownloadService.V2)) {
                this.u = "0";
            }
        }
        if (this.v == null) {
            this.v = "0";
        } else if (!this.v.equals("1")) {
            if (!this.v.equals(DownloadService.V2)) {
                this.v = "0";
            }
        }
        if (this.w == null) {
            this.w = RContactStorage.PRIMARY_KEY;
        }
        if (this.x == null) {
            this.x = RContactStorage.PRIMARY_KEY;
        }
        if (this.y == null) {
            this.y = RContactStorage.PRIMARY_KEY;
        }
        if (this.z == null) {
            this.z = RContactStorage.PRIMARY_KEY;
        }
        if (this.A == null) {
            this.A = new byte[0];
        }
    }

    public byte[] a() {
        Object r2_Object;
        byte r1b = (byte) 0;
        b();
        int r0i = Util.BYTE_OF_KB;
        if (this.A != null) {
            r0i += this.A.length + 1;
        }
        Object r4_Object = new Object[r0i];
        r4_Object[0] = Byte.parseByte(this.a);
        Object r0_Object = n.b(this.b);
        System.arraycopy(r0_Object, 0, r4_Object, 1, r0_Object.length);
        int r2i = r0_Object.length + 1;
        try {
            r0_Object = this.c.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e) {
            o.a(e);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.d.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_2) {
            o.a(e_2);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.o.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_3) {
            o.a(e_3);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.e.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_4) {
            o.a(e_4);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.f.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_5) {
            o.a(e_5);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.g.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_6) {
            o.a(e_6);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.s.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_7) {
            o.a(e_7);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.h.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_8) {
            o.a(e_8);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.p.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r2i += r0_Object.length;
        } catch (Exception e_9) {
            o.a(e_9);
            r4_Object[r2i] = false;
            r2i++;
        }
        try {
            r0_Object = this.q.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r0i = r0_Object.length + r2i;
        } catch (Exception e_10) {
            o.a(e_10);
            r4_Object[r2i] = false;
            r0i = r2i + 1;
        }
        if (TextUtils.isEmpty(this.r)) {
            r4_Object[r0i] = false;
            r2i = r0i + 1;
        } else {
            r2_Object = a(this.r);
            r4_Object[r0i] = (byte) r2_Object.length;
            r0i++;
            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
            r2i = r2_Object.length + r0i;
        }
        try {
            r0_Object = this.t.getBytes("GBK");
            r4_Object[r2i] = (byte) r0_Object.length;
            r2i++;
            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
            r0i = r0_Object.length + r2i;
        } catch (Exception e_11) {
            o.a(e_11);
            r4_Object[r2i] = false;
            r0i = r2i + 1;
        }
        r4_Object[r0i] = Byte.parseByte(this.u);
        r0i++;
        r4_Object[r0i] = Byte.parseByte(this.j);
        r0i++;
        int r5i;
        Object r3_Object;
        int r3i;
        String[] r3_StringA;
        String[] r5_StringA;
        String[] r6_StringA;
        CRC32 r3_CRC32;
        if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
            r2_Object = n.a(Integer.parseInt(this.l));
            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
            r0i += r2_Object.length;
            if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
                r2_Object = n.a(Integer.parseInt(this.m));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
                    r2_Object = n.b(this.n);
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r4_Object[r0i] = Byte.parseByte(this.v);
                    r0i++;
                    if (this.v.equals("1")) {
                        if (!this.v.equals(DownloadService.V2)) {
                            r2_Object = n.b(b("mcc"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b(KEYS.SID));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b("nid"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b("bid"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.a(b("lon"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.a(b("lat"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r2i = r2_Object.length + r0i;
                            r0i = Integer.parseInt(b("signal"));
                            if (r0i <= 127) {
                                r0i = 0;
                            } else if (r0i >= -128) {
                                r0i = 0;
                            }
                            r4_Object[r2i] = (byte) r0i;
                            r0i = r2i + 1;
                            r4_Object[r0i] = false;
                            r0i++;
                        }
                    } else {
                        r2_Object = n.b(b("mcc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("mnc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("lac"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("cellid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0i = Integer.parseInt(b("signal"));
                        if (r0i <= 127) {
                            r0i = 0;
                        } else if (r0i >= -128) {
                            r0i = 0;
                        }
                        r4_Object[r2i] = (byte) r0i;
                        r0i = r2i + 1;
                        if (this.x.length() != 0) {
                            r4_Object[r0i] = false;
                            r0i++;
                        } else {
                            r5i = this.x.split("\\*").length;
                            r4_Object[r0i] = (byte) r5i;
                            r0i++;
                            r2i = 0;
                            while (r2i < r5i) {
                                r3_Object = n.b(a("lac", r2i));
                                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                                r0i += r3_Object.length;
                                r3_Object = n.a(a("cellid", r2i));
                                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                                r3i = r3_Object.length + r0i;
                                r0i = Integer.parseInt(a("signal", r2i));
                                if (r0i <= 127) {
                                    r0i = 0;
                                } else if (r0i >= -128) {
                                    r0i = 0;
                                }
                                r4_Object[r3i] = (byte) r0i;
                                r2i++;
                                r0i = r3i + 1;
                            }
                        }
                    }
                    if (this.z.length() != 0) {
                        r4_Object[r0i] = false;
                        r0i++;
                    } else {
                        r4_Object[r0i] = true;
                        r2i = r0i + 1;
                        try {
                            r3_StringA = this.z.split(",");
                            r0_Object = a(r3_StringA[0]);
                            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                            r2i += r0_Object.length;
                            try {
                                r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                                r4_Object[r2i] = (byte) r0_Object.length;
                                r2i++;
                                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                                r2i += r0_Object.length;
                            } catch (Exception e_12) {
                                o.a(e_12);
                                r4_Object[r2i] = (byte) 0;
                                r2i++;
                            }
                            r0i = Integer.parseInt(r3_StringA[1]);
                            if (r0i <= 127) {
                                r0i = 0;
                            } else if (r0i >= -128) {
                                r0i = 0;
                            }
                            r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                            r0i = r2i + 1;
                        } catch (Exception e_13) {
                            o.a(e_13);
                            r4_Object[r2i] = false;
                            r0i = r2i + 1;
                        }
                    }
                    r5_StringA = this.y.split("\\*");
                    if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                        r4_Object[r0i] = false;
                        r0i++;
                    } else {
                        r4_Object[r0i] = (byte) r5_StringA.length;
                        r0i++;
                        r3i = 0;
                        while (r3i < r5_StringA.length) {
                            r6_StringA = r5_StringA[r3i].split(",");
                            r2_Object = a(r6_StringA[0]);
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r2i = r2_Object.length + r0i;
                            try {
                                r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                                r4_Object[r2i] = (byte) r0_Object.length;
                                r2i++;
                                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                                r0i = r0_Object.length + r2i;
                            } catch (Exception e_14) {
                                o.a(e_14);
                                r4_Object[r2i] = false;
                                r0i = r2i + 1;
                            }
                            r2i = Integer.parseInt(r6_StringA[1]);
                            if (r2i <= 127) {
                                r2i = 0;
                            } else if (r2i >= -128) {
                                r2i = 0;
                            }
                            r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                            r3i++;
                            r0i++;
                        }
                    }
                    r2i = this.A == null ? this.A.length : 0;
                    r3_Object = n.b(r2i);
                    System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    if (r2i <= 0) {
                        System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                        r0i += this.A.length;
                    }
                    r2_Object = new Object[r0i];
                    System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                    r3_CRC32 = new CRC32();
                    r3_CRC32.update(r2_Object);
                    r3_Object = n.a(r3_CRC32.getValue());
                    r4_Object = new Object[(r3_Object.length + r0i)];
                    System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                    System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    return r4_Object;
                } else {
                    r4_Object[r0i] = Byte.parseByte(this.v);
                    r0i++;
                    if (this.v.equals("1")) {
                        if (this.v.equals(DownloadService.V2)) {
                        } else {
                            r2_Object = n.b(b("mcc"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b(KEYS.SID));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b("nid"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.b(b("bid"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.a(b("lon"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r0i += r2_Object.length;
                            r2_Object = n.a(b("lat"));
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r2i = r2_Object.length + r0i;
                            r0i = Integer.parseInt(b("signal"));
                            if (r0i <= 127) {
                                if (r0i >= -128) {
                                    r4_Object[r2i] = (byte) r0i;
                                    r0i = r2i + 1;
                                    r4_Object[r0i] = false;
                                    r0i++;
                                } else {
                                    r0i = 0;
                                }
                            } else {
                                r0i = 0;
                            }
                            r4_Object[r2i] = (byte) r0i;
                            r0i = r2i + 1;
                            r4_Object[r0i] = false;
                            r0i++;
                        }
                    } else {
                        r2_Object = n.b(b("mcc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("mnc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("lac"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("cellid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0i = Integer.parseInt(b("signal"));
                        if (r0i <= 127) {
                            if (r0i >= -128) {
                                r4_Object[r2i] = (byte) r0i;
                                r0i = r2i + 1;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = 0;
                        }
                        r4_Object[r2i] = (byte) r0i;
                        r0i = r2i + 1;
                        if (this.x.length() != 0) {
                            r5i = this.x.split("\\*").length;
                            r4_Object[r0i] = (byte) r5i;
                            r0i++;
                            r2i = 0;
                            while (r2i < r5i) {
                                r3_Object = n.b(a("lac", r2i));
                                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                                r0i += r3_Object.length;
                                r3_Object = n.a(a("cellid", r2i));
                                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                                r3i = r3_Object.length + r0i;
                                r0i = Integer.parseInt(a("signal", r2i));
                                if (r0i <= 127) {
                                    if (r0i >= -128) {
                                        r4_Object[r3i] = (byte) r0i;
                                        r2i++;
                                        r0i = r3i + 1;
                                    } else {
                                        r0i = 0;
                                    }
                                } else {
                                    r0i = 0;
                                }
                                r4_Object[r3i] = (byte) r0i;
                                r2i++;
                                r0i = r3i + 1;
                            }
                        } else {
                            r4_Object[r0i] = false;
                            r0i++;
                        }
                    }
                    if (this.z.length() != 0) {
                        r4_Object[r0i] = true;
                        r2i = r0i + 1;
                        r3_StringA = this.z.split(",");
                        r0_Object = a(r3_StringA[0]);
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r2i += r0_Object.length;
                        r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                        r4_Object[r2i] = (byte) r0_Object.length;
                        r2i++;
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r2i += r0_Object.length;
                        r0i = Integer.parseInt(r3_StringA[1]);
                        if (r0i <= 127) {
                            if (r0i >= -128) {
                                r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                                r0i = r2i + 1;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = 0;
                        }
                        r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                        r0i = r2i + 1;
                    } else {
                        r4_Object[r0i] = false;
                        r0i++;
                    }
                    r5_StringA = this.y.split("\\*");
                    if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                        r4_Object[r0i] = false;
                        r0i++;
                    } else {
                        r4_Object[r0i] = (byte) r5_StringA.length;
                        r0i++;
                        r3i = 0;
                        while (r3i < r5_StringA.length) {
                            r6_StringA = r5_StringA[r3i].split(",");
                            r2_Object = a(r6_StringA[0]);
                            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                            r2i = r2_Object.length + r0i;
                            r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                            r4_Object[r2i] = (byte) r0_Object.length;
                            r2i++;
                            System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                            r0i = r0_Object.length + r2i;
                            r2i = Integer.parseInt(r6_StringA[1]);
                            if (r2i <= 127) {
                                if (r2i >= -128) {
                                    r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                                    r3i++;
                                    r0i++;
                                } else {
                                    r2i = 0;
                                }
                            } else {
                                r2i = 0;
                            }
                            r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                            r3i++;
                            r0i++;
                        }
                    }
                    if (this.A == null) {
                    }
                    r3_Object = n.b(r2i);
                    System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    if (r2i <= 0) {
                        r2_Object = new Object[r0i];
                        System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                        r3_CRC32 = new CRC32();
                        r3_CRC32.update(r2_Object);
                        r3_Object = n.a(r3_CRC32.getValue());
                        r4_Object = new Object[(r3_Object.length + r0i)];
                        System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                        System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                        r0i += r3_Object.length;
                        return r4_Object;
                    } else {
                        System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                        r0i += this.A.length;
                        r2_Object = new Object[r0i];
                        System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                        r3_CRC32 = new CRC32();
                        r3_CRC32.update(r2_Object);
                        r3_Object = n.a(r3_CRC32.getValue());
                        r4_Object = new Object[(r3_Object.length + r0i)];
                        System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                        System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                        r0i += r3_Object.length;
                        return r4_Object;
                    }
                }
            } else if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
                r2_Object = n.b(this.n);
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r4_Object[r0i] = Byte.parseByte(this.v);
                r0i++;
                if (this.v.equals("1")) {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("mnc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("lac"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("cellid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    if (this.x.length() != 0) {
                        r4_Object[r0i] = false;
                        r0i++;
                    } else {
                        r5i = this.x.split("\\*").length;
                        r4_Object[r0i] = (byte) r5i;
                        r0i++;
                        r2i = 0;
                        while (r2i < r5i) {
                            r3_Object = n.b(a("lac", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r0i += r3_Object.length;
                            r3_Object = n.a(a("cellid", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r3i = r3_Object.length + r0i;
                            r0i = Integer.parseInt(a("signal", r2i));
                            if (r0i <= 127) {
                                r0i = 0;
                            } else if (r0i >= -128) {
                                r0i = 0;
                            }
                            r4_Object[r3i] = (byte) r0i;
                            r2i++;
                            r0i = r3i + 1;
                        }
                    }
                } else if (this.v.equals(DownloadService.V2)) {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b(KEYS.SID));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("nid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("bid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lon"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lat"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    r4_Object[r0i] = false;
                    r0i++;
                }
                if (this.z.length() != 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = true;
                    r2i = r0i + 1;
                    r3_StringA = this.z.split(",");
                    r0_Object = a(r3_StringA[0]);
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0i = Integer.parseInt(r3_StringA[1]);
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                    r0i = r2i + 1;
                }
                r5_StringA = this.y.split("\\*");
                if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = (byte) r5_StringA.length;
                    r0i++;
                    r3i = 0;
                    while (r3i < r5_StringA.length) {
                        r6_StringA = r5_StringA[r3i].split(",");
                        r2_Object = a(r6_StringA[0]);
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                        r4_Object[r2i] = (byte) r0_Object.length;
                        r2i++;
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r0i = r0_Object.length + r2i;
                        r2i = Integer.parseInt(r6_StringA[1]);
                        if (r2i <= 127) {
                            r2i = 0;
                        } else if (r2i >= -128) {
                            r2i = 0;
                        }
                        r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                        r3i++;
                        r0i++;
                    }
                }
                if (this.A == null) {
                }
                r3_Object = n.b(r2i);
                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                if (r2i <= 0) {
                    System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                    r0i += this.A.length;
                }
                r2_Object = new Object[r0i];
                System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                r3_CRC32 = new CRC32();
                r3_CRC32.update(r2_Object);
                r3_Object = n.a(r3_CRC32.getValue());
                r4_Object = new Object[(r3_Object.length + r0i)];
                System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                return r4_Object;
            } else {
                r4_Object[r0i] = Byte.parseByte(this.v);
                r0i++;
                if (this.v.equals("1")) {
                    if (this.v.equals(DownloadService.V2)) {
                    } else {
                        r2_Object = n.b(b("mcc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b(KEYS.SID));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("nid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("bid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("lon"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("lat"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0i = Integer.parseInt(b("signal"));
                        if (r0i <= 127) {
                            if (r0i >= -128) {
                                r4_Object[r2i] = (byte) r0i;
                                r0i = r2i + 1;
                                r4_Object[r0i] = false;
                                r0i++;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = 0;
                        }
                        r4_Object[r2i] = (byte) r0i;
                        r0i = r2i + 1;
                        r4_Object[r0i] = false;
                        r0i++;
                    }
                } else {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("mnc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("lac"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("cellid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        if (r0i >= -128) {
                            r4_Object[r2i] = (byte) r0i;
                            r0i = r2i + 1;
                        } else {
                            r0i = 0;
                        }
                    } else {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    if (this.x.length() != 0) {
                        r5i = this.x.split("\\*").length;
                        r4_Object[r0i] = (byte) r5i;
                        r0i++;
                        r2i = 0;
                        while (r2i < r5i) {
                            r3_Object = n.b(a("lac", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r0i += r3_Object.length;
                            r3_Object = n.a(a("cellid", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r3i = r3_Object.length + r0i;
                            r0i = Integer.parseInt(a("signal", r2i));
                            if (r0i <= 127) {
                                if (r0i >= -128) {
                                    r4_Object[r3i] = (byte) r0i;
                                    r2i++;
                                    r0i = r3i + 1;
                                } else {
                                    r0i = 0;
                                }
                            } else {
                                r0i = 0;
                            }
                            r4_Object[r3i] = (byte) r0i;
                            r2i++;
                            r0i = r3i + 1;
                        }
                    } else {
                        r4_Object[r0i] = false;
                        r0i++;
                    }
                }
                if (this.z.length() != 0) {
                    r4_Object[r0i] = true;
                    r2i = r0i + 1;
                    r3_StringA = this.z.split(",");
                    r0_Object = a(r3_StringA[0]);
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0i = Integer.parseInt(r3_StringA[1]);
                    if (r0i <= 127) {
                        if (r0i >= -128) {
                            r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                            r0i = r2i + 1;
                        } else {
                            r0i = 0;
                        }
                    } else {
                        r0i = 0;
                    }
                    r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                    r0i = r2i + 1;
                } else {
                    r4_Object[r0i] = false;
                    r0i++;
                }
                r5_StringA = this.y.split("\\*");
                if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = (byte) r5_StringA.length;
                    r0i++;
                    r3i = 0;
                    while (r3i < r5_StringA.length) {
                        r6_StringA = r5_StringA[r3i].split(",");
                        r2_Object = a(r6_StringA[0]);
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                        r4_Object[r2i] = (byte) r0_Object.length;
                        r2i++;
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r0i = r0_Object.length + r2i;
                        r2i = Integer.parseInt(r6_StringA[1]);
                        if (r2i <= 127) {
                            if (r2i >= -128) {
                                r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                                r3i++;
                                r0i++;
                            } else {
                                r2i = 0;
                            }
                        } else {
                            r2i = 0;
                        }
                        r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                        r3i++;
                        r0i++;
                    }
                }
                if (this.A == null) {
                }
                r3_Object = n.b(r2i);
                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                if (r2i <= 0) {
                    r2_Object = new Object[r0i];
                    System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                    r3_CRC32 = new CRC32();
                    r3_CRC32.update(r2_Object);
                    r3_Object = n.a(r3_CRC32.getValue());
                    r4_Object = new Object[(r3_Object.length + r0i)];
                    System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                    System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    return r4_Object;
                } else {
                    System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                    r0i += this.A.length;
                    r2_Object = new Object[r0i];
                    System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                    r3_CRC32 = new CRC32();
                    r3_CRC32.update(r2_Object);
                    r3_Object = n.a(r3_CRC32.getValue());
                    r4_Object = new Object[(r3_Object.length + r0i)];
                    System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                    System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    return r4_Object;
                }
            }
        } else if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
            r2_Object = n.a(Integer.parseInt(this.m));
            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
            r0i += r2_Object.length;
            if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
                r2_Object = n.b(this.n);
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r4_Object[r0i] = Byte.parseByte(this.v);
                r0i++;
                if (this.v.equals("1")) {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("mnc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("lac"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("cellid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    if (this.x.length() != 0) {
                        r4_Object[r0i] = false;
                        r0i++;
                    } else {
                        r5i = this.x.split("\\*").length;
                        r4_Object[r0i] = (byte) r5i;
                        r0i++;
                        r2i = 0;
                        while (r2i < r5i) {
                            r3_Object = n.b(a("lac", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r0i += r3_Object.length;
                            r3_Object = n.a(a("cellid", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r3i = r3_Object.length + r0i;
                            r0i = Integer.parseInt(a("signal", r2i));
                            if (r0i <= 127) {
                                r0i = 0;
                            } else if (r0i >= -128) {
                                r0i = 0;
                            }
                            r4_Object[r3i] = (byte) r0i;
                            r2i++;
                            r0i = r3i + 1;
                        }
                    }
                } else if (this.v.equals(DownloadService.V2)) {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b(KEYS.SID));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("nid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("bid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lon"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lat"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    r4_Object[r0i] = false;
                    r0i++;
                }
                if (this.z.length() != 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = true;
                    r2i = r0i + 1;
                    r3_StringA = this.z.split(",");
                    r0_Object = a(r3_StringA[0]);
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0i = Integer.parseInt(r3_StringA[1]);
                    if (r0i <= 127) {
                        r0i = 0;
                    } else if (r0i >= -128) {
                        r0i = 0;
                    }
                    r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                    r0i = r2i + 1;
                }
                r5_StringA = this.y.split("\\*");
                if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = (byte) r5_StringA.length;
                    r0i++;
                    r3i = 0;
                    while (r3i < r5_StringA.length) {
                        r6_StringA = r5_StringA[r3i].split(",");
                        r2_Object = a(r6_StringA[0]);
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                        r4_Object[r2i] = (byte) r0_Object.length;
                        r2i++;
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r0i = r0_Object.length + r2i;
                        r2i = Integer.parseInt(r6_StringA[1]);
                        if (r2i <= 127) {
                            r2i = 0;
                        } else if (r2i >= -128) {
                            r2i = 0;
                        }
                        r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                        r3i++;
                        r0i++;
                    }
                }
                if (this.A == null) {
                }
                r3_Object = n.b(r2i);
                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                if (r2i <= 0) {
                    System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                    r0i += this.A.length;
                }
                r2_Object = new Object[r0i];
                System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                r3_CRC32 = new CRC32();
                r3_CRC32.update(r2_Object);
                r3_Object = n.a(r3_CRC32.getValue());
                r4_Object = new Object[(r3_Object.length + r0i)];
                System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                return r4_Object;
            } else {
                r4_Object[r0i] = Byte.parseByte(this.v);
                r0i++;
                if (this.v.equals("1")) {
                    if (this.v.equals(DownloadService.V2)) {
                    } else {
                        r2_Object = n.b(b("mcc"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b(KEYS.SID));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("nid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.b(b("bid"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("lon"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r0i += r2_Object.length;
                        r2_Object = n.a(b("lat"));
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0i = Integer.parseInt(b("signal"));
                        if (r0i <= 127) {
                            if (r0i >= -128) {
                                r4_Object[r2i] = (byte) r0i;
                                r0i = r2i + 1;
                                r4_Object[r0i] = false;
                                r0i++;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = 0;
                        }
                        r4_Object[r2i] = (byte) r0i;
                        r0i = r2i + 1;
                        r4_Object[r0i] = false;
                        r0i++;
                    }
                } else {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("mnc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("lac"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("cellid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        if (r0i >= -128) {
                            r4_Object[r2i] = (byte) r0i;
                            r0i = r2i + 1;
                        } else {
                            r0i = 0;
                        }
                    } else {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    if (this.x.length() != 0) {
                        r5i = this.x.split("\\*").length;
                        r4_Object[r0i] = (byte) r5i;
                        r0i++;
                        r2i = 0;
                        while (r2i < r5i) {
                            r3_Object = n.b(a("lac", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r0i += r3_Object.length;
                            r3_Object = n.a(a("cellid", r2i));
                            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                            r3i = r3_Object.length + r0i;
                            r0i = Integer.parseInt(a("signal", r2i));
                            if (r0i <= 127) {
                                if (r0i >= -128) {
                                    r4_Object[r3i] = (byte) r0i;
                                    r2i++;
                                    r0i = r3i + 1;
                                } else {
                                    r0i = 0;
                                }
                            } else {
                                r0i = 0;
                            }
                            r4_Object[r3i] = (byte) r0i;
                            r2i++;
                            r0i = r3i + 1;
                        }
                    } else {
                        r4_Object[r0i] = false;
                        r0i++;
                    }
                }
                if (this.z.length() != 0) {
                    r4_Object[r0i] = true;
                    r2i = r0i + 1;
                    r3_StringA = this.z.split(",");
                    r0_Object = a(r3_StringA[0]);
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r2i += r0_Object.length;
                    r0i = Integer.parseInt(r3_StringA[1]);
                    if (r0i <= 127) {
                        if (r0i >= -128) {
                            r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                            r0i = r2i + 1;
                        } else {
                            r0i = 0;
                        }
                    } else {
                        r0i = 0;
                    }
                    r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                    r0i = r2i + 1;
                } else {
                    r4_Object[r0i] = false;
                    r0i++;
                }
                r5_StringA = this.y.split("\\*");
                if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r4_Object[r0i] = (byte) r5_StringA.length;
                    r0i++;
                    r3i = 0;
                    while (r3i < r5_StringA.length) {
                        r6_StringA = r5_StringA[r3i].split(",");
                        r2_Object = a(r6_StringA[0]);
                        System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                        r2i = r2_Object.length + r0i;
                        r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                        r4_Object[r2i] = (byte) r0_Object.length;
                        r2i++;
                        System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                        r0i = r0_Object.length + r2i;
                        r2i = Integer.parseInt(r6_StringA[1]);
                        if (r2i <= 127) {
                            if (r2i >= -128) {
                                r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                                r3i++;
                                r0i++;
                            } else {
                                r2i = 0;
                            }
                        } else {
                            r2i = 0;
                        }
                        r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                        r3i++;
                        r0i++;
                    }
                }
                if (this.A == null) {
                }
                r3_Object = n.b(r2i);
                System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                if (r2i <= 0) {
                    r2_Object = new Object[r0i];
                    System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                    r3_CRC32 = new CRC32();
                    r3_CRC32.update(r2_Object);
                    r3_Object = n.a(r3_CRC32.getValue());
                    r4_Object = new Object[(r3_Object.length + r0i)];
                    System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                    System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    return r4_Object;
                } else {
                    System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                    r0i += this.A.length;
                    r2_Object = new Object[r0i];
                    System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                    r3_CRC32 = new CRC32();
                    r3_CRC32.update(r2_Object);
                    r3_Object = n.a(r3_CRC32.getValue());
                    r4_Object = new Object[(r3_Object.length + r0i)];
                    System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                    System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                    r0i += r3_Object.length;
                    return r4_Object;
                }
            }
        } else if (this.j.equals("1") || this.j.equals(DownloadService.V2)) {
            r2_Object = n.b(this.n);
            System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
            r0i += r2_Object.length;
            r4_Object[r0i] = Byte.parseByte(this.v);
            r0i++;
            if (this.v.equals("1")) {
                r2_Object = n.b(b("mcc"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("mnc"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("lac"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.a(b("cellid"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r2i = r2_Object.length + r0i;
                r0i = Integer.parseInt(b("signal"));
                if (r0i <= 127) {
                    r0i = 0;
                } else if (r0i >= -128) {
                    r0i = 0;
                }
                r4_Object[r2i] = (byte) r0i;
                r0i = r2i + 1;
                if (this.x.length() != 0) {
                    r4_Object[r0i] = false;
                    r0i++;
                } else {
                    r5i = this.x.split("\\*").length;
                    r4_Object[r0i] = (byte) r5i;
                    r0i++;
                    r2i = 0;
                    while (r2i < r5i) {
                        r3_Object = n.b(a("lac", r2i));
                        System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                        r0i += r3_Object.length;
                        r3_Object = n.a(a("cellid", r2i));
                        System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                        r3i = r3_Object.length + r0i;
                        r0i = Integer.parseInt(a("signal", r2i));
                        if (r0i <= 127) {
                            r0i = 0;
                        } else if (r0i >= -128) {
                            r0i = 0;
                        }
                        r4_Object[r3i] = (byte) r0i;
                        r2i++;
                        r0i = r3i + 1;
                    }
                }
            } else if (this.v.equals(DownloadService.V2)) {
                r2_Object = n.b(b("mcc"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b(KEYS.SID));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("nid"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("bid"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.a(b("lon"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.a(b("lat"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r2i = r2_Object.length + r0i;
                r0i = Integer.parseInt(b("signal"));
                if (r0i <= 127) {
                    r0i = 0;
                } else if (r0i >= -128) {
                    r0i = 0;
                }
                r4_Object[r2i] = (byte) r0i;
                r0i = r2i + 1;
                r4_Object[r0i] = false;
                r0i++;
            }
            if (this.z.length() != 0) {
                r4_Object[r0i] = false;
                r0i++;
            } else {
                r4_Object[r0i] = true;
                r2i = r0i + 1;
                r3_StringA = this.z.split(",");
                r0_Object = a(r3_StringA[0]);
                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                r2i += r0_Object.length;
                r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                r4_Object[r2i] = (byte) r0_Object.length;
                r2i++;
                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                r2i += r0_Object.length;
                r0i = Integer.parseInt(r3_StringA[1]);
                if (r0i <= 127) {
                    r0i = 0;
                } else if (r0i >= -128) {
                    r0i = 0;
                }
                r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                r0i = r2i + 1;
            }
            r5_StringA = this.y.split("\\*");
            if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                r4_Object[r0i] = false;
                r0i++;
            } else {
                r4_Object[r0i] = (byte) r5_StringA.length;
                r0i++;
                r3i = 0;
                while (r3i < r5_StringA.length) {
                    r6_StringA = r5_StringA[r3i].split(",");
                    r2_Object = a(r6_StringA[0]);
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r0i = r0_Object.length + r2i;
                    r2i = Integer.parseInt(r6_StringA[1]);
                    if (r2i <= 127) {
                        r2i = 0;
                    } else if (r2i >= -128) {
                        r2i = 0;
                    }
                    r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                    r3i++;
                    r0i++;
                }
            }
            if (this.A == null) {
            }
            r3_Object = n.b(r2i);
            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
            r0i += r3_Object.length;
            if (r2i <= 0) {
                System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                r0i += this.A.length;
            }
            r2_Object = new Object[r0i];
            System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
            r3_CRC32 = new CRC32();
            r3_CRC32.update(r2_Object);
            r3_Object = n.a(r3_CRC32.getValue());
            r4_Object = new Object[(r3_Object.length + r0i)];
            System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
            System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
            r0i += r3_Object.length;
            return r4_Object;
        } else {
            r4_Object[r0i] = Byte.parseByte(this.v);
            r0i++;
            if (this.v.equals("1")) {
                if (this.v.equals(DownloadService.V2)) {
                } else {
                    r2_Object = n.b(b("mcc"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b(KEYS.SID));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("nid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.b(b("bid"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lon"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r0i += r2_Object.length;
                    r2_Object = n.a(b("lat"));
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0i = Integer.parseInt(b("signal"));
                    if (r0i <= 127) {
                        if (r0i >= -128) {
                            r4_Object[r2i] = (byte) r0i;
                            r0i = r2i + 1;
                            r4_Object[r0i] = false;
                            r0i++;
                        } else {
                            r0i = 0;
                        }
                    } else {
                        r0i = 0;
                    }
                    r4_Object[r2i] = (byte) r0i;
                    r0i = r2i + 1;
                    r4_Object[r0i] = false;
                    r0i++;
                }
            } else {
                r2_Object = n.b(b("mcc"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("mnc"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.b(b("lac"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r0i += r2_Object.length;
                r2_Object = n.a(b("cellid"));
                System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                r2i = r2_Object.length + r0i;
                r0i = Integer.parseInt(b("signal"));
                if (r0i <= 127) {
                    if (r0i >= -128) {
                        r4_Object[r2i] = (byte) r0i;
                        r0i = r2i + 1;
                    } else {
                        r0i = 0;
                    }
                } else {
                    r0i = 0;
                }
                r4_Object[r2i] = (byte) r0i;
                r0i = r2i + 1;
                if (this.x.length() != 0) {
                    r5i = this.x.split("\\*").length;
                    r4_Object[r0i] = (byte) r5i;
                    r0i++;
                    r2i = 0;
                    while (r2i < r5i) {
                        r3_Object = n.b(a("lac", r2i));
                        System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                        r0i += r3_Object.length;
                        r3_Object = n.a(a("cellid", r2i));
                        System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
                        r3i = r3_Object.length + r0i;
                        r0i = Integer.parseInt(a("signal", r2i));
                        if (r0i <= 127) {
                            if (r0i >= -128) {
                                r4_Object[r3i] = (byte) r0i;
                                r2i++;
                                r0i = r3i + 1;
                            } else {
                                r0i = 0;
                            }
                        } else {
                            r0i = 0;
                        }
                        r4_Object[r3i] = (byte) r0i;
                        r2i++;
                        r0i = r3i + 1;
                    }
                } else {
                    r4_Object[r0i] = false;
                    r0i++;
                }
            }
            if (this.z.length() != 0) {
                r4_Object[r0i] = true;
                r2i = r0i + 1;
                r3_StringA = this.z.split(",");
                r0_Object = a(r3_StringA[0]);
                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                r2i += r0_Object.length;
                r0_Object = r3_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                r4_Object[r2i] = (byte) r0_Object.length;
                r2i++;
                System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                r2i += r0_Object.length;
                r0i = Integer.parseInt(r3_StringA[1]);
                if (r0i <= 127) {
                    if (r0i >= -128) {
                        r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                        r0i = r2i + 1;
                    } else {
                        r0i = 0;
                    }
                } else {
                    r0i = 0;
                }
                r4_Object[r2i] = Byte.parseByte(String.valueOf(r0i));
                r0i = r2i + 1;
            } else {
                r4_Object[r0i] = false;
                r0i++;
            }
            r5_StringA = this.y.split("\\*");
            if (TextUtils.isEmpty(this.y) || r5_StringA.length == 0) {
                r4_Object[r0i] = false;
                r0i++;
            } else {
                r4_Object[r0i] = (byte) r5_StringA.length;
                r0i++;
                r3i = 0;
                while (r3i < r5_StringA.length) {
                    r6_StringA = r5_StringA[r3i].split(",");
                    r2_Object = a(r6_StringA[0]);
                    System.arraycopy(r2_Object, 0, r4_Object, r0i, r2_Object.length);
                    r2i = r2_Object.length + r0i;
                    r0_Object = r6_StringA[XListViewHeader.STATE_REFRESHING].getBytes("GBK");
                    r4_Object[r2i] = (byte) r0_Object.length;
                    r2i++;
                    System.arraycopy(r0_Object, 0, r4_Object, r2i, r0_Object.length);
                    r0i = r0_Object.length + r2i;
                    r2i = Integer.parseInt(r6_StringA[1]);
                    if (r2i <= 127) {
                        if (r2i >= -128) {
                            r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                            r3i++;
                            r0i++;
                        } else {
                            r2i = 0;
                        }
                    } else {
                        r2i = 0;
                    }
                    r4_Object[r0i] = Byte.parseByte(String.valueOf(r2i));
                    r3i++;
                    r0i++;
                }
            }
            if (this.A == null) {
            }
            r3_Object = n.b(r2i);
            System.arraycopy(r3_Object, 0, r4_Object, r0i, r3_Object.length);
            r0i += r3_Object.length;
            if (r2i <= 0) {
                r2_Object = new Object[r0i];
                System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                r3_CRC32 = new CRC32();
                r3_CRC32.update(r2_Object);
                r3_Object = n.a(r3_CRC32.getValue());
                r4_Object = new Object[(r3_Object.length + r0i)];
                System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                return r4_Object;
            } else {
                System.arraycopy(this.A, 0, r4_Object, r0i, this.A.length);
                r0i += this.A.length;
                r2_Object = new Object[r0i];
                System.arraycopy(r4_Object, r1b, r2_Object, r1b, r0i);
                r3_CRC32 = new CRC32();
                r3_CRC32.update(r2_Object);
                r3_Object = n.a(r3_CRC32.getValue());
                r4_Object = new Object[(r3_Object.length + r0i)];
                System.arraycopy(r2_Object, r1b, r4_Object, r1b, r0i);
                System.arraycopy(r3_Object, r1b, r4_Object, r0i, r3_Object.length);
                r0i += r3_Object.length;
                return r4_Object;
            }
        }
    }
}
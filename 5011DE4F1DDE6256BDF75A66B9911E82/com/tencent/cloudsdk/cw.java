package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class cw {
    private cs a;
    private int[] b;
    private int c;
    private int d;
    private List[] e;
    private long f;
    private String g;
    private byte[] h;
    private StringBuilder i;

    public cw(cs r4_cs, String r5_String) throws cy, UnknownHostException, Exception {
        this.b = new int[4];
        this.f = 0;
        this.g = RContactStorage.PRIMARY_KEY;
        this.h = new byte[64];
        this.i = new StringBuilder();
        this.a = r4_cs;
        this.g = r5_String;
        this.e = new List[4];
        d();
        a(this.d);
        e();
    }

    private void a(int r5i) throws UnknownHostException, Exception {
        String r0_String = Integer.toBinaryString(r5i);
        if (r0_String.length() < 4) {
            throw new Exception(new StringBuilder("exception cause [FBS - ").append(r0_String).append("]").toString());
        } else {
            r0_String = r0_String.substring(r0_String.length() - 4);
            if (r0_String.equals("0011")) {
                throw new UnknownHostException(new StringBuilder("Unable to resolve host \"").append(this.g).append("\": No address associated with hostname").toString());
            } else if (!r0_String.equals("0000")) {
                throw new Exception(new StringBuilder("exception cause [RCODE - ").append(r0_String).append("]").toString());
            }
        }
    }

    private void a(long r5j) {
        if (this.f != 0 || r5j <= 0) {
        } else {
            this.f = System.currentTimeMillis() + 1000 * r5j;
        }
    }

    private void d() throws cy {
        this.c = this.a.f();
        this.d = this.a.f();
        int r0i = 0;
        while (r0i < this.b.length) {
            this.b[r0i] = this.a.f();
            r0i++;
        }
    }

    private void e() throws cy {
        int r2i = 0;
        while (r2i < 2) {
            try {
                int r3i = this.b[r2i];
                if (r3i > 0) {
                    this.e[r2i] = new ArrayList(r3i);
                }
                int r0i = 0;
                while (r0i < r3i) {
                    cp r4_cp = new cp();
                    if (r2i == 0) {
                        r4_cp.a = f();
                        r4_cp.c = this.a.f();
                        r4_cp.e = this.a.f();
                        this.e[r2i].add(r4_cp);
                    } else {
                        f();
                        r4_cp.a = this.g;
                        r4_cp.c = this.a.f();
                        r4_cp.e = this.a.f();
                        r4_cp.d = this.a.g();
                        this.a.a(this.a.f());
                        r4_cp.b = this.a.h();
                        if (r4_cp.c == 1) {
                            a(r4_cp.d);
                            this.e[r2i].add(r4_cp);
                        }
                    }
                    r0i++;
                }
                r2i++;
            } catch (cy e) {
                throw e;
            }
        }
    }

    private String f() throws cy {
        int r0i;
        int r3i;
        if (this.i.length() > 0) {
            this.i.delete(0, this.i.length());
            r0i = 0;
            r3i = 0;
        } else {
            r0i = 0;
            r3i = 0;
        }
        while (r3i == 0) {
            int r4i = this.a.e();
            switch ((r4i & 192)) {
                case XListViewHeader.STATE_NORMAL:
                    if (r4i == 0) {
                        r3i = 1;
                    } else {
                        this.a.a(this.h, 0, r4i);
                        this.i.append(cr.a(this.h, r4i));
                        this.i.append(".");
                    }
                    break;
                case 192:
                    r4i = (r4i & -193) << 8 + this.a.e();
                    if (r4i >= this.a.a() - 2) {
                        throw new cy("bad compression");
                    } else {
                        if (r0i == 0) {
                            this.a.c();
                            r0i = 1;
                        }
                        this.a.b(r4i);
                    }
                    break;
            }
            throw new cy("bad label type");
        }
        if (r0i != 0) {
            this.a.d();
        }
        if (this.i.length() > 0) {
            this.i.deleteCharAt(this.i.length() - 1);
        }
        return this.i.toString();
    }

    public InetAddress[] a() {
        if (this.e[1] == null || this.e[1].size() <= 0) {
            return null;
        }
        ArrayList r2_ArrayList = new ArrayList();
        int r1i = 0;
        while (r1i < this.e[1].size()) {
            cp r0_cp = (cp) this.e[1].get(r1i);
            try {
                InetAddress r0_InetAddress = InetAddress.getByAddress(r0_cp.a, r0_cp.b);
                if (r0_InetAddress == null || r0_InetAddress.getHostAddress() == null || r0_InetAddress.getHostAddress().length() <= 0) {
                    r1i++;
                } else {
                    r2_ArrayList.add(r0_InetAddress);
                    r1i++;
                }
            } catch (UnknownHostException e) {
                Throwable r0_Throwable = e;
                WnsClientLog.e("ResponsePacket", r0_Throwable.getMessage(), r0_Throwable);
            }
        }
        return (InetAddress[]) r2_ArrayList.toArray(new InetAddress[r2_ArrayList.size()]);
    }

    public long b() {
        return this.f;
    }

    public int c() {
        return this.c;
    }
}
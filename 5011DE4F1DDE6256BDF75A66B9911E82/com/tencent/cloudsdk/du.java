package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Collections;
import java.util.Map;

// compiled from: SourceFile
public class du {
    private Map a;

    public du() {
        this.a = Collections.synchronizedMap(new cd(64));
    }

    private dv b(String r5_String, String r6_String, int r7i) {
        String r1_String = a(r5_String, r6_String);
        dv r0_dv = (dv) this.a.get(r1_String);
        if (r0_dv != null) {
            return r0_dv;
        }
        r0_dv = new dv();
        r0_dv.a = r5_String;
        r0_dv.b = ep.c(r6_String);
        r0_dv.c = r7i;
        r0_dv.d = System.currentTimeMillis();
        this.a.put(r1_String, r0_dv);
        return r0_dv;
    }

    public String a(String r2_String, String r3_String) {
        return new StringBuilder(r2_String).append(r3_String).toString();
    }

    public void a() {
        this.a.clear();
    }

    public void a(String r4_String, String r5_String, int r6i, int r7i) {
        if (a(r4_String, r5_String, r6i)) {
            dv r0_dv = b(r4_String, r5_String, r6i);
            r0_dv.d = System.currentTimeMillis();
            if (r7i == -1) {
                r0_dv.f++;
            }
            r0_dv.e++;
        }
    }

    public void a(String r5_String, String r6_String, int r7i, int r8i, long r9j, int r11i) {
        if (a(r5_String, r6_String, r7i)) {
            dv r0_dv = b(r5_String, r6_String, r7i);
            r0_dv.d = System.currentTimeMillis();
            if (r8i != 0) {
                r0_dv.u++;
            }
            r0_dv.t++;
            if (r9j <= 0 || r9j > 1024) {
                if (r9j <= 1024 || r9j > 4096) {
                    if (r9j <= 4096 || r9j > 10240) {
                        if (r9j <= 10240 || r9j > 1048576) {
                            if (r8i != 0) {
                                r0_dv.I++;
                            } else {
                                r0_dv.J += r11i;
                            }
                            r0_dv.H++;
                            er.a("TcpStatisticsData", new StringBuilder("\u53d1\u9001\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                        } else {
                            if (r8i != 0) {
                                r0_dv.F++;
                            } else {
                                r0_dv.G += r11i;
                            }
                            r0_dv.E++;
                            er.a("TcpStatisticsData", new StringBuilder("\u53d1\u9001\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                        }
                    } else {
                        if (r8i != 0) {
                            r0_dv.C++;
                        } else {
                            r0_dv.D += r11i;
                        }
                        r0_dv.B++;
                        er.a("TcpStatisticsData", new StringBuilder("\u53d1\u9001\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                    }
                } else {
                    if (r8i != 0) {
                        r0_dv.z++;
                    } else {
                        r0_dv.A += r11i;
                    }
                    r0_dv.y++;
                    er.a("TcpStatisticsData", new StringBuilder("\u53d1\u9001\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                }
            } else {
                if (r8i != 0) {
                    r0_dv.w++;
                } else {
                    r0_dv.x += r11i;
                }
                r0_dv.v++;
                er.a("TcpStatisticsData", new StringBuilder("\u53d1\u9001\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
            }
        }
    }

    boolean a(String r2_String, String r3_String, int r4i) {
        return r2_String != null && !r2_String.equals(RContactStorage.PRIMARY_KEY) && !ep.b(r2_String) && r3_String != null && ep.b(r3_String) && r4i >= 0;
    }

    Map b() {
        return this.a;
    }

    public void b(String r5_String, String r6_String, int r7i, int r8i, long r9j, int r11i) {
        if (a(r5_String, r6_String, r7i)) {
            dv r0_dv = b(r5_String, r6_String, r7i);
            r0_dv.d = System.currentTimeMillis();
            if (r8i != 0) {
                r0_dv.h++;
            }
            r0_dv.g++;
            if (r8i == -1) {
                er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
            } else if (r9j <= 0 || r9j > 1024) {
                if (r9j <= 1024 || r9j > 4096) {
                    if (r9j <= 4096 || r9j > 10240) {
                        if (r9j <= 10240 || r9j > 1048576) {
                            if (r8i == 0) {
                                r0_dv.s += r11i;
                                r0_dv.r += r9j;
                            }
                            r0_dv.q++;
                            er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                        } else {
                            if (r8i == 0) {
                                r0_dv.p += r11i;
                            }
                            r0_dv.o++;
                            er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                        }
                    } else {
                        if (r8i == 0) {
                            r0_dv.n += r11i;
                        }
                        r0_dv.m++;
                        er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                    }
                } else {
                    if (r8i == 0) {
                        r0_dv.l += r11i;
                    }
                    r0_dv.k++;
                    er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
                }
            } else {
                if (r8i == 0) {
                    r0_dv.j += r11i;
                }
                r0_dv.i++;
                er.a("TcpStatisticsData", new StringBuilder("\u63a5\u6536\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_dv).toString());
            }
        }
    }
}
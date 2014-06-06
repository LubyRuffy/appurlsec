package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Collections;
import java.util.Map;

// compiled from: SourceFile
public class dn {
    private Map a;

    public dn() {
        this.a = Collections.synchronizedMap(new cd(64));
    }

    private do_ a(String r5_String, String r6_String, int r7i) {
        String r1_String = a(r5_String, r6_String);
        do_ r0_do_ = (do_) this.a.get(r1_String);
        if (r0_do_ != null) {
            return r0_do_;
        }
        r0_do_ = new do_();
        r0_do_.a = r5_String;
        r0_do_.b = ep.c(r6_String);
        r0_do_.c = r7i;
        r0_do_.d = System.currentTimeMillis();
        this.a.put(r1_String, r0_do_);
        return r0_do_;
    }

    private boolean b(String r2_String, String r3_String, int r4i) {
        return r2_String != null && !r2_String.equals(RContactStorage.PRIMARY_KEY) && !ep.b(r2_String) && r3_String != null && ep.b(r3_String) && r4i != -1;
    }

    public String a(String r2_String, String r3_String) {
        return new StringBuilder(r2_String).append(r3_String).toString();
    }

    public void a() {
        this.a.clear();
    }

    public void a(String r5_String, String r6_String, int r7i, int r8i, long r9j, long r11j) {
        if (b(r5_String, r6_String, r7i)) {
            do_ r0_do_ = a(r5_String, r6_String, r7i);
            r0_do_.d = System.currentTimeMillis();
            if (r8i != 0) {
                r0_do_.f++;
            }
            r0_do_.e++;
            if (r9j <= 0 || r9j > 1024) {
                if (r9j <= 1024 || r9j > 4096) {
                    if (r9j <= 4096 || r9j > 10240) {
                        if (r9j <= 10240 || r9j > 1048576) {
                            if (r8i != 0) {
                                r0_do_.t++;
                            } else {
                                r0_do_.u += r11j;
                            }
                            r0_do_.s++;
                            er.a("HttpStatisticsData", new StringBuilder("\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_do_).toString());
                        } else {
                            if (r8i != 0) {
                                r0_do_.q++;
                            } else {
                                r0_do_.r += r11j;
                            }
                            r0_do_.p++;
                            er.a("HttpStatisticsData", new StringBuilder("\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_do_).toString());
                        }
                    } else {
                        if (r8i != 0) {
                            r0_do_.n++;
                        } else {
                            r0_do_.o += r11j;
                        }
                        r0_do_.m++;
                        er.a("HttpStatisticsData", new StringBuilder("\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_do_).toString());
                    }
                } else {
                    if (r8i != 0) {
                        r0_do_.k++;
                    } else {
                        r0_do_.l += r11j;
                    }
                    r0_do_.j++;
                    er.a("HttpStatisticsData", new StringBuilder("\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_do_).toString());
                }
            } else {
                if (r8i != 0) {
                    r0_do_.h++;
                } else {
                    r0_do_.i += r11j;
                }
                r0_do_.g++;
                er.a("HttpStatisticsData", new StringBuilder("\u586b\u5145\u6570\u636e\uff0c\u6700\u7ec8").append(r5_String).append("\u7684\u6570\u636e\u4e3a\uff1a").append(r0_do_).toString());
            }
        }
    }

    Map b() {
        return this.a;
    }
}
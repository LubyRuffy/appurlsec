package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: SourceFile
class ee implements Runnable {
    final /* synthetic */ dy a;
    private String b;
    private int c;
    private String d;
    private int e;
    private String f;
    private long g;
    private int h;
    private long i;
    private long j;
    private long k;
    private long l;
    private int m;
    private long n;
    private int o;
    private long p;
    private int q;
    private long r;
    private int s;
    private int t;
    private long u;
    private long v;
    private long w;
    private long x;
    private long y;
    private long z;

    public ee(dy r3_dy, int r4i, cn r5_cn, cn r6_cn, String r7_String) {
        this.a = r3_dy;
        this.b = r6_cn.a;
        this.c = r6_cn.c;
        this.d = r5_cn == null ? RContactStorage.PRIMARY_KEY : r5_cn.a;
        if (r3_dy.n) {
            this.e = bv.c().g();
        } else {
            this.e = r5_cn == null ? 0 : r5_cn.c;
        }
        this.f = r7_String;
        this.l = ep.c((String) r3_dy.l.get(this.b));
        this.m = r3_dy.d;
        this.n = ep.c(this.d);
        this.o = r7_String.length() * 4;
        this.t = r4i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r43_this = this;
        r3 = 0;
        r33 = 0;
        r23 = 0;
        r21 = 0;
        r19 = 0;
        r17 = 0;
        r9 = 0;
        r7 = 0;
        r5 = 0;
        r31 = 0;
        r29 = 0;
        r27 = 0;
        r25 = 0;
        r11 = r5;
        r13 = r7;
        r15 = r9;
        r36 = r3;
        r6 = r23;
        r4 = r21;
    L_0x0022:
        r3 = 3;
        r0 = r36;
        if (r0 < r3) goto L_0x0161;
    L_0x0027:
        r3 = r25;
        r5 = r27;
        r7 = r29;
        r9 = r31;
        r11 = r33;
    L_0x0031:
        if (r36 == 0) goto L_0x0053;
    L_0x0033:
        r0 = r36;
        r12 = (long) r0;
        r9 = r9 / r12;
        r0 = r43;
        r0.g = r9;
        r0 = r36;
        r9 = (long) r0;
        r7 = r7 / r9;
        r0 = r43;
        r0.i = r7;
        r0 = r36;
        r7 = (long) r0;
        r5 = r5 / r7;
        r0 = r43;
        r0.j = r5;
        r0 = r36;
        r5 = (long) r0;
        r3 = r3 / r5;
        r0 = r43;
        r0.k = r3;
    L_0x0053:
        r0 = r43;
        r3 = r0.n;
        r5 = 0;
        r3 = (r3 > r5 ? 1 : (r3 == r5? 0 : -1));
        if (r3 != 0) goto L_0x08b4;
    L_0x005d:
        r0 = r43;
        r3 = r0.h;
        r0 = r43;
        r0.q = r3;
        r0 = r43;
        r3 = r0.g;
        r0 = r43;
        r0.p = r3;
        r0 = r43;
        r3 = r0.i;
        r0 = r43;
        r0.u = r3;
        r0 = r43;
        r3 = r0.j;
        r0 = r43;
        r0.w = r3;
        r0 = r43;
        r3 = r0.k;
        r0 = r43;
        r0.y = r3;
    L_0x0085:
        r4 = new com.tencent.cloudsdk.cm;
        r4.<init>();
        r0 = r43;
        r5 = r0.n;
        r4.c = r5;
        r0 = r43;
        r3 = r0.s;
        r4.h = r3;
        r0 = r43;
        r5 = r0.r;
        r4.g = r5;
        r0 = r43;
        r3 = r0.o;
        r4.d = r3;
        r0 = r43;
        r3 = r0.q;
        r4.f = r3;
        r0 = r43;
        r5 = r0.l;
        r4.a = r5;
        r0 = r43;
        r3 = r0.t;
        r4.j = r3;
        r0 = r43;
        r3 = r0.m;
        r4.b = r3;
        r0 = r43;
        r5 = r0.p;
        r4.e = r5;
        r3 = com.tencent.cloudsdk.em.b();
        r4.i = r3;
        r0 = r43;
        r5 = r0.u;
        r4.k = r5;
        r0 = r43;
        r5 = r0.v;
        r4.l = r5;
        r0 = r43;
        r5 = r0.w;
        r4.m = r5;
        r0 = r43;
        r5 = r0.x;
        r4.n = r5;
        r0 = r43;
        r5 = r0.y;
        r4.o = r5;
        r0 = r43;
        r5 = r0.z;
        r4.p = r5;
        r5 = new com.tencent.cloudsdk.co;
        r5.<init>();
        r0 = r43;
        r6 = r0.g;
        r5.b = r6;
        r0 = r43;
        r6 = r0.n;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8? 0 : -1));
        if (r3 != 0) goto L_0x08de;
    L_0x00ff:
        r0 = r43;
        r3 = r0.b;
    L_0x0103:
        r5.c = r3;
        r0 = r43;
        r6 = r0.n;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8? 0 : -1));
        if (r3 != 0) goto L_0x08e4;
    L_0x010f:
        r3 = 2;
    L_0x0110:
        r5.d = r3;
        r5.a = r4;
        r0 = r43;
        r3 = r0.a;
        r3 = r3.l;
        r0 = r43;
        r4 = r0.b;
        r3 = r3.get(r4);
        r3 = (java.lang.String) r3;
        r5.e = r3;
        r0 = r43;
        r3 = r0.b;
        r5.f = r3;
        r5.g = r11;
        r4 = new com.tencent.cloudsdk.cn;
        r4.<init>();
        r3 = r5.c;
        r4.a = r3;
        r0 = r43;
        r6 = r0.n;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8? 0 : -1));
        if (r3 != 0) goto L_0x08e7;
    L_0x0143:
        r3 = 2;
    L_0x0144:
        r4.b = r3;
        r0 = r43;
        r3 = r0.a;
        r3 = r3.e;
        r3.put(r4, r5);
        r0 = r43;
        r3 = r0.a;
        r0 = r43;
        r4 = r0.a;
        r4 = r4.c;
        r3.a(r4);
        return;
    L_0x0161:
        r3 = com.tencent.cloudsdk.dy.a;
        r8 = new java.lang.StringBuilder;
        r9 = ">>>\u6b63\u5728\u6d4b\u901f\u7b2c";
        r8.<init>(r9);
        r9 = r36 + 1;
        r8 = r8.append(r9);
        r9 = "\u8f6e<<<";
        r8 = r8.append(r9);
        r8 = r8.toString();
        com.tencent.cloudsdk.er.a(r3, r8);
        r10 = 0;
        r23 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x097e, IOException -> 0x095c, Exception -> 0x092d, all -> 0x08ea }
        r35 = 0;
        r34 = 0;
        r21 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x0994, IOException -> 0x0964, Exception -> 0x0935, all -> 0x08f3 }
        r9 = com.tencent.cloudsdk.et.b();	 //Catch:{ UnknownHostException -> 0x09a5, IOException -> 0x096a, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r3 = r0.n;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r5 = 0;
        r3 = (r3 > r5 ? 1 : (r3 == r5? 0 : -1));
        if (r3 != 0) goto L_0x0340;
    L_0x019a:
        r4 = new java.net.Socket;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r4.<init>();	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r3 = com.tencent.cloudsdk.dy.a;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r6 = "### \u6e90\u7ad9\u6d4b\u901f\u8fde\u63a5\u8d85\u65f6\u65f6\u95f4:";
        r5.<init>(r6);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5 = r5.append(r9);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5 = r5.toString();	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        com.tencent.cloudsdk.er.a(r3, r5);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = r0;
        r5 = new java.net.InetSocketAddress;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r0 = r43;
        r6 = r0.b;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r0 = r43;
        r7 = r0.c;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5.<init>(r6, r7);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3.connect(r5, r9);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = r0;
        r5 = r9 * 2;
        r3.setSoTimeout(r5);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = r0;
        r5 = 1;
        r3.setTcpNoDelay(r5);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = com.tencent.cloudsdk.dy.a;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r6 = "### \u6e90\u7ad9\u6d4b\u901f\u63a5\u6536\u8d85\u65f6\u65f6\u95f4:";
        r5.<init>(r6);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r6 = r9 * 2;
        r5 = r5.append(r6);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r5 = r5.toString();	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        com.tencent.cloudsdk.er.a(r3, r5);	 //Catch:{ IOException -> 0x09ee, UnknownHostException -> 0x09b4, Exception -> 0x093f }
    L_0x01f2:
        r19 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r17 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        if (r3 == 0) goto L_0x0a19;
    L_0x01fe:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = r0;
        r3 = r3.getOutputStream();	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
    L_0x0206:
        r5 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        if (r5 == 0) goto L_0x0a15;
    L_0x020a:
        r0 = r4;
        r0 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r0;	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r3 = r0;
        r3 = r3.getOutputStream();	 //Catch:{ IOException -> 0x05c0, UnknownHostException -> 0x09b4, Exception -> 0x093f }
        r35 = r3;
    L_0x0214:
        r37 = new java.io.PrintWriter;	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r0 = r37;
        r1 = r35;
        r0.<init>(r1);	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r3 = 0;
    L_0x021e:
        r5 = 4;
        if (r3 < r5) goto L_0x05de;
    L_0x0221:
        r9 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r7 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09d5, IOException -> 0x0975, Exception -> 0x0953, all -> 0x0913 }
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        if (r3 == 0) goto L_0x0a11;
    L_0x022d:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r3 = r0;
        r3 = r3.getInputStream();	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
    L_0x0235:
        r5 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        if (r5 == 0) goto L_0x0a0e;
    L_0x0239:
        r0 = r4;
        r0 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r0;	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r3 = r0;
        r3 = r3.getInputStream();	 //Catch:{ IOException -> 0x05ee, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r13 = r3;
    L_0x0242:
        r5 = new java.io.BufferedReader;	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r3 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r3.<init>(r13);	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r5.<init>(r3);	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r3 = 0;
    L_0x024d:
        r6 = 4;
        if (r3 < r6) goto L_0x0609;
    L_0x0250:
        r5.close();	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r37.close();	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        r35.close();	 //Catch:{ IOException -> 0x0664, UnknownHostException -> 0x05fe, Exception -> 0x0670, all -> 0x076c }
        r13.close();	 //Catch:{ IOException -> 0x0760, UnknownHostException -> 0x05fe, Exception -> 0x0670, all -> 0x076c }
        r11 = java.lang.System.currentTimeMillis();
        r13 = r11 - r23;
        r31 = r31 + r13;
        r13 = r19 - r21;
        r29 = r29 + r13;
        r13 = r9 - r17;
        r27 = r27 + r13;
        r13 = r5 - r7;
        r25 = r25 + r13;
        r3 = r4 instanceof java.net.Socket;
        if (r3 == 0) goto L_0x02c6;
    L_0x0278:
        r3 = com.tencent.cloudsdk.dy.a;
        r13 = new java.lang.StringBuilder;
        r14 = "\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r13.<init>(r14);
        r14 = r19 - r21;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "\u76f4\u8fde\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r14 = r9 - r17;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "\u76f4\u8fde\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r14 = r5 - r7;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "\u76f4\u8fde\u603b\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r14 = r11 - r23;
        r13 = r13.append(r14);
        r13 = r13.toString();
        com.tencent.cloudsdk.er.a(r3, r13);
    L_0x02c6:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;
        if (r3 == 0) goto L_0x0318;
    L_0x02ca:
        r3 = com.tencent.cloudsdk.dy.a;
        r13 = new java.lang.StringBuilder;
        r14 = "OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r13.<init>(r14);
        r14 = r19 - r21;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "OC\u52a0\u901f\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r14 = r9 - r17;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "OC\u52a0\u901f\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r14 = r5 - r7;
        r13 = r13.append(r14);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "OC\u52a0\u901f\u603b\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r11 = r11 - r23;
        r11 = r13.append(r11);
        r11 = r11.toString();
        com.tencent.cloudsdk.er.a(r3, r11);
    L_0x0318:
        if (r4 == 0) goto L_0x032e;
    L_0x031a:
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x088f }
        if (r3 == 0) goto L_0x0325;
    L_0x031e:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x088f }
        r3 = r0;
        r3.close();	 //Catch:{ IOException -> 0x088f }
    L_0x0325:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x088f }
        if (r3 == 0) goto L_0x032e;
    L_0x0329:
        r4 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r4;	 //Catch:{ IOException -> 0x088f }
        r4.close();	 //Catch:{ IOException -> 0x088f }
    L_0x032e:
        r3 = r36 + 1;
        r11 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        java.lang.Thread.sleep(r11);	 //Catch:{ InterruptedException -> 0x089d }
        r11 = r5;
        r13 = r7;
        r15 = r9;
        r36 = r3;
        r6 = r23;
        r4 = r21;
        goto L_0x0022;
    L_0x0340:
        r3 = com.tencent.cloudsdk.dy.a;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r4 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r5 = "### OC\u6d4b\u901f\u8fde\u63a5\u8d85\u65f6\u65f6\u95f4:";
        r4.<init>(r5);	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r4 = r4.append(r9);	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r4 = r4.toString();	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        com.tencent.cloudsdk.er.a(r3, r4);	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r3 = new com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r4 = r0.a;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r4 = r4.c;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r5 = r0.b;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r6 = r0.c;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r7 = r0.d;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r43;
        r8 = r0.e;	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r3.<init>(r4, r5, r6, r7, r8, r9);	 //Catch:{ IOException -> 0x039f, UnknownHostException -> 0x09a5, Exception -> 0x093b, all -> 0x08fa }
        r0 = r3;
        r0 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r0;	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r4 = r0;
        r5 = r9 * 2;
        r4.setSoTimeout(r5);	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r0 = r3;
        r0 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r0;	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r4 = r0;
        r5 = 1;
        r4.setTcpNoDelay(r5);	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r4 = com.tencent.cloudsdk.dy.a;	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r5 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r6 = "### OC\u6d4b\u901f\u63a5\u6536\u8d85\u65f6\u65f6\u95f4:";
        r5.<init>(r6);	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r6 = r9 * 2;
        r5 = r5.append(r6);	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r5 = r5.toString();	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        com.tencent.cloudsdk.er.a(r4, r5);	 //Catch:{ IOException -> 0x09f1, UnknownHostException -> 0x09c2, Exception -> 0x0942, all -> 0x0903 }
        r4 = r3;
        goto L_0x01f2;
    L_0x039f:
        r3 = move-exception;
        r4 = r10;
    L_0x03a1:
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r7 = com.tencent.cloudsdk.dy.a;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r8 = new java.lang.StringBuilder;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r9 = "### ERR \u8d85\u65f6:";
        r8.<init>(r9);	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r9 = r3.getMessage();	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r8 = r8.append(r9);	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r8 = r8.toString();	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        com.tencent.cloudsdk.er.a(r7, r8);	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r7 = r3 instanceof java.net.SocketTimeoutException;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        if (r7 == 0) goto L_0x04c5;
    L_0x03c3:
        r0 = r43;
        r7 = r0.h;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r7 = r7 + 1024;
        r0 = r43;
        r0.h = r7;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
    L_0x03cd:
        throw r3;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
    L_0x03ce:
        r3 = move-exception;
        r7 = r13;
        r9 = r15;
        r13 = r5;
        r15 = r21;
        r5 = r11;
        r11 = r17;
        r17 = r23;
    L_0x03d9:
        r19 = com.tencent.cloudsdk.dy.a;	 //Catch:{ all -> 0x091e }
        r20 = r3.getMessage();	 //Catch:{ all -> 0x091e }
        r0 = r19;
        r1 = r20;
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r0, r1, r3);	 //Catch:{ all -> 0x091e }
        r33 = 3;
        r34 = java.lang.System.currentTimeMillis();
        r19 = r34 - r17;
        r31 = r31 + r19;
        r19 = r13 - r15;
        r23 = r29 + r19;
        r19 = r9 - r11;
        r21 = r27 + r19;
        r19 = r5 - r7;
        r19 = r19 + r25;
        r3 = r4 instanceof java.net.Socket;
        if (r3 == 0) goto L_0x0452;
    L_0x0402:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r26 = r13 - r15;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r9 - r11;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r5 - r7;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u603b\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r34 - r17;
        r25 = r25.append(r26);
        r25 = r25.toString();
        r0 = r25;
        com.tencent.cloudsdk.er.a(r3, r0);
    L_0x0452:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;
        if (r3 == 0) goto L_0x04a3;
    L_0x0456:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r13 = r13 - r15;
        r0 = r25;
        r13 = r0.append(r13);
        r14 = "\n";
        r13 = r13.append(r14);
        r14 = "OC\u52a0\u901f\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r13 = r13.append(r14);
        r9 = r9 - r11;
        r9 = r13.append(r9);
        r10 = "\n";
        r9 = r9.append(r10);
        r10 = "OC\u52a0\u901f\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r9 = r9.append(r10);
        r5 = r5 - r7;
        r5 = r9.append(r5);
        r6 = "\n";
        r5 = r5.append(r6);
        r6 = "OC\u52a0\u901f\u603b\u8017\u65f6\uff1a";
        r5 = r5.append(r6);
        r6 = r34 - r17;
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.tencent.cloudsdk.er.a(r3, r5);
    L_0x04a3:
        if (r4 == 0) goto L_0x0a02;
    L_0x04a5:
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x0840 }
        if (r3 == 0) goto L_0x04b0;
    L_0x04a9:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x0840 }
        r3 = r0;
        r3.close();	 //Catch:{ IOException -> 0x0840 }
    L_0x04b0:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x0840 }
        if (r3 == 0) goto L_0x0a02;
    L_0x04b4:
        r4 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r4;	 //Catch:{ IOException -> 0x0840 }
        r4.close();	 //Catch:{ IOException -> 0x0840 }
        r3 = r19;
        r5 = r21;
        r7 = r23;
        r9 = r31;
        r11 = r33;
        goto L_0x0031;
    L_0x04c5:
        r0 = r43;
        r7 = r0.h;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        r7 = r7 + 2;
        r0 = r43;
        r0.h = r7;	 //Catch:{ UnknownHostException -> 0x03ce, IOException -> 0x04d1, Exception -> 0x094a, all -> 0x0908 }
        goto L_0x03cd;
    L_0x04d1:
        r3 = move-exception;
        r19 = r5;
    L_0x04d4:
        r5 = com.tencent.cloudsdk.dy.a;	 //Catch:{ all -> 0x08ff }
        r6 = r3.getMessage();	 //Catch:{ all -> 0x08ff }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r5, r6, r3);	 //Catch:{ all -> 0x08ff }
        r33 = 3;
        r34 = java.lang.System.currentTimeMillis();
        r5 = r34 - r23;
        r31 = r31 + r5;
        r5 = r19 - r21;
        r9 = r29 + r5;
        r5 = r15 - r17;
        r7 = r27 + r5;
        r5 = r11 - r13;
        r5 = r5 + r25;
        r3 = r4 instanceof java.net.Socket;
        if (r3 == 0) goto L_0x0549;
    L_0x04f9:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r26 = r19 - r21;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r15 - r17;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r11 - r13;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u603b\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r34 - r23;
        r25 = r25.append(r26);
        r25 = r25.toString();
        r0 = r25;
        com.tencent.cloudsdk.er.a(r3, r0);
    L_0x0549:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;
        if (r3 == 0) goto L_0x05a1;
    L_0x054d:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r19 = r19 - r21;
        r0 = r25;
        r1 = r19;
        r19 = r0.append(r1);
        r20 = "\n";
        r19 = r19.append(r20);
        r20 = "OC\u52a0\u901f\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r19 = r19.append(r20);
        r15 = r15 - r17;
        r0 = r19;
        r1 = r15;
        r15 = r0.append(r1);
        r16 = "\n";
        r15 = r15.append(r16);
        r16 = "OC\u52a0\u901f\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r15 = r15.append(r16);
        r11 = r11 - r13;
        r11 = r15.append(r11);
        r12 = "\n";
        r11 = r11.append(r12);
        r12 = "OC\u52a0\u901f\u603b\u8017\u65f6\uff1a";
        r11 = r11.append(r12);
        r12 = r34 - r23;
        r11 = r11.append(r12);
        r11 = r11.toString();
        com.tencent.cloudsdk.er.a(r3, r11);
    L_0x05a1:
        if (r4 == 0) goto L_0x09f9;
    L_0x05a3:
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x0858 }
        if (r3 == 0) goto L_0x05ae;
    L_0x05a7:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x0858 }
        r3 = r0;
        r3.close();	 //Catch:{ IOException -> 0x0858 }
    L_0x05ae:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x0858 }
        if (r3 == 0) goto L_0x09f9;
    L_0x05b2:
        r4 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r4;	 //Catch:{ IOException -> 0x0858 }
        r4.close();	 //Catch:{ IOException -> 0x0858 }
        r3 = r5;
        r11 = r33;
        r5 = r7;
        r7 = r9;
        r9 = r31;
        goto L_0x0031;
    L_0x05c0:
        r3 = move-exception;
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r0 = r43;
        r7 = r0.h;	 //Catch:{ UnknownHostException -> 0x05d0, IOException -> 0x0971, Exception -> 0x094f, all -> 0x090e }
        r7 = r7 + 4;
        r0 = r43;
        r0.h = r7;	 //Catch:{ UnknownHostException -> 0x05d0, IOException -> 0x0971, Exception -> 0x094f, all -> 0x090e }
        throw r3;	 //Catch:{ UnknownHostException -> 0x05d0, IOException -> 0x0971, Exception -> 0x094f, all -> 0x090e }
    L_0x05d0:
        r3 = move-exception;
        r7 = r13;
        r9 = r5;
        r15 = r21;
        r13 = r19;
        r5 = r11;
        r11 = r17;
        r17 = r23;
        goto L_0x03d9;
    L_0x05de:
        r0 = r43;
        r5 = r0.f;	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r0 = r37;
        r0.println(r5);	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r37.flush();	 //Catch:{ UnknownHostException -> 0x09b4, IOException -> 0x096e, Exception -> 0x093f }
        r3 = r3 + 1;
        goto L_0x021e;
    L_0x05ee:
        r3 = move-exception;
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r0 = r43;
        r11 = r0.h;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        r11 = r11 + 8;
        r0 = r43;
        r0.h = r11;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        throw r3;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
    L_0x05fe:
        r3 = move-exception;
        r11 = r17;
        r13 = r19;
        r15 = r21;
        r17 = r23;
        goto L_0x03d9;
    L_0x0609:
        r6 = r5.readLine();	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r0 = r43;
        r14 = r0.f;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r14 = r14.equals(r6);	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        if (r14 != 0) goto L_0x0660;
    L_0x0617:
        r3 = com.tencent.cloudsdk.dy.a;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r5 = "echo result wrong, should be = %s,but is =%s";
        r13 = 2;
        r13 = new java.lang.Object[r13];	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r14 = 0;
        r0 = r43;
        r15 = r0.f;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r13[r14] = r15;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r14 = 1;
        r13[r14] = r6;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r5 = java.lang.String.format(r5, r13);	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r3, r5);	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r3 = new java.io.IOException;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r5 = "echo result wrong, should be = %s,but is =%s";
        r13 = 2;
        r13 = new java.lang.Object[r13];	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r14 = 0;
        r0 = r43;
        r15 = r0.f;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r13[r14] = r15;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r14 = 1;
        r13[r14] = r6;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r5 = java.lang.String.format(r5, r13);	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        r3.<init>(r5);	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
        throw r3;	 //Catch:{ IOException -> 0x064a, UnknownHostException -> 0x09e2, Exception -> 0x0957, all -> 0x0918 }
    L_0x064a:
        r3 = move-exception;
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ UnknownHostException -> 0x09e2, IOException -> 0x0979, Exception -> 0x0957, all -> 0x0918 }
        r0 = r43;
        r11 = r0.h;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        r11 = r11 + 8;
        r0 = r43;
        r0.h = r11;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        throw r3;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
    L_0x065a:
        r3 = move-exception;
        r11 = r5;
        r13 = r7;
        r15 = r9;
        goto L_0x04d4;
    L_0x0660:
        r3 = r3 + 1;
        goto L_0x024d;
    L_0x0664:
        r3 = move-exception;
        r0 = r43;
        r11 = r0.h;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        r11 = r11 + 64;
        r0 = r43;
        r0.h = r11;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        throw r3;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
    L_0x0670:
        r3 = move-exception;
        r11 = r5;
        r13 = r7;
        r15 = r9;
    L_0x0674:
        r5 = com.tencent.cloudsdk.dy.a;	 //Catch:{ all -> 0x08ff }
        r6 = r3.getMessage();	 //Catch:{ all -> 0x08ff }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r5, r6, r3);	 //Catch:{ all -> 0x08ff }
        r33 = 3;
        r34 = java.lang.System.currentTimeMillis();
        r5 = r34 - r23;
        r31 = r31 + r5;
        r5 = r19 - r21;
        r9 = r29 + r5;
        r5 = r15 - r17;
        r7 = r27 + r5;
        r5 = r11 - r13;
        r5 = r5 + r25;
        r3 = r4 instanceof java.net.Socket;
        if (r3 == 0) goto L_0x06e9;
    L_0x0699:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r26 = r19 - r21;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r15 - r17;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r11 - r13;
        r25 = r25.append(r26);
        r26 = "\n";
        r25 = r25.append(r26);
        r26 = "\u76f4\u8fde\u603b\u8017\u65f6\uff1a";
        r25 = r25.append(r26);
        r26 = r34 - r23;
        r25 = r25.append(r26);
        r25 = r25.toString();
        r0 = r25;
        com.tencent.cloudsdk.er.a(r3, r0);
    L_0x06e9:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;
        if (r3 == 0) goto L_0x0741;
    L_0x06ed:
        r3 = com.tencent.cloudsdk.dy.a;
        r25 = new java.lang.StringBuilder;
        r26 = "OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r25.<init>(r26);
        r19 = r19 - r21;
        r0 = r25;
        r1 = r19;
        r19 = r0.append(r1);
        r20 = "\n";
        r19 = r19.append(r20);
        r20 = "OC\u52a0\u901f\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r19 = r19.append(r20);
        r15 = r15 - r17;
        r0 = r19;
        r1 = r15;
        r15 = r0.append(r1);
        r16 = "\n";
        r15 = r15.append(r16);
        r16 = "OC\u52a0\u901f\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r15 = r15.append(r16);
        r11 = r11 - r13;
        r11 = r15.append(r11);
        r12 = "\n";
        r11 = r11.append(r12);
        r12 = "OC\u52a0\u901f\u603b\u8017\u65f6\uff1a";
        r11 = r11.append(r12);
        r12 = r34 - r23;
        r11 = r11.append(r12);
        r11 = r11.toString();
        com.tencent.cloudsdk.er.a(r3, r11);
    L_0x0741:
        if (r4 == 0) goto L_0x09f9;
    L_0x0743:
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x086d }
        if (r3 == 0) goto L_0x074e;
    L_0x0747:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x086d }
        r3 = r0;
        r3.close();	 //Catch:{ IOException -> 0x086d }
    L_0x074e:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x086d }
        if (r3 == 0) goto L_0x09f9;
    L_0x0752:
        r4 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r4;	 //Catch:{ IOException -> 0x086d }
        r4.close();	 //Catch:{ IOException -> 0x086d }
        r3 = r5;
        r11 = r33;
        r5 = r7;
        r7 = r9;
        r9 = r31;
        goto L_0x0031;
    L_0x0760:
        r3 = move-exception;
        r0 = r43;
        r11 = r0.h;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        r11 = r11 + 128;
        r0 = r43;
        r0.h = r11;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
        throw r3;	 //Catch:{ UnknownHostException -> 0x05fe, IOException -> 0x065a, Exception -> 0x0670, all -> 0x076c }
    L_0x076c:
        r3 = move-exception;
        r11 = r5;
        r13 = r7;
        r15 = r9;
        r5 = r3;
    L_0x0771:
        r6 = java.lang.System.currentTimeMillis();
        r8 = r6 - r23;
        r8 = r8 + r31;
        r8 = r19 - r21;
        r8 = r8 + r29;
        r8 = r15 - r17;
        r8 = r8 + r27;
        r8 = r11 - r13;
        r8 = r8 + r25;
        r3 = r4 instanceof java.net.Socket;
        if (r3 == 0) goto L_0x07d7;
    L_0x0789:
        r3 = com.tencent.cloudsdk.dy.a;
        r8 = new java.lang.StringBuilder;
        r9 = "\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r8.<init>(r9);
        r9 = r19 - r21;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "\u76f4\u8fde\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r9 = r15 - r17;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "\u76f4\u8fde\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r9 = r11 - r13;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "\u76f4\u8fde\u603b\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r9 = r6 - r23;
        r8 = r8.append(r9);
        r8 = r8.toString();
        com.tencent.cloudsdk.er.a(r3, r8);
    L_0x07d7:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;
        if (r3 == 0) goto L_0x0829;
    L_0x07db:
        r3 = com.tencent.cloudsdk.dy.a;
        r8 = new java.lang.StringBuilder;
        r9 = "OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u8017\u65f6\uff1a";
        r8.<init>(r9);
        r9 = r19 - r21;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "OC\u52a0\u901f\u53d1\u9001\u6570\u636e\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r9 = r15 - r17;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "OC\u52a0\u901f\u63a5\u6536\u6570\u636e\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r9 = r11 - r13;
        r8 = r8.append(r9);
        r9 = "\n";
        r8 = r8.append(r9);
        r9 = "OC\u52a0\u901f\u603b\u8017\u65f6\uff1a";
        r8 = r8.append(r9);
        r6 = r6 - r23;
        r6 = r8.append(r6);
        r6 = r6.toString();
        com.tencent.cloudsdk.er.a(r3, r6);
    L_0x0829:
        if (r4 == 0) goto L_0x083f;
    L_0x082b:
        r3 = r4 instanceof java.net.Socket;	 //Catch:{ IOException -> 0x0882 }
        if (r3 == 0) goto L_0x0836;
    L_0x082f:
        r0 = r4;
        r0 = (java.net.Socket) r0;	 //Catch:{ IOException -> 0x0882 }
        r3 = r0;
        r3.close();	 //Catch:{ IOException -> 0x0882 }
    L_0x0836:
        r3 = r4 instanceof com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ IOException -> 0x0882 }
        if (r3 == 0) goto L_0x083f;
    L_0x083a:
        r4 = (com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket) r4;	 //Catch:{ IOException -> 0x0882 }
        r4.close();	 //Catch:{ IOException -> 0x0882 }
    L_0x083f:
        throw r5;
    L_0x0840:
        r3 = move-exception;
        r4 = com.tencent.cloudsdk.dy.a;
        r5 = r3.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r5, r3);
        r3 = r19;
        r5 = r21;
        r7 = r23;
        r9 = r31;
        r11 = r33;
        goto L_0x0031;
    L_0x0858:
        r3 = move-exception;
        r4 = com.tencent.cloudsdk.dy.a;
        r11 = r3.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r11, r3);
        r3 = r5;
        r11 = r33;
        r5 = r7;
        r7 = r9;
        r9 = r31;
        goto L_0x0031;
    L_0x086d:
        r3 = move-exception;
        r4 = com.tencent.cloudsdk.dy.a;
        r11 = r3.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r11, r3);
        r3 = r5;
        r11 = r33;
        r5 = r7;
        r7 = r9;
        r9 = r31;
        goto L_0x0031;
    L_0x0882:
        r3 = move-exception;
        r4 = com.tencent.cloudsdk.dy.a;
        r6 = r3.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r6, r3);
        goto L_0x083f;
    L_0x088f:
        r3 = move-exception;
        r4 = com.tencent.cloudsdk.dy.a;
        r11 = r3.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r4, r11, r3);
        goto L_0x032e;
    L_0x089d:
        r4 = move-exception;
        r11 = com.tencent.cloudsdk.dy.a;
        r12 = r4.getMessage();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r11, r12, r4);
        r11 = r5;
        r13 = r7;
        r15 = r9;
        r36 = r3;
        r6 = r23;
        r4 = r21;
        goto L_0x0022;
    L_0x08b4:
        r0 = r43;
        r3 = r0.h;
        r0 = r43;
        r0.s = r3;
        r0 = r43;
        r3 = r0.g;
        r0 = r43;
        r0.r = r3;
        r0 = r43;
        r3 = r0.i;
        r0 = r43;
        r0.v = r3;
        r0 = r43;
        r3 = r0.j;
        r0 = r43;
        r0.x = r3;
        r0 = r43;
        r3 = r0.k;
        r0 = r43;
        r0.z = r3;
        goto L_0x0085;
    L_0x08de:
        r0 = r43;
        r3 = r0.d;
        goto L_0x0103;
    L_0x08e4:
        r3 = 0;
        goto L_0x0110;
    L_0x08e7:
        r3 = 0;
        goto L_0x0144;
    L_0x08ea:
        r3 = move-exception;
        r21 = r4;
        r23 = r6;
        r5 = r3;
        r4 = r10;
        goto L_0x0771;
    L_0x08f3:
        r3 = move-exception;
        r21 = r4;
        r4 = r10;
        r5 = r3;
        goto L_0x0771;
    L_0x08fa:
        r3 = move-exception;
        r5 = r3;
        r4 = r10;
        goto L_0x0771;
    L_0x08ff:
        r3 = move-exception;
        r5 = r3;
        goto L_0x0771;
    L_0x0903:
        r4 = move-exception;
        r5 = r4;
        r4 = r3;
        goto L_0x0771;
    L_0x0908:
        r3 = move-exception;
        r19 = r5;
        r5 = r3;
        goto L_0x0771;
    L_0x090e:
        r3 = move-exception;
        r15 = r5;
        r5 = r3;
        goto L_0x0771;
    L_0x0913:
        r3 = move-exception;
        r5 = r3;
        r15 = r9;
        goto L_0x0771;
    L_0x0918:
        r3 = move-exception;
        r5 = r3;
        r13 = r7;
        r15 = r9;
        goto L_0x0771;
    L_0x091e:
        r3 = move-exception;
        r19 = r13;
        r21 = r15;
        r23 = r17;
        r15 = r9;
        r17 = r11;
        r13 = r7;
        r11 = r5;
        r5 = r3;
        goto L_0x0771;
    L_0x092d:
        r3 = move-exception;
        r21 = r4;
        r23 = r6;
        r4 = r10;
        goto L_0x0674;
    L_0x0935:
        r3 = move-exception;
        r21 = r4;
        r4 = r10;
        goto L_0x0674;
    L_0x093b:
        r3 = move-exception;
        r4 = r10;
        goto L_0x0674;
    L_0x093f:
        r3 = move-exception;
        goto L_0x0674;
    L_0x0942:
        r4 = move-exception;
        r38 = r4;
        r4 = r3;
        r3 = r38;
        goto L_0x0674;
    L_0x094a:
        r3 = move-exception;
        r19 = r5;
        goto L_0x0674;
    L_0x094f:
        r3 = move-exception;
        r15 = r5;
        goto L_0x0674;
    L_0x0953:
        r3 = move-exception;
        r15 = r9;
        goto L_0x0674;
    L_0x0957:
        r3 = move-exception;
        r13 = r7;
        r15 = r9;
        goto L_0x0674;
    L_0x095c:
        r3 = move-exception;
        r21 = r4;
        r23 = r6;
        r4 = r10;
        goto L_0x04d4;
    L_0x0964:
        r3 = move-exception;
        r21 = r4;
        r4 = r10;
        goto L_0x04d4;
    L_0x096a:
        r3 = move-exception;
        r4 = r10;
        goto L_0x04d4;
    L_0x096e:
        r3 = move-exception;
        goto L_0x04d4;
    L_0x0971:
        r3 = move-exception;
        r15 = r5;
        goto L_0x04d4;
    L_0x0975:
        r3 = move-exception;
        r15 = r9;
        goto L_0x04d4;
    L_0x0979:
        r3 = move-exception;
        r13 = r7;
        r15 = r9;
        goto L_0x04d4;
    L_0x097e:
        r3 = move-exception;
        r38 = r10;
        r9 = r15;
        r15 = r4;
        r4 = r38;
        r39 = r13;
        r13 = r19;
        r41 = r17;
        r17 = r6;
        r7 = r39;
        r5 = r11;
        r11 = r41;
        goto L_0x03d9;
    L_0x0994:
        r3 = move-exception;
        r7 = r13;
        r13 = r19;
        r38 = r10;
        r9 = r15;
        r15 = r4;
        r5 = r11;
        r4 = r38;
        r11 = r17;
        r17 = r23;
        goto L_0x03d9;
    L_0x09a5:
        r3 = move-exception;
        r4 = r10;
        r5 = r11;
        r7 = r13;
        r9 = r15;
        r11 = r17;
        r13 = r19;
        r15 = r21;
        r17 = r23;
        goto L_0x03d9;
    L_0x09b4:
        r3 = move-exception;
        r5 = r11;
        r7 = r13;
        r9 = r15;
        r11 = r17;
        r13 = r19;
        r15 = r21;
        r17 = r23;
        goto L_0x03d9;
    L_0x09c2:
        r4 = move-exception;
        r5 = r11;
        r7 = r13;
        r9 = r15;
        r11 = r17;
        r13 = r19;
        r15 = r21;
        r17 = r23;
        r38 = r3;
        r3 = r4;
        r4 = r38;
        goto L_0x03d9;
    L_0x09d5:
        r3 = move-exception;
        r5 = r11;
        r7 = r13;
        r15 = r21;
        r13 = r19;
        r11 = r17;
        r17 = r23;
        goto L_0x03d9;
    L_0x09e2:
        r3 = move-exception;
        r5 = r11;
        r13 = r19;
        r15 = r21;
        r11 = r17;
        r17 = r23;
        goto L_0x03d9;
    L_0x09ee:
        r3 = move-exception;
        goto L_0x03a1;
    L_0x09f1:
        r4 = move-exception;
        r38 = r4;
        r4 = r3;
        r3 = r38;
        goto L_0x03a1;
    L_0x09f9:
        r3 = r5;
        r11 = r33;
        r5 = r7;
        r7 = r9;
        r9 = r31;
        goto L_0x0031;
    L_0x0a02:
        r3 = r19;
        r5 = r21;
        r7 = r23;
        r9 = r31;
        r11 = r33;
        goto L_0x0031;
    L_0x0a0e:
        r13 = r3;
        goto L_0x0242;
    L_0x0a11:
        r3 = r34;
        goto L_0x0235;
    L_0x0a15:
        r35 = r3;
        goto L_0x0214;
    L_0x0a19:
        r3 = r35;
        goto L_0x0206;
        */

    }
}
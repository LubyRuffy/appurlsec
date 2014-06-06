package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;

// compiled from: SourceFile
final class o extends k {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    byte a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    String b;
    long c;
    String d;
    int e;
    int f;
    String g;
    int h;
    int i;
    String j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    private long r;
    private String s;
    private int t;
    private int u;
    private String v;
    private int w;
    private int x;
    private String y;
    private int z;

    o() {
    }

    o(DataInput r1_DataInput) {
        c(r1_DataInput);
    }

    private void c(DataInput r3_DataInput) {
        this.a = r3_DataInput.readByte();
        this.b = r3_DataInput.readUTF();
        this.c = r3_DataInput.readLong();
        this.r = r3_DataInput.readLong();
        this.d = r3_DataInput.readUTF();
        this.e = r3_DataInput.readUnsignedShort();
        this.f = r3_DataInput.readInt();
        this.g = r3_DataInput.readUTF();
        this.h = r3_DataInput.readUnsignedShort();
        this.i = r3_DataInput.readInt();
        this.j = r3_DataInput.readUTF();
        this.k = r3_DataInput.readUnsignedShort();
        this.l = r3_DataInput.readInt();
    }

    final void a(DataInput r2_DataInput) {
        this.s = r2_DataInput.readUTF();
        this.t = r2_DataInput.readUnsignedShort();
        this.u = r2_DataInput.readInt();
        this.v = r2_DataInput.readUTF();
        this.w = r2_DataInput.readUnsignedShort();
        this.x = r2_DataInput.readInt();
        this.y = r2_DataInput.readUTF();
        this.z = r2_DataInput.readUnsignedShort();
        this.A = r2_DataInput.readInt();
        this.B = r2_DataInput.readInt();
        this.C = r2_DataInput.readInt();
        this.m = r2_DataInput.readInt();
        this.n = r2_DataInput.readInt();
        this.o = r2_DataInput.readInt();
        this.p = r2_DataInput.readInt();
        this.D = r2_DataInput.readInt();
        this.E = r2_DataInput.readInt();
        this.F = r2_DataInput.readInt();
        this.G = r2_DataInput.readInt();
        this.H = r2_DataInput.readInt();
        this.I = r2_DataInput.readInt();
        this.J = r2_DataInput.readInt();
        this.K = r2_DataInput.readInt();
        this.q = r2_DataInput.readInt();
        this.L = r2_DataInput.readInt();
        this.M = r2_DataInput.readInt();
        this.N = r2_DataInput.readInt();
        this.O = r2_DataInput.readInt();
        this.P = r2_DataInput.readInt();
        this.Q = r2_DataInput.readInt();
        this.R = r2_DataInput.readInt();
        this.S = r2_DataInput.readInt();
        this.T = r2_DataInput.readInt();
        this.U = r2_DataInput.readInt();
        this.V = r2_DataInput.readInt();
        this.W = r2_DataInput.readInt();
        this.X = r2_DataInput.readInt();
        this.Y = r2_DataInput.readInt();
        this.Z = r2_DataInput.readInt();
        this.aa = r2_DataInput.readInt();
        this.ab = r2_DataInput.readInt();
        this.ac = r2_DataInput.readInt();
        this.ad = r2_DataInput.readInt();
        this.ae = r2_DataInput.readInt();
        this.af = r2_DataInput.readInt();
        this.ag = true;
    }

    final void a(DataOutput r3_DataOutput) {
        r3_DataOutput.writeByte(this.a);
        r3_DataOutput.writeUTF(this.b);
        r3_DataOutput.writeLong(this.c);
        r3_DataOutput.writeLong(this.r);
        r3_DataOutput.writeUTF(this.d);
        r3_DataOutput.writeShort(this.e);
        r3_DataOutput.writeInt(this.f);
        r3_DataOutput.writeUTF(this.g);
        r3_DataOutput.writeShort(this.h);
        r3_DataOutput.writeInt(this.i);
        r3_DataOutput.writeUTF(this.j);
        r3_DataOutput.writeShort(this.k);
        r3_DataOutput.writeInt(this.l);
        r3_DataOutput.writeBoolean(this.ag);
        if (this.ag) {
            r3_DataOutput.writeUTF(this.s);
            r3_DataOutput.writeShort(this.t);
            r3_DataOutput.writeInt(this.u);
            r3_DataOutput.writeUTF(this.v);
            r3_DataOutput.writeShort(this.w);
            r3_DataOutput.writeInt(this.x);
            r3_DataOutput.writeUTF(this.y);
            r3_DataOutput.writeShort(this.z);
            r3_DataOutput.writeInt(this.A);
            r3_DataOutput.writeInt(this.B);
            r3_DataOutput.writeInt(this.C);
            r3_DataOutput.writeInt(this.m);
            r3_DataOutput.writeInt(this.n);
            r3_DataOutput.writeInt(this.o);
            r3_DataOutput.writeInt(this.p);
            r3_DataOutput.writeInt(this.D);
            r3_DataOutput.writeInt(this.E);
            r3_DataOutput.writeInt(this.F);
            r3_DataOutput.writeInt(this.G);
            r3_DataOutput.writeInt(this.H);
            r3_DataOutput.writeInt(this.I);
            r3_DataOutput.writeInt(this.J);
            r3_DataOutput.writeInt(this.K);
            r3_DataOutput.writeInt(this.q);
            r3_DataOutput.writeInt(this.L);
            r3_DataOutput.writeInt(this.M);
            r3_DataOutput.writeInt(this.N);
            r3_DataOutput.writeInt(this.O);
            r3_DataOutput.writeInt(this.P);
            r3_DataOutput.writeInt(this.Q);
            r3_DataOutput.writeInt(this.R);
            r3_DataOutput.writeInt(this.S);
            r3_DataOutput.writeInt(this.T);
            r3_DataOutput.writeInt(this.U);
            r3_DataOutput.writeInt(this.V);
            r3_DataOutput.writeInt(this.W);
            r3_DataOutput.writeInt(this.X);
            r3_DataOutput.writeInt(this.Y);
            r3_DataOutput.writeInt(this.Z);
            r3_DataOutput.writeInt(this.aa);
            r3_DataOutput.writeInt(this.ab);
            r3_DataOutput.writeInt(this.ac);
            r3_DataOutput.writeInt(this.ad);
            r3_DataOutput.writeInt(this.ae);
            r3_DataOutput.writeInt(this.af);
        }
    }

    final void b(DataInput r2_DataInput) {
        c(r2_DataInput);
        this.ag = r2_DataInput.readBoolean();
        if (this.ag) {
            a(r2_DataInput);
        }
    }
}
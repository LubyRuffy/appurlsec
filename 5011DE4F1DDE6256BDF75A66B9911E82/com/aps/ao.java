package com.aps;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

final class ao implements Serializable {
    protected byte[] a;
    protected byte[] b;
    protected byte[] c;
    protected short d;
    protected short e;
    protected byte f;
    protected byte[] g;
    protected byte[] h;
    protected short i;
    protected ArrayList j;
    private byte k;
    private short l;

    ao() {
        this.k = (byte) 4;
        this.l = (short) 0;
        this.a = new byte[16];
        this.b = new byte[16];
        this.c = new byte[16];
        this.d = (short) 0;
        this.e = (short) 0;
        this.f = (byte) 0;
        this.g = new byte[16];
        this.h = new byte[32];
        this.i = (short) 0;
        this.j = new ArrayList();
    }

    private Boolean a(DataOutputStream r9_DataOutputStream) {
        Boolean.valueOf(true);
        try {
            OutputStream r3_OutputStream = new ByteArrayOutputStream();
            DataOutputStream r4_DataOutputStream = new DataOutputStream(r3_OutputStream);
            r4_DataOutputStream.flush();
            r4_DataOutputStream.write(this.a);
            r4_DataOutputStream.write(this.b);
            r4_DataOutputStream.write(this.c);
            r4_DataOutputStream.writeShort(this.d);
            r4_DataOutputStream.writeShort(this.e);
            r4_DataOutputStream.writeByte(this.f);
            this.g[15] = (byte) 0;
            r4_DataOutputStream.write(ab.a(this.g, this.g.length));
            this.h[31] = (byte) 0;
            r4_DataOutputStream.write(ab.a(this.h, this.h.length));
            r4_DataOutputStream.writeShort(this.i);
            short r1s = (short) 0;
            while (r1s < this.i) {
                OutputStream r5_OutputStream = new ByteArrayOutputStream();
                DataOutputStream r6_DataOutputStream = new DataOutputStream(r5_OutputStream);
                r6_DataOutputStream.flush();
                am r0_am = (am) this.j.get(r1s);
                if (r0_am.c == null || r0_am.c.a(r6_DataOutputStream).booleanValue()) {
                    if (r0_am.d == null || r0_am.d.a(r6_DataOutputStream).booleanValue()) {
                        if (r0_am.e == null || r0_am.e.a(r6_DataOutputStream).booleanValue()) {
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        } else {
                            Boolean.valueOf(false);
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        }
                    } else {
                        Boolean.valueOf(false);
                        if (r0_am.e == null || r0_am.e.a(r6_DataOutputStream).booleanValue()) {
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        } else {
                            Boolean.valueOf(false);
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        }
                    }
                } else {
                    Boolean.valueOf(false);
                    if (r0_am.d == null || r0_am.d.a(r6_DataOutputStream).booleanValue()) {
                        if (r0_am.e == null || r0_am.e.a(r6_DataOutputStream).booleanValue()) {
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        } else {
                            Boolean.valueOf(false);
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        }
                    } else {
                        Boolean.valueOf(false);
                        if (r0_am.e == null || r0_am.e.a(r6_DataOutputStream).booleanValue()) {
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        } else {
                            Boolean.valueOf(false);
                            if (r0_am.f == null || r0_am.f.a(r6_DataOutputStream).booleanValue()) {
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            } else {
                                Boolean.valueOf(false);
                                r0_am.a = Integer.valueOf(r5_OutputStream.size() + 4).shortValue();
                                r4_DataOutputStream.writeShort(r0_am.a);
                                r4_DataOutputStream.writeInt(r0_am.b);
                                r4_DataOutputStream.write(r5_OutputStream.toByteArray());
                                r1s = (short) (r1s + 1);
                            }
                        }
                    }
                }
            }
            this.l = Integer.valueOf(r3_OutputStream.size()).shortValue();
            r9_DataOutputStream.writeByte(this.k);
            r9_DataOutputStream.writeShort(this.l);
            r9_DataOutputStream.write(r3_OutputStream.toByteArray());
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }

    protected final byte[] a() {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        a(new DataOutputStream(r0_OutputStream));
        return r0_OutputStream.toByteArray();
    }
}
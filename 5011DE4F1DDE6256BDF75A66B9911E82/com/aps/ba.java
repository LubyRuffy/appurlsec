package com.aps;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

public final class ba {
    private RandomAccessFile a;
    private ab b;
    private String c;
    private File d;

    protected ba(ab r2_ab) {
        this.c = RContactStorage.PRIMARY_KEY;
        this.d = null;
        this.b = r2_ab;
    }

    protected final synchronized void a(long r8j, byte[] r10_byteA) {
        int r0i = 0;
        synchronized (this) {
            try {
                this.d = this.b.a(r8j);
                if (this.d == null) {
                } else {
                    int r1i;
                    this.a = new RandomAccessFile(this.d, "rw");
                    byte[] r2_byteA = new byte[this.b.a()];
                    r1i = this.a.read(r2_byteA) == -1 ? 0 : this.a.readInt();
                    BitSet r2_BitSet = ab.b(r2_byteA);
                    int r3i = this.b.a() + 4 + r1i * 1500;
                    if (r1i < 0 || r1i > (this.b.a() << 3)) {
                        this.a.close();
                        this.d.delete();
                        if (this.a != null) {
                            try {
                                this.a.close();
                            } catch (IOException e) {
                            }
                        }
                    } else {
                        this.a.seek((long) r3i);
                        byte[] r3_byteA = ab.a(r10_byteA);
                        this.a.writeInt(r3_byteA.length);
                        this.a.writeLong(r8j);
                        this.a.write(r3_byteA);
                        r2_BitSet.set(r1i, true);
                        this.a.seek(0);
                        this.a.write(ab.a(r2_BitSet));
                        r1i++;
                        if (r1i == (this.b.a() << 3)) {
                            this.a.writeInt(r0i);
                            if (this.c.equalsIgnoreCase(this.d.getName())) {
                                this.c = this.d.getName();
                            }
                            this.d.length();
                            if (this.a != null) {
                                try {
                                    this.a.close();
                                } catch (IOException e_2) {
                                }
                            }
                            this.d = null;
                        } else {
                            r0i = r1i;
                            this.a.writeInt(r0i);
                            if (this.c.equalsIgnoreCase(this.d.getName())) {
                                this.d.length();
                                if (this.a != null) {
                                    this.d = null;
                                } else {
                                    this.a.close();
                                    this.d = null;
                                }
                            } else {
                                this.c = this.d.getName();
                                this.d.length();
                                if (this.a != null) {
                                    this.a.close();
                                }
                                this.d = null;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e_3) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (IOException e_4) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (Throwable th) {
            }
        }
    }
}
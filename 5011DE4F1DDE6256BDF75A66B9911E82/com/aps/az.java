package com.aps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;

public final class az {
    private RandomAccessFile a;
    private ab b;
    private File c;

    protected az(ab r2_ab) {
        this.c = null;
        this.b = r2_ab;
    }

    private static byte a(byte[] r8_byteA) {
        byte[] r0_byteA = null;
        try {
            InputStream r1_InputStream = new ByteArrayInputStream(r8_byteA);
            GZIPInputStream r2_GZIPInputStream = new GZIPInputStream(r1_InputStream);
            byte[] r3_byteA = new byte[1024];
            ByteArrayOutputStream r4_ByteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int r5i = r2_GZIPInputStream.read(r3_byteA, 0, r3_byteA.length);
                if (r5i != -1) {
                    r4_ByteArrayOutputStream.write(r3_byteA, 0, r5i);
                } else {
                    r0_byteA = r4_ByteArrayOutputStream.toByteArray();
                    r4_ByteArrayOutputStream.flush();
                    r4_ByteArrayOutputStream.close();
                    r2_GZIPInputStream.close();
                    r1_InputStream.close();
                    return r0_byteA[0];
                }
            }
        } catch (Exception e) {
        }
    }

    private static int a(int r1i, int r2i, int r3i) {
        int r0i = (r3i - 1) * 1500 + r1i;
        while (r0i >= r2i) {
            r0i -= 1500;
        }
        return r0i;
    }

    private int a(BitSet r4_BitSet) {
        int r0i = 0;
        while (r0i < r4_BitSet.length()) {
            if (r4_BitSet.get(r0i)) {
                return this.b.a() + r0i * 1500 + 4;
            }
            r0i++;
        }
        return 0;
    }

    private ArrayList a(int r5i, int r6i) {
        ArrayList r1_ArrayList = new ArrayList();
        while (r5i <= r6i) {
            try {
                this.a.seek((long) r5i);
                int r0i = this.a.readInt();
                this.a.readLong();
                if (r0i <= 0) {
                    r0i = a.MAX_ACTIVITY_COUNT_UNLIMITED;
                }
                byte[] r0_byteA = new byte[r0i];
                this.a.read(r0_byteA);
                byte r2b = a(r0_byteA);
                if (r2b == 3 || r2b == 4) {
                    r1_ArrayList.add(r0_byteA);
                    r5i += 1500;
                } else {
                    throw new OutOfMemoryError();
                }
            } catch (IOException e) {
            }
        }
        return r1_ArrayList;
    }

    private BitSet b() {
        byte[] r1_byteA = new byte[this.b.a()];
        try {
            this.a.read(r1_byteA);
            return ab.b(r1_byteA);
        } catch (IOException e) {
            return null;
        }
    }

    protected final int a() {
        int r0i = 0;
        synchronized (this) {
            try {
                this.c = this.b.b();
                if (this.c != null) {
                    this.a = new RandomAccessFile(this.b.b(), "rw");
                    byte[] r1_byteA = new byte[this.b.a()];
                    this.a.read(r1_byteA);
                    BitSet r2_BitSet = ab.b(r1_byteA);
                    int r1i = 0;
                    while (r1i < r2_BitSet.size()) {
                        if (r2_BitSet.get(r1i)) {
                            r0i++;
                        }
                        r1i++;
                    }
                }
                if (this.a != null) {
                    try {
                        this.a.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e_2) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (IOException e_3) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (NullPointerException e_4) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (Throwable th) {
            }
            this.c = null;
        }
        return r0i;
    }

    protected final synchronized aa a(int r8i) {
        aa r0_aa = null;
        synchronized (this) {
            try {
                synchronized (this) {
                    this.c = this.b.b();
                    if (this.c == null) {
                    } else {
                        this.a = new RandomAccessFile(this.c, "rw");
                        BitSet r1_BitSet = b();
                        if (r1_BitSet == null) {
                            this.c.delete();
                            if (this.a != null) {
                                try {
                                    this.a.close();
                                } catch (IOException e) {
                                }
                            }
                        } else {
                            int r1i = a(r1_BitSet);
                            int r2i = a(r1i, (int) this.c.length(), r8i);
                            ArrayList r3_ArrayList = a(r1i, r2i);
                            int[] r4_intA = new int[2];
                            r4_intA[0] = ((r1i - this.b.a()) - 4) / 1500;
                            r4_intA[1] = ((r2i - this.b.a()) - 4) / 1500;
                            aa r1_aa = new aa(this.c, r3_ArrayList, r4_intA);
                            if (this.a != null) {
                                try {
                                    this.a.close();
                                    r0_aa = r1_aa;
                                } catch (IOException e_2) {
                                }
                            }
                            r0_aa = r1_aa;
                        }
                    }
                }
            } catch (FileNotFoundException e_3) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (OutOfMemoryError e_4) {
                if (this.a != null) {
                    this.a.close();
                }
                this.c.delete();
                this.c = null;
            } catch (Throwable th) {
            }
        }
        return r0_aa;
    }

    protected final synchronized void a(aa r5_aa) {
        BitSet r0_BitSet = null;
        synchronized (this) {
            try {
                synchronized (this) {
                    this.c = r5_aa.a;
                    if (this.c == null) {
                    } else {
                        this.a = new RandomAccessFile(this.c, "rw");
                        byte[] r1_byteA = new byte[this.b.a()];
                        this.a.read(r1_byteA);
                        r0_BitSet = ab.b(r1_byteA);
                        if (r5_aa.b()) {
                            int r1i = r5_aa.b[0];
                            while (r1i <= r5_aa.b[1]) {
                                r0_BitSet.set(r1i, false);
                                r1i++;
                            }
                            this.a.seek(0);
                            this.a.write(ab.a(r0_BitSet));
                        }
                        if (this.a != null) {
                            try {
                                this.a.close();
                            } catch (IOException e) {
                            }
                        }
                        if (r0_BitSet.isEmpty()) {
                            this.c.delete();
                        }
                        this.c = null;
                    }
                }
            } catch (FileNotFoundException e_2) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (IOException e_3) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (Throwable th) {
            }
        }
    }
}
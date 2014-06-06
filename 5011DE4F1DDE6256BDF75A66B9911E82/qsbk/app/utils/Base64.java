package qsbk.app.utils;

import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.Serializable;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.widget.listview.XListViewFooter;

public class Base64 {
    public static final int DECODE = 0;
    public static final int DONT_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    public static final int GZIP = 2;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    public static final int URL_SAFE = 16;
    private static final byte[] a;
    private static final byte[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static final byte[] e;
    private static final byte[] f;

    public static class InputStream extends FilterInputStream {
        protected byte[] a;
        private boolean b;
        private int c;
        private byte[] d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private int i;
        private byte[] j;

        public InputStream(java.io.InputStream r2_java_io_InputStream) {
            this(r2_java_io_InputStream, 0);
        }

        public InputStream(java.io.InputStream r5_java_io_InputStream, int r6i) {
            boolean r1z = true;
            super(r5_java_io_InputStream);
            this.h = (r6i & 8) != 8;
            if ((r6i & 1) == 1) {
                this.b = r1z;
                this.e = this.b ? XListViewFooter.STATE_NOMORE : XListViewFooter.STATE_NODATA;
                this.d = new byte[this.e];
                this.c = -1;
                this.g = 0;
                this.i = r6i;
                this.a = Base64.c(r6i);
                this.j = Base64.d(r6i);
            } else {
                r1z = false;
                this.b = r1z;
                if (this.b) {
                }
                this.e = this.b ? XListViewFooter.STATE_NOMORE : XListViewFooter.STATE_NODATA;
                this.d = new byte[this.e];
                this.c = -1;
                this.g = 0;
                this.i = r6i;
                this.a = Base64.c(r6i);
                this.j = Base64.d(r6i);
            }
        }

        public int read() throws IOException {
            byte[] r0_byteA;
            if (this.c < 0) {
                int r3i;
                if (this.b) {
                    r0_byteA = new byte[3];
                    int r4i = 0;
                    int r2i = 0;
                    while (r4i < 3) {
                        try {
                            r3i = this.in.read();
                            if (r3i >= 0) {
                                r0_byteA[r4i] = (byte) r3i;
                                r2i++;
                            }
                        } catch (IOException e) {
                            IOException r3_IOException = e;
                            if (r4i == 0) {
                                throw r3_IOException;
                            }
                        }
                        r4i++;
                    }
                    if (r2i <= 0) {
                        return -1;
                    }
                    Base64.b(r0_byteA, NO_OPTIONS, r2i, this.d, 0, this.i);
                    this.c = 0;
                    this.f = 4;
                } else {
                    byte[] r2_byteA = new byte[4];
                    int r0i = 0;
                    while (r0i < 4) {
                        while (true) {
                            r3i = this.in.read();
                            if (r3i >= 0) {
                                if (this.j[r3i & 127] > Constants.ERROR_PARAM) {
                                }
                            }
                            if (r3i < 0) {
                                break;
                            } else {
                                r2_byteA[r0i] = (byte) r3i;
                                r0i++;
                            }
                        }
                    }
                    if (r0i == 4) {
                        this.f = Base64.b(r2_byteA, NO_OPTIONS, this.d, NO_OPTIONS, this.i);
                        this.c = 0;
                    } else {
                        if (r0i == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.c >= 0) {
                if (this.c >= this.f) {
                    return -1;
                }
                if (this.b && this.h && this.g >= 76) {
                    this.g = 0;
                    return REQUEST_CODE.REQUEST_CODE_EDIT_INTRO;
                } else {
                    this.g++;
                    r0_byteA = this.d;
                    int r1i = this.c;
                    this.c = r1i + 1;
                    byte r0b = r0_byteA[r1i];
                    if (this.c >= this.e) {
                        this.c = -1;
                    }
                    return r0b & 255;
                }
            } else {
                throw new IOException("Error in Base64 code reading stream.");
            }
        }

        public int read(byte[] r4_byteA, int r5i, int r6i) throws IOException {
            int r0i = NO_OPTIONS;
            while (r0i < r6i) {
                int r1i = read();
                if (r1i >= 0) {
                    r4_byteA[r5i + r0i] = (byte) r1i;
                    r0i++;
                } else {
                    if (r0i == 0) {
                        return -1;
                    }
                    return r0i;
                }
            }
            return r0i;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        protected byte[] a;
        private boolean b;
        private int c;
        private byte[] d;
        private int e;
        private int f;
        private boolean g;
        private byte[] h;
        private boolean i;
        private int j;
        private byte[] k;

        public OutputStream(java.io.OutputStream r2_java_io_OutputStream) {
            this(r2_java_io_OutputStream, 1);
        }

        public OutputStream(java.io.OutputStream r6_java_io_OutputStream, int r7i) {
            boolean r1z = true;
            super(r6_java_io_OutputStream);
            this.g = (r7i & 8) != 8;
            if ((r7i & 1) == 1) {
                this.b = r1z;
                this.e = this.b ? 4 : XListViewFooter.STATE_NOMORE;
                this.d = new byte[this.e];
                this.c = 0;
                this.f = 0;
                this.i = false;
                this.h = new byte[4];
                this.j = r7i;
                this.a = Base64.c(r7i);
                this.k = Base64.d(r7i);
            } else {
                r1z = false;
                this.b = r1z;
                if (this.b) {
                }
                this.e = this.b ? 4 : XListViewFooter.STATE_NOMORE;
                this.d = new byte[this.e];
                this.c = 0;
                this.f = 0;
                this.i = false;
                this.h = new byte[4];
                this.j = r7i;
                this.a = Base64.c(r7i);
                this.k = Base64.d(r7i);
            }
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.d = null;
            this.out = null;
        }

        public void flushBase64() throws IOException {
            if (this.c > 0) {
                if (this.b) {
                    this.out.write(Base64.b(this.h, this.d, this.c, this.j));
                    this.c = 0;
                } else {
                    throw new IOException("Base64 input not properly padded.");
                }
            }
        }

        public void resumeEncoding() {
            this.i = false;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.i = true;
        }

        public void write(int r7i) throws IOException {
            if (this.i) {
                this.out.write(r7i);
            } else if (this.b) {
                r0_byteA = this.d;
                r1i = this.c;
                this.c = r1i + 1;
                r0_byteA[r1i] = (byte) r7i;
                if (this.c >= this.e) {
                    this.out.write(Base64.b(this.h, this.d, this.e, this.j));
                    this.f += 4;
                    if ((!this.g) || this.f < 76) {
                        this.c = 0;
                    } else {
                        this.out.write(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
                        this.f = 0;
                        this.c = 0;
                    }
                }
            } else if (this.k[r7i & 127] > (byte) -5) {
                r0_byteA = this.d;
                r1i = this.c;
                this.c = r1i + 1;
                r0_byteA[r1i] = (byte) r7i;
                if (this.c >= this.e) {
                    this.out.write(this.h, NO_OPTIONS, Base64.b(this.d, NO_OPTIONS, this.h, NO_OPTIONS, this.j));
                    this.c = 0;
                }
            } else if (this.k[r7i & 127] != (byte) -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] r3_byteA, int r4i, int r5i) throws IOException {
            if (this.i) {
                this.out.write(r3_byteA, r4i, r5i);
            } else {
                int r0i = NO_OPTIONS;
                while (r0i < r5i) {
                    write(r3_byteA[r4i + r0i]);
                    r0i++;
                }
            }
        }
    }

    static {
        a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        b = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
        c = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        d = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
        e = new byte[]{(byte) 45, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122};
        f = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) -9, (byte) -9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 37, (byte) -9, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) 62, (byte) 63, (byte) -9, (byte) -9, (byte) -9, (byte) -9};
    }

    private Base64() {
    }

    private static final void a(String r2_String) {
        System.err.println(r2_String);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }

    private static int b(byte[] r4_byteA, int r5i, byte[] r6_byteA, int r7i, int r8i) {
        byte[] r0_byteA = d(r8i);
        if (r4_byteA[r5i + 2] == (byte) 61) {
            r6_byteA[r7i] = (byte) ((((r0_byteA[r4_byteA[r5i + 1]] & 255) << 12) | ((r0_byteA[r4_byteA[r5i]] & 255) << 18)) >>> 16);
            return ENCODE;
        } else if (r4_byteA[r5i + 3] == (byte) 61) {
            int r0i = ((r0_byteA[r4_byteA[r5i + 2]] & 255) << 6) | (((r0_byteA[r4_byteA[r5i]] & 255) << 18) | ((r0_byteA[r4_byteA[r5i + 1]] & 255) << 12));
            r6_byteA[r7i] = (byte) (r0i >>> 16);
            r6_byteA[r7i + 1] = (byte) (r0i >>> 8);
            return GZIP;
        } else {
            try {
                int r1i = ((((r0_byteA[r4_byteA[r5i]] & 255) << 18) | ((r0_byteA[r4_byteA[r5i + 1]] & 255) << 12)) | ((r0_byteA[r4_byteA[r5i + 2]] & 255) << 6)) | (r0_byteA[r4_byteA[r5i + 3]] & 255);
                r6_byteA[r7i] = (byte) (r1i >> 16);
                r6_byteA[r7i + 1] = (byte) (r1i >> 8);
                r6_byteA[r7i + 2] = (byte) r1i;
                return XListViewFooter.STATE_NOMORE;
            } catch (Exception e) {
                System.out.println(RContactStorage.PRIMARY_KEY + r4_byteA[r5i] + ": " + r0_byteA[r4_byteA[r5i]]);
                System.out.println(RContactStorage.PRIMARY_KEY + r4_byteA[r5i + 1] + ": " + r0_byteA[r4_byteA[r5i + 1]]);
                System.out.println(RContactStorage.PRIMARY_KEY + r4_byteA[r5i + 2] + ": " + r0_byteA[r4_byteA[r5i + 2]]);
                System.out.println(RContactStorage.PRIMARY_KEY + r4_byteA[r5i + 3] + ": " + r0_byteA[r4_byteA[r5i + 3]]);
                return -1;
            }
        }
    }

    private static byte[] b(byte[] r5_byteA, int r6i, int r7i, byte[] r8_byteA, int r9i, int r10i) {
        int r0i = NO_OPTIONS;
        byte[] r3_byteA = c(r10i);
        int r1i = (r7i > 1 ? (r5_byteA[r6i + 1] << 24) >>> 16 : 0) | (r7i > 0 ? (r5_byteA[r6i] << 24) >>> 8 : 0);
        if (r7i > 2) {
            r0i = (r5_byteA[r6i + 2] << 24) >>> 24;
        }
        r0i |= r1i;
        switch (r7i) {
            case ENCODE:
                r8_byteA[r9i] = r3_byteA[r0i >>> 18];
                r8_byteA[r9i + 1] = r3_byteA[(r0i >>> 12) & 63];
                r8_byteA[r9i + 2] = (byte) 61;
                r8_byteA[r9i + 3] = (byte) 61;
                break;
            case GZIP:
                r8_byteA[r9i] = r3_byteA[r0i >>> 18];
                r8_byteA[r9i + 1] = r3_byteA[(r0i >>> 12) & 63];
                r8_byteA[r9i + 2] = r3_byteA[(r0i >>> 6) & 63];
                r8_byteA[r9i + 3] = (byte) 61;
                break;
            case XListViewFooter.STATE_NOMORE:
                r8_byteA[r9i] = r3_byteA[r0i >>> 18];
                r8_byteA[r9i + 1] = r3_byteA[(r0i >>> 12) & 63];
                r8_byteA[r9i + 2] = r3_byteA[(r0i >>> 6) & 63];
                r8_byteA[r9i + 3] = r3_byteA[r0i & 63];
                break;
        }
        return r8_byteA;
    }

    private static byte[] b(byte[] r6_byteA, byte[] r7_byteA, int r8i, int r9i) {
        b(r7_byteA, NO_OPTIONS, r8i, r6_byteA, 0, r9i);
        return r6_byteA;
    }

    private static final byte[] c(int r2i) {
        if ((r2i & 16) == 16) {
            return c;
        }
        if ((r2i & 32) == 32) {
            return e;
        }
        return a;
    }

    private static final byte[] d(int r2i) {
        if ((r2i & 16) == 16) {
            return d;
        }
        if ((r2i & 32) == 32) {
            return f;
        }
        return b;
    }

    public static byte[] decode(String r1_String) {
        return decode(r1_String, NO_OPTIONS);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(String r7_String, int r8i) {
        /*
        r2 = 0;
        r4 = 0;
        r0 = "UTF-8";
        r0 = r7.getBytes(r0);	 //Catch:{ UnsupportedEncodingException -> 0x0051 }
    L_0x0008:
        r1 = r0.length;
        r0 = decode(r0, r4, r1, r8);
        if (r0 == 0) goto L_0x0050;
    L_0x000f:
        r1 = r0.length;
        r3 = 4;
        if (r1 < r3) goto L_0x0050;
    L_0x0013:
        r1 = r0[r4];
        r1 = r1 & 255;
        r3 = 1;
        r3 = r0[r3];
        r3 = r3 << 8;
        r4 = 65280; // 0xff00 float:9.1477E-41 double:3.22526E-319;
        r3 = r3 & r4;
        r1 = r1 | r3;
        r3 = 35615; // 0x8b1f float:4.9907E-41 double:1.7596E-319;
        if (r3 != r1) goto L_0x0050;
    L_0x0026:
        r1 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r5 = new byte[r1];
        r1 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x008c, all -> 0x0067 }
        r1.<init>();	 //Catch:{ IOException -> 0x008c, all -> 0x0067 }
        r4 = new java.io.ByteArrayInputStream;	 //Catch:{ IOException -> 0x0090, all -> 0x0084 }
        r4.<init>(r0);	 //Catch:{ IOException -> 0x0090, all -> 0x0084 }
        r3 = new java.util.zip.GZIPInputStream;	 //Catch:{ IOException -> 0x0093, all -> 0x0087 }
        r3.<init>(r4);	 //Catch:{ IOException -> 0x0093, all -> 0x0087 }
    L_0x0039:
        r2 = r3.read(r5);	 //Catch:{ IOException -> 0x0044, all -> 0x0089 }
        if (r2 < 0) goto L_0x0057;
    L_0x003f:
        r6 = 0;
        r1.write(r5, r6, r2);	 //Catch:{ IOException -> 0x0044, all -> 0x0089 }
        goto L_0x0039;
    L_0x0044:
        r2 = move-exception;
        r2 = r3;
        r3 = r4;
    L_0x0047:
        r1.close();	 //Catch:{ Exception -> 0x0078 }
    L_0x004a:
        r2.close();	 //Catch:{ Exception -> 0x007a }
    L_0x004d:
        r3.close();	 //Catch:{ Exception -> 0x007c }
    L_0x0050:
        return r0;
    L_0x0051:
        r0 = move-exception;
        r0 = r7.getBytes();
        goto L_0x0008;
    L_0x0057:
        r0 = r1.toByteArray();	 //Catch:{ IOException -> 0x0044, all -> 0x0089 }
        r1.close();	 //Catch:{ Exception -> 0x0074 }
    L_0x005e:
        r3.close();	 //Catch:{ Exception -> 0x0076 }
    L_0x0061:
        r4.close();	 //Catch:{ Exception -> 0x0065 }
        goto L_0x0050;
    L_0x0065:
        r1 = move-exception;
        goto L_0x0050;
    L_0x0067:
        r0 = move-exception;
        r1 = r2;
        r4 = r2;
    L_0x006a:
        r1.close();	 //Catch:{ Exception -> 0x007e }
    L_0x006d:
        r2.close();	 //Catch:{ Exception -> 0x0080 }
    L_0x0070:
        r4.close();	 //Catch:{ Exception -> 0x0082 }
    L_0x0073:
        throw r0;
    L_0x0074:
        r1 = move-exception;
        goto L_0x005e;
    L_0x0076:
        r1 = move-exception;
        goto L_0x0061;
    L_0x0078:
        r1 = move-exception;
        goto L_0x004a;
    L_0x007a:
        r1 = move-exception;
        goto L_0x004d;
    L_0x007c:
        r1 = move-exception;
        goto L_0x0050;
    L_0x007e:
        r1 = move-exception;
        goto L_0x006d;
    L_0x0080:
        r1 = move-exception;
        goto L_0x0070;
    L_0x0082:
        r1 = move-exception;
        goto L_0x0073;
    L_0x0084:
        r0 = move-exception;
        r4 = r2;
        goto L_0x006a;
    L_0x0087:
        r0 = move-exception;
        goto L_0x006a;
    L_0x0089:
        r0 = move-exception;
        r2 = r3;
        goto L_0x006a;
    L_0x008c:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0047;
    L_0x0090:
        r3 = move-exception;
        r3 = r2;
        goto L_0x0047;
    L_0x0093:
        r3 = move-exception;
        r3 = r4;
        goto L_0x0047;
        */

    }

    public static byte[] decode(byte[] r10_byteA, int r11i, int r12i, int r13i) {
        int r0i;
        byte[] r5_byteA = d(r13i);
        Object r6_Object = new Object[((r12i * 3) / 4)];
        byte[] r7_byteA = new byte[4];
        int r4i = r11i;
        int r2i = 0;
        int r3i = 0;
        while (r4i < r11i + r12i) {
            byte r8b = (byte) (r10_byteA[r4i] & 127);
            byte r0b = r5_byteA[r8b];
            if (r0b >= -5) {
                if (r0b >= -1) {
                    r0i = r2i + 1;
                    r7_byteA[r2i] = r8b;
                    if (r0i > 3) {
                        r0i = b(r7_byteA, NO_OPTIONS, r6_Object, r3i, r13i) + r3i;
                        if (r8b == 61) {
                            break;
                        } else {
                            r2i = r0i;
                            r0i = 0;
                        }
                    } else {
                        r2i = r3i;
                    }
                } else {
                    r0i = r2i;
                    r2i = r3i;
                }
                r4i++;
                r3i = r2i;
                r2i = r0i;
            } else {
                System.err.println("Bad Base64 input character at " + r4i + ": " + r10_byteA[r4i] + "(decimal)");
                return null;
            }
        }
        r0i = r3i;
        Object r2_Object = new Object[r0i];
        System.arraycopy(r6_Object, NO_OPTIONS, r2_Object, NO_OPTIONS, r0i);
        return r2_Object;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean decodeFileToFile(String r6_String, String r7_String) {
        /*
        r3 = 0;
        r0 = 0;
        r4 = new qsbk.app.utils.Base64$InputStream;	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r1 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r2 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r2.<init>(r6);	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r1.<init>(r2);	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r2 = 0;
        r4.<init>(r1, r2);	 //Catch:{ IOException -> 0x005d, all -> 0x0041 }
        r2 = new java.io.BufferedOutputStream;	 //Catch:{ IOException -> 0x0060, all -> 0x0054 }
        r1 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0060, all -> 0x0054 }
        r1.<init>(r7);	 //Catch:{ IOException -> 0x0060, all -> 0x0054 }
        r2.<init>(r1);	 //Catch:{ IOException -> 0x0060, all -> 0x0054 }
        r1 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r1 = new byte[r1];	 //Catch:{ IOException -> 0x002b, all -> 0x0056 }
    L_0x0020:
        r3 = r4.read(r1);	 //Catch:{ IOException -> 0x002b, all -> 0x0056 }
        if (r3 < 0) goto L_0x0037;
    L_0x0026:
        r5 = 0;
        r2.write(r1, r5, r3);	 //Catch:{ IOException -> 0x002b, all -> 0x0056 }
        goto L_0x0020;
    L_0x002b:
        r1 = move-exception;
        r3 = r4;
    L_0x002d:
        r1.printStackTrace();	 //Catch:{ all -> 0x0059 }
        r3.close();	 //Catch:{ Exception -> 0x004c }
    L_0x0033:
        r2.close();	 //Catch:{ Exception -> 0x004e }
    L_0x0036:
        return r0;
    L_0x0037:
        r0 = 1;
        r4.close();	 //Catch:{ Exception -> 0x004a }
    L_0x003b:
        r2.close();	 //Catch:{ Exception -> 0x003f }
        goto L_0x0036;
    L_0x003f:
        r1 = move-exception;
        goto L_0x0036;
    L_0x0041:
        r0 = move-exception;
        r4 = r3;
    L_0x0043:
        r4.close();	 //Catch:{ Exception -> 0x0050 }
    L_0x0046:
        r3.close();	 //Catch:{ Exception -> 0x0052 }
    L_0x0049:
        throw r0;
    L_0x004a:
        r1 = move-exception;
        goto L_0x003b;
    L_0x004c:
        r1 = move-exception;
        goto L_0x0033;
    L_0x004e:
        r1 = move-exception;
        goto L_0x0036;
    L_0x0050:
        r1 = move-exception;
        goto L_0x0046;
    L_0x0052:
        r1 = move-exception;
        goto L_0x0049;
    L_0x0054:
        r0 = move-exception;
        goto L_0x0043;
    L_0x0056:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0043;
    L_0x0059:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0043;
    L_0x005d:
        r1 = move-exception;
        r2 = r3;
        goto L_0x002d;
    L_0x0060:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x002d;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decodeFromFile(String r9_String) {
        /*
        r0 = 0;
        r2 = 0;
        r1 = 0;
        r3 = new java.io.File;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r3.<init>(r9);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r4 = r3.length();	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = (r4 > r6 ? 1 : (r4 == r6? 0 : -1));
        if (r4 <= 0) goto L_0x0039;
    L_0x0013:
        r2 = java.lang.System.err;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r4 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r4.<init>();	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r5 = "File is too big for this convenience method (";
        r4 = r4.append(r5);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r5 = r3.length();	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r3 = r4.append(r5);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r4 = " bytes).";
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r3 = r3.toString();	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r2.println(r3);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r1.close();	 //Catch:{ Exception -> 0x008f }
    L_0x0038:
        return r0;
    L_0x0039:
        r4 = r3.length();	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r1 = (int) r4;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r4 = new byte[r1];	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r1 = new qsbk.app.utils.Base64$InputStream;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r5 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r6 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r6.<init>(r3);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r5.<init>(r6);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
        r3 = 0;
        r1.<init>(r5, r3);	 //Catch:{ IOException -> 0x0067, all -> 0x0087 }
    L_0x0050:
        r3 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r3 = r1.read(r4, r2, r3);	 //Catch:{ IOException -> 0x0095 }
        if (r3 < 0) goto L_0x005a;
    L_0x0058:
        r2 = r2 + r3;
        goto L_0x0050;
    L_0x005a:
        r0 = new byte[r2];	 //Catch:{ IOException -> 0x0095 }
        r3 = 0;
        r5 = 0;
        java.lang.System.arraycopy(r4, r3, r0, r5, r2);	 //Catch:{ IOException -> 0x0095 }
        r1.close();	 //Catch:{ Exception -> 0x0065 }
        goto L_0x0038;
    L_0x0065:
        r1 = move-exception;
        goto L_0x0038;
    L_0x0067:
        r1 = move-exception;
        r1 = r0;
    L_0x0069:
        r2 = java.lang.System.err;	 //Catch:{ all -> 0x0093 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0093 }
        r3.<init>();	 //Catch:{ all -> 0x0093 }
        r4 = "Error decoding from file ";
        r3 = r3.append(r4);	 //Catch:{ all -> 0x0093 }
        r3 = r3.append(r9);	 //Catch:{ all -> 0x0093 }
        r3 = r3.toString();	 //Catch:{ all -> 0x0093 }
        r2.println(r3);	 //Catch:{ all -> 0x0093 }
        r1.close();	 //Catch:{ Exception -> 0x0085 }
        goto L_0x0038;
    L_0x0085:
        r1 = move-exception;
        goto L_0x0038;
    L_0x0087:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x008b:
        r1.close();	 //Catch:{ Exception -> 0x0091 }
    L_0x008e:
        throw r0;
    L_0x008f:
        r1 = move-exception;
        goto L_0x0038;
    L_0x0091:
        r1 = move-exception;
        goto L_0x008e;
    L_0x0093:
        r0 = move-exception;
        goto L_0x008b;
    L_0x0095:
        r2 = move-exception;
        goto L_0x0069;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean decodeToFile(String r5_String, String r6_String) {
        /*
        r0 = 0;
        r2 = 0;
        r1 = new qsbk.app.utils.Base64$OutputStream;	 //Catch:{ IOException -> 0x001b, all -> 0x0023 }
        r3 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x001b, all -> 0x0023 }
        r3.<init>(r6);	 //Catch:{ IOException -> 0x001b, all -> 0x0023 }
        r4 = 0;
        r1.<init>(r3, r4);	 //Catch:{ IOException -> 0x001b, all -> 0x0023 }
        r2 = "UTF-8";
        r2 = r5.getBytes(r2);	 //Catch:{ IOException -> 0x002f, all -> 0x002c }
        r1.write(r2);	 //Catch:{ IOException -> 0x002f, all -> 0x002c }
        r0 = 1;
        r1.close();	 //Catch:{ Exception -> 0x0028 }
    L_0x001a:
        return r0;
    L_0x001b:
        r1 = move-exception;
        r1 = r2;
    L_0x001d:
        r1.close();	 //Catch:{ Exception -> 0x0021 }
        goto L_0x001a;
    L_0x0021:
        r1 = move-exception;
        goto L_0x001a;
    L_0x0023:
        r0 = move-exception;
    L_0x0024:
        r2.close();	 //Catch:{ Exception -> 0x002a }
    L_0x0027:
        throw r0;
    L_0x0028:
        r1 = move-exception;
        goto L_0x001a;
    L_0x002a:
        r1 = move-exception;
        goto L_0x0027;
    L_0x002c:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0024;
    L_0x002f:
        r2 = move-exception;
        goto L_0x001d;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Object decodeToObject(String r4_String) {
        /*
        r0 = 0;
        r1 = decode(r4);
        r3 = new java.io.ByteArrayInputStream;	 //Catch:{ IOException -> 0x001a, ClassNotFoundException -> 0x0029, all -> 0x0038 }
        r3.<init>(r1);	 //Catch:{ IOException -> 0x001a, ClassNotFoundException -> 0x0029, all -> 0x0038 }
        r2 = new java.io.ObjectInputStream;	 //Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0055, all -> 0x004f }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0055, all -> 0x004f }
        r0 = r2.readObject();	 //Catch:{ IOException -> 0x005d, ClassNotFoundException -> 0x0058 }
        r3.close();	 //Catch:{ Exception -> 0x0043 }
    L_0x0016:
        r2.close();	 //Catch:{ Exception -> 0x0045 }
    L_0x0019:
        return r0;
    L_0x001a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x001d:
        r1.printStackTrace();	 //Catch:{ all -> 0x0053 }
        r3.close();	 //Catch:{ Exception -> 0x0047 }
    L_0x0023:
        r2.close();	 //Catch:{ Exception -> 0x0027 }
        goto L_0x0019;
    L_0x0027:
        r1 = move-exception;
        goto L_0x0019;
    L_0x0029:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x002c:
        r1.printStackTrace();	 //Catch:{ all -> 0x0053 }
        r3.close();	 //Catch:{ Exception -> 0x0049 }
    L_0x0032:
        r2.close();	 //Catch:{ Exception -> 0x0036 }
        goto L_0x0019;
    L_0x0036:
        r1 = move-exception;
        goto L_0x0019;
    L_0x0038:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x003c:
        r3.close();	 //Catch:{ Exception -> 0x004b }
    L_0x003f:
        r2.close();	 //Catch:{ Exception -> 0x004d }
    L_0x0042:
        throw r0;
    L_0x0043:
        r1 = move-exception;
        goto L_0x0016;
    L_0x0045:
        r1 = move-exception;
        goto L_0x0019;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0023;
    L_0x0049:
        r1 = move-exception;
        goto L_0x0032;
    L_0x004b:
        r1 = move-exception;
        goto L_0x003f;
    L_0x004d:
        r1 = move-exception;
        goto L_0x0042;
    L_0x004f:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x003c;
    L_0x0053:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0055:
        r1 = move-exception;
        r2 = r0;
        goto L_0x002c;
    L_0x0058:
        r1 = move-exception;
        goto L_0x002c;
    L_0x005a:
        r1 = move-exception;
        r2 = r0;
        goto L_0x001d;
    L_0x005d:
        r1 = move-exception;
        goto L_0x001d;
        */

    }

    public static String encodeBytes(byte[] r2_byteA) {
        return encodeBytes(r2_byteA, NO_OPTIONS, r2_byteA.length, NO_OPTIONS);
    }

    public static String encodeBytes(byte[] r2_byteA, int r3i) {
        return encodeBytes(r2_byteA, NO_OPTIONS, r2_byteA.length, r3i);
    }

    public static String encodeBytes(byte[] r1_byteA, int r2i, int r3i) {
        return encodeBytes(r1_byteA, r2i, r3i, NO_OPTIONS);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String encodeBytes(byte[] r11_byteA, int r12i, int r13i, int r14i) {
        /*
        r0 = 0;
        r7 = 0;
        r1 = r14 & 8;
        r2 = r14 & 2;
        r3 = 2;
        if (r2 != r3) goto L_0x0062;
    L_0x0009:
        r4 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x0035, all -> 0x0048 }
        r4.<init>();	 //Catch:{ IOException -> 0x0035, all -> 0x0048 }
        r2 = new qsbk.app.utils.Base64$OutputStream;	 //Catch:{ IOException -> 0x00ea, all -> 0x00dc }
        r1 = r14 | 1;
        r2.<init>(r4, r1);	 //Catch:{ IOException -> 0x00ea, all -> 0x00dc }
        r3 = new java.util.zip.GZIPOutputStream;	 //Catch:{ IOException -> 0x00ef, all -> 0x00e2 }
        r3.<init>(r2);	 //Catch:{ IOException -> 0x00ef, all -> 0x00e2 }
        r3.write(r11, r12, r13);	 //Catch:{ IOException -> 0x00f3 }
        r3.close();	 //Catch:{ IOException -> 0x00f3 }
        r3.close();	 //Catch:{ Exception -> 0x00c4 }
    L_0x0023:
        r2.close();	 //Catch:{ Exception -> 0x00c7 }
    L_0x0026:
        r4.close();	 //Catch:{ Exception -> 0x00ca }
    L_0x0029:
        r0 = new java.lang.String;	 //Catch:{ UnsupportedEncodingException -> 0x0057 }
        r1 = r4.toByteArray();	 //Catch:{ UnsupportedEncodingException -> 0x0057 }
        r2 = "UTF-8";
        r0.<init>(r1, r2);	 //Catch:{ UnsupportedEncodingException -> 0x0057 }
    L_0x0034:
        return r0;
    L_0x0035:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
    L_0x0039:
        r1.printStackTrace();	 //Catch:{ all -> 0x00e7 }
        r3.close();	 //Catch:{ Exception -> 0x00cd }
    L_0x003f:
        r2.close();	 //Catch:{ Exception -> 0x00d0 }
    L_0x0042:
        r4.close();	 //Catch:{ Exception -> 0x0046 }
        goto L_0x0034;
    L_0x0046:
        r1 = move-exception;
        goto L_0x0034;
    L_0x0048:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r0 = r1;
    L_0x004d:
        r3.close();	 //Catch:{ Exception -> 0x00d3 }
    L_0x0050:
        r2.close();	 //Catch:{ Exception -> 0x00d6 }
    L_0x0053:
        r4.close();	 //Catch:{ Exception -> 0x00d9 }
    L_0x0056:
        throw r0;
    L_0x0057:
        r0 = move-exception;
        r0 = new java.lang.String;
        r1 = r4.toByteArray();
        r0.<init>(r1);
        goto L_0x0034;
    L_0x0062:
        if (r1 != 0) goto L_0x009f;
    L_0x0064:
        r0 = 1;
        r6 = r0;
    L_0x0066:
        r0 = r13 * 4;
        r1 = r0 / 3;
        r0 = r13 % 3;
        if (r0 <= 0) goto L_0x00a1;
    L_0x006e:
        r0 = 4;
    L_0x006f:
        r2 = r1 + r0;
        if (r6 == 0) goto L_0x00a3;
    L_0x0073:
        r0 = r1 / 76;
    L_0x0075:
        r0 = r0 + r2;
        r3 = new byte[r0];
        r10 = r13 + -2;
        r8 = r7;
        r4 = r7;
        r9 = r7;
    L_0x007d:
        if (r9 >= r10) goto L_0x00a5;
    L_0x007f:
        r1 = r9 + r12;
        r2 = 3;
        r0 = r11;
        r5 = r14;
        b(r0, r1, r2, r3, r4, r5);
        r0 = r8 + 4;
        if (r6 == 0) goto L_0x0098;
    L_0x008b:
        r1 = 76;
        if (r0 != r1) goto L_0x0098;
    L_0x008f:
        r0 = r4 + 4;
        r1 = 10;
        r3[r0] = r1;
        r4 = r4 + 1;
        r0 = r7;
    L_0x0098:
        r1 = r9 + 3;
        r4 = r4 + 4;
        r8 = r0;
        r9 = r1;
        goto L_0x007d;
    L_0x009f:
        r6 = r7;
        goto L_0x0066;
    L_0x00a1:
        r0 = r7;
        goto L_0x006f;
    L_0x00a3:
        r0 = r7;
        goto L_0x0075;
    L_0x00a5:
        if (r9 >= r13) goto L_0x00b2;
    L_0x00a7:
        r1 = r9 + r12;
        r2 = r13 - r9;
        r0 = r11;
        r5 = r14;
        b(r0, r1, r2, r3, r4, r5);
        r4 = r4 + 4;
    L_0x00b2:
        r0 = new java.lang.String;	 //Catch:{ UnsupportedEncodingException -> 0x00bc }
        r1 = 0;
        r2 = "UTF-8";
        r0.<init>(r3, r1, r4, r2);	 //Catch:{ UnsupportedEncodingException -> 0x00bc }
        goto L_0x0034;
    L_0x00bc:
        r0 = move-exception;
        r0 = new java.lang.String;
        r0.<init>(r3, r7, r4);
        goto L_0x0034;
    L_0x00c4:
        r0 = move-exception;
        goto L_0x0023;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x0026;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x0029;
    L_0x00cd:
        r1 = move-exception;
        goto L_0x003f;
    L_0x00d0:
        r1 = move-exception;
        goto L_0x0042;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x0050;
    L_0x00d6:
        r1 = move-exception;
        goto L_0x0053;
    L_0x00d9:
        r1 = move-exception;
        goto L_0x0056;
    L_0x00dc:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        goto L_0x004d;
    L_0x00e2:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x004d;
    L_0x00e7:
        r0 = move-exception;
        goto L_0x004d;
    L_0x00ea:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0039;
    L_0x00ef:
        r1 = move-exception;
        r3 = r0;
        goto L_0x0039;
    L_0x00f3:
        r1 = move-exception;
        goto L_0x0039;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean encodeFileToFile(String r7_String, String r8_String) {
        /*
        r3 = 0;
        r0 = 1;
        r1 = 0;
        r4 = new qsbk.app.utils.Base64$InputStream;	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r2 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r5 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r5.<init>(r7);	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r2.<init>(r5);	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r5 = 1;
        r4.<init>(r2, r5);	 //Catch:{ IOException -> 0x005f, all -> 0x0045 }
        r2 = new java.io.BufferedOutputStream;	 //Catch:{ IOException -> 0x0062, all -> 0x0056 }
        r5 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0062, all -> 0x0056 }
        r5.<init>(r8);	 //Catch:{ IOException -> 0x0062, all -> 0x0056 }
        r2.<init>(r5);	 //Catch:{ IOException -> 0x0062, all -> 0x0056 }
        r3 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r3 = new byte[r3];	 //Catch:{ IOException -> 0x002c, all -> 0x0058 }
    L_0x0021:
        r5 = r4.read(r3);	 //Catch:{ IOException -> 0x002c, all -> 0x0058 }
        if (r5 < 0) goto L_0x0039;
    L_0x0027:
        r6 = 0;
        r2.write(r3, r6, r5);	 //Catch:{ IOException -> 0x002c, all -> 0x0058 }
        goto L_0x0021;
    L_0x002c:
        r0 = move-exception;
        r3 = r4;
    L_0x002e:
        r0.printStackTrace();	 //Catch:{ all -> 0x005b }
        r3.close();	 //Catch:{ Exception -> 0x0050 }
    L_0x0034:
        r2.close();	 //Catch:{ Exception -> 0x0042 }
        r0 = r1;
    L_0x0038:
        return r0;
    L_0x0039:
        r4.close();	 //Catch:{ Exception -> 0x004e }
    L_0x003c:
        r2.close();	 //Catch:{ Exception -> 0x0040 }
        goto L_0x0038;
    L_0x0040:
        r1 = move-exception;
        goto L_0x0038;
    L_0x0042:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0038;
    L_0x0045:
        r0 = move-exception;
        r4 = r3;
    L_0x0047:
        r4.close();	 //Catch:{ Exception -> 0x0052 }
    L_0x004a:
        r3.close();	 //Catch:{ Exception -> 0x0054 }
    L_0x004d:
        throw r0;
    L_0x004e:
        r1 = move-exception;
        goto L_0x003c;
    L_0x0050:
        r0 = move-exception;
        goto L_0x0034;
    L_0x0052:
        r1 = move-exception;
        goto L_0x004a;
    L_0x0054:
        r1 = move-exception;
        goto L_0x004d;
    L_0x0056:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0058:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0047;
    L_0x005b:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0047;
    L_0x005f:
        r0 = move-exception;
        r2 = r3;
        goto L_0x002e;
    L_0x0062:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x002e;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String encodeFromFile(String r9_String) {
        /*
        r1 = 0;
        r0 = 0;
        r3 = new java.io.File;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r3.<init>(r9);	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r4 = r3.length();	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r4 = (double) r4;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r6 = 4608983858650965606; // 0x3ff6666666666666 float:2.720083E23 double:1.4;
        r4 = r4 * r6;
        r2 = (int) r4;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r4 = 40;
        r2 = java.lang.Math.max(r2, r4);	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r4 = new byte[r2];	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r2 = new qsbk.app.utils.Base64$InputStream;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r5 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r6 = new java.io.FileInputStream;	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r6.<init>(r3);	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r5.<init>(r6);	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r3 = 1;
        r2.<init>(r5, r3);	 //Catch:{ IOException -> 0x0043, all -> 0x0065 }
        r3 = r0;
    L_0x002c:
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = r2.read(r4, r3, r0);	 //Catch:{ IOException -> 0x0076, all -> 0x006e }
        if (r0 < 0) goto L_0x0037;
    L_0x0034:
        r0 = r0 + r3;
        r3 = r0;
        goto L_0x002c;
    L_0x0037:
        r0 = new java.lang.String;	 //Catch:{ IOException -> 0x0076, all -> 0x006e }
        r5 = 0;
        r6 = "UTF-8";
        r0.<init>(r4, r5, r3, r6);	 //Catch:{ IOException -> 0x0076, all -> 0x006e }
        r2.close();	 //Catch:{ Exception -> 0x006a }
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = move-exception;
        r0 = r1;
    L_0x0045:
        r2 = java.lang.System.err;	 //Catch:{ all -> 0x0071 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0071 }
        r3.<init>();	 //Catch:{ all -> 0x0071 }
        r4 = "Error encoding from file ";
        r3 = r3.append(r4);	 //Catch:{ all -> 0x0071 }
        r3 = r3.append(r9);	 //Catch:{ all -> 0x0071 }
        r3 = r3.toString();	 //Catch:{ all -> 0x0071 }
        r2.println(r3);	 //Catch:{ all -> 0x0071 }
        r0.close();	 //Catch:{ Exception -> 0x0062 }
        r0 = r1;
        goto L_0x0042;
    L_0x0062:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0042;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        r1.close();	 //Catch:{ Exception -> 0x006c }
    L_0x0069:
        throw r0;
    L_0x006a:
        r1 = move-exception;
        goto L_0x0042;
    L_0x006c:
        r1 = move-exception;
        goto L_0x0069;
    L_0x006e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0066;
    L_0x0071:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0066;
    L_0x0076:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0045;
        */

    }

    public static String encodeObject(Serializable r1_Serializable) {
        return encodeObject(r1_Serializable, NO_OPTIONS);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String encodeObject(Serializable r6_Serializable, int r7i) {
        /*
        r0 = 0;
        r1 = r7 & 2;
        r5 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x003e, all -> 0x0055 }
        r5.<init>();	 //Catch:{ IOException -> 0x003e, all -> 0x0055 }
        r4 = new qsbk.app.utils.Base64$OutputStream;	 //Catch:{ IOException -> 0x009a, all -> 0x0089 }
        r2 = r7 | 1;
        r4.<init>(r5, r2);	 //Catch:{ IOException -> 0x009a, all -> 0x0089 }
        r2 = 2;
        if (r1 != r2) goto L_0x0037;
    L_0x0012:
        r2 = new java.util.zip.GZIPOutputStream;	 //Catch:{ IOException -> 0x009f, all -> 0x008f }
        r2.<init>(r4);	 //Catch:{ IOException -> 0x009f, all -> 0x008f }
        r3 = new java.io.ObjectOutputStream;	 //Catch:{ IOException -> 0x00a3, all -> 0x0094 }
        r3.<init>(r2);	 //Catch:{ IOException -> 0x00a3, all -> 0x0094 }
    L_0x001c:
        r3.writeObject(r6);	 //Catch:{ IOException -> 0x00a6 }
        r3.close();	 //Catch:{ Exception -> 0x0073 }
    L_0x0022:
        r2.close();	 //Catch:{ Exception -> 0x0075 }
    L_0x0025:
        r4.close();	 //Catch:{ Exception -> 0x0077 }
    L_0x0028:
        r5.close();	 //Catch:{ Exception -> 0x0079 }
    L_0x002b:
        r0 = new java.lang.String;	 //Catch:{ UnsupportedEncodingException -> 0x0068 }
        r1 = r5.toByteArray();	 //Catch:{ UnsupportedEncodingException -> 0x0068 }
        r2 = "UTF-8";
        r0.<init>(r1, r2);	 //Catch:{ UnsupportedEncodingException -> 0x0068 }
    L_0x0036:
        return r0;
    L_0x0037:
        r3 = new java.io.ObjectOutputStream;	 //Catch:{ IOException -> 0x009f, all -> 0x008f }
        r3.<init>(r4);	 //Catch:{ IOException -> 0x009f, all -> 0x008f }
        r2 = r0;
        goto L_0x001c;
    L_0x003e:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
    L_0x0043:
        r1.printStackTrace();	 //Catch:{ all -> 0x0098 }
        r3.close();	 //Catch:{ Exception -> 0x007b }
    L_0x0049:
        r2.close();	 //Catch:{ Exception -> 0x007d }
    L_0x004c:
        r4.close();	 //Catch:{ Exception -> 0x007f }
    L_0x004f:
        r5.close();	 //Catch:{ Exception -> 0x0053 }
        goto L_0x0036;
    L_0x0053:
        r1 = move-exception;
        goto L_0x0036;
    L_0x0055:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r0 = r1;
    L_0x005b:
        r3.close();	 //Catch:{ Exception -> 0x0081 }
    L_0x005e:
        r2.close();	 //Catch:{ Exception -> 0x0083 }
    L_0x0061:
        r4.close();	 //Catch:{ Exception -> 0x0085 }
    L_0x0064:
        r5.close();	 //Catch:{ Exception -> 0x0087 }
    L_0x0067:
        throw r0;
    L_0x0068:
        r0 = move-exception;
        r0 = new java.lang.String;
        r1 = r5.toByteArray();
        r0.<init>(r1);
        goto L_0x0036;
    L_0x0073:
        r0 = move-exception;
        goto L_0x0022;
    L_0x0075:
        r0 = move-exception;
        goto L_0x0025;
    L_0x0077:
        r0 = move-exception;
        goto L_0x0028;
    L_0x0079:
        r0 = move-exception;
        goto L_0x002b;
    L_0x007b:
        r1 = move-exception;
        goto L_0x0049;
    L_0x007d:
        r1 = move-exception;
        goto L_0x004c;
    L_0x007f:
        r1 = move-exception;
        goto L_0x004f;
    L_0x0081:
        r1 = move-exception;
        goto L_0x005e;
    L_0x0083:
        r1 = move-exception;
        goto L_0x0061;
    L_0x0085:
        r1 = move-exception;
        goto L_0x0064;
    L_0x0087:
        r1 = move-exception;
        goto L_0x0067;
    L_0x0089:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r0 = r1;
        goto L_0x005b;
    L_0x008f:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        goto L_0x005b;
    L_0x0094:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x005b;
    L_0x0098:
        r0 = move-exception;
        goto L_0x005b;
    L_0x009a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x0043;
    L_0x009f:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0043;
    L_0x00a3:
        r1 = move-exception;
        r3 = r0;
        goto L_0x0043;
    L_0x00a6:
        r1 = move-exception;
        goto L_0x0043;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean encodeToFile(byte[] r5_byteA, String r6_String) {
        /*
        r0 = 1;
        r2 = 0;
        r1 = new qsbk.app.utils.Base64$OutputStream;	 //Catch:{ IOException -> 0x0014, all -> 0x001d }
        r3 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0014, all -> 0x001d }
        r3.<init>(r6);	 //Catch:{ IOException -> 0x0014, all -> 0x001d }
        r4 = 1;
        r1.<init>(r3, r4);	 //Catch:{ IOException -> 0x0014, all -> 0x001d }
        r1.write(r5);	 //Catch:{ IOException -> 0x0029, all -> 0x0026 }
        r1.close();	 //Catch:{ Exception -> 0x0022 }
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = r2;
    L_0x0016:
        r0 = 0;
        r1.close();	 //Catch:{ Exception -> 0x001b }
        goto L_0x0013;
    L_0x001b:
        r1 = move-exception;
        goto L_0x0013;
    L_0x001d:
        r0 = move-exception;
    L_0x001e:
        r2.close();	 //Catch:{ Exception -> 0x0024 }
    L_0x0021:
        throw r0;
    L_0x0022:
        r1 = move-exception;
        goto L_0x0013;
    L_0x0024:
        r1 = move-exception;
        goto L_0x0021;
    L_0x0026:
        r0 = move-exception;
        r2 = r1;
        goto L_0x001e;
    L_0x0029:
        r0 = move-exception;
        goto L_0x0016;
        */

    }

    public static final void main(String[] r4_StringA) {
        if (r4_StringA.length < 3) {
            a("Not enough arguments.");
        } else {
            String r0_String = r4_StringA[0];
            String r1_String = r4_StringA[1];
            String r2_String = r4_StringA[2];
            if (r0_String.equals("-e")) {
                encodeFileToFile(r1_String, r2_String);
            } else if (r0_String.equals("-d")) {
                decodeFileToFile(r1_String, r2_String);
            } else {
                a("Unknown flag: " + r0_String);
            }
        }
    }
}
package com.baidu.android.silentupdate;

import com.baidu.location.BDLocation;
import java.io.UnsupportedEncodingException;
import qsbk.app.widget.listview.XListViewHeader;

final class a {
    private static final byte[] a;

    static {
        a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    }

    public static String a_(byte[] r11_byteA, String r12_String) throws UnsupportedEncodingException {
        int r2i;
        int r0i = (r11_byteA.length * 4) / 3;
        byte[] r5_byteA = new byte[(r0i + r0i / 76 + 3)];
        int r6i = r11_byteA.length - r11_byteA.length % 3;
        int r3i = 0;
        r0i = 0;
        int r1i = 0;
        while (r3i < r6i) {
            r2i = r1i + 1;
            r5_byteA[r1i] = a[(r11_byteA[r3i] & 255) >> 2];
            r1i = r2i + 1;
            r5_byteA[r2i] = a[((r11_byteA[r3i] & 3) << 4) | ((r11_byteA[r3i + 1] & 255) >> 4)];
            r2i = r1i + 1;
            r5_byteA[r1i] = a[((r11_byteA[r3i + 1] & 15) << 2) | ((r11_byteA[r3i + 2] & 255) >> 6)];
            r1i = r2i + 1;
            r5_byteA[r2i] = a[r11_byteA[r3i + 2] & 63];
            if ((r1i - r0i) % 76 != 0 || r1i == 0) {
                r3i += 3;
            } else {
                r5_byteA[r1i] = (byte) 10;
                r0i++;
                r1i++;
                r3i += 3;
            }
        }
        switch ((r11_byteA.length % 3)) {
            case XListViewHeader.STATE_READY:
                r0i = r1i + 1;
                r5_byteA[r1i] = a[(r11_byteA[r6i] & 255) >> 2];
                r1i = r0i + 1;
                r5_byteA[r0i] = a[(r11_byteA[r6i] & 3) << 4];
                r2i = r1i + 1;
                r5_byteA[r1i] = (byte) 61;
                r0i = r2i + 1;
                r5_byteA[r2i] = (byte) 61;
                return new String(r5_byteA, 0, r0i, r12_String);
            case XListViewHeader.STATE_REFRESHING:
                r0i = r1i + 1;
                r5_byteA[r1i] = a[(r11_byteA[r6i] & 255) >> 2];
                r1i = r0i + 1;
                r5_byteA[r0i] = a[((r11_byteA[r6i] & 3) << 4) | ((r11_byteA[r6i + 1] & 255) >> 4)];
                r2i = r1i + 1;
                r5_byteA[r1i] = a[(r11_byteA[r6i + 1] & 15) << 2];
                r0i = r2i + 1;
                r5_byteA[r2i] = (byte) 61;
                return new String(r5_byteA, 0, r0i, r12_String);
        }
        r0i = r1i;
        return new String(r5_byteA, 0, r0i, r12_String);
    }

    public static byte[] a_(byte[] r1_byteA) {
        return a(r1_byteA, r1_byteA.length);
    }

    public static byte[] a_(byte[] r14_byteA, int r15i) {
        int r0i = (r15i / 4) * 3;
        if (r0i == 0) {
            return new byte[0];
        }
        byte[] r7_byteA = new byte[r0i];
        r0i = 0;
        while (true) {
            byte r2b = r14_byteA[r15i - 1];
            if (r2b == (byte) 10 || r2b == (byte) 13 || r2b == (byte) 32 || r2b == (byte) 9) {
                r15i--;
            } else if (r2b != 61) {
                int r2i;
                int r6i = 0;
                int r4i = 0;
                int r5i = 0;
                int r3i = 0;
                while (r6i < r15i) {
                    r2b = r14_byteA[r6i];
                    if (r2b == (byte) 10 || r2b == (byte) 13) {
                        r2i = r4i;
                        r4i = r3i;
                        r3i = r5i;
                        r6i++;
                        r5i = r3i;
                        r3i = r4i;
                        r4i = r2i;
                    } else if (r2b == (byte) 32) {
                        r2i = r4i;
                        r4i = r3i;
                        r3i = r5i;
                        r6i++;
                        r5i = r3i;
                        r3i = r4i;
                        r4i = r2i;
                    } else if (r2b == (byte) 9) {
                        r2i = r4i;
                        r4i = r3i;
                        r3i = r5i;
                        r6i++;
                        r5i = r3i;
                        r3i = r4i;
                        r4i = r2i;
                    } else {
                        if (r2b < 65 || r2b > 90) {
                            if (r2b < 97 || r2b > 122) {
                                if (r2b < 48 || r2b > 57) {
                                    if (r2b == 43) {
                                        r2i = BDLocation.TypeCriteriaException;
                                        r4i = (r4i << 6) | ((byte) r2i);
                                    } else {
                                        if (r2b != 47) {
                                            return null;
                                        }
                                        r2i = BDLocation.TypeNetWorkException;
                                        r4i = (r4i << 6) | ((byte) r2i);
                                    }
                                } else {
                                    r2i = r2b + 4;
                                    r4i = (r4i << 6) | ((byte) r2i);
                                }
                            } else {
                                r2i = r2b - 71;
                                r4i = (r4i << 6) | ((byte) r2i);
                            }
                        } else {
                            r2i = r2b - 65;
                            r4i = (r4i << 6) | ((byte) r2i);
                        }
                        if (r5i % 4 == 3) {
                            r2i = r3i + 1;
                            r7_byteA[r3i] = (byte) ((16711680 & r4i) >> 16);
                            r3i = r2i + 1;
                            r7_byteA[r2i] = (byte) ((65280 & r4i) >> 8);
                            r2i = r3i + 1;
                            r7_byteA[r3i] = (byte) (r4i & 255);
                        } else {
                            r2i = r3i;
                        }
                        r3i = r5i + 1;
                        r4i = r2i;
                        r2i = r4i;
                        r6i++;
                        r5i = r3i;
                        r3i = r4i;
                        r4i = r2i;
                    }
                }
                if (r0i > 0) {
                    r4i <<= r0i * 6;
                    r2i = r3i + 1;
                    r7_byteA[r3i] = (byte) ((16711680 & r4i) >> 16);
                    if (r0i == 1) {
                        r3i = r2i + 1;
                        r7_byteA[r2i] = (byte) ((65280 & r4i) >> 8);
                    } else {
                        r3i = r2i;
                    }
                }
                Object r0_Object = new Object[r3i];
                System.arraycopy(r7_byteA, 0, r0_Object, 0, r3i);
                return r0_Object;
            } else {
                r0i++;
                r15i--;
            }
        }
    }
}
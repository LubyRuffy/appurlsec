package com.tencent.mm.algorithm;

import java.util.Arrays;

public class Base64 {
    private static final char[] a;
    private static final int[] b;

    static {
        a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        int[] r0_intA = new int[256];
        b = r0_intA;
        Arrays.fill(r0_intA, -1);
        int r2i = a.length;
        int r0i = 0;
        while (r0i < r2i) {
            b[a[r0i]] = r0i;
            r0i++;
        }
        b[61] = 0;
    }

    public static final byte[] decode(String r9_String) {
        int r2i;
        r2i = r9_String != null ? r9_String.length() : 0;
        if (r2i == 0) {
            return new byte[0];
        }
        int r1i = 0;
        int r0i = 0;
        while (r1i < r2i) {
            if (b[r9_String.charAt(r1i)] < 0) {
                r0i++;
            }
            r1i++;
        }
        if ((r2i - r0i) % 4 != 0) {
            return null;
        }
        r1i = r2i;
        int r3i = 0;
        while (r1i > 1) {
            r1i--;
            if (b[r9_String.charAt(r1i)] <= 0) {
                if (r9_String.charAt(r1i) == '=') {
                    r3i++;
                }
            } else {
                break;
            }
        }
        int r7i = ((r2i - r0i) * 6) >> 3 - r3i;
        byte[] r3_byteA = new byte[r7i];
        int r6i = 0;
        r0i = 0;
        while (r6i < r7i) {
            r1i = 0;
            int r5i = r0i;
            r0i = 0;
            while (r0i < 4) {
                r2i = r5i + 1;
                r5i = b[r9_String.charAt(r5i)];
                if (r5i >= 0) {
                    r1i |= r5i << (18 - (r0i * 6));
                } else {
                    r0i--;
                }
                r0i++;
                r5i = r2i;
            }
            r0i = r6i + 1;
            r3_byteA[r6i] = (byte) (r1i >> 16);
            if (r0i < r7i) {
                r2i = r0i + 1;
                r3_byteA[r0i] = (byte) (r1i >> 8);
                if (r2i < r7i) {
                    r0i = r2i + 1;
                    r3_byteA[r2i] = (byte) r1i;
                } else {
                    r0i = r2i;
                }
            }
            r6i = r0i;
            r0i = r5i;
        }
        return r3_byteA;
    }

    public static final byte[] decode(byte[] r9_byteA) {
        int r2i = r9_byteA.length;
        int r1i = 0;
        int r0i = 0;
        while (r1i < r2i) {
            if (b[r9_byteA[r1i] & 255] < 0) {
                r0i++;
            }
            r1i++;
        }
        if ((r2i - r0i) % 4 != 0) {
            return null;
        }
        r1i = r2i;
        int r3i = 0;
        while (r1i > 1) {
            r1i--;
            if (b[r9_byteA[r1i] & 255] <= 0) {
                if (r9_byteA[r1i] == 61) {
                    r3i++;
                }
            } else {
                break;
            }
        }
        int r7i = ((r2i - r0i) * 6) >> 3 - r3i;
        byte[] r3_byteA = new byte[r7i];
        int r6i = 0;
        r0i = 0;
        while (r6i < r7i) {
            r1i = 0;
            int r5i = r0i;
            r0i = 0;
            while (r0i < 4) {
                r2i = r5i + 1;
                r5i = b[r9_byteA[r5i] & 255];
                if (r5i >= 0) {
                    r1i |= r5i << (18 - (r0i * 6));
                } else {
                    r0i--;
                }
                r0i++;
                r5i = r2i;
            }
            r0i = r6i + 1;
            r3_byteA[r6i] = (byte) (r1i >> 16);
            if (r0i < r7i) {
                r2i = r0i + 1;
                r3_byteA[r0i] = (byte) (r1i >> 8);
                if (r2i < r7i) {
                    r0i = r2i + 1;
                    r3_byteA[r2i] = (byte) r1i;
                } else {
                    r0i = r2i;
                }
            }
            r6i = r0i;
            r0i = r5i;
        }
        return r3_byteA;
    }

    public static final byte[] decode(char[] r9_charA) {
        int r2i;
        r2i = r9_charA != null ? r9_charA.length : 0;
        if (r2i == 0) {
            return new byte[0];
        }
        int r1i = 0;
        int r0i = 0;
        while (r1i < r2i) {
            if (b[r9_charA[r1i]] < 0) {
                r0i++;
            }
            r1i++;
        }
        if ((r2i - r0i) % 4 != 0) {
            return null;
        }
        r1i = r2i;
        int r3i = 0;
        while (r1i > 1) {
            r1i--;
            if (b[r9_charA[r1i]] <= 0) {
                if (r9_charA[r1i] == '=') {
                    r3i++;
                }
            } else {
                break;
            }
        }
        int r7i = ((r2i - r0i) * 6) >> 3 - r3i;
        byte[] r3_byteA = new byte[r7i];
        int r6i = 0;
        r0i = 0;
        while (r6i < r7i) {
            r1i = 0;
            int r5i = r0i;
            r0i = 0;
            while (r0i < 4) {
                r2i = r5i + 1;
                r5i = b[r9_charA[r5i]];
                if (r5i >= 0) {
                    r1i |= r5i << (18 - (r0i * 6));
                } else {
                    r0i--;
                }
                r0i++;
                r5i = r2i;
            }
            r0i = r6i + 1;
            r3_byteA[r6i] = (byte) (r1i >> 16);
            if (r0i < r7i) {
                r2i = r0i + 1;
                r3_byteA[r0i] = (byte) (r1i >> 8);
                if (r2i < r7i) {
                    r0i = r2i + 1;
                    r3_byteA[r2i] = (byte) r1i;
                } else {
                    r0i = r2i;
                }
            }
            r6i = r0i;
            r0i = r5i;
        }
        return r3_byteA;
    }

    public static final byte[] decodeFast(String r13_String) {
        int r1i = 0;
        int r2i = r13_String.length();
        if (r2i == 0) {
            return new byte[0];
        }
        int r6i;
        int r0i = r2i - 1;
        int r3i = 0;
        while (r3i < r0i && b[r13_String.charAt(r3i) & 255] < 0) {
            r3i++;
        }
        int r7i = r0i;
        while (r7i > 0 && b[r13_String.charAt(r7i) & 255] < 0) {
            r7i--;
        }
        if (r13_String.charAt(r7i) == '=') {
            r6i = r13_String.charAt(r7i + -1) == '=' ? 2 : 1;
        } else {
            r6i = 0;
        }
        int r4i = r7i - r3i + 1;
        if (r2i > 76) {
            r0i = (r13_String.charAt(76) == '\r' ? r4i / 78 : 0) << 1;
        } else {
            r0i = 0;
        }
        int r8i = ((r4i - r0i) * 6) >> 3 - r6i;
        byte[] r4_byteA = new byte[r8i];
        int r9i = (r8i / 3) * 3;
        r2i = 0;
        int r5i = 0;
        while (r5i < r9i) {
            int r11i = r3i + 1;
            int r12i = r11i + 1;
            r11i = r12i + 1;
            r3i = r11i + 1;
            int r10i = ((b[r13_String.charAt(r12i)] << 6) | ((b[r13_String.charAt(r3i)] << 18) | (b[r13_String.charAt(r11i)] << 12))) | b[r13_String.charAt(r11i)];
            r11i = r5i + 1;
            r4_byteA[r5i] = (byte) (r10i >> 16);
            r12i = r11i + 1;
            r4_byteA[r11i] = (byte) (r10i >> 8);
            r5i = r12i + 1;
            r4_byteA[r12i] = (byte) r10i;
            if (r0i > 0) {
                r2i++;
                if (r2i == 19) {
                    r3i += 2;
                    r2i = 0;
                }
            }
        }
        if (r5i < r8i) {
            r0i = r3i;
            r3i = 0;
            while (r0i <= r7i - r6i) {
                r1i++;
                r3i = (b[r13_String.charAt(r0i)] << (18 - (r1i * 6))) | r3i;
                r0i++;
            }
            r0i = qsbk.app.utils.Base64.URL_SAFE;
            r1i = r5i;
            while (r1i < r8i) {
                r4_byteA[r1i] = (byte) (r3i >> r0i);
                r0i -= 8;
                r1i++;
            }
        }
        return r4_byteA;
    }

    public static final byte[] decodeFast(byte[] r13_byteA) {
        int r1i = 0;
        int r2i = r13_byteA.length;
        if (r2i == 0) {
            return new byte[0];
        }
        int r6i;
        int r0i = r2i - 1;
        int r3i = 0;
        while (r3i < r0i && b[r13_byteA[r3i] & 255] < 0) {
            r3i++;
        }
        int r7i = r0i;
        while (r7i > 0 && b[r13_byteA[r7i] & 255] < 0) {
            r7i--;
        }
        if (r13_byteA[r7i] == (byte) 61) {
            r6i = r13_byteA[r7i + -1] == (byte) 61 ? 2 : 1;
        } else {
            r6i = 0;
        }
        int r4i = r7i - r3i + 1;
        if (r2i > 76) {
            r0i = (r13_byteA[76] == 13 ? r4i / 78 : 0) << 1;
        } else {
            r0i = 0;
        }
        int r8i = ((r4i - r0i) * 6) >> 3 - r6i;
        byte[] r4_byteA = new byte[r8i];
        int r9i = (r8i / 3) * 3;
        r2i = 0;
        int r5i = 0;
        while (r5i < r9i) {
            int r11i = r3i + 1;
            int r12i = r11i + 1;
            r11i = r12i + 1;
            r3i = r11i + 1;
            int r10i = ((b[r13_byteA[r12i]] << 6) | ((b[r13_byteA[r3i]] << 18) | (b[r13_byteA[r11i]] << 12))) | b[r13_byteA[r11i]];
            r11i = r5i + 1;
            r4_byteA[r5i] = (byte) (r10i >> 16);
            r12i = r11i + 1;
            r4_byteA[r11i] = (byte) (r10i >> 8);
            r5i = r12i + 1;
            r4_byteA[r12i] = (byte) r10i;
            if (r0i > 0) {
                r2i++;
                if (r2i == 19) {
                    r3i += 2;
                    r2i = 0;
                }
            }
        }
        if (r5i < r8i) {
            r0i = r3i;
            r3i = 0;
            while (r0i <= r7i - r6i) {
                r1i++;
                r3i = (b[r13_byteA[r0i]] << (18 - (r1i * 6))) | r3i;
                r0i++;
            }
            r0i = qsbk.app.utils.Base64.URL_SAFE;
            r1i = r5i;
            while (r1i < r8i) {
                r4_byteA[r1i] = (byte) (r3i >> r0i);
                r0i -= 8;
                r1i++;
            }
        }
        return r4_byteA;
    }

    public static final byte[] decodeFast(char[] r13_charA) {
        int r1i = 0;
        int r2i = r13_charA.length;
        if (r2i == 0) {
            return new byte[0];
        }
        int r6i;
        int r0i = r2i - 1;
        int r3i = 0;
        while (r3i < r0i && b[r13_charA[r3i]] < 0) {
            r3i++;
        }
        int r7i = r0i;
        while (r7i > 0 && b[r13_charA[r7i]] < 0) {
            r7i--;
        }
        if (r13_charA[r7i] == '=') {
            r6i = r13_charA[r7i + -1] == '=' ? 2 : 1;
        } else {
            r6i = 0;
        }
        int r4i = r7i - r3i + 1;
        if (r2i > 76) {
            r0i = (r13_charA[76] == '\r' ? r4i / 78 : 0) << 1;
        } else {
            r0i = 0;
        }
        int r8i = ((r4i - r0i) * 6) >> 3 - r6i;
        byte[] r4_byteA = new byte[r8i];
        int r9i = (r8i / 3) * 3;
        r2i = 0;
        int r5i = 0;
        while (r5i < r9i) {
            int r11i = r3i + 1;
            int r12i = r11i + 1;
            r11i = r12i + 1;
            r3i = r11i + 1;
            int r10i = ((b[r13_charA[r12i]] << 6) | ((b[r13_charA[r3i]] << 18) | (b[r13_charA[r11i]] << 12))) | b[r13_charA[r11i]];
            r11i = r5i + 1;
            r4_byteA[r5i] = (byte) (r10i >> 16);
            r12i = r11i + 1;
            r4_byteA[r11i] = (byte) (r10i >> 8);
            r5i = r12i + 1;
            r4_byteA[r12i] = (byte) r10i;
            if (r0i > 0) {
                r2i++;
                if (r2i == 19) {
                    r3i += 2;
                    r2i = 0;
                }
            }
        }
        if (r5i < r8i) {
            r0i = r3i;
            r3i = 0;
            while (r0i <= r7i - r6i) {
                r1i++;
                r3i = (b[r13_charA[r0i]] << (18 - (r1i * 6))) | r3i;
                r0i++;
            }
            r0i = qsbk.app.utils.Base64.URL_SAFE;
            r1i = r5i;
            while (r1i < r8i) {
                r4_byteA[r1i] = (byte) (r3i >> r0i);
                r0i -= 8;
                r1i++;
            }
        }
        return r4_byteA;
    }

    public static final byte[] encodeToByte(byte[] r14_byteA, boolean r15z) {
        int r6i;
        int r1i = 0;
        r6i = r14_byteA != null ? r14_byteA.length : 0;
        if (r6i == 0) {
            return new byte[0];
        }
        int r7i = (r6i / 3) * 3;
        int r2i = (((r6i - 1) / 3) + 1) << 2;
        int r8i = r2i + (r15z ? ((r2i - 1) / 76) << 1 : 0);
        byte[] r4_byteA = new byte[r8i];
        int r0i = 0;
        r2i = 0;
        int r5i = 0;
        while (r5i < r7i) {
            int r9i = r5i + 1;
            int r10i = r9i + 1;
            r5i = r10i + 1;
            r9i = (((r14_byteA[r9i] & 255) << 8) | ((r14_byteA[r5i] & 255) << 16)) | (r14_byteA[r10i] & 255);
            r10i = r2i + 1;
            r4_byteA[r2i] = (byte) a[(r9i >>> 18) & 63];
            r2i = r10i + 1;
            r4_byteA[r10i] = (byte) a[(r9i >>> 12) & 63];
            r10i = r2i + 1;
            r4_byteA[r2i] = (byte) a[(r9i >>> 6) & 63];
            r2i = r10i + 1;
            r4_byteA[r10i] = (byte) a[r9i & 63];
            if (r15z) {
                r0i++;
                if (r0i != 19 || r2i >= r8i - 2) {
                } else {
                    r9i = r2i + 1;
                    r4_byteA[r2i] = (byte) 13;
                    r4_byteA[r9i] = (byte) 10;
                    r2i = r9i + 1;
                    r0i = 0;
                }
            }
        }
        r0i = r6i - r7i;
        if (r0i > 0) {
            r2i = (r14_byteA[r7i] & 255) << 10;
            if (r0i == 2) {
                r1i = (r14_byteA[r6i - 1] & 255) << 2;
            }
            r1i |= r2i;
            r4_byteA[r8i - 4] = (byte) a[r1i >> 12];
            r4_byteA[r8i - 3] = (byte) a[(r1i >>> 6) & 63];
            r4_byteA[r8i - 2] = r0i == 2 ? (byte) a[r1i & 63] : (byte) 61;
            r4_byteA[r8i - 1] = (byte) 61;
        }
        return r4_byteA;
    }

    public static final char[] encodeToChar(byte[] r14_byteA, boolean r15z) {
        int r6i;
        int r1i = 0;
        r6i = r14_byteA != null ? r14_byteA.length : 0;
        if (r6i == 0) {
            return new char[0];
        }
        int r7i = (r6i / 3) * 3;
        int r2i = (((r6i - 1) / 3) + 1) << 2;
        int r8i = r2i + (r15z ? ((r2i - 1) / 76) << 1 : 0);
        char[] r4_charA = new char[r8i];
        int r0i = 0;
        r2i = 0;
        int r5i = 0;
        while (r5i < r7i) {
            int r9i = r5i + 1;
            int r10i = r9i + 1;
            r5i = r10i + 1;
            r9i = (((r14_byteA[r9i] & 255) << 8) | ((r14_byteA[r5i] & 255) << 16)) | (r14_byteA[r10i] & 255);
            r10i = r2i + 1;
            r4_charA[r2i] = a[(r9i >>> 18) & 63];
            r2i = r10i + 1;
            r4_charA[r10i] = a[(r9i >>> 12) & 63];
            r10i = r2i + 1;
            r4_charA[r2i] = a[(r9i >>> 6) & 63];
            r2i = r10i + 1;
            r4_charA[r10i] = a[r9i & 63];
            if (r15z) {
                r0i++;
                if (r0i != 19 || r2i >= r8i - 2) {
                } else {
                    r9i = r2i + 1;
                    r4_charA[r2i] = '\r';
                    r4_charA[r9i] = '\n';
                    r2i = r9i + 1;
                    r0i = 0;
                }
            }
        }
        r0i = r6i - r7i;
        if (r0i > 0) {
            r2i = (r14_byteA[r7i] & 255) << 10;
            if (r0i == 2) {
                r1i = (r14_byteA[r6i - 1] & 255) << 2;
            }
            r1i |= r2i;
            r4_charA[r8i - 4] = a[r1i >> 12];
            r4_charA[r8i - 3] = a[(r1i >>> 6) & 63];
            r4_charA[r8i - 2] = r0i == 2 ? a[r1i & 63] : '=';
            r4_charA[r8i - 1] = '=';
        }
        return r4_charA;
    }

    public static final String encodeToString(byte[] r2_byteA, boolean r3z) {
        return new String(encodeToChar(r2_byteA, r3z));
    }
}
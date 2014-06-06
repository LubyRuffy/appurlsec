package com.tencent.mm.sdk.platformtools;

public class CharSequences {
    static void a(int r1i, int r2i, int r3i) {
        if (r1i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (r2i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (r2i > r3i) {
            throw new IndexOutOfBoundsException();
        } else if (r1i > r2i) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static int compareToIgnoreCase(CharSequence r7_CharSequence, CharSequence r8_CharSequence) {
        int r0i;
        int r3i = 0;
        int r1i = r7_CharSequence.length();
        int r2i = r8_CharSequence.length();
        r0i = r1i < r2i ? r1i : r2i;
        int r4i = 0;
        while (r4i < r0i) {
            int r5i = r4i + 1;
            r4i = r3i + 1;
            r3i = Character.toLowerCase(r7_CharSequence.charAt(r4i)) - Character.toLowerCase(r8_CharSequence.charAt(r3i));
            if (r3i != 0) {
                return r3i;
            }
            r3i = r4i;
            r4i = r5i;
        }
        return r1i - r2i;
    }

    public static boolean equals(CharSequence r5_CharSequence, CharSequence r6_CharSequence) {
        if (r5_CharSequence.length() != r6_CharSequence.length()) {
            return false;
        }
        int r2i = r5_CharSequence.length();
        int r1i = 0;
        while (r1i < r2i) {
            if (r5_CharSequence.charAt(r1i) != r6_CharSequence.charAt(r1i)) {
                return false;
            }
            r1i++;
        }
        return true;
    }

    public static CharSequence forAsciiBytes(byte[] r1_byteA) {
        return new c(r1_byteA);
    }

    public static CharSequence forAsciiBytes(byte[] r1_byteA, int r2i, int r3i) {
        a(r2i, r3i, r1_byteA.length);
        return new d(r1_byteA, r2i, r3i);
    }
}
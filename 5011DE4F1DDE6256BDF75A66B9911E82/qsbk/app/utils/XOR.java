package qsbk.app.utils;

public class XOR {
    public static String encode(String r6_String, String r7_String) {
        byte[] r1_byteA = r6_String.getBytes();
        byte[] r2_byteA = r7_String.getBytes();
        byte[] r3_byteA = new byte[r1_byteA.length];
        int r0i = 0;
        while (r0i < r1_byteA.length) {
            r3_byteA[r0i] = (byte) (r1_byteA[r0i] ^ r2_byteA[r0i % r2_byteA.length]);
            r0i++;
        }
        return Base64.encodeBytes(r3_byteA);
    }
}
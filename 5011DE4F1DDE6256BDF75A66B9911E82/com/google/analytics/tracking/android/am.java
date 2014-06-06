package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import qsbk.app.bean.Base;

// compiled from: Utils.java
class am {
    private static final char[] a;

    static {
        a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    static int a(char r2c) {
        int r0i = r2c - 48;
        return r0i > 9 ? r0i - 7 : r0i;
    }

    static String a(Locale r3_Locale) {
        if (r3_Locale == null || TextUtils.isEmpty(r3_Locale.getLanguage())) {
            return null;
        }
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(r3_Locale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(r3_Locale.getCountry())) {
            r0_StringBuilder.append("-").append(r3_Locale.getCountry().toLowerCase());
        }
        return r0_StringBuilder.toString();
    }

    static String a(byte[] r6_byteA) {
        char[] r1_charA = new char[(r6_byteA.length * 2)];
        int r0i = 0;
        while (r0i < r6_byteA.length) {
            int r2i = r6_byteA[r0i] & 255;
            r1_charA[r0i * 2] = a[r2i >> 4];
            r1_charA[r0i * 2 + 1] = a[r2i & 15];
            r0i++;
        }
        return new String(r1_charA);
    }

    static byte[] a(String r4_String) {
        byte[] r1_byteA = new byte[(r4_String.length() / 2)];
        int r0i = 0;
        while (r0i < r1_byteA.length) {
            r1_byteA[r0i] = (byte) ((a(r4_String.charAt(r0i * 2)) << 4) | a(r4_String.charAt((r0i * 2) + 1)));
            r0i++;
        }
        return r1_byteA;
    }

    public static String filterCampaign(String r6_String) {
        int r4i = 1;
        if (TextUtils.isEmpty(r6_String)) {
            return null;
        }
        if (r6_String.contains("?")) {
            r6_String = r6_String.split("[\\?]")[1];
        }
        if (r6_String.contains("%3D")) {
            try {
                r6_String = URLDecoder.decode(r6_String, Base.UTF8);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else if (!r6_String.contains("=")) {
            return null;
        }
        Map r2_Map = parseURLParameters(r6_String);
        String[] r3_StringA = new String[9];
        r3_StringA[0] = ModelFields.DCLID;
        r3_StringA[r4i] = "utm_source";
        r3_StringA[2] = ModelFields.GCLID;
        r3_StringA[3] = "utm_campaign";
        r3_StringA[4] = "utm_medium";
        r3_StringA[5] = "utm_term";
        r3_StringA[6] = "utm_content";
        r3_StringA[7] = "utm_id";
        r3_StringA[8] = ModelFields.GMOB_T;
        StringBuilder r4_StringBuilder = new StringBuilder();
        int r1i = 0;
        while (r1i < r3_StringA.length) {
            if (!TextUtils.isEmpty((CharSequence) r2_Map.get(r3_StringA[r1i]))) {
                if (r4_StringBuilder.length() > 0) {
                    r4_StringBuilder.append("&");
                }
                r4_StringBuilder.append(r3_StringA[r1i]).append("=").append((String) r2_Map.get(r3_StringA[r1i]));
            }
            r1i++;
        }
        return r4_StringBuilder.toString();
    }

    public static Map<String, String> parseURLParameters(String r8_String) {
        Map<String, String> r2_Map_String__String = new HashMap();
        String[] r3_StringA = r8_String.split("&");
        int r4i = r3_StringA.length;
        int r0i = 0;
        while (r0i < r4i) {
            String[] r5_StringA = r3_StringA[r0i].split("=");
            if (r5_StringA.length > 1) {
                r2_Map_String__String.put(r5_StringA[0], r5_StringA[1]);
            } else if (r5_StringA.length != 1 || r5_StringA[0].length() == 0) {
                r0i++;
            } else {
                r2_Map_String__String.put(r5_StringA[0], null);
            }
            r0i++;
        }
        return r2_Map_String__String;
    }

    public static boolean safeParseBoolean(String r1_String) {
        return Boolean.parseBoolean(r1_String);
    }

    public static double safeParseDouble(String r3_String) {
        if (r3_String == null) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(r3_String);
        } catch (NumberFormatException e) {
            return 0.0d;
        }
    }

    public static long safeParseLong(String r3_String) {
        if (r3_String == null) {
            return 0;
        }
        try {
            return Long.parseLong(r3_String);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
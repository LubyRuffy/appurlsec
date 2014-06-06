package qsbk.app.thirdparty.Utility;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import qsbk.app.bean.Base;
import qsbk.app.thirdparty.ThirdPartyParameters;

public class Utility {
    private static char[] a;
    private static byte[] b;

    static {
        a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        b = new byte[256];
    }

    private static boolean a(ThirdPartyParameters r1_ThirdPartyParameters) {
        return r1_ThirdPartyParameters == null || r1_ThirdPartyParameters.size() == 0;
    }

    public static byte[] decodeBase62(String r9_String) {
        int r0i = 0;
        if (r9_String == null) {
            return null;
        }
        char[] r5_charA = r9_String.toCharArray();
        ByteArrayOutputStream r6_ByteArrayOutputStream = new ByteArrayOutputStream(r9_String.toCharArray().length);
        int r1i = 0;
        int r2i = 0;
        while (r0i < r5_charA.length) {
            int r4i;
            char r0c;
            char r4c = r5_charA[r0i];
            if (r4c == 'i') {
                r4i = r0i + 1;
                r0c = r5_charA[r4i];
                if (r0c == 'a') {
                    r0c = 'i';
                } else if (r0c == 'b') {
                    r0c = '+';
                } else if (r0c == 'c') {
                    r0c = '/';
                } else {
                    r4i--;
                    r0c = r5_charA[r4i];
                }
            } else {
                r4i = r0i;
                r0c = r4c;
            }
            r1i = (r1i << 6) | b[r0c];
            r2i += 6;
            while (r2i > 7) {
                r2i -= 8;
                r6_ByteArrayOutputStream.write(r1i >> r2i);
                r1i &= (1 << r2i) - 1;
            }
            r0i = r4i + 1;
        }
        return r6_ByteArrayOutputStream.toByteArray();
    }

    public static Bundle decodeUrl(String r8_String) {
        Bundle r2_Bundle = new Bundle();
        if (r8_String != null) {
            String[] r3_StringA = r8_String.split("&");
            int r4i = r3_StringA.length;
            int r0i = 0;
            while (r0i < r4i) {
                String[] r5_StringA = r3_StringA[r0i].split("=");
                r2_Bundle.putString(URLDecoder.decode(r5_StringA[0]), URLDecoder.decode(r5_StringA[1]));
                r0i++;
            }
        }
        return r2_Bundle;
    }

    public static String encodeBase62(byte[] r9_byteA) {
        int r0i = 0;
        StringBuffer r4_StringBuffer = new StringBuffer(r9_byteA.length * 2);
        int r1i = 0;
        int r2i = 0;
        while (r0i < r9_byteA.length) {
            r1i = (r1i << 8) | (r9_byteA[r0i] & 255);
            r2i += 8;
            while (r2i > 5) {
                Object r2_Object;
                int r3i = r2i - 6;
                char r2c = a[r1i >> r3i];
                if (r2c == 'i') {
                    r2_Object = "ia";
                } else if (r2c == '+') {
                    r2_Object = "ib";
                } else if (r2c == '/') {
                    r2_Object = "ic";
                } else {
                    r2_Object = Character.valueOf(r2c);
                }
                r4_StringBuffer.append(r2_Object);
                r1i &= (1 << r3i) - 1;
                r2i = r3i;
            }
            r0i++;
        }
        if (r2i > 0) {
            Object r0_Object;
            char r0c = a[r1i << (6 - r2i)];
            if (r0c == 'i') {
                r0_Object = "ia";
            } else if (r0c == '+') {
                r0_Object = "ib";
            } else if (r0c == '/') {
                r0_Object = "ic";
            } else {
                r0_Object = Character.valueOf(r0c);
            }
            r4_StringBuffer.append(r0_Object);
        }
        return r4_StringBuffer.toString();
    }

    public static String encodeParameters(ThirdPartyParameters r6_ThirdPartyParameters) {
        int r0i = 0;
        if (r6_ThirdPartyParameters == null || a(r6_ThirdPartyParameters)) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r2_StringBuilder = new StringBuilder();
        int r1i = 0;
        while (r0i < r6_ThirdPartyParameters.size()) {
            String r3_String = r6_ThirdPartyParameters.getKey(r0i);
            if (r1i != 0) {
                r2_StringBuilder.append("&");
            }
            try {
                r2_StringBuilder.append(URLEncoder.encode(r3_String, Base.UTF8)).append("=").append(URLEncoder.encode(r6_ThirdPartyParameters.getValue(r3_String), Base.UTF8));
            } catch (UnsupportedEncodingException e) {
            }
            r1i++;
            r0i++;
        }
        return r2_StringBuilder.toString();
    }

    public static String encodeUrl(ThirdPartyParameters r8_ThirdPartyParameters) {
        if (r8_ThirdPartyParameters == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r3_StringBuilder = new StringBuilder();
        int r2i = 1;
        int r0i = 0;
        while (r0i < r8_ThirdPartyParameters.size()) {
            if (r2i != 0) {
                r2i = 0;
            } else {
                r3_StringBuilder.append("&");
            }
            String r4_String = r8_ThirdPartyParameters.getKey(r0i);
            if (r8_ThirdPartyParameters.getValue(r4_String) == null) {
                Log.i("encodeUrl", "key:" + r4_String + " 's value is null");
            } else {
                r3_StringBuilder.append(URLEncoder.encode(r8_ThirdPartyParameters.getKey(r0i)) + "=" + URLEncoder.encode(r8_ThirdPartyParameters.getValue(r0i)));
            }
            r0i++;
        }
        return r3_StringBuilder.toString();
    }

    public static boolean isWifi(Context r2_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r2_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null && r0_NetworkInfo.getType() == 1;
    }

    public static Bundle parseUrl(String r2_String) {
        try {
            URL r1_URL = new URL(r2_String);
            Bundle r0_Bundle = decodeUrl(r1_URL.getQuery());
            r0_Bundle.putAll(decodeUrl(r1_URL.getRef()));
            return r0_Bundle;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static void showAlert(Context r1_Context, String r2_String, String r3_String) {
        Builder r0_Builder = new Builder(r1_Context);
        r0_Builder.setTitle(r2_String);
        r0_Builder.setMessage(r3_String);
        r0_Builder.create().show();
    }
}
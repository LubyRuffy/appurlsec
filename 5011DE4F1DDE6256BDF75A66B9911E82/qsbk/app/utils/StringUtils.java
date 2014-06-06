package qsbk.app.utils;

import android.content.ClipData;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.ClipboardManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern a;
    private static final SimpleDateFormat b;
    private static final SimpleDateFormat c;

    static {
        a = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static void copyToClipboard(String r2_String, Context r3_Context) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) r3_Context.getSystemService("clipboard")).setText(r2_String);
        } else {
            ((android.content.ClipboardManager) r3_Context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(r2_String, r2_String));
        }
    }

    public static String friendly_time(String r9_String) {
        Date r1_Date = toDate(r9_String);
        if (r1_Date == null) {
            return "Unknown";
        }
        String r0_String = RContactStorage.PRIMARY_KEY;
        Calendar r2_Calendar = Calendar.getInstance();
        int r0i;
        if (c.format(r2_Calendar.getTime()).equals(c.format(r1_Date))) {
            r0i = (int) ((r2_Calendar.getTimeInMillis() - r1_Date.getTime()) / 3600000);
            return r0i == 0 ? Math.max((r2_Calendar.getTimeInMillis() - r1_Date.getTime()) / 60000, 1) + "\u5206\u949f\u524d" : r0i + "\u5c0f\u65f6\u524d";
        } else {
            int r3i = (int) (r2_Calendar.getTimeInMillis() / 86400000 - r1_Date.getTime() / 86400000);
            if (r3i == 0) {
                r0i = (int) ((r2_Calendar.getTimeInMillis() - r1_Date.getTime()) / 3600000);
                return r0i == 0 ? Math.max((r2_Calendar.getTimeInMillis() - r1_Date.getTime()) / 60000, 1) + "\u5206\u949f\u524d" : r0i + "\u5c0f\u65f6\u524d";
            } else {
                if (r3i == 1) {
                    return "\u6628\u5929";
                }
                if (r3i == 2) {
                    return "\u524d\u5929";
                }
                if (r3i > 2 && r3i <= 10) {
                    return r3i + "\u5929\u524d";
                }
                if (r3i > 10) {
                    return c.format(r1_Date);
                }
                return r0_String;
            }
        }
    }

    public static boolean isEmail(String r1_String) {
        return (r1_String == null || r1_String.trim().length() == 0) ? false : a.matcher(r1_String).matches();
    }

    public static boolean isEmpty(String r5_String) {
        if (r5_String == null || RContactStorage.PRIMARY_KEY.equals(r5_String)) {
            return true;
        }
        int r0i = 0;
        while (r0i < r5_String.length()) {
            char r3c = r5_String.charAt(r0i);
            if (r3c != ' ' && r3c != '\t' && r3c != '\r' && r3c != '\n') {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean isToday(String r4_String) {
        Date r1_Date = toDate(r4_String);
        Date r2_Date = new Date();
        return r1_Date != null && c.format(r2_Date).equals(c.format(r1_Date));
    }

    public static String replaceHtml(String r2_String) {
        return Pattern.compile("<.+?>|\t|\r|\n{2,}").matcher(r2_String).replaceAll(RContactStorage.PRIMARY_KEY);
    }

    public static boolean toBool(String r1_String) {
        try {
            return Boolean.parseBoolean(r1_String);
        } catch (Exception e) {
            return false;
        }
    }

    public static Date toDate(String r1_String) {
        try {
            return b.parse(r1_String);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int toInt(Object r2_Object) {
        int r0i = 0;
        return r2_Object == null ? 0 : toInt(r2_Object.toString(), r0i);
    }

    public static int toInt(String r1_String, int r2i) {
        try {
            return Integer.parseInt(r1_String);
        } catch (Exception e) {
            return r2i;
        }
    }

    public static long toLong(String r2_String) {
        try {
            return Long.parseLong(r2_String);
        } catch (Exception e) {
            return 0;
        }
    }
}
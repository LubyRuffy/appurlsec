package qsbk.app.utils;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.message.util.ACache;
import qsbk.app.share.ShareUtils;

public class DateUtil {
    public static StringBuffer Get_DiffDate_Info(Context r10_Context, long r11j, int r13i) {
        int r0i;
        StringBuffer r1_StringBuffer = new StringBuffer();
        Calendar r0_Calendar = Calendar.getInstance();
        r0i = r13i > 0 ? (int) (r11j - r0_Calendar.getTimeInMillis() / 1000) : (int) (r0_Calendar.getTimeInMillis() / 1000 - r11j);
        if (r0i > 2592000) {
            r1_StringBuffer.append("\u53d1\u8868\u4e8e" + getTody(new Date(r11j * 1000)));
        } else if (r0i > 86400) {
            r2_String = r10_Context.getString(R.string.days_before);
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = Integer.valueOf(r0i / 86400);
            r1_StringBuffer.append(String.format(r2_String, r3_ObjectA));
        } else if (r0i > 3600) {
            r2_String = r10_Context.getString(R.string.hours_before);
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = Integer.valueOf(r0i / 3600);
            r1_StringBuffer.append(String.format(r2_String, r3_ObjectA));
        } else if (r0i > 60) {
            r2_String = r10_Context.getString(R.string.minutes_before);
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = Integer.valueOf(r0i / 60);
            r1_StringBuffer.append(String.format(r2_String, r3_ObjectA));
        } else if (r0i > 0) {
            r1_StringBuffer.append(r10_Context.getString(R.string.seconds_before));
        } else {
            r1_StringBuffer.append(r10_Context.getString(R.string.seconds_before));
        }
        return r1_StringBuffer;
    }

    public static String getSpecifiedDayAfter(Date r3_Date) {
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.setTime(r3_Date);
        r0_Calendar.set(ShareUtils.SHARE_SMS, r0_Calendar.get(ShareUtils.SHARE_SMS) + 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(r0_Calendar.getTime());
    }

    public static String getSpecifiedDayBefore(Date r3_Date) {
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.setTime(r3_Date);
        r0_Calendar.set(ShareUtils.SHARE_SMS, r0_Calendar.get(ShareUtils.SHARE_SMS) - 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(r0_Calendar.getTime());
    }

    public static String getTimePostStr(int r5i) {
        int r1i = ACache.TIME_DAY;
        Context r0_Context = QsbkApp.mContext;
        String r0_String;
        Object[] r2_ObjectA;
        if (r5i > 86400) {
            r0_String = r0_Context.getString(R.string.nearby_days_before);
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Integer.valueOf(r5i / r1i);
            return String.format(r0_String, r2_ObjectA);
        } else if (r5i > 3600) {
            r0_String = r0_Context.getString(R.string.nearby_hours_before);
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Integer.valueOf(r5i / 3600);
            return String.format(r0_String, r2_ObjectA);
        } else {
            if (r5i <= 60) {
                return r0_Context.getString(R.string.seconds_before);
            }
            r0_String = r0_Context.getString(R.string.nearby_minutes_before);
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = Integer.valueOf(r5i / 60);
            return String.format(r0_String, r2_ObjectA);
        }
    }

    public static String getTody(Date r3_Date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(r3_Date.getTime()));
    }
}
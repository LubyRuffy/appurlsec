package qsbk.app.utils;

import java.util.Calendar;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class AstrologyUtils {
    public static final String[] ASTROLOGY;
    public static final int[] ASTROLOGY_DATE_START_EDGE;

    static {
        String[] r0_StringA = new String[12];
        r0_StringA[0] = "\u6c34\u74f6\u5ea7";
        r0_StringA[1] = "\u53cc\u9c7c\u5ea7";
        r0_StringA[2] = "\u7261\u7f8a\u5ea7";
        r0_StringA[3] = "\u91d1\u725b\u5ea7";
        r0_StringA[4] = "\u53cc\u5b50\u5ea7";
        r0_StringA[5] = "\u5de8\u87f9\u5ea7";
        r0_StringA[6] = "\u72ee\u5b50\u5ea7";
        r0_StringA[7] = "\u5904\u5973\u5ea7";
        r0_StringA[8] = "\u5929\u79e4\u5ea7";
        r0_StringA[9] = "\u5929\u874e\u5ea7";
        r0_StringA[10] = "\u5c04\u624b\u5ea7";
        r0_StringA[11] = "\u9b54\u7faf\u5ea7";
        ASTROLOGY = r0_StringA;
        ASTROLOGY_DATE_START_EDGE = new int[]{21, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    }

    private AstrologyUtils() {
    }

    public static final String date2Astrology(Calendar r3_Calendar) {
        int r0i = r3_Calendar.get(XListViewHeader.STATE_REFRESHING);
        if (r3_Calendar.get(ShareUtils.SHARE_SMS) < ASTROLOGY_DATE_START_EDGE[r0i]) {
            r0i--;
        }
        return r0i >= 0 ? ASTROLOGY[r0i] : ASTROLOGY[11];
    }

    public static final int getAge(Calendar r7_Calendar) {
        int r6i = ShareUtils.SHARE_SMS;
        int r5i = XListViewHeader.STATE_REFRESHING;
        int r1i = 1;
        Calendar r3_Calendar = Calendar.getInstance();
        int r0i = Math.max(0, r3_Calendar.get(1) - r7_Calendar.get(1));
        if (r0i <= 0) {
            return r0i;
        }
        if (r3_Calendar.get(r6i) + r3_Calendar.get(r5i) * 100 >= r7_Calendar.get(r5i) * 100 + r7_Calendar.get(r6i)) {
            return r1i != 0 ? r0i - 1 : r0i;
        } else {
            r1i = 0;
            if (r1i != 0) {
            }
        }
    }
}
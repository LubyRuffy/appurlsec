package qsbk.app.report;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.bean.ReportBean;
import qsbk.app.utils.SharePreferenceUtils;

public final class ReportUtils {
    public static final ReportUtils INSTANCE;
    public static List<ReportBean> RESOURCE;
    private static int[] a;
    private static String[] b;
    private static boolean c;

    static {
        a = new int[]{1, 2, 3, 4};
        String[] r0_StringA = new String[4];
        r0_StringA[0] = "\u6beb\u65e0\u7b11\u70b9";
        r0_StringA[1] = "\u8272\u60c5\u4f4e\u4fd7";
        r0_StringA[2] = "\u9690\u79c1\u6cc4\u6f0f";
        r0_StringA[3] = "\u717d\u52a8\u60c5\u7eea";
        b = r0_StringA;
        INSTANCE = new ReportUtils();
        a();
        c = false;
    }

    private ReportUtils() {
    }

    private static void a() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("report-article");
        if (c || (!a(r0_String))) {
            int r1i = b.length;
            RESOURCE = new ArrayList(r1i);
            int r0i = 0;
            while (r0i < r1i) {
                RESOURCE.add(new ReportBean(b[r0i], a[r0i]));
                r0i++;
            }
        } else {
            c = true;
            reset(r0_String);
        }
    }

    private static boolean a(String r2_String) {
        return r2_String != null && r2_String.trim().length() > 2;
    }

    public static void reset(String r5_String) {
        String r0_String = "\\[.*?]";
        if (a(r5_String)) {
            if (RESOURCE != null) {
                RESOURCE.clear();
            } else {
                RESOURCE = new ArrayList();
            }
            try {
                Matcher r0_Matcher = Pattern.compile(r0_String).matcher(r5_String.substring(1, r5_String.length() - 1));
                while (r0_Matcher.find()) {
                    String r1_String = r0_Matcher.group(0);
                    String[] r1_StringA = r1_String.substring(1, r1_String.length() - 1).replaceAll("\"", RContactStorage.PRIMARY_KEY).split(",");
                    RESOURCE.add(new ReportBean(r1_StringA[0], Integer.parseInt(r1_StringA[1])));
                }
            } catch (Exception e) {
                a();
            }
        }
    }
}
package android.support.v4.text;

import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Locale;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.widget.listview.XListViewHeader;

public class TextUtilsCompat {
    public static final Locale ROOT;
    private static String a;
    private static String b;

    static {
        ROOT = new Locale(RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY);
        a = "Arab";
        b = "Hebr";
    }

    private static int a(Locale r2_Locale) {
        switch (Character.getDirectionality(r2_Locale.getDisplayName(r2_Locale).charAt(0))) {
            case XListViewHeader.STATE_READY:
            case XListViewHeader.STATE_REFRESHING:
                return 1;
        }
        return 0;
    }

    public static int getLayoutDirectionFromLocale(Locale r2_Locale) {
        if (r2_Locale == null || r2_Locale.equals(ROOT)) {
            return 0;
        }
        String r0_String = ICUCompat.getScript(ICUCompat.addLikelySubtags(r2_Locale.toString()));
        if (r0_String == null) {
            return a(r2_Locale);
        }
        if (r0_String.equalsIgnoreCase(a) || r0_String.equalsIgnoreCase(b)) {
            return 1;
        }
        return 0;
    }

    public static String htmlEncode(String r3_String) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r0i = 0;
        while (r0i < r3_String.length()) {
            char r2c = r3_String.charAt(r0i);
            switch (r2c) {
                case AdViewUtil.NETWORK_TYPE_AIRAD:
                    r1_StringBuilder.append("&quot;");
                    r0i++;
                    break;
                case AdViewUtil.NETWORK_TYPE_BAIDU:
                    r1_StringBuilder.append("&amp;");
                    r0i++;
                    break;
                case AdViewUtil.NETWORK_TYPE_LSENSE:
                    r1_StringBuilder.append("&#39;");
                    r0i++;
                    break;
                case NearbySelectView.TIME_60MIN:
                    r1_StringBuilder.append("&lt;");
                    r0i++;
                    break;
                case BDLocation.TypeCriteriaException:
                    r1_StringBuilder.append("&gt;");
                    r0i++;
                    break;
            }
            r1_StringBuilder.append(r2c);
            r0i++;
        }
        return r1_StringBuilder.toString();
    }
}
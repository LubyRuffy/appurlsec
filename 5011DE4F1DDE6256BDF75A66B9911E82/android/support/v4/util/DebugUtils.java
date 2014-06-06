package android.support.v4.util;

import com.qiubai.library.adview.util.AdViewUtil;

public class DebugUtils {
    public static void buildShortClassTag(Object r2_Object, StringBuilder r3_StringBuilder) {
        if (r2_Object == null) {
            r3_StringBuilder.append("null");
        } else {
            String r0_String = r2_Object.getClass().getSimpleName();
            if (r0_String == null || r0_String.length() <= 0) {
                r0_String = r2_Object.getClass().getName();
                int r1i = r0_String.lastIndexOf(AdViewUtil.NETWORK_TYPE_MOBWIN);
                if (r1i > 0) {
                    r0_String = r0_String.substring(r1i + 1);
                }
                r3_StringBuilder.append(r0_String);
                r3_StringBuilder.append('{');
                r3_StringBuilder.append(Integer.toHexString(System.identityHashCode(r2_Object)));
            } else {
                r3_StringBuilder.append(r0_String);
                r3_StringBuilder.append('{');
                r3_StringBuilder.append(Integer.toHexString(System.identityHashCode(r2_Object)));
            }
        }
    }
}
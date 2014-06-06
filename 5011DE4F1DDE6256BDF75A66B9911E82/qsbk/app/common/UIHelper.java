package qsbk.app.common;

import android.app.AlertDialog.Builder;
import android.content.Context;
import qsbk.app.R;

public class UIHelper {
    public static void sendAppCrashReport(Context r3_Context, String r4_String) {
        Builder r0_Builder = new Builder(r3_Context);
        r0_Builder.setIcon(17301659);
        r0_Builder.setTitle(R.string.app_error);
        r0_Builder.setMessage(R.string.app_error_message);
        r0_Builder.setPositiveButton(R.string.submit_report, new a(r4_String, r3_Context));
        r0_Builder.setNegativeButton(R.string.sure, new b(r3_Context));
        r0_Builder.show();
    }
}
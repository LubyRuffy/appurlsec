package qsbk.app.report;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

// compiled from: ReportActivity.java
class c implements OnCancelListener {
    final /* synthetic */ ReportActivity a;

    c(ReportActivity r1_ReportActivity) {
        this.a = r1_ReportActivity;
    }

    public void onCancel(DialogInterface r2_DialogInterface) {
        this.a.finish();
    }
}
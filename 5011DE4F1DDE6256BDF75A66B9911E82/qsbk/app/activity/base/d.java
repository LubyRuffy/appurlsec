package qsbk.app.activity.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

// compiled from: CommDialogActivity.java
class d implements OnCancelListener {
    final /* synthetic */ CommDialogActivity a;

    d(CommDialogActivity r1_CommDialogActivity) {
        this.a = r1_CommDialogActivity;
    }

    public void onCancel(DialogInterface r2_DialogInterface) {
        this.a.finish();
    }
}
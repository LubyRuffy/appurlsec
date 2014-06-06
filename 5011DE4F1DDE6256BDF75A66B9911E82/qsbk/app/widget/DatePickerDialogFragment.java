package qsbk.app.widget;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class DatePickerDialogFragment extends DialogFragment implements OnDateSetListener {
    private OnDateSetListener Y;
    private long Z;
    private long aa;

    public DatePickerDialogFragment() {
        this.Z = -1;
        this.aa = -1;
    }

    private void a(DatePickerDialog r3_DatePickerDialog, long r4j) {
        if (VERSION.SDK_INT >= 11) {
            r3_DatePickerDialog.getDatePicker().setMaxDate(r4j);
        }
    }

    public Dialog onCreateDialog(Bundle r10_Bundle) {
        Calendar r0_Calendar = Calendar.getInstance();
        if (this.Z != -1) {
            r0_Calendar.setTime(new Date(this.Z));
        }
        Dialog r0_Dialog = new a(this, getActivity(), this, r0_Calendar.get(1), r0_Calendar.get(XListViewHeader.STATE_REFRESHING), r0_Calendar.get(ShareUtils.SHARE_SMS));
        if (this.aa != -1) {
            a(r0_Dialog, this.aa);
        }
        return r0_Dialog;
    }

    public void onDateSet(DatePicker r2_DatePicker, int r3i, int r4i, int r5i) {
        if (this.Y != null) {
            this.Y.onDateSet(r2_DatePicker, r3i, r4i, r5i);
        }
    }

    public DatePickerDialogFragment setDateSetListener(OnDateSetListener r1_OnDateSetListener) {
        this.Y = r1_OnDateSetListener;
        return this;
    }

    public DatePickerDialogFragment setInitialTime(long r1j) {
        this.Z = r1j;
        return this;
    }

    public DatePickerDialogFragment setMaxDate(long r1j) {
        this.aa = r1j;
        return this;
    }
}
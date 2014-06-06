package qsbk.app.nearby.ui;

import android.view.View;
import android.widget.Checkable;
import android.widget.RadioGroup;
import qsbk.app.R;

public class NearbySelectView {
    public static final String GENDER_ALL = "ALL";
    public static final String GENDER_FEMALE = "F";
    public static final String GENDER_MALE = "M";
    public static final int TIME_15MIN = 15;
    public static final int TIME_1DAY = 1440;
    public static final int TIME_3DAY = 4320;
    public static final int TIME_60MIN = 60;
    private View a;
    private RadioGroup b;
    private RadioGroup c;

    public NearbySelectView(View r2_View) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = r2_View;
        this.b = (RadioGroup) r2_View.findViewById(R.id.id_gender_group);
        this.c = (RadioGroup) r2_View.findViewById(R.id.id_time_group);
    }

    public String getGender() {
        int r0i = this.b.getCheckedRadioButtonId();
        if (r0i == 2131099943) {
            return GENDER_FEMALE;
        }
        if (r0i == 2131099942) {
            return GENDER_MALE;
        }
        return GENDER_ALL;
    }

    public int getTimeInMinute() {
        int r0i = this.c.getCheckedRadioButtonId();
        if (r0i == 2131099946) {
            return TIME_60MIN;
        }
        if (r0i == 2131099947) {
            return TIME_1DAY;
        }
        if (r0i == 2131099948) {
            return TIME_3DAY;
        }
        return TIME_15MIN;
    }

    public void setGender(String r4_String) {
        if (GENDER_MALE.equals(r4_String)) {
            ((Checkable) this.b.findViewById(R.id.id_male)).setChecked(true);
        } else if (GENDER_FEMALE.equals(r4_String)) {
            ((Checkable) this.b.findViewById(R.id.id_female)).setChecked(true);
        } else {
            ((Checkable) this.b.findViewById(R.id.id_all)).setChecked(true);
        }
    }

    public void setTimeInMinute(int r4i) {
        if (r4i == 60) {
            ((Checkable) this.c.findViewById(R.id.id_60min)).setChecked(true);
        } else if (r4i == 1440) {
            ((Checkable) this.c.findViewById(R.id.id_1day)).setChecked(true);
        } else if (r4i == 4320) {
            ((Checkable) this.c.findViewById(R.id.id_3day)).setChecked(true);
        } else {
            ((Checkable) this.c.findViewById(R.id.id_15min)).setChecked(true);
        }
    }
}
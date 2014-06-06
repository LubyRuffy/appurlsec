package qsbk.app.activity;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import qsbk.app.nearby.ui.NearbySelectView;

// compiled from: EditGenderActivity.java
class t implements OnCheckedChangeListener {
    final /* synthetic */ EditGenderActivity a;

    t(EditGenderActivity r1_EditGenderActivity) {
        this.a = r1_EditGenderActivity;
    }

    public void onCheckedChanged(RadioGroup r3_RadioGroup, int r4i) {
        if (r4i != this.a.r) {
            this.a.r = r4i;
            if (this.a.r == 2131099920) {
                this.a.q = NearbySelectView.GENDER_FEMALE;
            } else if (this.a.r == 2131099919) {
                this.a.q = NearbySelectView.GENDER_MALE;
            }
            this.a.c();
        }
    }
}
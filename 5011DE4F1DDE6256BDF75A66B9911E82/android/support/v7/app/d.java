package android.support.v7.app;

import android.os.Bundle;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: ActionBarActivityDelegateHC.java
class d extends b {
    d(ActionBarActivity r1_ActionBarActivity) {
        super(r1_ActionBarActivity);
    }

    public ActionBar createSupportActionBar() {
        e();
        return new g(this.a, this.a);
    }

    void onCreate(Bundle r3_Bundle) {
        this.a.getWindow().requestFeature(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        super.onCreate(r3_Bundle);
    }
}
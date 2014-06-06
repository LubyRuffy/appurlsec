package com.androidquery;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class AQuery extends AbstractAQuery<AQuery> {
    public AQuery(Activity r1_Activity) {
        super(r1_Activity);
    }

    public AQuery(Activity r1_Activity, View r2_View) {
        super(r1_Activity, r2_View);
    }

    public AQuery(Context r1_Context) {
        super(r1_Context);
    }

    public AQuery(View r1_View) {
        super(r1_View);
    }
}
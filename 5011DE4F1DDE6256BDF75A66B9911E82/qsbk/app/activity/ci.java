package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

// compiled from: RegisterActivity.java
class ci implements OnClickListener {
    final /* synthetic */ RegisterActivity a;

    ci(RegisterActivity r1_RegisterActivity) {
        this.a = r1_RegisterActivity;
    }

    public void onClick(View r4_View) {
        this.a.startActivity(new Intent(this.a, LoginActivity.class));
        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        this.a.finish();
    }
}
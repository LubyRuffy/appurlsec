package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: LoginActivity.java
class as implements OnClickListener {
    final /* synthetic */ LoginActivity a;

    as(LoginActivity r1_LoginActivity) {
        this.a = r1_LoginActivity;
    }

    public void onClick(View r4_View) {
        this.a.startActivity(new Intent(this.a, RegisterActivity.class));
        this.a.finish();
    }
}
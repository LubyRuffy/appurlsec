package qsbk.app.nearby.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.About;

// compiled from: NearByListActivity.java
class af implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    af(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r4_View) {
        NearByListActivity.d(this.a).dismiss();
        Intent r0_Intent = new Intent();
        r0_Intent.setClass(this.a, About.class);
        r0_Intent.putExtra("targetPage", "feedback");
        this.a.startActivity(r0_Intent);
    }
}
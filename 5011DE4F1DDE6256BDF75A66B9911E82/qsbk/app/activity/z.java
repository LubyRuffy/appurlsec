package qsbk.app.activity;

import android.widget.TextView;
import qsbk.app.R;

// compiled from: EditInfoEntranceActivity.java
class z implements Runnable {
    final /* synthetic */ TextView a;
    final /* synthetic */ String b;
    final /* synthetic */ EditInfoEntranceActivity c;

    z(EditInfoEntranceActivity r1_EditInfoEntranceActivity, TextView r2_TextView, String r3_String) {
        this.c = r1_EditInfoEntranceActivity;
        this.a = r2_TextView;
        this.b = r3_String;
    }

    public void run() {
        this.a.setTextColor(this.c.getResources().getColor(R.color.g_txt_middle));
        this.a.setText(this.b);
    }
}
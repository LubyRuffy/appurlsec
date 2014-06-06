package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: EditBirthActivity.java
class s implements OnItemClickListener {
    final /* synthetic */ EditBirthActivity a;

    s(EditBirthActivity r1_EditBirthActivity) {
        this.a = r1_EditBirthActivity;
    }

    public void onItemClick(AdapterView<?> r2_AdapterView_, View r3_View, int r4i, long r5j) {
        this.a.c();
    }
}
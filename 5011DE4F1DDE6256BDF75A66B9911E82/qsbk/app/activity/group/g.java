package qsbk.app.activity.group;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.About;

// compiled from: TopActivityGroup.java
class g implements OnClickListener {
    final /* synthetic */ TopActivityGroup a;

    g(TopActivityGroup r1_TopActivityGroup) {
        this.a = r1_TopActivityGroup;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        this.a.startActivity(new Intent(this.a, About.class));
    }
}
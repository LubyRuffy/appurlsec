package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: Publish.java
class h implements OnClickListener {
    final /* synthetic */ Publish a;

    h(Publish r1_Publish) {
        this.a = r1_Publish;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        if (this.a.s) {
            if (r5i == 0) {
                Intent r0_Intent = new Intent(this.a, Publish_Image.class);
                r0_Intent.putExtra("picpath", Publish.d(this.a));
                this.a.startActivityForResult(r0_Intent, XListViewFooter.STATE_NOMORE);
            } else if (r5i == 1) {
                Publish.a(this.a, false);
                Publish.g(this.a);
                this.a.s = false;
            } else if (r5i == 2) {
                this.a.startCamera();
            } else {
                this.a.startAlbum();
            }
        }
    }
}
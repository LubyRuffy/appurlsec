package qsbk.app.nearby.ui;

import android.util.Pair;
import android.widget.Toast;
import qsbk.app.api.UserHeaderHelper.UploadAvatarTask;
import qsbk.app.utils.Base64;

// compiled from: InfoCompleteActivity.java
class c extends UploadAvatarTask {
    final /* synthetic */ InfoCompleteActivity a;

    c(InfoCompleteActivity r1_InfoCompleteActivity, String r2_String) {
        this.a = r1_InfoCompleteActivity;
        super(r2_String);
    }

    protected void a(Pair<Integer, String> r5_Pair_Integer__String) {
        super.a(r5_Pair_Integer__String);
        Toast.makeText(this.a, (CharSequence) r5_Pair_Integer__String.second, 1).show();
        if ((!((Integer) r5_Pair_Integer__String.first).equals(Integer.valueOf(0))) || this.a.r.getPickedBitmap() == null) {
            this.a.u.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.a.s.setImageBitmap(this.a.r.getPickedBitmap());
            this.a.t.setVisibility(Base64.DONT_BREAK_LINES);
            this.a.u.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }
}
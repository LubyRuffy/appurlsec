package qsbk.app.activity;

import android.util.Pair;
import android.widget.Toast;
import qsbk.app.api.UserHeaderHelper.UploadAvatarTask;

// compiled from: OneProfileActivity.java
class by extends UploadAvatarTask {
    final /* synthetic */ OneProfileActivity a;

    by(OneProfileActivity r1_OneProfileActivity, String r2_String) {
        this.a = r1_OneProfileActivity;
        super(r2_String);
    }

    protected void a(Pair<Integer, String> r4_Pair_Integer__String) {
        super.a(r4_Pair_Integer__String);
        Toast.makeText(this.a, (CharSequence) r4_Pair_Integer__String.second, 1).show();
        if ((!((Integer) r4_Pair_Integer__String.first).equals(Integer.valueOf(0))) || this.a.B.getPickedBitmap() == null) {
        } else {
            this.a.y.setImageBitmap(this.a.B.getPickedBitmap());
        }
    }
}
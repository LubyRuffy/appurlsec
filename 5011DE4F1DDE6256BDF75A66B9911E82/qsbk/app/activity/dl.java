package qsbk.app.activity;

import android.util.Pair;
import android.widget.Toast;
import qsbk.app.api.UserHeaderHelper.UploadAvatarTask;
import qsbk.app.utils.Base64;

// compiled from: UserSetting.java
class dl extends UploadAvatarTask {
    final /* synthetic */ UserSetting a;

    dl(UserSetting r1_UserSetting, String r2_String) {
        this.a = r1_UserSetting;
        super(r2_String);
    }

    protected void a(Pair<Integer, String> r4_Pair_Integer__String) {
        super.a(r4_Pair_Integer__String);
        Toast.makeText(this.a, (CharSequence) r4_Pair_Integer__String.second, 1).show();
        this.a.w.setVisibility(Base64.DONT_BREAK_LINES);
        if ((!((Integer) r4_Pair_Integer__String.first).equals(Integer.valueOf(0))) || this.a.F.getPickedBitmap() == null) {
        } else {
            this.a.o.setImageBitmap(this.a.F.getPickedBitmap());
        }
    }
}
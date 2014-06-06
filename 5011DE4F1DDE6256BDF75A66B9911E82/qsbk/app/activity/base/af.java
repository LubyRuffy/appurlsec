package qsbk.app.activity.base;

import android.text.InputFilter;
import android.text.Spanned;
import com.tencent.mm.sdk.contact.RContactStorage;

// compiled from: LoginRegisterBaseActivity.java
class af implements InputFilter {
    final /* synthetic */ LoginRegisterBaseActivity a;

    af(LoginRegisterBaseActivity r1_LoginRegisterBaseActivity) {
        this.a = r1_LoginRegisterBaseActivity;
    }

    public CharSequence filter(CharSequence r4_CharSequence, int r5i, int r6i, Spanned r7_Spanned, int r8i, int r9i) {
        if (r4_CharSequence == null || LoginRegisterBaseActivity.isCN(r4_CharSequence.toString())) {
            return r7_Spanned.toString();
        }
        if (r8i >= 30) {
            this.a.v.setSelection(r8i);
            return RContactStorage.PRIMARY_KEY;
        } else {
            if (r4_CharSequence.length() + r7_Spanned.length() > 30) {
                return r4_CharSequence.subSequence(r5i, r5i + 30 - r7_Spanned.length());
            }
            return r4_CharSequence;
        }
    }
}
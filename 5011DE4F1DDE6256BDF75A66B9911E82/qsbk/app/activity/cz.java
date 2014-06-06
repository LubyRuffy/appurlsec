package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.utils.Base64;

// compiled from: SingleEditTextBaseActivity.java
class cz implements TextWatcher {
    final /* synthetic */ SingleEditTextBaseActivity a;

    cz(SingleEditTextBaseActivity r1_SingleEditTextBaseActivity) {
        this.a = r1_SingleEditTextBaseActivity;
    }

    public void afterTextChanged(Editable r5_Editable) {
        int r0i = r5_Editable.length();
        if (this.a.maxLength() == 0 || this.a.maxLength() <= 10) {
        } else if (r0i < this.a.maxLength() - 10) {
            this.a.q.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.a.q.setVisibility(0);
            this.a.q.setText(RContactStorage.PRIMARY_KEY + (this.a.maxLength() - r0i));
        }
    }

    public void beforeTextChanged(CharSequence r1_CharSequence, int r2i, int r3i, int r4i) {
    }

    public void onTextChanged(CharSequence r1_CharSequence, int r2i, int r3i, int r4i) {
    }
}
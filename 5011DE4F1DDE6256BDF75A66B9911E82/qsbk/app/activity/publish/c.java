package qsbk.app.activity.publish;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Publish.java
class c implements OnClickListener {
    final /* synthetic */ Publish a;

    c(Publish r1_Publish) {
        this.a = r1_Publish;
    }

    public void onClick(View r6_View) {
        if (this.a.getCurrentFocus() != null) {
            ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
        }
        if ((!Publish.b(this.a)) || this.a.n.getText().toString().trim().length() <= 0) {
            this.a.finish();
        } else {
            Builder r0_Builder = new Builder(Publish.c(this.a));
            CharSequence[] r1_CharSequenceA = new CharSequence[3];
            r1_CharSequenceA[0] = "\u4fdd\u5b58\u8349\u7a3f";
            r1_CharSequenceA[1] = "\u5220\u9664\u8349\u7a3f";
            r1_CharSequenceA[2] = "\u53d6\u6d88";
            r0_Builder.setItems(r1_CharSequenceA, new d(this)).show();
        }
    }
}
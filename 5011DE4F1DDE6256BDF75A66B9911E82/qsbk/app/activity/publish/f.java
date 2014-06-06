package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Publish.java
class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e r1_e) {
        this.a = r1_e;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        if (r5i == 0) {
            Publish.a(this.a.a, Boolean.valueOf(true));
        } else {
            Publish.a(this.a.a, Boolean.valueOf(false));
        }
        if (HttpUtils.netIsAvailable()) {
            r4_DialogInterface.dismiss();
            this.a.a.submitContent();
        } else {
            SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a.a), this.a.a.n.getText().toString());
            Toast.makeText(QsbkApp.mContext, "\u672a\u53d1\u73b0\u53ef\u7528\u7f51\u7edc\uff0c\u5185\u5bb9\u5df2\u4fdd\u5b58\u4e3a\u8349\u7a3f", 1).show();
            this.a.a.finish();
        }
        if (this.a.a.getCurrentFocus() != null) {
            ((InputMethodManager) this.a.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.a.getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
        }
        r4_DialogInterface.dismiss();
    }
}
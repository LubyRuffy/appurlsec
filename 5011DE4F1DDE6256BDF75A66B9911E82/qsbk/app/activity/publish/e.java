package qsbk.app.activity.publish;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Publish.java
class e implements OnClickListener {
    final /* synthetic */ Publish a;

    e(Publish r1_Publish) {
        this.a = r1_Publish;
    }

    public void onClick(View r8_View) {
        int r2i = XListViewHeader.STATE_REFRESHING;
        Integer r0_Integer = null;
        if (Publish.b(this.a)) {
            r0_Integer = Integer.valueOf(R.array.Content_SendWay);
        }
        String r3_String = this.a.n.getText().toString().trim();
        int r1i = Publish.b(this.a) ? ShareUtils.SHARE_SMS : 2;
        if ((!Publish.d(this.a).equals(RContactStorage.PRIMARY_KEY)) || r3_String.length() >= r1i) {
            if (r0_Integer != null) {
                if (QsbkApp.currentUser != null) {
                    Builder r0_Builder = new Builder(Publish.e(this.a));
                    CharSequence[] r1_CharSequenceA = new CharSequence[r2i];
                    r1_CharSequenceA[0] = "\u533f\u540d\u6295\u7a3f";
                    r1_CharSequenceA[1] = "\u7f72\u540d";
                    r0_Builder.setItems(r1_CharSequenceA, new f(this)).show();
                } else if (HttpUtils.netIsAvailable()) {
                    this.a.submitContent();
                } else {
                    SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a), this.a.n.getText().toString());
                    Toast.makeText(QsbkApp.mContext, "\u672a\u53d1\u73b0\u53ef\u7528\u7f51\u7edc\uff0c\u5185\u5bb9\u5df2\u4fdd\u5b58\u4e3a\u8349\u7a3f", 1).show();
                    this.a.finish();
                }
            } else {
                if (HttpUtils.netIsAvailable()) {
                    this.a.submitContent();
                } else {
                    SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a), this.a.n.getText().toString());
                    Toast.makeText(QsbkApp.mContext, "\u672a\u53d1\u73b0\u53ef\u7528\u7f51\u7edc\uff0c\u5185\u5bb9\u5df2\u4fdd\u5b58\u4e3a\u8349\u7a3f", 1).show();
                    this.a.finish();
                }
                ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
            }
        } else {
            Toast.makeText(QsbkApp.mContext, "\u518d\u591a\u5199\u70b9\u5185\u5bb9\u5427!", 1).show();
        }
    }
}
package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.UIHelper;

// compiled from: AdapterForLinearLayout.java
class c extends ClickableSpan {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ AdapterForLinearLayout c;

    c(AdapterForLinearLayout r1_AdapterForLinearLayout, int r2i, String r3_String) {
        this.c = r1_AdapterForLinearLayout;
        this.a = r2i;
        this.b = r3_String;
    }

    public void onClick(View r6_View) {
        String r0_String;
        Builder r1_Builder;
        StringBuilder r2_StringBuilder;
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            JSONObject r0_JSONObject = ((JSONObject) this.c.h.get(Integer.valueOf(this.a))).getJSONObject(OneProfileActivity.USER);
            if (r0_JSONObject != null) {
                r0_String = r0_JSONObject.optString(QsbkDatabase.LOGIN);
                r1_Builder = new Builder(this.c.e);
                r2_StringBuilder = new StringBuilder().append(this.a).append("\u697c ");
                if (r0_String.length() <= 15) {
                    r0_String = r0_String.substring(0, NearbySelectView.TIME_15MIN);
                }
                r1_Builder.setTitle(r2_StringBuilder.append(r0_String).append(" \u7ae5\u978b\u8bf4:").toString()).setMessage(this.b).setPositiveButton("OK", new d(this)).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        r0_String = r1_String;
        r1_Builder = new Builder(this.c.e);
        r2_StringBuilder = new StringBuilder().append(this.a).append("\u697c ");
        if (r0_String.length() <= 15) {
            r1_Builder.setTitle(r2_StringBuilder.append(r0_String).append(" \u7ae5\u978b\u8bf4:").toString()).setMessage(this.b).setPositiveButton("OK", new d(this)).show();
        } else {
            r0_String = r0_String.substring(0, NearbySelectView.TIME_15MIN);
            r1_Builder.setTitle(r2_StringBuilder.append(r0_String).append(" \u7ae5\u978b\u8bf4:").toString()).setMessage(this.b).setPositiveButton("OK", new d(this)).show();
        }
    }

    public void updateDrawState(TextPaint r3_TextPaint) {
        super.updateDrawState(r3_TextPaint);
        if (UIHelper.isNightTheme()) {
            r3_TextPaint.setColor(this.c.e.getResources().getColor(R.color.comment_floor_night));
        } else {
            r3_TextPaint.setColor(this.c.e.getResources().getColor(R.color.comment_floor));
        }
        r3_TextPaint.setUnderlineText(false);
    }
}
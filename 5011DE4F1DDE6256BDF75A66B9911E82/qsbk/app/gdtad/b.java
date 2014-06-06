package qsbk.app.gdtad;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.androidquery.AQuery;
import com.qq.e.feedsad.CustomFeedsViewBuilder;
import com.qq.e.feedsad.FeedsADSetting;
import com.tencent.tauth.Constants;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.RatingView;

// compiled from: GdtAd.java
class b implements CustomFeedsViewBuilder {
    final /* synthetic */ GdtAd a;

    b(GdtAd r1_GdtAd) {
        this.a = r1_GdtAd;
    }

    public View build(Map<String, Object> r6_Map_String__Object, View r7_View, FeedsADSetting r8_FeedsADSetting) {
        if (r7_View == null || (!false instanceof AdView)) {
            View r0_View = (AdView) LayoutInflater.from(this.a.f).inflate(R.layout.feedslistitem, null);
            r0_View.setTag("AdvancedADView");
            r7_View = r0_View;
        } else {
            r7_View = (AdView) r7_View;
        }
        DebugUtil.debug(GdtAd.a, "build cid:" + r6_Map_String__Object.get("cid"));
        AQuery r2_AQuery = new AQuery(r7_View);
        ((AQuery) r2_AQuery.id(R.id.userIcon)).image((String) r6_Map_String__Object.get(QsbkDatabase.ICON), false, true);
        ((AQuery) r2_AQuery.id(R.id.image)).image((String) r6_Map_String__Object.get(Constants.PARAM_IMG_URL), false, true);
        ((TextView) r7_View.findViewById(R.id.userName)).setText((String) r6_Map_String__Object.get("text"));
        ((TextView) r7_View.findViewById(R.id.content)).setText((String) r6_Map_String__Object.get(Constants.PARAM_APP_DESC));
        ((TextView) r7_View.findViewById(R.id.players)).setText(r6_Map_String__Object.get("players") + "\u4eba\u5728\u73a9");
        ((RatingView) r7_View.findViewById(R.id.ratting)).setRating(((Integer) r6_Map_String__Object.get("score")).intValue());
        r7_View.bindPkg((String) r6_Map_String__Object.get("pkgname"));
        r7_View.updateButtonText(((Integer) r6_Map_String__Object.get("appstatus")).intValue());
        r7_View.updateBackGround(this.a.h.getAdBackGroundColor());
        r7_View.updateStyle(this.a.h.getStyleID());
        return r7_View;
    }
}
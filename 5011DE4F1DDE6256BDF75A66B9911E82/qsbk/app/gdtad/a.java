package qsbk.app.gdtad;

import com.qq.e.feedsad.FeedsAD.FeedsADListener;
import com.qq.e.feedsad.FeedsADViewRef;
import java.util.Collection;
import qsbk.app.utils.DebugUtil;

// compiled from: GdtAd.java
class a implements FeedsADListener {
    final /* synthetic */ GdtAd a;

    a(GdtAd r1_GdtAd) {
        this.a = r1_GdtAd;
    }

    public void onFeedsADFail(int r3i) {
        DebugUtil.debug(GdtAd.a(), " onFeedsADFail");
        GdtAd.a(this.a);
    }

    public void onFeedsADLoaded(Collection<FeedsADViewRef> r4_Collection_FeedsADViewRef) {
        DebugUtil.debug(GdtAd.a(), " onFeedsADLoaded size\uff1a" + r4_Collection_FeedsADViewRef.size());
        if (r4_Collection_FeedsADViewRef == null || r4_Collection_FeedsADViewRef.size() == 0) {
            GdtAd.a(this.a);
        } else {
            GdtAd.a(this.a, r4_Collection_FeedsADViewRef);
        }
    }

    public void onFeedsADReady(FeedsADViewRef r1_FeedsADViewRef) {
    }
}
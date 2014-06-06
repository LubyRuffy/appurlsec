package qsbk.app.gdtad;

import android.app.Activity;
import android.graphics.Color;
import com.qq.e.feedsad.FeedsAD;
import com.qq.e.feedsad.FeedsADSetting;
import com.qq.e.feedsad.FeedsADViewRef;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.UIHelper;

public class GdtAd {
    public static final int BG_Dark_Color;
    public static final int BG_Light_Color;
    public static int FEEDSAD_SHOW_TIMES;
    private static final String a;
    private static final int[] b;
    private static GdtAd c;
    private ArrayList<FeedsADViewRef> d;
    private FeedsAD e;
    private Activity f;
    private List<IFeedsAdLoaded> g;
    private FeedsADSetting h;
    private boolean i;
    private int j;
    private int k;

    static {
        a = GdtAd.class.getSimpleName();
        FEEDSAD_SHOW_TIMES = 9999;
        b = new int[]{3, 15};
        BG_Dark_Color = Color.argb(0, 0, 0, 0);
        BG_Light_Color = Color.argb(0, 0, 0, 0);
        c = null;
    }

    public GdtAd() {
        this.d = new ArrayList();
        this.e = null;
        this.g = new ArrayList();
        this.i = false;
        this.j = 0;
        this.k = 0;
        FEEDSAD_SHOW_TIMES = FeedsAdUtils.getMaxFeedAdShowTime();
        if ("21".equals(QsbkApp.getInstance().getBaiduChannel()) || "37".equals(QsbkApp.getInstance().getBaiduChannel())) {
            this.i = true;
        }
    }

    private FeedsAD a(FeedsADSetting r8_FeedsADSetting) {
        return new FeedsAD(this.f, "100722332", "9007200358824374949", r8_FeedsADSetting, new a(this), new b(this));
    }

    private void a(Collection<FeedsADViewRef> r3_Collection_FeedsADViewRef) {
        if (r3_Collection_FeedsADViewRef == null) {
        } else {
            DebugUtil.debug(a, " onFeedsLoaded");
            this.d.addAll(r3_Collection_FeedsADViewRef);
            if (this.g != null) {
                Iterator r1_Iterator = this.g.iterator();
                while (r1_Iterator.hasNext()) {
                    ((IFeedsAdLoaded) r1_Iterator.next()).iFeedsAdLoaded();
                }
            }
        }
    }

    private void b() {
        if (this.i && HttpUtils.netIsAvailable() && !FeedsAdUtils.isOver(d(), FEEDSAD_SHOW_TIMES)) {
            DebugUtil.debug(a, "loadFeedAd...");
            this.e.loadAD(Constants.pageCount);
        }
    }

    private void c() {
        if (this.k < 2) {
            b();
            this.k++;
        }
    }

    private String d() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static GdtAd getInstance() {
        if (c == null) {
            synchronized (GdtAd.class) {
                if (c == null) {
                    c = new GdtAd();
                }
            }
        }
        return c;
    }

    public void exit() {
        this.f = null;
        this.g.clear();
        Iterator r1_Iterator = this.d.iterator();
        while (r1_Iterator.hasNext()) {
            ((FeedsADViewRef) r1_Iterator.next()).release();
        }
        this.d.clear();
        c = null;
    }

    public boolean getFeedsAdSwitcherState() {
        return this.i;
    }

    public void initFeedAd(Activity r3_Activity) {
        this.f = r3_Activity;
        if (this.i) {
            this.h = new FeedsADSetting();
            if (UIHelper.isNightTheme()) {
                this.h.setStyleID("skin01-alpha0d6");
                this.h.setAdBackGroundColor(BG_Dark_Color);
            } else {
                this.h.setStyleID("skin01");
                this.h.setAdBackGroundColor(BG_Light_Color);
            }
            this.e = a(this.h);
            b();
        }
    }

    public synchronized void insertFeedAd(int r6i, ArrayList<Object> r7_ArrayList_Object) {
        if (FeedsAdUtils.isOver(d(), FEEDSAD_SHOW_TIMES) || this.d.size() == 0 || r7_ArrayList_Object.size() == 0) {
        } else {
            DebugUtil.debug(a, "insertFeedAd  curPositin:" + r6i);
            int r1i = 0;
            while (r1i < b.length) {
                int r2i = r6i + b[r1i];
                if (r2i < 0 || r7_ArrayList_Object.isEmpty() || r2i >= r7_ArrayList_Object.size() - 1) {
                    r1i++;
                } else {
                    DebugUtil.debug(a, "insertFeedAd position:" + r2i);
                    if (this.d.size() <= this.j) {
                        this.j = 0;
                    }
                    AdItemData r3_AdItemData = new AdItemData();
                    r3_AdItemData.setName("Ad view" + r7_ArrayList_Object.size());
                    r3_AdItemData.setView((FeedsADViewRef) this.d.get(this.j));
                    this.j++;
                    r7_ArrayList_Object.add(r2i, r3_AdItemData);
                    FeedsAdUtils.addMain_condition(d());
                    r1i++;
                }
            }
        }
    }

    public void registerListener(IFeedsAdLoaded r2_IFeedsAdLoaded) {
        if (!this.g.contains(r2_IFeedsAdLoaded)) {
            this.g.add(r2_IFeedsAdLoaded);
        }
    }

    public void unRegisterListener(IFeedsAdLoaded r2_IFeedsAdLoaded) {
        if (!this.g.contains(r2_IFeedsAdLoaded)) {
            this.g.remove(r2_IFeedsAdLoaded);
        }
    }
}
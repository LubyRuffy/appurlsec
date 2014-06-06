package qsbk.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View.OnTouchListener;
import com.baidu.frontia.FrontiaApplication;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.Tracker;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.cloudsdk.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.AuditActivity;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.RegisterActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.cache.ImageCache;
import qsbk.app.cache.ImageCache.ImageCacheParams;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.CrashHandler;
import qsbk.app.gdtad.GdtAd;
import qsbk.app.manager.MyLikeManager;
import qsbk.app.model.Article;
import qsbk.app.model.UserInfo;
import qsbk.app.model.Vote;
import qsbk.app.service.ReportHandler;
import qsbk.app.service.VoteHandler;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class QsbkApp extends Application {
    public static HashMap<String, Vote> AllVotes = null;
    public static final String CONTENT_TXT_SIZE_KEY = "content_txt_size";
    public static final OnTouchListener TouchDark;
    public static ArrayList<String> allCollection = null;
    public static AuditActivity audit = null;
    public static int brightness = 0;
    private static MyLikeManager c = null;
    public static JSONObject currentComment = null;
    public static ArrayList<Object> currentDataSource = null;
    public static int currentSelectItem = 0;
    public static String currentTheme = null;
    public static UserInfo currentUser = null;
    public static final int defaultDispatchPeriodInSeconds = 70;
    public static final double defaultSampleRateInDouble = 25.0d;
    private static QsbkApp e;
    private static int g;
    private static double h;
    public static HandlerThread hThread;
    public static boolean hasVerify;
    private static float i;
    public static JSONObject indexConfig;
    public static boolean isChange;
    public static boolean isEnterSingle;
    public static boolean isPad;
    private static boolean j;
    public static int loading_process;
    public static SparseArray<String> mActivityMap;
    public static Context mContext;
    public static int messageCount;
    public static boolean mobileSpeedupDisable;
    public static Article newArticle;
    public static RegisterActivity register;
    public static ReportHandler reportHandler;
    public static HandlerThread reportThread;
    public static boolean reportable;
    public static Integer screenMode;
    public static GroupBaseActivity tmpContext;
    public static HashMap<String, String> valuesMap;
    public static VoteHandler voteHandler;
    public static HashMap<String, Vote> waitSendVotes;
    public ImageFetcher _mAvatarWorker;
    public ImageFetcher _mImageWorker;
    private ImageCacheParams a;
    public List<Activity> activityList;
    private ImageCacheParams b;
    private final Handler d;
    private HashMap<String, ImageCache> f;
    public Bitmap waitSendBitmap;

    static {
        currentDataSource = null;
        currentUser = null;
        currentComment = null;
        hasVerify = false;
        isEnterSingle = false;
        newArticle = null;
        valuesMap = null;
        loading_process = 0;
        waitSendVotes = new HashMap();
        AllVotes = new HashMap();
        allCollection = new ArrayList();
        screenMode = Integer.valueOf(0);
        brightness = -1;
        isChange = false;
        messageCount = 1;
        mActivityMap = new SparseArray();
        indexConfig = null;
        reportable = false;
        mobileSpeedupDisable = false;
        isPad = false;
        TouchDark = new j();
        g = 70;
        h = 25.0d;
        j = false;
    }

    public QsbkApp() {
        this.activityList = new LinkedList();
        this.waitSendBitmap = null;
        this.a = new ImageCacheParams(Constants.IMG_CACHE_PATH_PRE);
        this.b = new ImageCacheParams(Constants.IMG_CACHE_PATH_AVATAR);
        this.d = new Handler(Looper.getMainLooper());
        this.f = new HashMap();
    }

    private synchronized ImageCache a(ImageCacheParams r4_ImageCacheParams, int r5i) {
        ImageCache r0_ImageCache;
        r0_ImageCache = (ImageCache) this.f.get(r4_ImageCacheParams.uniqueName);
        if (r0_ImageCache == null) {
            r0_ImageCache = new ImageCache((Context)this, r4_ImageCacheParams, r5i);
            this.f.put(r4_ImageCacheParams.uniqueName, r0_ImageCache);
        }
        return r0_ImageCache;
    }

    private void a() {
        this.d.postDelayed(new h(this), Util.MILLSECONDS_OF_SECOND);
    }

    private void a(Context r4_Context) {
        try {
            this.a.memCacheSize = (1048576 * Utils.getMemoryClass(r4_Context)) / 10;
            this._mImageWorker = new ImageFetcher(r4_Context, r4_Context.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size));
            this._mImageWorker.setLoadingImage(R.drawable.default_content_pic);
            this._mImageWorker.setImageCache(a(this.a, r4_Context.getResources().getDimensionPixelOffset(R.dimen.image_thumbnail_size)));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(boolean r6z) {
        CrashHandler r0_CrashHandler = CrashHandler.getInstance();
        r0_CrashHandler.init(getApplicationContext());
        if (r6z) {
            new Handler().postDelayed(new i(this, r0_CrashHandler), 15000);
        }
    }

    private void b() {
        SharePreferenceUtils.setSharePreferencesValue("launchedCount", (getLaunchedCount() + 1) + RContactStorage.PRIMARY_KEY);
    }

    private void b(Context r5_Context) {
        this.b.memCacheSize = (1048576 * Utils.getMemoryClass(r5_Context)) / 15;
        this._mAvatarWorker = new ImageFetcher(r5_Context, r5_Context.getResources().getDimensionPixelSize(R.dimen.image_avatar_size));
        this._mAvatarWorker.setImageFadeIn(false);
        if (UIHelper.isNightTheme()) {
            this._mAvatarWorker.setLoadingImage(R.drawable.default_users_avatar_night);
        } else {
            this._mAvatarWorker.setLoadingImage(R.drawable.default_users_avatar);
        }
        this._mAvatarWorker.setImageCache(a(this.b, r5_Context.getResources().getDimensionPixelSize(R.dimen.image_avatar_size)));
    }

    private void c() {
        currentComment = new JSONObject();
        try {
            currentComment.put("count", 1);
            currentComment.put("err", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void d() {
        AllVotes = QsbkDatabase.getInstance().queryVote();
        waitSendVotes = QsbkDatabase.getInstance().queryWaitSendVote();
        DebugUtil.debug("\u672c\u5730\u5f85\u53d1\u9001\u6295\u7968\u6570\uff1a" + waitSendVotes.size());
    }

    private void e() {
        mActivityMap.put(0, "Suggest");
        mActivityMap.put(1, "Latest");
        mActivityMap.put(XListViewHeader.STATE_REFRESHING, "Day");
        mActivityMap.put(XListViewFooter.STATE_NOMORE, "Week");
        mActivityMap.put(XListViewFooter.STATE_NODATA, "Month");
        mActivityMap.put(ShareUtils.SHARE_SMS, "HotImages");
        mActivityMap.put(ShareUtils.SHARE_COPY, "Images");
        mActivityMap.put(ShareUtils.SHARE_COLLECT, "history");
        mActivityMap.put(OneProfileActivity.REQ_CODE_SHARE, "mycontent");
        mActivityMap.put(AdViewUtil.NETWORK_TYPE_WOOBOO, "mycomment");
        mActivityMap.put(AdViewUtil.NETWORK_TYPE_KUAIYOU, "mycollection");
    }

    private void f() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("ga_sample_frequency");
        CharSequence r1_CharSequence = SharePreferenceUtils.getSharePreferencesValue("ga_dispatch_period");
        try {
            if (TextUtils.isEmpty(r0_String)) {
                setSampleRate((double)defaultSampleRateInDouble);
                if (TextUtils.isEmpty(r1_CharSequence)) {
                    setDispatchPeriod(defaultDispatchPeriodInSeconds);
                } else {
                    setDispatchPeriod(Integer.parseInt(r1_CharSequence));
                }
            } else {
                setSampleRate(Double.parseDouble(r0_String));
                try {
                    if (TextUtils.isEmpty(r1_CharSequence)) {
                        setDispatchPeriod(Integer.parseInt(r1_CharSequence));
                    } else {
                        setDispatchPeriod(defaultDispatchPeriodInSeconds);
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e_2) {
        }
    }

    private void g() {
        float r0f = getPrefContentTextSize();
        if (r0f == 0.0f) {
            r0f = getResources().getDimension(R.dimen.content) / getResources().getDisplayMetrics().scaledDensity;
        }
        i = r0f;
    }

    public static QsbkApp getInstance() {
        if (e == null) {
            e = new QsbkApp();
        }
        return e;
    }

    public static MyLikeManager getMyLikeManager() {
        if (c == null) {
            c = new MyLikeManager(10, mContext);
        }
        return c;
    }

    public static float getPrefContentTextSize() {
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(CONTENT_TXT_SIZE_KEY);
        float r0f = 0.0f;
        if (TextUtils.isEmpty(r1_String)) {
            return r0f;
        }
        try {
            return Float.parseFloat(r1_String);
        } catch (NumberFormatException e) {
            return r0f;
        }
    }

    public static void reportAppInfo() {
        reportThread = new HandlerThread("reportThread");
        reportThread.start();
        reportHandler = new ReportHandler(reportThread.getLooper());
    }

    public void addActivity(Activity r2_Activity) {
        if (!this.activityList.contains(r2_Activity)) {
            this.activityList.add(r2_Activity);
        }
    }

    public void destoryImageWorker() {
        this.f.clear();
        this._mImageWorker = null;
        this._mAvatarWorker = null;
    }

    public void exit() {
        Iterator r1_Iterator = this.activityList.iterator();
        while (r1_Iterator.hasNext()) {
            ((Activity) r1_Iterator.next()).finish();
        }
        this.activityList.clear();
        GdtAd.getInstance().exit();
        System.exit(0);
    }

    public synchronized ImageFetcher getAvatarWorker(Context r2_Context) {
        if (this._mAvatarWorker == null) {
            b(r2_Context);
        }
        return this._mAvatarWorker;
    }

    public String getBaiduChannel() {
        return getMetaDataValue("BaiduMobAd_CHANNEL");
    }

    public float getCurrentContentTextSize() {
        return i;
    }

    public synchronized ImageFetcher getImageWorker(Context r2_Context) {
        if (this._mImageWorker == null) {
            a(r2_Context);
        }
        return this._mImageWorker;
    }

    public int getLaunchedCount() {
        String r1_String = SharePreferenceUtils.getSharePreferencesValue("launchedCount");
        if (TextUtils.isEmpty(r1_String) || (!TextUtils.isDigitsOnly(r1_String))) {
            return 0;
        }
        int r1i = Integer.parseInt(r1_String);
        return r1i < 0 ? 0 : r1i;
    }

    public String getMetaDataValue(String r5_String) {
        Object r0_Object = null;
        try {
            ApplicationInfo r1_ApplicationInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (r1_ApplicationInfo == null || r1_ApplicationInfo.metaData == null) {
                if (r0_Object == null) {
                    return r0_Object.toString();
                }
                throw new RuntimeException("The name '" + r5_String + "' is not defined in the manifest file's meta data.");
            } else {
                r0_Object = r1_ApplicationInfo.metaData.get(r5_String);
                if (r0_Object == null) {
                    return r0_Object.toString();
                }
                throw new RuntimeException("The name '" + r5_String + "' is not defined in the manifest file's meta data.");
            }
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Could not read the name in the manifest file.", e);
        }
    }

    public Bitmap getWaitSendBitmap() {
        return this.waitSendBitmap;
    }

    public boolean hasContentTextSizeChange() {
        boolean r0z = j;
        j = false;
        return r0z;
    }

    public void initMobileSpeedupEnable() {
        mobileSpeedupDisable = "1".equalsIgnoreCase(SharePreferenceUtils.getSharePreferencesValue("mobile_speedup"));
    }

    public boolean isStart(Activity r2_Activity) {
        return this.activityList.contains(r2_Activity);
    }

    public void loadUserInfoFromLocalPreference() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("loginUser");
        if (!TextUtils.isEmpty(r0_String)) {
            currentUser = new UserInfo(r0_String);
        }
    }

    public void onCreate() {
        e = this;
        mContext = getApplicationContext();
        c();
        super.onCreate();
        isPad = getResources().getBoolean(R.bool.is_pad);
        a(true);
        d();
        initMobileSpeedupEnable();
        loadUserInfoFromLocalPreference();
        hThread = new HandlerThread("voteThread");
        hThread.start();
        voteHandler = new VoteHandler(hThread.getLooper());
        voteHandler.obtainMessage().sendToTarget();
        currentTheme = SharePreferenceUtils.getSharePreferencesValue("themeid");
        e();
        g();
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("config");
        if (TextUtils.isEmpty(r0_String)) {
            try {
                indexConfig = new JSONObject(HttpClient.readStream(getAssets().open("default_cfg")));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e_2) {
                e_2.printStackTrace();
            }
        } else {
            try {
                indexConfig = new JSONObject(r0_String);
            } catch (JSONException e_3) {
                e_3.printStackTrace();
            }
        }
        f();
        a();
        GlobalContext.setContext(mContext);
        FrontiaApplication.initFrontiaApplication(mContext);
    }

    public void removeActivity(Activity r2_Activity) {
        if (!this.activityList.contains(r2_Activity)) {
            this.activityList.remove(r2_Activity);
        }
    }

    public boolean setContentTextSize(float r4f) {
        if (r4f != getCurrentContentTextSize()) {
            SharePreferenceUtils.setSharePreferencesValue(CONTENT_TXT_SIZE_KEY, r4f + RContactStorage.PRIMARY_KEY);
            i = r4f;
            j = true;
        }
        return j;
    }

    public void setCurrentUserToLocal() {
        if (currentUser != null) {
            String r0_String = currentUser.toString();
            LogUtil.d("current value:" + r0_String);
            SharePreferenceUtils.setSharePreferencesValue("loginUser", r0_String);
        }
    }

    public void setDispatchPeriod(int r3i) {
        g = r3i;
        GAServiceManager.getInstance().setDispatchPeriod(g);
    }

    public void setSampleRate(double r1d) {
        h = r1d;
    }

    public void setSampleRate(Tracker r3_Tracker) {
        if (r3_Tracker != null) {
            r3_Tracker.setSampleRate(h);
        }
    }

    public void setWaitSendBitmap(Bitmap r1_Bitmap) {
        this.waitSendBitmap = r1_Bitmap;
    }

    public void updateCurrentUserInfo(JSONObject r3_JSONObject) {
        currentUser = UserInfo.updateServerJson(currentUser, r3_JSONObject);
        SharePreferenceUtils.setSharePreferencesValue("loginUser", currentUser.toString());
    }
}
package qsbk.app.api;

import android.content.pm.PackageManager.NameNotFoundException;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import qsbk.app.QsbkApp;

public class AnalyticsUtils {
    private static Tracker a;
    private static AnalyticsUtils b;

    static {
        b = null;
    }

    public static AnalyticsUtils getIntentce() {
        if (b == null) {
            EasyTracker.getInstance().setContext(QsbkApp.mContext);
            a = EasyTracker.getTracker();
            QsbkApp.getInstance().setSampleRate(a);
            try {
                a.setAppVersion(QsbkApp.mContext.getPackageManager().getPackageInfo(QsbkApp.mContext.getPackageName(), 0).versionName);
            } catch (NameNotFoundException e) {
            }
            b = new AnalyticsUtils();
        }
        return b;
    }

    public void reportInfo(String r2_String) {
        a.trackView(r2_String);
    }
}
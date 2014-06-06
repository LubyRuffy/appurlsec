package qsbk.app.service;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.gdtad.FeedsAdUtils;
import qsbk.app.report.ReportUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: ConfigService.java
class b extends Thread {
    final /* synthetic */ ConfigService a;

    b(ConfigService r1_ConfigService, String r2_String) {
        this.a = r1_ConfigService;
        super(r2_String);
    }

    public void run() {
        try {
            JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().get(Constants.CONFIG) + "?type=pad");
            JSONObject r0_JSONObject = r1_JSONObject.getJSONObject("ol");
            String r2_String = SharePreferenceUtils.getSharePreferencesValue("version");
            if (TextUtils.isEmpty(r2_String) || r0_JSONObject.getInt("version") > Integer.valueOf(r2_String).intValue()) {
                String r0_String;
                SharePreferenceUtils.setSharePreferencesValue(ConfigService.b, String.valueOf(r0_JSONObject.getInt("duration")));
                if (r1_JSONObject.isNull("pollTime")) {
                    SharePreferenceUtils.setSharePreferencesValue("pollTime", "30");
                } else {
                    SharePreferenceUtils.setSharePreferencesValue("pollTime", String.valueOf(r1_JSONObject.getInt("pollTime")));
                }
                if (r1_JSONObject.isNull("loc")) {
                    SharePreferenceUtils.setSharePreferencesValue("loc", RContactStorage.PRIMARY_KEY);
                } else {
                    SharePreferenceUtils.setSharePreferencesValue("loc", String.valueOf(r1_JSONObject.getJSONObject("loc").toString()));
                }
                if (r1_JSONObject.isNull("adbanner-close")) {
                    SharePreferenceUtils.setSharePreferencesValue("adbanner-close", RContactStorage.PRIMARY_KEY);
                } else {
                    SharePreferenceUtils.setSharePreferencesValue("adbanner-close", String.valueOf(r1_JSONObject.getInt("adbanner-close")));
                }
                if (!r1_JSONObject.isNull("image-domain")) {
                    r0_String = r1_JSONObject.getString("image-domain").trim();
                    if (!r0_String.endsWith("/")) {
                        r0_String = r0_String + "/";
                    }
                    SharePreferenceUtils.setSharePreferencesValue("image-domain", r0_String);
                    Constants.updateImageDomains();
                }
                if (r1_JSONObject.isNull("image-reportable")) {
                    SharePreferenceUtils.setSharePreferencesValue("image-reportable", "0");
                } else {
                    r0_String = r1_JSONObject.getString("image-reportable");
                    SharePreferenceUtils.setSharePreferencesValue("image-reportable", r0_String);
                    QsbkApp.reportable = r0_String.equals("1");
                }
                if (!r1_JSONObject.isNull("audit_webview")) {
                    SharePreferenceUtils.setSharePreferencesValue("audit_webview", r1_JSONObject.getString("audit_webview"));
                }
                if (!r1_JSONObject.isNull("report-article")) {
                    r0_String = r1_JSONObject.getString("report-article");
                    SharePreferenceUtils.setSharePreferencesValue("report-article", r0_String);
                    ReportUtils.reset(r0_String);
                }
                if (!r1_JSONObject.isNull("ga_dispatch_period")) {
                    r0_String = r1_JSONObject.getString("ga_dispatch_period");
                    SharePreferenceUtils.setSharePreferencesValue("ga_dispatch_period", r0_String);
                    try {
                        QsbkApp.getInstance().setDispatchPeriod(Integer.parseInt(r0_String));
                    } catch (Exception e) {
                        QsbkApp.getInstance().setDispatchPeriod(QsbkApp.defaultDispatchPeriodInSeconds);
                    }
                }
                if (!r1_JSONObject.isNull("ga_sample_frequency")) {
                    r0_String = r1_JSONObject.getString("ga_sample_frequency");
                    SharePreferenceUtils.setSharePreferencesValue("ga_sample_frequency", r0_String);
                    try {
                        QsbkApp.getInstance().setSampleRate(Double.parseDouble(r0_String));
                    } catch (Exception e_2) {
                        QsbkApp.getInstance().setSampleRate((double)QsbkApp.defaultSampleRateInDouble);
                    }
                }
                if (!r1_JSONObject.isNull("mobile_speedup")) {
                    r0_String = r1_JSONObject.getString("mobile_speedup");
                    SharePreferenceUtils.setSharePreferencesValue("mobile_speedup", r0_String);
                    QsbkApp.mobileSpeedupDisable = "1".equalsIgnoreCase(r0_String);
                }
                if (!r1_JSONObject.isNull("feed_ad_max_time")) {
                    FeedsAdUtils.setMaxFeedAdShowTime(r1_JSONObject.optInt("feed_ad_max_time", HttpClient.RESP_CODE_LOCAL_ERROR));
                }
                SharePreferenceUtils.setSharePreferencesValue("config", r1_JSONObject.toString());
                SharePreferenceUtils.setSharePreferencesValue(ConfigService.a, (System.currentTimeMillis() / 1000) + RContactStorage.PRIMARY_KEY);
                this.a.c.obtainMessage().sendToTarget();
            } else {
                SharePreferenceUtils.setSharePreferencesValue(ConfigService.a, (System.currentTimeMillis() / 1000) + RContactStorage.PRIMARY_KEY);
                this.a.c.obtainMessage().sendToTarget();
            }
        } catch (QiushibaikeException e_3) {
            this.a.c.obtainMessage().sendToTarget();
        } catch (JSONException e_4) {
            this.a.c.obtainMessage().sendToTarget();
        }
    }
}
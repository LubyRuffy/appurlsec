package com.qiubai.library.adview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.AdViewTargeting.UpdateMode;
import com.qiubai.library.adview.base64.Crypts;
import com.qiubai.library.adview.obj.AdFill;
import com.qiubai.library.adview.obj.Extra;
import com.qiubai.library.adview.obj.Ration;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.cordova.NetworkManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.nearby.ui.NearByListActivity;

public class AdViewManager {
    public static final long CONFIG_SERVER_LIMIT_MSTIME = 300000;
    public static int configExpireTimeout;
    public static long mLastConfigTime;
    Iterator<Ration> a;
    public String adKey;
    private Extra b;
    public boolean bLocationForeign;
    private List<Ration> c;
    private double d;
    private WeakReference<Context> e;
    private boolean f;
    public int height;
    public String mDeviceid;
    public String mLocation;
    public int mSimulator;
    public int width;

    static {
        configExpireTimeout = 1200;
        mLastConfigTime = 0;
    }

    public AdViewManager(WeakReference<Context> r4_WeakReference_Context, String r5_String) {
        this.d = 0.0d;
        this.mSimulator = 0;
        this.f = false;
        this.bLocationForeign = false;
        this.mLocation = RContactStorage.PRIMARY_KEY;
        this.mDeviceid = RContactStorage.PRIMARY_KEY;
        this.e = r4_WeakReference_Context;
        this.adKey = r5_String;
        this.mDeviceid = getDeviceID((Context) r4_WeakReference_Context.get());
        WindowManager r0_WindowManager = (WindowManager) ((Context) r4_WeakReference_Context.get()).getSystemService("window");
        this.width = r0_WindowManager.getDefaultDisplay().getWidth();
        this.height = r0_WindowManager.getDefaultDisplay().getHeight();
        if (this.width > this.height) {
            int r0i = this.width;
            this.width = this.height;
            this.height = r0i;
        }
        this.mSimulator = a();
        this.bLocationForeign = isLocateForeign();
        if (this.bLocationForeign) {
            this.mLocation = "foreign";
        } else {
            this.mLocation = "china";
        }
    }

    private int a() {
        Context r0_Context = (Context) this.e.get();
        if (r0_Context == null) {
            return 0;
        }
        String r0_String = ((TelephonyManager) r0_Context.getSystemService("phone")).getDeviceId();
        int r0i;
        if (r0_String == null || r0_String.equals("000000000000000")) {
            r0i = 1;
            AdViewUtil.logInfo(new StringBuilder("isSimulator, ret=").append(r0i).toString());
            return r0i;
        } else {
            r0i = 0;
            AdViewUtil.logInfo(new StringBuilder("isSimulator, ret=").append(r0i).toString());
            return r0i;
        }
    }

    private static String a(InputStream r5_InputStream) {
        BufferedReader r1_BufferedReader = new BufferedReader(new InputStreamReader(r5_InputStream), 8192);
        StringBuilder r2_StringBuilder = new StringBuilder();
        while (true) {
            try {
                String r3_String = r1_BufferedReader.readLine();
                if (r3_String == null) {
                    try {
                        r5_InputStream.close();
                        return r2_StringBuilder.toString();
                    } catch (IOException e) {
                        return null;
                    }
                } else {
                    r2_StringBuilder.append(new StringBuilder(String.valueOf(r3_String)).append("\n").toString());
                }
            } catch (IOException e_2) {
                r5_InputStream.close();
                return null;
            }
        }
    }

    private String a(String r5_String) {
        if (r5_String == null || r5_String.length() == 0) {
            return null;
        }
        Context r0_Context = (Context) this.e.get();
        try {
            InputStream r2_InputStream = r0_Context.getAssets().open(new StringBuilder(String.valueOf(r5_String)).append(".txt").toString());
            String r0_String = a(r2_InputStream);
            r2_InputStream.close();
            AdViewUtil.logInfo(new StringBuilder("localconfig=").append(r0_String).toString());
            return r0_String;
        } catch (Exception e) {
            AdViewUtil.logError(RContactStorage.PRIMARY_KEY, e);
            return null;
        }
    }

    private String a(boolean r8z) {
        String r1_String = null;
        int r4i = 0;
        Context r0_Context = (Context) this.e.get();
        if (r0_Context == null) {
            return null;
        }
        SharedPreferences r2_SharedPreferences = r0_Context.getSharedPreferences(this.adKey, 0);
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (r8z) {
            r0_String = AdViewUtil.urlConfig;
            Object[] r1_ObjectA = new Object[6];
            r1_ObjectA[r4i] = this.adKey;
            r1_ObjectA[1] = Integer.valueOf(AdViewLayout.appVersion);
            r1_ObjectA[2] = Integer.valueOf(this.mSimulator);
            r1_ObjectA[3] = this.mLocation;
            r1_ObjectA[4] = Long.valueOf(AdViewUtil.currentSecond());
            r1_ObjectA[5] = Integer.valueOf(AdViewUtil.VERSION);
            r0_String = String.format(r0_String, r1_ObjectA);
            AdViewUtil.logInfo(new StringBuilder("config url ").append(r0_String).toString());
            r0_String = performGetContent(r0_String);
            if (r0_String == null || r0_String.length() <= 0 || (!b(r0_String))) {
                return RContactStorage.PRIMARY_KEY;
            }
            mLastConfigTime = System.currentTimeMillis();
            Editor r1_Editor = r2_SharedPreferences.edit();
            r1_Editor.putString("config", r0_String);
            r1_Editor.putLong(AdViewUtil.PREFS_STRING_TIMESTAMP, System.currentTimeMillis());
            r1_Editor.commit();
            this.f = true;
            return r0_String;
        } else {
            r0_String = r2_SharedPreferences.getString("config", r1_String);
            mLastConfigTime = r2_SharedPreferences.getLong(AdViewUtil.PREFS_STRING_TIMESTAMP, 0);
            return r0_String;
        }
    }

    private void a(AdViewLayout r5_AdViewLayout, String r6_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject(r6_String);
            AdViewUtil.configVer = r0_JSONObject.optInt("version", 0);
            if (r0_JSONObject.has("adFill")) {
                if (r0_JSONObject.getString("adFill").equals("0")) {
                    AdViewLayout.isadFill = false;
                } else {
                    AdViewLayout.isadFill = true;
                }
            } else {
                AdViewLayout.isadFill = false;
            }
            a(r0_JSONObject.getJSONObject("extra"));
            a(r0_JSONObject.getJSONArray("rations"));
            if (r0_JSONObject.has("afp")) {
                AdViewUtil.adfill_precent = Double.parseDouble(Crypts.xorMapDecrypt(r0_JSONObject.getString("afp"))) / 100.0d;
            }
        } catch (JSONException e) {
            this.b = new Extra();
        } catch (NullPointerException e_2) {
            this.b = new Extra();
        } catch (IOException e_3) {
            e_3.printStackTrace();
        }
    }

    private synchronized void a(JSONArray r12_JSONArray) {
        int r0i = 0;
        synchronized (this) {
            List r3_List = new ArrayList();
            double r0d = 0.0d;
            int r2i = r0i;
            while (true) {
                try {
                    if (r2i >= r12_JSONArray.length()) {
                        Collections.sort(r3_List);
                        this.c = r3_List;
                        if (AdViewLayout.isadFill) {
                            if (this.c.isEmpty()) {
                                this.c.add(0, new AdFill());
                                r0d = 100.0d;
                            } else {
                                this.c.add(r3_List.size() - 1, new AdFill());
                            }
                        }
                        this.a = this.c.iterator();
                        this.d = r0d;
                    } else {
                        JSONObject r4_JSONObject = r12_JSONArray.getJSONObject(r2i);
                        if (r4_JSONObject == null) {
                            r2i++;
                        } else if (AdViewAdRegistry.getInstance().adapterClassForAdType(Integer.valueOf(r4_JSONObject.getInt(QsbkDatabase.TYPE))) == null) {
                            AdViewUtil.logInfo(new StringBuilder("don't include ad=").append(r4_JSONObject.getInt(QsbkDatabase.TYPE)).toString());
                            r2i++;
                        } else {
                            Ration r5_Ration = new Ration();
                            r5_Ration.nid = r4_JSONObject.getString("nid");
                            r5_Ration.type = r4_JSONObject.getInt(QsbkDatabase.TYPE);
                            r5_Ration.name = r4_JSONObject.getString("nname");
                            r5_Ration.weight = (double) r4_JSONObject.getInt("weight");
                            r5_Ration.priority = r4_JSONObject.getInt("priority");
                            r5_Ration.key = r4_JSONObject.getString(SharedPref.KEY);
                            r5_Ration.key2 = r4_JSONObject.optString("key2");
                            r5_Ration.key3 = r4_JSONObject.optString("key3");
                            r5_Ration.type2 = r4_JSONObject.optInt("type2");
                            r5_Ration.logo = r4_JSONObject.optString("logo");
                            r3_List.add(r5_Ration);
                            r0d += r5_Ration.weight;
                            r2i++;
                        }
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    private synchronized void a(JSONObject r4_JSONObject) {
        Extra r0_Extra = new Extra();
        try {
            r0_Extra.cycleTime = r4_JSONObject.getInt("cycle_time");
            r0_Extra.locationOn = r4_JSONObject.getInt("loacation_on");
            r0_Extra.transition = r4_JSONObject.getInt("transition");
            r0_Extra.report = r4_JSONObject.getString("report");
            AdViewUtil.initConfigUrls(r0_Extra.report);
            JSONObject r1_JSONObject = r4_JSONObject.getJSONObject("background_color_rgb");
            r0_Extra.bgRed = r1_JSONObject.getInt("red");
            r0_Extra.bgGreen = r1_JSONObject.getInt("green");
            r0_Extra.bgBlue = r1_JSONObject.getInt("blue");
            r0_Extra.bgAlpha = r1_JSONObject.getInt("alpha") * 255;
            r1_JSONObject = r4_JSONObject.getJSONObject("text_color_rgb");
            r0_Extra.fgRed = r1_JSONObject.getInt("red");
            r0_Extra.fgGreen = r1_JSONObject.getInt("green");
            r0_Extra.fgBlue = r1_JSONObject.getInt("blue");
            r0_Extra.fgAlpha = r1_JSONObject.getInt("alpha") * 255;
        } catch (JSONException e) {
        }
        this.b = r0_Extra;
    }

    private void b(AdViewLayout r1_AdViewLayout, String r2_String) {
        a(r1_AdViewLayout, r2_String);
    }

    private boolean b(String r4_String) {
        boolean r0z = false;
        try {
            JSONObject r1_JSONObject = new JSONObject(r4_String);
            if (r1_JSONObject.has("extra") && r1_JSONObject.has("rations")) {
                r0z = true;
                AdViewUtil.logInfo(new StringBuilder("ret=").append(r0z).toString());
                return r0z;
            } else {
                AdViewUtil.logInfo(new StringBuilder("ret=").append(r0z).toString());
                return r0z;
            }
        } catch (JSONException e) {
            AdViewUtil.logError(RContactStorage.PRIMARY_KEY, e);
        } catch (NullPointerException e_2) {
            AdViewUtil.logError(RContactStorage.PRIMARY_KEY, e_2);
        }
    }

    private void c(AdViewLayout r5_AdViewLayout, String r6_String) {
        try {
            JSONObject r0_JSONObject = new JSONObject(r6_String);
            r0_JSONObject = this.bLocationForeign ? r0_JSONObject.getJSONObject("foreign_cfg") : r0_JSONObject.getJSONObject("china_cfg");
            if (r0_JSONObject.has("adFill")) {
                if (r0_JSONObject.getString("adFill").equals("0")) {
                    AdViewLayout.isadFill = false;
                } else {
                    AdViewLayout.isadFill = true;
                }
            } else {
                AdViewLayout.isadFill = false;
            }
            AdViewUtil.configVer = r0_JSONObject.optInt("version", 0);
            a(r0_JSONObject.getJSONObject("extra"));
            a(r0_JSONObject.getJSONArray("rations"));
            if (r0_JSONObject.has("afp")) {
                AdViewUtil.adfill_precent = Double.parseDouble(Crypts.xorMapDecrypt(r0_JSONObject.getString("afp"))) / 100.0d;
            }
        } catch (JSONException e) {
            a(r5_AdViewLayout, r6_String);
        } catch (IOException e_2) {
            e_2.printStackTrace();
        }
    }

    public static String getDeviceID(Context r4_Context) {
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r4_Context.getSystemService("phone");
        StringBuffer r1_StringBuffer = new StringBuffer();
        try {
            String r0_String = r0_TelephonyManager.getDeviceId();
            if (r0_String == null) {
                r1_StringBuffer.append("000000000000000");
            } else {
                r1_StringBuffer.append(r0_String);
            }
            while (r1_StringBuffer.length() < 15) {
                r1_StringBuffer.append("0");
            }
            r1_StringBuffer.append(":");
            r0_String = getIDByMAC(r4_Context);
            r1_StringBuffer.append(TextUtils.isEmpty(r0_String) ? "000000000000" : r0_String.replace(":", RContactStorage.PRIMARY_KEY));
            r1_StringBuffer.append(":");
            while (r1_StringBuffer.length() < 32) {
                r1_StringBuffer.append("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AdViewUtil.logInfo("Failed to take mac as IMEI");
        }
        return r1_StringBuffer.toString();
    }

    public static String getIDByMAC(Context r3_Context) {
        try {
            return ((WifiManager) r3_Context.getSystemService(NetworkManager.WIFI)).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            AdViewUtil.logError("Could not read MAC, forget to include ACCESS_WIFI_STATE permission?", e);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String performGetContent(String r6_String) {
        /*
        r1 = 0;
        r2 = new org.apache.http.impl.client.DefaultHttpClient;
        r2.<init>();
        r0 = new org.apache.http.client.methods.HttpGet;
        r0.<init>(r6);
        r3 = r2.execute(r0);	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        r0 = r3.getStatusLine();	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        r0 = r0.getStatusCode();	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r4) goto L_0x007b;
    L_0x001b:
        r0 = r3.getEntity();	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        if (r0 == 0) goto L_0x007b;
    L_0x0021:
        r0 = r0.getContent();	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        r1 = a(r0);	 //Catch:{ ClientProtocolException -> 0x004c, IOException -> 0x005d }
        r0 = r1;
    L_0x002a:
        r1 = new java.lang.StringBuilder;	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r4 = "httpCode : ";
        r1.<init>(r4);	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r3 = r3.getStatusLine();	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r3 = r3.getStatusCode();	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r1 = r1.append(r3);	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r1 = r1.toString();	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        com.qiubai.library.adview.util.AdViewUtil.logInfo(r1);	 //Catch:{ ClientProtocolException -> 0x0079, IOException -> 0x0077 }
        r1 = r2.getConnectionManager();
        r1.shutdown();
    L_0x004b:
        return r0;
    L_0x004c:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0050:
        r3 = "";
        com.qiubai.library.adview.util.AdViewUtil.logError(r3, r1);	 //Catch:{ all -> 0x006e }
        r1 = r2.getConnectionManager();
        r1.shutdown();
        goto L_0x004b;
    L_0x005d:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0061:
        r3 = "";
        com.qiubai.library.adview.util.AdViewUtil.logError(r3, r1);	 //Catch:{ all -> 0x006e }
        r1 = r2.getConnectionManager();
        r1.shutdown();
        goto L_0x004b;
    L_0x006e:
        r0 = move-exception;
        r1 = r2.getConnectionManager();
        r1.shutdown();
        throw r0;
    L_0x0077:
        r1 = move-exception;
        goto L_0x0061;
    L_0x0079:
        r1 = move-exception;
        goto L_0x0050;
    L_0x007b:
        r0 = r1;
        goto L_0x002a;
        */

    }

    public void fetchConfig(AdViewLayout r5_AdViewLayout) {
        String r0_String = a(AdViewTargeting.getUpdateMode() == UpdateMode.EVERYTIME);
        AdViewUtil.logInfo(r0_String);
        if ((r0_String == null || r0_String.length() == 0) && AdViewTargeting.getUpdateMode() != UpdateMode.EVERYTIME) {
            r0_String = a(true);
            if (r0_String == null || r0_String.length() == 0) {
                c(r5_AdViewLayout, a(this.adKey));
            } else {
                b(r5_AdViewLayout, r0_String);
            }
        } else {
            b(r5_AdViewLayout, r0_String);
        }
    }

    public void fetchConfigFromServer(AdViewLayout r5_AdViewLayout) {
        if ((!this.f) || System.currentTimeMillis() - mLastConfigTime >= 300000) {
            String r0_String = a(true);
            if (r0_String == null || r0_String.length() <= 0) {
            } else {
                b(r5_AdViewLayout, r0_String);
            }
        }
    }

    public synchronized Ration getAdFill() {
        return new AdFill();
    }

    public int getConfigExpiereTimeout() {
        return configExpireTimeout;
    }

    public synchronized Extra getExtra() {
        return (this.d > 0.0d ? 1 : (this.d == 0.0d? 0 : -1)) <= 0 ? null : this.b;
    }

    public synchronized Ration getRation() {
        Ration r0_Ration;
        double r3d = new Random().nextDouble() * this.d;
        double r1d = 0.0d;
        Iterator r5_Iterator = this.c.iterator();
        r0_Ration = null;
        while (r5_Iterator.hasNext()) {
            r0_Ration = (Ration) r5_Iterator.next();
            r1d += r0_Ration.weight;
            if (r1d >= r3d) {
                break;
            }
        }
        return r0_Ration;
    }

    public synchronized List<Ration> getRationList() {
        return this.c;
    }

    public synchronized Ration getRollover() {
        Ration r0_Ration = null;
        synchronized (this) {
            if (this.a == null) {
            } else {
                if (this.a.hasNext()) {
                    r0_Ration = (Ration) this.a.next();
                }
            }
        }
        return r0_Ration;
    }

    public synchronized Ration getadFillRation() {
        Ration r0_Ration;
        int r2i = 0;
        while (true) {
            if (r2i >= this.c.size()) {
                r0_Ration = null;
            } else if (((Ration) this.c.get(r2i)).type == 28) {
                r0_Ration = (Ration) this.c.get(r2i);
            } else {
                r2i++;
            }
        }
        return r0_Ration;
    }

    public boolean isLocateForeign() {
        Context r0_Context = (Context) this.e.get();
        if (r0_Context == null) {
            return false;
        }
        TelephonyManager r1_TelephonyManager = (TelephonyManager) r0_Context.getSystemService("phone");
        String r4_String = r1_TelephonyManager.getDeviceId();
        if (r4_String == null || r4_String.equals("000000000000000")) {
            AdViewUtil.logInfo("There is no imei, or run in emulator");
            return false;
        } else {
            AdViewUtil.logInfo(new StringBuilder("run in device, imei=").append(r4_String).toString());
            String r5_String = Locale.getDefault().getCountry().toLowerCase();
            String r1_String = r1_TelephonyManager.getNetworkCountryIso().toLowerCase();
            String r6_String = Locale.getDefault().toString();
            if (AdViewTargeting.getRunMode() == RunMode.TEST) {
                AdViewUtil.logInfo(new StringBuilder("run in device, imei=").append(r4_String).append("\n").append("countryCodeDefault=").append(r5_String).append("\n").append("countryCodeNetwork=").append(r1_String).append("\n'").append("locale=").append(r6_String).toString());
            }
            if (r1_String == null || r1_String.length() <= 0) {
                if (r5_String == null || r5_String.length() <= 0) {
                    Location r0_Location;
                    double r3d;
                    try {
                        Location r0_Location_2;
                        double r3d_2;
                        LocationManager r0_LocationManager = (LocationManager) r0_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
                        Criteria r1_Criteria = new Criteria();
                        r1_Criteria.setAccuracy(1);
                        r1_Criteria.setAltitudeRequired(false);
                        r1_Criteria.setBearingRequired(false);
                        r1_Criteria.setCostAllowed(true);
                        r1_Criteria.setPowerRequirement(1);
                        String r3_String = r0_LocationManager.getBestProvider(r1_Criteria, true);
                        Location r1_Location = null;
                        if (r3_String == null || r3_String.length() <= 0) {
                            r0_Location_2 = r1_Location;
                            if (r0_Location_2 == null) {
                                AdViewUtil.logInfo("location != null");
                                r3d_2 = r0_Location_2.getLatitude();
                                AdViewUtil.logInfo(new StringBuilder("locationString=").append(new StringBuilder(String.valueOf(r3d_2)).append(",").append(r0_Location_2.getLongitude()).toString()).toString());
                            } else {
                                AdViewUtil.logInfo("location == null");
                                return false;
                            }
                        } else {
                            AdViewUtil.logInfo(new StringBuilder("provider=").append(r3_String).append("\n").append(r3_String).append(" enable =").append(r0_LocationManager.isProviderEnabled(r3_String)).toString());
                            r0_Location_2 = r0_LocationManager.getLastKnownLocation(r3_String);
                            if (r0_Location_2 == null) {
                                AdViewUtil.logInfo("location == null");
                                return false;
                            }
                        }
                        AdViewUtil.logInfo("location != null");
                        r3d_2 = r0_Location_2.getLatitude();
                        AdViewUtil.logInfo(new StringBuilder("locationString=").append(new StringBuilder(String.valueOf(r3d_2)).append(",").append(r0_Location_2.getLongitude()).toString()).toString());
                    } catch (Exception e) {
                        AdViewUtil.logError(RContactStorage.PRIMARY_KEY, e);
                    }
                    return false;
                } else {
                    if (r5_String.compareTo("cn") == 0) {
                        return false;
                    }
                    return true;
                }
            } else {
                if (r1_String.compareTo("cn") == 0) {
                    return false;
                }
                return true;
            }
        }
    }

    public boolean needUpdateConfig() {
        return AdViewTargeting.getUpdateMode() == UpdateMode.EVERYTIME || !(this.f) || System.currentTimeMillis() - mLastConfigTime >= ((long) (configExpireTimeout * 1000));
    }

    public synchronized void resetRollover() {
        int r0i = 0;
        synchronized (this) {
            if (AdViewLayout.isadFill) {
                int r1i = AdViewUtil.common_count + AdViewUtil.adfill_count;
                while (r0i < this.c.size()) {
                    if (this.c.get(r0i) instanceof AdFill) {
                        this.c.remove(r0i);
                    }
                    r0i++;
                }
                if (r1i != 0) {
                    if (((double) (AdViewUtil.adfill_count / r1i)) > AdViewUtil.adfill_precent) {
                        if (this.c.isEmpty()) {
                            this.c.add(0, new AdFill());
                        } else {
                            this.c.add(this.c.size() - 1, new AdFill());
                        }
                    } else {
                        this.c.add(0, new AdFill());
                    }
                } else {
                    this.c.add(0, new AdFill());
                }
            }
            this.a = this.c.iterator();
        }
    }
}
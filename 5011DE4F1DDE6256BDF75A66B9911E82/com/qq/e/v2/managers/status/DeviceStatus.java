package com.qq.e.v2.managers.status;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import com.androidquery.util.Constants;
import com.qiubai.library.adview.util.AdViewReqManager;
import com.qq.e.a.a;
import com.qq.e.v2.util.Md5Util;
import com.qq.e.v2.util.StringUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class DeviceStatus {
    private String a;
    private String b;
    private String c;
    private int d;
    private int e;
    private int f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private volatile String l;
    private volatile String m;
    public final String model;
    private Context n;

    public DeviceStatus(Context r5_Context) {
        this.model = Build.MODEL;
        this.n = r5_Context;
        DisplayMetrics r1_DisplayMetrics = r5_Context.getResources().getDisplayMetrics();
        this.f = getVersion() > 3 ? r1_DisplayMetrics.densityDpi : AdViewReqManager.REQ_LIMIT_TIME;
        this.d = getVersion() > 3 ? a(r1_DisplayMetrics.density, r1_DisplayMetrics.widthPixels) : r1_DisplayMetrics.widthPixels;
        this.e = getVersion() > 3 ? a(r1_DisplayMetrics.density, r1_DisplayMetrics.heightPixels) : r1_DisplayMetrics.heightPixels;
        a();
    }

    private int a(float r2f, int r3i) {
        return (this.n.getApplicationInfo().flags & 8192) != 0 ? (int) (((float) r3i) / r2f) : r3i;
    }

    private void a() {
        Location r2_Location = null;
        try {
            LocationManager r0_LocationManager = (LocationManager) this.n.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
            if (r0_LocationManager == null) {
            } else {
                String r1_String;
                Criteria r1_Criteria = new Criteria();
                r1_Criteria.setAccuracy(XListViewHeader.STATE_REFRESHING);
                r1_Criteria.setAltitudeRequired(false);
                r1_Criteria.setBearingRequired(false);
                r1_Criteria.setCostAllowed(true);
                r1_Criteria.setPowerRequirement(1);
                try {
                    r1_String = r0_LocationManager.getBestProvider(r1_Criteria, true);
                    try {
                        r2_Location = r0_LocationManager.getLastKnownLocation(r1_String);
                    } catch (Throwable th) {
                    }
                } catch (Throwable th_2) {
                    r1_String = null;
                }
                if (r2_Location != null) {
                    this.l = r2_Location.getLatitude();
                    this.m = r2_Location.getLongitude();
                } else {
                    try {
                        r0_LocationManager.requestLocationUpdates(r1_String, 2000, 7000.0f, new a(this, r0_LocationManager));
                    } catch (Throwable th_3) {
                    }
                }
            }
        } catch (Throwable th_4) {
        }
    }

    public String getBaseStopInfo() {
        String r0_String = a.a(this.n);
        if (r0_String != null) {
            try {
                if (!r0_String.equals(RContactStorage.PRIMARY_KEY)) {
                    int r1i = Integer.parseInt(r0_String.substring(0, XListViewFooter.STATE_NOMORE));
                    int r3i = Integer.parseInt(r0_String.substring(XListViewFooter.STATE_NOMORE));
                    if (r1i == 460) {
                        StringBuffer r4_StringBuffer = new StringBuffer(RContactStorage.PRIMARY_KEY);
                        TelephonyManager r0_TelephonyManager = (TelephonyManager) this.n.getSystemService("phone");
                        if (r3i == 3 || r3i == 5) {
                            CdmaCellLocation r1_CdmaCellLocation = (CdmaCellLocation) r0_TelephonyManager.getCellLocation();
                            r3i = r1_CdmaCellLocation.getNetworkId();
                            r1i = r1_CdmaCellLocation.getBaseStationId();
                        } else {
                            GsmCellLocation r1_GsmCellLocation = (GsmCellLocation) r0_TelephonyManager.getCellLocation();
                            r3i = r1_GsmCellLocation.getLac();
                            r1i = r1_GsmCellLocation.getCid();
                        }
                        r4_StringBuffer.append(new StringBuilder("'lac':'").append(r3i).append("','cellid':'").append(r1i).append("','neighborings':[").toString());
                        List r3_List = r0_TelephonyManager.getNeighboringCellInfo();
                        Iterator r5_Iterator = r3_List.iterator();
                        r1i = 1;
                        while (r5_Iterator.hasNext()) {
                            NeighboringCellInfo r0_NeighboringCellInfo = (NeighboringCellInfo) r5_Iterator.next();
                            r4_StringBuffer.append(new StringBuilder("{'lac':'").append(r0_NeighboringCellInfo.getLac()).append("','cellid':'").append(r0_NeighboringCellInfo.getCid()).append("','rssi':'").append(r0_NeighboringCellInfo.getRssi()).append("'}").toString());
                            if (r3_List.size() <= 1 || r1i == r3_List.size()) {
                                r1i++;
                            } else {
                                r4_StringBuffer.append(",");
                                r1i++;
                            }
                        }
                        r4_StringBuffer.append("]");
                        return r4_StringBuffer.toString();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return "'nolbs':1";
    }

    public Carrier getCarrier() {
        String r0_String = getOperator();
        if (r0_String != null) {
            if (r0_String.equals("46000") || r0_String.equals("46002") || r0_String.equals("46007") || r0_String.equals("46020")) {
                return Carrier.CMCC;
            }
            if (r0_String.equals("46001") || r0_String.equals("46006")) {
                return Carrier.UNICOM;
            }
            if (r0_String.equals("46003") || r0_String.equals("46005")) {
                return Carrier.TELECOM;
            }
        }
        return Carrier.UNKNOWN;
    }

    public String getDataNet() {
        NetworkInfo r0_NetworkInfo;
        try {
            r0_NetworkInfo = ((ConnectivityManager) this.n.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            r0_NetworkInfo = null;
        }
        if (r0_NetworkInfo == null) {
            return null;
        }
        String r0_String;
        switch (r0_NetworkInfo.getType()) {
            case XListViewHeader.STATE_NORMAL:
                r0_String = "ed";
                this.j = r0_String;
                return this.j;
            case XListViewHeader.STATE_READY:
                r0_String = "wi";
                this.j = r0_String;
                return this.j;
        }
        r0_String = "unknow";
        this.j = r0_String;
        return this.j;
    }

    public int getDeviceDensity() {
        return this.f;
    }

    public int getDeviceHeight() {
        return this.e;
    }

    public int getDeviceWidth() {
        return this.d;
    }

    public String getDid() {
        if (this.k == null) {
            try {
                TelephonyManager r0_TelephonyManager = (TelephonyManager) this.n.getSystemService("phone");
                if (StringUtil.isEmpty(r0_TelephonyManager.getDeviceId())) {
                    this.k = RContactStorage.PRIMARY_KEY;
                } else {
                    this.k = Md5Util.encode(r0_TelephonyManager.getDeviceId().toLowerCase(Locale.US)).toLowerCase(Locale.US);
                }
            } catch (Exception e) {
            }
        }
        return this.k;
    }

    public Map<String, String> getLacAndCeilId() {
        int r1i = 0;
        String r0_String = getOperator();
        Map<String, String> r2_Map_String__String = new HashMap();
        if (StringUtil.isEmpty(r0_String) || "null".equalsIgnoreCase(r0_String)) {
            return r2_Map_String__String;
        }
        int r3i = Integer.parseInt(r0_String.substring(0, XListViewFooter.STATE_NOMORE));
        int r4i = Integer.parseInt(r0_String.substring(XListViewFooter.STATE_NOMORE));
        if (r3i == 460) {
            int r0i;
            TelephonyManager r0_TelephonyManager = (TelephonyManager) this.n.getSystemService("phone");
            if (r4i == 3 || r4i == 5) {
                CdmaCellLocation r0_CdmaCellLocation = (CdmaCellLocation) r0_TelephonyManager.getCellLocation();
                r1i = r0_CdmaCellLocation.getNetworkId();
                r0i = r0_CdmaCellLocation.getBaseStationId();
            } else {
                GsmCellLocation r0_GsmCellLocation = (GsmCellLocation) r0_TelephonyManager.getCellLocation();
                if (r0_GsmCellLocation != null) {
                    r1i = r0_GsmCellLocation.getLac();
                    r0i = r0_GsmCellLocation.getCid();
                } else {
                    r0i = 0;
                }
            }
            r2_Map_String__String.put("lac", r1i);
            r2_Map_String__String.put("cellid", r0i);
        }
        return r2_Map_String__String;
    }

    public String getLanguage() {
        if (this.c == null) {
            this.c = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
            if (this.c.length() == 0) {
                this.c = Util.ENGLISH;
            }
        }
        return this.c;
    }

    public String getLat() {
        return this.l;
    }

    public String getLng() {
        return this.m;
    }

    public String getMarket() {
        if (this.a != null) {
            return this.a;
        }
        try {
            PackageManager r1_PackageManager = this.n.getPackageManager();
            ResolveInfo r2_ResolveInfo = r1_PackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.qq.e")), Constants.FLAG_ACTIVITY_NO_ANIMATION);
            if (r2_ResolveInfo == null) {
                return null;
            }
            ActivityInfo r2_ActivityInfo = r2_ResolveInfo.activityInfo;
            if (r2_ActivityInfo == null) {
                return null;
            }
            PackageInfo r1_PackageInfo = r1_PackageManager.getPackageInfo(r2_ActivityInfo.packageName, 0);
            if (r1_PackageInfo == null) {
                return null;
            }
            this.a = r1_PackageInfo.versionCode + "_" + r2_ActivityInfo.packageName;
            return this.a;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public NetworkType getNetworkType() {
        int r0i;
        String r1_String = getDataNet();
        try {
            r0i = Integer.parseInt(getPhoneNet());
        } catch (NumberFormatException e) {
            r0i = 0;
        }
        if (r1_String != null && r1_String.equals("wi")) {
            return NetworkType.WIFI;
        }
        switch (r0i) {
            case XListViewHeader.STATE_READY:
            case XListViewHeader.STATE_REFRESHING:
                return NetworkType.NET_2G;
            case XListViewFooter.STATE_NOMORE:
            case XListViewFooter.STATE_NODATA:
            case ShareUtils.SHARE_SMS:
            case ShareUtils.SHARE_COPY:
            case ShareUtils.SHARE_COLLECT:
            case Base64.DONT_BREAK_LINES:
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
            case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
            case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
            case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
            case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                return NetworkType.NET_3G;
            case REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
            case NearbySelectView.TIME_15MIN:
                return NetworkType.NET_4G;
        }
        return NetworkType.UNKNOWN;
    }

    public String getOperator() {
        try {
            this.h = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
        }
        return this.h;
    }

    public String getPhoneNet() {
        try {
            this.i = ((TelephonyManager) this.n.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
        }
        return this.i;
    }

    public String getScreenOrientation() {
        if (this.n.getResources().getConfiguration().orientation == 2) {
            this.g = "l";
        } else if (this.n.getResources().getConfiguration().orientation == 1) {
            this.g = "p";
        }
        return this.g;
    }

    public String getUid() {
        if (this.b == null) {
            String r0_String = Secure.getString(this.n.getContentResolver(), "android_id");
            this.b = r0_String == null ? Md5Util.encode("emulator") : Md5Util.encode(r0_String);
        }
        return this.b;
    }

    public int getVersion() {
        try {
            return VERSION.SDK_INT;
        } catch (Exception e) {
            return XListViewFooter.STATE_NOMORE;
        }
    }
}
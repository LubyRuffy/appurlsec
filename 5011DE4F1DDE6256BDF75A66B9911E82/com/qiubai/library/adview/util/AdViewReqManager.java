package com.qiubai.library.adview.util;

import android.content.Context;
import android.os.Handler;
import com.qiubai.library.adview.AdViewLayout;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

public class AdViewReqManager implements b {
    public static final int REQ_DELAY_TIME = 30;
    public static final int REQ_LIMIT_TIME = 120;
    public static final String REQ_URL = "http://report.adview.cn/agent/adview_reqinfo.php";
    private static AdViewReqManager c;
    public static long mLastReqInfoTime;
    Handler a;
    SendRunnable b;
    private DBOpenHelper d;
    private AdViewLayout e;
    private Object f;
    private Context g;
    public ReqInfoItem mCurReqInfo;
    public AdViewNetFetchThread mFetcher;
    public ArrayList<ReqInfoItem> mPendingReqInfos;

    public static class ReqInfoItem {
        long a;
        int b;
        boolean c;
        JSONArray d;

        public ReqInfoItem() {
            this.b = 0;
            this.c = false;
            this.d = new JSONArray();
        }

        public boolean isDataEmpty() {
            return this.d == null || this.d.length() < 1;
        }

        public synchronized boolean isSending() {
            return this.c;
        }

        public String makePostBody(AdViewLayout r6_AdViewLayout) {
            long r0j = AdViewUtil.currentSecond();
            return new StringBuilder("appid=").append(r6_AdViewLayout.adKey).append("&bundle=").append(AdViewLayout.appPackage).append("&uuid=0&keydev=").append(r6_AdViewLayout.deviceId).append("&client=0&data=").append(this.d).append("&dataTime=").append(this.a).append("&sdkver=").append(AdViewUtil.VERSION).append("&time=").append(r0j).append("&configVer=").append(AdViewUtil.configVer).append("&token=").append(r6_AdViewLayout.getTokenMd5(r0j)).toString();
        }

        public void saveToDB(DBOpenHelper r4_DBOpenHelper) {
            synchronized (r4_DBOpenHelper) {
                if (this.b != 0) {
                } else {
                    try {
                        this.b = r4_DBOpenHelper.getMaxId() + 1;
                        r4_DBOpenHelper.addVariable(this.b, Long.toString(this.a), this.d.toString());
                    } catch (Exception e) {
                        this.b = 0;
                    }
                }
            }
        }

        public synchronized void setSending(boolean r2z) {
            this.c = r2z;
        }

        public void storeInfo(String r6_String, int r7i, String r8_String) {
            int r0i = 1;
            int r2i = 0;
            while (true) {
                try {
                    if (r2i >= this.d.length()) {
                        r0i = 0;
                    } else {
                        JSONObject r3_JSONObject = this.d.getJSONObject(r2i);
                        if (r3_JSONObject.getInt(QsbkDatabase.TYPE) == r7i) {
                            if (r3_JSONObject.has(r8_String)) {
                                r3_JSONObject.put(r8_String, r3_JSONObject.getInt(r8_String) + 1);
                            } else {
                                r3_JSONObject.put(r8_String, 1);
                            }
                        } else {
                            r2i++;
                        }
                    }
                    if (r0i == 0) {
                        JSONObject r0_JSONObject = new JSONObject();
                        r0_JSONObject.put(QsbkDatabase.TYPE, r7i);
                        r0_JSONObject.put(r8_String, 1);
                        this.d.put(r0_JSONObject);
                    }
                    this.a = AdViewUtil.currentSecond();
                } catch (JSONException e) {
                    AdViewUtil.logError("JSONException", e);
                }
                return;
            }
        }
    }

    public class SendRunnable implements Runnable {
        public void run() {
            AdViewReqManager.this.b();
            AdViewReqManager.this.b = null;
            AdViewReqManager.this.a((int)REQ_DELAY_TIME);
        }
    }

    static {
        c = null;
        mLastReqInfoTime = 0;
    }

    private AdViewReqManager(Context r3_Context) {
        this.mPendingReqInfos = new ArrayList();
        this.mFetcher = null;
        this.d = null;
        this.f = new Object();
        this.a = new Handler();
        this.b = null;
        this.g = r3_Context;
        this.d = new DBOpenHelper(r3_Context, "reqinfo.db");
    }

    private void a() {
        ReqInfoItem r0_ReqInfoItem;
        long r1j = AdViewUtil.currentSecond();
        ArrayList r3_ArrayList = new ArrayList();
        Iterator r4_Iterator = this.mPendingReqInfos.iterator();
        while (r4_Iterator.hasNext()) {
            r0_ReqInfoItem = (ReqInfoItem) r4_Iterator.next();
            if (r1j - r0_ReqInfoItem.a > 86400) {
                r3_ArrayList.add(r0_ReqInfoItem);
            }
        }
        Iterator r1_Iterator = r3_ArrayList.iterator();
        while (r1_Iterator.hasNext()) {
            r0_ReqInfoItem = (ReqInfoItem) r1_Iterator.next();
            this.d.delVariable(r0_ReqInfoItem.b);
            this.mPendingReqInfos.remove(r0_ReqInfoItem);
        }
    }

    private void a(int r6i) {
        synchronized (this.f) {
            if (this.b != null) {
            } else if (this.mPendingReqInfos.size() >= 1 || (!this.mCurReqInfo.isDataEmpty())) {
                this.a.removeCallbacks(this.b);
                this.b = new SendRunnable();
                this.a.postDelayed(this.b, (long) (r6i * 1000));
            }
        }
    }

    private void a(Context r4_Context) {
        Iterator r1_Iterator;
        if (this.mCurReqInfo == null || this.mCurReqInfo.isDataEmpty()) {
            this.mCurReqInfo = null;
            r1_Iterator = this.mPendingReqInfos.iterator();
            while (r1_Iterator.hasNext()) {
                ((ReqInfoItem) r1_Iterator.next()).saveToDB(this.d);
            }
        } else {
            this.mPendingReqInfos.add(this.mCurReqInfo);
            this.mCurReqInfo = null;
            r1_Iterator = this.mPendingReqInfos.iterator();
            while (r1_Iterator.hasNext()) {
                ((ReqInfoItem) r1_Iterator.next()).saveToDB(this.d);
            }
        }
    }

    private void a(AdViewNetFetchThread r8_AdViewNetFetchThread, boolean r9z) {
        ReqInfoItem r0_ReqInfoItem = (ReqInfoItem) r8_AdViewNetFetchThread.getUserObject();
        if (r0_ReqInfoItem == null) {
        } else if (r9z) {
            synchronized (this.mPendingReqInfos) {
                Iterator r4_Iterator = this.mPendingReqInfos.iterator();
                ReqInfoItem r2_ReqInfoItem = null;
                while (r4_Iterator.hasNext()) {
                    ReqInfoItem r1_ReqInfoItem = (ReqInfoItem) r4_Iterator.next();
                    if (r0_ReqInfoItem.b == r1_ReqInfoItem.b) {
                        r2_ReqInfoItem = r1_ReqInfoItem;
                    }
                }
                if (r2_ReqInfoItem != null) {
                    this.mPendingReqInfos.remove(r2_ReqInfoItem);
                }
            }
            this.d.delVariable(r0_ReqInfoItem.b);
        } else {
            r0_ReqInfoItem.setSending(false);
        }
    }

    private static boolean a(ArrayList<ReqInfoItem> r2_ArrayList_ReqInfoItem, int r3i) {
        Iterator r1_Iterator = r2_ArrayList_ReqInfoItem.iterator();
        while (r1_Iterator.hasNext()) {
            if (((ReqInfoItem) r1_Iterator.next()).b == r3i) {
                return true;
            }
        }
        return false;
    }

    private void b() {
        synchronized (this.f) {
            if (this.mCurReqInfo == null) {
                this.mCurReqInfo = new ReqInfoItem();
            }
            Iterator r2_Iterator;
            ReqInfoItem r0_ReqInfoItem;
            if (this.mCurReqInfo.isDataEmpty() || AdViewUtil.currentSecond() - mLastReqInfoTime < 120) {
                if (this.e == null || (!AdViewLayout.isConnectInternet(this.g))) {
                } else if (this.mPendingReqInfos.size() <= 0 || this.mFetcher != null) {
                } else {
                    r2_Iterator = this.mPendingReqInfos.iterator();
                    while (r2_Iterator.hasNext()) {
                        r0_ReqInfoItem = (ReqInfoItem) r2_Iterator.next();
                        if (!r0_ReqInfoItem.isSending()) {
                            r0_ReqInfoItem.setSending(true);
                            this.mFetcher = new AdViewNetFetchThread(REQ_URL, r0_ReqInfoItem.makePostBody(this.e));
                            this.mFetcher.setFetchListener(this);
                            this.mFetcher.setUserObject(r0_ReqInfoItem);
                            this.mFetcher.start();
                            break;
                            break;
                            break;
                        }
                    }
                }
            } else {
                a(this.g);
                this.mCurReqInfo = new ReqInfoItem();
                if (this.e == null || AdViewLayout.isConnectInternet(this.g)) {
                } else if (this.mPendingReqInfos.size() <= 0 || this.mFetcher != null) {
                } else {
                    r2_Iterator = this.mPendingReqInfos.iterator();
                    while (r2_Iterator.hasNext()) {
                        r0_ReqInfoItem = (ReqInfoItem) r2_Iterator.next();
                        if (r0_ReqInfoItem.isSending()) {
                            while (r2_Iterator.hasNext()) {
                                r0_ReqInfoItem = (ReqInfoItem) r2_Iterator.next();
                                if (r0_ReqInfoItem.isSending()) {
                                    r0_ReqInfoItem.setSending(true);
                                    this.mFetcher = new AdViewNetFetchThread(REQ_URL, r0_ReqInfoItem.makePostBody(this.e));
                                    this.mFetcher.setFetchListener(this);
                                    this.mFetcher.setUserObject(r0_ReqInfoItem);
                                    this.mFetcher.start();
                                    break;
                                    break;
                                    break;
                                }
                            }
                        } else {
                            r0_ReqInfoItem.setSending(true);
                            this.mFetcher = new AdViewNetFetchThread(REQ_URL, r0_ReqInfoItem.makePostBody(this.e));
                            this.mFetcher.setFetchListener(this);
                            this.mFetcher.setUserObject(r0_ReqInfoItem);
                            this.mFetcher.start();
                            break;
                            break;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static AdViewReqManager getInstance(Context r1_Context) {
        if (c != null || c != null) {
            return c;
        }
        c = new AdViewReqManager(r1_Context);
        return c;
    }

    public void loadPendingReqInfos(Context r6_Context) {
        synchronized (this.f) {
            Iterator r2_Iterator = this.d.getReqInfoItems().iterator();
            while (r2_Iterator.hasNext()) {
                ReqInfoItem r0_ReqInfoItem = (ReqInfoItem) r2_Iterator.next();
                if (!a(this.mPendingReqInfos, r0_ReqInfoItem.b)) {
                    this.mPendingReqInfos.add(r0_ReqInfoItem);
                }
            }
            a();
        }
    }

    public void notifyFetchStatus(AdViewNetFetchThread r6_AdViewNetFetchThread, int r7i, Object r8_Object) {
        boolean r0z = true;
        if (r7i == 0) {
            if (r8_Object instanceof String) {
                try {
                    JSONObject r2_JSONObject = new JSONObject((String) r8_Object);
                    if (r2_JSONObject == null || 1 != r2_JSONObject.optInt("result", 0)) {
                        AdViewUtil.logInfo("upload error");
                        r0z = false;
                    }
                } catch (JSONException e) {
                    r0z = false;
                }
            }
            r0z = false;
        } else {
            if (r7i == -1) {
                AdViewUtil.logInfo("upload error");
            }
            r0z = false;
        }
        synchronized (this.f) {
            a(r6_AdViewNetFetchThread, r0z);
            if (r6_AdViewNetFetchThread == this.mFetcher) {
                this.mFetcher = null;
            }
        }
        if (r7i != 101) {
            b();
        }
    }

    public void savePendingReqInfos(Context r3_Context) {
        synchronized (this.f) {
            a(r3_Context);
        }
    }

    public void storeInfo(AdViewLayout r5_AdViewLayout, int r6i, String r7_String) {
        if (r5_AdViewLayout == null) {
        } else {
            this.e = r5_AdViewLayout;
            b();
            synchronized (this.f) {
                if (this.mCurReqInfo.isDataEmpty()) {
                    if (this.g == null) {
                        this.g = (Context) r5_AdViewLayout.activityReference.get();
                    }
                    this.mCurReqInfo = new ReqInfoItem();
                    mLastReqInfoTime = AdViewUtil.currentSecond();
                }
                this.mCurReqInfo.storeInfo(r5_AdViewLayout.adKey, r6i, r7_String);
            }
            a((int)REQ_DELAY_TIME);
        }
    }
}
package com.google.analytics.tracking.android;

import android.text.TextUtils;
import com.tencent.tauth.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.cordova.Globalization;

public class GoogleTracker implements Tracker {
    private static final DecimalFormat a;
    private final al b;
    private final a c;
    private ExceptionParser d;
    private boolean e;
    private boolean f;


    private static class a {
        private Map<String, String> a;
        private Map<String, String> b;

        private a() {
            this.a = new HashMap();
            this.b = new HashMap();
        }

        public void clearTemporaryValues() {
            this.a.clear();
        }

        public String get(String r2_String) {
            String r0_String = (String) this.a.get(r2_String);
            return r0_String != null ? r0_String : (String) this.b.get(r2_String);
        }

        public Map<String, String> getKeysAndValues() {
            Map<String, String> r0_Map_String__String = new HashMap(this.b);
            r0_Map_String__String.putAll(this.a);
            return r0_Map_String__String;
        }

        public void set(String r2_String, String r3_String) {
            this.b.put(r2_String, r3_String);
        }

        public void setAll(Map<String, String> r2_Map_String__String, Boolean r3_Boolean) {
            if (r3_Boolean.booleanValue()) {
                this.a.putAll(r2_Map_String__String);
            } else {
                this.b.putAll(r2_Map_String__String);
            }
        }

        public void setForNextHit(String r2_String, String r3_String) {
            this.a.put(r2_String, r3_String);
        }
    }

    static {
        a = new DecimalFormat("0.######", new DecimalFormatSymbols(Locale.US));
    }

    GoogleTracker(String r4_String, al r5_al) {
        this.e = false;
        this.f = false;
        if (r4_String == null) {
            throw new IllegalArgumentException("trackingId cannot be null");
        } else {
            this.b = r5_al;
            this.c = new a();
            this.c.set(ModelFields.TRACKING_ID, r4_String);
            this.c.set(ModelFields.SAMPLE_RATE, "100");
            this.c.setForNextHit(ModelFields.SESSION_CONTROL, "start");
        }
    }

    private static String a(long r5j) {
        return a.format(((double) r5j) / 1000000.0d);
    }

    private void a() {
        if (this.e) {
            throw new IllegalStateException("Tracker closed");
        }
    }

    public static Map<String, String> constructEvent(String r4_String, String r5_String, String r6_String, Long r7_Long) {
        Map<String, String> r0_Map_String__String = new HashMap();
        r0_Map_String__String.put(ModelFields.EVENT_CATEGORY, r4_String);
        r0_Map_String__String.put(ModelFields.EVENT_ACTION, r5_String);
        r0_Map_String__String.put(ModelFields.EVENT_LABEL, r6_String);
        if (r7_Long != null) {
            r0_Map_String__String.put(ModelFields.EVENT_VALUE, Long.toString(r7_Long.longValue()));
        }
        return r0_Map_String__String;
    }

    Map<String, String> a(Item r5_Item, Transaction r6_Transaction) {
        Map<String, String> r0_Map_String__String = new HashMap();
        r0_Map_String__String.put(ModelFields.TRANSACTION_ID, r6_Transaction.getTransactionId());
        r0_Map_String__String.put(Globalization.CURRENCYCODE, r6_Transaction.a());
        r0_Map_String__String.put(ModelFields.ITEM_CODE, r5_Item.getProductSKU());
        r0_Map_String__String.put(ModelFields.ITEM_NAME, r5_Item.getProductName());
        r0_Map_String__String.put(ModelFields.ITEM_CATEGORY, r5_Item.getProductCategory());
        r0_Map_String__String.put(ModelFields.ITEM_PRICE, a(r5_Item.getItemPriceInMicros()));
        r0_Map_String__String.put(ModelFields.ITEM_QUANTITY, Long.toString(r5_Item.getItemQuantity()));
        return r0_Map_String__String;
    }

    public boolean anonymizeIpEnabled() {
        return am.safeParseBoolean(this.c.get(ModelFields.ANONYMIZE_IP));
    }

    public void close() {
        this.e = true;
        this.b.closeTracker(this);
    }

    public Map<String, String> constructException(String r4_String, boolean r5z) {
        Map<String, String> r0_Map_String__String = new HashMap();
        r0_Map_String__String.put(ModelFields.EX_DESCRIPTION, r4_String);
        r0_Map_String__String.put(ModelFields.EX_FATAL, Boolean.toString(r5z));
        return r0_Map_String__String;
    }

    public Map<String, String> constructRawException(String r4_String, Throwable r5_Throwable, boolean r6z) throws IOException {
        Map<String, String> r0_Map_String__String = new HashMap();
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        ObjectOutputStream r2_ObjectOutputStream = new ObjectOutputStream(r1_OutputStream);
        r2_ObjectOutputStream.writeObject(r5_Throwable);
        r2_ObjectOutputStream.close();
        r0_Map_String__String.put(ModelFields.RAW_EXCEPTION, am.a(r1_OutputStream.toByteArray()));
        if (r4_String != null) {
            r0_Map_String__String.put(ModelFields.EXCEPTION_THREAD_NAME, r4_String);
        }
        r0_Map_String__String.put(ModelFields.EX_FATAL, Boolean.toString(r6z));
        return r0_Map_String__String;
    }

    public Map<String, String> constructTiming(String r4_String, long r5j, String r7_String, String r8_String) {
        Map<String, String> r0_Map_String__String = new HashMap();
        r0_Map_String__String.put(ModelFields.TIMING_CATEGORY, r4_String);
        r0_Map_String__String.put(ModelFields.TIMING_VALUE, Long.toString(r5j));
        r0_Map_String__String.put(ModelFields.TIMING_VAR, r7_String);
        r0_Map_String__String.put(ModelFields.TIMING_LABEL, r8_String);
        return r0_Map_String__String;
    }

    public Map<String, String> constructTransaction(Transaction r5_Transaction) {
        Map<String, String> r0_Map_String__String = new HashMap();
        r0_Map_String__String.put(ModelFields.TRANSACTION_ID, r5_Transaction.getTransactionId());
        r0_Map_String__String.put(ModelFields.TRANSACTION_AFFILIATION, r5_Transaction.getAffiliation());
        r0_Map_String__String.put(ModelFields.TRANSACTION_SHIPPING, a(r5_Transaction.getShippingCostInMicros()));
        r0_Map_String__String.put(ModelFields.TRANSACTION_TAX, a(r5_Transaction.getTotalTaxInMicros()));
        r0_Map_String__String.put(ModelFields.TRANSACTION_TOTAL, a(r5_Transaction.getTotalCostInMicros()));
        r0_Map_String__String.put(Globalization.CURRENCYCODE, r5_Transaction.a());
        return r0_Map_String__String;
    }

    public String get(String r2_String) {
        return this.c.get(r2_String);
    }

    public String getAppId() {
        return this.c.get(ModelFields.APP_ID);
    }

    public String getAppInstallerId() {
        return this.c.get(ModelFields.APP_INSTALLER_ID);
    }

    public ExceptionParser getExceptionParser() {
        return this.d;
    }

    public double getSampleRate() {
        return am.safeParseDouble(this.c.get(ModelFields.SAMPLE_RATE));
    }

    public String getTrackingId() {
        return this.c.get(ModelFields.TRACKING_ID);
    }

    public boolean getUseSecure() {
        return Boolean.parseBoolean(this.c.get(ModelFields.USE_SECURE));
    }

    public void send(String r3_String, Map<String, String> r4_Map_String__String) {
        boolean r1z = true;
        this.f = true;
        a();
        if (r4_Map_String__String == null) {
            r4_Map_String__String = new HashMap();
        }
        r4_Map_String__String.put(ModelFields.HIT_TYPE, r3_String);
        this.c.setAll(r4_Map_String__String, Boolean.valueOf(r1z));
        this.b.sendHit(this.c.getKeysAndValues());
        this.c.clearTemporaryValues();
    }

    public void set(String r2_String, String r3_String) {
        this.c.set(r2_String, r3_String);
    }

    public void setAnonymizeIp(boolean r4z) {
        this.c.set(ModelFields.ANONYMIZE_IP, Boolean.toString(r4z));
    }

    public void setAppId(String r3_String) {
        this.c.set(ModelFields.APP_ID, r3_String);
    }

    public void setAppInstallerId(String r3_String) {
        this.c.set(ModelFields.APP_INSTALLER_ID, r3_String);
    }

    public void setAppName(String r3_String) {
        if (this.f) {
            z.i("Tracking already started, setAppName call ignored");
        } else if (TextUtils.isEmpty(r3_String)) {
            z.i("setting appName to empty value not allowed, call ignored");
        } else {
            this.c.set(Constants.PARAM_APPNAME, r3_String);
        }
    }

    public void setAppScreen(String r3_String) {
        a();
        this.c.set(Constants.PARAM_COMMENT, r3_String);
    }

    public void setAppVersion(String r3_String) {
        if (this.f) {
            z.i("Tracking already started, setAppVersion call ignored");
        } else {
            this.c.set(ModelFields.APP_VERSION, r3_String);
        }
    }

    public void setCampaign(String r3_String) {
        this.c.setForNextHit(ModelFields.CAMPAIGN, r3_String);
    }

    public void setExceptionParser(ExceptionParser r1_ExceptionParser) {
        this.d = r1_ExceptionParser;
    }

    public void setReferrer(String r3_String) {
        this.c.setForNextHit(ModelFields.REFERRER, r3_String);
    }

    public void setSampleRate(double r4d) {
        this.c.set(ModelFields.SAMPLE_RATE, Double.toString(r4d));
    }

    public void setStartSession(boolean r4z) {
        a();
        this.c.setForNextHit(ModelFields.SESSION_CONTROL, r4z ? "start" : null);
    }

    public void setUseSecure(boolean r4z) {
        this.c.set(ModelFields.USE_SECURE, Boolean.toString(r4z));
    }

    public void trackEvent(String r3_String, String r4_String, String r5_String, Long r6_Long) {
        send(ModelFields.EVENT, constructEvent(r3_String, r4_String, r5_String, r6_Long));
    }

    public void trackException(String r3_String, Throwable r4_Throwable, boolean r5z) {
        String r0_String;
        a();
        if (this.d != null) {
            r0_String = this.d.getDescription(r3_String, r4_Throwable);
        } else {
            try {
                send(ModelFields.EXCEPTION, constructRawException(r3_String, r4_Throwable, r5z));
                return;
            } catch (IOException e) {
                z.h("trackException: couldn't serialize, sending \"Unknown Exception\"");
                r0_String = "Unknown Exception";
            }
        }
        trackException(r0_String, r5z);
    }

    public void trackException(String r3_String, boolean r4z) {
        send(ModelFields.EXCEPTION, constructException(r3_String, r4z));
    }

    public void trackTiming(String r3_String, long r4j, String r6_String, String r7_String) {
        send(ModelFields.TIMING, constructTiming(r3_String, r4j, r6_String, r7_String));
    }

    public void trackTransaction(Transaction r4_Transaction) {
        send(ModelFields.TRANSACTION, constructTransaction(r4_Transaction));
        Iterator r1_Iterator = r4_Transaction.getItems().iterator();
        while (r1_Iterator.hasNext()) {
            send(Globalization.ITEM, a((Item) r1_Iterator.next(), r4_Transaction));
        }
    }

    public void trackView() {
        a();
        if (TextUtils.isEmpty(this.c.get(Constants.PARAM_COMMENT))) {
            throw new IllegalStateException("trackerEnterScreen requires a appScreen to be set");
        } else {
            send(ModelFields.APP_VIEW, null);
        }
    }

    public void trackView(String r1_String) {
        a();
        setAppScreen(r1_String);
        trackView();
    }
}
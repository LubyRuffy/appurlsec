package android.support.v7.internal.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.apache.cordova.Globalization;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import qsbk.app.bean.Base;

public class ActivityChooserModel extends DataSetObservable {
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String a;
    private static final Object b;
    private static final Map<String, ActivityChooserModel> c;
    private final Object d;
    private final List<ActivityResolveInfo> e;
    private final List<HistoricalRecord> f;
    private final Context g;
    private final String h;
    private Intent i;
    private ActivitySorter j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private OnChooseActivityListener p;


    public static interface ActivityChooserModelClient {
        public void setActivityChooserModel(ActivityChooserModel r1_ActivityChooserModel);
    }

    public final class ActivityResolveInfo implements Comparable<android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo r2_ResolveInfo) {
            this.resolveInfo = r2_ResolveInfo;
        }

        public int compareTo(android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo r3_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo) {
            return Float.floatToIntBits(r3_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object r5_Object) {
            if (this == r5_Object) {
                return true;
            }
            if (r5_Object == null) {
                return false;
            }
            if (getClass() != r5_Object.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo) r5_Object).weight)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public String toString() {
            StringBuilder r0_StringBuilder = new StringBuilder();
            r0_StringBuilder.append("[");
            r0_StringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
            r0_StringBuilder.append("; weight:").append(new BigDecimal((double) this.weight));
            r0_StringBuilder.append("]");
            return r0_StringBuilder.toString();
        }
    }

    public static interface ActivitySorter {
        public void sort(Intent r1_Intent, List<android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo> r2_List_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo, List<android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord> r3_List_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord);
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName r1_ComponentName, long r2j, float r4f) {
            this.activity = r1_ComponentName;
            this.time = r2j;
            this.weight = r4f;
        }

        public HistoricalRecord(String r2_String, long r3j, float r5f) {
            this(ComponentName.unflattenFromString(r2_String), r3j, r5f);
        }

        public boolean equals(Object r7_Object) {
            if (this == r7_Object) {
                return true;
            }
            if (r7_Object == null) {
                return false;
            }
            if (getClass() != r7_Object.getClass()) {
                return false;
            }
            android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord r7_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord = (android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord) r7_Object;
            if (this.activity == null) {
                if (r7_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.activity != null) {
                    return false;
                }
            } else if (!this.activity.equals(r7_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.activity)) {
                return false;
            }
            if (this.time != r7_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.time) {
                return false;
            }
            if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(r7_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.weight)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((((this.activity == null ? 0 : this.activity.hashCode()) + 31) * 31) + ((int) (this.time ^ (this.time >>> 32)))) * 31 + Float.floatToIntBits(this.weight);
        }

        public String toString() {
            StringBuilder r0_StringBuilder = new StringBuilder();
            r0_StringBuilder.append("[");
            r0_StringBuilder.append("; activity:").append(this.activity);
            r0_StringBuilder.append("; time:").append(this.time);
            r0_StringBuilder.append("; weight:").append(new BigDecimal((double) this.weight));
            r0_StringBuilder.append("]");
            return r0_StringBuilder.toString();
        }
    }

    public static interface OnChooseActivityListener {
        public boolean onChooseActivity(ActivityChooserModel r1_ActivityChooserModel, Intent r2_Intent);
    }

    private final class a implements android.support.v7.internal.widget.ActivityChooserModel.ActivitySorter {
        private final Map<String, android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo> b;

        private a() {
            this.b = new HashMap();
        }

        public void sort(Intent r7_Intent, List<android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo> r8_List_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo, List<android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord> r9_List_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord) {
            Map r4_Map = this.b;
            r4_Map.clear();
            int r2i = r8_List_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.size();
            int r1i = 0;
            while (r1i < r2i) {
                android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo r0_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo = (android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo) r8_List_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.get(r1i);
                r0_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.weight = 0.0f;
                r4_Map.put(r0_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.resolveInfo.activityInfo.packageName, r0_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo);
                r1i++;
            }
            float r2f = 1.0f;
            int r3i = r9_List_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.size() - 1;
            while (r3i >= 0) {
                float r0f;
                android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord r0_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord = (android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord) r9_List_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.get(r3i);
                android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo r1_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo = (android.support.v7.internal.widget.ActivityChooserModel.ActivityResolveInfo) r4_Map.get(r0_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.activity.getPackageName());
                if (r1_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo != null) {
                    r1_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.weight = r0_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.weight * r2f + r1_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo.weight;
                    r0f = 0.95f * r2f;
                } else {
                    r0f = r2f;
                }
                r3i--;
                r2f = r0f;
            }
            Collections.sort(r8_List_android_support_v7_internal_widget_ActivityChooserModel_ActivityResolveInfo);
        }
    }

    private final class b extends AsyncTask<Object, Void, Void> {
        private b() {
        }

        public Void doInBackground(Object ... r13_ObjectA) {
            OutputStream r3_OutputStream;
            int r2i = 0;
            List r0_List = (List) r13_ObjectA[0];
            String r1_String = (String) r13_ObjectA[1];
            try {
                r3_OutputStream = ActivityChooserModel.this.g.openFileOutput(r1_String, 0);
                XmlSerializer r4_XmlSerializer = Xml.newSerializer();
                r4_XmlSerializer.setOutput(r3_OutputStream, null);
                r4_XmlSerializer.startDocument(Base.UTF8, Boolean.valueOf(true));
                r4_XmlSerializer.startTag(null, "historical-records");
                int r5i = r0_List.size();
                while (r2i < r5i) {
                    android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord r1_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord = (android.support.v7.internal.widget.ActivityChooserModel.HistoricalRecord) r0_List.remove(0);
                    r4_XmlSerializer.startTag(null, "historical-record");
                    r4_XmlSerializer.attribute(null, "activity", r1_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.activity.flattenToString());
                    r4_XmlSerializer.attribute(null, Globalization.TIME, String.valueOf(r1_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.time));
                    r4_XmlSerializer.attribute(null, "weight", String.valueOf(r1_android_support_v7_internal_widget_ActivityChooserModel_HistoricalRecord.weight));
                    r4_XmlSerializer.endTag(null, "historical-record");
                    r2i++;
                }
                r4_XmlSerializer.endTag(null, "historical-records");
                r4_XmlSerializer.endDocument();
                ActivityChooserModel.this.l = true;
                if (r3_OutputStream != null) {
                    try {
                        r3_OutputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e_2) {
                Log.e(a, "Error writing historical recrod file: " + r1_String, e_2);
            }
            return null;
        }
    }

    static {
        a = ActivityChooserModel.class.getSimpleName();
        b = new Object();
        c = new HashMap();
    }

    private ActivityChooserModel(Context r5_Context, String r6_String) {
        this.d = new Object();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.j = new a(null);
        this.k = 50;
        this.l = true;
        this.m = false;
        this.n = true;
        this.o = false;
        this.g = r5_Context.getApplicationContext();
        if (TextUtils.isEmpty(r6_String) || r6_String.endsWith(".xml")) {
            this.h = r6_String;
        } else {
            this.h = r6_String + ".xml";
        }
    }

    private boolean a(HistoricalRecord r3_HistoricalRecord) {
        boolean r0z = this.f.add(r3_HistoricalRecord);
        if (r0z) {
            this.n = true;
            i();
            b();
            f();
            notifyChanged();
        }
        return r0z;
    }

    private void b() {
        if (this.m) {
            if (this.n) {
                this.n = false;
                if (!TextUtils.isEmpty(this.h)) {
                    if (VERSION.SDK_INT >= 11) {
                        d();
                    } else {
                        c();
                    }
                }
            }
        } else {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
    }

    private void c() {
        b r0_b = new b(null);
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = new ArrayList(this.f);
        r1_ObjectA[1] = this.h;
        r0_b.execute(r1_ObjectA);
    }

    private void d() {
        b r0_b = new b(null);
        Executor r1_Executor = AsyncTask.SERIAL_EXECUTOR;
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = new ArrayList(this.f);
        r2_ObjectA[1] = this.h;
        r0_b.executeOnExecutor(r1_Executor, r2_ObjectA);
    }

    private void e() {
        int r0i = g() | h();
        i();
        if (r0i != 0) {
            f();
            notifyChanged();
        }
    }

    private boolean f() {
        if (this.j == null || this.i == null || this.e.isEmpty() || this.f.isEmpty()) {
            return false;
        }
        this.j.sort(this.i, this.e, Collections.unmodifiableList(this.f));
        return true;
    }

    private boolean g() {
        if ((!this.o) || this.i == null) {
            return false;
        }
        this.o = false;
        this.e.clear();
        List r2_List = this.g.getPackageManager().queryIntentActivities(this.i, 0);
        int r3i = r2_List.size();
        int r1i = 0;
        while (r1i < r3i) {
            this.e.add(new ActivityResolveInfo((ResolveInfo) r2_List.get(r1i)));
            r1i++;
        }
        return true;
    }

    public static ActivityChooserModel get(Context r3_Context, String r4_String) {
        ActivityChooserModel r0_ActivityChooserModel;
        synchronized (b) {
            r0_ActivityChooserModel = (ActivityChooserModel) c.get(r4_String);
            if (r0_ActivityChooserModel == null) {
                r0_ActivityChooserModel = new ActivityChooserModel(r3_Context, r4_String);
                c.put(r4_String, r0_ActivityChooserModel);
            }
        }
        return r0_ActivityChooserModel;
    }

    private boolean h() {
        if (!(this.l) || !(this.n) || TextUtils.isEmpty(this.h)) {
            return false;
        }
        this.l = false;
        this.m = true;
        j();
        return true;
    }

    private void i() {
        int r3i = this.f.size() - this.k;
        if (r3i <= 0) {
        } else {
            this.n = true;
            int r1i = 0;
            while (r1i < r3i) {
                HistoricalRecord r0_HistoricalRecord = (HistoricalRecord) this.f.remove(0);
                r1i++;
            }
        }
    }

    private void j() {
        InputStream r1_InputStream;
        try {
            r1_InputStream = this.g.openFileInput(this.h);
            XmlPullParser r2_XmlPullParser = Xml.newPullParser();
            r2_XmlPullParser.setInput(r1_InputStream, null);
            int r0i = 0;
            while (r0i != 1 && r0i != 2) {
                r0i = r2_XmlPullParser.next();
            }
            if ("historical-records".equals(r2_XmlPullParser.getName())) {
                List r0_List = this.f;
                r0_List.clear();
                while (true) {
                    int r3i = r2_XmlPullParser.next();
                    if (r3i == 1) {
                        if (r1_InputStream != null) {
                            try {
                                r1_InputStream.close();
                            } catch (IOException e) {
                            }
                        }
                        return;
                    } else if (r3i == 3 || r3i == 4) {
                    } else if ("historical-record".equals(r2_XmlPullParser.getName())) {
                        r0_List.add(new HistoricalRecord(r2_XmlPullParser.getAttributeValue(null, "activity"), Long.parseLong(r2_XmlPullParser.getAttributeValue(null, Globalization.TIME)), Float.parseFloat(r2_XmlPullParser.getAttributeValue(null, "weight"))));
                    } else {
                        throw new XmlPullParserException("Share records file not well-formed.");
                    }
                }
            } else {
                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            }
        } catch (FileNotFoundException e_2) {
        }
    }

    public Intent chooseActivity(int r8i) {
        Intent r0_Intent;
        synchronized (this.d) {
            if (this.i == null) {
                r0_Intent = null;
            } else {
                e();
                ActivityResolveInfo r0_ActivityResolveInfo = (ActivityResolveInfo) this.e.get(r8i);
                ComponentName r3_ComponentName = new ComponentName(r0_ActivityResolveInfo.resolveInfo.activityInfo.packageName, r0_ActivityResolveInfo.resolveInfo.activityInfo.name);
                r0_Intent = new Intent(this.i);
                r0_Intent.setComponent(r3_ComponentName);
                if (this.p != null) {
                    if (this.p.onChooseActivity(this, new Intent(r0_Intent))) {
                        r0_Intent = null;
                    }
                }
                a(new HistoricalRecord(r3_ComponentName, System.currentTimeMillis(), 1.0f));
            }
        }
        return r0_Intent;
    }

    public ResolveInfo getActivity(int r3i) {
        ResolveInfo r0_ResolveInfo;
        synchronized (this.d) {
            e();
            r0_ResolveInfo = ((ActivityResolveInfo) this.e.get(r3i)).resolveInfo;
        }
        return r0_ResolveInfo;
    }

    public int getActivityCount() {
        int r0i;
        synchronized (this.d) {
            e();
            r0i = this.e.size();
        }
        return r0i;
    }

    public int getActivityIndex(ResolveInfo r6_ResolveInfo) {
        int r0i;
        synchronized (this.d) {
            e();
            List r3_List = this.e;
            int r4i = r3_List.size();
            int r1i = 0;
            while (r1i < r4i) {
                if (((ActivityResolveInfo) r3_List.get(r1i)).resolveInfo == r6_ResolveInfo) {
                    r0i = r1i;
                } else {
                    r1i++;
                }
            }
            r0i = -1;
        }
        return r0i;
    }

    public ResolveInfo getDefaultActivity() {
        ResolveInfo r0_ResolveInfo;
        synchronized (this.d) {
            e();
            if (this.e.isEmpty()) {
                r0_ResolveInfo = null;
            } else {
                r0_ResolveInfo = ((ActivityResolveInfo) this.e.get(0)).resolveInfo;
            }
        }
        return r0_ResolveInfo;
    }

    public int getHistoryMaxSize() {
        int r0i;
        synchronized (this.d) {
            r0i = this.k;
        }
        return r0i;
    }

    public int getHistorySize() {
        int r0i;
        synchronized (this.d) {
            e();
            r0i = this.f.size();
        }
        return r0i;
    }

    public Intent getIntent() {
        Intent r0_Intent;
        synchronized (this.d) {
            r0_Intent = this.i;
        }
        return r0_Intent;
    }

    public void setActivitySorter(ActivitySorter r3_ActivitySorter) {
        synchronized (this.d) {
            if (this.j == r3_ActivitySorter) {
            } else {
                this.j = r3_ActivitySorter;
                if (f()) {
                    notifyChanged();
                }
            }
        }
    }

    public void setDefaultActivity(int r7i) {
        synchronized (this.d) {
            e();
            ActivityResolveInfo r0_ActivityResolveInfo = (ActivityResolveInfo) this.e.get(r7i);
            ActivityResolveInfo r1_ActivityResolveInfo = (ActivityResolveInfo) this.e.get(0);
            a(new HistoricalRecord(new ComponentName(r0_ActivityResolveInfo.resolveInfo.activityInfo.packageName, r0_ActivityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), r1_ActivityResolveInfo != null ? r1_ActivityResolveInfo.weight - r0_ActivityResolveInfo.weight + 5.0f : 1.0f));
        }
    }

    public void setHistoryMaxSize(int r3i) {
        synchronized (this.d) {
            if (this.k == r3i) {
            } else {
                this.k = r3i;
                i();
                if (f()) {
                    notifyChanged();
                }
            }
        }
    }

    public void setIntent(Intent r3_Intent) {
        synchronized (this.d) {
            if (this.i == r3_Intent) {
            } else {
                this.i = r3_Intent;
                this.o = true;
                e();
            }
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener r3_OnChooseActivityListener) {
        synchronized (this.d) {
            this.p = r3_OnChooseActivityListener;
        }
    }
}
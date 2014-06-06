package com.tencent.cloudsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.androidquery.util.Constants;
import com.qiubai.library.adview.AdViewManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class ca {
    private SharedPreferences a;

    public ca(Context r3_Context) {
        this.a = r3_Context.getSharedPreferences("ans_setting_content", 0);
    }

    private boolean a(String r12_String, bv r13_bv) {
        String[] r4_StringA = r12_String.split(",");
        if (r4_StringA == null || r4_StringA.length <= 0) {
            return false;
        }
        Map r5_Map = new HashMap(3);
        int r6i = r4_StringA.length;
        int r3i = 0;
        while (r3i < r6i) {
            String[] r0_StringA = r4_StringA[r3i].split("\\|");
            if (r0_StringA == null || r0_StringA.length < 2) {
                return false;
            }
            short r7s = Short.parseShort(r0_StringA[1]);
            Object r8_Object = r0_StringA[0];
            List r0_List = (List) r5_Map.get(Short.valueOf(r7s));
            if (r0_List == null) {
                r0_List = new ArrayList(3);
                r5_Map.put(Short.valueOf(r7s), r0_List);
            }
            r0_List.add(r8_Object);
            r3i++;
        }
        r13_bv.a((List) r5_Map.get(Short.valueOf((short) 1)));
        r13_bv.b((List) r5_Map.get(Short.valueOf((short) 2)));
        r13_bv.c((List) r5_Map.get(Short.valueOf((short) 4)));
        return true;
    }

    private void c(bv r2_bv) {
        r2_bv.a(Arrays.asList(bz.a));
        r2_bv.c(Arrays.asList(bz.c));
        r2_bv.b(Arrays.asList(bz.b));
    }

    private String d(bv r10_bv) {
        String r0_String;
        List r1_List = r10_bv.q();
        List r2_List = r10_bv.o();
        List r3_List = r10_bv.p();
        Iterator r4_Iterator = r1_List.iterator();
        String r1_String = null;
        while (r4_Iterator.hasNext()) {
            r0_String = (String) r4_Iterator.next();
            r1_String = r1_String == null ? new StringBuilder(String.valueOf(r0_String)).append("|").append(XListViewFooter.STATE_NODATA).toString() : new StringBuilder(String.valueOf(r1_String)).append(",").append(r0_String).append("|").append(XListViewFooter.STATE_NODATA).toString();
        }
        Iterator r2_Iterator = r2_List.iterator();
        while (r2_Iterator.hasNext()) {
            r0_String = (String) r2_Iterator.next();
            r1_String = r1_String == null ? new StringBuilder(String.valueOf(r0_String)).append("|").append(1).toString() : new StringBuilder(String.valueOf(r1_String)).append(",").append(r0_String).append("|").append(1).toString();
        }
        r2_Iterator = r3_List.iterator();
        while (r2_Iterator.hasNext()) {
            r0_String = (String) r2_Iterator.next();
            r1_String = r1_String == null ? new StringBuilder(String.valueOf(r0_String)).append("|").append(XListViewHeader.STATE_REFRESHING).toString() : new StringBuilder(String.valueOf(r1_String)).append(",").append(r0_String).append("|").append(XListViewHeader.STATE_REFRESHING).toString();
        }
        return r1_String;
    }

    public void a(bv r5_bv) {
        er.a("AnsSettingStorage", ">>>\u521d\u59cb\u5316\u8bbe\u7f6e\u5230\u5185\u5b58");
        SharedPreferences r0_SharedPreferences = this.a;
        r5_bv.a = r0_SharedPreferences.getInt("AnsQuTo", 15000);
        r5_bv.b = r0_SharedPreferences.getInt("TstSpTo", 15000);
        r5_bv.c = r0_SharedPreferences.getInt("AnsPort", 8000);
        r5_bv.d = r0_SharedPreferences.getInt("TsoRepMaxLinkErr", XListViewHeader.STATE_REFRESHING);
        r5_bv.e = r0_SharedPreferences.getLong("ReportIntval", AdViewManager.CONFIG_SERVER_LIMIT_MSTIME);
        r5_bv.f = r0_SharedPreferences.getInt("HttpOcTestPort", 2080);
        r5_bv.g = r0_SharedPreferences.getInt("DomainRetryTo", 600000);
        r5_bv.k = r0_SharedPreferences.getInt("MonitorReportInterval", 300000);
        r5_bv.l = r0_SharedPreferences.getInt("ClearBufInterval", 7200000);
        r5_bv.m = r0_SharedPreferences.getInt("MaxBufLength", Constants.FLAG_ACTIVITY_NO_ANIMATION);
        String r0_String = r0_SharedPreferences.getString("AnsList", null);
        if (r0_String == null || RContactStorage.PRIMARY_KEY.equals(r0_String)) {
            c(r5_bv);
        } else {
            if (!a(r0_String, r5_bv)) {
                c(r5_bv);
            }
        }
    }

    public void b(bv r5_bv) {
        Editor r0_Editor = this.a.edit();
        r0_Editor.putInt("AnsQuTo", r5_bv.a);
        r0_Editor.putInt("TstSpTo", r5_bv.b);
        r0_Editor.putInt("AnsPort", r5_bv.c);
        r0_Editor.putInt("TsoRepMaxLinkErr", r5_bv.d);
        r0_Editor.putLong("ReportIntval", r5_bv.e);
        r0_Editor.putInt("HttpOcTestPort", r5_bv.f);
        r0_Editor.putInt("DomainRetryTo", r5_bv.g);
        r0_Editor.putInt("MonitorReportInterval", r5_bv.k);
        r0_Editor.putInt("ClearBufInterval", r5_bv.l);
        r0_Editor.putInt("MaxBufLength", r5_bv.m);
        r0_Editor.putString("AnsList", d(r5_bv));
        r0_Editor.commit();
    }
}
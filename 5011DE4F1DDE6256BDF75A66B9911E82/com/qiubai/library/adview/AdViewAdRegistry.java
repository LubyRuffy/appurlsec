package com.qiubai.library.adview;

import android.util.SparseArray;
import com.qiubai.library.adview.adapters.AdBaiduAdapter;
import com.qiubai.library.adview.adapters.AdMobAdapter;
import com.qiubai.library.adview.adapters.AdViewAdapter;
import com.qiubai.library.adview.adapters.AdwoAdapter;
import com.qiubai.library.adview.adapters.CustomerAdatper;
import com.qiubai.library.adview.adapters.MobiSageAdapter;
import com.qiubai.library.adview.adapters.PunchBoxAdapter;

public class AdViewAdRegistry {
    private static AdViewAdRegistry a;
    private SparseArray<Class<? extends AdViewAdapter>> b;

    static {
        a = null;
    }

    private AdViewAdRegistry() {
        this.b = null;
        this.b = new SparseArray();
    }

    private void a() {
        try {
            CustomerAdatper.load(this);
        } catch (Error e) {
        }
        try {
            AdBaiduAdapter.load(this);
        } catch (Error e_2) {
        }
        try {
            AdMobAdapter.load(this);
        } catch (Error e_3) {
        }
        try {
            AdwoAdapter.load(this);
        } catch (Error e_4) {
        }
        try {
            MobiSageAdapter.load(this);
        } catch (Error e_5) {
        }
        try {
            PunchBoxAdapter.load(this);
        } catch (Error e_6) {
        }
    }

    public static AdViewAdRegistry getInstance() {
        if (a == null) {
            a = new AdViewAdRegistry();
            a.a();
        }
        return a;
    }

    public Class<? extends AdViewAdapter> adapterClassForAdType(Integer r3_Integer) {
        return (Class) this.b.get(r3_Integer.intValue());
    }

    public void registerClass(Integer r3_Integer, Class<? extends AdViewAdapter> r4_Class__extends_AdViewAdapter) {
        this.b.put(r3_Integer.intValue(), r4_Class__extends_AdViewAdapter);
    }
}
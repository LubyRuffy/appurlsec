package com.zkmm.adsdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.DisplayMetrics;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.bean.Base;
import qsbk.app.utils.audit.Rotate3dAnimation;

// compiled from: SourceFile
final class bg {
    protected static int a;
    protected static String b;
    protected static String c;
    private static bg d;
    private static String h;
    private static String i;
    private static String j;
    private int e;
    private HashMap f;
    private ArrayList g;
    private boolean k;

    static {
        a = 20;
        b = null;
        c = null;
        h = "1";
        i = "-1";
        j = "ADWO_SHARED_DATA";
    }

    private bg(Context r6_Context) {
        boolean r0z = true;
        boolean r1z = false;
        this.e = 1;
        this.f = new HashMap();
        this.g = new ArrayList();
        this.k = false;
        if (a("configure_version", r6_Context) != null) {
            Date r2_Date = new Date();
            if (this.k) {
                if (!r0z) {
                    c(r6_Context);
                    return;
                }
            } else {
                String r3_String = a("update_date", r6_Context);
                if (r3_String == null || (!r3_String.equalsIgnoreCase(m.M.format(r2_Date)))) {
                    this.k = false;
                    r0z = false;
                } else {
                    this.k = true;
                }
                if (r0z) {
                    new bh(this, r6_Context).execute(new String[r1z]);
                } else {
                    c(r6_Context);
                    return;
                }
            }
        }
        new bh(this, r6_Context).execute(new String[r1z]);
    }

    protected static bg a(Context r1_Context) {
        if (d == null) {
            d = new bg(r1_Context);
        }
        return d;
    }

    private static String a(String r2_String, Context r3_Context) {
        return r3_Context.getSharedPreferences(j, 0).getString(r2_String, null);
    }

    static /* synthetic */ void a(bg r2_bg, String r3_String, String r4_String, Context r5_Context) {
        Editor r0_Editor = r5_Context.getSharedPreferences(j, 0).edit();
        r0_Editor.putString(r3_String, r4_String);
        r0_Editor.commit();
    }

    private static String b(Context r4_Context) {
        String r0_String;
        StringBuffer r1_StringBuffer = new StringBuffer();
        r1_StringBuffer.append("publicid=");
        try {
            r1_StringBuffer.append(new String(m.b, Base.UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        r0_String = m.f != null ? new String(m.f) : r4_Context.getPackageName();
        r1_StringBuffer.append("&package=");
        r1_StringBuffer.append(r0_String);
        r1_StringBuffer.append("&verionCode=");
        r1_StringBuffer.append(m.b(r4_Context));
        r1_StringBuffer.append("&nettype=");
        r1_StringBuffer.append(m.n);
        r1_StringBuffer.append("&opertype=");
        r1_StringBuffer.append(0);
        try {
            r1_StringBuffer.append("&manu=");
            r0_String = EDIT_TYPE.TYPE_UNKNOWN;
            if (m.d != null) {
                r0_String = new String(m.d, Base.UTF8);
            }
            r1_StringBuffer.append(r0_String);
            r1_StringBuffer.append("&brand=");
            r0_String = "unknwon";
            if (m.e != null) {
                r0_String = new String(m.e, Base.UTF8);
            }
            r1_StringBuffer.append(r0_String);
            r1_StringBuffer.append("&os=");
            r1_StringBuffer.append(m.r);
            r1_StringBuffer.append("&userid=");
            r1_StringBuffer.append(new String(m.c, Base.UTF8));
            DisplayMetrics r0_DisplayMetrics = new DisplayMetrics();
            r1_StringBuffer.append(new StringBuilder("&screenSize=").append(m.z).append(Rotate3dAnimation.ROTATE_X).append(m.y).append(",").append(r4_Context.getResources().getDisplayMetrics().density).toString());
            r1_StringBuffer.append("&timestamp=");
            r1_StringBuffer.append(new Date().toString());
            r1_StringBuffer.append("&pversion=");
            r1_StringBuffer.append(i);
            r1_StringBuffer.append("&sdkversion=");
            r1_StringBuffer.append(new StringBuilder(String.valueOf(m.t)).toString());
        } catch (UnsupportedEncodingException e_2) {
            e_2.printStackTrace();
        }
        return r1_StringBuffer.toString();
    }

    private void c() {
        h = "1";
        a = 30;
        bi r1_bi = new bi();
        r1_bi.b = s.c;
        r1_bi.a = 1.0d;
        r1_bi.c = new String(m.b);
        r1_bi.d = s.d;
        this.g.add(r1_bi);
        int r0i = this.e;
        this.e = ((int) (r1_bi.a * 10.0d)) + this.e;
        while (r0i < this.e) {
            this.f.put(Integer.valueOf(r0i), Integer.valueOf(this.g.size() - 1));
            r0i++;
        }
    }

    private void c(Context r9_Context) {
        Map r0_Map = r9_Context.getSharedPreferences(j, 0).getAll();
        if (r0_Map == null || r0_Map.size() <= 0) {
            c();
        } else {
            Iterator r2_Iterator = r0_Map.entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                if ("strategy".equals(r0_Entry.getKey())) {
                    h = (String) r0_Entry.getValue();
                } else if ("requestInterval".equals(r0_Entry.getKey())) {
                    r0i = Integer.parseInt((String) r0_Entry.getValue());
                    if (r0i >= 30) {
                        a = r0i;
                    }
                } else if ("fs_requestInterval".equals(r0_Entry.getKey())) {
                    r0i = Integer.parseInt((String) r0_Entry.getValue());
                    if (r0i > 20) {
                        m.S = r0i;
                    }
                } else if ("fs_request_limit".equals(r0_Entry.getKey())) {
                    r0i = Integer.parseInt((String) r0_Entry.getValue());
                    if (r0i > 0) {
                        m.T = r0i;
                    }
                } else if ("update_date".equals(r0_Entry.getKey()) || "configure_version".equals(r0_Entry.getKey())) {
                } else if ("filter_ad_list".equals(r0_Entry.getKey())) {
                    r0_String = (String) r0_Entry.getValue();
                    if (RContactStorage.PRIMARY_KEY.equals(r0_String) || "0".equals(r0_String)) {
                        m.E = new int[0];
                    } else {
                        String[] r3_StringA = r0_String.split(",");
                        m.E = new int[r3_StringA.length];
                        r0i = 0;
                        while (r0i < r3_StringA.length) {
                            m.E[r0i] = Integer.parseInt(r3_StringA[r0i]);
                            r0i++;
                        }
                    }
                } else {
                    StringTokenizer r3_StringTokenizer = new StringTokenizer((String) r0_Entry.getValue(), "=");
                    bi r4_bi = new bi();
                    r4_bi.b = r3_StringTokenizer.nextToken();
                    r4_bi.a = Double.parseDouble(r3_StringTokenizer.nextToken());
                    r4_bi.c = r3_StringTokenizer.nextToken();
                    try {
                        r0_String = r3_StringTokenizer.nextToken();
                        if (r0_String != null) {
                            r4_bi.e = Integer.decode(r0_String).intValue();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        r4_bi.d = r3_StringTokenizer.nextToken();
                    } catch (Exception e_2) {
                    }
                    this.g.add(r4_bi);
                    r0i = this.e;
                    this.e += (int) (r4_bi.a * 10.0d);
                    while (r0i < this.e) {
                        this.f.put(Integer.valueOf(r0i), Integer.valueOf(this.g.size() - 1));
                        r0i++;
                    }
                }
            }
            this.e--;
        }
    }

    protected final String a() {
        String r0_String;
        try {
            if ("1".equals(h)) {
                b = ((bi) this.g.get(0)).c;
                r0i = ((bi) this.g.get(0)).e;
                c = ((bi) this.g.get(0)).d;
                r0_String = ((bi) this.g.get(0)).b;
                return r0_String;
            } else {
                int r1i = ((Integer) this.f.get(Integer.valueOf(Double.valueOf(Math.random() * ((double) this.e)).intValue() + 1))).intValue();
                b = ((bi) this.g.get(r1i)).c;
                r0i = ((bi) this.g.get(r1i)).e;
                c = ((bi) this.g.get(r1i)).d;
                r0_String = ((bi) this.g.get(r1i)).b;
                return r0_String;
            }
        } catch (Exception e) {
            b = new String(m.b);
            c = s.d;
            r0_String = s.c;
        }
    }
}
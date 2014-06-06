package com.baidu.mobads;

import com.baidu.mobads.a.d;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class AdSettings {
    private static HashSet<String> a;
    private static JSONArray b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static HashSet<String> j;
    private static JSONArray k;
    private static JSONObject l;

    public static enum Education {
        PRIMARY(0),
        JUNIOR(1),
        SENIOR(2),
        SPECIALTY(3),
        BACHELOR(4),
        MASTER(5),
        DOCTOR(6);

        private int a;

        static {
            PRIMARY = new com.baidu.mobads.AdSettings.Education("PRIMARY", 0, 0);
            JUNIOR = new com.baidu.mobads.AdSettings.Education("JUNIOR", 1, 1);
            SENIOR = new com.baidu.mobads.AdSettings.Education("SENIOR", 2, 2);
            SPECIALTY = new com.baidu.mobads.AdSettings.Education("SPECIALTY", 3, 3);
            BACHELOR = new com.baidu.mobads.AdSettings.Education("BACHELOR", 4, 4);
            MASTER = new com.baidu.mobads.AdSettings.Education("MASTER", 5, 5);
            DOCTOR = new com.baidu.mobads.AdSettings.Education("DOCTOR", 6, 6);
            com.baidu.mobads.AdSettings.Education[] r0_com_baidu_mobads_AdSettings_EducationA = new com.baidu.mobads.AdSettings.Education[7];
            r0_com_baidu_mobads_AdSettings_EducationA[0] = PRIMARY;
            r0_com_baidu_mobads_AdSettings_EducationA[1] = JUNIOR;
            r0_com_baidu_mobads_AdSettings_EducationA[2] = SENIOR;
            r0_com_baidu_mobads_AdSettings_EducationA[3] = SPECIALTY;
            r0_com_baidu_mobads_AdSettings_EducationA[4] = BACHELOR;
            r0_com_baidu_mobads_AdSettings_EducationA[5] = MASTER;
            r0_com_baidu_mobads_AdSettings_EducationA[6] = DOCTOR;
            b = r0_com_baidu_mobads_AdSettings_EducationA;
        }

        private Education(int r3i) {
            this.a = r3i;
        }

        public int getValue() {
            return this.a;
        }
    }

    public static enum Salary {
        F0T1k(0),
        F1kT2k(1),
        F2kT3k(2),
        F3kT4k(3),
        F4kT5k(4),
        F5kT6k(5),
        F6kT7k(6),
        F7kT8k(7),
        F8kT9k(8),
        F9kT10k(9),
        F10kT15k(10),
        F15kT20k(11),
        F20(12);

        private int a;

        static {
            F0T1k = new com.baidu.mobads.AdSettings.Salary("F0T1k", 0, 0);
            F1kT2k = new com.baidu.mobads.AdSettings.Salary("F1kT2k", 1, 1);
            F2kT3k = new com.baidu.mobads.AdSettings.Salary("F2kT3k", 2, 2);
            F3kT4k = new com.baidu.mobads.AdSettings.Salary("F3kT4k", 3, 3);
            F4kT5k = new com.baidu.mobads.AdSettings.Salary("F4kT5k", 4, 4);
            F5kT6k = new com.baidu.mobads.AdSettings.Salary("F5kT6k", 5, 5);
            F6kT7k = new com.baidu.mobads.AdSettings.Salary("F6kT7k", 6, 6);
            F7kT8k = new com.baidu.mobads.AdSettings.Salary("F7kT8k", 7, 7);
            F8kT9k = new com.baidu.mobads.AdSettings.Salary("F8kT9k", 8, 8);
            F9kT10k = new com.baidu.mobads.AdSettings.Salary("F9kT10k", 9, 9);
            F10kT15k = new com.baidu.mobads.AdSettings.Salary("F10kT15k", 10, 10);
            F15kT20k = new com.baidu.mobads.AdSettings.Salary("F15kT20k", 11, 11);
            F20 = new com.baidu.mobads.AdSettings.Salary("F20", 12, 12);
            com.baidu.mobads.AdSettings.Salary[] r0_com_baidu_mobads_AdSettings_SalaryA = new com.baidu.mobads.AdSettings.Salary[13];
            r0_com_baidu_mobads_AdSettings_SalaryA[0] = F0T1k;
            r0_com_baidu_mobads_AdSettings_SalaryA[1] = F1kT2k;
            r0_com_baidu_mobads_AdSettings_SalaryA[2] = F2kT3k;
            r0_com_baidu_mobads_AdSettings_SalaryA[3] = F3kT4k;
            r0_com_baidu_mobads_AdSettings_SalaryA[4] = F4kT5k;
            r0_com_baidu_mobads_AdSettings_SalaryA[5] = F5kT6k;
            r0_com_baidu_mobads_AdSettings_SalaryA[6] = F6kT7k;
            r0_com_baidu_mobads_AdSettings_SalaryA[7] = F7kT8k;
            r0_com_baidu_mobads_AdSettings_SalaryA[8] = F8kT9k;
            r0_com_baidu_mobads_AdSettings_SalaryA[9] = F9kT10k;
            r0_com_baidu_mobads_AdSettings_SalaryA[10] = F10kT15k;
            r0_com_baidu_mobads_AdSettings_SalaryA[11] = F15kT20k;
            r0_com_baidu_mobads_AdSettings_SalaryA[12] = F20;
            b = r0_com_baidu_mobads_AdSettings_SalaryA;
        }

        private Salary(int r3i) {
            this.a = r3i;
        }

        public int getValue() {
            return this.a;
        }
    }

    public static enum Sex {
        MALE(0),
        FEMALE(1);

        private int a;

        static {
            MALE = new com.baidu.mobads.AdSettings.Sex("MALE", 0, 0);
            FEMALE = new com.baidu.mobads.AdSettings.Sex("FEMALE", 1, 1);
            com.baidu.mobads.AdSettings.Sex[] r0_com_baidu_mobads_AdSettings_SexA = new com.baidu.mobads.AdSettings.Sex[2];
            r0_com_baidu_mobads_AdSettings_SexA[0] = MALE;
            r0_com_baidu_mobads_AdSettings_SexA[1] = FEMALE;
            b = r0_com_baidu_mobads_AdSettings_SexA;
        }

        private Sex(int r3i) {
            this.a = r3i;
        }

        public int getValue() {
            return this.a;
        }
    }

    static {
        a = new HashSet();
        b = new JSONArray();
        j = new HashSet();
        k = new JSONArray();
        l = new JSONObject();
    }

    protected static JSONObject getAttr() {
        JSONObject r1_JSONObject = new JSONObject();
        Iterator r0_Iterator = a.iterator();
        b = new JSONArray();
        while (r0_Iterator.hasNext()) {
            b.put(r0_Iterator.next());
        }
        r0_Iterator = j.iterator();
        k = new JSONArray();
        while (r0_Iterator.hasNext()) {
            k.put(r0_Iterator.next());
        }
        try {
            r1_JSONObject.putOpt("KEY", b);
            r1_JSONObject.putOpt("SEX", c);
            r1_JSONObject.putOpt("BIR", d);
            r1_JSONObject.putOpt("CITY", e);
            r1_JSONObject.putOpt("ZIP", f);
            r1_JSONObject.putOpt("JOB", g);
            r1_JSONObject.putOpt("EDU", h);
            r1_JSONObject.putOpt("SAL", i);
            r1_JSONObject.putOpt("HOB", k);
            r1_JSONObject.putOpt("R", l);
        } catch (Exception e) {
            d.c(e);
        }
        d.a("AdSettings.getAttr:" + r1_JSONObject);
        return r1_JSONObject;
    }

    public static void setBirthday(Calendar r5_Calendar) {
        if (r5_Calendar == null) {
            d.c("Birthday Parameter can't be null");
        } else {
            int r0i = r5_Calendar.get(1);
            int r1i = r5_Calendar.get(XListViewHeader.STATE_REFRESHING) + 1;
            int r2i = r5_Calendar.get(ShareUtils.SHARE_SMS);
            d = r0i + RContactStorage.PRIMARY_KEY;
            if (r1i <= 0 || r1i >= 10) {
                d += r1i;
                if (r2i <= 0 || r2i >= 10) {
                    d += r2i;
                } else {
                    d += "0" + r2i;
                    return;
                }
            } else {
                d += "0" + r1i;
                if (r2i <= 0 || r2i >= 10) {
                    d += r2i;
                } else {
                    d += "0" + r2i;
                    return;
                }
            }
            d += r2i;
        }
    }

    public static void setCity(String r0_String) {
        e = r0_String;
    }

    public static void setEducation(Education r2_Education) {
        if (r2_Education == null) {
            d.c("Education Parameter can't be null");
        } else {
            h = r2_Education.getValue() + RContactStorage.PRIMARY_KEY;
        }
    }

    public static void setHob(List<String> r1_List_String) {
        j.addAll(r1_List_String);
    }

    public static void setHob(String[] r3_StringA) {
        int r0i = 0;
        while (r0i < r3_StringA.length) {
            j.add(r3_StringA[r0i]);
            r0i++;
        }
    }

    public static void setJob(String r0_String) {
        g = r0_String;
    }

    public static void setKey(List<String> r1_List_String) {
        a.addAll(r1_List_String);
    }

    public static void setKey(String[] r3_StringA) {
        int r0i = 0;
        while (r0i < r3_StringA.length) {
            a.add(r3_StringA[r0i]);
            r0i++;
        }
    }

    public static void setSalary(Salary r2_Salary) {
        if (r2_Salary == null) {
            d.c("Salary Parameter can't be null");
        } else {
            i = r2_Salary.getValue() + RContactStorage.PRIMARY_KEY;
        }
    }

    public static void setSex(Sex r2_Sex) {
        if (r2_Sex == null) {
            d.c("Sex Parameter can't be null");
        } else {
            c = r2_Sex.getValue() + RContactStorage.PRIMARY_KEY;
        }
    }

    public static void setUserAttr(String r1_String, String r2_String) {
        try {
            l.put(r1_String, r2_String);
        } catch (JSONException e) {
            d.c("Baidu SDK:setUserAttr error!");
        }
    }

    public static void setZip(String r0_String) {
        f = r0_String;
    }
}
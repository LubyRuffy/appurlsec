package com.qiubai.library.adview;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class AdViewTargeting {
    private static RunMode a;
    private static UpdateMode b;
    private static SwitcherMode c;
    private static Gender d;
    private static GregorianCalendar e;
    private static String f;
    private static String g;
    private static Set<String> h;

    public static enum Gender {
        UNKNOWN,
        MALE,
        FEMALE;


        static {
            UNKNOWN = new com.qiubai.library.adview.AdViewTargeting.Gender("UNKNOWN", 0);
            MALE = new com.qiubai.library.adview.AdViewTargeting.Gender("MALE", 1);
            FEMALE = new com.qiubai.library.adview.AdViewTargeting.Gender("FEMALE", 2);
            com.qiubai.library.adview.AdViewTargeting.Gender[] r0_com_qiubai_library_adview_AdViewTargeting_GenderA = new com.qiubai.library.adview.AdViewTargeting.Gender[3];
            r0_com_qiubai_library_adview_AdViewTargeting_GenderA[0] = UNKNOWN;
            r0_com_qiubai_library_adview_AdViewTargeting_GenderA[1] = MALE;
            r0_com_qiubai_library_adview_AdViewTargeting_GenderA[2] = FEMALE;
            a = r0_com_qiubai_library_adview_AdViewTargeting_GenderA;
        }
    }

    public static enum RunMode {
        NORMAL,
        TEST;


        static {
            NORMAL = new com.qiubai.library.adview.AdViewTargeting.RunMode("NORMAL", 0);
            TEST = new com.qiubai.library.adview.AdViewTargeting.RunMode("TEST", 1);
            com.qiubai.library.adview.AdViewTargeting.RunMode[] r0_com_qiubai_library_adview_AdViewTargeting_RunModeA = new com.qiubai.library.adview.AdViewTargeting.RunMode[2];
            r0_com_qiubai_library_adview_AdViewTargeting_RunModeA[0] = NORMAL;
            r0_com_qiubai_library_adview_AdViewTargeting_RunModeA[1] = TEST;
            a = r0_com_qiubai_library_adview_AdViewTargeting_RunModeA;
        }
    }

    public static enum SwitcherMode {
        DEFAULT,
        CANCLOSED;


        static {
            DEFAULT = new com.qiubai.library.adview.AdViewTargeting.SwitcherMode("DEFAULT", 0);
            CANCLOSED = new com.qiubai.library.adview.AdViewTargeting.SwitcherMode("CANCLOSED", 1);
            com.qiubai.library.adview.AdViewTargeting.SwitcherMode[] r0_com_qiubai_library_adview_AdViewTargeting_SwitcherModeA = new com.qiubai.library.adview.AdViewTargeting.SwitcherMode[2];
            r0_com_qiubai_library_adview_AdViewTargeting_SwitcherModeA[0] = DEFAULT;
            r0_com_qiubai_library_adview_AdViewTargeting_SwitcherModeA[1] = CANCLOSED;
            a = r0_com_qiubai_library_adview_AdViewTargeting_SwitcherModeA;
        }
    }

    public static enum UpdateMode {
        DEFAULT,
        EVERYTIME;


        static {
            DEFAULT = new com.qiubai.library.adview.AdViewTargeting.UpdateMode("DEFAULT", 0);
            EVERYTIME = new com.qiubai.library.adview.AdViewTargeting.UpdateMode("EVERYTIME", 1);
            com.qiubai.library.adview.AdViewTargeting.UpdateMode[] r0_com_qiubai_library_adview_AdViewTargeting_UpdateModeA = new com.qiubai.library.adview.AdViewTargeting.UpdateMode[2];
            r0_com_qiubai_library_adview_AdViewTargeting_UpdateModeA[0] = DEFAULT;
            r0_com_qiubai_library_adview_AdViewTargeting_UpdateModeA[1] = EVERYTIME;
            a = r0_com_qiubai_library_adview_AdViewTargeting_UpdateModeA;
        }
    }

    static {
        a();
    }

    private static void a() {
        a = RunMode.NORMAL;
        b = UpdateMode.DEFAULT;
        c = SwitcherMode.DEFAULT;
        d = Gender.UNKNOWN;
        e = null;
        f = null;
        g = null;
        h = null;
    }

    public static void addKeyword(String r1_String) {
        if (h == null) {
            h = new HashSet();
        }
        h.add(r1_String);
    }

    public static int getAge() {
        return e != null ? GregorianCalendar.getInstance().get(1) - e.get(1) : -1;
    }

    public static GregorianCalendar getBirthDate() {
        return e;
    }

    public static Gender getGender() {
        return d;
    }

    public static Set<String> getKeywordSet() {
        return h;
    }

    public static String getKeywords() {
        return g;
    }

    public static String getPostalCode() {
        return f;
    }

    public static RunMode getRunMode() {
        return a;
    }

    public static SwitcherMode getSwitcherMode() {
        return c;
    }

    public static UpdateMode getUpdateMode() {
        return b;
    }

    public static void setAge(int r4i) {
        e = new GregorianCalendar(Calendar.getInstance().get(1) - r4i, 0, 1);
    }

    public static void setBirthDate(GregorianCalendar r0_GregorianCalendar) {
        e = r0_GregorianCalendar;
    }

    public static void setGender(Gender r0_Gender) {
        if (r0_Gender == null) {
            r0_Gender = Gender.UNKNOWN;
        }
        d = r0_Gender;
    }

    public static void setKeywordSet(Set<String> r0_Set_String) {
        h = r0_Set_String;
    }

    public static void setKeywords(String r0_String) {
        g = r0_String;
    }

    public static void setPostalCode(String r0_String) {
        f = r0_String;
    }

    public static void setRunMode(RunMode r0_RunMode) {
        if (r0_RunMode == null) {
            r0_RunMode = RunMode.NORMAL;
        }
        a = r0_RunMode;
    }

    public static void setSwitcherMode(SwitcherMode r0_SwitcherMode) {
        if (r0_SwitcherMode == null) {
            r0_SwitcherMode = SwitcherMode.DEFAULT;
        }
        c = r0_SwitcherMode;
    }

    public static void setUpdateMode(UpdateMode r0_UpdateMode) {
        if (r0_UpdateMode == null) {
            r0_UpdateMode = UpdateMode.DEFAULT;
        }
        b = r0_UpdateMode;
    }
}
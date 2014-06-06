package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public final class LocaleUtil {
    public static final String ARABIC = "ar";
    public static final String CHINA = "zh_CN";
    public static final String ENGLISH = "en";
    public static final String HEBREW = "iw";
    public static final String HINDI = "hi";
    public static final String HONGKONG = "zh_HK";
    public static final String INDONESIAN = "id";
    public static final String ITALIAN = "it";
    public static final String JAPANESE = "ja";
    public static final String KOREAN = "ko";
    public static final String LANGUAGE_DEFAULT = "language_default";
    public static final String LANGUAGE_KEY = "language_key";
    public static final String MALAY = "ms";
    public static final String POLISH = "pl";
    public static final String PORTUGUESE = "pt";
    public static final String RUSSIAN = "ru";
    public static final String SPANISH = "es";
    public static final String TAIWAN = "zh_TW";
    public static final String THAI = "th";
    public static final String TURKEY = "tr";
    public static final String VIETNAMESE = "vi";

    private LocaleUtil() {
    }

    private static String a(String r2_String) {
        String r0_String = Locale.getDefault().getLanguage().trim();
        if (r0_String.equals(ENGLISH)) {
            return r0_String;
        }
        r0_String = Locale.getDefault().getLanguage().trim() + "_" + Locale.getDefault().getCountry().trim();
        if (r0_String.equals(TAIWAN) || r0_String.equals(HONGKONG)) {
            return TAIWAN;
        }
        if ((Locale.getDefault().getLanguage().trim() + "_" + Locale.getDefault().getCountry().trim()).equals(CHINA)) {
            return CHINA;
        }
        if (Locale.getDefault().getLanguage().trim().equals(THAI)) {
            return THAI;
        }
        if (Locale.getDefault().getLanguage().trim().equals(INDONESIAN)) {
            return INDONESIAN;
        }
        if (Locale.getDefault().getLanguage().trim().equals(VIETNAMESE)) {
            return VIETNAMESE;
        }
        if (Locale.getDefault().getLanguage().trim().equals(PORTUGUESE)) {
            return PORTUGUESE;
        }
        if (Locale.getDefault().getLanguage().trim().equals(SPANISH)) {
            return SPANISH;
        }
        if (Locale.getDefault().getLanguage().trim().equals(RUSSIAN)) {
            return RUSSIAN;
        }
        if (Locale.getDefault().getLanguage().trim().equals(ARABIC)) {
            return ARABIC;
        }
        if (Locale.getDefault().getLanguage().trim().equals(HEBREW)) {
            return HEBREW;
        }
        if (Locale.getDefault().getLanguage().trim().equals(POLISH)) {
            return POLISH;
        }
        if (Locale.getDefault().getLanguage().trim().equals(HINDI)) {
            return HINDI;
        }
        if (Locale.getDefault().getLanguage().trim().equals(JAPANESE)) {
            return JAPANESE;
        }
        if (Locale.getDefault().getLanguage().trim().equals(ITALIAN)) {
            return ITALIAN;
        }
        if (Locale.getDefault().getLanguage().trim().equals(KOREAN)) {
            return KOREAN;
        }
        if (Locale.getDefault().getLanguage().trim().equals(MALAY)) {
            return MALAY;
        }
        if (Locale.getDefault().getLanguage().trim().equals(TURKEY)) {
            return TURKEY;
        }
        return r2_String;
    }

    public static String getApplicationLanguage() {
        String r0_String = Util.nullAsNil(SystemProperty.getProperty(LANGUAGE_KEY));
        return (r0_String.length() <= 0 || r0_String.equals(LANGUAGE_DEFAULT)) ? a(ENGLISH) : r0_String;
    }

    public static String getCurrentCountryCode() {
        return Locale.getDefault().getCountry().trim();
    }

    public static boolean isLanguageSupported(String r2_String) {
        if (Util.isNullOrNil(r2_String)) {
            return false;
        }
        if (r2_String.equalsIgnoreCase(TAIWAN) || r2_String.equalsIgnoreCase(HONGKONG) || r2_String.equalsIgnoreCase(CHINA) || r2_String.equalsIgnoreCase(ENGLISH) || r2_String.equalsIgnoreCase(THAI) || r2_String.equals(INDONESIAN) || r2_String.equals(VIETNAMESE) || r2_String.equalsIgnoreCase(PORTUGUESE) || r2_String.equalsIgnoreCase(SPANISH) || r2_String.equalsIgnoreCase(RUSSIAN) || r2_String.equalsIgnoreCase(ARABIC) || r2_String.equalsIgnoreCase(HEBREW) || r2_String.equalsIgnoreCase(POLISH) || r2_String.equalsIgnoreCase(HINDI) || r2_String.equalsIgnoreCase(JAPANESE) || r2_String.equalsIgnoreCase(ITALIAN) || r2_String.equalsIgnoreCase(KOREAN) || r2_String.equalsIgnoreCase(MALAY) || r2_String.equalsIgnoreCase(TURKEY)) {
            return true;
        }
        return false;
    }

    public static String loadApplicationLanguage(SharedPreferences r2_SharedPreferences, Context r3_Context) {
        String r0_String = Util.nullAsNil(r2_SharedPreferences.getString(LANGUAGE_KEY, null));
        if (r0_String.length() <= 0 || r0_String.equals(LANGUAGE_DEFAULT)) {
            r0_String = a(ENGLISH);
            SystemProperty.setProperty(LANGUAGE_KEY, r0_String);
            return r0_String;
        } else {
            SystemProperty.setProperty(LANGUAGE_KEY, r0_String);
            return r0_String;
        }
    }

    public static String loadApplicationLanguageSettings(SharedPreferences r2_SharedPreferences, Context r3_Context) {
        String r0_String = Util.nullAsNil(r2_SharedPreferences.getString(LANGUAGE_KEY, null));
        return Util.isNullOrNil(r0_String) ? LANGUAGE_DEFAULT : r0_String;
    }

    public static void saveApplicationLanguage(SharedPreferences r3_SharedPreferences, Context r4_Context, String r5_String) {
        if (r3_SharedPreferences.edit().putString(LANGUAGE_KEY, r5_String).commit()) {
            SystemProperty.setProperty(LANGUAGE_KEY, r5_String);
            Log.w("MicroMsg.LocaleUtil", new StringBuilder("save application lang as:").append(r5_String).toString());
        } else {
            Log.e("MicroMsg.LocaleUtil", "saving application lang failed");
        }
    }

    public static Locale transLanguageToLocale(String r3_String) {
        if (r3_String.equals(TAIWAN) || r3_String.equals(HONGKONG)) {
            return Locale.TAIWAN;
        }
        if (r3_String.equals(ENGLISH)) {
            return Locale.ENGLISH;
        }
        if (r3_String.equals(CHINA)) {
            return Locale.CHINA;
        }
        if (r3_String.equalsIgnoreCase(THAI)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(INDONESIAN)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(VIETNAMESE)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(PORTUGUESE)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(SPANISH)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(RUSSIAN)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(ARABIC)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(HEBREW)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(POLISH)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(HINDI)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(JAPANESE)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(ITALIAN)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(KOREAN)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(MALAY)) {
            return new Locale(r3_String);
        }
        if (r3_String.equalsIgnoreCase(TURKEY)) {
            return new Locale(r3_String);
        }
        Log.e("MicroMsg.LocaleUtil", new StringBuilder("transLanguageToLocale country = ").append(r3_String).toString());
        return Locale.ENGLISH;
    }

    public static void updateApplicationResourceLocale(Context r3_Context, Locale r4_Locale) {
        Resources r0_Resources = r3_Context.getResources();
        Configuration r1_Configuration = r0_Resources.getConfiguration();
        if (r1_Configuration.locale.equals(r4_Locale)) {
        } else {
            DisplayMetrics r2_DisplayMetrics = r0_Resources.getDisplayMetrics();
            r1_Configuration.locale = r4_Locale;
            r0_Resources.updateConfiguration(r1_Configuration, r2_DisplayMetrics);
            Resources.getSystem().updateConfiguration(r1_Configuration, r2_DisplayMetrics);
        }
    }
}
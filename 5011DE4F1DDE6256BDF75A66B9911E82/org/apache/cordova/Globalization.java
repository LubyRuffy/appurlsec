package org.apache.cordova;

import android.os.Build.VERSION;
import android.text.format.DateFormat;
import android.text.format.Time;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class Globalization extends CordovaPlugin {
    public static final String CURRENCY = "currency";
    public static final String CURRENCYCODE = "currencyCode";
    public static final String DATE = "date";
    public static final String DATESTRING = "dateString";
    public static final String DATETOSTRING = "dateToString";
    public static final String DAYS = "days";
    public static final String FORMATLENGTH = "formatLength";
    public static final String FULL = "full";
    public static final String GETCURRENCYPATTERN = "getCurrencyPattern";
    public static final String GETDATENAMES = "getDateNames";
    public static final String GETDATEPATTERN = "getDatePattern";
    public static final String GETFIRSTDAYOFWEEK = "getFirstDayOfWeek";
    public static final String GETLOCALENAME = "getLocaleName";
    public static final String GETNUMBERPATTERN = "getNumberPattern";
    public static final String GETPREFERREDLANGUAGE = "getPreferredLanguage";
    public static final String ISDAYLIGHTSAVINGSTIME = "isDayLightSavingsTime";
    public static final String ITEM = "item";
    public static final String LONG = "long";
    public static final String MEDIUM = "medium";
    public static final String MONTHS = "months";
    public static final String NARROW = "narrow";
    public static final String NUMBER = "number";
    public static final String NUMBERSTRING = "numberString";
    public static final String NUMBERTOSTRING = "numberToString";
    public static final String OPTIONS = "options";
    public static final String PERCENT = "percent";
    public static final String SELECTOR = "selector";
    public static final String STRINGTODATE = "stringToDate";
    public static final String STRINGTONUMBER = "stringToNumber";
    public static final String TIME = "time";
    public static final String TYPE = "type";
    public static final String WIDE = "wide";

    class AnonymousClass_1 implements Comparator<String> {
        final /* synthetic */ Map val$namesMap;

        AnonymousClass_1(Map r2_Map) {
            this.val$namesMap = r2_Map;
        }

        public int compare(String r3_String, String r4_String) {
            return ((Integer) this.val$namesMap.get(r3_String)).compareTo((Integer) this.val$namesMap.get(r4_String));
        }
    }

    private JSONObject getCurrencyPattern(JSONArray r6_JSONArray) throws GlobalizationError {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            DecimalFormat r0_DecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
            Currency r2_Currency = Currency.getInstance(r6_JSONArray.getJSONObject(0).getString(CURRENCYCODE));
            r0_DecimalFormat.setCurrency(r2_Currency);
            r1_JSONObject.put("pattern", r0_DecimalFormat.toPattern());
            r1_JSONObject.put("code", r2_Currency.getCurrencyCode());
            r1_JSONObject.put("fraction", r0_DecimalFormat.getMinimumFractionDigits());
            r1_JSONObject.put("rounding", new Integer(0));
            r1_JSONObject.put("decimal", String.valueOf(r0_DecimalFormat.getDecimalFormatSymbols().getDecimalSeparator()));
            r1_JSONObject.put("grouping", String.valueOf(r0_DecimalFormat.getDecimalFormatSymbols().getGroupingSeparator()));
            return r1_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private JSONObject getDateNames(JSONArray r10_JSONArray) throws GlobalizationError {
        JSONObject r5_JSONObject = new JSONObject();
        JSONArray r6_JSONArray = new JSONArray();
        List r7_List = new ArrayList();
        int r3i;
        int r0i;
        Map r2_Map;
        try {
            int r3i_2;
            int r0i_2;
            Map r2_Map_2;
            if (r10_JSONArray.getJSONObject(0).length() > 0) {
                r3i_2 = (((JSONObject) r10_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(TYPE) || (!((String) ((JSONObject) r10_JSONArray.getJSONObject(0).get(OPTIONS)).get(TYPE)).equalsIgnoreCase(NARROW))) ? 0 : 1;
                r0i_2 = (((JSONObject) r10_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(ITEM) || (!((String) ((JSONObject) r10_JSONArray.getJSONObject(0).get(OPTIONS)).get(ITEM)).equalsIgnoreCase(DAYS))) ? 0 : 10;
            } else {
                r0i_2 = 0;
                r3i_2 = 0;
            }
            r0i += r3i_2;
            if (r0i == 1) {
                r2_Map_2 = Calendar.getInstance().getDisplayNames(XListViewHeader.STATE_REFRESHING, 1, Locale.getDefault());
            } else if (r0i == 10) {
                r2_Map_2 = Calendar.getInstance().getDisplayNames(ShareUtils.SHARE_COLLECT, XListViewHeader.STATE_REFRESHING, Locale.getDefault());
            } else if (r0i == 11) {
                r2_Map_2 = Calendar.getInstance().getDisplayNames(ShareUtils.SHARE_COLLECT, 1, Locale.getDefault());
            } else {
                r2_Map_2 = Calendar.getInstance().getDisplayNames(XListViewHeader.STATE_REFRESHING, XListViewHeader.STATE_REFRESHING, Locale.getDefault());
            }
            Iterator r3_Iterator = r2_Map_2.keySet().iterator();
            while (r3_Iterator.hasNext()) {
                r7_List.add((String) r3_Iterator.next());
            }
            Collections.sort(r7_List, new AnonymousClass_1(r2_Map_2));
            r0i = 0;
            while (r0i < r7_List.size()) {
                r6_JSONArray.put(r7_List.get(r0i));
                r0i++;
            }
            return r5_JSONObject.put(SharedPref.VALUE, r6_JSONArray);
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getDatePattern(JSONArray r7_JSONArray) throws GlobalizationError {
        JSONObject r4_JSONObject = new JSONObject();
        String r0_String;
        try {
            String r0_String_2;
            SimpleDateFormat r0_SimpleDateFormat = (SimpleDateFormat) DateFormat.getDateFormat(this.cordova.getActivity());
            SimpleDateFormat r1_SimpleDateFormat = (SimpleDateFormat) DateFormat.getTimeFormat(this.cordova.getActivity());
            String r2_String = r0_SimpleDateFormat.toLocalizedPattern() + " " + r1_SimpleDateFormat.toLocalizedPattern();
            if (r7_JSONArray.getJSONObject(0).length() > 1) {
                SimpleDateFormat r3_SimpleDateFormat;
                if (((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(FORMATLENGTH)) {
                    r3_SimpleDateFormat = r0_SimpleDateFormat;
                } else {
                    r2_String = (String) ((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).get(FORMATLENGTH);
                    if (r2_String.equalsIgnoreCase(MEDIUM)) {
                        r3_SimpleDateFormat = (SimpleDateFormat) DateFormat.getMediumDateFormat(this.cordova.getActivity());
                    } else if (r2_String.equalsIgnoreCase(LONG) || r2_String.equalsIgnoreCase(FULL)) {
                        r3_SimpleDateFormat = (SimpleDateFormat) DateFormat.getLongDateFormat(this.cordova.getActivity());
                    } else {
                        r3_SimpleDateFormat = r0_SimpleDateFormat;
                    }
                }
                r2_String = r3_SimpleDateFormat.toLocalizedPattern() + " " + r1_SimpleDateFormat.toLocalizedPattern();
                if (!((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(SELECTOR)) {
                    r0_String_2 = (String) ((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).get(SELECTOR);
                    if (r0_String_2.equalsIgnoreCase(DATE)) {
                        r0_String = r3_SimpleDateFormat.toLocalizedPattern();
                    } else if (r0_String_2.equalsIgnoreCase(TIME)) {
                        r0_String = r1_SimpleDateFormat.toLocalizedPattern();
                    }
                }
                r0_String = r2_String;
            } else {
                r0_String = r2_String;
            }
            TimeZone r1_TimeZone = TimeZone.getTimeZone(Time.getCurrentTimezone());
            r4_JSONObject.put("pattern", r0_String);
            r4_JSONObject.put("timezone", r1_TimeZone.getDisplayName(r1_TimeZone.inDaylightTime(Calendar.getInstance().getTime()), 0));
            r4_JSONObject.put("utc_offset", r1_TimeZone.getRawOffset() / 1000);
            r4_JSONObject.put("dst_offset", r1_TimeZone.getDSTSavings() / 1000);
            return r4_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.PATTERN_ERROR);
        }
    }

    private JSONObject getDateToString(JSONArray r6_JSONArray) throws GlobalizationError {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            Date r2_Date = new Date(((Long) r6_JSONArray.getJSONObject(0).get(DATE)).longValue());
            return r1_JSONObject.put(SharedPref.VALUE, new SimpleDateFormat(getDatePattern(r6_JSONArray).getString("pattern")).format(r2_Date));
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private JSONObject getFirstDayOfWeek(JSONArray r4_JSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put(SharedPref.VALUE, Calendar.getInstance(Locale.getDefault()).getFirstDayOfWeek());
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getIsDayLightSavingsTime(JSONArray r6_JSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put("dst", TimeZone.getTimeZone(Time.getCurrentTimezone()).inDaylightTime(new Date(((Long) r6_JSONArray.getJSONObject(0).get(DATE)).longValue())));
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getLocaleName() throws GlobalizationError {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put(SharedPref.VALUE, Locale.getDefault().toString());
            return r0_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private DecimalFormat getNumberFormatInstance(JSONArray r5_JSONArray) throws JSONException {
        DecimalFormat r1_DecimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
        try {
            if (r5_JSONArray.getJSONObject(0).length() <= 1 || ((JSONObject) r5_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(TYPE)) {
                return r1_DecimalFormat;
            }
            String r2_String = (String) ((JSONObject) r5_JSONArray.getJSONObject(0).get(OPTIONS)).get(TYPE);
            if (r2_String.equalsIgnoreCase(CURRENCY)) {
                r1_DecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
                return r1_DecimalFormat;
            } else {
                if (r2_String.equalsIgnoreCase(PERCENT)) {
                    r1_DecimalFormat = (DecimalFormat) DecimalFormat.getPercentInstance(Locale.getDefault());
                }
                return r1_DecimalFormat;
            }
        } catch (JSONException e) {
        }
    }

    private JSONObject getNumberPattern(JSONArray r7_JSONArray) throws GlobalizationError {
        JSONObject r3_JSONObject = new JSONObject();
        DecimalFormat r1_DecimalFormat;
        String r0_String;
        try {
            DecimalFormat r1_DecimalFormat_2;
            String r0_String_2;
            DecimalFormat r0_DecimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
            String r2_String = String.valueOf(r0_DecimalFormat.getDecimalFormatSymbols().getDecimalSeparator());
            if (r7_JSONArray.getJSONObject(0).length() <= 0 || ((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).isNull(TYPE)) {
                r1_DecimalFormat_2 = r0_DecimalFormat;
                r0_String_2 = r2_String;
            } else {
                String r1_String = (String) ((JSONObject) r7_JSONArray.getJSONObject(0).get(OPTIONS)).get(TYPE);
                if (r1_String.equalsIgnoreCase(CURRENCY)) {
                    r0_DecimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
                    r1_DecimalFormat_2 = r0_DecimalFormat;
                    r0_String_2 = r0_DecimalFormat.getDecimalFormatSymbols().getCurrencySymbol();
                } else {
                    if (r1_String.equalsIgnoreCase(PERCENT)) {
                        r0_DecimalFormat = (DecimalFormat) DecimalFormat.getPercentInstance(Locale.getDefault());
                        r1_DecimalFormat_2 = r0_DecimalFormat;
                        r0_String_2 = String.valueOf(r0_DecimalFormat.getDecimalFormatSymbols().getPercent());
                    }
                    r1_DecimalFormat_2 = r0_DecimalFormat;
                    r0_String_2 = r2_String;
                }
            }
            r3_JSONObject.put("pattern", r1_DecimalFormat_2.toPattern());
            r3_JSONObject.put("symbol", r0_String_2);
            r3_JSONObject.put("fraction", r1_DecimalFormat_2.getMinimumFractionDigits());
            r3_JSONObject.put("rounding", new Integer(0));
            r3_JSONObject.put("positive", r1_DecimalFormat_2.getPositivePrefix());
            r3_JSONObject.put("negative", r1_DecimalFormat_2.getNegativePrefix());
            r3_JSONObject.put("decimal", String.valueOf(r1_DecimalFormat_2.getDecimalFormatSymbols().getDecimalSeparator()));
            r3_JSONObject.put("grouping", String.valueOf(r1_DecimalFormat_2.getDecimalFormatSymbols().getGroupingSeparator()));
            return r3_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.PATTERN_ERROR);
        }
    }

    private JSONObject getNumberToString(JSONArray r5_JSONArray) throws GlobalizationError {
        JSONObject r0_JSONObject = new JSONObject();
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            return r0_JSONObject.put(SharedPref.VALUE, getNumberFormatInstance(r5_JSONArray).format(r5_JSONArray.getJSONObject(0).get(NUMBER)));
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.FORMATTING_ERROR);
        }
    }

    private JSONObject getPreferredLanguage() throws GlobalizationError {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put(SharedPref.VALUE, Locale.getDefault().getDisplayLanguage().toString());
            return r0_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
        }
    }

    private JSONObject getStringToNumber(JSONArray r5_JSONArray) throws GlobalizationError {
        try {
            return new JSONObject().put(SharedPref.VALUE, getNumberFormatInstance(r5_JSONArray).parse((String) r5_JSONArray.getJSONObject(0).get(NUMBERSTRING)));
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.PARSING_ERROR);
        }
    }

    private JSONObject getStringtoDate(JSONArray r6_JSONArray) throws GlobalizationError {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            Date r1_Date = new SimpleDateFormat(getDatePattern(r6_JSONArray).getString("pattern")).parse(r6_JSONArray.getJSONObject(0).get(DATESTRING).toString());
            Time r2_Time = new Time();
            r2_Time.set(r1_Date.getTime());
            r0_JSONObject.put("year", r2_Time.year);
            r0_JSONObject.put("month", r2_Time.month);
            r0_JSONObject.put("day", r2_Time.monthDay);
            r0_JSONObject.put("hour", r2_Time.hour);
            r0_JSONObject.put("minute", r2_Time.minute);
            r0_JSONObject.put("second", r2_Time.second);
            r0_JSONObject.put("millisecond", new Long(0));
            return r0_JSONObject;
        } catch (Exception e) {
            throw new GlobalizationError(GlobalizationError.PARSING_ERROR);
        }
    }

    public boolean execute(String r4_String, JSONArray r5_JSONArray, CallbackContext r6_CallbackContext) {
        boolean r0z;
        JSONObject r0_JSONObject = new JSONObject();
        try {
            if (r4_String.equals(GETLOCALENAME)) {
                r0_JSONObject = getLocaleName();
            } else if (r4_String.equals(GETPREFERREDLANGUAGE)) {
                r0_JSONObject = getPreferredLanguage();
            } else if (r4_String.equalsIgnoreCase(DATETOSTRING)) {
                r0_JSONObject = getDateToString(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(STRINGTODATE)) {
                r0_JSONObject = getStringtoDate(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(GETDATEPATTERN)) {
                r0_JSONObject = getDatePattern(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(GETDATENAMES)) {
                if (VERSION.SDK_INT < 9) {
                    throw new GlobalizationError(GlobalizationError.UNKNOWN_ERROR);
                } else {
                    r0_JSONObject = getDateNames(r5_JSONArray);
                }
            } else if (r4_String.equalsIgnoreCase(ISDAYLIGHTSAVINGSTIME)) {
                r0_JSONObject = getIsDayLightSavingsTime(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(GETFIRSTDAYOFWEEK)) {
                r0_JSONObject = getFirstDayOfWeek(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(NUMBERTOSTRING)) {
                r0_JSONObject = getNumberToString(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(STRINGTONUMBER)) {
                r0_JSONObject = getStringToNumber(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(GETNUMBERPATTERN)) {
                r0_JSONObject = getNumberPattern(r5_JSONArray);
            } else if (r4_String.equalsIgnoreCase(GETCURRENCYPATTERN)) {
                r0_JSONObject = getCurrencyPattern(r5_JSONArray);
            } else {
                r0z = false;
                return r0z;
            }
            r6_CallbackContext.success(r0_JSONObject);
        } catch (GlobalizationError e) {
            r6_CallbackContext.sendPluginResult(new PluginResult(Status.ERROR, e.toJson()));
        } catch (Exception e_2) {
            r6_CallbackContext.sendPluginResult(new PluginResult(Status.JSON_EXCEPTION));
        }
        r0z = true;
        return r0z;
    }
}
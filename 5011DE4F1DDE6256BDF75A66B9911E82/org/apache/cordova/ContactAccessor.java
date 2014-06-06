package org.apache.cordova;

import android.util.Log;
import android.webkit.WebView;
import com.tencent.mm.sdk.contact.RContact;
import java.util.HashMap;
import org.apache.cordova.api.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ContactAccessor {
    protected final String LOG_TAG;
    protected CordovaInterface mApp;
    protected WebView mView;

    class WhereOptions {
        private String where;
        private String[] whereArgs;

        WhereOptions() {
        }

        public String getWhere() {
            return this.where;
        }

        public String[] getWhereArgs() {
            return this.whereArgs;
        }

        public void setWhere(String r1_String) {
            this.where = r1_String;
        }

        public void setWhereArgs(String[] r1_StringA) {
            this.whereArgs = r1_StringA;
        }
    }

    public ContactAccessor() {
        this.LOG_TAG = "ContactsAccessor";
    }

    protected HashMap<String, Boolean> buildPopulationSet(JSONArray r5_JSONArray) {
        int r0i = 0;
        HashMap<String, Boolean> r1_HashMap_String__Boolean = new HashMap();
        try {
            if (r5_JSONArray.length() == 1 && r5_JSONArray.getString(0).equals("*")) {
                r1_HashMap_String__Boolean.put("displayName", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("name", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put(RContact.COL_NICKNAME, Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("phoneNumbers", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("emails", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("addresses", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("ims", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("organizations", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("birthday", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("note", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("urls", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("photos", Boolean.valueOf(true));
                r1_HashMap_String__Boolean.put("categories", Boolean.valueOf(true));
            } else {
                while (r0i < r5_JSONArray.length()) {
                    String r2_String = r5_JSONArray.getString(r0i);
                    if (r2_String.startsWith("displayName")) {
                        r1_HashMap_String__Boolean.put("displayName", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("name")) {
                        r1_HashMap_String__Boolean.put("displayName", Boolean.valueOf(true));
                        r1_HashMap_String__Boolean.put("name", Boolean.valueOf(true));
                    } else if (r2_String.startsWith(RContact.COL_NICKNAME)) {
                        r1_HashMap_String__Boolean.put(RContact.COL_NICKNAME, Boolean.valueOf(true));
                    } else if (r2_String.startsWith("phoneNumbers")) {
                        r1_HashMap_String__Boolean.put("phoneNumbers", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("emails")) {
                        r1_HashMap_String__Boolean.put("emails", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("addresses")) {
                        r1_HashMap_String__Boolean.put("addresses", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("ims")) {
                        r1_HashMap_String__Boolean.put("ims", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("organizations")) {
                        r1_HashMap_String__Boolean.put("organizations", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("birthday")) {
                        r1_HashMap_String__Boolean.put("birthday", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("note")) {
                        r1_HashMap_String__Boolean.put("note", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("urls")) {
                        r1_HashMap_String__Boolean.put("urls", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("photos")) {
                        r1_HashMap_String__Boolean.put("photos", Boolean.valueOf(true));
                    } else if (r2_String.startsWith("categories")) {
                        r1_HashMap_String__Boolean.put("categories", Boolean.valueOf(true));
                    }
                    r0i++;
                }
            }
        } catch (JSONException e) {
            Throwable r0_Throwable = e;
            Log.e("ContactsAccessor", r0_Throwable.getMessage(), r0_Throwable);
        }
        return r1_HashMap_String__Boolean;
    }

    public abstract JSONObject getContactById(String r1_String) throws JSONException;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected String getJsonString(JSONObject r7_JSONObject, String r8_String) {
        /*
        r6_this = this;
        r0 = 0;
        if (r7 == 0) goto L_0x0027;
    L_0x0003:
        r1 = r7.getString(r8);	 //Catch:{ JSONException -> 0x0028 }
        r2 = "null";
        r2 = r1.equals(r2);	 //Catch:{ JSONException -> 0x0046 }
        if (r2 == 0) goto L_0x004b;
    L_0x000f:
        r2 = "ContactsAccessor";
        r3 = new java.lang.StringBuilder;	 //Catch:{ JSONException -> 0x0046 }
        r3.<init>();	 //Catch:{ JSONException -> 0x0046 }
        r3 = r3.append(r8);	 //Catch:{ JSONException -> 0x0046 }
        r4 = " is string called 'null'";
        r3 = r3.append(r4);	 //Catch:{ JSONException -> 0x0046 }
        r3 = r3.toString();	 //Catch:{ JSONException -> 0x0046 }
        android.util.Log.d(r2, r3);	 //Catch:{ JSONException -> 0x0046 }
    L_0x0027:
        return r0;
    L_0x0028:
        r1 = move-exception;
    L_0x0029:
        r2 = "ContactsAccessor";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Could not get = ";
        r3 = r3.append(r4);
        r1 = r1.getMessage();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.d(r2, r1);
        goto L_0x0027;
    L_0x0046:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x0029;
    L_0x004b:
        r0 = r1;
        goto L_0x0027;
        */

    }

    protected boolean isRequired(String r2_String, HashMap<String, Boolean> r3_HashMap_String__Boolean) {
        Boolean r0_Boolean = (Boolean) r3_HashMap_String__Boolean.get(r2_String);
        return r0_Boolean == null ? false : r0_Boolean.booleanValue();
    }

    public abstract boolean remove(String r1_String);

    public abstract String save(JSONObject r1_JSONObject);

    public abstract JSONArray search(JSONArray r1_JSONArray, JSONObject r2_JSONObject);
}
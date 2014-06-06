package org.apache.cordova;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.push.Utils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class GlobalizationError extends Exception {
    public static final String FORMATTING_ERROR = "FORMATTING_ERROR";
    public static final String PARSING_ERROR = "PARSING_ERROR";
    public static final String PATTERN_ERROR = "PATTERN_ERROR";
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private static final long serialVersionUID = 1;
    int error;

    public GlobalizationError() {
        this.error = 0;
    }

    public GlobalizationError(String r2_String) {
        this.error = 0;
        if (r2_String.equalsIgnoreCase(FORMATTING_ERROR)) {
            this.error = 1;
        } else if (r2_String.equalsIgnoreCase(PARSING_ERROR)) {
            this.error = 2;
        } else {
            if (r2_String.equalsIgnoreCase(PATTERN_ERROR)) {
                this.error = 3;
            }
        }
    }

    public int getErrorCode() {
        return this.error;
    }

    public String getErrorString() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        switch (this.error) {
            case XListViewHeader.STATE_NORMAL:
                return UNKNOWN_ERROR;
            case XListViewHeader.STATE_READY:
                return FORMATTING_ERROR;
            case XListViewHeader.STATE_REFRESHING:
                return PARSING_ERROR;
            case XListViewFooter.STATE_NOMORE:
                return PATTERN_ERROR;
        }
        return r0_String;
    }

    public JSONObject toJson() {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put("code", getErrorCode());
            r0_JSONObject.put(Utils.EXTRA_MESSAGE, getErrorString());
        } catch (JSONException e) {
        }
        return r0_JSONObject;
    }
}
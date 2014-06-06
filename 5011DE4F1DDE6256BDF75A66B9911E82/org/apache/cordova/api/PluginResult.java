package org.apache.cordova.api;

import android.util.Base64;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONArray;
import org.json.JSONObject;

public class PluginResult {
    public static final int MESSAGE_TYPE_ARRAYBUFFER = 6;
    public static final int MESSAGE_TYPE_BOOLEAN = 4;
    public static final int MESSAGE_TYPE_JSON = 2;
    public static final int MESSAGE_TYPE_NULL = 5;
    public static final int MESSAGE_TYPE_NUMBER = 3;
    public static final int MESSAGE_TYPE_STRING = 1;
    public static String[] StatusMessages;
    private String encodedMessage;
    private boolean keepCallback;
    private final int messageType;
    private final int status;
    private String strMessage;

    public static enum Status {
        NO_RESULT,
        OK,
        CLASS_NOT_FOUND_EXCEPTION,
        ILLEGAL_ACCESS_EXCEPTION,
        INSTANTIATION_EXCEPTION,
        MALFORMED_URL_EXCEPTION,
        IO_EXCEPTION,
        INVALID_ACTION,
        JSON_EXCEPTION,
        ERROR;

    }

    static {
        String[] r0_StringA = new String[10];
        r0_StringA[0] = "No result";
        r0_StringA[1] = "OK";
        r0_StringA[2] = "Class not found";
        r0_StringA[3] = "Illegal access";
        r0_StringA[4] = "Instantiation error";
        r0_StringA[5] = "Malformed url";
        r0_StringA[6] = "IO error";
        r0_StringA[7] = "Invalid action";
        r0_StringA[8] = "JSON error";
        r0_StringA[9] = "Error";
        StatusMessages = r0_StringA;
    }

    public PluginResult(Status r3_Status) {
        this(r3_Status, StatusMessages[r3_Status.ordinal()]);
    }

    public PluginResult(Status r3_Status, float r4f) {
        this.keepCallback = false;
        this.status = r3_Status.ordinal();
        this.messageType = 3;
        this.encodedMessage = RContactStorage.PRIMARY_KEY + r4f;
    }

    public PluginResult(Status r3_Status, int r4i) {
        this.keepCallback = false;
        this.status = r3_Status.ordinal();
        this.messageType = 3;
        this.encodedMessage = RContactStorage.PRIMARY_KEY + r4i;
    }

    public PluginResult(Status r2_Status, String r3_String) {
        this.keepCallback = false;
        this.status = r2_Status.ordinal();
        this.messageType = r3_String == null ? MESSAGE_TYPE_NULL : MESSAGE_TYPE_STRING;
        this.strMessage = r3_String;
    }

    public PluginResult(Status r2_Status, JSONArray r3_JSONArray) {
        this.keepCallback = false;
        this.status = r2_Status.ordinal();
        this.messageType = 2;
        this.encodedMessage = r3_JSONArray.toString();
    }

    public PluginResult(Status r2_Status, JSONObject r3_JSONObject) {
        this.keepCallback = false;
        this.status = r2_Status.ordinal();
        this.messageType = 2;
        this.encodedMessage = r3_JSONObject.toString();
    }

    public PluginResult(Status r2_Status, boolean r3z) {
        this.keepCallback = false;
        this.status = r2_Status.ordinal();
        this.messageType = 4;
        this.encodedMessage = Boolean.toString(r3z);
    }

    public PluginResult(Status r2_Status, byte[] r3_byteA) {
        this.keepCallback = false;
        this.status = r2_Status.ordinal();
        this.messageType = 6;
        this.encodedMessage = Base64.encodeToString(r3_byteA, MESSAGE_TYPE_JSON);
    }

    public String getJSONString() {
        return "{\"status\":" + this.status + ",\"message\":" + getMessage() + ",\"keepCallback\":" + this.keepCallback + "}";
    }

    public boolean getKeepCallback() {
        return this.keepCallback;
    }

    public String getMessage() {
        if (this.encodedMessage == null) {
            this.encodedMessage = JSONObject.quote(this.strMessage);
        }
        return this.encodedMessage;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStrMessage() {
        return this.strMessage;
    }

    public void setKeepCallback(boolean r1z) {
        this.keepCallback = r1z;
    }

    public String toCallbackString(String r3_String) {
        if (this.status == Status.NO_RESULT.ordinal() && this.keepCallback) {
            return null;
        }
        if (this.status == Status.OK.ordinal() || this.status == Status.NO_RESULT.ordinal()) {
            return toSuccessCallbackString(r3_String);
        }
        return toErrorCallbackString(r3_String);
    }

    public String toErrorCallbackString(String r3_String) {
        return "cordova.callbackError('" + r3_String + "', " + getJSONString() + ");";
    }

    public String toSuccessCallbackString(String r3_String) {
        return "cordova.callbackSuccess('" + r3_String + "'," + getJSONString() + ");";
    }
}
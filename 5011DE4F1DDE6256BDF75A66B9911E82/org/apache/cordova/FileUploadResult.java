package org.apache.cordova;

import org.json.JSONException;
import org.json.JSONObject;

public class FileUploadResult {
    private long bytesSent;
    private String objectId;
    private String response;
    private int responseCode;

    public FileUploadResult() {
        this.bytesSent = 0;
        this.responseCode = -1;
        this.response = null;
        this.objectId = null;
    }

    public long getBytesSent() {
        return this.bytesSent;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getResponse() {
        return this.response;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setBytesSent(long r1j) {
        this.bytesSent = r1j;
    }

    public void setObjectId(String r1_String) {
        this.objectId = r1_String;
    }

    public void setResponse(String r1_String) {
        this.response = r1_String;
    }

    public void setResponseCode(int r1i) {
        this.responseCode = r1i;
    }

    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject("{bytesSent:" + this.bytesSent + ",responseCode:" + this.responseCode + ",response:" + JSONObject.quote(this.response) + ",objectId:" + JSONObject.quote(this.objectId) + "}");
    }
}
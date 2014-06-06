package org.apache.cordova;

import android.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaArgs {
    private JSONArray baseArgs;

    public CordovaArgs(JSONArray r1_JSONArray) {
        this.baseArgs = r1_JSONArray;
    }

    public Object get(int r2i) throws JSONException {
        return this.baseArgs.get(r2i);
    }

    public byte[] getArrayBuffer(int r3i) throws JSONException {
        return Base64.decode(this.baseArgs.getString(r3i), 0);
    }

    public boolean getBoolean(int r2i) throws JSONException {
        return this.baseArgs.getBoolean(r2i);
    }

    public double getDouble(int r3i) throws JSONException {
        return this.baseArgs.getDouble(r3i);
    }

    public int getInt(int r2i) throws JSONException {
        return this.baseArgs.getInt(r2i);
    }

    public JSONArray getJSONArray(int r2i) throws JSONException {
        return this.baseArgs.getJSONArray(r2i);
    }

    public Object getJSONObject(int r2i) throws JSONException {
        return this.baseArgs.getJSONObject(r2i);
    }

    public long getLong(int r3i) throws JSONException {
        return this.baseArgs.getLong(r3i);
    }

    public String getString(int r2i) throws JSONException {
        return this.baseArgs.getString(r2i);
    }

    public boolean isNull(int r2i) {
        return this.baseArgs.isNull(r2i);
    }

    public Object opt(int r2i) {
        return this.baseArgs.opt(r2i);
    }

    public boolean optBoolean(int r2i) {
        return this.baseArgs.optBoolean(r2i);
    }

    public double optDouble(int r3i) {
        return this.baseArgs.optDouble(r3i);
    }

    public int optInt(int r2i) {
        return this.baseArgs.optInt(r2i);
    }

    public JSONArray optJSONArray(int r2i) {
        return this.baseArgs.optJSONArray(r2i);
    }

    public Object optJSONObject(int r2i) {
        return this.baseArgs.optJSONObject(r2i);
    }

    public long optLong(int r3i) {
        return this.baseArgs.optLong(r3i);
    }

    public String optString(int r2i) {
        return this.baseArgs.optString(r2i);
    }
}
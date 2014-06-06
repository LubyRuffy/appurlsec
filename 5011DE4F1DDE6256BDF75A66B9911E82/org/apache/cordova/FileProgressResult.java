package org.apache.cordova;

import org.json.JSONException;
import org.json.JSONObject;

public class FileProgressResult {
    private boolean lengthComputable;
    private long loaded;
    private long total;

    public FileProgressResult() {
        this.lengthComputable = false;
        this.loaded = 0;
        this.total = 0;
    }

    public boolean getLengthComputable() {
        return this.lengthComputable;
    }

    public long getLoaded() {
        return this.loaded;
    }

    public long getTotal() {
        return this.total;
    }

    public void setLengthComputable(boolean r1z) {
        this.lengthComputable = r1z;
    }

    public void setLoaded(long r1j) {
        this.loaded = r1j;
    }

    public void setTotal(long r1j) {
        this.total = r1j;
    }

    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject("{loaded:" + this.loaded + ",total:" + this.total + ",lengthComputable:" + (this.lengthComputable ? "true" : "false") + "}");
    }
}
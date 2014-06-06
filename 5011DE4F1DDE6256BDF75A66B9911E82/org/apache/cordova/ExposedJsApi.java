package org.apache.cordova;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.api.PluginManager;
import org.json.JSONException;

class ExposedJsApi {
    private NativeToJsMessageQueue jsMessageQueue;
    private PluginManager pluginManager;

    public ExposedJsApi(PluginManager r1_PluginManager, NativeToJsMessageQueue r2_NativeToJsMessageQueue) {
        this.pluginManager = r1_PluginManager;
        this.jsMessageQueue = r2_NativeToJsMessageQueue;
    }

    public String exec(String r4_String, String r5_String, String r6_String, String r7_String) throws JSONException {
        this.jsMessageQueue.setPaused(true);
        this.pluginManager.exec(r4_String, r5_String, r6_String, r7_String);
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = this.jsMessageQueue.popAndEncode();
        this.jsMessageQueue.setPaused(false);
        return r0_String;
    }

    public String retrieveJsMessages() {
        return this.jsMessageQueue.popAndEncode();
    }

    public void setNativeToJsBridgeMode(int r2i) {
        this.jsMessageQueue.setBridgeMode(r2i);
    }
}
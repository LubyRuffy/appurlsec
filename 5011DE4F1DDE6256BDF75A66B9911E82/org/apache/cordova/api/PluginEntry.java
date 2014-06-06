package org.apache.cordova.api;

import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.CordovaWebView;

public class PluginEntry {
    public boolean onload;
    public CordovaPlugin plugin;
    public String pluginClass;
    public String service;

    public PluginEntry(String r2_String, String r3_String, boolean r4z) {
        this.service = RContactStorage.PRIMARY_KEY;
        this.pluginClass = RContactStorage.PRIMARY_KEY;
        this.plugin = null;
        this.onload = false;
        this.service = r2_String;
        this.pluginClass = r3_String;
        this.onload = r4z;
    }

    private Class getClassByName(String r2_String) throws ClassNotFoundException {
        return r2_String != null ? Class.forName(r2_String) : null;
    }

    private boolean isCordovaPlugin(Class r2_Class) {
        return r2_Class != null ? CordovaPlugin.class.isAssignableFrom(r2_Class) : false;
    }

    public CordovaPlugin createPlugin(CordovaWebView r4_CordovaWebView, CordovaInterface r5_CordovaInterface) {
        if (this.plugin != null) {
            return this.plugin;
        }
        try {
            Class r0_Class = getClassByName(this.pluginClass);
            if (isCordovaPlugin(r0_Class)) {
                this.plugin = (CordovaPlugin) r0_Class.newInstance();
                this.plugin.initialize(r5_CordovaInterface, r4_CordovaWebView);
                return this.plugin;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding plugin " + this.pluginClass + ".");
        }
        return null;
    }
}
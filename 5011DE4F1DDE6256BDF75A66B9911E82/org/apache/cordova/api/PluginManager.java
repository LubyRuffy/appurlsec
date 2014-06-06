package org.apache.cordova.api;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

public class PluginManager {
    private static String TAG;
    private final CordovaWebView app;
    private final CordovaInterface ctx;
    private final HashMap<String, PluginEntry> entries;
    private boolean firstRun;
    protected HashMap<String, String> urlMap;

    static {
        TAG = "PluginManager";
    }

    public PluginManager(CordovaWebView r2_CordovaWebView, CordovaInterface r3_CordovaInterface) {
        this.entries = new HashMap();
        this.urlMap = new HashMap();
        this.ctx = r3_CordovaInterface;
        this.app = r2_CordovaWebView;
        this.firstRun = true;
    }

    private void pluginConfigurationMissing() {
        LOG.e(TAG, "=====================================================================================");
        LOG.e(TAG, "ERROR: plugin.xml is missing.  Add res/xml/plugins.xml to your project.");
        LOG.e(TAG, "https://git-wip-us.apache.org/repos/asf?p=incubator-cordova-android.git;a=blob;f=framework/res/xml/plugins.xml");
        LOG.e(TAG, "=====================================================================================");
    }

    public void addService(String r3_String, String r4_String) {
        addService(new PluginEntry(r3_String, r4_String, false));
    }

    public void addService(PluginEntry r3_PluginEntry) {
        this.entries.put(r3_PluginEntry.service, r3_PluginEntry);
    }

    public void clearPluginObjects() {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            ((PluginEntry) r1_Iterator.next()).plugin = null;
        }
    }

    public boolean exec(String r5_String, String r6_String, String r7_String, String r8_String) {
        CordovaPlugin r1_CordovaPlugin = getPlugin(r5_String);
        if (r1_CordovaPlugin == null) {
            this.app.sendPluginResult(new PluginResult(Status.CLASS_NOT_FOUND_EXCEPTION), r7_String);
            return true;
        } else {
            try {
                CallbackContext r2_CallbackContext = new CallbackContext(r7_String, this.app);
                if (r1_CordovaPlugin.execute(r6_String, r8_String, r2_CallbackContext)) {
                    return r2_CallbackContext.isFinished();
                }
                this.app.sendPluginResult(new PluginResult(Status.INVALID_ACTION), r7_String);
                return true;
            } catch (JSONException e) {
                this.app.sendPluginResult(new PluginResult(Status.JSON_EXCEPTION), r7_String);
                return true;
            }
        }
    }

    public boolean exec(String r2_String, String r3_String, String r4_String, String r5_String, boolean r6z) {
        return exec(r2_String, r3_String, r4_String, r5_String);
    }

    public CordovaPlugin getPlugin(String r4_String) {
        PluginEntry r0_PluginEntry = (PluginEntry) this.entries.get(r4_String);
        if (r0_PluginEntry == null) {
            return null;
        }
        CordovaPlugin r1_CordovaPlugin = r0_PluginEntry.plugin;
        return r1_CordovaPlugin == null ? r0_PluginEntry.createPlugin(this.app, this.ctx) : r1_CordovaPlugin;
    }

    public void init() {
        LOG.d(TAG, "init()");
        if (this.firstRun) {
            loadPlugins();
            this.firstRun = false;
        } else {
            onPause(false);
            onDestroy();
            clearPluginObjects();
        }
        startupPlugins();
    }

    public void loadPlugins() {
        int r0i = this.ctx.getActivity().getResources().getIdentifier("config", "xml", this.ctx.getActivity().getPackageName());
        if (r0i == 0) {
            r0i = this.ctx.getActivity().getResources().getIdentifier("plugins", "xml", this.ctx.getActivity().getPackageName());
            LOG.i(TAG, "Using plugins.xml instead of config.xml.  plugins.xml will eventually be deprecated");
        }
        if (r0i == 0) {
            pluginConfigurationMissing();
        } else {
            XmlResourceParser r6_XmlResourceParser = this.ctx.getActivity().getResources().getXml(r0i);
            String r3_String = RContactStorage.PRIMARY_KEY;
            String r2_String = RContactStorage.PRIMARY_KEY;
            String r5_String = RContactStorage.PRIMARY_KEY;
            int r5i = -1;
            r0i = 0;
            while (r5i != 1) {
                if (r5i == 2) {
                    String r7_String = r6_XmlResourceParser.getName();
                    if (r7_String.equals("plugin")) {
                        r3_String = r6_XmlResourceParser.getAttributeValue(null, "name");
                        r2_String = r6_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE);
                        addService(new PluginEntry(r3_String, r2_String, "true".equals(r6_XmlResourceParser.getAttributeValue(null, "onload"))));
                    } else if (r7_String.equals("url-filter")) {
                        this.urlMap.put(r6_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE), r3_String);
                    } else if (r7_String.equals("feature")) {
                        r6_XmlResourceParser.getAttributeValue(null, "name");
                        r0i = 1;
                    } else if ((!r7_String.equals("param")) || r0i == 0) {
                        try {
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e_2) {
                            e_2.printStackTrace();
                        }
                    } else {
                        r7_String = r6_XmlResourceParser.getAttributeValue(null, "name");
                        if (r7_String.equals("service")) {
                            r3_String = r6_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE);
                        } else if (r7_String.equals("package")) {
                            r2_String = r6_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE);
                        }
                        if (r3_String.length() <= 0 || r2_String.length() <= 0) {
                        } else {
                            addService(new PluginEntry(r3_String, r2_String, "true".equals(r6_XmlResourceParser.getAttributeValue(null, "onload"))));
                            r3_String = RContactStorage.PRIMARY_KEY;
                            r2_String = RContactStorage.PRIMARY_KEY;
                        }
                        r5i = r6_XmlResourceParser.next();
                    }
                } else {
                    if (r5i == 3 && r6_XmlResourceParser.getName().equals("feature")) {
                        r3_String = RContactStorage.PRIMARY_KEY;
                        r2_String = RContactStorage.PRIMARY_KEY;
                        r0i = 0;
                    }
                    r5i = r6_XmlResourceParser.next();
                }
                r5i = r6_XmlResourceParser.next();
            }
        }
    }

    public void onDestroy() {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.plugin != null) {
                r0_PluginEntry.plugin.onDestroy();
            }
        }
    }

    public void onNewIntent(Intent r4_Intent) {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.plugin != null) {
                r0_PluginEntry.plugin.onNewIntent(r4_Intent);
            }
        }
    }

    public boolean onOverrideUrlLoading(String r4_String) {
        Iterator r2_Iterator = this.urlMap.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r2_Iterator.next();
            if (r4_String.startsWith((String) r0_Entry.getKey())) {
                return getPlugin((String) r0_Entry.getValue()).onOverrideUrlLoading(r4_String);
            }
        }
        return false;
    }

    public void onPause(boolean r4z) {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.plugin != null) {
                r0_PluginEntry.plugin.onPause(r4z);
            }
        }
    }

    public void onReset() {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            CordovaPlugin r0_CordovaPlugin = ((PluginEntry) r1_Iterator.next()).plugin;
            if (r0_CordovaPlugin != null) {
                r0_CordovaPlugin.onReset();
            }
        }
    }

    public void onResume(boolean r4z) {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.plugin != null) {
                r0_PluginEntry.plugin.onResume(r4z);
            }
        }
    }

    public Object postMessage(String r4_String, Object r5_Object) {
        Object r0_Object = this.ctx.onMessage(r4_String, r5_Object);
        if (r0_Object != null) {
            return r0_Object;
        }
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.plugin != null) {
                r0_Object = r0_PluginEntry.plugin.onMessage(r4_String, r5_Object);
                if (r0_Object != null) {
                    return r0_Object;
                }
            }
        }
        return null;
    }

    public void startupPlugins() {
        Iterator r1_Iterator = this.entries.values().iterator();
        while (r1_Iterator.hasNext()) {
            PluginEntry r0_PluginEntry = (PluginEntry) r1_Iterator.next();
            if (r0_PluginEntry.onload) {
                r0_PluginEntry.createPlugin(this.app, this.ctx);
            }
        }
    }
}
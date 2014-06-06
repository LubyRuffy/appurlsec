package org.apache.cordova;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.cordova.api.LOG;
import org.xmlpull.v1.XmlPullParserException;
import qsbk.app.push.Utils;
import qsbk.app.utils.image.ImageFetcher;

public class Config {
    public static final String TAG = "Config";
    private static Config self;
    private String startUrl;
    private ArrayList<Pattern> whiteList;
    private HashMap<String, Boolean> whiteListCache;

    static {
        self = null;
    }

    private Config() {
        this.whiteList = new ArrayList();
        this.whiteListCache = new HashMap();
    }

    private Config(Activity r13_Activity) {
        this.whiteList = new ArrayList();
        this.whiteListCache = new HashMap();
        if (r13_Activity == null) {
            LOG.i("CordovaLog", "There is no activity. Is this on the lock screen?");
        } else {
            int r0i = r13_Activity.getResources().getIdentifier("config", "xml", r13_Activity.getPackageName());
            if (r0i == 0) {
                r0i = r13_Activity.getResources().getIdentifier("cordova", "xml", r13_Activity.getPackageName());
                LOG.i("CordovaLog", "config.xml missing, reverting to cordova.xml");
            }
            if (r0i == 0) {
                LOG.i("CordovaLog", "cordova.xml missing. Ignoring...");
            } else {
                XmlResourceParser r4_XmlResourceParser = r13_Activity.getResources().getXml(r0i);
                int r3i = -1;
                while (r3i != 1) {
                    if (r3i == 2) {
                        String r0_String = r4_XmlResourceParser.getName();
                        String r5_String;
                        if (r0_String.equals("access")) {
                            r5_String = r4_XmlResourceParser.getAttributeValue(null, "origin");
                            r0_String = r4_XmlResourceParser.getAttributeValue(null, "subdomains");
                            if (r5_String != null) {
                                boolean r0z;
                                if (r0_String == null || r0_String.compareToIgnoreCase("true") != 0) {
                                    r0z = false;
                                    _addWhiteListEntry(r5_String, r0z);
                                } else {
                                    r0z = true;
                                    _addWhiteListEntry(r5_String, r0z);
                                }
                            }
                        } else if (r0_String.equals("log")) {
                            r0_String = r4_XmlResourceParser.getAttributeValue(null, "level");
                            r7_ObjectA = new Object[1];
                            r7_ObjectA[0] = r0_String;
                            LOG.i("CordovaLog", "Found log level %s", r7_ObjectA);
                            if (r0_String != null) {
                                LOG.setLogLevel(r0_String);
                            }
                        } else if (r0_String.equals("preference")) {
                            r5_String = r4_XmlResourceParser.getAttributeValue(null, "name");
                            Object[] r8_ObjectA;
                            if (r5_String.equals("splashscreen")) {
                                r0_String = r4_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE);
                                if (r0_String != null) {
                                    r0_String = "splash";
                                }
                                r13_Activity.getIntent().putExtra(r5_String, r13_Activity.getResources().getIdentifier(r0_String, "drawable", r13_Activity.getPackageName()));
                                r8_ObjectA = new Object[2];
                                r8_ObjectA[0] = r5_String;
                                r8_ObjectA[1] = r0_String;
                                LOG.i("CordovaLog", "Found preference for %s=%s", r8_ObjectA);
                                Log.d("CordovaLog", "Found preference for " + r5_String + "=" + r0_String);
                            } else if (r5_String.equals("backgroundColor")) {
                                r0i = r4_XmlResourceParser.getAttributeIntValue(null, SharedPref.VALUE, ViewCompat.MEASURED_STATE_MASK);
                                r13_Activity.getIntent().putExtra(r5_String, r0i);
                                r8_ObjectA = new Object[2];
                                r8_ObjectA[0] = r5_String;
                                r8_ObjectA[1] = Integer.valueOf(r0i);
                                LOG.i("CordovaLog", "Found preference for %s=%d", r8_ObjectA);
                                Log.d("CordovaLog", "Found preference for " + r5_String + "=" + Integer.toString(r0i));
                            } else if (r5_String.equals("loadUrlTimeoutValue")) {
                                r0i = r4_XmlResourceParser.getAttributeIntValue(null, SharedPref.VALUE, 20000);
                                r13_Activity.getIntent().putExtra(r5_String, r0i);
                                r8_ObjectA = new Object[2];
                                r8_ObjectA[0] = r5_String;
                                r8_ObjectA[1] = Integer.valueOf(r0i);
                                LOG.i("CordovaLog", "Found preference for %s=%d", r8_ObjectA);
                                Log.d("CordovaLog", "Found preference for " + r5_String + "=" + Integer.toString(r0i));
                            } else if (r5_String.equals("keepRunning")) {
                                r13_Activity.getIntent().putExtra(r5_String, r4_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE).equals("true"));
                            } else {
                                r0_String = r4_XmlResourceParser.getAttributeValue(null, SharedPref.VALUE);
                                r13_Activity.getIntent().putExtra(r5_String, r0_String);
                                r8_ObjectA = new Object[2];
                                r8_ObjectA[0] = r5_String;
                                r8_ObjectA[1] = r0_String;
                                LOG.i("CordovaLog", "Found preference for %s=%s", r8_ObjectA);
                                Log.d("CordovaLog", "Found preference for " + r5_String + "=" + r0_String);
                            }
                        } else if (r0_String.equals(Utils.RESPONSE_CONTENT)) {
                            r0_String = r4_XmlResourceParser.getAttributeValue(null, "src");
                            r7_ObjectA = new Object[1];
                            r7_ObjectA[0] = r0_String;
                            LOG.i("CordovaLog", "Found start page location: %s", r7_ObjectA);
                            if (r0_String != null) {
                                if (Pattern.compile("^[a-z]+://").matcher(r0_String).find()) {
                                    this.startUrl = r0_String;
                                } else {
                                    if (r0_String.charAt(0) == '/') {
                                        r0_String = r0_String.substring(1);
                                    }
                                    this.startUrl = "file:///android_asset/www/" + r0_String;
                                }
                            }
                        }
                    }
                    try {
                        r3i = r4_XmlResourceParser.next();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e_2) {
                        e_2.printStackTrace();
                    }
                }
            }
        }
    }

    private void _addWhiteListEntry(String r7_String, boolean r8z) {
        String r0_String;
        Object[] r2_ObjectA;
        try {
            if (r7_String.compareTo("*") == 0) {
                LOG.d(TAG, "Unlimited access to network resources");
                this.whiteList.add(Pattern.compile(".*"));
            } else if (r8z) {
                if (r7_String.startsWith(ImageFetcher.HTTP_CACHE_DIR)) {
                    this.whiteList.add(Pattern.compile(r7_String.replaceFirst("https?://", "^https?://(.*\\.)?")));
                } else {
                    this.whiteList.add(Pattern.compile("^https?://(.*\\.)?" + r7_String));
                }
                r0_String = TAG;
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r7_String;
                LOG.d(r0_String, "Origin to allow with subdomains: %s", r2_ObjectA);
            } else {
                if (r7_String.startsWith(ImageFetcher.HTTP_CACHE_DIR)) {
                    this.whiteList.add(Pattern.compile(r7_String.replaceFirst("https?://", "^https?://")));
                } else {
                    this.whiteList.add(Pattern.compile("^https?://" + r7_String));
                }
                r0_String = TAG;
                r2_ObjectA = new Object[1];
                r2_ObjectA[0] = r7_String;
                LOG.d(r0_String, "Origin to allow: %s", r2_ObjectA);
            }
        } catch (Exception e) {
            r0_String = TAG;
            r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r7_String;
            LOG.d(r0_String, "Failed to add origin %s", r2_ObjectA);
        }
    }

    public static void addWhiteListEntry(String r1_String, boolean r2z) {
        if (self == null) {
        } else {
            self._addWhiteListEntry(r1_String, r2z);
        }
    }

    public static String getStartUrl() {
        return (self == null || self.startUrl == null) ? "file:///android_asset/www/index.html" : self.startUrl;
    }

    public static void init() {
        if (self == null) {
            self = new Config();
        }
    }

    public static void init(Activity r1_Activity) {
        if (self == null) {
            self = new Config(r1_Activity);
        }
    }

    public static boolean isUrlWhiteListed(String r4_String) {
        if (self == null) {
            return false;
        }
        if (self.whiteListCache.get(r4_String) != null) {
            return true;
        }
        Iterator r3_Iterator = self.whiteList.iterator();
        while (r3_Iterator.hasNext()) {
            if (((Pattern) r3_Iterator.next()).matcher(r4_String).find()) {
                self.whiteListCache.put(r4_String, Boolean.valueOf(true));
                return true;
            }
        }
        return false;
    }
}
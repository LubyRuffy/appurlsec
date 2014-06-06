package com.tencent.mm.sdk.plugin;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;
import com.tencent.mm.sdk.platformtools.Log;
import qsbk.app.database.QsbkDatabase;

public class MMPluginProviderConstants {
    public static final String AUTHORITY = "com.tencent.mm.sdk.plugin.provider";
    public static final String PLUGIN_PACKAGE_PATTERN = "com.tencent.mm.plugin";
    public static final int TYPE_BOOLEAN = 4;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_INT = 1;
    public static final int TYPE_LONG = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_UNKNOWN = 0;

    public static final class OAuth implements BaseColumns {
        public static final String ACCESS_TOKEN = "accessToken";
        public static final String ACTION_REQUEST_TOKEN = "request_token";
        public static final String API_KEY = "apiKey";
        public static final Uri CONTENT_URI;
        public static final String REQUEST_TOKEN = "requestToken";
        public static final String SECRET = "secret";

        static {
            CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/oauth");
        }

        private OAuth() {
        }
    }

    public static final class PluginDB implements BaseColumns {
        public static final Uri CONTENT_URI;
        public static final String KEY = "key";
        public static final String TYPE = "type";
        public static final String VALUE = "value";

        static {
            CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/plugindb");
        }

        private PluginDB() {
        }
    }

    public static class PluginIntent {
        public static final String ACCESS_TOKEN = "com.tencent.mm.sdk.plugin.Intent.ACCESS_TOKEN";
        public static final String ACTION_QRCODE_SCANNED = "com.tencent.mm.sdk.plugin.Intent.ACTION_QRCODE_SCANNED";
        public static final String ACTION_REQUEST_TOKEN = "com.tencent.mm.sdk.plugin.Intent.ACTION_REQUEST_TOKEN";
        public static final String ACTION_RESPONSE = "com.tencent.mm.sdk.plugin.Intent.ACTION_RESPONSE";
        public static final String APP_PACKAGE_PATTERN = "com.tencent.mm";
        public static final String AUTH_KEY = "com.tencent.mm.sdk.plugin.Intent.AUTHKEY";
        public static final String NAME = "com.tencent.mm.sdk.plugin.Intent.NAME";
        public static final String PACKAGE = "com.tencent.mm.sdk.plugin.Intent.PACKAGE";
        public static final String PERMISSIONS = "com.tencent.mm.sdk.plugin.Intent.PERMISSIONS";
        public static final String PLUGIN_PACKAGE_PATTERN = "com.tencent.mm.plugin";
        public static final String REQUEST_TOKEN = "com.tencent.mm.sdk.plugin.Intent.REQUEST_TOKEN";
    }

    public static final class Resolver {
        private Resolver() {
        }

        public static int getType(Object r4_Object) {
            if (r4_Object == null) {
                Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
                return 0;
            } else {
                if (r4_Object instanceof Integer) {
                    return TYPE_INT;
                }
                if (r4_Object instanceof Long) {
                    return TYPE_LONG;
                }
                if (r4_Object instanceof String) {
                    return TYPE_STRING;
                }
                if (r4_Object instanceof Boolean) {
                    return TYPE_BOOLEAN;
                }
                if (r4_Object instanceof Float) {
                    return TYPE_FLOAT;
                }
                if (r4_Object instanceof Double) {
                    return TYPE_DOUBLE;
                }
                Log.e("MicroMsg.SDK.PluginProvider.Resolver", new StringBuilder("unresolve failed, unknown type=").append(r4_Object.getClass().toString()).toString());
                return 0;
            }
        }

        public static Object resolveObj(int r2i, String r3_String) {
            switch (r2i) {
                case TYPE_INT:
                    r3_String = Integer.valueOf(r3_String);
                    break;
                case TYPE_LONG:
                    r3_String = Long.valueOf(r3_String);
                    break;
                case TYPE_STRING:
                    break;
                case TYPE_BOOLEAN:
                    r3_String = Boolean.valueOf(r3_String);
                    break;
                case TYPE_FLOAT:
                    r3_String = Float.valueOf(r3_String);
                    break;
                case TYPE_DOUBLE:
                    r3_String = Double.valueOf(r3_String);
                    break;
                default:
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    r3_String = null;
                    break;
            }
            return r3_String;
        }

        public static boolean unresolveObj(ContentValues r2_ContentValues, Object r3_Object) {
            int r0i = getType(r3_Object);
            if (r0i == 0) {
                return false;
            }
            r2_ContentValues.put(QsbkDatabase.TYPE, Integer.valueOf(r0i));
            r2_ContentValues.put(com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref.VALUE, r3_Object.toString());
            return true;
        }
    }

    public static final class SharedPref implements BaseColumns {
        public static final Uri CONTENT_URI;
        public static final String KEY = "key";
        public static final String TYPE = "type";
        public static final String VALUE = "value";

        static {
            CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
        }

        private SharedPref() {
        }
    }
}
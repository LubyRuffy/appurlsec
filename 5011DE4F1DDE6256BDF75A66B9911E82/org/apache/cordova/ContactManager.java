package org.apache.cordova;

import android.os.Build.VERSION;
import android.util.Log;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactManager extends CordovaPlugin {
    public static final int INVALID_ARGUMENT_ERROR = 1;
    public static final int IO_ERROR = 4;
    private static final String LOG_TAG = "Contact Query";
    public static final int NOT_SUPPORTED_ERROR = 5;
    public static final int PENDING_OPERATION_ERROR = 3;
    public static final int PERMISSION_DENIED_ERROR = 20;
    public static final int TIMEOUT_ERROR = 2;
    public static final int UNKNOWN_ERROR = 0;
    private ContactAccessor contactAccessor;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ JSONArray val$filter;
        final /* synthetic */ JSONObject val$options;

        AnonymousClass_1(JSONArray r2_JSONArray, JSONObject r3_JSONObject, CallbackContext r4_CallbackContext) {
            this.val$filter = r2_JSONArray;
            this.val$options = r3_JSONObject;
            this.val$callbackContext = r4_CallbackContext;
        }

        public void run() {
            this.val$callbackContext.success(ContactManager.this.contactAccessor.search(this.val$filter, this.val$options));
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ JSONObject val$contact;

        AnonymousClass_2(JSONObject r2_JSONObject, CallbackContext r3_CallbackContext) {
            this.val$contact = r2_JSONObject;
            this.val$callbackContext = r3_CallbackContext;
        }

        public void run() {
            JSONObject r0_JSONObject;
            String r0_String = ContactManager.this.contactAccessor.save(this.val$contact);
            if (r0_String != null) {
                try {
                    r0_JSONObject = ContactManager.this.contactAccessor.getContactById(r0_String);
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "JSON fail.", e);
                }
            } else {
                r0_JSONObject = null;
            }
            if (r0_JSONObject != null) {
                this.val$callbackContext.success(r0_JSONObject);
            } else {
                this.val$callbackContext.sendPluginResult(new PluginResult(Status.ERROR, 0));
            }
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ String val$contactId;

        AnonymousClass_3(String r2_String, CallbackContext r3_CallbackContext) {
            this.val$contactId = r2_String;
            this.val$callbackContext = r3_CallbackContext;
        }

        public void run() {
            if (ContactManager.this.contactAccessor.remove(this.val$contactId)) {
                this.val$callbackContext.success();
            } else {
                this.val$callbackContext.sendPluginResult(new PluginResult(Status.ERROR, 0));
            }
        }
    }

    public boolean execute(String r6_String, JSONArray r7_JSONArray, CallbackContext r8_CallbackContext) throws JSONException {
        int r1i = 0;
        if (VERSION.RELEASE.startsWith("1.")) {
            r8_CallbackContext.sendPluginResult(new PluginResult(Status.ERROR, 5));
            return true;
        } else {
            if (this.contactAccessor == null) {
                this.contactAccessor = new ContactAccessorSdk5(this.webView, this.cordova);
            }
            if (r6_String.equals("search")) {
                this.cordova.getThreadPool().execute(new AnonymousClass_1(r7_JSONArray.getJSONArray(r1i), r7_JSONArray.getJSONObject(INVALID_ARGUMENT_ERROR), r8_CallbackContext));
                return true;
            } else if (r6_String.equals("save")) {
                this.cordova.getThreadPool().execute(new AnonymousClass_2(r7_JSONArray.getJSONObject(r1i), r8_CallbackContext));
                return true;
            } else {
                if (!r6_String.equals("remove")) {
                    return false;
                }
                this.cordova.getThreadPool().execute(new AnonymousClass_3(r7_JSONArray.getString(r1i), r8_CallbackContext));
                return true;
            }
        }
    }
}
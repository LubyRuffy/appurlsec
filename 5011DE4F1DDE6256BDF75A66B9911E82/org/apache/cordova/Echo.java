package org.apache.cordova;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONException;

public class Echo extends CordovaPlugin {

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ String val$result;

        AnonymousClass_1(CallbackContext r2_CallbackContext, String r3_String) {
            this.val$callbackContext = r2_CallbackContext;
            this.val$result = r3_String;
        }

        public void run() {
            this.val$callbackContext.success(this.val$result);
        }
    }

    public boolean execute(String r5_String, CordovaArgs r6_CordovaArgs, CallbackContext r7_CallbackContext) throws JSONException {
        String r0_String = null;
        if ("echo".equals(r5_String)) {
            if (r6_CordovaArgs.isNull(0)) {
                r7_CallbackContext.success(r0_String);
                return true;
            } else {
                r0_String = r6_CordovaArgs.getString(0);
                r7_CallbackContext.success(r0_String);
                return true;
            }
        } else if ("echoAsync".equals(r5_String)) {
            if (r6_CordovaArgs.isNull(0)) {
                this.cordova.getThreadPool().execute(new AnonymousClass_1(r7_CallbackContext, r0_String));
                return true;
            } else {
                r0_String = r6_CordovaArgs.getString(0);
                this.cordova.getThreadPool().execute(new AnonymousClass_1(r7_CallbackContext, r0_String));
                return true;
            }
        } else {
            if (!"echoArrayBuffer".equals(r5_String)) {
                return false;
            }
            r7_CallbackContext.success(r6_CordovaArgs.getArrayBuffer(0));
            return true;
        }
    }
}
package org.apache.cordova;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Vibrator;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import qsbk.app.widget.listview.XListViewHeader;

public class Notification extends CordovaPlugin {
    public int confirmResult;
    public ProgressDialog progressDialog;
    public ProgressDialog spinnerDialog;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ String val$buttonLabel;
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ CordovaInterface val$cordova;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        AnonymousClass_1(CordovaInterface r2_CordovaInterface, String r3_String, String r4_String, String r5_String, CallbackContext r6_CallbackContext) {
            this.val$cordova = r2_CordovaInterface;
            this.val$message = r3_String;
            this.val$title = r4_String;
            this.val$buttonLabel = r5_String;
            this.val$callbackContext = r6_CallbackContext;
        }

        public void run() {
            Builder r0_Builder = new Builder(this.val$cordova.getActivity());
            r0_Builder.setMessage(this.val$message);
            r0_Builder.setTitle(this.val$title);
            r0_Builder.setCancelable(true);
            r0_Builder.setPositiveButton(this.val$buttonLabel, new OnClickListener() {
                public void onClick(DialogInterface r5_DialogInterface, int r6i) {
                    r5_DialogInterface.dismiss();
                    AnonymousClass_1.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 0));
                }
            });
            r0_Builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface r5_DialogInterface) {
                    r5_DialogInterface.dismiss();
                    AnonymousClass_1.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 0));
                }
            });
            r0_Builder.create();
            r0_Builder.show();
        }
    }

    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ CallbackContext val$callbackContext;
        final /* synthetic */ CordovaInterface val$cordova;
        final /* synthetic */ String[] val$fButtons;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        AnonymousClass_2(CordovaInterface r2_CordovaInterface, String r3_String, String r4_String, String[] r5_StringA, CallbackContext r6_CallbackContext) {
            this.val$cordova = r2_CordovaInterface;
            this.val$message = r3_String;
            this.val$title = r4_String;
            this.val$fButtons = r5_StringA;
            this.val$callbackContext = r6_CallbackContext;
        }

        public void run() {
            Builder r0_Builder = new Builder(this.val$cordova.getActivity());
            r0_Builder.setMessage(this.val$message);
            r0_Builder.setTitle(this.val$title);
            r0_Builder.setCancelable(true);
            if (this.val$fButtons.length > 0) {
                r0_Builder.setNegativeButton(this.val$fButtons[0], new OnClickListener() {
                    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
                        r5_DialogInterface.dismiss();
                        AnonymousClass_2.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 1));
                    }
                });
            }
            if (this.val$fButtons.length > 1) {
                r0_Builder.setNeutralButton(this.val$fButtons[1], new OnClickListener() {
                    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
                        r5_DialogInterface.dismiss();
                        AnonymousClass_2.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 2));
                    }
                });
            }
            if (this.val$fButtons.length > 2) {
                r0_Builder.setPositiveButton(this.val$fButtons[2], new OnClickListener() {
                    public void onClick(DialogInterface r5_DialogInterface, int r6i) {
                        r5_DialogInterface.dismiss();
                        AnonymousClass_2.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 3));
                    }
                });
            }
            r0_Builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface r5_DialogInterface) {
                    r5_DialogInterface.dismiss();
                    AnonymousClass_2.this.val$callbackContext.sendPluginResult(new PluginResult(Status.OK, 0));
                }
            });
            r0_Builder.create();
            r0_Builder.show();
        }
    }

    class AnonymousClass_3 implements Runnable {
        final /* synthetic */ CordovaInterface val$cordova;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        AnonymousClass_3(CordovaInterface r2_CordovaInterface, String r3_String, String r4_String) {
            this.val$cordova = r2_CordovaInterface;
            this.val$title = r3_String;
            this.val$message = r4_String;
        }

        public void run() {
            Notification.this.spinnerDialog = ProgressDialog.show(this.val$cordova.getActivity(), this.val$title, this.val$message, true, true, new OnCancelListener() {
                public void onCancel(DialogInterface r3_DialogInterface) {
                    AnonymousClass_3.this.this$0.spinnerDialog = null;
                }
            });
        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ CordovaInterface val$cordova;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        AnonymousClass_4(CordovaInterface r3_CordovaInterface, String r4_String, String r5_String) {
            this.val$cordova = r3_CordovaInterface;
            this.val$title = r4_String;
            this.val$message = r5_String;
        }

        public void run() {
            Notification.this.progressDialog = new ProgressDialog(this.val$cordova.getActivity());
            Notification.this.progressDialog.setProgressStyle(1);
            Notification.this.progressDialog.setTitle(this.val$title);
            Notification.this.progressDialog.setMessage(this.val$message);
            Notification.this.progressDialog.setCancelable(true);
            Notification.this.progressDialog.setMax(100);
            Notification.this.progressDialog.setProgress(0);
            Notification.this.progressDialog.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface r3_DialogInterface) {
                    AnonymousClass_4.this.val$notification.progressDialog = null;
                }
            });
            Notification.this.progressDialog.show();
        }
    }

    public Notification() {
        this.confirmResult = -1;
        this.spinnerDialog = null;
        this.progressDialog = null;
    }

    public synchronized void activityStart(String r3_String, String r4_String) {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_3(this.cordova, r3_String, r4_String));
    }

    public synchronized void activityStop() {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
    }

    public synchronized void alert(String r8_String, String r9_String, String r10_String, CallbackContext r11_CallbackContext) {
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_1(this.cordova, r8_String, r9_String, r10_String, r11_CallbackContext));
    }

    public void beep(long r12j) {
        Ringtone r6_Ringtone = RingtoneManager.getRingtone(this.cordova.getActivity().getBaseContext(), RingtoneManager.getDefaultUri(XListViewHeader.STATE_REFRESHING));
        if (r6_Ringtone != null) {
            long r2j = 0;
            while (r2j < r12j) {
                r6_Ringtone.play();
                long r0j = 5000;
                while (r6_Ringtone.isPlaying() && r0j > 0) {
                    r0j -= 100;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                r2j = 1 + r2j;
            }
        }
    }

    public synchronized void confirm(String r8_String, String r9_String, String r10_String, CallbackContext r11_CallbackContext) {
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_2(this.cordova, r8_String, r9_String, r10_String.split(","), r11_CallbackContext));
    }

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) throws JSONException {
        int r3i = XListViewHeader.STATE_REFRESHING;
        int r1i = 0;
        if (r5_String.equals("beep")) {
            beep(r6_JSONArray.getLong(r1i));
        } else if (r5_String.equals("vibrate")) {
            vibrate(r6_JSONArray.getLong(r1i));
        } else if (r5_String.equals("alert")) {
            alert(r6_JSONArray.getString(r1i), r6_JSONArray.getString(1), r6_JSONArray.getString(r3i), r7_CallbackContext);
            return true;
        } else if (r5_String.equals("confirm")) {
            confirm(r6_JSONArray.getString(r1i), r6_JSONArray.getString(1), r6_JSONArray.getString(r3i), r7_CallbackContext);
            return true;
        } else if (r5_String.equals("activityStart")) {
            activityStart(r6_JSONArray.getString(r1i), r6_JSONArray.getString(1));
        } else if (r5_String.equals("activityStop")) {
            activityStop();
        } else if (r5_String.equals("progressStart")) {
            progressStart(r6_JSONArray.getString(r1i), r6_JSONArray.getString(1));
        } else if (r5_String.equals("progressValue")) {
            progressValue(r6_JSONArray.getInt(r1i));
        } else {
            if (!r5_String.equals("progressStop")) {
                return false;
            }
            progressStop();
        }
        r7_CallbackContext.success();
        return true;
    }

    public synchronized void progressStart(String r7_String, String r8_String) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
        this.cordova.getActivity().runOnUiThread(new AnonymousClass_4(this, this.cordova, r7_String, r8_String));
    }

    public synchronized void progressStop() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    public synchronized void progressValue(int r2i) {
        if (this.progressDialog != null) {
            this.progressDialog.setProgress(r2i);
        }
    }

    public void vibrate(long r3j) {
        if (r3j == 0) {
            r3j = 500;
        }
        ((Vibrator) this.cordova.getActivity().getSystemService("vibrator")).vibrate(r3j);
    }
}
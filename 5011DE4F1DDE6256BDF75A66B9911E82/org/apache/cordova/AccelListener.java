package org.apache.cordova;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import com.google.analytics.tracking.android.ModelFields;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.push.Utils;
import qsbk.app.utils.audit.Rotate3dAnimation;
import qsbk.app.widget.listview.XListViewHeader;

public class AccelListener extends CordovaPlugin implements SensorEventListener {
    public static int ERROR_FAILED_TO_START;
    public static int RUNNING;
    public static int STARTING;
    public static int STOPPED;
    private int accuracy;
    private CallbackContext callbackContext;
    private Sensor mSensor;
    private SensorManager sensorManager;
    private int status;
    private long timestamp;
    private float x;
    private float y;
    private float z;

    static {
        STOPPED = 0;
        STARTING = 1;
        RUNNING = 2;
        ERROR_FAILED_TO_START = 3;
    }

    public AccelListener() {
        this.accuracy = 0;
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.timestamp = 0;
        setStatus(STOPPED);
    }

    private void fail(int r4i, String r5_String) {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("code", r4i);
            r1_JSONObject.put(Utils.EXTRA_MESSAGE, r5_String);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult r0_PluginResult = new PluginResult(Status.ERROR, r1_JSONObject);
        r0_PluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(r0_PluginResult);
    }

    private JSONObject getAccelerationJSON() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(Rotate3dAnimation.ROTATE_X, (double) this.x);
            r1_JSONObject.put(Rotate3dAnimation.ROTATE_Y, (double) this.y);
            r1_JSONObject.put(ModelFields.CACHE_BUSTER, (double) this.z);
            r1_JSONObject.put(AdViewUtil.PREFS_STRING_TIMESTAMP, this.timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r1_JSONObject;
    }

    private void setStatus(int r1i) {
        this.status = r1i;
    }

    private int start() {
        if (this.status == RUNNING || this.status == STARTING) {
            return this.status;
        }
        setStatus(STARTING);
        List r0_List = this.sensorManager.getSensorList(1);
        if (r0_List == null || r0_List.size() <= 0) {
            setStatus(ERROR_FAILED_TO_START);
            fail(ERROR_FAILED_TO_START, "No sensors found to register accelerometer listening to.");
            return this.status;
        } else {
            this.mSensor = (Sensor) r0_List.get(0);
            this.sensorManager.registerListener(this, this.mSensor, XListViewHeader.STATE_REFRESHING);
            setStatus(STARTING);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    AccelListener.this.timeout();
                }
            }, 2000);
            return this.status;
        }
    }

    private void stop() {
        if (this.status != STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        setStatus(STOPPED);
        this.accuracy = 0;
    }

    private void timeout() {
        if (this.status == STARTING) {
            setStatus(ERROR_FAILED_TO_START);
            fail(ERROR_FAILED_TO_START, "Accelerometer could not be started.");
        }
    }

    private void win() {
        PluginResult r0_PluginResult = new PluginResult(Status.OK, getAccelerationJSON());
        r0_PluginResult.setKeepCallback(true);
        this.callbackContext.sendPluginResult(r0_PluginResult);
    }

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) {
        if (r5_String.equals("start")) {
            this.callbackContext = r7_CallbackContext;
            if (this.status != RUNNING) {
                start();
            }
        } else {
            if (!r5_String.equals("stop")) {
                return false;
            }
            if (this.status == RUNNING) {
                stop();
            }
        }
        PluginResult r1_PluginResult = new PluginResult(Status.NO_RESULT, RContactStorage.PRIMARY_KEY);
        r1_PluginResult.setKeepCallback(true);
        r7_CallbackContext.sendPluginResult(r1_PluginResult);
        return true;
    }

    public void initialize(CordovaInterface r3_CordovaInterface, CordovaWebView r4_CordovaWebView) {
        super.initialize(r3_CordovaInterface, r4_CordovaWebView);
        this.sensorManager = (SensorManager) r3_CordovaInterface.getActivity().getSystemService("sensor");
    }

    public void onAccuracyChanged(Sensor r3_Sensor, int r4i) {
        if (r3_Sensor.getType() == 1 && this.status != STOPPED) {
            this.accuracy = r4i;
        }
    }

    public void onDestroy() {
        stop();
    }

    public void onReset() {
        if (this.status == RUNNING) {
            stop();
        }
    }

    public void onSensorChanged(SensorEvent r5_SensorEvent) {
        if (r5_SensorEvent.sensor.getType() == 1 && this.status != STOPPED) {
            setStatus(RUNNING);
            if (this.accuracy >= 2) {
                this.timestamp = System.currentTimeMillis();
                this.x = r5_SensorEvent.values[0];
                this.y = r5_SensorEvent.values[1];
                this.z = r5_SensorEvent.values[2];
                win();
            }
        }
    }
}
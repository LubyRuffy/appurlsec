package org.apache.cordova;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.List;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.widget.listview.XListViewFooter;

public class CompassListener extends CordovaPlugin implements SensorEventListener {
    public static int ERROR_FAILED_TO_START;
    public static int RUNNING;
    public static int STARTING;
    public static int STOPPED;
    public long TIMEOUT;
    int accuracy;
    private CallbackContext callbackContext;
    float heading;
    long lastAccessTime;
    Sensor mSensor;
    private SensorManager sensorManager;
    int status;
    long timeStamp;

    static {
        STOPPED = 0;
        STARTING = 1;
        RUNNING = 2;
        ERROR_FAILED_TO_START = 3;
    }

    public CompassListener() {
        this.TIMEOUT = 30000;
        this.heading = 0.0f;
        this.timeStamp = 0;
        setStatus(STOPPED);
    }

    private JSONObject getCompassHeading() throws JSONException {
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put("magneticHeading", (double) getHeading());
        r0_JSONObject.put("trueHeading", (double) getHeading());
        r0_JSONObject.put("headingAccuracy", 0);
        r0_JSONObject.put(AdViewUtil.PREFS_STRING_TIMESTAMP, this.timeStamp);
        return r0_JSONObject;
    }

    private void setStatus(int r1i) {
        this.status = r1i;
    }

    private void timeout() {
        if (this.status == STARTING) {
            setStatus(ERROR_FAILED_TO_START);
            if (this.callbackContext != null) {
                this.callbackContext.error("Compass listener failed to start.");
            }
        }
    }

    public boolean execute(String r6_String, JSONArray r7_JSONArray, CallbackContext r8_CallbackContext) throws JSONException {
        int r1i = 0;
        if (r6_String.equals("start")) {
            start();
            return true;
        } else if (r6_String.equals("stop")) {
            stop();
            return true;
        } else if (r6_String.equals("getStatus")) {
            r8_CallbackContext.sendPluginResult(new PluginResult(Status.OK, getStatus()));
            return true;
        } else if (r6_String.equals("getHeading")) {
            if (this.status != RUNNING) {
                if (start() == ERROR_FAILED_TO_START) {
                    r8_CallbackContext.sendPluginResult(new PluginResult(Status.IO_EXCEPTION, ERROR_FAILED_TO_START));
                    return true;
                } else {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            CompassListener.this.timeout();
                        }
                    }, 2000);
                }
            }
            r8_CallbackContext.sendPluginResult(new PluginResult(Status.OK, getCompassHeading()));
            return true;
        } else if (r6_String.equals("setTimeout")) {
            setTimeout(r7_JSONArray.getLong(r1i));
            return true;
        } else {
            if (!r6_String.equals("getTimeout")) {
                return false;
            }
            r8_CallbackContext.sendPluginResult(new PluginResult(Status.OK, (float) getTimeout()));
            return true;
        }
    }

    public float getHeading() {
        this.lastAccessTime = System.currentTimeMillis();
        return this.heading;
    }

    public int getStatus() {
        return this.status;
    }

    public long getTimeout() {
        return this.TIMEOUT;
    }

    public void initialize(CordovaInterface r3_CordovaInterface, CordovaWebView r4_CordovaWebView) {
        super.initialize(r3_CordovaInterface, r4_CordovaWebView);
        this.sensorManager = (SensorManager) r3_CordovaInterface.getActivity().getSystemService("sensor");
    }

    public void onAccuracyChanged(Sensor r1_Sensor, int r2i) {
    }

    public void onDestroy() {
        stop();
    }

    public void onReset() {
        stop();
    }

    public void onSensorChanged(SensorEvent r5_SensorEvent) {
        float r0f = r5_SensorEvent.values[0];
        this.timeStamp = System.currentTimeMillis();
        this.heading = r0f;
        setStatus(RUNNING);
        if (this.timeStamp - this.lastAccessTime > this.TIMEOUT) {
            stop();
        }
    }

    public void setTimeout(long r1j) {
        this.TIMEOUT = r1j;
    }

    public int start() {
        if (this.status == RUNNING || this.status == STARTING) {
            return this.status;
        }
        List r0_List = this.sensorManager.getSensorList(XListViewFooter.STATE_NOMORE);
        if (r0_List == null || r0_List.size() <= 0) {
            setStatus(ERROR_FAILED_TO_START);
            return this.status;
        } else {
            this.mSensor = (Sensor) r0_List.get(0);
            this.sensorManager.registerListener(this, this.mSensor, XListViewFooter.STATE_NOMORE);
            this.lastAccessTime = System.currentTimeMillis();
            setStatus(STARTING);
            return this.status;
        }
    }

    public void stop() {
        if (this.status != STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        setStatus(STOPPED);
    }
}
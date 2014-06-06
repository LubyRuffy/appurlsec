package com.baidu.mobads;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mobads.a.d;
import org.json.JSONObject;
import qsbk.app.push.Utils;

class a implements Callback {
    private final AdViewListener a;
    private final AdView b;

    public a(AdViewListener r1_AdViewListener, AdView r2_AdView) {
        this.a = r1_AdViewListener;
        this.b = r2_AdView;
    }

    public boolean handleMessage(Message r7_Message) {
        JSONObject r1_JSONObject;
        try {
            Bundle r2_Bundle = r7_Message.getData();
            String r0_String = r2_Bundle.getString(Utils.RESPONSE_METHOD);
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = "AdView.setListener handleMessage";
            r1_ObjectA[1] = r2_Bundle;
            d.a(r1_ObjectA);
            if ("onAdReady".equals(r0_String)) {
                this.a.onAdReady(this.b);
                return false;
            } else if ("onAdShow".equals(r0_String)) {
                r1_JSONObject = new JSONObject();
                try {
                    r0_JSONObject = new JSONObject(r2_Bundle.getString("p_jsonInfo"));
                } catch (Exception e) {
                    d.b(e);
                    r0_JSONObject = r1_JSONObject;
                }
                this.a.onAdShow(r0_JSONObject);
                return false;
            } else if ("onAdClick".equals(r0_String)) {
                r1_JSONObject = new JSONObject();
                try {
                    r0_JSONObject = new JSONObject(r2_Bundle.getString("p_jsonInfo"));
                } catch (Exception e_2) {
                    d.b(e_2);
                    r0_JSONObject = r1_JSONObject;
                }
                this.a.onAdClick(r0_JSONObject);
                return false;
            } else if ("onAdFailed".equals(r0_String)) {
                this.a.onAdFailed(r2_Bundle.getString("p_reason"));
                return false;
            } else if ("onAdSwitch".equals(r0_String)) {
                this.a.onAdSwitch();
                return false;
            } else if ("onVideoStart".equals(r0_String)) {
                this.a.onVideoStart();
                return false;
            } else if ("onVideoFinish".equals(r0_String)) {
                this.a.onVideoFinish();
                return false;
            } else if ("onVideoError".equals(r0_String)) {
                this.a.onVideoError();
                return false;
            } else if ("onVideoClickClose".equals(r0_String)) {
                this.a.onVideoClickClose();
                return false;
            } else if ("onVideoClickAd".equals(r0_String)) {
                this.a.onVideoClickAd();
                return false;
            } else {
                if ("onVideoClickReplay".equals(r0_String)) {
                    this.a.onVideoClickReplay();
                }
                return false;
            }
        } catch (Exception e_3) {
            d.b(e_3);
        }
    }
}
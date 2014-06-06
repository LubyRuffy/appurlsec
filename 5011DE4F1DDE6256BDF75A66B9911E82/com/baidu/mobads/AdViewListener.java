package com.baidu.mobads;

import org.json.JSONObject;

public interface AdViewListener {
    public void onAdClick(JSONObject r1_JSONObject);

    public void onAdFailed(String r1_String);

    public void onAdReady(AdView r1_AdView);

    public void onAdShow(JSONObject r1_JSONObject);

    public void onAdSwitch();

    public void onVideoClickAd();

    public void onVideoClickClose();

    public void onVideoClickReplay();

    public void onVideoError();

    public void onVideoFinish();

    public void onVideoStart();
}
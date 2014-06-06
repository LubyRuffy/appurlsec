package com.tencent.tauth;

import org.json.JSONObject;

// compiled from: ProGuard
public interface IUiListener {
    public void onCancel();

    public void onComplete(JSONObject r1_JSONObject);

    public void onError(UiError r1_UiError);
}
package com.tencent.tauth;

// compiled from: ProGuard
public class UiError {
    public int errorCode;
    public String errorDetail;
    public String errorMessage;

    public UiError(int r1i, String r2_String, String r3_String) {
        this.errorMessage = r2_String;
        this.errorCode = r1i;
        this.errorDetail = r3_String;
    }
}
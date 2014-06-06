package com.google.analytics.tracking.android;

import java.io.IOException;
import java.util.Map;

public interface Tracker {
    public boolean anonymizeIpEnabled();

    public void close();

    public Map<String, String> constructException(String r1_String, boolean r2z);

    public Map<String, String> constructRawException(String r1_String, Throwable r2_Throwable, boolean r3z) throws IOException;

    public Map<String, String> constructTiming(String r1_String, long r2j, String r4_String, String r5_String);

    public Map<String, String> constructTransaction(Transaction r1_Transaction);

    public String get(String r1_String);

    public String getAppId();

    public String getAppInstallerId();

    public ExceptionParser getExceptionParser();

    public double getSampleRate();

    public String getTrackingId();

    public boolean getUseSecure();

    public void send(String r1_String, Map<String, String> r2_Map_String__String);

    public void set(String r1_String, String r2_String);

    public void setAnonymizeIp(boolean r1z);

    public void setAppId(String r1_String);

    public void setAppInstallerId(String r1_String);

    public void setAppName(String r1_String);

    public void setAppScreen(String r1_String);

    public void setAppVersion(String r1_String);

    public void setCampaign(String r1_String);

    public void setExceptionParser(ExceptionParser r1_ExceptionParser);

    public void setReferrer(String r1_String);

    public void setSampleRate(double r1d);

    public void setStartSession(boolean r1z);

    public void setUseSecure(boolean r1z);

    public void trackEvent(String r1_String, String r2_String, String r3_String, Long r4_Long);

    public void trackException(String r1_String, Throwable r2_Throwable, boolean r3z);

    public void trackException(String r1_String, boolean r2z);

    public void trackTiming(String r1_String, long r2j, String r4_String, String r5_String);

    public void trackTransaction(Transaction r1_Transaction);

    public void trackView();

    public void trackView(String r1_String);
}
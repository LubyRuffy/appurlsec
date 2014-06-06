package com.tencent.mm.sdk.plugin;

import com.tencent.mm.sdk.channel.MMessage.CallBack;

public interface IMMPluginAPI {
    public boolean appendNetStat(int r1i, int r2i, int r3i);

    public void createMsgController(String r1_String);

    public void createQRCodeController(String r1_String);

    public void createQRCodeController(String r1_String, CallBack r2_CallBack);

    public void createQRCodeController(String r1_String, CallBack r2_CallBack, String r3_String);

    public Profile getCurrentProfile(String r1_String);

    public String getPluginKey(String r1_String);

    public boolean installPlugin(String r1_String);

    public boolean isPluginInstalled(String r1_String);

    public void jumpToBindEmail(String r1_String);

    public void jumpToBindMobile(String r1_String);

    public void jumpToBindQQ(String r1_String);

    public void jumpToChattingUI(String r1_String, String r2_String);

    public void jumpToSettingView(String r1_String, String r2_String);

    public boolean registerAutoMsg(String r1_String, String r2_String);

    public boolean registerPattern(String r1_String, CallBack r2_CallBack, String r3_String);

    public boolean registerQRCodePattern(String r1_String, CallBack r2_CallBack, String r3_String);

    public void release();

    public boolean sendMsgNotify(String r1_String, String r2_String, int r3i, String r4_String, Class<?> r5_Class_);

    public boolean unregisterAutoMsg(String r1_String, String r2_String);
}
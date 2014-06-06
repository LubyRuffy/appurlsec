package com.qq.e.v2.a;

import com.qq.e.v2.constants.Constants.KEYS;
import com.qq.e.v2.managers.plugin.PM;
import com.qq.e.v2.managers.setting.SM;
import com.qq.e.v2.net.GDTSecurityADNetRequest;
import com.qq.e.v2.net.GDTSecurityADNetRequest.CallBack;
import com.qq.e.v2.net.GDTSecurityADNetResponse;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.StringUtil;
import com.tencent.tauth.Constants;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

final class b implements CallBack {
    private /* synthetic */ SM a;
    private /* synthetic */ PM b;

    b(a r1_a, SM r2_SM, PM r3_PM) {
        this.a = r2_SM;
        this.b = r3_PM;
    }

    public final void onErr(Exception r2_Exception) {
        GDTLogger.e("ActivateError", r2_Exception);
    }

    public final void onResponse(GDTSecurityADNetRequest r6_GDTSecurityADNetRequest, GDTSecurityADNetResponse r7_GDTSecurityADNetResponse) {
        try {
            if (r7_GDTSecurityADNetResponse.getStatusCode() == 200) {
                String r0_String = r7_GDTSecurityADNetResponse.getContentAsString();
                GDTLogger.d(new StringBuilder("ACTIVERESPONSE:").append(r0_String).toString());
                if (StringUtil.isEmpty(r0_String)) {
                    GDTLogger.report("SDK Server response empty string,maybe zip or tea format error");
                } else {
                    JSONObject r1_JSONObject = new JSONObject(r0_String);
                    int r0i = -1;
                    if (r1_JSONObject.has(KEYS.RET)) {
                        r0i = r1_JSONObject.getInt(KEYS.RET);
                    }
                    if (r0i != 0) {
                        GDTLogger.e(new StringBuilder("Response Error,retCode=").append(r0i).toString());
                    } else {
                        if (r1_JSONObject.has(KEYS.SUID)) {
                            r0_String = r1_JSONObject.getString(KEYS.SUID);
                            if (!StringUtil.isEmpty(r0_String)) {
                                this.a.updateSUID(r0_String);
                            }
                        }
                        if (r1_JSONObject.has(KEYS.SID)) {
                            r0_String = r1_JSONObject.getString(KEYS.SID);
                            if (!StringUtil.isEmpty(r0_String)) {
                                this.a.updateSID(r0_String);
                            }
                        }
                        if (r1_JSONObject.has(KEYS.SIG)) {
                            JSONObject r0_JSONObject = r1_JSONObject.getJSONObject(KEYS.SIG);
                            if (r1_JSONObject.has(KEYS.SETTING)) {
                                r1_JSONObject = r1_JSONObject.getJSONObject(KEYS.SETTING);
                                String r2_String;
                                if (r1_JSONObject.has(KEYS.APPINFO) && r0_JSONObject.has(KEYS.APPINFO)) {
                                    r2_String = r1_JSONObject.getString(KEYS.APPINFO);
                                    this.a.updateDEVCloudSetting(r0_JSONObject.getString(KEYS.APPINFO), r2_String);
                                    if (r1_JSONObject.has(KEYS.SDKINFO) && r0_JSONObject.has(KEYS.SDKINFO)) {
                                        r2_String = r1_JSONObject.getString(KEYS.SDKINFO);
                                        this.a.updateSDKCloudSetting(r0_JSONObject.getString(KEYS.SDKINFO), r2_String);
                                        if (r1_JSONObject.has(KEYS.CTXTSETTING)) {
                                            this.a.updateContextSetting(r1_JSONObject.getString(KEYS.CTXTSETTING));
                                        } else {
                                            this.a.updateContextSetting(null);
                                        }
                                    } else if (r1_JSONObject.has(KEYS.CTXTSETTING)) {
                                        this.a.updateContextSetting(r1_JSONObject.getString(KEYS.CTXTSETTING));
                                    } else {
                                        this.a.updateContextSetting(null);
                                    }
                                } else if (r1_JSONObject.has(KEYS.SDKINFO) || r0_JSONObject.has(KEYS.SDKINFO)) {
                                    if (r1_JSONObject.has(KEYS.CTXTSETTING)) {
                                        this.a.updateContextSetting(r1_JSONObject.getString(KEYS.CTXTSETTING));
                                    } else {
                                        this.a.updateContextSetting(null);
                                    }
                                } else {
                                    r2_String = r1_JSONObject.getString(KEYS.SDKINFO);
                                    this.a.updateSDKCloudSetting(r0_JSONObject.getString(KEYS.SDKINFO), r2_String);
                                    if (r1_JSONObject.has(KEYS.CTXTSETTING)) {
                                        this.a.updateContextSetting(null);
                                    } else {
                                        this.a.updateContextSetting(r1_JSONObject.getString(KEYS.CTXTSETTING));
                                    }
                                }
                            }
                            if (r0_JSONObject.has(KEYS.JAR) && r0_JSONObject.has(Constants.PARAM_URL)) {
                                this.b.update(r0_JSONObject.getString(KEYS.JAR), r0_JSONObject.getString(Constants.PARAM_URL));
                            }
                        }
                    }
                }
            } else {
                GDTLogger.report(new StringBuilder("SDK server response code error while launch or activate,code:").append(r7_GDTSecurityADNetResponse.getStatusCode()).toString());
            }
        } catch (IOException e) {
            GDTLogger.e("ActivateError", e);
        } catch (JSONException e_2) {
            GDTLogger.report("Parse Active or launch response exception", e_2);
        }
    }
}
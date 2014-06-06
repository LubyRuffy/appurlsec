package com.tencent.mm.sdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import com.tencent.mm.algorithm.MD5;
import com.tencent.mm.sdk.MMSharedPreferences;
import com.tencent.mm.sdk.channel.ConstantsMMessage;
import com.tencent.mm.sdk.channel.MMessage;
import com.tencent.mm.sdk.channel.MMessageAct;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.openapi.GetMessageFromWX.Req;
import com.tencent.mm.sdk.openapi.SendAuth.Resp;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

final class a implements IWXAPI {
    private Context a;
    private String b;
    private boolean c;

    protected a(Context r2_Context, String r3_String) {
        this(r2_Context, r3_String, false);
    }

    protected a(Context r2_Context, String r3_String, boolean r4z) {
        this.c = false;
        this.a = r2_Context;
        this.b = r3_String;
        this.c = r4z;
    }

    private boolean a_(String r3_String) {
        if (this.c) {
            try {
                return a(this.a.getPackageManager().getPackageInfo(r3_String, RContact.MM_CONTACTFLAG_FAVOURCONTACT).signatures);
            } catch (NameNotFoundException e) {
                return false;
            }
        } else {
            Log.d("MicroMsg.SDK.WXApiImplV10", "ignore wechat app signature validation");
            return true;
        }
    }

    private static boolean a_(byte[] r4_byteA, byte[] r5_byteA) {
        if (r4_byteA == null || r4_byteA.length == 0 || r5_byteA == null || r5_byteA.length == 0) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, invalid arguments");
            return false;
        } else if (r4_byteA.length != r5_byteA.length) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, length is different");
            return false;
        } else {
            int r0i = 0;
            while (r0i < r4_byteA.length) {
                if (r4_byteA[r0i] != r5_byteA[r0i]) {
                    return false;
                }
                r0i++;
            }
            return true;
        }
    }

    private boolean a_(Signature[] r9_SignatureA) {
        if (this.c) {
            int r3i = r9_SignatureA.length;
            int r2i = 0;
            while (r2i < r3i) {
                String r4_String = r9_SignatureA[r2i].toCharsString();
                Log.d("MicroMsg.SDK.WXApiImplV10", new StringBuilder("check signature:").append(r4_String).toString());
                if (r4_String.equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    Log.d("MicroMsg.SDK.WXApiImplV10", "pass");
                    return true;
                } else {
                    r2i++;
                }
            }
            return false;
        } else {
            Log.d("MicroMsg.SDK.WXApiImplV10", "ignore wechat app signature validation");
            return true;
        }
    }

    public final int getWXAppSupportAPI() {
        int r0i = 0;
        if (isWXAppInstalled()) {
            return new MMSharedPreferences(this.a).getInt("_build_info_sdk_int_", r0i);
        }
        Log.e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
        return 0;
    }

    public final boolean handleIntent(Intent r8_Intent, IWXAPIEventHandler r9_IWXAPIEventHandler) {
        int r2i;
        String r2_String;
        if (r8_Intent == null) {
            r2i = 0;
        } else {
            r2_String = r8_Intent.getStringExtra(ConstantsAPI.WX_TOKEN_KEY);
            r2i = (r2_String == null || (!r2_String.equals(ConstantsAPI.WX_TOKEN_VALUE))) ? 0 : 1;
        }
        if (r2i == 0) {
            return false;
        }
        r2_String = r8_Intent.getStringExtra(ConstantsMMessage.CONTENT);
        int r3i = r8_Intent.getIntExtra(ConstantsMMessage.SDK_VERSION, 0);
        String r4_String = r8_Intent.getStringExtra(ConstantsMMessage.APP_PACKAGE);
        if (r4_String == null || r4_String.length() == 0) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "invalid argument");
            return false;
        } else {
            byte[] r5_byteA = r8_Intent.getByteArrayExtra(ConstantsMMessage.CHECK_SUM);
            StringBuffer r6_StringBuffer = new StringBuffer();
            if (r2_String != null) {
                r6_StringBuffer.append(r2_String);
            }
            r6_StringBuffer.append(r3i);
            r6_StringBuffer.append(r4_String);
            r6_StringBuffer.append("mMcShCsTr");
            if (a(r5_byteA, MD5.getMessageDigest(r6_StringBuffer.toString().substring(1, REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY).getBytes()).getBytes())) {
                switch (r8_Intent.getIntExtra("_wxapi_command_type", 0)) {
                    case XListViewHeader.STATE_READY:
                        r9_IWXAPIEventHandler.onResp(new Resp(r8_Intent.getExtras()));
                        return true;
                    case XListViewHeader.STATE_REFRESHING:
                        r9_IWXAPIEventHandler.onResp(new SendMessageToWX.Resp(r8_Intent.getExtras()));
                        return true;
                    case XListViewFooter.STATE_NOMORE:
                        r9_IWXAPIEventHandler.onReq(new Req(r8_Intent.getExtras()));
                        return true;
                    case XListViewFooter.STATE_NODATA:
                        r9_IWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(r8_Intent.getExtras()));
                        return true;
                }
                return false;
            } else {
                Log.e("MicroMsg.SDK.WXApiImplV10", "checksum fail");
                return false;
            }
        }
    }

    public final boolean isWXAppInstalled() {
        boolean r0z = false;
        try {
            PackageInfo r1_PackageInfo = this.a.getPackageManager().getPackageInfo(PluginIntent.APP_PACKAGE_PATTERN, RContact.MM_CONTACTFLAG_FAVOURCONTACT);
            if (r1_PackageInfo == null) {
                return r0z;
            }
            r0z = a(r1_PackageInfo.signatures);
            return r0z;
        } catch (NameNotFoundException e) {
        }
    }

    public final boolean isWXAppSupportAPI() {
        return getWXAppSupportAPI() >= 553844737;
    }

    public final boolean openWXApp() {
        if (isWXAppInstalled()) {
            try {
                this.a.startActivity(this.a.getPackageManager().getLaunchIntentForPackage(PluginIntent.APP_PACKAGE_PATTERN));
                return true;
            } catch (Exception e) {
                Log.e("MicroMsg.SDK.WXApiImplV10", new StringBuilder("startActivity fail, exception = ").append(e.getMessage()).toString());
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return false;
        }
    }

    public final boolean registerApp(String r6_String) {
        if (a(PluginIntent.APP_PACKAGE_PATTERN)) {
            if (r6_String != null) {
                this.b = r6_String;
            }
            Log.d("MicroMsg.SDK.WXApiImplV10", new StringBuilder("register app ").append(this.a.getPackageName()).toString());
            MMessage.send(this.a, PluginIntent.APP_PACKAGE_PATTERN, ConstantsAPI.ACTION_HANDLE_APP_REGISTER, new StringBuilder("weixin://registerapp?appid=").append(this.b).toString());
            return true;
        } else {
            Log.e("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        }
    }

    public final boolean sendReq(BaseReq r5_BaseReq) {
        if (a(PluginIntent.APP_PACKAGE_PATTERN)) {
            if (r5_BaseReq.a()) {
                Bundle r0_Bundle = new Bundle();
                r5_BaseReq.toBundle(r0_Bundle);
                return MMessageAct.sendToWx(this.a, new StringBuilder("weixin://sendreq?appid=").append(this.b).toString(), r0_Bundle);
            } else {
                Log.e("MicroMsg.SDK.WXApiImplV10", "sendReq checkArgs fail");
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXApiImplV10", "sendReq failed for wechat app signature check failed");
            return false;
        }
    }

    public final boolean sendResp(BaseResp r5_BaseResp) {
        if (a(PluginIntent.APP_PACKAGE_PATTERN)) {
            if (r5_BaseResp.a()) {
                Bundle r0_Bundle = new Bundle();
                r5_BaseResp.toBundle(r0_Bundle);
                return MMessageAct.sendToWx(this.a, new StringBuilder("weixin://sendresp?appid=").append(this.b).toString(), r0_Bundle);
            } else {
                Log.e("MicroMsg.SDK.WXApiImplV10", "sendResp checkArgs fail");
                return false;
            }
        } else {
            Log.e("MicroMsg.SDK.WXApiImplV10", "sendResp failed for wechat app signature check failed");
            return false;
        }
    }

    public final void unregisterApp() {
        if (a(PluginIntent.APP_PACKAGE_PATTERN)) {
            if (this.b == null || this.b.length() == 0) {
                Log.e("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
            } else {
                Log.d("MicroMsg.SDK.WXApiImplV10", new StringBuilder("unregister app ").append(this.a.getPackageName()).toString());
                MMessage.send(this.a, PluginIntent.APP_PACKAGE_PATTERN, ConstantsAPI.ACTION_HANDLE_APP_UNREGISTER, new StringBuilder("weixin://unregisterapp?appid=").append(this.b).toString());
            }
        } else {
            Log.e("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
        }
    }
}
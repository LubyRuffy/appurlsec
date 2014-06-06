package com.tencent.mm.sdk.plugin;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import com.tencent.mm.algorithm.Base64;
import com.tencent.mm.sdk.ConstantsUI.Contact;
import com.tencent.mm.sdk.channel.MMessage.CallBack;
import com.tencent.mm.sdk.channel.MMessage.Receiver;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginDB;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.Resolver;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import qsbk.app.database.QsbkDatabase;

public class MMPluginAPIImpl implements IMMPluginAPI {
    private static final String[] d;
    private final Context a;
    private Receiver b;
    private String c;

    static {
        String[] r0_StringA = new String[3];
        r0_StringA[0] = SharedPref.KEY;
        r0_StringA[1] = QsbkDatabase.TYPE;
        r0_StringA[2] = SharedPref.VALUE;
        d = r0_StringA;
    }

    public MMPluginAPIImpl(Context r2_Context) {
        this.a = r2_Context;
        this.c = r2_Context.getPackageName();
    }

    private Object a(String r7_String) {
        String r3_String = null;
        ContentResolver r0_ContentResolver = this.a.getContentResolver();
        Uri r1_Uri = PluginDB.CONTENT_URI;
        String[] r2_StringA = d;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = r7_String;
        Cursor r0_Cursor = r0_ContentResolver.query(r1_Uri, r2_StringA, null, r4_StringA, null);
        if (r0_Cursor == null) {
            return r3_String;
        }
        int r1i = r0_Cursor.getColumnIndex(QsbkDatabase.TYPE);
        int r2i = r0_Cursor.getColumnIndex(SharedPref.VALUE);
        if (r0_Cursor.moveToFirst()) {
            r3_String = Resolver.resolveObj(r0_Cursor.getInt(r1i), r0_Cursor.getString(r2i));
        }
        r0_Cursor.close();
        return r3_String;
    }

    private void a(String r4_String, String r5_String, Intent r6_Intent) {
        Intent r0_Intent = new Intent(r5_String);
        r0_Intent.setClassName(PluginIntent.APP_PACKAGE_PATTERN, "com.tencent.mm.plugin.PluginProxyUI");
        if (r6_Intent != null) {
            r0_Intent.putExtras(r6_Intent);
        }
        r0_Intent.putExtra(PluginIntent.ACCESS_TOKEN, r4_String);
        this.a.startActivity(r0_Intent);
    }

    public boolean appendNetStat(int r3i, int r4i, int r5i) {
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(this.c).append("/action.appnetstat?recv=").append(r3i).append("&send=").append(r4i).append("&type=").append(r5i).toString()));
    }

    public void createMsgController(String r1_String) {
    }

    public void createQRCodeController(String r3_String) {
        a(r3_String, "qrcode", null);
    }

    public void createQRCodeController(String r2_String, CallBack r3_CallBack) {
        createQRCodeController(r2_String, r3_CallBack, "*");
    }

    public void createQRCodeController(String r4_String, CallBack r5_CallBack, String r6_String) {
        a(r4_String, "qrcode", new Intent().putExtra("qrcode_pattern", r6_String));
    }

    public Profile getCurrentProfile(String r7_String) {
        Profile r3_Profile = null;
        ContentResolver r0_ContentResolver = this.a.getContentResolver();
        Uri r1_Uri = Profile.CONTENT_URI;
        String[] r2_StringA = Profile.columns;
        String[] r4_StringA = new String[1];
        r4_StringA[0] = r7_String;
        Cursor r0_Cursor = r0_ContentResolver.query(r1_Uri, r2_StringA, null, r4_StringA, null);
        if (r0_Cursor == null) {
            Log.e("MicroMsg.SDK.MMPluginMgrImpl", "get current profile failed");
        } else {
            if (r0_Cursor.moveToFirst()) {
                r3_Profile = new Profile();
                r3_Profile.convertFrom(r0_Cursor);
            }
            r0_Cursor.close();
        }
        return r3_Profile;
    }

    public String getPluginKey(String r3_String) {
        return (String) a(new StringBuilder("plugindb://").append(r3_String).append("/comm.pluginkey").toString());
    }

    public boolean installPlugin(String r3_String) {
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(r3_String).append("/action.install").toString()));
    }

    public boolean isPluginInstalled(String r3_String) {
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(r3_String).append("/comm.installed").toString()));
    }

    public void jumpToBindEmail(String r3_String) {
        a(r3_String, "bindemail", new Intent());
    }

    public void jumpToBindMobile(String r3_String) {
        a(r3_String, "bindmobile", new Intent());
    }

    public void jumpToBindQQ(String r3_String) {
        a(r3_String, "bindqq", new Intent());
    }

    public void jumpToChattingUI(String r4_String, String r5_String) {
        a(r4_String, "chatting", new Intent().putExtra(Contact.KUser, r5_String).setFlags(67108864));
    }

    public void jumpToSettingView(String r4_String, String r5_String) {
        a(r4_String, "profile", new Intent().putExtra(Contact.KUser, r5_String).setFlags(67108864));
    }

    public boolean registerAutoMsg(String r3_String, String r4_String) {
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(this.c).append("/action.automsg?pluginMsgUsername=").append(r3_String).append("&pluginUsername=").append(r4_String).toString()));
    }

    public boolean registerPattern(String r2_String, CallBack r3_CallBack, String r4_String) {
        return false;
    }

    public boolean registerQRCodePattern(String r5_String, CallBack r6_CallBack, String r7_String) {
        if (this.b != null) {
            this.a.unregisterReceiver(this.b);
        }
        this.b = new Receiver(r6_CallBack);
        this.a.registerReceiver(this.b, new IntentFilter(PluginIntent.ACTION_QRCODE_SCANNED));
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(r5_String).append("/action.register_qrcode_result?pattern=").append(r7_String).toString()));
    }

    public void release() {
        Log.d("MicroMsg.SDK.MMPluginMgrImpl", "release plugin mgr implemetation");
        if (this.b != null) {
            this.a.unregisterReceiver(this.b);
            Log.d("MicroMsg.SDK.MMPluginMgrImpl", "unregister qrcode scan result receiver");
        }
    }

    public boolean sendMsgNotify(String r5_String, String r6_String, int r7i, String r8_String, Class<?> r9_Class_) {
        boolean r0z = false;
        return r9_Class_ == null ? false : Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(this.c).append("/action.msgnotify?username=").append(r5_String).append("&msgContent=").append(r6_String).append("&msgType=").append(r7i).append("&customNotify=").append(r8_String).append("&intentUri=").append(Base64.encodeToString(new Intent(this.a, r9_Class_).toUri(r0z).getBytes(), r0z)).toString()));
    }

    public boolean unregisterAutoMsg(String r3_String, String r4_String) {
        return Util.nullAsFalse((Boolean) a(new StringBuilder("plugindb://").append(this.c).append("/action.unautomsg?pluginMsgUsername=").append(r3_String).append("&pluginUsername=").append(r4_String).toString()));
    }
}
package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.cordova.NetworkManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import qsbk.app.About;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.UserSetting;
import qsbk.app.activity.base.IScrollView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.BrightnessSetting;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class UserMenuLayoutAdapter extends MenuLayoutAdapter {
    Handler e;
    private int f;
    private Handler g;

    public UserMenuLayoutAdapter() {
        this.f = 0;
        this.e = new h(this);
        this.g = new o(this);
    }

    private String a(String r3_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if ("auto".equals(r3_String) || RContactStorage.PRIMARY_KEY.equals(r3_String)) {
            r0_String = "\u81ea\u52a8";
            if (!NetworkManager.WIFI.equals(r3_String)) {
                r0_String = "WIFI";
            }
            return "textonly".equals(r3_String) ? "\u70b9\u51fb" : r0_String;
        } else if (NetworkManager.WIFI.equals(r3_String)) {
            if ("textonly".equals(r3_String)) {
            }
        } else {
            r0_String = "WIFI";
            if ("textonly".equals(r3_String)) {
            }
        }
    }

    private void a(int r3i, int r4i) {
        Message r0_Message = new Message();
        r0_Message.what = r3i;
        r0_Message.arg1 = r4i;
        this.g.sendMessage(r0_Message);
    }

    private void b() {
        new i(this, "bqk-UserMenu1").start();
    }

    private Builder c() {
        return new BrightnessSetting(this.a).createDialog();
    }

    private Builder d() {
        Builder r1_Builder = new Builder(this.a);
        View r2_View = LayoutInflater.from(QsbkApp.mContext).inflate(R.layout.update_dialog_message, null);
        ((TextView) r2_View.findViewById(R.id.updateMessage)).setText(Constants.change);
        r1_Builder.setView(r2_View);
        r1_Builder.setTitle("\u8f6f\u4ef6\u7248\u672c\u66f4\u65b0");
        r1_Builder.setPositiveButton("\u4ee5\u540e\u518d\u8bf4", new l(this)).setNegativeButton("\u7acb\u5373\u4e0b\u8f7d", new j(this));
        return r1_Builder;
    }

    private Builder e() {
        int r0i;
        int r1i = 1;
        int r2i = 0;
        r0i = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay").equals(NetworkManager.WIFI) ? 1 : 0;
        Builder r3_Builder = new Builder(new ContextThemeWrapper(this.a, 2131493154)).setTitle("\u9009\u62e9\u56fe\u7247\u52a0\u8f7d\u65b9\u5f0f");
        CharSequence[] r4_CharSequenceA = new CharSequence[2];
        r4_CharSequenceA[r2i] = "\u603b\u662f\u81ea\u52a8\u52a0\u8f7d";
        r4_CharSequenceA[r1i] = "\u4ec5\u5728WIFI\u73af\u5883\u4e2d\u81ea\u52a8\u52a0\u8f7d";
        return r3_Builder.setSingleChoiceItems(r4_CharSequenceA, r0i, new n(this)).setNegativeButton("\u53d6\u6d88", new m(this));
    }

    protected String a() {
        return a(SharePreferenceUtils.getSharePreferencesValue("imageLoadWay"));
    }

    protected boolean a(int r6i) {
        boolean r0z = true;
        Intent r2_Intent = new Intent();
        switch (r6i) {
            case XListViewHeader.STATE_NORMAL:
                c().show();
                return false;
            case XListViewHeader.STATE_READY:
                e().show();
                return false;
            case XListViewFooter.STATE_NOMORE:
                if (QsbkApp.currentUser == null) {
                    a(UserSetting.class);
                    return true;
                } else if (this.f == 3) {
                    ((IScrollView) this.a).getScrollView().clickMenuBtn();
                    return false;
                } else {
                    ((IScrollView) this.a).getScrollView().clickMenuItem();
                    r2_Intent.setClass(this.a, UserSetting.class);
                    a(r2_Intent);
                    return false;
                }
            case ShareUtils.SHARE_SMS:
                if (this.f == 5) {
                    ((IScrollView) this.a).getScrollView().clickMenuBtn();
                    return false;
                } else if (HttpUtils.isNetworkConnected(QsbkApp.mContext)) {
                    ((IScrollView) this.a).getScrollView().clickMenuItem();
                    r2_Intent.setClass(this.a, About.class);
                    r2_Intent.putExtra("targetPage", "feedback");
                    a(r2_Intent);
                    return false;
                } else {
                    Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, r0z).show();
                }
                break;
            case ShareUtils.SHARE_COPY:
                Toast.makeText(QsbkApp.mContext, "\u68c0\u6d4b\u65b0\u7248\u672c\uff0c\u8bf7\u7a0d\u5019...", 0).show();
                b();
                return false;
            case ShareUtils.SHARE_COLLECT:
                if (this.f == 7) {
                    ((IScrollView) this.a).getScrollView().clickMenuBtn();
                    return false;
                } else {
                    ((IScrollView) this.a).getScrollView().clickMenuItem();
                    r2_Intent.setClass(this.a, About.class);
                    r2_Intent.putExtra("targetPage", "about");
                    a(r2_Intent);
                    return false;
                }
        }
        return false;
    }

    protected boolean b(int r2i) {
        switch (r2i) {
            case XListViewHeader.STATE_NORMAL:
            case XListViewHeader.STATE_READY:
            case ShareUtils.SHARE_COPY:
                return false;
        }
        return true;
    }

    protected boolean e(int r2i) {
        return r2i == 1;
    }

    public void loadLatestVersion(String r10_String) {
        HttpClient r0_HttpClient = new DefaultHttpClient();
        HttpUriRequest r1_HttpUriRequest = new HttpGet(r10_String);
        r1_HttpUriRequest.addHeader("User-Agent", "qiushibalke_" + Constants.localVersionName);
        try {
            HttpEntity r0_HttpEntity = r0_HttpClient.execute(r1_HttpUriRequest).getEntity();
            float r2f = (float) r0_HttpEntity.getContentLength();
            InputStream r3_InputStream = r0_HttpEntity.getContent();
            FileOutputStream r0_FileOutputStream = null;
            if (r3_InputStream != null) {
                r0_FileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), r10_String.substring(r10_String.lastIndexOf("/"), r10_String.length())));
                byte[] r4_byteA = new byte[1024];
                float r1f = 0.0f;
                while (true) {
                    int r5i = r3_InputStream.read(r4_byteA);
                    if (r5i != -1) {
                        r0_FileOutputStream.write(r4_byteA, 0, r5i);
                        r1f += (float) r5i;
                        a(1, (int) ((100.0f * r1f) / r2f));
                    }
                }
            }
            a(XListViewHeader.STATE_REFRESHING, 0);
            r0_FileOutputStream.flush();
            if (r0_FileOutputStream != null) {
                r0_FileOutputStream.close();
            }
        } catch (Exception e) {
            a(-1, 0);
        }
    }
}
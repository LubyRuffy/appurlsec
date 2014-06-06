package qsbk.app.service;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class VersionCheckService extends Service {
    public static final String CANCLE_UDATE_TIME = "cancleUpdateTime";
    public static final String CANCLE_UDATE_VERSION = "cancleUpdateVersion";
    private Handler a;
    private Handler b;

    public VersionCheckService() {
        this.a = new i(this);
        this.b = new o(this);
    }

    private void a(int r3i, int r4i) {
        Message r0_Message = new Message();
        r0_Message.what = r3i;
        r0_Message.arg1 = r4i;
        this.b.sendMessage(r0_Message);
    }

    private static boolean b() {
        long r2j;
        long r4j = 0;
        String r2_String = SharePreferenceUtils.getSharePreferencesValue(CANCLE_UDATE_TIME);
        r2j = TextUtils.isEmpty(r2_String) ? 0 : Long.valueOf(r2_String).longValue();
        if (r2j > r4j) {
            if (r2j + 172800000 >= System.currentTimeMillis()) {
                return false;
            }
            SharePreferenceUtils.setSharePreferencesValue(CANCLE_UDATE_TIME, String.valueOf(System.currentTimeMillis()));
            return true;
        } else if (r2j == -1) {
            return Constants.serverVersion > SharePreferenceUtils.getSharePreferencesIntValue(CANCLE_UDATE_VERSION);
        } else {
            if (Constants.serverVersion <= Constants.localVersion) {
                return false;
            }
            return true;
        }
    }

    private void c() {
        Builder r1_Builder = new Builder(QsbkApp.mContext);
        View r2_View = LayoutInflater.from(QsbkApp.mContext).inflate(R.layout.update_dialog_message, null);
        ((TextView) r2_View.findViewById(R.id.updateMessage)).setText(Constants.change);
        r1_Builder.setView(r2_View);
        r1_Builder.setTitle("\u65b0\u7248\u672c\uff1a" + Constants.serviceVersionName);
        r1_Builder.setNegativeButton("\u8df3\u8fc7\u6b64\u7248\u672c", new n(this)).setPositiveButton("\u7acb\u5373\u4e0b\u8f7d", new l(this)).setNeutralButton("\u7a0d\u540e\u63d0\u9192", new k(this));
        AlertDialog r0_AlertDialog = r1_Builder.create();
        r0_AlertDialog.getWindow().setType(2008);
        r0_AlertDialog.getWindow().setType(2003);
        r0_AlertDialog.setCanceledOnTouchOutside(false);
        r0_AlertDialog.show();
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
            r3_InputStream.close();
        } catch (Exception e) {
            a(-1, 0);
        }
    }

    public IBinder onBind(Intent r2_Intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onStart(Intent r3_Intent, int r4i) {
        super.onStart(r3_Intent, r4i);
        new j(this, "qbk-VerCheck1").start();
    }

    public int onStartCommand(Intent r2_Intent, int r3i, int r4i) {
        return super.onStartCommand(r2_Intent, r3i, r4i);
    }
}
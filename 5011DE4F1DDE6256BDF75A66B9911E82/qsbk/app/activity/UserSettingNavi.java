package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import qsbk.app.manager.PushMessageManager;
import qsbk.app.utils.Base64;
import qsbk.app.utils.BrightnessSetting;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewHeader;

public class UserSettingNavi extends Activity implements OnClickListener {
    private static float[] o;
    private static final String[] p;
    Handler a;
    private ImageButton b;
    private TextView c;
    private TextView d;
    private TextView e;
    private List<File> f;
    private CheckBox g;
    private boolean h;
    private CheckBox i;
    private boolean j;
    private CheckBox k;
    private boolean l;
    private boolean m;
    private Handler n;

    static {
        o = null;
        String[] r0_StringA = new String[2];
        r0_StringA[0] = "\u6b63\u5e38";
        r0_StringA[1] = "\u52a0\u5927";
        p = r0_StringA;
    }

    public UserSettingNavi() {
        this.f = new ArrayList();
        this.h = false;
        this.j = false;
        this.l = true;
        this.m = true;
        this.a = new dy(this);
        this.n = new ed(this);
    }

    private long a(List<File> r6_List_File) {
        File r2_File = Utils.getExternalCacheDir(this);
        long r0j = 0;
        return (r2_File == null || r2_File.getAbsolutePath().equalsIgnoreCase(getCacheDir().getAbsolutePath())) ? 0 : r0j + FileUtils.getFileSize(r2_File, r6_List_File);
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
        this.n.sendMessage(r0_Message);
    }

    private void a(Context r4_Context) throws ActivityNotFoundException {
        a(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=qsbk.app")));
    }

    private void b(List<File> r4_List_File) {
        if (r4_List_File != null) {
            Iterator r1_Iterator = r4_List_File.iterator();
            while (r1_Iterator.hasNext()) {
                File r0_File = (File) r1_Iterator.next();
                if (r0_File.isFile()) {
                    r0_File.delete();
                }
            }
        }
    }

    private void c() {
        findViewById(R.id.rightBtn).setVisibility(Base64.DONT_BREAK_LINES);
        this.b = (ImageButton) findViewById(R.id.leftBtn);
        this.b.setOnTouchListener(QsbkApp.TouchDark);
        if (UIHelper.isNightTheme()) {
            this.b.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.b.setBackgroundResource(R.drawable.icon_close_large);
        }
        ((TextView) findViewById(R.id.title)).setText("\u8bbe\u7f6e");
        this.c = (TextView) findViewById(R.id.loadway);
        this.c.setText(a(SharePreferenceUtils.getSharePreferencesValue("imageLoadWay")));
        this.d = (TextView) findViewById(R.id.textSizeCurrent);
        this.d.setText(i());
        this.e = (TextView) findViewById(R.id.cacheSize);
        long r1j = a(this.f);
        if (r1j <= 0) {
            this.e.setText("0M");
        } else {
            CharSequence r0_CharSequence = "0M";
            try {
                Object[] r4_ObjectA = new Object[1];
                r4_ObjectA[0] = Float.valueOf((((float) r1j) / 1024.0f) / 1024.0f);
                r0_CharSequence = String.format("%.2fM", r4_ObjectA);
            } catch (Exception e) {
            }
            this.e.setText(r0_CharSequence);
        }
        this.g = (CheckBox) findViewById(R.id.isNight);
        this.i = (CheckBox) findViewById(R.id.isReceiveMessage);
        if (UIHelper.isNightTheme()) {
            this.g.setChecked(true);
            ((TextView) findViewById(R.id.brightTtitle)).setText("\u591c\u95f4-\u4eae\u5ea6\u8c03\u8282");
        } else {
            this.g.setChecked(false);
            ((TextView) findViewById(R.id.brightTtitle)).setText("\u65e5\u95f4-\u4eae\u5ea6\u8c03\u8282");
        }
        this.j = PushMessageManager.getReceiveMsgFromLocal();
        this.i.setChecked(this.j);
        this.k = (CheckBox) findViewById(R.id.isSavePosition);
        boolean r0z = ListViewHelper.canSelectionSaveable(this);
        this.l = r0z;
        this.m = r0z;
        this.k.setChecked(this.l);
    }

    private void d() {
        this.b.setOnClickListener(new do_(this));
        findViewById(R.id.feedback).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
        findViewById(R.id.versioncheck).setOnClickListener(this);
        findViewById(R.id.userSetting).setOnClickListener(this);
        findViewById(R.id.imageload).setOnClickListener(this);
        findViewById(R.id.bightness_setting).setOnClickListener(this);
        findViewById(R.id.cacheLayout).setOnClickListener(this);
        findViewById(R.id.textSizeContent).setOnClickListener(this);
        findViewById(R.id.marketComment).setOnClickListener(this);
        this.g.setOnCheckedChangeListener(new dt(this));
        this.i.setOnCheckedChangeListener(new du(this));
        this.k.setOnCheckedChangeListener(new dx(this));
    }

    private void e() {
        new dz(this, "qbk-UserSetN2").start();
    }

    private Builder f() {
        return new BrightnessSetting(this).createDialog();
    }

    private Builder g() {
        Builder r1_Builder = new Builder(this);
        View r2_View = LayoutInflater.from(QsbkApp.mContext).inflate(R.layout.update_dialog_message, null);
        ((TextView) r2_View.findViewById(R.id.updateMessage)).setText(Constants.change);
        r1_Builder.setView(r2_View);
        r1_Builder.setTitle("\u8f6f\u4ef6\u7248\u672c\u66f4\u65b0");
        r1_Builder.setPositiveButton("\u4ee5\u540e\u518d\u8bf4", new ec(this)).setNegativeButton("\u7acb\u5373\u4e0b\u8f7d", new ea(this));
        return r1_Builder;
    }

    private Builder h() {
        int r0i;
        int r1i = 1;
        int r2i = 0;
        r0i = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay").equals(NetworkManager.WIFI) ? 1 : 0;
        Builder r3_Builder = new Builder(new ContextThemeWrapper(this, 2131493154)).setTitle("\u8bf7\u9009\u62e9\u56fe\u7247\u52a0\u8f7d\u65b9\u5f0f");
        CharSequence[] r4_CharSequenceA = new CharSequence[2];
        r4_CharSequenceA[r2i] = "\u603b\u662f\u81ea\u52a8\u52a0\u8f7d";
        r4_CharSequenceA[r1i] = "\u4ec5\u5728WIFI\u73af\u5883\u4e2d\u81ea\u52a8\u52a0\u8f7d";
        return r3_Builder.setSingleChoiceItems(r4_CharSequenceA, r0i, new dq(this)).setNegativeButton("\u53d6\u6d88", new dp(this));
    }

    private String i() {
        return p[j()];
    }

    private int j() {
        if (o == null) {
            if (QsbkApp.isPad) {
                o = new float[]{19.5f, 22.5f, 22.5f};
            } else {
                o = new float[]{16.5f, 19.5f, 21.5f};
            }
        }
        int r2i = o.length;
        float r3f = QsbkApp.getInstance().getCurrentContentTextSize();
        int r0i = 0;
        while (r0i < r2i) {
            if (r3f == o[r0i]) {
                break;
            } else {
                r0i++;
            }
        }
        r0i = 0;
        return r0i >= p.length ? p.length - 1 : r0i;
    }

    private Builder k() {
        return new Builder(new ContextThemeWrapper(this, 2131493154)).setTitle("\u8bf7\u9009\u62e9\u5b57\u4f53\u5927\u5c0f").setSingleChoiceItems(p, j(), new ds(this)).setNegativeButton("\u53d6\u6d88", new dr(this));
    }

    protected void a(Intent r1_Intent) {
        startActivity(r1_Intent);
    }

    protected void a(Class<?> r3_Class_) {
        Intent r0_Intent = new Intent(this, LoginActivity.class);
        r0_Intent.putExtra("targetClass", r3_Class_);
        startActivity(r0_Intent);
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }

    public void finish() {
        if (this.h) {
            QsbkApp.isChange = true;
            if (QsbkApp.tmpContext != null) {
                QsbkApp.tmpContext.recreate();
            }
            QsbkApp.currentTheme = SharePreferenceUtils.getSharePreferencesValue("themeid");
            if ("day".equals(SharePreferenceUtils.getSharePreferencesValue("themeid"))) {
                StatService.onEvent(QsbkApp.tmpContext, "nightTheme", "open", 1);
            } else {
                StatService.onEvent(QsbkApp.tmpContext, "nightTheme", "close", 1);
            }
        } else {
            QsbkApp.isChange = false;
        }
        super.finish();
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
            a((int)XListViewHeader.STATE_REFRESHING, 0);
            r0_FileOutputStream.flush();
            if (r0_FileOutputStream != null) {
                r0_FileOutputStream.close();
            }
        } catch (Exception e) {
            a(-1, 0);
        }
    }

    public void onClick(View r4_View) {
        Intent r0_Intent = new Intent();
        switch (r4_View.getId()) {
            case R.id.about:
                r0_Intent.setClass(this, About.class);
                r0_Intent.putExtra("targetPage", "about");
                a(r0_Intent);
                break;
            case R.id.bightness_setting:
                f().show();
                break;
            case R.id.imageload:
                h().show();
                break;
            case R.id.textSizeContent:
                k().show();
                break;
            case R.id.cacheLayout:
                if (this.f == null || this.f.size() <= 0) {
                } else {
                    b(this.f);
                    this.e.setText("0M");
                }
                break;
            case R.id.userSetting:
                if (QsbkApp.currentUser == null) {
                    a(UserSetting.class);
                } else {
                    r0_Intent.setClass(this, OneProfileActivity.class);
                    r0_Intent.putExtra(OneProfileActivity.USER, QsbkApp.currentUser.toString());
                    r0_Intent.putExtra(OneProfileActivity.SOURCE, "userSetting");
                    a(r0_Intent);
                }
                break;
            case R.id.feedback:
                r0_Intent.setClass(this, About.class);
                r0_Intent.putExtra("targetPage", "feedback");
                a(r0_Intent);
                break;
            case R.id.marketComment:
                try {
                    a((Context)this);
                    SharePreferenceUtils.setSharePreferencesValue("isRated", "true");
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "\u611f\u8c22\u60a8\u7684\u652f\u6301, \u6211\u4eec\u4f1a\u66f4\u52a0\u52aa\u529b.", 0).show();
                    SharePreferenceUtils.setSharePreferencesValue("isRated", "true");
                }
                break;
            case R.id.versioncheck:
                Toast.makeText(QsbkApp.mContext, "\u68c0\u6d4b\u65b0\u7248\u672c\uff0c\u8bf7\u7a0d\u5019...", 0).show();
                e();
                break;
        }
    }

    protected void onCreate(Bundle r2_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r2_Bundle);
        setContentView(R.layout.activity_usersetting_navi);
        c();
        d();
        QsbkApp.currentTheme = SharePreferenceUtils.getSharePreferencesValue("themeid");
    }

    public boolean onKeyDown(int r3i, KeyEvent r4_KeyEvent) {
        if (r3i != 4) {
            return super.onKeyDown(r3i, r4_KeyEvent);
        }
        finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        return true;
    }

    protected void onNewIntent(Intent r1_Intent) {
        setIntent(r1_Intent);
    }

    protected void onStop() {
        if (this.l != this.m) {
            ListViewHelper.setSelectionSaveble(this, this.l);
        }
        super.onStop();
        PushMessageManager.updateRecvMsg(this.j, this);
    }
}
package qsbk.app.thirdparty.sso;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyDialogError;

public class SsoHandler {
    private static String c;
    private static String d;
    private ServiceConnection a;
    private int b;
    private ThirdPartyAuthListener e;
    private Oauth2AccessToken f;
    private Activity g;
    private ThirdParty h;

    static {
        c = RContactStorage.PRIMARY_KEY;
        d = RContactStorage.PRIMARY_KEY;
    }

    public SsoHandler(Activity r2_Activity, ThirdParty r3_ThirdParty) {
        this.a = null;
        this.f = null;
        this.g = r2_Activity;
        this.h = r3_ThirdParty;
        this.a = new a(this);
    }

    private void a(int r4i, ThirdPartyAuthListener r5_ThirdPartyAuthListener) {
        this.b = r4i;
        this.e = r5_ThirdPartyAuthListener;
        if (a(this.g) || this.h == null) {
        } else {
            this.h.startAuthDialog(this.g, this.e);
        }
    }

    private boolean a(Activity r5_Activity) {
        return r5_Activity.getApplicationContext().bindService(new Intent("com.sina.weibo.remotessoservice"), this.a, 1);
    }

    private boolean a(Activity r7_Activity, Intent r8_Intent) {
        ResolveInfo r1_ResolveInfo = r7_Activity.getPackageManager().resolveActivity(r8_Intent, 0);
        if (r1_ResolveInfo == null) {
            return false;
        }
        try {
            Signature[] r2_SignatureA = r7_Activity.getPackageManager().getPackageInfo(r1_ResolveInfo.activityInfo.packageName, RContact.MM_CONTACTFLAG_FAVOURCONTACT).signatures;
            int r3i = r2_SignatureA.length;
            int r1i = 0;
            while (r1i < r3i) {
                if ("30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(r2_SignatureA[r1i].toCharsString())) {
                    return true;
                }
                r1i++;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private boolean a(Activity r6_Activity, String r7_String, String[] r8_StringA, int r9i) {
        boolean r0z = true;
        Intent r2_Intent = new Intent();
        r2_Intent.setClassName(c, d);
        r2_Intent.putExtra("appKey", r7_String);
        r2_Intent.putExtra("redirectUri", ThirdParty.redirecturl);
        if (r8_StringA.length > 0) {
            r2_Intent.putExtra(QQDialogAuthorizeActivity.SCOPE, TextUtils.join(",", r8_StringA));
        }
        if (!a(r6_Activity, r2_Intent)) {
            return false;
        }
        try {
            r6_Activity.startActivityForResult(r2_Intent, r9i);
        } catch (ActivityNotFoundException e) {
            r0z = false;
        }
        r6_Activity.getApplication().unbindService(this.a);
        return r0z;
    }

    public void authorize(ThirdPartyAuthListener r2_ThirdPartyAuthListener) {
        a(32973, r2_ThirdPartyAuthListener);
    }

    public void authorizeCallBack(int r6i, int r7i, Intent r8_Intent) {
        int r4i = -1;
        if (r6i == this.b) {
            if (r7i == -1) {
                String r0_String = r8_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_RET);
                if (r0_String == null) {
                    r0_String = r8_Intent.getStringExtra("error_type");
                }
                if (r0_String != null) {
                    if (r0_String.equals("access_denied") || r0_String.equals("OAuthAccessDeniedException")) {
                        Log.d("Weibo-authorize", "Login canceled by user.");
                        this.e.onCancel();
                    } else {
                        String r1_String = r8_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_DES);
                        if (r1_String != null) {
                            r0_String = r0_String + ":" + r1_String;
                        }
                        Log.d("Weibo-authorize", "Login failed: " + r0_String);
                        this.e.onError(new ThirdPartyDialogError(r0_String, r7i, r1_String));
                    }
                } else {
                    if (this.f == null) {
                        this.f = new Oauth2AccessToken();
                    }
                    this.f.setToken(r8_Intent.getStringExtra(ThirdParty.KEY_TOKEN));
                    this.f.setExpiresIn(r8_Intent.getStringExtra(ThirdParty.KEY_EXPIRES));
                    this.f.setRefreshToken(r8_Intent.getStringExtra(ThirdParty.KEY_REFRESHTOKEN));
                    if (this.f.isSessionValid()) {
                        Log.d("Weibo-authorize", "Login Success! access_token=" + this.f.getToken() + " expires=" + this.f.getExpiresTime() + "refresh_token=" + this.f.getRefreshToken());
                        this.e.onComplete(r8_Intent.getExtras());
                    } else {
                        Log.d("Weibo-authorize", "Failed to receive access token by SSO");
                        this.h.startAuthDialog(this.g, this.e);
                    }
                }
            } else if (r7i == 0) {
                if (r8_Intent != null) {
                    Log.d("Weibo-authorize", "Login failed: " + r8_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_RET));
                    this.e.onError(new ThirdPartyDialogError(r8_Intent.getStringExtra(QQDialogAuthorizeActivity.ERROR_RET), r8_Intent.getIntExtra("error_code", r4i), r8_Intent.getStringExtra("failing_url")));
                } else {
                    Log.d("Weibo-authorize", "Login canceled by user.");
                    this.e.onCancel();
                }
            }
        }
    }
}
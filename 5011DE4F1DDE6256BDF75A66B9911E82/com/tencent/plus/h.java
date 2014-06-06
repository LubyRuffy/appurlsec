package com.tencent.plus;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.audit.RequestListener;

// compiled from: ProGuard
class h implements IRequestListener {
    final /* synthetic */ ImageActivity a;

    h(ImageActivity r1_ImageActivity) {
        this.a = r1_ImageActivity;
    }

    private void a(int r5i, String r6_String) {
        ImageActivity.k(this.a).post(new l(this));
        if (r6_String == null) {
            if (r5i == -2) {
                ImageActivity.b(this.a, "\u7f51\u7edc\u6709\u95ee\u9898\u5462\uff0c\u68c0\u67e5\u4e00\u4e0b\u7f51\u7edc\u518d\u91cd\u8bd5\u5427\uff1a\uff09", 1);
            } else {
                ImageActivity.b(this.a, "\u8bbe\u7f6e\u51fa\u9519\u4e86\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u518d\u5c1d\u8bd5\u4e0b\u5462\uff1a\uff09", 1);
            }
        } else {
            ImageActivity.b(this.a, r6_String, 1);
        }
        this.a.a("10660", 0);
        Log.i("ImageActivity", "setAvatar failure, errorCode: " + r5i);
    }

    public void onComplete(JSONObject r7_JSONObject, Object r8_Object) {
        int r0i;
        ImageActivity.k(this.a).post(new m(this));
        int r1i = -1;
        try {
            r0i = r7_JSONObject.getInt(KEYS.RET);
        } catch (JSONException e) {
            e.printStackTrace();
            r0i = r1i;
        }
        if (r0i == 0) {
            ImageActivity.b(this.a, "\u8bbe\u7f6e\u6210\u529f", 0);
            this.a.a("10658", 0);
            Context r0_Context = this.a;
            if (ImageActivity.l(this.a) == null || RContactStorage.PRIMARY_KEY.equals(ImageActivity.l(this.a))) {
                ImageActivity.a(this.a, 0, r7_JSONObject.toString(), null, null);
                ImageActivity.j(this.a);
            } else {
                Intent r1_Intent = new Intent();
                r1_Intent.setClassName(r0_Context, ImageActivity.l(this.a));
                if (r0_Context.getPackageManager().resolveActivity(r1_Intent, 0) != null) {
                    r0_Context.startActivity(r1_Intent);
                }
                ImageActivity.a(this.a, 0, r7_JSONObject.toString(), null, null);
                ImageActivity.j(this.a);
            }
        } else {
            a(r0i, null);
        }
    }

    public void onConnectTimeoutException(ConnectTimeoutException r3_ConnectTimeoutException, Object r4_Object) {
        a(RequestListener.DEFAULT_LOADED_SIZE, null);
    }

    public void onHttpStatusException(HttpStatusException r3_HttpStatusException, Object r4_Object) {
        a(RequestListener.DEFAULT_LOADED_SIZE, null);
    }

    public void onIOException(IOException r3_IOException, Object r4_Object) {
        r3_IOException.printStackTrace();
        a(RequestListener.DEFAULT_LOADED_SIZE, null);
    }

    public void onJSONException(JSONException r3_JSONException, Object r4_Object) {
        r3_JSONException.printStackTrace();
        a(-1, null);
    }

    public void onMalformedURLException(MalformedURLException r3_MalformedURLException, Object r4_Object) {
        r3_MalformedURLException.printStackTrace();
        a(0, null);
    }

    public void onNetworkUnavailableException(NetworkUnavailableException r3_NetworkUnavailableException, Object r4_Object) {
        a(RequestListener.DEFAULT_LOADED_SIZE, null);
    }

    public void onSocketTimeoutException(SocketTimeoutException r3_SocketTimeoutException, Object r4_Object) {
        a(RequestListener.DEFAULT_LOADED_SIZE, null);
    }

    public void onUnknowException(Exception r3_Exception, Object r4_Object) {
        a(1, null);
    }
}
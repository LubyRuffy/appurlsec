package com.tencent.plus;

import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
class g implements IRequestListener {
    final /* synthetic */ ImageActivity a;

    g(ImageActivity r1_ImageActivity) {
        this.a = r1_ImageActivity;
    }

    private void a(int r3i) {
        if (ImageActivity.m(this.a) < 2) {
            ImageActivity.n(this.a);
        }
    }

    public void onComplete(JSONObject r6_JSONObject, Object r7_Object) {
        int r1i = -1;
        try {
            r1i = r6_JSONObject.getInt(KEYS.RET);
            if (r1i == 0) {
                ImageActivity.k(this.a).post(new a(this, r6_JSONObject.getString(RContact.COL_NICKNAME)));
                this.a.a("10659", 0);
                if (r1i == 0) {
                    a(r1i);
                }
            } else {
                this.a.a("10661", 0);
                if (r1i == 0) {
                } else {
                    a(r1i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onConnectTimeoutException(ConnectTimeoutException r2_ConnectTimeoutException, Object r3_Object) {
        a(0);
    }

    public void onHttpStatusException(HttpStatusException r2_HttpStatusException, Object r3_Object) {
        a(0);
    }

    public void onIOException(IOException r2_IOException, Object r3_Object) {
        a(0);
    }

    public void onJSONException(JSONException r2_JSONException, Object r3_Object) {
        a(0);
    }

    public void onMalformedURLException(MalformedURLException r2_MalformedURLException, Object r3_Object) {
        a(0);
    }

    public void onNetworkUnavailableException(NetworkUnavailableException r2_NetworkUnavailableException, Object r3_Object) {
        a(0);
    }

    public void onSocketTimeoutException(SocketTimeoutException r2_SocketTimeoutException, Object r3_Object) {
        a(0);
    }

    public void onUnknowException(Exception r2_Exception, Object r3_Object) {
        a(0);
    }
}
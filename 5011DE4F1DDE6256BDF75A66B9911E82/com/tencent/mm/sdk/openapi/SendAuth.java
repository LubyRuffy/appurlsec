package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.Log;

public final class SendAuth {

    public static class Req extends BaseReq {
        public String scope;
        public String state;

        public Req(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            if (this.scope == null || this.scope.length() == 0 || this.scope.length() > 1024) {
                Log.e("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, scope is invalid");
                return false;
            } else {
                if (this.state == null || this.state.length() <= 1024) {
                    return true;
                }
                Log.e("MicroMsg.SDK.SendAuth.Req", "checkArgs fail, state is invalid");
                return false;
            }
        }

        public void fromBundle(Bundle r2_Bundle) {
            super.fromBundle(r2_Bundle);
            this.scope = r2_Bundle.getString("_wxapi_sendauth_req_scope");
            this.state = r2_Bundle.getString("_wxapi_sendauth_req_state");
        }

        public int getType() {
            return 1;
        }

        public void toBundle(Bundle r3_Bundle) {
            super.toBundle(r3_Bundle);
            r3_Bundle.putString("_wxapi_sendauth_req_scope", this.scope);
            r3_Bundle.putString("_wxapi_sendauth_req_state", this.state);
        }
    }

    public static class Resp extends BaseResp {
        public int expireDate;
        public String resultUrl;
        public String state;
        public String token;
        public String userName;

        public Resp(Bundle r1_Bundle) {
            fromBundle(r1_Bundle);
        }

        final boolean a() {
            if (this.state == null || this.state.length() <= 1024) {
                return true;
            }
            Log.e("MicroMsg.SDK.SendAuth.Resp", "checkArgs fail, state is invalid");
            return false;
        }

        public void fromBundle(Bundle r3_Bundle) {
            super.fromBundle(r3_Bundle);
            this.userName = r3_Bundle.getString("_wxapi_sendauth_resp_userName");
            this.token = r3_Bundle.getString("_wxapi_sendauth_resp_token");
            this.expireDate = r3_Bundle.getInt("_wxapi_sendauth_resp_expireDate", 0);
            this.state = r3_Bundle.getString("_wxapi_sendauth_resp_state");
        }

        public int getType() {
            return 1;
        }

        public void toBundle(Bundle r3_Bundle) {
            super.toBundle(r3_Bundle);
            r3_Bundle.putString("_wxapi_sendauth_resp_userName", this.userName);
            r3_Bundle.putString("_wxapi_sendauth_resp_token", this.token);
            r3_Bundle.putInt("_wxapi_sendauth_resp_expireDate", this.expireDate);
            r3_Bundle.putString("_wxapi_sendauth_resp_state", this.state);
        }
    }

    private SendAuth() {
    }
}
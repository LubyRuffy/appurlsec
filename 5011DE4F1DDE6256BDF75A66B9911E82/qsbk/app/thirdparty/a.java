package qsbk.app.thirdparty;

import android.os.Bundle;
import android.util.Log;

// compiled from: ThirdParty.java
class a implements ThirdPartyAuthListener {
    final /* synthetic */ ThirdPartyAuthListener a;
    final /* synthetic */ ThirdParty b;

    a(ThirdParty r1_ThirdParty, ThirdPartyAuthListener r2_ThirdPartyAuthListener) {
        this.b = r1_ThirdParty;
        this.a = r2_ThirdPartyAuthListener;
    }

    public void onCancel() {
        Log.d("ThirdParty-authorize", "Login canceled");
        this.a.onCancel();
    }

    public void onComplete(Bundle r5_Bundle) {
        if (this.b.accessToken == null) {
            this.b.accessToken = new Oauth2AccessToken();
        }
        this.b.accessToken.setToken(r5_Bundle.getString(ThirdParty.KEY_TOKEN));
        this.b.accessToken.setExpiresIn(r5_Bundle.getString(ThirdParty.KEY_EXPIRES));
        this.b.accessToken.setRefreshToken(r5_Bundle.getString(ThirdParty.KEY_REFRESHTOKEN));
        if (this.b.accessToken.isSessionValid()) {
            Log.d("ThirdParty-authorize", "Login Success! access_token=" + this.b.accessToken.getToken() + " expires=" + this.b.accessToken.getExpiresTime() + " refresh_token=" + this.b.accessToken.getRefreshToken());
            this.a.onComplete(r5_Bundle);
        } else {
            Log.d("ThirdParty-authorize", "Failed to receive access token");
            this.a.onThirdPartyException(new ThirdPartyException("Failed to receive access token."));
        }
    }

    public void onError(ThirdPartyDialogError r4_ThirdPartyDialogError) {
        Log.d("ThirdParty-authorize", "Login failed: " + r4_ThirdPartyDialogError);
        this.a.onError(r4_ThirdPartyDialogError);
    }

    public void onThirdPartyException(ThirdPartyException r4_ThirdPartyException) {
        Log.d("ThirdParty-authorize", "Login failed: " + r4_ThirdPartyException);
        this.a.onThirdPartyException(r4_ThirdPartyException);
    }
}
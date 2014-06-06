package qsbk.app.thirdparty;

import android.os.Bundle;

public interface ThirdPartyAuthListener {
    public void onCancel();

    public void onComplete(Bundle r1_Bundle);

    public void onError(ThirdPartyDialogError r1_ThirdPartyDialogError);

    public void onThirdPartyException(ThirdPartyException r1_ThirdPartyException);
}
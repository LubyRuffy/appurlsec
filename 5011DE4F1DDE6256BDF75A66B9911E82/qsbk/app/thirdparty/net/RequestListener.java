package qsbk.app.thirdparty.net;

import java.io.IOException;
import qsbk.app.thirdparty.ThirdPartyException;

public interface RequestListener {
    public void onComplete(String r1_String);

    public void onError(ThirdPartyException r1_ThirdPartyException);

    public void onIOException(IOException r1_IOException);
}
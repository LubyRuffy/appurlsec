package qsbk.app.activity;

import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.AuditArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: AuditNativeActivity.java
class q implements Task {
    final /* synthetic */ int a;
    final /* synthetic */ AuditNativeActivity b;

    q(AuditNativeActivity r1_AuditNativeActivity, int r2i) {
        this.b = r1_AuditNativeActivity;
        this.a = r2i;
    }

    public void fail(Throwable r1_Throwable) {
    }

    public Object proccess() throws QiushibaikeException {
        Map r1_Map = new HashMap();
        r1_Map.put(LocaleUtil.INDONESIAN, ((AuditArticle) this.b.ah.get(this.b.ag - 1)).id);
        r1_Map.put(KEYS.RET, this.a + RContactStorage.PRIMARY_KEY);
        return HttpClient.getIntentce().post(AuditNativeActivity.ae, r1_Map);
    }

    public void success(Object r1_Object) {
    }
}
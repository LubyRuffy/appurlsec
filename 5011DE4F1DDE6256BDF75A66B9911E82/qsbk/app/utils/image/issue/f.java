package qsbk.app.utils.image.issue;

import android.content.Context;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: Reporter.java
class f implements Task {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ Reporter c;

    f(Reporter r1_Reporter, String r2_String, Context r3_Context) {
        this.c = r1_Reporter;
        this.a = r2_String;
        this.b = r3_Context;
    }

    public void fail(Throwable r6_Throwable) {
        this.c.a("rpt_" + (System.currentTimeMillis() / 1000) + ".di", this.a, this.b);
    }

    public Object proccess() throws QiushibaikeException {
        try {
            if (QsbkApp.reportable) {
                HttpClient.getIntentce().get("http://imglog.moumentei.com" + this.a);
            }
            return null;
        } catch (Exception e) {
            Object[] r3_ObjectA = new Object[1];
            r3_ObjectA[0] = e.toString();
            throw new QiushibaikeException(String.format("%1$s happened when sending file", r3_ObjectA));
        }
    }

    public void success(Object r6_Object) {
        this.c.a("rpt_" + (System.currentTimeMillis() / 1000) + ".sent", this.a, this.b);
    }
}
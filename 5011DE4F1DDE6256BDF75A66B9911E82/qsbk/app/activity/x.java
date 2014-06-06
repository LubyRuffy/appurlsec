package qsbk.app.activity;

import java.util.Map;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: EditInfoBaseActivity.java
class x implements Task {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ EditInfoBaseActivity c;

    x(EditInfoBaseActivity r1_EditInfoBaseActivity, String r2_String, Map r3_Map) {
        this.c = r1_EditInfoBaseActivity;
        this.a = r2_String;
        this.b = r3_Map;
    }

    public void fail(Throwable r4_Throwable) {
        this.c.b("Exception happens on submit." + this.a);
    }

    public Object proccess() throws QiushibaikeException {
        return HttpClient.getIntentce().post(this.a, this.b);
    }

    public void success(Object r4_Object) {
        this.c.b("Submit success. " + r4_Object);
    }
}
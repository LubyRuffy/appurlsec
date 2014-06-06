package qsbk.app.utils.image.issue;

import android.content.Context;
import java.io.IOException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: DisplayIssueManager.java
class a implements Task {
    final /* synthetic */ IOException a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;
    final /* synthetic */ DisplayIssueManager d;

    a(DisplayIssueManager r1_DisplayIssueManager, IOException r2_IOException, String r3_String, Context r4_Context) {
        this.d = r1_DisplayIssueManager;
        this.a = r2_IOException;
        this.b = r3_String;
        this.c = r4_Context;
    }

    public void fail(Throwable r1_Throwable) {
    }

    public Object proccess() throws QiushibaikeException {
        String r5_String = HttpClient.getIntentce().getLastSuccessUrl();
        IssueBean r0_IssueBean = Analyzer.getInstance().getIssue(this.a, this.b, HttpClient.getIntentce().getLastSuccessTime(), r5_String);
        if (r0_IssueBean != null) {
            Reporter.getInstance().reportMsg(this.c, r0_IssueBean.toString());
        }
        return null;
    }

    public void success(Object r1_Object) {
    }
}
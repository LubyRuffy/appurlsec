package android.support.v4.content;

import android.os.Process;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: ModernAsyncTask.java
class i extends c<Params, Result> {
    final /* synthetic */ ModernAsyncTask a;

    i(ModernAsyncTask r2_ModernAsyncTask) {
        this.a = r2_ModernAsyncTask;
        super();
    }

    public Result call() throws Exception {
        this.a.h.set(true);
        Process.setThreadPriority(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        return this.a.d(this.a.a(this.b));
    }
}
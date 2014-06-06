package qsbk.app.core;

import android.os.Process;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;

// compiled from: AsyncTask.java
class b extends d<Params, Result> {
    final /* synthetic */ AsyncTask a;

    b(AsyncTask r2_AsyncTask) {
        this.a = r2_AsyncTask;
        super();
    }

    public Result call() throws Exception {
        this.a.i.set(true);
        Process.setThreadPriority(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        return this.a.d(this.a.a(this.b));
    }
}
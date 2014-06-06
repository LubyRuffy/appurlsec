package android.support.v4.content;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// compiled from: ModernAsyncTask.java
class j extends FutureTask<Result> {
    final /* synthetic */ ModernAsyncTask a;

    j(ModernAsyncTask r1_ModernAsyncTask, Callable r2_Callable) {
        this.a = r1_ModernAsyncTask;
        super(r2_Callable);
    }

    protected void done() {
        try {
            this.a.c(get());
        } catch (InterruptedException e) {
            Log.w("AsyncTask", e);
        } catch (ExecutionException e_2) {
            throw new RuntimeException("An error occured while executing doInBackground()", e_2.getCause());
        } catch (CancellationException e_3) {
            this.a.c(null);
        }
    }
}
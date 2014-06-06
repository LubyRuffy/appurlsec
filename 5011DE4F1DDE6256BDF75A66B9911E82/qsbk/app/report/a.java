package qsbk.app.report;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;

// compiled from: ArticleReporter.java
class a extends Handler {
    final /* synthetic */ ArticleReporter a;

    a(ArticleReporter r1_ArticleReporter) {
        this.a = r1_ArticleReporter;
    }

    public void handleMessage(Message r4_Message) {
        Toast.makeText(QsbkApp.mContext, (String) r4_Message.obj, 1).show();
    }
}
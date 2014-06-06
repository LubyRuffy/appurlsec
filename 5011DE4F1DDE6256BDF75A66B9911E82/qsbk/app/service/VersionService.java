package qsbk.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;
import qsbk.app.QsbkApp;
import qsbk.app.R;

public class VersionService extends Service {
    private NotificationManager a;
    private int b;
    private boolean c;
    private Handler d;

    public VersionService() {
        this.b = 0;
        this.c = false;
        this.d = new p(this);
    }

    private void a(int r8i) {
        Intent r0_Intent = new Intent(this, getClass());
        r0_Intent.addFlags(536870912);
        PendingIntent r0_PendingIntent = PendingIntent.getActivity(this, 0, r0_Intent, 0);
        Notification r1_Notification = new Notification(2130837700, "\u7cd7\u4e8b\u767e\u79d1", System.currentTimeMillis());
        RemoteViews r2_RemoteViews;
        if (this.c || QsbkApp.loading_process > 97) {
            r1_Notification.defaults |= 1;
            r1_Notification.defaults |= 2;
            r1_Notification.flags |= 2;
            r2_RemoteViews = new RemoteViews(getPackageName(), 2130903147);
            r2_RemoteViews.setTextViewText(R.id.n_title, "\u7cd7\u4e8b\u767e\u79d1");
            r2_RemoteViews.setTextViewText(R.id.n_text, r8i + "% ");
            r2_RemoteViews.setProgressBar(R.id.n_progress, 100, r8i, false);
            r1_Notification.contentView = r2_RemoteViews;
            r1_Notification.contentIntent = r0_PendingIntent;
            this.a.notify(0, r1_Notification);
        } else {
            r1_Notification.flags |= 2;
            r2_RemoteViews = new RemoteViews(getPackageName(), 2130903147);
            r2_RemoteViews.setTextViewText(R.id.n_title, "\u7cd7\u4e8b\u767e\u79d1");
            r2_RemoteViews.setTextViewText(R.id.n_text, r8i + "% ");
            r2_RemoteViews.setProgressBar(R.id.n_progress, 100, r8i, false);
            r1_Notification.contentView = r2_RemoteViews;
            r1_Notification.contentIntent = r0_PendingIntent;
            this.a.notify(0, r1_Notification);
        }
    }

    public IBinder onBind(Intent r2_Intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.c = true;
        this.a = (NotificationManager) getSystemService("notification");
        this.d.handleMessage(new Message());
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
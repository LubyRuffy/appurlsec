package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

// compiled from: NotificationCompatHoneycomb.java
class z {
    static Notification a(Context r6_Context, Notification r7_Notification, CharSequence r8_CharSequence, CharSequence r9_CharSequence, CharSequence r10_CharSequence, RemoteViews r11_RemoteViews, int r12i, PendingIntent r13_PendingIntent, PendingIntent r14_PendingIntent, Bitmap r15_Bitmap) {
        boolean r1z = true;
        Builder r0_Builder = new Builder(r6_Context).setWhen(r7_Notification.when).setSmallIcon(r7_Notification.icon, r7_Notification.iconLevel).setContent(r7_Notification.contentView).setTicker(r7_Notification.tickerText, r11_RemoteViews).setSound(r7_Notification.sound, r7_Notification.audioStreamType).setVibrate(r7_Notification.vibrate).setLights(r7_Notification.ledARGB, r7_Notification.ledOnMS, r7_Notification.ledOffMS).setOngoing((r7_Notification.flags & 2) != 0).setOnlyAlertOnce((r7_Notification.flags & 8) != 0).setAutoCancel((r7_Notification.flags & 16) != 0).setDefaults(r7_Notification.defaults).setContentTitle(r8_CharSequence).setContentText(r9_CharSequence).setContentInfo(r10_CharSequence).setContentIntent(r13_PendingIntent).setDeleteIntent(r7_Notification.deleteIntent);
        if ((r7_Notification.flags & 128) != 0) {
            return r0_Builder.setFullScreenIntent(r14_PendingIntent, r1z).setLargeIcon(r15_Bitmap).setNumber(r12i).getNotification();
        }
        r1z = false;
        return r0_Builder.setFullScreenIntent(r14_PendingIntent, r1z).setLargeIcon(r15_Bitmap).setNumber(r12i).getNotification();
    }
}
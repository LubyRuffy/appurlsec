package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

// compiled from: NotificationCompatIceCreamSandwich.java
class aa {
    static Notification a(Context r7_Context, Notification r8_Notification, CharSequence r9_CharSequence, CharSequence r10_CharSequence, CharSequence r11_CharSequence, RemoteViews r12_RemoteViews, int r13i, PendingIntent r14_PendingIntent, PendingIntent r15_PendingIntent, Bitmap r16_Bitmap, int r17i, int r18i, boolean r19z) {
        return new Builder(r7_Context).setWhen(r8_Notification.when).setSmallIcon(r8_Notification.icon, r8_Notification.iconLevel).setContent(r8_Notification.contentView).setTicker(r8_Notification.tickerText, r12_RemoteViews).setSound(r8_Notification.sound, r8_Notification.audioStreamType).setVibrate(r8_Notification.vibrate).setLights(r8_Notification.ledARGB, r8_Notification.ledOnMS, r8_Notification.ledOffMS).setOngoing((r8_Notification.flags & 2) != 0).setOnlyAlertOnce((r8_Notification.flags & 8) != 0).setAutoCancel((r8_Notification.flags & 16) != 0).setDefaults(r8_Notification.defaults).setContentTitle(r9_CharSequence).setContentText(r10_CharSequence).setContentInfo(r11_CharSequence).setContentIntent(r14_PendingIntent).setDeleteIntent(r8_Notification.deleteIntent).setFullScreenIntent(r15_PendingIntent, (r8_Notification.flags & 128) != 0).setLargeIcon(r16_Bitmap).setNumber(r13i).setProgress(r17i, r18i, r19z).getNotification();
    }
}
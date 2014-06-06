package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: NotificationCompatJellybean.java
class ab {
    private Builder a;

    public ab(Context r8_Context, Notification r9_Notification, CharSequence r10_CharSequence, CharSequence r11_CharSequence, CharSequence r12_CharSequence, RemoteViews r13_RemoteViews, int r14i, PendingIntent r15_PendingIntent, PendingIntent r16_PendingIntent, Bitmap r17_Bitmap, int r18i, int r19i, boolean r20z, boolean r21z, int r22i, CharSequence r23_CharSequence) {
        this.a = new Builder(r8_Context).setWhen(r9_Notification.when).setSmallIcon(r9_Notification.icon, r9_Notification.iconLevel).setContent(r9_Notification.contentView).setTicker(r9_Notification.tickerText, r13_RemoteViews).setSound(r9_Notification.sound, r9_Notification.audioStreamType).setVibrate(r9_Notification.vibrate).setLights(r9_Notification.ledARGB, r9_Notification.ledOnMS, r9_Notification.ledOffMS).setOngoing((r9_Notification.flags & 2) != 0).setOnlyAlertOnce((r9_Notification.flags & 8) != 0).setAutoCancel((r9_Notification.flags & 16) != 0).setDefaults(r9_Notification.defaults).setContentTitle(r10_CharSequence).setContentText(r11_CharSequence).setSubText(r23_CharSequence).setContentInfo(r12_CharSequence).setContentIntent(r15_PendingIntent).setDeleteIntent(r9_Notification.deleteIntent).setFullScreenIntent(r16_PendingIntent, (r9_Notification.flags & 128) != 0).setLargeIcon(r17_Bitmap).setNumber(r14i).setUsesChronometer(r21z).setPriority(r22i).setProgress(r18i, r19i, r20z);
    }

    public void addAction(int r2i, CharSequence r3_CharSequence, PendingIntent r4_PendingIntent) {
        this.a.addAction(r2i, r3_CharSequence, r4_PendingIntent);
    }

    public void addBigPictureStyle(CharSequence r3_CharSequence, boolean r4z, CharSequence r5_CharSequence, Bitmap r6_Bitmap, Bitmap r7_Bitmap, boolean r8z) {
        BigPictureStyle r0_BigPictureStyle = new BigPictureStyle(this.a).setBigContentTitle(r3_CharSequence).bigPicture(r6_Bitmap);
        if (r8z) {
            r0_BigPictureStyle.bigLargeIcon(r7_Bitmap);
        }
        if (r4z) {
            r0_BigPictureStyle.setSummaryText(r5_CharSequence);
        }
    }

    public void addBigTextStyle(CharSequence r3_CharSequence, boolean r4z, CharSequence r5_CharSequence, CharSequence r6_CharSequence) {
        BigTextStyle r0_BigTextStyle = new BigTextStyle(this.a).setBigContentTitle(r3_CharSequence).bigText(r6_CharSequence);
        if (r4z) {
            r0_BigTextStyle.setSummaryText(r5_CharSequence);
        }
    }

    public void addInboxStyle(CharSequence r4_CharSequence, boolean r5z, CharSequence r6_CharSequence, ArrayList<CharSequence> r7_ArrayList_CharSequence) {
        InboxStyle r1_InboxStyle = new InboxStyle(this.a).setBigContentTitle(r4_CharSequence);
        if (r5z) {
            r1_InboxStyle.setSummaryText(r6_CharSequence);
        }
        Iterator r2_Iterator = r7_ArrayList_CharSequence.iterator();
        while (r2_Iterator.hasNext()) {
            r1_InboxStyle.addLine((CharSequence) r2_Iterator.next());
        }
    }

    public Notification build() {
        return this.a.build();
    }
}
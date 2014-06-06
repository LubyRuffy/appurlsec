package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.utils.Base64;

public class NotificationCompat {
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    private static final a a;

    public static class Action {
        public PendingIntent actionIntent;
        public int icon;
        public CharSequence title;

        public Action(int r1i, CharSequence r2_CharSequence, PendingIntent r3_PendingIntent) {
            this.icon = r1i;
            this.title = r2_CharSequence;
            this.actionIntent = r3_PendingIntent;
        }
    }

    public static abstract class Style {
        android.support.v4.app.NotificationCompat.Builder d;
        CharSequence e;
        CharSequence f;
        boolean g;

        public Style() {
            this.g = false;
        }

        public Notification build() {
            return this.d != null ? this.d.build() : null;
        }

        public void setBuilder(android.support.v4.app.NotificationCompat.Builder r2_android_support_v4_app_NotificationCompat_Builder) {
            if (this.d != r2_android_support_v4_app_NotificationCompat_Builder) {
                this.d = r2_android_support_v4_app_NotificationCompat_Builder;
                if (this.d != null) {
                    this.d.setStyle(this);
                }
            }
        }
    }

    public static class BigPictureStyle extends android.support.v4.app.NotificationCompat.Style {
        Bitmap a;
        Bitmap b;
        boolean c;

        public BigPictureStyle(android.support.v4.app.NotificationCompat.Builder r1_android_support_v4_app_NotificationCompat_Builder) {
            setBuilder(r1_android_support_v4_app_NotificationCompat_Builder);
        }

        public android.support.v4.app.NotificationCompat.BigPictureStyle bigLargeIcon(Bitmap r2_Bitmap) {
            this.b = r2_Bitmap;
            this.c = true;
            return this;
        }

        public android.support.v4.app.NotificationCompat.BigPictureStyle bigPicture(Bitmap r1_Bitmap) {
            this.a = r1_Bitmap;
            return this;
        }

        public android.support.v4.app.NotificationCompat.BigPictureStyle setBigContentTitle(CharSequence r1_CharSequence) {
            this.e = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.BigPictureStyle setSummaryText(CharSequence r2_CharSequence) {
            this.f = r2_CharSequence;
            this.g = true;
            return this;
        }
    }

    public static class BigTextStyle extends android.support.v4.app.NotificationCompat.Style {
        CharSequence a;

        public BigTextStyle(android.support.v4.app.NotificationCompat.Builder r1_android_support_v4_app_NotificationCompat_Builder) {
            setBuilder(r1_android_support_v4_app_NotificationCompat_Builder);
        }

        public android.support.v4.app.NotificationCompat.BigTextStyle bigText(CharSequence r1_CharSequence) {
            this.a = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.BigTextStyle setBigContentTitle(CharSequence r1_CharSequence) {
            this.e = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.BigTextStyle setSummaryText(CharSequence r2_CharSequence) {
            this.f = r2_CharSequence;
            this.g = true;
            return this;
        }
    }

    public static class Builder {
        Context a;
        CharSequence b;
        CharSequence c;
        PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        Bitmap g;
        CharSequence h;
        int i;
        int j;
        boolean k;
        android.support.v4.app.NotificationCompat.Style l;
        CharSequence m;
        int n;
        int o;
        boolean p;
        ArrayList<android.support.v4.app.NotificationCompat.Action> q;
        Notification r;

        public Builder(Context r4_Context) {
            this.q = new ArrayList();
            this.r = new Notification();
            this.a = r4_Context;
            this.r.when = System.currentTimeMillis();
            this.r.audioStreamType = -1;
            this.j = 0;
        }

        private void a(int r4i, boolean r5z) {
            if (r5z) {
                r0_Notification = this.r;
                r0_Notification.flags |= r4i;
            } else {
                r0_Notification = this.r;
                r0_Notification.flags &= r4i ^ -1;
            }
        }

        public android.support.v4.app.NotificationCompat.Builder addAction(int r3i, CharSequence r4_CharSequence, PendingIntent r5_PendingIntent) {
            this.q.add(new android.support.v4.app.NotificationCompat.Action(r3i, r4_CharSequence, r5_PendingIntent));
            return this;
        }

        public Notification build() {
            return a.build(this);
        }

        public Notification getNotification() {
            return a.build(this);
        }

        public android.support.v4.app.NotificationCompat.Builder setAutoCancel(boolean r2z) {
            a(Base64.URL_SAFE, r2z);
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setContent(RemoteViews r2_RemoteViews) {
            this.r.contentView = r2_RemoteViews;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setContentInfo(CharSequence r1_CharSequence) {
            this.h = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setContentIntent(PendingIntent r1_PendingIntent) {
            this.d = r1_PendingIntent;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setContentText(CharSequence r1_CharSequence) {
            this.c = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setContentTitle(CharSequence r1_CharSequence) {
            this.b = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setDefaults(int r3i) {
            this.r.defaults = r3i;
            if ((r3i & 4) != 0) {
                Notification r0_Notification = this.r;
                r0_Notification.flags |= 1;
            }
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setDeleteIntent(PendingIntent r2_PendingIntent) {
            this.r.deleteIntent = r2_PendingIntent;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setFullScreenIntent(PendingIntent r2_PendingIntent, boolean r3z) {
            this.e = r2_PendingIntent;
            a(FLAG_HIGH_PRIORITY, r3z);
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setLargeIcon(Bitmap r1_Bitmap) {
            this.g = r1_Bitmap;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setLights(int r6i, int r7i, int r8i) {
            int r0i;
            int r1i = PRIORITY_HIGH;
            this.r.ledARGB = r6i;
            this.r.ledOnMS = r7i;
            this.r.ledOffMS = r8i;
            r0i = (this.r.ledOnMS == 0 || this.r.ledOffMS == 0) ? 0 : 1;
            Notification r3_Notification = this.r;
            int r4i = this.r.flags & -2;
            if (r0i != 0) {
                r3_Notification.flags = r4i | r1i;
                return this;
            } else {
                r1i = 0;
                r3_Notification.flags = r4i | r1i;
                return this;
            }
        }

        public android.support.v4.app.NotificationCompat.Builder setNumber(int r1i) {
            this.i = r1i;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setOngoing(boolean r2z) {
            a(PRIORITY_MAX, r2z);
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setOnlyAlertOnce(boolean r2z) {
            a(Base64.DONT_BREAK_LINES, r2z);
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setPriority(int r1i) {
            this.j = r1i;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setProgress(int r1i, int r2i, boolean r3z) {
            this.n = r1i;
            this.o = r2i;
            this.p = r3z;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setSmallIcon(int r2i) {
            this.r.icon = r2i;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setSmallIcon(int r2i, int r3i) {
            this.r.icon = r2i;
            this.r.iconLevel = r3i;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setSound(Uri r3_Uri) {
            this.r.sound = r3_Uri;
            this.r.audioStreamType = -1;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setSound(Uri r2_Uri, int r3i) {
            this.r.sound = r2_Uri;
            this.r.audioStreamType = r3i;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setStyle(android.support.v4.app.NotificationCompat.Style r2_android_support_v4_app_NotificationCompat_Style) {
            if (this.l != r2_android_support_v4_app_NotificationCompat_Style) {
                this.l = r2_android_support_v4_app_NotificationCompat_Style;
                if (this.l != null) {
                    this.l.setBuilder(this);
                }
            }
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setSubText(CharSequence r1_CharSequence) {
            this.m = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setTicker(CharSequence r2_CharSequence) {
            this.r.tickerText = r2_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setTicker(CharSequence r2_CharSequence, RemoteViews r3_RemoteViews) {
            this.r.tickerText = r2_CharSequence;
            this.f = r3_RemoteViews;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setUsesChronometer(boolean r1z) {
            this.k = r1z;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setVibrate(long[] r2_longA) {
            this.r.vibrate = r2_longA;
            return this;
        }

        public android.support.v4.app.NotificationCompat.Builder setWhen(long r2j) {
            this.r.when = r2j;
            return this;
        }
    }

    public static class InboxStyle extends android.support.v4.app.NotificationCompat.Style {
        ArrayList<CharSequence> a;

        public InboxStyle() {
            this.a = new ArrayList();
        }

        public InboxStyle(android.support.v4.app.NotificationCompat.Builder r2_android_support_v4_app_NotificationCompat_Builder) {
            this.a = new ArrayList();
            setBuilder(r2_android_support_v4_app_NotificationCompat_Builder);
        }

        public android.support.v4.app.NotificationCompat.InboxStyle addLine(CharSequence r2_CharSequence) {
            this.a.add(r2_CharSequence);
            return this;
        }

        public android.support.v4.app.NotificationCompat.InboxStyle setBigContentTitle(CharSequence r1_CharSequence) {
            this.e = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.NotificationCompat.InboxStyle setSummaryText(CharSequence r2_CharSequence) {
            this.f = r2_CharSequence;
            this.g = true;
            return this;
        }
    }

    static interface a {
        public Notification build(android.support.v4.app.NotificationCompat.Builder r1_android_support_v4_app_NotificationCompat_Builder);
    }

    static class b implements a {
        b() {
        }

        public Notification build(android.support.v4.app.NotificationCompat.Builder r6_android_support_v4_app_NotificationCompat_Builder) {
            Notification r0_Notification = r6_android_support_v4_app_NotificationCompat_Builder.r;
            r0_Notification.setLatestEventInfo(r6_android_support_v4_app_NotificationCompat_Builder.a, r6_android_support_v4_app_NotificationCompat_Builder.b, r6_android_support_v4_app_NotificationCompat_Builder.c, r6_android_support_v4_app_NotificationCompat_Builder.d);
            if (r6_android_support_v4_app_NotificationCompat_Builder.j > 0) {
                r0_Notification.flags |= 128;
            }
            return r0_Notification;
        }
    }

    static class c implements a {
        c() {
        }

        public Notification build(android.support.v4.app.NotificationCompat.Builder r11_android_support_v4_app_NotificationCompat_Builder) {
            return z.a(r11_android_support_v4_app_NotificationCompat_Builder.a, r11_android_support_v4_app_NotificationCompat_Builder.r, r11_android_support_v4_app_NotificationCompat_Builder.b, r11_android_support_v4_app_NotificationCompat_Builder.c, r11_android_support_v4_app_NotificationCompat_Builder.h, r11_android_support_v4_app_NotificationCompat_Builder.f, r11_android_support_v4_app_NotificationCompat_Builder.i, r11_android_support_v4_app_NotificationCompat_Builder.d, r11_android_support_v4_app_NotificationCompat_Builder.e, r11_android_support_v4_app_NotificationCompat_Builder.g);
        }
    }

    static class d implements a {
        d() {
        }

        public Notification build(android.support.v4.app.NotificationCompat.Builder r14_android_support_v4_app_NotificationCompat_Builder) {
            return aa.a(r14_android_support_v4_app_NotificationCompat_Builder.a, r14_android_support_v4_app_NotificationCompat_Builder.r, r14_android_support_v4_app_NotificationCompat_Builder.b, r14_android_support_v4_app_NotificationCompat_Builder.c, r14_android_support_v4_app_NotificationCompat_Builder.h, r14_android_support_v4_app_NotificationCompat_Builder.f, r14_android_support_v4_app_NotificationCompat_Builder.i, r14_android_support_v4_app_NotificationCompat_Builder.d, r14_android_support_v4_app_NotificationCompat_Builder.e, r14_android_support_v4_app_NotificationCompat_Builder.g, r14_android_support_v4_app_NotificationCompat_Builder.n, r14_android_support_v4_app_NotificationCompat_Builder.o, r14_android_support_v4_app_NotificationCompat_Builder.p);
        }
    }

    static class e implements a {
        e() {
        }

        public Notification build(android.support.v4.app.NotificationCompat.Builder r19_android_support_v4_app_NotificationCompat_Builder) {
            ab r1_ab = new ab(r19_android_support_v4_app_NotificationCompat_Builder.a, r19_android_support_v4_app_NotificationCompat_Builder.r, r19_android_support_v4_app_NotificationCompat_Builder.b, r19_android_support_v4_app_NotificationCompat_Builder.c, r19_android_support_v4_app_NotificationCompat_Builder.h, r19_android_support_v4_app_NotificationCompat_Builder.f, r19_android_support_v4_app_NotificationCompat_Builder.i, r19_android_support_v4_app_NotificationCompat_Builder.d, r19_android_support_v4_app_NotificationCompat_Builder.e, r19_android_support_v4_app_NotificationCompat_Builder.g, r19_android_support_v4_app_NotificationCompat_Builder.n, r19_android_support_v4_app_NotificationCompat_Builder.o, r19_android_support_v4_app_NotificationCompat_Builder.p, r19_android_support_v4_app_NotificationCompat_Builder.k, r19_android_support_v4_app_NotificationCompat_Builder.j, r19_android_support_v4_app_NotificationCompat_Builder.m);
            Iterator r3_Iterator = r19_android_support_v4_app_NotificationCompat_Builder.q.iterator();
            while (r3_Iterator.hasNext()) {
                android.support.v4.app.NotificationCompat.Action r2_android_support_v4_app_NotificationCompat_Action = (android.support.v4.app.NotificationCompat.Action) r3_Iterator.next();
                r1_ab.addAction(r2_android_support_v4_app_NotificationCompat_Action.icon, r2_android_support_v4_app_NotificationCompat_Action.title, r2_android_support_v4_app_NotificationCompat_Action.actionIntent);
            }
            if (r19_android_support_v4_app_NotificationCompat_Builder.l != null) {
                if (r19_android_support_v4_app_NotificationCompat_Builder.l instanceof android.support.v4.app.NotificationCompat.BigTextStyle) {
                    android.support.v4.app.NotificationCompat.BigTextStyle r2_android_support_v4_app_NotificationCompat_BigTextStyle = (android.support.v4.app.NotificationCompat.BigTextStyle) r19_android_support_v4_app_NotificationCompat_Builder.l;
                    r1_ab.addBigTextStyle(r2_android_support_v4_app_NotificationCompat_BigTextStyle.e, r2_android_support_v4_app_NotificationCompat_BigTextStyle.g, r2_android_support_v4_app_NotificationCompat_BigTextStyle.f, r2_android_support_v4_app_NotificationCompat_BigTextStyle.a);
                } else if (r19_android_support_v4_app_NotificationCompat_Builder.l instanceof android.support.v4.app.NotificationCompat.InboxStyle) {
                    android.support.v4.app.NotificationCompat.InboxStyle r2_android_support_v4_app_NotificationCompat_InboxStyle = (android.support.v4.app.NotificationCompat.InboxStyle) r19_android_support_v4_app_NotificationCompat_Builder.l;
                    r1_ab.addInboxStyle(r2_android_support_v4_app_NotificationCompat_InboxStyle.e, r2_android_support_v4_app_NotificationCompat_InboxStyle.g, r2_android_support_v4_app_NotificationCompat_InboxStyle.f, r2_android_support_v4_app_NotificationCompat_InboxStyle.a);
                } else if (r19_android_support_v4_app_NotificationCompat_Builder.l instanceof android.support.v4.app.NotificationCompat.BigPictureStyle) {
                    android.support.v4.app.NotificationCompat.BigPictureStyle r7_android_support_v4_app_NotificationCompat_BigPictureStyle = (android.support.v4.app.NotificationCompat.BigPictureStyle) r19_android_support_v4_app_NotificationCompat_Builder.l;
                    r1_ab.addBigPictureStyle(r7_android_support_v4_app_NotificationCompat_BigPictureStyle.e, r7_android_support_v4_app_NotificationCompat_BigPictureStyle.g, r7_android_support_v4_app_NotificationCompat_BigPictureStyle.f, r7_android_support_v4_app_NotificationCompat_BigPictureStyle.a, r7_android_support_v4_app_NotificationCompat_BigPictureStyle.b, r7_android_support_v4_app_NotificationCompat_BigPictureStyle.c);
                }
            }
            return r1_ab.build();
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new e();
        } else if (VERSION.SDK_INT >= 14) {
            a = new d();
        } else if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }
}
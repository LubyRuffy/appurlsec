package android.support.v4.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.OnGetPlaybackPositionListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.view.View;
import android.view.ViewTreeObserver.OnWindowAttachListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: TransportMediatorJellybeanMR2.java
class d implements OnGetPlaybackPositionListener, OnPlaybackPositionUpdateListener {
    final Context a;
    final AudioManager b;
    final View c;
    final c d;
    final String e;
    final IntentFilter f;
    final Intent g;
    final OnWindowAttachListener h;
    final OnWindowFocusChangeListener i;
    final BroadcastReceiver j;
    OnAudioFocusChangeListener k;
    PendingIntent l;
    RemoteControlClient m;
    boolean n;
    int o;
    boolean p;

    public d(Context r3_Context, AudioManager r4_AudioManager, View r5_View, c r6_c) {
        this.h = new e(this);
        this.i = new f(this);
        this.j = new g(this);
        this.k = new h(this);
        this.o = 0;
        this.a = r3_Context;
        this.b = r4_AudioManager;
        this.c = r5_View;
        this.d = r6_c;
        this.e = r3_Context.getPackageName() + ":transport:" + System.identityHashCode(this);
        this.g = new Intent(this.e);
        this.g.setPackage(r3_Context.getPackageName());
        this.f = new IntentFilter();
        this.f.addAction(this.e);
        this.c.getViewTreeObserver().addOnWindowAttachListener(this.h);
        this.c.getViewTreeObserver().addOnWindowFocusChangeListener(this.i);
    }

    void a() {
        this.a.registerReceiver(this.j, this.f);
        this.l = PendingIntent.getBroadcast(this.a, 0, this.g, 268435456);
        this.m = new RemoteControlClient(this.l);
        this.m.setOnGetPlaybackPositionListener(this);
        this.m.setPlaybackPositionUpdateListener(this);
    }

    void b() {
        if (!this.n) {
            this.n = true;
            this.b.registerMediaButtonEventReceiver(this.l);
            this.b.registerRemoteControlClient(this.m);
            if (this.o == XListViewFooter.STATE_NOMORE) {
                c();
            }
        }
    }

    void c() {
        if (!this.p) {
            this.p = true;
            this.b.requestAudioFocus(this.k, XListViewFooter.STATE_NOMORE, 1);
        }
    }

    void d_() {
        if (this.p) {
            this.p = false;
            this.b.abandonAudioFocus(this.k);
        }
    }

    public void destroy() {
        f();
        this.c.getViewTreeObserver().removeOnWindowAttachListener(this.h);
        this.c.getViewTreeObserver().removeOnWindowFocusChangeListener(this.i);
    }

    void e() {
        d();
        if (this.n) {
            this.n = false;
            this.b.unregisterRemoteControlClient(this.m);
            this.b.unregisterMediaButtonEventReceiver(this.l);
        }
    }

    void f() {
        e();
        if (this.l != null) {
            this.a.unregisterReceiver(this.j);
            this.l.cancel();
            this.l = null;
            this.m = null;
        }
    }

    public Object getRemoteControlClient() {
        return this.m;
    }

    public long onGetPlaybackPosition() {
        return this.d.getPlaybackPosition();
    }

    public void onPlaybackPositionUpdate(long r2j) {
        this.d.playbackPositionUpdate(r2j);
    }

    public void pausePlaying() {
        if (this.o == 3) {
            this.o = 2;
            this.m.setPlaybackState(XListViewHeader.STATE_REFRESHING);
        }
        d();
    }

    public void refreshState(boolean r4z, long r5j, int r7i) {
        if (this.m != null) {
            this.m.setPlaybackState(r4z ? 3 : 1, r5j, r4z ? 1.0f : 0.0f);
            this.m.setTransportControlFlags(r7i);
        }
    }

    public void startPlaying() {
        if (this.o != 3) {
            this.o = 3;
            this.m.setPlaybackState(XListViewFooter.STATE_NOMORE);
        }
        if (this.n) {
            c();
        }
    }

    public void stopPlaying() {
        if (this.o != 1) {
            this.o = 1;
            this.m.setPlaybackState(1);
        }
        d();
    }
}
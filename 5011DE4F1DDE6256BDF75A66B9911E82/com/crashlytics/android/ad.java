package com.crashlytics.android;

import com.crashlytics.android.internal.aa;
import com.crashlytics.android.internal.v;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class ad extends aa {
    private final float a;
    private /* synthetic */ ab b;

    ad(ab r1_ab, float r2f) {
        this.b = r1_ab;
        this.a = r2f;
    }

    public final void a() {
        try {
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Starting report processing in ").append(this.a).append(" second(s)...").toString());
            if (this.a > 0.0f) {
                try {
                    Thread.sleep((long) (this.a * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Crashlytics r1_Crashlytics = Crashlytics.getInstance();
            ba r2_ba = r1_Crashlytics.l();
            List r0_List = this.b.a();
            if (!r2_ba.a()) {
                if (r0_List.isEmpty() || r1_Crashlytics.p()) {
                    int r1i = 0;
                    while (!r0_List.isEmpty() && !Crashlytics.getInstance().l().a()) {
                        v.a().b().a(Crashlytics.TAG, new StringBuilder("Attempting to send ").append(r0_List.size()).append(" report(s)").toString());
                        Iterator r2_Iterator = r0_List.iterator();
                        while (r2_Iterator.hasNext()) {
                            this.b.a((z) r2_Iterator.next());
                        }
                        r0_List = this.b.a();
                        if (!r0_List.isEmpty()) {
                            int r2i = r1i + 1;
                            long r3j = (long) ab.c[Math.min(r1i, ab.c.length - 1)];
                            v.a().b().a(Crashlytics.TAG, new StringBuilder("Report submisson: scheduling delayed retry in ").append(r3j).append(" seconds").toString());
                            try {
                                Thread.sleep(r3j * 1000);
                                r1i = r2i;
                            } catch (InterruptedException e_2) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                } else {
                    v.a().b().a(Crashlytics.TAG, new StringBuilder("User declined to send. Removing ").append(r0_List.size()).append(" Report(s).").toString());
                    Iterator r1_Iterator = r0_List.iterator();
                    while (r1_Iterator.hasNext()) {
                        ((z) r1_Iterator.next()).a();
                    }
                }
            }
        } catch (Exception e_3) {
            v.a().b().a(Crashlytics.TAG, "An unexpected error occurred while attempting to upload crash reports.", e_3);
        }
        this.b.f = null;
    }
}
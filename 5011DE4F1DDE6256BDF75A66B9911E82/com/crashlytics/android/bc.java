package com.crashlytics.android;

import com.androidquery.util.Constants;
import java.util.concurrent.Callable;

// compiled from: SourceFile
final class bc implements Callable<Void> {
    private /* synthetic */ long a;
    private /* synthetic */ String b;
    private /* synthetic */ ba c;

    bc(ba r1_ba, long r2j, String r4_String) {
        this.c = r1_ba;
        this.a = r2j;
        this.b = r4_String;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!ba.a(this.c).get()) {
            if (ba.d(this.c) == null) {
                ba.e(this.c);
            }
            ba r0_ba = this.c;
            ba.a(ba.d(this.c), (int)Constants.FLAG_ACTIVITY_NO_ANIMATION, this.a, this.b);
        }
        return null;
    }
}
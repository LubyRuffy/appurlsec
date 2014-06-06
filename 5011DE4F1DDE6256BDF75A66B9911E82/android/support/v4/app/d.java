package android.support.v4.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

// compiled from: ActivityOptionsCompatJB.java
class d {
    private final ActivityOptions a;

    private d(ActivityOptions r1_ActivityOptions) {
        this.a = r1_ActivityOptions;
    }

    public static d makeCustomAnimation(Context r2_Context, int r3i, int r4i) {
        return new d(ActivityOptions.makeCustomAnimation(r2_Context, r3i, r4i));
    }

    public static d makeScaleUpAnimation(View r2_View, int r3i, int r4i, int r5i, int r6i) {
        return new d(ActivityOptions.makeScaleUpAnimation(r2_View, r3i, r4i, r5i, r6i));
    }

    public static d makeThumbnailScaleUpAnimation(View r2_View, Bitmap r3_Bitmap, int r4i, int r5i) {
        return new d(ActivityOptions.makeThumbnailScaleUpAnimation(r2_View, r3_Bitmap, r4i, r5i));
    }

    public Bundle toBundle() {
        return this.a.toBundle();
    }

    public void update(d r3_d) {
        this.a.update(r3_d.a);
    }
}
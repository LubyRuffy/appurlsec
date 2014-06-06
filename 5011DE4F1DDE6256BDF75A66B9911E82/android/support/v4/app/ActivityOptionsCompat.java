package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;

public class ActivityOptionsCompat {

    private static class a extends ActivityOptionsCompat {
        private final d a;

        a(d r1_d) {
            this.a = r1_d;
        }

        public Bundle toBundle() {
            return this.a.toBundle();
        }

        public void update(ActivityOptionsCompat r3_ActivityOptionsCompat) {
            if (r3_ActivityOptionsCompat instanceof a) {
                this.a.update(((a) r3_ActivityOptionsCompat).a);
            }
        }
    }

    protected ActivityOptionsCompat() {
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context r2_Context, int r3i, int r4i) {
        return VERSION.SDK_INT >= 16 ? new a(d.makeCustomAnimation(r2_Context, r3i, r4i)) : new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View r2_View, int r3i, int r4i, int r5i, int r6i) {
        return VERSION.SDK_INT >= 16 ? new a(d.makeScaleUpAnimation(r2_View, r3i, r4i, r5i, r6i)) : new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View r2_View, Bitmap r3_Bitmap, int r4i, int r5i) {
        return VERSION.SDK_INT >= 16 ? new a(d.makeThumbnailScaleUpAnimation(r2_View, r3_Bitmap, r4i, r5i)) : new ActivityOptionsCompat();
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat r1_ActivityOptionsCompat) {
    }
}
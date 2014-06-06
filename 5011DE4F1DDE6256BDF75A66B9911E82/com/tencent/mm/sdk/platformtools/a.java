package com.tencent.mm.sdk.platformtools;

import android.view.View;
import android.view.animation.Animation;
import com.tencent.mm.sdk.platformtools.BackwardSupportUtil.AnimationHelper.IHelper;

class a implements IHelper {
    a() {
    }

    public void cancelAnimation(View r2_View, Animation r3_Animation) {
        if (r2_View != null) {
            r2_View.setAnimation(null);
        }
    }
}
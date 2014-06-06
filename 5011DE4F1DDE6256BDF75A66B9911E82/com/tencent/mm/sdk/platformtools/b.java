package com.tencent.mm.sdk.platformtools;

import android.view.View;
import android.view.animation.Animation;
import com.tencent.mm.sdk.platformtools.BackwardSupportUtil.AnimationHelper.IHelper;

class b implements IHelper {
    b() {
    }

    public void cancelAnimation(View r1_View, Animation r2_Animation) {
        r2_Animation.cancel();
    }
}
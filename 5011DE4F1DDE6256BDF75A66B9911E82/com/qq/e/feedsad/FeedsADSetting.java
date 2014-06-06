package com.qq.e.feedsad;

import android.graphics.Color;
import com.tencent.mm.sdk.platformtools.Util;

public class FeedsADSetting {
    private String a;
    private int b;

    public FeedsADSetting() {
        this.b = Color.argb(Util.MASK_8BIT, 248, 248, 241);
    }

    public int getAdBackGroundColor() {
        return this.b;
    }

    public String getStyleID() {
        return this.a;
    }

    public void setAdBackGroundColor(int r1i) {
        this.b = r1i;
    }

    public void setStyleID(String r1_String) {
        this.a = r1_String;
    }
}
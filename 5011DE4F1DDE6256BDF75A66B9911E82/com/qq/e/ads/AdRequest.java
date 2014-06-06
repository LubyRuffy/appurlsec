package com.qq.e.ads;

import com.qq.e.v2.constants.Constants.KEYS;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AdRequest {
    private Map<String, Object> a;
    private volatile Map<String, Object> b;
    private boolean c;
    private int d;

    public AdRequest() {
        this.a = new HashMap();
        this.d = 30000;
        this.a.put(KEYS.BannerShowCloseBtn, Boolean.valueOf(false));
        setUseRollAnimation(1);
    }

    public Map<String, Object> getExtConfig() {
        if (this.b == null) {
            this.b = Collections.unmodifiableMap(this.a);
        }
        return this.b;
    }

    public int getRefresh() {
        return this.d;
    }

    public boolean getTestAd() {
        return this.c;
    }

    public int getUseRollAnimation(boolean r3z) {
        return ((Integer) this.a.get(KEYS.BannerRollAnimation)).intValue();
    }

    public boolean isShowCloseBtn() {
        return ((Boolean) this.a.get(KEYS.BannerShowCloseBtn)).booleanValue();
    }

    public boolean isTestAd() {
        return this.c;
    }

    public void setRefresh(int r3i) {
        if (r3i >= 30 || r3i == 0) {
            if (r3i > 120) {
                r3i = 120;
            }
        } else {
            r3i = 30;
        }
        this.d = r3i * 1000;
    }

    public void setShowCloseBtn(boolean r4z) {
        this.a.put(KEYS.BannerShowCloseBtn, Boolean.valueOf(r4z));
    }

    public void setTestAd(boolean r1z) {
        this.c = r1z;
    }

    public void setUseRollAnimation(int r4i) {
        this.a.put(KEYS.BannerRollAnimation, Integer.valueOf(r4i));
    }
}
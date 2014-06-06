package com.qiubai.library.adview.obj;

import com.tencent.mm.sdk.contact.RContactStorage;

public class Extra {
    public int bgAlpha;
    public int bgBlue;
    public int bgGreen;
    public int bgRed;
    public int cycleTime;
    public int fgAlpha;
    public int fgBlue;
    public int fgGreen;
    public int fgRed;
    public int locationOn;
    public String report;
    public int transition;

    public Extra() {
        this.fgRed = 255;
        this.fgGreen = 255;
        this.fgBlue = 255;
        this.fgAlpha = 1;
        this.bgRed = 0;
        this.bgGreen = 0;
        this.bgBlue = 0;
        this.bgAlpha = 1;
        this.cycleTime = 30;
        this.locationOn = 0;
        this.transition = 0;
        this.report = RContactStorage.PRIMARY_KEY;
    }
}
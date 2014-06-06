package com.qq.e.ads;

public class AdSize {
    public static final AdSize BANNER;
    public static final AdSize BANNER_SMALL;
    public static final AdSize SMART_BANNER;
    public static final String SMART_BANNER_STR = "smart_banner";

    static {
        BANNER = new AdSize(320, 50, "mb");
        BANNER_SMALL = new AdSize(220, 120, "mb");
        SMART_BANNER = new AdSize((byte) 0);
    }

    private AdSize(byte r1b) {
    }

    private AdSize(int r1i, int r2i, String r3_String) {
    }
}
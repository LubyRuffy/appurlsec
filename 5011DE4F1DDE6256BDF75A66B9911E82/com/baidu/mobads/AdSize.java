package com.baidu.mobads;

public enum AdSize {
    Banner(0),
    Square(1),
    VideoInterstitial(2),
    VideoPause(3),
    VideoSwitch(4),
    InterstitialGame(6),
    InterstitialReader(7),
    InterstitialRefresh(9),
    InterstitialOther(10);

    private int a;

    static {
        Banner = new AdSize("Banner", 0, 0);
        Square = new AdSize("Square", 1, 1);
        VideoInterstitial = new AdSize("VideoInterstitial", 2, 2);
        VideoPause = new AdSize("VideoPause", 3, 3);
        VideoSwitch = new AdSize("VideoSwitch", 4, 4);
        InterstitialGame = new AdSize("InterstitialGame", 5, 6);
        InterstitialReader = new AdSize("InterstitialReader", 6, 7);
        InterstitialRefresh = new AdSize("InterstitialRefresh", 7, 9);
        InterstitialOther = new AdSize("InterstitialOther", 8, 10);
        AdSize[] r0_AdSizeA = new AdSize[9];
        r0_AdSizeA[0] = Banner;
        r0_AdSizeA[1] = Square;
        r0_AdSizeA[2] = VideoInterstitial;
        r0_AdSizeA[3] = VideoPause;
        r0_AdSizeA[4] = VideoSwitch;
        r0_AdSizeA[5] = InterstitialGame;
        r0_AdSizeA[6] = InterstitialReader;
        r0_AdSizeA[7] = InterstitialRefresh;
        r0_AdSizeA[8] = InterstitialOther;
        b = r0_AdSizeA;
    }

    private AdSize(int r3i) {
        this.a = r3i;
    }

    public int getValue() {
        return this.a;
    }
}
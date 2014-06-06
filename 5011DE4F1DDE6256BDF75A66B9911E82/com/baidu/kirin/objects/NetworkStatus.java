package com.baidu.kirin.objects;

public enum NetworkStatus {
    NotReachable,
    TwoG,
    ThreeG,
    Wifi;


    static {
        NotReachable = new NetworkStatus("NotReachable", 0);
        TwoG = new NetworkStatus("TwoG", 1);
        ThreeG = new NetworkStatus("ThreeG", 2);
        Wifi = new NetworkStatus("Wifi", 3);
        NetworkStatus[] r0_NetworkStatusA = new NetworkStatus[4];
        r0_NetworkStatusA[0] = NotReachable;
        r0_NetworkStatusA[1] = TwoG;
        r0_NetworkStatusA[2] = ThreeG;
        r0_NetworkStatusA[3] = Wifi;
        a = r0_NetworkStatusA;
    }
}
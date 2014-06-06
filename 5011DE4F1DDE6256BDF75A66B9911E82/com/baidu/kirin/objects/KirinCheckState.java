package com.baidu.kirin.objects;

public enum KirinCheckState {
    ALREADY_UP_TO_DATE,
    NEWER_VERSION_FOUND,
    ERROR_CHECK_VERSION;


    static {
        ALREADY_UP_TO_DATE = new KirinCheckState("ALREADY_UP_TO_DATE", 0);
        NEWER_VERSION_FOUND = new KirinCheckState("NEWER_VERSION_FOUND", 1);
        ERROR_CHECK_VERSION = new KirinCheckState("ERROR_CHECK_VERSION", 2);
        KirinCheckState[] r0_KirinCheckStateA = new KirinCheckState[3];
        r0_KirinCheckStateA[0] = ALREADY_UP_TO_DATE;
        r0_KirinCheckStateA[1] = NEWER_VERSION_FOUND;
        r0_KirinCheckStateA[2] = ERROR_CHECK_VERSION;
        a = r0_KirinCheckStateA;
    }
}
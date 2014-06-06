package com.baidu.mobstat;

public enum SendStrategyEnum {
    APP_START,
    ONCE_A_DAY,
    SET_TIME_INTERVAL;


    static {
        APP_START = new SendStrategyEnum("APP_START", 0);
        ONCE_A_DAY = new SendStrategyEnum("ONCE_A_DAY", 1);
        SET_TIME_INTERVAL = new SendStrategyEnum("SET_TIME_INTERVAL", 2);
        SendStrategyEnum[] r0_SendStrategyEnumA = new SendStrategyEnum[3];
        r0_SendStrategyEnumA[0] = APP_START;
        r0_SendStrategyEnumA[1] = ONCE_A_DAY;
        r0_SendStrategyEnumA[2] = SET_TIME_INTERVAL;
        a = r0_SendStrategyEnumA;
    }
}
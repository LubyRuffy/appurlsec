package com.baidu.kirin.objects;

public enum InfoDataType {
    EventInfo,
    UerEventInfo,
    ErrorInfo,
    CrashInfo,
    SessionInfo;


    static {
        EventInfo = new InfoDataType("EventInfo", 0);
        UerEventInfo = new InfoDataType("UerEventInfo", 1);
        ErrorInfo = new InfoDataType("ErrorInfo", 2);
        CrashInfo = new InfoDataType("CrashInfo", 3);
        SessionInfo = new InfoDataType("SessionInfo", 4);
        InfoDataType[] r0_InfoDataTypeA = new InfoDataType[5];
        r0_InfoDataTypeA[0] = EventInfo;
        r0_InfoDataTypeA[1] = UerEventInfo;
        r0_InfoDataTypeA[2] = ErrorInfo;
        r0_InfoDataTypeA[3] = CrashInfo;
        r0_InfoDataTypeA[4] = SessionInfo;
        a = r0_InfoDataTypeA;
    }
}
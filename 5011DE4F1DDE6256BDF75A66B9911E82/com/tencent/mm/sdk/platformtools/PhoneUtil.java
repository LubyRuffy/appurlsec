package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;

public final class PhoneUtil {
    public static final String CELL_CDMA = "cdma";
    public static final String CELL_GSM = "gsm";
    private static final int a;

    public static class CellInfo {
        public static final int MAX_CID = 65535;
        public static final int MAX_LAC = 65535;
        public String cellid;
        public String dbm;
        public String lac;
        public String mcc;
        public String mnc;
        public String networkId;
        public String stationId;
        public String systemId;
        public String type;

        public CellInfo(String r1_String, String r2_String, String r3_String, String r4_String, String r5_String, String r6_String, String r7_String, String r8_String, String r9_String) {
            this.mcc = r1_String;
            this.mnc = r2_String;
            this.lac = r3_String;
            this.type = r6_String;
            this.cellid = r4_String;
            this.stationId = r7_String;
            this.networkId = r8_String;
            this.systemId = r9_String;
            this.dbm = r5_String;
        }
    }

    public static class MacInfo {
        public String dbm;
        public String mac;

        public MacInfo(String r1_String, String r2_String) {
            this.mac = r1_String;
            this.dbm = r2_String;
        }
    }

    static {
        a = 17;
    }

    private PhoneUtil() {
    }

    public static List<CellInfo> getCellInfoList(Context r2_Context) {
        return Integer.valueOf(VERSION.SDK).intValue() >= 5 ? new i().getCellInfoList(r2_Context) : new g().getCellInfoList(r2_Context);
    }

    public static String getCellXml(List<CellInfo> r4_List_CellInfo) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        if (r4_List_CellInfo == null || r4_List_CellInfo.size() <= 0) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = r1_String;
        int r1i = 0;
        while (r1i < r4_List_CellInfo.size()) {
            r0_String = ((((((((((r0_String + "<cell ") + "mcc=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).mcc + "\" ") + "mnc=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).mnc + "\" ") + "lac=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).lac + "\" ") + "type=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).type + "\" ") + "stationId=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).stationId + "\" ") + "networkId=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).networkId + "\" ") + "systemId=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).systemId + "\" ") + "dbm=\"" + ((CellInfo) r4_List_CellInfo.get(r1i)).dbm + "\" ") + " >") + ((CellInfo) r4_List_CellInfo.get(r1i)).cellid;
            r1i++;
            r0_String = r0_String + "</cell>";
        }
        return r0_String;
    }

    public static String getMacXml(List<MacInfo> r4_List_MacInfo) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        if (r4_List_MacInfo == null || r4_List_MacInfo.size() <= 0) {
            return RContactStorage.PRIMARY_KEY;
        }
        int r2i = 0;
        while (r2i < r4_List_MacInfo.size()) {
            String r0_String;
            if (r4_List_MacInfo.get(r2i) == null || ((MacInfo) r4_List_MacInfo.get(r2i)).mac.length() != a) {
                r0_String = r1_String;
                r2i++;
                r1_String = r0_String;
            } else {
                r0_String = ((((r1_String + "<mac ") + "macDbm=\"" + ((MacInfo) r4_List_MacInfo.get(r2i)).dbm + "\"") + ">") + ((MacInfo) r4_List_MacInfo.get(r2i)).mac) + "</mac>";
                r2i++;
                r1_String = r0_String;
            }
        }
        return r1_String;
    }

    public static void getSignalStrength(Context r2_Context) {
        if (Integer.valueOf(VERSION.SDK).intValue() >= 5) {
            new i().getSignalStrength(r2_Context);
        } else {
            new g().getSignalStrength(r2_Context);
        }
    }
}
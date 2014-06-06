package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.PhoneUtil.CellInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.cordova.NetworkManager;
import qsbk.app.widget.listview.XListViewFooter;

class g {
    private static int a;
    private static int b;
    private TelephonyManager c;
    private PhoneStateListener d;

    static {
        a = 10000;
        b = 10000;
    }

    g() {
        this.d = new h(this);
    }

    public List<CellInfo> getCellInfoList(Context r14_Context) {
        List<CellInfo> r11_List_CellInfo = new LinkedList();
        TelephonyManager r10_TelephonyManager = (TelephonyManager) r14_Context.getSystemService("phone");
        String r0_String = r10_TelephonyManager.getNetworkOperator();
        if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) {
            return r11_List_CellInfo;
        }
        List r0_List;
        String r1_String = "460";
        String r2_String = RContactStorage.PRIMARY_KEY;
        try {
            r1_String = r0_String.substring(0, XListViewFooter.STATE_NOMORE);
            r2_String = r0_String.substring(XListViewFooter.STATE_NOMORE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GsmCellLocation r0_GsmCellLocation = (GsmCellLocation) r10_TelephonyManager.getCellLocation();
            if (r0_GsmCellLocation != null) {
                int r4i = r0_GsmCellLocation.getCid();
                int r3i = r0_GsmCellLocation.getLac();
                if (r3i >= 65535 || r3i == -1 || r4i == -1) {
                    r0_List = r10_TelephonyManager.getNeighboringCellInfo();
                } else {
                    r11_List_CellInfo.add(new CellInfo(r1_String, r2_String, String.valueOf(r3i), String.valueOf(r4i), b == a ? RContactStorage.PRIMARY_KEY : b, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
        r0_List = r10_TelephonyManager.getNeighboringCellInfo();
        if (r0_List == null || r0_List.size() <= 0) {
            return r11_List_CellInfo;
        }
        Iterator r10_Iterator = r0_List.iterator();
        while (r10_Iterator.hasNext()) {
            NeighboringCellInfo r4_NeighboringCellInfo = (NeighboringCellInfo) r10_Iterator.next();
            if (r4_NeighboringCellInfo.getCid() != -1) {
                r11_List_CellInfo.add(new CellInfo(r1_String, r2_String, RContactStorage.PRIMARY_KEY, r4_NeighboringCellInfo.getCid(), RContactStorage.PRIMARY_KEY, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
            }
        }
        return r11_List_CellInfo;
    }

    public void getSignalStrength(Context r4_Context) {
        this.c = (TelephonyManager) r4_Context.getSystemService("phone");
        this.c.listen(this.d, AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
    }
}
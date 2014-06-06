package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.PhoneUtil.CellInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.cordova.NetworkManager;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

class i {
    private static int a;
    private static int b;
    private TelephonyManager c;
    private int d;
    private PhoneStateListener e;

    static {
        a = 10000;
        b = 10000;
    }

    i() {
        this.e = new j(this);
    }

    public List<CellInfo> getCellInfoList(Context r15_Context) {
        GsmCellLocation r1_GsmCellLocation;
        List r1_List;
        Iterator r11_Iterator;
        TelephonyManager r11_TelephonyManager = (TelephonyManager) r15_Context.getSystemService("phone");
        List<CellInfo> r12_List_CellInfo = new LinkedList();
        String r2_String = "460";
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            String r3_String = r11_TelephonyManager.getNetworkOperator();
            CdmaCellLocation r10_CdmaCellLocation;
            String r6_String;
            int r5i;
            int r4i;
            NeighboringCellInfo r5_NeighboringCellInfo;
            if (r3_String == null || r3_String.equals(RContactStorage.PRIMARY_KEY)) {
                r3_String = r11_TelephonyManager.getSimOperator();
                if (r3_String == null || r3_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r3_String = r1_String;
                    if (r11_TelephonyManager.getPhoneType() == 2) {
                        try {
                            r10_CdmaCellLocation = (CdmaCellLocation) r11_TelephonyManager.getCellLocation();
                        } catch (Exception e) {
                            r1_GsmCellLocation = (GsmCellLocation) r11_TelephonyManager.getCellLocation();
                            if (r1_GsmCellLocation != null) {
                                r5i = r1_GsmCellLocation.getCid();
                                r4i = r1_GsmCellLocation.getLac();
                                if (r4i >= 65535 || r4i == -1 || r5i == -1) {
                                    r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                                } else {
                                    r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, String.valueOf(r4i), String.valueOf(r5i), RContactStorage.PRIMARY_KEY, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                }
                                if (r1_List == null || r1_List.size() <= 0) {
                                } else {
                                    r11_Iterator = r1_List.iterator();
                                    while (r11_Iterator.hasNext()) {
                                        r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                        if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535 || r5_NeighboringCellInfo.getLac() == -1) {
                                        } else {
                                            r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), (r5_NeighboringCellInfo.getRssi() * 2 - 113), NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                        }
                                    }
                                }
                            }
                            r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                            if (r1_List == null || r1_List.size() <= 0) {
                            } else {
                                r11_Iterator = r1_List.iterator();
                                while (r11_Iterator.hasNext()) {
                                    r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                    if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535 || r5_NeighboringCellInfo.getLac() == -1) {
                                    } else {
                                        r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), (r5_NeighboringCellInfo.getRssi() * 2 - 113), NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                    }
                                }
                            }
                        }
                        if (r10_CdmaCellLocation != null) {
                            r6_String = b == a ? RContactStorage.PRIMARY_KEY : b;
                            if (r10_CdmaCellLocation.getBaseStationId() == -1 || r10_CdmaCellLocation.getNetworkId() == -1 || r10_CdmaCellLocation.getSystemId() == -1) {
                            } else {
                                r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, r6_String, NetworkManager.CDMA, r10_CdmaCellLocation.getBaseStationId(), r10_CdmaCellLocation.getNetworkId(), r10_CdmaCellLocation.getSystemId()));
                            }
                        }
                    } else {
                        try {
                            r1_GsmCellLocation = (GsmCellLocation) r11_TelephonyManager.getCellLocation();
                            if (r1_GsmCellLocation != null) {
                                r5i = r1_GsmCellLocation.getCid();
                                r4i = r1_GsmCellLocation.getLac();
                                if (r4i >= 65535 || r4i == -1 || r5i == -1) {
                                    r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                                } else {
                                    r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, String.valueOf(r4i), String.valueOf(r5i), b == a ? RContactStorage.PRIMARY_KEY : b, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                }
                            }
                        } catch (Exception e_2) {
                            e_2.printStackTrace();
                        }
                        r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                        if (r1_List == null || r1_List.size() <= 0) {
                        } else {
                            r11_Iterator = r1_List.iterator();
                            while (r11_Iterator.hasNext()) {
                                r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535) {
                                } else {
                                    r6_String = (r5_NeighboringCellInfo.getRssi() * 2 - 113);
                                    Log.v("checked", new StringBuilder("lac:").append(r5_NeighboringCellInfo.getLac()).append("  cid:").append(r5_NeighboringCellInfo.getCid()).append(" dbm:").append(r6_String).toString());
                                    r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), r6_String, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                }
                            }
                        }
                    }
                    return r12_List_CellInfo;
                } else {
                    r2_String = r3_String.substring(0, XListViewFooter.STATE_NOMORE);
                    r1_String = r3_String.substring(XListViewFooter.STATE_NOMORE, ShareUtils.SHARE_SMS);
                    r3_String = r1_String;
                    if (r11_TelephonyManager.getPhoneType() == 2) {
                        r1_GsmCellLocation = (GsmCellLocation) r11_TelephonyManager.getCellLocation();
                        if (r1_GsmCellLocation != null) {
                            r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                            if (r1_List == null || r1_List.size() <= 0) {
                            } else {
                                r11_Iterator = r1_List.iterator();
                                while (r11_Iterator.hasNext()) {
                                    r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                    if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535) {
                                    } else {
                                        r6_String = (r5_NeighboringCellInfo.getRssi() * 2 - 113);
                                        Log.v("checked", new StringBuilder("lac:").append(r5_NeighboringCellInfo.getLac()).append("  cid:").append(r5_NeighboringCellInfo.getCid()).append(" dbm:").append(r6_String).toString());
                                        r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), r6_String, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                    }
                                }
                            }
                        } else {
                            r5i = r1_GsmCellLocation.getCid();
                            r4i = r1_GsmCellLocation.getLac();
                            if (r4i >= 65535 || r4i == -1 || r5i == -1) {
                                r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                                if (r1_List == null || r1_List.size() <= 0) {
                                } else {
                                    r11_Iterator = r1_List.iterator();
                                    while (r11_Iterator.hasNext()) {
                                        r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                        if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535) {
                                        } else {
                                            r6_String = (r5_NeighboringCellInfo.getRssi() * 2 - 113);
                                            Log.v("checked", new StringBuilder("lac:").append(r5_NeighboringCellInfo.getLac()).append("  cid:").append(r5_NeighboringCellInfo.getCid()).append(" dbm:").append(r6_String).toString());
                                            r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), r6_String, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                        }
                                    }
                                }
                            } else {
                                if (b == a) {
                                }
                                r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, String.valueOf(r4i), String.valueOf(r5i), b == a ? RContactStorage.PRIMARY_KEY : b, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                                if (r1_List == null || r1_List.size() <= 0) {
                                } else {
                                    r11_Iterator = r1_List.iterator();
                                    while (r11_Iterator.hasNext()) {
                                        r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                                        if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535) {
                                        } else {
                                            r6_String = (r5_NeighboringCellInfo.getRssi() * 2 - 113);
                                            Log.v("checked", new StringBuilder("lac:").append(r5_NeighboringCellInfo.getLac()).append("  cid:").append(r5_NeighboringCellInfo.getCid()).append(" dbm:").append(r6_String).toString());
                                            r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), r6_String, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        r10_CdmaCellLocation = (CdmaCellLocation) r11_TelephonyManager.getCellLocation();
                        if (r10_CdmaCellLocation != null) {
                        } else {
                            if (b == a) {
                            }
                            if (r10_CdmaCellLocation.getBaseStationId() == -1 || r10_CdmaCellLocation.getNetworkId() == -1 || r10_CdmaCellLocation.getSystemId() == -1) {
                            } else {
                                r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, r6_String, NetworkManager.CDMA, r10_CdmaCellLocation.getBaseStationId(), r10_CdmaCellLocation.getNetworkId(), r10_CdmaCellLocation.getSystemId()));
                            }
                        }
                    }
                    return r12_List_CellInfo;
                }
            } else {
                r2_String = r3_String.substring(0, XListViewFooter.STATE_NOMORE);
                r3_String = r3_String.substring(XListViewFooter.STATE_NOMORE, ShareUtils.SHARE_SMS);
                if (r11_TelephonyManager.getPhoneType() == 2) {
                    r10_CdmaCellLocation = (CdmaCellLocation) r11_TelephonyManager.getCellLocation();
                    if (r10_CdmaCellLocation != null) {
                        if (b == a) {
                        }
                        if (r10_CdmaCellLocation.getBaseStationId() == -1 || r10_CdmaCellLocation.getNetworkId() == -1 || r10_CdmaCellLocation.getSystemId() == -1) {
                        } else {
                            r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, r6_String, NetworkManager.CDMA, r10_CdmaCellLocation.getBaseStationId(), r10_CdmaCellLocation.getNetworkId(), r10_CdmaCellLocation.getSystemId()));
                        }
                    }
                } else {
                    r1_GsmCellLocation = (GsmCellLocation) r11_TelephonyManager.getCellLocation();
                    if (r1_GsmCellLocation != null) {
                        r5i = r1_GsmCellLocation.getCid();
                        r4i = r1_GsmCellLocation.getLac();
                        if (r4i >= 65535 || r4i == -1 || r5i == -1) {
                            r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                        } else {
                            if (b == a) {
                            }
                            r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, String.valueOf(r4i), String.valueOf(r5i), b == a ? RContactStorage.PRIMARY_KEY : b, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                        }
                    }
                    r1_List = r11_TelephonyManager.getNeighboringCellInfo();
                    if (r1_List == null || r1_List.size() <= 0) {
                    } else {
                        r11_Iterator = r1_List.iterator();
                        while (r11_Iterator.hasNext()) {
                            r5_NeighboringCellInfo = (NeighboringCellInfo) r11_Iterator.next();
                            if (r5_NeighboringCellInfo.getCid() == -1 || r5_NeighboringCellInfo.getLac() > 65535) {
                            } else {
                                r6_String = (r5_NeighboringCellInfo.getRssi() * 2 - 113);
                                Log.v("checked", new StringBuilder("lac:").append(r5_NeighboringCellInfo.getLac()).append("  cid:").append(r5_NeighboringCellInfo.getCid()).append(" dbm:").append(r6_String).toString());
                                r12_List_CellInfo.add(new CellInfo(r2_String, r3_String, r5_NeighboringCellInfo.getLac(), r5_NeighboringCellInfo.getCid(), r6_String, NetworkManager.GSM, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY));
                            }
                        }
                    }
                }
                return r12_List_CellInfo;
            }
        } catch (Exception e_3) {
            e_3.printStackTrace();
            return r12_List_CellInfo;
        }
    }

    public void getSignalStrength(Context r4_Context) {
        this.c = (TelephonyManager) r4_Context.getSystemService("phone");
        this.c.listen(this.e, AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        this.d = this.c.getPhoneType();
    }
}
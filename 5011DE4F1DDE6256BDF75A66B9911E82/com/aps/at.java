package com.aps;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import java.util.List;

public final class at {
    private static int c;
    private static int d;
    private static float f;
    protected ax a;
    protected au b;
    private ae e;

    static {
        c = 10;
        d = 100;
        f = 0.5f;
    }

    protected at(ae r2_ae) {
        this.a = new ax(this);
        this.b = new au(this);
        this.e = r2_ae;
    }

    protected static void a() {
    }

    protected static void a(int r0i) {
        c = r0i;
    }

    protected final boolean a(Location r7_Location) {
        boolean r2z = false;
        if (this.e == null) {
            return r2z;
        }
        List r4_List = this.e.j();
        new StringBuilder("cell.list.size: ").append(r4_List.size()).toString();
        av r0_av = null;
        if (r4_List.size() >= 2) {
            av r3_av = new av((CellLocation) r4_List.get(1));
            if (this.b.b == null) {
                r0_av = r3_av;
                r2z = true;
            } else {
                boolean r0z;
                r0z = (r7_Location.distanceTo(this.b.b) > ((float) d) ? 1 : (r7_Location.distanceTo(this.b.b) == ((float) d)? 0 : -1)) > 0;
                if (!r0z) {
                    r0_av = this.b.a;
                    int r0i = (r3_av.e == r0_av.e && r3_av.d == r0_av.d && r3_av.c == r0_av.c && r3_av.b == r0_av.b && r3_av.a == r0_av.a) ? 1 : 0;
                    r0z = r0i == 0;
                }
                new StringBuilder("collect cell?: ").append(r0z).toString();
                r2z = r0z;
                r0_av = r3_av;
            }
        }
        if (r2z) {
            this.b.a = r0_av;
        }
        return r2z;
    }

    protected final boolean b(Location r14_Location) {
        boolean r4z = false;
        if (this.e == null) {
            return false;
        }
        boolean r1z;
        List r2_List;
        boolean r3z;
        List r1_List = this.e.k();
        List r0_List = null;
        if (r1_List.size() >= 2) {
            r0_List = (List) r1_List.get(1);
            if (this.a.b == null) {
                r1z = true;
            } else {
                if (r0_List == null || r0_List.size() <= 0) {
                    r2_List = r0_List;
                    r1z = false;
                } else {
                    r1z = (r14_Location.distanceTo(this.a.b) > ((float) c) ? 1 : (r14_Location.distanceTo(this.a.b) == ((float) c)? 0 : -1)) > 0;
                    if (r1z) {
                        r2_List = r0_List;
                    } else {
                        List r7_List = this.a.a;
                        float r8f = f;
                        int r1i;
                        if (r0_List == null || r7_List == null) {
                            r1i = 0;
                            r1z = r1i != 0;
                        } else if (r0_List == null || r7_List == null) {
                            r1i = 0;
                            if (r1i != 0) {
                            }
                        } else {
                            int r9i = r0_List.size();
                            int r10i = r7_List.size();
                            float r11f = (float) (r9i + r10i);
                            if (r9i == 0 && r10i == 0) {
                                r1i = 1;
                                if (r1i != 0) {
                                }
                            } else if (r9i == 0 || r10i == 0) {
                                r1i = 0;
                                if (r1i != 0) {
                                }
                            } else {
                                int r6i = 0;
                                int r2i = 0;
                                while (r6i < r9i) {
                                    String r12_String = ((ScanResult) r0_List.get(r6i)).BSSID;
                                    if (r12_String != null) {
                                        int r5i = 0;
                                        while (r5i < r10i) {
                                            if (r12_String.equals(((aw) r7_List.get(r5i)).a)) {
                                                r1i = r2i + 1;
                                                break;
                                            } else {
                                                r5i++;
                                            }
                                        }
                                        r1i = r2i;
                                    } else {
                                        r1i = r2i;
                                    }
                                    r6i++;
                                    r2i = r1i;
                                }
                                if (((float) (r2i << 1)) >= r11f * r8f) {
                                    r1i = 1;
                                    if (r1i != 0) {
                                    }
                                } else {
                                    r1i = 0;
                                    if (r1i != 0) {
                                    }
                                }
                            }
                        }
                    }
                }
                this.a.a.clear();
                r3z = r2_List.size();
                while (r4z < r3z) {
                    this.a.a.add(new aw(((ScanResult) r2_List.get(r4z)).BSSID));
                    r4z++;
                }
                return r1z;
            }
            r2_List = r0_List;
        } else {
            r2_List = r0_List;
            r1z = false;
        }
        if (r1z) {
            this.a.a.clear();
            r3z = r2_List.size();
            while (r4z < r3z) {
                this.a.a.add(new aw(((ScanResult) r2_List.get(r4z)).BSSID));
                r4z++;
            }
        }
        return r1z;
    }
}
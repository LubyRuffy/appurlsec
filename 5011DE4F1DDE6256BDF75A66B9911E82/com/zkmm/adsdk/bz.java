package com.zkmm.adsdk;

import android.util.Log;
import com.baidu.location.BDLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;
import qsbk.app.bean.Base;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
class bz {
    bz() {
    }

    bz(AdDisplayer r1_AdDisplayer) {
    }

    static int a(byte[] r2_byteA, int r3i) {
        return (r2_byteA[3] & 255) << 24 + 0 + (r2_byteA[2] & 255) << 16 + (r2_byteA[1] & 255) << 8 + (r2_byteA[0] & 255) << 0;
    }

    protected static g a(byte[] r9_byteA) {
        int r3i;
        int r0i = AdViewUtil.NETWORK_TYPE_ADCHINA;
        g r1_g = new g();
        ErrorCode r0_ErrorCode;
        if (r9_byteA.length == 1) {
            if (r9_byteA[0] == -29) {
                Log.e("Adwo SDK", "Adwo_Pid inexist");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INEXIST_PID");
            } else if (r9_byteA[0] == -32) {
                Log.e("Adwo SDK", "Server busy");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_SERVERBUSY");
            } else if (r9_byteA[0] == -28) {
                Log.e("Adwo SDK", "Adwo_Pid inactive");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INACTIVATED_PID");
            } else if (r9_byteA[0] == -31 || r9_byteA[0] == -24) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_IP");
            } else if (r9_byteA[0] == -23) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_LOWRANK");
            } else if (r9_byteA[0] == -25) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_IP");
            } else if (r9_byteA[0] == -30) {
                Log.e("Adwo SDK", "Unknown Error");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_UNKNOWN");
            } else if (r9_byteA[0] == -26) {
                Log.e("Adwo SDK", "Error in receiving data");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INCOMM");
            } else if (r9_byteA[0] == -27) {
                Log.e("Adwo SDK", "Error in request data");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_REQDATA");
            } else {
                Log.e("Adwo SDK", new StringBuilder("Unknown Error ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "OTHER_ERROR");
            }
            r1_g.m = r0_ErrorCode;
            return r1_g;
        } else if (r9_byteA.length < 7) {
            r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INCOMM");
            Log.e("Adwo SDK", "Error in receiving data");
            r1_g.m = r0_ErrorCode;
            return r1_g;
        } else {
            short r5s;
            int r6i;
            short r0s;
            g r2_g = new g();
            r2_g.a = a(r9_byteA, 0);
            r2_g.o = new String(r9_byteA, 4, 19);
            short r1s = a(r9_byteA[24], r9_byteA[25]);
            if (r1s != (short) 0) {
                r2_g.d = new String(r9_byteA, r0i, r1s);
                r0i = r1s + 26;
            }
            int r1i = r0i + 1;
            if (r9_byteA[r0i] == 70) {
                r3i = r1i + 1;
                r0i = r3i + 1;
                r1s = a(r9_byteA[r1i], r9_byteA[r3i]);
                if (r1s != (short) 0) {
                    r2_g.b = new String(r9_byteA, r0i, r1s);
                    r0i += r1s;
                }
            } else {
                r0i = r1i;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 69) {
                r3i = r1i + 1;
                r0i = r3i + 1;
                r1s = a(r9_byteA[r1i], r9_byteA[r3i]);
                if (r1s != (short) 0) {
                    r2_g.c = new String(r9_byteA, r0i, r1s);
                    r0i += r1s;
                }
            } else {
                r0i = r1i;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 68) {
                r0i = r1i + 1;
                byte r1b = r9_byteA[r1i];
                if (r1b != 0) {
                    r2_g.e = new String(r9_byteA, r0i, r1b);
                    r0i += r1b;
                }
            } else {
                r0i = r1i;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 66) {
                r0i = r1i + 1;
                if (r9_byteA[r1i] != 0) {
                    r1i = r0i + 1;
                    r3i = r1i + 1;
                    r2_g.f = a(r9_byteA[r0i], r9_byteA[r1i]);
                    r1i = r3i + 1;
                    r0i = r1i + 1;
                    r2_g.g = a(r9_byteA[r3i], r9_byteA[r1i]);
                }
            } else {
                r0i = r1i;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 73) {
                r1i++;
                r0i = r1i + 1;
                r2_g.k = r9_byteA[r1i];
            } else {
                r0i = r1i;
            }
            r1i = r0i + 1;
            r2_g.l = r9_byteA[r0i];
            r0i = r1i + 1;
            if (r9_byteA[r1i] == 76) {
                r1i = r0i + 1;
                byte r3b = r9_byteA[r0i];
                r0i = r1i + 1;
                r5s = a(r3b, r9_byteA[r1i]);
                if (r5s != (short) 0) {
                    r1s = (short) 0;
                    while (r1s < r5s) {
                        r3i = r0i + 1;
                        r6i = r3i + 1;
                        r0s = a(r9_byteA[r0i], r9_byteA[r3i]);
                        r2_g.h.add(new String(r9_byteA, r6i, r0s));
                        r1s++;
                        r0i = r6i + r0s;
                    }
                }
            }
            if (r0i >= r9_byteA.length) {
                return r2_g;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 83) {
                r3i = r1i + 1;
                r0i = r3i + 1;
                r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                if (r5s != (short) 0) {
                    r1s = (short) 0;
                    while (r1s < r5s) {
                        r3i = r0i + 1;
                        r6i = r3i + 1;
                        r0s = a(r9_byteA[r0i], r9_byteA[r3i]);
                        r2_g.i.add(new String(r9_byteA, r6i, r0s));
                        r1s++;
                        r0i = r6i + r0s;
                    }
                }
            } else {
                r0i = r1i;
            }
            if (r0i >= r9_byteA.length) {
                return r2_g;
            }
            r1i = r0i + 1;
            try {
                if (r9_byteA[r0i] == 80) {
                    r3i = r1i + 1;
                    try {
                        r0i = r3i + 1;
                        r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                        if (r5s != (short) 0) {
                            short r3s = (short) 0;
                            while (r3s < r5s) {
                                r1i = r0i + 1;
                                byte r4b = r9_byteA[r0i];
                                r0i = r1i + 1;
                                r1s = a(r4b, r9_byteA[r1i]);
                                String r4_String = new String(r9_byteA, r0i, r1s, Base.UTF8);
                                System.err.println(new StringBuilder("full screen packageName ").append(r4_String).toString());
                                r2_g.j.add(r4_String);
                                r3s++;
                                r0i = r1s + r0i;
                            }
                        }
                    } catch (Exception e) {
                        r0i = r3i;
                    }
                    if (r0i >= r9_byteA.length) {
                        return r2_g;
                    }
                    try {
                        r2_g.p = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                    } catch (Exception e_2) {
                    }
                    return r2_g;
                } else {
                    r0i = r1i;
                    if (r0i >= r9_byteA.length) {
                        return r2_g;
                    }
                    r2_g.p = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                    return r2_g;
                }
            } catch (Exception e_3) {
                r0i = r1i;
            }
        }
    }

    protected static short a(byte r2b, byte r3b) {
        return (short) ((r3b << 8) | (r2b & 255));
    }

    protected static byte[] a(byte r5b, byte r6b, byte r7b, byte r8b, byte r9b, byte r10b, boolean r11z, byte[] r12_byteA, byte[] r13_byteA, byte r14b, byte[] r15_byteA, short r16s, short r17s, short r18s, short r19s, String r20_String, double r21d, byte[] r23_byteA, byte[] r24_byteA, byte[] r25_byteA, byte[] r26_byteA, byte r27b, byte r28b, boolean r29z, double r30d, double r32d, String r34_String, HashMap r35_HashMap, HashMap r36_HashMap, HashMap r37_HashMap, int r38i) {
        byte[] r1_byteA;
        short r2s;
        ByteArrayOutputStream r3_ByteArrayOutputStream = new ByteArrayOutputStream();
        Entry r1_Entry;
        int r2i;
        int r1i;
        try {
            Entry r1_Entry_2;
            int r2i_2;
            int r1i_2;
            r3_ByteArrayOutputStream.write(new byte[2]);
            r3_ByteArrayOutputStream.write(r5b);
            r3_ByteArrayOutputStream.write(r6b);
            r3_ByteArrayOutputStream.write(r7b);
            r3_ByteArrayOutputStream.write(r9b);
            r3_ByteArrayOutputStream.write(r8b);
            r3_ByteArrayOutputStream.write(m.v);
            r3_ByteArrayOutputStream.write(r10b);
            if (r11z) {
                r3_ByteArrayOutputStream.write(0);
            } else {
                r3_ByteArrayOutputStream.write(1);
            }
            r3_ByteArrayOutputStream.write(r12_byteA);
            r3_ByteArrayOutputStream.write(AdDisplayer.a);
            r3_ByteArrayOutputStream.write(m.u);
            r3_ByteArrayOutputStream.write(73);
            if (r13_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r13_byteA.length);
                r3_ByteArrayOutputStream.write(r13_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(r14b);
            r3_ByteArrayOutputStream.write(87);
            if (r15_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r15_byteA.length);
                r3_ByteArrayOutputStream.write(r15_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(81);
            r3_ByteArrayOutputStream.write(XListViewFooter.STATE_NODATA);
            r3_ByteArrayOutputStream.write(a(r16s));
            r3_ByteArrayOutputStream.write(a(r17s));
            r3_ByteArrayOutputStream.write(71);
            r3_ByteArrayOutputStream.write(XListViewFooter.STATE_NODATA);
            r3_ByteArrayOutputStream.write(a(r18s));
            r3_ByteArrayOutputStream.write(a(r19s));
            r3_ByteArrayOutputStream.write(75);
            if (r20_String != null) {
                r1_byteA = r20_String.getBytes(Base.UTF8);
                r3_ByteArrayOutputStream.write(a((short) r1_byteA.length));
                r3_ByteArrayOutputStream.write(r1_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(83);
            r3_ByteArrayOutputStream.write(Base64.DONT_BREAK_LINES);
            r3_ByteArrayOutputStream.write(a(r21d));
            r3_ByteArrayOutputStream.write(77);
            if (r23_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r23_byteA.length);
                r3_ByteArrayOutputStream.write(r23_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(BDLocation.TypeCacheLocation);
            if (r24_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r24_byteA.length);
                r3_ByteArrayOutputStream.write(r24_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(80);
            if (r25_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r25_byteA.length);
                r3_ByteArrayOutputStream.write(r25_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(BDLocation.TypeOffLineLocationFail);
            if (r26_byteA != null) {
                r3_ByteArrayOutputStream.write((byte) r26_byteA.length);
                r3_ByteArrayOutputStream.write(r26_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(84);
            r3_ByteArrayOutputStream.write(1);
            r3_ByteArrayOutputStream.write(r27b);
            r3_ByteArrayOutputStream.write(86);
            if (m.m != null) {
                r1_byteA = m.m.getBytes(Base.UTF8);
                r3_ByteArrayOutputStream.write((byte) r1_byteA.length);
                r3_ByteArrayOutputStream.write(r1_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(74);
            r3_ByteArrayOutputStream.write(1);
            r3_ByteArrayOutputStream.write(r28b);
            r3_ByteArrayOutputStream.write(76);
            if (r29z) {
                r3_ByteArrayOutputStream.write(Base64.URL_SAFE);
                r3_ByteArrayOutputStream.write(a(r30d));
                r3_ByteArrayOutputStream.write(a(r32d));
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(QsbkApp.defaultDispatchPeriodInSeconds);
            r3_ByteArrayOutputStream.write(1);
            r3_ByteArrayOutputStream.write(AdDisplayer.b);
            r3_ByteArrayOutputStream.write(88);
            r3_ByteArrayOutputStream.write(1);
            r3_ByteArrayOutputStream.write(m.U);
            r3_ByteArrayOutputStream.write(72);
            r3_ByteArrayOutputStream.write(XListViewFooter.STATE_NODATA);
            r3_ByteArrayOutputStream.write(a(r38i));
            r3_ByteArrayOutputStream.write(82);
            if (r34_String != null) {
                r1_byteA = r34_String.getBytes(Base.UTF8);
                r3_ByteArrayOutputStream.write((byte) r1_byteA.length);
                r3_ByteArrayOutputStream.write(r1_byteA);
            } else {
                r3_ByteArrayOutputStream.write(0);
            }
            r3_ByteArrayOutputStream.write(BDLocation.TypeOffLineLocation);
            r3_ByteArrayOutputStream.write(a(r36_HashMap.size()));
            Iterator r4_Iterator = r36_HashMap.entrySet().iterator();
            while (r4_Iterator.hasNext()) {
                r1_Entry_2 = (Entry) r4_Iterator.next();
                r2i_2 = ((Integer) r1_Entry_2.getKey()).intValue();
                r1i_2 = ((Integer) r1_Entry_2.getValue()).intValue();
                r3_ByteArrayOutputStream.write(a(r2i_2));
                r3_ByteArrayOutputStream.write(a(r1i_2));
            }
            r3_ByteArrayOutputStream.write(69);
            r3_ByteArrayOutputStream.write(a(r37_HashMap.size()));
            r4_Iterator = r37_HashMap.entrySet().iterator();
            while (r4_Iterator.hasNext()) {
                r1_Entry = (Entry) r4_Iterator.next();
                r2i = ((Integer) r1_Entry.getKey()).intValue();
                r1i = ((Integer) r1_Entry.getValue()).intValue();
                r3_ByteArrayOutputStream.write(a(r2i));
                r3_ByteArrayOutputStream.write(a(r1i));
            }
            r3_ByteArrayOutputStream.write(BDLocation.TypeOffLineLocationNetworkFail);
            r3_ByteArrayOutputStream.write(a(r35_HashMap.size()));
            r4_Iterator = r35_HashMap.entrySet().iterator();
            while (r4_Iterator.hasNext()) {
                r1_Entry = (Entry) r4_Iterator.next();
                r2i = ((Integer) r1_Entry.getKey()).intValue();
                r1i = ((Integer) r1_Entry.getValue()).intValue();
                r3_ByteArrayOutputStream.write(a(r2i));
                r3_ByteArrayOutputStream.write(a(r1i));
            }
            r3_ByteArrayOutputStream.write(a(s.b));
            r3_ByteArrayOutputStream.write(90);
            r2s = (short) m.E.length;
            r3_ByteArrayOutputStream.write(a(r2s));
            short r1s = (short) 0;
            while (r1s < r2s) {
                r3_ByteArrayOutputStream.write(a(m.E[r1s]));
                r1s = (short) (r1s + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        r1_byteA = r3_ByteArrayOutputStream.toByteArray();
        r2s = (short) (r1_byteA.length - 2);
        r1_byteA[1] = (byte) ((65280 & r2s) >> 8);
        r1_byteA[0] = (byte) (r2s & 255);
        return r1_byteA;
    }

    protected static byte[] a(double r9d) {
        long r0j = Double.doubleToLongBits(r9d);
        byte[] r2_byteA = new byte[8];
        r2_byteA[7] = (byte) ((int) ((r0j >> 56) & 255));
        r2_byteA[6] = (byte) ((int) ((r0j >> 48) & 255));
        r2_byteA[5] = (byte) ((int) ((r0j >> 40) & 255));
        r2_byteA[4] = (byte) ((int) ((r0j >> 32) & 255));
        r2_byteA[3] = (byte) ((int) ((r0j >> 24) & 255));
        r2_byteA[2] = (byte) ((int) ((r0j >> 16) & 255));
        r2_byteA[1] = (byte) ((int) ((r0j >> 8) & 255));
        r2_byteA[0] = (byte) ((int) (r0j & 255));
        return r2_byteA;
    }

    protected static byte[] a(int r3i) {
        byte[] r0_byteA = new byte[4];
        r0_byteA[3] = (byte) (r3i >>> 24);
        r0_byteA[2] = (byte) (r3i >>> 16);
        r0_byteA[1] = (byte) (r3i >>> 8);
        r0_byteA[0] = (byte) r3i;
        return r0_byteA;
    }

    static byte[] a(short r3s) {
        byte[] r0_byteA = new byte[2];
        r0_byteA[0] = (byte) (r3s & 255);
        r0_byteA[1] = (byte) ((65280 & r3s) >> 8);
        return r0_byteA;
    }

    protected static j b(byte[] r9_byteA) {
        int r0i = AdViewUtil.NETWORK_TYPE_ADCHINA;
        int r1i = AdViewUtil.NETWORK_TYPE_CASEE;
        j r2_j = new j();
        ErrorCode r0_ErrorCode;
        if (r9_byteA.length == 1) {
            if (r9_byteA[0] == -29) {
                Log.e("Adwo SDK", "Adwo_Pid inexist");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INEXIST_PID");
            } else if (r9_byteA[0] == -32) {
                Log.e("Adwo SDK", "Server busy");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_SERVERBUSY");
            } else if (r9_byteA[0] == -28) {
                Log.e("Adwo SDK", "Adwo_Pid inactive");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INACTIVATED_PID");
            } else if (r9_byteA[0] == -31 || r9_byteA[0] == -24) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_IP");
            } else if (r9_byteA[0] == -23) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_LOWRANK");
            } else if (r9_byteA[0] == -25) {
                Log.e("Adwo SDK", new StringBuilder("No ad available: ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_NOAD_IP");
            } else if (r9_byteA[0] == -30) {
                Log.e("Adwo SDK", "Unknown Error");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_UNKNOWN");
            } else if (r9_byteA[0] == -26) {
                Log.e("Adwo SDK", "Error in receiving data");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INCOMM");
            } else if (r9_byteA[0] == -27) {
                Log.e("Adwo SDK", "Error in request data");
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_REQDATA");
            } else {
                Log.e("Adwo SDK", new StringBuilder("Unknown Error ").append(r9_byteA[0]).toString());
                r0_ErrorCode = new ErrorCode(r9_byteA[0], "OTHER_ERROR");
            }
            r2_j.a(r0_ErrorCode);
            return r2_j;
        } else if (r9_byteA.length < 7) {
            r0_ErrorCode = new ErrorCode(r9_byteA[0], "ERR_INCOMM");
            Log.e("Adwo SDK", "Error in receiving data");
            r2_j.a(r0_ErrorCode);
            return r2_j;
        } else {
            short r1s;
            int r3i;
            r2_j.a = a(r9_byteA, 0);
            r2_j.k = new String(r9_byteA, 4, 19);
            if (r9_byteA[23] == BDLocation.TypeCacheLocation) {
                r1s = a(r9_byteA[r1i], r9_byteA[25]);
                if (r1s != (short) 0) {
                    r2_j.d = new String(r9_byteA, r0i, r1s);
                    r0i = r1s + 26;
                }
            } else {
                r0i = 24;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 70) {
                r3i = r1i + 1;
                r0i = r3i + 1;
                r1s = a(r9_byteA[r1i], r9_byteA[r3i]);
                if (r1s != (short) 0) {
                    r2_j.c = new String(r9_byteA, r0i, r1s);
                    r0i += r1s;
                }
            } else {
                r0i = r1i;
            }
            if (r0i >= r9_byteA.length) {
                return r2_j;
            }
            short r5s;
            int r6i;
            short r0s;
            r1i = r0i + 1;
            r0i = r9_byteA[r0i] == 73 ? r1i + 1 + 1 : r1i;
            r1i = r0i + 1;
            r2_j.m = r9_byteA[r0i];
            r0i = r1i + 1;
            if (r9_byteA[r1i] == 76) {
                r1i = r0i + 1;
                byte r3b = r9_byteA[r0i];
                r0i = r1i + 1;
                r5s = a(r3b, r9_byteA[r1i]);
                if (r5s != (short) 0) {
                    r1s = (short) 0;
                    while (r1s < r5s) {
                        r3i = r0i + 1;
                        r6i = r3i + 1;
                        r0s = a(r9_byteA[r0i], r9_byteA[r3i]);
                        r2_j.e.add(new String(r9_byteA, r6i, r0s));
                        r1s++;
                        r0i = r6i + r0s;
                    }
                }
            }
            if (r0i >= r9_byteA.length) {
                return r2_j;
            }
            r1i = r0i + 1;
            if (r9_byteA[r0i] == 83) {
                r3i = r1i + 1;
                r0i = r3i + 1;
                r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                if (r5s != (short) 0) {
                    r1s = (short) 0;
                    while (r1s < r5s) {
                        r3i = r0i + 1;
                        r6i = r3i + 1;
                        r0s = a(r9_byteA[r0i], r9_byteA[r3i]);
                        r2_j.f.add(new String(r9_byteA, r6i, r0s));
                        r1s++;
                        r0i = r6i + r0s;
                    }
                }
            } else {
                r0i = r1i;
            }
            if (r0i >= r9_byteA.length) {
                return r2_j;
            }
            r1i = r0i + 1;
            try {
                short r3s;
                byte r4b;
                String r4_String;
                if (r9_byteA[r0i] == 75) {
                    r3i = r1i + 1;
                    try {
                        r0i = r3i + 1;
                        r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                    } catch (Exception e) {
                        r0i = r3i;
                    }
                    if (r5s != (short) 0) {
                        try {
                            byte[] r6_byteA = new byte[r5s];
                            r3i = 0;
                            while (r3i < r6_byteA.length) {
                                r1i = r0i + 1;
                                try {
                                    r6_byteA[r3i] = r9_byteA[r0i];
                                    r3i++;
                                    r0i = r1i;
                                } catch (Exception e_2) {
                                    r0i = r1i;
                                }
                            }
                            if (r5s == (short) 2 && r6_byteA[1] == (byte) 1) {
                                r2_j.h = true;
                            }
                        } catch (Exception e_3) {
                        }
                        if (r5s == (short) 3 && r6_byteA[1] == 0) {
                            r2_j.h = false;
                        }
                        if (r0i < r9_byteA.length) {
                            return r2_j;
                        }
                        r1i = r0i + 1;
                        try {
                            if (r9_byteA[r0i] == 80) {
                                r3i = r1i + 1;
                                try {
                                    r0i = r3i + 1;
                                    try {
                                        r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                                        if (r5s != (short) 0) {
                                            r3s = (short) 0;
                                            while (r3s < r5s) {
                                                r1i = r0i + 1;
                                                r4b = r9_byteA[r0i];
                                                r0i = r1i + 1;
                                                r1s = a(r4b, r9_byteA[r1i]);
                                                r4_String = new String(r9_byteA, r0i, r1s, Base.UTF8);
                                                System.out.println(new StringBuilder("packageName ").append(r4_String).toString());
                                                r2_j.g.add(r4_String);
                                                r3s++;
                                                r0i = r1s + r0i;
                                            }
                                        }
                                    } catch (Exception e_4) {
                                    }
                                } catch (Exception e_5) {
                                    r0i = r3i;
                                }
                                if (r0i < r9_byteA.length) {
                                    return r2_j;
                                }
                                try {
                                    r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                                } catch (Exception e_6) {
                                }
                                return r2_j;
                            } else {
                                r0i = r1i;
                                if (r0i < r9_byteA.length) {
                                    return r2_j;
                                }
                                r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                                return r2_j;
                            }
                        } catch (Exception e_7) {
                            r0i = r1i;
                        }
                    }
                    if (r0i < r9_byteA.length) {
                        return r2_j;
                    }
                    r1i = r0i + 1;
                    if (r9_byteA[r0i] == 80) {
                        r0i = r1i;
                        if (r0i < r9_byteA.length) {
                            return r2_j;
                        }
                        r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                        return r2_j;
                    } else {
                        r3i = r1i + 1;
                        r0i = r3i + 1;
                        r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                        if (r5s != (short) 0) {
                            if (r0i < r9_byteA.length) {
                                return r2_j;
                            }
                            r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                            return r2_j;
                        } else {
                            r3s = (short) 0;
                            while (r3s < r5s) {
                                r1i = r0i + 1;
                                r4b = r9_byteA[r0i];
                                r0i = r1i + 1;
                                r1s = a(r4b, r9_byteA[r1i]);
                                r4_String = new String(r9_byteA, r0i, r1s, Base.UTF8);
                                System.out.println(new StringBuilder("packageName ").append(r4_String).toString());
                                r2_j.g.add(r4_String);
                                r3s++;
                                r0i = r1s + r0i;
                            }
                            if (r0i < r9_byteA.length) {
                                return r2_j;
                            }
                            r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                            return r2_j;
                        }
                    }
                } else {
                    r0i = r1i;
                    if (r0i < r9_byteA.length) {
                        return r2_j;
                    }
                    r1i = r0i + 1;
                    if (r9_byteA[r0i] == 80) {
                        r3i = r1i + 1;
                        r0i = r3i + 1;
                        r5s = a(r9_byteA[r1i], r9_byteA[r3i]);
                        if (r5s != (short) 0) {
                            r3s = (short) 0;
                            while (r3s < r5s) {
                                r1i = r0i + 1;
                                r4b = r9_byteA[r0i];
                                r0i = r1i + 1;
                                r1s = a(r4b, r9_byteA[r1i]);
                                r4_String = new String(r9_byteA, r0i, r1s, Base.UTF8);
                                System.out.println(new StringBuilder("packageName ").append(r4_String).toString());
                                r2_j.g.add(r4_String);
                                r3s++;
                                r0i = r1s + r0i;
                            }
                        }
                        if (r0i < r9_byteA.length) {
                            return r2_j;
                        }
                        r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                        return r2_j;
                    } else {
                        r0i = r1i;
                        if (r0i < r9_byteA.length) {
                            return r2_j;
                        }
                        r2_j.l = a(r9_byteA[r0i], r9_byteA[r0i + 1]);
                        return r2_j;
                    }
                }
            } catch (Exception e_8) {
                r0i = r1i;
            }
        }
    }
}
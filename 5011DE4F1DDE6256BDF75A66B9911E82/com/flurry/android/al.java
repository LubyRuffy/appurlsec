package com.flurry.android;

import android.content.Context;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class al {
    private Context a;
    private ag b;
    private a c;
    private volatile long d;
    private g e;
    private g f;
    private Map g;
    private Map h;
    private Map i;
    private Map j;
    private volatile boolean k;

    al() {
        this.e = new g(50);
        this.f = new g(50);
        this.g = new HashMap();
        this.h = new HashMap();
        this.i = new HashMap();
        this.j = new HashMap();
    }

    private synchronized o a(byte r3b) {
        return (o) this.i.get(Byte.valueOf(r3b));
    }

    private void a(int r2i) {
        this.k = !this.g.isEmpty();
        if (this.k) {
            this.b.a(r2i);
        }
    }

    private void a(DataInputStream r9_DataInputStream) {
        int r0i = 0;
        i.a("FlurryAgent", "Reading cache");
        if (r9_DataInputStream.readUnsignedShort() != XListViewHeader.STATE_REFRESHING) {
        } else {
            long r3j;
            this.d = r9_DataInputStream.readLong();
            int r2i = r9_DataInputStream.readUnsignedShort();
            this.e = new g(50);
            int r1i = 0;
            while (r1i < r2i) {
                r3j = r9_DataInputStream.readLong();
                AdImage r5_AdImage = new AdImage();
                r5_AdImage.a(r9_DataInputStream);
                this.e.a(Long.valueOf(r3j), r5_AdImage);
                r1i++;
            }
            r2i = r9_DataInputStream.readUnsignedShort();
            this.f = new g(50);
            r1i = 0;
            while (r1i < r2i) {
                r3j = r9_DataInputStream.readLong();
                m r5_m = new m();
                if (r9_DataInputStream.readBoolean()) {
                    r5_m.a = r9_DataInputStream.readUTF();
                }
                if (r9_DataInputStream.readBoolean()) {
                    r5_m.b = r9_DataInputStream.readUTF();
                }
                r5_m.c = r9_DataInputStream.readInt();
                this.f.a(Long.valueOf(r3j), r5_m);
                r1i++;
            }
            r2i = r9_DataInputStream.readUnsignedShort();
            this.h = new HashMap(r2i);
            r1i = 0;
            while (r1i < r2i) {
                this.h.put(r9_DataInputStream.readUTF(), new q(r9_DataInputStream));
                r1i++;
            }
            int r3i = r9_DataInputStream.readUnsignedShort();
            this.g = new HashMap(r3i);
            r2i = 0;
            while (r2i < r3i) {
                String r4_String = r9_DataInputStream.readUTF();
                int r5i = r9_DataInputStream.readUnsignedShort();
                Object r6_Object = new Object[r5i];
                r1i = 0;
                while (r1i < r5i) {
                    ah r7_ah = new ah();
                    r7_ah.a((DataInput)r9_DataInputStream);
                    r6_Object[r1i] = r7_ah;
                    r1i++;
                }
                this.g.put(r4_String, r6_Object);
                r2i++;
            }
            r2i = r9_DataInputStream.readUnsignedShort();
            this.i = new HashMap();
            r1i = 0;
            while (r1i < r2i) {
                byte r3b = r9_DataInputStream.readByte();
                o r4_o = new o();
                r4_o.b(r9_DataInputStream);
                this.i.put(Byte.valueOf(r3b), r4_o);
                r1i++;
            }
            r1i = r9_DataInputStream.readUnsignedShort();
            this.j = new HashMap(r1i);
            while (r0i < r1i) {
                this.j.put(Short.valueOf(r9_DataInputStream.readShort()), Long.valueOf(r9_DataInputStream.readLong()));
                r0i++;
            }
            f();
            i.a("FlurryAgent", "Cache read, num images: " + this.e.a());
        }
    }

    private void a(DataOutputStream r9_DataOutputStream) {
        Entry r0_Entry;
        Entry r1_Entry;
        r9_DataOutputStream.writeShort(XListViewHeader.STATE_REFRESHING);
        r9_DataOutputStream.writeLong(this.d);
        List r0_List = this.e.b();
        r9_DataOutputStream.writeShort(r0_List.size());
        Iterator r4_Iterator = r0_List.iterator();
        while (r4_Iterator.hasNext()) {
            r0_Entry = (Entry) r4_Iterator.next();
            r9_DataOutputStream.writeLong(((Long) r0_Entry.getKey()).longValue());
            AdImage r0_AdImage = (AdImage) r0_Entry.getValue();
            r9_DataOutputStream.writeLong(r0_AdImage.a);
            r9_DataOutputStream.writeInt(r0_AdImage.b);
            r9_DataOutputStream.writeInt(r0_AdImage.c);
            r9_DataOutputStream.writeUTF(r0_AdImage.d);
            r9_DataOutputStream.writeInt(r0_AdImage.e.length);
            r9_DataOutputStream.write(r0_AdImage.e);
        }
        r0_List = this.f.b();
        r9_DataOutputStream.writeShort(r0_List.size());
        r4_Iterator = r0_List.iterator();
        while (r4_Iterator.hasNext()) {
            boolean r1z;
            r1_Entry = (Entry) r4_Iterator.next();
            r9_DataOutputStream.writeLong(((Long) r1_Entry.getKey()).longValue());
            m r0_m = (m) r1_Entry.getValue();
            r1z = r0_m.a != null;
            r9_DataOutputStream.writeBoolean(r1z);
            if (r1z) {
                r9_DataOutputStream.writeUTF(r0_m.a);
            }
            r1z = r0_m.b != null;
            r9_DataOutputStream.writeBoolean(r1z);
            if (r1z) {
                r9_DataOutputStream.writeUTF(r0_m.b);
            }
            r9_DataOutputStream.writeInt(r0_m.c);
        }
        r9_DataOutputStream.writeShort(this.h.size());
        Iterator r2_Iterator = this.h.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            r1_Entry = (Entry) r2_Iterator.next();
            r9_DataOutputStream.writeUTF((String) r1_Entry.getKey());
            q r0_q = (q) r1_Entry.getValue();
            r9_DataOutputStream.writeUTF(r0_q.a);
            r9_DataOutputStream.writeByte(r0_q.b);
            r9_DataOutputStream.writeByte(r0_q.c);
        }
        r9_DataOutputStream.writeShort(this.g.size());
        r4_Iterator = this.g.entrySet().iterator();
        while (r4_Iterator.hasNext()) {
            int r1i;
            r0_Entry = (Entry) r4_Iterator.next();
            r9_DataOutputStream.writeUTF((String) r0_Entry.getKey());
            ah[] r0_ahA = (ah[]) r0_Entry.getValue();
            r1i = r0_ahA == null ? 0 : r0_ahA.length;
            r9_DataOutputStream.writeShort(r1i);
            int r2i = 0;
            while (r2i < r1i) {
                ah r5_ah = r0_ahA[r2i];
                r9_DataOutputStream.writeLong(r5_ah.a);
                r9_DataOutputStream.writeLong(r5_ah.b);
                r9_DataOutputStream.writeUTF(r5_ah.d);
                r9_DataOutputStream.writeUTF(r5_ah.c);
                r9_DataOutputStream.writeLong(r5_ah.e);
                r9_DataOutputStream.writeLong(r5_ah.f.longValue());
                r9_DataOutputStream.writeByte(r5_ah.g.length);
                r9_DataOutputStream.write(r5_ah.g);
                r2i++;
            }
        }
        r9_DataOutputStream.writeShort(this.i.size());
        r2_Iterator = this.i.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            r0_Entry = (Entry) r2_Iterator.next();
            r9_DataOutputStream.writeByte(((Byte) r0_Entry.getKey()).byteValue());
            ((o) r0_Entry.getValue()).a((DataOutput)r9_DataOutputStream);
        }
        r9_DataOutputStream.writeShort(this.j.size());
        r2_Iterator = this.j.entrySet().iterator();
        while (r2_Iterator.hasNext()) {
            r1_Entry = (Entry) r2_Iterator.next();
            r9_DataOutputStream.writeShort(((Short) r1_Entry.getKey()).shortValue());
            r9_DataOutputStream.writeLong(((Long) r1_Entry.getValue()).longValue());
        }
    }

    private static void a(File r2_File) {
        if (!r2_File.delete()) {
            i.b("FlurryAgent", "Cannot delete cached ads");
        }
    }

    private void f() {
        Iterator r0_Iterator = this.i.values().iterator();
        while (r0_Iterator.hasNext()) {
            r0_Iterator.next();
        }
        Iterator r2_Iterator = this.g.values().iterator();
        while (r2_Iterator.hasNext()) {
            ah[] r0_ahA = (ah[]) r2_Iterator.next();
            if (r0_ahA != null) {
                int r3i = r0_ahA.length;
                int r1i = 0;
                while (r1i < r3i) {
                    ah r4_ah = r0_ahA[r1i];
                    r4_ah.h = b(r4_ah.f.longValue());
                    if (r4_ah.h == null) {
                        i.b("FlurryAgent", "Ad " + r4_ah.d + " has no image");
                    }
                    if (a(r4_ah.a) == null) {
                        i.b("FlurryAgent", "Ad " + r4_ah.d + " has no pricing");
                    }
                    r1i++;
                }
            }
        }
        Iterator r1_Iterator = this.h.values().iterator();
        while (r1_Iterator.hasNext()) {
            q r0_q = (q) r1_Iterator.next();
            r0_q.d = a(r0_q.c);
            if (r0_q.d == null) {
                i.d("FlurryAgent", "No ad theme found for " + r0_q.c);
            }
        }
    }

    private String g() {
        return ".flurryappcircle." + Integer.toString(this.c.a.hashCode(), Base64.URL_SAFE);
    }

    final synchronized AdImage a(short r3s) {
        Long r0_Long;
        r0_Long = (Long) this.j.get(Short.valueOf((short) 1));
        return r0_Long == null ? null : b(r0_Long.longValue());
    }

    final synchronized m a(long r3j) {
        return (m) this.f.a(Long.valueOf(r3j));
    }

    final synchronized Set a() {
        return this.e.c();
    }

    final void a(Context r1_Context, ag r2_ag, a r3_a) {
        this.a = r1_Context;
        this.b = r2_ag;
        this.c = r3_a;
    }

    final synchronized void a(Map r6_Map, Map r7_Map, Map r8_Map, Map r9_Map, Map r10_Map, Map r11_Map) {
        Entry r0_Entry;
        this.d = System.currentTimeMillis();
        Iterator r1_Iterator = r9_Map.entrySet().iterator();
        while (r1_Iterator.hasNext()) {
            r0_Entry = (Entry) r1_Iterator.next();
            if (r0_Entry.getValue() != null) {
                this.e.a(r0_Entry.getKey(), r0_Entry.getValue());
            }
        }
        r1_Iterator = r10_Map.entrySet().iterator();
        while (r1_Iterator.hasNext()) {
            r0_Entry = (Entry) r1_Iterator.next();
            if (r0_Entry.getValue() != null) {
                this.f.a(r0_Entry.getKey(), r0_Entry.getValue());
            }
        }
        Iterator r3_Iterator;
        q r1_q;
        ah[] r2_ahA;
        o r0_o;
        if (r7_Map == null || r7_Map.isEmpty()) {
            if (r8_Map == null || r8_Map.isEmpty()) {
                if (r11_Map == null || r11_Map.isEmpty()) {
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                        }
                        r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                        if (r0_o == null) {
                            r1_q.d = r0_o;
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                } else {
                    this.j = r11_Map;
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                            } else {
                                r1_q.d = r0_o;
                            }
                        } else {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                                r1_q.d = r0_o;
                            }
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                }
            } else {
                this.i = r8_Map;
                if (r11_Map == null || r11_Map.isEmpty()) {
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                        }
                        r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                        if (r0_o == null) {
                        } else {
                            r1_q.d = r0_o;
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                } else {
                    this.j = r11_Map;
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                                r1_q.d = r0_o;
                            }
                        } else {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                            } else {
                                r1_q.d = r0_o;
                            }
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                }
            }
        } else {
            this.h = r7_Map;
            if (r8_Map == null || r8_Map.isEmpty()) {
                if (r11_Map == null || r11_Map.isEmpty()) {
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                        }
                        r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                        if (r0_o == null) {
                            r1_q.d = r0_o;
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                } else {
                    this.j = r11_Map;
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                            } else {
                                r1_q.d = r0_o;
                            }
                        } else {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                                r1_q.d = r0_o;
                            }
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                }
            } else {
                this.i = r8_Map;
                if (r11_Map == null || r11_Map.isEmpty()) {
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                        }
                        r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                        if (r0_o == null) {
                        } else {
                            r1_q.d = r0_o;
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                } else {
                    this.j = r11_Map;
                    this.g = new HashMap();
                    r3_Iterator = r7_Map.entrySet().iterator();
                    while (r3_Iterator.hasNext()) {
                        r0_Entry = (Entry) r3_Iterator.next();
                        r1_q = (q) r0_Entry.getValue();
                        r2_ahA = (ah[]) r6_Map.get(Byte.valueOf(r1_q.b));
                        if (r2_ahA == null) {
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                                r1_q.d = r0_o;
                            }
                        } else {
                            this.g.put(r0_Entry.getKey(), r2_ahA);
                            r0_o = (o) r8_Map.get(Byte.valueOf(r1_q.c));
                            if (r0_o == null) {
                            } else {
                                r1_q.d = r0_o;
                            }
                        }
                    }
                    f();
                    a((int)HttpUtils.HTTP_OK_CODE);
                }
            }
        }
    }

    final synchronized ah[] a(String r3_String) {
        ah[] r0_ahA;
        r0_ahA = (ah[]) this.g.get(r3_String);
        if (r0_ahA == null) {
            r0_ahA = (ah[]) this.g.get(RContactStorage.PRIMARY_KEY);
        }
        return r0_ahA;
    }

    final synchronized AdImage b(long r3j) {
        return (AdImage) this.e.a(Long.valueOf(r3j));
    }

    final synchronized q b(String r3_String) {
        q r0_q;
        r0_q = (q) this.h.get(r3_String);
        if (r0_q == null) {
            r0_q = (q) this.h.get(RContactStorage.PRIMARY_KEY);
        }
        return r0_q;
    }

    final boolean b() {
        return this.k;
    }

    final long c() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized void d() {
        /*
        r5_this = this;
        monitor-enter(r5);
        r0 = r5.a;	 //Catch:{ all -> 0x0045 }
        r1 = r5.g();	 //Catch:{ all -> 0x0045 }
        r3 = r0.getFileStreamPath(r1);	 //Catch:{ all -> 0x0045 }
        r0 = r3.exists();	 //Catch:{ all -> 0x0045 }
        if (r0 == 0) goto L_0x004e;
    L_0x0011:
        r2 = 0;
        r0 = new java.io.FileInputStream;	 //Catch:{ Throwable -> 0x006d, all -> 0x0048 }
        r0.<init>(r3);	 //Catch:{ Throwable -> 0x006d, all -> 0x0048 }
        r1 = new java.io.DataInputStream;	 //Catch:{ Throwable -> 0x006d, all -> 0x0048 }
        r1.<init>(r0);	 //Catch:{ Throwable -> 0x006d, all -> 0x0048 }
        r0 = r1.readUnsignedShort();	 //Catch:{ Throwable -> 0x0036 }
        r2 = 46587; // 0xb5fb float:6.5282E-41 double:2.3017E-319;
        if (r0 != r2) goto L_0x0032;
    L_0x0025:
        r5.a(r1);	 //Catch:{ Throwable -> 0x0036 }
        r0 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        r5.a(r0);	 //Catch:{ Throwable -> 0x0036 }
    L_0x002d:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x0045 }
    L_0x0030:
        monitor-exit(r5);
        return;
    L_0x0032:
        a(r3);	 //Catch:{ Throwable -> 0x0036 }
        goto L_0x002d;
    L_0x0036:
        r0 = move-exception;
    L_0x0037:
        r2 = "FlurryAgent";
        r4 = "Discarding cache";
        com.flurry.android.i.a(r2, r4, r0);	 //Catch:{ all -> 0x006b }
        a(r3);	 //Catch:{ all -> 0x006b }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x0045 }
        goto L_0x0030;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0048:
        r0 = move-exception;
        r1 = r2;
    L_0x004a:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x0045 }
        throw r0;	 //Catch:{ all -> 0x0045 }
    L_0x004e:
        r0 = "FlurryAgent";
        r1 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0045 }
        r1.<init>();	 //Catch:{ all -> 0x0045 }
        r2 = "cache file does not exist, path=";
        r1 = r1.append(r2);	 //Catch:{ all -> 0x0045 }
        r2 = r3.getAbsolutePath();	 //Catch:{ all -> 0x0045 }
        r1 = r1.append(r2);	 //Catch:{ all -> 0x0045 }
        r1 = r1.toString();	 //Catch:{ all -> 0x0045 }
        com.flurry.android.i.c(r0, r1);	 //Catch:{ all -> 0x0045 }
        goto L_0x0030;
    L_0x006b:
        r0 = move-exception;
        goto L_0x004a;
    L_0x006d:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0037;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized void e() {
        /*
        r5_this = this;
        r1 = 0;
        monitor-enter(r5);
        r0 = r5.a;	 //Catch:{ Throwable -> 0x0054 }
        r2 = r5.g();	 //Catch:{ Throwable -> 0x0054 }
        r0 = r0.getFileStreamPath(r2);	 //Catch:{ Throwable -> 0x0054 }
        r2 = r0.getParentFile();	 //Catch:{ Throwable -> 0x0054 }
        r3 = r2.mkdirs();	 //Catch:{ Throwable -> 0x0054 }
        if (r3 != 0) goto L_0x003a;
    L_0x0016:
        r3 = r2.exists();	 //Catch:{ Throwable -> 0x0054 }
        if (r3 != 0) goto L_0x003a;
    L_0x001c:
        r0 = "FlurryAgent";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Throwable -> 0x0054 }
        r3.<init>();	 //Catch:{ Throwable -> 0x0054 }
        r4 = "Unable to create persistent dir: ";
        r3 = r3.append(r4);	 //Catch:{ Throwable -> 0x0054 }
        r2 = r3.append(r2);	 //Catch:{ Throwable -> 0x0054 }
        r2 = r2.toString();	 //Catch:{ Throwable -> 0x0054 }
        com.flurry.android.i.b(r0, r2);	 //Catch:{ Throwable -> 0x0054 }
        r0 = 0;
        com.flurry.android.ad.a(r0);	 //Catch:{ all -> 0x0051 }
    L_0x0038:
        monitor-exit(r5);
        return;
    L_0x003a:
        r3 = new java.io.FileOutputStream;	 //Catch:{ Throwable -> 0x0054 }
        r3.<init>(r0);	 //Catch:{ Throwable -> 0x0054 }
        r2 = new java.io.DataOutputStream;	 //Catch:{ Throwable -> 0x0054 }
        r2.<init>(r3);	 //Catch:{ Throwable -> 0x0054 }
        r0 = 46587; // 0xb5fb float:6.5282E-41 double:2.3017E-319;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0068, all -> 0x0065 }
        r5.a(r2);	 //Catch:{ Throwable -> 0x0068, all -> 0x0065 }
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x0051 }
        goto L_0x0038;
    L_0x0051:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0054:
        r0 = move-exception;
    L_0x0055:
        r2 = "FlurryAgent";
        r3 = "";
        com.flurry.android.i.b(r2, r3, r0);	 //Catch:{ all -> 0x0060 }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x0051 }
        goto L_0x0038;
    L_0x0060:
        r0 = move-exception;
    L_0x0061:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x0051 }
        throw r0;	 //Catch:{ all -> 0x0051 }
    L_0x0065:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0061;
    L_0x0068:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0055;
        */

    }

    public final String toString() {
        StringBuilder r2_StringBuilder = new StringBuilder();
        r2_StringBuilder.append("{");
        r2_StringBuilder.append("adImages (" + this.e.b().size() + "),\n");
        r2_StringBuilder.append("adBlock (" + this.g.size() + "):").append(",\n");
        Iterator r3_Iterator = this.g.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r2_StringBuilder.append("\t" + ((String) r0_Entry.getKey()) + ": " + Arrays.toString((Object[]) r0_Entry.getValue()));
        }
        r2_StringBuilder.append("adHooks (" + this.h.size() + "):" + this.h).append(",\n");
        r2_StringBuilder.append("adThemes (" + this.i.size() + "):" + this.i).append(",\n");
        r2_StringBuilder.append("auxMap (" + this.j.size() + "):" + this.j).append(",\n");
        r2_StringBuilder.append("}");
        return r2_StringBuilder.toString();
    }
}
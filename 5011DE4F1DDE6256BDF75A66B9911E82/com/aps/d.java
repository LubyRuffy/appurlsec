package com.aps;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

// compiled from: Cache.java
public class d {
    private static d a;
    private Hashtable<String, List<a>> b;
    private long c;

    // compiled from: Cache.java
    class a {
        private c b;
        private String c;

        protected a() {
            this.b = null;
            this.c = null;
        }

        public c a_() {
            return this.b;
        }

        public void a_(c r1_c) {
            this.b = r1_c;
        }

        public void a_(String r3_String) {
            this.c = r3_String.replace("##", "#");
        }

        public String b() {
            return this.c;
        }
    }

    static {
        a = null;
    }

    private d() {
        this.b = new Hashtable();
        this.c = 0;
    }

    private double a(double[] r12_doubleA, double[] r13_doubleA) {
        double r1d = 0.0d;
        int r0i = 0;
        double r3d = 0.0d;
        double r5d = 0.0d;
        while (r0i < r12_doubleA.length) {
            r3d += r12_doubleA[r0i] * r12_doubleA[r0i];
            r1d += r13_doubleA[r0i] * r13_doubleA[r0i];
            r5d += r12_doubleA[r0i] * r13_doubleA[r0i];
            r0i++;
        }
        return r5d / (Math.sqrt(r1d) * Math.sqrt(r3d));
    }

    private a a(String r22_String, StringBuilder r23_StringBuilder, String r24_String, String r25_String) {
        Iterator r5_Iterator;
        a r6_a = null;
        Hashtable r14_Hashtable = new Hashtable();
        Hashtable r15_Hashtable = new Hashtable();
        Hashtable r16_Hashtable = new Hashtable();
        if (r25_String.equals("mem")) {
            r5_Iterator = this.b.entrySet().iterator();
        } else if (r25_String.equals("db")) {
            r5_Iterator = null;
        } else {
            r5_Iterator = null;
        }
        int r7i = 1;
        while (r5_Iterator != null && r5_Iterator.hasNext()) {
            List r12_List;
            int r13i;
            if (r7i != 0) {
                r11_String = r22_String;
                r12_List = (List) this.b.get(r22_String);
                r13i = 0;
            } else {
                Entry r3_Entry = (Entry) r5_Iterator.next();
                r11_String = (String) r3_Entry.getKey();
                r12_List = (List) r3_Entry.getValue();
                r13i = r7i;
            }
            if (r6_a != null) {
                break;
            } else if (r12_List == null) {
                r7i = r13i;
            } else {
                a r3_a;
                r7i = 0;
                while (r7i < r12_List.size()) {
                    r3_a = (a) r12_List.get(r7i);
                    if (TextUtils.isEmpty(r3_a.b()) || TextUtils.isEmpty(r23_StringBuilder) || r11_String.indexOf(r24_String) == -1) {
                        r7i++;
                    } else if ((!a(r3_a.b(), r23_StringBuilder)) || r3_a.a().e() > 800.0f) {
                        a(r3_a.b(), r14_Hashtable);
                        a(r23_StringBuilder.toString(), r15_Hashtable);
                        r16_Hashtable.clear();
                        Iterator r8_Iterator = r14_Hashtable.keySet().iterator();
                        while (r8_Iterator.hasNext()) {
                            r16_Hashtable.put((String) r8_Iterator.next(), RContactStorage.PRIMARY_KEY);
                        }
                        r8_Iterator = r15_Hashtable.keySet().iterator();
                        while (r8_Iterator.hasNext()) {
                            r16_Hashtable.put((String) r8_Iterator.next(), RContactStorage.PRIMARY_KEY);
                        }
                        Set r17_Set = r16_Hashtable.keySet();
                        double[] r18_doubleA = new double[r17_Set.size()];
                        double[] r19_doubleA = new double[r17_Set.size()];
                        Iterator r20_Iterator = r17_Set.iterator();
                        int r8i = 0;
                        while (r20_Iterator.hasNext()) {
                            String r4_String = (String) r20_Iterator.next();
                            r18_doubleA[r8i] = r14_Hashtable.containsKey(r4_String) ? 1.0d : 0.0d;
                            r19_doubleA[r8i] = r15_Hashtable.containsKey(r4_String) ? 1.0d : 0.0d;
                            r8i++;
                        }
                        r17_Set.clear();
                        double r8d = a(r18_doubleA, r19_doubleA);
                        if (r25_String.equals("mem")) {
                            if (r8d > 0.8500000238418579d) {
                                break;
                            }
                            r7i++;
                        } else if ((!r25_String.equals("db")) || r8d <= 0.8500000238418579d) {
                            r7i++;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                r3_a = r6_a;
                r7i = r13i;
                r6_a = r3_a;
            }
        }
        r14_Hashtable.clear();
        r15_Hashtable.clear();
        r16_Hashtable.clear();
        return r6_a;
    }

    static synchronized d a() {
        d r0_d;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            r0_d = a;
        }
        return r0_d;
    }

    private void a(String r6_String, Hashtable<String, String> r7_Hashtable_String__String) {
        r7_Hashtable_String__String.clear();
        String[] r1_StringA = r6_String.split("#");
        int r2i = r1_StringA.length;
        int r0i = 0;
        while (r0i < r2i) {
            String r3_String = r1_StringA[r0i];
            if (r3_String.length() > 0) {
                r7_Hashtable_String__String.put(r3_String, RContactStorage.PRIMARY_KEY);
            }
            r0i++;
        }
    }

    private boolean a(String r6_String, StringBuilder r7_StringBuilder) {
        int r1i = r6_String.indexOf(",access");
        if (r1i == -1 || r1i < 17 || r7_StringBuilder.indexOf(",access") == -1) {
            return false;
        }
        String r1_String = r6_String.substring(r1i - 17, r1i);
        return r7_StringBuilder.toString().indexOf(new StringBuilder().append(r1_String).append(",access").toString()) != -1;
    }

    c a(String r4_String, StringBuilder r5_StringBuilder, String r6_String) {
        if ((!r6_String.equals("mem")) || f.k) {
            if (r6_String.equals("mem") && b()) {
                c();
                return null;
            } else {
                a r0_a;
                String r0_String = RContactStorage.PRIMARY_KEY;
                if (r4_String.indexOf("#cellwifi") != -1) {
                    r0_a = a(r4_String, r5_StringBuilder, "#cellwifi", r6_String);
                    if (r0_a != null) {
                        r2_String = "found#cellwifi";
                    } else {
                        r2_String = "no found";
                    }
                } else if (r4_String.indexOf("#wifi") != -1) {
                    r0_a = a(r4_String, r5_StringBuilder, "#wifi", r6_String);
                    if (r0_a != null) {
                        r2_String = "found#wifi";
                    } else {
                        r2_String = "no found";
                    }
                } else if (r4_String.indexOf("#cell") != -1) {
                    if (r6_String.equals("mem")) {
                        List r0_List = (List) this.b.get(r4_String);
                        r0_a = (r0_List == null || r0_List.size() <= 0) ? null : (a) r0_List.get(r0_List.size() - 1);
                        r2_String = "found#cell";
                    } else {
                        if (r6_String.equals("db")) {
                        }
                    }
                    if (r0_a != null) {
                        r2_String = "found#cell";
                    } else {
                        r2_String = "no found";
                    }
                } else {
                    r0_a = null;
                }
                return r0_a != null ? r0_a.a() : null;
            }
        } else {
            c();
            return null;
        }
    }

    void a(String r4_String, c r5_c, StringBuilder r6_StringBuilder, Context r7_Context) {
        if (f.k) {
            if ((!a(r4_String, r5_c)) || r5_c.g().equals("mem")) {
            } else {
                if (b()) {
                    c();
                }
                this.c = o.a();
                a r1_a = new a();
                r5_c.d("mem");
                r1_a.a(r5_c);
                r1_a.a(r6_StringBuilder.toString());
                List r0_List;
                if (this.b.contains(r4_String)) {
                    r0_List = (List) this.b.get(r4_String);
                    if (r0_List == null || r0_List.contains(r1_a)) {
                        this.b.put(r4_String, r0_List);
                    } else {
                        r0_List.add(r1_a);
                        this.b.put(r4_String, r0_List);
                    }
                } else {
                    r0_List = new ArrayList();
                    r0_List.add(r1_a);
                    this.b.put(r4_String, r0_List);
                }
            }
        } else {
            c();
        }
    }

    boolean a(String r7_String, c r8_c) {
        return r7_String != null && r8_c != null && r7_String.indexOf("#network") != -1 && r8_c.c() != 0.0d;
    }

    boolean b() {
        long r2j = o.a() - this.c;
        if (this.c == 0) {
            return false;
        }
        if (this.b.size() > 360) {
            return true;
        }
        if (r2j > 3600000) {
            return true;
        }
        return false;
    }

    void c() {
        this.c = 0;
        this.b.clear();
    }
}
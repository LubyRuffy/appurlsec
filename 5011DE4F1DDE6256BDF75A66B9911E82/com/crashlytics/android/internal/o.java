package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class o {
    private final bh a;
    private final ah b;
    private aK c;
    private final aj d;
    private final int e;
    private List<ak> f;

    o(bh r2_bh, ah r3_ah, aj r4_aj) throws IOException {
        this(r2_bh, r3_ah, r4_aj, 100);
    }

    private o(bh r2_bh, ah r3_ah, aj r4_aj, int r5i) throws IOException {
        this.f = new CopyOnWriteArrayList();
        this.a = r2_bh;
        this.d = r4_aj;
        this.b = r3_ah;
        this.b.a();
        this.e = 100;
    }

    private void a(String r6_String) {
        Iterator r1_Iterator = this.f.iterator();
        while (r1_Iterator.hasNext()) {
            try {
                ((ak) r1_Iterator.next()).c();
            } catch (Exception e) {
                v.a().b().a(Crashlytics.TAG, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    private static long b(String r5_String) {
        String[] r2_StringA = r5_String.split("_");
        if (r2_StringA.length != 3) {
            return 0;
        }
        try {
            return Long.valueOf(r2_StringA[2]).longValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int e() {
        return this.c == null ? 8000 : this.c.c;
    }

    final void a(aK r1_aK) {
        this.c = r1_aK;
    }

    final void a(ak r2_ak) {
        if (r2_ak != null) {
            this.f.add(r2_ak);
        }
    }

    final void a(bf r8_bf) throws IOException {
        byte[] r0_byteA = this.a.a(r8_bf);
        int r1i = r0_byteA.length;
        if (!this.d.a(r1i, e())) {
            Locale r2_Locale = Locale.US;
            Object[] r4_ObjectA = new Object[3];
            r4_ObjectA[0] = Integer.valueOf(this.d.a());
            r4_ObjectA[1] = Integer.valueOf(r1i);
            r4_ObjectA[2] = Integer.valueOf(e());
            ab.a((int)XListViewFooter.STATE_NODATA, String.format(r2_Locale, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", r4_ObjectA));
            a();
        }
        this.d.a(r0_byteA);
    }

    final void a(List<File> r2_List_File) {
        this.d.a((List)r2_List_File);
    }

    final boolean a() throws IOException {
        boolean r1z = true;
        int r2i = 0;
        String r0_String = null;
        if (this.d.b()) {
            r1z = false;
        } else {
            UUID r0_UUID = UUID.randomUUID();
            StringBuilder r3_StringBuilder = new StringBuilder();
            r3_StringBuilder.append("sa");
            r3_StringBuilder.append("_");
            r3_StringBuilder.append(r0_UUID.toString());
            r3_StringBuilder.append("_");
            r3_StringBuilder.append(this.b.a());
            r3_StringBuilder.append(".tap");
            r0_String = r3_StringBuilder.toString();
            this.d.a(r0_String);
            Locale r4_Locale = Locale.US;
            Object[] r6_ObjectA = new Object[1];
            r6_ObjectA[r2i] = r0_String;
            ab.a((int)XListViewFooter.STATE_NODATA, String.format(r4_Locale, "generated new to-send analytics file %s", r6_ObjectA));
            this.b.a();
        }
        a(r0_String);
        return r1z;
    }

    final List<File> b() {
        return this.d.a(1);
    }

    final void c() {
        this.d.a(this.d.c());
        this.d.d();
    }

    final void d() {
        List r0_List = this.d.c();
        if (r0_List.size() <= this.e) {
        } else {
            int r1i = r0_List.size() - this.e;
            Locale r2_Locale = Locale.US;
            Object[] r4_ObjectA = new Object[3];
            r4_ObjectA[0] = Integer.valueOf(r0_List.size());
            r4_ObjectA[1] = Integer.valueOf(this.e);
            r4_ObjectA[2] = Integer.valueOf(r1i);
            ab.c(String.format(r2_Locale, "Found %d files in session analytics roll over directory, this is greater than %d, deleting %d oldest files", r4_ObjectA));
            TreeSet r2_TreeSet = new TreeSet(new w(this));
            Iterator r3_Iterator = r0_List.iterator();
            while (r3_Iterator.hasNext()) {
                File r0_File = (File) r3_Iterator.next();
                r2_TreeSet.add(new x(this, r0_File, b(r0_File.getName())));
            }
            List r3_List = new ArrayList();
            Iterator r2_Iterator = r2_TreeSet.iterator();
            while (r2_Iterator.hasNext()) {
                r3_List.add(((x) r2_Iterator.next()).a);
                if (r3_List.size() == r1i) {
                    break;
                }
            }
            this.d.a(r3_List);
        }
    }
}
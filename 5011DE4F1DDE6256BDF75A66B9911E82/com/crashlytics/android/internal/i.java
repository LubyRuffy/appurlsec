package com.crashlytics.android.internal;

import java.io.File;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
final class i extends Z implements y {
    public i(String r2_String, String r3_String, av r4_av) {
        super(r2_String, r3_String, r4_av, ax.b);
    }

    public final boolean a(String r9_String, List<File> r10_List_File) {
        ay r3_ay = b().a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()).a("X-CRASHLYTICS-API-KEY", r9_String);
        Iterator r4_Iterator = r10_List_File.iterator();
        int r1i = 0;
        while (r4_Iterator.hasNext()) {
            File r0_File = (File) r4_Iterator.next();
            ab.c(new StringBuilder("Adding analytics session file ").append(r0_File.getName()).append(" to multipart POST").toString());
            r3_ay.a(new StringBuilder("session_analytics_file_").append(r1i).toString(), r0_File.getName(), "application/vnd.crashlytics.android.events", r0_File);
            r1i++;
        }
        ab.c(new StringBuilder("Sending ").append(r10_List_File.size()).append(" analytics files to ").append(a()).toString());
        int r0i = r3_ay.b();
        ab.c(new StringBuilder("Response code for analytics file send is ").append(r0i).toString());
        return r.a(r0i) == 0;
    }
}
package com.tencent.mm.sdk.contact;

import android.content.Context;
import android.net.Uri;
import com.tencent.mm.sdk.storage.ContentProviderDB;
import java.util.HashMap;
import java.util.Map;

public class RContactDB extends ContentProviderDB<RContactDB> {
    private static final Map<String, Uri> a;

    static {
        Map r0_Map = new HashMap();
        a = r0_Map;
        r0_Map.put(RContactStorage.TABLE, Uri.parse("content://com.tencent.mm.sdk.contact.provider/rcontact"));
    }

    public RContactDB(Context r1_Context) {
        super(r1_Context);
    }

    public Uri getUriFromTable(String r2_String) {
        return (Uri) a.get(r2_String);
    }
}
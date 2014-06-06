package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.utils.ClientIdManager;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
public class bh implements bi {
    private bg a;
    private boolean b;
    private byte[] c;
    private String d;

    public bh(short r4s, String r5_String, boolean r6z) {
        this.d = r5_String;
        this.a = new bg(r4s, r5_String, new bf((short) b(), (short) 5));
        this.b = r6z;
        this.c = new byte[b()];
    }

    public byte[] a() {
        Object r2_Object = this.a.a();
        System.arraycopy(r2_Object, 0, this.c, 0, r2_Object.length);
        this.c[r2_Object.length + 4] = (byte) (this.b ? 1 : 0);
        System.arraycopy(eo.a(ClientIdManager.getInstance().getClientId()), 0, this.c, r2_Object.length + 5, XListViewFooter.STATE_NODATA);
        return this.c;
    }

    public int b() {
        return this.d.length() + 7 + 9;
    }
}
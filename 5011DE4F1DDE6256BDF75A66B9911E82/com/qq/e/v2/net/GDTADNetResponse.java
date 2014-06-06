package com.qq.e.v2.net;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import qsbk.app.bean.Base;

public abstract class GDTADNetResponse {
    private HttpResponse a;

    public GDTADNetResponse(HttpResponse r1_HttpResponse) {
        this.a = r1_HttpResponse;
    }

    protected final HttpResponse a() {
        return this.a;
    }

    protected final byte[] b() throws IOException {
        if (200 != getStatusCode()) {
            return null;
        }
        long r0j = this.a.getEntity().getContentLength();
        InputStream r2_InputStream = this.a.getEntity().getContent();
        ByteArrayOutputStream r3_ByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] r4_byteA = new byte[1024];
        while (true) {
            int r5i = r2_InputStream.read(r4_byteA);
            if (r5i > 0) {
                r3_ByteArrayOutputStream.write(r4_byteA, 0, r5i);
            } else {
                if (r0j <= 0 || ((long) r3_ByteArrayOutputStream.size()) == r0j) {
                    return r3_ByteArrayOutputStream.toByteArray();
                }
                Locale r2_Locale = Locale.US;
                Object[] r5_ObjectA = new Object[2];
                r5_ObjectA[0] = Integer.valueOf(r3_ByteArrayOutputStream.size());
                r5_ObjectA[1] = Long.valueOf(r0j);
                throw new IOException(String.format(r2_Locale, "ContentLength not match (%d,%d)", r5_ObjectA));
            }
        }
    }

    public void close() throws IllegalStateException, IOException {
        this.a.getEntity().getContent().close();
    }

    public abstract byte[] getContentAsByteArray() throws IOException;

    public String getContentAsString() throws IOException {
        return getContentAsString(Base.UTF8);
    }

    public String getContentAsString(String r4_String) throws IOException {
        String r0_String = null;
        byte[] r1_byteA = getContentAsByteArray();
        if (r1_byteA == null) {
            return null;
        }
        if (r1_byteA.length == 0) {
            return RContactStorage.PRIMARY_KEY;
        }
        try {
            r0_String = EntityUtils.getContentCharSet(this.a.getEntity());
        } catch (Throwable th) {
        }
        if (r0_String == null) {
            return new String(r1_byteA, r4_String);
        }
        r4_String = r0_String;
        return new String(r1_byteA, r4_String);
    }

    public InputStream getRawContent() throws IllegalStateException, IOException {
        return this.a.getEntity().getContent();
    }

    public int getStatusCode() {
        return this.a.getStatusLine().getStatusCode();
    }
}
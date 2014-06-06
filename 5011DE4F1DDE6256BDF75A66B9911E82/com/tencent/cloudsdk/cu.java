package com.tencent.cloudsdk;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

// compiled from: SourceFile
public class cu {
    private String a;

    public cu(String r2_String) throws UnknownHostException {
        this.a = RContactStorage.PRIMARY_KEY;
        this.a = r2_String;
    }

    public void a(String r1_String) {
        this.a = r1_String;
    }

    public InetAddress[] b(String r6_String) throws IOException, SocketTimeoutException, cy, UnknownHostException, Exception {
        InetAddress[] r0_InetAddressA = null;
        cv r1_cv = new cv(r6_String);
        byte[] r2_byteA = r1_cv.a();
        if (r2_byteA == null) {
            return r0_InetAddressA;
        }
        try {
            r2_byteA = new cg().a(this.a, r2_byteA);
        } catch (cy e) {
            throw e;
        } catch (SocketTimeoutException e_2) {
            throw e_2;
        } catch (IOException e_3) {
            throw e_3;
        }
        if (r2_byteA != null) {
            cw r3_cw = new cw(new cs(r2_byteA), r6_String);
            if (r3_cw.c() == r1_cv.b()) {
                r0_InetAddressA = r3_cw.a();
                if (r0_InetAddressA == null || r0_InetAddressA.length <= 0) {
                    return r0_InetAddressA;
                }
                cb.a().a(r6_String, r0_InetAddressA, r3_cw.b());
            }
        }
        return r0_InetAddressA;
    }
}
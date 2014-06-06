package com.qq.e.v2.net;

import com.qq.e.comm.a;
import com.qq.e.v2.util.GDTLogger;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class GDTPlainADNetResponse extends GDTADNetResponse {
    private GDTPlainADNetRequest a;

    public GDTPlainADNetResponse(HttpResponse r1_HttpResponse, GDTPlainADNetRequest r2_GDTPlainADNetRequest) {
        super(r1_HttpResponse);
        this.a = r2_GDTPlainADNetRequest;
    }

    public byte[] getContentAsByteArray() throws IOException {
        byte[] r0_byteA = b();
        if (r0_byteA == null) {
            GDTLogger.e(new StringBuilder("HTTPStatus Error for PlainRequst to URL").append(this.a.getUrlWithParas()).append(" ;statusCode=").append(getStatusCode()).toString());
            return null;
        } else {
            Header r1_Header = a().getEntity().getContentEncoding();
            return (r1_Header == null || (!r1_Header.getValue().contains("gzip"))) ? r0_byteA : a.d(r0_byteA);
        }
    }
}
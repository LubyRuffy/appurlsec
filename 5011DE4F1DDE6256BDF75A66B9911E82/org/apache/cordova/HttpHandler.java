package org.apache.cordova;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpHandler {
    private HttpEntity getHttpEntity(String r3_String) {
        try {
            return new DefaultHttpClient().execute(new HttpGet(r3_String)).getEntity();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeToDisk(HttpEntity r6_HttpEntity, String r7_String) throws IllegalStateException, IOException {
        String r0_String = "/sdcard/" + r7_String;
        InputStream r1_InputStream = r6_HttpEntity.getContent();
        byte[] r2_byteA = new byte[1024];
        FileOutputStream r3_FileOutputStream = new FileOutputStream(r0_String);
        while (true) {
            int r0i = r1_InputStream.read(r2_byteA);
            if (r0i <= 0) {
                r3_FileOutputStream.flush();
                r3_FileOutputStream.close();
                return;
            } else {
                r3_FileOutputStream.write(r2_byteA, 0, r0i);
            }
        }
    }

    protected Boolean get(String r3_String, String r4_String) {
        HttpEntity r0_HttpEntity = getHttpEntity(r3_String);
        try {
            writeToDisk(r0_HttpEntity, r4_String);
            r0_HttpEntity.consumeContent();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }
}
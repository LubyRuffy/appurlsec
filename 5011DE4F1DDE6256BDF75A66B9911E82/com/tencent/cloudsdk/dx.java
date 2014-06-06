package com.tencent.cloudsdk;

import android.os.Bundle;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public abstract class dx implements eb {
    public void a(Bundle r2_Bundle) {
        if (r2_Bundle == null) {
        } else {
            a(b(r2_Bundle));
        }
    }

    public void a(List r2_List) {
        b(r2_List, null);
    }

    public void a(List r3_List, ea r4_ea) {
        if (a()) {
            a(c(r3_List, r4_ea));
        } else {
            Iterator r1_Iterator = r3_List.iterator();
            while (r1_Iterator.hasNext()) {
                a((Bundle) r1_Iterator.next());
            }
        }
    }

    protected void a(byte[] r6_byteA) {
        Throwable r0_Throwable;
        try {
            Socket r0_Socket = new Socket("c.yun.qq.com", 8192);
            r0_Socket.setSoTimeout(15000);
            OutputStream r1_OutputStream = r0_Socket.getOutputStream();
            InputStream r2_InputStream = r0_Socket.getInputStream();
            DataOutputStream r3_DataOutputStream = new DataOutputStream(r1_OutputStream);
            r3_DataOutputStream.write(r6_byteA);
            r3_DataOutputStream.flush();
            r2_InputStream.read(new byte[2]);
            r3_DataOutputStream.close();
            r1_OutputStream.close();
            r0_Socket.close();
        } catch (UnknownHostException e) {
            r0_Throwable = e;
            WnsClientLog.e("AbsReportTool", r0_Throwable.getMessage(), r0_Throwable);
        } catch (IOException e_2) {
            r0_Throwable = e_2;
            WnsClientLog.e("AbsReportTool", r0_Throwable.getMessage(), r0_Throwable);
        }
    }

    protected abstract boolean a();

    public void b(List r3_List, ea r4_ea) {
        en.a.post(new dm(this, r3_List, r4_ea));
    }

    protected abstract byte[] b(Bundle r1_Bundle);

    protected abstract byte[] c(List r1_List, ea r2_ea);
}
package qsbk.app.utils.image.issue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: Reporter.java
class d implements Task {
    final /* synthetic */ File a;
    final /* synthetic */ Reporter b;

    d(Reporter r1_Reporter, File r2_File) {
        this.b = r1_Reporter;
        this.a = r2_File;
    }

    public void fail(Throwable r1_Throwable) {
    }

    public Object proccess() throws QiushibaikeException {
        String r2_String;
        Object[] r3_ObjectA;
        StringBuffer r0_StringBuffer = new StringBuffer();
        try {
            BufferedReader r1_BufferedReader = new BufferedReader(new FileReader(this.a));
            while (true) {
                r2_String = r1_BufferedReader.readLine();
                if (r2_String != null) {
                    r0_StringBuffer.append(r2_String);
                } else {
                    r1_BufferedReader.close();
                    try {
                        HttpClient.getIntentce().get("http://imglog.moumentei.com" + r0_StringBuffer.toString());
                        return null;
                    } catch (Exception e) {
                        r3_ObjectA = new Object[1];
                        r3_ObjectA[0] = e.toString();
                        throw new QiushibaikeException(String.format("%1$s happened when sending file", r3_ObjectA));
                    }
                }
            }
        } catch (FileNotFoundException e_2) {
            r2_String = "file %1$s not found";
            r3_ObjectA = new Object[1];
            r3_ObjectA[0] = this.a != null ? this.a.getAbsolutePath() : "unknow";
            throw new QiushibaikeException(String.format(r2_String, r3_ObjectA));
        } catch (IOException e_3) {
            r2_String = "%1$s happened when writing file %2$s";
            r3_ObjectA = new Object[2];
            r3_ObjectA[0] = e_3.toString();
            r3_ObjectA[1] = this.a != null ? this.a.getAbsolutePath() : "unknow";
            throw new QiushibaikeException(String.format(r2_String, r3_ObjectA));
        }
    }

    public void success(Object r4_Object) {
        if (!this.a.renameTo(new File(this.a.getPath().replace(".di", ".sent")))) {
            this.a.delete();
        }
    }
}
package qsbk.app.utils.image.issue;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: Reporter.java
class b implements Task {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Reporter d;

    b(Reporter r1_Reporter, Context r2_Context, String r3_String, String r4_String) {
        this.d = r1_Reporter;
        this.a = r2_Context;
        this.b = r3_String;
        this.c = r4_String;
    }

    public void fail(Throwable r1_Throwable) {
    }

    public Object proccess() throws QiushibaikeException {
        Object[] r3_ObjectA;
        String r0_String = DeviceUtils.getSDPath();
        if (TextUtils.isEmpty(r0_String)) {
            try {
                FileOutputStream r0_FileOutputStream = this.a.openFileOutput(this.b, 0);
                r0_FileOutputStream.write(this.c.getBytes());
                r0_FileOutputStream.flush();
                r0_FileOutputStream.close();
            } catch (FileNotFoundException e) {
                Object[] r2_ObjectA = new Object[1];
                r2_ObjectA[0] = this.b;
                throw new QiushibaikeException(String.format("file %1$s not found", r2_ObjectA));
            } catch (IOException e_2) {
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = e_2.toString();
                r3_ObjectA[1] = this.b;
                throw new QiushibaikeException(String.format("%1$s happened when writing file %2$s", r3_ObjectA));
            }
        } else {
            File r1_File = new File(r0_String + File.separator + this.a.getPackageName() + File.separator + "img_issue");
            if (r1_File.exists()) {
                FileUtils.removeOldFiles(r1_File, new c(this), 1048576);
            } else {
                r1_File.mkdirs();
            }
            try {
                FileWriter r1_FileWriter = new FileWriter(new File(r1_File, this.b));
                r1_FileWriter.write(this.c);
                r1_FileWriter.flush();
                r1_FileWriter.close();
            } catch (IOException e_3) {
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = e_3.toString();
                r3_ObjectA[1] = this.b;
                throw new QiushibaikeException(String.format("%1$s happened when writing file %2$s", r3_ObjectA));
            }
        }
        return null;
    }

    public void success(Object r1_Object) {
    }
}
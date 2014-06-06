package qsbk.app.utils.image.issue;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import qsbk.app.utils.DeviceUtils;

public class Reporter {
    private static Reporter a;
    private static final TaskExecutor b;

    static {
        a = null;
        b = TaskExecutor.getInstance();
    }

    private Reporter() {
    }

    private void a(File r3_File) {
        b.addTask(new d(this, r3_File));
    }

    private void a(String r3_String, String r4_String, Context r5_Context) {
        b.addTask(new b(this, r5_Context, r3_String, r4_String));
    }

    private File[] a(Context r12_Context) {
        Object r0_Object;
        int r7i;
        String r5_String;
        String[] r6_StringA;
        Object r2_Object;
        File r0_File = r12_Context.getFilesDir();
        FilenameFilter r3_FilenameFilter = new e(this);
        String[] r5_StringA = r0_File.list(r3_FilenameFilter);
        String r6_String = DeviceUtils.getSDPath();
        if (r5_StringA == null || r5_StringA.length <= 0) {
            r0_Object = null;
        } else {
            r0_Object = new Object[r5_StringA.length];
            r7i = r5_StringA.length;
            int r2i = 0;
            while (r2i < r7i) {
                r0_Object[r2i] = new File(r12_Context.getFilesDir(), r5_StringA[r2i]);
                r2i++;
            }
        }
        if (TextUtils.isEmpty(r6_String)) {
            r5_String = null;
            r6_StringA = null;
        } else {
            String r2_String = r6_String + File.separator + r12_Context.getPackageName() + File.separator + "img_issue";
            File r5_File = new File(r2_String);
            if (r5_File.exists()) {
                r5_String = r2_String;
                r6_StringA = r5_File.list(r3_FilenameFilter);
            } else {
                r5_String = r2_String;
                r6_StringA = null;
            }
        }
        if (r6_StringA == null || r6_StringA.length <= 0) {
            r2_Object = null;
        } else {
            r2_Object = new Object[r6_StringA.length];
            r7i = r6_StringA.length;
            int r3i = 0;
            while (r3i < r7i) {
                r2_Object[r3i] = new File(r5_String + File.separator + r6_StringA[r3i]);
                r3i++;
            }
        }
        if (r0_Object == null || r2_Object == null) {
            if (r0_Object != null && r0_Object.length > 0) {
                return r0_Object;
            }
            if (r2_Object != null) {
                return r2_Object;
            }
            return null;
        } else {
            Object r1_Object = new Object[(r0_Object.length + r2_Object.length)];
            System.arraycopy(r0_Object, 0, r1_Object, 0, r0_Object.length);
            System.arraycopy(r2_Object, 0, r1_Object, r0_Object.length, r2_Object.length);
            return r1_Object;
        }
    }

    public static synchronized Reporter getInstance() {
        Reporter r0_Reporter;
        synchronized (Reporter.class) {
            if (a == null) {
                a = new Reporter();
            }
            r0_Reporter = a;
        }
        return r0_Reporter;
    }

    public void reportFile(Context r5_Context) {
        File[] r1_FileA = a(r5_Context);
        if (r1_FileA == null || r1_FileA.length <= 0) {
        } else {
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                a(r1_FileA[r0i]);
                r0i++;
            }
        }
    }

    public void reportMsg(Context r3_Context, String r4_String) {
        b.addTask(new f(this, r4_String, r3_Context));
    }
}
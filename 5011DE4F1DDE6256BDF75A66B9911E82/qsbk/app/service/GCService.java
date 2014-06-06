package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import qsbk.app.Constants;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.utils.image.Utils;

public class GCService extends Service implements Runnable {


    private static class a implements Comparator<File> {
        private a() {
        }

        public int compare(File r7_File, File r8_File) {
            long r0j = r7_File.lastModified() - r8_File.lastModified();
            if (r0j > 0) {
                return 1;
            }
            if (r0j == 0) {
                return 0;
            }
            return -1;
        }

        public boolean equals(Object r2_Object) {
            return true;
        }
    }

    private void a(File r5_File) {
        if (r5_File.exists() && r5_File.isDirectory()) {
            File[] r2_FileA = r5_File.listFiles();
            if (r2_FileA == null || r2_FileA.length < 1) {
            } else {
                int r0i;
                Arrays.sort(r2_FileA, new a());
                r0i = r2_FileA.length > OneProfileActivity.REQ_CODE_SHARE ? r2_FileA.length - 20 : -1;
                int r1i = 0;
                while (r1i < r0i) {
                    r2_FileA[r1i].delete();
                    r1i++;
                }
            }
        }
    }

    public IBinder onBind(Intent r2_Intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        new Thread(this, "qbk-GcSrv1").start();
    }

    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

    public void run() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            File r1_File;
            File r2_File;
            if (Environment.getExternalStorageState().equals("mounted") || (!Utils.isExternalStorageRemovable())) {
                r0_String = Utils.getExternalCacheDir(this).getPath();
                r1_File = new File(r0_String + Constants.IMG_CACHE_PATH_PRE);
                r2_File = new File(r0_String + Constants.IMG_CACHE_PATH_MEDIUM);
                a(new File(r0_String + Constants.IMG_CACHE_PATH_AVATAR));
                a(r1_File);
                a(r2_File);
                a(new File(r0_String + "/http"));
                stopSelf();
            } else {
                r0_String = getCacheDir().getPath();
                r1_File = new File(r0_String + Constants.IMG_CACHE_PATH_PRE);
                r2_File = new File(r0_String + Constants.IMG_CACHE_PATH_MEDIUM);
                a(new File(r0_String + Constants.IMG_CACHE_PATH_AVATAR));
                a(r1_File);
                a(r2_File);
                a(new File(r0_String + "/http"));
                stopSelf();
            }
        } catch (Exception e) {
            stopSelf();
        }
    }
}
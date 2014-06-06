package android.support.v4.os;

import android.os.Environment;
import java.io.File;

// compiled from: EnvironmentCompatKitKat.java
class a {
    public static String getStorageState(File r1_File) {
        return Environment.getStorageState(r1_File);
    }
}
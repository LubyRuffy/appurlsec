package qsbk.app.utils;

import android.widget.Toast;
import qsbk.app.QsbkApp;

public class ToastUtil {
    public static void Long(int r2i) {
        Toast.makeText(QsbkApp.mContext, r2i, 1).show();
    }

    public static void Long(String r2_String) {
        Toast.makeText(QsbkApp.mContext, r2_String, 1).show();
    }

    public static void Short(int r2i) {
        Toast.makeText(QsbkApp.mContext, r2i, 0).show();
    }

    public static void Short(String r2_String) {
        Toast.makeText(QsbkApp.mContext, r2_String, 0).show();
    }
}
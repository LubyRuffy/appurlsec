package qsbk.app.utils;

public class LogUtil {
    public static void d(String r0_String) {
    }

    public static void e(String r0_String) {
    }

    public static String getClassName(int r1i) {
        return Thread.currentThread().getStackTrace()[r1i].getClassName();
    }
}
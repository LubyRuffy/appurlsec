package org.apache.cordova.api;

import android.util.Log;

public class LOG {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static int LOGLEVEL = 0;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    static {
        LOGLEVEL = 6;
    }

    public static void d(String r2_String, String r3_String) {
        if (3 >= LOGLEVEL) {
            Log.d(r2_String, r3_String);
        }
    }

    public static void d(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (3 >= LOGLEVEL) {
            Log.d(r2_String, r3_String, r4_Throwable);
        }
    }

    public static void d(String r2_String, String r3_String, Object ... r4_ObjectA) {
        if (3 >= LOGLEVEL) {
            Log.d(r2_String, String.format(r3_String, r4_ObjectA));
        }
    }

    public static void e(String r2_String, String r3_String) {
        if (6 >= LOGLEVEL) {
            Log.e(r2_String, r3_String);
        }
    }

    public static void e(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (6 >= LOGLEVEL) {
            Log.e(r2_String, r3_String, r4_Throwable);
        }
    }

    public static void e(String r2_String, String r3_String, Object ... r4_ObjectA) {
        if (6 >= LOGLEVEL) {
            Log.e(r2_String, String.format(r3_String, r4_ObjectA));
        }
    }

    public static void i(String r2_String, String r3_String) {
        if (4 >= LOGLEVEL) {
            Log.i(r2_String, r3_String);
        }
    }

    public static void i(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (4 >= LOGLEVEL) {
            Log.i(r2_String, r3_String, r4_Throwable);
        }
    }

    public static void i(String r2_String, String r3_String, Object ... r4_ObjectA) {
        if (4 >= LOGLEVEL) {
            Log.i(r2_String, String.format(r3_String, r4_ObjectA));
        }
    }

    public static boolean isLoggable(int r1i) {
        return r1i >= LOGLEVEL;
    }

    public static void setLogLevel(int r3i) {
        LOGLEVEL = r3i;
        Log.i("CordovaLog", "Changing log level to " + r3i);
    }

    public static void setLogLevel(String r3_String) {
        if ("VERBOSE".equals(r3_String)) {
            LOGLEVEL = 2;
        } else if ("DEBUG".equals(r3_String)) {
            LOGLEVEL = 3;
        } else if ("INFO".equals(r3_String)) {
            LOGLEVEL = 4;
        } else if ("WARN".equals(r3_String)) {
            LOGLEVEL = 5;
        } else if ("ERROR".equals(r3_String)) {
            LOGLEVEL = 6;
        }
        Log.i("CordovaLog", "Changing log level to " + r3_String + "(" + LOGLEVEL + ")");
    }

    public static void v(String r2_String, String r3_String) {
        if (2 >= LOGLEVEL) {
            Log.v(r2_String, r3_String);
        }
    }

    public static void v(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (2 >= LOGLEVEL) {
            Log.v(r2_String, r3_String, r4_Throwable);
        }
    }

    public static void v(String r2_String, String r3_String, Object ... r4_ObjectA) {
        if (2 >= LOGLEVEL) {
            Log.v(r2_String, String.format(r3_String, r4_ObjectA));
        }
    }

    public static void w(String r2_String, String r3_String) {
        if (5 >= LOGLEVEL) {
            Log.w(r2_String, r3_String);
        }
    }

    public static void w(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (5 >= LOGLEVEL) {
            Log.w(r2_String, r3_String, r4_Throwable);
        }
    }

    public static void w(String r2_String, String r3_String, Object ... r4_ObjectA) {
        if (5 >= LOGLEVEL) {
            Log.w(r2_String, String.format(r3_String, r4_ObjectA));
        }
    }
}
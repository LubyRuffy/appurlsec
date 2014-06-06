package qsbk.app.api;

public class CacheType {
    public static String DAY;
    public static String LATEST;
    public static String SUGGEST;

    static {
        SUGGEST = "s";
        LATEST = "l";
        DAY = "d";
    }
}
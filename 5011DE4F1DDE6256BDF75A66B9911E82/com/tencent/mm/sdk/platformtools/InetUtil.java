package com.tencent.mm.sdk.platformtools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public final class InetUtil {
    private static final Pattern a;
    private static final Pattern b;
    private static final Pattern c;

    static {
        a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
        b = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
        c = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
    }

    private InetUtil() {
    }

    public static InetAddress getByDottedAddress(String r2_String) {
        if (isIPv4Address(r2_String)) {
            return InetAddress.getByName(r2_String);
        }
        if (isIPv6Address(r2_String)) {
            return InetAddress.getByName(r2_String);
        }
        throw new UnknownHostException("invalid ipv4 or ipv6 dotted string");
    }

    public static boolean isIPv4Address(String r1_String) {
        return a.matcher(r1_String).matches();
    }

    public static boolean isIPv6Address(String r1_String) {
        return isIPv6StdAddress(r1_String) || isIPv6HexCompressedAddress(r1_String);
    }

    public static boolean isIPv6HexCompressedAddress(String r1_String) {
        return c.matcher(r1_String).matches();
    }

    public static boolean isIPv6StdAddress(String r1_String) {
        return b.matcher(r1_String).matches();
    }
}
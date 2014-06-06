package android.support.v4.net;

import android.net.TrafficStats;
import java.net.Socket;
import java.net.SocketException;

// compiled from: TrafficStatsCompatIcs.java
class e {
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    public static void incrementOperationCount(int r0i) {
        TrafficStats.incrementOperationCount(r0i);
    }

    public static void incrementOperationCount(int r0i, int r1i) {
        TrafficStats.incrementOperationCount(r0i, r1i);
    }

    public static void setThreadStatsTag(int r0i) {
        TrafficStats.setThreadStatsTag(r0i);
    }

    public static void tagSocket(Socket r0_Socket) throws SocketException {
        TrafficStats.tagSocket(r0_Socket);
    }

    public static void untagSocket(Socket r0_Socket) throws SocketException {
        TrafficStats.untagSocket(r0_Socket);
    }
}
package android.support.v4.net;

import android.os.Build.VERSION;
import java.net.Socket;
import java.net.SocketException;

public class TrafficStatsCompat {
    private static final c a;


    static interface c {
        public void clearThreadStatsTag();

        public int getThreadStatsTag();

        public void incrementOperationCount(int r1i);

        public void incrementOperationCount(int r1i, int r2i);

        public void setThreadStatsTag(int r1i);

        public void tagSocket(Socket r1_Socket) throws SocketException;

        public void untagSocket(Socket r1_Socket) throws SocketException;
    }

    static class a implements c {
        private ThreadLocal<a> a;

        private static class a {
            public int statsTag;

            private a() {
                this.statsTag = -1;
            }
        }

        a() {
            this.a = new d(this);
        }

        public void clearThreadStatsTag() {
            ((a) this.a.get()).statsTag = -1;
        }

        public int getThreadStatsTag() {
            return ((a) this.a.get()).statsTag;
        }

        public void incrementOperationCount(int r1i) {
        }

        public void incrementOperationCount(int r1i, int r2i) {
        }

        public void setThreadStatsTag(int r2i) {
            ((a) this.a.get()).statsTag = r2i;
        }

        public void tagSocket(Socket r1_Socket) {
        }

        public void untagSocket(Socket r1_Socket) {
        }
    }

    static class b implements c {
        b() {
        }

        public void clearThreadStatsTag() {
            e.clearThreadStatsTag();
        }

        public int getThreadStatsTag() {
            return e.getThreadStatsTag();
        }

        public void incrementOperationCount(int r1i) {
            e.incrementOperationCount(r1i);
        }

        public void incrementOperationCount(int r1i, int r2i) {
            e.incrementOperationCount(r1i, r2i);
        }

        public void setThreadStatsTag(int r1i) {
            e.setThreadStatsTag(r1i);
        }

        public void tagSocket(Socket r1_Socket) throws SocketException {
            e.tagSocket(r1_Socket);
        }

        public void untagSocket(Socket r1_Socket) throws SocketException {
            e.untagSocket(r1_Socket);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static void clearThreadStatsTag() {
        a.clearThreadStatsTag();
    }

    public static int getThreadStatsTag() {
        return a.getThreadStatsTag();
    }

    public static void incrementOperationCount(int r1i) {
        a.incrementOperationCount(r1i);
    }

    public static void incrementOperationCount(int r1i, int r2i) {
        a.incrementOperationCount(r1i, r2i);
    }

    public static void setThreadStatsTag(int r1i) {
        a.setThreadStatsTag(r1i);
    }

    public static void tagSocket(Socket r1_Socket) throws SocketException {
        a.tagSocket(r1_Socket);
    }

    public static void untagSocket(Socket r1_Socket) throws SocketException {
        a.untagSocket(r1_Socket);
    }
}
package qsbk.app.utils;

public class TimeDelta {
    public long start;

    public TimeDelta() {
        this.start = System.currentTimeMillis();
    }

    public long getDelta() {
        return System.currentTimeMillis() - this.start;
    }
}
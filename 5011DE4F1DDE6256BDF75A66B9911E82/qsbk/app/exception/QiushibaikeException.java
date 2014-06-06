package qsbk.app.exception;

public class QiushibaikeException extends Exception {
    private int a;

    public QiushibaikeException(Exception r2_Exception) {
        super(r2_Exception);
        this.a = -1;
    }

    public QiushibaikeException(String r2_String) {
        super(r2_String);
        this.a = -1;
    }

    public QiushibaikeException(String r2_String, int r3i) {
        super(r2_String);
        this.a = -1;
        this.a = r3i;
    }

    public QiushibaikeException(String r2_String, Exception r3_Exception) {
        super(r2_String, r3_Exception);
        this.a = -1;
    }

    public QiushibaikeException(String r2_String, Exception r3_Exception, int r4i) {
        super(r2_String, r3_Exception);
        this.a = -1;
        this.a = r4i;
    }

    public int getStatusCode() {
        return this.a;
    }

    public String toString() {
        return super.toString() + " statucCode:" + this.a;
    }
}
package qsbk.app.thirdparty;

public class ThirdPartyException extends Exception {
    private int a;

    public ThirdPartyException() {
        this.a = -1;
    }

    public ThirdPartyException(int r2i) {
        this.a = -1;
        this.a = r2i;
    }

    public ThirdPartyException(Exception r2_Exception) {
        super(r2_Exception);
        this.a = -1;
    }

    public ThirdPartyException(String r2_String) {
        super(r2_String);
        this.a = -1;
    }

    public ThirdPartyException(String r2_String, int r3i) {
        super(r2_String);
        this.a = -1;
        this.a = r3i;
    }

    public ThirdPartyException(String r2_String, Exception r3_Exception) {
        super(r2_String, r3_Exception);
        this.a = -1;
    }

    public ThirdPartyException(String r2_String, Exception r3_Exception, int r4i) {
        super(r2_String, r3_Exception);
        this.a = -1;
        this.a = r4i;
    }

    public ThirdPartyException(String r2_String, Throwable r3_Throwable) {
        super(r2_String, r3_Throwable);
        this.a = -1;
    }

    public ThirdPartyException(Throwable r2_Throwable) {
        super(r2_Throwable);
        this.a = -1;
    }

    public int getStatusCode() {
        return this.a;
    }

    public void setStatusCode(int r1i) {
        this.a = r1i;
    }
}
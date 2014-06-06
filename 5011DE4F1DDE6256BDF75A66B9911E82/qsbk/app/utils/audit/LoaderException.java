package qsbk.app.utils.audit;

public class LoaderException extends Exception {
    public LoaderException(String r1_String) {
        super(r1_String);
    }

    public LoaderException(String r1_String, Throwable r2_Throwable) {
        super(r1_String, r2_Throwable);
    }

    public LoaderException(Throwable r1_Throwable) {
        super(r1_Throwable);
    }
}
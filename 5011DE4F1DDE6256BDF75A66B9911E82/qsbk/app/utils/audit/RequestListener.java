package qsbk.app.utils.audit;

public interface RequestListener {
    public static final int DEFAULT_LOADED_SIZE = -2;
    public static final int DEFAULT_TOTAL_SIZE = -1;

    public void onCanceled(Request r1_Request);

    public void onDownloading(Request r1_Request, int r2i, int r3i);

    public void onError(Request r1_Request, LoaderException r2_LoaderException);

    public void onPrepare(Request r1_Request, int r2i);

    public void onSuccess(Request r1_Request, int r2i, byte[] r3_byteA);
}
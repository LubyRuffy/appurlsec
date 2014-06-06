package qsbk.app.loader;

public interface OnAsyncLoadListener {
    public void onFinishListener(String r1_String);

    public void onPrepareListener();

    public String onStartListener();
}
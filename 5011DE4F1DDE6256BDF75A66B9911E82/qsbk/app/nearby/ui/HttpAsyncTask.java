package qsbk.app.nearby.ui;

import android.util.Pair;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;

public abstract class HttpAsyncTask extends AsyncTask<String, Void, Pair<Integer, String>> {
    public static Pair<Integer, String> getLocalError() {
        return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
    }

    protected Pair<Integer, String> a(String ... r5_StringA) {
        try {
            JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().post(getURL(), getPostParams()));
            return new Pair(Integer.valueOf(r1_JSONObject.getInt("err")), r1_JSONObject.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
        }
    }

    public Map<String, Object> getPostParams() {
        return null;
    }

    public String getURL() {
        return null;
    }

    public void run() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }
}
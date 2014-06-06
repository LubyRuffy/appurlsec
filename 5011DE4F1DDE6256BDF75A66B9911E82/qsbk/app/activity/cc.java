package qsbk.app.activity;

import android.util.Pair;
import org.json.JSONObject;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.utils.ToastAndDialog;

// compiled from: OneProfileActivity.java
class cc extends HttpAsyncTask {
    JSONObject a;
    final /* synthetic */ String b;
    final /* synthetic */ OneProfileActivity c;

    cc(OneProfileActivity r2_OneProfileActivity, String r3_String) {
        this.c = r2_OneProfileActivity;
        this.b = r3_String;
        this.a = null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Pair<Integer, String> a(String ... r4_StringA) {
        /*
        r3_this = this;
        r0 = new org.json.JSONObject;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1 = qsbk.app.utils.HttpClient.getIntentce();	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r2 = r3.b;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1 = r1.messgePorst(r2);	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r0.<init>(r1);	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r3.a = r0;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r0 = r3.a;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1 = "err";
        r0 = r0.get(r1);	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r0 = (java.lang.Integer) r0;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1 = r3.a;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r2 = "err_msg";
        r2 = r1.optString(r2);	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1 = new android.util.Pair;	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r1.<init>(r0, r2);	 //Catch:{ JSONException -> 0x002a, QiushibaikeException -> 0x0033 }
        r0 = r1;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x002e:
        r0 = qsbk.app.nearby.ui.HttpAsyncTask.getLocalError();
        goto L_0x0029;
    L_0x0033:
        r0 = move-exception;
        goto L_0x002e;
        */

    }

    protected void a(Pair<Integer, String> r3_Pair_Integer__String) {
        if (((Integer) r3_Pair_Integer__String.first).intValue() == 9999) {
            ToastAndDialog.makeText(this.c, "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5").show();
        } else {
            ToastAndDialog.makeText(this.c, (String) r3_Pair_Integer__String.second).show();
        }
    }
}
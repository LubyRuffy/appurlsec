package qsbk.app.activity;

import android.text.TextUtils;
import com.qq.e.v2.constants.Constants.KEYS;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.AuditArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

// compiled from: AuditNativeActivity.java
class n implements Task {
    final /* synthetic */ boolean a;
    final /* synthetic */ AuditNativeActivity b;

    n(AuditNativeActivity r1_AuditNativeActivity, boolean r2z) {
        this.b = r1_AuditNativeActivity;
        this.a = r2z;
    }

    public void fail(Throwable r3_Throwable) {
        if (!this.a) {
            this.b.c(r3_Throwable.toString());
        }
    }

    public Object proccess() throws QiushibaikeException {
        if (!this.a) {
            this.b.k();
        }
        String r0_String = null;
        if (TextUtils.isEmpty(this.b.af)) {
            r0_String = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
        } else if (TextUtils.isDigitsOnly(this.b.af)) {
            r0_String = Constants.AUDIT;
            Object[] r1_ObjectA = new Object[1];
            r1_ObjectA[0] = Integer.valueOf(Integer.parseInt(this.b.af));
            r0_String = String.format(r0_String, r1_ObjectA);
        }
        return HttpClient.getIntentce().get(r0_String);
    }

    public void success(Object r7_Object) {
        if (r7_Object == null || TextUtils.isEmpty((String) r7_Object)) {
            if (!this.a) {
                this.b.c("network no response");
            }
        } else {
            try {
                JSONObject r1_JSONObject = new JSONObject((String) r7_Object);
                if ((!r1_JSONObject.getString("err").equalsIgnoreCase("0")) || r1_JSONObject.isNull("articles")) {
                    if (!this.a) {
                        this.b.c("unknow error, perhaps param auth failed");
                    }
                } else {
                    JSONArray r2_JSONArray = new JSONArray(r1_JSONObject.getString("articles"));
                    int r3i = r2_JSONArray.length();
                    if (r3i > 0) {
                        int r0i = 0;
                        while (r0i < r3i) {
                            AuditArticle r5_AuditArticle = new AuditArticle(r2_JSONArray.optJSONObject(r0i));
                            if (this.a) {
                                this.b.ai.add(r5_AuditArticle);
                            } else {
                                this.b.ah.add(r5_AuditArticle);
                            }
                            r0i++;
                        }
                        if (!r1_JSONObject.isNull(KEYS.SID)) {
                            this.b.af = r1_JSONObject.getString(KEYS.SID);
                        }
                        if (!this.a) {
                            this.b.l();
                        }
                    } else {
                        if (!this.a) {
                            this.b.c("server return empty article");
                        }
                    }
                }
            } catch (JSONException e) {
                JSONException r0_JSONException = e;
                if (!this.a) {
                    this.b.c(r0_JSONException.toString());
                }
            } catch (QiushibaikeException e_2) {
                QiushibaikeException r0_QiushibaikeException = e_2;
                if (!this.a) {
                    this.b.c(r0_QiushibaikeException.toString());
                }
            }
        }
    }
}
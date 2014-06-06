package qsbk.app.utils;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.thirdparty.UsersAPI;

public class HttpRequestor {
    private Map<String, Object> a;
    private Map<String, String> b;
    private String c;
    private String d;

    public HttpRequestor(String r2_String) {
        this.a = null;
        this.b = null;
        this.c = UsersAPI.HTTPMETHOD_POST;
        this.d = r2_String;
    }

    public HttpRequestor(String r2_String, String r3_String) {
        this.a = null;
        this.b = null;
        this.c = UsersAPI.HTTPMETHOD_POST;
        if (TextUtils.isEmpty(r3_String)) {
            r3_String = UsersAPI.HTTPMETHOD_POST;
        }
        this.c = r3_String;
        this.d = r2_String;
    }

    public HttpRequestor addHeader(String r2_String, String r3_String) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        this.b.put(r2_String, r3_String);
        return this;
    }

    public HttpRequestor addParam(String r2_String, Object r3_Object) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(r2_String, r3_Object);
        return this;
    }

    public HttpRequestor addParam(Map<String, Object> r4_Map_String__Object) {
        if (r4_Map_String__Object == null || r4_Map_String__Object.isEmpty()) {
            return this;
        }
        Iterator r1_Iterator = r4_Map_String__Object.keySet().iterator();
        while (r1_Iterator.hasNext()) {
            String r0_String = (String) r1_Iterator.next();
            addParam(r0_String, r4_Map_String__Object.get(r0_String));
        }
        return this;
    }

    public String request() throws QiushibaikeException {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            r0_String = HttpClient.encodeParameters(this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HttpClient.getIntentce().httpRequest(this.d, r0_String, this.c, this.b);
    }
}
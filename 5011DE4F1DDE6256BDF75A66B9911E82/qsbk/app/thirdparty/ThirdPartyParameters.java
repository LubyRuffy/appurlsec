package qsbk.app.thirdparty;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;

public class ThirdPartyParameters {
    private ArrayList<String> a;
    private ArrayList<String> b;

    public ThirdPartyParameters() {
        this.a = new ArrayList();
        this.b = new ArrayList();
    }

    private int a(String r2_String) {
        return this.a.contains(r2_String) ? this.a.indexOf(r2_String) : -1;
    }

    public void add(String r3_String, int r4i) {
        this.a.add(r3_String);
        this.b.add(String.valueOf(r4i));
    }

    public void add(String r3_String, long r4j) {
        this.a.add(r3_String);
        this.b.add(String.valueOf(r4j));
    }

    public void add(String r2_String, String r3_String) {
        if (TextUtils.isEmpty(r2_String) || TextUtils.isEmpty(r3_String)) {
        } else {
            this.a.add(r2_String);
            this.b.add(r3_String);
        }
    }

    public void addAll(ThirdPartyParameters r4_ThirdPartyParameters) {
        int r0i = 0;
        while (r0i < r4_ThirdPartyParameters.size()) {
            add(r4_ThirdPartyParameters.getKey(r0i), r4_ThirdPartyParameters.getValue(r0i));
            r0i++;
        }
    }

    public void clear() {
        this.a.clear();
        this.b.clear();
    }

    public String getKey(int r2i) {
        return (r2i < 0 || r2i >= this.a.size()) ? RContactStorage.PRIMARY_KEY : (String) this.a.get(r2i);
    }

    public String getValue(int r2i) {
        return (r2i < 0 || r2i >= this.a.size()) ? null : (String) this.b.get(r2i);
    }

    public String getValue(String r3_String) {
        int r0i = a(r3_String);
        return (r0i < 0 || r0i >= this.a.size()) ? null : (String) this.b.get(r0i);
    }

    public void remove(int r2i) {
        if (r2i < this.a.size()) {
            this.a.remove(r2i);
            this.b.remove(r2i);
        }
    }

    public void remove(String r3_String) {
        int r0i = this.a.indexOf(r3_String);
        if (r0i >= 0) {
            this.a.remove(r0i);
            this.b.remove(r0i);
        }
    }

    public int size() {
        return this.a.size();
    }
}
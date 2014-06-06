package qsbk.app.cache;

import android.support.v4.util.LruCache;
import org.json.JSONObject;

// compiled from: TextCache.java
class i extends LruCache<String, JSONObject> {
    final /* synthetic */ TextCache a;

    i(TextCache r1_TextCache, int r2i) {
        this.a = r1_TextCache;
        super(r2i);
    }

    protected int a(String r2_String, JSONObject r3_JSONObject) {
        return 0;
    }
}
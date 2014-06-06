package qsbk.app.cache;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import java.io.File;
import org.json.JSONObject;
import qsbk.app.utils.image.RetainFragment;

public class TextCache {
    private DiskLruCache a;
    private LruCache<String, JSONObject> b;

    public static class TextCacheParams {
        public boolean clearDiskCacheOnStart;
        public boolean diskCacheEnabled;
        public int diskCacheSize;
        public int memCacheSize;
        public boolean memoryCacheEnabled;
        public String uniqueName;

        public TextCacheParams(String r3_String) {
            this.memCacheSize = 102400;
            this.diskCacheSize = 204800;
            this.memoryCacheEnabled = true;
            this.diskCacheEnabled = true;
            this.clearDiskCacheOnStart = false;
            this.uniqueName = r3_String;
        }
    }

    public TextCache(Context r2_Context, String r3_String) {
        a(r2_Context, new TextCacheParams(r3_String));
    }

    public TextCache(Context r1_Context, TextCacheParams r2_TextCacheParams) {
        a(r1_Context, r2_TextCacheParams);
    }

    private void a(Context r5_Context, TextCacheParams r6_TextCacheParams) {
        File r0_File = DiskLruCache.getDiskCacheDir(r5_Context, r6_TextCacheParams.uniqueName);
        if (r6_TextCacheParams.diskCacheEnabled) {
            this.a = DiskLruCache.openCache(r5_Context, r0_File, (long) r6_TextCacheParams.diskCacheSize, 0);
            if (r6_TextCacheParams.clearDiskCacheOnStart) {
                this.a.clearCache();
            }
        }
        if (r6_TextCacheParams.memoryCacheEnabled) {
            this.b = new i(this, r6_TextCacheParams.memCacheSize);
        }
    }

    public static TextCache findOrCreateCache(FragmentActivity r1_FragmentActivity, String r2_String) {
        return findOrCreateCache(r1_FragmentActivity, new TextCacheParams(r2_String));
    }

    public static TextCache findOrCreateCache(FragmentActivity r2_FragmentActivity, TextCacheParams r3_TextCacheParams) {
        RetainFragment r1_RetainFragment = RetainFragment.findOrCreateRetainFragment(r2_FragmentActivity.getSupportFragmentManager());
        TextCache r0_TextCache = (TextCache) r1_RetainFragment.getObject();
        if (r0_TextCache != null) {
            return r0_TextCache;
        }
        r0_TextCache = new TextCache((Context)r2_FragmentActivity, r3_TextCacheParams);
        r1_RetainFragment.setObject(r0_TextCache);
        return r0_TextCache;
    }

    public void addJSONObjectToCache(String r2_String, JSONObject r3_JSONObject) {
        if (r2_String == null || r3_JSONObject == null || this.b == null || this.b.get(r2_String) != null) {
        } else {
            this.b.put(r2_String, r3_JSONObject);
        }
    }

    public void clearCaches() {
        this.a.clearCache();
        this.b.evictAll();
    }

    public JSONObject getJSONObjectFromMemCache(String r2_String) {
        if (this.b != null) {
            JSONObject r0_JSONObject = (JSONObject) this.b.get(r2_String);
            if (r0_JSONObject != null) {
                return r0_JSONObject;
            }
        }
        return null;
    }

    public void writeTextToFile(String r1_String, String r2_String) {
    }
}
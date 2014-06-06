package com.androidquery.util;

import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class BitmapCache extends LinkedHashMap<String, Bitmap> {
    private int a;
    private int b;
    private int c;
    private int d;

    public BitmapCache(int r4i, int r5i, int r6i) {
        super(8, 0.75f, true);
        this.a = r4i;
        this.b = r5i;
        this.c = r6i;
    }

    private int a(Bitmap r3_Bitmap) {
        return r3_Bitmap == null ? 0 : r3_Bitmap.getWidth() * r3_Bitmap.getHeight();
    }

    private void a() {
        if (this.d > this.c) {
            Iterator r0_Iterator = keySet().iterator();
            while (r0_Iterator.hasNext()) {
                r0_Iterator.next();
                r0_Iterator.remove();
                if (this.d <= this.c) {
                }
            }
        }
    }

    public void clear() {
        super.clear();
        this.d = 0;
    }

    public Bitmap put(String r4_String, Bitmap r5_Bitmap) {
        Bitmap r0_Bitmap = null;
        int r1i = a(r5_Bitmap);
        if (r1i <= this.b) {
            this.d += r1i;
            r0_Bitmap = (Bitmap) super.put(r4_String, r5_Bitmap);
            if (r0_Bitmap != null) {
                this.d -= a(r0_Bitmap);
            }
        }
        return r0_Bitmap;
    }

    public Bitmap remove(Object r4_Object) {
        Bitmap r0_Bitmap = (Bitmap) super.remove(r4_Object);
        if (r0_Bitmap != null) {
            this.d -= a(r0_Bitmap);
        }
        return r0_Bitmap;
    }

    public boolean removeEldestEntry(Entry<String, Bitmap> r3_Entry_String__Bitmap) {
        if (this.d > this.c || size() > this.a) {
            remove(r3_Entry_String__Bitmap.getKey());
            a();
            return false;
        } else {
            a();
            return false;
        }
    }
}
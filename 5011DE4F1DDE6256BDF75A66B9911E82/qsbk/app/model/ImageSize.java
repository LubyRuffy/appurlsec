package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONArray;
import qsbk.app.widget.listview.XListViewHeader;

public class ImageSize implements Serializable {
    public static final int UNKNOW_SIZE = 0;
    private int a;
    private int b;
    private int c;

    private ImageSize() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public static ImageSize newImageSize(JSONArray r2_JSONArray) {
        ImageSize r0_ImageSize = new ImageSize();
        if (r2_JSONArray != null) {
            r0_ImageSize.a = r2_JSONArray.optInt(0);
            r0_ImageSize.b = r2_JSONArray.optInt(1);
            r0_ImageSize.c = r2_JSONArray.optInt(XListViewHeader.STATE_REFRESHING);
        }
        return r0_ImageSize;
    }

    public static final JSONArray toJSONArray(ImageSize r2_ImageSize) {
        JSONArray r0_JSONArray = new JSONArray();
        r0_JSONArray.put(r2_ImageSize.a);
        r0_JSONArray.put(r2_ImageSize.b);
        r0_JSONArray.put(r2_ImageSize.c);
        return r0_JSONArray;
    }

    public boolean equals(Object r5_Object) {
        if (r5_Object == this) {
            return true;
        }
        if (!(r5_Object instanceof ImageSize)) {
            return false;
        }
        ImageSize r5_ImageSize = (ImageSize) r5_Object;
        return r5_ImageSize.c == this.c && r5_ImageSize.b == this.b && r5_ImageSize.a == this.a;
    }

    public int getHeight() {
        return this.b;
    }

    public int getSize() {
        return this.c;
    }

    public int getWidth() {
        return this.a;
    }

    public void setHeight(int r1i) {
        this.b = r1i;
    }

    public void setSize(int r1i) {
        this.c = r1i;
    }

    public void setWidth(int r1i) {
        this.a = r1i;
    }

    public String toString() {
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = Integer.valueOf(this.a);
        r1_ObjectA[1] = Integer.valueOf(this.b);
        r1_ObjectA[2] = Integer.valueOf(this.c);
        return String.format("[%s,%s,%s]", r1_ObjectA);
    }
}
package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageSizes implements Serializable {
    private ImageSize a;
    private ImageSize b;

    private ImageSizes() {
    }

    public static ImageSizes newImageSizes(JSONObject r2_JSONObject) {
        if (r2_JSONObject == null) {
            return null;
        }
        ImageSizes r0_ImageSizes = new ImageSizes();
        r0_ImageSizes.a = ImageSize.newImageSize(r2_JSONObject.optJSONArray("s"));
        r0_ImageSizes.b = ImageSize.newImageSize(r2_JSONObject.optJSONArray("m"));
        return r0_ImageSizes;
    }

    public static JSONObject toJSONObject(ImageSizes r3_ImageSizes) {
        if (r3_ImageSizes == null) {
            return null;
        }
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put("s", ImageSize.toJSONArray(r3_ImageSizes.a));
            r0_JSONObject.put("m", ImageSize.toJSONArray(r3_ImageSizes.b));
            return r0_JSONObject;
        } catch (JSONException e) {
            return r0_JSONObject;
        }
    }

    public ImageSize mediumSize() {
        return this.b;
    }

    public void setMedium(ImageSize r1_ImageSize) {
        this.b = r1_ImageSize;
    }

    public void setSmall(ImageSize r1_ImageSize) {
        this.a = r1_ImageSize;
    }

    public ImageSize smallSize() {
        return this.a;
    }

    public String toString() {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = this.a;
        r1_ObjectA[1] = this.b;
        return String.format("s:%s, m:%s", r1_ObjectA);
    }
}
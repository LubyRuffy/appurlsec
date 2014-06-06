package qsbk.app.utils;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.Article;

public class CollectionUtils {
    private CollectionUtils() {
    }

    public static String artilesToJsonString(List<Object> r4_List_Object) {
        String r0_String = null;
        if (r4_List_Object == null || r4_List_Object.isEmpty()) {
            return (r0_String == null || r0_String.length() <= 2) ? "{items:[],total:0}" : r0_String;
        } else {
            JSONObject r1_JSONObject = new JSONObject();
            try {
                r1_JSONObject.put("total", r4_List_Object.size());
                JSONArray r2_JSONArray = new JSONArray();
                Iterator r3_Iterator = r4_List_Object.iterator();
                while (r3_Iterator.hasNext()) {
                    r2_JSONArray.put(((Article) r3_Iterator.next()).toJSONObject());
                }
                r1_JSONObject.put("items", r2_JSONArray);
                r0_String = r1_JSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                r0_String = r1_JSONObject.toString();
            }
            if (r0_String == null || r0_String.length() <= 2) {
            }
        }
    }
}
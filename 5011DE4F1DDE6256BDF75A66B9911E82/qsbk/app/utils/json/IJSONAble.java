package qsbk.app.utils.json;

import org.json.JSONObject;

public interface IJSONAble {
    public JSONObject encodeToJsonObject();

    public void initJSONDefaultValue();

    public void parseFromJSONObject(JSONObject r1_JSONObject);
}
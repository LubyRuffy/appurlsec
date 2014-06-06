package qsbk.app.utils.json;

import org.json.JSONObject;
import qsbk.app.utils.LogUtil;

public class JSONAble implements IJSONAble {

    public static class A extends JSONAble {
        public static int st;
        public String a;
        public String b;
        public String c;
        private String d;

        public A() {
            this.d = "should in a but should not in b";
        }
    }

    public static class B extends qsbk.app.utils.json.JSONAble.A {
        public String d;
        public String e;
    }

    public static void test1() {
        A r0_A = new A();
        A.st = 2;
        r0_A.a = "hello";
        r0_A.b = "world";
        r0_A.c = "should not out";
        LogUtil.d("test json:" + r0_A.encodeToJsonObject().toString());
        A r1_A = new A();
        r1_A.parseFromJSONObject(r0_A.encodeToJsonObject());
        LogUtil.d("a.a " + r1_A.a);
        LogUtil.d("a.b " + r1_A.b);
        LogUtil.d("a.c " + r1_A.c);
        B r0_B = new B();
        r0_B.a = "hello";
        r0_B.b = "world";
        r0_B.d = "new property";
        r0_B.e = "new key name";
        B r1_B = new B();
        r1_B.parseFromJSONObject(r0_B.encodeToJsonObject());
        LogUtil.d("b1.a " + r1_B.a);
        LogUtil.d("b1.b " + r1_B.b);
        LogUtil.d("b1.d " + r1_B.d);
        LogUtil.d("b1.e " + r1_B.e);
        LogUtil.d("test json:" + r0_B.encodeToJsonObject().toString());
    }

    public JSONObject encodeToJsonObject() {
        return JSONUtil.encodeToJsonObject(this);
    }

    public void initJSONDefaultValue() {
    }

    public void parseFromJSONObject(JSONObject r1_JSONObject) {
        JSONUtil.parseFromJSONObject(r1_JSONObject, this);
    }
}
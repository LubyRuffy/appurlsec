package qsbk.app.model;

import java.util.HashMap;
import qsbk.app.utils.json.JSONAble;

public class BaseUserInfo extends JSONAble {
    public static final String FEMALE = "F";
    public static final String GENDER_UNKONW = "U";
    public static final String MALE = "M";
    public static final HashMap<String, String> MAP;
    public int age;
    public String astrology;
    public long birthday;
    public String gender;
    public String signature;
    public String userIcon;
    public String userId;
    public String userName;

    static {
        MAP = new a();
    }

    public BaseUserInfo() {
        this.gender = GENDER_UNKONW;
    }
}
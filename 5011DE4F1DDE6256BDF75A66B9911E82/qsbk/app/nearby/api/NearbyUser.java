package qsbk.app.nearby.api;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.DateUtil;

public class NearbyUser extends BaseUserInfo {
    public static final String FEMALE = "F";
    public static final String MALE = "M";
    public int mDistance;
    public int mLastVisitTime;

    public NearbyUser() {
        this.mLastVisitTime = 0;
    }

    public static String getDistanceStr(int r7i) {
        if (r7i <= 100) {
            return "0.1km";
        }
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = Double.valueOf(((double) Math.round(((double) r7i) / 100.0d)) / 10.0d);
        return String.format("%.1fkm", r1_ObjectA);
    }

    public static List<NearbyUser> getNearbyUserFromJson(JSONObject r6_JSONObject) {
        List<NearbyUser> r0_List_NearbyUser = new ArrayList();
        NearbyUser r1_NearbyUser = new NearbyUser();
        r1_NearbyUser.userName = "\u9006\u98ce\u8e66\u54d2\u7684\u951a\u5730";
        r1_NearbyUser.mDistance = 150;
        r1_NearbyUser.signature = "\u597d\u60f3\u6536\u5230\u5c0f\u7eb8\u6761";
        r1_NearbyUser.gender = FEMALE;
        r1_NearbyUser.userIcon = RContactStorage.PRIMARY_KEY;
        r1_NearbyUser.mLastVisitTime = ((int) (System.currentTimeMillis() / 1000)) - 100;
        r0_List_NearbyUser.add(r1_NearbyUser);
        r1_NearbyUser = new NearbyUser();
        r1_NearbyUser.userName = "djboy";
        r1_NearbyUser.mDistance = 200;
        r1_NearbyUser.signature = "\u4e13\u4e1a\u70b9\u8d5e\uff0c10\u5143\u4e00\u6b21\uff0c50\u5143\u5305\u6708";
        r1_NearbyUser.gender = MALE;
        r1_NearbyUser.userIcon = RContactStorage.PRIMARY_KEY;
        r1_NearbyUser.mLastVisitTime = ((int) (System.currentTimeMillis() / 1000)) - 3601;
        r0_List_NearbyUser.add(r1_NearbyUser);
        r1_NearbyUser = new NearbyUser();
        r1_NearbyUser.userName = "\u5fc3\u91cc\u6709\u672f";
        r1_NearbyUser.mDistance = 350;
        r1_NearbyUser.signature = "\u5982\u679c\u5bf9\u6211\u6ee1\u610f\u8bf7\u52a0\u6211\u597d\u53cb\u4f60\u597d\u6c34\u7535\u8d39\u6c34\u7535\u8d39\u6c34\u7535\u8d39\u6c34\u7535\u8d39";
        r1_NearbyUser.gender = MALE;
        r1_NearbyUser.userIcon = RContactStorage.PRIMARY_KEY;
        r1_NearbyUser.mLastVisitTime = ((int) (System.currentTimeMillis() / 1000)) - 90000;
        r0_List_NearbyUser.add(r1_NearbyUser);
        return r0_List_NearbyUser;
    }

    public boolean equals(Object r4_Object) {
        return r4_Object instanceof NearbyUser && this.userId != null && ((NearbyUser) r4_Object).userId.equals(this.userId);
    }

    public String getDistanceStr() {
        return getDistanceStr(this.mDistance);
    }

    public String getLastLogin() {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = getDistanceStr();
        r1_ObjectA[1] = getTimePost();
        return String.format("%s | %s", r1_ObjectA);
    }

    public String getTimePost() {
        return DateUtil.getTimePostStr(((int) (System.currentTimeMillis() / 1000)) - this.mLastVisitTime);
    }
}
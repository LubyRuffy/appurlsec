package qsbk.app.model;

import java.util.HashMap;
import qsbk.app.nearby.ui.NearbySelectView;

// compiled from: BaseUserInfo.java
final class a extends HashMap<String, String> {
    a() {
        put(NearbySelectView.GENDER_MALE, "\u7537");
        put(NearbySelectView.GENDER_FEMALE, "\u5973");
        put(BaseUserInfo.GENDER_UNKONW, "\u672a\u77e5");
    }
}
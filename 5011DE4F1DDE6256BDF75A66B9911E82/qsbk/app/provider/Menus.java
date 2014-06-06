package qsbk.app.provider;

import com.tencent.mm.sdk.contact.RContactStorage;

public class Menus {
    public static final int[] ContentMenuTypes;
    public static final int TYPE_DIVIDER_ITEM = 0;
    public static final int TYPE_MENU_ITEM = 1;
    public static final int[] UseSettingMenuTypes;
    public static final String[] UserSettingMenus;
    public static final int[] menusIcon;

    public class MenuOrder {
        public static final int ABOUT = 7;
        public static final int BRIGHTNESS_SETTING = 0;
        public static final int FEEDBACK = 5;
        public static final int IMAGE_LOAD = 1;
        public static final int USER_SETTING_ACTIVITY = 3;
        public static final int VERSION_CHECK = 6;
    }

    static {
        String[] r0_StringA = new String[8];
        r0_StringA[0] = "\u5c4f\u5e55\u4eae\u5ea6";
        r0_StringA[1] = "\u56fe\u7247\u52a0\u8f7d\u65b9\u5f0f";
        r0_StringA[2] = RContactStorage.PRIMARY_KEY;
        r0_StringA[3] = "\u6211\u7684\u8d26\u53f7";
        r0_StringA[4] = RContactStorage.PRIMARY_KEY;
        r0_StringA[5] = "\u610f\u89c1\u53cd\u9988";
        r0_StringA[6] = "\u65b0\u7248\u672c\u68c0\u6d4b";
        r0_StringA[7] = "\u5173\u4e8e\u7cd7\u767e";
        UserSettingMenus = r0_StringA;
        UseSettingMenuTypes = new int[]{1, 1, 0, 1, 0, 1, 1, 1};
        ContentMenuTypes = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1};
        menusIcon = new int[0];
    }
}
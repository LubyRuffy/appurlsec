package qsbk.app.provider;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.activity.AuditNativeActivity;
import qsbk.app.activity.group.EssenceActivityGroup;
import qsbk.app.activity.group.HistoryActivityGroup;
import qsbk.app.activity.group.ImagesActivityGroup;
import qsbk.app.activity.group.TopActivityGroup;
import qsbk.app.message.ui.MessageListActivityGroup;
import qsbk.app.nearby.ui.NearActivityGroup;

public class QBMenu {
    private static List<QBMenu> g;
    private String a;
    private MenuType b;
    private Class c;
    private boolean d;
    private boolean e;
    private boolean f;

    public static enum MenuType {
        MENU,
        SPLIT_LINE;


        static {
            MENU = new qsbk.app.provider.QBMenu.MenuType("MENU", 0);
            SPLIT_LINE = new qsbk.app.provider.QBMenu.MenuType("SPLIT_LINE", 1);
            qsbk.app.provider.QBMenu.MenuType[] r0_qsbk_app_provider_QBMenu_MenuTypeA = new qsbk.app.provider.QBMenu.MenuType[2];
            r0_qsbk_app_provider_QBMenu_MenuTypeA[0] = MENU;
            r0_qsbk_app_provider_QBMenu_MenuTypeA[1] = SPLIT_LINE;
            a = r0_qsbk_app_provider_QBMenu_MenuTypeA;
        }
    }

    static {
        g = null;
    }

    public QBMenu(MenuType r2_MenuType, String r3_String, Class r4_Class) {
        this.d = false;
        this.e = false;
        this.f = true;
        this.b = r2_MenuType;
        this.a = r3_String;
        this.c = r4_Class;
    }

    public QBMenu(MenuType r1_MenuType, String r2_String, Class r3_Class, boolean r4z) {
        this(r1_MenuType, r2_String, r3_Class);
        this.d = r4z;
    }

    public static List<QBMenu> getContentMenu() {
        if (g == null) {
            g = new ArrayList();
            g.add(new QBMenu(MenuType.MENU, "\u70ed\u95e8", TopActivityGroup.class));
            g.add(new QBMenu(MenuType.MENU, "\u7cbe\u534e", EssenceActivityGroup.class));
            g.add(new QBMenu(MenuType.MENU, "\u6709\u56fe\u6709\u771f\u76f8", ImagesActivityGroup.class));
            g.add(new QBMenu(MenuType.MENU, "\u7a7f\u8d8a", HistoryActivityGroup.class));
            g.add(new QBMenu(MenuType.SPLIT_LINE, RContactStorage.PRIMARY_KEY, null));
            g.add(new QBMenu(MenuType.MENU, "\u5c0f\u7eb8\u6761", MessageListActivityGroup.class));
            g.add(new QBMenu(MenuType.MENU, "\u9644\u8fd1", NearActivityGroup.class, true));
            g.add(new QBMenu(MenuType.SPLIT_LINE, RContactStorage.PRIMARY_KEY, null));
            QBMenu r0_QBMenu = new QBMenu(MenuType.MENU, "\u5ba1\u6838\u7cd7\u4e8b", AuditNativeActivity.class, true);
            r0_QBMenu.setNeedCheckNet(true);
            r0_QBMenu.setmNeedFinish(false);
            g.add(r0_QBMenu);
        }
        return g;
    }

    public Class getMenuClass() {
        return this.c;
    }

    public String getName() {
        return this.a;
    }

    public MenuType getType() {
        return this.b;
    }

    public boolean isNeedLogin() {
        return this.d;
    }

    public boolean needCheckNetStatus() {
        return this.e;
    }

    public boolean needFinish() {
        return this.f;
    }

    public void setNeedCheckNet(boolean r1z) {
        this.e = r1z;
    }

    public void setNeedLogin(boolean r1z) {
        this.d = r1z;
    }

    public void setmNeedFinish(boolean r1z) {
        this.f = r1z;
    }
}
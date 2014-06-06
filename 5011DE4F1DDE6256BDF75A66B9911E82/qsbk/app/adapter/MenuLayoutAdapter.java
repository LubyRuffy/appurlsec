package qsbk.app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LoginActivity;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.message.ui.MessageListActivityGroup;
import qsbk.app.provider.QBMenu;
import qsbk.app.provider.QBMenu.MenuType;
import qsbk.app.utils.Base64;
import qsbk.app.utils.UIHelper;

public abstract class MenuLayoutAdapter extends BaseAdapter {
    protected Activity a;
    protected boolean[] b;
    protected Class c;
    protected List<QBMenu> d;
    private List<Map<String, Object>> e;
    private int f;
    private LayoutInflater g;
    private int h;
    private boolean i;
    private LayoutParams j;

    public static class MenuItemParams {
        public int initPressedId;

        public MenuItemParams(int r1i) {
            this.initPressedId = r1i;
        }
    }

    public class MenuItemsView {
        public TextView menuText;
        public TextView selectItemText;
    }

    public MenuLayoutAdapter() {
        this.h = -1;
        this.i = false;
        this.c = null;
        this.d = null;
    }

    protected String a() {
        return "\u81ea\u52a8\u52a0\u8f7d";
    }

    protected void a(Activity r6_Activity, int r7i, Class r8_Class, List<QBMenu> r9_List_QBMenu) {
        this.a = r6_Activity;
        this.c = r8_Class;
        this.d = r9_List_QBMenu;
        this.h = r7i;
        this.j = new LayoutParams(-1, -2);
        this.e = new ArrayList();
        this.f = this.d.size();
        this.b = new boolean[this.f];
        int r1i = 0;
        while (r1i < this.f) {
            Map r3_Map = new HashMap();
            r3_Map.put("menuText", ((QBMenu) this.d.get(r1i)).getName());
            this.e.add(r3_Map);
            this.b[r1i] = false;
            if (this.c == ((QBMenu) this.d.get(r1i)).getMenuClass()) {
                this.b[r1i] = true;
            }
            r1i++;
        }
        this.g = LayoutInflater.from(r6_Activity);
    }

    protected void a(Intent r4_Intent) {
        this.a.startActivity(r4_Intent);
        this.a.overridePendingTransition(R.anim.roll_right, R.anim.still_when_right);
        this.a.finish();
    }

    protected void a(Class<?> r4_Class_) {
        Intent r0_Intent = new Intent(this.a, LoginActivity.class);
        r0_Intent.putExtra("targetClass", r4_Class_);
        this.a.startActivity(r0_Intent);
        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }

    protected abstract boolean a(int r1i);

    protected boolean b(int r2i) {
        return true;
    }

    protected void c(int r4i) {
        int r0i = 0;
        while (r0i < this.f) {
            this.b[r0i] = false;
            r0i++;
        }
        this.b[r4i] = true;
    }

    protected boolean d(int r3i) {
        QBMenu r0_QBMenu = (QBMenu) this.d.get(r3i);
        return r0_QBMenu.getMenuClass() != null && r0_QBMenu.getMenuClass().equals(MessageListActivityGroup.class);
    }

    protected boolean e(int r2i) {
        return false;
    }

    public int getCount() {
        return this.f;
    }

    public Object getItem(int r2i) {
        return Integer.valueOf(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public View getView(int r7i, View r8_View, ViewGroup r9_ViewGroup) {
        OnClickListener r2_OnClickListener = null;
        if (((QBMenu) this.d.get(r7i)).getType() == MenuType.MENU) {
            View r1_View = this.g.inflate(R.layout.layout_menu_list_item, r2_OnClickListener);
            MenuItemsView r2_MenuItemsView = new MenuItemsView();
            r2_MenuItemsView.menuText = (TextView) r1_View.findViewById(R.id.menuText);
            if (e(r7i)) {
                r2_MenuItemsView.selectItemText = (TextView) r1_View.findViewById(R.id.selectItem);
                r2_MenuItemsView.selectItemText.setText(a());
            }
            r2_MenuItemsView.menuText.setText((String) ((Map) this.e.get(r7i)).get("menuText"));
            if (this.b[r7i]) {
                if ((!this.i) || this.h == -1 || e(r7i)) {
                    if (UIHelper.isNightTheme()) {
                        r1_View.findViewById(R.id.itembg).setBackgroundResource(R.drawable.side_menu_background_active);
                    } else {
                        r1_View.findViewById(R.id.itembg).setBackgroundResource(R.drawable.side_menu_background_active_night);
                    }
                } else {
                    r1_View.findViewById(R.id.itembg).setAnimation(AnimationUtils.loadAnimation(this.a, this.h));
                    if (UIHelper.isNightTheme()) {
                        r1_View.findViewById(R.id.itembg).setBackgroundResource(R.drawable.side_menu_background_active);
                    } else {
                        r1_View.findViewById(R.id.itembg).setBackgroundResource(R.drawable.side_menu_background_active_night);
                    }
                }
            }
            if ((!d(r7i)) || QsbkApp.currentUser == null || ChatEngine.unreadCount <= 0) {
                r1_View.findViewById(R.id.messageHint).setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                r1_View.findViewById(R.id.messageHint).setVisibility(0);
                ((TextView) r1_View.findViewById(R.id.messageHint)).setText(ChatEngine.unreadCount + RContactStorage.PRIMARY_KEY);
            }
            r1_View.setOnClickListener(new g(this, r7i, r7i));
            return r1_View;
        } else {
            View r0_View = new ImageView(this.a);
            if (UIHelper.isNightTheme()) {
                r0_View.setBackgroundResource(R.drawable.side_menu_gourp_divider_night);
            } else {
                r0_View.setBackgroundResource(R.drawable.side_menu_gourp_divider);
            }
            r0_View.setLayoutParams(this.j);
            r0_View.setOnClickListener(null);
            return r0_View;
        }
    }

    public void resume() {
        this.i = true;
        notifyDataSetInvalidated();
    }
}
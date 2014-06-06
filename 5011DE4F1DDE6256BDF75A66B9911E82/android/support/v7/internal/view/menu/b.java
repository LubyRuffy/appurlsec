package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.HashMap;
import java.util.Iterator;

// compiled from: BaseMenuWrapper.java
abstract class b<T> extends c<T> {
    private HashMap<MenuItem, SupportMenuItem> b;
    private HashMap<SubMenu, SubMenu> c;

    b(T r1_T) {
        super(r1_T);
    }

    final SupportMenuItem a(MenuItem r3_MenuItem) {
        if (r3_MenuItem == null) {
            return null;
        }
        if (this.b == null) {
            this.b = new HashMap();
        }
        SupportMenuItem r0_SupportMenuItem = (SupportMenuItem) this.b.get(r3_MenuItem);
        if (r0_SupportMenuItem != null) {
            return r0_SupportMenuItem;
        }
        r0_SupportMenuItem = MenuWrapperFactory.createSupportMenuItemWrapper(r3_MenuItem);
        this.b.put(r3_MenuItem, r0_SupportMenuItem);
        return r0_SupportMenuItem;
    }

    final SubMenu a(SubMenu r3_SubMenu) {
        if (r3_SubMenu == null) {
            return null;
        }
        if (this.c == null) {
            this.c = new HashMap();
        }
        SubMenu r0_SubMenu = (SubMenu) this.c.get(r3_SubMenu);
        if (r0_SubMenu != null) {
            return r0_SubMenu;
        }
        r0_SubMenu = MenuWrapperFactory.createSupportSubMenuWrapper(r3_SubMenu);
        this.c.put(r3_SubMenu, r0_SubMenu);
        return r0_SubMenu;
    }

    final void a() {
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
    }

    final void a(int r3i) {
        if (this.b == null) {
        } else {
            Iterator r1_Iterator = this.b.keySet().iterator();
            while (r1_Iterator.hasNext() && r3i == ((MenuItem) r1_Iterator.next()).getGroupId()) {
                r1_Iterator.remove();
            }
        }
    }

    final void b_(int r3i) {
        if (this.b == null) {
        } else {
            Iterator r1_Iterator = this.b.keySet().iterator();
            while (r1_Iterator.hasNext()) {
                if (r3i == ((MenuItem) r1_Iterator.next()).getItemId()) {
                    r1_Iterator.remove();
                }
            }
        }
    }
}
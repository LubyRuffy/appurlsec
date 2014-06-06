package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

// compiled from: MenuWrapperICS.java
class g extends b<Menu> implements SupportMenu {
    g(Menu r1_Menu) {
        super(r1_Menu);
    }

    public MenuItem add(int r2i) {
        return a(((Menu) this.a).add(r2i));
    }

    public MenuItem add(int r2i, int r3i, int r4i, int r5i) {
        return a(((Menu) this.a).add(r2i, r3i, r4i, r5i));
    }

    public MenuItem add(int r2i, int r3i, int r4i, CharSequence r5_CharSequence) {
        return a(((Menu) this.a).add(r2i, r3i, r4i, r5_CharSequence));
    }

    public MenuItem add(CharSequence r2_CharSequence) {
        return a(((Menu) this.a).add(r2_CharSequence));
    }

    public int addIntentOptions(int r11i, int r12i, int r13i, ComponentName r14_ComponentName, Intent[] r15_IntentA, Intent r16_Intent, int r17i, MenuItem[] r18_MenuItemA) {
        MenuItem[] r9_MenuItemA = null;
        if (r18_MenuItemA != null) {
            r9_MenuItemA = new MenuItem[r18_MenuItemA.length];
        }
        int r2i = ((Menu) this.a).addIntentOptions(r11i, r12i, r13i, r14_ComponentName, r15_IntentA, r16_Intent, r17i, r9_MenuItemA);
        if (r9_MenuItemA != null) {
            int r1i = 0;
            int r3i = r9_MenuItemA.length;
            while (r1i < r3i) {
                r18_MenuItemA[r1i] = a(r9_MenuItemA[r1i]);
                r1i++;
            }
        }
        return r2i;
    }

    public SubMenu addSubMenu(int r2i) {
        return a(((Menu) this.a).addSubMenu(r2i));
    }

    public SubMenu addSubMenu(int r2i, int r3i, int r4i, int r5i) {
        return a(((Menu) this.a).addSubMenu(r2i, r3i, r4i, r5i));
    }

    public SubMenu addSubMenu(int r2i, int r3i, int r4i, CharSequence r5_CharSequence) {
        return a(((Menu) this.a).addSubMenu(r2i, r3i, r4i, r5_CharSequence));
    }

    public SubMenu addSubMenu(CharSequence r2_CharSequence) {
        return a(((Menu) this.a).addSubMenu(r2_CharSequence));
    }

    public void clear() {
        a();
        ((Menu) this.a).clear();
    }

    public void close() {
        ((Menu) this.a).close();
    }

    public MenuItem findItem(int r2i) {
        return a(((Menu) this.a).findItem(r2i));
    }

    public MenuItem getItem(int r2i) {
        return a(((Menu) this.a).getItem(r2i));
    }

    public boolean hasVisibleItems() {
        return ((Menu) this.a).hasVisibleItems();
    }

    public boolean isShortcutKey(int r2i, KeyEvent r3_KeyEvent) {
        return ((Menu) this.a).isShortcutKey(r2i, r3_KeyEvent);
    }

    public boolean performIdentifierAction(int r2i, int r3i) {
        return ((Menu) this.a).performIdentifierAction(r2i, r3i);
    }

    public boolean performShortcut(int r2i, KeyEvent r3_KeyEvent, int r4i) {
        return ((Menu) this.a).performShortcut(r2i, r3_KeyEvent, r4i);
    }

    public void removeGroup(int r2i) {
        a(r2i);
        ((Menu) this.a).removeGroup(r2i);
    }

    public void removeItem(int r2i) {
        b(r2i);
        ((Menu) this.a).removeItem(r2i);
    }

    public void setGroupCheckable(int r2i, boolean r3z, boolean r4z) {
        ((Menu) this.a).setGroupCheckable(r2i, r3z, r4z);
    }

    public void setGroupEnabled(int r2i, boolean r3z) {
        ((Menu) this.a).setGroupEnabled(r2i, r3z);
    }

    public void setGroupVisible(int r2i, boolean r3z) {
        ((Menu) this.a).setGroupVisible(r2i, r3z);
    }

    public void setQwertyMode(boolean r2z) {
        ((Menu) this.a).setQwertyMode(r2z);
    }

    public int size() {
        return ((Menu) this.a).size();
    }
}
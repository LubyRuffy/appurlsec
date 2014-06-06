package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
    private static final int[] d;
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private Callback i;
    private ArrayList<MenuItemImpl> j;
    private ArrayList<MenuItemImpl> k;
    private boolean l;
    private ArrayList<MenuItemImpl> m;
    private ArrayList<MenuItemImpl> n;
    private boolean o;
    private int p;
    private ContextMenuInfo q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private ArrayList<MenuItemImpl> v;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> w;
    private MenuItemImpl x;

    public static interface Callback {
        public boolean onMenuItemSelected(MenuBuilder r1_MenuBuilder, MenuItem r2_MenuItem);

        public void onMenuModeChange(MenuBuilder r1_MenuBuilder);
    }

    public static interface ItemInvoker {
        public boolean invokeItem(MenuItemImpl r1_MenuItemImpl);
    }

    static {
        d = new int[]{1, 4, 5, 3, 2, 0};
    }

    public MenuBuilder(Context r3_Context) {
        this.p = 0;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = new ArrayList();
        this.w = new CopyOnWriteArrayList();
        this.e = r3_Context;
        this.f = r3_Context.getResources();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = true;
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = true;
        d(true);
    }

    private static int a(int r2i) {
        int r0i = (-65536 & r2i) >> 16;
        if (r0i >= 0 && r0i < d.length) {
            return (d[r0i] << 16) | (65535 & r2i);
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private static int a(ArrayList<MenuItemImpl> r2_ArrayList_MenuItemImpl, int r3i) {
        int r1i = r2_ArrayList_MenuItemImpl.size() - 1;
        while (r1i >= 0) {
            if (((MenuItemImpl) r2_ArrayList_MenuItemImpl.get(r1i)).getOrdering() <= r3i) {
                return r1i + 1;
            }
            r1i--;
        }
        return 0;
    }

    private MenuItem a(int r9i, int r10i, int r11i, CharSequence r12_CharSequence) {
        int r5i = a(r11i);
        MenuItem r0_MenuItem = new MenuItemImpl(this, r9i, r10i, r11i, r5i, r12_CharSequence, this.p);
        if (this.q != null) {
            r0_MenuItem.a(this.q);
        }
        this.j.add(a(this.j, r5i), r0_MenuItem);
        b(true);
        return r0_MenuItem;
    }

    private void a(int r4i, CharSequence r5_CharSequence, int r6i, Drawable r7_Drawable, View r8_View) {
        Resources r0_Resources = a();
        if (r8_View != null) {
            this.c = r8_View;
            this.a = null;
            this.b = null;
        } else {
            if (r4i > 0) {
                this.a = r0_Resources.getText(r4i);
            } else if (r5_CharSequence != null) {
                this.a = r5_CharSequence;
            }
            if (r6i > 0) {
                this.b = r0_Resources.getDrawable(r6i);
            } else if (r7_Drawable != null) {
                this.b = r7_Drawable;
            }
            this.c = null;
        }
        b(false);
    }

    private void a(int r2i, boolean r3z) {
        if (r2i < 0 || r2i >= this.j.size()) {
        } else {
            this.j.remove(r2i);
            if (r3z) {
                b(true);
            }
        }
    }

    private void a(Bundle r5_Bundle) {
        if (this.w.isEmpty()) {
        } else {
            SparseArray r2_SparseArray = new SparseArray();
            Iterator r3_Iterator = this.w.iterator();
            while (r3_Iterator.hasNext()) {
                WeakReference r0_WeakReference = (WeakReference) r3_Iterator.next();
                MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
                if (r1_MenuPresenter == null) {
                    this.w.remove(r0_WeakReference);
                } else {
                    int r0i = r1_MenuPresenter.getId();
                    if (r0i > 0) {
                        Parcelable r1_Parcelable = r1_MenuPresenter.onSaveInstanceState();
                        if (r1_Parcelable != null) {
                            r2_SparseArray.put(r0i, r1_Parcelable);
                        }
                    }
                }
            }
            r5_Bundle.putSparseParcelableArray("android:menu:presenters", r2_SparseArray);
        }
    }

    private boolean a(SubMenuBuilder r5_SubMenuBuilder) {
        if (this.w.isEmpty()) {
            return false;
        }
        Iterator r3_Iterator = this.w.iterator();
        boolean r2z = false;
        while (r3_Iterator.hasNext()) {
            boolean r0z;
            WeakReference r0_WeakReference = (WeakReference) r3_Iterator.next();
            MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
            if (r1_MenuPresenter == null) {
                this.w.remove(r0_WeakReference);
                r0z = r2z;
            } else if (r2z) {
                r0z = r2z;
            } else {
                r0z = r1_MenuPresenter.onSubMenuSelected(r5_SubMenuBuilder);
            }
            r2z = r0z;
        }
        return r2z;
    }

    private void b(Bundle r5_Bundle) {
        SparseArray r2_SparseArray = r5_Bundle.getSparseParcelableArray("android:menu:presenters");
        if (r2_SparseArray == null || this.w.isEmpty()) {
        } else {
            Iterator r3_Iterator = this.w.iterator();
            while (r3_Iterator.hasNext()) {
                WeakReference r0_WeakReference = (WeakReference) r3_Iterator.next();
                MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
                if (r1_MenuPresenter == null) {
                    this.w.remove(r0_WeakReference);
                } else {
                    int r0i = r1_MenuPresenter.getId();
                    if (r0i > 0) {
                        Parcelable r0_Parcelable = (Parcelable) r2_SparseArray.get(r0i);
                        if (r0_Parcelable != null) {
                            r1_MenuPresenter.onRestoreInstanceState(r0_Parcelable);
                        }
                    }
                }
            }
        }
    }

    private void c(boolean r4z) {
        if (this.w.isEmpty()) {
        } else {
            stopDispatchingItemsChanged();
            Iterator r2_Iterator = this.w.iterator();
            while (r2_Iterator.hasNext()) {
                WeakReference r0_WeakReference = (WeakReference) r2_Iterator.next();
                MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
                if (r1_MenuPresenter == null) {
                    this.w.remove(r0_WeakReference);
                } else {
                    r1_MenuPresenter.updateMenuView(r4z);
                }
            }
            startDispatchingItemsChanged();
        }
    }

    private void d(boolean r4z) {
        boolean r0z = true;
        if (r4z && this.f.getConfiguration().keyboard != 1 && this.f.getBoolean(R.bool.abc_config_showMenuShortcutsWhenKeyboardPresent)) {
            this.h = r0z;
        } else {
            r0z = false;
            this.h = r0z;
        }
    }

    Resources a() {
        return this.f;
    }

    protected MenuBuilder a(Drawable r7_Drawable) {
        a(0, null, 0, r7_Drawable, null);
        return this;
    }

    protected MenuBuilder a(View r7_View) {
        a(0, null, 0, null, r7_View);
        return this;
    }

    protected MenuBuilder a(CharSequence r7_CharSequence) {
        a(0, r7_CharSequence, 0, null, null);
        return this;
    }

    MenuItemImpl a(int r13i, KeyEvent r14_KeyEvent) {
        ArrayList r5_ArrayList = this.v;
        r5_ArrayList.clear();
        a(r5_ArrayList, r13i, r14_KeyEvent);
        if (r5_ArrayList.isEmpty()) {
            return null;
        }
        int r6i = r14_KeyEvent.getMetaState();
        KeyData r7_KeyData = new KeyData();
        r14_KeyEvent.getKeyData(r7_KeyData);
        int r8i = r5_ArrayList.size();
        if (r8i == 1) {
            return (MenuItemImpl) r5_ArrayList.get(0);
        }
        boolean r9z = isQwertyMode();
        int r3i = 0;
        while (r3i < r8i) {
            char r1c;
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) r5_ArrayList.get(r3i);
            r1c = r9z ? r0_MenuItemImpl.getAlphabeticShortcut() : r0_MenuItemImpl.getNumericShortcut();
            if (r1c == r7_KeyData.meta[0] && (r6i & 2) == 0) {
                return r0_MenuItemImpl;
            }
            if (r1c == r7_KeyData.meta[2] && (r6i & 2) != 0) {
                return r0_MenuItemImpl;
            }
            if (r9z && r1c == '\b' && r13i == 67) {
                return r0_MenuItemImpl;
            }
            r3i++;
        }
        return null;
    }

    void a(MenuItemImpl r2_MenuItemImpl) {
        this.l = true;
        b(true);
    }

    void a(MenuItem r7_MenuItem) {
        int r4i = r7_MenuItem.getGroupId();
        int r5i = this.j.size();
        int r3i = 0;
        while (r3i < r5i) {
            MenuItem r0_MenuItem = (MenuItemImpl) this.j.get(r3i);
            if (r0_MenuItem.getGroupId() == r4i && r0_MenuItem.isExclusiveCheckable() && r0_MenuItem.isCheckable()) {
                r0_MenuItem.a(r0_MenuItem == r7_MenuItem);
                r3i++;
            } else {
                r3i++;
            }
        }
    }

    void a(List<MenuItemImpl> r12_List_MenuItemImpl, int r13i, KeyEvent r14_KeyEvent) {
        boolean r4z = isQwertyMode();
        int r5i = r14_KeyEvent.getMetaState();
        KeyData r6_KeyData = new KeyData();
        if (r14_KeyEvent.getKeyData(r6_KeyData) || r13i == 67) {
            int r7i = this.j.size();
            int r2i = 0;
            while (r2i < r7i) {
                char r1c;
                MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r2i);
                if (r0_MenuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) r0_MenuItemImpl.getSubMenu()).a(r12_List_MenuItemImpl, r13i, r14_KeyEvent);
                }
                r1c = r4z ? r0_MenuItemImpl.getAlphabeticShortcut() : r0_MenuItemImpl.getNumericShortcut();
                if ((r5i & 5) != 0 || r1c == '\u0000') {
                    r2i++;
                } else if (r1c == r6_KeyData.meta[0] || r1c == r6_KeyData.meta[2]) {
                    if (!!r0_MenuItemImpl.isEnabled()) {
                        r12_List_MenuItemImpl.add(r0_MenuItemImpl);
                    }
                    r2i++;
                } else if (r4z && r1c == '\b' && r13i == 67 && !r0_MenuItemImpl.isEnabled()) {
                    r12_List_MenuItemImpl.add(r0_MenuItemImpl);
                    r2i++;
                } else {
                    r2i++;
                }
            }
        }
    }

    final void a(boolean r4z) {
        if (this.u) {
        } else {
            this.u = true;
            Iterator r2_Iterator = this.w.iterator();
            while (r2_Iterator.hasNext()) {
                WeakReference r0_WeakReference = (WeakReference) r2_Iterator.next();
                MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
                if (r1_MenuPresenter == null) {
                    this.w.remove(r0_WeakReference);
                } else {
                    r1_MenuPresenter.onCloseMenu(this, r4z);
                }
            }
            this.u = false;
        }
    }

    public MenuItem add(int r3i) {
        return a(0, 0, 0, this.f.getString(r3i));
    }

    public MenuItem add(int r2i, int r3i, int r4i, int r5i) {
        return a(r2i, r3i, r4i, this.f.getString(r5i));
    }

    public MenuItem add(int r2i, int r3i, int r4i, CharSequence r5_CharSequence) {
        return a(r2i, r3i, r4i, r5_CharSequence);
    }

    public MenuItem add(CharSequence r2_CharSequence) {
        return a(0, 0, 0, r2_CharSequence);
    }

    public int addIntentOptions(int r10i, int r11i, int r12i, ComponentName r13_ComponentName, Intent[] r14_IntentA, Intent r15_Intent, int r16i, MenuItem[] r17_MenuItemA) {
        int r3i;
        PackageManager r4_PackageManager = this.e.getPackageManager();
        List r5_List = r4_PackageManager.queryIntentActivityOptions(r13_ComponentName, r14_IntentA, r15_Intent, 0);
        r3i = r5_List != null ? r5_List.size() : 0;
        if ((r16i & 1) == 0) {
            removeGroup(r10i);
        }
        int r2i = 0;
        while (r2i < r3i) {
            ResolveInfo r0_ResolveInfo = (ResolveInfo) r5_List.get(r2i);
            Intent r6_Intent = new Intent(r0_ResolveInfo.specificIndex < 0 ? r15_Intent : r14_IntentA[r0_ResolveInfo.specificIndex]);
            r6_Intent.setComponent(new ComponentName(r0_ResolveInfo.activityInfo.applicationInfo.packageName, r0_ResolveInfo.activityInfo.name));
            MenuItem r1_MenuItem = add(r10i, r11i, r12i, r0_ResolveInfo.loadLabel(r4_PackageManager)).setIcon(r0_ResolveInfo.loadIcon(r4_PackageManager)).setIntent(r6_Intent);
            if (r17_MenuItemA == null || r0_ResolveInfo.specificIndex < 0) {
                r2i++;
            } else {
                r17_MenuItemA[r0_ResolveInfo.specificIndex] = r1_MenuItem;
                r2i++;
            }
        }
        return r3i;
    }

    public void addMenuPresenter(MenuPresenter r3_MenuPresenter) {
        this.w.add(new WeakReference(r3_MenuPresenter));
        r3_MenuPresenter.initForMenu(this.e, this);
        this.o = true;
    }

    public SubMenu addSubMenu(int r3i) {
        return addSubMenu(0, 0, 0, this.f.getString(r3i));
    }

    public SubMenu addSubMenu(int r2i, int r3i, int r4i, int r5i) {
        return addSubMenu(r2i, r3i, r4i, this.f.getString(r5i));
    }

    public SubMenu addSubMenu(int r4i, int r5i, int r6i, CharSequence r7_CharSequence) {
        MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) a(r4i, r5i, r6i, r7_CharSequence);
        SubMenuBuilder r1_SubMenuBuilder = new SubMenuBuilder(this.e, this, r0_MenuItemImpl);
        r0_MenuItemImpl.a(r1_SubMenuBuilder);
        return r1_SubMenuBuilder;
    }

    public SubMenu addSubMenu(CharSequence r2_CharSequence) {
        return addSubMenu(0, 0, 0, r2_CharSequence);
    }

    ArrayList<MenuItemImpl> b() {
        if (!(this.l)) {
            return this.k;
        }
        this.k.clear();
        int r3i = this.j.size();
        int r1i = 0;
        while (r1i < r3i) {
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r1i);
            if (r0_MenuItemImpl.isVisible()) {
                this.k.add(r0_MenuItemImpl);
            }
            r1i++;
        }
        this.l = false;
        this.o = true;
        return this.k;
    }

    void b(MenuItemImpl r2_MenuItemImpl) {
        this.o = true;
        b(true);
    }

    void b(boolean r3z) {
        if (this.r) {
            this.s = true;
        } else {
            if (r3z) {
                this.l = true;
                this.o = true;
            }
            c(r3z);
        }
    }

    ArrayList<MenuItemImpl> c() {
        flagActionItems();
        return this.m;
    }

    public void changeMenuMode() {
        if (this.i != null) {
            this.i.onMenuModeChange(this);
        }
    }

    public void clear() {
        if (this.x != null) {
            collapseItemActionView(this.x);
        }
        this.j.clear();
        b(true);
    }

    public void clearAll() {
        this.r = true;
        clear();
        clearHeader();
        this.r = false;
        this.s = false;
        b(true);
    }

    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        b(false);
    }

    public void close() {
        a(true);
    }

    public boolean collapseItemActionView(MenuItemImpl r5_MenuItemImpl) {
        boolean r0z = false;
        if (this.w.isEmpty() || this.x != r5_MenuItemImpl) {
            return r0z;
        }
        stopDispatchingItemsChanged();
        Iterator r3_Iterator = this.w.iterator();
        boolean r2z = false;
        while (r3_Iterator.hasNext()) {
            WeakReference r0_WeakReference = (WeakReference) r3_Iterator.next();
            MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
            if (r1_MenuPresenter == null) {
                this.w.remove(r0_WeakReference);
                r0z = r2z;
            } else {
                r0z = r1_MenuPresenter.collapseItemActionView(this, r5_MenuItemImpl);
                if (r0z) {
                    break;
                }
            }
            r2z = r0z;
        }
        r0z = r2z;
        startDispatchingItemsChanged();
        if (r0z) {
            this.x = null;
        }
        return r0z;
    }

    ArrayList<MenuItemImpl> d() {
        flagActionItems();
        return this.n;
    }

    boolean dispatchMenuItemSelected(MenuBuilder r2_MenuBuilder, MenuItem r3_MenuItem) {
        return this.i != null && this.i.onMenuItemSelected(r2_MenuBuilder, r3_MenuItem);
    }

    boolean e() {
        return this.t;
    }

    public boolean expandItemActionView(MenuItemImpl r5_MenuItemImpl) {
        boolean r0z = false;
        if (this.w.isEmpty()) {
            return r0z;
        }
        stopDispatchingItemsChanged();
        Iterator r3_Iterator = this.w.iterator();
        boolean r2z = false;
        while (r3_Iterator.hasNext()) {
            WeakReference r0_WeakReference = (WeakReference) r3_Iterator.next();
            MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
            if (r1_MenuPresenter == null) {
                this.w.remove(r0_WeakReference);
                r0z = r2z;
            } else {
                r0z = r1_MenuPresenter.expandItemActionView(this, r5_MenuItemImpl);
                if (r0z) {
                    break;
                }
            }
            r2z = r0z;
        }
        r0z = r2z;
        startDispatchingItemsChanged();
        if (r0z) {
            this.x = r5_MenuItemImpl;
        }
        return r0z;
    }

    public int findGroupIndex(int r2i) {
        return findGroupIndex(r2i, 0);
    }

    public int findGroupIndex(int r4i, int r5i) {
        int r2i = size();
        if (r5i < 0) {
            r5i = 0;
        }
        int r1i = r5i;
        while (r1i < r2i) {
            if (((MenuItemImpl) this.j.get(r1i)).getGroupId() == r4i) {
                return r1i;
            }
            r1i++;
        }
        return -1;
    }

    public MenuItem findItem(int r5i) {
        int r2i = size();
        int r1i = 0;
        while (r1i < r2i) {
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r1i);
            if (r0_MenuItemImpl.getItemId() == r5i) {
                return r0_MenuItemImpl;
            }
            if (r0_MenuItemImpl.hasSubMenu()) {
                MenuItem r0_MenuItem = r0_MenuItemImpl.getSubMenu().findItem(r5i);
                if (r0_MenuItem != null) {
                    return r0_MenuItem;
                }
                r1i++;
            }
            r1i++;
        }
        return null;
    }

    public int findItemIndex(int r4i) {
        int r2i = size();
        int r1i = 0;
        while (r1i < r2i) {
            if (((MenuItemImpl) this.j.get(r1i)).getItemId() == r4i) {
                return r1i;
            }
            r1i++;
        }
        return -1;
    }

    public void flagActionItems() {
        if (this.o) {
            Iterator r4_Iterator = this.w.iterator();
            int r2i = 0;
            while (r4_Iterator.hasNext()) {
                int r0i;
                WeakReference r0_WeakReference = (WeakReference) r4_Iterator.next();
                MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
                if (r1_MenuPresenter == null) {
                    this.w.remove(r0_WeakReference);
                    r0i = r2i;
                } else {
                    r0i = r1_MenuPresenter.flagActionItems() | r2i;
                }
                r2i = r0i;
            }
            if (r2i != 0) {
                this.m.clear();
                this.n.clear();
                ArrayList r2_ArrayList = b();
                int r4i = r2_ArrayList.size();
                int r1i = 0;
                while (r1i < r4i) {
                    MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) r2_ArrayList.get(r1i);
                    if (r0_MenuItemImpl.isActionButton()) {
                        this.m.add(r0_MenuItemImpl);
                    } else {
                        this.n.add(r0_MenuItemImpl);
                    }
                    r1i++;
                }
            } else {
                this.m.clear();
                this.n.clear();
                this.n.addAll(b());
            }
            this.o = false;
        }
    }

    protected String getActionViewStatesKey() {
        return "android:menu:actionviewstates";
    }

    public Context getContext() {
        return this.e;
    }

    public MenuItemImpl getExpandedItem() {
        return this.x;
    }

    public Drawable getHeaderIcon() {
        return this.b;
    }

    public CharSequence getHeaderTitle() {
        return this.a;
    }

    public View getHeaderView() {
        return this.c;
    }

    public MenuItem getItem(int r2i) {
        return (MenuItem) this.j.get(r2i);
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public boolean hasVisibleItems() {
        int r3i = size();
        int r2i = 0;
        while (r2i < r3i) {
            if (((MenuItemImpl) this.j.get(r2i)).isVisible()) {
                return true;
            }
            r2i++;
        }
        return false;
    }

    boolean isQwertyMode() {
        return this.g;
    }

    public boolean isShortcutKey(int r2i, KeyEvent r3_KeyEvent) {
        return a(r2i, r3_KeyEvent) != null;
    }

    public boolean isShortcutsVisible() {
        return this.h;
    }

    public boolean performIdentifierAction(int r2i, int r3i) {
        return performItemAction(findItem(r2i), r3i);
    }

    public boolean performItemAction(MenuItem r7_MenuItem, int r8i) {
        boolean r0z = false;
        MenuItemImpl r7_MenuItemImpl = (MenuItemImpl) r7_MenuItem;
        if (r7_MenuItemImpl == null || (!r7_MenuItemImpl.isEnabled())) {
            return false;
        }
        int r1i;
        boolean r3z = r7_MenuItemImpl.invoke();
        ActionProvider r4_ActionProvider = r7_MenuItemImpl.getSupportActionProvider();
        r1i = (r4_ActionProvider == null || (!r4_ActionProvider.hasSubMenu())) ? 0 : 1;
        if (r7_MenuItemImpl.hasCollapsibleActionView()) {
            r0z = r7_MenuItemImpl.expandActionView() | r3z;
            if (!r0z) {
                return r0z;
            }
            a(true);
            return r0z;
        } else if (r7_MenuItemImpl.hasSubMenu() || r1i != 0) {
            a(r0z);
            if (!r7_MenuItemImpl.hasSubMenu()) {
                r7_MenuItemImpl.a(new SubMenuBuilder(getContext(), this, r7_MenuItemImpl));
            }
            SubMenuBuilder r0_SubMenuBuilder = (SubMenuBuilder) r7_MenuItemImpl.getSubMenu();
            if (r1i != 0) {
                r4_ActionProvider.onPrepareSubMenu(r0_SubMenuBuilder);
            }
            r0z = a(r0_SubMenuBuilder) | r3z;
            if (r0z) {
                return r0z;
            }
            a(true);
            return r0z;
        } else {
            if ((r8i & 1) == 0) {
                a(true);
            }
            return r3z;
        }
    }

    public boolean performShortcut(int r3i, KeyEvent r4_KeyEvent, int r5i) {
        MenuItem r1_MenuItem = a(r3i, r4_KeyEvent);
        boolean r0z = false;
        if (r1_MenuItem != null) {
            r0z = performItemAction(r1_MenuItem, r5i);
        }
        if ((r5i & 2) != 0) {
            a(true);
        }
        return r0z;
    }

    public void removeGroup(int r6i) {
        int r3i = findGroupIndex(r6i);
        if (r3i >= 0) {
            int r4i = this.j.size() - r3i;
            int r0i = 0;
            while (true) {
                int r2i = r0i + 1;
                if (r0i >= r4i || ((MenuItemImpl) this.j.get(r3i)).getGroupId() != r6i) {
                    b(true);
                } else {
                    a(r3i, false);
                    r0i = r2i;
                }
            }
        }
    }

    public void removeItem(int r3i) {
        a(findItemIndex(r3i), true);
    }

    public void removeItemAt(int r2i) {
        a(r2i, true);
    }

    public void removeMenuPresenter(MenuPresenter r4_MenuPresenter) {
        Iterator r2_Iterator = this.w.iterator();
        while (r2_Iterator.hasNext()) {
            WeakReference r0_WeakReference = (WeakReference) r2_Iterator.next();
            MenuPresenter r1_MenuPresenter = (MenuPresenter) r0_WeakReference.get();
            if (r1_MenuPresenter == null || r1_MenuPresenter == r4_MenuPresenter) {
                this.w.remove(r0_WeakReference);
            }
        }
    }

    public void restoreActionViewStates(Bundle r8_Bundle) {
        if (r8_Bundle == null) {
        } else {
            MenuItem r0_MenuItem;
            SparseArray r2_SparseArray = r8_Bundle.getSparseParcelableArray(getActionViewStatesKey());
            int r3i = size();
            int r1i = 0;
            while (r1i < r3i) {
                r0_MenuItem = getItem(r1i);
                View r4_View = MenuItemCompat.getActionView(r0_MenuItem);
                if (r4_View == null || r4_View.getId() == -1) {
                } else {
                    r4_View.restoreHierarchyState(r2_SparseArray);
                }
                if (r0_MenuItem.hasSubMenu()) {
                    ((SubMenuBuilder) r0_MenuItem.getSubMenu()).restoreActionViewStates(r8_Bundle);
                }
                r1i++;
            }
            int r0i = r8_Bundle.getInt("android:menu:expandedactionview");
            if (r0i > 0) {
                r0_MenuItem = findItem(r0i);
                if (r0_MenuItem != null) {
                    MenuItemCompat.expandActionView(r0_MenuItem);
                }
            }
        }
    }

    public void restorePresenterStates(Bundle r1_Bundle) {
        b(r1_Bundle);
    }

    public void saveActionViewStates(Bundle r8_Bundle) {
        int r3i = size();
        int r2i = 0;
        SparseArray r0_SparseArray = null;
        while (r2i < r3i) {
            MenuItem r4_MenuItem = getItem(r2i);
            View r1_View = MenuItemCompat.getActionView(r4_MenuItem);
            SparseArray r1_SparseArray;
            if (r1_View == null || r1_View.getId() == -1) {
                r1_SparseArray = r0_SparseArray;
                if (!r4_MenuItem.hasSubMenu()) {
                    ((SubMenuBuilder) r4_MenuItem.getSubMenu()).saveActionViewStates(r8_Bundle);
                }
                r2i++;
                r0_SparseArray = r1_SparseArray;
            } else {
                if (r0_SparseArray == null) {
                    r0_SparseArray = new SparseArray();
                }
                r1_View.saveHierarchyState(r0_SparseArray);
                if (MenuItemCompat.isActionViewExpanded(r4_MenuItem)) {
                    r8_Bundle.putInt("android:menu:expandedactionview", r4_MenuItem.getItemId());
                }
                r1_SparseArray = r0_SparseArray;
                if (r4_MenuItem.hasSubMenu()) {
                    r2i++;
                    r0_SparseArray = r1_SparseArray;
                } else {
                    ((SubMenuBuilder) r4_MenuItem.getSubMenu()).saveActionViewStates(r8_Bundle);
                    r2i++;
                    r0_SparseArray = r1_SparseArray;
                }
            }
        }
        if (r0_SparseArray != null) {
            r8_Bundle.putSparseParcelableArray(getActionViewStatesKey(), r0_SparseArray);
        }
    }

    public void savePresenterStates(Bundle r1_Bundle) {
        a(r1_Bundle);
    }

    public void setCallback(Callback r1_Callback) {
        this.i = r1_Callback;
    }

    public void setCurrentMenuInfo(ContextMenuInfo r1_ContextMenuInfo) {
        this.q = r1_ContextMenuInfo;
    }

    public MenuBuilder setDefaultShowAsAction(int r1i) {
        this.p = r1i;
        return this;
    }

    public void setGroupCheckable(int r5i, boolean r6z, boolean r7z) {
        int r2i = this.j.size();
        int r1i = 0;
        while (r1i < r2i) {
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r1i);
            if (r0_MenuItemImpl.getGroupId() == r5i) {
                r0_MenuItemImpl.setExclusiveCheckable(r7z);
                r0_MenuItemImpl.setCheckable(r6z);
            }
            r1i++;
        }
    }

    public void setGroupEnabled(int r5i, boolean r6z) {
        int r2i = this.j.size();
        int r1i = 0;
        while (r1i < r2i) {
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r1i);
            if (r0_MenuItemImpl.getGroupId() == r5i) {
                r0_MenuItemImpl.setEnabled(r6z);
            }
            r1i++;
        }
    }

    public void setGroupVisible(int r7i, boolean r8z) {
        int r4i = this.j.size();
        int r3i = 0;
        int r2i = 0;
        while (r3i < r4i) {
            int r0i;
            MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) this.j.get(r3i);
            r0i = (r0_MenuItemImpl.getGroupId() == r7i && r0_MenuItemImpl.b(r8z)) ? 1 : r2i;
            r3i++;
            r2i = r0i;
        }
        if (r2i != 0) {
            b(true);
        }
    }

    public void setQwertyMode(boolean r2z) {
        this.g = r2z;
        b(false);
    }

    public void setShortcutsVisible(boolean r2z) {
        if (this.h == r2z) {
        } else {
            d(r2z);
            b(false);
        }
    }

    public int size() {
        return this.j.size();
    }

    public void startDispatchingItemsChanged() {
        this.r = false;
        if (this.s) {
            this.s = false;
            b(true);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.r) {
            this.r = true;
            this.s = false;
        }
    }
}
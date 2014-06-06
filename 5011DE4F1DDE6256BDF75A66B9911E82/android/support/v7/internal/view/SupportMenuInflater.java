package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.cordova.Globalization;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class SupportMenuInflater extends MenuInflater {
    private static final Class<?>[] a;
    private static final Class<?>[] b;
    private final Object[] c;
    private final Object[] d;
    private Context e;
    private Object f;

    private static class a implements OnMenuItemClickListener {
        private static final Class<?>[] a;
        private Object b;
        private Method c;

        static {
            Class[] r0_ClassA = new Class[1];
            r0_ClassA[0] = MenuItem.class;
            a = r0_ClassA;
        }

        public a(Object r6_Object, String r7_String) {
            this.b = r6_Object;
            Class r1_Class = r6_Object.getClass();
            try {
                this.c = r1_Class.getMethod(r7_String, a);
            } catch (Exception e) {
                InflateException r2_InflateException = new InflateException("Couldn't resolve menu item onClick handler " + r7_String + " in class " + r1_Class.getName());
                r2_InflateException.initCause(e);
                throw r2_InflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem r6_MenuItem) {
            boolean r0z = true;
            try {
                if (this.c.getReturnType() == Boolean.TYPE) {
                    Method r0_Method = this.c;
                    Object r1_Object = this.b;
                    Object[] r2_ObjectA = new Object[1];
                    r2_ObjectA[0] = r6_MenuItem;
                    r0z = ((Boolean) r0_Method.invoke(r1_Object, r2_ObjectA)).booleanValue();
                } else {
                    Method r1_Method = this.c;
                    Object r2_Object = this.b;
                    Object[] r3_ObjectA = new Object[1];
                    r3_ObjectA[0] = r6_MenuItem;
                    r1_Method.invoke(r2_Object, r3_ObjectA);
                }
                return r0z;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class b {
        private Menu b;
        private int c;
        private int d;
        private int e;
        private int f;
        private boolean g;
        private boolean h;
        private boolean i;
        private int j;
        private int k;
        private CharSequence l;
        private CharSequence m;
        private int n;
        private char o;
        private char p;
        private int q;
        private boolean r;
        private boolean s;
        private boolean t;
        private int u;
        private int v;
        private String w;
        private String x;
        private String y;
        private ActionProvider z;

        public b(Menu r2_Menu) {
            this.b = r2_Menu;
            resetGroup();
        }

        private char a(String r2_String) {
            char r0c = '\u0000';
            return r2_String == null ? '\u0000' : r2_String.charAt(r0c);
        }

        private <T> T a(String r5_String, Class<?>[] r6_Class_A, Object[] r7_ObjectA) {
            try {
                return SupportMenuInflater.this.e.getClassLoader().loadClass(r5_String).getConstructor(r6_Class_A).newInstance(r7_ObjectA);
            } catch (Exception e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + r5_String, e);
                return null;
            }
        }

        private void a(MenuItem r6_MenuItem) {
            boolean r1z = true;
            r6_MenuItem.setChecked(this.r).setVisible(this.s).setEnabled(this.t).setCheckable(this.q >= 1).setTitleCondensed(this.m).setIcon(this.n).setAlphabeticShortcut(this.o).setNumericShortcut(this.p);
            if (this.u >= 0) {
                MenuItemCompat.setShowAsAction(r6_MenuItem, this.u);
            }
            if (this.y != null) {
                if (SupportMenuInflater.this.e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                } else {
                    r6_MenuItem.setOnMenuItemClickListener(new a(SupportMenuInflater.this.f, this.y));
                }
            }
            if (r6_MenuItem instanceof MenuItemImpl) {
                MenuItemImpl r0_MenuItemImpl = (MenuItemImpl) r6_MenuItem;
            }
            if (this.q >= 2) {
                if (r6_MenuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) r6_MenuItem).setExclusiveCheckable(true);
                } else if (r6_MenuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) r6_MenuItem).setExclusiveCheckable(true);
                }
            }
            if (this.w != null) {
                MenuItemCompat.setActionView(r6_MenuItem, (View) a(this.w, a, SupportMenuInflater.this.c));
            } else {
                r1z = false;
            }
            if (this.v > 0) {
                if (r1z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    MenuItemCompat.setActionView(r6_MenuItem, this.v);
                }
            }
            if (this.z != null) {
                MenuItemCompat.setActionProvider(r6_MenuItem, this.z);
            }
        }

        public void addItem() {
            this.i = true;
            a(this.b.add(this.c, this.j, this.k, this.l));
        }

        public SubMenu addSubMenuItem() {
            this.i = true;
            SubMenu r0_SubMenu = this.b.addSubMenu(this.c, this.j, this.k, this.l);
            a(r0_SubMenu.getItem());
            return r0_SubMenu;
        }

        public boolean hasAddedItem() {
            return this.i;
        }

        public void readGroup(AttributeSet r5_AttributeSet) {
            TypedArray r0_TypedArray = SupportMenuInflater.this.e.obtainStyledAttributes(r5_AttributeSet, R.styleable.MenuGroup);
            this.c = r0_TypedArray.getResourceId(1, 0);
            this.d = r0_TypedArray.getInt(XListViewFooter.STATE_NOMORE, 0);
            this.e = r0_TypedArray.getInt(XListViewFooter.STATE_NODATA, 0);
            this.f = r0_TypedArray.getInt(ShareUtils.SHARE_SMS, 0);
            this.g = r0_TypedArray.getBoolean(XListViewHeader.STATE_REFRESHING, true);
            this.h = r0_TypedArray.getBoolean(0, true);
            r0_TypedArray.recycle();
        }

        public void readItem(AttributeSet r8_AttributeSet) {
            int r1i = 1;
            TypedArray r3_TypedArray = SupportMenuInflater.this.e.obtainStyledAttributes(r8_AttributeSet, R.styleable.MenuItem);
            this.j = r3_TypedArray.getResourceId(XListViewHeader.STATE_REFRESHING, 0);
            this.k = (r3_TypedArray.getInt(ShareUtils.SHARE_SMS, this.d) & -65536) | (r3_TypedArray.getInt(ShareUtils.SHARE_COPY, this.e) & 65535);
            this.l = r3_TypedArray.getText(ShareUtils.SHARE_COLLECT);
            this.m = r3_TypedArray.getText(Base64.DONT_BREAK_LINES);
            this.n = r3_TypedArray.getResourceId(0, 0);
            this.o = a(r3_TypedArray.getString(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY));
            this.p = a(r3_TypedArray.getString(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO));
            if (r3_TypedArray.hasValue(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE)) {
                this.q = r3_TypedArray.getBoolean(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE, false) ? 1 : 0;
            } else {
                this.q = this.f;
            }
            this.r = r3_TypedArray.getBoolean(XListViewFooter.STATE_NOMORE, false);
            this.s = r3_TypedArray.getBoolean(XListViewFooter.STATE_NODATA, this.g);
            this.t = r3_TypedArray.getBoolean(1, this.h);
            this.u = r3_TypedArray.getInt(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, -1);
            this.y = r3_TypedArray.getString(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
            this.v = r3_TypedArray.getResourceId(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, 0);
            this.w = r3_TypedArray.getString(NearbySelectView.TIME_15MIN);
            this.x = r3_TypedArray.getString(Base64.URL_SAFE);
            if (this.x != null) {
                if (r1i != 0 && this.v == 0 && this.w == null) {
                    this.z = (ActionProvider) a(this.x, b, SupportMenuInflater.this.d);
                } else {
                    if (r1i == 0) {
                        Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                    }
                    this.z = null;
                }
                r3_TypedArray.recycle();
                this.i = false;
            } else {
                r1i = 0;
                if (r1i != 0 || this.v == 0 || this.w == null) {
                    if (r1i == 0) {
                        this.z = null;
                    } else {
                        Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                        this.z = null;
                    }
                } else {
                    this.z = (ActionProvider) a(this.x, b, SupportMenuInflater.this.d);
                }
                r3_TypedArray.recycle();
                this.i = false;
            }
        }

        public void resetGroup() {
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = true;
            this.h = true;
        }
    }

    static {
        Class[] r0_ClassA = new Class[1];
        r0_ClassA[0] = Context.class;
        a = r0_ClassA;
        b = a;
    }

    public SupportMenuInflater(Context r3_Context) {
        super(r3_Context);
        this.e = r3_Context;
        this.f = r3_Context;
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = r3_Context;
        this.c = r0_ObjectA;
        this.d = this.c;
    }

    private void a(XmlPullParser r11_XmlPullParser, AttributeSet r12_AttributeSet, Menu r13_Menu) throws XmlPullParserException, IOException {
        b r7_b = new b(r13_Menu);
        int r0i = r11_XmlPullParser.getEventType();
        while (r0i != 2) {
            r0i = r11_XmlPullParser.next();
            if (r0i == 1) {
                break;
            }
        }
        String r0_String = r11_XmlPullParser.getName();
        if (r0_String.equals("menu")) {
            r0i = r11_XmlPullParser.next();
            String r2_String = null;
            int r5i = 0;
            int r3i = r0i;
            r0i = 0;
            while (r0i == 0) {
                String r3_String;
                switch (r3i) {
                    case XListViewHeader.STATE_READY:
                        throw new RuntimeException("Unexpected end of document");
                    case XListViewHeader.STATE_REFRESHING:
                        if (r5i != 0) {
                            r3i = r5i;
                            r3i = r11_XmlPullParser.next();
                            r5i = r3i;
                        } else {
                            r3_String = r11_XmlPullParser.getName();
                            if (r3_String.equals("group")) {
                                r7_b.readGroup(r12_AttributeSet);
                                r3i = r5i;
                                r3i = r11_XmlPullParser.next();
                                r5i = r3i;
                            } else if (r3_String.equals(Globalization.ITEM)) {
                                r7_b.readItem(r12_AttributeSet);
                                r3i = r5i;
                                r3i = r11_XmlPullParser.next();
                                r5i = r3i;
                            } else if (r3_String.equals("menu")) {
                                a(r11_XmlPullParser, r12_AttributeSet, r7_b.addSubMenuItem());
                                r3i = r5i;
                                r3i = r11_XmlPullParser.next();
                                r5i = r3i;
                            } else {
                                r2_String = r3_String;
                                r3i = 1;
                                r3i = r11_XmlPullParser.next();
                                r5i = r3i;
                            }
                        }
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        r3_String = r11_XmlPullParser.getName();
                        if (r5i == 0 || (!r3_String.equals(r2_String))) {
                            if (r3_String.equals("group")) {
                                r7_b.resetGroup();
                                r3i = r5i;
                            } else if (r3_String.equals(Globalization.ITEM)) {
                                if (!r7_b.hasAddedItem()) {
                                    if (r7_b.z == null || (!r7_b.z.hasSubMenu())) {
                                        r7_b.addItem();
                                        r3i = r5i;
                                    } else {
                                        r7_b.addSubMenuItem();
                                        r3i = r5i;
                                    }
                                }
                            } else if (r3_String.equals("menu")) {
                                r0i = 1;
                                r3i = r5i;
                            }
                        } else {
                            r2_String = null;
                            r3i = 0;
                        }
                        r3i = r11_XmlPullParser.next();
                        r5i = r3i;
                        break;
                }
                r3i = r5i;
                r3i = r11_XmlPullParser.next();
                r5i = r3i;
            }
        } else {
            throw new RuntimeException("Expecting menu, got " + r0_String);
        }
    }

    public void inflate(int r5i, Menu r6_Menu) {
        if (r6_Menu instanceof SupportMenu) {
            try {
                Object r1_Object = this.e.getResources().getLayout(r5i);
                a(r1_Object, Xml.asAttributeSet(r1_Object), r6_Menu);
                if (r1_Object != null) {
                    r1_Object.close();
                }
            } catch (XmlPullParserException e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (IOException e_2) {
                throw new InflateException("Error inflating menu XML", e_2);
            } catch (Throwable th) {
                if (0 != 0) {
                    null.close();
                }
            }
        } else {
            super.inflate(r5i, r6_Menu);
        }
    }
}
package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class SearchViewCompat {
    private static final c a;

    public static abstract class OnCloseListenerCompat {
        final Object a;

        public OnCloseListenerCompat() {
            this.a = a.newOnCloseListener(this);
        }

        public boolean onClose() {
            return false;
        }
    }

    public static abstract class OnQueryTextListenerCompat {
        final Object a;

        public OnQueryTextListenerCompat() {
            this.a = a.newOnQueryTextListener(this);
        }

        public boolean onQueryTextChange(String r2_String) {
            return false;
        }

        public boolean onQueryTextSubmit(String r2_String) {
            return false;
        }
    }

    static interface c {
        public CharSequence getQuery(View r1_View);

        public boolean isIconified(View r1_View);

        public boolean isQueryRefinementEnabled(View r1_View);

        public boolean isSubmitButtonEnabled(View r1_View);

        public Object newOnCloseListener(android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat r1_android_support_v4_widget_SearchViewCompat_OnCloseListenerCompat);

        public Object newOnQueryTextListener(android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat r1_android_support_v4_widget_SearchViewCompat_OnQueryTextListenerCompat);

        public View newSearchView(Context r1_Context);

        public void setIconified(View r1_View, boolean r2z);

        public void setImeOptions(View r1_View, int r2i);

        public void setInputType(View r1_View, int r2i);

        public void setMaxWidth(View r1_View, int r2i);

        public void setOnCloseListener(Object r1_Object, Object r2_Object);

        public void setOnQueryTextListener(Object r1_Object, Object r2_Object);

        public void setQuery(View r1_View, CharSequence r2_CharSequence, boolean r3z);

        public void setQueryHint(View r1_View, CharSequence r2_CharSequence);

        public void setQueryRefinementEnabled(View r1_View, boolean r2z);

        public void setSearchableInfo(View r1_View, ComponentName r2_ComponentName);

        public void setSubmitButtonEnabled(View r1_View, boolean r2z);
    }

    static class d implements c {
        d() {
        }

        public CharSequence getQuery(View r2_View) {
            return null;
        }

        public boolean isIconified(View r2_View) {
            return true;
        }

        public boolean isQueryRefinementEnabled(View r2_View) {
            return false;
        }

        public boolean isSubmitButtonEnabled(View r2_View) {
            return false;
        }

        public Object newOnCloseListener(android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat r2_android_support_v4_widget_SearchViewCompat_OnCloseListenerCompat) {
            return null;
        }

        public Object newOnQueryTextListener(android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat r2_android_support_v4_widget_SearchViewCompat_OnQueryTextListenerCompat) {
            return null;
        }

        public View newSearchView(Context r2_Context) {
            return null;
        }

        public void setIconified(View r1_View, boolean r2z) {
        }

        public void setImeOptions(View r1_View, int r2i) {
        }

        public void setInputType(View r1_View, int r2i) {
        }

        public void setMaxWidth(View r1_View, int r2i) {
        }

        public void setOnCloseListener(Object r1_Object, Object r2_Object) {
        }

        public void setOnQueryTextListener(Object r1_Object, Object r2_Object) {
        }

        public void setQuery(View r1_View, CharSequence r2_CharSequence, boolean r3z) {
        }

        public void setQueryHint(View r1_View, CharSequence r2_CharSequence) {
        }

        public void setQueryRefinementEnabled(View r1_View, boolean r2z) {
        }

        public void setSearchableInfo(View r1_View, ComponentName r2_ComponentName) {
        }

        public void setSubmitButtonEnabled(View r1_View, boolean r2z) {
        }
    }

    static class a extends d {
        a() {
        }

        public CharSequence getQuery(View r2_View) {
            return m.getQuery(r2_View);
        }

        public boolean isIconified(View r2_View) {
            return m.isIconified(r2_View);
        }

        public boolean isQueryRefinementEnabled(View r2_View) {
            return m.isQueryRefinementEnabled(r2_View);
        }

        public boolean isSubmitButtonEnabled(View r2_View) {
            return m.isSubmitButtonEnabled(r2_View);
        }

        public Object newOnCloseListener(android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat r2_android_support_v4_widget_SearchViewCompat_OnCloseListenerCompat) {
            return m.newOnCloseListener(new l(this, r2_android_support_v4_widget_SearchViewCompat_OnCloseListenerCompat));
        }

        public Object newOnQueryTextListener(android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat r2_android_support_v4_widget_SearchViewCompat_OnQueryTextListenerCompat) {
            return m.newOnQueryTextListener(new k(this, r2_android_support_v4_widget_SearchViewCompat_OnQueryTextListenerCompat));
        }

        public View newSearchView(Context r2_Context) {
            return m.newSearchView(r2_Context);
        }

        public void setIconified(View r1_View, boolean r2z) {
            m.setIconified(r1_View, r2z);
        }

        public void setMaxWidth(View r1_View, int r2i) {
            m.setMaxWidth(r1_View, r2i);
        }

        public void setOnCloseListener(Object r1_Object, Object r2_Object) {
            m.setOnCloseListener(r1_Object, r2_Object);
        }

        public void setOnQueryTextListener(Object r1_Object, Object r2_Object) {
            m.setOnQueryTextListener(r1_Object, r2_Object);
        }

        public void setQuery(View r1_View, CharSequence r2_CharSequence, boolean r3z) {
            m.setQuery(r1_View, r2_CharSequence, r3z);
        }

        public void setQueryHint(View r1_View, CharSequence r2_CharSequence) {
            m.setQueryHint(r1_View, r2_CharSequence);
        }

        public void setQueryRefinementEnabled(View r1_View, boolean r2z) {
            m.setQueryRefinementEnabled(r1_View, r2z);
        }

        public void setSearchableInfo(View r1_View, ComponentName r2_ComponentName) {
            m.setSearchableInfo(r1_View, r2_ComponentName);
        }

        public void setSubmitButtonEnabled(View r1_View, boolean r2z) {
            m.setSubmitButtonEnabled(r1_View, r2z);
        }
    }

    static class b extends a {
        b() {
        }

        public View newSearchView(Context r2_Context) {
            return SearchViewCompatIcs.newSearchView(r2_Context);
        }

        public void setImeOptions(View r1_View, int r2i) {
            SearchViewCompatIcs.setImeOptions(r1_View, r2i);
        }

        public void setInputType(View r1_View, int r2i) {
            SearchViewCompatIcs.setInputType(r1_View, r2i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new b();
        } else if (VERSION.SDK_INT >= 11) {
            a = new a();
        } else {
            a = new d();
        }
    }

    public static CharSequence getQuery(View r1_View) {
        return a.getQuery(r1_View);
    }

    public static boolean isIconified(View r1_View) {
        return a.isIconified(r1_View);
    }

    public static boolean isQueryRefinementEnabled(View r1_View) {
        return a.isQueryRefinementEnabled(r1_View);
    }

    public static boolean isSubmitButtonEnabled(View r1_View) {
        return a.isSubmitButtonEnabled(r1_View);
    }

    public static View newSearchView(Context r1_Context) {
        return a.newSearchView(r1_Context);
    }

    public static void setIconified(View r1_View, boolean r2z) {
        a.setIconified(r1_View, r2z);
    }

    public static void setImeOptions(View r1_View, int r2i) {
        a.setImeOptions(r1_View, r2i);
    }

    public static void setInputType(View r1_View, int r2i) {
        a.setInputType(r1_View, r2i);
    }

    public static void setMaxWidth(View r1_View, int r2i) {
        a.setMaxWidth(r1_View, r2i);
    }

    public static void setOnCloseListener(View r2_View, OnCloseListenerCompat r3_OnCloseListenerCompat) {
        a.setOnCloseListener(r2_View, r3_OnCloseListenerCompat.a);
    }

    public static void setOnQueryTextListener(View r2_View, OnQueryTextListenerCompat r3_OnQueryTextListenerCompat) {
        a.setOnQueryTextListener(r2_View, r3_OnQueryTextListenerCompat.a);
    }

    public static void setQuery(View r1_View, CharSequence r2_CharSequence, boolean r3z) {
        a.setQuery(r1_View, r2_CharSequence, r3z);
    }

    public static void setQueryHint(View r1_View, CharSequence r2_CharSequence) {
        a.setQueryHint(r1_View, r2_CharSequence);
    }

    public static void setQueryRefinementEnabled(View r1_View, boolean r2z) {
        a.setQueryRefinementEnabled(r1_View, r2z);
    }

    public static void setSearchableInfo(View r1_View, ComponentName r2_ComponentName) {
        a.setSearchableInfo(r1_View, r2_ComponentName);
    }

    public static void setSubmitButtonEnabled(View r1_View, boolean r2z) {
        a.setSubmitButtonEnabled(r1_View, r2z);
    }
}
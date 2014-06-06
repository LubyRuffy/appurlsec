package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

// compiled from: SearchViewCompatHoneycomb.java
class m {

    // compiled from: SearchViewCompatHoneycomb.java
    static interface b {
        public boolean onQueryTextChange(String r1_String);

        public boolean onQueryTextSubmit(String r1_String);
    }

    // compiled from: SearchViewCompatHoneycomb.java
    static interface a {
        public boolean onClose();
    }

    public static CharSequence getQuery(View r1_View) {
        return ((SearchView) r1_View).getQuery();
    }

    public static boolean isIconified(View r1_View) {
        return ((SearchView) r1_View).isIconified();
    }

    public static boolean isQueryRefinementEnabled(View r1_View) {
        return ((SearchView) r1_View).isQueryRefinementEnabled();
    }

    public static boolean isSubmitButtonEnabled(View r1_View) {
        return ((SearchView) r1_View).isSubmitButtonEnabled();
    }

    public static Object newOnCloseListener(a r1_a) {
        return new o(r1_a);
    }

    public static Object newOnQueryTextListener(b r1_b) {
        return new n(r1_b);
    }

    public static View newSearchView(Context r1_Context) {
        return new SearchView(r1_Context);
    }

    public static void setIconified(View r0_View, boolean r1z) {
        ((SearchView) r0_View).setIconified(r1z);
    }

    public static void setMaxWidth(View r0_View, int r1i) {
        ((SearchView) r0_View).setMaxWidth(r1i);
    }

    public static void setOnCloseListener(Object r0_Object, Object r1_Object) {
        ((SearchView) r0_Object).setOnCloseListener((OnCloseListener) r1_Object);
    }

    public static void setOnQueryTextListener(Object r0_Object, Object r1_Object) {
        ((SearchView) r0_Object).setOnQueryTextListener((OnQueryTextListener) r1_Object);
    }

    public static void setQuery(View r0_View, CharSequence r1_CharSequence, boolean r2z) {
        ((SearchView) r0_View).setQuery(r1_CharSequence, r2z);
    }

    public static void setQueryHint(View r0_View, CharSequence r1_CharSequence) {
        ((SearchView) r0_View).setQueryHint(r1_CharSequence);
    }

    public static void setQueryRefinementEnabled(View r0_View, boolean r1z) {
        ((SearchView) r0_View).setQueryRefinementEnabled(r1z);
    }

    public static void setSearchableInfo(View r2_View, ComponentName r3_ComponentName) {
        SearchView r2_SearchView = (SearchView) r2_View;
        r2_SearchView.setSearchableInfo(((SearchManager) r2_SearchView.getContext().getSystemService("search")).getSearchableInfo(r3_ComponentName));
    }

    public static void setSubmitButtonEnabled(View r0_View, boolean r1z) {
        ((SearchView) r0_View).setSubmitButtonEnabled(r1z);
    }
}
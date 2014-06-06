package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import com.tencent.mm.sdk.contact.RContactStorage;

class SearchViewCompatIcs {

    public static class MySearchView extends SearchView {
        public MySearchView(Context r1_Context) {
            super(r1_Context);
        }

        public void onActionViewCollapsed() {
            setQuery(RContactStorage.PRIMARY_KEY, false);
            super.onActionViewCollapsed();
        }
    }

    public static View newSearchView(Context r1_Context) {
        return new MySearchView(r1_Context);
    }

    public static void setImeOptions(View r0_View, int r1i) {
        ((SearchView) r0_View).setImeOptions(r1i);
    }

    public static void setInputType(View r0_View, int r1i) {
        ((SearchView) r0_View).setInputType(r1i);
    }
}
package qsbk.app.activity.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.WrapperListAdapter;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;

// compiled from: GroupChildBaseListViewActivity.java
class y implements OnItemLongClickListener {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    y(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public boolean onItemLongClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
        if (r6i == 0 || !(this.a.s.getAdapter().getItem(r6i) instanceof Article)) {
            return true;
        }
        if (!UIHelper.isNightTheme()) {
            r5_View.findViewById(R.id.layerMask).setVisibility(0);
        }
        this.a.z = r5_View;
        this.a.y = (Article) this.a.s.getAdapter().getItem(r6i);
        if (this.a.s.getAdapter() instanceof ArticleAdapter) {
            this.a.K = ((ViewHolder) r5_View.getTag()).collection_icon;
        } else {
            if (this.a.s.getAdapter() instanceof WrapperListAdapter && ((WrapperListAdapter) this.a.s.getAdapter()).getWrappedAdapter() instanceof ArticleAdapter) {
                this.a.K = ((ViewHolder) r5_View.getTag()).collection_icon;
            } else {
                this.a.n();
            }
            return true;
        }
        this.a.n();
        return true;
    }
}
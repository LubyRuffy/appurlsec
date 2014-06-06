package qsbk.app.activity.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.WrapperListAdapter;
import qsbk.app.adapter.OneProfileArticleAdapter;
import qsbk.app.adapter.OneProfileArticleAdapter.ViewHolder;
import qsbk.app.model.Article;

// compiled from: MysBaseActivity.java
class al implements OnItemLongClickListener {
    final /* synthetic */ MysBaseActivity a;

    al(MysBaseActivity r1_MysBaseActivity) {
        this.a = r1_MysBaseActivity;
    }

    public boolean onItemLongClick(AdapterView<?> r3_AdapterView_, View r4_View, int r5i, long r6j) {
        this.a.z = (Article) this.a.s.getAdapter().getItem(r5i);
        if (this.a.z == null) {
            return false;
        }
        if (this.a.s.getAdapter() instanceof OneProfileArticleAdapter) {
            this.a.setCollectionIcon(((ViewHolder) r4_View.getTag()).collection_icon);
        } else if (this.a.s.getAdapter() instanceof WrapperListAdapter && ((WrapperListAdapter) this.a.s.getAdapter()).getWrappedAdapter() instanceof OneProfileArticleAdapter) {
            this.a.setCollectionIcon(((ViewHolder) r4_View.getTag()).collection_icon);
        } else {
            this.a.n();
        }
        this.a.n();
        return true;
    }
}
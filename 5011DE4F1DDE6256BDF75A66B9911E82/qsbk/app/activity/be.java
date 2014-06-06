package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.adapter.MyContentsAdapter;
import qsbk.app.model.Article;

// compiled from: ManageMyContentsActivity.java
class be implements OnItemLongClickListener {
    final /* synthetic */ ManageMyContentsActivity a;

    be(ManageMyContentsActivity r1_ManageMyContentsActivity) {
        this.a = r1_ManageMyContentsActivity;
    }

    public boolean onItemLongClick(AdapterView<?> r6_AdapterView_, View r7_View, int r8i, long r9j) {
        this.a.z = (Article) this.a.s.getAdapter().getItem(r8i);
        if (this.a.z == null) {
            return true;
        }
        if (this.a.z.state.equals(MyContentsAdapter.PUBLISH)) {
            new Builder(this.a).setTitle("\u6e29\u99a8\u63d0\u793a").setMessage("\u6b64\u7cd7\u4e8b\u5df2\u53d1\u8868\uff0c\u786e\u8ba4\u8981\u5220\u9664\u5417\uff1f").setPositiveButton("\u786e\u5b9a", new bg(this)).setNegativeButton("\u53d6\u6d88", new bf(this)).show();
            return true;
        } else {
            Builder r0_Builder = new Builder(this.a).setTitle("\u522a\u9664\u6b64\u6761\u7cd7\u4e8b\uff1f");
            CharSequence[] r1_CharSequenceA = new CharSequence[1];
            r1_CharSequenceA[0] = "\u7acb\u5373\u5220\u9664";
            r0_Builder.setItems(r1_CharSequenceA, new bh(this)).show();
            return true;
        }
    }
}
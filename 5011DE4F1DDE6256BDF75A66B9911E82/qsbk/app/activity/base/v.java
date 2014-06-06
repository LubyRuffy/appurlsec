package qsbk.app.activity.base;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.SingleArticle;

// compiled from: GroupChildBaseListViewActivity.java
class v implements OnItemClickListener {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    v(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void onItemClick(AdapterView<?> r5_AdapterView_, View r6_View, int r7i, long r8j) {
        this.a.H = true;
        QsbkApp.currentDataSource = this.a.r;
        QsbkApp.currentSelectItem = (int) r5_AdapterView_.getAdapter().getItemId(r7i);
        if (QsbkApp.currentSelectItem < 0 || this.a.r.size() < QsbkApp.currentSelectItem) {
        } else {
            Intent r0_Intent = new Intent(this.a.o, SingleArticle.class);
            r0_Intent.putExtra(OneProfileActivity.SOURCE, this.a.getVotePoint() + (QsbkApp.currentSelectItem + 1));
            this.a.startActivity(r0_Intent);
            this.a.setCanBack(false);
        }
    }
}
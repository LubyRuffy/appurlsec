package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;

// compiled from: ManageMyContentsActivity.java
class ba implements OnItemClickListener {
    final /* synthetic */ ManageMyContentsActivity a;

    ba(ManageMyContentsActivity r1_ManageMyContentsActivity) {
        this.a = r1_ManageMyContentsActivity;
    }

    public void onItemClick(AdapterView<?> r5_AdapterView_, View r6_View, int r7i, long r8j) {
        this.a.D = true;
        QsbkApp.currentDataSource = this.a.q;
        QsbkApp.currentSelectItem = (int) r5_AdapterView_.getAdapter().getItemId(r7i);
        if (QsbkApp.currentSelectItem < 0 || this.a.q.size() < QsbkApp.currentSelectItem) {
        } else {
            Intent r0_Intent = new Intent(this.a, SingleArticle.class);
            r0_Intent.putExtra(OneProfileActivity.SOURCE, this.a.getVotePoint() + (QsbkApp.currentSelectItem + 1));
            this.a.startActivity(r0_Intent);
        }
    }
}
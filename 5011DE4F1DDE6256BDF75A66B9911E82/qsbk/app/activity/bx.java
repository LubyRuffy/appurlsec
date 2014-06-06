package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.Article;

// compiled from: OneProfileActivity.java
class bx implements OnItemClickListener {
    final /* synthetic */ OneProfileActivity a;

    bx(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    public void onItemClick(AdapterView<?> r5_AdapterView_, View r6_View, int r7i, long r8j) {
        if (this.a.B() != 2) {
        } else if (r5_AdapterView_.getAdapter().getItem(r7i) instanceof Article) {
            QsbkApp.currentDataSource = this.a.C;
            QsbkApp.currentSelectItem = (int) r8j;
            Intent r0_Intent = new Intent(this.a, SingleArticle.class);
            r0_Intent.putExtra(OneProfileActivity.SOURCE, this.a.getVotePoint() + (QsbkApp.currentSelectItem + 1));
            this.a.startActivity(r0_Intent);
        } else {
            this.a.d("item_click");
        }
    }
}
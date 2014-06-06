package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

// compiled from: SingleArticle.java
class ct implements OnClickListener {
    final /* synthetic */ SingleArticle a;

    ct(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void onClick(View r7_View) {
        if (r7_View.getTag().equals("enable")) {
            ToastAndDialog.makeTextSingleArticle(this.a.O, r7_View, "-1", 800, -16776961);
            r7_View.getBackground().setLevel(1);
            r7_View.setTag("active");
            this.a.C.getCompoundDrawables()[0].setLevel(1);
            this.a.a(this.a.C, true);
            int r1i = this.a.X.vote_down - 1;
            if (this.a.p()) {
                ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).vote_down = r1i;
            } else {
                this.a.X.vote_down = r1i;
            }
            if (this.a.Y.startsWith("mylike")) {
                QsbkApp.currentDataSource.remove(QsbkApp.currentSelectItem);
            }
            this.a.C.setText(String.valueOf(r1i));
            if (((String) this.a.B.getTag()).equals("active")) {
                this.a.B.getBackground().setLevel(0);
                this.a.B.setTag("enable");
                this.a.a(this.a.B, false);
                this.a.B.getCompoundDrawables()[0].setLevel(0);
                r1i = this.a.X.vote_up - 1;
                this.a.B.setText(String.valueOf(r1i));
                if (this.a.p()) {
                    ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).vote_up = r1i;
                } else {
                    this.a.X.vote_up = r1i;
                }
            }
            this.a.a("dn", this.a.af, "active");
            QsbkApp.getMyLikeManager().dislike(this.a.X);
            QsbkApp.getMyLikeManager().saveToFile();
        }
    }
}
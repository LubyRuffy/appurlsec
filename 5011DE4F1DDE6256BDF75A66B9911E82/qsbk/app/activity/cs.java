package qsbk.app.activity;

import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

// compiled from: SingleArticle.java
class cs implements OnClickListener {
    final /* synthetic */ SingleArticle a;

    cs(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void onClick(View r7_View) {
        if (r7_View.getTag().equals("enable")) {
            ToastAndDialog.makeTextSingleArticle(this.a.O, r7_View, "+1", 800, SupportMenu.CATEGORY_MASK);
            r7_View.setTag("active");
            r7_View.getBackground().setLevel(1);
            this.a.a(this.a.B, true);
            this.a.B.getCompoundDrawables()[0].setLevel(1);
            int r1i = this.a.X.vote_up + 1;
            if (this.a.p()) {
                ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).vote_up = r1i;
            } else {
                this.a.X.vote_up = r1i;
            }
            if ((!this.a.Y.startsWith("mylike")) || QsbkApp.currentDataSource.contains(this.a.X)) {
                this.a.B.setText(String.valueOf(r1i));
                if (!((String) this.a.C.getTag()).equals("active")) {
                    this.a.C.getBackground().setLevel(0);
                    this.a.C.setTag("enable");
                    this.a.a(this.a.C, false);
                    this.a.C.getCompoundDrawables()[0].setLevel(0);
                    r1i = this.a.X.vote_down + 1;
                    this.a.C.setText(String.valueOf(r1i));
                    if (this.a.p()) {
                        this.a.X.vote_down = r1i;
                    } else {
                        ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).vote_down = r1i;
                    }
                }
                this.a.a("up", this.a.af, "active");
                QsbkApp.getMyLikeManager().like(this.a.X);
                QsbkApp.getMyLikeManager().saveToFile();
            } else {
                QsbkApp.currentDataSource.add(QsbkApp.currentSelectItem, this.a.X);
                this.a.B.setText(String.valueOf(r1i));
                if (((String) this.a.C.getTag()).equals("active")) {
                    this.a.a("up", this.a.af, "active");
                    QsbkApp.getMyLikeManager().like(this.a.X);
                    QsbkApp.getMyLikeManager().saveToFile();
                } else {
                    this.a.C.getBackground().setLevel(0);
                    this.a.C.setTag("enable");
                    this.a.a(this.a.C, false);
                    this.a.C.getCompoundDrawables()[0].setLevel(0);
                    r1i = this.a.X.vote_down + 1;
                    this.a.C.setText(String.valueOf(r1i));
                    if (this.a.p()) {
                        this.a.X.vote_down = r1i;
                        this.a.a("up", this.a.af, "active");
                        QsbkApp.getMyLikeManager().like(this.a.X);
                        QsbkApp.getMyLikeManager().saveToFile();
                    } else {
                        ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).vote_down = r1i;
                        this.a.a("up", this.a.af, "active");
                        QsbkApp.getMyLikeManager().like(this.a.X);
                        QsbkApp.getMyLikeManager().saveToFile();
                    }
                }
            }
        }
    }
}
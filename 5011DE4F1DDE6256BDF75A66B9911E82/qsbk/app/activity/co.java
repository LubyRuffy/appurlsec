package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.model.Article;

// compiled from: SingleArticle.java
class co implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ SingleArticle b;

    co(SingleArticle r1_SingleArticle, int r2i) {
        this.b = r1_SingleArticle;
        this.a = r2i;
    }

    public void run() {
        this.b.D.setText(String.valueOf(this.a));
        if (this.b.p()) {
            ((Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem)).comment_count = this.a;
        } else {
            this.b.X.comment_count = this.a;
        }
    }
}
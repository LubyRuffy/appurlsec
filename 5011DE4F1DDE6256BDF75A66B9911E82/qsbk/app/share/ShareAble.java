package qsbk.app.share;

import android.view.View;
import qsbk.app.model.Article;

public interface ShareAble {
    public int getShareRequestCode();

    public void setCollectionIcon(View r1_View);

    public void setSelectedArticle(Article r1_Article);
}
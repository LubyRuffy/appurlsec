package qsbk.app.manager;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.cache.FileCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.utils.CollectionUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class MyLikeManager {
    private static final String a;
    private ArrayList<Object> b;
    private final Lock c;
    private Context d;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[qsbk.app.manager.MyLikeManager.ANSWER.values().length];
            try {
                a[qsbk.app.manager.MyLikeManager.ANSWER.LIKE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a[qsbk.app.manager.MyLikeManager.ANSWER.DISLIKE.ordinal()] = 2;
        }
    }

    public static enum ANSWER {
        LIKE,
        DISLIKE;


        static {
            LIKE = new qsbk.app.manager.MyLikeManager.ANSWER("LIKE", 0);
            DISLIKE = new qsbk.app.manager.MyLikeManager.ANSWER("DISLIKE", 1);
            qsbk.app.manager.MyLikeManager.ANSWER[] r0_qsbk_app_manager_MyLikeManager_ANSWERA = new qsbk.app.manager.MyLikeManager.ANSWER[2];
            r0_qsbk_app_manager_MyLikeManager_ANSWERA[0] = LIKE;
            r0_qsbk_app_manager_MyLikeManager_ANSWERA[1] = DISLIKE;
            a = r0_qsbk_app_manager_MyLikeManager_ANSWERA;
        }
    }

    static {
        a = MyLikeManager.class.getName();
    }

    public MyLikeManager(int r2i, Context r3_Context) {
        this.b = null;
        this.c = new ReentrantLock();
        if (r2i < 0) {
            r2i = 0;
        }
        this.d = r3_Context;
        this.b = new ArrayList(r2i);
        a(this.d);
    }

    private void a(Context r6_Context) {
        String r0_String = FileCache.getContentFromDisk(r6_Context, "mylike");
        if (r0_String != null && r0_String.startsWith("{") && r0_String.endsWith("}")) {
            try {
                JSONArray r1_JSONArray = new JSONObject(r0_String).getJSONArray("items");
                int r2i = r1_JSONArray.length();
                int r0i = 0;
                while (r0i < r2i) {
                    try {
                        if (r1_JSONArray.optJSONObject(r0i) != null) {
                            like(new Article(r1_JSONArray.optJSONObject(r0i)));
                        }
                    } catch (QiushibaikeException e) {
                    }
                    r0i++;
                }
            } catch (Exception e_2) {
                e_2.printStackTrace();
            }
        }
    }

    public void destroy() {
        if (this.b == null || this.b.isEmpty()) {
        } else {
            this.b.clear();
            this.b = null;
        }
    }

    public boolean dislike(Object r3_Object) {
        if (r3_Object == null) {
            return false;
        }
        this.c.lock();
        boolean r0z = this.b.remove(r3_Object);
        this.c.unlock();
        return r0z;
    }

    public boolean dislikeAll(ArrayList<Article> r4_ArrayList_Article) {
        boolean r1z;
        r1z = r4_ArrayList_Article == null || r4_ArrayList_Article.isEmpty();
        if (r1z) {
            Iterator r2_Iterator = r4_ArrayList_Article.iterator();
            while (r2_Iterator.hasNext()) {
                dislike((Article) r2_Iterator.next());
            }
        }
        return r1z;
    }

    public boolean doYouLikeIt(Article r4_Article, ANSWER r5_ANSWER) {
        switch (AnonymousClass_1.a[r5_ANSWER.ordinal()]) {
            case XListViewHeader.STATE_READY:
                return like(r4_Article);
            case XListViewHeader.STATE_REFRESHING:
                return dislike(r4_Article);
        }
        return false;
    }

    public ArrayList<Object> getLikes() {
        return this.b;
    }

    public boolean like(Object r3_Object) {
        boolean r0z = false;
        if (r3_Object != null) {
            this.c.lock();
            r0z = this.b.contains(r3_Object);
            if (!r0z) {
                r0z = this.b.add(r3_Object);
            }
            this.c.unlock();
        }
        return r0z;
    }

    public boolean likeAll(ArrayList<Article> r4_ArrayList_Article) {
        boolean r1z;
        r1z = r4_ArrayList_Article == null || r4_ArrayList_Article.isEmpty();
        if (r1z) {
            Iterator r2_Iterator = r4_ArrayList_Article.iterator();
            while (r2_Iterator.hasNext()) {
                like((Article) r2_Iterator.next());
            }
        }
        return r1z;
    }

    public void saveToFile() {
        this.c.lock();
        FileCache.cacheTextToDiskImmediately(this.d, "mylike", CollectionUtils.artilesToJsonString(this.b));
        this.c.unlock();
    }
}
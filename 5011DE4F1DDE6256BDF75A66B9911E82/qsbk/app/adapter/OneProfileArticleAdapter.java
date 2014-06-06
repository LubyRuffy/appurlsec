package qsbk.app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import org.apache.cordova.Globalization;
import org.apache.cordova.NetworkManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ImageViewer;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.activity.base.IVotePoint;
import qsbk.app.cache.DiskLruCache;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.Article;
import qsbk.app.model.ImageSize;
import qsbk.app.model.Vote;
import qsbk.app.share.ShareAble;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.HighlightableImageButton;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class OneProfileArticleAdapter extends DefaultAdapter<Object> {
    private static String g;
    private static final String h;
    private static int j;
    public final float[] BT_SELECTED;
    private String e;
    private ImageFetcher f;
    private int i;

    public class ViewHolder {
        public View collection_icon;
        public View comment;
        public TextView content;
        public View imageLayout;
        public View imageLoading;
        public ImageView imageView;
        public TextView location;
        public HighlightableImageButton support;
        public TextView supportAndCommentsCount;
        public HighlightableImageButton unsupport;

        public ViewHolder(View r4_View) {
            this.content = (TextView) r4_View.findViewById(R.id.content);
            this.content.setTransformationMethod(new MobileTransformationMethod());
            this.supportAndCommentsCount = (TextView) r4_View.findViewById(R.id.points_and_comments_count);
            this.location = (TextView) r4_View.findViewById(R.id.location);
            this.comment = r4_View.findViewById(R.id.comment);
            this.imageView = (ImageView) r4_View.findViewById(R.id.image);
            this.support = (HighlightableImageButton) r4_View.findViewById(R.id.support);
            this.unsupport = (HighlightableImageButton) r4_View.findViewById(R.id.unsupport);
            this.collection_icon = (ImageView) r4_View.findViewById(R.id.collection_icon);
            this.imageLoading = r4_View.findViewById(R.id.imageLoading);
            this.imageLayout = r4_View.findViewById(R.id.imageLayout);
        }
    }

    class a implements OnClickListener {
        int a;
        String b;
        Article c;
        View d;
        View e;

        public a(View r2_View, String r3_String, int r4i, View r5_View, Article r6_Article) {
            this.b = r3_String;
            this.d = r2_View;
            this.a = r4i;
            this.e = r5_View;
            this.c = r6_Article;
        }

        public void onClick(View r5_View) {
            if (OneProfileArticleAdapter.this.c instanceof ShareAble) {
                ShareAble r0_ShareAble = (ShareAble) OneProfileArticleAdapter.this.c;
                r0_ShareAble.setSelectedArticle(this.c);
                r0_ShareAble.setCollectionIcon(this.d);
                boolean r1z = false;
                if (this.d == null || (!this.d.getTag().equals("active"))) {
                    ShareUtils.openShareDialog(OneProfileArticleAdapter.this.c, r0_ShareAble.getShareRequestCode(), r1z);
                } else {
                    r1z = true;
                    ShareUtils.openShareDialog(OneProfileArticleAdapter.this.c, r0_ShareAble.getShareRequestCode(), r1z);
                }
            }
        }
    }

    class b implements OnClickListener {
        int a;

        public b(int r2i) {
            this.a = r2i;
        }

        public void onClick(View r5_View) {
            if (QsbkApp.currentUser == null || SharePreferenceUtils.getSharePreferencesValue(QsbkDatabase.TOKEN).equals("noregister")) {
            } else {
                QsbkApp.currentDataSource = OneProfileArticleAdapter.this;
                QsbkApp.currentSelectItem = this.a;
                Intent r1_Intent = new Intent(OneProfileArticleAdapter.this.c, SingleArticle.class);
                r1_Intent.putExtra(OneProfileActivity.SOURCE, ((IVotePoint) OneProfileArticleAdapter.this.c).getVotePoint() + (QsbkApp.currentSelectItem + 1));
                OneProfileArticleAdapter.this.c.startActivity(r1_Intent);
            }
        }
    }

    class c implements OnClickListener {
        int a;
        HighlightableImageButton b;
        TextView c;
        HighlightableImageButton d;

        public c(HighlightableImageButton r2_HighlightableImageButton, TextView r3_TextView, HighlightableImageButton r4_HighlightableImageButton, int r5i) {
            this.a = r5i;
            this.b = r2_HighlightableImageButton;
            this.c = r3_TextView;
            this.d = r4_HighlightableImageButton;
        }

        public void onClick(View r7_View) {
            boolean r4z = false;
            if (!this.b.isHighlighted()) {
                this.b.setHighlighted(true);
                Article r0_Article = (Article) OneProfileArticleAdapter.this.b.get(this.a);
                r0_Article.vote_down++;
                if (this.d.isHighlighted()) {
                    this.d.setHighlighted(false);
                    r0_Article = (Article) OneProfileArticleAdapter.this.b.get(this.a);
                    r0_Article.vote_up--;
                }
                String r1_String = g;
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[r4z] = Integer.valueOf(((Article) OneProfileArticleAdapter.this.b.get(this.a)).vote_up);
                r2_ObjectA[1] = Integer.valueOf(((Article) OneProfileArticleAdapter.this.b.get(this.a)).comment_count);
                this.c.setText(String.format(r1_String, r2_ObjectA));
                OneProfileArticleAdapter.this.a(this.a, "dn", ((Article) OneProfileArticleAdapter.this.b.get(this.a)).id, "active");
            }
        }
    }

    class d implements OnClickListener {
        private String b;
        private String c;
        private String d;
        private ImageView e;
        private View f;

        public d(String r2_String, ImageView r3_ImageView, View r4_View, String r5_String, String r6_String) {
            this.b = r5_String;
            this.c = r6_String;
            this.d = r2_String;
            this.e = r3_ImageView;
            this.f = r4_View;
        }

        public void onClick(View r6_View) {
            ((TextView) this.f).setText("\u6b63\u5728\u52a0\u8f7d...");
            OneProfileArticleAdapter.this.f.loadImage(this.d, this.e);
            this.f.setVisibility(Base64.DONT_BREAK_LINES);
            this.e.setOnClickListener(new e(this.b, this.c));
        }
    }

    class e implements OnClickListener {
        String a;
        String b;

        public e(String r3_String, String r4_String) {
            this.a = null;
            this.b = null;
            this.a = r3_String;
            this.b = r4_String;
        }

        public void onClick(View r4_View) {
            Intent r0_Intent = new Intent(OneProfileArticleAdapter.this, ImageViewer.class);
            r0_Intent.putExtra("contentId", this.a);
            r0_Intent.putExtra("imageName", this.b);
            OneProfileArticleAdapter.this.startActivity(r0_Intent);
        }
    }

    class f implements OnClickListener {
        int a;
        HighlightableImageButton b;
        TextView c;
        HighlightableImageButton d;

        public f(HighlightableImageButton r2_HighlightableImageButton, TextView r3_TextView, HighlightableImageButton r4_HighlightableImageButton, int r5i) {
            this.a = r5i;
            this.b = r2_HighlightableImageButton;
            this.c = r3_TextView;
            this.d = r4_HighlightableImageButton;
        }

        public void onClick(View r8_View) {
            if (!this.b.isHighlighted()) {
                this.b.setHighlighted(true);
                Article r0_Article = (Article) OneProfileArticleAdapter.this.b.get(this.a);
                r0_Article.vote_up++;
                if (this.d.isHighlighted()) {
                    this.d.setHighlighted(false);
                    r0_Article = (Article) OneProfileArticleAdapter.this.b.get(this.a);
                    r0_Article.vote_down--;
                }
                OneProfileArticleAdapter.this.a(this.a, "up", ((Article) OneProfileArticleAdapter.this.b.get(this.a)).id, "active");
                String r1_String = g;
                Object[] r2_ObjectA = new Object[2];
                r2_ObjectA[0] = Integer.valueOf(((Article) OneProfileArticleAdapter.this.b.get(this.a)).vote_up);
                r2_ObjectA[1] = Integer.valueOf(((Article) OneProfileArticleAdapter.this.b.get(this.a)).comment_count);
                this.c.setText(String.format(r1_String, r2_ObjectA));
            }
        }
    }

    static {
        h = OneProfileArticleAdapter.class.getName();
    }

    public OneProfileArticleAdapter(Activity r5_Activity, ListView r6_ListView, ArrayList<Object> r7_ArrayList_Object) {
        super(r7_ArrayList_Object, r5_Activity);
        this.e = null;
        this.BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -35.0f, 0.0f, 1.0f, 0.0f, 0.0f, -35.0f, 0.0f, 0.0f, 1.0f, 0.0f, -35.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.a = r6_ListView;
        this.f = QsbkApp.getInstance().getImageWorker(r5_Activity);
        if (g == null) {
            g = this.c.getResources().getString(R.string.points_and_count);
        }
        Resources r0_Resources = this.c.getResources();
        DisplayMetrics r1_DisplayMetrics = r0_Resources.getDisplayMetrics();
        this.i = r1_DisplayMetrics.widthPixels - r0_Resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2;
        j = (int) (((double) r1_DisplayMetrics.heightPixels) * 1.5d);
    }

    private String a(String r5_String, String r6_String) {
        String r0_String = Constants.CONTENT_IMAGE_URL;
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(r5_String).intValue() / 10000);
        r1_ObjectA[1] = r5_String;
        r1_ObjectA[2] = Globalization.MEDIUM;
        r1_ObjectA[3] = r6_String;
        return String.format(r0_String, r1_ObjectA);
    }

    private void a(int r6i, int r7i, int r8i, ViewHolder r9_ViewHolder) {
        String r0_String = r6i + RContactStorage.PRIMARY_KEY;
        String r1_String = r8i + RContactStorage.PRIMARY_KEY;
        String r2_String = g;
        Object[] r3_ObjectA = new Object[2];
        r3_ObjectA[0] = r0_String;
        r3_ObjectA[1] = r1_String;
        r9_ViewHolder.supportAndCommentsCount.setText(String.format(r2_String, r3_ObjectA));
    }

    private void a(View r5_View, int r6i) {
        View r1_View = r5_View.findViewById(R.id.mainLayout);
        LayoutParams r0_LayoutParams;
        if (r1_View.getLayoutParams() == null || r6i != getCount() - 1) {
            r0_LayoutParams = (LayoutParams) r1_View.getLayoutParams();
            r0_LayoutParams.bottomMargin = 0;
            r1_View.setLayoutParams(r0_LayoutParams);
        } else {
            r0_LayoutParams = (LayoutParams) r1_View.getLayoutParams();
            r0_LayoutParams.bottomMargin = this.c.getResources().getDimensionPixelSize(R.dimen.profile_item_margin);
            r1_View.setLayoutParams(r0_LayoutParams);
        }
    }

    private void a(ImageView r5_ImageView, ImageSize r6_ImageSize, int r7i, int r8i) {
        int r2i = 0;
        LayoutParams r0_LayoutParams = (LayoutParams) r5_ImageView.getLayoutParams();
        int[] r1_intA = new int[2];
        calWidthAndHeight(r7i, r8i, r1_intA, r6_ImageSize);
        if (r0_LayoutParams != null) {
            r0_LayoutParams.width = r1_intA[r2i];
            r0_LayoutParams.height = r1_intA[1];
        } else {
            r0_LayoutParams = new LayoutParams(r1_intA[r2i], r1_intA[1]);
        }
        r5_ImageView.setLayoutParams(r0_LayoutParams);
    }

    private void a(String r2_String, TextView r3_TextView) {
        if (TextUtils.isEmpty(r2_String) || "null".equals(r2_String)) {
            r3_TextView.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r3_TextView.setVisibility(0);
            r3_TextView.setText(r2_String);
        }
    }

    private void a(String r8_String, String r9_String, ImageView r10_ImageView, View r11_View) {
        if (TextUtils.isEmpty(r9_String) || r10_ImageView == null) {
        } else {
            DiskLruCache r0_DiskLruCache;
            String r2_String = a(r8_String, r9_String);
            if (!this.e.equals("textonly")) {
                if ((!this.e.equals(NetworkManager.WIFI)) || HttpUtils.isWifi(this.c)) {
                    if (UIHelper.isNightTheme()) {
                        this.f.setLoadingImage(R.color.black);
                    } else {
                        this.f.setLoadingImage(R.color.alpha_black_mask_10_percent);
                    }
                    r10_ImageView.setOnClickListener(new e(r8_String, r9_String));
                    if (!this.f.getMemoryBitmap(r2_String, r10_ImageView)) {
                        this.f.loadImage(r2_String, r10_ImageView);
                    }
                }
            }
            if (UIHelper.isNightTheme()) {
                this.f.setLoadingImage(R.color.black);
            } else {
                this.f.setLoadingImage(R.color.alpha_black_mask_10_percent);
            }
            r0_DiskLruCache = this.f.getImageCache() == null ? null : this.f.getImageCache().getDiskLruCache();
            if (this.f.getMemoryBitmap(r2_String, r10_ImageView)) {
                r11_View.setVisibility(Base64.DONT_BREAK_LINES);
                r10_ImageView.setOnClickListener(new e(r8_String, r9_String));
            } else if (r0_DiskLruCache == null || (!r0_DiskLruCache.containsKey(Md5.MD5(r2_String)))) {
                r10_ImageView.setOnClickListener(new d(r2_String, r10_ImageView, r11_View, r8_String, r9_String));
            } else {
                r11_View.setVisibility(Base64.DONT_BREAK_LINES);
                r10_ImageView.setOnClickListener(new e(r8_String, r9_String));
                this.f.loadImage(r2_String, r10_ImageView);
            }
        }
    }

    private void a(String r6_String, ViewHolder r7_ViewHolder) {
        if (QsbkApp.AllVotes.containsKey(r6_String + "_up")) {
            r7_ViewHolder.support.setHighlighted(true);
        } else {
            r7_ViewHolder.support.setHighlighted(false);
        }
        if (QsbkApp.AllVotes.containsKey(r6_String + "_dn")) {
            r7_ViewHolder.unsupport.setHighlighted(true);
        } else {
            r7_ViewHolder.unsupport.setHighlighted(false);
        }
        if (QsbkApp.allCollection.contains(r6_String)) {
            r7_ViewHolder.collection_icon.setTag("active");
        } else {
            r7_ViewHolder.collection_icon.setTag("enable");
        }
    }

    private void a(Article r5_Article, ViewHolder r6_ViewHolder) {
        a(r5_Article.id, r6_ViewHolder);
        b(r5_Article, r6_ViewHolder);
        c(r5_Article, r6_ViewHolder);
        if (TextUtils.isEmpty(r5_Article.image) || r5_Article.image.equals("null") || r5_Article.image_size == null) {
            a(r5_Article.vote_up, r5_Article.vote_down, r5_Article.comment_count, r6_ViewHolder);
            a(r5_Article.location, r6_ViewHolder.location);
        } else {
            a(r6_ViewHolder.imageView, r5_Article.image_size.smallSize(), this.i, j);
            a(r5_Article.vote_up, r5_Article.vote_down, r5_Article.comment_count, r6_ViewHolder);
            a(r5_Article.location, r6_ViewHolder.location);
        }
    }

    private boolean a(int r6i, String r7_String, String r8_String, String r9_String) {
        String r0_String;
        Vote r1_Vote = new Vote(((IVotePoint) this.c).getVotePoint() + (r6i + 1), r7_String, r8_String, "1");
        QsbkDatabase.getInstance().insertVote(r1_Vote);
        QsbkApp.waitSendVotes.put(String.valueOf(r8_String + "_" + r7_String), r1_Vote);
        QsbkApp.AllVotes.put(String.valueOf(r8_String + "_" + r7_String), r1_Vote);
        r0_String = r7_String.equals("up") ? "dn" : "up";
        Integer r1_Integer = QsbkDatabase.getInstance().queryVote(r8_String, r0_String);
        if (r1_Integer != null) {
            QsbkDatabase.getInstance().deleteVote(r1_Integer);
            QsbkApp.waitSendVotes.remove(String.valueOf(r8_String + "_" + r0_String));
            QsbkApp.AllVotes.remove(String.valueOf(r8_String + "_" + r0_String));
        }
        QsbkApp.voteHandler.obtainMessage().sendToTarget();
        return r1_Integer != null;
    }

    private void b(Article r4_Article, ViewHolder r5_ViewHolder) {
        if (TextUtils.isEmpty(r4_Article.getContent()) || "null".equals(r4_Article.getContent().trim())) {
            r5_ViewHolder.content.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            CharSequence r0_CharSequence = r4_Article.getContent();
            r5_ViewHolder.content.setVisibility(0);
            r5_ViewHolder.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            r5_ViewHolder.content.setText(r0_CharSequence);
        }
    }

    private void c(Article r7_Article, ViewHolder r8_ViewHolder) {
        if (TextUtils.isEmpty(this.e)) {
            this.e = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
            if (TextUtils.isEmpty(this.e)) {
                this.e = "auto";
            }
        }
        if (this.e.equals("textonly")) {
            if (UIHelper.isNightTheme()) {
                r8_ViewHolder.imageView.setImageResource(R.color.black);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            }
            r8_ViewHolder.imageLoading.setVisibility(0);
            ((TextView) r8_ViewHolder.imageLoading).setText("\u70b9\u51fb\u52a0\u8f7d\u56fe\u7247");
        } else if ((!this.e.equals(NetworkManager.WIFI)) || HttpUtils.isWifi(this.c)) {
            r8_ViewHolder.imageLoading.setVisibility(Base64.DONT_BREAK_LINES);
            if (UIHelper.isNightTheme()) {
                r8_ViewHolder.imageView.setImageResource(R.color.black);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            }
        } else {
            if (UIHelper.isNightTheme()) {
                r8_ViewHolder.imageView.setImageResource(R.color.black);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            }
            r8_ViewHolder.imageLoading.setVisibility(0);
            ((TextView) r8_ViewHolder.imageLoading).setText("\u70b9\u51fb\u52a0\u8f7d\u56fe\u7247");
        }
        if (TextUtils.isEmpty(r7_Article.image)) {
            r8_ViewHolder.imageView.setVisibility(XListViewFooter.STATE_NODATA);
            r8_ViewHolder.imageLayout.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r8_ViewHolder.imageLayout.setVisibility(0);
            r8_ViewHolder.imageView.setVisibility(0);
            if (HttpUtils.isNetworkConnected(this.c)) {
                a(r7_Article.id, r7_Article.image, r8_ViewHolder.imageView, r8_ViewHolder.imageLoading);
            } else {
                DiskLruCache r0_DiskLruCache;
                String r1_String = a(r7_Article.id, r7_Article.image);
                r0_DiskLruCache = this.f.getImageCache() == null ? null : this.f.getImageCache().getDiskLruCache();
                if (this.f.getMemoryBitmap(r1_String, r8_ViewHolder.imageView)) {
                    r8_ViewHolder.imageLoading.setVisibility(Base64.DONT_BREAK_LINES);
                } else if (r0_DiskLruCache == null || (!r0_DiskLruCache.containsKey(Md5.MD5(r1_String)))) {
                    r8_ViewHolder.imageView.setOnClickListener(new e(r7_Article.id, r7_Article.image));
                } else {
                    a(r7_Article.id, r7_Article.image, r8_ViewHolder.imageView, r8_ViewHolder.imageLoading);
                }
                r8_ViewHolder.imageView.setOnClickListener(new e(r7_Article.id, r7_Article.image));
            }
        }
    }

    public static void calWidthAndHeight(int r3i, int r4i, int[] r5_intA, ImageSize r6_ImageSize) {
        if (r5_intA == null || r6_ImageSize == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        } else {
            int r0i = (int) ((((float) r3i) / ((float) r6_ImageSize.getWidth())) * ((float) r6_ImageSize.getHeight()));
            if (r4i == -1 || r0i <= r4i) {
                r4i = r0i;
                r5_intA[0] = r3i;
                r5_intA[1] = r4i;
            } else {
                r5_intA[0] = r3i;
                r5_intA[1] = r4i;
            }
        }
    }

    public void clearImageCache() {
    }

    public int getItemViewType(int r2i) {
        return getItem(r2i) instanceof Article ? 0 : 1;
    }

    public View getView(int r10i, View r11_View, ViewGroup r12_ViewGroup) {
        ColorFilter r2_ColorFilter = null;
        switch (getItemViewType(r10i)) {
            case XListViewHeader.STATE_NORMAL:
                ViewHolder r7_ViewHolder;
                if (r11_View == null) {
                    r11_View = this.d.inflate(R.layout.one_profile_my_article_item, null);
                    ViewHolder r0_ViewHolder = new ViewHolder(r11_View);
                    r11_View.setTag(r0_ViewHolder);
                    r7_ViewHolder = r0_ViewHolder;
                } else {
                    r7_ViewHolder = (ViewHolder) r11_View.getTag();
                }
                r7_ViewHolder.imageView.setColorFilter(r2_ColorFilter);
                a(r11_View, r10i);
                Article r6_Article = (Article) getItem(r10i);
                a(r6_Article, r7_ViewHolder);
                r7_ViewHolder.support.setOnClickListener(new f(r7_ViewHolder.support, r7_ViewHolder.supportAndCommentsCount, r7_ViewHolder.unsupport, r10i));
                r7_ViewHolder.unsupport.setOnClickListener(new c(r7_ViewHolder.unsupport, r7_ViewHolder.supportAndCommentsCount, r7_ViewHolder.support, r10i));
                r7_ViewHolder.collection_icon.setOnClickListener(new a(r7_ViewHolder.collection_icon, r6_Article.id, r10i, r11_View, r6_Article));
                r7_ViewHolder.comment.setOnClickListener(new b(r10i));
                return r11_View;
            case XListViewHeader.STATE_READY:
                return r11_View == null ? this.d.inflate(R.layout.one_profile_article_item_0, null) : r11_View;
        }
        return r11_View;
    }

    public int getViewTypeCount() {
        return XListViewHeader.STATE_REFRESHING;
    }

    public void onPause() {
        this.f.setExitTasksEarly(true);
    }

    public void onResume() {
        this.f.setExitTasksEarly(false);
    }
}
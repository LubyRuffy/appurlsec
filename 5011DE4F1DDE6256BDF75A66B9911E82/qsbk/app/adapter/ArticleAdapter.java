package qsbk.app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.LocationClientOption;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import org.apache.cordova.Globalization;
import org.apache.cordova.NetworkManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ImageViewer;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.IVotePoint;
import qsbk.app.cache.DiskLruCache;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.gdtad.AdItemData;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.model.Article;
import qsbk.app.model.ImageSize;
import qsbk.app.model.Vote;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ArticleAdapter extends DefaultAdapter<Object> {
    private static int m;
    public final float[] BT_SELECTED;
    private String e;
    private ColorStateList f;
    private ColorStateList g;
    private ColorStateList h;
    private ColorStateList i;
    private ImageFetcher j;
    private ImageFetcher k;
    private int l;

    public class AdViewHolder {
        public TextView adContent;
        public ImageView adImage;
        public TextView adOption;
        public TextView adSize;
        public TextView adTitle;
        public TextView usersNum;

        public AdViewHolder(View r3_View) {
            this.adTitle = (TextView) r3_View.findViewById(R.id.AdTitle);
            this.adContent = (TextView) r3_View.findViewById(R.id.AdContent);
            this.adImage = (ImageView) r3_View.findViewById(R.id.AdImage);
            this.usersNum = (TextView) r3_View.findViewById(R.id.usersNum);
            this.adSize = (TextView) r3_View.findViewById(R.id.AdSize);
            this.adOption = (TextView) r3_View.findViewById(R.id.AdOption);
        }
    }

    public class ViewHolder {
        public ImageView collection_icon;
        public TextView comment_text;
        public TextView content;
        public ImageView currentAvatarView;
        public View imageLayout;
        public View imageLoading;
        public ImageView imageView;
        public ImageView oppose_icon;
        public Button oppose_text;
        public ImageView support_icon;
        public Button support_text;
        public TextView tagContent;
        public View textMoreTag;
        public View userInfoLayout;
        public TextView userName;

        public ViewHolder(View r4_View) {
            this.userName = (TextView) r4_View.findViewById(R.id.userName);
            this.content = (TextView) r4_View.findViewById(R.id.content);
            this.content.setTransformationMethod(new MobileTransformationMethod());
            this.comment_text = (TextView) r4_View.findViewById(R.id.comment_text);
            this.tagContent = (TextView) r4_View.findViewById(R.id.tagContent);
            this.tagContent.setTransformationMethod(new MobileTransformationMethod());
            this.imageView = (ImageView) r4_View.findViewById(R.id.image);
            this.textMoreTag = r4_View.findViewById(R.id.textmore);
            this.support_text = (Button) r4_View.findViewById(R.id.support_text);
            this.oppose_text = (Button) r4_View.findViewById(R.id.down_text);
            this.collection_icon = (ImageView) r4_View.findViewById(R.id.collection_icon);
            this.userInfoLayout = r4_View.findViewById(R.id.userInfo);
            this.currentAvatarView = (ImageView) r4_View.findViewById(R.id.userIcon);
            this.imageLoading = r4_View.findViewById(R.id.imageLoading);
            this.imageLayout = r4_View.findViewById(R.id.imageLayout);
        }
    }

    class a implements OnClickListener {
        int a;
        String b;
        ImageView c;
        View d;

        public a(ImageView r2_ImageView, String r3_String, int r4i, View r5_View) {
            this.b = r3_String;
            this.c = r2_ImageView;
            this.a = r4i;
            this.d = r5_View;
        }

        public void onClick(View r7_View) {
            ArticleAdapter.this.a.getOnItemLongClickListener().onItemLongClick(ArticleAdapter.this.a, this.d, this.a + 1, (long) (this.a + 1));
        }
    }

    class b implements OnClickListener {
        int a;
        View b;
        ImageView c;
        TextView d;
        ImageView e;
        TextView f;

        public b(ImageView r2_ImageView, TextView r3_TextView, ImageView r4_ImageView, TextView r5_TextView, View r6_View, int r7i) {
            this.a = r7i;
            this.c = r2_ImageView;
            this.d = r3_TextView;
            this.e = r4_ImageView;
            this.f = r5_TextView;
            this.b = r6_View;
        }

        public void onClick(View r8_View) {
            if (r8_View.getTag().equals("enable")) {
                ToastAndDialog.makeText(ArticleAdapter.this.c, this.b, "-1", 800, -16776961);
                r8_View.getBackground().setLevel(1);
                r8_View.setTag("active");
                ArticleAdapter.this.a(this.d, true);
                this.d.getCompoundDrawables()[0].setLevel(1);
                int r1i = Integer.valueOf(this.d.getText().toString()).intValue() - 1;
                ((Article) ArticleAdapter.this.b.get(this.a)).vote_down = r1i;
                this.d.setText(String.valueOf(r1i));
                if (((String) this.f.getTag()).equals("active")) {
                    this.f.getBackground().setLevel(0);
                    this.f.setTag("enable");
                    ArticleAdapter.this.a(this.f, false);
                    this.f.getCompoundDrawables()[0].setLevel(0);
                    Integer r1_Integer = Integer.valueOf(Integer.valueOf(this.f.getText().toString()).intValue() - 1);
                    this.f.setText(r1_Integer.toString());
                    ((Article) ArticleAdapter.this.b.get(this.a)).vote_up = r1_Integer.intValue();
                }
                ArticleAdapter.this.a(this.a, "dn", ((Article) ArticleAdapter.this.b.get(this.a)).id, "active");
            }
        }
    }

    class c implements OnClickListener {
        private String b;
        private String c;
        private String d;
        private ImageView e;
        private View f;

        public c(String r2_String, ImageView r3_ImageView, View r4_View, String r5_String, String r6_String) {
            this.b = r5_String;
            this.c = r6_String;
            this.d = r2_String;
            this.e = r3_ImageView;
            this.f = r4_View;
        }

        public void onClick(View r6_View) {
            ((TextView) this.f).setText("\u6b63\u5728\u52a0\u8f7d...");
            ArticleAdapter.this.j.loadImage(this.d, this.e);
            this.f.setVisibility(Base64.DONT_BREAK_LINES);
            this.e.setOnClickListener(new d(this.b, this.c));
        }
    }

    class d implements OnClickListener {
        String a;
        String b;

        public d(String r3_String, String r4_String) {
            this.a = null;
            this.b = null;
            this.a = r3_String;
            this.b = r4_String;
        }

        public void onClick(View r4_View) {
            Intent r0_Intent = new Intent(ArticleAdapter.this, ImageViewer.class);
            r0_Intent.putExtra("contentId", this.a);
            r0_Intent.putExtra("imageName", this.b);
            ArticleAdapter.this.startActivity(r0_Intent);
            ((GroupChildBaseListViewActivity) ArticleAdapter.this).setCanBack(false);
        }
    }

    class e implements OnClickListener {
        int a;
        View b;
        ImageView c;
        TextView d;
        ImageView e;
        TextView f;

        public e(ImageView r2_ImageView, TextView r3_TextView, ImageView r4_ImageView, TextView r5_TextView, View r6_View, int r7i) {
            this.a = r7i;
            this.c = r2_ImageView;
            this.d = r3_TextView;
            this.e = r4_ImageView;
            this.f = r5_TextView;
            this.b = r6_View;
        }

        public void onClick(View r8_View) {
            if (r8_View.getTag().equals("enable")) {
                ToastAndDialog.makeText(ArticleAdapter.this.c, this.d, "+1", 800, SupportMenu.CATEGORY_MASK);
                r8_View.getBackground().setLevel(1);
                this.d.getCompoundDrawables()[0].setLevel(1);
                r8_View.setTag("active");
                ArticleAdapter.this.a(this.d, true);
                Integer r1_Integer = Integer.valueOf(Integer.valueOf(this.d.getText().toString()).intValue() + 1);
                ((Article) ArticleAdapter.this.b.get(this.a)).vote_up = r1_Integer.intValue();
                this.d.setText(r1_Integer.toString());
                if (((String) this.f.getTag()).equals("active")) {
                    this.f.getBackground().setLevel(0);
                    this.f.setTag("enable");
                    ArticleAdapter.this.a(this.f, false);
                    this.f.getCompoundDrawables()[0].setLevel(0);
                    r1_Integer = Integer.valueOf(Integer.valueOf(this.f.getText().toString()).intValue() + 1);
                    this.f.setText(r1_Integer.toString());
                    ((Article) ArticleAdapter.this.b.get(this.a)).vote_down = r1_Integer.intValue();
                }
                ArticleAdapter.this.a(this.a, "up", ((Article) ArticleAdapter.this.b.get(this.a)).id, "active");
            }
        }
    }

    class f implements OnClickListener {
        String a;
        String b;
        String c;
        String d;

        public f(String r3_String, String r4_String, String r5_String) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.a = r3_String;
            this.b = r4_String;
            this.c = r5_String;
        }

        public void onClick(View r6_View) {
            if (QsbkApp.currentUser != null) {
                Intent r0_Intent = new Intent(ArticleAdapter.this.c, OneProfileActivity.class);
                if (QsbkApp.currentUser.userId.equals(this.a)) {
                    r0_Intent.putExtra(OneProfileActivity.USER, QsbkApp.currentUser.toString());
                } else {
                    r0_Intent.putExtra(QsbkDatabase.USER_ID, this.a);
                    r0_Intent.putExtra(OneProfileActivity.USER_ICON_URL, this.c);
                    r0_Intent.putExtra(OneProfileActivity.USER_NAME, this.b);
                    r0_Intent.putExtra(OneProfileActivity.SELECTED_TAB_ID, XListViewHeader.STATE_REFRESHING);
                    r0_Intent.putExtra(OneProfileActivity.MSG_SOURCE, new ChatMsgSource(2, this.a, this.d).encodeToJsonObject().toString());
                }
                ArticleAdapter.this.c.startActivity(r0_Intent);
            } else {
                Toast.makeText(ArticleAdapter.this.c, "\u767b\u9646\u540e\u624d\u80fd\u67e5\u770b\u522b\u4eba\u7684\u4fe1\u606f\u54e6", 1).show();
            }
        }

        public void setArticleId(String r1_String) {
            this.d = r1_String;
        }
    }

    public ArticleAdapter(Activity r5_Activity, ListView r6_ListView, ArrayList<Object> r7_ArrayList_Object) {
        super(r7_ArrayList_Object, r5_Activity);
        this.e = "auto";
        this.BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -35.0f, 0.0f, 1.0f, 0.0f, 0.0f, -35.0f, 0.0f, 0.0f, 1.0f, 0.0f, -35.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.a = r6_ListView;
        this.f = UIHelper.getColorStateList(R.color.vote_active);
        this.g = UIHelper.getColorStateList(R.color.vote);
        this.h = UIHelper.getColorStateList(R.color.vote_active_night);
        this.i = UIHelper.getColorStateList(R.color.vote_night);
        this.j = QsbkApp.getInstance().getImageWorker(r5_Activity);
        this.k = QsbkApp.getInstance().getAvatarWorker(r5_Activity);
        Resources r0_Resources = this.c.getResources();
        DisplayMetrics r1_DisplayMetrics = r0_Resources.getDisplayMetrics();
        this.l = r1_DisplayMetrics.widthPixels - r0_Resources.getDimensionPixelSize(R.dimen.item_padding) * 2 - 7;
        m = (int) (((double) r1_DisplayMetrics.heightPixels) * 1.5d);
    }

    private String a(String r5_String, String r6_String) {
        String r1_String = Constants.CONTENT_IMAGE_URL;
        Object[] r2_ObjectA = new Object[4];
        r2_ObjectA[0] = Integer.valueOf(Integer.valueOf(r5_String).intValue() / 10000);
        r2_ObjectA[1] = r5_String;
        r2_ObjectA[XListViewHeader.STATE_REFRESHING] = QsbkApp.isPad ? Globalization.MEDIUM : "small";
        r2_ObjectA[3] = r6_String;
        return String.format(r1_String, r2_ObjectA);
    }

    private void a(int r4i, int r5i, int r6i, ViewHolder r7_ViewHolder) {
        r7_ViewHolder.support_text.setText(String.valueOf(r4i));
        r7_ViewHolder.oppose_text.setText(String.valueOf(r5i));
        StringBuffer r1_StringBuffer = new StringBuffer(RContactStorage.PRIMARY_KEY);
        if (r6i == 0) {
            r7_ViewHolder.comment_text.setTextSize(14.0f);
            r1_StringBuffer.append("\u8bc4\u8bba");
        } else {
            r7_ViewHolder.comment_text.setTextSize(16.0f);
            r1_StringBuffer.append(r6i > LocationClientOption.MIN_SCAN_SPAN ? "1K+" : String.valueOf(r6i));
        }
        r7_ViewHolder.comment_text.setText(r1_StringBuffer);
    }

    private void a(ImageView r5_ImageView, ImageSize r6_ImageSize, int r7i, int r8i) {
        int r2i = 0;
        LayoutParams r0_LayoutParams = (LayoutParams) r5_ImageView.getLayoutParams();
        int[] r1_intA = new int[2];
        MyCollectionsAdapter.calWidthAndHeight(r7i, r8i, r1_intA, r6_ImageSize);
        if (r0_LayoutParams != null) {
            r0_LayoutParams.width = r1_intA[r2i];
            r0_LayoutParams.height = r1_intA[1];
        } else {
            r0_LayoutParams = new LayoutParams(r1_intA[r2i], r1_intA[1]);
        }
        r5_ImageView.setLayoutParams(r0_LayoutParams);
    }

    private void a(TextView r2_TextView, boolean r3z) {
        if (UIHelper.isNightTheme()) {
            if (r3z) {
                r2_TextView.setTextColor(this.h);
            } else {
                r2_TextView.setTextColor(this.i);
            }
        } else if (r3z) {
            r2_TextView.setTextColor(this.f);
        } else {
            r2_TextView.setTextColor(this.g);
        }
    }

    private void a(String r5_String, String r6_String, ImageView r7_ImageView) {
        if (TextUtils.isEmpty(r6_String) || "null".equals(r6_String.trim())) {
            if (UIHelper.isNightTheme()) {
                r7_ImageView.setImageResource(R.drawable.default_users_avatar_night);
            } else {
                r7_ImageView.setImageResource(R.drawable.default_users_avatar);
            }
        } else {
            String r0_String = Constants.ARATAR_URL;
            Object[] r1_ObjectA = new Object[4];
            r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(r5_String).intValue() / 10000);
            r1_ObjectA[1] = r5_String;
            r1_ObjectA[2] = "thumb";
            r1_ObjectA[3] = r6_String;
            this.k.loadImage(String.format(r0_String, r1_ObjectA), r7_ImageView);
            if (UIHelper.isNightTheme()) {
                r7_ImageView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            }
        }
    }

    private void a(String r8_String, String r9_String, ImageView r10_ImageView, View r11_View) {
        if (TextUtils.isEmpty(r9_String) || r10_ImageView == null) {
        } else {
            DiskLruCache r0_DiskLruCache;
            if (UIHelper.isNightTheme()) {
                r10_ImageView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            }
            String r2_String = a(r8_String, r9_String);
            if (!this.e.equals("textonly")) {
                if ((!this.e.equals(NetworkManager.WIFI)) || HttpUtils.isWifi(this.c)) {
                    if (UIHelper.isNightTheme()) {
                        if (QsbkApp.isPad) {
                            this.j.setLoadingImage(R.color.black);
                        } else {
                            this.j.setLoadingImage(R.drawable.default_content_pic_night);
                        }
                    } else if (QsbkApp.isPad) {
                        this.j.setLoadingImage(R.color.alpha_black_mask_10_percent);
                    } else {
                        this.j.setLoadingImage(R.drawable.default_content_pic);
                    }
                    r10_ImageView.setOnClickListener(new d(r8_String, r9_String));
                    if (!this.j.getMemoryBitmap(r2_String, r10_ImageView)) {
                        this.j.loadImage(r2_String, r10_ImageView);
                    }
                }
            }
            if (UIHelper.isNightTheme()) {
                if (QsbkApp.isPad) {
                    this.j.setLoadingImage(R.color.black);
                } else {
                    this.j.setLoadingImage(R.drawable.default_lazy_content_pic_night);
                }
            } else if (QsbkApp.isPad) {
                this.j.setLoadingImage(R.color.alpha_black_mask_10_percent);
            } else {
                this.j.setLoadingImage(R.drawable.default_lazy_content_pic);
            }
            r0_DiskLruCache = this.j.getImageCache() == null ? null : this.j.getImageCache().getDiskLruCache();
            if (this.j.getMemoryBitmap(r2_String, r10_ImageView)) {
                r11_View.setVisibility(Base64.DONT_BREAK_LINES);
                r10_ImageView.setOnClickListener(new d(r8_String, r9_String));
            } else if (r0_DiskLruCache == null || (!r0_DiskLruCache.containsKey(Md5.MD5(r2_String)))) {
                r10_ImageView.setOnClickListener(new c(r2_String, r10_ImageView, r11_View, r8_String, r9_String));
            } else {
                r11_View.setVisibility(Base64.DONT_BREAK_LINES);
                r10_ImageView.setOnClickListener(new d(r8_String, r9_String));
                this.j.loadImage(r2_String, r10_ImageView);
            }
        }
    }

    private void a(String r6_String, ViewHolder r7_ViewHolder) {
        if (QsbkApp.AllVotes.containsKey(r6_String + "_up")) {
            r7_ViewHolder.support_text.setTag("active");
            r7_ViewHolder.support_text.getBackground().setLevel(1);
            a(r7_ViewHolder.support_text, true);
            r7_ViewHolder.support_text.getCompoundDrawables()[0].setLevel(1);
        } else {
            r7_ViewHolder.support_text.setTag("enable");
            r7_ViewHolder.support_text.getBackground().setLevel(0);
            a(r7_ViewHolder.support_text, false);
            r7_ViewHolder.support_text.getCompoundDrawables()[0].setLevel(0);
        }
        if (QsbkApp.AllVotes.containsKey(r6_String + "_dn")) {
            r7_ViewHolder.oppose_text.getBackground().setLevel(1);
            r7_ViewHolder.oppose_text.setTag("active");
            a(r7_ViewHolder.oppose_text, true);
            r7_ViewHolder.oppose_text.getCompoundDrawables()[0].setLevel(1);
        } else {
            r7_ViewHolder.oppose_text.setTag("enable");
            r7_ViewHolder.oppose_text.getBackground().setLevel(0);
            a(r7_ViewHolder.oppose_text, false);
            r7_ViewHolder.oppose_text.getCompoundDrawables()[0].setLevel(0);
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
        d(r5_Article, r6_ViewHolder);
        if ((!QsbkApp.isPad) || TextUtils.isEmpty(r5_Article.image) || r5_Article.image.equals("null") || r5_Article.image_size == null) {
            e(r5_Article, r6_ViewHolder);
            a(r5_Article.vote_up, r5_Article.vote_down, r5_Article.comment_count, r6_ViewHolder);
        } else {
            a(r6_ViewHolder.imageView, r5_Article.image_size.smallSize(), this.l, m);
            e(r5_Article, r6_ViewHolder);
            a(r5_Article.vote_up, r5_Article.vote_down, r5_Article.comment_count, r6_ViewHolder);
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

    private void b(Article r5_Article, ViewHolder r6_ViewHolder) {
        if (TextUtils.isEmpty(r5_Article.login) || "Guest".equals(r5_Article.login)) {
            r6_ViewHolder.userInfoLayout.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r6_ViewHolder.userInfoLayout.setVisibility(0);
            r6_ViewHolder.userName.setText(r5_Article.login);
            a(r5_Article.user_id, r5_Article.user_icon, r6_ViewHolder.currentAvatarView);
            OnClickListener r0_OnClickListener = new f(r5_Article.user_id, r5_Article.login, r5_Article.user_icon);
            r0_OnClickListener.setArticleId(r5_Article.id);
            r6_ViewHolder.userInfoLayout.setOnClickListener(r0_OnClickListener);
        }
    }

    private void c(Article r6_Article, ViewHolder r7_ViewHolder) {
        if (TextUtils.isEmpty(r6_Article.getContent()) || "null".equals(r6_Article.getContent().trim())) {
            r7_ViewHolder.content.setVisibility(Base64.DONT_BREAK_LINES);
            r7_ViewHolder.textMoreTag.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            CharSequence r0_CharSequence = r6_Article.getContent();
            r7_ViewHolder.content.setVisibility(0);
            if (r0_CharSequence.length() > 180) {
                r0_CharSequence = r0_CharSequence.substring(0, 180);
                r7_ViewHolder.textMoreTag.setVisibility(0);
            } else {
                r7_ViewHolder.textMoreTag.setVisibility(Base64.DONT_BREAK_LINES);
            }
            r7_ViewHolder.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            r7_ViewHolder.content.setText(r0_CharSequence);
        }
    }

    private void d(Article r7_Article, ViewHolder r8_ViewHolder) {
        this.e = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
        if (this.e.equals("textonly")) {
            if (UIHelper.isNightTheme()) {
                if (QsbkApp.isPad) {
                    r8_ViewHolder.imageView.setImageResource(R.color.black);
                } else {
                    r8_ViewHolder.imageView.setImageResource(R.drawable.default_lazy_content_pic_night);
                }
            } else if (QsbkApp.isPad) {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.drawable.default_lazy_content_pic);
            }
            r8_ViewHolder.imageLoading.setVisibility(0);
            ((TextView) r8_ViewHolder.imageLoading).setText("\u70b9\u51fb\u52a0\u8f7d\u56fe\u7247");
        } else if ((!this.e.equals(NetworkManager.WIFI)) || HttpUtils.isWifi(this.c)) {
            r8_ViewHolder.imageLoading.setVisibility(Base64.DONT_BREAK_LINES);
            if (UIHelper.isNightTheme()) {
                if (QsbkApp.isPad) {
                    r8_ViewHolder.imageView.setImageResource(R.color.black);
                } else {
                    r8_ViewHolder.imageView.setImageResource(R.drawable.default_content_pic_night);
                }
            } else if (QsbkApp.isPad) {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.drawable.default_content_pic);
            }
        } else {
            if (UIHelper.isNightTheme()) {
                if (QsbkApp.isPad) {
                    r8_ViewHolder.imageView.setImageResource(R.color.black);
                } else {
                    r8_ViewHolder.imageView.setImageResource(R.drawable.default_lazy_content_pic_night);
                }
            } else if (QsbkApp.isPad) {
                r8_ViewHolder.imageView.setImageResource(R.color.alpha_black_mask_10_percent);
            } else {
                r8_ViewHolder.imageView.setImageResource(R.drawable.default_lazy_content_pic);
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
                r0_DiskLruCache = this.j.getImageCache() == null ? null : this.j.getImageCache().getDiskLruCache();
                if (this.j.getMemoryBitmap(r1_String, r8_ViewHolder.imageView)) {
                    r8_ViewHolder.imageLoading.setVisibility(Base64.DONT_BREAK_LINES);
                } else if (r0_DiskLruCache == null || (!r0_DiskLruCache.containsKey(Md5.MD5(r1_String)))) {
                    r8_ViewHolder.imageView.setOnClickListener(new d(r7_Article.id, r7_Article.image));
                } else {
                    a(r7_Article.id, r7_Article.image, r8_ViewHolder.imageView, r8_ViewHolder.imageLoading);
                }
                r8_ViewHolder.imageView.setOnClickListener(new d(r7_Article.id, r7_Article.image));
            }
        }
        if (UIHelper.isNightTheme()) {
            r8_ViewHolder.imageView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            r8_ViewHolder.currentAvatarView.setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
        }
    }

    private void e(Article r3_Article, ViewHolder r4_ViewHolder) {
        if (TextUtils.isEmpty(r3_Article.tag) || "null".equals(r3_Article.tag.trim())) {
            r4_ViewHolder.tagContent.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r4_ViewHolder.tagContent.setVisibility(0);
            r4_ViewHolder.tagContent.setText(r3_Article.tag.trim());
        }
    }

    public void clearImageCache() {
    }

    public int getItemViewType(int r2i) {
        return getItem(r2i) instanceof Article ? 0 : 1;
    }

    public View getView(int r12i, View r13_View, ViewGroup r14_ViewGroup) {
        ColorFilter r2_ColorFilter = null;
        switch (getItemViewType(r12i)) {
            case XListViewHeader.STATE_NORMAL:
                ViewHolder r9_ViewHolder;
                if (r13_View == null) {
                    r13_View = this.d.inflate(R.layout.listitem_content_row, null);
                    ViewHolder r0_ViewHolder = new ViewHolder(r13_View);
                    r13_View.setTag(r0_ViewHolder);
                    r9_ViewHolder = r0_ViewHolder;
                } else {
                    r9_ViewHolder = (ViewHolder) r13_View.getTag();
                }
                r9_ViewHolder.imageView.setColorFilter(r2_ColorFilter);
                r9_ViewHolder.currentAvatarView.setColorFilter(r2_ColorFilter);
                Article r8_Article = (Article) getItem(r12i);
                a(r8_Article, r9_ViewHolder);
                r9_ViewHolder.support_text.setOnClickListener(new e(r9_ViewHolder.support_icon, r9_ViewHolder.support_text, r9_ViewHolder.oppose_icon, r9_ViewHolder.oppose_text, r9_ViewHolder.support_text, r12i));
                r9_ViewHolder.oppose_text.setOnClickListener(new b(r9_ViewHolder.oppose_icon, r9_ViewHolder.oppose_text, r9_ViewHolder.support_icon, r9_ViewHolder.support_text, r9_ViewHolder.oppose_text, r12i));
                r9_ViewHolder.collection_icon.setOnClickListener(new a(r9_ViewHolder.collection_icon, r8_Article.id, r12i, r13_View));
                return r13_View;
            case XListViewHeader.STATE_READY:
                return ((AdItemData) getItem(r12i)).getView().getView(r13_View, this.c);
        }
        return r13_View;
    }

    public int getViewTypeCount() {
        return XListViewHeader.STATE_REFRESHING;
    }

    public void onPause() {
        this.j.setExitTasksEarly(true);
    }

    public void onResume() {
        this.j.setExitTasksEarly(false);
    }
}
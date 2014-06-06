package qsbk.app.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.Article;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DateUtil;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.image.ImageFetcher;

public class ManageMyContentsAdapter extends DefaultAdapter {
    public static final String PENDING = "pending";
    public static final String PRIVATE = "private";
    public static final String PUBLISH = "publish";
    public static final String REPORTED = "reported";
    public static final String SPAM = "spam";
    private static String f;
    private static int h;
    private ImageFetcher e;
    private int g;

    class a {
        public TextView content;
        public TextView location;
        public TextView pointAndCount;
        public TextView sendDate;
        public TextView sendStateContent;
        public ImageView sendStateImage;

        a() {
        }
    }

    public ManageMyContentsAdapter(Activity r5_Activity, ListView r6_ListView, ArrayList<Object> r7_ArrayList_Object) {
        super(r7_ArrayList_Object, r5_Activity);
        this.a = r6_ListView;
        this.e = QsbkApp.getInstance().getImageWorker(this.c);
        if (f == null) {
            f = this.c.getResources().getString(R.string.points_and_count);
        }
        Resources r0_Resources = this.c.getResources();
        DisplayMetrics r1_DisplayMetrics = r0_Resources.getDisplayMetrics();
        this.g = r1_DisplayMetrics.widthPixels - r0_Resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2 - 10;
        h = (int) (((double) r1_DisplayMetrics.heightPixels) * 1.5d);
    }

    private void a(ImageView r5_ImageView, ImageSize r6_ImageSize, int r7i, int r8i) {
        int r2i = 0;
        LayoutParams r0_LayoutParams = (LayoutParams) r5_ImageView.getLayoutParams();
        int[] r1_intA = new int[2];
        OneProfileArticleAdapter.calWidthAndHeight(r7i, r8i, r1_intA, r6_ImageSize);
        if (r0_LayoutParams != null) {
            r0_LayoutParams.width = r1_intA[r2i];
            r0_LayoutParams.height = r1_intA[1];
        } else {
            r0_LayoutParams = new LayoutParams(r1_intA[r2i], r1_intA[1]);
        }
        r5_ImageView.setLayoutParams(r0_LayoutParams);
    }

    private void a(TextView r2_TextView, String r3_String) {
        if (TextUtils.isEmpty(r3_String) || "null".equals(r3_String)) {
            r2_TextView.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r2_TextView.setVisibility(0);
            r2_TextView.setText(r3_String);
        }
    }

    private void a(a r5_a, String r6_String) {
        Drawable r0_Drawable;
        if (r6_String.equals(PUBLISH)) {
            r5_a.sendStateContent.setText("\u5df2\u53d1\u8868");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.my_content_pass_icon);
        } else if (r6_String.equals(PENDING)) {
            r5_a.sendStateContent.setText("\u5f85\u5ba1\u6838");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.my_content_waiting_icon);
        } else if (r6_String.equals(SPAM) || r6_String.equals(PRIVATE)) {
            r5_a.sendStateContent.setText("\u672a\u901a\u8fc7\u5ba1\u6838");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.my_content_no_pass);
        } else if (r6_String.equals(REPORTED)) {
            r5_a.sendStateContent.setText("\u88ab\u4e3e\u62a5");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.my_content_report_icon);
        } else {
            r5_a.sendStateContent.setText("\u706b\u661f\u8d44\u6599\u5907\u4efd\u4e2d");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.my_content_waiting_icon);
        }
        r5_a.sendStateContent.setCompoundDrawablesWithIntrinsicBounds(r0_Drawable, null, null, null);
    }

    private void a(a r7_a, Article r8_Article, String r9_String) {
        String r0_String;
        String r1_String;
        String r2_String;
        Object[] r3_ObjectA;
        if (TextUtils.isEmpty(r8_Article.image) || r8_Article.image.equals("null") || r8_Article.image_size == null) {
            r0_String = r8_Article.image;
            r1_String = r8_Article.id;
            if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                r7_a.sendStateImage.setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r9_String.equals(PENDING)) {
                r7_a.sendStateImage.setVisibility(0);
                r2_String = Constants.CONTENT_IMAGE_URL;
                r3_ObjectA = new Object[4];
                r3_ObjectA[0] = Integer.valueOf(Integer.valueOf(r1_String).intValue() / 10000);
                r3_ObjectA[1] = r1_String;
                r3_ObjectA[2] = "small";
                r3_ObjectA[3] = r0_String;
                r0_String = String.format(r2_String, r3_ObjectA);
                DebugUtil.debug("\u56fe\u7247\u52a0\u8f7d\u5730\u5740\uff1a" + r0_String);
                this.e.setLoadingImage(R.color.alpha_black_mask_10_percent);
                this.e.loadImage(r0_String, r7_a.sendStateImage);
            } else {
                r7_a.sendStateImage.setImageResource(R.color.white);
            }
        } else {
            a(r7_a.sendStateImage, r8_Article.image_size.smallSize(), this.g, h);
            r0_String = r8_Article.image;
            r1_String = r8_Article.id;
            if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                r7_a.sendStateImage.setVisibility(Base64.DONT_BREAK_LINES);
            } else if (r9_String.equals(PENDING)) {
                r7_a.sendStateImage.setVisibility(0);
                r2_String = Constants.CONTENT_IMAGE_URL;
                r3_ObjectA = new Object[4];
                r3_ObjectA[0] = Integer.valueOf(Integer.valueOf(r1_String).intValue() / 10000);
                r3_ObjectA[1] = r1_String;
                r3_ObjectA[2] = "small";
                r3_ObjectA[3] = r0_String;
                r0_String = String.format(r2_String, r3_ObjectA);
                DebugUtil.debug("\u56fe\u7247\u52a0\u8f7d\u5730\u5740\uff1a" + r0_String);
                this.e.setLoadingImage(R.color.alpha_black_mask_10_percent);
                this.e.loadImage(r0_String, r7_a.sendStateImage);
            } else {
                r7_a.sendStateImage.setImageResource(R.color.white);
            }
        }
    }

    public View getView(int r7i, View r8_View, ViewGroup r9_ViewGroup) {
        a r1_a;
        int r5i = 0;
        if (r8_View == null) {
            r8_View = this.d.inflate(R.layout.listitem_manage_mycontent_row, null);
            a r0_a = initHolderView(r8_View, new a());
            r8_View.setTag(r0_a);
            r1_a = r0_a;
        } else {
            r1_a = (a) r8_View.getTag();
        }
        Article r0_Article = (Article) getItem(r7i);
        CharSequence r2_CharSequence = r0_Article.content.trim();
        if (TextUtils.isEmpty(r2_CharSequence) || r2_CharSequence.equals("null")) {
            r1_a.content.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r1_a.content.setVisibility(0);
            r1_a.content.setTransformationMethod(new MobileTransformationMethod());
            r1_a.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            r1_a.content.setText(r2_CharSequence);
        }
        r1_a.sendDate.setText(DateUtil.Get_DiffDate_Info(this.c, r0_Article.created_at, r5i).toString());
        String r2_String = f;
        Object[] r3_ObjectA = new Object[2];
        r3_ObjectA[r5i] = Integer.valueOf(r0_Article.vote_up);
        r3_ObjectA[1] = Integer.valueOf(r0_Article.comment_count);
        r1_a.pointAndCount.setText(String.format(r2_String, r3_ObjectA));
        r2_String = r0_Article.state;
        a(r1_a, r0_Article, r2_String);
        a(r1_a, r2_String);
        a(r1_a.location, r0_Article.location);
        return r8_View;
    }

    public a initHolderView(View r2_View, a r3_a) {
        r3_a.content = (TextView) r2_View.findViewById(R.id.content);
        r3_a.sendStateImage = (ImageView) r2_View.findViewById(R.id.image);
        r3_a.sendStateContent = (TextView) r2_View.findViewById(R.id.sendStateContent);
        r3_a.sendDate = (TextView) r2_View.findViewById(R.id.sendDate);
        r3_a.pointAndCount = (TextView) r2_View.findViewById(R.id.points_and_comments_count);
        r3_a.location = (TextView) r2_View.findViewById(R.id.location);
        return r3_a;
    }
}
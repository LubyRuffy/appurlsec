package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.Article;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DateUtil;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.ImageFetcher;

public class MyContentsAdapter extends DefaultAdapter {
    public static final String PENDING = "pending";
    public static final String PRIVATE = "private";
    public static final String PUBLISH = "publish";
    public static final String REPORTED = "reported";
    public static final String SPAM = "spam";
    private ImageFetcher e;

    class a {
        public TextView content;
        public TextView sendDate;
        public TextView sendStateContent;
        public ImageView sendStateImage;

        a() {
        }
    }

    public MyContentsAdapter(Activity r3_Activity, ListView r4_ListView, ArrayList<Object> r5_ArrayList_Object) {
        super(r5_ArrayList_Object, r3_Activity);
        this.a = r4_ListView;
        this.e = QsbkApp.getInstance().getImageWorker(this.c);
    }

    public MyContentsAdapter(ArrayList<Object> r1_ArrayList_Object, Activity r2_Activity) {
        super(r1_ArrayList_Object, r2_Activity);
    }

    public View getView(int r12i, View r13_View, ViewGroup r14_ViewGroup) {
        a r1_a;
        Drawable r0_Drawable;
        if (r13_View == null) {
            r13_View = ((LayoutInflater) this.c.getSystemService("layout_inflater")).inflate(R.layout.listitem_mycontent_row, null);
            a r0_a = initHolderView(r13_View, new a());
            r13_View.setTag(r0_a);
            r1_a = r0_a;
        } else {
            r1_a = (a) r13_View.getTag();
        }
        Article r0_Article = (Article) getItem(r12i);
        CharSequence r2_CharSequence = r0_Article.content.trim();
        String r3_String = r0_Article.id;
        if (TextUtils.isEmpty(r2_CharSequence) || r2_CharSequence.equals("null")) {
            r13_View.findViewById(R.id.textmore).setVisibility(Base64.DONT_BREAK_LINES);
            r1_a.content.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r1_a.content.setVisibility(0);
            r1_a.content.setTransformationMethod(new MobileTransformationMethod());
            if (r2_CharSequence.length() > 40) {
                r2_CharSequence = r2_CharSequence.substring(0, 40);
                r13_View.findViewById(R.id.textmore).setVisibility(0);
            } else {
                r13_View.findViewById(R.id.textmore).setVisibility(Base64.DONT_BREAK_LINES);
            }
            r1_a.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            r1_a.content.setText(r2_CharSequence);
        }
        r1_a.sendDate.setText(DateUtil.Get_DiffDate_Info(this.c, r0_Article.created_at, 0).toString());
        String r2_String = r0_Article.state;
        String r0_String = r0_Article.image;
        if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
            r1_a.sendStateImage.setVisibility(Base64.DONT_BREAK_LINES);
        } else if (r2_String.equals(PENDING)) {
            r1_a.sendStateImage.setImageResource(R.drawable.thumb_pic);
        } else {
            r1_a.sendStateImage.setVisibility(0);
            String r4_String = Constants.CONTENT_IMAGE_URL;
            Object[] r5_ObjectA = new Object[4];
            r5_ObjectA[0] = Integer.valueOf(Integer.valueOf(r3_String).intValue() / 10000);
            r5_ObjectA[1] = r3_String;
            r5_ObjectA[2] = "small";
            r5_ObjectA[3] = r0_String;
            r0_String = String.format(r4_String, r5_ObjectA);
            DebugUtil.debug("\u56fe\u7247\u52a0\u8f7d\u5730\u5740\uff1a" + r0_String);
            this.e.loadImage(r0_String, r1_a.sendStateImage);
        }
        if (r2_String.equals(PUBLISH)) {
            r1_a.sendStateContent.setText("\u5df2\u53d1\u8868");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.icon_color4);
        } else if (r2_String.equals(PENDING)) {
            r1_a.sendStateContent.setText("\u5f85\u5ba1\u6838");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.icon_color1);
        } else if (r2_String.equals(SPAM) || r2_String.equals(PRIVATE)) {
            r1_a.sendStateContent.setText("\u672a\u901a\u8fc7\u5ba1\u6838");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.icon_color2);
        } else if (r2_String.equals(REPORTED)) {
            r1_a.sendStateContent.setText("\u88ab\u4e3e\u62a5");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.icon_color3);
        } else {
            r1_a.sendStateContent.setText("\u706b\u661f\u8d44\u6599\u5907\u4efd\u4e2d");
            r0_Drawable = this.c.getResources().getDrawable(R.drawable.icon_color3);
        }
        r1_a.sendStateContent.setCompoundDrawablesWithIntrinsicBounds(r0_Drawable, null, null, null);
        if (UIHelper.isNightTheme()) {
            r1_a.sendStateContent.setTextColor(this.c.getResources().getColor(R.color.tag_night));
        } else {
            r1_a.sendStateContent.setTextColor(this.c.getResources().getColor(R.color.tag));
        }
        return r13_View;
    }

    public a initHolderView(View r2_View, a r3_a) {
        r3_a.content = (TextView) r2_View.findViewById(R.id.content);
        r3_a.sendStateImage = (ImageView) r2_View.findViewById(R.id.image);
        r3_a.sendStateContent = (TextView) r2_View.findViewById(R.id.sendStateContent);
        r3_a.sendDate = (TextView) r2_View.findViewById(R.id.sendDate);
        return r3_a;
    }
}
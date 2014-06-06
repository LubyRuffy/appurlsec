package qsbk.app.activity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.cordova.Globalization;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.model.AuditArticle;
import qsbk.app.utils.Base64;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.utils.audit.Rotate3dAnimation;
import qsbk.app.utils.audit.ScrollViewTouchListener;
import qsbk.app.utils.audit.SimpleImageLoader;
import qsbk.app.utils.image.Utils;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;
import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class AuditNativeActivity extends StatFragmentActivity implements OnClickListener, AnimationListener {
    private static final String ae;
    private static final String n;
    private static final String[] y;
    private List<View> A;
    private SimpleImageLoader B;
    private Drawable C;
    private Drawable D;
    private AnimationSet E;
    private AnimationSet F;
    private AnimationSet G;
    private AnimationSet H;
    private AnimationSet I;
    private AnimationSet J;
    private AnimationSet K;
    private AnimationSet L;
    private Animation M;
    private Animation N;
    private View O;
    private View P;
    private View Q;
    private View R;
    private ViewPager S;
    private RelativeLayout T;
    private ScrollViewTouchListener U;
    private boolean V;
    private View W;
    private View X;
    private AtomicBoolean Y;
    private int Z;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private String af;
    private int ag;
    private List<AuditArticle> ah;
    private List<AuditArticle> ai;
    private final b aj;
    private Runnable ak;
    private Handler al;
    private final Runnable am;
    private final Runnable an;
    private final Runnable ao;
    private final Runnable ap;
    private final Runnable aq;
    private final Runnable ar;
    private final Runnable as;
    private boolean o;
    private ImageView p;
    private TextView q;
    private AnimationDrawable r;
    private View s;
    private View t;
    private View u;
    private View v;
    private TextView w;
    private TextView x;
    private FrameLayout z;

    private class a extends PagerAdapter implements OnPageChangeListener {
        List<View> a;
        ViewPager b;
        TextView c;
        TextView d;
        ViewGroup e;

        a(List<View> r3_List_View, ViewPager r4_ViewPager, TextView r5_TextView, TextView r6_TextView, ViewGroup r7_ViewGroup) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.a = r3_List_View;
            this.b = r4_ViewPager;
            this.c = r5_TextView;
            this.d = r6_TextView;
            this.e = r7_ViewGroup;
        }

        public void destroyItem(ViewGroup r2_ViewGroup, int r3i, Object r4_Object) {
            r2_ViewGroup.removeView((View) this.a.get(r3i));
        }

        public int getCount() {
            return this.a == null ? 0 : this.a.size();
        }

        public int getItemPosition(Object r2_Object) {
            return RequestListener.DEFAULT_LOADED_SIZE;
        }

        public Object instantiateItem(ViewGroup r2_ViewGroup, int r3i) {
            r2_ViewGroup.addView((View) this.a.get(r3i));
            return this.a.get(r3i);
        }

        public boolean isViewFromObject(View r2_View, Object r3_Object) {
            return r2_View == r3_Object;
        }

        public void onPageScrollStateChanged(int r1i) {
        }

        public void onPageScrolled(int r1i, float r2f, int r3i) {
        }

        public void onPageSelected(int r5i) {
            if (r5i == 0) {
                this.d.setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new r(this, r5i), 100);
            }
            this.c.setText(y[r5i]);
        }
    }

    class b {
        int a;
        LinkedList<View> b;

        public b(AuditNativeActivity r2_AuditNativeActivity) {
            this(3);
        }

        b(int r5i) {
            this.a = 1;
            if (r5i < 1) {
                throw new IllegalArgumentException("Max Size " + r5i + " must be positive.");
            } else {
                this.b = new LinkedList();
                this.a = r5i;
            }
        }

        public View add(View r5_View) {
            View r0_View = null;
            synchronized (this.b) {
                if (this.b.size() >= this.a) {
                    r0_View = (View) this.b.removeFirst();
                }
                this.b.addLast(r5_View);
            }
            return r0_View;
        }

        public void clear() {
            if (this.b != null) {
                this.b.clear();
            }
        }

        public List getAll() {
            return this.b;
        }
    }

    static {
        n = AuditNativeActivity.class.getName();
        String[] r0_StringA = new String[2];
        r0_StringA[0] = "\u5ba1\u6838\u6761\u4f8b";
        r0_StringA[1] = "\u5ba1\u6838";
        y = r0_StringA;
        ae = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
    }

    public AuditNativeActivity() {
        this.o = false;
        this.B = SimpleImageLoader.getInstance();
        this.V = true;
        this.W = null;
        this.X = null;
        this.Y = new AtomicBoolean(false);
        this.af = RContactStorage.PRIMARY_KEY;
        this.ag = 0;
        this.aj = new b(this);
        this.ak = new o(this);
        this.al = new p(this, Looper.getMainLooper());
        this.am = new c(this);
        this.an = new d(this);
        this.ao = new e(this);
        this.ap = new f(this);
        this.aq = new g(this);
        this.ar = new h(this);
        this.as = new i(this);
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

    private void a(int r11i) {
        if (this.A != null) {
            this.A.clear();
            this.A = null;
        }
        this.A = new ArrayList(r11i);
        LayoutInflater r7_LayoutInflater = LayoutInflater.from(this);
        int r6i = 0;
        while (r6i < r11i) {
            View r0_View = r7_LayoutInflater.inflate(R.layout.layout_review_content_item, null);
            r0_View.findViewById(R.id.scrollview).setOnTouchListener(this.U);
            this.A.add(r0_View);
            a((AuditArticle) this.ah.get(r6i), (ProgressBar) r0_View.findViewById(R.id.progressbar), (ImageView) r0_View.findViewById(R.id.content_img), (TextView) r0_View.findViewById(R.id.tagContent), (TextView) r0_View.findViewById(R.id.content_txt), r6i);
            r6i++;
        }
    }

    private void a(int r3i, String r4_String) {
        if (this.al != null) {
            Message r0_Message = new Message();
            r0_Message.what = r3i;
            r0_Message.obj = r4_String;
            this.al.sendMessage(r0_Message);
        }
    }

    private void a(View r5_View) {
        if (r5_View != null) {
            Drawable r1_Drawable = ((ImageView) r5_View.findViewById(R.id.content_img)).getDrawable();
            View r0_View;
            if (r1_Drawable == null || r1_Drawable == this.C || r1_Drawable == this.D) {
                r0_View = r5_View.findViewById(R.id.scrollview);
                if (r0_View == null) {
                    r0_View.setOnTouchListener(null);
                }
            } else {
                Bitmap r0_Bitmap = ((BitmapDrawable) r1_Drawable).getBitmap();
                if (r0_Bitmap == null || r0_Bitmap.isRecycled()) {
                    r1_Drawable.setCallback(null);
                } else {
                    r0_Bitmap.recycle();
                    r1_Drawable.setCallback(null);
                }
                r0_View = r5_View.findViewById(R.id.scrollview);
                if (r0_View == null) {
                } else {
                    r0_View.setOnTouchListener(null);
                }
            }
        }
    }

    private synchronized void a(View r3_View, Animation r4_Animation) {
        if (r4_Animation == this.H) {
            n();
        }
        if (r3_View == null) {
            r3_View = this.X;
        }
        View r0_View;
        Object r0_Object;
        if (r3_View == null || r4_Animation == null || r3_View.getVisibility() == 8) {
            this.z.removeAllViews();
            if (this.X == null) {
                r0_View = this.X.findViewById(R.id.content_img);
                if (r0_View == null) {
                    r0_Object = r0_View.getTag();
                    if (r0_Object == null || (!r0_Object instanceof String)) {
                        r0_View = this.aj.add(this.X);
                    } else {
                        this.B.cancel((String) r0_Object);
                    }
                }
                r0_View = this.aj.add(this.X);
                if (r0_View == null) {
                    a(r0_View);
                }
            }
            this.z.addView(this.W);
            this.W.startAnimation(this.J);
            if (this.ag == XListViewHeader.STATE_REFRESHING) {
                b("preLoad");
            }
            i();
            this.ag++;
        } else {
            r3_View.clearAnimation();
            r3_View.startAnimation(r4_Animation);
            this.z.removeAllViews();
            if (this.X == null) {
                this.z.addView(this.W);
                this.W.startAnimation(this.J);
                if (this.ag == XListViewHeader.STATE_REFRESHING) {
                    i();
                    this.ag++;
                } else {
                    b("preLoad");
                    i();
                    this.ag++;
                }
            } else {
                r0_View = this.X.findViewById(R.id.content_img);
                if (r0_View == null) {
                    r0_View = this.aj.add(this.X);
                    if (r0_View == null) {
                        this.z.addView(this.W);
                        this.W.startAnimation(this.J);
                        if (this.ag == XListViewHeader.STATE_REFRESHING) {
                            b("preLoad");
                        }
                        i();
                        this.ag++;
                    } else {
                        a(r0_View);
                        this.z.addView(this.W);
                        this.W.startAnimation(this.J);
                        if (this.ag == XListViewHeader.STATE_REFRESHING) {
                            i();
                            this.ag++;
                        } else {
                            b("preLoad");
                            i();
                            this.ag++;
                        }
                    }
                } else {
                    r0_Object = r0_View.getTag();
                    if (r0_Object == null || r0_Object instanceof String) {
                        r0_View = this.aj.add(this.X);
                        if (r0_View == null) {
                            a(r0_View);
                        }
                        this.z.addView(this.W);
                        this.W.startAnimation(this.J);
                        if (this.ag == XListViewHeader.STATE_REFRESHING) {
                            b("preLoad");
                        }
                        i();
                        this.ag++;
                    } else {
                        this.B.cancel((String) r0_Object);
                        r0_View = this.aj.add(this.X);
                        if (r0_View == null) {
                            this.z.addView(this.W);
                            this.W.startAnimation(this.J);
                            if (this.ag == XListViewHeader.STATE_REFRESHING) {
                                i();
                                this.ag++;
                            } else {
                                b("preLoad");
                                i();
                                this.ag++;
                            }
                        } else {
                            a(r0_View);
                            this.z.addView(this.W);
                            this.W.startAnimation(this.J);
                            if (this.ag == XListViewHeader.STATE_REFRESHING) {
                                b("preLoad");
                            }
                            i();
                            this.ag++;
                        }
                    }
                }
            }
        }
    }

    private void a(String r10_String, ImageView r11_ImageView, ProgressBar r12_ProgressBar, int r13i) {
        if (TextUtils.isEmpty(r10_String)) {
            r11_ImageView.setVisibility(Base64.DONT_BREAK_LINES);
            if (r12_ProgressBar != null) {
                r12_ProgressBar.setVisibility(Base64.DONT_BREAK_LINES);
            }
        } else {
            DisplayMetrics r0_DisplayMetrics = getResources().getDisplayMetrics();
            this.B.loadImage(r11_ImageView, r12_ProgressBar, r10_String, this.C, this.D, r0_DisplayMetrics.widthPixels - 20, r0_DisplayMetrics.heightPixels * 2, null);
            r11_ImageView.setTag(r10_String);
        }
    }

    private void a(String r3_String, TextView r4_TextView) {
        if (r3_String == null || r3_String.trim().length() == 0 || "null".equals(r3_String.trim())) {
            r4_TextView.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r4_TextView.setVisibility(0);
            r4_TextView.setText(r3_String.trim());
        }
    }

    private void a(AuditArticle r3_AuditArticle, ProgressBar r4_ProgressBar, ImageView r5_ImageView, TextView r6_TextView, TextView r7_TextView, int r8i) {
        a(r3_AuditArticle.tag, r6_TextView);
        a(r3_AuditArticle.content, r7_TextView);
        a(TextUtils.isEmpty(r3_AuditArticle.image) ? RContactStorage.PRIMARY_KEY : a(r3_AuditArticle.id, r3_AuditArticle.image), r5_ImageView, r4_ProgressBar, r8i);
    }

    private boolean a(View r6_View, int r7i) {
        int r1i = 0;
        if (r6_View != null) {
            View r3_View;
            Animation r2_Animation;
            if (r7i == 4) {
                r3_View = r6_View.findViewById(R.id.review_seal_no_pass);
                r2_Animation = this.L;
            } else if (r7i == 3) {
                r3_View = r6_View.findViewById(R.id.review_seal_pass);
                r2_Animation = this.K;
            } else {
                r2_Animation = null;
                r3_View = null;
            }
            if (r3_View == null || r2_Animation == null) {
            } else {
                r3_View.setVisibility(r1i);
                r3_View.clearAnimation();
                r3_View.startAnimation(r2_Animation);
                this.al.postDelayed(new m(this, r6_View), 50);
                return true;
            }
        }
        return false;
    }

    private synchronized void b(int r6i) {
        if (this.ah.isEmpty()) {
            this.z.removeAllViews();
            b(null);
        } else if (this.ag == this.ah.size()) {
            this.ag = 0;
            this.ah.clear();
            if (this.W != null) {
                switch (r6i) {
                    case XListViewHeader.STATE_READY:
                        this.W.clearAnimation();
                        this.W.startAnimation(this.H);
                        b(null);
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        this.W.clearAnimation();
                        this.W.startAnimation(this.H);
                        b(null);
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        this.W.clearAnimation();
                        this.K.setDuration(this.K.getDuration() + 1);
                        a(this.W, (int)XListViewFooter.STATE_NOMORE);
                        break;
                    case XListViewFooter.STATE_NODATA:
                        this.W.clearAnimation();
                        this.L.setDuration(this.L.getDuration() + 1);
                        a(this.W, (int)XListViewFooter.STATE_NODATA);
                        break;
                }
            }
        } else {
            c(r6i);
        }
    }

    private synchronized void b(String r3_String) {
        boolean r0z = "preLoad".equalsIgnoreCase(r3_String);
        if (this.ai == null || this.ai.size() <= 0 || r0z) {
            Task r1_Task = new n(this, r0z);
            if (!r0z) {
                k();
            }
            TaskExecutor.getInstance().addTask(r1_Task);
        } else {
            this.ah.clear();
            this.ah.addAll(this.ai);
            this.ai.clear();
            l();
        }
    }

    private synchronized void c(int r6i) {
        int r1i = 1;
        synchronized (this) {
            Animation r0_Animation;
            this.X = this.W;
            this.W = (View) this.A.get(0);
            this.A.remove(this.W);
            View r4_View = this.X;
            switch (r6i) {
                case ProfileHeaderListView.INVALID_TAB_ID:
                    r0_Animation = null;
                    if (r1i == 0) {
                        a(r4_View, r0_Animation);
                    }
                    break;
                case XListViewHeader.STATE_NORMAL:
                    r0_Animation = null;
                    if (r1i == 0) {
                    } else {
                        a(r4_View, r0_Animation);
                    }
                    break;
                case XListViewHeader.STATE_READY:
                    r0_Animation = this.H;
                    if (r1i == 0) {
                        a(r4_View, r0_Animation);
                    }
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    r0_Animation = this.H;
                    if (r1i == 0) {
                    } else {
                        a(r4_View, r0_Animation);
                    }
                    break;
                case XListViewFooter.STATE_NOMORE:
                    if (a(r4_View, r6i)) {
                        r1i = 0;
                        r0_Animation = null;
                        if (r1i == 0) {
                        } else {
                            a(r4_View, r0_Animation);
                        }
                    } else {
                        r0_Animation = null;
                        if (r1i == 0) {
                            a(r4_View, r0_Animation);
                        }
                    }
                    break;
                case XListViewFooter.STATE_NODATA:
                    r1i = a(r4_View, r6i) ? 0 : 1;
                    r0_Animation = null;
                    if (r1i == 0) {
                        a(r4_View, r0_Animation);
                    }
                    break;
            }
            r0_Animation = null;
            if (r1i == 0) {
            } else {
                a(r4_View, r0_Animation);
            }
        }
    }

    private void c(String r2_String) {
        this.o = true;
        a(-1, r2_String);
    }

    private void d(int r3i) {
        TaskExecutor.getInstance().addTask(new q(this, r3i));
        switch (r3i) {
            case -100:
                b((int)XListViewFooter.STATE_NODATA);
                break;
            case XListViewHeader.STATE_READY:
                b((int)XListViewFooter.STATE_NOMORE);
                break;
        }
    }

    private void e() {
        this.R = findViewById(R.id.loading_content);
        this.p = (ImageView) findViewById(R.id.loading);
        this.q = (TextView) findViewById(R.id.loading_text);
        this.r = (AnimationDrawable) this.p.getDrawable();
        this.t = View.inflate(this, R.layout.layout_review_content, null);
        this.P = this.t.findViewById(R.id.bottom_left_container);
        this.Q = this.t.findViewById(R.id.bottom_right_container);
        this.O = this.t.findViewById(R.id.bottom_layout);
        this.z = (FrameLayout) this.t.findViewById(R.id.scroll_view_container);
        this.s = View.inflate(this, R.layout.layout_review_rules, null);
        this.u = findViewById(R.id.top_bar_left_container);
        this.v = findViewById(R.id.next);
        this.w = (TextView) findViewById(R.id.title);
        this.x = (TextView) findViewById(R.id.tips);
        this.T = (RelativeLayout) findViewById(R.id.center_container);
        this.S = (ViewPager) findViewById(R.id.pager);
        List r2_List = new ArrayList(2);
        r2_List.add(this.s);
        r2_List.add(this.t);
        Object r0_Object = new a(r2_List, this.S, this.w, this.x, this.T);
        this.S.setAdapter(r0_Object);
        this.S.setOnPageChangeListener(r0_Object);
    }

    private void f() {
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.R.setClickable(false);
        this.U = new ScrollViewTouchListener(this, new b(this));
    }

    private void h() {
        int r2i = Base64.DONT_BREAK_LINES;
        if (this.S.getChildCount() == 0 || this.S.getCurrentItem() == 1) {
            this.S.setVisibility(r2i);
            this.T.setVisibility(r2i);
            this.R.setVisibility(0);
            this.q.setText("\u52aa\u529b\u52a0\u8f7d\u4e2d...");
            this.p.postDelayed(new j(this), 200);
            if (this.o) {
                this.p.postDelayed(new k(this), 1700);
            }
        }
    }

    private void i() {
        if (this.R.getVisibility() == 0) {
            if (this.S.getChildCount() == 0 || this.S.getCurrentItem() == 1) {
                this.S.setVisibility(Base64.DONT_BREAK_LINES);
                this.T.setVisibility(0);
            } else {
                this.S.setVisibility(0);
                this.T.setVisibility(Base64.DONT_BREAK_LINES);
            }
            this.R.setVisibility(Base64.DONT_BREAK_LINES);
            this.r.stop();
            this.r.setLevel(0);
        }
    }

    private void j() {
        this.ah = new ArrayList();
        this.ai = new ArrayList();
        this.C = getResources().getDrawable(R.color.review_img_default_background);
        this.D = getResources().getDrawable(R.color.review_img_failed);
        e();
        f();
        this.al.postDelayed(new l(this), 200);
    }

    private void k() {
        this.Y.set(true);
        a(0, null);
    }

    private void l() {
        this.Y.set(false);
        a(1, null);
    }

    private void m() {
        this.o = false;
        this.r.stop();
        this.r.setLevel(0);
        this.q.setText(Html.fromHtml("\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e<font color=#000000>\u70b9\u6211</font>\u91cd\u8bd5..."));
        this.R.setClickable(true);
    }

    private void n() {
        this.P.setClickable(false);
        this.Q.setClickable(false);
        this.O.clearAnimation();
        this.O.startAnimation(this.E);
        this.O.setVisibility(XListViewFooter.STATE_NODATA);
    }

    private void o() {
        this.F = new AnimationSet(true);
        this.F.setDuration(300);
        this.F.setAnimationListener(this);
        this.E = new AnimationSet(true);
        this.E.setDuration(300);
        this.E.setAnimationListener(this);
        this.I = new AnimationSet(true);
        this.I.setDuration(400);
        this.I.setAnimationListener(this);
        this.H = new AnimationSet(true);
        this.H.setDuration(400);
        this.J = new AnimationSet(true);
        this.J.setDuration(400);
        this.J.setAnimationListener(this);
        this.G = new AnimationSet(true);
        this.G.setDuration(400);
        this.G.setAnimationListener(this);
        this.K = new AnimationSet(true);
        this.K.setDuration(166);
        this.L = new AnimationSet(true);
        this.L.setDuration(166);
        this.M = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.M.setDuration(166);
        this.M.setAnimationListener(this);
        this.N = new ScaleAnimation(1.0f, 0.98f, 1.0f, 0.98f, 1, 0.5f, 1, 0.5f);
        this.N.setDuration(166);
        this.L.setAnimationListener(this);
        Animation r10_Animation = new AlphaAnimation(1.0f, 0.0f);
        Animation r11_Animation = new AlphaAnimation(0.0f, 1.0f);
        Animation r9_Animation = new AlphaAnimation(0.08f, 1.0f);
        Animation r0_Animation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.K.addAnimation(r9_Animation);
        this.K.addAnimation(r0_Animation);
        this.K.setAnimationListener(this);
        this.L.addAnimation(r9_Animation);
        this.L.addAnimation(r0_Animation);
        this.L.setAnimationListener(this);
        int r12i = getResources().getDisplayMetrics().widthPixels;
        new Rotate3dAnimation(0.0f, -24.0f, (float) (r12i / 2), (float) (this.aa / 2), 310.0f, true, Rotate3dAnimation.ROTATE_Y).setDuration(500);
        r0_Animation = new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f);
        r0_Animation.setDuration(500);
        Animation r13_Animation = new AlphaAnimation(0.9f, 0.0f);
        this.H.addAnimation(new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f, 1, 0.5f, 1, 0.5f));
        this.H.addAnimation(r13_Animation);
        this.H.addAnimation(r0_Animation);
        Rotate3dAnimation r0_Rotate3dAnimation = new Rotate3dAnimation(-24.0f, 0.0f, (float) (r12i / 2), (float) (this.aa / 2), 310.0f, false, Rotate3dAnimation.ROTATE_Y);
        r0_Animation = new TranslateAnimation(1, 0.8f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        Animation r12_Animation = new AlphaAnimation(0.1f, 1.0f);
        this.J.addAnimation(new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, 1, 0.5f, 1, 0.5f));
        this.J.addAnimation(r12_Animation);
        this.J.addAnimation(r0_Animation);
        this.G.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f));
        this.G.addAnimation(r10_Animation);
        r0_Animation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.I.addAnimation(r0_Animation);
        this.I.addAnimation(r10_Animation);
        this.F.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f));
        this.F.addAnimation(r11_Animation);
        r0_Animation.setFillAfter(true);
        r10_Animation.setFillAfter(true);
        this.E.addAnimation(r0_Animation);
        this.E.addAnimation(r10_Animation);
    }

    public void finish() {
        Iterator r2_Iterator;
        super.finish();
        if (this.B != null) {
            this.B.cancelAll();
        }
        if (this.A != null) {
            synchronized (this.A) {
                r2_Iterator = this.A.iterator();
                while (r2_Iterator.hasNext()) {
                    a((View) r2_Iterator.next());
                }
                this.A.clear();
            }
        }
        if (this.W != null) {
            a(this.W);
        }
        if (this.X != null) {
            a(this.X);
        }
        List r1_List = this.aj.getAll();
        if (r1_List == null || r1_List.isEmpty()) {
            overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        } else {
            synchronized (r1_List) {
                r2_Iterator = r1_List.iterator();
                while (r2_Iterator.hasNext()) {
                    a((View) r2_Iterator.next());
                }
            }
            overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        }
    }

    public void onAnimationEnd(Animation r13_Animation) {
        long r0j;
        if (r13_Animation == this.K) {
            r0j = this.K.getDuration();
            if (r0j % 2 != 0) {
                this.K.setDuration(r0j - 1);
                this.al.postDelayed(this.ao, 200);
            } else {
                this.al.postDelayed(this.am, 200);
            }
        } else if (r13_Animation == this.L) {
            r0j = this.L.getDuration();
            if (r0j % 2 != 0) {
                this.L.setDuration(r0j - 1);
                this.al.postDelayed(this.ap, 200);
            } else {
                this.al.postDelayed(this.an, 200);
            }
        } else if (r13_Animation == this.I) {
            r0j = this.I.getDuration();
            if (r0j % 2 != 0) {
                this.I.setDuration(r0j - 1);
            }
        } else if (r13_Animation == this.G) {
            r0j = this.G.getDuration();
            if (r0j % 2 != 0) {
                this.G.setDuration(r0j - 1);
            }
        } else if (r13_Animation == this.J) {
            this.V = true;
        } else if (r13_Animation == this.F) {
            this.O.setVisibility(0);
            this.P.setClickable(true);
            this.Q.setClickable(true);
            this.v.setClickable(true);
        } else if (r13_Animation == this.N || r13_Animation != this.M) {
        }
    }

    public void onAnimationRepeat(Animation r1_Animation) {
    }

    public void onAnimationStart(Animation r5_Animation) {
        if (r5_Animation == this.J) {
            this.al.postDelayed(this.aq, 400);
        }
        if (r5_Animation == this.E) {
            if (r5_Animation != this.F) {
            }
        } else if (r5_Animation != this.F) {
        }
    }

    public void onClick(View r6_View) {
        switch (r6_View.getId()) {
            case R.id.loading_content:
                if (!this.o) {
                    b("retry");
                    r6_View.setClickable(false);
                }
                break;
            case R.id.next:
                if (this.T.getVisibility() == 0) {
                    r6_View.setClickable(false);
                    b(1);
                } else {
                    this.S.setCurrentItem(1, true);
                }
                break;
            case R.id.bottom_left_container:
                n();
                this.al.postDelayed(this.ar, 200);
                break;
            case R.id.bottom_right_container:
                n();
                this.al.postDelayed(this.as, 200);
                break;
            case R.id.top_bar_left_container:
                onBackPressed();
                break;
        }
    }

    public void onConfigurationChanged(Configuration r1_Configuration) {
        super.onConfigurationChanged(r1_Configuration);
    }

    public void onCreate(Bundle r4_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r4_Bundle);
        if (VERSION.SDK_INT >= 11) {
            getWindow().setFlags(Utils.IO_BUFFER_SIZE, Utils.IO_BUFFER_SIZE);
        }
        setContentView(R.layout.activity_audit_native);
        j();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
    }
}
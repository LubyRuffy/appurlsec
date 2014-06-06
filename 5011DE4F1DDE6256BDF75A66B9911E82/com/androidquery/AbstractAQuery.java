package com.androidquery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.androidquery.auth.AccountHandle;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.callback.ImageOptions;
import com.androidquery.callback.Transformer;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.WebImage;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class AbstractAQuery<T extends AbstractAQuery<T>> implements Constants {
    private static final Class<?>[] k;
    private static Class<?>[] l;
    private static Class<?>[] m;
    private static final Class<?>[] n;
    private static Class<?>[] o;
    private static final Class<?>[] p;
    private static Class<?>[] q;
    private static WeakHashMap<Dialog, Void> r;
    protected View a;
    protected Object b;
    protected AccountHandle c;
    private View d;
    private Activity e;
    private Context f;
    private Transformer g;
    private int h;
    private HttpHost i;
    private Constructor<T> j;

    static {
        Class[] r0_ClassA = new Class[1];
        r0_ClassA[0] = View.class;
        k = r0_ClassA;
        r0_ClassA = new Class[4];
        r0_ClassA[0] = AdapterView.class;
        r0_ClassA[1] = View.class;
        r0_ClassA[2] = Integer.TYPE;
        r0_ClassA[3] = Long.TYPE;
        l = r0_ClassA;
        r0_ClassA = new Class[2];
        r0_ClassA[0] = AbsListView.class;
        r0_ClassA[1] = Integer.TYPE;
        m = r0_ClassA;
        r0_ClassA = new Class[4];
        r0_ClassA[0] = CharSequence.class;
        r0_ClassA[1] = Integer.TYPE;
        r0_ClassA[2] = Integer.TYPE;
        r0_ClassA[3] = Integer.TYPE;
        n = r0_ClassA;
        r0_ClassA = new Class[2];
        r0_ClassA[0] = Integer.TYPE;
        r0_ClassA[1] = Integer.TYPE;
        o = r0_ClassA;
        r0_ClassA = new Class[1];
        r0_ClassA[0] = Integer.TYPE;
        p = r0_ClassA;
        r0_ClassA = new Class[2];
        r0_ClassA[0] = Integer.TYPE;
        r0_ClassA[1] = Paint.class;
        q = r0_ClassA;
        r = new WeakHashMap();
    }

    public AbstractAQuery(Activity r2_Activity) {
        this.h = 0;
        this.e = r2_Activity;
    }

    public AbstractAQuery(Activity r2_Activity, View r3_View) {
        this.h = 0;
        this.d = r3_View;
        this.a = r3_View;
        this.e = r2_Activity;
    }

    public AbstractAQuery(Context r2_Context) {
        this.h = 0;
        this.f = r2_Context;
    }

    public AbstractAQuery(View r2_View) {
        this.h = 0;
        this.d = r2_View;
        this.a = r2_View;
    }

    private View a(int r3i) {
        if (this.d != null) {
            return this.d.findViewById(r3i);
        }
        if (this.e != null) {
            return this.e.findViewById(r3i);
        }
        return null;
    }

    private View a(String r4_String) {
        if (this.d != null) {
            return this.d.findViewWithTag(r4_String);
        }
        if (this.e != null) {
            View r0_View = ((ViewGroup) this.e.findViewById(16908290)).getChildAt(0);
            if (r0_View != null) {
                return r0_View.findViewWithTag(r4_String);
            }
        }
        return null;
    }

    private View a(int ... r4_intA) {
        View r1_View = a(r4_intA[0]);
        int r0i = 1;
        while (r0i < r4_intA.length && r1_View != null) {
            r1_View = r1_View.findViewById(r4_intA[r0i]);
            r0i++;
        }
        return r1_View;
    }

    private void a(boolean r4z, int r5i, boolean r6z) {
        if (this.a != null) {
            LayoutParams r0_LayoutParams = this.a.getLayoutParams();
            Context r1_Context = getContext();
            if (r5i <= 0 || (!r6z)) {
                if (!r4z) {
                    r0_LayoutParams.width = r5i;
                }
                this.a.setLayoutParams(r0_LayoutParams);
            } else {
                r5i = AQUtility.dip2pixel(r1_Context, (float) r5i);
                if (r4z) {
                    r0_LayoutParams.height = r5i;
                } else {
                    r0_LayoutParams.width = r5i;
                }
                this.a.setLayoutParams(r0_LayoutParams);
            }
            r0_LayoutParams.height = r5i;
            this.a.setLayoutParams(r0_LayoutParams);
        }
    }

    private Constructor<T> c() {
        if (this.j == null) {
            try {
                Class r0_Class = getClass();
                Class[] r1_ClassA = new Class[1];
                r1_ClassA[0] = View.class;
                this.j = r0_Class.getConstructor(r1_ClassA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.j;
    }

    private Common d() {
        AbsListView r0_AbsListView = (AbsListView) this.a;
        Common r1_Common = (Common) r0_AbsListView.getTag(Constants.TAG_SCROLL_LISTENER);
        if (r1_Common != null) {
            return r1_Common;
        }
        Object r1_Object = new Common();
        r0_AbsListView.setOnScrollListener(r1_Object);
        r0_AbsListView.setTag(Constants.TAG_SCROLL_LISTENER, r1_Object);
        AQUtility.debug((Object)"set scroll listenr");
        return r1_Object;
    }

    protected T a() {
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected T a(View r6_View) {
        /*
        r5_this = this;
        r1 = 0;
        r0 = r5.c();	 //Catch:{ Exception -> 0x0016 }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 //Catch:{ Exception -> 0x0016 }
        r3 = 0;
        r2[r3] = r6;	 //Catch:{ Exception -> 0x0016 }
        r0 = r0.newInstance(r2);	 //Catch:{ Exception -> 0x0016 }
        r0 = (com.androidquery.AbstractAQuery) r0;	 //Catch:{ Exception -> 0x0016 }
        r1 = r5.e;	 //Catch:{ Exception -> 0x001e }
        r0.e = r1;	 //Catch:{ Exception -> 0x001e }
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x001a:
        r1.printStackTrace();
        goto L_0x0015;
    L_0x001e:
        r1 = move-exception;
        goto L_0x001a;
        */

    }

    protected <K> T a(AbstractAjaxCallback<?, K> r3_AbstractAjaxCallback___K) {
        if (this.c != null) {
            r3_AbstractAjaxCallback___K.auth(this.c);
        }
        if (this.b != null) {
            r3_AbstractAjaxCallback___K.progress(this.b);
        }
        if (this.g != null) {
            r3_AbstractAjaxCallback___K.transformer(this.g);
        }
        r3_AbstractAjaxCallback___K.policy(this.h);
        if (this.i != null) {
            r3_AbstractAjaxCallback___K.proxy(this.i.getHostName(), this.i.getPort());
        }
        if (this.e != null) {
            r3_AbstractAjaxCallback___K.async(this.e);
        } else {
            r3_AbstractAjaxCallback___K.async(getContext());
        }
        b();
        return a();
    }

    protected T a(String r10_String, ImageOptions r11_ImageOptions, String r12_String) {
        if (this.a instanceof ImageView) {
            BitmapAjaxCallback.async(this.e, getContext(), (ImageView) this.a, r10_String, this.b, this.c, r11_ImageOptions, this.i, r12_String);
            b();
        }
        return a();
    }

    protected T a(String r20_String, boolean r21z, boolean r22z, int r23i, int r24i, Bitmap r25_Bitmap, int r26i, float r27f, int r28i, String r29_String) {
        if (this.a instanceof ImageView) {
            BitmapAjaxCallback.async(this.e, getContext(), (ImageView) this.a, r20_String, r21z, r22z, r23i, r24i, r25_Bitmap, r26i, r27f, Constants.RATIO_PRESERVE, this.b, this.c, this.h, r28i, this.i, r29_String);
            b();
        }
        return a();
    }

    public T adapter(Adapter r2_Adapter) {
        if (this.a instanceof AdapterView) {
            ((AdapterView) this.a).setAdapter(r2_Adapter);
        }
        return a();
    }

    public T adapter(ExpandableListAdapter r2_ExpandableListAdapter) {
        if (this.a instanceof ExpandableListView) {
            ((ExpandableListView) this.a).setAdapter(r2_ExpandableListAdapter);
        }
        return a();
    }

    public <K> T ajax(AjaxCallback<K> r2_AjaxCallback_K) {
        return a((AbstractAjaxCallback)r2_AjaxCallback_K);
    }

    public <K> T ajax(String r3_String, Class<K> r4_Class_K, long r5j, AjaxCallback<K> r7_AjaxCallback_K) {
        ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) r7_AjaxCallback_K.type(r4_Class_K)).url(r3_String)).fileCache(true)).expire(r5j);
        return ajax(r7_AjaxCallback_K);
    }

    public <K> T ajax(String r4_String, Class<K> r5_Class_K, long r6j, Object r8_Object, String r9_String) {
        AjaxCallback r1_AjaxCallback = new AjaxCallback();
        ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) r1_AjaxCallback.type(r5_Class_K)).weakHandler(r8_Object, r9_String)).fileCache(true)).expire(r6j);
        return ajax(r4_String, r5_Class_K, r1_AjaxCallback);
    }

    public <K> T ajax(String r2_String, Class<K> r3_Class_K, AjaxCallback<K> r4_AjaxCallback_K) {
        ((AjaxCallback) r4_AjaxCallback_K.type(r3_Class_K)).url(r2_String);
        return ajax(r4_AjaxCallback_K);
    }

    public <K> T ajax(String r3_String, Class<K> r4_Class_K, Object r5_Object, String r6_String) {
        AjaxCallback r1_AjaxCallback = new AjaxCallback();
        ((AjaxCallback) r1_AjaxCallback.type(r4_Class_K)).weakHandler(r5_Object, r6_String);
        return ajax(r3_String, r4_Class_K, r1_AjaxCallback);
    }

    public <K> T ajax(String r2_String, Map<String, ?> r3_Map_String__, Class<K> r4_Class_K, AjaxCallback<K> r5_AjaxCallback_K) {
        ((AjaxCallback) ((AjaxCallback) r5_AjaxCallback_K.type(r4_Class_K)).url(r2_String)).params(r3_Map_String__);
        return ajax(r5_AjaxCallback_K);
    }

    public <K> T ajax(String r3_String, Map<String, ?> r4_Map_String__, Class<K> r5_Class_K, Object r6_Object, String r7_String) {
        AjaxCallback r1_AjaxCallback = new AjaxCallback();
        ((AjaxCallback) r1_AjaxCallback.type(r5_Class_K)).weakHandler(r6_Object, r7_String);
        return ajax(r3_String, (Map)r4_Map_String__, (Class)r5_Class_K, r1_AjaxCallback);
    }

    public T ajaxCancel() {
        AjaxCallback.cancel();
        return a();
    }

    public T animate(int r2i) {
        return animate(r2i, null);
    }

    public T animate(int r2i, AnimationListener r3_AnimationListener) {
        Animation r0_Animation = AnimationUtils.loadAnimation(getContext(), r2i);
        r0_Animation.setAnimationListener(r3_AnimationListener);
        return animate(r0_Animation);
    }

    public T animate(Animation r2_Animation) {
        if (this.a == null || r2_Animation == null) {
            return a();
        }
        this.a.startAnimation(r2_Animation);
        return a();
    }

    public T auth(AccountHandle r2_AccountHandle) {
        this.c = r2_AccountHandle;
        return a();
    }

    protected void b() {
        this.c = null;
        this.b = null;
        this.g = null;
        this.h = 0;
        this.i = null;
    }

    public T background(int r3i) {
        if (this.a != null) {
            if (r3i != 0) {
                this.a.setBackgroundResource(r3i);
            } else {
                this.a.setBackgroundDrawable(null);
            }
        }
        return a();
    }

    public T backgroundColor(int r2i) {
        if (this.a != null) {
            this.a.setBackgroundColor(r2i);
        }
        return a();
    }

    public T backgroundColorId(int r3i) {
        if (this.a != null) {
            this.a.setBackgroundColor(getContext().getResources().getColor(r3i));
        }
        return a();
    }

    public T cache(String r8_String, long r9j) {
        return ajax(r8_String, byte[].class, r9j, null, null);
    }

    public T checked(boolean r2z) {
        if (this.a instanceof CompoundButton) {
            ((CompoundButton) this.a).setChecked(r2z);
        }
        return a();
    }

    public T clear() {
        if (this.a != null) {
            if (this.a instanceof ImageView) {
                ImageView r0_ImageView = (ImageView) this.a;
                r0_ImageView.setImageBitmap(null);
                r0_ImageView.setTag(Constants.TAG_URL, null);
            } else if (this.a instanceof WebView) {
                WebView r0_WebView = (WebView) this.a;
                r0_WebView.stopLoading();
                r0_WebView.clearView();
                r0_WebView.setTag(Constants.TAG_URL, null);
            } else if (this.a instanceof TextView) {
                ((TextView) this.a).setText(RContactStorage.PRIMARY_KEY);
            }
        }
        return a();
    }

    public T click() {
        if (this.a != null) {
            this.a.performClick();
        }
        return a();
    }

    public T clickable(boolean r2z) {
        if (this.a != null) {
            this.a.setClickable(r2z);
        }
        return a();
    }

    public T clicked(OnClickListener r2_OnClickListener) {
        if (this.a != null) {
            this.a.setOnClickListener(r2_OnClickListener);
        }
        return a();
    }

    public T clicked(Object r4_Object, String r5_String) {
        return clicked(new Common().forward(r4_Object, r5_String, true, k));
    }

    public T dataChanged() {
        if (this.a instanceof AdapterView) {
            Adapter r0_Adapter = ((AdapterView) this.a).getAdapter();
            if (r0_Adapter instanceof BaseAdapter) {
                ((BaseAdapter) r0_Adapter).notifyDataSetChanged();
            }
        }
        return a();
    }

    public <K> T delete(String r3_String, Class<K> r4_Class_K, AjaxCallback<K> r5_AjaxCallback_K) {
        ((AjaxCallback) ((AjaxCallback) r5_AjaxCallback_K.url(r3_String)).type(r4_Class_K)).method(XListViewHeader.STATE_REFRESHING);
        return ajax(r5_AjaxCallback_K);
    }

    public <K> T delete(String r2_String, Class<K> r3_Class_K, Object r4_Object, String r5_String) {
        AjaxCallback r0_AjaxCallback = new AjaxCallback();
        r0_AjaxCallback.weakHandler(r4_Object, r5_String);
        return delete(r2_String, r3_Class_K, r0_AjaxCallback);
    }

    public T dismiss() {
        Iterator r1_Iterator = r.keySet().iterator();
        while (r1_Iterator.hasNext()) {
            try {
                ((Dialog) r1_Iterator.next()).dismiss();
            } catch (Exception e) {
            }
            r1_Iterator.remove();
        }
        return a();
    }

    public T dismiss(Dialog r2_Dialog) {
        if (r2_Dialog != null) {
            try {
                r.remove(r2_Dialog);
                r2_Dialog.dismiss();
            } catch (Exception e) {
            }
        }
        return a();
    }

    public T download(String r3_String, File r4_File, AjaxCallback<File> r5_AjaxCallback_File) {
        ((AjaxCallback) ((AjaxCallback) r5_AjaxCallback_File.url(r3_String)).type(File.class)).targetFile(r4_File);
        return ajax(r5_AjaxCallback_File);
    }

    public T download(String r2_String, File r3_File, Object r4_Object, String r5_String) {
        AjaxCallback r0_AjaxCallback = new AjaxCallback();
        r0_AjaxCallback.weakHandler(r4_Object, r5_String);
        return download(r2_String, r3_File, r0_AjaxCallback);
    }

    public T enabled(boolean r2z) {
        if (this.a != null) {
            this.a.setEnabled(r2z);
        }
        return a();
    }

    public T expand(int r2i, boolean r3z) {
        if (this.a instanceof ExpandableListView) {
            ExpandableListView r0_ExpandableListView = (ExpandableListView) this.a;
            if (r3z) {
                r0_ExpandableListView.expandGroup(r2i);
            } else {
                r0_ExpandableListView.collapseGroup(r2i);
            }
        }
        return a();
    }

    public T expand(boolean r4z) {
        if (this.a instanceof ExpandableListView) {
            ExpandableListView r0_ExpandableListView = (ExpandableListView) this.a;
            ExpandableListAdapter r1_ExpandableListAdapter = r0_ExpandableListView.getExpandableListAdapter();
            if (r1_ExpandableListAdapter != null) {
                int r2i = r1_ExpandableListAdapter.getGroupCount();
                int r1i = 0;
                while (r1i < r2i) {
                    if (r4z) {
                        r0_ExpandableListView.expandGroup(r1i);
                    } else {
                        r0_ExpandableListView.collapseGroup(r1i);
                    }
                    r1i++;
                }
            }
        }
        return a();
    }

    public T find(int r2i) {
        return a(a(r2i));
    }

    public Button getButton() {
        return (Button) this.a;
    }

    public File getCachedFile(String r3_String) {
        File r0_File = AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(getContext(), 1), r3_String);
        return r0_File == null ? AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(getContext(), 0), r3_String) : r0_File;
    }

    public Bitmap getCachedImage(int r2i) {
        return BitmapAjaxCallback.getMemoryCached(getContext(), r2i);
    }

    public Bitmap getCachedImage(String r2_String) {
        return getCachedImage(r2_String, 0);
    }

    public Bitmap getCachedImage(String r5_String, int r6i) {
        Bitmap r0_Bitmap = BitmapAjaxCallback.getMemoryCached(r5_String, r6i);
        if (r0_Bitmap != null) {
            return r0_Bitmap;
        }
        File r1_File = getCachedFile(r5_String);
        return r1_File != null ? BitmapAjaxCallback.getResizedImage(r1_File.getAbsolutePath(), null, r6i, true, 0) : r0_Bitmap;
    }

    public CheckBox getCheckBox() {
        return (CheckBox) this.a;
    }

    public Context getContext() {
        if (this.e != null) {
            return this.e;
        }
        if (this.d != null) {
            return this.d.getContext();
        }
        return this.f;
    }

    public EditText getEditText() {
        return (EditText) this.a;
    }

    public Editable getEditable() {
        return this.a instanceof EditText ? ((EditText) this.a).getEditableText() : null;
    }

    public ExpandableListView getExpandableListView() {
        return (ExpandableListView) this.a;
    }

    public Gallery getGallery() {
        return (Gallery) this.a;
    }

    public GridView getGridView() {
        return (GridView) this.a;
    }

    public ImageView getImageView() {
        return (ImageView) this.a;
    }

    public ListView getListView() {
        return (ListView) this.a;
    }

    public ProgressBar getProgressBar() {
        return (ProgressBar) this.a;
    }

    public RatingBar getRatingBar() {
        return (RatingBar) this.a;
    }

    public SeekBar getSeekBar() {
        return (SeekBar) this.a;
    }

    public Object getSelectedItem() {
        return this.a instanceof AdapterView ? ((AdapterView) this.a).getSelectedItem() : null;
    }

    public int getSelectedItemPosition() {
        return this.a instanceof AdapterView ? ((AdapterView) this.a).getSelectedItemPosition() : -1;
    }

    public Spinner getSpinner() {
        return (Spinner) this.a;
    }

    public Object getTag() {
        return this.a != null ? this.a.getTag() : null;
    }

    public Object getTag(int r3i) {
        return this.a != null ? this.a.getTag(r3i) : null;
    }

    public CharSequence getText() {
        return this.a instanceof TextView ? ((TextView) this.a).getText() : null;
    }

    public TextView getTextView() {
        return (TextView) this.a;
    }

    public View getView() {
        return this.a;
    }

    public WebView getWebView() {
        return (WebView) this.a;
    }

    public T gone() {
        return visibility(Base64.DONT_BREAK_LINES);
    }

    public T hardwareAccelerated11() {
        if (this.e != null) {
            this.e.getWindow().setFlags(Constants.FLAG_HARDWARE_ACCELERATED, Constants.FLAG_HARDWARE_ACCELERATED);
        }
        return a();
    }

    public T height(int r3i) {
        a(false, r3i, true);
        return a();
    }

    public T height(int r2i, boolean r3z) {
        a(false, r2i, r3z);
        return a();
    }

    public T id(int r2i) {
        return id(a(r2i));
    }

    public T id(View r2_View) {
        this.a = r2_View;
        b();
        return a();
    }

    public T id(String r2_String) {
        return id(a(r2_String));
    }

    public T id(int ... r2_intA) {
        return id(a(r2_intA));
    }

    public T image(int r4i) {
        if (this.a instanceof ImageView) {
            ImageView r0_ImageView = (ImageView) this.a;
            r0_ImageView.setTag(Constants.TAG_URL, null);
            if (r4i == 0) {
                r0_ImageView.setImageBitmap(null);
            } else {
                r0_ImageView.setImageResource(r4i);
            }
        }
        return a();
    }

    public T image(Bitmap r4_Bitmap) {
        if (this.a instanceof ImageView) {
            ImageView r0_ImageView = (ImageView) this.a;
            r0_ImageView.setTag(Constants.TAG_URL, null);
            r0_ImageView.setImageBitmap(r4_Bitmap);
        }
        return a();
    }

    public T image(Bitmap r3_Bitmap, float r4f) {
        BitmapAjaxCallback r0_BitmapAjaxCallback = new BitmapAjaxCallback();
        r0_BitmapAjaxCallback.ratio(r4f).bitmap(r3_Bitmap);
        return image(r0_BitmapAjaxCallback);
    }

    public T image(Drawable r4_Drawable) {
        if (this.a instanceof ImageView) {
            ImageView r0_ImageView = (ImageView) this.a;
            r0_ImageView.setTag(Constants.TAG_URL, null);
            r0_ImageView.setImageDrawable(r4_Drawable);
        }
        return a();
    }

    public T image(BitmapAjaxCallback r2_BitmapAjaxCallback) {
        if (this.a instanceof ImageView) {
            r2_BitmapAjaxCallback.imageView((ImageView) this.a);
            a((AbstractAjaxCallback)r2_BitmapAjaxCallback);
        }
        return a();
    }

    public T image(File r3_File, int r4i) {
        return image(r3_File, true, r4i, null);
    }

    public T image(File r8_File, boolean r9z, int r10i, BitmapAjaxCallback r11_BitmapAjaxCallback) {
        BitmapAjaxCallback r6_BitmapAjaxCallback;
        r6_BitmapAjaxCallback = r11_BitmapAjaxCallback == null ? new BitmapAjaxCallback() : r11_BitmapAjaxCallback;
        r6_BitmapAjaxCallback.file(r8_File);
        String r1_String = null;
        if (r8_File != null) {
            r1_String = r8_File.getAbsolutePath();
        }
        return image(r1_String, r9z, true, r10i, 0, r6_BitmapAjaxCallback);
    }

    public T image(String r7_String) {
        return image(r7_String, true, true, 0, 0);
    }

    public T image(String r2_String, ImageOptions r3_ImageOptions) {
        return a(r2_String, r3_ImageOptions, null);
    }

    public T image(String r7_String, boolean r8z, boolean r9z) {
        return image(r7_String, r8z, r9z, 0, 0);
    }

    public T image(String r9_String, boolean r10z, boolean r11z, int r12i, int r13i) {
        return image(r9_String, r10z, r11z, r12i, r13i, null, 0);
    }

    public T image(String r10_String, boolean r11z, boolean r12z, int r13i, int r14i, Bitmap r15_Bitmap, int r16i) {
        return image(r10_String, r11z, r12z, r13i, r14i, r15_Bitmap, r16i, 0.0f);
    }

    public T image(String r12_String, boolean r13z, boolean r14z, int r15i, int r16i, Bitmap r17_Bitmap, int r18i, float r19f) {
        return a(r12_String, r13z, r14z, r15i, r16i, r17_Bitmap, r18i, r19f, 0, null);
    }

    public T image(String r2_String, boolean r3z, boolean r4z, int r5i, int r6i, BitmapAjaxCallback r7_BitmapAjaxCallback) {
        ((BitmapAjaxCallback) ((BitmapAjaxCallback) r7_BitmapAjaxCallback.targetWidth(r5i).fallback(r6i).url(r2_String)).memCache(r3z)).fileCache(r4z);
        return image(r7_BitmapAjaxCallback);
    }

    public View inflate(View r4_View, int r5i, ViewGroup r6_ViewGroup) {
        if (r4_View != null) {
            Integer r0_Integer = (Integer) r4_View.getTag(Constants.TAG_LAYOUT);
            if (r0_Integer != null && r0_Integer.intValue() == r5i) {
                return r4_View;
            }
        }
        r4_View = (this.e != null ? this.e.getLayoutInflater() : (LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(r5i, r6_ViewGroup, false);
        r4_View.setTag(Constants.TAG_LAYOUT, Integer.valueOf(r5i));
        return r4_View;
    }

    public T invalidate(String r2_String) {
        File r0_File = getCachedFile(r2_String);
        if (r0_File != null) {
            r0_File.delete();
        }
        return a();
    }

    public T invisible() {
        return visibility(XListViewFooter.STATE_NODATA);
    }

    public Object invoke(String r7_String, Class<?>[] r8_Class_A, Object ... r9_ObjectA) {
        View r0_View = this.a;
        if (r0_View == null) {
            r0_View = this.e;
        }
        return AQUtility.invokeHandler(r0_View, r7_String, false, false, r8_Class_A, r9_ObjectA);
    }

    public boolean isChecked() {
        return this.a instanceof CompoundButton ? ((CompoundButton) this.a).isChecked() : false;
    }

    public boolean isExist() {
        return this.a != null;
    }

    public T itemClicked(OnItemClickListener r2_OnItemClickListener) {
        if (this.a instanceof AdapterView) {
            ((AdapterView) this.a).setOnItemClickListener(r2_OnItemClickListener);
        }
        return a();
    }

    public T itemClicked(Object r4_Object, String r5_String) {
        return itemClicked(new Common().forward(r4_Object, r5_String, true, l));
    }

    public T itemSelected(OnItemSelectedListener r2_OnItemSelectedListener) {
        if (this.a instanceof AdapterView) {
            ((AdapterView) this.a).setOnItemSelectedListener(r2_OnItemSelectedListener);
        }
        return a();
    }

    public T itemSelected(Object r4_Object, String r5_String) {
        return itemSelected(new Common().forward(r4_Object, r5_String, true, l));
    }

    public T longClick() {
        if (this.a != null) {
            this.a.performLongClick();
        }
        return a();
    }

    public T longClicked(OnLongClickListener r2_OnLongClickListener) {
        if (this.a != null) {
            this.a.setOnLongClickListener(r2_OnLongClickListener);
        }
        return a();
    }

    public T longClicked(Object r4_Object, String r5_String) {
        return longClicked(new Common().forward(r4_Object, r5_String, true, k));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public File makeSharedFile(String r10_String, String r11_String) {
        /*
        r9_this = this;
        r0 = 0;
        r1 = r9.getCachedFile(r10);	 //Catch:{ Exception -> 0x0053 }
        if (r1 == 0) goto L_0x003d;
    L_0x0007:
        r2 = com.androidquery.util.AQUtility.getTempDir();	 //Catch:{ Exception -> 0x0053 }
        if (r2 == 0) goto L_0x003d;
    L_0x000d:
        r6 = new java.io.File;	 //Catch:{ Exception -> 0x0053 }
        r6.<init>(r2, r11);	 //Catch:{ Exception -> 0x0053 }
        r6.createNewFile();	 //Catch:{ Exception -> 0x004c }
        r7 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x004c }
        r7.<init>(r1);	 //Catch:{ Exception -> 0x004c }
        r8 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x004c }
        r8.<init>(r6);	 //Catch:{ Exception -> 0x004c }
        r0 = r7.getChannel();	 //Catch:{ Exception -> 0x004c }
        r5 = r8.getChannel();	 //Catch:{ Exception -> 0x004c }
        r1 = 0;
        r3 = r0.size();	 //Catch:{ all -> 0x003e }
        r0.transferTo(r1, r3, r5);	 //Catch:{ all -> 0x003e }
        com.androidquery.util.AQUtility.close(r7);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r8);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r0);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r5);	 //Catch:{ Exception -> 0x004c }
        r0 = r6;
    L_0x003d:
        return r0;
    L_0x003e:
        r1 = move-exception;
        com.androidquery.util.AQUtility.close(r7);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r8);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r0);	 //Catch:{ Exception -> 0x004c }
        com.androidquery.util.AQUtility.close(r5);	 //Catch:{ Exception -> 0x004c }
        throw r1;	 //Catch:{ Exception -> 0x004c }
    L_0x004c:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x004f:
        com.androidquery.util.AQUtility.debug(r1);
        goto L_0x003d;
    L_0x0053:
        r1 = move-exception;
        goto L_0x004f;
        */

    }

    public T margin(float r7f, float r8f, float r9f, float r10f) {
        if (this.a != null) {
            LayoutParams r1_LayoutParams = this.a.getLayoutParams();
            if (r1_LayoutParams instanceof MarginLayoutParams) {
                Context r0_Context = getContext();
                ((MarginLayoutParams) r1_LayoutParams).setMargins(AQUtility.dip2pixel(r0_Context, r7f), AQUtility.dip2pixel(r0_Context, r8f), AQUtility.dip2pixel(r0_Context, r9f), AQUtility.dip2pixel(r0_Context, r10f));
                this.a.setLayoutParams(r1_LayoutParams);
            }
        }
        return a();
    }

    public T overridePendingTransition5(int r8i, int r9i) {
        if (this.e != null) {
            Activity r0_Activity = this.e;
            Class[] r4_ClassA = o;
            Object[] r5_ObjectA = new Object[2];
            r5_ObjectA[0] = Integer.valueOf(r8i);
            r5_ObjectA[1] = Integer.valueOf(r9i);
            AQUtility.invokeHandler(r0_Activity, "overridePendingTransition", false, false, r4_ClassA, r5_ObjectA);
        }
        return a();
    }

    public T parent(int r4i) {
        View r0_View = this.a;
        while (r0_View != null) {
            if (r0_View.getId() != r4i) {
                ViewParent r0_ViewParent = r0_View.getParent();
                if (r0_ViewParent instanceof View) {
                    r0_View = (View) r0_ViewParent;
                } else {
                    r0_View = null;
                    break;
                }
            } else {
                break;
            }
        }
        r0_View = null;
        return a(r0_View);
    }

    public T policy(int r2i) {
        this.h = r2i;
        return a();
    }

    public <K> T post(String r3_String, String r4_String, HttpEntity r5_HttpEntity, Class<K> r6_Class_K, AjaxCallback<K> r7_AjaxCallback_K) {
        ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) r7_AjaxCallback_K.url(r3_String)).type(r6_Class_K)).method(1)).header("Content-Type", r4_String)).param(Constants.POST_ENTITY, r5_HttpEntity);
        return ajax(r7_AjaxCallback_K);
    }

    public <K> T post(String r7_String, JSONObject r8_JSONObject, Class<K> r9_Class_K, AjaxCallback<K> r10_AjaxCallback_K) {
        try {
            return post(r7_String, "application/json", new StringEntity(r8_JSONObject.toString(), Base.UTF8), r9_Class_K, r10_AjaxCallback_K);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public T progress(int r2i) {
        this.b = a(r2i);
        return a();
    }

    public T progress(Dialog r2_Dialog) {
        this.b = r2_Dialog;
        return a();
    }

    public T progress(Object r2_Object) {
        this.b = r2_Object;
        return a();
    }

    public T proxy(String r2_String, int r3i) {
        this.i = new HttpHost(r2_String, r3i);
        return a();
    }

    public <K> T put(String r3_String, String r4_String, HttpEntity r5_HttpEntity, Class<K> r6_Class_K, AjaxCallback<K> r7_AjaxCallback_K) {
        ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) ((AjaxCallback) r7_AjaxCallback_K.url(r3_String)).type(r6_Class_K)).method(XListViewFooter.STATE_NOMORE)).header("Content-Type", r4_String)).param(Constants.POST_ENTITY, r5_HttpEntity);
        return ajax(r7_AjaxCallback_K);
    }

    public <K> T put(String r7_String, JSONObject r8_JSONObject, Class<K> r9_Class_K, AjaxCallback<K> r10_AjaxCallback_K) {
        try {
            return put(r7_String, "application/json", new StringEntity(r8_JSONObject.toString(), Base.UTF8), r9_Class_K, r10_AjaxCallback_K);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public T rating(float r2f) {
        if (this.a instanceof RatingBar) {
            ((RatingBar) this.a).setRating(r2f);
        }
        return a();
    }

    public T recycle(View r2_View) {
        this.d = r2_View;
        this.a = r2_View;
        b();
        this.f = null;
        return a();
    }

    public T scrolled(OnScrollListener r2_OnScrollListener) {
        if (this.a instanceof AbsListView) {
            d().forward(r2_OnScrollListener);
        }
        return a();
    }

    public T scrolledBottom(Object r4_Object, String r5_String) {
        if (this.a instanceof AbsListView) {
            d().forward(r4_Object, r5_String, true, m);
        }
        return a();
    }

    public T setLayerType11(int r7i, Paint r8_Paint) {
        if (this.a != null) {
            View r0_View = this.a;
            Class[] r4_ClassA = q;
            Object[] r5_ObjectA = new Object[2];
            r5_ObjectA[0] = Integer.valueOf(r7i);
            r5_ObjectA[1] = r8_Paint;
            AQUtility.invokeHandler(r0_View, "setLayerType", false, false, r4_ClassA, r5_ObjectA);
        }
        return a();
    }

    public T setOverScrollMode9(int r7i) {
        if (this.a instanceof AbsListView) {
            View r0_View = this.a;
            Class[] r4_ClassA = p;
            Object[] r5_ObjectA = new Object[1];
            r5_ObjectA[0] = Integer.valueOf(r7i);
            AQUtility.invokeHandler(r0_View, "setOverScrollMode", false, false, r4_ClassA, r5_ObjectA);
        }
        return a();
    }

    public T setSelection(int r2i) {
        if (this.a instanceof AdapterView) {
            ((AdapterView) this.a).setSelection(r2i);
        }
        return a();
    }

    public boolean shouldDelay(int r2i, int r3i, boolean r4z, View r5_View, ViewGroup r6_ViewGroup, String r7_String) {
        return Common.shouldDelay(r2i, r3i, r5_View, r6_ViewGroup, r7_String);
    }

    public boolean shouldDelay(int r3i, View r4_View, ViewGroup r5_ViewGroup, String r6_String) {
        if (!(r5_ViewGroup instanceof ExpandableListView)) {
            return Common.shouldDelay(r3i, r4_View, r5_ViewGroup, r6_String);
        }
        throw new IllegalArgumentException("Please use the other shouldDelay methods for expandable list.");
    }

    public boolean shouldDelay(int r2i, boolean r3z, View r4_View, ViewGroup r5_ViewGroup, String r6_String) {
        return Common.shouldDelay(r2i, -1, r4_View, r5_ViewGroup, r6_String);
    }

    public boolean shouldDelay(View r2_View, ViewGroup r3_ViewGroup, String r4_String, float r5f) {
        return Common.shouldDelay(r2_View, r3_ViewGroup, r4_String, r5f, true);
    }

    public boolean shouldDelay(View r2_View, ViewGroup r3_ViewGroup, String r4_String, float r5f, boolean r6z) {
        return Common.shouldDelay(r2_View, r3_ViewGroup, r4_String, r5f, r6z);
    }

    public T show(Dialog r3_Dialog) {
        if (r3_Dialog != null) {
            try {
                r3_Dialog.show();
                r.put(r3_Dialog, null);
            } catch (Exception e) {
            }
        }
        return a();
    }

    public <K> T sync(AjaxCallback<K> r2_AjaxCallback_K) {
        ajax(r2_AjaxCallback_K);
        r2_AjaxCallback_K.block();
        return a();
    }

    public T tag(int r2i, Object r3_Object) {
        if (this.a != null) {
            this.a.setTag(r2i, r3_Object);
        }
        return a();
    }

    public T tag(Object r2_Object) {
        if (this.a != null) {
            this.a.setTag(r2_Object);
        }
        return a();
    }

    public T text(int r2i) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setText(r2i);
        }
        return a();
    }

    public T text(int r2i, Object ... r3_ObjectA) {
        Context r0_Context = getContext();
        if (r0_Context != null) {
            text(r0_Context.getString(r2i, r3_ObjectA));
        }
        return a();
    }

    public T text(Spanned r2_Spanned) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setText(r2_Spanned);
        }
        return a();
    }

    public T text(CharSequence r2_CharSequence) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setText(r2_CharSequence);
        }
        return a();
    }

    public T text(CharSequence r2_CharSequence, boolean r3z) {
        if (r3z) {
            if (r2_CharSequence == null || r2_CharSequence.length() == 0) {
                return gone();
            }
        }
        return text(r2_CharSequence);
    }

    public T textChanged(Object r5_Object, String r6_String) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).addTextChangedListener(new Common().forward(r5_Object, r6_String, true, n));
        }
        return a();
    }

    public T textColor(int r2i) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setTextColor(r2i);
        }
        return a();
    }

    public T textColorId(int r2i) {
        return textColor(getContext().getResources().getColor(r2i));
    }

    public T textSize(float r2f) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setTextSize(r2f);
        }
        return a();
    }

    public T transformer(Transformer r2_Transformer) {
        this.g = r2_Transformer;
        return a();
    }

    public T transparent(boolean r2z) {
        if (this.a != null) {
            AQUtility.transparent(this.a, r2z);
        }
        return a();
    }

    public T typeface(Typeface r2_Typeface) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setTypeface(r2_Typeface);
        }
        return a();
    }

    public T visibility(int r2i) {
        if (this.a == null || this.a.getVisibility() == r2i) {
            return a();
        }
        this.a.setVisibility(r2i);
        return a();
    }

    public T visible() {
        return visibility(0);
    }

    public T webImage(String r4_String) {
        return webImage(r4_String, true, false, ViewCompat.MEASURED_STATE_MASK);
    }

    public T webImage(String r9_String, boolean r10z, boolean r11z, int r12i) {
        if (this.a instanceof WebView) {
            setLayerType11(1, null);
            new WebImage((WebView) this.a, r9_String, this.b, r10z, r11z, r12i).load();
            this.b = null;
        }
        return a();
    }

    public T width(int r2i) {
        a(true, r2i, true);
        return a();
    }

    public T width(int r2i, boolean r3z) {
        a(true, r2i, r3z);
        return a();
    }
}
package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.CollapsibleActionView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;
import com.androidquery.util.Constants;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class SearchView extends LinearLayout implements CollapsibleActionView {
    static final a a;
    private CharSequence A;
    private CharSequence B;
    private boolean C;
    private int D;
    private SearchableInfo E;
    private Bundle F;
    private Runnable G;
    private Runnable H;
    private Runnable I;
    private final Intent J;
    private final Intent K;
    private final WeakHashMap<String, ConstantState> L;
    private final OnClickListener M;
    private final OnEditorActionListener N;
    private final OnItemClickListener O;
    private final OnItemSelectedListener P;
    private TextWatcher Q;
    OnKeyListener b;
    private OnQueryTextListener c;
    private OnCloseListener d;
    private OnFocusChangeListener e;
    private OnSuggestionListener f;
    private OnClickListener g;
    private boolean h;
    private boolean i;
    private CursorAdapter j;
    private View k;
    private View l;
    private View m;
    private View n;
    private ImageView o;
    private View p;
    private View q;
    private SearchAutoComplete r;
    private View s;
    private ImageView t;
    private boolean u;
    private CharSequence v;
    private boolean w;
    private boolean x;
    private int y;
    private boolean z;

    public static interface OnCloseListener {
        public boolean onClose();
    }

    public static interface OnQueryTextListener {
        public boolean onQueryTextChange(String r1_String);

        public boolean onQueryTextSubmit(String r1_String);
    }

    public static interface OnSuggestionListener {
        public boolean onSuggestionClick(int r1i);

        public boolean onSuggestionSelect(int r1i);
    }

    public static class SearchAutoComplete extends AutoCompleteTextView {
        private int a;
        private SearchView b;

        public SearchAutoComplete(Context r2_Context) {
            super(r2_Context);
            this.a = getThreshold();
        }

        public SearchAutoComplete(Context r2_Context, AttributeSet r3_AttributeSet) {
            super(r2_Context, r3_AttributeSet);
            this.a = getThreshold();
        }

        public SearchAutoComplete(Context r2_Context, AttributeSet r3_AttributeSet, int r4i) {
            super(r2_Context, r3_AttributeSet, r4i);
            this.a = getThreshold();
        }

        private boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void a(SearchView r1_SearchView) {
            this.b = r1_SearchView;
        }

        public boolean enoughToFilter() {
            return this.a <= 0 || super.enoughToFilter();
        }

        protected void onFocusChanged(boolean r2z, int r3i, Rect r4_Rect) {
            super.onFocusChanged(r2z, r3i, r4_Rect);
            this.b.a();
        }

        public boolean onKeyPreIme(int r4i, KeyEvent r5_KeyEvent) {
            if (r4i == 4) {
                DispatcherState r1_DispatcherState;
                if (r5_KeyEvent.getAction() == 0 && r5_KeyEvent.getRepeatCount() == 0) {
                    r1_DispatcherState = getKeyDispatcherState();
                    if (r1_DispatcherState == null) {
                        return true;
                    }
                    r1_DispatcherState.startTracking(r5_KeyEvent, this);
                    return true;
                } else if (r5_KeyEvent.getAction() == 1) {
                    r1_DispatcherState = getKeyDispatcherState();
                    if (r1_DispatcherState != null) {
                        r1_DispatcherState.handleUpEvent(r5_KeyEvent);
                    }
                    if ((!r5_KeyEvent.isTracking()) || r5_KeyEvent.isCanceled()) {
                    } else {
                        this.b.clearFocus();
                        this.b.c(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(r4i, r5_KeyEvent);
        }

        public void onWindowFocusChanged(boolean r3z) {
            super.onWindowFocusChanged(r3z);
            if (r3z && this.b.hasFocus() && getVisibility() == 0) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                if (SearchView.a(getContext())) {
                    a.a(this, true);
                }
            }
        }

        public void performCompletion() {
        }

        protected void replaceText(CharSequence r1_CharSequence) {
        }

        public void setThreshold(int r1i) {
            super.setThreshold(r1i);
            this.a = r1i;
        }
    }

    private static class a {
        private Method a;
        private Method b;
        private Method c;
        private Method d;

        a() {
            Class[] r2_ClassA;
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b.setAccessible(true);
            } catch (NoSuchMethodException e_2) {
            }
            try {
                r2_ClassA = new Class[1];
                r2_ClassA[0] = Boolean.TYPE;
                this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", r2_ClassA);
                this.c.setAccessible(true);
            } catch (NoSuchMethodException e_3) {
            }
            try {
                r2_ClassA = new Class[2];
                r2_ClassA[0] = Integer.TYPE;
                r2_ClassA[1] = ResultReceiver.class;
                this.d = InputMethodManager.class.getMethod("showSoftInputUnchecked", r2_ClassA);
                this.d.setAccessible(true);
            } catch (NoSuchMethodException e_4) {
            }
        }

        void a_(InputMethodManager r5_InputMethodManager, View r6_View, int r7i) {
            if (this.d != null) {
                try {
                    Method r0_Method = this.d;
                    Object[] r1_ObjectA = new Object[2];
                    r1_ObjectA[0] = Integer.valueOf(r7i);
                    r1_ObjectA[1] = null;
                    r0_Method.invoke(r5_InputMethodManager, r1_ObjectA);
                    return;
                } catch (Exception e) {
                }
            }
            r5_InputMethodManager.showSoftInput(r6_View, r7i);
        }

        void a_(AutoCompleteTextView r3_AutoCompleteTextView) {
            if (this.a != null) {
                try {
                    this.a.invoke(r3_AutoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        void a_(AutoCompleteTextView r5_AutoCompleteTextView, boolean r6z) {
            if (this.c != null) {
                try {
                    Method r0_Method = this.c;
                    Object[] r1_ObjectA = new Object[1];
                    r1_ObjectA[0] = Boolean.valueOf(r6z);
                    r0_Method.invoke(r5_AutoCompleteTextView, r1_ObjectA);
                } catch (Exception e) {
                }
            }
        }

        void b(AutoCompleteTextView r3_AutoCompleteTextView) {
            if (this.b != null) {
                try {
                    this.b.invoke(r3_AutoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }
    }

    static {
        a = new a();
    }

    public SearchView(Context r2_Context) {
        this(r2_Context, null);
    }

    public SearchView(Context r8_Context, AttributeSet r9_AttributeSet) {
        super(r8_Context, r9_AttributeSet);
        this.G = new a(this);
        this.H = new e(this);
        this.I = new f(this);
        this.L = new WeakHashMap();
        this.M = new j(this);
        this.b = new k(this);
        this.N = new l(this);
        this.O = new b(this);
        this.P = new c(this);
        this.Q = new d(this);
        ((LayoutInflater) r8_Context.getSystemService("layout_inflater")).inflate(R.layout.abc_search_view, this, true);
        this.k = findViewById(R.id.search_button);
        this.r = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.r.a(this);
        this.p = findViewById(R.id.search_edit_frame);
        this.m = findViewById(R.id.search_plate);
        this.n = findViewById(R.id.submit_area);
        this.l = findViewById(R.id.search_go_btn);
        this.o = (ImageView) findViewById(R.id.search_close_btn);
        this.q = findViewById(R.id.search_voice_btn);
        this.t = (ImageView) findViewById(R.id.search_mag_icon);
        this.k.setOnClickListener(this.M);
        this.o.setOnClickListener(this.M);
        this.l.setOnClickListener(this.M);
        this.q.setOnClickListener(this.M);
        this.r.setOnClickListener(this.M);
        this.r.addTextChangedListener(this.Q);
        this.r.setOnEditorActionListener(this.N);
        this.r.setOnItemClickListener(this.O);
        this.r.setOnItemSelectedListener(this.P);
        this.r.setOnKeyListener(this.b);
        this.r.setOnFocusChangeListener(new g(this));
        TypedArray r0_TypedArray = r8_Context.obtainStyledAttributes(r9_AttributeSet, R.styleable.SearchView, 0, 0);
        setIconifiedByDefault(r0_TypedArray.getBoolean(XListViewFooter.STATE_NOMORE, true));
        int r1i = r0_TypedArray.getDimensionPixelSize(0, -1);
        if (r1i != -1) {
            setMaxWidth(r1i);
        }
        CharSequence r1_CharSequence = r0_TypedArray.getText(XListViewFooter.STATE_NODATA);
        if (!TextUtils.isEmpty(r1_CharSequence)) {
            setQueryHint(r1_CharSequence);
        }
        r1i = r0_TypedArray.getInt(XListViewHeader.STATE_REFRESHING, -1);
        if (r1i != -1) {
            setImeOptions(r1i);
        }
        r1i = r0_TypedArray.getInt(1, -1);
        if (r1i != -1) {
            setInputType(r1i);
        }
        r0_TypedArray.recycle();
        r0_TypedArray = r8_Context.obtainStyledAttributes(r9_AttributeSet, R.styleable.View, 0, 0);
        boolean r1z = r0_TypedArray.getBoolean(0, true);
        r0_TypedArray.recycle();
        setFocusable(r1z);
        this.J = new Intent("android.speech.action.WEB_SEARCH");
        this.J.addFlags(268435456);
        this.J.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.K = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.K.addFlags(268435456);
        this.s = findViewById(this.r.getDropDownAnchor());
        if (this.s != null) {
            if (VERSION.SDK_INT >= 11) {
                b();
            } else {
                c();
            }
        }
        a(this.h);
        l();
    }

    private Intent a(Intent r4_Intent, SearchableInfo r5_SearchableInfo) {
        Intent r1_Intent = new Intent(r4_Intent);
        ComponentName r0_ComponentName = r5_SearchableInfo.getSearchActivity();
        r1_Intent.putExtra("calling_package", r0_ComponentName == null ? null : r0_ComponentName.flattenToShortString());
        return r1_Intent;
    }

    private Intent a(Cursor r9_Cursor, int r10i, String r11_String) {
        Intent r0_Intent;
        int r0i;
        try {
            String r1_String = m.getColumnString(r9_Cursor, "suggest_intent_action");
            if (r1_String == null) {
                r1_String = this.E.getSuggestIntentAction();
            }
            if (r1_String == null) {
                r1_String = "android.intent.action.SEARCH";
            }
            String r0_String = m.getColumnString(r9_Cursor, "suggest_intent_data");
            if (r0_String == null) {
                r0_String = this.E.getSuggestIntentData();
            }
            if (r0_String != null) {
                String r2_String = m.getColumnString(r9_Cursor, "suggest_intent_data_id");
                if (r2_String != null) {
                    r0_String = r0_String + "/" + Uri.encode(r2_String);
                }
            }
            r0_Intent = a(r1_String, r0_String == null ? null : Uri.parse(r0_String), m.getColumnString(r9_Cursor, "suggest_intent_extra_data"), m.getColumnString(r9_Cursor, "suggest_intent_query"), r10i, r11_String);
        } catch (RuntimeException e) {
            Throwable r1_Throwable = e;
            r0i = r9_Cursor.getPosition();
            Log.w("SearchView", "Search suggestions cursor at row " + r0i + " returned exception.", r1_Throwable);
            r0_Intent = null;
        }
        return r0_Intent;
    }

    private Intent a(String r4_String, Uri r5_Uri, String r6_String, String r7_String, int r8i, String r9_String) {
        Intent r0_Intent = new Intent(r4_String);
        r0_Intent.addFlags(268435456);
        if (r5_Uri != null) {
            r0_Intent.setData(r5_Uri);
        }
        r0_Intent.putExtra("user_query", this.B);
        if (r7_String != null) {
            r0_Intent.putExtra("query", r7_String);
        }
        if (r6_String != null) {
            r0_Intent.putExtra("intent_extra_data_key", r6_String);
        }
        if (this.F != null) {
            r0_Intent.putExtra("app_data", this.F);
        }
        if (r8i != 0) {
            r0_Intent.putExtra("action_key", r8i);
            r0_Intent.putExtra("action_msg", r9_String);
        }
        r0_Intent.setComponent(this.E.getSearchActivity());
        return r0_Intent;
    }

    private void a(int r8i, String r9_String, String r10_String) {
        getContext().startActivity(a("android.intent.action.SEARCH", null, null, r10_String, r8i, r9_String));
    }

    private void a(Intent r5_Intent) {
        if (r5_Intent == null) {
        } else {
            try {
                getContext().startActivity(r5_Intent);
            } catch (RuntimeException e) {
                Log.e("SearchView", "Failed launch activity: " + r5_Intent, e);
            }
        }
    }

    private void a(boolean r7z) {
        int r0i;
        boolean r3z;
        boolean r4z = true;
        int r2i = Base64.DONT_BREAK_LINES;
        this.i = r7z;
        r0i = r7z ? 0 : 8;
        r3z = !TextUtils.isEmpty(this.r.getText());
        this.k.setVisibility(r0i);
        b(r3z);
        this.p.setVisibility(r7z ? 8 : 0);
        ImageView r0_ImageView = this.t;
        if (this.h) {
            r0_ImageView.setVisibility(r2i);
            h();
            if (r3z) {
                d(r4z);
                g();
            } else {
                r4z = false;
                d(r4z);
                g();
            }
        } else {
            r2i = 0;
            r0_ImageView.setVisibility(r2i);
            h();
            if (r3z) {
                r4z = false;
            }
            d(r4z);
            g();
        }
    }

    private boolean a(int r2i) {
        if (this.f != null && this.f.onSuggestionSelect(r2i)) {
            return false;
        }
        b(r2i);
        return true;
    }

    private boolean a(int r3i, int r4i, String r5_String) {
        boolean r0z = false;
        if (this.f != null && this.f.onSuggestionClick(r3i)) {
            return false;
        }
        b(r3i, r0z, null);
        c(r0z);
        o();
        return true;
    }

    static boolean a(Context r2_Context) {
        return r2_Context.getResources().getConfiguration().orientation == 2;
    }

    private boolean a(View r5_View, int r6i, KeyEvent r7_KeyEvent) {
        int r1i = false;
        if (this.E == null || this.j == null || r7_KeyEvent.getAction() != 0 || !KeyEventCompat.hasNoModifiers(r7_KeyEvent)) {
            return false;
        }
        if (r6i == 66 || r6i == 84 || r6i == 61) {
            return a(this.r.getListSelection(), r1i, null);
        }
        if (r6i == 21 || r6i == 22) {
            this.r.setSelection(r6i == 21 ? 0 : this.r.length());
            this.r.setListSelection(r1i);
            this.r.clearListSelection();
            a.a(this.r, true);
            return true;
        } else {
            if (r6i == 19 && this.r.getListSelection() == 0) {
                return false;
            }
            return false;
        }
    }

    private Intent b(Intent r11_Intent, SearchableInfo r12_SearchableInfo) {
        String r1_String;
        String r3_String;
        String r2_String = null;
        ComponentName r5_ComponentName = r12_SearchableInfo.getSearchActivity();
        Intent r0_Intent = new Intent("android.intent.action.SEARCH");
        r0_Intent.setComponent(r5_ComponentName);
        Parcelable r6_Parcelable = PendingIntent.getActivity(getContext(), 0, r0_Intent, 1073741824);
        Bundle r7_Bundle = new Bundle();
        if (this.F != null) {
            r7_Bundle.putParcelable("app_data", this.F);
        }
        Intent r8_Intent = new Intent(r11_Intent);
        String r0_String = "free_form";
        int r4i = 1;
        Resources r3_Resources = getResources();
        if (r12_SearchableInfo.getVoiceLanguageModeId() != 0) {
            r0_String = r3_Resources.getString(r12_SearchableInfo.getVoiceLanguageModeId());
        }
        r1_String = r12_SearchableInfo.getVoicePromptTextId() != 0 ? r3_Resources.getString(r12_SearchableInfo.getVoicePromptTextId()) : null;
        r3_String = r12_SearchableInfo.getVoiceLanguageId() != 0 ? r3_Resources.getString(r12_SearchableInfo.getVoiceLanguageId()) : null;
        if (r12_SearchableInfo.getVoiceMaxResults() != 0) {
            r4i = r12_SearchableInfo.getVoiceMaxResults();
        }
        r8_Intent.putExtra("android.speech.extra.LANGUAGE_MODEL", r0_String);
        r8_Intent.putExtra("android.speech.extra.PROMPT", r1_String);
        r8_Intent.putExtra("android.speech.extra.LANGUAGE", r3_String);
        r8_Intent.putExtra("android.speech.extra.MAX_RESULTS", r4i);
        r0_String = "calling_package";
        if (r5_ComponentName == null) {
            r8_Intent.putExtra(r0_String, r2_String);
            r8_Intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", r6_Parcelable);
            r8_Intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", r7_Bundle);
            return r8_Intent;
        } else {
            r2_String = r5_ComponentName.flattenToShortString();
            r8_Intent.putExtra(r0_String, r2_String);
            r8_Intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", r6_Parcelable);
            r8_Intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", r7_Bundle);
            return r8_Intent;
        }
    }

    private CharSequence b(CharSequence r8_CharSequence) {
        if (!(this.h)) {
            return r8_CharSequence;
        }
        SpannableStringBuilder r0_SpannableStringBuilder = new SpannableStringBuilder("   ");
        r0_SpannableStringBuilder.append(r8_CharSequence);
        Drawable r1_Drawable = getContext().getResources().getDrawable(k());
        int r2i = (int) (((double) this.r.getTextSize()) * 1.25d);
        r1_Drawable.setBounds(0, 0, r2i, r2i);
        r0_SpannableStringBuilder.setSpan(new ImageSpan(r1_Drawable), 1, XListViewHeader.STATE_REFRESHING, AdViewUtil.NETWORK_TYPE_ADWO);
        return r0_SpannableStringBuilder;
    }

    private void b() {
        this.s.addOnLayoutChangeListener(new h(this));
    }

    private void b(int r4i) {
        CharSequence r0_CharSequence = this.r.getText();
        Cursor r1_Cursor = this.j.getCursor();
        if (r1_Cursor == null) {
        } else if (r1_Cursor.moveToPosition(r4i)) {
            CharSequence r1_CharSequence = this.j.convertToString(r1_Cursor);
            if (r1_CharSequence != null) {
                d(r1_CharSequence);
            } else {
                d(r0_CharSequence);
            }
        } else {
            d(r0_CharSequence);
        }
    }

    private void b(boolean r3z) {
        int r0i = Base64.DONT_BREAK_LINES;
        if (this.u && f() && hasFocus()) {
            if (r3z || (!this.z)) {
                r0i = 0;
                this.l.setVisibility(r0i);
            } else {
                this.l.setVisibility(r0i);
            }
        } else {
            this.l.setVisibility(r0i);
        }
    }

    private boolean b(int r3i, int r4i, String r5_String) {
        Cursor r0_Cursor = this.j.getCursor();
        if (r0_Cursor == null || (!r0_Cursor.moveToPosition(r3i))) {
            return false;
        }
        a(a(r0_Cursor, r4i, r5_String));
        return true;
    }

    private void c() {
        this.s.getViewTreeObserver().addOnGlobalLayoutListener(new i(this));
    }

    private void c(CharSequence r4_CharSequence) {
        boolean r0z;
        boolean r1z = true;
        CharSequence r0_CharSequence = this.r.getText();
        this.B = r0_CharSequence;
        r0z = !TextUtils.isEmpty(r0_CharSequence);
        b(r0z);
        if (r0z) {
            r1z = false;
            d(r1z);
            h();
            g();
            if (this.c == null || TextUtils.equals(r4_CharSequence, this.A)) {
                this.A = r4_CharSequence.toString();
            } else {
                this.c.onQueryTextChange(r4_CharSequence.toString());
                this.A = r4_CharSequence.toString();
            }
        } else {
            d(r1z);
            h();
            g();
            if (this.c == null || TextUtils.equals(r4_CharSequence, this.A)) {
                this.A = r4_CharSequence.toString();
            } else {
                this.c.onQueryTextChange(r4_CharSequence.toString());
                this.A = r4_CharSequence.toString();
            }
        }
    }

    private void c(boolean r4z) {
        if (r4z) {
            post(this.G);
        } else {
            removeCallbacks(this.G);
            InputMethodManager r0_InputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (r0_InputMethodManager != null) {
                r0_InputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    private int d() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private void d(CharSequence r3_CharSequence) {
        this.r.setText(r3_CharSequence);
        this.r.setSelection(TextUtils.isEmpty(r3_CharSequence) ? 0 : r3_CharSequence.length());
    }

    private void d(boolean r4z) {
        int r0i;
        if (this.z && !isIconified() && r4z) {
            r0i = 0;
            this.l.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r0i = 8;
        }
        this.q.setVisibility(r0i);
    }

    private boolean e() {
        if (this.E == null || (!this.E.getVoiceSearchEnabled())) {
            return false;
        }
        Intent r1_Intent = null;
        if (this.E.getVoiceSearchLaunchWebSearch()) {
            r1_Intent = this.J;
        } else if (this.E.getVoiceSearchLaunchRecognizer()) {
            r1_Intent = this.K;
        }
        return r1_Intent != null && getContext().getPackageManager().resolveActivity(r1_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION) != null;
    }

    private boolean f() {
        return (this.u || this.z) && (!isIconified());
    }

    private void g() {
        int r0i = Base64.DONT_BREAK_LINES;
        if (f()) {
            if (this.l.getVisibility() == 0 || this.q.getVisibility() == 0) {
                r0i = 0;
            }
        }
        this.n.setVisibility(r0i);
    }

    private void h() {
        int r2i;
        int r0i = 1;
        int r1i = 0;
        r2i = TextUtils.isEmpty(this.r.getText()) ? 0 : 1;
        if (r2i == 0) {
            if ((!this.h) || this.C) {
                r0i = 0;
            }
        }
        ImageView r3_ImageView = this.o;
        if (r0i != 0) {
            r3_ImageView.setVisibility(r1i);
            this.o.getDrawable().setState(r2i == 0 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        } else {
            r1i = Base64.DONT_BREAK_LINES;
            r3_ImageView.setVisibility(r1i);
            if (r2i == 0) {
            }
            this.o.getDrawable().setState(r2i == 0 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void i() {
        post(this.H);
    }

    private void j() {
        boolean r1z = this.r.hasFocus();
        this.m.getBackground().setState(r1z ? FOCUSED_STATE_SET : EMPTY_STATE_SET);
        this.n.getBackground().setState(r1z ? FOCUSED_STATE_SET : EMPTY_STATE_SET);
        invalidate();
    }

    private int k() {
        TypedValue r0_TypedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.searchViewSearchIcon, r0_TypedValue, true);
        return r0_TypedValue.resourceId;
    }

    private void l() {
        if (this.v != null) {
            this.r.setHint(b(this.v));
        } else if (this.E != null) {
            CharSequence r0_CharSequence = null;
            int r1i = this.E.getHintId();
            if (r1i != 0) {
                r0_CharSequence = getContext().getString(r1i);
            }
            if (r0_CharSequence != null) {
                this.r.setHint(b(r0_CharSequence));
            }
        } else {
            this.r.setHint(b(RContactStorage.PRIMARY_KEY));
        }
    }

    private void m() {
        int r1i = 1;
        this.r.setThreshold(this.E.getSuggestThreshold());
        this.r.setImeOptions(this.E.getImeOptions());
        int r0i = this.E.getInputType();
        if ((r0i & 15) == 1) {
            r0i &= -65537;
            if (this.E.getSuggestAuthority() != null) {
                r0i = (r0i | 65536) | 524288;
            }
        }
        this.r.setInputType(r0i);
        if (this.j != null) {
            this.j.changeCursor(null);
        }
        if (this.E.getSuggestAuthority() != null) {
            this.j = new m(getContext(), this, this.E, this.L);
            this.r.setAdapter(this.j);
            m r0_m = (m) this.j;
            if (this.w) {
                r1i = XListViewHeader.STATE_REFRESHING;
            }
            r0_m.setQueryRefinement(r1i);
        }
    }

    private void n() {
        CharSequence r0_CharSequence = this.r.getText();
        if (r0_CharSequence == null || TextUtils.getTrimmedLength(r0_CharSequence) <= 0) {
        } else if (this.c == null || (!this.c.onQueryTextSubmit(r0_CharSequence.toString()))) {
            if (this.E != null) {
                a(0, null, r0_CharSequence.toString());
                c(false);
            }
            o();
        }
    }

    private void o() {
        this.r.dismissDropDown();
    }

    private void p() {
        if (TextUtils.isEmpty(this.r.getText())) {
            if (this.h) {
                if (this.d == null || (!this.d.onClose())) {
                    clearFocus();
                    a(true);
                }
            }
        } else {
            this.r.setText(RContactStorage.PRIMARY_KEY);
            this.r.requestFocus();
            c(true);
        }
    }

    private void q() {
        a(false);
        this.r.requestFocus();
        c(true);
        if (this.g != null) {
            this.g.onClick(this);
        }
    }

    private void r() {
        if (this.E == null) {
        } else {
            SearchableInfo r0_SearchableInfo = this.E;
            try {
            } catch (ActivityNotFoundException e) {
                Log.w("SearchView", "Could not find voice search activity");
            }
            if (r0_SearchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(a(this.J, r0_SearchableInfo));
            } else {
                if (r0_SearchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(b(this.K, r0_SearchableInfo));
                }
            }
        }
    }

    private void s() {
        if (this.s.getWidth() > 1) {
            int r0i;
            Resources r0_Resources = getContext().getResources();
            int r1i = this.m.getPaddingLeft();
            Rect r2_Rect = new Rect();
            if (this.h) {
                r0i = r0_Resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) + r0_Resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width);
            } else {
                r0i = 0;
            }
            this.r.getDropDownBackground().getPadding(r2_Rect);
            this.r.setDropDownHorizontalOffset(r1i - r2_Rect.left + r0i);
            this.r.setDropDownWidth(r0i + r2_Rect.right + this.s.getWidth() + r2_Rect.left - r1i);
        }
    }

    private void t() {
        a.a(this.r);
        a.b(this.r);
    }

    void a() {
        a(isIconified());
        i();
        if (this.r.hasFocus()) {
            t();
        }
    }

    void a(CharSequence r1_CharSequence) {
        d(r1_CharSequence);
    }

    public void clearFocus() {
        this.x = true;
        c(false);
        super.clearFocus();
        this.r.clearFocus();
        this.x = false;
    }

    public int getImeOptions() {
        return this.r.getImeOptions();
    }

    public int getInputType() {
        return this.r.getInputType();
    }

    public int getMaxWidth() {
        return this.y;
    }

    public CharSequence getQuery() {
        return this.r.getText();
    }

    public CharSequence getQueryHint() {
        if (this.v != null) {
            return this.v;
        }
        if (this.E == null) {
            return null;
        }
        int r1i = this.E.getHintId();
        return r1i != 0 ? getContext().getString(r1i) : null;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.j;
    }

    public boolean isIconfiedByDefault() {
        return this.h;
    }

    public boolean isIconified() {
        return this.i;
    }

    public boolean isQueryRefinementEnabled() {
        return this.w;
    }

    public boolean isSubmitButtonEnabled() {
        return this.u;
    }

    public void onActionViewCollapsed() {
        clearFocus();
        a(true);
        this.r.setImeOptions(this.D);
        this.C = false;
    }

    public void onActionViewExpanded() {
        if (this.C) {
        } else {
            this.C = true;
            this.D = this.r.getImeOptions();
            this.r.setImeOptions(this.D | 33554432);
            this.r.setText(RContactStorage.PRIMARY_KEY);
            setIconified(false);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.H);
        post(this.I);
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int r2i, KeyEvent r3_KeyEvent) {
        return this.E == null ? false : super.onKeyDown(r2i, r3_KeyEvent);
    }

    protected void onMeasure(int r3i, int r4i) {
        if (isIconified()) {
            super.onMeasure(r3i, r4i);
        } else {
            int r1i = MeasureSpec.getMode(r3i);
            int r0i = MeasureSpec.getSize(r3i);
            switch (r1i) {
                case ExploreByTouchHelper.INVALID_ID:
                    r0i = this.y > 0 ? Math.min(this.y, r0i) : Math.min(d(), r0i);
                    break;
                case XListViewHeader.STATE_NORMAL:
                    r0i = this.y > 0 ? this.y : d();
                    break;
                case 1073741824:
                    if (this.y > 0) {
                        r0i = Math.min(this.y, r0i);
                    }
                    break;
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(r0i, 1073741824), r4i);
        }
    }

    public void onWindowFocusChanged(boolean r1z) {
        super.onWindowFocusChanged(r1z);
        i();
    }

    public boolean requestFocus(int r3i, Rect r4_Rect) {
        if (this.x || !isFocusable()) {
            return false;
        }
        if (isIconified()) {
            return super.requestFocus(r3i, r4_Rect);
        }
        boolean r1z = this.r.requestFocus(r3i, r4_Rect);
        if (r1z) {
            a(false);
        }
        return r1z;
    }

    public void setAppSearchData(Bundle r1_Bundle) {
        this.F = r1_Bundle;
    }

    public void setIconified(boolean r1z) {
        if (r1z) {
            p();
        } else {
            q();
        }
    }

    public void setIconifiedByDefault(boolean r2z) {
        if (this.h == r2z) {
        } else {
            this.h = r2z;
            a(r2z);
            l();
        }
    }

    public void setImeOptions(int r2i) {
        this.r.setImeOptions(r2i);
    }

    public void setInputType(int r2i) {
        this.r.setInputType(r2i);
    }

    public void setMaxWidth(int r1i) {
        this.y = r1i;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener r1_OnCloseListener) {
        this.d = r1_OnCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener r1_OnFocusChangeListener) {
        this.e = r1_OnFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener r1_OnQueryTextListener) {
        this.c = r1_OnQueryTextListener;
    }

    public void setOnSearchClickListener(OnClickListener r1_OnClickListener) {
        this.g = r1_OnClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener r1_OnSuggestionListener) {
        this.f = r1_OnSuggestionListener;
    }

    public void setQuery(CharSequence r3_CharSequence, boolean r4z) {
        this.r.setText(r3_CharSequence);
        if (r3_CharSequence != null) {
            this.r.setSelection(this.r.length());
            this.B = r3_CharSequence;
        }
        if ((!r4z) || TextUtils.isEmpty(r3_CharSequence)) {
        } else {
            n();
        }
    }

    public void setQueryHint(CharSequence r1_CharSequence) {
        this.v = r1_CharSequence;
        l();
    }

    public void setQueryRefinementEnabled(boolean r3z) {
        this.w = r3z;
        if (this.j instanceof m) {
            ((m) this.j).setQueryRefinement(r3z ? XListViewHeader.STATE_REFRESHING : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo r3_SearchableInfo) {
        this.E = r3_SearchableInfo;
        if (this.E != null) {
            m();
            l();
        }
        this.z = e();
        if (this.z) {
            this.r.setPrivateImeOptions("nm");
        }
        a(isIconified());
    }

    public void setSubmitButtonEnabled(boolean r2z) {
        this.u = r2z;
        a(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter r3_CursorAdapter) {
        this.j = r3_CursorAdapter;
        this.r.setAdapter(this.j);
    }
}
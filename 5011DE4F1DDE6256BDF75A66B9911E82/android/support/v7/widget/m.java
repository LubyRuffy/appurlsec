package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
import qsbk.app.Constants;
import qsbk.app.push.Utils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SuggestionsAdapter.java
class m extends ResourceCursorAdapter implements OnClickListener {
    private SearchManager j;
    private SearchView k;
    private SearchableInfo l;
    private Context m;
    private WeakHashMap<String, ConstantState> n;
    private boolean o;
    private int p;
    private ColorStateList q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;

    // compiled from: SuggestionsAdapter.java
    private static final class a {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public a(View r2_View) {
            this.mText1 = (TextView) r2_View.findViewById(16908308);
            this.mText2 = (TextView) r2_View.findViewById(16908309);
            this.mIcon1 = (ImageView) r2_View.findViewById(16908295);
            this.mIcon2 = (ImageView) r2_View.findViewById(16908296);
            this.mIconRefine = (ImageView) r2_View.findViewById(R.id.edit_query);
        }
    }

    public m(Context r5_Context, SearchView r6_SearchView, SearchableInfo r7_SearchableInfo, WeakHashMap<String, ConstantState> r8_WeakHashMap_String__ConstantState) {
        super(r5_Context, R.layout.abc_search_dropdown_item_icons_2line, null, true);
        this.o = false;
        this.p = 1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.j = (SearchManager) this.d.getSystemService("search");
        this.k = r6_SearchView;
        this.l = r7_SearchableInfo;
        this.m = r5_Context;
        this.n = r8_WeakHashMap_String__ConstantState;
    }

    private Drawable a(ComponentName r5_ComponentName) {
        ConstantState r1_ConstantState = null;
        String r2_String = r5_ComponentName.flattenToShortString();
        if (this.n.containsKey(r2_String)) {
            ConstantState r0_ConstantState = (ConstantState) this.n.get(r2_String);
            return r0_ConstantState == null ? null : r0_ConstantState.newDrawable(this.m.getResources());
        } else {
            Drawable r0_Drawable = b(r5_ComponentName);
            if (r0_Drawable == null) {
                this.n.put(r2_String, r1_ConstantState);
                return r0_Drawable;
            } else {
                r1_ConstantState = r0_Drawable.getConstantState();
                this.n.put(r2_String, r1_ConstantState);
                return r0_Drawable;
            }
        }
    }

    private Drawable a(String r5_String) {
        if (r5_String == null || r5_String.length() == 0 || "0".equals(r5_String)) {
            return null;
        }
        Drawable r0_Drawable;
        try {
            int r2i = Integer.parseInt(r5_String);
            String r3_String = "android.resource://" + this.m.getPackageName() + "/" + r2i;
            r0_Drawable = b(r3_String);
            if (r0_Drawable != null) {
                return r0_Drawable;
            }
            r0_Drawable = this.m.getResources().getDrawable(r2i);
            a(r3_String, r0_Drawable);
            return r0_Drawable;
        } catch (NumberFormatException e) {
            r0_Drawable = b(r5_String);
            if (r0_Drawable != null) {
                return r0_Drawable;
            }
            r0_Drawable = b(Uri.parse(r5_String));
            a(r5_String, r0_Drawable);
            return r0_Drawable;
        } catch (NotFoundException e_2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + r5_String);
            return null;
        }
    }

    private CharSequence a(CharSequence r8_CharSequence) {
        String r1_String = null;
        if (this.q == null) {
            TypedValue r0_TypedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.attr.textColorSearchUrl, r0_TypedValue, true);
            this.q = this.d.getResources().getColorStateList(r0_TypedValue.resourceId);
        }
        CharSequence r6_CharSequence = new SpannableString(r8_CharSequence);
        r6_CharSequence.setSpan(new TextAppearanceSpan(r1_String, 0, 0, this.q, r1_String), 0, r8_CharSequence.length(), AdViewUtil.NETWORK_TYPE_ADWO);
        return r6_CharSequence;
    }

    private static String a(Cursor r4_Cursor, int r5i) {
        if (r5i == -1) {
            return null;
        }
        try {
            return r4_Cursor.getString(r5i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    private void a(Cursor r3_Cursor) {
        Bundle r0_Bundle;
        r0_Bundle = r3_Cursor != null ? r3_Cursor.getExtras() : null;
        if (r0_Bundle == null || (!r0_Bundle.getBoolean("in_progress"))) {
        }
    }

    private void a(ImageView r3_ImageView, Drawable r4_Drawable, int r5i) {
        r3_ImageView.setImageDrawable(r4_Drawable);
        if (r4_Drawable == null) {
            r3_ImageView.setVisibility(r5i);
        } else {
            r3_ImageView.setVisibility(0);
            r4_Drawable.setVisible(false, false);
            r4_Drawable.setVisible(true, false);
        }
    }

    private void a(TextView r2_TextView, CharSequence r3_CharSequence) {
        r2_TextView.setText(r3_CharSequence);
        if (TextUtils.isEmpty(r3_CharSequence)) {
            r2_TextView.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            r2_TextView.setVisibility(0);
        }
    }

    private void a(String r3_String, Drawable r4_Drawable) {
        if (r4_Drawable != null) {
            this.n.put(r3_String, r4_Drawable.getConstantState());
        }
    }

    private Drawable b(ComponentName r6_ComponentName) {
        PackageManager r1_PackageManager = this.d.getPackageManager();
        try {
            ActivityInfo r2_ActivityInfo = r1_PackageManager.getActivityInfo(r6_ComponentName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            int r3i = r2_ActivityInfo.getIconResource();
            if (r3i == 0) {
                return null;
            }
            Drawable r1_Drawable = r1_PackageManager.getDrawable(r6_ComponentName.getPackageName(), r3i, r2_ActivityInfo.applicationInfo);
            if (r1_Drawable != null) {
                return r1_Drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + r3i + " for " + r6_ComponentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    private Drawable b(Cursor r3_Cursor) {
        if (this.u == -1) {
            return null;
        }
        Drawable r0_Drawable = a(r3_Cursor.getString(this.u));
        return r0_Drawable == null ? d(r3_Cursor) : r0_Drawable;
    }

    private Drawable b(Uri r7_Uri) {
        InputStream r2_InputStream;
        try {
            if ("android.resource".equals(r7_Uri.getScheme())) {
                return a(r7_Uri);
            }
            r2_InputStream = this.m.getContentResolver().openInputStream(r7_Uri);
            if (r2_InputStream == null) {
                throw new FileNotFoundException("Failed to open " + r7_Uri);
            } else {
                Drawable r0_Drawable = Drawable.createFromStream(r2_InputStream, null);
                try {
                    r2_InputStream.close();
                    return r0_Drawable;
                } catch (IOException e) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + r7_Uri, e);
                    return r0_Drawable;
                }
            }
        } catch (NotFoundException e_2) {
            throw new FileNotFoundException("Resource does not exist: " + r7_Uri);
        } catch (FileNotFoundException e_3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + r7_Uri + ", " + e_3.getMessage());
            return null;
        } catch (Throwable th) {
            r2_InputStream.close();
        }
    }

    private Drawable b(String r2_String) {
        ConstantState r0_ConstantState = (ConstantState) this.n.get(r2_String);
        return r0_ConstantState == null ? null : r0_ConstantState.newDrawable();
    }

    private Drawable c(Cursor r3_Cursor) {
        return this.v == -1 ? null : a(r3_Cursor.getString(this.v));
    }

    private Drawable d(Cursor r2_Cursor) {
        Drawable r0_Drawable = a(this.l.getSearchActivity());
        return r0_Drawable != null ? r0_Drawable : this.d.getPackageManager().getDefaultActivityIcon();
    }

    public static String getColumnString(Cursor r1_Cursor, String r2_String) {
        return a(r1_Cursor, r1_Cursor.getColumnIndex(r2_String));
    }

    Cursor a(SearchableInfo r7_SearchableInfo, String r8_String, int r9i) {
        Cursor r2_Cursor = null;
        if (r7_SearchableInfo == null) {
            return null;
        }
        String r0_String = r7_SearchableInfo.getSuggestAuthority();
        if (r0_String == null) {
            return null;
        }
        String[] r4_StringA;
        Builder r0_Builder = new Builder().scheme(Utils.RESPONSE_CONTENT).authority(r0_String).query(RContactStorage.PRIMARY_KEY).fragment(RContactStorage.PRIMARY_KEY);
        String r1_String = r7_SearchableInfo.getSuggestPath();
        if (r1_String != null) {
            r0_Builder.appendEncodedPath(r1_String);
        }
        r0_Builder.appendPath("search_suggest_query");
        String r3_String = r7_SearchableInfo.getSuggestSelection();
        if (r3_String != null) {
            r4_StringA = new String[1];
            r4_StringA[0] = r8_String;
        } else {
            r0_Builder.appendPath(r8_String);
            r4_StringA = null;
        }
        if (r9i > 0) {
            r0_Builder.appendQueryParameter("limit", String.valueOf(r9i));
        }
        return this.d.getContentResolver().query(r0_Builder.build(), r2_Cursor, r3_String, r4_StringA, r2_Cursor);
    }

    Drawable a(Uri r8_Uri) throws FileNotFoundException {
        String r2_String = r8_Uri.getAuthority();
        if (TextUtils.isEmpty(r2_String)) {
            throw new FileNotFoundException("No authority: " + r8_Uri);
        } else {
            try {
                Resources r3_Resources = this.d.getPackageManager().getResourcesForApplication(r2_String);
                List r1_List = r8_Uri.getPathSegments();
                if (r1_List == null) {
                    throw new FileNotFoundException("No path: " + r8_Uri);
                } else {
                    int r0i = r1_List.size();
                    if (r0i == 1) {
                        try {
                            r0i = Integer.parseInt((String) r1_List.get(0));
                        } catch (NumberFormatException e) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + r8_Uri);
                        }
                    } else if (r0i == 2) {
                        r0i = r3_Resources.getIdentifier((String) r1_List.get(1), (String) r1_List.get(0), r2_String);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + r8_Uri);
                    }
                    if (r0i != 0) {
                        return r3_Resources.getDrawable(r0i);
                    }
                    throw new FileNotFoundException("No resource found for: " + r8_Uri);
                }
            } catch (NameNotFoundException e_2) {
                throw new FileNotFoundException("No package found for authority: " + r8_Uri);
            }
        }
    }

    public void bindView(View r10_View, Context r11_Context, Cursor r12_Cursor) {
        boolean r2z = false;
        a r0_a = (a) r10_View.getTag();
        int r1i = this.w != -1 ? r12_Cursor.getInt(this.w) : 0;
        if (r0_a.mText1 != null) {
            a(r0_a.mText1, a(r12_Cursor, this.r));
        }
        if (r0_a.mText2 != null) {
            CharSequence r3_CharSequence = a(r12_Cursor, this.t);
            r3_CharSequence = r3_CharSequence != null ? a(r3_CharSequence) : a(r12_Cursor, this.s);
            if (TextUtils.isEmpty(r3_CharSequence)) {
                if (r0_a.mText1 != null) {
                    r0_a.mText1.setSingleLine(false);
                    r0_a.mText1.setMaxLines(XListViewHeader.STATE_REFRESHING);
                }
            } else if (r0_a.mText1 != null) {
                r0_a.mText1.setSingleLine(true);
                r0_a.mText1.setMaxLines(1);
            }
            a(r0_a.mText2, r3_CharSequence);
        }
        if (r0_a.mIcon1 != null) {
            a(r0_a.mIcon1, b(r12_Cursor), (int)XListViewFooter.STATE_NODATA);
        }
        if (r0_a.mIcon2 != null) {
            a(r0_a.mIcon2, c(r12_Cursor), (int)Base64.DONT_BREAK_LINES);
        }
        if (this.p != 2) {
            if (this.p != 1 || (r1i & 1) == 0) {
                r0_a.mIconRefine.setVisibility(Base64.DONT_BREAK_LINES);
                return;
            }
        }
        r0_a.mIconRefine.setVisibility(r2z);
        r0_a.mIconRefine.setTag(r0_a.mText1.getText());
        r0_a.mIconRefine.setOnClickListener(this);
    }

    public void changeCursor(Cursor r4_Cursor) {
        if (this.o) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (r4_Cursor != null) {
                r4_Cursor.close();
            }
        } else {
            try {
                super.changeCursor(r4_Cursor);
                if (r4_Cursor != null) {
                    this.r = r4_Cursor.getColumnIndex("suggest_text_1");
                    this.s = r4_Cursor.getColumnIndex("suggest_text_2");
                    this.t = r4_Cursor.getColumnIndex("suggest_text_2_url");
                    this.u = r4_Cursor.getColumnIndex("suggest_icon_1");
                    this.v = r4_Cursor.getColumnIndex("suggest_icon_2");
                    this.w = r4_Cursor.getColumnIndex("suggest_flags");
                }
            } catch (Exception e) {
                Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
            }
        }
    }

    public void close() {
        changeCursor(null);
        this.o = true;
    }

    public CharSequence convertToString(Cursor r3_Cursor) {
        if (r3_Cursor == null) {
            return null;
        }
        String r1_String = getColumnString(r3_Cursor, "suggest_intent_query");
        if (r1_String != null) {
            return r1_String;
        }
        if (this.l.shouldRewriteQueryFromData()) {
            r1_String = getColumnString(r3_Cursor, "suggest_intent_data");
            if (r1_String != null) {
                return r1_String;
            }
        }
        if (!this.l.shouldRewriteQueryFromText()) {
            return null;
        }
        r1_String = getColumnString(r3_Cursor, "suggest_text_1");
        return r1_String != null ? r1_String : null;
    }

    public int getQueryRefinement() {
        return this.p;
    }

    public View getView(int r4i, View r5_View, ViewGroup r6_ViewGroup) {
        try {
            return super.getView(r4i, r5_View, r6_ViewGroup);
        } catch (RuntimeException e) {
            Throwable r2_Throwable = e;
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", r2_Throwable);
            View r1_View = newView(this.d, this.c, r6_ViewGroup);
            if (r1_View != null) {
                ((a) r1_View.getTag()).mText1.setText(r2_Throwable.toString());
            }
            return r1_View;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public View newView(Context r3_Context, Cursor r4_Cursor, ViewGroup r5_ViewGroup) {
        View r0_View = super.newView(r3_Context, r4_Cursor, r5_ViewGroup);
        r0_View.setTag(new a(r0_View));
        return r0_View;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        a(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        a(getCursor());
    }

    public void onClick(View r3_View) {
        Object r0_Object = r3_View.getTag();
        if (r0_Object instanceof CharSequence) {
            this.k.a((CharSequence) r0_Object);
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence r5_CharSequence) {
        String r0_String;
        r0_String = r5_CharSequence == null ? RContactStorage.PRIMARY_KEY : r5_CharSequence.toString();
        if (this.k.getVisibility() != 0 || this.k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor r0_Cursor = a(this.l, r0_String, (int)Constants.CommentCount);
            if (r0_Cursor != null) {
                r0_Cursor.getCount();
                return r0_Cursor;
            }
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    public void setQueryRefinement(int r1i) {
        this.p = r1i;
    }
}
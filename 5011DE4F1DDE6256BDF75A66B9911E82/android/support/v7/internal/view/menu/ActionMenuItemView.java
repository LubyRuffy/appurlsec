package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ActionMenuView.ActionMenuChildView;
import android.support.v7.internal.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.internal.widget.CompatTextView;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.Locale;
import qsbk.app.utils.Base64;

public class ActionMenuItemView extends CompatTextView implements ActionMenuChildView, ItemView, OnClickListener, OnLongClickListener {
    private MenuItemImpl a;
    private CharSequence b;
    private Drawable c;
    private ItemInvoker d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;

    private class a implements TransformationMethod {
        private Locale b;

        public a() {
            this.b = ActionMenuItemView.this.getContext().getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence r3_CharSequence, View r4_View) {
            return r3_CharSequence != null ? r3_CharSequence.toString().toUpperCase(this.b) : null;
        }

        public void onFocusChanged(View r1_View, CharSequence r2_CharSequence, boolean r3z, int r4i, Rect r5_Rect) {
        }
    }

    public ActionMenuItemView(Context r2_Context) {
        this(r2_Context, null);
    }

    public ActionMenuItemView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public ActionMenuItemView(Context r4_Context, AttributeSet r5_AttributeSet, int r6i) {
        super(r4_Context, r5_AttributeSet, r6i);
        this.e = r4_Context.getResources().getBoolean(R.bool.abc_config_allowActionMenuItemTextWithIcon);
        TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, R.styleable.ActionMenuItemView, 0, 0);
        this.g = r0_TypedArray.getDimensionPixelSize(0, 0);
        r0_TypedArray.recycle();
        setOnClickListener(this);
        setOnLongClickListener(this);
        setTransformationMethod(new a());
        this.h = -1;
    }

    private void a() {
        int r2i = 0;
        int r0i = TextUtils.isEmpty(this.b) ? 0 : 1;
        if (this.c != null) {
            if (this.a.showsTextAsAction()) {
                if (this.e || this.f) {
                    r2i = 1;
                }
            }
        } else {
            r2i = 1;
        }
        setText((r0i & r2i) != 0 ? this.b : null);
    }

    public MenuItemImpl getItemData() {
        return this.a;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    public void initialize(MenuItemImpl r2_MenuItemImpl, int r3i) {
        this.a = r2_MenuItemImpl;
        setIcon(r2_MenuItemImpl.getIcon());
        setTitle(r2_MenuItemImpl.a((ItemView)this));
        setId(r2_MenuItemImpl.getItemId());
        setVisibility(r2_MenuItemImpl.isVisible() ? 0 : Base64.DONT_BREAK_LINES);
        setEnabled(r2_MenuItemImpl.isEnabled());
    }

    public boolean needsDividerAfter() {
        return hasText();
    }

    public boolean needsDividerBefore() {
        return hasText() && this.a.getIcon() == null;
    }

    public void onClick(View r3_View) {
        if (this.d != null) {
            this.d.invokeItem(this.a);
        }
    }

    public boolean onLongClick(View r11_View) {
        boolean r0z = false;
        if (hasText()) {
            return false;
        }
        int[] r2_intA = new int[2];
        Rect r3_Rect = new Rect();
        getLocationOnScreen(r2_intA);
        getWindowVisibleDisplayFrame(r3_Rect);
        Context r4_Context = getContext();
        int r5i = getWidth();
        int r6i = getHeight();
        int r7i = r2_intA[1] + r6i / 2;
        int r8i = r4_Context.getResources().getDisplayMetrics().widthPixels;
        Toast r4_Toast = Toast.makeText(r4_Context, this.a.getTitle(), 0);
        if (r7i < r3_Rect.height()) {
            r4_Toast.setGravity(AdViewUtil.NETWORK_TYPE_YUNYUN, r8i - r2_intA[r0z] - r5i / 2, r6i);
        } else {
            r4_Toast.setGravity(81, 0, r6i);
        }
        r4_Toast.show();
        return true;
    }

    protected void onMeasure(int r7i, int r8i) {
        boolean r1z = hasText();
        int r2i;
        int r0i;
        if ((!r1z) || this.h < 0) {
            super.onMeasure(r7i, r8i);
            r2i = MeasureSpec.getMode(r7i);
            r0i = MeasureSpec.getSize(r7i);
            r3i = getMeasuredWidth();
            r0i = r2i != -2147483648 ? Math.min(r0i, this.g) : this.g;
            if (r2i == 1073741824 || this.g <= 0 || r3i >= r0i) {
                if (r1z || this.c == null) {
                } else {
                    super.setPadding((getMeasuredWidth() - this.c.getIntrinsicWidth()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
                }
            } else {
                super.onMeasure(MeasureSpec.makeMeasureSpec(r0i, 1073741824), r8i);
                if (r1z || this.c == null) {
                } else {
                    super.setPadding((getMeasuredWidth() - this.c.getIntrinsicWidth()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
                }
            }
        } else {
            super.setPadding(this.h, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            super.onMeasure(r7i, r8i);
            r2i = MeasureSpec.getMode(r7i);
            r0i = MeasureSpec.getSize(r7i);
            r3i = getMeasuredWidth();
            if (r2i != -2147483648) {
            }
            if (r2i == 1073741824 || this.g <= 0 || r3i >= r0i) {
                if (r1z || this.c == null) {
                } else {
                    super.setPadding((getMeasuredWidth() - this.c.getIntrinsicWidth()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
                }
            } else {
                super.onMeasure(MeasureSpec.makeMeasureSpec(r0i, 1073741824), r8i);
                if (r1z || this.c == null) {
                } else {
                    super.setPadding((getMeasuredWidth() - this.c.getIntrinsicWidth()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
                }
            }
        }
    }

    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean r1z) {
    }

    public void setChecked(boolean r1z) {
    }

    public void setExpandedFormat(boolean r2z) {
        if (this.f != r2z) {
            this.f = r2z;
            if (this.a != null) {
                this.a.actionFormatChanged();
            }
        }
    }

    public void setIcon(Drawable r2_Drawable) {
        this.c = r2_Drawable;
        setCompoundDrawablesWithIntrinsicBounds(r2_Drawable, null, null, null);
        a();
    }

    public void setItemInvoker(ItemInvoker r1_ItemInvoker) {
        this.d = r1_ItemInvoker;
    }

    public void setPadding(int r1i, int r2i, int r3i, int r4i) {
        this.h = r1i;
        super.setPadding(r1i, r2i, r3i, r4i);
    }

    public void setShortcut(boolean r1z, char r2c) {
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.b = r2_CharSequence;
        setContentDescription(this.b);
        a();
    }

    public boolean showsIcon() {
        return true;
    }
}
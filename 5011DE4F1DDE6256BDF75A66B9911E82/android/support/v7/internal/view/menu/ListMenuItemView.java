package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;

public class ListMenuItemView extends LinearLayout implements ItemView {
    private MenuItemImpl a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private Drawable g;
    private int h;
    private Context i;
    private boolean j;
    private int k;
    private Context l;
    private LayoutInflater m;
    private boolean n;

    public ListMenuItemView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, 0);
    }

    public ListMenuItemView(Context r5_Context, AttributeSet r6_AttributeSet, int r7i) {
        super(r5_Context, r6_AttributeSet);
        this.l = r5_Context;
        TypedArray r0_TypedArray = r5_Context.obtainStyledAttributes(r6_AttributeSet, R.styleable.MenuView, r7i, 0);
        this.g = r0_TypedArray.getDrawable(ShareUtils.SHARE_SMS);
        this.h = r0_TypedArray.getResourceId(1, -1);
        this.j = r0_TypedArray.getBoolean(ShareUtils.SHARE_COLLECT, false);
        this.i = r5_Context;
        r0_TypedArray.recycle();
    }

    private void a() {
        this.b = (ImageView) d().inflate(R.layout.abc_list_menu_item_icon, this, false);
        addView(this.b, 0);
    }

    private void b() {
        this.c = (RadioButton) d().inflate(R.layout.abc_list_menu_item_radio, this, false);
        addView(this.c);
    }

    private void c() {
        this.e = (CheckBox) d().inflate(R.layout.abc_list_menu_item_checkbox, this, false);
        addView(this.e);
    }

    private LayoutInflater d() {
        if (this.m == null) {
            this.m = LayoutInflater.from(this.l);
        }
        return this.m;
    }

    public MenuItemImpl getItemData() {
        return this.a;
    }

    public void initialize(MenuItemImpl r3_MenuItemImpl, int r4i) {
        this.a = r3_MenuItemImpl;
        this.k = r4i;
        setVisibility(r3_MenuItemImpl.isVisible() ? 0 : Base64.DONT_BREAK_LINES);
        setTitle(r3_MenuItemImpl.a((ItemView)this));
        setCheckable(r3_MenuItemImpl.isCheckable());
        setShortcut(r3_MenuItemImpl.c(), r3_MenuItemImpl.a());
        setIcon(r3_MenuItemImpl.getIcon());
        setEnabled(r3_MenuItemImpl.isEnabled());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundDrawable(this.g);
        this.d = (TextView) findViewById(R.id.title);
        if (this.h != -1) {
            this.d.setTextAppearance(this.i, this.h);
        }
        this.f = (TextView) findViewById(R.id.shortcut);
    }

    protected void onMeasure(int r4i, int r5i) {
        if (this.b == null || (!this.j)) {
            super.onMeasure(r4i, r5i);
        } else {
            LayoutParams r1_LayoutParams = getLayoutParams();
            LinearLayout.LayoutParams r0_LinearLayout_LayoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            if (r1_LayoutParams.height <= 0 || r0_LinearLayout_LayoutParams.width > 0) {
                super.onMeasure(r4i, r5i);
            } else {
                r0_LinearLayout_LayoutParams.width = r1_LayoutParams.height;
                super.onMeasure(r4i, r5i);
            }
        }
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean r6z) {
        if (!(!r6z && this.c == null && this.e == null)) {
            CompoundButton r3_CompoundButton;
            CompoundButton r2_CompoundButton;
            if (this.a.isExclusiveCheckable()) {
                if (this.c == null) {
                    b();
                }
                r3_CompoundButton = this.c;
                r2_CompoundButton = this.e;
            } else {
                if (this.e == null) {
                    c();
                }
                r3_CompoundButton = this.e;
                r2_CompoundButton = this.c;
            }
            if (r6z) {
                int r0i;
                r3_CompoundButton.setChecked(this.a.isChecked());
                r0i = r6z ? 0 : 8;
                if (r3_CompoundButton.getVisibility() != r0i) {
                    r3_CompoundButton.setVisibility(r0i);
                }
                if (r2_CompoundButton == null || r2_CompoundButton.getVisibility() == 8) {
                } else {
                    r2_CompoundButton.setVisibility(Base64.DONT_BREAK_LINES);
                }
            } else {
                if (this.e != null) {
                    this.e.setVisibility(Base64.DONT_BREAK_LINES);
                }
                if (this.c != null) {
                    this.c.setVisibility(Base64.DONT_BREAK_LINES);
                }
            }
        }
    }

    public void setChecked(boolean r2z) {
        CompoundButton r0_CompoundButton;
        if (this.a.isExclusiveCheckable()) {
            if (this.c == null) {
                b();
            }
            r0_CompoundButton = this.c;
        } else {
            if (this.e == null) {
                c();
            }
            r0_CompoundButton = this.e;
        }
        r0_CompoundButton.setChecked(r2z);
    }

    public void setForceShowIcon(boolean r1z) {
        this.n = r1z;
        this.j = r1z;
    }

    public void setIcon(Drawable r4_Drawable) {
        int r0i;
        r0i = (this.a.shouldShowIcon() || this.n) ? 1 : 0;
        if (r0i != 0 || this.j) {
            if (!(this.b == null && r4_Drawable == null && !(this.j))) {
                if (this.b == null) {
                    a();
                }
                if (r4_Drawable != null || this.j) {
                    ImageView r2_ImageView = this.b;
                    if (r0i != 0) {
                        r2_ImageView.setImageDrawable(r4_Drawable);
                        if (this.b.getVisibility() == 0) {
                            this.b.setVisibility(0);
                        }
                    } else {
                        r4_Drawable = null;
                        r2_ImageView.setImageDrawable(r4_Drawable);
                        if (this.b.getVisibility() == 0) {
                        } else {
                            this.b.setVisibility(0);
                        }
                    }
                } else {
                    this.b.setVisibility(Base64.DONT_BREAK_LINES);
                }
            }
        }
    }

    public void setShortcut(boolean r4z, char r5c) {
        int r0i;
        r0i = (r4z && this.a.c()) ? 0 : Base64.DONT_BREAK_LINES;
        if (r0i == 0) {
            this.f.setText(this.a.b());
        }
        if (this.f.getVisibility() != r0i) {
            this.f.setVisibility(r0i);
        }
    }

    public void setTitle(CharSequence r3_CharSequence) {
        if (r3_CharSequence != null) {
            this.d.setText(r3_CharSequence);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        } else {
            if (this.d.getVisibility() != 8) {
                this.d.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    public boolean showsIcon() {
        return this.n;
    }
}
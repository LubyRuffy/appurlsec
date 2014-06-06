package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.view.ActionMode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ActionBarContextView extends a {
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private Drawable p;
    private boolean q;

    public ActionBarContextView(Context r2_Context) {
        this(r2_Context, null);
    }

    public ActionBarContextView(Context r2_Context, AttributeSet r3_AttributeSet) {
        this(r2_Context, r3_AttributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context r4_Context, AttributeSet r5_AttributeSet, int r6i) {
        super(r4_Context, r5_AttributeSet, r6i);
        TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, R.styleable.ActionMode, r6i, 0);
        setBackgroundDrawable(r0_TypedArray.getDrawable(XListViewFooter.STATE_NOMORE));
        this.n = r0_TypedArray.getResourceId(1, 0);
        this.o = r0_TypedArray.getResourceId(XListViewHeader.STATE_REFRESHING, 0);
        this.f = r0_TypedArray.getLayoutDimension(0, 0);
        this.p = r0_TypedArray.getDrawable(XListViewFooter.STATE_NODATA);
        r0_TypedArray.recycle();
    }

    private void a() {
        int r0i;
        int r4i = Base64.DONT_BREAK_LINES;
        int r1i = 1;
        if (this.k == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            this.k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.l = (TextView) this.k.findViewById(R.id.action_bar_title);
            this.m = (TextView) this.k.findViewById(R.id.action_bar_subtitle);
            if (this.n != 0) {
                this.l.setTextAppearance(getContext(), this.n);
            }
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
        }
        this.l.setText(this.g);
        this.m.setText(this.h);
        r0i = TextUtils.isEmpty(this.g) ? 0 : 1;
        LinearLayout r3_LinearLayout;
        if (TextUtils.isEmpty(this.h)) {
            r1i = 0;
            this.m.setVisibility(r1i == 0 ? 8 : 0);
            r3_LinearLayout = this.k;
            if (r0i == 0 && r1i == 0) {
                r3_LinearLayout.setVisibility(r4i);
                if (this.k.getParent() != null) {
                } else {
                    addView(this.k);
                }
            } else {
                r4i = 0;
                r3_LinearLayout.setVisibility(r4i);
                if (this.k.getParent() != null) {
                    addView(this.k);
                }
            }
        } else {
            if (r1i == 0) {
            }
            this.m.setVisibility(r1i == 0 ? 8 : 0);
            r3_LinearLayout = this.k;
            if (r0i == 0 || r1i == 0) {
                r4i = 0;
                r3_LinearLayout.setVisibility(r4i);
                if (this.k.getParent() != null) {
                    addView(this.k);
                }
            } else {
                r3_LinearLayout.setVisibility(r4i);
                if (this.k.getParent() != null) {
                } else {
                    addView(this.k);
                }
            }
        }
    }

    public /* bridge */ /* synthetic */ void animateToVisibility(int r1i) {
        super.animateToVisibility(r1i);
    }

    public void closeMode() {
        if (this.i == null) {
            killMode();
        }
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new MarginLayoutParams(getContext(), r3_AttributeSet);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.h;
    }

    public CharSequence getTitle() {
        return this.g;
    }

    public boolean hideOverflowMenu() {
        return this.b != null ? this.b.hideOverflowMenu() : false;
    }

    public void initForMode(ActionMode r7_ActionMode) {
        if (this.i == null) {
            this.i = LayoutInflater.from(getContext()).inflate(R.layout.abc_action_mode_close_item, this, false);
            addView(this.i);
        } else if (this.i.getParent() == null) {
            addView(this.i);
        }
        this.i.findViewById(R.id.action_mode_close_button).setOnClickListener(new d(this, r7_ActionMode));
        MenuBuilder r0_MenuBuilder = (MenuBuilder) r7_ActionMode.getMenu();
        if (this.b != null) {
            this.b.dismissPopupMenus();
        }
        this.b = new ActionMenuPresenter(getContext());
        this.b.setReserveOverflow(true);
        LayoutParams r1_LayoutParams = new LayoutParams(-2, -1);
        if (this.d) {
            this.b.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
            this.b.setItemLimit(a.MAX_ACTIVITY_COUNT_UNLIMITED);
            r1_LayoutParams.width = -1;
            r1_LayoutParams.height = this.f;
            r0_MenuBuilder.addMenuPresenter(this.b);
            this.a = (ActionMenuView) this.b.getMenuView(this);
            this.a.setBackgroundDrawable(this.p);
            this.c.addView(this.a, r1_LayoutParams);
        } else {
            r0_MenuBuilder.addMenuPresenter(this.b);
            this.a = (ActionMenuView) this.b.getMenuView(this);
            this.a.setBackgroundDrawable(null);
            addView(this.a, r1_LayoutParams);
        }
    }

    public boolean isOverflowMenuShowing() {
        return this.b != null ? this.b.isOverflowMenuShowing() : false;
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public boolean isTitleOptional() {
        return this.q;
    }

    public void killMode() {
        removeAllViews();
        if (this.c != null) {
            this.c.removeView(this.a);
        }
        this.j = null;
        this.a = null;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.hideOverflowMenu();
            this.b.hideSubMenus();
        }
    }

    protected void onLayout(boolean r7z, int r8i, int r9i, int r10i, int r11i) {
        int r0i;
        int r1i = getPaddingLeft();
        int r2i = getPaddingTop();
        int r3i = r11i - r9i - getPaddingTop() - getPaddingBottom();
        if (this.i == null || this.i.getVisibility() == 8) {
            r0i = r1i;
        } else {
            MarginLayoutParams r0_MarginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
            r1i += r0_MarginLayoutParams.leftMargin;
            r0i = r0_MarginLayoutParams.rightMargin + r1i + b(this.i, r1i, r2i, r3i);
        }
        if (this.k == null || this.j != null || this.k.getVisibility() == 8) {
            if (this.j == null) {
                r0i += b(this.j, r0i, r2i, r3i);
            }
            r0i = r10i - r8i - getPaddingRight();
            if (this.a != null) {
                r0i -= c(this.a, r0i, r2i, r3i);
            }
        } else {
            r0i += b(this.k, r0i, r2i, r3i);
            if (this.j == null) {
                r0i = r10i - r8i - getPaddingRight();
                if (this.a != null) {
                } else {
                    r0i -= c(this.a, r0i, r2i, r3i);
                }
            } else {
                r0i += b(this.j, r0i, r2i, r3i);
                r0i = r10i - r8i - getPaddingRight();
                if (this.a != null) {
                    r0i -= c(this.a, r0i, r2i, r3i);
                }
            }
        }
    }

    protected void onMeasure(int r13i, int r14i) {
        int r4i = 1073741824;
        int r3i = 0;
        if (MeasureSpec.getMode(r13i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"FILL_PARENT\" (or fill_parent)");
        } else if (MeasureSpec.getMode(r14i) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int r1i;
            int r7i = MeasureSpec.getSize(r13i);
            r1i = this.f > 0 ? this.f : MeasureSpec.getSize(r14i);
            int r8i = getPaddingTop() + getPaddingBottom();
            int r0i = r7i - getPaddingLeft() - getPaddingRight();
            int r6i = r1i - r8i;
            int r2i = MeasureSpec.makeMeasureSpec(r6i, ExploreByTouchHelper.INVALID_ID);
            if (this.i != null) {
                MarginLayoutParams r0_MarginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
                r0i = a(this.i, r0i, r2i, 0) - r0_MarginLayoutParams.rightMargin + r0_MarginLayoutParams.leftMargin;
            }
            LayoutParams r9_LayoutParams;
            int r9i;
            if (this.a == null || this.a.getParent() != this) {
                if (this.k == null || this.j != null) {
                    if (this.j != null) {
                        r9_LayoutParams = this.j.getLayoutParams();
                        r2i = r9_LayoutParams.width != -2 ? 1073741824 : -2147483648;
                        if (r9_LayoutParams.width >= 0) {
                            r0i = Math.min(r9_LayoutParams.width, r0i);
                        }
                        if (r9_LayoutParams.height == -2) {
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                        } else {
                            r4i = -2147483648;
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                        }
                    }
                    if (this.f > 0) {
                        r2i = getChildCount();
                        r1i = 0;
                        while (r3i < r2i) {
                            r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                            if (r0i <= r1i) {
                                r3i++;
                                r1i = r0i;
                            } else {
                                r0i = r1i;
                                r3i++;
                                r1i = r0i;
                            }
                        }
                        setMeasuredDimension(r7i, r1i);
                    } else {
                        setMeasuredDimension(r7i, r1i);
                    }
                } else if (this.q) {
                    r0i = a(this.k, r0i, r2i, 0);
                    if (this.j != null) {
                        r9_LayoutParams = this.j.getLayoutParams();
                        if (r9_LayoutParams.width != -2) {
                        }
                        if (r9_LayoutParams.width >= 0) {
                            r0i = Math.min(r9_LayoutParams.width, r0i);
                        }
                        if (r9_LayoutParams.height == -2) {
                            r4i = -2147483648;
                        }
                        if (r9_LayoutParams.height >= 0) {
                        }
                        this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                    }
                    if (this.f > 0) {
                        setMeasuredDimension(r7i, r1i);
                    } else {
                        r2i = getChildCount();
                        r1i = 0;
                        while (r3i < r2i) {
                            r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                            if (r0i <= r1i) {
                                r0i = r1i;
                            }
                            r3i++;
                            r1i = r0i;
                        }
                        setMeasuredDimension(r7i, r1i);
                    }
                } else {
                    this.k.measure(MeasureSpec.makeMeasureSpec(0, 0), r2i);
                    r9i = this.k.getMeasuredWidth();
                    r2i = r9i > r0i ? 1 : 0;
                    if (r2i == 0) {
                        r0i -= r9i;
                    }
                    this.k.setVisibility(r2i != 0 ? 0 : Base64.DONT_BREAK_LINES);
                    if (this.j != null) {
                        if (this.f > 0) {
                            setMeasuredDimension(r7i, r1i);
                        } else {
                            r2i = getChildCount();
                            r1i = 0;
                            while (r3i < r2i) {
                                r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                if (r0i <= r1i) {
                                    r0i = r1i;
                                }
                                r3i++;
                                r1i = r0i;
                            }
                            setMeasuredDimension(r7i, r1i);
                        }
                    } else {
                        r9_LayoutParams = this.j.getLayoutParams();
                        if (r9_LayoutParams.width != -2) {
                        }
                        if (r9_LayoutParams.width >= 0) {
                            if (r9_LayoutParams.height == -2) {
                                r4i = -2147483648;
                            }
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                            if (this.f > 0) {
                                r2i = getChildCount();
                                r1i = 0;
                                while (r3i < r2i) {
                                    r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                    if (r0i <= r1i) {
                                        r3i++;
                                        r1i = r0i;
                                    } else {
                                        r0i = r1i;
                                        r3i++;
                                        r1i = r0i;
                                    }
                                }
                                setMeasuredDimension(r7i, r1i);
                            } else {
                                setMeasuredDimension(r7i, r1i);
                            }
                        } else {
                            r0i = Math.min(r9_LayoutParams.width, r0i);
                            if (r9_LayoutParams.height == -2) {
                                if (r9_LayoutParams.height >= 0) {
                                }
                                this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                if (this.f > 0) {
                                    setMeasuredDimension(r7i, r1i);
                                } else {
                                    r2i = getChildCount();
                                    r1i = 0;
                                    while (r3i < r2i) {
                                        r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                        if (r0i <= r1i) {
                                            r0i = r1i;
                                        }
                                        r3i++;
                                        r1i = r0i;
                                    }
                                    setMeasuredDimension(r7i, r1i);
                                }
                            } else {
                                r4i = -2147483648;
                                if (r9_LayoutParams.height >= 0) {
                                }
                                this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                if (this.f > 0) {
                                    r2i = getChildCount();
                                    r1i = 0;
                                    while (r3i < r2i) {
                                        r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                        if (r0i <= r1i) {
                                            r3i++;
                                            r1i = r0i;
                                        } else {
                                            r0i = r1i;
                                            r3i++;
                                            r1i = r0i;
                                        }
                                    }
                                    setMeasuredDimension(r7i, r1i);
                                } else {
                                    setMeasuredDimension(r7i, r1i);
                                }
                            }
                        }
                    }
                }
            } else {
                r0i = a(this.a, r0i, r2i, 0);
                if (this.k == null || this.j != null) {
                    if (this.j != null) {
                        if (this.f > 0) {
                            r2i = getChildCount();
                            r1i = 0;
                            while (r3i < r2i) {
                                r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                if (r0i <= r1i) {
                                    r3i++;
                                    r1i = r0i;
                                } else {
                                    r0i = r1i;
                                    r3i++;
                                    r1i = r0i;
                                }
                            }
                            setMeasuredDimension(r7i, r1i);
                        } else {
                            setMeasuredDimension(r7i, r1i);
                        }
                    } else {
                        r9_LayoutParams = this.j.getLayoutParams();
                        if (r9_LayoutParams.width != -2) {
                        }
                        if (r9_LayoutParams.width >= 0) {
                            if (r9_LayoutParams.height == -2) {
                                if (r9_LayoutParams.height >= 0) {
                                }
                                this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                if (this.f > 0) {
                                    setMeasuredDimension(r7i, r1i);
                                } else {
                                    r2i = getChildCount();
                                    r1i = 0;
                                    while (r3i < r2i) {
                                        r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                        if (r0i <= r1i) {
                                            r0i = r1i;
                                        }
                                        r3i++;
                                        r1i = r0i;
                                    }
                                    setMeasuredDimension(r7i, r1i);
                                }
                            } else {
                                r4i = -2147483648;
                                if (r9_LayoutParams.height >= 0) {
                                }
                                this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                if (this.f > 0) {
                                    r2i = getChildCount();
                                    r1i = 0;
                                    while (r3i < r2i) {
                                        r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                        if (r0i <= r1i) {
                                            r3i++;
                                            r1i = r0i;
                                        } else {
                                            r0i = r1i;
                                            r3i++;
                                            r1i = r0i;
                                        }
                                    }
                                    setMeasuredDimension(r7i, r1i);
                                } else {
                                    setMeasuredDimension(r7i, r1i);
                                }
                            }
                        } else {
                            r0i = Math.min(r9_LayoutParams.width, r0i);
                            if (r9_LayoutParams.height == -2) {
                                r4i = -2147483648;
                            }
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                            if (this.f > 0) {
                                setMeasuredDimension(r7i, r1i);
                            } else {
                                r2i = getChildCount();
                                r1i = 0;
                                while (r3i < r2i) {
                                    r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                    if (r0i <= r1i) {
                                        r0i = r1i;
                                    }
                                    r3i++;
                                    r1i = r0i;
                                }
                                setMeasuredDimension(r7i, r1i);
                            }
                        }
                    }
                } else if (this.q) {
                    r0i = a(this.k, r0i, r2i, 0);
                    if (this.j != null) {
                        r9_LayoutParams = this.j.getLayoutParams();
                        if (r9_LayoutParams.width != -2) {
                        }
                        if (r9_LayoutParams.width >= 0) {
                            r0i = Math.min(r9_LayoutParams.width, r0i);
                        }
                        if (r9_LayoutParams.height == -2) {
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                        } else {
                            r4i = -2147483648;
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                        }
                    }
                    if (this.f > 0) {
                        r2i = getChildCount();
                        r1i = 0;
                        while (r3i < r2i) {
                            r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                            if (r0i <= r1i) {
                                r3i++;
                                r1i = r0i;
                            } else {
                                r0i = r1i;
                                r3i++;
                                r1i = r0i;
                            }
                        }
                        setMeasuredDimension(r7i, r1i);
                    } else {
                        setMeasuredDimension(r7i, r1i);
                    }
                } else {
                    this.k.measure(MeasureSpec.makeMeasureSpec(0, 0), r2i);
                    r9i = this.k.getMeasuredWidth();
                    if (r9i > r0i) {
                    }
                    if (r2i == 0) {
                        if (r2i != 0) {
                        }
                        this.k.setVisibility(r2i != 0 ? 0 : Base64.DONT_BREAK_LINES);
                        if (this.j != null) {
                            if (this.f > 0) {
                                setMeasuredDimension(r7i, r1i);
                            } else {
                                r2i = getChildCount();
                                r1i = 0;
                                while (r3i < r2i) {
                                    r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                    if (r0i <= r1i) {
                                        r0i = r1i;
                                    }
                                    r3i++;
                                    r1i = r0i;
                                }
                                setMeasuredDimension(r7i, r1i);
                            }
                        } else {
                            r9_LayoutParams = this.j.getLayoutParams();
                            if (r9_LayoutParams.width != -2) {
                            }
                            if (r9_LayoutParams.width >= 0) {
                                if (r9_LayoutParams.height == -2) {
                                    r4i = -2147483648;
                                }
                                if (r9_LayoutParams.height >= 0) {
                                }
                                this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                if (this.f > 0) {
                                    r2i = getChildCount();
                                    r1i = 0;
                                    while (r3i < r2i) {
                                        r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                        if (r0i <= r1i) {
                                            r3i++;
                                            r1i = r0i;
                                        } else {
                                            r0i = r1i;
                                            r3i++;
                                            r1i = r0i;
                                        }
                                    }
                                    setMeasuredDimension(r7i, r1i);
                                } else {
                                    setMeasuredDimension(r7i, r1i);
                                }
                            } else {
                                r0i = Math.min(r9_LayoutParams.width, r0i);
                                if (r9_LayoutParams.height == -2) {
                                    if (r9_LayoutParams.height >= 0) {
                                    }
                                    this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                    if (this.f > 0) {
                                        setMeasuredDimension(r7i, r1i);
                                    } else {
                                        r2i = getChildCount();
                                        r1i = 0;
                                        while (r3i < r2i) {
                                            r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                            if (r0i <= r1i) {
                                                r0i = r1i;
                                            }
                                            r3i++;
                                            r1i = r0i;
                                        }
                                        setMeasuredDimension(r7i, r1i);
                                    }
                                } else {
                                    r4i = -2147483648;
                                    if (r9_LayoutParams.height >= 0) {
                                    }
                                    this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                                    if (this.f > 0) {
                                        r2i = getChildCount();
                                        r1i = 0;
                                        while (r3i < r2i) {
                                            r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                            if (r0i <= r1i) {
                                                r3i++;
                                                r1i = r0i;
                                            } else {
                                                r0i = r1i;
                                                r3i++;
                                                r1i = r0i;
                                            }
                                        }
                                        setMeasuredDimension(r7i, r1i);
                                    } else {
                                        setMeasuredDimension(r7i, r1i);
                                    }
                                }
                            }
                        }
                    } else {
                        r0i -= r9i;
                        if (r2i != 0) {
                        }
                        this.k.setVisibility(r2i != 0 ? 0 : Base64.DONT_BREAK_LINES);
                        if (this.j != null) {
                            r9_LayoutParams = this.j.getLayoutParams();
                            if (r9_LayoutParams.width != -2) {
                            }
                            if (r9_LayoutParams.width >= 0) {
                                r0i = Math.min(r9_LayoutParams.width, r0i);
                            }
                            if (r9_LayoutParams.height == -2) {
                                r4i = -2147483648;
                            }
                            if (r9_LayoutParams.height >= 0) {
                            }
                            this.j.measure(MeasureSpec.makeMeasureSpec(r0i, r2i), MeasureSpec.makeMeasureSpec(r9_LayoutParams.height >= 0 ? Math.min(r9_LayoutParams.height, r6i) : r6i, r4i));
                        }
                        if (this.f > 0) {
                            setMeasuredDimension(r7i, r1i);
                        } else {
                            r2i = getChildCount();
                            r1i = 0;
                            while (r3i < r2i) {
                                r0i = getChildAt(r3i).getMeasuredHeight() + r8i;
                                if (r0i <= r1i) {
                                    r0i = r1i;
                                }
                                r3i++;
                                r1i = r0i;
                            }
                            setMeasuredDimension(r7i, r1i);
                        }
                    }
                }
            }
        }
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public void setContentHeight(int r1i) {
        this.f = r1i;
    }

    public void setCustomView(View r2_View) {
        if (this.j != null) {
            removeView(this.j);
        }
        this.j = r2_View;
        if (this.k != null) {
            removeView(this.k);
            this.k = null;
        }
        if (r2_View != null) {
            addView(r2_View);
        }
        requestLayout();
    }

    public void setSplitActionBar(boolean r6z) {
        if (this.d != r6z) {
            if (this.b != null) {
                LayoutParams r1_LayoutParams = new LayoutParams(-2, -1);
                ViewGroup r0_ViewGroup;
                if (r6z) {
                    this.b.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.b.setItemLimit(a.MAX_ACTIVITY_COUNT_UNLIMITED);
                    r1_LayoutParams.width = -1;
                    r1_LayoutParams.height = this.f;
                    this.a = (ActionMenuView) this.b.getMenuView(this);
                    this.a.setBackgroundDrawable(this.p);
                    r0_ViewGroup = (ViewGroup) this.a.getParent();
                    if (r0_ViewGroup != null) {
                        r0_ViewGroup.removeView(this.a);
                    }
                    this.c.addView(this.a, r1_LayoutParams);
                } else {
                    this.a = (ActionMenuView) this.b.getMenuView(this);
                    this.a.setBackgroundDrawable(null);
                    r0_ViewGroup = (ViewGroup) this.a.getParent();
                    if (r0_ViewGroup != null) {
                        r0_ViewGroup.removeView(this.a);
                    }
                    addView(this.a, r1_LayoutParams);
                }
            }
            super.setSplitActionBar(r6z);
        }
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer r1_ActionBarContainer) {
        super.setSplitView(r1_ActionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean r1z) {
        super.setSplitWhenNarrow(r1z);
    }

    public void setSubtitle(CharSequence r1_CharSequence) {
        this.h = r1_CharSequence;
        a();
    }

    public void setTitle(CharSequence r1_CharSequence) {
        this.g = r1_CharSequence;
        a();
    }

    public void setTitleOptional(boolean r2z) {
        if (r2z != this.q) {
            requestLayout();
        }
        this.q = r2z;
    }

    public /* bridge */ /* synthetic */ void setVisibility(int r1i) {
        super.setVisibility(r1i);
    }

    public boolean showOverflowMenu() {
        return this.b != null ? this.b.showOverflowMenu() : false;
    }
}
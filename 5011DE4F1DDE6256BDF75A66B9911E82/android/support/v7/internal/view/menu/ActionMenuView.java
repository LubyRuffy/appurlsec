package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.internal.widget.LinearLayoutICS;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutICS implements ItemInvoker, MenuView {
    private MenuBuilder a;
    private boolean b;
    private ActionMenuPresenter c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;

    public static interface ActionMenuChildView {
        public boolean needsDividerAfter();

        public boolean needsDividerBefore();
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        public int cellsUsed;
        public boolean expandable;
        public boolean expanded;
        public int extraPixels;
        public boolean isOverflowButton;
        public boolean preventEdgeOffset;

        public LayoutParams(int r2i, int r3i) {
            super(r2i, r3i);
            this.isOverflowButton = false;
        }

        public LayoutParams(int r1i, int r2i, boolean r3z) {
            super(r1i, r2i);
            this.isOverflowButton = r3z;
        }

        public LayoutParams(Context r1_Context, AttributeSet r2_AttributeSet) {
            super(r1_Context, r2_AttributeSet);
        }

        public LayoutParams(android.support.v7.internal.view.menu.ActionMenuView.LayoutParams r2_android_support_v7_internal_view_menu_ActionMenuView_LayoutParams) {
            super(r2_android_support_v7_internal_view_menu_ActionMenuView_LayoutParams);
            this.isOverflowButton = r2_android_support_v7_internal_view_menu_ActionMenuView_LayoutParams.isOverflowButton;
        }
    }

    public ActionMenuView(Context r2_Context) {
        this(r2_Context, null);
    }

    public ActionMenuView(Context r4_Context, AttributeSet r5_AttributeSet) {
        super(r4_Context, r5_AttributeSet);
        setBaselineAligned(false);
        float r0f = r4_Context.getResources().getDisplayMetrics().density;
        this.f = (int) (56.0f * r0f);
        this.g = (int) (r0f * 4.0f);
        TypedArray r0_TypedArray = r4_Context.obtainStyledAttributes(r5_AttributeSet, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.i = r0_TypedArray.getDimensionPixelSize(1, 0);
        r0_TypedArray.recycle();
    }

    static int a(View r8_View, int r9i, int r10i, int r11i, int r12i) {
        int r5i;
        int r1i;
        boolean r2z = false;
        LayoutParams r0_LayoutParams = (LayoutParams) r8_View.getLayoutParams();
        int r6i = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(r11i) - r12i, MeasureSpec.getMode(r11i));
        ActionMenuItemView r1_ActionMenuItemView = r8_View instanceof ActionMenuItemView ? (ActionMenuItemView) r8_View : null;
        r5i = (r1_ActionMenuItemView == null || (!r1_ActionMenuItemView.hasText())) ? 0 : 1;
        if (r10i > 0) {
            if (r5i == 0 || r10i >= 2) {
                r8_View.measure(MeasureSpec.makeMeasureSpec(r9i * r10i, ExploreByTouchHelper.INVALID_ID), r6i);
                int r7i = r8_View.getMeasuredWidth();
                r1i = r7i / r9i;
                if (r7i % r9i != 0) {
                    r1i++;
                }
                if (r5i == 0 || r1i >= 2) {
                } else {
                    r1i = 2;
                }
            } else {
                r1i = 0;
            }
        } else {
            r1i = 0;
        }
        if (r0_LayoutParams.isOverflowButton || r5i == 0) {
            r0_LayoutParams.expandable = r2z;
            r0_LayoutParams.cellsUsed = r1i;
            r8_View.measure(MeasureSpec.makeMeasureSpec(r1i * r9i, 1073741824), r6i);
            return r1i;
        } else {
            r2z = true;
            r0_LayoutParams.expandable = r2z;
            r0_LayoutParams.cellsUsed = r1i;
            r8_View.measure(MeasureSpec.makeMeasureSpec(r1i * r9i, 1073741824), r6i);
            return r1i;
        }
    }

    private void a(int r34i, int r35i) {
        int r8i;
        int r23i = MeasureSpec.getMode(r35i);
        int r6i = MeasureSpec.getSize(r34i);
        int r19i = MeasureSpec.getSize(r35i);
        int r7i = getPaddingRight() + getPaddingLeft();
        int r18i = getPaddingTop() + getPaddingBottom();
        r8i = r23i == 1073741824 ? MeasureSpec.makeMeasureSpec(r19i - r18i, 1073741824) : MeasureSpec.makeMeasureSpec(Math.min(this.i, r19i - r18i), ExploreByTouchHelper.INVALID_ID);
        int r24i = r6i - r7i;
        int r9i = r24i / this.f;
        int r5i = r24i % this.f;
        if (r9i == 0) {
            setMeasuredDimension(r24i, 0);
        } else {
            View r7_View;
            int r14i;
            LayoutParams r5_LayoutParams;
            long r20j;
            int r25i = this.f + r5i / r9i;
            int r16i = 0;
            int r15i = 0;
            int r10i = 0;
            r6i = 0;
            int r11i = 0;
            long r12j = 0;
            int r26i = getChildCount();
            int r17i = 0;
            while (r17i < r26i) {
                long r5j;
                int r12i;
                int r13i;
                r7_View = getChildAt(r17i);
                if (r7_View.getVisibility() == 8) {
                    r7i = r6i;
                    r5j = r12j;
                    r12i = r16i;
                    r13i = r9i;
                    r9i = r15i;
                } else {
                    boolean r20z = r7_View instanceof ActionMenuItemView;
                    r14i = r6i + 1;
                    if (r20z) {
                        r7_View.setPadding(this.g, 0, this.g, 0);
                    }
                    r5_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                    r5_LayoutParams.expanded = false;
                    r5_LayoutParams.extraPixels = 0;
                    r5_LayoutParams.cellsUsed = 0;
                    r5_LayoutParams.expandable = false;
                    r5_LayoutParams.leftMargin = 0;
                    r5_LayoutParams.rightMargin = 0;
                    boolean r6z;
                    if (r20z && ((ActionMenuItemView) r7_View).hasText()) {
                        r6z = true;
                        r5_LayoutParams.preventEdgeOffset = r6z;
                    } else {
                        r6z = false;
                        r5_LayoutParams.preventEdgeOffset = r6z;
                    }
                    int r20i = a(r7_View, r25i, r5_LayoutParams.isOverflowButton ? 1 : r9i, r8i, r18i);
                    r15i = Math.max(r15i, r20i);
                    r6i = r5_LayoutParams.expandable ? r10i + 1 : r10i;
                    r5i = r5_LayoutParams.isOverflowButton ? 1 : r11i;
                    r11i = r9i - r20i;
                    r7i = Math.max(r16i, r7_View.getMeasuredHeight());
                    if (r20i == 1) {
                        r12i = r7i;
                        r13i = r11i;
                        r7i = r14i;
                        r11i = r5i;
                        r9i = r15i;
                        r10i = r6i;
                        r5j = ((long) (1 << r17i)) | r12j;
                    } else {
                        r10i = r6i;
                        r9i = r15i;
                        r12i = r7i;
                        r13i = r11i;
                        r11i = r5i;
                        r7i = r14i;
                        r5j = r12j;
                    }
                }
                r17i++;
                r15i = r9i;
                r16i = r12i;
                r9i = r13i;
                r12j = r5j;
                r6i = r7i;
            }
            if (r11i == 0 || r6i != 2) {
                r7i = 0;
                r17i = 0;
                r20j = r12j;
                r18i = r9i;
            } else {
                r7i = 1;
                r17i = 0;
                r20j = r12j;
                r18i = r9i;
            }
            while (r10i > 0 && r18i > 0) {
                r14i = a.MAX_ACTIVITY_COUNT_UNLIMITED;
                r12j = 0;
                r9i = 0;
                int r22i = 0;
                while (r22i < r26i) {
                    r5_LayoutParams = (LayoutParams) getChildAt(r22i).getLayoutParams();
                    if (r5_LayoutParams.expandable) {
                        if (r5_LayoutParams.cellsUsed < r14i) {
                            r9i = r5_LayoutParams.cellsUsed;
                            r12j = (long) (1 << r22i);
                            r5i = 1;
                        } else if (r5_LayoutParams.cellsUsed == r14i) {
                            r12j |= (long) (1 << r22i);
                            r5i = r9i + 1;
                            r9i = r14i;
                        } else {
                            r5i = r9i;
                            r9i = r14i;
                        }
                    } else {
                        r5i = r9i;
                        r9i = r14i;
                    }
                    r22i++;
                    r14i = r9i;
                    r9i = r5i;
                }
                r20j |= r12j;
                if (r9i > r18i) {
                    r12j = r20j;
                    break;
                } else {
                    r22i = r14i + 1;
                    r14i = 0;
                    r9i = r18i;
                    long r17j = r20j;
                    while (r14i < r26i) {
                        View r20_View = getChildAt(r14i);
                        r5_LayoutParams = (LayoutParams) r20_View.getLayoutParams();
                        if ((((long) (1 << r14i)) & r12j) == 0) {
                            if (r5_LayoutParams.cellsUsed == r22i) {
                                r17j |= (long) (1 << r14i);
                                r5i = r9i;
                            } else {
                                r5i = r9i;
                            }
                        } else if (r7i != 0 && r5_LayoutParams.preventEdgeOffset && r9i == 1) {
                            r20_View.setPadding(this.g + r25i, 0, this.g, 0);
                            r5_LayoutParams.cellsUsed++;
                            r5_LayoutParams.expanded = true;
                            r5i = r9i - 1;
                        } else {
                            r5_LayoutParams.cellsUsed++;
                            r5_LayoutParams.expanded = true;
                            r5i = r9i - 1;
                        }
                        r14i++;
                        r9i = r5i;
                    }
                    r20j = r17j;
                    r17i = 1;
                    r18i = r9i;
                }
            }
            r12j = r20j;
            float r6f;
            float r5f;
            View r10_View;
            if (r11i == 0 && r6i == 1) {
                r5i = 1;
                if (r18i <= 0 || r12j == 0) {
                    r7i = r17i;
                    r9i = r18i;
                    if (r7i != 0) {
                        r6i = 0;
                    }
                    if (r23i != 1073741824) {
                        r16i = r19i;
                    }
                    setMeasuredDimension(r24i, r16i);
                    this.h = r9i * r25i;
                } else if (r18i < r6i - 1 || r5i != 0 || r15i > 1) {
                    r6f = (float) Long.bitCount(r12j);
                    if (r5i != 0) {
                    } else {
                        if ((1 & r12j) == 0 || ((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        } else {
                            r6f -= 0.5f;
                        }
                        r5f = (((((long) (1 << (r26i + -1))) & r12j) > 0 ? 1 : ((((long) (1 << (r26i + -1))) & r12j) == 0? 0 : -1)) == 0 || ((LayoutParams) getChildAt(r26i - 1).getLayoutParams()).preventEdgeOffset) ? r6f : r6f - 0.5f;
                        r6i = (r5f > 0.0f ? 1 : (r5f == 0.0f? 0 : -1)) <= 0 ? (int) (((float) (r18i * r25i)) / r5f) : 0;
                        r9i = 0;
                        r7i = r17i;
                        while (r9i < r26i) {
                            if ((((long) (1 << r9i)) & r12j) != 0) {
                                r5i = r7i;
                            } else {
                                r10_View = getChildAt(r9i);
                                r5_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                                if (r10_View instanceof ActionMenuItemView) {
                                    if (r5_LayoutParams.isOverflowButton) {
                                        if (r9i == 0) {
                                            r5_LayoutParams.leftMargin = r6i / 2;
                                        }
                                        if (r9i == r26i - 1) {
                                            r5i = r7i;
                                        } else {
                                            r5_LayoutParams.rightMargin = r6i / 2;
                                            r5i = r7i;
                                        }
                                    } else {
                                        r5_LayoutParams.extraPixels = r6i;
                                        r5_LayoutParams.expanded = true;
                                        r5_LayoutParams.rightMargin = (-r6i) / 2;
                                        r5i = 1;
                                    }
                                } else {
                                    r5_LayoutParams.extraPixels = r6i;
                                    r5_LayoutParams.expanded = true;
                                    if (r9i != 0 || r5_LayoutParams.preventEdgeOffset) {
                                        r5i = 1;
                                    } else {
                                        r5_LayoutParams.leftMargin = (-r6i) / 2;
                                        r5i = 1;
                                    }
                                }
                            }
                            r9i++;
                            r7i = r5i;
                        }
                        r9i = 0;
                        if (r7i != 0) {
                            if (r23i != 1073741824) {
                                setMeasuredDimension(r24i, r16i);
                                this.h = r9i * r25i;
                            } else {
                                r16i = r19i;
                                setMeasuredDimension(r24i, r16i);
                                this.h = r9i * r25i;
                            }
                        }
                    }
                    if ((r5f > 0.0f ? 1 : (r5f == 0.0f? 0 : -1)) <= 0) {
                    }
                    r9i = 0;
                    r7i = r17i;
                    while (r9i < r26i) {
                        if ((((long) (1 << r9i)) & r12j) != 0) {
                            r10_View = getChildAt(r9i);
                            r5_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                            if (r10_View instanceof ActionMenuItemView) {
                                if (r5_LayoutParams.isOverflowButton) {
                                    if (r9i == 0) {
                                        if (r9i == r26i - 1) {
                                            r5_LayoutParams.rightMargin = r6i / 2;
                                        }
                                        r5i = r7i;
                                    } else {
                                        r5_LayoutParams.leftMargin = r6i / 2;
                                        if (r9i == r26i - 1) {
                                            r5i = r7i;
                                        } else {
                                            r5_LayoutParams.rightMargin = r6i / 2;
                                            r5i = r7i;
                                        }
                                    }
                                } else {
                                    r5_LayoutParams.extraPixels = r6i;
                                    r5_LayoutParams.expanded = true;
                                    r5_LayoutParams.rightMargin = (-r6i) / 2;
                                    r5i = 1;
                                }
                            } else {
                                r5_LayoutParams.extraPixels = r6i;
                                r5_LayoutParams.expanded = true;
                                if (r9i != 0 || r5_LayoutParams.preventEdgeOffset) {
                                    r5i = 1;
                                } else {
                                    r5_LayoutParams.leftMargin = (-r6i) / 2;
                                    r5i = 1;
                                }
                            }
                        } else {
                            r5i = r7i;
                        }
                        r9i++;
                        r7i = r5i;
                    }
                    r9i = 0;
                    if (r7i != 0) {
                        r6i = 0;
                    }
                    if (r23i != 1073741824) {
                        r16i = r19i;
                    }
                    setMeasuredDimension(r24i, r16i);
                    this.h = r9i * r25i;
                } else {
                    r7i = r17i;
                    r9i = r18i;
                    if (r7i != 0) {
                        if (r23i != 1073741824) {
                            setMeasuredDimension(r24i, r16i);
                            this.h = r9i * r25i;
                        } else {
                            r16i = r19i;
                            setMeasuredDimension(r24i, r16i);
                            this.h = r9i * r25i;
                        }
                    }
                }
                r6i = 0;
                while (r6i < r26i) {
                    r7_View = getChildAt(r6i);
                    r5_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                    if (!r5_LayoutParams.expanded) {
                        r7_View.measure(MeasureSpec.makeMeasureSpec(r5_LayoutParams.extraPixels + r5_LayoutParams.cellsUsed * r25i, 1073741824), r8i);
                    }
                    r6i++;
                }
                if (r23i != 1073741824) {
                    r16i = r19i;
                }
                setMeasuredDimension(r24i, r16i);
                this.h = r9i * r25i;
            } else {
                r5i = 0;
                if (r18i <= 0 || r12j == 0) {
                    r7i = r17i;
                    r9i = r18i;
                    if (r7i != 0) {
                        r6i = 0;
                    }
                    if (r23i != 1073741824) {
                        setMeasuredDimension(r24i, r16i);
                        this.h = r9i * r25i;
                    } else {
                        r16i = r19i;
                        setMeasuredDimension(r24i, r16i);
                        this.h = r9i * r25i;
                    }
                } else if (r18i < r6i - 1 || r5i != 0 || r15i > 1) {
                    r6f = (float) Long.bitCount(r12j);
                    if (r5i != 0) {
                        if ((1 & r12j) == 0 || ((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        } else {
                            r6f -= 0.5f;
                        }
                        if (((((long) (1 << (r26i + -1))) & r12j) > 0 ? 1 : ((((long) (1 << (r26i + -1))) & r12j) == 0? 0 : -1)) == 0 || ((LayoutParams) getChildAt(r26i - 1).getLayoutParams()).preventEdgeOffset) {
                        }
                        if ((r5f > 0.0f ? 1 : (r5f == 0.0f? 0 : -1)) <= 0) {
                        }
                        r9i = 0;
                        r7i = r17i;
                        while (r9i < r26i) {
                            if ((((long) (1 << r9i)) & r12j) != 0) {
                                r5i = r7i;
                            } else {
                                r10_View = getChildAt(r9i);
                                r5_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                                if (r10_View instanceof ActionMenuItemView) {
                                    r5_LayoutParams.extraPixels = r6i;
                                    r5_LayoutParams.expanded = true;
                                    if (r9i != 0 || r5_LayoutParams.preventEdgeOffset) {
                                        r5i = 1;
                                    } else {
                                        r5_LayoutParams.leftMargin = (-r6i) / 2;
                                        r5i = 1;
                                    }
                                } else if (r5_LayoutParams.isOverflowButton) {
                                    r5_LayoutParams.extraPixels = r6i;
                                    r5_LayoutParams.expanded = true;
                                    r5_LayoutParams.rightMargin = (-r6i) / 2;
                                    r5i = 1;
                                } else {
                                    if (r9i == 0) {
                                        r5_LayoutParams.leftMargin = r6i / 2;
                                    }
                                    if (r9i == r26i - 1) {
                                        r5_LayoutParams.rightMargin = r6i / 2;
                                    }
                                    r5i = r7i;
                                }
                            }
                            r9i++;
                            r7i = r5i;
                        }
                        r9i = 0;
                        if (r7i != 0) {
                            if (r23i != 1073741824) {
                                r16i = r19i;
                            }
                            setMeasuredDimension(r24i, r16i);
                            this.h = r9i * r25i;
                        }
                    }
                    if ((r5f > 0.0f ? 1 : (r5f == 0.0f? 0 : -1)) <= 0) {
                    }
                    r9i = 0;
                    r7i = r17i;
                    while (r9i < r26i) {
                        if ((((long) (1 << r9i)) & r12j) != 0) {
                            r10_View = getChildAt(r9i);
                            r5_LayoutParams = (LayoutParams) r10_View.getLayoutParams();
                            if (r10_View instanceof ActionMenuItemView) {
                                if (r5_LayoutParams.isOverflowButton) {
                                    if (r9i == 0) {
                                        if (r9i == r26i - 1) {
                                            r5i = r7i;
                                        } else {
                                            r5_LayoutParams.rightMargin = r6i / 2;
                                            r5i = r7i;
                                        }
                                    } else {
                                        r5_LayoutParams.leftMargin = r6i / 2;
                                        if (r9i == r26i - 1) {
                                            r5_LayoutParams.rightMargin = r6i / 2;
                                        }
                                        r5i = r7i;
                                    }
                                } else {
                                    r5_LayoutParams.extraPixels = r6i;
                                    r5_LayoutParams.expanded = true;
                                    r5_LayoutParams.rightMargin = (-r6i) / 2;
                                    r5i = 1;
                                }
                            } else {
                                r5_LayoutParams.extraPixels = r6i;
                                r5_LayoutParams.expanded = true;
                                if (r9i != 0 || r5_LayoutParams.preventEdgeOffset) {
                                    r5i = 1;
                                } else {
                                    r5_LayoutParams.leftMargin = (-r6i) / 2;
                                    r5i = 1;
                                }
                            }
                        } else {
                            r5i = r7i;
                        }
                        r9i++;
                        r7i = r5i;
                    }
                    r9i = 0;
                    if (r7i != 0) {
                        r6i = 0;
                    }
                    if (r23i != 1073741824) {
                        setMeasuredDimension(r24i, r16i);
                        this.h = r9i * r25i;
                    } else {
                        r16i = r19i;
                        setMeasuredDimension(r24i, r16i);
                        this.h = r9i * r25i;
                    }
                } else {
                    r7i = r17i;
                    r9i = r18i;
                    if (r7i != 0) {
                        if (r23i != 1073741824) {
                            r16i = r19i;
                        }
                        setMeasuredDimension(r24i, r16i);
                        this.h = r9i * r25i;
                    }
                }
                r6i = 0;
                while (r6i < r26i) {
                    r7_View = getChildAt(r6i);
                    r5_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                    if (r5_LayoutParams.expanded) {
                        r6i++;
                    } else {
                        r7_View.measure(MeasureSpec.makeMeasureSpec(r5_LayoutParams.extraPixels + r5_LayoutParams.cellsUsed * r25i, 1073741824), r8i);
                        r6i++;
                    }
                }
                if (r23i != 1073741824) {
                    setMeasuredDimension(r24i, r16i);
                    this.h = r9i * r25i;
                } else {
                    r16i = r19i;
                    setMeasuredDimension(r24i, r16i);
                    this.h = r9i * r25i;
                }
            }
            r7i = r17i;
            r9i = r18i;
            if (r7i != 0) {
                r6i = 0;
                while (r6i < r26i) {
                    r7_View = getChildAt(r6i);
                    r5_LayoutParams = (LayoutParams) r7_View.getLayoutParams();
                    if (r5_LayoutParams.expanded) {
                        r6i++;
                    } else {
                        r7_View.measure(MeasureSpec.makeMeasureSpec(r5_LayoutParams.extraPixels + r5_LayoutParams.cellsUsed * r25i, 1073741824), r8i);
                        r6i++;
                    }
                }
            }
            if (r23i != 1073741824) {
                setMeasuredDimension(r24i, r16i);
                this.h = r9i * r25i;
            } else {
                r16i = r19i;
                setMeasuredDimension(r24i, r16i);
                this.h = r9i * r25i;
            }
        }
    }

    protected LayoutParams a() {
        LayoutParams r0_LayoutParams = new LayoutParams(-2, -2);
        r0_LayoutParams.gravity = 16;
        return r0_LayoutParams;
    }

    protected LayoutParams a(android.view.ViewGroup.LayoutParams r3_android_view_ViewGroup_LayoutParams) {
        if (!(r3_android_view_ViewGroup_LayoutParams instanceof LayoutParams)) {
            return a();
        }
        LayoutParams r0_LayoutParams = new LayoutParams((LayoutParams) r3_android_view_ViewGroup_LayoutParams);
        if (r0_LayoutParams.gravity > 0) {
            return r0_LayoutParams;
        }
        r0_LayoutParams.gravity = 16;
        return r0_LayoutParams;
    }

    protected boolean a(int r5i) {
        View r0_View = getChildAt(r5i - 1);
        View r1_View = getChildAt(r5i);
        boolean r2z = false;
        if (r5i >= getChildCount() || (!r0_View instanceof ActionMenuChildView)) {
            if (r5i <= 0 && !(r1_View instanceof ActionMenuChildView)) {
                return ((ActionMenuChildView) r1_View).needsDividerBefore() | r2z;
            }
        } else {
            r2z |= ((ActionMenuChildView) r0_View).needsDividerAfter();
            if (r5i <= 0 && !(r1_View instanceof ActionMenuChildView)) {
                return ((ActionMenuChildView) r1_View).needsDividerBefore() | r2z;
            }
        }
        return r2z;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return r2_android_view_ViewGroup_LayoutParams != null && r2_android_view_ViewGroup_LayoutParams instanceof LayoutParams;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r2_AccessibilityEvent) {
        return false;
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    public LayoutParams generateLayoutParams(AttributeSet r3_AttributeSet) {
        return new LayoutParams(getContext(), r3_AttributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return a(r2_android_view_ViewGroup_LayoutParams);
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams r2_android_view_ViewGroup_LayoutParams) {
        return a(r2_android_view_ViewGroup_LayoutParams);
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams r0_LayoutParams = a();
        r0_LayoutParams.isOverflowButton = true;
        return r0_LayoutParams;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void initialize(MenuBuilder r1_MenuBuilder) {
        this.a = r1_MenuBuilder;
    }

    public boolean invokeItem(MenuItemImpl r3_MenuItemImpl) {
        return this.a.performItemAction(r3_MenuItemImpl, 0);
    }

    public boolean isExpandedFormat() {
        return this.d;
    }

    public boolean isOverflowReserved() {
        return this.b;
    }

    public void onConfigurationChanged(Configuration r3_Configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(r3_Configuration);
        }
        this.c.updateMenuView(false);
        if (this.c == null || (!this.c.isOverflowMenuShowing())) {
        } else {
            this.c.hideOverflowMenu();
            this.c.showOverflowMenu();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.dismissPopupMenus();
    }

    protected void onLayout(boolean r15z, int r16i, int r17i, int r18i, int r19i) {
        if (this.d) {
            int r0i;
            LayoutParams r0_LayoutParams;
            int r9i;
            int r6i = getChildCount();
            int r7i = (r17i + r19i) / 2;
            int r8i = getSupportDividerWidth();
            int r4i = 0;
            int r3i = 0;
            int r2i = r18i - r16i - getPaddingRight() - getPaddingLeft();
            int r1i = 0;
            int r5i = 0;
            while (r5i < r6i) {
                View r9_View = getChildAt(r5i);
                if (r9_View.getVisibility() == 8) {
                    r0i = r1i;
                    r1i = r2i;
                    r2i = r3i;
                    r3i = r4i;
                } else {
                    r0_LayoutParams = (LayoutParams) r9_View.getLayoutParams();
                    if (r0_LayoutParams.isOverflowButton) {
                        r1i = r9_View.getMeasuredWidth();
                        if (a(r5i)) {
                            r1i += r8i;
                        }
                        int r10i = r9_View.getMeasuredHeight();
                        r0i = getWidth() - getPaddingRight() - r0_LayoutParams.rightMargin;
                        int r12i = r7i - r10i / 2;
                        r9_View.layout(r0i - r1i, r12i, r0i, r10i + r12i);
                        r1i = r2i - r1i;
                        r0i = 1;
                        r2i = r3i;
                        r3i = r4i;
                    } else {
                        r9i = r9_View.getMeasuredWidth() + r0_LayoutParams.leftMargin + r0_LayoutParams.rightMargin;
                        r0i = r4i + r9i;
                        r2i -= r9i;
                        if (a(r5i)) {
                            r0i += r8i;
                        }
                        r1i = r2i;
                        r2i = r3i + 1;
                        r3i = r0i;
                        r0i = r1i;
                    }
                }
                r5i++;
                r4i = r3i;
                r3i = r2i;
                r2i = r1i;
                r1i = r0i;
            }
            if (r6i == 1 && r1i == 0) {
                View r0_View = getChildAt(0);
                r1i = r0_View.getMeasuredWidth();
                r2i = r0_View.getMeasuredHeight();
                r3i = (r18i - r16i) / 2 - r1i / 2;
                r4i = r7i - r2i / 2;
                r0_View.layout(r3i, r4i, r1i + r3i, r2i + r4i);
            } else {
                r0i = r3i - (r1i != 0 ? 0 : 1);
                r3i = Math.max(0, r0i > 0 ? r2i / r0i : 0);
                r1i = getPaddingLeft();
                r2i = 0;
                while (r2i < r6i) {
                    View r4_View = getChildAt(r2i);
                    r0_LayoutParams = (LayoutParams) r4_View.getLayoutParams();
                    if (r4_View.getVisibility() != 8) {
                        if (r0_LayoutParams.isOverflowButton) {
                            r0i = r1i;
                        } else {
                            r1i += r0_LayoutParams.leftMargin;
                            r5i = r4_View.getMeasuredWidth();
                            r8i = r4_View.getMeasuredHeight();
                            r9i = r7i - r8i / 2;
                            r4_View.layout(r1i, r9i, r1i + r5i, r8i + r9i);
                            r0i = r0_LayoutParams.rightMargin + r5i + r3i + r1i;
                        }
                    } else {
                        r0i = r1i;
                    }
                    r2i++;
                    r1i = r0i;
                }
            }
        } else {
            super.onLayout(r15z, r16i, r17i, r18i, r19i);
        }
    }

    protected void onMeasure(int r6i, int r7i) {
        boolean r3z = this.d;
        this.d = MeasureSpec.getMode(r6i) == 1073741824;
        if (r3z != this.d) {
            this.e = 0;
        }
        int r0i = MeasureSpec.getMode(r6i);
        int r3i;
        int r1i;
        LayoutParams r0_LayoutParams;
        if ((!this.d) || this.a == null || r0i == this.e) {
            if (this.d) {
                r3i = getChildCount();
                r1i = 0;
                while (r1i < r3i) {
                    r0_LayoutParams = (LayoutParams) getChildAt(r1i).getLayoutParams();
                    r0_LayoutParams.rightMargin = 0;
                    r0_LayoutParams.leftMargin = 0;
                    r1i++;
                }
                super.onMeasure(r6i, r7i);
            } else {
                a(r6i, r7i);
            }
        } else {
            this.e = r0i;
            this.a.b(true);
            if (this.d) {
                r3i = getChildCount();
                r1i = 0;
                while (r1i < r3i) {
                    r0_LayoutParams = (LayoutParams) getChildAt(r1i).getLayoutParams();
                    r0_LayoutParams.rightMargin = 0;
                    r0_LayoutParams.leftMargin = 0;
                    r1i++;
                }
                super.onMeasure(r6i, r7i);
            } else {
                a(r6i, r7i);
            }
        }
    }

    public void setOverflowReserved(boolean r1z) {
        this.b = r1z;
    }

    public void setPresenter(ActionMenuPresenter r1_ActionMenuPresenter) {
        this.c = r1_ActionMenuPresenter;
    }
}
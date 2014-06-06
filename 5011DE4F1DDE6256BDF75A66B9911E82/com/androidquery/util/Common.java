package com.androidquery.util;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;
import java.io.File;
import java.util.Comparator;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewHeader;

public class Common implements TextWatcher, OnClickListener, OnLongClickListener, OnScrollListener, OnItemClickListener, OnItemSelectedListener, Runnable, Comparator<File> {
    private Object a;
    private String b;
    private Object[] c;
    private boolean d;
    private Class<?>[] e;
    private int f;
    private int g;
    private OnScrollListener h;
    private int i;
    private OnItemSelectedListener j;
    private boolean k;

    public Common() {
        this.g = 0;
        this.k = false;
    }

    private Object a(Object ... r7_ObjectA) {
        int r1i = 0;
        if (this.b != null) {
            Object[] r5_ObjectA;
            r5_ObjectA = this.c != null ? this.c : r7_ObjectA;
            Common r0_Common = this.a;
            if (r0_Common == null) {
                r0_Common = this;
            }
            return AQUtility.invokeHandler(r0_Common, this.b, this.d, true, this.e, r5_ObjectA);
        } else {
            if (this.f != 0) {
                switch (this.f) {
                    case XListViewHeader.STATE_READY:
                        AQUtility.store((File) this.c[r1i], (byte[]) this.c[1]);
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        AQUtility.cleanCache((File) this.c[r1i], ((Long) this.c[1]).longValue(), ((Long) this.c[2]).longValue());
                        break;
                }
            }
            return null;
        }
    }

    private void a(AbsListView r4_AbsListView, int r5i) {
        int r0i = r4_AbsListView.getCount();
        int r1i = r4_AbsListView.getLastVisiblePosition();
        if (r5i == 0 && r0i == r1i + 1) {
            if (r1i != this.i) {
                this.i = r1i;
                Object[] r0_ObjectA = new Object[2];
                r0_ObjectA[0] = r4_AbsListView;
                r0_ObjectA[1] = Integer.valueOf(r5i);
                a(r0_ObjectA);
            }
        } else {
            this.i = -1;
        }
    }

    private void a(ExpandableListView r14_ExpandableListView, int r15i) {
        r14_ExpandableListView.setTag(Constants.TAG_NUM, Integer.valueOf(r15i));
        if (r15i == 0) {
            int r7i = r14_ExpandableListView.getFirstVisiblePosition();
            int r8i = r14_ExpandableListView.getLastVisiblePosition() - r7i;
            ExpandableListAdapter r0_ExpandableListAdapter = r14_ExpandableListView.getExpandableListAdapter();
            int r6i = 0;
            while (r6i <= r8i) {
                long r9j = r14_ExpandableListView.getExpandableListPosition(r6i + r7i);
                int r1i = ExpandableListView.getPackedPositionGroup(r9j);
                int r2i = ExpandableListView.getPackedPositionChild(r9j);
                if (r1i >= 0) {
                    View r4_View = r14_ExpandableListView.getChildAt(r6i);
                    Long r3_Long = (Long) r4_View.getTag(Constants.TAG_NUM);
                    if (r3_Long == null || r3_Long.longValue() != r9j) {
                        r6i++;
                    } else {
                        if (r2i == -1) {
                            r0_ExpandableListAdapter.getGroupView(r1i, r14_ExpandableListView.isGroupExpanded(r1i), r4_View, r14_ExpandableListView);
                        } else {
                            r0_ExpandableListAdapter.getChildView(r1i, r2i, r2i == r0_ExpandableListAdapter.getChildrenCount(r1i) + -1, r4_View, r14_ExpandableListView);
                        }
                        r4_View.setTag(Constants.TAG_NUM, null);
                    }
                }
                r6i++;
            }
        }
    }

    private static boolean a(int r5i, View r6_View, ViewGroup r7_ViewGroup, String r8_String) {
        if (r8_String == null || BitmapAjaxCallback.isMemoryCached(r8_String)) {
            return false;
        }
        Gallery r7_Gallery = (Gallery) r7_ViewGroup;
        Integer r0_Integer = (Integer) r7_Gallery.getTag(Constants.TAG_NUM);
        if (r0_Integer == null) {
            r0_Integer = Integer.valueOf(0);
            r7_Gallery.setTag(Constants.TAG_NUM, Integer.valueOf(0));
            r7_Gallery.setCallbackDuringFling(false);
            new Common().listen(r7_Gallery);
        }
        int r3i = (r7_Gallery.getLastVisiblePosition() - r7_Gallery.getFirstVisiblePosition()) / 2 + 1;
        int r2i = r0_Integer.intValue() - r3i;
        int r0i = r0_Integer.intValue() + r3i;
        if (r2i < 0) {
            r0i -= r2i;
            r2i = 0;
        }
        if (r5i < r2i || r5i > r0i) {
            r6_View.setTag(Constants.TAG_NUM, null);
            return true;
        } else {
            r6_View.setTag(Constants.TAG_NUM, Integer.valueOf(r5i));
            return false;
        }
    }

    private void b(AbsListView r10_AbsListView, int r11i) {
        r10_AbsListView.setTag(Constants.TAG_NUM, Integer.valueOf(r11i));
        if (r11i == 0) {
            int r3i = r10_AbsListView.getFirstVisiblePosition();
            int r4i = r10_AbsListView.getLastVisiblePosition() - r3i;
            ListAdapter r0_ListAdapter = (ListAdapter) r10_AbsListView.getAdapter();
            int r2i = 0;
            while (r2i <= r4i) {
                long r5j = (long) (r2i + r3i);
                View r7_View = r10_AbsListView.getChildAt(r2i);
                if (((Number) r7_View.getTag(Constants.TAG_NUM)) != null) {
                    r0_ListAdapter.getView((int) r5j, r7_View, r10_AbsListView);
                    r7_View.setTag(Constants.TAG_NUM, null);
                }
                r2i++;
            }
        }
    }

    public static boolean shouldDelay(int r6i, int r7i, View r8_View, ViewGroup r9_ViewGroup, String r10_String) {
        if (r10_String == null || BitmapAjaxCallback.isMemoryCached(r10_String)) {
            return false;
        }
        AbsListView r0_AbsListView = (AbsListView) r9_ViewGroup;
        if (((OnScrollListener) r9_ViewGroup.getTag(Constants.TAG_SCROLL_LISTENER)) == null) {
            OnScrollListener r1_OnScrollListener = new Common();
            r0_AbsListView.setOnScrollListener(r1_OnScrollListener);
            r9_ViewGroup.setTag(Constants.TAG_SCROLL_LISTENER, r1_OnScrollListener);
        }
        Integer r0_Integer = (Integer) r0_AbsListView.getTag(Constants.TAG_NUM);
        if (r0_Integer == null || r0_Integer.intValue() == 0 || r0_Integer.intValue() == 1) {
            return false;
        }
        long r0j = (long) r7i;
        if (r9_ViewGroup instanceof ExpandableListView) {
            r0j = ExpandableListView.getPackedPositionForChild(r6i, r7i);
        }
        r8_View.setTag(Constants.TAG_NUM, Long.valueOf(r0j));
        return true;
    }

    public static boolean shouldDelay(int r1i, View r2_View, ViewGroup r3_ViewGroup, String r4_String) {
        return r3_ViewGroup instanceof Gallery ? a(r1i, r2_View, r3_ViewGroup, r4_String) : shouldDelay((int)RequestListener.DEFAULT_LOADED_SIZE, r1i, r2_View, r3_ViewGroup, r4_String);
    }

    public static boolean shouldDelay(View r1_View, ViewGroup r2_ViewGroup, String r3_String, float r4f, boolean r5z) {
        return shouldDelay(-1, r1_View, r2_ViewGroup, r3_String);
    }

    public static void showProgress(Object r5_Object, String r6_String, boolean r7z) {
        if (r5_Object != null) {
            if (r5_Object instanceof View) {
                ProgressBar r5_ProgressBar;
                View r0_View = (View) r5_Object;
                r5_ProgressBar = r5_Object instanceof ProgressBar ? (ProgressBar) r5_Object : null;
                if (r7z) {
                    r0_View.setTag(Constants.TAG_URL, r6_String);
                    r0_View.setVisibility(0);
                    if (r5_ProgressBar != null) {
                        r5_ProgressBar.setProgress(0);
                        r5_ProgressBar.setMax(100);
                    }
                } else {
                    Object r2_Object = r0_View.getTag(Constants.TAG_URL);
                    if (r2_Object == null || r2_Object.equals(r6_String)) {
                        r0_View.setTag(Constants.TAG_URL, null);
                        if (r5_ProgressBar == null || r5_ProgressBar.isIndeterminate()) {
                            r0_View.setVisibility(Base64.DONT_BREAK_LINES);
                        }
                    }
                }
            } else if (r5_Object instanceof Dialog) {
                Dialog r5_Dialog = (Dialog) r5_Object;
                AQuery r0_AQuery = new AQuery(r5_Dialog.getContext());
                if (r7z) {
                    r0_AQuery.show(r5_Dialog);
                } else {
                    r0_AQuery.dismiss(r5_Dialog);
                }
            } else if (r5_Object instanceof Activity) {
                Activity r5_Activity = (Activity) r5_Object;
                r5_Activity.setProgressBarIndeterminateVisibility(r7z);
                r5_Activity.setProgressBarVisibility(r7z);
                if (r7z) {
                    r5_Activity.setProgress(0);
                }
            }
        }
    }

    public void afterTextChanged(Editable r1_Editable) {
    }

    public void beforeTextChanged(CharSequence r1_CharSequence, int r2i, int r3i, int r4i) {
    }

    public int compare(File r6_File, File r7_File) {
        long r0j = r6_File.lastModified();
        long r2j = r7_File.lastModified();
        if (r2j > r0j) {
            return 1;
        }
        if (r2j == r0j) {
            return 0;
        }
        return -1;
    }

    public Common forward(Object r1_Object, String r2_String, boolean r3z, Class<?>[] r4_Class_A) {
        this.a = r1_Object;
        this.b = r2_String;
        this.d = r3z;
        this.e = r4_Class_A;
        return this;
    }

    public void forward(OnScrollListener r1_OnScrollListener) {
        this.h = r1_OnScrollListener;
    }

    public int getScrollState() {
        return this.g;
    }

    public void listen(Gallery r2_Gallery) {
        this.j = r2_Gallery.getOnItemSelectedListener();
        this.k = true;
        r2_Gallery.setOnItemSelectedListener(this);
    }

    public Common method(int r1i, Object ... r2_ObjectA) {
        this.f = r1i;
        this.c = r2_ObjectA;
        return this;
    }

    public void onClick(View r3_View) {
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = r3_View;
        a(r0_ObjectA);
    }

    public void onItemClick(AdapterView<?> r4_AdapterView_, View r5_View, int r6i, long r7j) {
        Object[] r0_ObjectA = new Object[4];
        r0_ObjectA[0] = r4_AdapterView_;
        r0_ObjectA[1] = r5_View;
        r0_ObjectA[2] = Integer.valueOf(r6i);
        r0_ObjectA[3] = Long.valueOf(r7j);
        a(r0_ObjectA);
    }

    public void onItemSelected(AdapterView<?> r9_AdapterView_, View r10_View, int r11i, long r12j) {
        Object[] r0_ObjectA = new Object[4];
        r0_ObjectA[0] = r9_AdapterView_;
        r0_ObjectA[1] = r10_View;
        r0_ObjectA[2] = Integer.valueOf(r11i);
        r0_ObjectA[3] = Long.valueOf(r12j);
        a(r0_ObjectA);
        if (this.j != null) {
            this.j.onItemSelected(r9_AdapterView_, r10_View, r11i, r12j);
        }
        if ((!this.k) || ((Integer) r9_AdapterView_.getTag(Constants.TAG_NUM)).intValue() == r11i) {
        } else {
            Adapter r2_Adapter = r9_AdapterView_.getAdapter();
            r9_AdapterView_.setTag(Constants.TAG_NUM, Integer.valueOf(r11i));
            int r3i = r9_AdapterView_.getChildCount();
            int r4i = r9_AdapterView_.getFirstVisiblePosition();
            int r1i = 0;
            while (r1i < r3i) {
                View r5_View = r9_AdapterView_.getChildAt(r1i);
                int r6i = r4i + r1i;
                Integer r0_Integer = (Integer) r5_View.getTag(Constants.TAG_NUM);
                if (r0_Integer == null || r0_Integer.intValue() != r6i) {
                    r2_Adapter.getView(r6i, r5_View, r9_AdapterView_);
                    r1i++;
                } else {
                    r1i++;
                }
            }
        }
    }

    public boolean onLongClick(View r4_View) {
        Object[] r0_ObjectA = new Object[1];
        r0_ObjectA[0] = r4_View;
        Object r0_Object = a(r0_ObjectA);
        return r0_Object instanceof Boolean ? ((Boolean) r0_Object).booleanValue() : false;
    }

    public void onNothingSelected(AdapterView<?> r2_AdapterView_) {
        if (this.j != null) {
            this.j.onNothingSelected(r2_AdapterView_);
        }
    }

    public void onScroll(AbsListView r2_AbsListView, int r3i, int r4i, int r5i) {
        a(r2_AbsListView, this.g);
        if (this.h != null) {
            this.h.onScroll(r2_AbsListView, r3i, r4i, r5i);
        }
    }

    public void onScrollStateChanged(AbsListView r2_AbsListView, int r3i) {
        this.g = r3i;
        a(r2_AbsListView, r3i);
        if (r2_AbsListView instanceof ExpandableListView) {
            a((ExpandableListView) r2_AbsListView, r3i);
        } else {
            b(r2_AbsListView, r3i);
        }
        if (this.h != null) {
            this.h.onScrollStateChanged(r2_AbsListView, r3i);
        }
    }

    public void onTextChanged(CharSequence r4_CharSequence, int r5i, int r6i, int r7i) {
        Object[] r0_ObjectA = new Object[4];
        r0_ObjectA[0] = r4_CharSequence;
        r0_ObjectA[1] = Integer.valueOf(r5i);
        r0_ObjectA[2] = Integer.valueOf(r6i);
        r0_ObjectA[3] = Integer.valueOf(r7i);
        a(r0_ObjectA);
    }

    public void run() {
        a(new Object[0]);
    }
}
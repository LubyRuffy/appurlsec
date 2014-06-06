package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.Base64;

public class ListFragment extends Fragment {
    private final Runnable Y;
    private final OnItemClickListener Z;
    ListAdapter a;
    ListView b;
    View c;
    TextView d;
    View e;
    View f;
    CharSequence g;
    boolean h;
    private final Handler i;

    public ListFragment() {
        this.i = new Handler();
        this.Y = new u(this);
        this.Z = new v(this);
    }

    private void a(boolean r7z, boolean r8z) {
        l();
        if (this.e == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else if (this.h == r7z) {
        } else {
            this.h = r7z;
            if (r7z) {
                if (r8z) {
                    this.e.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432577));
                    this.f.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432576));
                } else {
                    this.e.clearAnimation();
                    this.f.clearAnimation();
                }
                this.e.setVisibility(Base64.DONT_BREAK_LINES);
                this.f.setVisibility(0);
            } else {
                if (r8z) {
                    this.e.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432576));
                    this.f.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432577));
                } else {
                    this.e.clearAnimation();
                    this.f.clearAnimation();
                }
                this.e.setVisibility(0);
                this.f.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
    }

    private void l() {
        if (this.b != null) {
        } else {
            View r0_View = getView();
            if (r0_View == null) {
                throw new IllegalStateException("Content view not yet created");
            } else {
                if (r0_View instanceof ListView) {
                    this.b = (ListView) r0_View;
                } else {
                    this.d = (TextView) r0_View.findViewById(16711681);
                    if (this.d == null) {
                        this.c = r0_View.findViewById(16908292);
                    } else {
                        this.d.setVisibility(Base64.DONT_BREAK_LINES);
                    }
                    this.e = r0_View.findViewById(16711682);
                    this.f = r0_View.findViewById(16711683);
                    r0_View = r0_View.findViewById(16908298);
                    if (r0_View instanceof ListView) {
                        this.b = (ListView) r0_View;
                        if (this.c != null) {
                            this.b.setEmptyView(this.c);
                        } else if (this.g != null) {
                            this.d.setText(this.g);
                            this.b.setEmptyView(this.d);
                        }
                    } else if (r0_View == null) {
                        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                    } else {
                        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                    }
                }
                this.h = true;
                this.b.setOnItemClickListener(this.Z);
                if (this.a != null) {
                    ListAdapter r0_ListAdapter = this.a;
                    this.a = null;
                    setListAdapter(r0_ListAdapter);
                } else if (this.e != null) {
                    a(false, false);
                }
                this.i.post(this.Y);
            }
        }
    }

    public ListAdapter getListAdapter() {
        return this.a;
    }

    public ListView getListView() {
        l();
        return this.b;
    }

    public long getSelectedItemId() {
        l();
        return this.b.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        l();
        return this.b.getSelectedItemPosition();
    }

    public View onCreateView(LayoutInflater r10_LayoutInflater, ViewGroup r11_ViewGroup, Bundle r12_Bundle) {
        Context r0_Context = getActivity();
        View r1_View = new FrameLayout(r0_Context);
        View r2_View = new LinearLayout(r0_Context);
        r2_View.setId(16711682);
        r2_View.setOrientation(1);
        r2_View.setVisibility(Base64.DONT_BREAK_LINES);
        r2_View.setGravity(R.styleable.ActionBar_progressBarPadding);
        r2_View.addView(new ProgressBar(r0_Context, null, 16842874), new LayoutParams(-2, -2));
        r1_View.addView(r2_View, new LayoutParams(-1, -1));
        r2_View = new FrameLayout(r0_Context);
        r2_View.setId(16711683);
        View r0_View = new TextView(getActivity());
        r0_View.setId(16711681);
        r0_View.setGravity(R.styleable.ActionBar_progressBarPadding);
        r2_View.addView(r0_View, new LayoutParams(-1, -1));
        r0_View = new ListView(getActivity());
        r0_View.setId(16908298);
        r0_View.setDrawSelectorOnTop(false);
        r2_View.addView(r0_View, new LayoutParams(-1, -1));
        r1_View.addView(r2_View, new LayoutParams(-1, -1));
        r1_View.setLayoutParams(new LayoutParams(-1, -1));
        return r1_View;
    }

    public void onDestroyView() {
        this.i.removeCallbacks(this.Y);
        this.b = null;
        this.h = false;
        this.f = null;
        this.e = null;
        this.c = null;
        this.d = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView r1_ListView, View r2_View, int r3i, long r4j) {
    }

    public void onViewCreated(View r1_View, Bundle r2_Bundle) {
        super.onViewCreated(r1_View, r2_Bundle);
        l();
    }

    public void setEmptyText(CharSequence r3_CharSequence) {
        l();
        if (this.d == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else {
            this.d.setText(r3_CharSequence);
            if (this.g == null) {
                this.b.setEmptyView(this.d);
            }
            this.g = r3_CharSequence;
        }
    }

    public void setListAdapter(ListAdapter r5_ListAdapter) {
        boolean r2z = false;
        int r0i = this.a != null ? 1 : 0;
        this.a = r5_ListAdapter;
        if (this.b != null) {
            this.b.setAdapter(r5_ListAdapter);
            if (this.h || r0i != 0) {
            } else {
                if (getView().getWindowToken() != null) {
                    r2z = true;
                }
                a(true, r2z);
            }
        }
    }

    public void setListShown(boolean r2z) {
        a(r2z, true);
    }

    public void setListShownNoAnimation(boolean r2z) {
        a(r2z, false);
    }

    public void setSelection(int r2i) {
        l();
        this.b.setSelection(r2i);
    }
}
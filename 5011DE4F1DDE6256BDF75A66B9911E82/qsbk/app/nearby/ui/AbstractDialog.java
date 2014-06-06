package qsbk.app.nearby.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.Base64;

public abstract class AbstractDialog extends Dialog {
    protected boolean a;
    protected CharSequence b;
    protected CharSequence c;
    protected CharSequence d;
    protected CharSequence e;
    protected Drawable f;
    protected OnClickListener g;
    protected OnClickListener h;
    protected LinearLayout i;
    protected LinearLayout j;
    protected LinearLayout k;
    protected TextView l;
    protected ImageView m;
    protected TextView n;
    protected TextView o;
    protected FrameLayout p;
    View q;
    View r;
    View s;
    private View t;

    public static abstract class AbstractBuilder {
        protected Context a;
        protected Drawable b;
        protected CharSequence c;
        protected CharSequence d;
        protected CharSequence e;
        protected CharSequence f;
        protected boolean g;
        protected OnClickListener h;
        protected OnClickListener i;

        public AbstractBuilder(Context r2_Context) {
            this.b = null;
            this.g = true;
            this.a = r2_Context;
        }

        protected abstract int a();

        protected abstract AbstractDialog a(Context r1_Context, int r2i);

        public AbstractDialog create() {
            View r0_View = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(a(), null);
            AbstractDialog r1_AbstractDialog = a(this.a, R.style.DefaultAlertDialog);
            r1_AbstractDialog.addContentView(r0_View, new LayoutParams(-1, -2));
            r1_AbstractDialog.setTitle(this.c);
            r1_AbstractDialog.setIcon(this.b);
            r1_AbstractDialog.setMessage(this.d);
            r1_AbstractDialog.setCancelable(this.g);
            r1_AbstractDialog.setPositiveButton(this.e, this.h);
            r1_AbstractDialog.setNegativeButton(this.f, this.i);
            r1_AbstractDialog.c();
            return r1_AbstractDialog;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setCancelable(boolean r1z) {
            this.g = r1z;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setIcon(int r2i) {
            this.b = this.a.getResources().getDrawable(r2i);
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setIcon(Drawable r1_Drawable) {
            this.b = r1_Drawable;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setMessage(int r2i) {
            this.d = (String) this.a.getText(r2i);
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setMessage(CharSequence r1_CharSequence) {
            this.d = r1_CharSequence;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setNegativeButton(int r2i, OnClickListener r3_OnClickListener) {
            this.f = (String) this.a.getText(r2i);
            this.i = r3_OnClickListener;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setNegativeButton(CharSequence r1_CharSequence, OnClickListener r2_OnClickListener) {
            this.f = r1_CharSequence;
            this.i = r2_OnClickListener;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setPositiveButton(int r2i, OnClickListener r3_OnClickListener) {
            this.e = (String) this.a.getText(r2i);
            this.h = r3_OnClickListener;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setPositiveButton(CharSequence r1_CharSequence, OnClickListener r2_OnClickListener) {
            this.e = r1_CharSequence;
            this.h = r2_OnClickListener;
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setTitle(int r2i) {
            this.c = this.a.getString(r2i);
            return this;
        }

        public qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder setTitle(CharSequence r1_CharSequence) {
            this.c = r1_CharSequence;
            return this;
        }

        public AbstractDialog show() {
            AbstractDialog r0_AbstractDialog = create();
            r0_AbstractDialog.show();
            return r0_AbstractDialog;
        }
    }

    protected static enum DialogType {
        Alert,
        Edit,
        List,
        Time;


        static {
            Alert = new DialogType("Alert", 0);
            Edit = new DialogType("Edit", 1);
            List = new DialogType("List", 2);
            Time = new DialogType("Time", 3);
            DialogType[] r0_DialogTypeA = new DialogType[4];
            r0_DialogTypeA[0] = Alert;
            r0_DialogTypeA[1] = Edit;
            r0_DialogTypeA[2] = List;
            r0_DialogTypeA[3] = Time;
            a = r0_DialogTypeA;
        }
    }

    public static enum ListType {
        Single,
        Mutiple,
        Direct;


        static {
            Single = new qsbk.app.nearby.ui.AbstractDialog.ListType("Single", 0);
            Mutiple = new qsbk.app.nearby.ui.AbstractDialog.ListType("Mutiple", 1);
            Direct = new qsbk.app.nearby.ui.AbstractDialog.ListType("Direct", 2);
            qsbk.app.nearby.ui.AbstractDialog.ListType[] r0_qsbk_app_nearby_ui_AbstractDialog_ListTypeA = new qsbk.app.nearby.ui.AbstractDialog.ListType[3];
            r0_qsbk_app_nearby_ui_AbstractDialog_ListTypeA[0] = Single;
            r0_qsbk_app_nearby_ui_AbstractDialog_ListTypeA[1] = Mutiple;
            r0_qsbk_app_nearby_ui_AbstractDialog_ListTypeA[2] = Direct;
            a = r0_qsbk_app_nearby_ui_AbstractDialog_ListTypeA;
        }
    }

    public AbstractDialog(Context r3_Context) {
        super(r3_Context);
        this.a = true;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.t = null;
    }

    public AbstractDialog(Context r3_Context, int r4i) {
        super(r3_Context, r4i);
        this.a = true;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.t = null;
    }

    public AbstractDialog(Context r3_Context, boolean r4z, OnCancelListener r5_OnCancelListener) {
        super(r3_Context, r4z, r5_OnCancelListener);
        this.a = true;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.m = null;
        this.t = null;
    }

    protected void a() {
        this.t.requestFocus();
        this.t.invalidate();
    }

    protected abstract void a(View r1_View);

    protected void a(TextView r4_TextView) {
        LinearLayout.LayoutParams r0_LinearLayout_LayoutParams = (LinearLayout.LayoutParams) r4_TextView.getLayoutParams();
        r0_LinearLayout_LayoutParams.gravity = 1;
        r0_LinearLayout_LayoutParams.weight = 0.5f;
        r4_TextView.setLayoutParams(r0_LinearLayout_LayoutParams);
        if (this.q != null) {
            this.q.setVisibility(0);
        }
        if (this.r != null) {
            this.r.setVisibility(0);
        }
        if (this.s != null) {
            this.s.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    public void addContentView(View r2_View, LayoutParams r3_LayoutParams) {
        this.i = (LinearLayout) r2_View.findViewById(R.id.topPanel);
        this.j = (LinearLayout) r2_View.findViewById(R.id.contentPanel);
        this.k = (LinearLayout) r2_View.findViewById(R.id.buttonPanel);
        this.l = (TextView) r2_View.findViewById(R.id.tvAlertTitle);
        this.m = (ImageView) r2_View.findViewById(R.id.ivAlertIcon);
        this.n = (TextView) r2_View.findViewById(R.id.btnPositiveButton);
        this.o = (TextView) r2_View.findViewById(R.id.btnNegativeButton);
        this.q = r2_View.findViewById(R.id.leftSpacer);
        this.r = r2_View.findViewById(R.id.rightSpacer);
        this.s = r2_View.findViewById(R.id.middle_divider);
        this.p = (FrameLayout) r2_View.findViewById(R.id.custom);
        a(this.j);
        this.t = r2_View;
        super.addContentView(r2_View, r3_LayoutParams);
    }

    protected abstract void b();

    protected void c() {
        if (this.b == null || this.l == null) {
            this.i.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.l.setText(this.b);
        }
        if (this.m != null) {
            if (this.f != null) {
                this.m.setImageDrawable(this.f);
            } else {
                this.m.setVisibility(Base64.DONT_BREAK_LINES);
            }
        }
        if (this.d != null) {
            this.n.setText(this.d);
            if (this.g != null) {
                this.n.setOnClickListener(new a(this));
            }
        } else {
            this.n.setVisibility(Base64.DONT_BREAK_LINES);
            a(this.o);
        }
        if (this.e != null) {
            this.o.setText(this.e);
            if (this.h != null) {
                this.o.setOnClickListener(new b(this));
            }
        } else {
            this.o.setVisibility(Base64.DONT_BREAK_LINES);
            a(this.n);
        }
        if (this.e == null && this.d == null) {
            this.k.setVisibility(Base64.DONT_BREAK_LINES);
            b();
        } else {
            b();
        }
    }

    public abstract DialogType getDialogType();

    public void onBackPressed() {
        if (this.a) {
            super.onBackPressed();
        }
    }

    public abstract void resetContent();

    public abstract void saveContent();

    public void setIcon(int r3i) {
        this.f = getContext().getResources().getDrawable(r3i);
        if (this.f != null) {
            this.m.setImageDrawable(this.f);
        }
    }

    public void setIcon(Drawable r3_Drawable) {
        this.f = r3_Drawable;
        if (this.f != null) {
            this.m.setImageDrawable(this.f);
        }
    }

    public void setMessage(CharSequence r1_CharSequence) {
        this.c = r1_CharSequence;
    }

    public void setNegativeButton(int r2i, OnClickListener r3_OnClickListener) {
        this.e = getContext().getResources().getString(r2i);
        this.h = r3_OnClickListener;
    }

    public void setNegativeButton(CharSequence r1_CharSequence, OnClickListener r2_OnClickListener) {
        this.e = r1_CharSequence;
        this.h = r2_OnClickListener;
    }

    public void setPositiveButton(int r2i, OnClickListener r3_OnClickListener) {
        this.d = getContext().getResources().getString(r2i);
        this.g = r3_OnClickListener;
    }

    public void setPositiveButton(CharSequence r1_CharSequence, OnClickListener r2_OnClickListener) {
        this.d = r1_CharSequence;
        this.g = r2_OnClickListener;
    }

    public void setTitle(CharSequence r2_CharSequence) {
        this.b = r2_CharSequence;
        if (this.l != null) {
            this.l.setText(r2_CharSequence);
        }
    }
}
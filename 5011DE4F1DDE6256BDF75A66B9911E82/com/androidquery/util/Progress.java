package com.androidquery.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import com.androidquery.AQuery;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpClient;

public class Progress implements Runnable {
    private ProgressBar a;
    private ProgressDialog b;
    private Activity c;
    private View d;
    private boolean e;
    private int f;
    private int g;
    private String h;

    public Progress(Object r2_Object) {
        if (r2_Object instanceof ProgressBar) {
            this.a = (ProgressBar) r2_Object;
        } else if (r2_Object instanceof ProgressDialog) {
            this.b = (ProgressDialog) r2_Object;
        } else if (r2_Object instanceof Activity) {
            this.c = (Activity) r2_Object;
        } else {
            if (r2_Object instanceof View) {
                this.d = (View) r2_Object;
            }
        }
    }

    private void a(String r5_String) {
        if (this.b != null) {
            new AQuery(this.b.getContext()).dismiss(this.b);
        }
        if (this.c != null) {
            this.c.setProgressBarIndeterminateVisibility(false);
            this.c.setProgressBarVisibility(false);
        }
        if (this.a != null) {
            this.a.setTag(Constants.TAG_URL, r5_String);
            this.a.setVisibility(0);
        }
        View r0_View = this.a;
        if (r0_View == null) {
            r0_View = this.d;
        }
        if (r0_View != null) {
            Object r1_Object = r0_View.getTag(Constants.TAG_URL);
            if (r1_Object == null || r1_Object.equals(r5_String)) {
                r0_View.setTag(Constants.TAG_URL, null);
                if (this.a == null || (!this.a.isIndeterminate())) {
                } else {
                    r0_View.setVisibility(Base64.DONT_BREAK_LINES);
                }
            }
        }
    }

    public void done() {
        if (this.a != null) {
            this.a.setProgress(this.a.getMax());
        }
        if (this.b != null) {
            this.b.setProgress(this.b.getMax());
        }
        if (this.c != null) {
            this.c.setProgress(HttpClient.RESP_CODE_LOCAL_ERROR);
        }
    }

    public void hide(String r2_String) {
        if (AQUtility.isUIThread()) {
            a(r2_String);
        } else {
            this.h = r2_String;
            AQUtility.post(this);
        }
    }

    public void increment(int r5i) {
        int r1i = 1;
        if (this.a != null) {
            this.a.incrementProgressBy(this.e ? 1 : r5i);
        }
        if (this.b != null) {
            ProgressDialog r0_ProgressDialog = this.b;
            if (this.e) {
                r0_ProgressDialog.incrementProgressBy(r1i);
            } else {
                r1i = r5i;
                r0_ProgressDialog.incrementProgressBy(r1i);
            }
        }
        if (this.c != null) {
            int r0i;
            if (this.e) {
                r0i = this.g;
                this.g = r0i + 1;
            } else {
                this.g += r5i;
                r0i = (this.g * 10000) / this.f;
            }
            if (r0i > 9999) {
                r0i = 9999;
            }
            this.c.setProgress(r0i);
        }
    }

    public void reset() {
        if (this.a != null) {
            this.a.setProgress(0);
            this.a.setMax(10000);
        }
        if (this.b != null) {
            this.b.setProgress(0);
            this.b.setMax(10000);
        }
        if (this.c != null) {
            this.c.setProgress(0);
        }
        this.e = false;
        this.g = 0;
        this.f = 10000;
    }

    public void run() {
        a(this.h);
    }

    public void setBytes(int r3i) {
        if (r3i <= 0) {
            this.e = true;
            r3i = 10000;
        }
        this.f = r3i;
        if (this.a != null) {
            this.a.setProgress(0);
            this.a.setMax(r3i);
        }
        if (this.b != null) {
            this.b.setProgress(0);
            this.b.setMax(r3i);
        }
    }

    public void show(String r6_String) {
        reset();
        if (this.b != null) {
            new AQuery(this.b.getContext()).show(this.b);
        }
        if (this.c != null) {
            this.c.setProgressBarIndeterminateVisibility(true);
            this.c.setProgressBarVisibility(true);
        }
        if (this.a != null) {
            this.a.setTag(Constants.TAG_URL, r6_String);
            this.a.setVisibility(0);
        }
        if (this.d != null) {
            this.d.setTag(Constants.TAG_URL, r6_String);
            this.d.setVisibility(0);
        }
    }
}
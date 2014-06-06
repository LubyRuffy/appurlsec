package qsbk.app.nearby.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.nearby.ui.AbstractDialog.AbstractBuilder;
import qsbk.app.utils.Base64;

public class AlertDialog extends AbstractDialog {
    private TextView t;
    private View u;

    public static class Builder extends AbstractBuilder {
        public Builder(Context r1_Context) {
            super(r1_Context);
        }

        protected int a() {
            return R.layout.base_alert_dialog;
        }

        protected AbstractDialog a(Context r2_Context, int r3i) {
            return new AlertDialog(r2_Context, r3i);
        }
    }

    public AlertDialog(Context r1_Context, int r2i) {
        super(r1_Context, r2i);
    }

    protected void a(View r2_View) {
        this.t = (TextView) r2_View.findViewById(R.id.tvAlertMessage);
    }

    protected void b() {
        if (this.c != null) {
            this.t.setText(this.c);
        }
        if (this.u != null) {
            this.j.setVisibility(Base64.DONT_BREAK_LINES);
            this.p.addView(this.u);
            this.p.setVisibility(0);
            a();
        } else {
            this.p.setVisibility(Base64.DONT_BREAK_LINES);
            this.j.setVisibility(0);
            this.t.setText(this.c);
            a();
        }
    }

    public DialogType getDialogType() {
        return DialogType.Alert;
    }

    public void hideTitleLayout() {
        if (this.i != null) {
            this.i.setVisibility(Base64.DONT_BREAK_LINES);
        }
    }

    protected void onStart() {
        super.onStart();
    }

    public void resetContent() {
    }

    public void saveContent() {
    }

    public View setCustomView(int r3i) {
        this.u = getLayoutInflater().inflate(r3i, null);
        b();
        return this.u;
    }

    public View setCustomView(View r2_View) {
        this.u = r2_View;
        b();
        return this.u;
    }

    public void setMessage(CharSequence r1_CharSequence) {
        super.setMessage(r1_CharSequence);
        b();
    }

    public void show() {
        super.show();
    }

    public void showTitleLayout() {
        if (this.i != null) {
            this.i.setVisibility(0);
        }
    }
}
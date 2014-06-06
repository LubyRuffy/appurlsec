package qsbk.app.activity.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.compat.ThemeCompat;
import qsbk.app.utils.Base64;
import qsbk.app.utils.LogUtil;

public class CommDialogActivity extends Activity {
    public static String KEY_ACTIONS;
    public static String KEY_ITEMS;
    public static String KEY_TITLE;
    protected ListView a;
    protected AlertDialog b;
    protected String[] c;
    protected int[] d;
    protected String e;
    protected TextView f;
    protected View g;
    private boolean h;
    public OnItemClickListener itemClickListener;

    static {
        KEY_ITEMS = "items";
        KEY_ACTIONS = "actions";
        KEY_TITLE = "titel";
    }

    public CommDialogActivity() {
        this.itemClickListener = new c(this);
        this.h = false;
    }

    public void bindEvent() {
        if (ThemeCompat.preHoneycomb()) {
            setContentView(R.layout.activity_shareactivity_new);
            this.a = (ListView) findViewById(R.id.listview);
            this.a.setBackgroundResource(R.drawable.dialog_holo_light);
            this.a.setPadding(OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE);
            LayoutParams r0_LayoutParams = new RelativeLayout.LayoutParams(-1, -2);
            r0_LayoutParams.setMargins(OneProfileActivity.REQ_CODE_SHARE, 1, OneProfileActivity.REQ_CODE_SHARE, 1);
            this.a.setLayoutParams(r0_LayoutParams);
            this.f = (TextView) findViewById(R.id.id_title);
            this.g = findViewById(R.id.id_top_layout);
        } else {
            Builder r1_Builder = new Builder(this);
            View r2_View = LayoutInflater.from(this).inflate(R.layout.activity_shareactivity_new, null);
            this.a = (ListView) r2_View.findViewById(R.id.listview);
            r1_Builder.setView(r2_View);
            this.b = r1_Builder.show();
            this.b.setOnCancelListener(new d(this));
            this.f = (TextView) r2_View.findViewById(R.id.id_title);
            this.g = r2_View.findViewById(R.id.id_top_layout);
        }
    }

    public void confirmOption(int r3i) {
        setResult(this.d[r3i], new Intent());
        finish();
    }

    public void finish() {
        if (!this.h) {
            this.h = true;
            if (this.b == null || (!this.b.isShowing())) {
                super.finish();
            } else {
                this.b.dismiss();
                super.finish();
            }
        }
    }

    public String[] getMenuTitles() {
        return this.c;
    }

    protected void onCreate(Bundle r1_Bundle) {
        super.onCreate(r1_Bundle);
        bindEvent();
    }

    protected void onStart() {
        super.onStart();
        Intent r0_Intent = getIntent();
        this.c = r0_Intent.getStringArrayExtra(KEY_ITEMS);
        this.d = r0_Intent.getIntArrayExtra(KEY_ACTIONS);
        if (this.c == null || this.d == null || this.c.length != this.d.length) {
            LogUtil.e("intent param is not correct");
            finish();
        } else {
            this.e = r0_Intent.getStringExtra(KEY_TITLE);
            if (TextUtils.isEmpty(this.e)) {
                this.g.setVisibility(Base64.DONT_BREAK_LINES);
            } else {
                this.f.setText(this.e);
                this.g.setVisibility(0);
            }
            this.a.setAdapter(new ArrayAdapter(this, ThemeCompat.getSimpleListItem(), getMenuTitles()));
            this.a.setOnItemClickListener(this.itemClickListener);
        }
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        finish();
        return super.onTouchEvent(r2_MotionEvent);
    }
}
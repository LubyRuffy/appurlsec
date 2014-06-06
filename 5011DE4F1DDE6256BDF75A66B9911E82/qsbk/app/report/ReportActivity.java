package qsbk.app.report;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.bean.ReportBean;
import qsbk.app.compat.ThemeCompat;
import qsbk.app.utils.HttpUtils;

public class ReportActivity extends Activity {
    private ListView a;
    private AlertDialog b;
    private List<ReportBean> c;
    private boolean d;

    private class a implements OnItemClickListener {
        private a() {
        }

        public void onItemClick(AdapterView<?> r3_AdapterView_, View r4_View, int r5i, long r6j) {
            ReportActivity.this.a();
            Integer r0_Integer = ReportActivity.this.a(((TextView) r4_View).getText().toString());
            if (r0_Integer != null) {
                ReportActivity.this.a(r0_Integer.intValue());
            }
        }
    }

    public ReportActivity() {
        this.c = ReportUtils.RESOURCE;
        this.d = false;
    }

    private Integer a(String r5_String) {
        Integer r1_Integer = null;
        if (this.c == null || this.c.size() <= 0 || r5_String == null || r5_String.trim().length() <= 0) {
            return r1_Integer;
        }
        int r3i = this.c.size();
        int r2i = 0;
        while (r2i < r3i) {
            Integer r0_Integer;
            r0_Integer = ((ReportBean) this.c.get(r2i)).getName().equalsIgnoreCase(r5_String) ? Integer.valueOf(((ReportBean) this.c.get(r2i)).getValue()) : r1_Integer;
            r2i++;
            r1_Integer = r0_Integer;
        }
        return r1_Integer;
    }

    private void a() {
        if (!HttpUtils.isNetworkConnected(this)) {
            Toast.makeText(QsbkApp.mContext, getResources().getString(R.string.network_not_connected), 0).show();
            finish();
        }
    }

    private void a(int r2i) {
        setResult(r2i, new Intent());
        finish();
    }

    public void bindEvent() {
        int r2i = R.layout.activity_shareactivity_new;
        if (ThemeCompat.preHoneycomb()) {
            setContentView(r2i);
            this.a = (ListView) findViewById(R.id.listview);
            this.a.setBackgroundResource(R.drawable.dialog_holo_light);
            this.a.setPadding(OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE, OneProfileActivity.REQ_CODE_SHARE);
            LayoutParams r0_LayoutParams = new RelativeLayout.LayoutParams(-1, -2);
            r0_LayoutParams.setMargins(OneProfileActivity.REQ_CODE_SHARE, 1, OneProfileActivity.REQ_CODE_SHARE, 1);
            this.a.setLayoutParams(r0_LayoutParams);
        } else {
            Builder r1_Builder = new Builder(this);
            View r2_View = LayoutInflater.from(this).inflate(r2i, null);
            this.a = (ListView) r2_View.findViewById(R.id.listview);
            r1_Builder.setView(r2_View);
            this.b = r1_Builder.show();
            this.b.setOnCancelListener(new c(this));
        }
        this.a.setOnItemClickListener(new a(null));
        r2i = this.c.size();
        String[] r3_StringA = new String[r2i];
        int r1i = 0;
        while (r1i < r2i) {
            r3_StringA[r1i] = ((ReportBean) this.c.get(r1i)).getName();
            r1i++;
        }
        this.a.setAdapter(new ArrayAdapter(this, ThemeCompat.getSimpleListItem(), r3_StringA));
    }

    public void finish() {
        if (!this.d) {
            this.d = true;
            if (this.b == null || (!this.b.isShowing())) {
                super.finish();
            } else {
                this.b.dismiss();
                super.finish();
            }
        }
    }

    protected void onCreate(Bundle r1_Bundle) {
        super.onCreate(r1_Bundle);
        bindEvent();
    }

    public boolean onTouchEvent(MotionEvent r2_MotionEvent) {
        finish();
        return super.onTouchEvent(r2_MotionEvent);
    }
}
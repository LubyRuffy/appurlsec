package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.issue.TaskExecutor;

public abstract class EditInfoBaseActivity extends FragmentActivity {
    public static final String URL = "http://nearby.qiushibaike.com/user/%s/detail";
    private static String r;
    protected View n;
    protected View o;
    private boolean p;
    private final boolean q;
    private FrameLayout s;

    public static interface REQUEST_KEY {
        public static final String KEY_AUTO_SUBMIT = "auto_submit";
        public static final String KEY_DEFAULT_VALUE = "default_value";
        public static final String KEY_EDIT_TYPE = "edit_type";
        public static final String KEY_RETURN_VALUE = "return_value";
    }

    static {
        r = null;
    }

    public EditInfoBaseActivity() {
        this.p = true;
        this.q = true;
    }

    private void a(Intent r3_Intent) {
        this.p = r3_Intent.getBooleanExtra(REQUEST_KEY.KEY_AUTO_SUBMIT, true);
    }

    private void c() {
        this.n = findViewById(R.id.cancel);
        this.o = findViewById(R.id.sure);
        this.n.setOnClickListener(new v(this));
        this.o.setOnClickListener(new w(this));
    }

    protected void a(Map<String, Object> r3_Map_String__Object, String r4_String) {
        if (HttpUtils.netIsAvailable()) {
            TaskExecutor.getInstance().addTask(new x(this, r4_String, r3_Map_String__Object));
        }
    }

    protected void b(String r4_String) {
        Log.e(r, r4_String + RContactStorage.PRIMARY_KEY);
    }

    protected void c(String r2_String) {
        b(r2_String);
        setResult(0);
        finish();
    }

    public abstract int getLayout();

    public abstract Map<String, Object> getPostParams();

    public abstract String getPostUrl();

    public abstract Intent getResultData();

    public abstract void handleIntent(Intent r1_Intent);

    public abstract void init();

    public abstract void onCancel(View r1_View);

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        r = getClass().getName();
        a(getIntent());
        setContentView(R.layout.activity_edit_info);
        this.s = (FrameLayout) findViewById(R.id.container);
        this.s.addView(View.inflate(this, getLayout(), null));
        c();
        init();
        handleIntent(getIntent());
    }

    public abstract boolean onSure(View r1_View);
}
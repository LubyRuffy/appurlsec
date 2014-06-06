package qsbk.app.activity;

import android.os.Handler;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.LoginRegisterBaseActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;

public class RegisterActivity extends LoginRegisterBaseActivity {
    private Handler n;

    public RegisterActivity() {
        this.n = new cj(this);
    }

    protected void c() {
        super.c();
        QsbkApp.register = this;
        this.r.setVisibility(Base64.DONT_BREAK_LINES);
        findViewById(R.id.login).setOnClickListener(new ci(this));
        this.v.setInputType(1);
        findViewById(R.id.next).setOnClickListener(this);
    }

    protected String d() {
        this.u.trackView("\u6ce8\u518c\u7cd7\u767e/");
        return Constants.REGISTER;
    }

    protected HashMap<String, Object> e() {
        HashMap<String, Object> r0_HashMap_String__Object = new HashMap();
        r0_HashMap_String__Object.put(QsbkDatabase.LOGIN, this.w.getText().toString().trim());
        return r0_HashMap_String__Object;
    }

    public String getCustomerTitle() {
        return "\u6ce8\u518c\u8d26\u53f7";
    }

    public Handler getHanler() {
        return this.n;
    }

    public int getLayoutId() {
        return R.layout.activity_register;
    }

    public void submit() {
        h();
        new ck(this, "qbk-RegisterAct").start();
    }
}
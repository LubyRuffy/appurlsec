package qsbk.app.activity.group;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyLikeActivity;
import qsbk.app.activity.MyParticipateActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.UIHelper;

public class MyParticipateActivityGroup extends GroupBaseActivity implements OnClickListener {
    Button p;
    Button q;

    private void b(String r6_String) {
        Window r0_Window;
        this.c.removeAllViews();
        j();
        r0_Window = "Participate".equals(r6_String) ? getLocalActivityManager().startActivity("Participate", new Intent(this, MyParticipateActivity.class).addFlags(67108864)) : getLocalActivityManager().startActivity("Like", new Intent(this, MyLikeActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void c(String r4_String) {
        if (r4_String.equals("normal")) {
            this.p.setTag("active");
            this.q.setTag("normal");
            if (UIHelper.isNightTheme()) {
                this.p.setBackgroundResource(R.drawable.top_tab_active_night);
                this.p.setTextColor(this.h);
                this.q.setBackgroundResource(R.drawable.top_tab_normal);
                this.q.setTextColor(this.j);
            } else {
                this.p.setBackgroundResource(R.drawable.top_tab_active);
                this.p.setTextColor(this.g);
                this.q.setBackgroundResource(R.drawable.top_tab_normal);
                this.q.setTextColor(this.i);
            }
            b("Participate");
        } else {
            h();
        }
    }

    private void d(String r5_String) {
        if (r5_String.equals("normal")) {
            this.q.setTag("active");
            this.p.setTag("normal");
            if (UIHelper.isNightTheme()) {
                this.q.setBackgroundResource(R.drawable.top_tab_active_night);
                this.q.setTextColor(this.h);
                this.q.setSelected(true);
                this.p.setBackgroundResource(R.drawable.top_tab_normal);
                this.p.setTextColor(this.j);
            } else {
                this.q.setBackgroundResource(R.drawable.top_tab_active);
                this.q.setTextColor(this.g);
                this.q.setSelected(true);
                this.p.setBackgroundResource(R.drawable.top_tab_normal);
                this.p.setTextColor(this.i);
            }
            b("Like");
        } else {
            h();
        }
    }

    private void j() {
        if (QsbkApp.isChange) {
            LocalActivityManager r0_LocalActivityManager = getLocalActivityManager();
            if (r0_LocalActivityManager.getActivity("Participate") != null) {
                r0_LocalActivityManager.destroyActivity("Participate", true);
            }
            if (r0_LocalActivityManager.getActivity("Like") != null) {
                r0_LocalActivityManager.destroyActivity("Like", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        this.p = (Button) this.b.findViewById(R.id.tab_left);
        this.p.setText(R.string.title_participate);
        this.q = (Button) this.b.findViewById(R.id.tab_right);
        this.q.setText(R.string.title_like);
    }

    protected void b() {
        super.b();
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        MyParticipateActivityGroup r5_MyParticipateActivityGroup = (MyParticipateActivityGroup) r5_Object;
        if ("MyParticipateActivityGroup" == null) {
            return "MyParticipateActivityGroup" == null;
        } else {
            if ("MyParticipateActivityGroup".equals("MyParticipateActivityGroup")) {
                return true;
            }
            return false;
        }
    }

    protected int f() {
        return R.layout.activity_group_two_tab;
    }

    public int hashCode() {
        return ("MyParticipateActivityGroup" == null ? 0 : "MyParticipateActivityGroup".hashCode()) + 31;
    }

    public void onClick(View r3_View) {
        String r0_String = (String) r3_View.getTag();
        switch (r3_View.getId()) {
            case R.id.tab_left:
                c(r0_String);
                break;
            case R.id.tab_right:
                d(r0_String);
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        if ("Like".equals(getIntent().getStringExtra("subActivity"))) {
            b("Like");
        } else {
            b("Participate");
        }
    }
}
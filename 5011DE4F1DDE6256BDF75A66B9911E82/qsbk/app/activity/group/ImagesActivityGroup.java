package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.HotImagesActivity;
import qsbk.app.activity.ImagesActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.UIHelper;

public class ImagesActivityGroup extends GroupBaseActivity implements OnClickListener {
    Button p;
    Button q;

    private void b(String r6_String) {
        Window r0_Window;
        this.c.removeAllViews();
        j();
        r0_Window = "Images".equals(r6_String) ? getLocalActivityManager().startActivity("Images", new Intent(this, ImagesActivity.class).addFlags(67108864)) : getLocalActivityManager().startActivity("Images", new Intent(this, HotImagesActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void j() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("Images") != null) {
                getLocalActivityManager().destroyActivity("Images", true);
            }
            if (getLocalActivityManager().getActivity("HotImages") != null) {
                getLocalActivityManager().destroyActivity("HotImages", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        this.p = (Button) this.b.findViewById(R.id.tab_left);
        this.p.setText(R.string.title_old_images);
        this.q = (Button) this.b.findViewById(R.id.tab_right);
        this.q.setText(R.string.title_images);
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
        ImagesActivityGroup r5_ImagesActivityGroup = (ImagesActivityGroup) r5_Object;
        if ("ImagesActivityGroup" == null) {
            return "ImagesActivityGroup" == null;
        } else {
            if ("ImagesActivityGroup".equals("ImagesActivityGroup")) {
                return true;
            }
            return false;
        }
    }

    protected int f() {
        return R.layout.activity_group_two_tab;
    }

    public int hashCode() {
        return ("ImagesActivityGroup" == null ? 0 : "ImagesActivityGroup".hashCode()) + 31;
    }

    public void onClick(View r7_View) {
        String r0_String = (String) r7_View.getTag();
        switch (r7_View.getId()) {
            case R.id.tab_left:
                if (r0_String.equals("normal")) {
                    this.p.setTag("active");
                    if (UIHelper.isNightTheme()) {
                        this.p.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.p.setTextColor(this.h);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.j);
                    } else {
                        this.p.setBackgroundResource(R.drawable.top_tab_active);
                        this.p.setTextColor(this.g);
                        this.q.setTag("normal");
                        this.q.setBackgroundResource(R.drawable.top_tab_normal);
                        this.q.setTextColor(this.i);
                    }
                    b("HotImages");
                } else {
                    h();
                }
                break;
            case R.id.tab_right:
                if (r0_String.equals("normal")) {
                    if (UIHelper.isNightTheme()) {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active_night);
                        this.q.setTextColor(this.h);
                        this.q.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.j);
                    } else {
                        this.q.setTag("active");
                        this.q.setBackgroundResource(R.drawable.top_tab_active);
                        this.q.setTextColor(this.g);
                        this.q.setSelected(true);
                        this.p.setTag("normal");
                        this.p.setBackgroundResource(R.drawable.top_tab_normal);
                        this.p.setTextColor(this.i);
                    }
                    b("Images");
                } else {
                    h();
                }
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        CharSequence r0_CharSequence = getIntent().getStringExtra("subActivity");
        if (TextUtils.isEmpty(r0_CharSequence) || "HotImages".equals(r0_CharSequence)) {
            b("HotImages");
        } else {
            b("Images");
        }
    }
}
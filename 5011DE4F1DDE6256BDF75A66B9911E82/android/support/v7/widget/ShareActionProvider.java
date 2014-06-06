package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener;
import android.support.v7.internal.widget.ActivityChooserView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider extends ActionProvider {
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    private int a;
    private final b b;
    private final Context c;
    private String d;
    private OnShareTargetSelectedListener e;
    private OnChooseActivityListener f;


    public static interface OnShareTargetSelectedListener {
        public boolean onShareTargetSelected(ShareActionProvider r1_ShareActionProvider, Intent r2_Intent);
    }

    private class a implements OnChooseActivityListener {
        private a() {
        }

        public boolean onChooseActivity(ActivityChooserModel r3_ActivityChooserModel, Intent r4_Intent) {
            if (ShareActionProvider.this.e != null) {
                ShareActionProvider.this.e.onShareTargetSelected(ShareActionProvider.this, r4_Intent);
            }
            return false;
        }
    }

    private class b implements OnMenuItemClickListener {
        private b() {
        }

        public boolean onMenuItemClick(MenuItem r3_MenuItem) {
            Intent r0_Intent = ActivityChooserModel.get(ShareActionProvider.this.c, ShareActionProvider.this.d).chooseActivity(r3_MenuItem.getItemId());
            if (r0_Intent != null) {
                r0_Intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                ShareActionProvider.this.c.startActivity(r0_Intent);
            }
            return true;
        }
    }

    public ShareActionProvider(Context r3_Context) {
        super(r3_Context);
        this.a = 4;
        this.b = new b(null);
        this.d = DEFAULT_SHARE_HISTORY_FILE_NAME;
        this.c = r3_Context;
    }

    private void a() {
        if (this.e == null) {
        } else {
            if (this.f == null) {
                this.f = new a(null);
            }
            ActivityChooserModel.get(this.c, this.d).setOnChooseActivityListener(this.f);
        }
    }

    public boolean hasSubMenu() {
        return true;
    }

    public View onCreateActionView() {
        ActivityChooserModel r0_ActivityChooserModel = ActivityChooserModel.get(this.c, this.d);
        View r1_View = new ActivityChooserView(this.c);
        r1_View.setActivityChooserModel(r0_ActivityChooserModel);
        TypedValue r0_TypedValue = new TypedValue();
        this.c.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, r0_TypedValue, true);
        r1_View.setExpandActivityOverflowButtonDrawable(this.c.getResources().getDrawable(r0_TypedValue.resourceId));
        r1_View.setProvider(this);
        r1_View.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        r1_View.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return r1_View;
    }

    public void onPrepareSubMenu(SubMenu r9_SubMenu) {
        ResolveInfo r6_ResolveInfo;
        r9_SubMenu.clear();
        ActivityChooserModel r2_ActivityChooserModel = ActivityChooserModel.get(this.c, this.d);
        PackageManager r3_PackageManager = this.c.getPackageManager();
        int r4i = r2_ActivityChooserModel.getActivityCount();
        int r5i = Math.min(r4i, this.a);
        int r0i = 0;
        while (r0i < r5i) {
            r6_ResolveInfo = r2_ActivityChooserModel.getActivity(r0i);
            r9_SubMenu.add(0, r0i, r0i, r6_ResolveInfo.loadLabel(r3_PackageManager)).setIcon(r6_ResolveInfo.loadIcon(r3_PackageManager)).setOnMenuItemClickListener(this.b);
            r0i++;
        }
        if (r5i < r4i) {
            SubMenu r5_SubMenu = r9_SubMenu.addSubMenu(0, r5i, r5i, this.c.getString(R.string.abc_activity_chooser_view_see_all));
            r0i = 0;
            while (r0i < r4i) {
                r6_ResolveInfo = r2_ActivityChooserModel.getActivity(r0i);
                r5_SubMenu.add(0, r0i, r0i, r6_ResolveInfo.loadLabel(r3_PackageManager)).setIcon(r6_ResolveInfo.loadIcon(r3_PackageManager)).setOnMenuItemClickListener(this.b);
                r0i++;
            }
        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener r1_OnShareTargetSelectedListener) {
        this.e = r1_OnShareTargetSelectedListener;
        a();
    }

    public void setShareHistoryFileName(String r1_String) {
        this.d = r1_String;
        a();
    }

    public void setShareIntent(Intent r3_Intent) {
        ActivityChooserModel.get(this.c, this.d).setIntent(r3_Intent);
    }
}
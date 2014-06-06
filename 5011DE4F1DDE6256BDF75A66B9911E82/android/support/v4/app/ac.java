package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

// compiled from: ShareCompatICS.java
class ac {
    public static void configureMenuItem(MenuItem r3_MenuItem, Activity r4_Activity, Intent r5_Intent) {
        ShareActionProvider r0_ShareActionProvider;
        ActionProvider r0_ActionProvider = r3_MenuItem.getActionProvider();
        r0_ShareActionProvider = r0_ActionProvider instanceof ShareActionProvider ? (ShareActionProvider) r0_ActionProvider : new ShareActionProvider(r4_Activity);
        r0_ShareActionProvider.setShareHistoryFileName(".sharecompat_" + r4_Activity.getClass().getName());
        r0_ShareActionProvider.setShareIntent(r5_Intent);
        r3_MenuItem.setActionProvider(r0_ShareActionProvider);
    }
}
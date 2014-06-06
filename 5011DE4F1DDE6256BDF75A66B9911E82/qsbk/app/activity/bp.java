package qsbk.app.activity;

import java.util.HashMap;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: OneProfileActivity.java
class bp extends HashMap<Integer, Integer> {
    final /* synthetic */ OneProfileActivity a;

    bp(OneProfileActivity r4_OneProfileActivity) {
        this.a = r4_OneProfileActivity;
        put(Integer.valueOf(XListViewHeader.STATE_REFRESHING), Integer.valueOf(1));
        put(Integer.valueOf(XListViewFooter.STATE_NOMORE), Integer.valueOf(1));
    }
}
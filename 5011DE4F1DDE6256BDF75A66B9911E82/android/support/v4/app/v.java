package android.support.v4.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// compiled from: ListFragment.java
class v implements OnItemClickListener {
    final /* synthetic */ ListFragment a;

    v(ListFragment r1_ListFragment) {
        this.a = r1_ListFragment;
    }

    public void onItemClick(AdapterView<?> r7_AdapterView_, View r8_View, int r9i, long r10j) {
        this.a.onListItemClick((ListView) r7_AdapterView_, r8_View, r9i, r10j);
    }
}
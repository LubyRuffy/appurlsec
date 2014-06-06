package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView a;

    public ListViewAutoScrollHelper(ListView r1_ListView) {
        super(r1_ListView);
        this.a = r1_ListView;
    }

    public boolean canTargetScrollHorizontally(int r2i) {
        return false;
    }

    public boolean canTargetScrollVertically(int r7i) {
        ListView r1_ListView = this.a;
        int r2i = r1_ListView.getCount();
        int r3i = r1_ListView.getChildCount();
        int r4i = r1_ListView.getFirstVisiblePosition();
        int r5i = r4i + r3i;
        if (r7i > 0) {
            if (r5i >= r2i && r1_ListView.getChildAt(r3i - 1).getBottom() <= r1_ListView.getHeight()) {
                return false;
            }
        } else {
            if (r7i >= 0) {
                return false;
            }
            if (r4i <= 0 && r1_ListView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    public void scrollTargetBy(int r4i, int r5i) {
        ListView r0_ListView = this.a;
        int r1i = r0_ListView.getFirstVisiblePosition();
        if (r1i == -1) {
        } else {
            View r2_View = r0_ListView.getChildAt(0);
            if (r2_View != null) {
                r0_ListView.setSelectionFromTop(r1i, r2_View.getTop() - r5i);
            }
        }
    }
}
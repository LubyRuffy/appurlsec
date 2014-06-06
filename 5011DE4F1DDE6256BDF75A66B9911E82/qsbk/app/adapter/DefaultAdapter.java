package qsbk.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public abstract class DefaultAdapter<T> extends BaseAdapter {
    protected ListView a;
    protected ArrayList<T> b;
    protected final Activity c;
    protected LayoutInflater d;

    public DefaultAdapter(ArrayList<T> r2_ArrayList_T, Activity r3_Activity) {
        this.b = r2_ArrayList_T;
        this.c = r3_Activity;
        this.d = LayoutInflater.from(r3_Activity);
    }

    public void clearImageCache() {
    }

    public int getCount() {
        return this.b.size();
    }

    public T getItem(int r2i) {
        return (this.b == null || this.b.size() <= 0) ? null : this.b.get(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }
}
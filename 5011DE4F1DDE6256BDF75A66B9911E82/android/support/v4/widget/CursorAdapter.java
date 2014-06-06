package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import com.tencent.mm.sdk.contact.RContactStorage;

public abstract class CursorAdapter extends BaseAdapter implements a, Filterable {
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    protected boolean a;
    protected boolean b;
    protected Cursor c;
    protected Context d;
    protected int e;
    protected a f;
    protected DataSetObserver g;
    protected c h;
    protected FilterQueryProvider i;


    private class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean r2z) {
            CursorAdapter.this.a();
        }
    }

    private class b extends DataSetObserver {
        private b() {
        }

        public void onChanged() {
            CursorAdapter.this = true;
            CursorAdapter.this.notifyDataSetChanged();
        }

        public void onInvalidated() {
            CursorAdapter.this = false;
            CursorAdapter.this.notifyDataSetInvalidated();
        }
    }

    public CursorAdapter(Context r2_Context, Cursor r3_Cursor) {
        a(r2_Context, r3_Cursor, FLAG_AUTO_REQUERY);
    }

    public CursorAdapter(Context r1_Context, Cursor r2_Cursor, int r3i) {
        a(r1_Context, r2_Cursor, r3i);
    }

    public CursorAdapter(Context r2_Context, Cursor r3_Cursor, boolean r4z) {
        a(r2_Context, r3_Cursor, r4z ? FLAG_AUTO_REQUERY : FLAG_REGISTER_CONTENT_OBSERVER);
    }

    protected void a() {
        if ((!this.b) || this.c == null || this.c.isClosed()) {
        } else {
            this.a = this.c.requery();
        }
    }

    void a(Context r5_Context, Cursor r6_Cursor, int r7i) {
        boolean r0z = true;
        if ((r7i & 1) == 1) {
            r7i |= 2;
            this.b = true;
        } else {
            this.b = false;
        }
        if (r6_Cursor != null) {
            this.c = r6_Cursor;
            this.a = r0z;
            this.d = r5_Context;
            this.e = r0z ? -1 : r6_Cursor.getColumnIndexOrThrow("_id");
            if ((r7i & 2) != 2) {
                this.f = new a();
                this.g = new b(null);
            } else {
                this.f = null;
                this.g = null;
            }
            if (!r0z) {
                if (this.f == null) {
                    r6_Cursor.registerContentObserver(this.f);
                }
                if (this.g != null) {
                    r6_Cursor.registerDataSetObserver(this.g);
                }
            }
        } else {
            r0z = false;
            this.c = r6_Cursor;
            this.a = r0z;
            this.d = r5_Context;
            if (r0z) {
            }
            this.e = r0z ? -1 : r6_Cursor.getColumnIndexOrThrow("_id");
            if ((r7i & 2) != 2) {
                this.f = null;
                this.g = null;
            } else {
                this.f = new a();
                this.g = new b(null);
            }
            if (r0z) {
            } else if (this.f == null) {
                if (this.g != null) {
                } else {
                    r6_Cursor.registerDataSetObserver(this.g);
                }
            } else {
                r6_Cursor.registerContentObserver(this.f);
                if (this.g != null) {
                    r6_Cursor.registerDataSetObserver(this.g);
                }
            }
        }
    }

    public abstract void bindView(View r1_View, Context r2_Context, Cursor r3_Cursor);

    public void changeCursor(Cursor r2_Cursor) {
        Cursor r0_Cursor = swapCursor(r2_Cursor);
        if (r0_Cursor != null) {
            r0_Cursor.close();
        }
    }

    public CharSequence convertToString(Cursor r2_Cursor) {
        return r2_Cursor == null ? RContactStorage.PRIMARY_KEY : r2_Cursor.toString();
    }

    public int getCount() {
        return ((!this.a) || this.c == null) ? 0 : this.c.getCount();
    }

    public Cursor getCursor() {
        return this.c;
    }

    public View getDropDownView(int r3i, View r4_View, ViewGroup r5_ViewGroup) {
        if (!(this.a)) {
            return null;
        }
        this.c.moveToPosition(r3i);
        if (r4_View == null) {
            r4_View = newDropDownView(this.d, this.c, r5_ViewGroup);
        }
        bindView(r4_View, this.d, this.c);
        return r4_View;
    }

    public Filter getFilter() {
        if (this.h == null) {
            this.h = new c(this);
        }
        return this.h;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.i;
    }

    public Object getItem(int r2i) {
        if ((!this.a) || this.c == null) {
            return null;
        }
        this.c.moveToPosition(r2i);
        return this.c;
    }

    public long getItemId(int r4i) {
        return (this.a && this.c != null && this.c.moveToPosition(r4i)) ? this.c.getLong(this.e) : 0;
    }

    public View getView(int r4i, View r5_View, ViewGroup r6_ViewGroup) {
        if (this.a) {
            if (this.c.moveToPosition(r4i)) {
                if (r5_View == null) {
                    r5_View = newView(this.d, this.c, r6_ViewGroup);
                }
                bindView(r5_View, this.d, this.c);
                return r5_View;
            } else {
                throw new IllegalStateException("couldn't move cursor to position " + r4i);
            }
        } else {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
    }

    public boolean hasStableIds() {
        return true;
    }

    public View newDropDownView(Context r2_Context, Cursor r3_Cursor, ViewGroup r4_ViewGroup) {
        return newView(r2_Context, r3_Cursor, r4_ViewGroup);
    }

    public abstract View newView(Context r1_Context, Cursor r2_Cursor, ViewGroup r3_ViewGroup);

    public Cursor runQueryOnBackgroundThread(CharSequence r2_CharSequence) {
        return this.i != null ? this.i.runQuery(r2_CharSequence) : this.c;
    }

    public void setFilterQueryProvider(FilterQueryProvider r1_FilterQueryProvider) {
        this.i = r1_FilterQueryProvider;
    }

    public Cursor swapCursor(Cursor r3_Cursor) {
        if (r3_Cursor == this.c) {
            return null;
        }
        Cursor r0_Cursor = this.c;
        if (r0_Cursor != null) {
            if (this.f != null) {
                r0_Cursor.unregisterContentObserver(this.f);
            }
            if (this.g != null) {
                r0_Cursor.unregisterDataSetObserver(this.g);
            }
        }
        this.c = r3_Cursor;
        if (r3_Cursor != null) {
            if (this.f != null) {
                r3_Cursor.registerContentObserver(this.f);
            }
            if (this.g != null) {
                r3_Cursor.registerDataSetObserver(this.g);
            }
            this.e = r3_Cursor.getColumnIndexOrThrow("_id");
            this.a = true;
            notifyDataSetChanged();
            return r0_Cursor;
        } else {
            this.e = -1;
            this.a = false;
            notifyDataSetInvalidated();
            return r0_Cursor;
        }
    }
}
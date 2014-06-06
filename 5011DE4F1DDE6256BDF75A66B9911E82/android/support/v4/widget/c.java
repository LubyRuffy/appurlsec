package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

// compiled from: CursorFilter.java
class c extends Filter {
    a a;

    // compiled from: CursorFilter.java
    static interface a {
        public void changeCursor(Cursor r1_Cursor);

        public CharSequence convertToString(Cursor r1_Cursor);

        public Cursor getCursor();

        public Cursor runQueryOnBackgroundThread(CharSequence r1_CharSequence);
    }

    c(a r1_a) {
        this.a = r1_a;
    }

    public CharSequence convertResultToString(Object r2_Object) {
        return this.a.convertToString((Cursor) r2_Object);
    }

    protected FilterResults performFiltering(CharSequence r4_CharSequence) {
        Cursor r0_Cursor = this.a.runQueryOnBackgroundThread(r4_CharSequence);
        FilterResults r1_FilterResults = new FilterResults();
        if (r0_Cursor != null) {
            r1_FilterResults.count = r0_Cursor.getCount();
            r1_FilterResults.values = r0_Cursor;
        } else {
            r1_FilterResults.count = 0;
            r1_FilterResults.values = null;
        }
        return r1_FilterResults;
    }

    protected void publishResults(CharSequence r3_CharSequence, FilterResults r4_FilterResults) {
        Cursor r0_Cursor = this.a.getCursor();
        if (r4_FilterResults.values == null || r4_FilterResults.values == r0_Cursor) {
        } else {
            this.a.changeCursor((Cursor) r4_FilterResults.values);
        }
    }
}
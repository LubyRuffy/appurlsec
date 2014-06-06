package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.Loader.ForceLoadContentObserver;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
    final ForceLoadContentObserver f;
    Uri g;
    String[] h;
    String i;
    String[] j;
    String k;
    Cursor l;

    public CursorLoader(Context r2_Context) {
        super(r2_Context);
        this.f = new ForceLoadContentObserver();
    }

    public CursorLoader(Context r2_Context, Uri r3_Uri, String[] r4_StringA, String r5_String, String[] r6_StringA, String r7_String) {
        super(r2_Context);
        this.f = new ForceLoadContentObserver();
        this.g = r3_Uri;
        this.h = r4_StringA;
        this.i = r5_String;
        this.j = r6_StringA;
        this.k = r7_String;
    }

    protected void d() {
        if (this.l != null) {
            deliverResult(this.l);
        }
        if (takeContentChanged() || this.l == null) {
            forceLoad();
        }
    }

    public void deliverResult(Cursor r3_Cursor) {
        if (isReset()) {
            if (r3_Cursor != null) {
                r3_Cursor.close();
            }
        } else {
            Cursor r0_Cursor = this.l;
            this.l = r3_Cursor;
            if (isStarted()) {
                super.deliverResult(r3_Cursor);
            }
            if (r0_Cursor == null || r0_Cursor == r3_Cursor || r0_Cursor.isClosed()) {
            } else {
                r0_Cursor.close();
            }
        }
    }

    public void dump(String r2_String, FileDescriptor r3_FileDescriptor, PrintWriter r4_PrintWriter, String[] r5_StringA) {
        super.dump(r2_String, r3_FileDescriptor, r4_PrintWriter, r5_StringA);
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mUri=");
        r4_PrintWriter.println(this.g);
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mProjection=");
        r4_PrintWriter.println(Arrays.toString(this.h));
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mSelection=");
        r4_PrintWriter.println(this.i);
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mSelectionArgs=");
        r4_PrintWriter.println(Arrays.toString(this.j));
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mSortOrder=");
        r4_PrintWriter.println(this.k);
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mCursor=");
        r4_PrintWriter.println(this.l);
        r4_PrintWriter.print(r2_String);
        r4_PrintWriter.print("mContentChanged=");
        r4_PrintWriter.println(this.s);
    }

    protected void e() {
        cancelLoad();
    }

    protected void f() {
        super.f();
        e();
        if (this.l == null || this.l.isClosed()) {
            this.l = null;
        } else {
            this.l.close();
            this.l = null;
        }
    }

    public String[] getProjection() {
        return this.h;
    }

    public String getSelection() {
        return this.i;
    }

    public String[] getSelectionArgs() {
        return this.j;
    }

    public String getSortOrder() {
        return this.k;
    }

    public Uri getUri() {
        return this.g;
    }

    public Cursor loadInBackground() {
        Cursor r0_Cursor = getContext().getContentResolver().query(this.g, this.h, this.i, this.j, this.k);
        if (r0_Cursor != null) {
            r0_Cursor.getCount();
            r0_Cursor.registerContentObserver(this.f);
        }
        return r0_Cursor;
    }

    public void onCanceled(Cursor r2_Cursor) {
        if (r2_Cursor == null || r2_Cursor.isClosed()) {
        } else {
            r2_Cursor.close();
        }
    }

    public void setProjection(String[] r1_StringA) {
        this.h = r1_StringA;
    }

    public void setSelection(String r1_String) {
        this.i = r1_String;
    }

    public void setSelectionArgs(String[] r1_StringA) {
        this.j = r1_StringA;
    }

    public void setSortOrder(String r1_String) {
        this.k = r1_String;
    }

    public void setUri(Uri r1_Uri) {
        this.g = r1_Uri;
    }
}
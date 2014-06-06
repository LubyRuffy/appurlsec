package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.mm.sdk.contact.RContactStorage;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
    protected int[] j;
    protected int[] k;
    String[] l;
    private int m;
    private CursorToStringConverter n;
    private ViewBinder o;

    public static interface CursorToStringConverter {
        public CharSequence convertToString(Cursor r1_Cursor);
    }

    public static interface ViewBinder {
        public boolean setViewValue(View r1_View, Cursor r2_Cursor, int r3i);
    }

    public SimpleCursorAdapter(Context r2_Context, int r3i, Cursor r4_Cursor, String[] r5_StringA, int[] r6_intA) {
        super(r2_Context, r3i, r4_Cursor);
        this.m = -1;
        this.k = r6_intA;
        this.l = r5_StringA;
        a(r5_StringA);
    }

    public SimpleCursorAdapter(Context r2_Context, int r3i, Cursor r4_Cursor, String[] r5_StringA, int[] r6_intA, int r7i) {
        super(r2_Context, r3i, r4_Cursor, r7i);
        this.m = -1;
        this.k = r6_intA;
        this.l = r5_StringA;
        a(r5_StringA);
    }

    private void a(String[] r6_StringA) {
        if (this.c != null) {
            int r1i = r6_StringA.length;
            int r0i;
            if (this.j == null || this.j.length != r1i) {
                this.j = new int[r1i];
                r0i = 0;
                while (r0i < r1i) {
                    this.j[r0i] = this.c.getColumnIndexOrThrow(r6_StringA[r0i]);
                    r0i++;
                }
            } else {
                r0i = 0;
                while (r0i < r1i) {
                    this.j[r0i] = this.c.getColumnIndexOrThrow(r6_StringA[r0i]);
                    r0i++;
                }
            }
        } else {
            this.j = null;
        }
    }

    public void bindView(View r10_View, Context r11_Context, Cursor r12_Cursor) {
        ViewBinder r4_ViewBinder = this.o;
        int r5i = this.k.length;
        int[] r6_intA = this.j;
        int[] r7_intA = this.k;
        int r3i = 0;
        while (r3i < r5i) {
            View r0_View = r10_View.findViewById(r7_intA[r3i]);
            if (r0_View != null) {
                if (!r4_ViewBinder != null ? r4_ViewBinder.setViewValue(r0_View, r12_Cursor, r6_intA[r3i]) : false) {
                    String r1_String = r12_Cursor.getString(r6_intA[r3i]);
                    if (r1_String == null) {
                        r1_String = RContactStorage.PRIMARY_KEY;
                    }
                    if (r0_View instanceof TextView) {
                        setViewText((TextView) r0_View, r1_String);
                    } else if (r0_View instanceof ImageView) {
                        setViewImage((ImageView) r0_View, r1_String);
                    } else {
                        throw new IllegalStateException(r0_View.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                    }
                }
            }
            r3i++;
        }
    }

    public void changeCursorAndColumns(Cursor r2_Cursor, String[] r3_StringA, int[] r4_intA) {
        this.l = r3_StringA;
        this.k = r4_intA;
        super.changeCursor(r2_Cursor);
        a(this.l);
    }

    public CharSequence convertToString(Cursor r3_Cursor) {
        if (this.n != null) {
            return this.n.convertToString(r3_Cursor);
        }
        if (this.m > -1) {
            return r3_Cursor.getString(this.m);
        }
        return super.convertToString(r3_Cursor);
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.n;
    }

    public int getStringConversionColumn() {
        return this.m;
    }

    public ViewBinder getViewBinder() {
        return this.o;
    }

    public void setCursorToStringConverter(CursorToStringConverter r1_CursorToStringConverter) {
        this.n = r1_CursorToStringConverter;
    }

    public void setStringConversionColumn(int r1i) {
        this.m = r1i;
    }

    public void setViewBinder(ViewBinder r1_ViewBinder) {
        this.o = r1_ViewBinder;
    }

    public void setViewImage(ImageView r2_ImageView, String r3_String) {
        try {
            r2_ImageView.setImageResource(Integer.parseInt(r3_String));
        } catch (NumberFormatException e) {
            r2_ImageView.setImageURI(Uri.parse(r3_String));
        }
    }

    public void setViewText(TextView r1_TextView, String r2_String) {
        r1_TextView.setText(r2_String);
    }

    public Cursor swapCursor(Cursor r3_Cursor) {
        Cursor r0_Cursor = super.swapCursor(r3_Cursor);
        a(this.l);
        return r0_Cursor;
    }
}
package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int j;
    private int k;
    private LayoutInflater l;

    public ResourceCursorAdapter(Context r2_Context, int r3i, Cursor r4_Cursor) {
        super(r2_Context, r4_Cursor);
        this.k = r3i;
        this.j = r3i;
        this.l = (LayoutInflater) r2_Context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context r2_Context, int r3i, Cursor r4_Cursor, int r5i) {
        super(r2_Context, r4_Cursor, r5i);
        this.k = r3i;
        this.j = r3i;
        this.l = (LayoutInflater) r2_Context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context r2_Context, int r3i, Cursor r4_Cursor, boolean r5z) {
        super(r2_Context, r4_Cursor, r5z);
        this.k = r3i;
        this.j = r3i;
        this.l = (LayoutInflater) r2_Context.getSystemService("layout_inflater");
    }

    public View newDropDownView(Context r4_Context, Cursor r5_Cursor, ViewGroup r6_ViewGroup) {
        return this.l.inflate(this.k, r6_ViewGroup, false);
    }

    public View newView(Context r4_Context, Cursor r5_Cursor, ViewGroup r6_ViewGroup) {
        return this.l.inflate(this.j, r6_ViewGroup, false);
    }

    public void setDropDownViewResource(int r1i) {
        this.k = r1i;
    }

    public void setViewResource(int r1i) {
        this.j = r1i;
    }
}
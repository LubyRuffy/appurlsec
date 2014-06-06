package com.tencent.mm.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;

public interface MDBItem {
    public void convertFrom(Cursor r1_Cursor);

    public ContentValues convertTo();
}
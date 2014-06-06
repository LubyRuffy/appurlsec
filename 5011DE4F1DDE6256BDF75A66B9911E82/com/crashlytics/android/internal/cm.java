package com.crashlytics.android.internal;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

// compiled from: SourceFile
final class cm extends ContextWrapper {
    private final String a;

    public cm(Context r3_Context, String r4_String) {
        super(r3_Context);
        this.a = new StringBuilder(".TwitterSdk/").append(r4_String).toString();
    }

    public final File getCacheDir() {
        return new File(super.getCacheDir(), this.a);
    }

    public final File getDatabasePath(String r4_String) {
        File r0_File = new File(super.getDatabasePath(r4_String).getParentFile(), this.a);
        r0_File.mkdirs();
        return new File(r0_File, r4_String);
    }

    public final File getExternalCacheDir() {
        return new File(super.getExternalCacheDir(), this.a);
    }

    public final File getExternalFilesDir(String r4_String) {
        return new File(super.getExternalFilesDir(r4_String), this.a);
    }

    public final File getFilesDir() {
        return new File(super.getFilesDir(), this.a);
    }

    public final SQLiteDatabase openOrCreateDatabase(String r2_String, int r3i, CursorFactory r4_CursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(r2_String), r4_CursorFactory);
    }

    public final SQLiteDatabase openOrCreateDatabase(String r2_String, int r3i, CursorFactory r4_CursorFactory, DatabaseErrorHandler r5_DatabaseErrorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(r2_String).getPath(), r4_CursorFactory, r5_DatabaseErrorHandler);
    }
}
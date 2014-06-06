package com.crashlytics.android.internal;

import java.io.File;
import java.util.Comparator;

// compiled from: SourceFile
final class bq implements Comparator<File> {
    bq() {
    }

    public final /* synthetic */ int compare(Object r5_Object, Object r6_Object) {
        return (int) (((File) r5_Object).lastModified() - ((File) r6_Object).lastModified());
    }
}
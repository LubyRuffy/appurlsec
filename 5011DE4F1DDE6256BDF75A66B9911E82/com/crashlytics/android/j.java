package com.crashlytics.android;

import java.io.File;
import java.util.Comparator;

// compiled from: SourceFile
final class j implements Comparator<File> {
    j() {
    }

    public final /* synthetic */ int compare(Object r3_Object, Object r4_Object) {
        return ((File) r3_Object).getName().compareTo(((File) r4_Object).getName());
    }
}
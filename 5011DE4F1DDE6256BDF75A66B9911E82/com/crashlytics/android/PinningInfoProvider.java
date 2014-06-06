package com.crashlytics.android;

import java.io.InputStream;

// compiled from: SourceFile
public interface PinningInfoProvider {
    public String getKeyStorePassword();

    public InputStream getKeyStoreStream();

    public String[] getPins();
}
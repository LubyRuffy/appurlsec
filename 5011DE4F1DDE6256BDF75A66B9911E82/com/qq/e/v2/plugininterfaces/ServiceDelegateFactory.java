package com.qq.e.v2.plugininterfaces;

import android.app.Service;

public interface ServiceDelegateFactory {
    public ServiceDelegate getAPKDownloadServiceDelegate(Service r1_Service);
}
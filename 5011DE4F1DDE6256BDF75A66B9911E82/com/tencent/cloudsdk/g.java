package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class g {
    private static final String a;
    private static final byte[] b;
    private static g c;
    private int d;
    private HashMap e;

    static {
        a = g.class.getSimpleName();
        b = new byte[0];
        c = null;
    }

    private g() {
        this.d = 0;
        this.e = new HashMap();
    }

    public static g a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new g();
                }
            }
        }
        return c;
    }

    private Class a(String r5_String, boolean r6z, ClassLoader r7_ClassLoader) {
        if (r7_ClassLoader == null) {
            return null;
        }
        try {
            return Class.forName(r5_String, r6z, r7_ClassLoader);
        } catch (ClassNotFoundException e) {
            WnsClientLog.i(a, new StringBuilder("class not found : ").append(r5_String).toString());
            return null;
        }
    }

    public DexClassLoader a(a r8_a) {
        if (this.e.get(r8_a) == null) {
            if (f.a().b(r8_a)) {
                String r1_String = f.a().b();
                File r2_File = new File(new StringBuilder(String.valueOf(r1_String)).append(File.separator).append("dlock").toString());
                if (r2_File.exists() || au.a(r1_String, "dlock")) {
                    ar r1_ar = new as();
                    try {
                        if (r1_ar.a(r2_File) == null) {
                            WnsClientLog.i(a, "\u83b7\u53d6\u6587\u4ef6\u9501\u5931\u8d25\uff01");
                            if (r1_ar == null) {
                                return null;
                            }
                            r1_ar.a();
                            return null;
                        } else {
                            synchronized (b.a) {
                                if (this.e.get(r8_a) == null) {
                                    String r3_String = f.a().a(r8_a);
                                    String r4_String = f.a().d();
                                    File r5_File = new File(r4_String);
                                    if (r5_File.exists() || r5_File.mkdirs()) {
                                        this.e.put(r8_a, new DexClassLoader(r3_String, r4_String, null, getClass().getClassLoader()));
                                    } else {
                                        WnsClientLog.e(a, new StringBuilder(">>> createDir E: \u63d2\u4ef6\u76ee\u5f55\u521b\u5efa\u5931\u8d25, dir: ").append(r5_File).toString());
                                        if (r1_ar == null) {
                                            return null;
                                        }
                                        r1_ar.a();
                                        return null;
                                    }
                                }
                                WnsClientLog.i(a, "new dexclassloader succ~~");
                                if (r1_ar != null) {
                                    r1_ar.a();
                                }
                            }
                        }
                    } catch (Throwable th) {
                        if (r1_ar != null) {
                            r1_ar.a();
                        }
                    }
                } else {
                    WnsClientLog.i(a, "\u521b\u5efa\u9501\u6587\u4ef6\u5931\u8d25!");
                    return null;
                }
            } else {
                WnsClientLog.i(a, "dex file not exist~~");
            }
        }
        return (DexClassLoader) this.e.get(r8_a);
    }

    public synchronized Class a(a r8_a, String r9_String, String r10_String) {
        Class r0_Class = null;
        synchronized (this) {
            int r2i = 1;
            String r4_String = r9_String;
            Class r1_Class = null;
            while (r2i != 0) {
                Class r4_Class;
                String r1_String;
                r2i = 0;
                switch (this.d) {
                    case XListViewHeader.STATE_NORMAL:
                    case XListViewHeader.STATE_READY:
                        r4_Class = a(r9_String, true, a(r8_a));
                        r1_String = r4_String;
                        if (this.d == 0) {
                            if (r4_Class == null) {
                                this.d = 2;
                                r2i = 1;
                                r1_Class = r4_Class;
                                r4_String = r1_String;
                            } else {
                                this.d = 1;
                                r1_Class = r4_Class;
                                r4_String = r1_String;
                            }
                        } else {
                            r1_Class = r4_Class;
                            r4_String = r1_String;
                        }
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        r4_Class = a(r10_String, true, getClass().getClassLoader());
                        r1_String = r10_String;
                        if (this.d == 0) {
                            r1_Class = r4_Class;
                            r4_String = r1_String;
                        } else if (r4_Class == null) {
                            this.d = 1;
                            r1_Class = r4_Class;
                            r4_String = r1_String;
                        } else {
                            this.d = 2;
                            r2i = 1;
                            r1_Class = r4_Class;
                            r4_String = r1_String;
                        }
                        break;
                }
                r4_Class = r1_Class;
                r1_String = r4_String;
                if (this.d == 0) {
                    if (r4_Class == null) {
                        this.d = 2;
                        r2i = 1;
                        r1_Class = r4_Class;
                        r4_String = r1_String;
                    } else {
                        this.d = 1;
                        r1_Class = r4_Class;
                        r4_String = r1_String;
                    }
                } else {
                    r1_Class = r4_Class;
                    r4_String = r1_String;
                }
            }
            if (r1_Class == null) {
                WnsClientLog.i(a, new StringBuilder("load class failed! className: ").append(r4_String).toString());
            } else {
                WnsClientLog.i(a, new StringBuilder("load class succeed! className: ").append(r4_String).toString());
                r0_Class = r1_Class;
            }
        }
        return r0_Class;
    }
}
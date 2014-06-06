package com.qq.e.v2.managers.plugin;

import android.content.Context;
import com.qq.e.splash.AdViewFactory;
import com.qq.e.v2.constants.Constants.PLUGIN;
import com.qq.e.v2.net.GDTADNetClient;
import com.qq.e.v2.net.GDTADNetClient.Priority;
import com.qq.e.v2.net.GDTADNetRequest.Method;
import com.qq.e.v2.net.GDTPlainADNetRequest;
import com.qq.e.v2.net.GDTPlainADNetRequest.CallBack;
import com.qq.e.v2.net.GDTPlainADNetResponse;
import com.qq.e.v2.plugininterfaces.ActivityDelegateFactory;
import com.qq.e.v2.plugininterfaces.AppWallViewFactory;
import com.qq.e.v2.plugininterfaces.BannerViewFactory;
import com.qq.e.v2.plugininterfaces.FeedsAdViewFactory;
import com.qq.e.v2.plugininterfaces.GridAdViewFactory;
import com.qq.e.v2.plugininterfaces.InterstitialViewFactory;
import com.qq.e.v2.plugininterfaces.ServiceDelegateFactory;
import com.qq.e.v2.util.GDTLogger;
import com.qq.e.v2.util.GDTST;
import com.qq.e.v2.util.StringUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.widget.listview.XListViewFooter;

public class PM {
    private static final Pattern a;
    private static final Map<Class<?>, String> g;
    private Context b;
    private String c;
    private File d;
    private int e;
    private DexClassLoader f;

    class a implements CallBack {
        private String a;

        public a(String r2_String) {
            this.a = r2_String;
        }

        public final void onError(Exception r2_Exception) {
            GDTLogger.report("Exception while update plugin", r2_Exception);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onResponse(GDTPlainADNetRequest r10_GDTPlainADNetRequest, GDTPlainADNetResponse r11_GDTPlainADNetResponse) {
            /*
            r9_this = this;
            r2 = 0;
            r0 = 1;
            r4 = 0;
            r1 = r11.getStatusCode();
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r1 != r3) goto L_0x0139;
        L_0x000b:
            r1 = "MD5";
            r5 = java.security.MessageDigest.getInstance(r1);	 //Catch:{ IOException -> 0x0160, NoSuchAlgorithmException -> 0x0159, all -> 0x012f }
            r1 = r11.getRawContent();	 //Catch:{ IOException -> 0x0160, NoSuchAlgorithmException -> 0x0159, all -> 0x012f }
            r3 = com.qq.e.v2.managers.plugin.PM.this;	 //Catch:{ IOException -> 0x0164, NoSuchAlgorithmException -> 0x015d, all -> 0x0151 }
            r6 = new java.io.File(r3.c(), com.qq.e.v2.constants.Constants.PLUGIN.TMP_JAR_FILE);	 //Catch:{ IOException -> 0x0164, NoSuchAlgorithmException -> 0x015d, all -> 0x0151 }
            r3 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0164, NoSuchAlgorithmException -> 0x015d, all -> 0x0151 }
            r3.<init>(r6);	 //Catch:{ IOException -> 0x0164, NoSuchAlgorithmException -> 0x015d, all -> 0x0151 }
            r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
            r2 = new byte[r2];	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
        L_0x0024:
            r7 = r1.read(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r7 <= 0) goto L_0x0041;
        L_0x002a:
            r8 = 0;
            r5.update(r2, r8, r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r8 = 0;
            r3.write(r2, r8, r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            goto L_0x0024;
        L_0x0033:
            r0 = move-exception;
            r2 = r3;
        L_0x0035:
            r3 = "DownLoad plugin jar exception";
            com.qq.e.v2.util.GDTLogger.report(r3, r0);	 //Catch:{ all -> 0x0156 }
            com.qq.e.v2.util.FileUtil.tryClose(r1);
            com.qq.e.v2.util.FileUtil.tryClose(r2);
        L_0x0040:
            return;
        L_0x0041:
            com.qq.e.v2.util.FileUtil.tryClose(r1);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.FileUtil.tryClose(r3);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r5.digest();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = com.qq.e.v2.util.Md5Util.byteArrayToHexString(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r5 = com.qq.e.v2.util.GDTST.getToolInstance();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7 = r9.a;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r5 = r5.verify(r7, r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r5 == 0) goto L_0x0109;
        L_0x005b:
            r2 = com.qq.e.v2.managers.plugin.PM.this;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r5 = new java.io.File(r2.c(), com.qq.e.v2.constants.Constants.PLUGIN.TMP_SIG_FILE);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7 = r10.getUrl();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = "0";
            r8 = a;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7 = r8.matcher(r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r8 = r7.matches();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r8 == 0) goto L_0x007a;
        L_0x0075:
            r2 = 1;
            r2 = r7.group(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
        L_0x007a:
            r7 = 0;
            r7 = com.qq.e.v2.util.StringUtil.parseInteger(r2, r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r8 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
            if (r7 >= r8) goto L_0x00a2;
        L_0x0083:
            r0 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = "online plugin version is smaller than asset plugin version";
            r0.<init>(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r0 = r0.append(r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = ",443";
            r0 = r0.append(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r0 = r0.toString();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.GDTLogger.w(r0);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.FileUtil.tryClose(r1);
            com.qq.e.v2.util.FileUtil.tryClose(r3);
            goto L_0x0040;
        L_0x00a2:
            r7 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7.<init>();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r7.append(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7 = "#####";
            r2 = r2.append(r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r7 = r9.a;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.append(r7);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.toString();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.StringUtil.writeTo(r2, r5);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = com.qq.e.v2.managers.plugin.PM.this;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.d();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = com.qq.e.v2.util.FileUtil.renameTo(r6, r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r2 == 0) goto L_0x0107;
        L_0x00ca:
            r2 = com.qq.e.v2.managers.plugin.PM.this;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.e();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = com.qq.e.v2.util.FileUtil.renameTo(r5, r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r2 == 0) goto L_0x0107;
        L_0x00d6:
            r2 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r4 = "TIMESTAMP_AFTER_DOWNPLUGIN:";
            r2.<init>(r4);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r4 = java.lang.System.currentTimeMillis();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.append(r4);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r4 = ";sig=";
            r2 = r2.append(r4);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r4 = r9.a;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.append(r4);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r2 = r2.toString();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.GDTLogger.d(r2);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            if (r0 != 0) goto L_0x00ff;
        L_0x00fa:
            r0 = "Fail to update plugin while renaming jar and sig files";
            com.qq.e.v2.util.GDTLogger.report(r0);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
        L_0x00ff:
            com.qq.e.v2.util.FileUtil.tryClose(r1);
            com.qq.e.v2.util.FileUtil.tryClose(r3);
            goto L_0x0040;
        L_0x0107:
            r0 = r4;
            goto L_0x00d6;
        L_0x0109:
            r6.delete();	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r0 = "Fail to update plugin while verifying,sig=%s,md5=%s";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r5 = 0;
            r6 = r9.a;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r4[r5] = r6;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r5 = 1;
            r4[r5] = r2;	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            r0 = java.lang.String.format(r0, r4);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            com.qq.e.v2.util.GDTLogger.report(r0);	 //Catch:{ IOException -> 0x0033, NoSuchAlgorithmException -> 0x0121 }
            goto L_0x00ff;
        L_0x0121:
            r0 = move-exception;
        L_0x0122:
            r2 = "MD5 method not support on this device";
            com.qq.e.v2.util.GDTLogger.report(r2, r0);	 //Catch:{ all -> 0x0154 }
            com.qq.e.v2.util.FileUtil.tryClose(r1);
            com.qq.e.v2.util.FileUtil.tryClose(r3);
            goto L_0x0040;
        L_0x012f:
            r0 = move-exception;
            r1 = r2;
            r3 = r2;
        L_0x0132:
            com.qq.e.v2.util.FileUtil.tryClose(r1);
            com.qq.e.v2.util.FileUtil.tryClose(r3);
            throw r0;
        L_0x0139:
            r0 = new java.lang.StringBuilder;
            r1 = "DownLoad Plugin Jar Status error,response status code=";
            r0.<init>(r1);
            r1 = r11.getStatusCode();
            r0 = r0.append(r1);
            r0 = r0.toString();
            com.qq.e.v2.util.GDTLogger.report(r0);
            goto L_0x0040;
        L_0x0151:
            r0 = move-exception;
            r3 = r2;
            goto L_0x0132;
        L_0x0154:
            r0 = move-exception;
            goto L_0x0132;
        L_0x0156:
            r0 = move-exception;
            r3 = r2;
            goto L_0x0132;
        L_0x0159:
            r0 = move-exception;
            r1 = r2;
            r3 = r2;
            goto L_0x0122;
        L_0x015d:
            r0 = move-exception;
            r3 = r2;
            goto L_0x0122;
        L_0x0160:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0035;
        L_0x0164:
            r0 = move-exception;
            goto L_0x0035;
            */

        }
    }

    static {
        a = Pattern.compile(".*plugin\\.dex-(\\d+)\\.jar.*");
        g = new b();
    }

    public PM(Context r6_Context) {
        this.b = r6_Context;
        if (b()) {
            GDTLogger.d(new StringBuilder("PluginFile:\t").append(this.d == null ? "null" : this.d.getAbsolutePath()).toString());
            if (this.c != null) {
                try {
                    this.f = new DexClassLoader(this.d.getAbsolutePath(), this.b.getDir(PLUGIN.DEXDIR, 0).getAbsolutePath(), null, getClass().getClassLoader());
                } catch (Throwable th) {
                    GDTLogger.e("exception while init plugin class loader", th);
                }
            } else {
                this.f = null;
            }
        }
    }

    private int a(File r5_File, File r6_File) {
        try {
            if (!r5_File.exists() || !r6_File.exists()) {
                return 0;
            }
            String[] r0_StringA = StringUtil.readAll(r5_File).split("#####");
            if (r0_StringA.length != 2) {
                return 0;
            }
            String r2_String = r0_StringA[1];
            int r0i = StringUtil.parseInteger(r0_StringA[0], 0);
            if (!GDTST.getToolInstance().verifyFile(r2_String, r6_File)) {
                return 0;
            }
            this.c = r2_String;
            this.d = r6_File;
            return r0i;
        } catch (Throwable th) {
            GDTLogger.report("Exception while init plugin manager", th);
            return 0;
        }
    }

    private boolean b() {
        try {
            GDTLogger.d(new StringBuilder("TimeStap_BEFORE_PLUGIN_INIT:").append(System.currentTimeMillis()).toString());
            File r2_File = e();
            File r3_File = d();
            int r2i = a(r2_File, r3_File);
            GDTLogger.d(new StringBuilder("last updated plugin version =").append(r2i).append(";asset plugin version=443").toString());
            if (r2i < 443) {
                if (com.qq.e.comm.a.a(this.b, d(), e())) {
                    this.c = PLUGIN.ASSET_PLUGIN_SIG;
                    this.d = r3_File;
                    this.e = 443;
                    GDTLogger.d(new StringBuilder("TimeStap_AFTER_PLUGIN_INIT:").append(System.currentTimeMillis()).toString());
                    return true;
                } else {
                    GDTLogger.e("Fail to prepair Defult plugin ");
                    GDTLogger.d(new StringBuilder("TimeStap_AFTER_PLUGIN_INIT:").append(System.currentTimeMillis()).toString());
                    return false;
                }
            } else {
                this.e = r2i;
                GDTLogger.d(new StringBuilder("TimeStap_AFTER_PLUGIN_INIT:").append(System.currentTimeMillis()).toString());
                return true;
            }
        } catch (Throwable th) {
            GDTLogger.report("Exception while init plugin manager", th);
            GDTLogger.d(new StringBuilder("TimeStap_AFTER_PLUGIN_INIT:").append(System.currentTimeMillis()).toString());
            return false;
        }
    }

    private File c() {
        return this.b.getDir(PLUGIN.PLUGIN_DIR, 0);
    }

    private File d() {
        return new File(c(), PLUGIN.FINAL_JAR_FILE);
    }

    private File e() {
        return new File(c(), PLUGIN.FINAL_SIG_FILE);
    }

    public ActivityDelegateFactory getActivityDelegateFactory() throws a {
        return (ActivityDelegateFactory) getFactory(ActivityDelegateFactory.class);
    }

    public AdViewFactory getAdViewFactory() throws a {
        return (AdViewFactory) getFactory(AdViewFactory.class);
    }

    public AppWallViewFactory getAppWallViewFactory() throws a {
        return (AppWallViewFactory) getFactory(AppWallViewFactory.class);
    }

    public BannerViewFactory getBannerViewFactory() throws a {
        return (BannerViewFactory) getFactory(BannerViewFactory.class);
    }

    public <T> T getFactory(Class<T> r5_Class_T) throws a {
        ClassLoader r1_ClassLoader;
        GDTLogger.d(new StringBuilder("GetFactoryInstaceforInterface:").append(r5_Class_T).toString());
        r1_ClassLoader = PLUGIN.ASSET_PLUGIN_SIG == null ? getClass().getClassLoader() : this.f;
        if (r1_ClassLoader == null) {
            throw new a(new StringBuilder("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:").append(r5_Class_T).toString());
        } else {
            String r0_String = (String) g.get(r5_Class_T);
            if (StringUtil.isEmpty(r0_String)) {
                throw new a(new StringBuilder("factory  implemention name is not specified for interface:").append(r5_Class_T.getName()).toString());
            } else {
                Class r0_Class = r1_ClassLoader.loadClass(r0_String);
                return r5_Class_T.cast(r0_Class.getDeclaredMethod("getInstance", new Class[0]).invoke(r0_Class, new Object[0]));
            }
        }
    }

    public FeedsAdViewFactory getFeedsAdViewFactory() throws a {
        return (FeedsAdViewFactory) getFactory(FeedsAdViewFactory.class);
    }

    public GridAdViewFactory getGridAdViewFactory() throws a {
        return (GridAdViewFactory) getFactory(GridAdViewFactory.class);
    }

    public InterstitialViewFactory getInterstitialViewFactory() throws a {
        return (InterstitialViewFactory) getFactory(InterstitialViewFactory.class);
    }

    public String getLocalSig() {
        return this.c;
    }

    public int getPluginVersion() {
        return this.e;
    }

    public ServiceDelegateFactory getServiceDelegateFactory() throws a {
        return (ServiceDelegateFactory) getFactory(ServiceDelegateFactory.class);
    }

    public void update(String r5_String, String r6_String) {
        GDTLogger.d(new StringBuilder("TIMESTAP_BEFORE_DOWN_PLUGIN:").append(System.currentTimeMillis()).toString());
        String r0_String = "0";
        Matcher r1_Matcher = a.matcher(r6_String);
        if (r1_Matcher.matches()) {
            r0_String = r1_Matcher.group(1);
        }
        int r0i = StringUtil.parseInteger(r0_String, 0);
        if (r0i < PLUGIN.ASSET_PLUGIN_VERSION) {
            GDTLogger.w(new StringBuilder("online plugin version is smaller than asset plugin version").append(r0i).append(",443").append(".download give up").toString());
        } else {
            GDTADNetClient.getInstance().excute(new GDTPlainADNetRequest(r6_String, Method.GET, null, new a(r5_String)), Priority.High, XListViewFooter.STATE_NOMORE);
        }
    }
}
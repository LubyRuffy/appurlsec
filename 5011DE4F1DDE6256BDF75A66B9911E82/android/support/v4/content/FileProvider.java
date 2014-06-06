package android.support.v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParserException;
import qsbk.app.push.Utils;

public class FileProvider extends ContentProvider {
    private static final String[] a;
    private static final File b;
    private static HashMap<String, a> c;
    private a d;

    static interface a {
        public File getFileForUri(Uri r1_Uri);

        public Uri getUriForFile(File r1_File);
    }

    static class b implements a {
        private final String a;
        private final HashMap<String, File> b;

        public b(String r2_String) {
            this.b = new HashMap();
            this.a = r2_String;
        }

        public void addRoot(String r5_String, File r6_File) {
            if (TextUtils.isEmpty(r5_String)) {
                throw new IllegalArgumentException("Name must not be empty");
            } else {
                try {
                    this.b.put(r5_String, r6_File.getCanonicalFile());
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + r6_File, e);
                }
            }
        }

        public File getFileForUri(Uri r5_Uri) {
            String r0_String = r5_Uri.getEncodedPath();
            int r1i = r0_String.indexOf(AdViewUtil.NETWORK_TYPE_SUIZONG, 1);
            String r2_String = Uri.decode(r0_String.substring(1, r1i));
            String r1_String = Uri.decode(r0_String.substring(r1i + 1));
            File r0_File = (File) this.b.get(r2_String);
            if (r0_File == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + r5_Uri);
            } else {
                File r2_File = new File(r0_File, r1_String);
                try {
                    File r1_File = r2_File.getCanonicalFile();
                    if (r1_File.getPath().startsWith(r0_File.getPath())) {
                        return r1_File;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + r2_File);
                }
            }
        }

        public Uri getUriForFile(File r7_File) {
            try {
                String r3_String = r7_File.getCanonicalPath();
                Entry r2_Entry = null;
                Iterator r4_Iterator = this.b.entrySet().iterator();
                while (r4_Iterator.hasNext()) {
                    Entry r0_Entry = (Entry) r4_Iterator.next();
                    String r1_String = ((File) r0_Entry.getValue()).getPath();
                    if (r3_String.startsWith(r1_String)) {
                        if (r2_Entry == null || r1_String.length() > ((File) r2_Entry.getValue()).getPath().length()) {
                            r2_Entry = r0_Entry;
                        } else {
                            r0_Entry = r2_Entry;
                        }
                    } else {
                        r0_Entry = r2_Entry;
                    }
                    r2_Entry = r0_Entry;
                }
                if (r2_Entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains " + r3_String);
                } else {
                    String r0_String = ((File) r2_Entry.getValue()).getPath();
                    return new Builder().scheme(Utils.RESPONSE_CONTENT).authority(this.a).encodedPath(Uri.encode((String) r2_Entry.getKey()) + '/' + Uri.encode(r0_String.endsWith("/") ? r3_String.substring(r0_String.length()) : r3_String.substring(r0_String.length() + 1), "/")).build();
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + r7_File);
            }
        }
    }

    static {
        String[] r0_StringA = new String[2];
        r0_StringA[0] = "_display_name";
        r0_StringA[1] = "_size";
        a = r0_StringA;
        b = new File("/");
        c = new HashMap();
    }

    private static int a(String r3_String) {
        if ("r".equals(r3_String)) {
            return 268435456;
        }
        if ("w".equals(r3_String) || "wt".equals(r3_String)) {
            return 738197504;
        }
        if ("wa".equals(r3_String)) {
            return 704643072;
        }
        if ("rw".equals(r3_String)) {
            return 939524096;
        }
        if ("rwt".equals(r3_String)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + r3_String);
    }

    private static a a(Context r4_Context, String r5_String) {
        a r0_a;
        synchronized (c) {
            try {
                r0_a = (a) c.get(r5_String);
                if (r0_a == null) {
                    r0_a = b(r4_Context, r5_String);
                    c.put(r5_String, r0_a);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
            } catch (XmlPullParserException e_2) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e_2);
            } catch (Throwable th) {
            }
        }
        return r0_a;
    }

    private static File a(File r5_File, String ... r6_StringA) {
        int r3i = r6_StringA.length;
        int r2i = 0;
        File r1_File = r5_File;
        while (r2i < r3i) {
            String r4_String = r6_StringA[r2i];
            r2i++;
            r1_File = r4_String != null ? new File(r1_File, r4_String) : r1_File;
        }
        return r1_File;
    }

    private static Object[] a(Object[] r2_ObjectA, int r3i) {
        Object r0_Object = new Object[r3i];
        System.arraycopy(r2_ObjectA, 0, r0_Object, 0, r3i);
        return r0_Object;
    }

    private static String[] a(String[] r2_StringA, int r3i) {
        Object r0_Object = new Object[r3i];
        System.arraycopy(r2_StringA, 0, r0_Object, 0, r3i);
        return r0_Object;
    }

    private static a b(Context r9_Context, String r10_String) throws IOException, XmlPullParserException {
        b r2_b = new b(r10_String);
        XmlResourceParser r3_XmlResourceParser = r9_Context.getPackageManager().resolveContentProvider(r10_String, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).loadXmlMetaData(r9_Context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (r3_XmlResourceParser == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            int r0i = r3_XmlResourceParser.next();
            if (r0i == 1) {
                return r2_b;
            }
            if (r0i == 2) {
                File r0_File;
                String r0_String = r3_XmlResourceParser.getName();
                String r4_String = r3_XmlResourceParser.getAttributeValue(null, "name");
                String r5_String = r3_XmlResourceParser.getAttributeValue(null, "path");
                String[] r6_StringA;
                if ("root-path".equals(r0_String)) {
                    r0_File = b;
                    r6_StringA = new String[1];
                    r6_StringA[0] = r5_String;
                    r0_File = a(r0_File, r6_StringA);
                } else if ("files-path".equals(r0_String)) {
                    r0_File = r9_Context.getFilesDir();
                    r6_StringA = new String[1];
                    r6_StringA[0] = r5_String;
                    r0_File = a(r0_File, r6_StringA);
                } else if ("cache-path".equals(r0_String)) {
                    r0_File = r9_Context.getCacheDir();
                    r6_StringA = new String[1];
                    r6_StringA[0] = r5_String;
                    r0_File = a(r0_File, r6_StringA);
                } else if ("external-path".equals(r0_String)) {
                    r0_File = Environment.getExternalStorageDirectory();
                    r6_StringA = new String[1];
                    r6_StringA[0] = r5_String;
                    r0_File = a(r0_File, r6_StringA);
                } else {
                    r0_File = null;
                }
                if (r0_File != null) {
                    r2_b.addRoot(r4_String, r0_File);
                }
            }
        }
    }

    public static Uri getUriForFile(Context r1_Context, String r2_String, File r3_File) {
        return a(r1_Context, r2_String).getUriForFile(r3_File);
    }

    public void attachInfo(Context r3_Context, ProviderInfo r4_ProviderInfo) {
        super.attachInfo(r3_Context, r4_ProviderInfo);
        if (r4_ProviderInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (r4_ProviderInfo.grantUriPermissions) {
            this.d = a(r3_Context, r4_ProviderInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public int delete(Uri r2_Uri, String r3_String, String[] r4_StringA) {
        return this.d.getFileForUri(r2_Uri).delete() ? 1 : 0;
    }

    public String getType(Uri r4_Uri) {
        File r0_File = this.d.getFileForUri(r4_Uri);
        int r1i = r0_File.getName().lastIndexOf(AdViewUtil.NETWORK_TYPE_MOBWIN);
        if (r1i >= 0) {
            String r0_String = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r0_File.getName().substring(r1i + 1));
            if (r0_String != null) {
                return r0_String;
            }
        }
        return "application/octet-stream";
    }

    public Uri insert(Uri r3_Uri, ContentValues r4_ContentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri r3_Uri, String r4_String) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.d.getFileForUri(r3_Uri), a(r4_String));
    }

    public Cursor query(Uri r10_Uri, String[] r11_StringA, String r12_String, String[] r13_StringA, String r14_String) {
        File r3_File = this.d.getFileForUri(r10_Uri);
        if (r11_StringA == null) {
            r11_StringA = a;
        }
        String[] r4_StringA = new String[r11_StringA.length];
        Object[] r5_ObjectA = new Object[r11_StringA.length];
        int r6i = r11_StringA.length;
        int r2i = 0;
        int r1i = 0;
        while (r2i < r6i) {
            int r0i;
            Object r0_Object = r11_StringA[r2i];
            if ("_display_name".equals(r0_Object)) {
                r4_StringA[r1i] = "_display_name";
                r0i = r1i + 1;
                r5_ObjectA[r1i] = r3_File.getName();
            } else if ("_size".equals(r0_Object)) {
                r4_StringA[r1i] = "_size";
                r0i = r1i + 1;
                r5_ObjectA[r1i] = Long.valueOf(r3_File.length());
            } else {
                r0i = r1i;
            }
            r2i++;
            r1i = r0i;
        }
        String[] r0_StringA = a(r4_StringA, r1i);
        Object[] r1_ObjectA = a(r5_ObjectA, r1i);
        Cursor r2_Cursor = new MatrixCursor(r0_StringA, 1);
        r2_Cursor.addRow(r1_ObjectA);
        return r2_Cursor;
    }

    public int update(Uri r3_Uri, ContentValues r4_ContentValues, String r5_String, String[] r6_StringA) {
        throw new UnsupportedOperationException("No external updates");
    }
}
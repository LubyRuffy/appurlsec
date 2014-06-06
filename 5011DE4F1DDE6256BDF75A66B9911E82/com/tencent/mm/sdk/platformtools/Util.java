package com.tencent.mm.sdk.platformtools;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import com.androidquery.util.Constants;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.algorithm.MD5;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Character.UnicodeBlock;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class Util {
    public static final int BEGIN_TIME = 22;
    public static final int BIT_OF_KB = 10;
    public static final int BIT_OF_MB = 20;
    public static final int BYTE_OF_KB = 1024;
    public static final int BYTE_OF_MB = 1048576;
    public static final String CHINA = "zh_CN";
    public static final int DAY = 0;
    public static final int END_TIME = 8;
    public static final String ENGLISH = "en";
    public static final String HONGKONG = "zh_HK";
    public static final String LANGUAGE_DEFAULT = "language_default";
    public static final int MASK_16BIT = 65535;
    public static final int MASK_32BIT = -1;
    public static final int MASK_4BIT = 15;
    public static final int MASK_8BIT = 255;
    public static final long MAX_32BIT_VALUE = 4294967295L;
    public static final int MAX_ACCOUNT_LENGTH = 20;
    public static final int MAX_DECODE_PICTURE_SIZE = 2764800;
    public static final int MAX_PASSWORD_LENGTH = 9;
    public static final long MILLSECONDS_OF_DAY = 86400000;
    public static final long MILLSECONDS_OF_HOUR = 3600000;
    public static final long MILLSECONDS_OF_MINUTE = 60000;
    public static final long MILLSECONDS_OF_SECOND = 1000;
    public static final long MINUTE_OF_HOUR = 60;
    public static final int MIN_ACCOUNT_LENGTH = 6;
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final String PHOTO_DEFAULT_EXT = ".jpg";
    public static final long SECOND_OF_MINUTE = 60;
    public static final String TAIWAN = "zh_TW";
    private static final long[] a;
    private static final TimeZone b;
    private static final char[] c;
    private static final char[] d;
    private static final String[] e;

    static {
        a = new long[]{300, 200, 300, 200};
        b = TimeZone.getTimeZone("GMT");
        c = new char[]{'\t', '\n', '\r'};
        d = new char[]{'<', '>', '\"', '\'', '&'};
        String[] r0_StringA = new String[5];
        r0_StringA[0] = "&lt;";
        r0_StringA[1] = "&gt;";
        r0_StringA[2] = "&quot;";
        r0_StringA[3] = "&apos;";
        r0_StringA[4] = "&amp;";
        e = r0_StringA;
    }

    private Util() {
    }

    public static String GetHostIp() {
        try {
            Enumeration r1_Enumeration = NetworkInterface.getNetworkInterfaces();
            while (r1_Enumeration.hasMoreElements()) {
                Enumeration r2_Enumeration = ((NetworkInterface) r1_Enumeration.nextElement()).getInetAddresses();
                while (r2_Enumeration.hasMoreElements()) {
                    InetAddress r0_InetAddress = (InetAddress) r2_Enumeration.nextElement();
                    if (!r0_InetAddress.isLoopbackAddress()) {
                        return r0_InetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
        } catch (Exception e_2) {
        }
        return null;
    }

    public static int UnZipFolder(String r7_String, String r8_String) {
        try {
            Log.v("XZip", "UnZipFolder(String, String)");
            ZipInputStream r2_ZipInputStream = new ZipInputStream(new FileInputStream(r7_String));
            while (true) {
                ZipEntry r3_ZipEntry = r2_ZipInputStream.getNextEntry();
                if (r3_ZipEntry != null) {
                    String r4_String = r3_ZipEntry.getName();
                    if (r3_ZipEntry.isDirectory()) {
                        new File(r8_String + File.separator + r4_String.substring(DAY, r4_String.length() - 1)).mkdirs();
                    } else {
                        File r3_File = new File(r8_String + File.separator + r4_String);
                        r3_File.createNewFile();
                        FileOutputStream r4_FileOutputStream = new FileOutputStream(r3_File);
                        byte[] r3_byteA = new byte[1024];
                        while (true) {
                            int r5i = r2_ZipInputStream.read(r3_byteA);
                            if (r5i != -1) {
                                r4_FileOutputStream.write(r3_byteA, DAY, r5i);
                                r4_FileOutputStream.flush();
                            } else {
                                r4_FileOutputStream.close();
                            }
                        }
                    }
                } else {
                    r2_ZipInputStream.close();
                    return DAY;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e_2) {
            e_2.printStackTrace();
            return RequestListener.DEFAULT_LOADED_SIZE;
        }
    }

    private static int a(char[] r4_charA, int r5i, int r6i) {
        if (r6i <= 0) {
            return DAY;
        }
        if (r4_charA[r5i] == '#') {
            if (r6i > 1) {
                if (r4_charA[r5i + 1] == 'x' || r4_charA[r5i + 1] == 'X') {
                    try {
                        return Integer.parseInt(new String(r4_charA, r5i + 2, r6i - 2), Base64.URL_SAFE);
                    } catch (NumberFormatException e) {
                        return DAY;
                    }
                }
            }
            try {
                return Integer.parseInt(new String(r4_charA, r5i + 1, r6i - 1), BIT_OF_KB);
            } catch (NumberFormatException e_2) {
                return DAY;
            }
        } else {
            String r1_String = new String(r4_charA, r5i, r6i);
            return DAY;
        }
    }

    private static void a(Map<String, String> r7_Map_String__String, String r8_String, Node r9_Node, int r10i) {
        int r1i = DAY;
        if (r9_Node.getNodeName().equals("#text")) {
            r7_Map_String__String.put(r8_String, r9_Node.getNodeValue());
        } else if (r9_Node.getNodeName().equals("#cdata-section")) {
            r7_Map_String__String.put(r8_String, r9_Node.getNodeValue());
        } else {
            int r0i;
            String r2_String = r8_String + "." + r9_Node.getNodeName() + (r10i > 0 ? Integer.valueOf(r10i) : RContactStorage.PRIMARY_KEY);
            r7_Map_String__String.put(r2_String, r9_Node.getNodeValue());
            NamedNodeMap r3_NamedNodeMap = r9_Node.getAttributes();
            if (r3_NamedNodeMap != null) {
                r0i = 0;
                while (r0i < r3_NamedNodeMap.getLength()) {
                    Node r4_Node = r3_NamedNodeMap.item(r0i);
                    r7_Map_String__String.put(r2_String + ".$" + r4_Node.getNodeName(), r4_Node.getNodeValue());
                    r0i++;
                }
            }
            HashMap r3_HashMap = new HashMap();
            NodeList r4_NodeList = r9_Node.getChildNodes();
            while (r1i < r4_NodeList.getLength()) {
                Node r5_Node = r4_NodeList.item(r1i);
                r0i = nullAsNil((Integer) r3_HashMap.get(r5_Node.getNodeName()));
                a(r7_Map_String__String, r2_String, r5_Node, r0i);
                r3_HashMap.put(r5_Node.getNodeName(), Integer.valueOf(r0i + 1));
                r1i++;
            }
        }
    }

    public static byte[] bmpToByteArray(Bitmap r3_Bitmap, boolean r4z) {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        r3_Bitmap.compress(CompressFormat.JPEG, 100, r0_OutputStream);
        if (r4z) {
            r3_Bitmap.recycle();
        }
        byte[] r1_byteA = r0_OutputStream.toByteArray();
        try {
            r0_OutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r1_byteA;
    }

    public static boolean checkPermission(Context r4_Context, String r5_String) {
        boolean r0z;
        Assert.assertNotNull(r4_Context);
        String r1_String = r4_Context.getPackageName();
        r0z = r4_Context.getPackageManager().checkPermission(r5_String, r1_String) == 0;
        Log.d("MicroMsg.Util", r1_String + " has " + (r0z ? "permission " : "no permission ") + r5_String);
        return r0z;
    }

    public static boolean checkSDCardFull() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        StatFs r2_StatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long r3j = (long) r2_StatFs.getBlockCount();
        long r5j = (long) r2_StatFs.getAvailableBlocks();
        if (r3j <= 0 || r3j - r5j < 0) {
            return false;
        }
        int r1i = (int) (((r3j - r5j) * 100) / r3j);
        long r7j = ((long) r2_StatFs.getBlockSize()) * ((long) r2_StatFs.getFreeBlocks());
        Log.d("MicroMsg.Util", new StringBuilder("checkSDCardFull per:").append(r1i).append(" blockCount:").append(r3j).append(" availCount:").append(r5j).append(" availSize:").append(r7j).toString());
        return 95 <= r1i && r7j <= 52428800;
    }

    public static String convertStreamToString(InputStream r4_InputStream) {
        BufferedReader r0_BufferedReader = new BufferedReader(new InputStreamReader(r4_InputStream));
        StringBuilder r1_StringBuilder = new StringBuilder();
        while (true) {
            try {
                String r2_String = r0_BufferedReader.readLine();
                if (r2_String != null) {
                    r1_StringBuilder.append(r2_String + "\n");
                } else {
                    try {
                        r4_InputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return r1_StringBuilder.toString();
                }
            } catch (IOException e_2) {
                e_2.printStackTrace();
                r4_InputStream.close();
            }
        }
    }

    public static long currentDayInMills() {
        return (nowMilliSecond() / 86400000) * 86400000;
    }

    public static long currentMonthInMills() {
        GregorianCalendar r0_GregorianCalendar = new GregorianCalendar();
        GregorianCalendar r1_GregorianCalendar = new GregorianCalendar(r0_GregorianCalendar.get(1), r0_GregorianCalendar.get(XListViewHeader.STATE_REFRESHING), 1);
        r1_GregorianCalendar.setTimeZone(b);
        return r1_GregorianCalendar.getTimeInMillis();
    }

    public static long currentTicks() {
        return SystemClock.elapsedRealtime();
    }

    public static long currentWeekInMills() {
        GregorianCalendar r0_GregorianCalendar = new GregorianCalendar();
        GregorianCalendar r1_GregorianCalendar = new GregorianCalendar(r0_GregorianCalendar.get(1), r0_GregorianCalendar.get(XListViewHeader.STATE_REFRESHING), r0_GregorianCalendar.get(ShareUtils.SHARE_SMS));
        r1_GregorianCalendar.setTimeZone(b);
        r1_GregorianCalendar.add(MIN_ACCOUNT_LENGTH, -(r0_GregorianCalendar.get(ShareUtils.SHARE_COLLECT) - r0_GregorianCalendar.getFirstDayOfWeek()));
        return r1_GregorianCalendar.getTimeInMillis();
    }

    public static long currentYearInMills() {
        GregorianCalendar r1_GregorianCalendar = new GregorianCalendar(new GregorianCalendar().get(1), 1, 1);
        r1_GregorianCalendar.setTimeZone(b);
        return r1_GregorianCalendar.getTimeInMillis();
    }

    public static byte[] decodeHexString(String r5_String) {
        if (r5_String == null || r5_String.length() <= 0) {
            return new byte[0];
        }
        try {
            byte[] r0_byteA = new byte[(r5_String.length() / 2)];
            int r1i = 0;
            while (r1i < r0_byteA.length) {
                r0_byteA[r1i] = (byte) (Integer.parseInt(r5_String.substring(r1i * 2, (r1i * 2) + 2), Base64.URL_SAFE) & 255);
                r1i++;
            }
            return r0_byteA;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String dumpArray(Object[] r4_ObjectA) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r2i = r4_ObjectA.length;
        int r0i = DAY;
        while (r0i < r2i) {
            r1_StringBuilder.append(r4_ObjectA[r0i]);
            r1_StringBuilder.append(",");
            r0i++;
        }
        return r1_StringBuilder.toString();
    }

    public static String dumpHex(byte[] r8_byteA) {
        int r0i = DAY;
        if (r8_byteA == null) {
            return "(null)";
        }
        char[] r3_charA = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int r4i = r8_byteA.length;
        char[] r5_charA = new char[(r4i * 3 + r4i / 16)];
        int r2i = 0;
        while (r2i < r4i) {
            byte r6b = r8_byteA[r2i];
            int r1i = r0i + 1;
            r5_charA[r0i] = ' ';
            r0i = r1i + 1;
            r5_charA[r1i] = r3_charA[(r6b >>> 4) & 15];
            r1i = r0i + 1;
            r5_charA[r0i] = r3_charA[r6b & 15];
            if (r2i % 16 != 0 || r2i <= 0) {
                r0i = r1i;
                r2i++;
            } else {
                r0i = r1i + 1;
                r5_charA[r1i] = '\n';
                r2i++;
            }
        }
        return new String(r5_charA);
    }

    public static String encodeHexString(byte[] r6_byteA) {
        StringBuilder r2_StringBuilder = new StringBuilder(RContactStorage.PRIMARY_KEY);
        if (r6_byteA != null) {
            int r0i = 0;
            while (r0i < r6_byteA.length) {
                Object[] r4_ObjectA = new Object[1];
                r4_ObjectA[0] = Integer.valueOf(r6_byteA[r0i] & 255);
                r2_StringBuilder.append(String.format("%02x", r4_ObjectA));
                r0i++;
            }
        }
        return r2_StringBuilder.toString();
    }

    public static String escapeSqlValue(String r3_String) {
        return r3_String != null ? r3_String.replace("\\[", "[[]").replace("%", RContactStorage.PRIMARY_KEY).replace("\\^", RContactStorage.PRIMARY_KEY).replace("'", RContactStorage.PRIMARY_KEY).replace("\\{", RContactStorage.PRIMARY_KEY).replace("\\}", RContactStorage.PRIMARY_KEY).replace("\"", RContactStorage.PRIMARY_KEY) : r3_String;
    }

    public static String escapeStringForXml(String r8_String) {
        if (r8_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuffer r4_StringBuffer = new StringBuffer();
        int r5i = r8_String.length();
        int r3i = 0;
        while (r3i < r5i) {
            char r6c = r8_String.charAt(r3i);
            if ((r6c >= ' ' || r6c == c[0] || r6c == c[1] || r6c == c[2]) && r6c <= '\u007f') {
                int r0i = d.length - 1;
                while (r0i >= 0) {
                    if (d[r0i] == r6c) {
                        r4_StringBuffer.append(e[r0i]);
                        r0i = 0;
                        break;
                    } else {
                        r0i--;
                    }
                }
                r0i = 1;
                if (r0i != 0) {
                    r4_StringBuffer.append(r6c);
                }
                r3i++;
            } else {
                r4_StringBuffer.append("&#");
                r4_StringBuffer.append(Integer.toString(r6c));
                r4_StringBuffer.append(';');
                r3i++;
            }
        }
        return r4_StringBuffer.toString();
    }

    public static String expandEntities(String r10_String) {
        int r6i = r10_String.length();
        char[] r7_charA = new char[r6i];
        int r3i = 0;
        int r5i = 0;
        int r0i = -1;
        while (r3i < r6i) {
            char r8c = r10_String.charAt(r3i);
            int r1i = r5i + 1;
            r7_charA[r5i] = r8c;
            if (r8c == '&' && r0i == -1) {
                r0i = r1i;
            } else if (r0i == -1 || Character.isLetter(r8c) || Character.isDigit(r8c) || r8c == '#') {
                r3i++;
                r5i = r1i;
            } else if (r8c == ';') {
                r5i = a(r7_charA, r0i, r1i - r0i - 1);
                if (r5i > 65535) {
                    r1i = r5i - 65536;
                    r7_charA[r0i - 1] = (char) (r1i >> 10 + 55296);
                    r7_charA[r0i] = (char) (r1i & 1023 + 56320);
                    r0i++;
                } else if (r5i != 0) {
                    r7_charA[r0i - 1] = (char) r5i;
                } else {
                    r0i = r1i;
                }
                r1i = r0i;
                r0i = -1;
            } else {
                r0i = -1;
            }
            r3i++;
            r5i = r1i;
        }
        return new String(r7_charA, 0, r5i);
    }

    public static String formatSecToMin(int r7i) {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = Long.valueOf(((long) r7i) / 60);
        r1_ObjectA[1] = Long.valueOf(((long) r7i) % 60);
        return String.format("%d:%02d", r1_ObjectA);
    }

    public static String formatUnixTime(long r4j) {
        return new SimpleDateFormat("[yy-MM-dd HH:mm:ss]").format(new Date(1000 * r4j));
    }

    public static void freeBitmapMap(Map<String, Bitmap> r2_Map_String__Bitmap) {
        Iterator r1_Iterator = r2_Map_String__Bitmap.entrySet().iterator();
        while (r1_Iterator.hasNext()) {
            Bitmap r0_Bitmap = (Bitmap) ((Entry) r1_Iterator.next()).getValue();
            if (r0_Bitmap != null) {
                r0_Bitmap.recycle();
            }
        }
        r2_Map_String__Bitmap.clear();
    }

    public static String getCutPasswordMD5(String r2_String) {
        if (r2_String == null) {
            r2_String = RContactStorage.PRIMARY_KEY;
        }
        return r2_String.length() <= 16 ? getFullPasswordMD5(r2_String) : getFullPasswordMD5(r2_String.substring(DAY, Base64.URL_SAFE));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String getDeviceId(Context r3_Context) {
        /*
        r1 = 0;
        if (r3 != 0) goto L_0x0005;
    L_0x0003:
        r0 = r1;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = "phone";
        r0 = r3.getSystemService(r0);	 //Catch:{ SecurityException -> 0x001e, Exception -> 0x0028 }
        r0 = (android.telephony.TelephonyManager) r0;	 //Catch:{ SecurityException -> 0x001e, Exception -> 0x0028 }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        r0 = r1;
        goto L_0x0004;
    L_0x0011:
        r0 = r0.getDeviceId();	 //Catch:{ SecurityException -> 0x001e, Exception -> 0x0028 }
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        r0 = r1;
        goto L_0x0004;
    L_0x0019:
        r0 = r0.trim();	 //Catch:{ SecurityException -> 0x001e, Exception -> 0x0028 }
        goto L_0x0004;
    L_0x001e:
        r0 = move-exception;
        r0 = "MicroMsg.Util";
        r2 = "getDeviceId failed, security exception";
        com.tencent.mm.sdk.platformtools.Log.e(r0, r2);
    L_0x0026:
        r0 = r1;
        goto L_0x0004;
    L_0x0028:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0026;
        */

    }

    public static String getFullPasswordMD5(String r1_String) {
        return MD5.getMessageDigest(r1_String.getBytes());
    }

    public static int getHex(String r4_String, int r5i) {
        if (r4_String == null) {
            return r5i;
        }
        try {
            return (int) (Long.decode(r4_String).longValue() & 4294967295L);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return r5i;
        }
    }

    public static Options getImageOptions(String r5_String) {
        boolean r0z;
        r0z = r5_String != null && !r5_String.equals(RContactStorage.PRIMARY_KEY);
        Assert.assertTrue(r0z);
        Options r2_Options = new Options();
        r2_Options.inJustDecodeBounds = true;
        try {
            Bitmap r0_Bitmap = BitmapFactory.decodeFile(r5_String, r2_Options);
            if (r0_Bitmap != null) {
                r0_Bitmap.recycle();
            }
        } catch (OutOfMemoryError e) {
            Log.e("MicroMsg.Util", new StringBuilder("decode bitmap failed: ").append(e.getMessage()).toString());
        }
        return r2_Options;
    }

    public static Intent getInstallPackIntent(String r3_String, Context r4_Context) {
        boolean r0z;
        r0z = r3_String != null && !r3_String.equals(RContactStorage.PRIMARY_KEY);
        Assert.assertTrue(r0z);
        Intent r0_Intent = new Intent("android.intent.action.VIEW");
        r0_Intent.addFlags(268435456);
        r0_Intent.setDataAndType(Uri.fromFile(new File(r3_String)), "application/vnd.android.package-archive");
        return r0_Intent;
    }

    public static int getInt(String r1_String, int r2i) {
        if (r1_String == null) {
            return r2i;
        }
        try {
            return Integer.parseInt(r1_String);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return r2i;
        }
    }

    public static int getIntRandom(int r3i, int r4i) {
        Assert.assertTrue(r3i > r4i);
        return new Random(System.currentTimeMillis()).nextInt(r3i - r4i + 1) + r4i;
    }

    public static String getLine1Number(Context r3_Context) {
        if (r3_Context == null) {
            return null;
        }
        try {
            if (((TelephonyManager) r3_Context.getSystemService("phone")) == null) {
                Log.e("MicroMsg.Util", "get line1 number failed, null tm");
            }
        } catch (SecurityException e) {
            Log.e("MicroMsg.Util", "getLine1Number failed, security exception");
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
        return null;
    }

    public static long getLong(String r1_String, long r2j) {
        if (r1_String == null) {
            return r2j;
        }
        try {
            return Long.parseLong(r1_String);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return r2j;
        }
    }

    public static Element getRootElementFromXML(byte[] r3_byteA) {
        try {
            DocumentBuilder r1_DocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            if (r1_DocumentBuilder == null) {
                Log.e("MicroMsg.Util", "new Document Builder failed");
                return null;
            } else {
                try {
                    Document r1_Document = r1_DocumentBuilder.parse(new ByteArrayInputStream(r3_byteA));
                    if (r1_Document != null) {
                        return r1_Document.getDocumentElement();
                    }
                    Log.e("MicroMsg.Util", "new Document failed");
                    return null;
                } catch (SAXException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e_2) {
                    e_2.printStackTrace();
                    return null;
                }
            }
        } catch (ParserConfigurationException e_3) {
            e_3.printStackTrace();
            return null;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap r8_Bitmap, boolean r9z, float r10f) {
        Assert.assertNotNull(r8_Bitmap);
        Bitmap r0_Bitmap = Bitmap.createBitmap(r8_Bitmap.getWidth(), r8_Bitmap.getHeight(), Config.ARGB_8888);
        Canvas r1_Canvas = new Canvas(r0_Bitmap);
        Paint r2_Paint = new Paint();
        Rect r3_Rect = new Rect(0, 0, r8_Bitmap.getWidth(), r8_Bitmap.getHeight());
        RectF r4_RectF = new RectF(r3_Rect);
        r2_Paint.setAntiAlias(true);
        r2_Paint.setDither(true);
        r2_Paint.setFilterBitmap(true);
        r1_Canvas.drawARGB(DAY, DAY, DAY, DAY);
        r2_Paint.setColor(-4144960);
        r1_Canvas.drawRoundRect(r4_RectF, r10f, r10f, r2_Paint);
        r2_Paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        r1_Canvas.drawBitmap(r8_Bitmap, r3_Rect, r3_Rect, r2_Paint);
        if (r9z) {
            r8_Bitmap.recycle();
        }
        return r0_Bitmap;
    }

    public static String getSizeKB(long r5j) {
        if ((r5j >> 20) > 0) {
            return getSizeMB(r5j);
        }
        if ((r5j >> 9) > 0) {
            return (((float) Math.round((((float) r5j) * 10.0f) / 1024.0f)) / 10.0f) + "KB";
        }
        return r5j + "B";
    }

    public static String getSizeMB(long r3j) {
        return (((float) Math.round((((float) r3j) * 10.0f) / 1048576.0f)) / 10.0f) + "MB";
    }

    public static String getStack() {
        StackTraceElement[] r2_StackTraceElementA = new Throwable().getStackTrace();
        if (r2_StackTraceElementA == null || r2_StackTraceElementA.length < 2) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r1_String = RContactStorage.PRIMARY_KEY;
        int r0i = 1;
        while (r0i < r2_StackTraceElementA.length && r2_StackTraceElementA[r0i].getClassName().contains(PluginIntent.APP_PACKAGE_PATTERN)) {
            r1_String = r1_String + "[" + r2_StackTraceElementA[r0i].getClassName().substring(MASK_4BIT) + ":" + r2_StackTraceElementA[r0i].getMethodName() + "]";
            r0i++;
        }
        return r1_String;
    }

    public static int getSystemVersion(Context r2_Context, int r3i) {
        return r2_Context == null ? r3i : System.getInt(r2_Context.getContentResolver(), "sys.settings_system_version", r3i);
    }

    public static String getTimeZone() {
        int r3i = 1;
        String r1_String = getTimeZoneDef();
        int r0i = r1_String.indexOf(AdViewUtil.NETWORK_TYPE_UMENG);
        if (r0i == -1) {
            r0i = r1_String.indexOf(AdViewUtil.NETWORK_TYPE_LMMOB);
        }
        if (r0i == -1) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = r1_String.substring(r0i, r0i + 3);
        return r0_String.charAt(1) == '0' ? r0_String.substring(DAY, r3i) + r0_String.substring(XListViewHeader.STATE_REFRESHING, XListViewFooter.STATE_NOMORE) : r0_String;
    }

    public static String getTimeZoneDef() {
        int r1i = (int) (((long) TimeZone.getDefault().getRawOffset()) / 60000);
        char r0c = '+';
        if (r1i < 0) {
            r0c = '-';
            r1i = -r1i;
        }
        Object[] r3_ObjectA = new Object[3];
        r3_ObjectA[0] = Character.valueOf(r0c);
        r3_ObjectA[1] = Long.valueOf(((long) r1i) / 60);
        r3_ObjectA[2] = Long.valueOf(((long) r1i) % 60);
        return String.format("GMT%s%02d:%02d", r3_ObjectA);
    }

    public static String getTimeZoneOffset() {
        int r1i = 1;
        TimeZone r0_TimeZone = TimeZone.getDefault();
        Object[] r1_ObjectA = new Object[r1i];
        r1_ObjectA[0] = Double.valueOf(((double) (((long) (r0_TimeZone.getRawOffset() * 100)) / 3600000)) / 100.0d + ((double) (r0_TimeZone.useDaylightTime() ? 1 : 0)));
        return String.format("%.2f", r1_ObjectA);
    }

    public static String getTopActivityName(Context r2_Context) {
        try {
            return ((RunningTaskInfo) ((ActivityManager) r2_Context.getSystemService("activity")).getRunningTasks(1).get(DAY)).topActivity.getClassName();
        } catch (Exception e) {
            e.printStackTrace();
            return "(null)";
        }
    }

    public static int guessHttpContinueRecvLength(int r1i) {
        return (((r1i - 1) / 1462) + 1) * 52 + 52 + r1i;
    }

    public static int guessHttpRecvLength(int r1i) {
        return (((r1i - 1) / 1462) + 1) * 52 + 208 + r1i;
    }

    public static int guessHttpSendLength(int r1i) {
        return (((r1i - 1) / 1462) + 1) * 52 + 224 + r1i;
    }

    public static int guessTcpConnectLength() {
        return 172;
    }

    public static int guessTcpDisconnectLength() {
        return 156;
    }

    public static int guessTcpRecvLength(int r1i) {
        return (((r1i - 1) / 1462) + 1) * 52 + 40 + r1i;
    }

    public static int guessTcpSendLength(int r1i) {
        return (((r1i - 1) / 1462) + 1) * 52 + 40 + r1i;
    }

    public static void installPack(String r1_String, Context r2_Context) {
        r2_Context.startActivity(getInstallPackIntent(r1_String, r2_Context));
    }

    public static boolean isAlpha(char r1c) {
        if (r1c >= 'a' && r1c <= 'z') {
            return true;
        }
        if (r1c < 'A' || r1c > 'Z') {
            return false;
        }
        return true;
    }

    public static boolean isChinese(char r2c) {
        UnicodeBlock r0_UnicodeBlock = UnicodeBlock.of(r2c);
        return r0_UnicodeBlock == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || r0_UnicodeBlock == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || r0_UnicodeBlock == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || r0_UnicodeBlock == UnicodeBlock.GENERAL_PUNCTUATION || r0_UnicodeBlock == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || r0_UnicodeBlock == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean isDayTimeNow() {
        int r0i = new GregorianCalendar().get(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
        return (((long) r0i) > 6 ? 1 : (((long) r0i) == 6? 0 : -1)) >= 0 && ((long) r0i) < 18;
    }

    public static boolean isImgFile(String r4_String) {
        if (r4_String == null || r4_String.length() == 0) {
            Log.e("MicroMsg.Util", "isImgFile, invalid argument");
            return false;
        } else {
            if (r4_String.length() < 3 || (!new File(r4_String).exists())) {
                return false;
            }
            Options r2_Options = new Options();
            r2_Options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r4_String, r2_Options);
            return r2_Options.outWidth > 0 && r2_Options.outHeight > 0;
        }
    }

    public static boolean isIntentAvailable(Context r2_Context, Intent r3_Intent) {
        return r2_Context.getPackageManager().queryIntentActivities(r3_Intent, Constants.FLAG_ACTIVITY_NO_ANIMATION).size() > 0;
    }

    public static boolean isLockScreen(Context r1_Context) {
        try {
            return ((KeyguardManager) r1_Context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNightTime(int r2i, int r3i, int r4i) {
        if (r3i > r4i) {
            return r2i >= r3i || r2i <= r4i;
        } else {
            if (r3i >= r4i) {
                return true;
            }
            if (r2i > r4i || r2i < r3i) {
                return false;
            }
            return true;
        }
    }

    public static boolean isNullOrNil(String r1_String) {
        return r1_String == null || r1_String.length() <= 0;
    }

    public static boolean isNullOrNil(byte[] r1_byteA) {
        return r1_byteA == null || r1_byteA.length <= 0;
    }

    public static boolean isNum(char r1c) {
        return r1c >= '0' && r1c <= '9';
    }

    public static boolean isProcessRunning(Context r3_Context, String r4_String) {
        Iterator r1_Iterator = ((ActivityManager) r3_Context.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (r1_Iterator.hasNext()) {
            RunningAppProcessInfo r0_RunningAppProcessInfo = (RunningAppProcessInfo) r1_Iterator.next();
            if (r0_RunningAppProcessInfo == null || r0_RunningAppProcessInfo.processName == null || (!r0_RunningAppProcessInfo.processName.equals(r4_String))) {
            } else {
                Log.w("MicroMsg.Util", new StringBuilder("process ").append(r4_String).append(" is running").toString());
                return true;
            }
        }
        Log.w("MicroMsg.Util", new StringBuilder("process ").append(r4_String).append(" is not running").toString());
        return false;
    }

    public static boolean isServiceRunning(Context r3_Context, String r4_String) {
        Iterator r1_Iterator = ((ActivityManager) r3_Context.getSystemService("activity")).getRunningServices(a.MAX_ACTIVITY_COUNT_UNLIMITED).iterator();
        while (r1_Iterator.hasNext()) {
            RunningServiceInfo r0_RunningServiceInfo = (RunningServiceInfo) r1_Iterator.next();
            if (r0_RunningServiceInfo == null || r0_RunningServiceInfo.service == null || (!r0_RunningServiceInfo.service.getClassName().toString().equals(r4_String))) {
            } else {
                Log.w("MicroMsg.Util", new StringBuilder("service ").append(r4_String).append(" is running").toString());
                return true;
            }
        }
        Log.w("MicroMsg.Util", new StringBuilder("service ").append(r4_String).append(" is not running").toString());
        return false;
    }

    public static boolean isTopActivity(Context r5_Context) {
        String r0_String = r5_Context.getClass().getName();
        String r1_String = getTopActivityName(r5_Context);
        Log.d("MicroMsg.Util", new StringBuilder("top activity=").append(r1_String).append(", context=").append(r0_String).toString());
        return r1_String.equalsIgnoreCase(r0_String);
    }

    public static boolean isTopApplication(Context r6_Context) {
        try {
            String r0_String = ((RunningTaskInfo) ((ActivityManager) r6_Context.getSystemService("activity")).getRunningTasks(1).get(DAY)).topActivity.getClassName();
            CharSequence r2_CharSequence = r6_Context.getPackageName();
            Log.d("MicroMsg.Util", new StringBuilder("top activity=").append(r0_String).append(", context=").append(r2_CharSequence).toString());
            return r0_String.contains(r2_CharSequence);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidAccount(String r5_String) {
        if (r5_String == null) {
            return false;
        }
        String r2_String = r5_String.trim();
        if (r2_String.length() < 6 || r2_String.length() > 20 || (!isAlpha(r2_String.charAt(DAY)))) {
            return false;
        }
        int r0i = 0;
        while (r0i < r2_String.length()) {
            char r3c = r2_String.charAt(r0i);
            if (!isAlpha(r3c) && !isNum(r3c) && r3c != '-' && r3c != '_') {
                return false;
            }
            r0i++;
        }
        return true;
    }

    public static boolean isValidEmail(String r2_String) {
        return (r2_String == null || r2_String.length() <= 0) ? false : r2_String.trim().matches("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
    }

    public static boolean isValidPassword(String r4_String) {
        if (r4_String == null || r4_String.length() < 4) {
            return false;
        }
        if (r4_String.length() >= 9) {
            return true;
        }
        try {
            Integer.parseInt(r4_String);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isValidQQNum(String r5_String) {
        if (r5_String == null || r5_String.length() <= 0) {
            return false;
        }
        try {
            long r1j = Long.valueOf(r5_String.trim()).longValue();
            return (r1j > 0 ? 1 : (r1j == 0? 0 : -1)) > 0 && r1j <= 4294967295L;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean jump(Context r3_Context, String r4_String) {
        Intent r0_Intent = new Intent("android.intent.action.VIEW", Uri.parse(r4_String));
        if (isIntentAvailable(r3_Context, r0_Intent)) {
            r3_Context.startActivity(r0_Intent);
            return true;
        } else {
            Log.e("MicroMsg.Util", new StringBuilder("jump to url failed, ").append(r4_String).toString());
            return false;
        }
    }

    public static String listToString(List<String> r4_List_String, String r5_String) {
        if (r4_List_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r2_StringBuilder = new StringBuilder(RContactStorage.PRIMARY_KEY);
        int r1i = 0;
        while (r1i < r4_List_String.size()) {
            if (r1i == r4_List_String.size() - 1) {
                r2_StringBuilder.append(((String) r4_List_String.get(r1i)).trim());
            } else {
                r2_StringBuilder.append(((String) r4_List_String.get(r1i)).trim() + r5_String);
            }
            r1i++;
        }
        return r2_StringBuilder.toString();
    }

    public static String mapToXml(String r6_String, LinkedHashMap<String, String> r7_LinkedHashMap_String__String) {
        StringBuilder r2_StringBuilder = new StringBuilder();
        r2_StringBuilder.append("<key>");
        Iterator r3_Iterator = r7_LinkedHashMap_String__String.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            String r1_String = r0_Entry.getKey();
            String r0_String = r0_Entry.getValue();
            if (r1_String == null) {
                r1_String = "unknow";
            }
            if (r0_String == null) {
                r0_String = "unknow";
            }
            r2_StringBuilder.append(new StringBuilder("<").append(r1_String).append(">").toString());
            r2_StringBuilder.append(r0_String);
            r2_StringBuilder.append(new StringBuilder("</").append(r1_String).append(">").toString());
        }
        r2_StringBuilder.append("</key>");
        return r2_StringBuilder.toString();
    }

    public static long milliSecondsToNow(long r2j) {
        return System.currentTimeMillis() - r2j;
    }

    public static long nowMilliSecond() {
        return System.currentTimeMillis();
    }

    public static long nowSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static int nullAs(Integer r0_Integer, int r1i) {
        return r0_Integer == null ? r1i : r0_Integer.intValue();
    }

    public static long nullAs(Long r0_Long, long r1j) {
        return r0_Long == null ? r1j : r0_Long.longValue();
    }

    public static String nullAs(String r0_String, String r1_String) {
        return r0_String == null ? r1_String : r0_String;
    }

    public static boolean nullAs(Boolean r0_Boolean, boolean r1z) {
        return r0_Boolean == null ? r1z : r0_Boolean.booleanValue();
    }

    public static boolean nullAsFalse(Boolean r1_Boolean) {
        return r1_Boolean == null ? false : r1_Boolean.booleanValue();
    }

    public static int nullAsInt(Object r1_Object, int r2i) {
        if (r1_Object == null) {
            return r2i;
        }
        if (r1_Object instanceof Integer) {
            return ((Integer) r1_Object).intValue();
        }
        if (r1_Object instanceof Long) {
            return ((Long) r1_Object).intValue();
        }
        return r2i;
    }

    public static int nullAsNil(Integer r1_Integer) {
        return r1_Integer == null ? DAY : r1_Integer.intValue();
    }

    public static long nullAsNil(Long r2_Long) {
        return r2_Long == null ? 0 : r2_Long.longValue();
    }

    public static String nullAsNil(String r0_String) {
        return r0_String == null ? RContactStorage.PRIMARY_KEY : r0_String;
    }

    public static boolean nullAsTrue(Boolean r1_Boolean) {
        return r1_Boolean == null ? true : r1_Boolean.booleanValue();
    }

    public static Map<String, String> parseIni(String r9_String) {
        if (r9_String == null || r9_String.length() <= 0) {
            return null;
        }
        Map<String, String> r0_Map_String__String = new HashMap();
        String[] r3_StringA = r9_String.split("\n");
        int r4i = r3_StringA.length;
        int r1i = 0;
        while (r1i < r4i) {
            String r5_String = r3_StringA[r1i];
            if (r5_String == null || r5_String.length() <= 0) {
                r1i++;
            } else {
                String[] r5_StringA = r5_String.trim().split("=", XListViewHeader.STATE_REFRESHING);
                if (r5_StringA == null || r5_StringA.length < 2) {
                    r1i++;
                } else {
                    String r6_String = r5_StringA[0];
                    Object r5_Object = r5_StringA[1];
                    if (r6_String == null || r6_String.length() <= 0 || (!r6_String.matches("^[a-zA-Z0-9_]*"))) {
                        r1i++;
                    } else {
                        r0_Map_String__String.put(r6_String, r5_Object);
                        r1i++;
                    }
                }
            }
        }
        return r0_Map_String__String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Map<String, String> parseXml(String r7_String, String r8_String, String r9_String) {
        /*
        r6 = 0;
        r0 = 0;
        if (r7 == 0) goto L_0x000a;
    L_0x0004:
        r1 = r7.length();
        if (r1 > 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = new java.util.HashMap;
        r2.<init>();
        r1 = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        r1 = r1.newDocumentBuilder();	 //Catch:{ ParserConfigurationException -> 0x0022 }
        if (r1 != 0) goto L_0x0027;
    L_0x001a:
        r1 = "MicroMsg.Util";
        r2 = "new Document Builder failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x000a;
    L_0x0022:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x0027:
        r3 = new org.xml.sax.InputSource;	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        r4 = new java.io.ByteArrayInputStream;	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        r5 = r7.getBytes();	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        r4.<init>(r5);	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        r3.<init>(r4);	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        if (r9 == 0) goto L_0x003a;
    L_0x0037:
        r3.setEncoding(r9);	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
    L_0x003a:
        r3 = r1.parse(r3);	 //Catch:{ DOMException -> 0x004b, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
        r3.normalize();	 //Catch:{ DOMException -> 0x00ed, SAXException -> 0x0051, IOException -> 0x0056, Exception -> 0x005b }
    L_0x0041:
        if (r3 != 0) goto L_0x0060;
    L_0x0043:
        r1 = "MicroMsg.Util";
        r2 = "new Document failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x000a;
    L_0x004b:
        r1 = move-exception;
        r3 = r0;
    L_0x004d:
        r1.printStackTrace();
        goto L_0x0041;
    L_0x0051:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x0056:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x005b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x0060:
        r1 = r3.getDocumentElement();
        if (r1 != 0) goto L_0x006e;
    L_0x0066:
        r1 = "MicroMsg.Util";
        r2 = "getDocumentElement failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x000a;
    L_0x006e:
        if (r8 == 0) goto L_0x00bf;
    L_0x0070:
        r3 = r1.getNodeName();
        r3 = r8.equals(r3);
        if (r3 == 0) goto L_0x00bf;
    L_0x007a:
        r0 = "";
        a(r2, r0, r1, r6);
    L_0x007f:
        r0 = r2.entrySet();
        r3 = r0.iterator();
    L_0x0087:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x00ea;
    L_0x008d:
        r0 = r3.next();
        r1 = r0;
        r1 = (java.util.Map.Entry) r1;
        r4 = "MicroMsg.Util";
        r5 = new java.lang.StringBuilder;
        r0 = "key=";
        r5.<init>(r0);
        r0 = r1.getKey();
        r0 = (java.lang.String) r0;
        r0 = r5.append(r0);
        r5 = " value=";
        r5 = r0.append(r5);
        r0 = r1.getValue();
        r0 = (java.lang.String) r0;
        r0 = r5.append(r0);
        r0 = r0.toString();
        com.tencent.mm.sdk.platformtools.Log.v(r4, r0);
        goto L_0x0087;
    L_0x00bf:
        r1 = r1.getElementsByTagName(r8);
        r3 = r1.getLength();
        if (r3 > 0) goto L_0x00d2;
    L_0x00c9:
        r1 = "MicroMsg.Util";
        r2 = "parse item null";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x000a;
    L_0x00d2:
        r0 = r1.getLength();
        r3 = 1;
        if (r0 <= r3) goto L_0x00e0;
    L_0x00d9:
        r0 = "MicroMsg.Util";
        r3 = "parse items more than one";
        com.tencent.mm.sdk.platformtools.Log.w(r0, r3);
    L_0x00e0:
        r0 = "";
        r1 = r1.item(r6);
        a(r2, r0, r1, r6);
        goto L_0x007f;
    L_0x00ea:
        r0 = r2;
        goto L_0x000a;
    L_0x00ed:
        r1 = move-exception;
        goto L_0x004d;
        */

    }

    public static MediaPlayer playSound(Context r7_Context, int r8i, OnCompletionListener r9_OnCompletionListener) {
        try {
            AssetFileDescriptor r6_AssetFileDescriptor = r7_Context.getAssets().openFd(r7_Context.getString(r8i));
            MediaPlayer r0_MediaPlayer = new MediaPlayer();
            r0_MediaPlayer.setDataSource(r6_AssetFileDescriptor.getFileDescriptor(), r6_AssetFileDescriptor.getStartOffset(), r6_AssetFileDescriptor.getLength());
            r6_AssetFileDescriptor.close();
            r0_MediaPlayer.prepare();
            r0_MediaPlayer.setLooping(false);
            r0_MediaPlayer.start();
            r0_MediaPlayer.setOnCompletionListener(r9_OnCompletionListener);
            return r0_MediaPlayer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void playSound(Context r1_Context, int r2i) {
        playSound(r1_Context, r2i, new n());
    }

    public static String processXml(String r2_String) {
        return (r2_String == null || r2_String.length() == 0 || VERSION.SDK_INT >= 8) ? r2_String : expandEntities(r2_String);
    }

    public static void saveBitmapToImage(Bitmap r3_Bitmap, int r4i, CompressFormat r5_CompressFormat, String r6_String, String r7_String, boolean r8z) {
        boolean r0z;
        r0z = r6_String != null && r7_String != null;
        Assert.assertTrue(r0z);
        Log.d("MicroMsg.Util", new StringBuilder("saving to ").append(r6_String).append(r7_String).toString());
        File r0_File = new File(r6_String + r7_String);
        r0_File.createNewFile();
        try {
            OutputStream r1_OutputStream = new FileOutputStream(r0_File);
            r3_Bitmap.compress(r5_CompressFormat, r4i, r1_OutputStream);
            r1_OutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveBitmapToImage(Bitmap r3_Bitmap, int r4i, CompressFormat r5_CompressFormat, String r6_String, boolean r7z) {
        /*
        r0 = isNullOrNil(r6);
        if (r0 != 0) goto L_0x0036;
    L_0x0006:
        r0 = 1;
    L_0x0007:
        junit.framework.Assert.assertTrue(r0);
        r0 = "MicroMsg.Util";
        r1 = new java.lang.StringBuilder;
        r2 = "saving to ";
        r1.<init>(r2);
        r1 = r1.append(r6);
        r1 = r1.toString();
        com.tencent.mm.sdk.platformtools.Log.d(r0, r1);
        r0 = new java.io.File;
        r0.<init>(r6);
        r0.createNewFile();
        r2 = 0;
        r1 = new java.io.FileOutputStream;	 //Catch:{ FileNotFoundException -> 0x0038, all -> 0x0041 }
        r1.<init>(r0);	 //Catch:{ FileNotFoundException -> 0x0038, all -> 0x0041 }
        r3.compress(r5, r4, r1);	 //Catch:{ FileNotFoundException -> 0x0049 }
        r1.flush();	 //Catch:{ FileNotFoundException -> 0x0049 }
        r1.close();
    L_0x0035:
        return;
    L_0x0036:
        r0 = 0;
        goto L_0x0007;
    L_0x0038:
        r0 = move-exception;
        r1 = r2;
    L_0x003a:
        r0.printStackTrace();	 //Catch:{ all -> 0x0047 }
        r1.close();
        goto L_0x0035;
    L_0x0041:
        r0 = move-exception;
        r1 = r2;
    L_0x0043:
        r1.close();
        throw r0;
    L_0x0047:
        r0 = move-exception;
        goto L_0x0043;
    L_0x0049:
        r0 = move-exception;
        goto L_0x003a;
        */

    }

    public static long secondsToNow(long r4j) {
        return System.currentTimeMillis() / 1000 - r4j;
    }

    public static void selectPicture(Context r2_Context, int r3i) {
        Intent r0_Intent = new Intent("android.intent.action.GET_CONTENT");
        r0_Intent.setType("image/*");
        ((Activity) r2_Context).startActivityForResult(Intent.createChooser(r0_Intent, null), r3i);
    }

    public static void shake(Context r3_Context, boolean r4z) {
        Vibrator r0_Vibrator = (Vibrator) r3_Context.getSystemService("vibrator");
        if (r0_Vibrator == null) {
        } else if (r4z) {
            r0_Vibrator.vibrate(a, MASK_32BIT);
        } else {
            r0_Vibrator.cancel();
        }
    }

    public static int[] splitToIntArray(String r7_String) {
        if (r7_String == null) {
            return null;
        }
        String[] r3_StringA = r7_String.split(":");
        List r4_List = new ArrayList();
        int r5i = r3_StringA.length;
        int r2i = 0;
        while (r2i < r5i) {
            String r1_String = r3_StringA[r2i];
            if (r1_String == null || r1_String.length() <= 0) {
                r2i++;
            } else {
                try {
                    r4_List.add(Integer.valueOf(Integer.valueOf(r1_String).intValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MicroMsg.Util", "invalid port num, ignore");
                }
                r2i++;
            }
        }
        int[] r2_intA = new int[r4_List.size()];
        int r1i = 0;
        while (r1i < r2_intA.length) {
            r2_intA[r1i] = ((Integer) r4_List.get(r1i)).intValue();
            r1i++;
        }
        return r2_intA;
    }

    public static List<String> stringsToList(String[] r3_StringA) {
        if (r3_StringA == null || r3_StringA.length == 0) {
            return null;
        }
        List<String> r1_List_String = new ArrayList();
        int r0i = DAY;
        while (r0i < r3_StringA.length) {
            r1_List_String.add(r3_StringA[r0i]);
            r0i++;
        }
        return r1_List_String;
    }

    public static long ticksToNow(long r2j) {
        return SystemClock.elapsedRealtime() - r2j;
    }

    public static void transClickToSelect(View r1_View, View r2_View) {
        r1_View.setOnTouchListener(new o(r2_View, r1_View));
    }
}
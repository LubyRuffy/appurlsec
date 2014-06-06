package qsbk.app.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    private static void a(File r4_File, String r5_String) {
        try {
            FileWriter r0_FileWriter = new FileWriter(r4_File, true);
            r0_FileWriter.write(r5_String);
            r0_FileWriter.close();
        } catch (IOException e) {
            Log.e("FileUtils", "\u6587\u4ef6\u7f13\u5b58\u51fa\u9519 path:" + r4_File.getPath());
            e.printStackTrace();
        }
    }

    public static void cacheCotent(String r2_String, String r3_String, String r4_String) {
        new e("qbk-FileUtl1", r2_String, r4_String, r3_String).start();
    }

    public static String getContent(String r5_String) throws IOException {
        String r0_String = RContactStorage.PRIMARY_KEY;
        File r0_File = new File(r5_String);
        byte[] r1_byteA = new byte[1024];
        ByteArrayOutputStream r2_ByteArrayOutputStream = new ByteArrayOutputStream();
        InputStream r3_InputStream = new BufferedInputStream(new FileInputStream(r0_File));
        while (true) {
            int r0i = r3_InputStream.read(r1_byteA);
            if (r0i != -1) {
                r2_ByteArrayOutputStream.write(r1_byteA, 0, r0i);
            } else {
                byte[] r0_byteA = r2_ByteArrayOutputStream.toByteArray();
                r3_InputStream.close();
                r2_ByteArrayOutputStream.close();
                return new String(r0_byteA);
            }
        }
    }

    public static long getFileSize(File r6_File, List<File> r7_List_File) {
        long r1j = 0;
        File[] r3_FileA = r6_File.listFiles();
        int r0i = 0;
        while (r0i < r3_FileA.length) {
            if (r7_List_File != null) {
                r7_List_File.add(r3_FileA[r0i]);
            }
            r1j = r3_FileA[r0i].isDirectory() ? r1j + getFileSize(r3_FileA[r0i], r7_List_File) : r1j + r3_FileA[r0i].length();
            r0i++;
        }
        return r1j;
    }

    public static int removeOldFiles(File r5_File, FilenameFilter r6_FilenameFilter, long r7j) {
        int r1i = 0;
        if (r5_File.isDirectory()) {
            File[] r0_FileA;
            r0_FileA = r6_FilenameFilter != null ? r5_File.listFiles(r6_FilenameFilter) : r5_File.listFiles();
            if (getFileSize(r5_File, null) <= r7j) {
                return 0;
            }
            Arrays.sort(r0_FileA);
            int r3i = r0_FileA.length;
            int r2i = 0;
            while (r1i < r3i / 2) {
                if (r0_FileA[r1i].delete()) {
                    r2i++;
                }
                r1i++;
            }
            return r2i;
        } else {
            throw new IllegalArgumentException("the first param is not directory");
        }
    }

    public static void saveContent(String r6_String, String r7_String) {
        if (r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY) || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
        } else {
            File r0_File = new File(r6_String);
            if (r0_File.exists()) {
                long r1j = r0_File.lastModified();
                DebugUtil.debug("\u4e0a\u6b21\u7f13\u5b58\u65f6\u95f4\uff1a" + r1j);
                DebugUtil.debug("\u7f13\u5b58\u65f6\u95f4\u95f4\u9694\uff1a" + (System.currentTimeMillis() - r1j));
                if (System.currentTimeMillis() - r1j > 180000) {
                    r0_File.delete();
                    a(r0_File, r7_String);
                }
            } else {
                a(r0_File, r7_String);
            }
        }
    }

    public static String saveDrawable(Bitmap r7_Bitmap, String r8_String, String r9_String, Handler r10_Handler, boolean r11z) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        Message r2_Message = new Message();
        File r1_File = new File(r9_String);
        File r3_File = new File(r9_String, r8_String);
        if (!r1_File.isDirectory()) {
            r1_File.mkdirs();
        }
        OutputStream r1_OutputStream;
        if (r3_File.exists()) {
            if (r11z) {
                r3_File.delete();
                try {
                    r3_File.createNewFile();
                    r1_OutputStream = new FileOutputStream(r3_File);
                    if (r7_Bitmap.compress(CompressFormat.JPEG, 80, r1_OutputStream)) {
                        r1_OutputStream.flush();
                    }
                    r1_OutputStream.close();
                    if (r10_Handler != null) {
                        r2_Message.what = 1;
                        r2_Message.obj = r3_File.getPath();
                        r10_Handler.sendMessage(r2_Message);
                    }
                    r0_String = r3_File.getPath();
                } catch (IOException e) {
                    IOException r1_IOException = e;
                    if (r10_Handler != null) {
                        r2_Message.what = 0;
                        r2_Message.obj = "\u56fe\u7247\u4fdd\u5b58\u5931\u8d25\uff01";
                        r10_Handler.sendMessage(r2_Message);
                    }
                    r1_IOException.printStackTrace();
                }
            }
            if (r10_Handler == null) {
                return r0_String;
            }
            r2_Message.what = 1;
            r2_Message.obj = r3_File.getPath();
            r10_Handler.sendMessage(r2_Message);
            return r0_String;
        } else {
            try {
                r3_File.createNewFile();
                r1_OutputStream = new FileOutputStream(r3_File);
                if (r7_Bitmap.compress(CompressFormat.JPEG, 80, r1_OutputStream)) {
                    r1_OutputStream.flush();
                }
                r1_OutputStream.close();
                if (r10_Handler != null) {
                    r2_Message.what = 1;
                    r2_Message.obj = r3_File.getPath();
                    r10_Handler.sendMessage(r2_Message);
                }
                return r3_File.getPath();
            } catch (IOException e_2) {
                Log.e("FileUtils", e_2.toString() + "--\u5934\u50cf\u4fdd\u5b58\u65f6\u51fa\u73b0\u5f02\u5e38\uff01");
                return r0_String;
            }
        }
    }

    public static void saveDrawable(Bitmap r6_Bitmap, String r7_String, String r8_String, Handler r9_Handler) {
        Message r1_Message = new Message();
        File r2_File = new File(DeviceUtils.getSDPath() + File.separator + r8_String);
        if (!r2_File.exists()) {
            r2_File.mkdirs();
        }
        if (r2_File.getPath().equals(RContactStorage.PRIMARY_KEY)) {
            if (r9_Handler != null) {
                r1_Message.what = 0;
                r1_Message.obj = "\u672a\u53d1\u73b0SD\u5361,\u4fdd\u5b58\u5931\u8d25\uff01";
                r9_Handler.sendMessage(r1_Message);
            }
        } else {
            File r0_File = new File(r2_File.getPath() + File.separator + r7_String);
            if (r0_File.exists()) {
                if (r9_Handler != null) {
                    r1_Message.what = 1;
                    r1_Message.obj = r0_File.getPath();
                    r9_Handler.sendMessage(r1_Message);
                }
            } else {
                try {
                    OutputStream r2_OutputStream = new FileOutputStream(r0_File);
                    if (r6_Bitmap.compress(CompressFormat.JPEG, 85, r2_OutputStream)) {
                        r2_OutputStream.flush();
                    }
                    r2_OutputStream.close();
                    if (r9_Handler != null) {
                        r1_Message.what = 1;
                        r1_Message.obj = r0_File.getPath();
                        r9_Handler.sendMessage(r1_Message);
                    }
                } catch (IOException e) {
                    IOException r0_IOException = e;
                    if (r9_Handler != null) {
                        r1_Message.what = 0;
                        r1_Message.obj = "\u56fe\u7247\u4fdd\u5b58\u5931\u8d25\uff01";
                        r9_Handler.sendMessage(r1_Message);
                    }
                    r0_IOException.printStackTrace();
                }
            }
        }
    }
}
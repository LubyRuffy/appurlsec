package org.apache.cordova;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.webkit.MimeTypeMap;
import com.baidu.location.LocationClientOption;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import org.apache.commons.codec.binary.Base64;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.apache.cordova.file.EncodingException;
import org.apache.cordova.file.FileExistsException;
import org.apache.cordova.file.InvalidModificationException;
import org.apache.cordova.file.NoModificationAllowedException;
import org.apache.cordova.file.TypeMismatchException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.push.Utils;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class FileUtils extends CordovaPlugin {
    public static int ABORT_ERR = 0;
    public static int APPLICATION = 0;
    public static int ENCODING_ERR = 0;
    public static int INVALID_MODIFICATION_ERR = 0;
    public static int INVALID_STATE_ERR = 0;
    private static final String LOG_TAG = "FileUtils";
    public static int NOT_FOUND_ERR = 0;
    public static int NOT_READABLE_ERR = 0;
    public static int NO_MODIFICATION_ALLOWED_ERR = 0;
    public static int PATH_EXISTS_ERR = 0;
    public static int PERSISTENT = 0;
    public static int QUOTA_EXCEEDED_ERR = 0;
    public static int RESOURCE = 0;
    public static int SECURITY_ERR = 0;
    public static int SYNTAX_ERR = 0;
    public static int TEMPORARY = 0;
    public static int TYPE_MISMATCH_ERR = 0;
    private static final String _DATA = "_data";
    FileReader f_in;
    FileWriter f_out;

    static {
        NOT_FOUND_ERR = 1;
        SECURITY_ERR = 2;
        ABORT_ERR = 3;
        NOT_READABLE_ERR = 4;
        ENCODING_ERR = 5;
        NO_MODIFICATION_ALLOWED_ERR = 6;
        INVALID_STATE_ERR = 7;
        SYNTAX_ERR = 8;
        INVALID_MODIFICATION_ERR = 9;
        QUOTA_EXCEEDED_ERR = 10;
        TYPE_MISMATCH_ERR = 11;
        PATH_EXISTS_ERR = 12;
        TEMPORARY = 0;
        PERSISTENT = 1;
        RESOURCE = 2;
        APPLICATION = 3;
    }

    private boolean atRootDirectory(String r4_String) {
        String r0_String = getRealPathFromURI(Uri.parse(r4_String), this.cordova);
        return r0_String.equals(new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Android/data/").append(this.cordova.getActivity().getPackageName()).append("/cache").toString()) || r0_String.equals(Environment.getExternalStorageDirectory().getAbsolutePath()) || r0_String.equals("/data/data/" + this.cordova.getActivity().getPackageName());
    }

    private void copyAction(File r9_File, File r10_File) throws FileNotFoundException, IOException {
        FileInputStream r6_FileInputStream = new FileInputStream(r9_File);
        FileOutputStream r7_FileOutputStream = new FileOutputStream(r10_File);
        FileChannel r0_FileChannel = r6_FileInputStream.getChannel();
        FileChannel r5_FileChannel = r7_FileOutputStream.getChannel();
        r0_FileChannel.transferTo(0, r0_FileChannel.size(), r5_FileChannel);
        r6_FileInputStream.close();
        r7_FileOutputStream.close();
        r0_FileChannel.close();
        r5_FileChannel.close();
    }

    private JSONObject copyDirectory(File r8_File, File r9_File) throws JSONException, IOException, NoModificationAllowedException, InvalidModificationException {
        if (r9_File.exists() && r9_File.isFile()) {
            throw new InvalidModificationException("Can't rename a file to a directory");
        } else if (isCopyOnItself(r8_File.getAbsolutePath(), r9_File.getAbsolutePath())) {
            throw new InvalidModificationException("Can't copy itself into itself");
        } else if (r9_File.exists() || r9_File.mkdir()) {
            File[] r1_FileA = r8_File.listFiles();
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                File r3_File = r1_FileA[r0i];
                if (r3_File.isDirectory()) {
                    copyDirectory(r3_File, r9_File);
                } else {
                    copyFile(r3_File, new File(r9_File.getAbsoluteFile() + File.separator + r3_File.getName()));
                }
                r0i++;
            }
            return getEntry(r9_File);
        } else {
            throw new NoModificationAllowedException("Couldn't create the destination directory");
        }
    }

    private JSONObject copyFile(File r3_File, File r4_File) throws IOException, InvalidModificationException, JSONException {
        if (r4_File.exists() && r4_File.isDirectory()) {
            throw new InvalidModificationException("Can't rename a file to a directory");
        } else {
            copyAction(r3_File, r4_File);
            return getEntry(r4_File);
        }
    }

    private File createDestination(String r4_String, File r5_File, File r6_File) {
        if ("null".equals(r4_String) || RContactStorage.PRIMARY_KEY.equals(r4_String)) {
            r4_String = null;
            return r4_String == null ? new File(r6_File.getAbsolutePath() + File.separator + r4_String) : new File(r6_File.getAbsolutePath() + File.separator + r5_File.getName());
        } else if (r4_String == null) {
        }
    }

    private File createFileObject(String r3_String) {
        return new File(getRealPathFromURI(Uri.parse(r3_String), this.cordova));
    }

    private File createFileObject(String r4_String, String r5_String) {
        if (r5_String.startsWith("/")) {
            return new File(r5_String);
        }
        return new File(getRealPathFromURI(Uri.parse(r4_String), this.cordova) + File.separator + r5_String);
    }

    private JSONObject getEntry(String r2_String) throws JSONException {
        return getEntry(new File(r2_String));
    }

    private JSONObject getFile(String r4_String, String r5_String, JSONObject r6_JSONObject, boolean r7z) throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException {
        boolean r1z;
        boolean r0z = false;
        if (r6_JSONObject != null) {
            r1z = r6_JSONObject.optBoolean("create");
            if (r1z) {
                r0z = r6_JSONObject.optBoolean("exclusive");
            }
        } else {
            r1z = false;
        }
        if (r5_String.contains(":")) {
            throw new EncodingException("This file has a : in it's name");
        } else {
            File r2_File = createFileObject(r4_String, r5_String);
            if (r1z) {
                if (r0z && r2_File.exists()) {
                    throw new FileExistsException("create/exclusive fails");
                } else {
                    if (r7z) {
                        r2_File.mkdir();
                    } else {
                        r2_File.createNewFile();
                    }
                    if (!r2_File.exists()) {
                        throw new FileExistsException("create fails");
                    }
                }
            } else if (r2_File.exists()) {
                if (r7z) {
                    if (r2_File.isFile()) {
                        throw new TypeMismatchException("path doesn't exist or is file");
                    }
                } else if (r2_File.isDirectory()) {
                    throw new TypeMismatchException("path doesn't exist or is directory");
                }
            } else {
                throw new FileNotFoundException("path does not exist");
            }
            return getEntry(r2_File);
        }
    }

    private JSONObject getFileMetadata(String r6_String) throws FileNotFoundException, JSONException {
        File r0_File = createFileObject(r6_String);
        if (r0_File.exists()) {
            JSONObject r1_JSONObject = new JSONObject();
            r1_JSONObject.put("size", r0_File.length());
            r1_JSONObject.put(QsbkDatabase.TYPE, getMimeType(r6_String));
            r1_JSONObject.put("name", r0_File.getName());
            r1_JSONObject.put("fullPath", r6_String);
            r1_JSONObject.put("lastModifiedDate", r0_File.lastModified());
            return r1_JSONObject;
        } else {
            throw new FileNotFoundException("File: " + r6_String + " does not exist.");
        }
    }

    private long getMetadata(String r3_String) throws FileNotFoundException {
        File r0_File = createFileObject(r3_String);
        if (r0_File.exists()) {
            return r0_File.lastModified();
        }
        throw new FileNotFoundException("Failed to find file in getMetadata");
    }

    public static String getMimeType(String r4_String) {
        if (r4_String == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        String r0_String = r4_String.replace(" ", "%20").toLowerCase();
        MimeTypeMap r1_MimeTypeMap = MimeTypeMap.getSingleton();
        r0_String = MimeTypeMap.getFileExtensionFromUrl(r0_String);
        return r0_String.toLowerCase().equals("3ga") ? "audio/3gpp" : r1_MimeTypeMap.getMimeTypeFromExtension(r0_String);
    }

    private JSONObject getParent(String r3_String) throws JSONException {
        String r0_String = getRealPathFromURI(Uri.parse(r3_String), this.cordova);
        return atRootDirectory(r0_String) ? getEntry(r0_String) : getEntry(new File(r0_String).getParent());
    }

    private InputStream getPathFromUri(String r3_String) throws FileNotFoundException {
        if (!r3_String.startsWith(Utils.RESPONSE_CONTENT)) {
            return new FileInputStream(getRealPathFromURI(Uri.parse(r3_String), this.cordova));
        }
        return this.cordova.getActivity().getContentResolver().openInputStream(Uri.parse(r3_String));
    }

    protected static String getRealPathFromURI(Uri r6_Uri, CordovaInterface r7_CordovaInterface) {
        String r0_String = r6_Uri.getScheme();
        if (r0_String == null) {
            return r6_Uri.toString();
        }
        if (r0_String.compareTo(Utils.RESPONSE_CONTENT) == 0) {
            String[] r2_StringA = new String[1];
            r2_StringA[0] = _DATA;
            Cursor r0_Cursor = r7_CordovaInterface.getActivity().managedQuery(r6_Uri, r2_StringA, null, null, null);
            int r1i = r0_Cursor.getColumnIndexOrThrow(_DATA);
            r0_Cursor.moveToFirst();
            return r0_Cursor.getString(r1i);
        } else {
            if (r0_String.compareTo("file") == 0) {
                return r6_Uri.getPath();
            }
            return r6_Uri.toString();
        }
    }

    private boolean isCopyOnItself(String r3_String, String r4_String) {
        return r4_String.startsWith(r3_String) && r4_String.indexOf(File.separator, r3_String.length() - 1) != -1;
    }

    private JSONObject moveDirectory(File r3_File, File r4_File) throws IOException, JSONException, InvalidModificationException, NoModificationAllowedException, FileExistsException {
        if (r4_File.exists() && r4_File.isFile()) {
            throw new InvalidModificationException("Can't rename a file to a directory");
        } else if (isCopyOnItself(r3_File.getAbsolutePath(), r4_File.getAbsolutePath())) {
            throw new InvalidModificationException("Can't move itself into itself");
        } else if ((!r4_File.exists()) || r4_File.list().length <= 0) {
            if (!r3_File.renameTo(r4_File)) {
                copyDirectory(r3_File, r4_File);
                if (r4_File.exists()) {
                    removeDirRecursively(r3_File);
                } else {
                    throw new IOException("moved failed");
                }
            }
            return getEntry(r4_File);
        } else {
            throw new InvalidModificationException("directory is not empty");
        }
    }

    private JSONObject moveFile(File r3_File, File r4_File) throws IOException, JSONException, InvalidModificationException {
        if (r4_File.exists() && r4_File.isDirectory()) {
            throw new InvalidModificationException("Can't rename a file to a directory");
        } else {
            if (!r3_File.renameTo(r4_File)) {
                copyAction(r3_File, r4_File);
                if (r4_File.exists()) {
                    r3_File.delete();
                } else {
                    throw new IOException("moved failed");
                }
            }
            return getEntry(r4_File);
        }
    }

    private void notifyDelete(String r7_String) {
        String r0_String = getRealPathFromURI(Uri.parse(r7_String), this.cordova);
        try {
            ContentResolver r1_ContentResolver = this.cordova.getActivity().getContentResolver();
            Uri r2_Uri = Media.EXTERNAL_CONTENT_URI;
            String[] r4_StringA = new String[1];
            r4_StringA[0] = r0_String;
            r1_ContentResolver.delete(r2_Uri, "_data = ?", r4_StringA);
        } catch (UnsupportedOperationException e) {
        }
    }

    private JSONArray readEntries(String r5_String) throws FileNotFoundException, JSONException {
        File r0_File = createFileObject(r5_String);
        if (r0_File.exists()) {
            JSONArray r1_JSONArray = new JSONArray();
            if (r0_File.isDirectory()) {
                File[] r2_FileA = r0_File.listFiles();
                int r0i = 0;
                while (r0i < r2_FileA.length) {
                    if (r2_FileA[r0i].canRead()) {
                        r1_JSONArray.put(getEntry(r2_FileA[r0i]));
                    }
                    r0i++;
                }
            }
            return r1_JSONArray;
        } else {
            throw new FileNotFoundException();
        }
    }

    private boolean remove(String r3_String) throws NoModificationAllowedException, InvalidModificationException {
        File r0_File = createFileObject(r3_String);
        if (atRootDirectory(r3_String)) {
            throw new NoModificationAllowedException("You can't delete the root directory");
        } else {
            if ((!r0_File.isDirectory()) || r0_File.list().length <= 0) {
                return r0_File.delete();
            }
            throw new InvalidModificationException("You can't delete a directory that is not empty.");
        }
    }

    private boolean removeDirRecursively(File r5_File) throws FileExistsException {
        if (r5_File.isDirectory()) {
            File[] r1_FileA = r5_File.listFiles();
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                removeDirRecursively(r1_FileA[r0i]);
                r0i++;
            }
        }
        if (r5_File.delete()) {
            return true;
        }
        throw new FileExistsException("could not delete: " + r5_File.getName());
    }

    private boolean removeRecursively(String r3_String) throws FileExistsException {
        return atRootDirectory(r3_String) ? false : removeDirRecursively(createFileObject(r3_String));
    }

    private JSONObject requestFileSystem(int r5i) throws IOException, JSONException {
        JSONObject r0_JSONObject = new JSONObject();
        if (r5i == TEMPORARY) {
            r0_JSONObject.put("name", "temporary");
            if (Environment.getExternalStorageState().equals("mounted")) {
                new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + this.cordova.getActivity().getPackageName() + "/cache/").mkdirs();
                r0_JSONObject.put("root", getEntry(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + this.cordova.getActivity().getPackageName() + "/cache/"));
            } else {
                new File("/data/data/" + this.cordova.getActivity().getPackageName() + "/cache/").mkdirs();
                r0_JSONObject.put("root", getEntry("/data/data/" + this.cordova.getActivity().getPackageName() + "/cache/"));
            }
        } else if (r5i == PERSISTENT) {
            r0_JSONObject.put("name", "persistent");
            if (Environment.getExternalStorageState().equals("mounted")) {
                r0_JSONObject.put("root", getEntry(Environment.getExternalStorageDirectory()));
            } else {
                r0_JSONObject.put("root", getEntry("/data/data/" + this.cordova.getActivity().getPackageName()));
            }
        } else {
            throw new IOException("No filesystem of type requested");
        }
        return r0_JSONObject;
    }

    private JSONObject resolveLocalFileSystemURI(String r7_String) throws IOException, JSONException {
        File r0_File;
        String r1_String = URLDecoder.decode(r7_String, Base.UTF8);
        int r2i;
        if (r1_String.startsWith("content:")) {
            Activity r0_Activity = this.cordova.getActivity();
            Uri r1_Uri = Uri.parse(r1_String);
            String[] r2_StringA = new String[1];
            r2_StringA[0] = _DATA;
            Cursor r1_Cursor = r0_Activity.managedQuery(r1_Uri, r2_StringA, null, null, null);
            r2i = r1_Cursor.getColumnIndexOrThrow(_DATA);
            r1_Cursor.moveToFirst();
            r0_File = new File(r1_Cursor.getString(r2i));
        } else {
            URL r0_URL = new URL(r1_String);
            if (r1_String.startsWith("file://")) {
                r2i = r1_String.indexOf("?");
                r0_File = r2i < 0 ? new File(r1_String.substring(ShareUtils.SHARE_COLLECT, r1_String.length())) : new File(r1_String.substring(ShareUtils.SHARE_COLLECT, r2i));
            } else {
                r0_File = new File(r1_String);
            }
        }
        if (r0_File.exists()) {
            if (r0_File.canRead()) {
                return getEntry(r0_File);
            }
            throw new IOException();
        } else {
            throw new FileNotFoundException();
        }
    }

    public static String stripFileProtocol(String r1_String) {
        return r1_String.startsWith("file://") ? r1_String.substring(ShareUtils.SHARE_COLLECT) : r1_String;
    }

    private JSONObject transferTo(String r5_String, String r6_String, String r7_String, boolean r8z) throws JSONException, NoModificationAllowedException, IOException, InvalidModificationException, EncodingException, FileExistsException {
        String r0_String = getRealPathFromURI(Uri.parse(r5_String), this.cordova);
        String r1_String = getRealPathFromURI(Uri.parse(r6_String), this.cordova);
        if (r7_String == null || (!r7_String.contains(":"))) {
            File r2_File = new File(r0_String);
            if (r2_File.exists()) {
                File r0_File = new File(r1_String);
                if (r0_File.exists()) {
                    r0_File = createDestination(r7_String, r2_File, r0_File);
                    if (r2_File.getAbsolutePath().equals(r0_File.getAbsolutePath())) {
                        throw new InvalidModificationException("Can't copy a file onto itself");
                    } else if (r2_File.isDirectory()) {
                        return r8z ? moveDirectory(r2_File, r0_File) : copyDirectory(r2_File, r0_File);
                    } else {
                        if (!r8z) {
                            return copyFile(r2_File, r0_File);
                        }
                        JSONObject r0_JSONObject = moveFile(r2_File, r0_File);
                        if (!r5_String.startsWith("content://")) {
                            return r0_JSONObject;
                        }
                        notifyDelete(r5_String);
                        return r0_JSONObject;
                    }
                } else {
                    throw new FileNotFoundException("The source does not exist");
                }
            } else {
                throw new FileNotFoundException("The source does not exist");
            }
        } else {
            throw new EncodingException("Bad file name");
        }
    }

    private long truncateFile(String r5_String, long r6j) throws FileNotFoundException, IOException, NoModificationAllowedException {
        if (r5_String.startsWith("content://")) {
            throw new NoModificationAllowedException("Couldn't truncate file given its content URI");
        } else {
            RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(getRealPathFromURI(Uri.parse(r5_String), this.cordova), "rw");
            if (r1_RandomAccessFile.length() >= r6j) {
                r1_RandomAccessFile.getChannel().truncate(r6j);
            } else {
                r6j = r1_RandomAccessFile.length();
            }
            r1_RandomAccessFile.close();
            return r6j;
        }
    }

    public boolean execute(String r9_String, JSONArray r10_JSONArray, CallbackContext r11_CallbackContext) throws JSONException {
        int r2i = a.MAX_ACTIVITY_COUNT_UNLIMITED;
        boolean r0z = false;
        try {
            if (r9_String.equals("testSaveLocationExists")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, DirectoryManager.testSaveLocationExists()));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getFreeDiskSpace")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, (float) DirectoryManager.getFreeDiskSpace(false)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("testFileExists")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, DirectoryManager.testFileExists(r10_JSONArray.getString(0))));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("testDirectoryExists")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, DirectoryManager.testFileExists(r10_JSONArray.getString(0))));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("readAsText")) {
                if (r10_JSONArray.length() >= 3) {
                    r0z = r10_JSONArray.getInt(XListViewHeader.STATE_REFRESHING);
                }
                if (r10_JSONArray.length() >= 4) {
                    r2i = r10_JSONArray.getInt(XListViewFooter.STATE_NOMORE);
                }
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, readAsText(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r0z, r2i)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("readAsDataURL")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, readAsDataURL(r10_JSONArray.getString(0), r10_JSONArray.length() >= 2 ? r10_JSONArray.getInt(1) : 0, r10_JSONArray.length() >= 3 ? r10_JSONArray.getInt(XListViewHeader.STATE_REFRESHING) : 2147483647)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("write")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, (float) write(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r10_JSONArray.getInt(XListViewHeader.STATE_REFRESHING))));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("truncate")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, (float) truncateFile(r10_JSONArray.getString(0), r10_JSONArray.getLong(1))));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("requestFileSystem")) {
                long r2j = r10_JSONArray.optLong(1);
                if (r2j == 0 || r2j <= DirectoryManager.getFreeDiskSpace(true) * 1024) {
                    r11_CallbackContext.success(requestFileSystem(r10_JSONArray.getInt(0)));
                } else {
                    r11_CallbackContext.sendPluginResult(new PluginResult(Status.ERROR, QUOTA_EXCEEDED_ERR));
                }
                r0z = true;
                return r0z;
            } else if (r9_String.equals("resolveLocalFileSystemURI")) {
                r11_CallbackContext.success(resolveLocalFileSystemURI(r10_JSONArray.getString(0)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getMetadata")) {
                r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, (float) getMetadata(r10_JSONArray.getString(0))));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getFileMetadata")) {
                r11_CallbackContext.success(getFileMetadata(r10_JSONArray.getString(0)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getParent")) {
                r11_CallbackContext.success(getParent(r10_JSONArray.getString(0)));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getDirectory")) {
                r11_CallbackContext.success(getFile(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r10_JSONArray.optJSONObject(XListViewHeader.STATE_REFRESHING), true));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("getFile")) {
                r11_CallbackContext.success(getFile(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r10_JSONArray.optJSONObject(XListViewHeader.STATE_REFRESHING), false));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("remove")) {
                if (remove(r10_JSONArray.getString(0))) {
                    notifyDelete(r10_JSONArray.getString(0));
                    r11_CallbackContext.success();
                    r0z = true;
                    return r0z;
                } else {
                    r11_CallbackContext.error(NO_MODIFICATION_ALLOWED_ERR);
                    r0z = true;
                    return r0z;
                }
            } else if (r9_String.equals("removeRecursively")) {
                if (removeRecursively(r10_JSONArray.getString(0))) {
                    r11_CallbackContext.success();
                    r0z = true;
                    return r0z;
                } else {
                    r11_CallbackContext.error(NO_MODIFICATION_ALLOWED_ERR);
                    r0z = true;
                    return r0z;
                }
            } else if (r9_String.equals("moveTo")) {
                r11_CallbackContext.success(transferTo(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r10_JSONArray.getString(XListViewHeader.STATE_REFRESHING), true));
                r0z = true;
                return r0z;
            } else if (r9_String.equals("copyTo")) {
                r11_CallbackContext.success(transferTo(r10_JSONArray.getString(0), r10_JSONArray.getString(1), r10_JSONArray.getString(XListViewHeader.STATE_REFRESHING), false));
                r0z = true;
                return r0z;
            } else {
                if (r9_String.equals("readEntries")) {
                    r11_CallbackContext.success(readEntries(r10_JSONArray.getString(0)));
                    r0z = true;
                }
                return r0z;
            }
        } catch (FileNotFoundException e) {
            r11_CallbackContext.error(NOT_FOUND_ERR);
        } catch (FileExistsException e_2) {
            r11_CallbackContext.error(PATH_EXISTS_ERR);
        } catch (NoModificationAllowedException e_3) {
            r11_CallbackContext.error(NO_MODIFICATION_ALLOWED_ERR);
        } catch (InvalidModificationException e_4) {
            r11_CallbackContext.error(INVALID_MODIFICATION_ERR);
        } catch (MalformedURLException e_5) {
            r11_CallbackContext.error(ENCODING_ERR);
        } catch (IOException e_6) {
            r11_CallbackContext.error(INVALID_MODIFICATION_ERR);
        } catch (EncodingException e_7) {
            r11_CallbackContext.error(ENCODING_ERR);
        } catch (TypeMismatchException e_8) {
            r11_CallbackContext.error(TYPE_MISMATCH_ERR);
        }
    }

    public JSONObject getEntry(File r5_File) throws JSONException {
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put("isFile", r5_File.isFile());
        r0_JSONObject.put("isDirectory", r5_File.isDirectory());
        r0_JSONObject.put("name", r5_File.getName());
        r0_JSONObject.put("fullPath", "file://" + r5_File.getAbsolutePath());
        return r0_JSONObject;
    }

    public boolean isSynch(String r3_String) {
        return r3_String.equals("testSaveLocationExists") || r3_String.equals("getFreeDiskSpace") || r3_String.equals("testFileExists") || r3_String.equals("testDirectoryExists");
    }

    public String readAsDataURL(String r9_String, int r10i, int r11i) throws FileNotFoundException, IOException {
        String r0_String;
        int r0i = r11i - r10i;
        byte[] r1_byteA = new byte[1000];
        BufferedInputStream r2_BufferedInputStream = new BufferedInputStream(getPathFromUri(r9_String), 1024);
        ByteArrayOutputStream r3_ByteArrayOutputStream = new ByteArrayOutputStream();
        if (r10i > 0) {
            r2_BufferedInputStream.skip((long) r10i);
        }
        while (r0i > 0) {
            int r4i = r2_BufferedInputStream.read(r1_byteA, 0, Math.min(LocationClientOption.MIN_SCAN_SPAN, r0i));
            if (r4i >= 0) {
                r0i -= r4i;
                r3_ByteArrayOutputStream.write(r1_byteA, 0, r4i);
            } else {
                break;
            }
        }
        if (r9_String.startsWith("content:")) {
            r0_String = this.cordova.getActivity().getContentResolver().getType(Uri.parse(r9_String));
        } else {
            r0_String = getMimeType(r9_String);
        }
        return "data:" + r0_String + ";base64," + new String(Base64.encodeBase64(r3_ByteArrayOutputStream.toByteArray()));
    }

    public String readAsText(String r9_String, String r10_String, int r11i, int r12i) throws FileNotFoundException, IOException {
        int r0i = r12i - r11i;
        byte[] r1_byteA = new byte[1000];
        BufferedInputStream r2_BufferedInputStream = new BufferedInputStream(getPathFromUri(r9_String), 1024);
        ByteArrayOutputStream r3_ByteArrayOutputStream = new ByteArrayOutputStream();
        if (r11i > 0) {
            r2_BufferedInputStream.skip((long) r11i);
        }
        while (r0i > 0) {
            int r4i = r2_BufferedInputStream.read(r1_byteA, 0, Math.min(LocationClientOption.MIN_SCAN_SPAN, r0i));
            if (r4i >= 0) {
                r0i -= r4i;
                r3_ByteArrayOutputStream.write(r1_byteA, 0, r4i);
            } else {
                break;
            }
        }
        return new String(r3_ByteArrayOutputStream.toByteArray(), r10_String);
    }

    public long write(String r7_String, String r8_String, int r9i) throws FileNotFoundException, IOException, NoModificationAllowedException {
        if (r7_String.startsWith("content://")) {
            throw new NoModificationAllowedException("Couldn't write to file given its content URI");
        } else {
            boolean r0z;
            String r2_String = getRealPathFromURI(Uri.parse(r7_String), this.cordova);
            if (r9i > 0) {
                truncateFile(r2_String, (long) r9i);
                r0z = true;
            } else {
                r0z = false;
            }
            byte[] r3_byteA = r8_String.getBytes();
            ByteArrayInputStream r4_ByteArrayInputStream = new ByteArrayInputStream(r3_byteA);
            FileOutputStream r5_FileOutputStream = new FileOutputStream(r2_String, r0z);
            byte[] r0_byteA = new byte[r3_byteA.length];
            r4_ByteArrayInputStream.read(r0_byteA, 0, r0_byteA.length);
            r5_FileOutputStream.write(r0_byteA, 0, r3_byteA.length);
            r5_FileOutputStream.flush();
            r5_FileOutputStream.close();
            return (long) r3_byteA.length;
        }
    }
}
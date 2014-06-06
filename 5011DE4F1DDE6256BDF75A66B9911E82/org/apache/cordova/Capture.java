package org.apache.cordova;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.push.Utils;

public class Capture extends CordovaPlugin {
    private static final String AUDIO_3GPP = "audio/3gpp";
    private static final int CAPTURE_AUDIO = 0;
    private static final int CAPTURE_IMAGE = 1;
    private static final int CAPTURE_INTERNAL_ERR = 0;
    private static final int CAPTURE_NO_MEDIA_FILES = 3;
    private static final int CAPTURE_VIDEO = 2;
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String LOG_TAG = "Capture";
    private static final String VIDEO_3GPP = "video/3gpp";
    private static final String VIDEO_MP4 = "video/mp4";
    private CallbackContext callbackContext;
    private double duration;
    private long limit;
    private int numPics;
    private JSONArray results;

    private void captureAudio() {
        this.cordova.startActivityForResult(this, new Intent("android.provider.MediaStore.RECORD_SOUND"), CAPTURE_INTERNAL_ERR);
    }

    private void captureImage() {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent r0_Intent = new Intent("android.media.action.IMAGE_CAPTURE");
        r0_Intent.putExtra("output", Uri.fromFile(new File(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()), "Capture.jpg")));
        this.cordova.startActivityForResult(this, r0_Intent, CAPTURE_IMAGE);
    }

    private void captureVideo(double r4d) {
        this.cordova.startActivityForResult(this, new Intent("android.media.action.VIDEO_CAPTURE"), CAPTURE_VIDEO);
    }

    private void checkForDuplicateImage() {
        Uri r0_Uri = whichContentStore();
        Cursor r1_Cursor = queryImgDB(r0_Uri);
        if (r1_Cursor.getCount() - this.numPics == CAPTURE_VIDEO) {
            r1_Cursor.moveToLast();
            this.cordova.getActivity().getContentResolver().delete(Uri.parse(r0_Uri + "/" + (Integer.valueOf(r1_Cursor.getString(r1_Cursor.getColumnIndex("_id"))).intValue() - 1)), null, null);
        }
    }

    private JSONObject createErrorObject(int r3i, String r4_String) {
        JSONObject r0_JSONObject = new JSONObject();
        try {
            r0_JSONObject.put("code", r3i);
            r0_JSONObject.put(Utils.EXTRA_MESSAGE, r4_String);
        } catch (JSONException e) {
        }
        return r0_JSONObject;
    }

    private JSONObject createMediaFile(Uri r6_Uri) {
        File r0_File = new File(FileUtils.getRealPathFromURI(r6_Uri, this.cordova));
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("name", r0_File.getName());
            r1_JSONObject.put("fullPath", "file://" + r0_File.getAbsolutePath());
            if (r0_File.getAbsoluteFile().toString().endsWith(".3gp") || r0_File.getAbsoluteFile().toString().endsWith(".3gpp")) {
                if (r6_Uri.toString().contains("/audio/")) {
                    r1_JSONObject.put(QsbkDatabase.TYPE, AUDIO_3GPP);
                    r1_JSONObject.put("lastModifiedDate", r0_File.lastModified());
                    r1_JSONObject.put("size", r0_File.length());
                    return r1_JSONObject;
                } else {
                    r1_JSONObject.put(QsbkDatabase.TYPE, VIDEO_3GPP);
                    r1_JSONObject.put("lastModifiedDate", r0_File.lastModified());
                    r1_JSONObject.put("size", r0_File.length());
                    return r1_JSONObject;
                }
            } else {
                r1_JSONObject.put(QsbkDatabase.TYPE, FileUtils.getMimeType(r0_File.getAbsolutePath()));
                r1_JSONObject.put("lastModifiedDate", r0_File.lastModified());
                r1_JSONObject.put("size", r0_File.length());
                return r1_JSONObject;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getAudioVideoData(String r4_String, JSONObject r5_JSONObject, boolean r6z) throws JSONException {
        MediaPlayer r0_MediaPlayer = new MediaPlayer();
        try {
            r0_MediaPlayer.setDataSource(r4_String);
            r0_MediaPlayer.prepare();
            r5_JSONObject.put("duration", r0_MediaPlayer.getDuration() / 1000);
            if (r6z) {
                r5_JSONObject.put("height", r0_MediaPlayer.getVideoHeight());
                r5_JSONObject.put("width", r0_MediaPlayer.getVideoWidth());
            }
        } catch (IOException e) {
            Log.d(LOG_TAG, "Error: loading video file");
        }
        return r5_JSONObject;
    }

    private JSONObject getFormatData(String r6_String, String r7_String) throws JSONException {
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put("height", CAPTURE_INTERNAL_ERR);
        r0_JSONObject.put("width", CAPTURE_INTERNAL_ERR);
        r0_JSONObject.put("bitrate", CAPTURE_INTERNAL_ERR);
        r0_JSONObject.put("duration", CAPTURE_INTERNAL_ERR);
        r0_JSONObject.put("codecs", RContactStorage.PRIMARY_KEY);
        if (r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY) || "null".equals(r7_String)) {
            r7_String = FileUtils.getMimeType(r6_String);
            Log.d(LOG_TAG, "Mime type = " + r7_String);
            if (r7_String.equals(IMAGE_JPEG) || r6_String.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                return getImageData(r6_String, r0_JSONObject);
            }
            if (r7_String.endsWith(AUDIO_3GPP)) {
                return getAudioVideoData(r6_String, r0_JSONObject, false);
            }
            if (r7_String.equals(VIDEO_3GPP) || r7_String.equals(VIDEO_MP4)) {
                return getAudioVideoData(r6_String, r0_JSONObject, true);
            }
            return r0_JSONObject;
        } else {
            Log.d(LOG_TAG, "Mime type = " + r7_String);
            if (r7_String.equals(IMAGE_JPEG) || r6_String.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                return getImageData(r6_String, r0_JSONObject);
            }
            if (r7_String.endsWith(AUDIO_3GPP)) {
                return getAudioVideoData(r6_String, r0_JSONObject, false);
            }
            if (r7_String.equals(VIDEO_3GPP) || r7_String.equals(VIDEO_MP4)) {
                return getAudioVideoData(r6_String, r0_JSONObject, true);
            }
            return r0_JSONObject;
        }
    }

    private JSONObject getImageData(String r4_String, JSONObject r5_JSONObject) throws JSONException {
        Options r0_Options = new Options();
        r0_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(FileUtils.stripFileProtocol(r4_String), r0_Options);
        r5_JSONObject.put("height", r0_Options.outHeight);
        r5_JSONObject.put("width", r0_Options.outWidth);
        return r5_JSONObject;
    }

    private Cursor queryImgDB(Uri r7_Uri) {
        ContentResolver r0_ContentResolver = this.cordova.getActivity().getContentResolver();
        String[] r2_StringA = new String[1];
        r2_StringA[0] = "_id";
        return r0_ContentResolver.query(r7_Uri, r2_StringA, null, null, null);
    }

    private Uri whichContentStore() {
        return Environment.getExternalStorageState().equals("mounted") ? Media.EXTERNAL_CONTENT_URI : Media.INTERNAL_CONTENT_URI;
    }

    public boolean execute(String r10_String, JSONArray r11_JSONArray, CallbackContext r12_CallbackContext) throws JSONException {
        int r1i = CAPTURE_INTERNAL_ERR;
        this.callbackContext = r12_CallbackContext;
        this.limit = 1;
        this.duration = 0.0d;
        this.results = new JSONArray();
        JSONObject r2_JSONObject = r11_JSONArray.optJSONObject(CAPTURE_INTERNAL_ERR);
        if (r2_JSONObject != null) {
            this.limit = r2_JSONObject.optLong("limit", 1);
            this.duration = r2_JSONObject.optDouble("duration", 0.0d);
        }
        if (r10_String.equals("getFormatData")) {
            r12_CallbackContext.success(getFormatData(r11_JSONArray.getString(r1i), r11_JSONArray.getString(CAPTURE_IMAGE)));
            return true;
        } else if (r10_String.equals("captureAudio")) {
            captureAudio();
            return true;
        } else if (r10_String.equals("captureImage")) {
            captureImage();
            return true;
        } else {
            if (!r10_String.equals("captureVideo")) {
                return false;
            }
            captureVideo(this.duration);
            return true;
        }
    }

    public void fail(JSONObject r2_JSONObject) {
        this.callbackContext.error(r2_JSONObject);
    }

    public void onActivityResult(int r9i, int r10i, Intent r11_Intent) {
        ContentValues r0_ContentValues;
        Uri r0_Uri;
        if (r10i == -1) {
            if (r9i == 0) {
                this.results.put(createMediaFile(r11_Intent.getData()));
                if (((long) this.results.length()) >= this.limit) {
                    this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
                } else {
                    captureAudio();
                }
            } else if (r9i == 1) {
                try {
                    r0_ContentValues = new ContentValues();
                    r0_ContentValues.put("mime_type", IMAGE_JPEG);
                    r0_Uri = this.cordova.getActivity().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, r0_ContentValues);
                    FileInputStream r1_FileInputStream = new FileInputStream(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()) + "/Capture.jpg");
                    OutputStream r2_OutputStream = this.cordova.getActivity().getContentResolver().openOutputStream(r0_Uri);
                    byte[] r3_byteA = new byte[4096];
                    while (true) {
                        int r4i = r1_FileInputStream.read(r3_byteA);
                        if (r4i != -1) {
                            r2_OutputStream.write(r3_byteA, CAPTURE_INTERNAL_ERR, r4i);
                        } else {
                            r2_OutputStream.flush();
                            r2_OutputStream.close();
                            r1_FileInputStream.close();
                            this.results.put(createMediaFile(r0_Uri));
                            checkForDuplicateImage();
                            if (((long) this.results.length()) >= this.limit) {
                                this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
                                return;
                            } else {
                                captureImage();
                                return;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    fail(createErrorObject(CAPTURE_INTERNAL_ERR, "Error capturing image."));
                }
            } else {
                if (r9i == 2) {
                    this.results.put(createMediaFile(r11_Intent.getData()));
                    if (((long) this.results.length()) >= this.limit) {
                        this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
                    } else {
                        captureVideo(this.duration);
                    }
                }
            }
        } else if (r10i == 0) {
            if (this.results.length() > 0) {
                this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
            } else {
                fail(createErrorObject(CAPTURE_NO_MEDIA_FILES, "Canceled."));
            }
        } else if (this.results.length() > 0) {
            this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
        } else {
            fail(createErrorObject(CAPTURE_NO_MEDIA_FILES, "Did not complete!"));
        }
    }
}
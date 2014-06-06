package org.apache.cordova;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.LOG;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;

public class CameraLauncher extends CordovaPlugin implements MediaScannerConnectionClient {
    private static final int ALLMEDIA = 2;
    private static final int CAMERA = 1;
    private static final int DATA_URL = 0;
    private static final int FILE_URI = 1;
    private static final String GET_All = "Get All";
    private static final String GET_PICTURE = "Get Picture";
    private static final String GET_VIDEO = "Get Video";
    private static final int JPEG = 0;
    private static final String LOG_TAG = "CameraLauncher";
    private static final int NATIVE_URI = 2;
    private static final int PHOTOLIBRARY = 0;
    private static final int PICTURE = 0;
    private static final int PNG = 1;
    private static final int SAVEDPHOTOALBUM = 2;
    private static final int VIDEO = 1;
    public CallbackContext callbackContext;
    private MediaScannerConnection conn;
    private boolean correctOrientation;
    private int encodingType;
    private Uri imageUri;
    private int mQuality;
    private int mediaType;
    private int numPics;
    private boolean saveToPhotoAlbum;
    private Uri scanMe;
    private int targetHeight;
    private int targetWidth;

    public static int calculateSampleSize(int r3i, int r4i, int r5i, int r6i) {
        return ((((float) r3i) / ((float) r4i)) > (((float) r5i) / ((float) r6i)) ? 1 : ((((float) r3i) / ((float) r4i)) == (((float) r5i) / ((float) r6i))? 0 : -1)) > 0 ? r3i / r5i : r4i / r6i;
    }

    private void checkForDuplicateImage(int r8i) {
        int r0i = VIDEO;
        Uri r3_Uri = whichContentStore();
        Cursor r2_Cursor = queryImgDB(r3_Uri);
        int r4i = r2_Cursor.getCount();
        int r2i;
        if (r8i == 1 && this.saveToPhotoAlbum) {
            r0i = 2;
            if (r4i - this.numPics != r0i) {
            } else {
                r2_Cursor.moveToLast();
                r2i = Integer.valueOf(r2_Cursor.getString(r2_Cursor.getColumnIndex("_id"))).intValue();
                this.cordova.getActivity().getContentResolver().delete(Uri.parse(r3_Uri + "/" + (r0i != 2 ? r2i : r2i - 1)), null, null);
            }
        } else {
            if (r4i - this.numPics != r0i) {
                r2_Cursor.moveToLast();
                r2i = Integer.valueOf(r2_Cursor.getString(r2_Cursor.getColumnIndex("_id"))).intValue();
                if (r0i != 2) {
                }
                this.cordova.getActivity().getContentResolver().delete(Uri.parse(r3_Uri + "/" + (r0i != 2 ? r2i : r2i - 1)), null, null);
            }
        }
    }

    private void cleanup(int r3i, Uri r4_Uri, Uri r5_Uri, Bitmap r6_Bitmap) {
        if (r6_Bitmap != null) {
            r6_Bitmap.recycle();
        }
        new File(FileUtils.stripFileProtocol(r4_Uri.toString())).delete();
        checkForDuplicateImage(r3i);
        if ((!this.saveToPhotoAlbum) || r5_Uri == null) {
            System.gc();
        } else {
            scanForGallery(r5_Uri);
            System.gc();
        }
    }

    private File createCaptureFile(int r4i) {
        if (r4i == 0) {
            return new File(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()), ".Pic.jpg");
        }
        if (r4i == 1) {
            return new File(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()), ".Pic.png");
        }
        throw new IllegalArgumentException("Invalid Encoding Type: " + r4i);
    }

    private Bitmap getRotatedBitmap(int r8i, Bitmap r9_Bitmap, ExifHelper r10_ExifHelper) {
        Matrix r5_Matrix = new Matrix();
        if (r8i == 180) {
            r5_Matrix.setRotate((float) r8i);
        } else {
            r5_Matrix.setRotate((float) r8i, ((float) r9_Bitmap.getWidth()) / 2.0f, ((float) r9_Bitmap.getHeight()) / 2.0f);
        }
        Bitmap r0_Bitmap = Bitmap.createBitmap(r9_Bitmap, PICTURE, 0, r9_Bitmap.getWidth(), r9_Bitmap.getHeight(), r5_Matrix, true);
        r10_ExifHelper.resetOrientation();
        return r0_Bitmap;
    }

    private Bitmap getScaledBitmap(String r10_String) {
        if (this.targetWidth <= 0 && this.targetHeight <= 0) {
            return BitmapFactory.decodeFile(r10_String);
        }
        Options r1_Options = new Options();
        r1_Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(r10_String, r1_Options);
        if (r1_Options.outWidth == 0 || r1_Options.outHeight == 0) {
            return null;
        }
        int[] r2_intA = calculateAspectRatio(r1_Options.outWidth, r1_Options.outHeight);
        r1_Options.inJustDecodeBounds = false;
        r1_Options.inSampleSize = calculateSampleSize(r1_Options.outWidth, r1_Options.outHeight, this.targetWidth, this.targetHeight);
        Bitmap r1_Bitmap = BitmapFactory.decodeFile(r10_String, r1_Options);
        return r1_Bitmap != null ? Bitmap.createScaledBitmap(r1_Bitmap, r2_intA[0], r2_intA[1], true) : null;
    }

    private Uri getUriFromMediaStore() {
        ContentValues r0_ContentValues = new ContentValues();
        r0_ContentValues.put("mime_type", "image/jpeg");
        try {
            return this.cordova.getActivity().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, r0_ContentValues);
        } catch (UnsupportedOperationException e) {
            LOG.d(LOG_TAG, "Can't write to external media storage.");
            return this.cordova.getActivity().getContentResolver().insert(Media.INTERNAL_CONTENT_URI, r0_ContentValues);
        }
    }

    private Cursor queryImgDB(Uri r7_Uri) {
        ContentResolver r0_ContentResolver = this.cordova.getActivity().getContentResolver();
        String[] r2_StringA = new String[1];
        r2_StringA[0] = "_id";
        return r0_ContentResolver.query(r7_Uri, r2_StringA, null, null, null);
    }

    private void scanForGallery(Uri r3_Uri) {
        this.scanMe = r3_Uri;
        if (this.conn != null) {
            this.conn.disconnect();
        }
        this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
        this.conn.connect();
    }

    private Uri whichContentStore() {
        return Environment.getExternalStorageState().equals("mounted") ? Media.EXTERNAL_CONTENT_URI : Media.INTERNAL_CONTENT_URI;
    }

    private void writeUncompressedImage(Uri r6_Uri) throws FileNotFoundException, IOException {
        FileInputStream r0_FileInputStream = new FileInputStream(FileUtils.stripFileProtocol(this.imageUri.toString()));
        OutputStream r1_OutputStream = this.cordova.getActivity().getContentResolver().openOutputStream(r6_Uri);
        byte[] r2_byteA = new byte[4096];
        while (true) {
            int r3i = r0_FileInputStream.read(r2_byteA);
            if (r3i != -1) {
                r1_OutputStream.write(r2_byteA, PICTURE, r3i);
            } else {
                r1_OutputStream.flush();
                r1_OutputStream.close();
                r0_FileInputStream.close();
                return;
            }
        }
    }

    public int[] calculateAspectRatio(int r9i, int r10i) {
        int r1i = this.targetWidth;
        int r0i = this.targetHeight;
        int[] r0_intA;
        if (r1i > 0 || r0i > 0) {
            if (r1i <= 0 || r0i > 0) {
                if (r1i > 0 || r0i <= 0) {
                    double r2d = ((double) r1i) / ((double) r0i);
                    double r4d = ((double) r9i) / ((double) r10i);
                    if (r4d > r2d) {
                        r10i = (r1i * r10i) / r9i;
                        r9i = r1i;
                    } else if (r4d < r2d) {
                        r9i = (r0i * r9i) / r10i;
                        r10i = r0i;
                    } else {
                        r10i = r0i;
                        r9i = r1i;
                    }
                } else {
                    r9i = (r0i * r9i) / r10i;
                    r10i = r0i;
                }
            } else {
                r10i = (r1i * r10i) / r9i;
                r9i = r1i;
            }
            r0_intA = new int[2];
            r0_intA[0] = r9i;
            r0_intA[1] = r10i;
            return r0_intA;
        } else {
            r0_intA = new int[2];
            r0_intA[0] = r9i;
            r0_intA[1] = r10i;
            return r0_intA;
        }
    }

    public boolean execute(String r7_String, JSONArray r8_JSONArray, CallbackContext r9_CallbackContext) throws JSONException {
        boolean r1z = false;
        this.callbackContext = r9_CallbackContext;
        if (!r7_String.equals("takePicture")) {
            return false;
        }
        this.saveToPhotoAlbum = r1z;
        this.targetHeight = r1z;
        this.targetWidth = r1z;
        this.encodingType = r1z;
        this.mediaType = r1z;
        this.mQuality = 80;
        this.mQuality = r8_JSONArray.getInt(r1z);
        int r1i = r8_JSONArray.getInt(VIDEO);
        int r2i = r8_JSONArray.getInt(SAVEDPHOTOALBUM);
        this.targetWidth = r8_JSONArray.getInt(XListViewFooter.STATE_NOMORE);
        this.targetHeight = r8_JSONArray.getInt(XListViewFooter.STATE_NODATA);
        this.encodingType = r8_JSONArray.getInt(ShareUtils.SHARE_SMS);
        this.mediaType = r8_JSONArray.getInt(ShareUtils.SHARE_COPY);
        this.correctOrientation = r8_JSONArray.getBoolean(Base64.DONT_BREAK_LINES);
        this.saveToPhotoAlbum = r8_JSONArray.getBoolean(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        if (this.targetWidth < 1) {
            this.targetWidth = -1;
        }
        if (this.targetHeight < 1) {
            this.targetHeight = -1;
        }
        if (r2i == 1) {
            takePicture(r1i, this.encodingType);
        } else if (r2i == 0 || r2i == 2) {
            getImage(r2i, r1i);
        }
        PluginResult r1_PluginResult = new PluginResult(Status.NO_RESULT);
        r1_PluginResult.setKeepCallback(true);
        r9_CallbackContext.sendPluginResult(r1_PluginResult);
        return true;
    }

    public void failPicture(String r2_String) {
        this.callbackContext.error(r2_String);
    }

    public void getImage(int r5i, int r6i) {
        Intent r1_Intent = new Intent();
        String r0_String = GET_PICTURE;
        if (this.mediaType == 0) {
            r1_Intent.setType("image/*");
        } else if (this.mediaType == 1) {
            r1_Intent.setType("video/*");
            r0_String = GET_VIDEO;
        } else if (this.mediaType == 2) {
            r1_Intent.setType("*/*");
            r0_String = GET_All;
        }
        r1_Intent.setAction("android.intent.action.GET_CONTENT");
        r1_Intent.addCategory("android.intent.category.OPENABLE");
        if (this.cordova != null) {
            this.cordova.startActivityForResult(this, Intent.createChooser(r1_Intent, new String(r0_String)), (r5i + 1) * 16 + r6i + 1);
        }
    }

    public void onActivityResult(int r12i, int r13i, Intent r14_Intent) {
        int r0i = r12i / 16 - 1;
        int r8i = r12i % 16 - 1;
        int r6i = PICTURE;
        ExifHelper r3_ExifHelper;
        Bitmap r1_Bitmap;
        if (r0i == VIDEO) {
            if (r13i == -1) {
                Uri r0_Uri;
                r3_ExifHelper = new ExifHelper();
                try {
                    if (this.encodingType == 0) {
                        r3_ExifHelper.createInFile(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()) + "/.Pic.jpg");
                        r3_ExifHelper.readExifData();
                        r0i = r3_ExifHelper.getOrientation();
                    } else {
                        r0i = 0;
                    }
                    r6i = r0i;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri r1_Uri = null;
                if (r8i == 0) {
                    Bitmap r0_Bitmap = getScaledBitmap(FileUtils.stripFileProtocol(this.imageUri.toString()));
                    if (r0_Bitmap == null) {
                        r0_Bitmap = (Bitmap) r14_Intent.getExtras().get("data");
                    }
                    if (r0_Bitmap == null) {
                        Log.d(LOG_TAG, "I either have a null image path or bitmap");
                        failPicture("Unable to create bitmap!");
                    } else {
                        if (r6i != 0) {
                            if (this.correctOrientation) {
                                r0_Bitmap = getRotatedBitmap(r6i, r0_Bitmap, r3_ExifHelper);
                            }
                        }
                        processPicture(r0_Bitmap);
                        checkForDuplicateImage(PICTURE);
                        r1_Bitmap = r0_Bitmap;
                        r0_Uri = r1_Uri;
                    }
                } else if (r8i == 1 || r8i == 2) {
                    r0_Uri = this.saveToPhotoAlbum ? getUriFromMediaStore() : Uri.fromFile(new File(DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()), System.currentTimeMillis() + Util.PHOTO_DEFAULT_EXT));
                    if (r0_Uri == null) {
                        failPicture("Error capturing image - no media storage found.");
                    }
                    if (this.targetHeight == -1 && this.targetWidth == -1 && this.mQuality == 100 && !(this.correctOrientation)) {
                        writeUncompressedImage(r0_Uri);
                        this.callbackContext.success(r0_Uri.toString());
                        r1_Bitmap = null;
                    } else {
                        r1_Bitmap = getScaledBitmap(FileUtils.stripFileProtocol(this.imageUri.toString()));
                        OutputStream r2_OutputStream;
                        if (r6i == 0 || (!this.correctOrientation)) {
                            r2_OutputStream = this.cordova.getActivity().getContentResolver().openOutputStream(r0_Uri);
                            r1_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r2_OutputStream);
                            r2_OutputStream.close();
                            if (this.encodingType != 0) {
                                r3_ExifHelper.createOutFile(this.saveToPhotoAlbum ? r0_Uri.getPath() : FileUtils.getRealPathFromURI(r0_Uri, this.cordova));
                                r3_ExifHelper.writeExifData();
                            }
                        } else {
                            r1_Bitmap = getRotatedBitmap(r6i, r1_Bitmap, r3_ExifHelper);
                            r2_OutputStream = this.cordova.getActivity().getContentResolver().openOutputStream(r0_Uri);
                            r1_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r2_OutputStream);
                            r2_OutputStream.close();
                            if (this.encodingType != 0) {
                                this.callbackContext.success(r0_Uri.toString());
                            } else {
                                if (this.saveToPhotoAlbum) {
                                }
                                r3_ExifHelper.createOutFile(this.saveToPhotoAlbum ? r0_Uri.getPath() : FileUtils.getRealPathFromURI(r0_Uri, this.cordova));
                                r3_ExifHelper.writeExifData();
                            }
                        }
                    }
                    this.callbackContext.success(r0_Uri.toString());
                } else {
                    r0_Uri = r1_Uri;
                    r1_Bitmap = null;
                }
                cleanup(VIDEO, this.imageUri, r0_Uri, r1_Bitmap);
            } else if (r13i == 0) {
                failPicture("Camera cancelled.");
            } else {
                failPicture("Did not complete!");
            }
        } else if (r0i == 0 || r0i == 2) {
            if (r13i == -1) {
                Uri r9_Uri = r14_Intent.getData();
                if (this.mediaType != 0) {
                    this.callbackContext.success(r9_Uri.toString());
                } else if (this.targetHeight == -1 && this.targetWidth == -1) {
                    if ((r8i == 1 || r8i == 2) && (!this.correctOrientation)) {
                        this.callbackContext.success(r9_Uri.toString());
                    } else {
                        r0_String = FileUtils.getRealPathFromURI(r9_Uri, this.cordova);
                        r1_String = FileUtils.getMimeType(r0_String);
                        if (r0_String == null || r1_String == null) {
                            Log.d(LOG_TAG, "I either have a null image path or bitmap");
                            failPicture("Unable to retrieve path to picture!");
                        } else if (r1_String.equalsIgnoreCase("image/jpeg") || r1_String.equalsIgnoreCase("image/png")) {
                            r7_Bitmap = getScaledBitmap(r0_String);
                            if (r7_Bitmap != null) {
                                if (this.correctOrientation) {
                                    r2_StringA = new String[1];
                                    r2_StringA[0] = "orientation";
                                    r0_Cursor = this.cordova.getActivity().getContentResolver().query(r14_Intent.getData(), r2_StringA, null, null, null);
                                    if (r0_Cursor == null) {
                                        if (r6i != 0) {
                                            r1_Bitmap = r7_Bitmap;
                                        } else {
                                            r5_Matrix = new Matrix();
                                            r5_Matrix.setRotate((float) r6i);
                                            r1_Bitmap = Bitmap.createBitmap(r7_Bitmap, PICTURE, PICTURE, r7_Bitmap.getWidth(), r7_Bitmap.getHeight(), r5_Matrix, true);
                                        }
                                    } else {
                                        r0_Cursor.moveToPosition(PICTURE);
                                        r6i = r0_Cursor.getInt(PICTURE);
                                        r0_Cursor.close();
                                        if (r6i != 0) {
                                            r5_Matrix = new Matrix();
                                            r5_Matrix.setRotate((float) r6i);
                                            r1_Bitmap = Bitmap.createBitmap(r7_Bitmap, PICTURE, PICTURE, r7_Bitmap.getWidth(), r7_Bitmap.getHeight(), r5_Matrix, true);
                                        }
                                        r1_Bitmap = r7_Bitmap;
                                    }
                                } else {
                                    r1_Bitmap = r7_Bitmap;
                                }
                                if (r8i != 0) {
                                    if (r8i == 1 || r8i == 2) {
                                        if (this.targetHeight <= 0 || this.targetWidth <= 0) {
                                            this.callbackContext.success(r9_Uri.toString());
                                        } else {
                                            r2_String = DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()) + "/resize.jpg";
                                            r3_ExifHelper = new ExifHelper();
                                            if (this.encodingType != 0) {
                                                r0_OutputStream = new FileOutputStream(r2_String);
                                                r1_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r0_OutputStream);
                                                r0_OutputStream.close();
                                                if (this.encodingType == 0) {
                                                    this.callbackContext.success("file://" + r2_String + "?" + System.currentTimeMillis());
                                                } else {
                                                    r3_ExifHelper.createOutFile(FileUtils.getRealPathFromURI(r9_Uri, this.cordova));
                                                    r3_ExifHelper.writeExifData();
                                                    this.callbackContext.success("file://" + r2_String + "?" + System.currentTimeMillis());
                                                }
                                            } else {
                                                r3_ExifHelper.createInFile(r2_String);
                                                r3_ExifHelper.readExifData();
                                                r3_ExifHelper.getOrientation();
                                                r0_OutputStream = new FileOutputStream(r2_String);
                                                r1_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r0_OutputStream);
                                                r0_OutputStream.close();
                                                if (this.encodingType == 0) {
                                                    r3_ExifHelper.createOutFile(FileUtils.getRealPathFromURI(r9_Uri, this.cordova));
                                                    r3_ExifHelper.writeExifData();
                                                }
                                                this.callbackContext.success("file://" + r2_String + "?" + System.currentTimeMillis());
                                            }
                                        }
                                    }
                                } else {
                                    processPicture(r1_Bitmap);
                                }
                                if (r1_Bitmap == null) {
                                    System.gc();
                                } else {
                                    r1_Bitmap.recycle();
                                    System.gc();
                                }
                            } else {
                                Log.d(LOG_TAG, "I either have a null image path or bitmap");
                                failPicture("Unable to create bitmap!");
                            }
                        } else {
                            Log.d(LOG_TAG, "I either have a null image path or bitmap");
                            failPicture("Unable to retrieve path to picture!");
                        }
                    }
                } else {
                    r0_String = FileUtils.getRealPathFromURI(r9_Uri, this.cordova);
                    r1_String = FileUtils.getMimeType(r0_String);
                    if (r0_String == null || r1_String == null) {
                        Log.d(LOG_TAG, "I either have a null image path or bitmap");
                        failPicture("Unable to retrieve path to picture!");
                    } else if (r1_String.equalsIgnoreCase("image/jpeg") || r1_String.equalsIgnoreCase("image/png")) {
                        r7_Bitmap = getScaledBitmap(r0_String);
                        if (r7_Bitmap != null) {
                            Log.d(LOG_TAG, "I either have a null image path or bitmap");
                            failPicture("Unable to create bitmap!");
                        } else {
                            if (this.correctOrientation) {
                                r2_StringA = new String[1];
                                r2_StringA[0] = "orientation";
                                r0_Cursor = this.cordova.getActivity().getContentResolver().query(r14_Intent.getData(), r2_StringA, null, null, null);
                                if (r0_Cursor == null) {
                                    r0_Cursor.moveToPosition(PICTURE);
                                    r6i = r0_Cursor.getInt(PICTURE);
                                    r0_Cursor.close();
                                }
                                if (r6i != 0) {
                                    r5_Matrix = new Matrix();
                                    r5_Matrix.setRotate((float) r6i);
                                    r1_Bitmap = Bitmap.createBitmap(r7_Bitmap, PICTURE, PICTURE, r7_Bitmap.getWidth(), r7_Bitmap.getHeight(), r5_Matrix, true);
                                }
                                r1_Bitmap = r7_Bitmap;
                            } else {
                                r1_Bitmap = r7_Bitmap;
                            }
                            if (r8i != 0) {
                                processPicture(r1_Bitmap);
                            } else if (r8i == 1 || r8i == 2) {
                                if (this.targetHeight <= 0 || this.targetWidth <= 0) {
                                    this.callbackContext.success(r9_Uri.toString());
                                } else {
                                    try {
                                        r2_String = DirectoryManager.getTempDirectoryPath(this.cordova.getActivity()) + "/resize.jpg";
                                        r3_ExifHelper = new ExifHelper();
                                        try {
                                            if (this.encodingType != 0) {
                                                r3_ExifHelper.createInFile(r2_String);
                                                r3_ExifHelper.readExifData();
                                                r3_ExifHelper.getOrientation();
                                            }
                                        } catch (IOException e_2) {
                                            e_2.printStackTrace();
                                        }
                                        r0_OutputStream = new FileOutputStream(r2_String);
                                        r1_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r0_OutputStream);
                                        r0_OutputStream.close();
                                        if (this.encodingType == 0) {
                                            r3_ExifHelper.createOutFile(FileUtils.getRealPathFromURI(r9_Uri, this.cordova));
                                            r3_ExifHelper.writeExifData();
                                        }
                                        this.callbackContext.success("file://" + r2_String + "?" + System.currentTimeMillis());
                                    } catch (Exception e_3) {
                                        e_3.printStackTrace();
                                        failPicture("Error retrieving image.");
                                    }
                                }
                            }
                            if (r1_Bitmap == null) {
                                r1_Bitmap.recycle();
                            }
                            System.gc();
                        }
                    } else {
                        Log.d(LOG_TAG, "I either have a null image path or bitmap");
                        failPicture("Unable to retrieve path to picture!");
                    }
                }
            } else if (r13i == 0) {
                failPicture("Selection cancelled.");
            } else {
                failPicture("Selection did not complete!");
            }
        }
    }

    public void onMediaScannerConnected() {
        try {
            this.conn.scanFile(this.scanMe.toString(), "image/*");
        } catch (IllegalStateException e) {
            LOG.e(LOG_TAG, "Can't scan file in MediaScanner after taking picture");
        }
    }

    public void onScanCompleted(String r2_String, Uri r3_Uri) {
        this.conn.disconnect();
    }

    public void processPicture(Bitmap r4_Bitmap) {
        OutputStream r0_OutputStream = new ByteArrayOutputStream();
        try {
            if (r4_Bitmap.compress(CompressFormat.JPEG, this.mQuality, r0_OutputStream)) {
                this.callbackContext.success(new String(org.apache.commons.codec.binary.Base64.encodeBase64(r0_OutputStream.toByteArray())));
            }
        } catch (Exception e) {
            failPicture("Error compressing image.");
        }
    }

    public void takePicture(int r5i, int r6i) {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent r0_Intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File r1_File = createCaptureFile(r6i);
        r0_Intent.putExtra("output", Uri.fromFile(r1_File));
        this.imageUri = Uri.fromFile(r1_File);
        if (this.cordova != null) {
            this.cordova.startActivityForResult(this, r0_Intent, r5i + 32 + 1);
        }
    }
}
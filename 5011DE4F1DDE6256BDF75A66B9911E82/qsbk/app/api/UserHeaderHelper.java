package qsbk.app.api;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Pair;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.cordova.Globalization;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;

public class UserHeaderHelper {
    public static final int CAMERA_WITH_DATA = 0;
    public static final int PHOTO_PICKED_WITH_DATA = 1;
    private static final File b;
    private Activity a;
    private Bitmap c;
    public File mCapturedFile;

    public static class UploadAvatarTask extends AsyncTask<String, Void, Pair<Integer, String>> {
        public static final int UPLOAD_FAIL = -1;
        public static final int UPLOAD_OK = 0;
        private String a;
        private String b;

        public UploadAvatarTask(String r2_String) {
            this.a = null;
            this.b = r2_String;
        }

        protected Pair<Integer, String> a(String ... r7_StringA) {
            InputStream r0_InputStream;
            try {
                r0_InputStream = new FileInputStream(new File(this.b));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                r0_InputStream = null;
            }
            try {
                JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().submit(Constants.UPDATE_AVATAR, new HashMap(), r0_InputStream));
                if (r1_JSONObject.getInt("err") != 0) {
                    return new Pair(Integer.valueOf(UPLOAD_FAIL), r1_JSONObject.getString("err_msg"));
                }
                QsbkApp.currentUser.userIcon = r1_JSONObject.getString(QsbkDatabase.ICON);
                UserInfo r0_UserInfo = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
                r0_UserInfo.userIcon = r1_JSONObject.getString(QsbkDatabase.ICON);
                SharePreferenceUtils.setSharePreferencesValue("loginUser", r0_UserInfo.toString());
                return new Pair(Integer.valueOf(CAMERA_WITH_DATA), "\u5934\u50cf\u4e0a\u4f20\u6210\u529f");
            } catch (Exception e_2) {
                return new Pair(Integer.valueOf(UPLOAD_FAIL), e_2.getMessage());
            }
        }
    }

    static {
        b = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    }

    public UserHeaderHelper(Activity r2_Activity) {
        this.c = null;
        this.a = r2_Activity;
    }

    private String a() {
        Date r0_Date = new java.sql.Date(System.currentTimeMillis());
        return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(r0_Date) + Util.PHOTO_DEFAULT_EXT;
    }

    public static Intent getCropImageIntent(Uri r5_Uri) {
        Intent r0_Intent = new Intent("com.android.camera.action.CROP");
        r0_Intent.setDataAndType(r5_Uri, "image/*");
        r0_Intent.putExtra("crop", "true");
        r0_Intent.putExtra("aspectX", PHOTO_PICKED_WITH_DATA);
        r0_Intent.putExtra("aspectY", PHOTO_PICKED_WITH_DATA);
        r0_Intent.putExtra("outputX", 200);
        r0_Intent.putExtra("outputY", 200);
        r0_Intent.putExtra("return-data", true);
        return r0_Intent;
    }

    public static String getIconUrl(BaseUserInfo r4_BaseUserInfo) {
        String r0_String = Constants.ARATAR_URL;
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(r4_BaseUserInfo.userId).intValue() / 10000);
        r1_ObjectA[1] = r4_BaseUserInfo.userId;
        r1_ObjectA[2] = Globalization.MEDIUM;
        r1_ObjectA[3] = r4_BaseUserInfo.userIcon;
        return String.format(r0_String, r1_ObjectA);
    }

    public static Intent getPhotoPickIntent() {
        Intent r0_Intent = new Intent("android.intent.action.GET_CONTENT", null);
        r0_Intent.setType("image/*");
        r0_Intent.putExtra("crop", "true");
        r0_Intent.putExtra("aspectX", PHOTO_PICKED_WITH_DATA);
        r0_Intent.putExtra("aspectY", PHOTO_PICKED_WITH_DATA);
        r0_Intent.putExtra("outputX", 200);
        r0_Intent.putExtra("outputY", 200);
        r0_Intent.putExtra("return-data", true);
        return r0_Intent;
    }

    public void doCropPhoto(File r5_File) {
        try {
            this.a.startActivityForResult(getCropImageIntent(Uri.fromFile(r5_File)), PHOTO_PICKED_WITH_DATA);
        } catch (Exception e) {
            ToastAndDialog.makeText(this.a, "\u62cd\u7167\u51fa\u9519\u4e86", Integer.valueOf(PHOTO_PICKED_WITH_DATA)).show();
        }
    }

    public void doCropPhotoWithCaptured() {
        doCropPhoto(this.mCapturedFile);
    }

    public void getPicFromCapture() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            b.mkdir();
            this.mCapturedFile = new File(b, a());
            Intent r0_Intent = new Intent("android.media.action.IMAGE_CAPTURE");
            r0_Intent.putExtra("output", Uri.fromFile(this.mCapturedFile));
            this.a.startActivityForResult(r0_Intent, CAMERA_WITH_DATA);
        } else {
            ToastAndDialog.makeText(this.a, "\u6ca1\u6709sd\u5361", Integer.valueOf(PHOTO_PICKED_WITH_DATA));
        }
    }

    public void getPicFromContent() {
        try {
            this.a.startActivityForResult(getPhotoPickIntent(), PHOTO_PICKED_WITH_DATA);
        } catch (Exception e) {
            ToastAndDialog.makeText(this.a, "\u9519\u8bef", Integer.valueOf(PHOTO_PICKED_WITH_DATA)).show();
        }
    }

    public Bitmap getPickedBitmap() {
        return this.c;
    }

    public String savePickedBitmap(Intent r8_Intent) {
        Bundle r0_Bundle = r8_Intent.getExtras();
        if (r0_Bundle == null || r0_Bundle.get("data") == null) {
            ToastUtil.Short("\u9009\u62e9\u7684\u56fe\u7247\u4e3a\u7a7a");
            return null;
        } else {
            Bitmap r0_Bitmap = (Bitmap) r0_Bundle.get("data");
            this.c = r0_Bitmap;
            String r1_String = FileUtils.saveDrawable(r0_Bitmap, QsbkApp.currentUser.userId, this.a.getCacheDir() + Constants.IMG_CACHE_PATH_MEDIUM, null, true);
            FileUtils.saveDrawable(r0_Bitmap, QsbkApp.currentUser.userId, this.a.getCacheDir() + Constants.IMG_CACHE_PATH_AVATAR, null, true);
            return r1_String;
        }
    }
}
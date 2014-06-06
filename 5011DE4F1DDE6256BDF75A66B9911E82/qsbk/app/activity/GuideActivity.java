package qsbk.app.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.utils.DeviceUtils;

public class GuideActivity extends StatFragmentActivity {
    public static final String KEY_DISMISS_DELAY_IN_SECONDS = "dismiss_delay";
    private static final String n;

    static {
        n = GuideActivity.class.getSimpleName();
    }

    private boolean c() {
        boolean r0z;
        boolean r1z = false;
        Properties r0_Properties = new Properties();
        try {
            r0_Properties.load(getAssets().open("all_cfg"));
            r0z = "1".equalsIgnoreCase(r0_Properties.getProperty("showGuide"));
        } catch (IOException e) {
            e.printStackTrace();
            r0z = false;
        }
        SharedPreferences r2_SharedPreferences = getSharedPreferences(n, 0);
        String r3_String = r2_SharedPreferences.getString("shown", RContactStorage.PRIMARY_KEY);
        int r4i = r2_SharedPreferences.getInt("version", 0);
        if (r0z) {
            if (((!"shown".equalsIgnoreCase(r3_String)) || r4i < Constants.localVersion) && d()) {
                r1z = true;
            }
        }
        if (r1z) {
            Editor r0_Editor = r2_SharedPreferences.edit();
            r0_Editor.putInt("version", Constants.localVersion);
            r0_Editor.putString("shown", "shown");
            r0_Editor.commit();
        }
        return r1z;
    }

    private boolean d() {
        boolean r0z = true;
        String r2_String = DeviceUtils.getSDPath();
        if (TextUtils.isEmpty(r2_String)) {
            return true;
        }
        File r3_File = new File(r2_String + File.separator + getPackageName());
        File r4_File = new File(r3_File, n);
        if (!r3_File.exists()) {
            return false;
        }
        if (!r4_File.exists()) {
            r3_File.mkdirs();
            try {
                r4_File.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Properties r3_Properties = new Properties();
        int r2i;
        try {
            int r2i_2;
            r3_Properties.load(new FileInputStream(r4_File));
            r2_String = r3_Properties.getProperty("version");
            r2i_2 = (r2_String == null || (!TextUtils.isDigitsOnly(r2_String))) ? 0 : Integer.parseInt(r2_String);
            if (r2i_2 < Constants.localVersion) {
                r3_Properties.setProperty("version", Constants.localVersion + RContactStorage.PRIMARY_KEY);
                r3_Properties.store(new FileOutputStream(r4_File), null);
                return r0z;
            } else {
                r0z = false;
                r3_Properties.setProperty("version", Constants.localVersion + RContactStorage.PRIMARY_KEY);
                r3_Properties.store(new FileOutputStream(r4_File), null);
                return r0z;
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
            return true;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        if (c()) {
            setContentView(R.layout.activity_guide);
            OnClickListener r0_OnClickListener = new am(this);
            findViewById(R.id.close).setOnClickListener(r0_OnClickListener);
            findViewById(R.id.known).setOnClickListener(r0_OnClickListener);
        } else {
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        int r0i = getIntent().getIntExtra(KEY_DISMISS_DELAY_IN_SECONDS, -1);
        if (r0i > 0) {
            if (r0i <= 0) {
                new Handler().postDelayed(new an(this), (long) (r0i * 1000));
            }
        } else {
            r0i = -1;
            if (r0i <= 0) {
            } else {
                new Handler().postDelayed(new an(this), (long) (r0i * 1000));
            }
        }
    }
}
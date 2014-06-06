package qsbk.app.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;

public class BrightnessSetting {
    private final Activity a;
    private SeekBar b;
    private CheckBox c;
    private View d;
    private int e;

    class a implements OnCheckedChangeListener {
        a() {
        }

        public void onCheckedChanged(CompoundButton r3_CompoundButton, boolean r4z) {
            int r0i;
            if (r4z) {
                BrightnessSetting.this.b.setEnabled(false);
                r0i = QsbkApp.brightness;
            } else {
                BrightnessSetting.this.b.setEnabled(true);
                r0i = BrightnessSetting.this.e;
            }
            BrightnessSetting.this.b.setProgress(r0i);
            BrightnessSetting.this.a(r0i);
        }
    }

    class b implements OnSeekBarChangeListener {
        b() {
        }

        public void onProgressChanged(SeekBar r3_SeekBar, int r4i, boolean r5z) {
            if (r5z || (!BrightnessSetting.this.c.isChecked())) {
                BrightnessSetting.this.a(r3_SeekBar.getProgress());
            }
        }

        public void onStartTrackingTouch(SeekBar r1_SeekBar) {
        }

        public void onStopTrackingTouch(SeekBar r4_SeekBar) {
            BrightnessSetting.this.cacheScreenBrightness(Math.max(((float) (r4_SeekBar.getProgress() + 1)) / 255.0f, 0.0588235f));
        }
    }

    public BrightnessSetting(Activity r3_Activity) {
        this.a = r3_Activity;
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = UIHelper.isNightTheme() ? SharePreferenceUtils.getSharePreferencesValue("brightness_night") : SharePreferenceUtils.getSharePreferencesValue("brightness");
        if (TextUtils.isEmpty(r0_String)) {
            this.e = QsbkApp.brightness;
        } else {
            this.e = Integer.valueOf(r0_String).intValue();
        }
    }

    public static void CacheSystemScreenBrightness(Context r0_Context) {
        initOrgBrightness(r0_Context);
    }

    private void a() {
        System.putInt(this.a.getContentResolver(), "screen_brightness_mode", 0);
    }

    private void a(int r4i) {
        LayoutParams r1_LayoutParams = this.a.getWindow().getAttributes();
        if (r4i < 15) {
            r4i = 15;
        }
        if (r4i < 0 || r4i > 255) {
            this.a.getWindow().setAttributes(r1_LayoutParams);
        } else {
            r1_LayoutParams.screenBrightness = ((float) r4i) / 255.0f;
            this.a.getWindow().setAttributes(r1_LayoutParams);
        }
    }

    public static void initOrgBrightness(Context r3_Context) {
        try {
            QsbkApp.screenMode = Integer.valueOf(System.getInt(r3_Context.getContentResolver(), "screen_brightness_mode"));
            if (Build.MODEL.startsWith("M0")) {
                QsbkApp.brightness = System.getInt(r3_Context.getContentResolver(), "screen_brightness");
            }
            if (QsbkApp.screenMode.intValue() != 1) {
                QsbkApp.brightness = System.getInt(r3_Context.getContentResolver(), "screen_brightness");
            }
        } catch (SettingNotFoundException e) {
            QsbkApp.screenMode = Integer.valueOf(1);
        }
    }

    public static void setBrightness(Activity r7_Activity) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = UIHelper.isNightTheme() ? SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem_night") : SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem");
        if (r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals("true")) {
            System.putInt(r7_Activity.getContentResolver(), "screen_brightness_mode", QsbkApp.screenMode.intValue());
            LayoutParams r0_LayoutParams = r7_Activity.getWindow().getAttributes();
            if (QsbkApp.brightness < 0 || QsbkApp.brightness > 255) {
                r7_Activity.getWindow().setAttributes(r0_LayoutParams);
            } else {
                r0_LayoutParams.screenBrightness = ((float) QsbkApp.brightness) / 255.0f;
                r7_Activity.getWindow().setAttributes(r0_LayoutParams);
            }
        } else {
            int r0i;
            r0_String = RContactStorage.PRIMARY_KEY;
            r0_String = UIHelper.isNightTheme() ? SharePreferenceUtils.getSharePreferencesValue("brightness_night") : SharePreferenceUtils.getSharePreferencesValue("brightness");
            if (TextUtils.isEmpty(r0_String)) {
                try {
                    r0i = System.getInt(r7_Activity.getContentResolver(), "screen_brightness");
                } catch (SettingNotFoundException e) {
                    r0i = Constants.CommentCount;
                }
            } else {
                r0i = Integer.valueOf(r0_String).intValue();
            }
            try {
                if (System.getInt(r7_Activity.getContentResolver(), "screen_brightness_mode") == 1) {
                    System.putInt(r7_Activity.getContentResolver(), "screen_brightness_mode", 0);
                }
                if (r0i < 15) {
                    r0i = 15;
                }
                LayoutParams r1_LayoutParams = r7_Activity.getWindow().getAttributes();
                if (r0i < 0 || r0i > 255) {
                    r7_Activity.getWindow().setAttributes(r1_LayoutParams);
                } else {
                    r1_LayoutParams.screenBrightness = ((float) r0i) / 255.0f;
                    r7_Activity.getWindow().setAttributes(r1_LayoutParams);
                }
            } catch (SettingNotFoundException e_2) {
                e_2.printStackTrace();
            }
        }
    }

    public void cacheScreenBrightness(float r4f) {
        if (UIHelper.isNightTheme()) {
            this.a.getSharedPreferences(KEYS.SETTING, 0).edit().putFloat("brightness_night", r4f).commit();
        } else {
            this.a.getSharedPreferences(KEYS.SETTING, 0).edit().putFloat("brightness", r4f).commit();
        }
    }

    public final Builder createDialog() {
        Builder r1_Builder = new Builder(this.a).setTitle("\u4eae\u5ea6");
        r1_Builder.setPositiveButton("\u786e\u5b9a", new d(this)).setNegativeButton("\u53d6\u6d88", new c(this));
        if (this.d != null && this.d.getParent() == null) {
            return r1_Builder.setView(this.d);
        }
        this.d = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.brightness_dialog, null);
        this.b = (SeekBar) this.d.findViewById(R.id.brightness_dialog_seek_bar);
        this.b.setOnSeekBarChangeListener(new b());
        this.c = (CheckBox) this.d.findViewById(R.id.brightness_dialog_auto);
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = UIHelper.isNightTheme() ? SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem_night") : SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem");
        View r0_View;
        if (r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals("true")) {
            this.c.setChecked(true);
            this.b.setEnabled(false);
            this.c.setOnCheckedChangeListener(new a());
            r0_View = this.d;
            this.b.setProgress(this.e);
            return r1_Builder.setView(r0_View);
        } else {
            a();
            this.c.setChecked(false);
            this.b.setEnabled(true);
            this.c.setOnCheckedChangeListener(new a());
            r0_View = this.d;
            this.b.setProgress(this.e);
            return r1_Builder.setView(r0_View);
        }
    }
}
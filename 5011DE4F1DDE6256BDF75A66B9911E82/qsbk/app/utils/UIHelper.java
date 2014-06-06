package qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import qsbk.app.QsbkApp;
import qsbk.app.widget.listview.XListViewHeader;

public class UIHelper {
    public static int dip2px(Context r2_Context, float r3f) {
        return (int) (r2_Context.getResources().getDisplayMetrics().density * r3f + 0.5f);
    }

    public static int getColor(int r1i) {
        return QsbkApp.mContext.getResources().getColor(r1i);
    }

    public static ColorStateList getColorStateList(int r1i) {
        return QsbkApp.mContext.getResources().getColorStateList(r1i);
    }

    public static Drawable getDrawable(int r1i) {
        return QsbkApp.mContext.getResources().getDrawable(r1i);
    }

    public static void hideKeyboard(Activity r1_Activity) {
        hideKeyboard(r1_Activity, r1_Activity.getWindow());
    }

    public static void hideKeyboard(Activity r3_Activity, Window r4_Window) {
        View r1_View;
        InputMethodManager r0_InputMethodManager = (InputMethodManager) r3_Activity.getSystemService("input_method");
        r1_View = r4_Window != null ? r4_Window.getDecorView() : r3_Activity.getWindow().getCurrentFocus();
        if (r1_View != null) {
            r0_InputMethodManager.hideSoftInputFromWindow(r1_View.getWindowToken(), 0);
        }
    }

    public static boolean isNightTheme() {
        return !TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("themeid")) && !SharePreferenceUtils.getSharePreferencesValue("themeid").equals("day");
    }

    public static void showKeyboard(Context r3_Context) {
        ((InputMethodManager) r3_Context.getSystemService("input_method")).toggleSoftInput(XListViewHeader.STATE_REFRESHING, 0);
    }
}
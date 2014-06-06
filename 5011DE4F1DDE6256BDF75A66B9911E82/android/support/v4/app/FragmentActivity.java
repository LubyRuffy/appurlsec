package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.util.Constants;
import com.qq.e.v2.constants.Constants.KEYS;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class FragmentActivity extends Activity {
    final Handler a;
    final l b;
    final k c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    SimpleArrayMap<String, w> l;
    w m;

    static class a {
        public static final int[] Fragment;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        static {
            Fragment = new int[]{16842755, 16842960, 16842961};
        }
    }

    static final class b {
        Object a;
        Object b;
        SimpleArrayMap<String, Object> c;
        ArrayList<Fragment> d;
        SimpleArrayMap<String, w> e;

        b() {
        }
    }

    public FragmentActivity() {
        this.a = new i(this);
        this.b = new l();
        this.c = new j(this);
    }

    private static String a(View r7_View) {
        int r1i;
        Resources r2_Resources;
        String r0_String;
        String r3_String;
        String r1_String;
        char r1c = 'F';
        char r2c = '.';
        StringBuilder r4_StringBuilder = new StringBuilder(128);
        r4_StringBuilder.append(r7_View.getClass().getName());
        r4_StringBuilder.append('{');
        r4_StringBuilder.append(Integer.toHexString(System.identityHashCode(r7_View)));
        r4_StringBuilder.append(' ');
        switch (r7_View.getVisibility()) {
            case XListViewHeader.STATE_NORMAL:
                r4_StringBuilder.append('V');
                r4_StringBuilder.append(r7_View.isFocusable() ? '.' : 'F');
                r4_StringBuilder.append(r7_View.isEnabled() ? '.' : 'E');
                r4_StringBuilder.append(r7_View.willNotDraw() ? 'D' : '.');
                r4_StringBuilder.append(r7_View.isHorizontalScrollBarEnabled() ? '.' : 'H');
                r4_StringBuilder.append(r7_View.isVerticalScrollBarEnabled() ? '.' : 'V');
                r4_StringBuilder.append(r7_View.isClickable() ? '.' : 'C');
                r4_StringBuilder.append(r7_View.isLongClickable() ? '.' : 'L');
                r4_StringBuilder.append(' ');
                if (r7_View.isFocused()) {
                    r1c = '.';
                    r4_StringBuilder.append(r1c);
                    r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
                    if (r7_View.isPressed()) {
                        r2c = 'P';
                        r4_StringBuilder.append(r2c);
                        r4_StringBuilder.append(' ');
                        r4_StringBuilder.append(r7_View.getLeft());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getTop());
                        r4_StringBuilder.append('-');
                        r4_StringBuilder.append(r7_View.getRight());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getBottom());
                        r1i = r7_View.getId();
                        if (r1i != -1) {
                            r4_StringBuilder.append(" #");
                            r4_StringBuilder.append(Integer.toHexString(r1i));
                            r2_Resources = r7_View.getResources();
                            if (r1i == 0 || r2_Resources == null) {
                                r4_StringBuilder.append("}");
                            } else {
                                switch ((-16777216 & r1i)) {
                                    case Constants.FLAG_HARDWARE_ACCELERATED:
                                        r0_String = "android";
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        break;
                                    case 2130706432:
                                        r0_String = KEYS.APPINFO;
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        break;
                                }
                                r0_String = r2_Resources.getResourcePackageName(r1i);
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                            }
                        }
                        r4_StringBuilder.append("}");
                        return r4_StringBuilder.toString();
                    } else {
                        r4_StringBuilder.append(r2c);
                        r4_StringBuilder.append(' ');
                        r4_StringBuilder.append(r7_View.getLeft());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getTop());
                        r4_StringBuilder.append('-');
                        r4_StringBuilder.append(r7_View.getRight());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getBottom());
                        r1i = r7_View.getId();
                        if (r1i != -1) {
                            r4_StringBuilder.append("}");
                            return r4_StringBuilder.toString();
                        } else {
                            r4_StringBuilder.append(" #");
                            r4_StringBuilder.append(Integer.toHexString(r1i));
                            r2_Resources = r7_View.getResources();
                            if (r1i == 0 || r2_Resources == null) {
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                            } else {
                                switch ((-16777216 & r1i)) {
                                    case Constants.FLAG_HARDWARE_ACCELERATED:
                                        r0_String = "android";
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        r4_StringBuilder.append("}");
                                        return r4_StringBuilder.toString();
                                    case 2130706432:
                                        r0_String = KEYS.APPINFO;
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        r4_StringBuilder.append("}");
                                        return r4_StringBuilder.toString();
                                }
                                r0_String = r2_Resources.getResourcePackageName(r1i);
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                            }
                        }
                    }
                } else {
                    r4_StringBuilder.append(r1c);
                    if (r7_View.isSelected()) {
                    }
                    r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
                    if (r7_View.isPressed()) {
                        r2c = 'P';
                    }
                    r4_StringBuilder.append(r2c);
                    r4_StringBuilder.append(' ');
                    r4_StringBuilder.append(r7_View.getLeft());
                    r4_StringBuilder.append(',');
                    r4_StringBuilder.append(r7_View.getTop());
                    r4_StringBuilder.append('-');
                    r4_StringBuilder.append(r7_View.getRight());
                    r4_StringBuilder.append(',');
                    r4_StringBuilder.append(r7_View.getBottom());
                    r1i = r7_View.getId();
                    if (r1i != -1) {
                        r4_StringBuilder.append(" #");
                        r4_StringBuilder.append(Integer.toHexString(r1i));
                        r2_Resources = r7_View.getResources();
                        if (r1i == 0 || r2_Resources == null) {
                            r4_StringBuilder.append("}");
                        } else {
                            switch ((-16777216 & r1i)) {
                                case Constants.FLAG_HARDWARE_ACCELERATED:
                                    r0_String = "android";
                                    r3_String = r2_Resources.getResourceTypeName(r1i);
                                    r1_String = r2_Resources.getResourceEntryName(r1i);
                                    r4_StringBuilder.append(" ");
                                    r4_StringBuilder.append(r0_String);
                                    r4_StringBuilder.append(":");
                                    r4_StringBuilder.append(r3_String);
                                    r4_StringBuilder.append("/");
                                    r4_StringBuilder.append(r1_String);
                                    break;
                                case 2130706432:
                                    r0_String = KEYS.APPINFO;
                                    r3_String = r2_Resources.getResourceTypeName(r1i);
                                    r1_String = r2_Resources.getResourceEntryName(r1i);
                                    r4_StringBuilder.append(" ");
                                    r4_StringBuilder.append(r0_String);
                                    r4_StringBuilder.append(":");
                                    r4_StringBuilder.append(r3_String);
                                    r4_StringBuilder.append("/");
                                    r4_StringBuilder.append(r1_String);
                                    break;
                            }
                            r0_String = r2_Resources.getResourcePackageName(r1i);
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                        }
                    }
                    r4_StringBuilder.append("}");
                    return r4_StringBuilder.toString();
                }
            case XListViewFooter.STATE_NODATA:
                r4_StringBuilder.append('I');
                if (r7_View.isFocusable()) {
                }
                r4_StringBuilder.append(r7_View.isFocusable() ? '.' : 'F');
                if (r7_View.isEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isEnabled() ? '.' : 'E');
                if (r7_View.willNotDraw()) {
                }
                r4_StringBuilder.append(r7_View.willNotDraw() ? 'D' : '.');
                if (r7_View.isHorizontalScrollBarEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isHorizontalScrollBarEnabled() ? '.' : 'H');
                if (r7_View.isVerticalScrollBarEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isVerticalScrollBarEnabled() ? '.' : 'V');
                if (r7_View.isClickable()) {
                }
                r4_StringBuilder.append(r7_View.isClickable() ? '.' : 'C');
                if (r7_View.isLongClickable()) {
                }
                r4_StringBuilder.append(r7_View.isLongClickable() ? '.' : 'L');
                r4_StringBuilder.append(' ');
                if (r7_View.isFocused()) {
                    r1c = '.';
                }
                r4_StringBuilder.append(r1c);
                if (r7_View.isSelected()) {
                }
                r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
                if (r7_View.isPressed()) {
                    r2c = 'P';
                }
                r4_StringBuilder.append(r2c);
                r4_StringBuilder.append(' ');
                r4_StringBuilder.append(r7_View.getLeft());
                r4_StringBuilder.append(',');
                r4_StringBuilder.append(r7_View.getTop());
                r4_StringBuilder.append('-');
                r4_StringBuilder.append(r7_View.getRight());
                r4_StringBuilder.append(',');
                r4_StringBuilder.append(r7_View.getBottom());
                r1i = r7_View.getId();
                if (r1i != -1) {
                    r4_StringBuilder.append("}");
                    return r4_StringBuilder.toString();
                } else {
                    r4_StringBuilder.append(" #");
                    r4_StringBuilder.append(Integer.toHexString(r1i));
                    r2_Resources = r7_View.getResources();
                    if (r1i == 0 || r2_Resources == null) {
                        r4_StringBuilder.append("}");
                        return r4_StringBuilder.toString();
                    } else {
                        switch ((-16777216 & r1i)) {
                            case Constants.FLAG_HARDWARE_ACCELERATED:
                                r0_String = "android";
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                            case 2130706432:
                                r0_String = KEYS.APPINFO;
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                        }
                        r0_String = r2_Resources.getResourcePackageName(r1i);
                        r3_String = r2_Resources.getResourceTypeName(r1i);
                        r1_String = r2_Resources.getResourceEntryName(r1i);
                        r4_StringBuilder.append(" ");
                        r4_StringBuilder.append(r0_String);
                        r4_StringBuilder.append(":");
                        r4_StringBuilder.append(r3_String);
                        r4_StringBuilder.append("/");
                        r4_StringBuilder.append(r1_String);
                        r4_StringBuilder.append("}");
                        return r4_StringBuilder.toString();
                    }
                }
            case Base64.DONT_BREAK_LINES:
                r4_StringBuilder.append('G');
                if (r7_View.isFocusable()) {
                }
                r4_StringBuilder.append(r7_View.isFocusable() ? '.' : 'F');
                if (r7_View.isEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isEnabled() ? '.' : 'E');
                if (r7_View.willNotDraw()) {
                }
                r4_StringBuilder.append(r7_View.willNotDraw() ? 'D' : '.');
                if (r7_View.isHorizontalScrollBarEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isHorizontalScrollBarEnabled() ? '.' : 'H');
                if (r7_View.isVerticalScrollBarEnabled()) {
                }
                r4_StringBuilder.append(r7_View.isVerticalScrollBarEnabled() ? '.' : 'V');
                if (r7_View.isClickable()) {
                }
                r4_StringBuilder.append(r7_View.isClickable() ? '.' : 'C');
                if (r7_View.isLongClickable()) {
                }
                r4_StringBuilder.append(r7_View.isLongClickable() ? '.' : 'L');
                r4_StringBuilder.append(' ');
                if (r7_View.isFocused()) {
                    r4_StringBuilder.append(r1c);
                    if (r7_View.isSelected()) {
                    }
                    r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
                    if (r7_View.isPressed()) {
                        r4_StringBuilder.append(r2c);
                        r4_StringBuilder.append(' ');
                        r4_StringBuilder.append(r7_View.getLeft());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getTop());
                        r4_StringBuilder.append('-');
                        r4_StringBuilder.append(r7_View.getRight());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getBottom());
                        r1i = r7_View.getId();
                        if (r1i != -1) {
                            r4_StringBuilder.append(" #");
                            r4_StringBuilder.append(Integer.toHexString(r1i));
                            r2_Resources = r7_View.getResources();
                            if (r1i == 0 || r2_Resources == null) {
                                r4_StringBuilder.append("}");
                            } else {
                                switch ((-16777216 & r1i)) {
                                    case Constants.FLAG_HARDWARE_ACCELERATED:
                                        r0_String = "android";
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        break;
                                    case 2130706432:
                                        r0_String = KEYS.APPINFO;
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        break;
                                }
                                r0_String = r2_Resources.getResourcePackageName(r1i);
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                            }
                        }
                        r4_StringBuilder.append("}");
                        return r4_StringBuilder.toString();
                    } else {
                        r2c = 'P';
                        r4_StringBuilder.append(r2c);
                        r4_StringBuilder.append(' ');
                        r4_StringBuilder.append(r7_View.getLeft());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getTop());
                        r4_StringBuilder.append('-');
                        r4_StringBuilder.append(r7_View.getRight());
                        r4_StringBuilder.append(',');
                        r4_StringBuilder.append(r7_View.getBottom());
                        r1i = r7_View.getId();
                        if (r1i != -1) {
                            r4_StringBuilder.append("}");
                            return r4_StringBuilder.toString();
                        } else {
                            r4_StringBuilder.append(" #");
                            r4_StringBuilder.append(Integer.toHexString(r1i));
                            r2_Resources = r7_View.getResources();
                            if (r1i == 0 || r2_Resources == null) {
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                            } else {
                                switch ((-16777216 & r1i)) {
                                    case Constants.FLAG_HARDWARE_ACCELERATED:
                                        r0_String = "android";
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        r4_StringBuilder.append("}");
                                        return r4_StringBuilder.toString();
                                    case 2130706432:
                                        r0_String = KEYS.APPINFO;
                                        r3_String = r2_Resources.getResourceTypeName(r1i);
                                        r1_String = r2_Resources.getResourceEntryName(r1i);
                                        r4_StringBuilder.append(" ");
                                        r4_StringBuilder.append(r0_String);
                                        r4_StringBuilder.append(":");
                                        r4_StringBuilder.append(r3_String);
                                        r4_StringBuilder.append("/");
                                        r4_StringBuilder.append(r1_String);
                                        r4_StringBuilder.append("}");
                                        return r4_StringBuilder.toString();
                                }
                                r0_String = r2_Resources.getResourcePackageName(r1i);
                                r3_String = r2_Resources.getResourceTypeName(r1i);
                                r1_String = r2_Resources.getResourceEntryName(r1i);
                                r4_StringBuilder.append(" ");
                                r4_StringBuilder.append(r0_String);
                                r4_StringBuilder.append(":");
                                r4_StringBuilder.append(r3_String);
                                r4_StringBuilder.append("/");
                                r4_StringBuilder.append(r1_String);
                                r4_StringBuilder.append("}");
                                return r4_StringBuilder.toString();
                            }
                        }
                    }
                } else {
                    r1c = '.';
                    r4_StringBuilder.append(r1c);
                    if (r7_View.isSelected()) {
                    }
                    r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
                    if (r7_View.isPressed()) {
                        r2c = 'P';
                    }
                    r4_StringBuilder.append(r2c);
                    r4_StringBuilder.append(' ');
                    r4_StringBuilder.append(r7_View.getLeft());
                    r4_StringBuilder.append(',');
                    r4_StringBuilder.append(r7_View.getTop());
                    r4_StringBuilder.append('-');
                    r4_StringBuilder.append(r7_View.getRight());
                    r4_StringBuilder.append(',');
                    r4_StringBuilder.append(r7_View.getBottom());
                    r1i = r7_View.getId();
                    if (r1i != -1) {
                        r4_StringBuilder.append(" #");
                        r4_StringBuilder.append(Integer.toHexString(r1i));
                        r2_Resources = r7_View.getResources();
                        if (r1i == 0 || r2_Resources == null) {
                            r4_StringBuilder.append("}");
                        } else {
                            switch ((-16777216 & r1i)) {
                                case Constants.FLAG_HARDWARE_ACCELERATED:
                                    r0_String = "android";
                                    r3_String = r2_Resources.getResourceTypeName(r1i);
                                    r1_String = r2_Resources.getResourceEntryName(r1i);
                                    r4_StringBuilder.append(" ");
                                    r4_StringBuilder.append(r0_String);
                                    r4_StringBuilder.append(":");
                                    r4_StringBuilder.append(r3_String);
                                    r4_StringBuilder.append("/");
                                    r4_StringBuilder.append(r1_String);
                                    break;
                                case 2130706432:
                                    r0_String = KEYS.APPINFO;
                                    r3_String = r2_Resources.getResourceTypeName(r1i);
                                    r1_String = r2_Resources.getResourceEntryName(r1i);
                                    r4_StringBuilder.append(" ");
                                    r4_StringBuilder.append(r0_String);
                                    r4_StringBuilder.append(":");
                                    r4_StringBuilder.append(r3_String);
                                    r4_StringBuilder.append("/");
                                    r4_StringBuilder.append(r1_String);
                                    break;
                            }
                            r0_String = r2_Resources.getResourcePackageName(r1i);
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                        }
                    }
                    r4_StringBuilder.append("}");
                    return r4_StringBuilder.toString();
                }
        }
        r4_StringBuilder.append('.');
        if (r7_View.isFocusable()) {
        }
        r4_StringBuilder.append(r7_View.isFocusable() ? '.' : 'F');
        if (r7_View.isEnabled()) {
        }
        r4_StringBuilder.append(r7_View.isEnabled() ? '.' : 'E');
        if (r7_View.willNotDraw()) {
        }
        r4_StringBuilder.append(r7_View.willNotDraw() ? 'D' : '.');
        if (r7_View.isHorizontalScrollBarEnabled()) {
        }
        r4_StringBuilder.append(r7_View.isHorizontalScrollBarEnabled() ? '.' : 'H');
        if (r7_View.isVerticalScrollBarEnabled()) {
        }
        r4_StringBuilder.append(r7_View.isVerticalScrollBarEnabled() ? '.' : 'V');
        if (r7_View.isClickable()) {
        }
        r4_StringBuilder.append(r7_View.isClickable() ? '.' : 'C');
        if (r7_View.isLongClickable()) {
        }
        r4_StringBuilder.append(r7_View.isLongClickable() ? '.' : 'L');
        r4_StringBuilder.append(' ');
        if (r7_View.isFocused()) {
            r1c = '.';
        }
        r4_StringBuilder.append(r1c);
        if (r7_View.isSelected()) {
        }
        r4_StringBuilder.append(r7_View.isSelected() ? 'S' : '.');
        if (r7_View.isPressed()) {
            r4_StringBuilder.append(r2c);
            r4_StringBuilder.append(' ');
            r4_StringBuilder.append(r7_View.getLeft());
            r4_StringBuilder.append(',');
            r4_StringBuilder.append(r7_View.getTop());
            r4_StringBuilder.append('-');
            r4_StringBuilder.append(r7_View.getRight());
            r4_StringBuilder.append(',');
            r4_StringBuilder.append(r7_View.getBottom());
            r1i = r7_View.getId();
            if (r1i != -1) {
                r4_StringBuilder.append("}");
                return r4_StringBuilder.toString();
            } else {
                r4_StringBuilder.append(" #");
                r4_StringBuilder.append(Integer.toHexString(r1i));
                r2_Resources = r7_View.getResources();
                if (r1i == 0 || r2_Resources == null) {
                    r4_StringBuilder.append("}");
                    return r4_StringBuilder.toString();
                } else {
                    switch ((-16777216 & r1i)) {
                        case Constants.FLAG_HARDWARE_ACCELERATED:
                            r0_String = "android";
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                            r4_StringBuilder.append("}");
                            return r4_StringBuilder.toString();
                        case 2130706432:
                            r0_String = KEYS.APPINFO;
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                            r4_StringBuilder.append("}");
                            return r4_StringBuilder.toString();
                    }
                    r0_String = r2_Resources.getResourcePackageName(r1i);
                    r3_String = r2_Resources.getResourceTypeName(r1i);
                    r1_String = r2_Resources.getResourceEntryName(r1i);
                    r4_StringBuilder.append(" ");
                    r4_StringBuilder.append(r0_String);
                    r4_StringBuilder.append(":");
                    r4_StringBuilder.append(r3_String);
                    r4_StringBuilder.append("/");
                    r4_StringBuilder.append(r1_String);
                    r4_StringBuilder.append("}");
                    return r4_StringBuilder.toString();
                }
            }
        } else {
            r2c = 'P';
            r4_StringBuilder.append(r2c);
            r4_StringBuilder.append(' ');
            r4_StringBuilder.append(r7_View.getLeft());
            r4_StringBuilder.append(',');
            r4_StringBuilder.append(r7_View.getTop());
            r4_StringBuilder.append('-');
            r4_StringBuilder.append(r7_View.getRight());
            r4_StringBuilder.append(',');
            r4_StringBuilder.append(r7_View.getBottom());
            r1i = r7_View.getId();
            if (r1i != -1) {
                r4_StringBuilder.append(" #");
                r4_StringBuilder.append(Integer.toHexString(r1i));
                r2_Resources = r7_View.getResources();
                if (r1i == 0 || r2_Resources == null) {
                    r4_StringBuilder.append("}");
                } else {
                    switch ((-16777216 & r1i)) {
                        case Constants.FLAG_HARDWARE_ACCELERATED:
                            r0_String = "android";
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                            break;
                        case 2130706432:
                            r0_String = KEYS.APPINFO;
                            r3_String = r2_Resources.getResourceTypeName(r1i);
                            r1_String = r2_Resources.getResourceEntryName(r1i);
                            r4_StringBuilder.append(" ");
                            r4_StringBuilder.append(r0_String);
                            r4_StringBuilder.append(":");
                            r4_StringBuilder.append(r3_String);
                            r4_StringBuilder.append("/");
                            r4_StringBuilder.append(r1_String);
                            break;
                    }
                    r0_String = r2_Resources.getResourcePackageName(r1i);
                    r3_String = r2_Resources.getResourceTypeName(r1i);
                    r1_String = r2_Resources.getResourceEntryName(r1i);
                    r4_StringBuilder.append(" ");
                    r4_StringBuilder.append(r0_String);
                    r4_StringBuilder.append(":");
                    r4_StringBuilder.append(r3_String);
                    r4_StringBuilder.append("/");
                    r4_StringBuilder.append(r1_String);
                }
            }
            r4_StringBuilder.append("}");
            return r4_StringBuilder.toString();
        }
    }

    private void a(String r5_String, PrintWriter r6_PrintWriter, View r7_View) {
        r6_PrintWriter.print(r5_String);
        if (r7_View == null) {
            r6_PrintWriter.println("null");
        } else {
            r6_PrintWriter.println(a(r7_View));
            if (r7_View instanceof ViewGroup) {
                ViewGroup r7_ViewGroup = (ViewGroup) r7_View;
                int r1i = r7_ViewGroup.getChildCount();
                if (r1i > 0) {
                    String r2_String = r5_String + "  ";
                    int r0i = 0;
                    while (r0i < r1i) {
                        a(r2_String, r6_PrintWriter, r7_ViewGroup.getChildAt(r0i));
                        r0i++;
                    }
                }
            }
        }
    }

    w a(String r3_String, boolean r4z, boolean r5z) {
        if (this.l == null) {
            this.l = new SimpleArrayMap();
        }
        w r0_w = (w) this.l.get(r3_String);
        if (r0_w == null) {
            if (!r5z) {
                return r0_w;
            }
            r0_w = new w(r3_String, this, r4z);
            this.l.put(r3_String, r0_w);
            return r0_w;
        } else {
            r0_w.a(this);
            return r0_w;
        }
    }

    protected void a() {
        this.b.dispatchResume();
    }

    void a(String r3_String) {
        if (this.l != null) {
            w r0_w = (w) this.l.get(r3_String);
            if (r0_w == null || r0_w.g) {
            } else {
                r0_w.g();
                this.l.remove(r3_String);
            }
        }
    }

    void a(boolean r3z) {
        if (!this.g) {
            this.g = true;
            this.h = r3z;
            this.a.removeMessages(1);
            b();
        }
    }

    protected boolean a(View r2_View, Menu r3_Menu) {
        return super.onPreparePanel(0, r2_View, r3_Menu);
    }

    void b() {
        if (this.k) {
            this.k = false;
            if (this.m != null) {
                if (this.h) {
                    this.m.c();
                } else {
                    this.m.b();
                }
            }
        }
        this.b.dispatchReallyStop();
    }

    public void dump(String r4_String, FileDescriptor r5_FileDescriptor, PrintWriter r6_PrintWriter, String[] r7_StringA) {
        String r0_String;
        if (VERSION.SDK_INT >= 11) {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("Local FragmentActivity ");
            r6_PrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
            r6_PrintWriter.println(" State:");
            r0_String = r4_String + "  ";
            r6_PrintWriter.print(r0_String);
            r6_PrintWriter.print("mCreated=");
            r6_PrintWriter.print(this.d);
            r6_PrintWriter.print("mResumed=");
            r6_PrintWriter.print(this.e);
            r6_PrintWriter.print(" mStopped=");
            r6_PrintWriter.print(this.f);
            r6_PrintWriter.print(" mReallyStopped=");
            r6_PrintWriter.println(this.g);
            r6_PrintWriter.print(r0_String);
            r6_PrintWriter.print("mLoadersStarted=");
            r6_PrintWriter.println(this.k);
            if (this.m == null) {
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("Loader Manager ");
                r6_PrintWriter.print(Integer.toHexString(System.identityHashCode(this.m)));
                r6_PrintWriter.println(":");
                this.m.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
            }
            this.b.dump(r4_String, r5_FileDescriptor, r6_PrintWriter, r7_StringA);
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.println("View Hierarchy:");
            a(r4_String + "  ", r6_PrintWriter, getWindow().getDecorView());
        } else {
            r6_PrintWriter.print(r4_String);
            r6_PrintWriter.print("Local FragmentActivity ");
            r6_PrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
            r6_PrintWriter.println(" State:");
            r0_String = r4_String + "  ";
            r6_PrintWriter.print(r0_String);
            r6_PrintWriter.print("mCreated=");
            r6_PrintWriter.print(this.d);
            r6_PrintWriter.print("mResumed=");
            r6_PrintWriter.print(this.e);
            r6_PrintWriter.print(" mStopped=");
            r6_PrintWriter.print(this.f);
            r6_PrintWriter.print(" mReallyStopped=");
            r6_PrintWriter.println(this.g);
            r6_PrintWriter.print(r0_String);
            r6_PrintWriter.print("mLoadersStarted=");
            r6_PrintWriter.println(this.k);
            if (this.m == null) {
                this.b.dump(r4_String, r5_FileDescriptor, r6_PrintWriter, r7_StringA);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.println("View Hierarchy:");
                a(r4_String + "  ", r6_PrintWriter, getWindow().getDecorView());
            } else {
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.print("Loader Manager ");
                r6_PrintWriter.print(Integer.toHexString(System.identityHashCode(this.m)));
                r6_PrintWriter.println(":");
                this.m.dump(r4_String + "  ", r5_FileDescriptor, r6_PrintWriter, r7_StringA);
                this.b.dump(r4_String, r5_FileDescriptor, r6_PrintWriter, r7_StringA);
                r6_PrintWriter.print(r4_String);
                r6_PrintWriter.println("View Hierarchy:");
                a(r4_String + "  ", r6_PrintWriter, getWindow().getDecorView());
            }
        }
    }

    public Object getLastCustomNonConfigurationInstance() {
        b r0_b = (b) getLastNonConfigurationInstance();
        return r0_b != null ? r0_b.b : null;
    }

    public FragmentManager getSupportFragmentManager() {
        return this.b;
    }

    public LoaderManager getSupportLoaderManager() {
        if (this.m != null) {
            return this.m;
        }
        this.j = true;
        this.m = a("(root)", this.k, true);
        return this.m;
    }

    protected void onActivityResult(int r4i, int r5i, Intent r6_Intent) {
        this.b.noteStateNotSaved();
        int r0i = r4i >> 16;
        if (r0i != 0) {
            r0i--;
            if (this.b.f == null || r0i < 0 || r0i >= this.b.f.size()) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(r4i));
            } else {
                Fragment r0_Fragment = (Fragment) this.b.f.get(r0i);
                if (r0_Fragment == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(r4i));
                } else {
                    r0_Fragment.onActivityResult(65535 & r4i, r5i, r6_Intent);
                }
            }
        } else {
            super.onActivityResult(r4i, r5i, r6_Intent);
        }
    }

    public void onAttachFragment(Fragment r1_Fragment) {
    }

    public void onBackPressed() {
        if (!this.b.popBackStackImmediate()) {
            finish();
        }
    }

    public void onConfigurationChanged(Configuration r2_Configuration) {
        super.onConfigurationChanged(r2_Configuration);
        this.b.dispatchConfigurationChanged(r2_Configuration);
    }

    protected void onCreate(Bundle r5_Bundle) {
        this.b.attachActivity(this, this.c, null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(r5_Bundle);
        b r0_b = (b) getLastNonConfigurationInstance();
        if (r0_b != null) {
            this.l = r0_b.e;
        }
        if (r5_Bundle != null) {
            this.b.a(r5_Bundle.getParcelable("android:support:fragments"), r0_b != null ? r0_b.d : null);
        }
        this.b.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int r4i, Menu r5_Menu) {
        if (r4i != 0) {
            return super.onCreatePanelMenu(r4i, r5_Menu);
        }
        return VERSION.SDK_INT >= REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE ? super.onCreatePanelMenu(r4i, r5_Menu) | this.b.dispatchCreateOptionsMenu(r5_Menu, getMenuInflater()) : true;
    }

    public View onCreateView(String r10_String, Context r11_Context, AttributeSet r12_AttributeSet) {
        int r3i = 0;
        Fragment r1_Fragment = null;
        if (!"fragment".equals(r10_String)) {
            return super.onCreateView(r10_String, r11_Context, r12_AttributeSet);
        }
        String r0_String = r12_AttributeSet.getAttributeValue(null, "class");
        TypedArray r4_TypedArray = r11_Context.obtainStyledAttributes(r12_AttributeSet, a.Fragment);
        if (r0_String == null) {
            r0_String = r4_TypedArray.getString(0);
        }
        int r2i = r4_TypedArray.getResourceId(1, -1);
        String r5_String = r4_TypedArray.getString(XListViewHeader.STATE_REFRESHING);
        r4_TypedArray.recycle();
        if (!Fragment.a((Context)this, r0_String)) {
            return super.onCreateView(r10_String, r11_Context, r12_AttributeSet);
        }
        if (0 != 0) {
            r3i = null.getId();
        }
        if (r3i == -1 && r2i == -1 && r5_String == null) {
            throw new IllegalArgumentException(r12_AttributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + r0_String);
        } else {
            Fragment r4_Fragment;
            if (r2i != -1) {
                r1_Fragment = this.b.findFragmentById(r2i);
            }
            if (r1_Fragment != null || r5_String == null) {
                if (r1_Fragment != null || r3i == -1) {
                } else {
                    r1_Fragment = this.b.findFragmentById(r3i);
                }
                if (l.a) {
                    Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(r2i) + " fname=" + r0_String + " existing=" + r1_Fragment);
                }
                if (r1_Fragment != null) {
                    r4_Fragment = Fragment.instantiate(this, r0_String);
                    r4_Fragment.x = true;
                    r4_Fragment.F = r2i == 0 ? r2i : r3i;
                    r4_Fragment.G = r3i;
                    r4_Fragment.H = r5_String;
                    r4_Fragment.y = true;
                    r4_Fragment.B = this.b;
                    r4_Fragment.onInflate(this, r12_AttributeSet, r4_Fragment.m);
                    this.b.addFragment(r4_Fragment, true);
                    r1_Fragment = r4_Fragment;
                } else if (r1_Fragment.y) {
                    r1_Fragment.y = true;
                    if (r1_Fragment.L) {
                        r1_Fragment.onInflate(this, r12_AttributeSet, r1_Fragment.m);
                    }
                    this.b.a(r1_Fragment);
                } else {
                    throw new IllegalArgumentException(r12_AttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(r2i) + ", tag " + r5_String + ", or parent id 0x" + Integer.toHexString(r3i) + " with another fragment for " + r0_String);
                }
                if (r1_Fragment.R != null) {
                    throw new IllegalStateException("Fragment " + r0_String + " did not create a view.");
                } else {
                    if (r2i == 0) {
                        r1_Fragment.R.setId(r2i);
                    }
                    if (r1_Fragment.R.getTag() != null) {
                        r1_Fragment.R.setTag(r5_String);
                    }
                    return r1_Fragment.R;
                }
            } else {
                r1_Fragment = this.b.findFragmentByTag(r5_String);
                if (r1_Fragment != null || r3i == -1) {
                } else {
                    r1_Fragment = this.b.findFragmentById(r3i);
                }
                if (l.a) {
                    if (r1_Fragment != null) {
                        if (r1_Fragment.y) {
                            r1_Fragment.y = true;
                            if (r1_Fragment.L) {
                                this.b.a(r1_Fragment);
                            } else {
                                r1_Fragment.onInflate(this, r12_AttributeSet, r1_Fragment.m);
                                this.b.a(r1_Fragment);
                            }
                        } else {
                            throw new IllegalArgumentException(r12_AttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(r2i) + ", tag " + r5_String + ", or parent id 0x" + Integer.toHexString(r3i) + " with another fragment for " + r0_String);
                        }
                    } else {
                        r4_Fragment = Fragment.instantiate(this, r0_String);
                        r4_Fragment.x = true;
                        if (r2i == 0) {
                        }
                        r4_Fragment.F = r2i == 0 ? r2i : r3i;
                        r4_Fragment.G = r3i;
                        r4_Fragment.H = r5_String;
                        r4_Fragment.y = true;
                        r4_Fragment.B = this.b;
                        r4_Fragment.onInflate(this, r12_AttributeSet, r4_Fragment.m);
                        this.b.addFragment(r4_Fragment, true);
                        r1_Fragment = r4_Fragment;
                    }
                    if (r1_Fragment.R != null) {
                        if (r2i == 0) {
                            if (r1_Fragment.R.getTag() != null) {
                                return r1_Fragment.R;
                            }
                            r1_Fragment.R.setTag(r5_String);
                            return r1_Fragment.R;
                        } else {
                            r1_Fragment.R.setId(r2i);
                            if (r1_Fragment.R.getTag() != null) {
                                r1_Fragment.R.setTag(r5_String);
                            }
                            return r1_Fragment.R;
                        }
                    } else {
                        throw new IllegalStateException("Fragment " + r0_String + " did not create a view.");
                    }
                } else {
                    Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(r2i) + " fname=" + r0_String + " existing=" + r1_Fragment);
                    if (r1_Fragment != null) {
                        r4_Fragment = Fragment.instantiate(this, r0_String);
                        r4_Fragment.x = true;
                        if (r2i == 0) {
                        }
                        r4_Fragment.F = r2i == 0 ? r2i : r3i;
                        r4_Fragment.G = r3i;
                        r4_Fragment.H = r5_String;
                        r4_Fragment.y = true;
                        r4_Fragment.B = this.b;
                        r4_Fragment.onInflate(this, r12_AttributeSet, r4_Fragment.m);
                        this.b.addFragment(r4_Fragment, true);
                        r1_Fragment = r4_Fragment;
                    } else if (r1_Fragment.y) {
                        throw new IllegalArgumentException(r12_AttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(r2i) + ", tag " + r5_String + ", or parent id 0x" + Integer.toHexString(r3i) + " with another fragment for " + r0_String);
                    } else {
                        r1_Fragment.y = true;
                        if (r1_Fragment.L) {
                            r1_Fragment.onInflate(this, r12_AttributeSet, r1_Fragment.m);
                        }
                        this.b.a(r1_Fragment);
                    }
                    if (r1_Fragment.R != null) {
                        throw new IllegalStateException("Fragment " + r0_String + " did not create a view.");
                    } else {
                        if (r2i == 0) {
                            r1_Fragment.R.setId(r2i);
                        }
                        if (r1_Fragment.R.getTag() != null) {
                            return r1_Fragment.R;
                        }
                        r1_Fragment.R.setTag(r5_String);
                        return r1_Fragment.R;
                    }
                }
            }
            if (l.a) {
                Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(r2i) + " fname=" + r0_String + " existing=" + r1_Fragment);
            }
            if (r1_Fragment != null) {
                if (r1_Fragment.y) {
                    r1_Fragment.y = true;
                    if (r1_Fragment.L) {
                        this.b.a(r1_Fragment);
                    } else {
                        r1_Fragment.onInflate(this, r12_AttributeSet, r1_Fragment.m);
                        this.b.a(r1_Fragment);
                    }
                } else {
                    throw new IllegalArgumentException(r12_AttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(r2i) + ", tag " + r5_String + ", or parent id 0x" + Integer.toHexString(r3i) + " with another fragment for " + r0_String);
                }
            } else {
                r4_Fragment = Fragment.instantiate(this, r0_String);
                r4_Fragment.x = true;
                if (r2i == 0) {
                }
                r4_Fragment.F = r2i == 0 ? r2i : r3i;
                r4_Fragment.G = r3i;
                r4_Fragment.H = r5_String;
                r4_Fragment.y = true;
                r4_Fragment.B = this.b;
                r4_Fragment.onInflate(this, r12_AttributeSet, r4_Fragment.m);
                this.b.addFragment(r4_Fragment, true);
                r1_Fragment = r4_Fragment;
            }
            if (r1_Fragment.R != null) {
                if (r2i == 0) {
                    if (r1_Fragment.R.getTag() != null) {
                        r1_Fragment.R.setTag(r5_String);
                    }
                    return r1_Fragment.R;
                } else {
                    r1_Fragment.R.setId(r2i);
                    if (r1_Fragment.R.getTag() != null) {
                        return r1_Fragment.R;
                    }
                    r1_Fragment.R.setTag(r5_String);
                    return r1_Fragment.R;
                }
            } else {
                throw new IllegalStateException("Fragment " + r0_String + " did not create a view.");
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        a(false);
        this.b.dispatchDestroy();
        if (this.m != null) {
            this.m.g();
        }
    }

    public boolean onKeyDown(int r3i, KeyEvent r4_KeyEvent) {
        if (VERSION.SDK_INT >= 5 || r3i != 4 || r4_KeyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(r3i, r4_KeyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.b.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int r2i, MenuItem r3_MenuItem) {
        if (super.onMenuItemSelected(r2i, r3_MenuItem)) {
            return true;
        }
        switch (r2i) {
            case XListViewHeader.STATE_NORMAL:
                return this.b.dispatchOptionsItemSelected(r3_MenuItem);
            case ShareUtils.SHARE_COPY:
                return this.b.dispatchContextItemSelected(r3_MenuItem);
        }
        return false;
    }

    protected void onNewIntent(Intent r2_Intent) {
        super.onNewIntent(r2_Intent);
        this.b.noteStateNotSaved();
    }

    public void onPanelClosed(int r2i, Menu r3_Menu) {
        switch (r2i) {
            case XListViewHeader.STATE_NORMAL:
                this.b.dispatchOptionsMenuClosed(r3_Menu);
                break;
        }
        super.onPanelClosed(r2i, r3_Menu);
    }

    protected void onPause() {
        super.onPause();
        this.e = false;
        if (this.a.hasMessages(XListViewHeader.STATE_REFRESHING)) {
            this.a.removeMessages(XListViewHeader.STATE_REFRESHING);
            a();
        }
        this.b.dispatchPause();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.a.removeMessages(XListViewHeader.STATE_REFRESHING);
        a();
        this.b.execPendingActions();
    }

    public boolean onPreparePanel(int r3i, View r4_View, Menu r5_Menu) {
        if (r3i != 0 || r5_Menu == null) {
            return super.onPreparePanel(r3i, r4_View, r5_Menu);
        }
        if (this.i) {
            this.i = false;
            r5_Menu.clear();
            onCreatePanelMenu(r3i, r5_Menu);
        }
        return a(r4_View, r5_Menu) | this.b.dispatchPrepareOptionsMenu(r5_Menu);
    }

    protected void onResume() {
        super.onResume();
        this.a.sendEmptyMessage(XListViewHeader.STATE_REFRESHING);
        this.e = true;
        this.b.execPendingActions();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public final Object onRetainNonConfigurationInstance() {
        int r3i = 0;
        if (this.f) {
            a(true);
        }
        Object r5_Object = onRetainCustomNonConfigurationInstance();
        ArrayList r6_ArrayList = this.b.c();
        if (this.l != null) {
            int r7i = this.l.size();
            w[] r8_wA = new w[r7i];
            int r4i = r7i - 1;
            while (r4i >= 0) {
                r8_wA[r4i] = (w) this.l.valueAt(r4i);
                r4i--;
            }
            r0i = 0;
            while (r3i < r7i) {
                w r4_w = r8_wA[r3i];
                if (r4_w.g) {
                    r0i = 1;
                } else {
                    r4_w.g();
                    this.l.remove(r4_w.d);
                }
                r3i++;
            }
        } else {
            r0i = 0;
        }
        if (r6_ArrayList == null && r0i == 0 && r5_Object == null) {
            return null;
        }
        b r0_b = new b();
        r0_b.a = null;
        r0_b.b = r5_Object;
        r0_b.c = null;
        r0_b.d = r6_ArrayList;
        r0_b.e = this.l;
        return r0_b;
    }

    protected void onSaveInstanceState(Bundle r3_Bundle) {
        super.onSaveInstanceState(r3_Bundle);
        Parcelable r0_Parcelable = this.b.d();
        if (r0_Parcelable != null) {
            r3_Bundle.putParcelable("android:support:fragments", r0_Parcelable);
        }
    }

    protected void onStart() {
        super.onStart();
        this.f = false;
        this.g = false;
        this.a.removeMessages(1);
        if (!this.d) {
            this.d = true;
            this.b.dispatchActivityCreated();
        }
        this.b.noteStateNotSaved();
        this.b.execPendingActions();
        if (!this.k) {
            this.k = true;
            if (this.m != null) {
                this.m.a();
            } else if (!this.j) {
                this.m = a("(root)", this.k, false);
                if (this.m == null || this.m.f) {
                    this.j = true;
                } else {
                    this.m.a();
                }
            }
            this.j = true;
        }
        this.b.dispatchStart();
        if (this.l != null) {
            int r3i = this.l.size();
            w[] r4_wA = new w[r3i];
            int r2i = r3i - 1;
            while (r2i >= 0) {
                r4_wA[r2i] = (w) this.l.valueAt(r2i);
                r2i--;
            }
            int r0i = 0;
            while (r0i < r3i) {
                w r1_w = r4_wA[r0i];
                r1_w.d();
                r1_w.f();
                r0i++;
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.f = true;
        this.a.sendEmptyMessage(1);
        this.b.dispatchStop();
    }

    public void startActivityForResult(Intent r3_Intent, int r4i) {
        if (r4i == -1 || (-65536 & r4i) == 0) {
            super.startActivityForResult(r3_Intent, r4i);
        } else {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public void startActivityFromFragment(Fragment r3_Fragment, Intent r4_Intent, int r5i) {
        if (r5i == -1) {
            super.startActivityForResult(r4_Intent, -1);
        } else if ((-65536 & r5i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else {
            super.startActivityForResult(r4_Intent, (r3_Fragment.o + 1) << 16 + 65535 & r5i);
        }
    }

    public void supportInvalidateOptionsMenu() {
        if (VERSION.SDK_INT >= 11) {
            b.a(this);
        } else {
            this.i = true;
        }
    }
}
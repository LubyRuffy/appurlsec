package com.baidu.mobads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.baidu.mobads.a.b;
import com.baidu.mobads.a.d;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import qsbk.app.widget.listview.XListViewFooter;

public class AppActivity extends Activity {
    private Object a;
    private Class<?> b;
    private Method[] c;

    public AppActivity() {
        this.c = null;
    }

    private Method a(String r7_String) {
        if (this.c == null) {
            return null;
        }
        Method[] r3_MethodA = this.c;
        int r4i = r3_MethodA.length;
        int r2i = 0;
        while (r2i < r4i) {
            Method r1_Method = r3_MethodA[r2i];
            if (r1_Method.getName().equals(r7_String)) {
                r1_Method.setAccessible(true);
                return r1_Method;
            } else {
                r2i++;
            }
        }
        return null;
    }

    private void a(String r4_String, Object ... r5_ObjectA) {
        int r0i = 0;
        try {
            Object[] r1_ObjectA = new Object[XListViewFooter.STATE_NOMORE];
            r1_ObjectA[0] = r4_String;
            int r2i = 1;
            if (r5_ObjectA != null) {
                r0i = r5_ObjectA.length;
            }
            r1_ObjectA[r2i] = Integer.valueOf(r0i);
            r1_ObjectA[2] = r5_ObjectA;
            d.a(r1_ObjectA);
            Method r0_Method = a(r4_String);
            if (r0_Method != null) {
                if (r5_ObjectA == null || r5_ObjectA.length == 0) {
                    r0_Method.invoke(this.a, new Object[0]);
                } else {
                    r0_Method.invoke(this.a, r5_ObjectA);
                }
            }
        } catch (Exception e) {
            d.a(e);
        }
    }

    private boolean b(String r5_String, Object ... r6_ObjectA) {
        boolean r0z;
        try {
            Object[] r2_ObjectA = new Object[XListViewFooter.STATE_NOMORE];
            r2_ObjectA[0] = r5_String;
            r2_ObjectA[1] = Integer.valueOf(r6_ObjectA != null ? r6_ObjectA.length : 0);
            r2_ObjectA[2] = r6_ObjectA;
            d.a(r2_ObjectA);
            Method r0_Method = a(r5_String);
            if (r0_Method != null) {
                r0z = (r6_ObjectA == null || r6_ObjectA.length == 0) ? ((Boolean) r0_Method.invoke(this.a, new Object[0])).booleanValue() : ((Boolean) r0_Method.invoke(this.a, r6_ObjectA)).booleanValue();
                return r0z;
            }
        } catch (Exception e) {
            d.a(e);
        }
        r0z = false;
        return r0z;
    }

    private Object c(String r4_String, Object ... r5_ObjectA) {
        Object r0_Object;
        int r0i = 0;
        try {
            Object[] r1_ObjectA = new Object[XListViewFooter.STATE_NOMORE];
            r1_ObjectA[0] = r4_String;
            int r2i = 1;
            if (r5_ObjectA != null) {
                r0i = r5_ObjectA.length;
            }
            r1_ObjectA[r2i] = Integer.valueOf(r0i);
            r1_ObjectA[2] = r5_ObjectA;
            d.a(r1_ObjectA);
            Method r0_Method = a(r4_String);
            if (r0_Method != null) {
                r0_Object = (r5_ObjectA == null || r5_ObjectA.length == 0) ? r0_Method.invoke(this.a, new Object[0]) : r0_Method.invoke(this.a, r5_ObjectA);
                return r0_Object;
            }
        } catch (Exception e) {
            d.a(e);
        }
        r0_Object = null;
        return r0_Object;
    }

    public boolean dispatchKeyEvent(KeyEvent r5_KeyEvent) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_KeyEvent;
        return b("dispatchKeyEvent", r2_ObjectA) ? true : super.dispatchKeyEvent(r5_KeyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent r5_MotionEvent) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MotionEvent;
        return b("dispatchTouchEvent", r2_ObjectA) ? true : super.dispatchTouchEvent(r5_MotionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent r5_MotionEvent) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MotionEvent;
        return b("dispatchTrackballEvent", r2_ObjectA) ? true : super.dispatchTrackballEvent(r5_MotionEvent);
    }

    protected void finalize() {
        a("finalize", new Object[0]);
        super.finalize();
    }

    protected void onActivityResult(int r5i, int r6i, Intent r7_Intent) {
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = Integer.valueOf(r5i);
        r1_ObjectA[1] = Integer.valueOf(r6i);
        r1_ObjectA[2] = r7_Intent;
        a("onActivityResult", r1_ObjectA);
        super.onActivityResult(r5i, r6i, r7_Intent);
    }

    protected void onApplyThemeResource(Theme r5_Theme, int r6i, boolean r7z) {
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = r5_Theme;
        r1_ObjectA[1] = Integer.valueOf(r6i);
        r1_ObjectA[2] = Boolean.valueOf(r7z);
        a("onApplyThemeResource", r1_ObjectA);
        super.onApplyThemeResource(r5_Theme, r6i, r7z);
    }

    protected void onChildTitleChanged(Activity r4_Activity, CharSequence r5_CharSequence) {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = r4_Activity;
        r1_ObjectA[1] = r5_CharSequence;
        a("onChildTitleChanged", r1_ObjectA);
        super.onChildTitleChanged(r4_Activity, r5_CharSequence);
    }

    public void onConfigurationChanged(Configuration r4_Configuration) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Configuration;
        a("onConfigurationChanged", r1_ObjectA);
        super.onConfigurationChanged(r4_Configuration);
    }

    public void onContentChanged() {
        a("onContentChanged", new Object[0]);
        super.onContentChanged();
    }

    public boolean onContextItemSelected(MenuItem r5_MenuItem) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MenuItem;
        return b("onContextItemSelected", r2_ObjectA) ? true : super.onContextItemSelected(r5_MenuItem);
    }

    public void onContextMenuClosed(Menu r4_Menu) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Menu;
        a("onContextMenuClosed", r1_ObjectA);
        super.onContextMenuClosed(r4_Menu);
    }

    protected void onCreate(Bundle r8_Bundle) {
        Object[] r1_ObjectA;
        super.onCreate(r8_Bundle);
        try {
            String r0_String = getIntent().getExtras().getString("remote_activity");
            this.b = b.b(this, r0_String);
            this.c = this.b.getDeclaredMethods();
            Class r1_Class = this.b;
            Class[] r2_ClassA = new Class[1];
            r2_ClassA[0] = Activity.class;
            Constructor r1_Constructor = r1_Class.getConstructor(r2_ClassA);
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = this;
            this.a = r1_Constructor.newInstance(r2_ObjectA);
            r1_ObjectA = new Object[3];
            r1_ObjectA[0] = r0_String;
            r1_ObjectA[1] = this.b;
            r1_ObjectA[2] = this.a;
            d.a(r1_ObjectA);
        } catch (Exception e) {
            d.b(e);
        }
        r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r8_Bundle;
        a("onCreate", r1_ObjectA);
    }

    public void onCreateContextMenu(ContextMenu r4_ContextMenu, View r5_View, ContextMenuInfo r6_ContextMenuInfo) {
        super.onCreateContextMenu(r4_ContextMenu, r5_View, r6_ContextMenuInfo);
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = r4_ContextMenu;
        r1_ObjectA[1] = r5_View;
        r1_ObjectA[2] = r6_ContextMenuInfo;
        a("onCreateContextMenu", r1_ObjectA);
    }

    public CharSequence onCreateDescription() {
        CharSequence r0_CharSequence = (CharSequence) c("onCreateDescription", new Object[0]);
        return r0_CharSequence != null ? r0_CharSequence : super.onCreateDescription();
    }

    protected Dialog onCreateDialog(int r5i) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = Integer.valueOf(r5i);
        Dialog r0_Dialog = (Dialog) c("onCreateDialog", r1_ObjectA);
        return r0_Dialog != null ? r0_Dialog : super.onCreateDialog(r5i);
    }

    public boolean onCreateOptionsMenu(Menu r5_Menu) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_Menu;
        return b("onCreateOptionsMenu", r2_ObjectA) ? true : super.onCreateOptionsMenu(r5_Menu);
    }

    public boolean onCreatePanelMenu(int r6i, Menu r7_Menu) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_Menu;
        return b("onCreatePanelMenu", r2_ObjectA) ? true : super.onCreatePanelMenu(r6i, r7_Menu);
    }

    public View onCreatePanelView(int r5i) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = Integer.valueOf(r5i);
        View r0_View = (View) c("onCreatePanelView", r1_ObjectA);
        return r0_View != null ? r0_View : super.onCreatePanelView(r5i);
    }

    public boolean onCreateThumbnail(Bitmap r5_Bitmap, Canvas r6_Canvas) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = r5_Bitmap;
        r2_ObjectA[1] = r6_Canvas;
        return b("onCreateThumbnail", r2_ObjectA) ? true : super.onCreateThumbnail(r5_Bitmap, r6_Canvas);
    }

    public View onCreateView(String r4_String, Context r5_Context, AttributeSet r6_AttributeSet) {
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = r4_String;
        r1_ObjectA[1] = r5_Context;
        r1_ObjectA[2] = r6_AttributeSet;
        View r0_View = (View) c("onCreateView", r1_ObjectA);
        return r0_View != null ? r0_View : super.onCreateView(r4_String, r5_Context, r6_AttributeSet);
    }

    protected void onDestroy() {
        a("onDestroy", new Object[0]);
        super.onDestroy();
    }

    public boolean onKeyDown(int r6i, KeyEvent r7_KeyEvent) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_KeyEvent;
        return b("onKeyDown", r2_ObjectA) ? true : super.onKeyDown(r6i, r7_KeyEvent);
    }

    public boolean onKeyMultiple(int r6i, int r7i, KeyEvent r8_KeyEvent) {
        Object[] r2_ObjectA = new Object[3];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = Integer.valueOf(r7i);
        r2_ObjectA[2] = r8_KeyEvent;
        return b("onKeyMultiple", r2_ObjectA) ? true : super.onKeyMultiple(r6i, r7i, r8_KeyEvent);
    }

    public boolean onKeyUp(int r6i, KeyEvent r7_KeyEvent) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_KeyEvent;
        return b("onKeyUp", r2_ObjectA) ? true : super.onKeyUp(r6i, r7_KeyEvent);
    }

    public void onLowMemory() {
        a("onLowMemory", new Object[0]);
        super.onLowMemory();
    }

    public boolean onMenuItemSelected(int r6i, MenuItem r7_MenuItem) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_MenuItem;
        return b("onMenuItemSelected", r2_ObjectA) ? true : super.onMenuItemSelected(r6i, r7_MenuItem);
    }

    public boolean onMenuOpened(int r6i, Menu r7_Menu) {
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_Menu;
        return b("onMenuOpened", r2_ObjectA) ? true : super.onMenuOpened(r6i, r7_Menu);
    }

    protected void onNewIntent(Intent r4_Intent) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Intent;
        a("onNewIntent", r1_ObjectA);
        super.onNewIntent(r4_Intent);
    }

    public boolean onOptionsItemSelected(MenuItem r5_MenuItem) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MenuItem;
        return b("onOptionsItemSelected", r2_ObjectA) ? true : super.onOptionsItemSelected(r5_MenuItem);
    }

    public void onOptionsMenuClosed(Menu r4_Menu) {
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Menu;
        a("onOptionsMenuClosed", r1_ObjectA);
        super.onOptionsMenuClosed(r4_Menu);
    }

    public void onPanelClosed(int r5i, Menu r6_Menu) {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = Integer.valueOf(r5i);
        r1_ObjectA[1] = r6_Menu;
        a("onPanelClosed", r1_ObjectA);
        super.onPanelClosed(r5i, r6_Menu);
    }

    protected void onPause() {
        a("onPause", new Object[0]);
        super.onPause();
    }

    protected void onPostCreate(Bundle r4_Bundle) {
        super.onPostCreate(r4_Bundle);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Bundle;
        a("onPostCreate", r1_ObjectA);
    }

    protected void onPostResume() {
        super.onPostResume();
        a("onPostResume", new Object[0]);
    }

    protected void onPrepareDialog(int r5i, Dialog r6_Dialog) {
        super.onPrepareDialog(r5i, r6_Dialog);
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = Integer.valueOf(r5i);
        r1_ObjectA[1] = r6_Dialog;
        a("onPrepareDialog", r1_ObjectA);
    }

    public boolean onPrepareOptionsMenu(Menu r5_Menu) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_Menu;
        return b("onPrepareOptionsMenu", r2_ObjectA) ? true : super.onPrepareOptionsMenu(r5_Menu);
    }

    public boolean onPreparePanel(int r6i, View r7_View, Menu r8_Menu) {
        Object[] r2_ObjectA = new Object[3];
        r2_ObjectA[0] = Integer.valueOf(r6i);
        r2_ObjectA[1] = r7_View;
        r2_ObjectA[2] = r8_Menu;
        return b("onPreparePanel", r2_ObjectA) ? true : super.onPreparePanel(r6i, r7_View, r8_Menu);
    }

    protected void onRestart() {
        super.onRestart();
        a("onRestart", new Object[0]);
    }

    protected void onRestoreInstanceState(Bundle r4_Bundle) {
        super.onRestoreInstanceState(r4_Bundle);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Bundle;
        a("onRestoreInstanceState", r1_ObjectA);
    }

    protected void onResume() {
        super.onResume();
        a("onResume", new Object[0]);
    }

    public Object onRetainNonConfigurationInstance() {
        Object r0_Object = c("onRetainNonConfigurationInstance", new Object[0]);
        return r0_Object != null ? r0_Object : super.onRetainNonConfigurationInstance();
    }

    protected void onSaveInstanceState(Bundle r4_Bundle) {
        super.onSaveInstanceState(r4_Bundle);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_Bundle;
        a("onSaveInstanceState", r1_ObjectA);
    }

    public boolean onSearchRequested() {
        return b("onSearchRequested", new Object[0]) ? true : super.onSearchRequested();
    }

    protected void onStart() {
        super.onStart();
        a("onStart", new Object[0]);
    }

    protected void onStop() {
        a("onStop", new Object[0]);
        super.onStop();
    }

    protected void onTitleChanged(CharSequence r5_CharSequence, int r6i) {
        super.onTitleChanged(r5_CharSequence, r6i);
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = r5_CharSequence;
        r1_ObjectA[1] = Integer.valueOf(r6i);
        a("onTitleChanged", r1_ObjectA);
    }

    public boolean onTouchEvent(MotionEvent r5_MotionEvent) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MotionEvent;
        return b("onTouchEvent", r2_ObjectA) ? true : super.onTouchEvent(r5_MotionEvent);
    }

    public boolean onTrackballEvent(MotionEvent r5_MotionEvent) {
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = r5_MotionEvent;
        return b("onTrackballEvent", r2_ObjectA) ? true : super.onTrackballEvent(r5_MotionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        a("onUserInteraction", new Object[0]);
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        a("onUserLeaveHint", new Object[0]);
    }

    public void onWindowAttributesChanged(LayoutParams r4_LayoutParams) {
        super.onWindowAttributesChanged(r4_LayoutParams);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = r4_LayoutParams;
        a("onWindowAttributesChanged", r1_ObjectA);
    }

    public void onWindowFocusChanged(boolean r5z) {
        super.onWindowFocusChanged(r5z);
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = Boolean.valueOf(r5z);
        a("onWindowFocusChanged", r1_ObjectA);
    }
}
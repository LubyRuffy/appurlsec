package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobstat.a.c;

public class StatActivity extends Activity {
    public void onPause() {
        super.onPause();
        c.a("stat", "StatActivity.OnResume()");
        StatService.onPause((Context)this);
    }

    public void onResume() {
        super.onResume();
        c.a("stat", "StatActivity.OnResume()");
        StatService.onResume((Context)this);
    }
}
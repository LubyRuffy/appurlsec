package com.baidu.mobstat;

import android.support.v4.app.Fragment;
import com.baidu.mobstat.a.c;

public class StatFragment extends Fragment {
    public void onPause() {
        super.onPause();
        c.a("stat", "StatFragment.OnResume()");
        StatService.onPause((Fragment)this);
    }

    public void onResume() {
        super.onResume();
        c.a("stat", "StatFragment.OnResume()");
        StatService.onResume((Fragment)this);
    }
}
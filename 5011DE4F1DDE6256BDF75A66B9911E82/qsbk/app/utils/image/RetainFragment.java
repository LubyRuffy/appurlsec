package qsbk.app.utils.image;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class RetainFragment extends Fragment {
    private Object a;

    public static RetainFragment findOrCreateRetainFragment(FragmentManager r3_FragmentManager) {
        RetainFragment r0_RetainFragment = (RetainFragment) r3_FragmentManager.findFragmentByTag("RetainFragment");
        if (r0_RetainFragment != null) {
            return r0_RetainFragment;
        }
        Fragment r0_Fragment = new RetainFragment();
        r3_FragmentManager.beginTransaction().add(r0_Fragment, "RetainFragment").commit();
        return r0_Fragment;
    }

    public Object getObject() {
        return this.a;
    }

    public void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        setRetainInstance(true);
    }

    public void setObject(Object r1_Object) {
        this.a = r1_Object;
    }
}
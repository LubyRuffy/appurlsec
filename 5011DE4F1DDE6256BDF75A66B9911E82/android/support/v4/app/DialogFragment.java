package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.qiubai.library.adview.util.AdViewUtil;

public class DialogFragment extends Fragment implements OnCancelListener, OnDismissListener {
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int a;
    int b;
    boolean c;
    boolean d;
    int e;
    Dialog f;
    boolean g;
    boolean h;
    boolean i;

    public DialogFragment() {
        this.a = 0;
        this.b = 0;
        this.c = true;
        this.d = true;
        this.e = -1;
    }

    void a(boolean r4z) {
        if (this.h) {
        } else {
            this.h = true;
            this.i = false;
            if (this.f != null) {
                this.f.dismiss();
                this.f = null;
            }
            this.g = true;
            if (this.e >= 0) {
                getFragmentManager().popBackStack(this.e, (int)STYLE_NO_TITLE);
                this.e = -1;
            } else {
                FragmentTransaction r0_FragmentTransaction = getFragmentManager().beginTransaction();
                r0_FragmentTransaction.remove(this);
                if (r4z) {
                    r0_FragmentTransaction.commitAllowingStateLoss();
                } else {
                    r0_FragmentTransaction.commit();
                }
            }
        }
    }

    public void dismiss() {
        a(false);
    }

    public void dismissAllowingStateLoss() {
        a(true);
    }

    public Dialog getDialog() {
        return this.f;
    }

    public LayoutInflater getLayoutInflater(Bundle r3_Bundle) {
        if (!(this.d)) {
            return super.getLayoutInflater(r3_Bundle);
        }
        this.f = onCreateDialog(r3_Bundle);
        switch (this.a) {
            case STYLE_NO_TITLE:
            case STYLE_NO_FRAME:
                break;
            case STYLE_NO_INPUT:
                this.f.getWindow().addFlags(AdViewUtil.NETWORK_TYPE_CASEE);
                break;
            default:
                return this.f == null ? (LayoutInflater) this.f.getContext().getSystemService("layout_inflater") : (LayoutInflater) this.C.getSystemService("layout_inflater");
        }
        this.f.requestWindowFeature(STYLE_NO_TITLE);
        if (this.f == null) {
        }
    }

    public boolean getShowsDialog() {
        return this.d;
    }

    public int getTheme() {
        return this.b;
    }

    public boolean isCancelable() {
        return this.c;
    }

    public void onActivityCreated(Bundle r3_Bundle) {
        super.onActivityCreated(r3_Bundle);
        if (this.d) {
            View r0_View = getView();
            if (r0_View != null) {
                if (r0_View.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                } else {
                    this.f.setContentView(r0_View);
                }
            }
            this.f.setOwnerActivity(getActivity());
            this.f.setCancelable(this.c);
            this.f.setOnCancelListener(this);
            this.f.setOnDismissListener(this);
            if (r3_Bundle != null) {
                Bundle r0_Bundle = r3_Bundle.getBundle("android:savedDialogState");
                if (r0_Bundle != null) {
                    this.f.onRestoreInstanceState(r0_Bundle);
                }
            }
        }
    }

    public void onAttach(Activity r2_Activity) {
        super.onAttach(r2_Activity);
        if (!this.i) {
            this.h = false;
        }
    }

    public void onCancel(DialogInterface r1_DialogInterface) {
    }

    public void onCreate(Bundle r4_Bundle) {
        boolean r1z = true;
        super.onCreate(r4_Bundle);
        this.d = this.G == 0;
        if (r4_Bundle != null) {
            this.a = r4_Bundle.getInt("android:style", STYLE_NORMAL);
            this.b = r4_Bundle.getInt("android:theme", STYLE_NORMAL);
            this.c = r4_Bundle.getBoolean("android:cancelable", r1z);
            this.d = r4_Bundle.getBoolean("android:showsDialog", this.d);
            this.e = r4_Bundle.getInt("android:backStackId", -1);
        }
    }

    public Dialog onCreateDialog(Bundle r4_Bundle) {
        return new Dialog(getActivity(), getTheme());
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f != null) {
            this.g = true;
            this.f.dismiss();
            this.f = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.i || this.h) {
        } else {
            this.h = true;
        }
    }

    public void onDismiss(DialogInterface r2_DialogInterface) {
        if (!this.g) {
            a(true);
        }
    }

    public void onSaveInstanceState(Bundle r3_Bundle) {
        super.onSaveInstanceState(r3_Bundle);
        if (this.f != null) {
            Bundle r0_Bundle = this.f.onSaveInstanceState();
            if (r0_Bundle != null) {
                r3_Bundle.putBundle("android:savedDialogState", r0_Bundle);
            }
        }
        if (this.a != 0) {
            r3_Bundle.putInt("android:style", this.a);
        }
        if (this.b != 0) {
            r3_Bundle.putInt("android:theme", this.b);
        }
        if (!this.c) {
            r3_Bundle.putBoolean("android:cancelable", this.c);
        }
        if (!this.d) {
            r3_Bundle.putBoolean("android:showsDialog", this.d);
        }
        if (this.e != -1) {
            r3_Bundle.putInt("android:backStackId", this.e);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.f != null) {
            this.g = false;
            this.f.show();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.f != null) {
            this.f.hide();
        }
    }

    public void setCancelable(boolean r2z) {
        this.c = r2z;
        if (this.f != null) {
            this.f.setCancelable(r2z);
        }
    }

    public void setShowsDialog(boolean r1z) {
        this.d = r1z;
    }

    public void setStyle(int r3i, int r4i) {
        this.a = r3i;
        if (this.a == 2 || this.a == 3) {
            this.b = 16973913;
            if (r4i == 0) {
                this.b = r4i;
            }
        } else if (r4i == 0) {
        } else {
            this.b = r4i;
        }
    }

    public int show(FragmentTransaction r3_FragmentTransaction, String r4_String) {
        this.h = false;
        this.i = true;
        r3_FragmentTransaction.add((Fragment)this, r4_String);
        this.g = false;
        this.e = r3_FragmentTransaction.commit();
        return this.e;
    }

    public void show(FragmentManager r2_FragmentManager, String r3_String) {
        this.h = false;
        this.i = true;
        FragmentTransaction r0_FragmentTransaction = r2_FragmentManager.beginTransaction();
        r0_FragmentTransaction.add((Fragment)this, r3_String);
        r0_FragmentTransaction.commit();
    }
}
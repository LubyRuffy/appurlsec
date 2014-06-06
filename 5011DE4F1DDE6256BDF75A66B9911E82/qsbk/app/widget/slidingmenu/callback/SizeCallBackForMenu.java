package qsbk.app.widget.slidingmenu.callback;

import android.widget.ImageButton;
import qsbk.app.QsbkApp;
import qsbk.app.R;

public class SizeCallBackForMenu implements SizeCallBack {
    private ImageButton a;
    private int b;

    public SizeCallBackForMenu(ImageButton r1_ImageButton) {
        this.a = r1_ImageButton;
    }

    public void getViewSize(int r3i, int r4i, int r5i, int[] r6_intA) {
        r6_intA[0] = r4i;
        r6_intA[1] = r5i;
        if (r3i != 1) {
            if (QsbkApp.isPad) {
                r6_intA[0] = r4i / 2;
            } else {
                r6_intA[0] = r4i - this.b;
            }
        }
    }

    public void onGlobalLayout() {
        this.b = this.a.getMeasuredWidth() + QsbkApp.mContext.getResources().getDimensionPixelSize(R.dimen.enlarge_width) - QsbkApp.mContext.getResources().getDimensionPixelSize(R.dimen.buffer_width);
    }
}
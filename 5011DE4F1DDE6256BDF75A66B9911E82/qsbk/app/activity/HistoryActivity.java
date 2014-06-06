package qsbk.app.activity;

import android.os.Bundle;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import qsbk.app.Constants;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.activity.group.HistoryActivityGroup;

public class HistoryActivity extends GroupChildBaseListViewActivity {
    private String G;
    private String H;
    private SimpleDateFormat I;
    private SimpleDateFormat J;
    private long K;
    Calendar n;

    public HistoryActivity() {
        this.I = new SimpleDateFormat("yyyy-MM-dd");
        this.J = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
        this.n = null;
    }

    protected void a_() {
        long r0j = Calendar.getInstance().getTime().getTime();
        double r2d = Math.random();
        this.n = Calendar.getInstance();
        this.n.setTimeInMillis(((long) (((double) (r0j - this.K)) * r2d)) + this.K);
        this.H = this.J.format(this.n.getTime());
        this.G = this.I.format(this.n.getTime());
        this.n = null;
    }

    public void agaginTraver() {
        a_();
        this.lastRefreshFirstPageTime = null;
        if ((!this.p.equals(MysBaseActivity.TOP_REFRESH)) || this.s.getSelectedItemPosition() == 0) {
            d();
        } else {
            this.s.setSelection(0);
            d();
        }
    }

    protected void c() {
        this.s.stopRefresh();
        this.s.stopLoadMore();
        if (this.r.size() <= 0 || this.v) {
            this.s.loadNoMore();
            ((HistoryActivityGroup) getParent()).setTitleName(this.H);
            this.q.notifyDataSetChanged();
        } else {
            ((HistoryActivityGroup) getParent()).setTitleName(this.H);
            this.q.notifyDataSetChanged();
        }
    }

    protected String e() {
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.add(1, -1);
        this.G = this.I.format(r0_Calendar.getTime());
        this.H = this.J.format(r0_Calendar.getTime());
        return "\u7a7f\u8d8a\u4e2d...";
    }

    public String getCacheUniqueName() {
        return "history";
    }

    public String getTargetDataUrl(String r5_String) {
        if (MysBaseActivity.LOAD.equals(r5_String)) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (this.n != null) {
                r0_String = this.I.format(this.n.getTime());
            } else {
                Calendar r0_Calendar = Calendar.getInstance();
                r0_Calendar.add(1, -1);
                r0_String = this.I.format(r0_Calendar.getTime());
            }
            this.A.trackView("\u7a7f\u8d8a/" + r0_String + "/" + this.t);
        }
        return new StringBuffer(Constants.HISTORY).append("/").append(this.G).toString();
    }

    public String getVotePoint() {
        return "history/";
    }

    protected void onCreate(Bundle r5_Bundle) {
        e();
        super.onCreate(r5_Bundle);
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.set(2005, REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY, 1);
        this.K = r0_Calendar.getTime().getTime();
    }
}
package qsbk.app.provider;

import android.text.TextUtils;
import java.util.LinkedList;
import qsbk.app.QsbkApp;
import qsbk.app.utils.AuditQueue;
import qsbk.app.utils.DataParse.StampContent;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewFooter;

public class ContentLoadAndUpdate {
    public static final AuditQueue auditQueue;
    private int a;
    private LinkedList<DataUnit> b;

    static {
        auditQueue = new AuditQueue();
    }

    public ContentLoadAndUpdate() {
        this.a = 0;
        this.b = new LinkedList();
    }

    public void fillData(int r2i, DataUnit r3_DataUnit) {
        this.b.addLast(r3_DataUnit);
    }

    public DataUnit getCurrentData() {
        return loadData(this.a);
    }

    public int getIndex() {
        return this.a;
    }

    public int getRemoteDataSize() {
        return this.b.size();
    }

    public boolean hasNext() {
        return this.a + 1 < getRemoteDataSize();
    }

    public boolean hasPrevious() {
        return this.a > 1;
    }

    public boolean isNeedLoadMore() {
        return getRemoteDataSize() - this.a < XListViewFooter.STATE_NOMORE;
    }

    public DataUnit loadData(int r3i) {
        return (r3i < 0 || r3i > getRemoteDataSize()) ? null : (DataUnit) this.b.get(r3i - 1);
    }

    public DataUnit loadNext() {
        this.a++;
        DataUnit r0_DataUnit = loadData(this.a);
        return (HttpUtils.isWifi(QsbkApp.mContext) || TextUtils.isEmpty(r0_DataUnit.getImage())) ? r0_DataUnit : loadNext();
    }

    public DataUnit loadPrevious() {
        this.a--;
        return loadData(this.a);
    }

    public void setIndex(int r1i) {
        this.a = r1i;
    }

    public boolean udapteDataCurrent(StampContent r2_StampContent) {
        return updateData(this.a, r2_StampContent);
    }

    public boolean updateData(int r4i, StampContent r5_StampContent) {
        if (r4i < 0 || r4i > getRemoteDataSize()) {
            return false;
        }
        ((DataUnit) this.b.get(r4i - 1)).setEvaluate(r5_StampContent);
        auditQueue.push((DataUnit) this.b.get(r4i - 1));
        return true;
    }
}
package qsbk.app.activity.base;

// compiled from: GroupChildBaseListViewActivity.java
class w implements Runnable {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    w(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void run() {
        ((GroupBaseActivity) this.a.getParent()).g();
    }
}
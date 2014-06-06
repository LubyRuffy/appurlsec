package qsbk.app.activity.base;

// compiled from: GroupChildBaseListViewActivity.java
class p implements Runnable {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    p(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void run() {
        this.a.setCanBack(true);
    }
}
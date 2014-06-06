package android.support.v7.widget;

// compiled from: SearchView.java
class f implements Runnable {
    final /* synthetic */ SearchView a;

    f(SearchView r1_SearchView) {
        this.a = r1_SearchView;
    }

    public void run() {
        if (SearchView.b(this.a) == null || (!SearchView.b(this.a) instanceof m)) {
        } else {
            SearchView.b(this.a).changeCursor(null);
        }
    }
}
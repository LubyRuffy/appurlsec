package android.support.v7.app;

// compiled from: ActionBarActivityDelegateJB.java
class f extends e {
    f(ActionBarActivity r1_ActionBarActivity) {
        super(r1_ActionBarActivity);
    }

    public ActionBar createSupportActionBar() {
        return new ActionBarImplJB(this.a, this.a);
    }
}
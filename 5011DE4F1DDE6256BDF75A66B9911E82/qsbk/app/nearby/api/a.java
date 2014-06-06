package qsbk.app.nearby.api;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

// compiled from: BDLocationManger.java
class a implements BDLocationListener {
    final /* synthetic */ BDLocationManger a;

    a(BDLocationManger r1_BDLocationManger) {
        this.a = r1_BDLocationManger;
    }

    public void onReceiveLocation(BDLocation r2_BDLocation) {
        BDLocationManger.a(this.a, r2_BDLocation);
    }

    public void onReceivePoi(BDLocation r1_BDLocation) {
    }
}
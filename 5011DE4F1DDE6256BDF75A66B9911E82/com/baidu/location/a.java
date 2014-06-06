package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.widget.listview.XListViewFooter;

class a implements ServiceConnection {
    final /* synthetic */ LocationClient a;

    a(LocationClient r1_LocationClient) {
        this.a = r1_LocationClient;
    }

    public void onServiceConnected(ComponentName r4_ComponentName, IBinder r5_IBinder) {
        LocationClient.a(this.a, new Messenger(r5_IBinder));
        if (LocationClient.a(this.a) == null) {
            j.if("baidu_location_Client", "server not connected");
        } else {
            LocationClient.a(this.a, true);
            Log.d("baidu_location_client", "baidu location connected ...");
            try {
                Message r0_Message = Message.obtain(null, REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
                r0_Message.replyTo = LocationClient.b(this.a);
                r0_Message.setData(LocationClient.c(this.a));
                LocationClient.a(this.a).send(r0_Message);
                LocationClient.a(this.a, true);
                LocationClient.b(this.a, true);
                j.if("baidu_location_Client", "bindService ...");
                if (LocationClient.d(this.a) != null) {
                    LocationClient.e(this.a).obtainMessage(XListViewFooter.STATE_NODATA).sendToTarget();
                }
            } catch (Exception e) {
            }
        }
    }

    public void onServiceDisconnected(ComponentName r3_ComponentName) {
        LocationClient.a(this.a, null);
        LocationClient.a(this.a, false);
        j.if("baidu_location_Client", "unbindservice...");
    }
}
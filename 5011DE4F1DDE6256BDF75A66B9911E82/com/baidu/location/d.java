package com.baidu.location;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.push.Utils;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.utils.audit.Rotate3dAnimation;

class d {
    private static String a;
    private ArrayList b;
    private Handler c;
    private boolean d;
    private boolean e;

    private class a {
        public LocationClientOption do;
        public Messenger for;
        public int if;
        public String int;

        public a(Message r5_Message) {
            this.int = null;
            this.for = null;
            this.do = new LocationClientOption();
            this.if = 0;
            this.for = r5_Message.replyTo;
            this.int = r5_Message.getData().getString("packName");
            this.do.f = r5_Message.getData().getString("prodName");
            j.ak = this.int;
            j.b = this.do.f;
            d.this = r5_Message.getData().getString("coorType");
            this.do.b = r5_Message.getData().getString("addrType");
            j.A = this.do.b;
            this.do.c = r5_Message.getData().getBoolean("openGPS");
            this.do.d = r5_Message.getData().getInt("scanSpan");
            this.do.e = r5_Message.getData().getInt("timeOut");
            this.do.g = r5_Message.getData().getInt("priority");
            this.do.h = r5_Message.getData().getBoolean("location_change_notify");
        }

        private void a_(int r3i) {
            Message r0_Message = Message.obtain(null, r3i);
            try {
                if (this.for != null) {
                    this.for.send(r0_Message);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        private void a_(int r3i, String r4_String, String r5_String) {
            Bundle r0_Bundle = new Bundle();
            r0_Bundle.putString(r4_String, r5_String);
            Message r1_Message = Message.obtain(null, r3i);
            r1_Message.setData(r0_Bundle);
            try {
                if (this.for != null) {
                    this.for.send(r1_Message);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        public void a_() {
            a((int)AdViewUtil.NETWORK_TYPE_KUAIYOU);
        }

        public void a_(String r3_String) {
            if (this.do.h) {
                if(r3_String);
            }
        }

        public void a_(String r14_String, int r15i) {
            int r0i = 0;
            j.if(a, "decode...");
            if (r14_String == null) {
            } else {
                if (r15i == 21) {
                    a(27, "locStr", r14_String);
                }
                if (d.this == null || d.this.equals("gcj02")) {
                    a(r15i, "locStr", r14_String);
                } else {
                    double r1d = j.c(r14_String, "x\":\"", "\"");
                    double r3d = j.c(r14_String, "y\":\"", "\"");
                    j.if(a, "decode..." + r1d + ":" + r3d);
                    if (r1d == 4.9E-324d || r3d == 4.9E-324d) {
                    } else {
                        double[] r1_doubleA = Jni.if(r1d, r3d, d.this);
                        r14_String = j.a(j.a(r14_String, "x\":\"", "\"", r1_doubleA[0]), "y\":\"", "\"", r1_doubleA[1]);
                        j.if(a, "decode2 ..." + r1_doubleA[0] + ":" + r1_doubleA[1]);
                        j.if(a, "decode3 ..." + r14_String);
                    }
                    if (this.do.j) {
                        try {
                            JSONObject r1_JSONObject = new JSONObject(r14_String);
                            JSONObject r2_JSONObject = r1_JSONObject.getJSONObject("result");
                            JSONObject r3_JSONObject = r1_JSONObject.getJSONObject(Utils.RESPONSE_CONTENT);
                            if (Integer.parseInt(r2_JSONObject.getString(QQDialogAuthorizeActivity.ERROR_RET)) == BDLocation.TypeNetWorkLocation) {
                                r2_JSONObject = r3_JSONObject.getJSONObject("poi");
                                JSONArray r4_JSONArray = r2_JSONObject.getJSONArray("p");
                                while (r0i < r4_JSONArray.length()) {
                                    JSONObject r5_JSONObject = r4_JSONArray.getJSONObject(r0i);
                                    double r6d = Double.parseDouble(r5_JSONObject.getString(Rotate3dAnimation.ROTATE_X));
                                    double r8d = Double.parseDouble(r5_JSONObject.getString(Rotate3dAnimation.ROTATE_Y));
                                    if (r6d == 4.9E-324d || r8d == 4.9E-324d) {
                                    } else {
                                        double[] r6_doubleA = Jni.if(r6d, r8d, d.this);
                                        r5_JSONObject.put(Rotate3dAnimation.ROTATE_X, String.valueOf(r6_doubleA[0]));
                                        r5_JSONObject.put(Rotate3dAnimation.ROTATE_Y, String.valueOf(r6_doubleA[1]));
                                        r4_JSONArray.put(r0i, r5_JSONObject);
                                        r0i++;
                                    }
                                }
                                r2_JSONObject.put("p", r4_JSONArray);
                                r3_JSONObject.put("poi", r2_JSONObject);
                                r1_JSONObject.put(Utils.RESPONSE_CONTENT, r3_JSONObject);
                                r14_String = r1_JSONObject.toString();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    a(r15i, "locStr", r14_String);
                }
            }
        }

        public void if() {
            if (this.do.h) {
                if (j.ab) {
                    a(54);
                } else {
                    a(55);
                }
            }
        }

        public void if(String r12_String) {
            if (r12_String == null) {
            } else {
                a(27, "locStr", r12_String);
                if (d.this == null || d.this.equals("gcj02")) {
                    a(AdViewUtil.NETWORK_TYPE_WOOBOO, "locStr", r12_String);
                } else {
                    double r0d = j.c(r12_String, "x\":\"", "\"");
                    double r2d = j.c(r12_String, "y\":\"", "\"");
                    if (r0d == 4.9E-324d || r2d == 4.9E-324d) {
                        a(AdViewUtil.NETWORK_TYPE_WOOBOO, "locStr", r12_String);
                    } else {
                        double[] r0_doubleA = Jni.if(r0d, r2d, d.this);
                        if (r0_doubleA[0] > 0.0d || r0_doubleA[1] > 0.0d) {
                            r12_String = j.a(j.a(r12_String, "x\":\"", "\"", r0_doubleA[0]), "y\":\"", "\"", r0_doubleA[1]);
                            a(AdViewUtil.NETWORK_TYPE_WOOBOO, "locStr", r12_String);
                        } else {
                            a(AdViewUtil.NETWORK_TYPE_WOOBOO, "locStr", r12_String);
                        }
                    }
                }
            }
        }
    }

    static {
        a = f.v;
    }

    public d(Handler r3_Handler) {
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.c = r3_Handler;
        this.b = new ArrayList();
    }

    private a a(Messenger r5_Messenger) {
        if (this.b == null) {
            return null;
        }
        Iterator r2_Iterator = this.b.iterator();
        while (r2_Iterator.hasNext()) {
            a r0_a = (a) r2_Iterator.next();
            if (r0_a.for.equals(r5_Messenger)) {
                return r0_a;
            }
        }
        return null;
    }

    private void a(a r4_a) {
        if (r4_a == null) {
        } else if (a(r4_a.for) != null) {
            r4_a.a((int)REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION);
        } else {
            this.b.add(r4_a);
            j.if(a, r4_a.int + " registered ");
            r4_a.a((int)REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
        }
    }

    private void b() {
        c();
        d();
        new();
    }

    private void c() {
        Iterator r4_Iterator = this.b.iterator();
        boolean r1z = false;
        boolean r2z = false;
        while (r4_Iterator.hasNext()) {
            a r0_a = (a) r4_Iterator.next();
            if (r0_a.do.c) {
                r2z = true;
            }
            r1z = r0_a.do.h ? true : r1z;
        }
        j.H = r1z;
        if (this.d != r2z) {
            this.d = r2z;
            this.c.obtainMessage(AdViewUtil.NETWORK_TYPE_ADLANTIS).sendToTarget();
        }
    }

    private void d_() {
        Iterator r2_Iterator = this.b.iterator();
        boolean r1z = false;
        while (r2_Iterator.hasNext()) {
            r1z = ((a) r2_Iterator.next()).do.f.equals("kuikedefancaiburudashahaochi") ? true : r1z;
        }
        if (this.e != r1z) {
            this.e = r1z;
            this.c.obtainMessage(81).sendToTarget();
        }
    }

    public String a(Message r7_Message) {
        if (r7_Message == null || r7_Message.replyTo == null) {
            j.if(a, "invalid Poirequest");
            return null;
        } else {
            a r1_a = a(r7_Message.replyTo);
            if (r1_a == null) {
                return null;
            }
            r1_a.do.m = r7_Message.getData().getInt("num", r1_a.do.m);
            r1_a.do.l = r7_Message.getData().getFloat("distance", r1_a.do.l);
            r1_a.do.k = r7_Message.getData().getBoolean("extraInfo", r1_a.do.k);
            r1_a.do.j = true;
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = Float.valueOf(r1_a.do.l);
            r2_ObjectA[1] = Integer.valueOf(r1_a.do.m);
            String r0_String = String.format("&poi=%.1f|%d", r2_ObjectA);
            return r1_a.do.k ? r0_String + "|1" : r0_String;
        }
    }

    public void a(String r6_String) {
        ArrayList r1_ArrayList = new ArrayList();
        Iterator r2_Iterator = this.b.iterator();
        while (r2_Iterator.hasNext()) {
            a r0_a = (a) r2_Iterator.next();
            r0_a.if(r6_String);
            if (r0_a.if > 4) {
                r1_ArrayList.add(r0_a);
            }
        }
        if (r1_ArrayList == null || r1_ArrayList.size() <= 0) {
        } else {
            Iterator r1_Iterator = r1_ArrayList.iterator();
            while (r1_Iterator.hasNext()) {
                j.if(a, "remove dead object...");
                this.b.remove((a) r1_Iterator.next());
            }
        }
    }

    public void a(String r6_String, int r7i) {
        ArrayList r1_ArrayList = new ArrayList();
        Iterator r2_Iterator = this.b.iterator();
        while (r2_Iterator.hasNext()) {
            a r0_a = (a) r2_Iterator.next();
            r0_a.a(r6_String, r7i);
            if (r0_a.if > 4) {
                r1_ArrayList.add(r0_a);
            }
        }
        if (r1_ArrayList == null || r1_ArrayList.size() <= 0) {
        } else {
            Iterator r1_Iterator = r1_ArrayList.iterator();
            while (r1_Iterator.hasNext()) {
                j.if(a, "remove dead object...");
                this.b.remove((a) r1_Iterator.next());
            }
        }
    }

    public void a(String r4_String, Message r5_Message) {
        if (r4_String == null || r5_Message == null) {
        } else {
            a r0_a = a(r5_Message.replyTo);
            if (r0_a != null) {
                r0_a.if(r4_String);
                if (r0_a.if > 4) {
                    this.b.remove(r0_a);
                }
            } else {
                j.if(a, "not found the client messener...");
            }
        }
    }

    public String byte() {
        StringBuffer r1_StringBuffer = new StringBuffer(256);
        a r0_a = (a) this.b.get(0);
        if (r0_a.do.f != null) {
            r1_StringBuffer.append(r0_a.do.f);
        }
        if (r0_a.int != null) {
            r1_StringBuffer.append(":");
            r1_StringBuffer.append(r0_a.int);
            r1_StringBuffer.append("|");
        }
        String r0_String = r1_StringBuffer.toString();
        return (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY)) ? null : "&prod=" + r0_String;
    }

    public int do(Message r4_Message) {
        if (r4_Message == null || r4_Message.replyTo == null) {
            return 1;
        }
        a r1_a = a(r4_Message.replyTo);
        return (r1_a == null || r1_a.do == null) ? 1 : r1_a.do.g;
    }

    public boolean for() {
        return this.d;
    }

    public boolean for(Message r10_Message) {
        boolean r0z = true;
        a r2_a = a(r10_Message.replyTo);
        if (r2_a == null) {
            return false;
        }
        String r1_String;
        LocationClientOption r3_LocationClientOption;
        int r3i = r2_a.do.d;
        r2_a.do.d = r10_Message.getData().getInt("scanSpan", r2_a.do.d);
        if (r2_a.do.d < 1000) {
            j.R = false;
        } else {
            j.R = true;
        }
        if (r2_a.do.d <= 999 || r3i >= 1000) {
            r0z = false;
            r2_a.do.c = r10_Message.getData().getBoolean("openGPS", r2_a.do.c);
            r1_String = r10_Message.getData().getString("coorType");
            r3_LocationClientOption = r2_a.do;
        } else {
            r2_a.do.c = r10_Message.getData().getBoolean("openGPS", r2_a.do.c);
            r1_String = r10_Message.getData().getString("coorType");
            r3_LocationClientOption = r2_a.do;
        }
        if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            r1_String = r2_a.do.a;
            r3_LocationClientOption.a = r1_String;
            r1_String = r10_Message.getData().getString("addrType");
            r3_LocationClientOption = r2_a.do;
        } else {
            r3_LocationClientOption.a = r1_String;
            r1_String = r10_Message.getData().getString("addrType");
            r3_LocationClientOption = r2_a.do;
        }
        if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            r1_String = r2_a.do.b;
            r3_LocationClientOption.b = r1_String;
            j.A = r2_a.do.b;
            r2_a.do.e = r10_Message.getData().getInt("timeOut", r2_a.do.e);
            r2_a.do.h = r10_Message.getData().getBoolean("location_change_notify", r2_a.do.h);
            r2_a.do.g = r10_Message.getData().getInt("priority", r2_a.do.g);
            b();
            return r0z;
        } else {
            r3_LocationClientOption.b = r1_String;
            j.A = r2_a.do.b;
            r2_a.do.e = r10_Message.getData().getInt("timeOut", r2_a.do.e);
            r2_a.do.h = r10_Message.getData().getBoolean("location_change_notify", r2_a.do.h);
            r2_a.do.g = r10_Message.getData().getInt("priority", r2_a.do.g);
            b();
            return r0z;
        }
    }

    public void if() {
        Iterator r1_Iterator = this.b.iterator();
        while (r1_Iterator.hasNext()) {
            ((a) r1_Iterator.next()).a();
        }
    }

    public void if(Message r5_Message) {
        a r0_a = a(r5_Message.replyTo);
        if (r0_a != null) {
            j.if(a, r0_a.int + " unregistered");
            this.b.remove(r0_a);
        }
        b();
    }

    public void if(String r3_String) {
        Iterator r1_Iterator = this.b.iterator();
        while (r1_Iterator.hasNext()) {
            ((a) r1_Iterator.next()).a(r3_String);
        }
    }

    public void int(Message r3_Message) {
        if (r3_Message == null || r3_Message.replyTo == null) {
            j.if(a, "invalid regist client");
        } else {
            a(new a(r3_Message));
            b();
        }
    }

    public void new() {
        Iterator r1_Iterator = this.b.iterator();
        while (r1_Iterator.hasNext()) {
            ((a) r1_Iterator.next()).if();
        }
    }
}
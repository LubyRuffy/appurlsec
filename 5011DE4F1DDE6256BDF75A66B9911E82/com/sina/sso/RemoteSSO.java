package com.sina.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import qsbk.app.widget.listview.XListViewHeader;

public interface RemoteSSO extends IInterface {

    public static abstract class Stub extends Binder implements RemoteSSO {

        private static class a implements RemoteSSO {
            private IBinder a;

            a(IBinder r1_IBinder) {
                this.a = r1_IBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String getActivityName() throws RemoteException {
                Parcel r1_Parcel = Parcel.obtain();
                Parcel r2_Parcel = Parcel.obtain();
                r1_Parcel.writeInterfaceToken("com.sina.sso.RemoteSSO");
                this.a.transact(XListViewHeader.STATE_REFRESHING, r1_Parcel, r2_Parcel, 0);
                r2_Parcel.readException();
                String r0_String = r2_Parcel.readString();
                r2_Parcel.recycle();
                r1_Parcel.recycle();
                return r0_String;
            }

            public String getInterfaceDescriptor() {
                return "com.sina.sso.RemoteSSO";
            }

            public String getPackageName() throws RemoteException {
                Parcel r1_Parcel = Parcel.obtain();
                Parcel r2_Parcel = Parcel.obtain();
                r1_Parcel.writeInterfaceToken("com.sina.sso.RemoteSSO");
                this.a.transact(1, r1_Parcel, r2_Parcel, 0);
                r2_Parcel.readException();
                String r0_String = r2_Parcel.readString();
                r2_Parcel.recycle();
                r1_Parcel.recycle();
                return r0_String;
            }
        }

        public Stub() {
            attachInterface(this, "com.sina.sso.RemoteSSO");
        }

        public static RemoteSSO asInterface(IBinder r2_IBinder) {
            if (r2_IBinder == null) {
                return null;
            }
            IInterface r0_IInterface = r2_IBinder.queryLocalInterface("com.sina.sso.RemoteSSO");
            return (r0_IInterface == null || (!r0_IInterface instanceof RemoteSSO)) ? new a(r2_IBinder) : (RemoteSSO) r0_IInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int r3i, Parcel r4_Parcel, Parcel r5_Parcel, int r6i) throws RemoteException {
            String r1_String;
            switch (r3i) {
                case XListViewHeader.STATE_READY:
                    r4_Parcel.enforceInterface("com.sina.sso.RemoteSSO");
                    r1_String = getPackageName();
                    r5_Parcel.writeNoException();
                    r5_Parcel.writeString(r1_String);
                    return true;
                case XListViewHeader.STATE_REFRESHING:
                    r4_Parcel.enforceInterface("com.sina.sso.RemoteSSO");
                    r1_String = getActivityName();
                    r5_Parcel.writeNoException();
                    r5_Parcel.writeString(r1_String);
                    return true;
                case 1598968902:
                    r5_Parcel.writeString("com.sina.sso.RemoteSSO");
                    return true;
            }
            return super.onTransact(r3i, r4_Parcel, r5_Parcel, r6i);
        }
    }

    public String getActivityName() throws RemoteException;

    public String getPackageName() throws RemoteException;
}
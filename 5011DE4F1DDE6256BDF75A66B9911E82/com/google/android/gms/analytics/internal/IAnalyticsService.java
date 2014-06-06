package com.google.android.gms.analytics.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;
import qsbk.app.widget.listview.XListViewHeader;

public interface IAnalyticsService extends IInterface {

    public static abstract class Stub extends Binder implements IAnalyticsService {

        private static class a implements IAnalyticsService {
            private IBinder a;

            a(IBinder r1_IBinder) {
                this.a = r1_IBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void clearHits() throws RemoteException {
                Parcel r1_Parcel = Parcel.obtain();
                Parcel r2_Parcel = Parcel.obtain();
                r1_Parcel.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
                this.a.transact(XListViewHeader.STATE_REFRESHING, r1_Parcel, r2_Parcel, 0);
                r2_Parcel.readException();
                r2_Parcel.recycle();
                r1_Parcel.recycle();
            }

            public String getInterfaceDescriptor() {
                return "com.google.android.gms.analytics.internal.IAnalyticsService";
            }

            public void sendHit(Map r6_Map, long r7j, String r9_String, List<Command> r10_List_Command) throws RemoteException {
                Parcel r1_Parcel = Parcel.obtain();
                Parcel r2_Parcel = Parcel.obtain();
                r1_Parcel.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
                r1_Parcel.writeMap(r6_Map);
                r1_Parcel.writeLong(r7j);
                r1_Parcel.writeString(r9_String);
                r1_Parcel.writeTypedList(r10_List_Command);
                this.a.transact(1, r1_Parcel, r2_Parcel, 0);
                r2_Parcel.readException();
                r2_Parcel.recycle();
                r1_Parcel.recycle();
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.gms.analytics.internal.IAnalyticsService");
        }

        public static IAnalyticsService asInterface(IBinder r2_IBinder) {
            if (r2_IBinder == null) {
                return null;
            }
            IInterface r0_IInterface = r2_IBinder.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
            return (r0_IInterface == null || (!r0_IInterface instanceof IAnalyticsService)) ? new a(r2_IBinder) : (IAnalyticsService) r0_IInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int r8i, Parcel r9_Parcel, Parcel r10_Parcel, int r11i) throws RemoteException {
            switch (r8i) {
                case XListViewHeader.STATE_READY:
                    r9_Parcel.enforceInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                    sendHit(r9_Parcel.readHashMap(getClass().getClassLoader()), r9_Parcel.readLong(), r9_Parcel.readString(), r9_Parcel.createTypedArrayList(Command.CREATOR));
                    r10_Parcel.writeNoException();
                    return true;
                case XListViewHeader.STATE_REFRESHING:
                    r9_Parcel.enforceInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                    clearHits();
                    r10_Parcel.writeNoException();
                    return true;
                case 1598968902:
                    r10_Parcel.writeString("com.google.android.gms.analytics.internal.IAnalyticsService");
                    return true;
            }
            return super.onTransact(r8i, r9_Parcel, r10_Parcel, r11i);
        }
    }

    public void clearHits() throws RemoteException;

    public void sendHit(Map r1_Map, long r2j, String r4_String, List<Command> r5_List_Command) throws RemoteException;
}
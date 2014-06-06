package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.tencent.tauth.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.ProfileHeaderListView;

public class LocalBroadcastManager {
    private static final Object f;
    private static LocalBroadcastManager g;
    private final Context a;
    private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> b;
    private final HashMap<String, ArrayList<b>> c;
    private final ArrayList<a> d;
    private final Handler e;

    private static class a {
        final Intent a;
        final ArrayList<b> b;

        a(Intent r1_Intent, ArrayList<b> r2_ArrayList_b) {
            this.a = r1_Intent;
            this.b = r2_ArrayList_b;
        }
    }

    private static class b {
        final IntentFilter a;
        final BroadcastReceiver b;
        boolean c;

        b(IntentFilter r1_IntentFilter, BroadcastReceiver r2_BroadcastReceiver) {
            this.a = r1_IntentFilter;
            this.b = r2_BroadcastReceiver;
        }

        public String toString() {
            StringBuilder r0_StringBuilder = new StringBuilder(128);
            r0_StringBuilder.append("Receiver{");
            r0_StringBuilder.append(this.b);
            r0_StringBuilder.append(" filter=");
            r0_StringBuilder.append(this.a);
            r0_StringBuilder.append("}");
            return r0_StringBuilder.toString();
        }
    }

    static {
        f = new Object();
    }

    private LocalBroadcastManager(Context r3_Context) {
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new ArrayList();
        this.a = r3_Context;
        this.e = new g(this, r3_Context.getMainLooper());
    }

    private void a() {
        while (true) {
            synchronized (this.b) {
                int r0i = this.d.size();
                if (r0i <= 0) {
                    return;
                } else {
                    a[] r4_aA = new a[r0i];
                    this.d.toArray(r4_aA);
                    this.d.clear();
                    int r3i = 0;
                    while (r3i < r4_aA.length) {
                        a r5_a = r4_aA[r3i];
                        int r1i = 0;
                        while (r1i < r5_a.b.size()) {
                            ((b) r5_a.b.get(r1i)).b.onReceive(this.a, r5_a.a);
                            r1i++;
                        }
                        r3i++;
                    }
                }
            }
        }
    }

    public static LocalBroadcastManager getInstance(Context r3_Context) {
        LocalBroadcastManager r0_LocalBroadcastManager;
        synchronized (f) {
            if (g == null) {
                g = new LocalBroadcastManager(r3_Context.getApplicationContext());
            }
            r0_LocalBroadcastManager = g;
        }
        return r0_LocalBroadcastManager;
    }

    public void registerReceiver(BroadcastReceiver r7_BroadcastReceiver, IntentFilter r8_IntentFilter) {
        synchronized (this.b) {
            b r3_b = new b(r8_IntentFilter, r7_BroadcastReceiver);
            ArrayList r0_ArrayList = (ArrayList) this.b.get(r7_BroadcastReceiver);
            if (r0_ArrayList == null) {
                r0_ArrayList = new ArrayList(1);
                this.b.put(r7_BroadcastReceiver, r0_ArrayList);
            }
            r0_ArrayList.add(r8_IntentFilter);
            int r1i = 0;
            while (r1i < r8_IntentFilter.countActions()) {
                String r4_String = r8_IntentFilter.getAction(r1i);
                r0_ArrayList = (ArrayList) this.c.get(r4_String);
                if (r0_ArrayList == null) {
                    r0_ArrayList = new ArrayList(1);
                    this.c.put(r4_String, r0_ArrayList);
                }
                r0_ArrayList.add(r3_b);
                r1i++;
            }
        }
    }

    public boolean sendBroadcast(Intent r17_Intent) {
        boolean r1z;
        synchronized (this.b) {
            int r12i;
            String r2_String = r17_Intent.getAction();
            String r3_String = r17_Intent.resolveTypeIfNeeded(this.a.getContentResolver());
            Uri r5_Uri = r17_Intent.getData();
            String r4_String = r17_Intent.getScheme();
            Set r6_Set = r17_Intent.getCategories();
            r12i = (r17_Intent.getFlags() & 8) != 0 ? 1 : 0;
            if (r12i != 0) {
                Log.v("LocalBroadcastManager", "Resolving type " + r3_String + " scheme " + r4_String + " of intent " + r17_Intent);
            }
            ArrayList r8_ArrayList = (ArrayList) this.c.get(r17_Intent.getAction());
            if (r8_ArrayList != null) {
                if (r12i != 0) {
                    Log.v("LocalBroadcastManager", "Action list: " + r8_ArrayList);
                }
                ArrayList r10_ArrayList = null;
                int r11i = 0;
                while (r11i < r8_ArrayList.size()) {
                    ArrayList r1_ArrayList;
                    b r9_b = (b) r8_ArrayList.get(r11i);
                    if (r12i != 0) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + r9_b.a);
                    }
                    if (r9_b.c) {
                        if (r12i != 0) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                            r1_ArrayList = r10_ArrayList;
                        }
                        r1_ArrayList = r10_ArrayList;
                    } else {
                        int r1i = r9_b.a.match(r2_String, r3_String, r4_String, r5_Uri, r6_Set, "LocalBroadcastManager");
                        if (r1i >= 0) {
                            if (r12i != 0) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(r1i));
                            }
                            r1_ArrayList = r10_ArrayList == null ? new ArrayList() : r10_ArrayList;
                            r1_ArrayList.add(r9_b);
                            r9_b.c = true;
                        } else {
                            if (r12i != 0) {
                                String r1_String;
                                switch (r1i) {
                                    case Constants.ERROR_JSON:
                                        r1_String = "category";
                                        Log.v("LocalBroadcastManager", "  Filter did not match: " + r1_String);
                                        break;
                                    case Constants.ERROR_URL:
                                        r1_String = QsbkDatabase.ACTION;
                                        Log.v("LocalBroadcastManager", "  Filter did not match: " + r1_String);
                                        break;
                                    case RequestListener.DEFAULT_LOADED_SIZE:
                                        r1_String = "data";
                                        Log.v("LocalBroadcastManager", "  Filter did not match: " + r1_String);
                                        break;
                                    case ProfileHeaderListView.INVALID_TAB_ID:
                                        r1_String = QsbkDatabase.TYPE;
                                        Log.v("LocalBroadcastManager", "  Filter did not match: " + r1_String);
                                        break;
                                }
                                r1_String = "unknown reason";
                                Log.v("LocalBroadcastManager", "  Filter did not match: " + r1_String);
                            }
                            r1_ArrayList = r10_ArrayList;
                        }
                    }
                    r11i++;
                    r10_ArrayList = r1_ArrayList;
                }
                if (r10_ArrayList != null) {
                    int r2i = 0;
                    while (r2i < r10_ArrayList.size()) {
                        ((b) r10_ArrayList.get(r2i)).c = false;
                        r2i++;
                    }
                    this.d.add(new a(r17_Intent, r10_ArrayList));
                    if (!this.e.hasMessages(1)) {
                        this.e.sendEmptyMessage(1);
                    }
                    r1z = true;
                }
                r1z = false;
            } else {
                r1z = false;
            }
        }
        return r1z;
    }

    public void sendBroadcastSync(Intent r2_Intent) {
        if (sendBroadcast(r2_Intent)) {
            a();
        }
    }

    public void unregisterReceiver(BroadcastReceiver r11_BroadcastReceiver) {
        synchronized (this.b) {
            ArrayList r0_ArrayList = (ArrayList) this.b.remove(r11_BroadcastReceiver);
            if (r0_ArrayList == null) {
            } else {
                int r7i = 0;
                while (r7i < r0_ArrayList.size()) {
                    IntentFilter r1_IntentFilter = (IntentFilter) r0_ArrayList.get(r7i);
                    int r6i = 0;
                    while (r6i < r1_IntentFilter.countActions()) {
                        String r9_String = r1_IntentFilter.getAction(r6i);
                        ArrayList r2_ArrayList = (ArrayList) this.c.get(r9_String);
                        if (r2_ArrayList != null) {
                            int r4i = 0;
                            while (r4i < r2_ArrayList.size()) {
                                int r3i;
                                if (((b) r2_ArrayList.get(r4i)).b == r11_BroadcastReceiver) {
                                    r2_ArrayList.remove(r4i);
                                    r3i = r4i - 1;
                                } else {
                                    r3i = r4i;
                                }
                                r4i = r3i + 1;
                            }
                            if (r2_ArrayList.size() <= 0) {
                                this.c.remove(r9_String);
                            }
                        }
                        r6i++;
                    }
                    r7i++;
                }
            }
        }
    }
}
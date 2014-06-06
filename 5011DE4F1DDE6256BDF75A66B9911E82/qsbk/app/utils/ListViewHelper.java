package qsbk.app.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.cache.FileCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

public class ListViewHelper {
    public static final long DEFAULT_PAGE_IGNORE_INTERVAL = 900000;
    public static final long DEFAULT_TIPS_TO_REFRESH_INTERVAL = 3600000;
    private static final String a;
    private static Boolean b;

    private static class a implements Task {
        private final List<Object> a;
        private final Context b;
        private final String c;

        public a(List<Object> r1_List_Object, Context r2_Context, String r3_String) {
            this.a = r1_List_Object;
            this.b = r2_Context;
            this.c = r3_String;
        }

        public void fail(Throwable r1_Throwable) {
        }

        public Object proccess() throws QiushibaikeException {
            File r0_File = FileCache.getDiskCacheDir(this.b, this.c);
            if (r0_File.exists()) {
                r0_File.delete();
            }
            FileCache.writeFile(this.b, this.c, CollectionUtils.artilesToJsonString(this.a));
            return null;
        }

        public void success(Object r1_Object) {
        }
    }

    static {
        a = ListViewHelper.class.getName();
        b = null;
    }

    private ListViewHelper() {
    }

    private static String a(ListView r5_ListView, ListAdapter r6_ListAdapter) {
        int r2i = r5_ListView.getFirstVisiblePosition();
        int r3i = r6_ListAdapter.getCount();
        int r0i = r2i - r5_ListView.getHeaderViewsCount();
        if (r0i < 0) {
            r0i = 0;
        }
        if (r3i > r2i) {
            Object r0_Object = r6_ListAdapter.getItem(r0i);
            if (r0_Object instanceof Article) {
                return ((Article) r0_Object).id;
            }
            if (r3i > r2i + 1) {
                r0_Object = r6_ListAdapter.getItem(r2i + 1);
            } else if (r2i - 1 > 0) {
                r0_Object = r6_ListAdapter.getItem(r2i - 1);
            }
            if (r0_Object instanceof Article) {
                return ((Article) r0_Object).id;
            }
        }
        return null;
    }

    private static String a(String r1_String) {
        return a(r1_String, "_last");
    }

    private static String a(String r1_String, String r2_String) {
        return new StringBuffer().append(r1_String).append(r2_String).toString();
    }

    private static void a(int r3i, List<Object> r4_List_Object) {
        int r0i = r4_List_Object.size();
        if (r3i <= 25 || r0i <= 50) {
        } else {
            int r1i = Math.min(r0i - r3i - 1, NearbySelectView.TIME_15MIN);
            r0i--;
            while (r0i > r3i + r1i) {
                r4_List_Object.remove(r0i);
                r0i--;
            }
            r0i = r3i - 30 - r1i;
            while (r0i >= 0) {
                r4_List_Object.remove(r0i);
                r0i--;
            }
        }
    }

    private static String b(String r1_String) {
        return a(r1_String, "_page");
    }

    public static boolean canSelectionSaveable(Context r3_Context) {
        if (b != null) {
            return b.booleanValue();
        }
        boolean r0z = r3_Context.getSharedPreferences("list_state", 0).getBoolean("selection_saveble", true);
        b = Boolean.valueOf(r0z);
        return r0z;
    }

    public static int findIndex(List<Object> r4_List_Object, String r5_String) {
        int r3i = r4_List_Object.size();
        int r1i = 0;
        while (r1i < r3i) {
            if (((Article) r4_List_Object.get(r1i)).id.equals(r5_String)) {
                return r1i;
            }
            r1i++;
        }
        return -1;
    }

    public static ArrayList<Object> getDataFromListAdapter(ListAdapter r5_ListAdapter) {
        int r2i = r5_ListAdapter.getCount();
        ArrayList<Object> r3_ArrayList_Object = new ArrayList(r2i);
        int r1i = 0;
        while (r1i < r2i) {
            Object r0_Object = r5_ListAdapter.getItem(r1i);
            if (r0_Object instanceof Article) {
                r3_ArrayList_Object.add((Article) r0_Object);
            }
            r1i++;
        }
        r3_ArrayList_Object.trimToSize();
        return r3_ArrayList_Object;
    }

    public static String getSaveArticleId(Context r2_Context, String r3_String) {
        return TextUtils.isEmpty(r3_String) ? null : r2_Context.getSharedPreferences("list_state", 0).getString(r3_String, RContactStorage.PRIMARY_KEY);
    }

    public static int getSaveLastPage(Context r3_Context, String r4_String) {
        return Math.max(r3_Context.getSharedPreferences("list_state", 0).getInt(b(r4_String), 0), 0);
    }

    public static int getSaveSelection(Context r5_Context, String r6_String, ArrayList<Object> r7_ArrayList_Object) {
        int r3i = r7_ArrayList_Object.size();
        String r4_String = getSaveArticleId(r5_Context, r6_String);
        if (r4_String != null) {
            int r1i = 0;
            while (r1i < r3i) {
                if (r7_ArrayList_Object.get(r1i) instanceof Article && ((Article) r7_ArrayList_Object.get(r1i)).id.equals(r4_String)) {
                    return r1i;
                }
                r1i++;
            }
        }
        return 0;
    }

    public static boolean isOutSideInterval(Context r6_Context, String r7_String, long r8j) {
        long r1j = r6_Context.getSharedPreferences("list_state", 0).getLong(a(r7_String), 0);
        if (r8j < 0) {
            r8j = DEFAULT_TIPS_TO_REFRESH_INTERVAL;
        }
        return (r1j > 0 ? 1 : (r1j == 0? 0 : -1)) != 0 && Math.abs(System.currentTimeMillis() - r1j) > r8j;
    }

    public static boolean isOutSizeIntervalOfPage(Context r6_Context, String r7_String, long r8j) {
        long r1j = r6_Context.getSharedPreferences("list_state", 0).getLong(a(r7_String), 0);
        if (r8j < 0) {
            r8j = DEFAULT_PAGE_IGNORE_INTERVAL;
        }
        return (r1j > 0 ? 1 : (r1j == 0? 0 : -1)) != 0 && Math.abs(System.currentTimeMillis() - r1j) > r8j;
    }

    public static void onRestoreListViewSelection(Context r2_Context, String r3_String, ArrayList<Object> r4_ArrayList_Object, ListView r5_ListView) {
        int r0i = getSaveSelection(r2_Context, r3_String, r4_ArrayList_Object) + r5_ListView.getHeaderViewsCount();
        if (r0i > 1) {
            r5_ListView.setSelectionFromTop(r0i, -10);
        }
    }

    public static boolean onSaveListAdapterDataIfNeed(Context r5_Context, ListAdapter r6_ListAdapter, String r7_String, String r8_String) {
        if (r6_ListAdapter.getCount() < 30) {
            return false;
        }
        boolean r0z;
        List r2_List = getDataFromListAdapter(r6_ListAdapter);
        int r3i = findIndex(r2_List, r8_String);
        int r4i = r2_List.size();
        saveLastPage(r5_Context, r7_String, r3i % 30 == 0 ? r3i / 30 : r3i / 30 + 1);
        a(r3i, r2_List);
        r0z = r4i > r2_List.size();
        if (!r0z) {
            return r0z;
        }
        TaskExecutor.getInstance().addTask(new a(r2_List, r5_Context, r7_String));
        return r0z;
    }

    public static void onSaveListViewFirstVisibleItem(Context r3_Context, ListView r4_ListView, ListAdapter r5_ListAdapter, String r6_String, boolean r7z) {
        String r0_String = a(r4_ListView, r5_ListAdapter);
        if (!TextUtils.isEmpty(r0_String)) {
            Editor r1_Editor = r3_Context.getSharedPreferences("list_state", 0).edit();
            r1_Editor.putString(r6_String, r0_String);
            r1_Editor.commit();
        }
        if (r7z) {
            onSaveListAdapterDataIfNeed(r3_Context, r5_ListAdapter, r6_String, r0_String);
        }
    }

    public static void saveLastPage(Context r3_Context, String r4_String, int r5i) {
        r3_Context.getSharedPreferences("list_state", 0).edit().putInt(b(r4_String), r5i).commit();
    }

    public static void saveLastUpdateTime(Context r4_Context, String r5_String) {
        r4_Context.getSharedPreferences("list_state", 0).edit().putLong(a(r5_String), System.currentTimeMillis()).commit();
    }

    public static void setSelectionSaveble(Context r2_Context, boolean r3z) {
        b = Boolean.valueOf(r3z);
        Editor r0_Editor = r2_Context.getSharedPreferences("list_state", 0).edit();
        r0_Editor.putBoolean("selection_saveble", r3z);
        r0_Editor.commit();
    }
}
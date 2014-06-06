package qsbk.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.model.Vote;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class QsbkDatabase {
    public static final String A = "a";
    public static final String ACTION = "action";
    public static final String CACHE_ARTICLE = "cache_article";
    public static final String CREATE_AT = "created_at";
    public static final String ICON = "icon";
    public static final String LAST_DEVICE = "last_device";
    public static final String LAST_VISITED_AT = "last_visited_at";
    public static final String LOGIN = "login";
    public static final String ROLE = "role";
    public static final String SESSION = "session";
    public static final String STATE = "state";
    public static final String T = "t";
    public static final String TARGET = "target";
    public static final String TOKEN = "token";
    public static final String TYPE = "type";
    public static final String T_ALL = "t_all";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "user_id";
    public static final String USER_TABLE_NAME = "userInfo";
    public static final String VOTE_TABLE_NAME = "votes";
    private static QsbkDatabase h;
    private byte[] a;
    private Context b;
    private SQLiteDatabase c;
    private a d;
    private final String e;
    private final String f;
    private final String g;

    private class a extends SQLiteOpenHelper {
        public a(Context r5_Context) {
            super(r5_Context, "qiushibaike", null, 5);
        }

        public void onCreate(SQLiteDatabase r2_SQLiteDatabase) {
            r2_SQLiteDatabase.execSQL(" create table cache_article(tid integer primary key,id long,content varchar,anonymous varchar,comment_count long,tag varchar,state varchar,vote_up integer,vote_down integer,create_at long,image varchar,allow_comment varchar,user_id long,published_at long)");
            r2_SQLiteDatabase.execSQL("CREATE TABLE userInfo( tid INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar(20),icon varchar(50),state varchar(2),last_visited_at varchar(50),role varchar(50),created_at varchar(50),login varchar(50),token varchar(50),t_all INTEGER,t INTEGER,a INTEGER,email varchar(50),last_device varchar(50));");
            r2_SQLiteDatabase.execSQL("CREATE TABLE votes( tid INTEGER PRIMARY KEY AUTOINCREMENT, session varchar(50),target varchar(50),action varchar(2),type varchar(2),state varchar(50));");
        }

        public void onUpgrade(SQLiteDatabase r2_SQLiteDatabase, int r3i, int r4i) {
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache_article");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS userInfo");
            r2_SQLiteDatabase.execSQL("DROP TABLE IF EXISTS votes");
            onCreate(r2_SQLiteDatabase);
        }
    }

    private QsbkDatabase(Context r3_Context) {
        this.a = new byte[0];
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = " create table cache_article(tid integer primary key,id long,content varchar,anonymous varchar,comment_count long,tag varchar,state varchar,vote_up integer,vote_down integer,create_at long,image varchar,allow_comment varchar,user_id long,published_at long)";
        this.f = "CREATE TABLE userInfo( tid INTEGER PRIMARY KEY AUTOINCREMENT, user_id varchar(20),icon varchar(50),state varchar(2),last_visited_at varchar(50),role varchar(50),created_at varchar(50),login varchar(50),token varchar(50),t_all INTEGER,t INTEGER,a INTEGER,email varchar(50),last_device varchar(50));";
        this.g = "CREATE TABLE votes( tid INTEGER PRIMARY KEY AUTOINCREMENT, session varchar(50),target varchar(50),action varchar(2),type varchar(2),state varchar(50));";
        this.b = r3_Context;
        this.d = new a(this.b);
    }

    private boolean a() {
        if (this.c != null && this.c.isOpen()) {
            return true;
        }
        this.c = this.d.getWritableDatabase();
        return true;
    }

    public static synchronized QsbkDatabase getInstance() {
        QsbkDatabase r0_QsbkDatabase;
        synchronized (QsbkDatabase.class) {
            if (h == null) {
                h = new QsbkDatabase(QsbkApp.mContext);
            }
            r0_QsbkDatabase = h;
        }
        return r0_QsbkDatabase;
    }

    public void close() {
        if (this.c.isOpen()) {
            this.c.close();
        }
    }

    public void delete(String r7_String, Integer r8_Integer) {
        synchronized (this.a) {
            a();
            SQLiteDatabase r0_SQLiteDatabase = this.c;
            String[] r3_StringA = new String[1];
            r3_StringA[0] = String.valueOf(r8_Integer);
            r0_SQLiteDatabase.delete(r7_String, "tid=? ", r3_StringA);
            close();
        }
    }

    public void deleteUser(Integer r2_Integer) {
        delete(USER_TABLE_NAME, r2_Integer);
    }

    public void deleteVote(Integer r2_Integer) {
        delete(VOTE_TABLE_NAME, r2_Integer);
    }

    public boolean hasArticles(String r10_String, String r11_String) {
        Cursor r0_Cursor = null;
        Boolean r1_Boolean = Boolean.valueOf(false);
        synchronized (this.a) {
            a();
            if (TextUtils.isEmpty(r11_String)) {
                r0_Cursor = this.c.query(r10_String, null, null, null, null, null, null);
                r1_Boolean = Boolean.valueOf(r0_Cursor.moveToFirst());
            }
            r0_Cursor.close();
            close();
        }
        return r1_Boolean.booleanValue();
    }

    public long insert(String r7_String, HashMap<String, String> r8_HashMap_String__String) {
        long r0j;
        ContentValues r4_ContentValues = new ContentValues();
        Iterator r5_Iterator = r8_HashMap_String__String.keySet().iterator();
        while (r5_Iterator.hasNext()) {
            String r0_String = (String) r5_Iterator.next();
            r4_ContentValues.put(r0_String, (String) r8_HashMap_String__String.get(r0_String));
        }
        synchronized (this.a) {
            try {
                a();
                r0j = this.c.insert(r7_String, "tid", r4_ContentValues);
                close();
            } catch (SQLiteException e) {
                close();
                r0j = 0;
            } catch (Throwable th) {
            }
        }
        return r0j;
    }

    public long insertVote(Vote r4_Vote) {
        HashMap r0_HashMap = new HashMap();
        r0_HashMap.put(SESSION, r4_Vote.session);
        r0_HashMap.put(TARGET, r4_Vote.target);
        r0_HashMap.put(TYPE, r4_Vote.type);
        r0_HashMap.put(ACTION, r4_Vote.action);
        r0_HashMap.put(STATE, "0");
        return insert(VOTE_TABLE_NAME, r0_HashMap);
    }

    public boolean isHasVote(String r2_String, String r3_String) {
        return queryVote(r2_String, r3_String) != null;
    }

    public Integer query(String r11_String, String[] r12_StringA, String r13_String, String[] r14_StringA) {
        Integer r0_Integer;
        synchronized (this.a) {
            a();
            Cursor r1_Cursor = this.c.query(r11_String, r12_StringA, r13_String, r14_StringA, null, null, null);
            r1_Cursor.moveToFirst();
            r0_Integer = null;
            while (!r1_Cursor.isAfterLast()) {
                r0_Integer = Integer.valueOf(r1_Cursor.getInt(0));
                r1_Cursor.moveToNext();
            }
            r1_Cursor.close();
            close();
        }
        return r0_Integer;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Integer queryVote(String r12_String, String r13_String) {
        /*
        r11_this = this;
        r8 = 0;
        r3 = "target= ? and type = ? ";
        r0 = 2;
        r4 = new java.lang.String[r0];	 //Catch:{ SQLiteException -> 0x004c }
        r0 = 0;
        r4[r0] = r12;	 //Catch:{ SQLiteException -> 0x004c }
        r0 = 1;
        r4[r0] = r13;	 //Catch:{ SQLiteException -> 0x004c }
        r9 = r11.a;	 //Catch:{ SQLiteException -> 0x004c }
        monitor-enter(r9);	 //Catch:{ SQLiteException -> 0x004c }
        r11.a();	 //Catch:{ all -> 0x0045 }
        r0 = r11.c;	 //Catch:{ all -> 0x0045 }
        r1 = "votes";
        r2 = 1;
        r2 = new java.lang.String[r2];	 //Catch:{ all -> 0x0045 }
        r5 = 0;
        r6 = "tid";
        r2[r5] = r6;	 //Catch:{ all -> 0x0045 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 //Catch:{ all -> 0x0045 }
        r2.moveToFirst();	 //Catch:{ all -> 0x0045 }
        r0 = r8;
    L_0x0029:
        r1 = r2.isAfterLast();	 //Catch:{ all -> 0x004f }
        if (r1 != 0) goto L_0x003d;
    L_0x002f:
        r1 = 0;
        r1 = r2.getInt(r1);	 //Catch:{ all -> 0x004f }
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ all -> 0x004f }
        r2.moveToNext();	 //Catch:{ all -> 0x0054 }
        r0 = r1;
        goto L_0x0029;
    L_0x003d:
        r2.close();	 //Catch:{ all -> 0x004f }
        r11.close();	 //Catch:{ all -> 0x004f }
        monitor-exit(r9);	 //Catch:{ all -> 0x004f }
    L_0x0044:
        return r0;
    L_0x0045:
        r0 = move-exception;
        r1 = r8;
    L_0x0047:
        monitor-exit(r9);	 //Catch:{ all -> 0x0054 }
        throw r0;	 //Catch:{ SQLiteException -> 0x0049 }
    L_0x0049:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0044;
    L_0x004c:
        r0 = move-exception;
        r0 = r8;
        goto L_0x0044;
    L_0x004f:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0047;
    L_0x0054:
        r0 = move-exception;
        goto L_0x0047;
        */

    }

    public HashMap<String, Vote> queryVote() {
        HashMap<String, Vote> r0_HashMap_String__Vote = new HashMap();
        synchronized (this.a) {
            try {
                a();
                Cursor r2_Cursor = this.c.rawQuery("select tid,session,type,target,action from votes order by target limit 0,30 ", null);
                r2_Cursor.moveToFirst();
                while (!r2_Cursor.isAfterLast()) {
                    String r3_String = r2_Cursor.getString(XListViewHeader.STATE_REFRESHING);
                    String r4_String = r2_Cursor.getString(XListViewFooter.STATE_NOMORE);
                    r0_HashMap_String__Vote.put(r4_String + "_" + r3_String, new Vote(r2_Cursor.getString(1), r3_String, r4_String, r2_Cursor.getString(XListViewFooter.STATE_NODATA)));
                    r2_Cursor.moveToNext();
                }
                r2_Cursor.close();
                close();
            } catch (SQLiteException e) {
                close();
            } catch (Throwable th) {
            }
        }
        return r0_HashMap_String__Vote;
    }

    public HashMap<String, Vote> queryWaitSendVote() {
        HashMap<String, Vote> r8_HashMap_String__Vote = new HashMap();
        String r3_String = "state = 0";
        String r7_String = "target limit 0,30 ";
        String[] r2_StringA = new String[5];
        r2_StringA[0] = "tid";
        r2_StringA[1] = SESSION;
        r2_StringA[2] = TYPE;
        r2_StringA[3] = TARGET;
        r2_StringA[4] = ACTION;
        synchronized (this.a) {
            a();
            Cursor r0_Cursor = this.c.query(VOTE_TABLE_NAME, r2_StringA, r3_String, null, null, null, r7_String);
            r0_Cursor.moveToFirst();
            while (!r0_Cursor.isAfterLast()) {
                String r1_String = r0_Cursor.getString(XListViewHeader.STATE_REFRESHING);
                String r2_String = r0_Cursor.getString(XListViewFooter.STATE_NOMORE);
                r8_HashMap_String__Vote.put(r2_String + "_" + r1_String, new Vote(r0_Cursor.getString(1), r1_String, r2_String, r0_Cursor.getString(XListViewFooter.STATE_NODATA)));
                r0_Cursor.moveToNext();
            }
            r0_Cursor.close();
            close();
        }
        return r8_HashMap_String__Vote;
    }

    public void update(String r8_String, HashMap<String, String> r9_HashMap_String__String, Integer r10_Integer) {
        ContentValues r2_ContentValues = new ContentValues();
        Iterator r3_Iterator = r9_HashMap_String__String.keySet().iterator();
        while (r3_Iterator.hasNext()) {
            String r0_String = (String) r3_Iterator.next();
            r2_ContentValues.put(r0_String, (String) r9_HashMap_String__String.get(r0_String));
        }
        synchronized (this.a) {
            try {
                a();
                if (r10_Integer != null) {
                    SQLiteDatabase r0_SQLiteDatabase = this.c;
                    String[] r4_StringA = new String[1];
                    r4_StringA[0] = String.valueOf(r10_Integer);
                    r0_SQLiteDatabase.update(r8_String, r2_ContentValues, "tid=?", r4_StringA);
                } else {
                    this.c.update(r8_String, r2_ContentValues, null, null);
                }
                close();
            } catch (SQLiteException e) {
                close();
            } catch (Throwable th) {
            }
        }
    }

    public void updateVote() {
        HashMap r0_HashMap = new HashMap();
        r0_HashMap.put(STATE, "1");
        h.update(VOTE_TABLE_NAME, r0_HashMap, null);
    }
}
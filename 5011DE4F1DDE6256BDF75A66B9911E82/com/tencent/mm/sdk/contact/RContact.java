package com.tencent.mm.sdk.contact;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.message.api.ChatEngine;

public class RContact extends MAutoDBItem {
    public static final String[] COLUMNS;
    public static final String COL_ALIAS = "alias";
    public static final String COL_CONREMARK = "conRemark";
    public static final String COL_CONREMARK_PYFULL = "conRemarkPYFull";
    public static final String COL_CONREMARK_PYSHORT = "conRemarkPYShort";
    public static final String COL_DOMAINLIST = "domainList";
    public static final int COL_ID_INVALID_VALUE = -1;
    public static final String COL_NICKNAME = "nickname";
    public static final String COL_PYINITIAL = "pyInitial";
    public static final String COL_QUANPIN = "quanPin";
    public static final String COL_SHOWHEAD = "showHead";
    public static final String COL_TYPE = "type";
    public static final String COL_USERNAME = "username";
    public static final String COL_VERIFY_FLAG = "verifyFlag";
    public static final String COL_WEIBOFLAG = "weiboFlag";
    public static final String COL_WEIBONICKNAME = "weiboNickname";
    public static final int DEL_CONTACT_MSG = -1;
    public static final String FAVOUR_CONTACT_SHOW_HEAD_CHAR = "$";
    public static final int FAVOUR_CONTACT_SHOW_HEAD_CODE = 32;
    public static final int MM_CONTACTFLAG_ALL = 127;
    public static final int MM_CONTACTFLAG_BLACKLISTCONTACT = 8;
    public static final int MM_CONTACTFLAG_CHATCONTACT = 2;
    public static final int MM_CONTACTFLAG_CHATROOMCONTACT = 4;
    public static final int MM_CONTACTFLAG_CONTACT = 1;
    public static final int MM_CONTACTFLAG_DOMAINCONTACT = 16;
    public static final int MM_CONTACTFLAG_FAVOURCONTACT = 64;
    public static final int MM_CONTACTFLAG_HIDECONTACT = 32;
    public static final int MM_CONTACTFLAG_NULL = 0;
    public static final int MM_CONTACTIMGFLAG_HAS_HEADIMG = 3;
    public static final int MM_CONTACTIMGFLAG_HAS_NO_HEADIMG = 4;
    public static final int MM_CONTACTIMGFLAG_LOCAL_EXIST = 153;
    public static final int MM_CONTACTIMGFLAG_MODIFY = 2;
    public static final int MM_CONTACTIMGFLAG_NOTMODIFY = 1;
    public static final int MM_CONTACT_BOTTLE = 5;
    public static final int MM_CONTACT_CHATROOM = 2;
    public static final int MM_CONTACT_EMAIL = 3;
    public static final int MM_CONTACT_QQ = 4;
    public static final int MM_CONTACT_QQMICROBLOG = 1;
    public static final int MM_CONTACT_WEIXIN = 0;
    public static final int MM_SEX_FEMALE = 2;
    public static final int MM_SEX_MALE = 1;
    public static final int MM_SEX_UNKNOWN = 0;
    public static final int NOT_IN_CHAT_LIST = 0;
    protected static Field[] a;
    private static Map<String, String> x;
    private static Map<String, String> y;
    private int b;
    private int c;
    public long contactId;
    private String d;
    private long e;
    private int f;
    public String field_alias;
    public String field_conRemark;
    public String field_conRemarkPYFull;
    public String field_conRemarkPYShort;
    public String field_domainList;
    public byte[] field_lvbuff;
    public String field_nickname;
    public String field_pyInitial;
    public String field_quanPin;
    public int field_showHead;
    public int field_type;
    public String field_username;
    public int field_verifyFlag;
    public int field_weiboFlag;
    public String field_weiboNickname;
    private String g;
    private String h;
    private int i;
    private int j;
    private String k;
    private String l;
    private int m;
    private int n;
    private String o;
    private String p;
    private String q;
    private String r;
    private int s;
    private int t;
    private String u;
    private String v;
    private String w;

    static {
        Field[] r0_FieldA = MAutoDBItem.getValidFields(RContact.class);
        a = r0_FieldA;
        COLUMNS = MAutoDBItem.getFullColumns(r0_FieldA);
        x = new HashMap();
        y = new HashMap();
    }

    public RContact() {
        reset();
    }

    public RContact(String r1_String) {
        this();
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        this.field_username = r1_String;
    }

    private static boolean a(char r1c) {
        if (r1c >= 'A' && r1c <= 'Z') {
            return true;
        }
        if (r1c < 'a' || r1c > 'z') {
            return false;
        }
        return true;
    }

    private byte[] a() {
        try {
            LVBuffer r0_LVBuffer = new LVBuffer();
            r0_LVBuffer.initBuild();
            r0_LVBuffer.putInt(this.b);
            r0_LVBuffer.putInt(this.c);
            r0_LVBuffer.putString(this.d);
            r0_LVBuffer.putLong(this.e);
            r0_LVBuffer.putInt(this.f);
            r0_LVBuffer.putString(this.g);
            r0_LVBuffer.putString(this.h);
            r0_LVBuffer.putInt(this.i);
            r0_LVBuffer.putInt(this.j);
            r0_LVBuffer.putString(this.k);
            r0_LVBuffer.putString(this.l);
            r0_LVBuffer.putInt(this.m);
            r0_LVBuffer.putInt(this.n);
            r0_LVBuffer.putString(this.o);
            r0_LVBuffer.putString(this.p);
            r0_LVBuffer.putString(this.q);
            r0_LVBuffer.putString(this.r);
            r0_LVBuffer.putInt(this.s);
            r0_LVBuffer.putInt(this.t);
            r0_LVBuffer.putString(this.u);
            r0_LVBuffer.putInt(this.field_verifyFlag);
            r0_LVBuffer.putString(this.v);
            r0_LVBuffer.putString(this.w);
            return r0_LVBuffer.buildFinish();
        } catch (Exception e) {
            Log.e("MicroMsg.Contact", "get value failed");
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDisplayNick(String r3_String) {
        if (r3_String == null) {
            return null;
        }
        if (r3_String.toLowerCase().endsWith("@t.qq.com")) {
            return new StringBuilder("@").append(r3_String.replace("@t.qq.com", RContactStorage.PRIMARY_KEY)).toString();
        }
        if (r3_String.toLowerCase().endsWith("@qqim")) {
            return r3_String.replace("@qqim", RContactStorage.PRIMARY_KEY);
        }
        return r3_String;
    }

    public static int getBlackListContactBit() {
        return MM_CONTACTFLAG_BLACKLISTCONTACT;
    }

    public static int getContactBit() {
        return MM_SEX_MALE;
    }

    public static int getDomainContactBit() {
        return MM_CONTACTFLAG_DOMAINCONTACT;
    }

    public static int getHiddenContactBit() {
        return MM_CONTACTFLAG_HIDECONTACT;
    }

    public static boolean isContact(int r1i) {
        return (r1i & 1) != 0;
    }

    public static void setHardCodeAliasMaps(Map<String, String> r0_Map_String__String) {
        y = r0_Map_String__String;
    }

    public static void setHardCodeNickMaps(Map<String, String> r0_Map_String__String) {
        x = r0_Map_String__String;
    }

    public int calculateShowHead() {
        char r0c = ' ';
        if (this.field_conRemarkPYShort == null || this.field_conRemarkPYShort.equals(RContactStorage.PRIMARY_KEY)) {
            if (this.field_conRemarkPYFull == null || this.field_conRemarkPYFull.equals(RContactStorage.PRIMARY_KEY)) {
                if (this.field_pyInitial == null || this.field_pyInitial.equals(RContactStorage.PRIMARY_KEY)) {
                    if (this.field_quanPin == null || this.field_quanPin.equals(RContactStorage.PRIMARY_KEY)) {
                        if (this.field_nickname == null || this.field_nickname.equals(RContactStorage.PRIMARY_KEY) || (!a(this.field_nickname.charAt(MM_CONTACT_WEIXIN)))) {
                            if (this.field_username != null) {
                                if (!this.field_username.equals(RContactStorage.PRIMARY_KEY)) {
                                    if (a(this.field_username.charAt(MM_CONTACT_WEIXIN))) {
                                        r0c = this.field_username.charAt(MM_CONTACT_WEIXIN);
                                    }
                                }
                            }
                        } else {
                            r0c = this.field_nickname.charAt(MM_CONTACT_WEIXIN);
                        }
                    } else {
                        r0c = this.field_quanPin.charAt(MM_CONTACT_WEIXIN);
                    }
                } else {
                    r0c = this.field_pyInitial.charAt(MM_CONTACT_WEIXIN);
                }
            } else {
                r0c = this.field_conRemarkPYFull.charAt(MM_CONTACT_WEIXIN);
            }
        } else {
            r0c = this.field_conRemarkPYShort.charAt(MM_CONTACT_WEIXIN);
        }
        if (r0c >= 'a' && r0c <= 'z') {
            return (char) (r0c - 32);
        }
        if (r0c < 'A' || r0c > 'Z') {
            return ChatEngine.HAVE_CONV_MSG;
        }
        return r0c;
    }

    public void convertFrom(Cursor r5_Cursor) {
        super.convertFrom(r5_Cursor);
        this.contactId = (long) ((int) r5_Cursor.getLong(r5_Cursor.getColumnCount() - 1));
        byte[] r0_byteA = this.field_lvbuff;
        try {
            LVBuffer r1_LVBuffer = new LVBuffer();
            int r0i = r1_LVBuffer.initParse(r0_byteA);
            if (r0i != 0) {
                Log.e("MicroMsg.Contact", new StringBuilder("parse LVBuffer error:").append(r0i).toString());
            } else {
                this.b = r1_LVBuffer.getInt();
                this.c = r1_LVBuffer.getInt();
                this.d = r1_LVBuffer.getString();
                this.e = r1_LVBuffer.getLong();
                this.f = r1_LVBuffer.getInt();
                this.g = r1_LVBuffer.getString();
                this.h = r1_LVBuffer.getString();
                this.i = r1_LVBuffer.getInt();
                this.j = r1_LVBuffer.getInt();
                this.k = r1_LVBuffer.getString();
                this.l = r1_LVBuffer.getString();
                this.m = r1_LVBuffer.getInt();
                this.n = r1_LVBuffer.getInt();
                this.o = r1_LVBuffer.getString();
                this.p = r1_LVBuffer.getString();
                this.q = r1_LVBuffer.getString();
                this.r = r1_LVBuffer.getString();
                this.s = r1_LVBuffer.getInt();
                this.t = r1_LVBuffer.getInt();
                this.u = r1_LVBuffer.getString();
                this.field_verifyFlag = r1_LVBuffer.getInt();
                this.v = r1_LVBuffer.getString();
                if (!r1_LVBuffer.checkGetFinish()) {
                    this.w = r1_LVBuffer.getString();
                }
            }
        } catch (Exception e) {
            Log.e("MicroMsg.Contact", "get value failed");
            e.printStackTrace();
        }
    }

    public ContentValues convertTo() {
        this.field_lvbuff = a();
        return super.convertTo();
    }

    public Field[] fields() {
        return a;
    }

    public String getAlias() {
        String r0_String = (String) y.get(this.field_username);
        return r0_String == null ? this.field_alias : r0_String;
    }

    public int getChatroomNotify() {
        return this.m;
    }

    public String getCity() {
        return this.q;
    }

    public String getConQQMBlog() {
        return this.l;
    }

    public String getConRemark() {
        return this.field_conRemark;
    }

    public String getConRemarkPYFull() {
        return this.field_conRemarkPYFull;
    }

    public String getConRemarkPYShort() {
        return this.field_conRemarkPYShort;
    }

    public String getConSMBlog() {
        return this.k;
    }

    public int getConType() {
        return this.j;
    }

    public int getContactID() {
        return (int) this.contactId;
    }

    public String getDisplayNick() {
        String r0_String = (String) x.get(this.field_username);
        if (r0_String != null) {
            return r0_String;
        }
        if (this.field_nickname == null || this.field_nickname.length() <= 0) {
            return getDisplayUser();
        }
        return this.field_nickname;
    }

    public String getDisplayRemark() {
        return (this.field_conRemark == null || this.field_conRemark.trim().equals(RContactStorage.PRIMARY_KEY)) ? getDisplayNick() : this.field_conRemark;
    }

    public String getDisplayUser() {
        String r0_String = getAlias();
        if (!Util.isNullOrNil(r0_String)) {
            return r0_String;
        }
        r0_String = formatDisplayNick(this.field_username);
        return (r0_String == null || r0_String.length() == 0) ? this.field_username : r0_String;
    }

    public String getDistance() {
        return this.r;
    }

    public String getDomainList() {
        return this.field_domainList;
    }

    public String getEmail() {
        return this.g;
    }

    public long getFaceBookId() {
        return this.e;
    }

    public String getFaceBookUsername() {
        return this.d;
    }

    public int getFromType() {
        return this.s;
    }

    public int getImgFlag() {
        return this.b;
    }

    public String getMobile() {
        return this.h;
    }

    public String getNickname() {
        return this.field_nickname;
    }

    public int getPersonalCard() {
        return this.n;
    }

    public String getProvince() {
        return this.p;
    }

    public String getPyInitial() {
        return (this.field_pyInitial == null || this.field_pyInitial.length() < 0) ? getQuanPin() : this.field_pyInitial;
    }

    public String getQuanPin() {
        return (this.field_quanPin == null || this.field_quanPin.length() < 0) ? getNickname() : this.field_quanPin;
    }

    public String getRegionCode() {
        return this.w;
    }

    public int getSex() {
        return this.c;
    }

    public int getShowFlag() {
        return this.i;
    }

    public int getShowHead() {
        return this.field_showHead;
    }

    public String getSignature() {
        return this.o;
    }

    public int getSource() {
        return this.t;
    }

    public int getType() {
        return this.field_type;
    }

    public int getUin() {
        return this.f;
    }

    public String getUsername() {
        return this.field_username;
    }

    public int getVerifyFlag() {
        return this.field_verifyFlag;
    }

    public String getVerifyInfo() {
        return this.v;
    }

    public String getWeibo() {
        return this.u;
    }

    public int getWeiboFlag() {
        return this.field_weiboFlag;
    }

    public String getWeiboNickName() {
        return this.field_weiboNickname;
    }

    public boolean isBlackListContact() {
        return (this.field_type & 8) != 0;
    }

    public boolean isChatContact() {
        return (this.field_type & 2) != 0;
    }

    public boolean isChatRoomContact() {
        return (this.field_type & 4) != 0;
    }

    public boolean isContact() {
        return isContact(this.field_type);
    }

    public boolean isDomainContact() {
        return (this.field_type & 16) != 0;
    }

    public boolean isFavourContact() {
        return (this.field_type & 64) != 0;
    }

    public boolean isHidden() {
        return (this.field_type & 32) != 0;
    }

    public boolean isImgLocalExist() {
        return 153 == this.b;
    }

    public void reset() {
        this.field_username = RContactStorage.PRIMARY_KEY;
        this.field_nickname = RContactStorage.PRIMARY_KEY;
        this.field_pyInitial = RContactStorage.PRIMARY_KEY;
        this.field_quanPin = RContactStorage.PRIMARY_KEY;
        this.field_alias = RContactStorage.PRIMARY_KEY;
        this.field_conRemark = RContactStorage.PRIMARY_KEY;
        this.field_conRemarkPYShort = RContactStorage.PRIMARY_KEY;
        this.field_conRemarkPYFull = RContactStorage.PRIMARY_KEY;
        this.field_domainList = RContactStorage.PRIMARY_KEY;
        this.field_weiboFlag = 0;
        this.field_weiboNickname = RContactStorage.PRIMARY_KEY;
        this.field_showHead = 0;
        this.field_type = 0;
        this.field_verifyFlag = 0;
        this.c = 0;
        this.r = RContactStorage.PRIMARY_KEY;
        this.s = 0;
        this.f = 0;
        this.g = RContactStorage.PRIMARY_KEY;
        this.h = RContactStorage.PRIMARY_KEY;
        this.i = 0;
        this.j = 0;
        this.k = RContactStorage.PRIMARY_KEY;
        this.l = RContactStorage.PRIMARY_KEY;
        this.m = 1;
        this.b = 0;
        this.n = 0;
        this.o = RContactStorage.PRIMARY_KEY;
        this.p = RContactStorage.PRIMARY_KEY;
        this.q = RContactStorage.PRIMARY_KEY;
        this.t = 0;
        this.v = RContactStorage.PRIMARY_KEY;
        this.u = RContactStorage.PRIMARY_KEY;
        this.e = 0;
        this.d = RContactStorage.PRIMARY_KEY;
        this.w = RContactStorage.PRIMARY_KEY;
    }

    public void setAlias(String r1_String) {
        this.field_alias = r1_String;
    }

    public void setBlackList() {
        this.field_type |= 8;
    }

    public void setChatContact() {
        this.field_type |= 2;
    }

    public void setChatroomContact() {
        this.field_type |= 4;
    }

    public void setChatroomNotify(int r1i) {
        this.m = r1i;
    }

    public void setCity(String r1_String) {
        this.q = r1_String;
    }

    public void setConQQMBlog(String r1_String) {
        this.l = r1_String;
    }

    public void setConRemark(String r1_String) {
        this.field_conRemark = r1_String;
    }

    public void setConRemarkPYFull(String r1_String) {
        this.field_conRemarkPYFull = r1_String;
    }

    public void setConRemarkPYShort(String r1_String) {
        this.field_conRemarkPYShort = r1_String;
    }

    public void setConSMBlog(String r1_String) {
        this.k = r1_String;
    }

    public void setConType(int r1i) {
        this.j = r1i;
    }

    public void setContact() {
        this.field_type |= 1;
    }

    public void setDistance(String r1_String) {
        this.r = r1_String;
    }

    public void setDomainList(String r1_String) {
        this.field_domainList = r1_String;
    }

    public void setEmail(String r1_String) {
        this.g = r1_String;
    }

    public void setFaceBookId(long r1j) {
        this.e = r1j;
    }

    public void setFaceBookUsername(String r1_String) {
        this.d = r1_String;
    }

    public void setFavour() {
        this.field_type |= 64;
    }

    public void setFromType(int r1i) {
        this.s = r1i;
    }

    public void setHidden() {
        this.field_type |= 32;
    }

    public void setImgFlag(int r1i) {
        this.b = r1i;
    }

    public void setMobile(String r1_String) {
        this.h = r1_String;
    }

    public void setNickname(String r1_String) {
        this.field_nickname = r1_String;
    }

    public void setNullContact() {
        this.field_type = 0;
    }

    public void setPersonalCard(int r1i) {
        this.n = r1i;
    }

    public void setProvince(String r1_String) {
        this.p = r1_String;
    }

    public void setPyInitial(String r1_String) {
        this.field_pyInitial = r1_String;
    }

    public void setQuanPin(String r1_String) {
        this.field_quanPin = r1_String;
    }

    public void setRegionCode(String r1_String) {
        this.w = r1_String;
    }

    public void setSex(int r1i) {
        this.c = r1i;
    }

    public void setShowFlag(int r1i) {
        this.i = r1i;
    }

    public void setShowHead(int r1i) {
        this.field_showHead = r1i;
    }

    public void setSignature(String r1_String) {
        this.o = r1_String;
    }

    public void setSource(int r1i) {
        this.t = r1i;
    }

    public void setType(int r1i) {
        this.field_type = r1i;
    }

    public void setUin(int r1i) {
        this.f = r1i;
    }

    public void setUsername(String r1_String) {
        this.field_username = r1_String;
    }

    public void setVerifyFlag(int r1i) {
        this.field_verifyFlag = r1i;
    }

    public void setVerifyInfo(String r1_String) {
        this.v = r1_String;
    }

    public void setWeibo(String r1_String) {
        this.u = r1_String;
    }

    public void setWeiboFlag(int r1i) {
        this.field_weiboFlag = r1i;
    }

    public void setWeiboNickName(String r1_String) {
        this.field_weiboNickname = r1_String;
    }

    public void unSetBlackList() {
        this.field_type &= -9;
    }

    public void unSetChatContact() {
        this.field_type &= -3;
    }

    public void unSetContact() {
        this.field_type &= -2;
    }

    public void unSetFavour() {
        this.field_type &= -65;
    }

    public void unSetHidden() {
        this.field_type &= -33;
    }
}
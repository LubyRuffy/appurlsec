package com.tencent.mm.sdk.message;

import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import java.lang.reflect.Field;

public class RMsgInfo extends MAutoDBItem {
    public static final String[] COLUMNS;
    public static final String COL_CONTENT = "content";
    public static final String COL_CREATE_TIME = "createTime";
    public static final String COL_ID = "msgId";
    public static final String COL_ID_SVR = "msgSvrId";
    public static final String COL_IMG_PATH = "imgPath";
    public static final String COL_IS_SEND = "isSend";
    public static final String COL_IS_SHOWTIMER = "isShowTimer";
    public static final String COL_LVBUFFER = "lvbuffer";
    public static final String COL_RESERVED = "reserved";
    public static final String COL_STATUS = "status";
    public static final String COL_TALKER = "talker";
    public static final String COL_TYPE = "type";
    protected static Field[] a;
    public String commentUrl;
    public String field_content;
    public long field_createTime;
    public String field_imgPath;
    public int field_isSend;
    public int field_isShowTimer;
    public byte[] field_lvBuffer;
    public long field_msgId;
    public int field_msgSvrId;
    public String field_reserved;
    public int field_status;
    public String field_talker;
    public int field_type;

    static {
        Field[] r0_FieldA = MAutoDBItem.getValidFields(RMsgInfo.class);
        a = r0_FieldA;
        COLUMNS = MAutoDBItem.getFullColumns(r0_FieldA);
    }

    public RMsgInfo() {
        this.field_type = 0;
        this.field_status = 0;
        this.field_talker = RContactStorage.PRIMARY_KEY;
        this.field_content = RContactStorage.PRIMARY_KEY;
        this.field_imgPath = RContactStorage.PRIMARY_KEY;
        this.field_reserved = RContactStorage.PRIMARY_KEY;
        this.commentUrl = RContactStorage.PRIMARY_KEY;
    }

    public RMsgInfo(long r2j) {
        this.field_type = 0;
        this.field_status = 0;
        this.field_talker = RContactStorage.PRIMARY_KEY;
        this.field_content = RContactStorage.PRIMARY_KEY;
        this.field_imgPath = RContactStorage.PRIMARY_KEY;
        this.field_reserved = RContactStorage.PRIMARY_KEY;
        this.commentUrl = RContactStorage.PRIMARY_KEY;
        this.field_msgId = r2j;
    }

    public RMsgInfo(String r2_String) {
        this.field_type = 0;
        this.field_status = 0;
        this.field_talker = RContactStorage.PRIMARY_KEY;
        this.field_content = RContactStorage.PRIMARY_KEY;
        this.field_imgPath = RContactStorage.PRIMARY_KEY;
        this.field_reserved = RContactStorage.PRIMARY_KEY;
        this.commentUrl = RContactStorage.PRIMARY_KEY;
        this.field_talker = r2_String;
    }

    public Field[] fields() {
        return a;
    }
}
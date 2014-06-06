package com.tencent.mm.sdk.conversation;

import com.tencent.mm.sdk.storage.MAutoDBItem;
import java.lang.reflect.Field;

public class RConversation extends MAutoDBItem {
    public static final String[] COLUMNS;
    public static final String COL_CHATMODE = "chatmode";
    public static final String COL_CONTENT = "content";
    public static final String COL_CONVERSATION_TIME = "conversationTime";
    public static final String COL_FLAG = "flag";
    public static final String COL_IS_SEND = "isSend";
    public static final String COL_MSGTYPE = "msgType";
    public static final String COL_STATUS = "status";
    public static final String COL_UNREAD_COUNT = "unReadCount";
    public static final int COL_UNREAD_COUNT_INVALID_VALUE = -1;
    public static final String COL_USERNAME = "username";
    public static final int FLAG_NORMAL = 0;
    public static final int HAS_SHOWN_TIPS = 1;
    public static final int INDEX_CONTENT = 5;
    public static final int INDEX_CONVERSATION_TIME = 3;
    public static final int INDEX_FLAG = 7;
    public static final int INDEX_IS_SEND = 2;
    public static final int INDEX_MSGTYPE = 6;
    public static final int INDEX_STATUE = 1;
    public static final int INDEX_UNREAD_COUNT = 0;
    public static final int INDEX_USERNAME = 4;
    public static final long MAX_TIME = 9223372036854775807L;
    public static final int NOT_SHOWN_TIPS = 0;
    public static final String OLD_BOTTLE_TABLE = "bottleconversation";
    public static final String OLD_TABLE = "conversation";
    public static final int ROOM_MUTE_OFF = 1;
    public static final int ROOM_MUTE_ON = 0;
    protected static Field[] a;
    public int field_chatmode;
    public String field_content;
    public long field_conversationTime;
    public String field_customNotify;
    public long field_flag;
    public int field_isSend;
    public String field_msgType;
    public int field_showTips;
    public int field_status;
    public int field_unReadCount;
    public String field_username;

    static {
        Field[] r0_FieldA = MAutoDBItem.getValidFields(RConversation.class);
        a = r0_FieldA;
        COLUMNS = MAutoDBItem.getFullColumns(r0_FieldA);
    }

    public RConversation(String r1_String) {
        this.field_username = r1_String;
    }

    public static Field[] getFields() {
        return a;
    }

    public Field[] fields() {
        return a;
    }

    public int getChatmode() {
        return this.field_chatmode;
    }

    public String getContent() {
        return this.field_content;
    }

    public long getConversationTime() {
        return this.field_conversationTime;
    }

    public String getCustomNotify() {
        return this.field_customNotify;
    }

    public long getFlag() {
        return this.field_flag;
    }

    public int getIsSend() {
        return this.field_isSend;
    }

    public String getMsgType() {
        return this.field_msgType;
    }

    public int getShowTips() {
        return this.field_showTips;
    }

    public int getStatus() {
        return this.field_status;
    }

    public int getUnReadCount() {
        return this.field_unReadCount;
    }

    public String getUsername() {
        return this.field_username;
    }

    public void setChatmode(int r1i) {
        this.field_chatmode = r1i;
    }

    public void setContent(String r1_String) {
        this.field_content = r1_String;
    }

    public void setConversationTime(long r1j) {
        this.field_conversationTime = r1j;
    }

    public void setCustomNotify(String r1_String) {
        this.field_customNotify = r1_String;
    }

    public void setFlag(long r1j) {
        this.field_flag = r1j;
    }

    public void setIsSend(int r1i) {
        this.field_isSend = r1i;
    }

    public void setMsgType(String r1_String) {
        this.field_msgType = r1_String;
    }

    public void setShowTips(int r1i) {
        this.field_showTips = r1i;
    }

    public void setStatus(int r1i) {
        this.field_status = r1i;
    }

    public void setUnReadCount(int r1i) {
        this.field_unReadCount = r1i;
    }

    public void setUsername(String r1_String) {
        this.field_username = r1_String;
    }
}
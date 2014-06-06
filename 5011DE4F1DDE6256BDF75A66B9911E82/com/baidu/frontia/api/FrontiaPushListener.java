package com.baidu.frontia.api;

import com.baidu.frontia.api.FrontiaPushUtil.MessageContent;
import com.baidu.frontia.api.FrontiaPushUtil.Trigger;
import java.util.List;
import org.json.JSONObject;

public interface FrontiaPushListener {

    public static interface CommonMessageListener {
        public void onFailure(int r1i, String r2_String);

        public void onSuccess();
    }

    public static interface DescribeMessageListener {
        public void onFailure(int r1i, String r2_String);

        public void onSuccess(com.baidu.frontia.api.FrontiaPushListener.DescribeMessageResult r1_com_baidu_frontia_api_FrontiaPushListener_DescribeMessageResult);
    }

    public static class DescribeMessageResult {
        private com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageResult a;

        DescribeMessageResult(com.baidu.frontia.module.push.FrontiaPushListenerImpl.DescribeMessageResult r1_com_baidu_frontia_module_push_FrontiaPushListenerImpl_DescribeMessageResult) {
            this.a = r1_com_baidu_frontia_module_push_FrontiaPushListenerImpl_DescribeMessageResult;
        }

        public String getChannelId() {
            return this.a.getChannelId();
        }

        public JSONObject getExtras() {
            return this.a.getExtras();
        }

        public String getId() {
            return this.a.getId();
        }

        public MessageContent getMessage() {
            return new MessageContent(this.a.getMessage());
        }

        public String getTag() {
            return this.a.getTag();
        }

        public Trigger getTrigger() {
            return new Trigger(this.a.getTrigger());
        }

        public String getUserId() {
            return this.a.getUserId();
        }
    }

    public static interface ListMessageListener {
        public void onFailure(int r1i, String r2_String);

        public void onSuccess(List<com.baidu.frontia.api.FrontiaPushListener.DescribeMessageResult> r1_List_com_baidu_frontia_api_FrontiaPushListener_DescribeMessageResult);
    }

    public static interface PushMessageListener {
        public void onFailure(int r1i, String r2_String);

        public void onSuccess(String r1_String);
    }
}
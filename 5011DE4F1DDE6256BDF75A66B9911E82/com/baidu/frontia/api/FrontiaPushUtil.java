package com.baidu.frontia.api;

import android.net.Uri;
import com.baidu.android.pushservice.internal.BasicPushNotificationBuilder;
import com.baidu.android.pushservice.internal.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.internal.PushNotificationBuilder;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.AndroidContentImpl;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.DefaultNotificationStyleImpl;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.IOSContentImpl;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.MessageContentImpl;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.NotificationContentImpl;
import com.baidu.frontia.module.push.FrontiaPushUtilImpl.TriggerImpl;
import org.json.JSONObject;

public class FrontiaPushUtil {

    public static class AndroidContent {
        private AndroidContentImpl a;

        public AndroidContent() {
            this.a = new AndroidContentImpl();
        }

        AndroidContent(AndroidContentImpl r1_AndroidContentImpl) {
            this.a = r1_AndroidContentImpl;
        }

        AndroidContentImpl a() {
            return this.a;
        }

        public int getBuilderId() {
            return this.a.getBuilderId();
        }

        public com.baidu.frontia.api.FrontiaPushUtil.DefaultNotificationStyle getNotificationStyle() {
            return new com.baidu.frontia.api.FrontiaPushUtil.DefaultNotificationStyle(this.a.getNotificationStyle());
        }

        public String getPKGContent() {
            return this.a.getPKGContent();
        }

        public String getUrl() {
            return this.a.getUrl();
        }

        public void setBuilderId(int r2i) {
            this.a.setBuilderId(r2i);
        }

        public void setNotificationStyle(com.baidu.frontia.api.FrontiaPushUtil.DefaultNotificationStyle r3_com_baidu_frontia_api_FrontiaPushUtil_DefaultNotificationStyle) {
            this.a.setNotificationStyle(r3_com_baidu_frontia_api_FrontiaPushUtil_DefaultNotificationStyle.a());
        }

        public void setPKGContent(String r2_String) {
            this.a.setPKGContent(r2_String);
        }

        public void setUrl(String r2_String) {
            this.a.setUrl(r2_String);
        }
    }

    public static class DefaultNotificationStyle {
        private DefaultNotificationStyleImpl a;

        public DefaultNotificationStyle() {
            this.a = new DefaultNotificationStyleImpl();
        }

        DefaultNotificationStyle(DefaultNotificationStyleImpl r1_DefaultNotificationStyleImpl) {
            this.a = r1_DefaultNotificationStyleImpl;
        }

        DefaultNotificationStyleImpl a() {
            return this.a;
        }

        public void disableAlert() {
            this.a.disableAlert();
        }

        public void disableDismissible() {
            this.a.disableDismissible();
        }

        public void disableVibration() {
            this.a.disableVibration();
        }

        public void enableAlert() {
            this.a.enableAlert();
        }

        public void enableDismissible() {
            this.a.enableDismissible();
        }

        public void enableVibration() {
            this.a.enableVibration();
        }

        public boolean isAlertEnabled() {
            return this.a.isAlertEnabled();
        }

        public boolean isDismissible() {
            return this.a.isDismissible();
        }

        public boolean isVibrationEnabled() {
            return this.a.isVibrationEnabled();
        }
    }

    public static enum DeployStatus {
        DEVELOPE,
        PRODUCTION;


        static {
            DEVELOPE = new com.baidu.frontia.api.FrontiaPushUtil.DeployStatus("DEVELOPE", 0);
            PRODUCTION = new com.baidu.frontia.api.FrontiaPushUtil.DeployStatus("PRODUCTION", 1);
            com.baidu.frontia.api.FrontiaPushUtil.DeployStatus[] r0_com_baidu_frontia_api_FrontiaPushUtil_DeployStatusA = new com.baidu.frontia.api.FrontiaPushUtil.DeployStatus[2];
            r0_com_baidu_frontia_api_FrontiaPushUtil_DeployStatusA[0] = DEVELOPE;
            r0_com_baidu_frontia_api_FrontiaPushUtil_DeployStatusA[1] = PRODUCTION;
            a = r0_com_baidu_frontia_api_FrontiaPushUtil_DeployStatusA;
        }
    }

    static abstract class a {
        a() {
        }

        abstract PushNotificationBuilder a_();

        public abstract void setNotificationDefaults(int r1i);

        public abstract void setNotificationFlags(int r1i);

        public abstract void setNotificationSound(Uri r1_Uri);

        public abstract void setNotificationText(String r1_String);

        public abstract void setNotificationTitle(String r1_String);

        public abstract void setNotificationVibrate(long[] r1_longA);

        public abstract void setStatusbarIcon(int r1i);
    }

    public static class FrontiaPushBasicNotificationBuilder extends a {
        private BasicPushNotificationBuilder a;

        public FrontiaPushBasicNotificationBuilder() {
            this.a = new BasicPushNotificationBuilder();
        }

        PushNotificationBuilder a() {
            return this.a;
        }

        public void setNotificationDefaults(int r2i) {
            this.a.setNotificationDefaults(r2i);
        }

        public void setNotificationFlags(int r2i) {
            this.a.setNotificationFlags(r2i);
        }

        public void setNotificationSound(Uri r2_Uri) {
            this.a.setNotificationSound(r2_Uri);
        }

        public void setNotificationText(String r2_String) {
            this.a.setNotificationText(r2_String);
        }

        public void setNotificationTitle(String r2_String) {
            this.a.setNotificationTitle(r2_String);
        }

        public void setNotificationVibrate(long[] r2_longA) {
            this.a.setNotificationVibrate(r2_longA);
        }

        public void setStatusbarIcon(int r2i) {
            this.a.setStatusbarIcon(r2i);
        }
    }

    public static class FrontiaPushCustomNotificationBuilder extends a {
        private CustomPushNotificationBuilder a;

        public FrontiaPushCustomNotificationBuilder(int r2i, int r3i, int r4i, int r5i) {
            this.a = null;
            this.a = new CustomPushNotificationBuilder(r2i, r3i, r4i, r5i);
        }

        PushNotificationBuilder a() {
            return this.a;
        }

        public void setNotificationDefaults(int r2i) {
            this.a.setNotificationDefaults(r2i);
        }

        public void setNotificationFlags(int r2i) {
            this.a.setNotificationFlags(r2i);
        }

        public void setNotificationSound(Uri r2_Uri) {
            this.a.setNotificationSound(r2_Uri);
        }

        public void setNotificationText(String r2_String) {
            this.a.setNotificationText(r2_String);
        }

        public void setNotificationTitle(String r2_String) {
            this.a.setNotificationTitle(r2_String);
        }

        public void setNotificationVibrate(long[] r2_longA) {
            this.a.setNotificationVibrate(r2_longA);
        }

        public void setStatusbarIcon(int r2i) {
            this.a.setStatusbarIcon(r2i);
        }
    }

    public static class IOSContent {
        private IOSContentImpl a;

        public IOSContent() {
            this.a = new IOSContentImpl();
        }

        IOSContent(IOSContentImpl r1_IOSContentImpl) {
            this.a = r1_IOSContentImpl;
        }

        IOSContentImpl a() {
            return this.a;
        }

        public String getAlertMsg() {
            return this.a.getAlertMsg();
        }

        public int getBadge() {
            return this.a.getBadge();
        }

        public String getSoundFile() {
            return this.a.getSoundFile();
        }

        public void setAlertMsg(String r2_String) {
            this.a.setAlertMsg(r2_String);
        }

        public void setBadge(int r2i) {
            this.a.setBadge(r2i);
        }

        public void setSoundFile(String r2_String) {
            this.a.setSoundFile(r2_String);
        }
    }

    public static class MessageContent {
        private MessageContentImpl a;
        private int b;

        MessageContent(MessageContentImpl r1_MessageContentImpl) {
            this.a = r1_MessageContentImpl;
        }

        public MessageContent(String r3_String, com.baidu.frontia.api.FrontiaPushUtil.DeployStatus r4_com_baidu_frontia_api_FrontiaPushUtil_DeployStatus) {
            if (r4_com_baidu_frontia_api_FrontiaPushUtil_DeployStatus == com.baidu.frontia.api.FrontiaPushUtil.DeployStatus.DEVELOPE) {
                this.b = 1;
            } else {
                this.b = 2;
            }
            this.a = new MessageContentImpl(r3_String, this.b);
        }

        public static com.baidu.frontia.api.FrontiaPushUtil.MessageContent createNotificationMessage(String r2_String, String r3_String, String r4_String) {
            return new com.baidu.frontia.api.FrontiaPushUtil.MessageContent(MessageContentImpl.createNotificationMessage(r2_String, r3_String, r4_String));
        }

        public static com.baidu.frontia.api.FrontiaPushUtil.MessageContent createStringMessage(String r2_String, String r3_String) {
            return new com.baidu.frontia.api.FrontiaPushUtil.MessageContent(MessageContentImpl.createStringMessage(r2_String, r3_String));
        }

        MessageContentImpl a() {
            return this.a;
        }

        public com.baidu.frontia.api.FrontiaPushUtil.DeployStatus getDeployStatus() {
            return this.a.getDeployStatus() == 1 ? com.baidu.frontia.api.FrontiaPushUtil.DeployStatus.DEVELOPE : com.baidu.frontia.api.FrontiaPushUtil.DeployStatus.PRODUCTION;
        }

        public String getMessage() {
            return this.a.getMesssage();
        }

        public String getMessageKeys() {
            return this.a.getMessageKeys();
        }

        public com.baidu.frontia.api.FrontiaPushUtil.NotificationContent getNotification() {
            return new com.baidu.frontia.api.FrontiaPushUtil.NotificationContent(this.a.getNotificationContent());
        }

        public void setMessage(String r2_String) {
            this.a.setMessage(r2_String);
        }

        public void setNotification(com.baidu.frontia.api.FrontiaPushUtil.NotificationContent r3_com_baidu_frontia_api_FrontiaPushUtil_NotificationContent) {
            this.a.setNotification(r3_com_baidu_frontia_api_FrontiaPushUtil_NotificationContent.a());
        }
    }

    public static class NotificationContent {
        private NotificationContentImpl a;

        NotificationContent(NotificationContentImpl r1_NotificationContentImpl) {
            this.a = r1_NotificationContentImpl;
        }

        public NotificationContent(String r2_String, String r3_String) {
            this.a = new NotificationContentImpl(r2_String, r3_String);
        }

        NotificationContentImpl a() {
            return this.a;
        }

        public void addAndroidContent(com.baidu.frontia.api.FrontiaPushUtil.AndroidContent r3_com_baidu_frontia_api_FrontiaPushUtil_AndroidContent) {
            this.a.addAndroidContent(r3_com_baidu_frontia_api_FrontiaPushUtil_AndroidContent.a());
        }

        public void addCustomContent(String r2_String, String r3_String) {
            this.a.addCustomContent(r2_String, r3_String);
        }

        public void addIOSContent(com.baidu.frontia.api.FrontiaPushUtil.IOSContent r3_com_baidu_frontia_api_FrontiaPushUtil_IOSContent) {
            this.a.addIOSContent(r3_com_baidu_frontia_api_FrontiaPushUtil_IOSContent.a());
        }

        public com.baidu.frontia.api.FrontiaPushUtil.AndroidContent getAndroidContent() {
            return new com.baidu.frontia.api.FrontiaPushUtil.AndroidContent(this.a.getAndroidContent());
        }

        public JSONObject getCustomContent() {
            return this.a.getCustomContent();
        }

        public String getDescription() {
            return this.a.getDescription();
        }

        public com.baidu.frontia.api.FrontiaPushUtil.IOSContent getIOSContent() {
            return new com.baidu.frontia.api.FrontiaPushUtil.IOSContent(this.a.getIOSContent());
        }

        public String getTitle() {
            return this.a.getTitle();
        }
    }

    public static class Trigger {
        private TriggerImpl a;

        public Trigger() {
            this.a = new TriggerImpl();
        }

        Trigger(TriggerImpl r1_TriggerImpl) {
            this.a = r1_TriggerImpl;
        }

        TriggerImpl a() {
            return this.a;
        }

        public String getCrontab() {
            return this.a.getCrontab();
        }

        public String getTime() {
            return this.a.getTime();
        }

        public void setCrontab(String r2_String) {
            this.a.setCrontab(r2_String);
        }

        public void setTime(String r2_String) {
            this.a.setTime(r2_String);
        }
    }
}
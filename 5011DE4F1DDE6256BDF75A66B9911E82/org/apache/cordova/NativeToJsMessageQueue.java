package org.apache.cordova;

import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class NativeToJsMessageQueue {
    private static final int DEFAULT_BRIDGE_MODE = 2;
    static final boolean DISABLE_EXEC_CHAINING = false;
    static final boolean ENABLE_LOCATION_CHANGE_EXEC_MODE = false;
    private static final boolean FORCE_ENCODE_USING_EVAL = false;
    private static final String LOG_TAG = "JsMessageQueue";
    private static int MAX_PAYLOAD_SIZE;
    private int activeListenerIndex;
    private final CordovaInterface cordova;
    private boolean paused;
    private final LinkedList<JsMessage> queue;
    private final BridgeMode[] registeredListeners;
    private final CordovaWebView webView;


    private static interface BridgeMode {
        public void onNativeToJsMessageAvailable();
    }

    private static class JsMessage {
        final String jsPayloadOrCallbackId;
        final PluginResult pluginResult;

        JsMessage(String r2_String) {
            if (r2_String == null) {
                throw new NullPointerException();
            } else {
                this.jsPayloadOrCallbackId = r2_String;
                this.pluginResult = null;
            }
        }

        JsMessage(PluginResult r2_PluginResult, String r3_String) {
            if (r3_String == null || r2_PluginResult == null) {
                throw new NullPointerException();
            } else {
                this.jsPayloadOrCallbackId = r3_String;
                this.pluginResult = r2_PluginResult;
            }
        }

        int calculateEncodedLength() {
            if (this.pluginResult == null) {
                return this.jsPayloadOrCallbackId.length() + 1;
            }
            int r0i = String.valueOf(this.pluginResult.getStatus()).length() + 2 + 1 + this.jsPayloadOrCallbackId.length() + 1;
            switch (this.pluginResult.getMessageType()) {
                case XListViewHeader.STATE_READY:
                    return r0i + this.pluginResult.getStrMessage().length() + 1;
                case XListViewFooter.STATE_NOMORE:
                    return r0i + this.pluginResult.getMessage().length() + 1;
                case XListViewFooter.STATE_NODATA:
                case ShareUtils.SHARE_SMS:
                    return r0i + 1;
                case ShareUtils.SHARE_COPY:
                    return r0i + this.pluginResult.getMessage().length() + 1;
            }
            return r0i + this.pluginResult.getMessage().length();
        }

        void encodeAsJsMessage(StringBuilder r5_StringBuilder) {
            if (this.pluginResult == null) {
                r5_StringBuilder.append(this.jsPayloadOrCallbackId);
            } else {
                int r1i = this.pluginResult.getStatus();
                boolean r0z;
                if (r1i == Status.OK.ordinal() || r1i == Status.NO_RESULT.ordinal()) {
                    r0z = true;
                    r5_StringBuilder.append("cordova.callbackFromNative('").append(this.jsPayloadOrCallbackId).append("',").append(r0z).append(",").append(r1i).append(",").append(this.pluginResult.getMessage()).append(",").append(this.pluginResult.getKeepCallback()).append(");");
                } else {
                    r0z = FORCE_ENCODE_USING_EVAL;
                    r5_StringBuilder.append("cordova.callbackFromNative('").append(this.jsPayloadOrCallbackId).append("',").append(r0z).append(",").append(r1i).append(",").append(this.pluginResult.getMessage()).append(",").append(this.pluginResult.getKeepCallback()).append(");");
                }
            }
        }

        void encodeAsMessage(StringBuilder r7_StringBuilder) {
            int r1i = 1;
            if (this.pluginResult == null) {
                r7_StringBuilder.append('J').append(this.jsPayloadOrCallbackId);
            } else {
                int r0i;
                int r3i = this.pluginResult.getStatus();
                r0i = r3i == Status.NO_RESULT.ordinal() ? 1 : 0;
                char r0c;
                if (r3i == Status.OK.ordinal()) {
                    r4z = this.pluginResult.getKeepCallback();
                    if (r0i == 0 && r1i == 0) {
                        r0c = 'F';
                    } else {
                        r0c = 'S';
                    }
                    r7_StringBuilder.append(r0c).append(r4z ? '0' : '1').append(r3i).append(' ').append(this.jsPayloadOrCallbackId).append(' ');
                    switch (this.pluginResult.getMessageType()) {
                        case XListViewHeader.STATE_READY:
                            r7_StringBuilder.append('s');
                            r7_StringBuilder.append(this.pluginResult.getStrMessage());
                            return;
                        case XListViewFooter.STATE_NOMORE:
                            r7_StringBuilder.append('n').append(this.pluginResult.getMessage());
                            return;
                        case XListViewFooter.STATE_NODATA:
                            r7_StringBuilder.append(this.pluginResult.getMessage().charAt(0));
                            return;
                        case ShareUtils.SHARE_SMS:
                            r7_StringBuilder.append('N');
                            return;
                        case ShareUtils.SHARE_COPY:
                            r7_StringBuilder.append('A');
                            r7_StringBuilder.append(this.pluginResult.getMessage());
                            return;
                    }
                    r7_StringBuilder.append(this.pluginResult.getMessage());
                } else {
                    r1i = 0;
                    r4z = this.pluginResult.getKeepCallback();
                    if (r0i == 0 || r1i == 0) {
                        r0c = 'S';
                    } else {
                        r0c = 'F';
                    }
                    if (r4z) {
                    }
                    r7_StringBuilder.append(r0c).append(r4z ? '0' : '1').append(r3i).append(' ').append(this.jsPayloadOrCallbackId).append(' ');
                    switch (this.pluginResult.getMessageType()) {
                        case XListViewHeader.STATE_READY:
                            r7_StringBuilder.append('s');
                            r7_StringBuilder.append(this.pluginResult.getStrMessage());
                            return;
                        case XListViewFooter.STATE_NOMORE:
                            r7_StringBuilder.append('n').append(this.pluginResult.getMessage());
                            return;
                        case XListViewFooter.STATE_NODATA:
                            r7_StringBuilder.append(this.pluginResult.getMessage().charAt(0));
                            return;
                        case ShareUtils.SHARE_SMS:
                            r7_StringBuilder.append('N');
                            return;
                        case ShareUtils.SHARE_COPY:
                            r7_StringBuilder.append('A');
                            r7_StringBuilder.append(this.pluginResult.getMessage());
                            return;
                    }
                    r7_StringBuilder.append(this.pluginResult.getMessage());
                }
            }
        }
    }

    private class LoadUrlBridgeMode implements BridgeMode {
        final Runnable runnable;

        private LoadUrlBridgeMode() {
            this.runnable = new Runnable() {
                public void run() {
                    String r0_String = LoadUrlBridgeMode.this.this$0.popAndEncodeAsJs();
                    if (r0_String != null) {
                        LoadUrlBridgeMode.this.this$0.webView.loadUrlNow("javascript:" + r0_String);
                    }
                }
            };
        }

        public void onNativeToJsMessageAvailable() {
            NativeToJsMessageQueue.this.cordova.getActivity().runOnUiThread(this.runnable);
        }
    }

    private class OnlineEventsBridgeMode implements BridgeMode {
        boolean online;
        final Runnable runnable;

        OnlineEventsBridgeMode() {
            this.online = true;
            this.runnable = new Runnable() {
                public void run() {
                    if (!OnlineEventsBridgeMode.this.this$0.queue.isEmpty()) {
                        OnlineEventsBridgeMode.this.online = OnlineEventsBridgeMode.this.online ? FORCE_ENCODE_USING_EVAL : true;
                        OnlineEventsBridgeMode.this.this$0.webView.setNetworkAvailable(OnlineEventsBridgeMode.this.online);
                    }
                }
            };
            NativeToJsMessageQueue.this.webView.setNetworkAvailable(true);
        }

        public void onNativeToJsMessageAvailable() {
            NativeToJsMessageQueue.this.cordova.getActivity().runOnUiThread(this.runnable);
        }
    }

    private class PrivateApiBridgeMode implements BridgeMode {
        private static final int EXECUTE_JS = 194;
        boolean initFailed;
        Method sendMessageMethod;
        Object webViewCore;

        private PrivateApiBridgeMode() {
        }

        private void initReflection() {
            CordovaWebView r1_CordovaWebView;
            Class r0_Class;
            Class r1_Class = WebView.class;
            try {
                Field r2_Field = r1_Class.getDeclaredField("mProvider");
                r2_Field.setAccessible(true);
                CordovaWebView r0_CordovaWebView = r2_Field.get(NativeToJsMessageQueue.this.webView);
                r1_CordovaWebView = r0_CordovaWebView;
                r0_Class = r0_CordovaWebView.getClass();
            } catch (Throwable th) {
                r1_CordovaWebView = NativeToJsMessageQueue.this.webView;
                r0_Class = r1_Class;
            }
            try {
                Field r0_Field = r0_Class.getDeclaredField("mWebViewCore");
                r0_Field.setAccessible(true);
                this.webViewCore = r0_Field.get(r1_CordovaWebView);
                if (this.webViewCore != null) {
                    r0_Class = this.webViewCore.getClass();
                    Class[] r2_ClassA = new Class[1];
                    r2_ClassA[0] = Message.class;
                    this.sendMessageMethod = r0_Class.getDeclaredMethod("sendMessage", r2_ClassA);
                    this.sendMessageMethod.setAccessible(true);
                }
            } catch (Throwable th_2) {
                this.initFailed = true;
                Log.e(LOG_TAG, "PrivateApiBridgeMode failed to find the expected APIs.", th_2);
            }
        }

        public void onNativeToJsMessageAvailable() {
            Message r0_Message;
            Method r1_Method;
            Object r2_Object;
            Object[] r3_ObjectA;
            if (this.sendMessageMethod != null || this.initFailed) {
                if (this.sendMessageMethod == null) {
                    r0_Message = Message.obtain(null, EXECUTE_JS, NativeToJsMessageQueue.this.popAndEncodeAsJs());
                    try {
                        r1_Method = this.sendMessageMethod;
                        r2_Object = this.webViewCore;
                        r3_ObjectA = new Object[1];
                        r3_ObjectA[0] = r0_Message;
                        r1_Method.invoke(r2_Object, r3_ObjectA);
                    } catch (Throwable th) {
                        Log.e(LOG_TAG, "Reflection message bridge failed.", th);
                    }
                }
            } else {
                initReflection();
                if (this.sendMessageMethod == null) {
                } else {
                    r0_Message = Message.obtain(null, EXECUTE_JS, NativeToJsMessageQueue.this.popAndEncodeAsJs());
                    r1_Method = this.sendMessageMethod;
                    r2_Object = this.webViewCore;
                    r3_ObjectA = new Object[1];
                    r3_ObjectA[0] = r0_Message;
                    r1_Method.invoke(r2_Object, r3_ObjectA);
                }
            }
        }
    }

    static {
        MAX_PAYLOAD_SIZE = -1;
    }

    public NativeToJsMessageQueue(CordovaWebView r5_CordovaWebView, CordovaInterface r6_CordovaInterface) {
        this.queue = new LinkedList();
        this.cordova = r6_CordovaInterface;
        this.webView = r5_CordovaWebView;
        this.registeredListeners = new BridgeMode[4];
        this.registeredListeners[0] = null;
        this.registeredListeners[1] = new LoadUrlBridgeMode(null);
        this.registeredListeners[2] = new OnlineEventsBridgeMode();
        this.registeredListeners[3] = new PrivateApiBridgeMode(null);
        reset();
    }

    private int calculatePackedMessageLength(JsMessage r3_JsMessage) {
        int r0i = r3_JsMessage.calculateEncodedLength();
        return r0i + String.valueOf(r0i).length() + 1;
    }

    private void enqueueMessage(JsMessage r3_JsMessage) {
        synchronized (this) {
            this.queue.add(r3_JsMessage);
            if (this.paused || this.registeredListeners[this.activeListenerIndex] == null) {
            } else {
                this.registeredListeners[this.activeListenerIndex].onNativeToJsMessageAvailable();
            }
        }
    }

    private void packMessage(JsMessage r3_JsMessage, StringBuilder r4_StringBuilder) {
        r4_StringBuilder.append(r3_JsMessage.calculateEncodedLength()).append(' ');
        r3_JsMessage.encodeAsMessage(r4_StringBuilder);
    }

    private String popAndEncodeAsJs() {
        String r0_String;
        synchronized (this) {
            if (this.queue.size() == 0) {
                r0_String = null;
            } else {
                int r0i;
                int r4i;
                Iterator r4_Iterator = this.queue.iterator();
                int r5i = 0;
                int r3i = 0;
                while (r4_Iterator.hasNext()) {
                    r0i = ((JsMessage) r4_Iterator.next()).calculateEncodedLength() + 50;
                    if (r5i <= 0 || r3i + r0i <= MAX_PAYLOAD_SIZE || MAX_PAYLOAD_SIZE <= 0) {
                        r3i += r0i;
                        r5i++;
                    } else {
                        break;
                    }
                }
                r4i = r5i == this.queue.size() ? 1 : 0;
                StringBuilder r6_StringBuilder = new StringBuilder((r4i != 0 ? 0 : 100) + r3i);
                r3i = 0;
                while (r3i < r5i) {
                    JsMessage r0_JsMessage = (JsMessage) this.queue.removeFirst();
                    if (r4i == 0 || r3i + 1 != r5i) {
                        r6_StringBuilder.append("try{");
                        r0_JsMessage.encodeAsJsMessage(r6_StringBuilder);
                        r6_StringBuilder.append("}finally{");
                    } else {
                        r0_JsMessage.encodeAsJsMessage(r6_StringBuilder);
                    }
                    r3i++;
                }
                if (r4i == 0) {
                    r6_StringBuilder.append("window.setTimeout(function(){cordova.require('cordova/plugin/android/polling').pollOnce();},0);");
                }
                r0i = r4i != 0 ? 1 : 0;
                while (r0i < r5i) {
                    r6_StringBuilder.append('}');
                    r0i++;
                }
                r0_String = r6_StringBuilder.toString();
            }
        }
        return r0_String;
    }

    public void addJavaScript(String r2_String) {
        enqueueMessage(new JsMessage(r2_String));
    }

    public void addPluginResult(PluginResult r4_PluginResult, String r5_String) {
        if (r5_String == null) {
            Log.e(LOG_TAG, "Got plugin result with no callbackId", new Throwable());
        } else {
            int r0i;
            r0i = r4_PluginResult.getStatus() == Status.NO_RESULT.ordinal() ? 1 : 0;
            boolean r1z = r4_PluginResult.getKeepCallback();
            if (r0i == 0 || (!r1z)) {
                enqueueMessage(new JsMessage(r4_PluginResult, r5_String));
            }
        }
    }

    public boolean getPaused() {
        return this.paused;
    }

    public String popAndEncode() {
        String r0_String;
        int r1i = 0;
        synchronized (this) {
            if (this.queue.isEmpty()) {
                r0_String = null;
            } else {
                Iterator r4_Iterator = this.queue.iterator();
                int r2i = 0;
                int r3i = 0;
                while (r4_Iterator.hasNext()) {
                    int r0i = calculatePackedMessageLength((JsMessage) r4_Iterator.next());
                    if (r2i <= 0 || r3i + r0i <= MAX_PAYLOAD_SIZE || MAX_PAYLOAD_SIZE <= 0) {
                        r3i += r0i;
                        r2i++;
                    } else {
                        break;
                    }
                }
                StringBuilder r4_StringBuilder = new StringBuilder(r3i);
                while (r1i < r2i) {
                    packMessage((JsMessage) this.queue.removeFirst(), r4_StringBuilder);
                    r1i++;
                }
                if (!this.queue.isEmpty()) {
                    r4_StringBuilder.append('*');
                }
                r0_String = r4_StringBuilder.toString();
            }
        }
        return r0_String;
    }

    public void reset() {
        synchronized (this) {
            this.queue.clear();
            setBridgeMode(DEFAULT_BRIDGE_MODE);
        }
    }

    public void setBridgeMode(int r4i) {
        if (r4i < 0 || r4i >= this.registeredListeners.length) {
            Log.d(LOG_TAG, "Invalid NativeToJsBridgeMode: " + r4i);
        } else {
            if (r4i != this.activeListenerIndex) {
                Log.d(LOG_TAG, "Set native->JS mode to " + r4i);
                synchronized (this) {
                    this.activeListenerIndex = r4i;
                    BridgeMode r0_BridgeMode = this.registeredListeners[r4i];
                    if (this.paused || this.queue.isEmpty() || r0_BridgeMode == null) {
                    } else {
                        r0_BridgeMode.onNativeToJsMessageAvailable();
                    }
                }
            }
        }
    }

    public void setPaused(boolean r4z) {
        if (this.paused && r4z) {
            Log.e(LOG_TAG, "nested call to setPaused detected.", new Throwable());
            this.paused = r4z;
            if (r4z) {
            } else {
                synchronized (this) {
                    if (this.queue.isEmpty() || this.registeredListeners[this.activeListenerIndex] == null) {
                    } else {
                        this.registeredListeners[this.activeListenerIndex].onNativeToJsMessageAvailable();
                    }
                }
            }
        } else {
            this.paused = r4z;
            if (r4z) {
                synchronized (this) {
                    if (this.queue.isEmpty() || this.registeredListeners[this.activeListenerIndex] == null) {
                    } else {
                        this.registeredListeners[this.activeListenerIndex].onNativeToJsMessageAvailable();
                    }
                }
            }
        }
    }
}
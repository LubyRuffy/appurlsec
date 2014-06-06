package qsbk.app.message;

public class ChatMsgSourceManager {
    private static ChatMsgSourceManager a;

    static {
        a = null;
    }

    private ChatMsgSourceManager() {
    }

    public static synchronized ChatMsgSourceManager instance() {
        ChatMsgSourceManager r0_ChatMsgSourceManager;
        synchronized (ChatMsgSourceManager.class) {
            if (a == null) {
                a = new ChatMsgSourceManager();
            }
            r0_ChatMsgSourceManager = a;
        }
        return r0_ChatMsgSourceManager;
    }
}
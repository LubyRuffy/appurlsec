package qsbk.app.utils.image.issue;

import android.content.Context;
import java.io.IOException;

public class DisplayIssueManager {
    private static DisplayIssueManager a;
    private static final TaskExecutor b;

    static {
        a = null;
        b = TaskExecutor.getInstance();
    }

    private DisplayIssueManager() {
    }

    public static synchronized DisplayIssueManager getInstance() {
        DisplayIssueManager r0_DisplayIssueManager;
        synchronized (DisplayIssueManager.class) {
            if (a == null) {
                a = new DisplayIssueManager();
            }
            r0_DisplayIssueManager = a;
        }
        return r0_DisplayIssueManager;
    }

    public void reportNewIssue(Context r3_Context, IOException r4_IOException, String r5_String) {
        b.addTask(new a(this, r4_IOException, r5_String, r3_Context));
    }

    public void reportOlderIssue(Context r3_Context) {
        if (r3_Context == null) {
            throw new NullPointerException("context must not be null");
        } else {
            Reporter.getInstance().reportFile(r3_Context);
        }
    }
}
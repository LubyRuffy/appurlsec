package qsbk.app.utils.image.issue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qsbk.app.exception.QiushibaikeException;

public class TaskExecutor {
    private static TaskExecutor a;
    private ExecutorService b;

    public static interface Task {
        public void fail(Throwable r1_Throwable);

        public Object proccess() throws QiushibaikeException;

        public void success(Object r1_Object);
    }

    public static class SimpleTask implements qsbk.app.utils.image.issue.TaskExecutor.Task {
        public void fail(Throwable r1_Throwable) {
        }

        public Object proccess() throws QiushibaikeException {
            return null;
        }

        public void success(Object r1_Object) {
        }
    }

    private final class a implements Runnable {
        qsbk.app.utils.image.issue.TaskExecutor.Task a;

        a(qsbk.app.utils.image.issue.TaskExecutor.Task r3_qsbk_app_utils_image_issue_TaskExecutor_Task) {
            this.a = null;
            this.a = r3_qsbk_app_utils_image_issue_TaskExecutor_Task;
        }

        qsbk.app.utils.image.issue.TaskExecutor.Task a_() {
            return this.a;
        }

        public void run() {
            try {
                this.a.success(this.a.proccess());
            } catch (Exception e) {
                this.a.fail(e);
            }
        }
    }

    static {
        a = null;
    }

    private TaskExecutor() {
        this.b = Executors.newSingleThreadExecutor();
    }

    public static synchronized TaskExecutor getInstance() {
        TaskExecutor r0_TaskExecutor;
        synchronized (TaskExecutor.class) {
            if (a == null) {
                a = new TaskExecutor();
            }
            r0_TaskExecutor = a;
        }
        return r0_TaskExecutor;
    }

    public boolean addTask(Task r3_Task) {
        if (r3_Task == null) {
            throw new NullPointerException("task must not be null");
        } else {
            if (this.b.isShutdown() || this.b.isTerminated()) {
                return false;
            }
            this.b.submit(new a(r3_Task));
            return true;
        }
    }

    public synchronized List<Task> shutdownAll() {
        List<Task> r0_List_Task;
        List r3_List = this.b.shutdownNow();
        r0_List_Task = null;
        if (r3_List == null || r3_List.isEmpty()) {
        } else {
            int r4i = r3_List.size();
            List<Task> r1_List_Task = new ArrayList();
            int r2i = 0;
            while (r2i < r4i) {
                Runnable r0_Runnable = (Runnable) r3_List.get(r2i);
                if (r0_Runnable == null || (!r0_Runnable instanceof a)) {
                    r2i++;
                } else {
                    r1_List_Task.add(((a) r0_Runnable).a());
                    r2i++;
                }
            }
            r0_List_Task = r1_List_Task;
        }
        return r0_List_Task;
    }
}
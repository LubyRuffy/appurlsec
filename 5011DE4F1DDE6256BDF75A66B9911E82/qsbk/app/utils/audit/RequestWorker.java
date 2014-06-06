package qsbk.app.utils.audit;

import java.util.concurrent.BlockingQueue;

public class RequestWorker extends Thread {
    private boolean a;
    private final BlockingQueue<Request> b;

    public RequestWorker(BlockingQueue<Request> r2_BlockingQueue_Request) {
        this.a = false;
        this.b = r2_BlockingQueue_Request;
    }

    public void quit() {
        this.a = true;
        interrupt();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14_this = this;
        r13 = -1;
        r7 = 0;
        r3 = 0;
        r0 = 10;
        android.os.Process.setThreadPriority(r0);
    L_0x0008:
        r0 = r14.b;	 //Catch:{ InterruptedException -> 0x002f }
        r0 = r0.take();	 //Catch:{ InterruptedException -> 0x002f }
        r0 = (qsbk.app.utils.audit.Request) r0;	 //Catch:{ InterruptedException -> 0x002f }
        r6 = r0.getRequestListener();	 //Catch:{ LoaderException -> 0x01a8, Exception -> 0x01a4 }
        r1 = 0;
        r4 = 0;
        r2 = r0.isCanceled();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        if (r2 == 0) goto L_0x0035;
    L_0x001c:
        if (r3 == 0) goto L_0x0021;
    L_0x001e:
        r1.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x0021:
        if (r3 == 0) goto L_0x0008;
    L_0x0023:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
        goto L_0x0008;
    L_0x0027:
        r1 = move-exception;
        r2 = r6;
    L_0x0029:
        if (r2 == 0) goto L_0x0008;
    L_0x002b:
        r2.onError(r0, r1);
        goto L_0x0008;
    L_0x002f:
        r0 = move-exception;
        r0 = r14.a;
        if (r0 == 0) goto L_0x0008;
    L_0x0034:
        return;
    L_0x0035:
        r2 = new org.apache.http.impl.client.DefaultHttpClient;	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r2.<init>();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r5 = new org.apache.http.client.methods.HttpGet;	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r8 = r0.getUrl();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r5.<init>(r8);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r8 = r2.getParams();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r9 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r8, r9);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r8 = r2.getParams();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r9 = 45000; // 0xafc8 float:6.3058E-41 double:2.2233E-319;
        org.apache.http.params.HttpConnectionParams.setSoTimeout(r8, r9);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r8 = "Source";
        r9 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r9.<init>();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r10 = "android_";
        r9 = r9.append(r10);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r10 = qsbk.app.Constants.localVersionName;	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r9 = r9.append(r10);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r9 = r9.toString();	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r5.addHeader(r8, r9);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r2 = r2.execute(r5);	 //Catch:{ IOException -> 0x01b3, all -> 0x01ac }
        r8 = r2.getEntity();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r9 = r8.getContentLength();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r9 = (int) r9;	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r10 = r0.isCanceled();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        if (r10 == 0) goto L_0x009e;
    L_0x0083:
        if (r3 == 0) goto L_0x0088;
    L_0x0085:
        r1.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x0088:
        if (r3 == 0) goto L_0x0008;
    L_0x008a:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
        goto L_0x0008;
    L_0x008f:
        r1 = move-exception;
    L_0x0090:
        if (r6 == 0) goto L_0x0008;
    L_0x0092:
        r2 = new qsbk.app.utils.audit.LoaderException;
        r4 = "Unhandler Exception";
        r2.<init>(r4, r1);
        r6.onError(r0, r2);
        goto L_0x0008;
    L_0x009e:
        if (r6 == 0) goto L_0x00a3;
    L_0x00a0:
        r6.onPrepare(r0, r9);	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
    L_0x00a3:
        r10 = r0.isCanceled();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        if (r10 != 0) goto L_0x00af;
    L_0x00a9:
        r10 = r0.isFinished();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        if (r10 == 0) goto L_0x00be;
    L_0x00af:
        r5.abort();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        if (r3 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x00b7:
        if (r3 == 0) goto L_0x0008;
    L_0x00b9:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
        goto L_0x0008;
    L_0x00be:
        r1 = r8.getContent();	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r5 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r5.<init>(r1);	 //Catch:{ IOException -> 0x01b9, all -> 0x01ac }
        r4 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x01be, all -> 0x01b0 }
        r4.<init>();	 //Catch:{ IOException -> 0x01be, all -> 0x01b0 }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r8 = new byte[r1];	 //Catch:{ IOException -> 0x00fa }
        r1 = r7;
    L_0x00d1:
        r10 = r5.read(r8);	 //Catch:{ IOException -> 0x00fa }
        if (r10 == r13) goto L_0x00dd;
    L_0x00d7:
        r11 = r0.isCanceled();	 //Catch:{ IOException -> 0x00fa }
        if (r11 == 0) goto L_0x00ef;
    L_0x00dd:
        r1 = r0.isCanceled();	 //Catch:{ IOException -> 0x00fa }
        if (r1 == 0) goto L_0x0144;
    L_0x00e3:
        if (r5 == 0) goto L_0x00e8;
    L_0x00e5:
        r5.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x00e8:
        if (r4 == 0) goto L_0x0008;
    L_0x00ea:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
        goto L_0x0008;
    L_0x00ef:
        r11 = 0;
        r4.write(r8, r11, r10);	 //Catch:{ IOException -> 0x00fa }
        r1 = r1 + r10;
        if (r6 == 0) goto L_0x00d1;
    L_0x00f6:
        r6.onDownloading(r0, r9, r1);	 //Catch:{ IOException -> 0x00fa }
        goto L_0x00d1;
    L_0x00fa:
        r1 = move-exception;
    L_0x00fb:
        if (r2 == 0) goto L_0x0185;
    L_0x00fd:
        r8 = qsbk.app.utils.image.issue.DisplayIssueManager.getInstance();	 //Catch:{ all -> 0x0138 }
        r9 = qsbk.app.QsbkApp.mContext;	 //Catch:{ all -> 0x0138 }
        r10 = new qsbk.app.utils.image.issue.IOExceptionWrapper;	 //Catch:{ all -> 0x0138 }
        r11 = r2.getStatusLine();	 //Catch:{ all -> 0x0138 }
        r11 = r11.getStatusCode();	 //Catch:{ all -> 0x0138 }
        r10.<init>(r11, r1);	 //Catch:{ all -> 0x0138 }
        r11 = r0.getUrl();	 //Catch:{ all -> 0x0138 }
        r8.reportNewIssue(r9, r10, r11);	 //Catch:{ all -> 0x0138 }
        r8 = new qsbk.app.utils.audit.LoaderException;	 //Catch:{ all -> 0x0138 }
        r9 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0138 }
        r9.<init>();	 //Catch:{ all -> 0x0138 }
        r10 = "IOException, Sever response code ";
        r9 = r9.append(r10);	 //Catch:{ all -> 0x0138 }
        r2 = r2.getStatusLine();	 //Catch:{ all -> 0x0138 }
        r2 = r2.getStatusCode();	 //Catch:{ all -> 0x0138 }
        r2 = r9.append(r2);	 //Catch:{ all -> 0x0138 }
        r2 = r2.toString();	 //Catch:{ all -> 0x0138 }
        r8.<init>(r2, r1);	 //Catch:{ all -> 0x0138 }
        throw r8;	 //Catch:{ all -> 0x0138 }
    L_0x0138:
        r1 = move-exception;
    L_0x0139:
        if (r5 == 0) goto L_0x013e;
    L_0x013b:
        r5.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x013e:
        if (r4 == 0) goto L_0x0143;
    L_0x0140:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x0143:
        throw r1;	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x0144:
        r1 = r4.toByteArray();	 //Catch:{ IOException -> 0x00fa }
        r8 = r1.length;	 //Catch:{ IOException -> 0x00fa }
        if (r8 != r9) goto L_0x015c;
    L_0x014b:
        if (r6 == 0) goto L_0x015c;
    L_0x014d:
        r6.onSuccess(r0, r9, r1);	 //Catch:{ IOException -> 0x00fa }
    L_0x0150:
        if (r5 == 0) goto L_0x0155;
    L_0x0152:
        r5.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
    L_0x0155:
        if (r4 == 0) goto L_0x0008;
    L_0x0157:
        r4.close();	 //Catch:{ LoaderException -> 0x0027, Exception -> 0x008f }
        goto L_0x0008;
    L_0x015c:
        if (r6 == 0) goto L_0x0150;
    L_0x015e:
        r8 = r0.isCanceled();	 //Catch:{ IOException -> 0x00fa }
        if (r8 != 0) goto L_0x0150;
    L_0x0164:
        r8 = new qsbk.app.utils.audit.LoaderException;	 //Catch:{ IOException -> 0x00fa }
        r10 = "Not enough data. Loaded %1d of %2d";
        r11 = 2;
        r11 = new java.lang.Object[r11];	 //Catch:{ IOException -> 0x00fa }
        r12 = 0;
        r1 = r1.length;	 //Catch:{ IOException -> 0x00fa }
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ IOException -> 0x00fa }
        r11[r12] = r1;	 //Catch:{ IOException -> 0x00fa }
        r1 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 //Catch:{ IOException -> 0x00fa }
        r11[r1] = r9;	 //Catch:{ IOException -> 0x00fa }
        r1 = java.lang.String.format(r10, r11);	 //Catch:{ IOException -> 0x00fa }
        r8.<init>(r1);	 //Catch:{ IOException -> 0x00fa }
        r6.onError(r0, r8);	 //Catch:{ IOException -> 0x00fa }
        goto L_0x0150;
    L_0x0185:
        r2 = qsbk.app.utils.image.issue.DisplayIssueManager.getInstance();	 //Catch:{ all -> 0x0138 }
        r8 = qsbk.app.QsbkApp.mContext;	 //Catch:{ all -> 0x0138 }
        r9 = new qsbk.app.utils.image.issue.IOExceptionWrapper;	 //Catch:{ all -> 0x0138 }
        r10 = -1;
        r11 = "fromAudit";
        r9.<init>(r10, r11, r1);	 //Catch:{ all -> 0x0138 }
        r10 = r0.getUrl();	 //Catch:{ all -> 0x0138 }
        r2.reportNewIssue(r8, r9, r10);	 //Catch:{ all -> 0x0138 }
        r2 = new qsbk.app.utils.audit.LoaderException;	 //Catch:{ all -> 0x0138 }
        r8 = r1.toString();	 //Catch:{ all -> 0x0138 }
        r2.<init>(r8, r1);	 //Catch:{ all -> 0x0138 }
        throw r2;	 //Catch:{ all -> 0x0138 }
    L_0x01a4:
        r1 = move-exception;
        r6 = r3;
        goto L_0x0090;
    L_0x01a8:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0029;
    L_0x01ac:
        r1 = move-exception;
        r4 = r3;
        r5 = r3;
        goto L_0x0139;
    L_0x01b0:
        r1 = move-exception;
        r4 = r3;
        goto L_0x0139;
    L_0x01b3:
        r1 = move-exception;
        r2 = r3;
        r4 = r3;
        r5 = r3;
        goto L_0x00fb;
    L_0x01b9:
        r1 = move-exception;
        r4 = r3;
        r5 = r3;
        goto L_0x00fb;
    L_0x01be:
        r1 = move-exception;
        r4 = r3;
        goto L_0x00fb;
        */

    }
}
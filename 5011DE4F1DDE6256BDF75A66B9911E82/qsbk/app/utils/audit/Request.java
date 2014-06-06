package qsbk.app.utils.audit;

public class Request {
    private String a;
    private Object b;
    private boolean c;
    private RequestListener d;
    private boolean e;
    private boolean f;

    public Request(String r2_String, RequestListener r3_RequestListener) {
        this.e = false;
        this.f = false;
        this.a = r2_String;
        this.d = r3_RequestListener;
    }

    public void cancel() {
        if (!this.c) {
            this.c = true;
            if (this.d == null || this.e) {
            } else {
                this.d.onCanceled(this);
            }
        }
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        Request r5_Request = (Request) r5_Object;
        if (this.b == null) {
            if (r5_Request.b != null) {
                return false;
            }
        } else if (!this.b.equals(r5_Request.b)) {
            return false;
        }
        if (this.a == null) {
            return r5_Request.a == null;
        } else {
            if (this.a.equals(r5_Request.a)) {
                return true;
            }
            return false;
        }
    }

    public RequestListener getRequestListener() {
        return this.d;
    }

    public Object getTag() {
        return this.b;
    }

    public String getUrl() {
        return this.a;
    }

    public int hashCode() {
        int r1i = 1231;
        int r3i = 0;
        int r0i = ((this.c ? 1231 : 1237) + 31) * 31;
        if (this.e) {
            r0i = ((this.b != null ? 0 : this.b.hashCode()) + ((r0i + r1i) * 31)) * 31;
            if (this.a != null) {
                return r0i + r3i;
            }
            r3i = this.a.hashCode();
            return r0i + r3i;
        } else {
            r1i = 1237;
            if (this.b != null) {
            }
            r0i = ((this.b != null ? 0 : this.b.hashCode()) + ((r0i + r1i) * 31)) * 31;
            if (this.a != null) {
                r3i = this.a.hashCode();
            }
            return r0i + r3i;
        }
    }

    public boolean isCanceled() {
        return this.c;
    }

    public boolean isFinished() {
        return this.e;
    }

    public boolean isSuccess() {
        return this.f;
    }

    public void markFinished() {
        this.e = true;
    }

    public void markSuccess() {
        markFinished();
        this.f = true;
    }

    public void setTag(Object r1_Object) {
        this.b = r1_Object;
    }
}
package qsbk.app.utils.image.issue;

public class IssueBean {
    private long a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;

    public IssueBean(long r1j, String r3_String, String r4_String, String r5_String, String r6_String) {
        this.a = r1j;
        this.b = r3_String;
        this.c = r4_String;
        this.d = r5_String;
        this.e = r6_String;
    }

    public String getCdnAddress() {
        return this.e;
    }

    public String getIssueAddress() {
        return this.c;
    }

    public String getIssueInetAddress() {
        return this.d;
    }

    public long getLastSuccessTime() {
        return this.f;
    }

    public String getMsg() {
        return this.b;
    }

    public long getTime() {
        return this.a;
    }

    public void setCdnAddress(String r1_String) {
        this.e = r1_String;
    }

    public void setIssueAddress(String r1_String) {
        this.c = r1_String;
    }

    public void setIssueInetAddress(String r1_String) {
        this.d = r1_String;
    }

    public void setLastSuccessTime(long r1j) {
        this.f = r1j;
    }

    public void setMsg(String r1_String) {
        this.b = r1_String;
    }

    public void setTime(long r1j) {
        this.a = r1j;
    }

    public String toString() {
        return "?time=" + this.a + "&msg=" + this.b + "&issueAddress=" + this.c + "&issueInetAddress=" + this.d + "&cdnAddress=" + this.e + "&lastSuccessTime=" + this.f;
    }
}
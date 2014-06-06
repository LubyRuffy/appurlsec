package qsbk.app.model;

public class Vote {
    public String action;
    public String session;
    public String state;
    public String target;
    public String type;

    public Vote(String r6_String, String r7_String, String r8_String, String r9_String) {
        this.session = r6_String + "/" + (System.currentTimeMillis() / 1000);
        this.type = r7_String;
        this.target = r8_String;
        this.action = r9_String;
    }

    public Vote(String r6_String, String r7_String, String r8_String, String r9_String, String r10_String) {
        this.session = r6_String + "/" + (System.currentTimeMillis() / 1000);
        this.type = r7_String;
        this.target = r8_String;
        this.action = r9_String;
        this.state = r10_String;
    }

    public String toString() {
        StringBuffer r0_StringBuffer = new StringBuffer();
        r0_StringBuffer.append("{");
        r0_StringBuffer.append("\"session\":\"" + this.session + "\"");
        r0_StringBuffer.append(",\"type\":\"" + this.type + "\"");
        r0_StringBuffer.append(",\"target\":\"" + this.target + "\"");
        r0_StringBuffer.append(",\"action\":" + Integer.valueOf(this.action));
        r0_StringBuffer.append("}");
        return r0_StringBuffer.toString();
    }
}
package qsbk.app.bean;

import java.io.Serializable;

public class Base implements Serializable {
    public static final String UTF8 = "UTF-8";
    protected Notice a;

    public Notice getNotice() {
        return this.a;
    }

    public void setNotice(Notice r1_Notice) {
        this.a = r1_Notice;
    }
}
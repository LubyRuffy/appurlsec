package qsbk.app.bean;

import java.io.Serializable;

public class ReportBean implements Serializable {
    private String a;
    private int b;

    public ReportBean(String r1_String, int r2i) {
        this.a = r1_String;
        this.b = r2i;
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
        ReportBean r5_ReportBean = (ReportBean) r5_Object;
        if (this.a == null) {
            if (r5_ReportBean.a != null) {
                return false;
            }
        } else if (!this.a.equals(r5_ReportBean.a)) {
            return false;
        }
        return this.b == r5_ReportBean.b;
    }

    public String getName() {
        return this.a;
    }

    public int getValue() {
        return this.b;
    }

    public int hashCode() {
        return ((this.a == null ? 0 : this.a.hashCode()) + 31) * 31 + this.b;
    }

    public void setName(String r1_String) {
        this.a = r1_String;
    }

    public void setValue(int r1i) {
        this.b = r1i;
    }
}
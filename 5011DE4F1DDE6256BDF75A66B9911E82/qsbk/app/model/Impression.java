package qsbk.app.model;

public class Impression {
    private String a;
    private Long b;
    private Long c;

    public Impression(String r4_String) {
        this.a = r4_String;
        this.b = Long.valueOf(0);
        this.c = Long.valueOf(0);
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
        Impression r5_Impression = (Impression) r5_Object;
        if (this.a == null) {
            return r5_Impression.a == null;
        } else {
            if (this.a.equals(r5_Impression.a)) {
                return true;
            }
            return false;
        }
    }

    public Long getShowTime() {
        return Long.valueOf(this.c.longValue() - this.b.longValue());
    }

    public String get_articleId() {
        return this.a;
    }

    public Long get_endTime() {
        return this.c;
    }

    public Long get_startTime() {
        return this.b;
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public void set_articleId(String r1_String) {
        this.a = r1_String;
    }

    public void set_endTime(Long r1_Long) {
        this.c = r1_Long;
    }

    public void set_startTime(Long r1_Long) {
        this.b = r1_Long;
    }
}
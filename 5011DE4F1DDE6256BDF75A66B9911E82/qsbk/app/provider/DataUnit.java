package qsbk.app.provider;

import qsbk.app.utils.DataParse.StampContent;

public class DataUnit {
    private int a;
    private String b;
    private String c;
    private String d;
    private DataUnit e;
    private float f;
    private StampContent g;

    public DataUnit(int r1i, String r2_String, String r3_String, String r4_String, DataUnit r5_DataUnit, float r6f, StampContent r7_StampContent) {
        this.a = r1i;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_DataUnit;
        this.f = r6f;
        this.g = r7_StampContent;
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
        if (this.a != ((DataUnit) r5_Object).a) {
            return false;
        }
        return true;
    }

    public String getContent() {
        return this.b;
    }

    public StampContent getEvaluate() {
        return this.g;
    }

    public String getImage() {
        return this.c;
    }

    public int getIndex() {
        return this.a;
    }

    public DataUnit getSimilar_art() {
        return this.e;
    }

    public float getSimilar_value() {
        return this.f;
    }

    public String getTag() {
        return this.d;
    }

    public boolean hasEvaluate() {
        return this.g != null && !this.g.equals(StampContent.NONE);
    }

    public int hashCode() {
        return this.a + 31;
    }

    public void setContent(String r1_String) {
        this.b = r1_String;
    }

    public void setEvaluate(StampContent r1_StampContent) {
        this.g = r1_StampContent;
    }

    public void setImage(String r1_String) {
        this.c = r1_String;
    }

    public void setIndex(int r1i) {
        this.a = r1i;
    }

    public void setSimilar_art(DataUnit r1_DataUnit) {
        this.e = r1_DataUnit;
    }

    public void setSimilar_value(float r1f) {
        this.f = r1f;
    }

    public void setTag(String r1_String) {
        this.d = r1_String;
    }
}
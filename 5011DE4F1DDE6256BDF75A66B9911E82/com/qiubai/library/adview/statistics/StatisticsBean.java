package com.qiubai.library.adview.statistics;

public class StatisticsBean {
    private String a;
    private int b;
    private int c;
    private int d;

    public StatisticsBean() {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }

    public String getAdName() {
        return this.a;
    }

    public int getClick() {
        return this.c;
    }

    public int getFailed() {
        return this.d;
    }

    public int getImpression() {
        return this.b;
    }

    public void setAdName(String r1_String) {
        this.a = r1_String;
    }

    public void setClick(int r1i) {
        this.c = r1i;
    }

    public void setFailed(int r1i) {
        this.d = r1i;
    }

    public void setImpression(int r1i) {
        this.b = r1i;
    }
}
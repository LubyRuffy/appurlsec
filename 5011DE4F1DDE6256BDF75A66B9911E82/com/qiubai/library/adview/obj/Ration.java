package com.qiubai.library.adview.obj;

import com.tencent.mm.sdk.contact.RContactStorage;

public class Ration implements Comparable<Ration> {
    public String key;
    public String key2;
    public String key3;
    public String logo;
    public String name;
    public String nid;
    public int priority;
    public int type;
    public int type2;
    public double weight;

    public Ration() {
        this.nid = RContactStorage.PRIMARY_KEY;
        this.type = 0;
        this.name = RContactStorage.PRIMARY_KEY;
        this.weight = 0.0d;
        this.key = RContactStorage.PRIMARY_KEY;
        this.key2 = RContactStorage.PRIMARY_KEY;
        this.key3 = RContactStorage.PRIMARY_KEY;
        this.type2 = 1;
        this.priority = 0;
        this.logo = RContactStorage.PRIMARY_KEY;
    }

    public int compareTo(Ration r3_Ration) {
        int r0i = r3_Ration.priority;
        if (this.priority < r0i) {
            return -1;
        }
        if (this.priority > r0i) {
            return 1;
        }
        return 0;
    }
}
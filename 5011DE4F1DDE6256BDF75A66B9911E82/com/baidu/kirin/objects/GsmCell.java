package com.baidu.kirin.objects;

import com.tencent.mm.sdk.contact.RContactStorage;

public class GsmCell extends SCell {
    public int CID;
    public int LAC;

    public String toString() {
        return this.cellType + "," + this.MCCMNC + "," + this.MCC + "," + this.MNC + RContactStorage.PRIMARY_KEY + this.LAC + "," + this.CID;
    }
}
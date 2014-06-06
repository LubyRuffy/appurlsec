package com.baidu.kirin.objects;

import com.tencent.mm.sdk.contact.RContactStorage;

public class CdmaCell extends SCell {
    public int networkId;
    public int stationId;
    public int systemId;

    public String toString() {
        return this.cellType + "," + this.MCCMNC + "," + this.MCC + "," + this.MNC + RContactStorage.PRIMARY_KEY + this.stationId + "," + this.networkId + "," + this.systemId;
    }
}
package com.tencent.mm.algorithm;

import com.tencent.mm.algorithm.LRUMap.TimeVal;
import java.util.Comparator;
import java.util.Map.Entry;

class a implements Comparator<Entry<K, TimeVal<O>>> {
    final /* synthetic */ LRUMap a;

    a(LRUMap r1_LRUMap) {
        this.a = r1_LRUMap;
    }

    public int compare(Entry<K, TimeVal<O>> r3_Entry_K__TimeVal_O, Entry<K, TimeVal<O>> r4_Entry_K__TimeVal_O) {
        return ((TimeVal) r3_Entry_K__TimeVal_O.getValue()).t.compareTo(((TimeVal) r4_Entry_K__TimeVal_O.getValue()).t);
    }
}
package com.baidu.frontia;

import com.baidu.frontia.base.impl.FrontiaQueryImpl;
import org.json.JSONObject;

public class FrontiaQuery {
    private FrontiaQueryImpl a;

    public static enum SortOrder {
        ASC,
        DESC;


        static {
            ASC = new com.baidu.frontia.FrontiaQuery.SortOrder("ASC", 0);
            DESC = new com.baidu.frontia.FrontiaQuery.SortOrder("DESC", 1);
            com.baidu.frontia.FrontiaQuery.SortOrder[] r0_com_baidu_frontia_FrontiaQuery_SortOrderA = new com.baidu.frontia.FrontiaQuery.SortOrder[2];
            r0_com_baidu_frontia_FrontiaQuery_SortOrderA[0] = ASC;
            r0_com_baidu_frontia_FrontiaQuery_SortOrderA[1] = DESC;
            a = r0_com_baidu_frontia_FrontiaQuery_SortOrderA;
        }
    }

    public FrontiaQuery() {
        this.a = new FrontiaQueryImpl();
    }

    public FrontiaQuery addSort(String r3_String, SortOrder r4_SortOrder) {
        if (r4_SortOrder == SortOrder.ASC) {
            this.a.addSort(r3_String, com.baidu.frontia.base.impl.FrontiaQueryImpl.SortOrder.ASC);
        } else {
            this.a.addSort(r3_String, com.baidu.frontia.base.impl.FrontiaQueryImpl.SortOrder.DESC);
        }
        return this;
    }

    public FrontiaQuery all(String r2_String, Object[] r3_ObjectA) {
        this.a = this.a.all(r2_String, r3_ObjectA);
        return this;
    }

    public FrontiaQuery and(FrontiaQuery r3_FrontiaQuery) {
        this.a = this.a.and(r3_FrontiaQuery.a);
        return this;
    }

    public FrontiaQuery endsWith(String r2_String, String r3_String) {
        this.a.endsWith(r2_String, r3_String);
        return this;
    }

    public FrontiaQuery equals(String r2_String, Object r3_Object) {
        this.a = this.a.equals(r2_String, r3_Object);
        return this;
    }

    public int getLimit() {
        return this.a.getLimit();
    }

    public int getSkip() {
        return this.a.getSkip();
    }

    public JSONObject getSort() {
        return this.a.getSort();
    }

    public FrontiaQuery greaterThan(String r2_String, Object r3_Object) {
        this.a = this.a.greaterThan(r2_String, r3_Object);
        return this;
    }

    public FrontiaQuery greaterThanEqualTo(String r2_String, Object r3_Object) {
        this.a = this.a.greaterThanEqualTo(r2_String, r3_Object);
        return this;
    }

    public FrontiaQuery in(String r2_String, Object[] r3_ObjectA) {
        this.a = this.a.in(r2_String, r3_ObjectA);
        return this;
    }

    public FrontiaQuery lessThan(String r2_String, Object r3_Object) {
        this.a = this.a.lessThan(r2_String, r3_Object);
        return this;
    }

    public FrontiaQuery lessThanEqualTo(String r2_String, Object r3_Object) {
        this.a = this.a.lessThanEqualTo(r2_String, r3_Object);
        return this;
    }

    public FrontiaQuery not() {
        this.a.not();
        return this;
    }

    public FrontiaQuery notEqual(String r2_String, Object r3_Object) {
        this.a.notEqual(r2_String, r3_Object);
        return this;
    }

    public FrontiaQuery notIn(String r2_String, Object[] r3_ObjectA) {
        this.a = this.a.notIn(r2_String, r3_ObjectA);
        return this;
    }

    public FrontiaQuery or(FrontiaQuery r3_FrontiaQuery) {
        this.a = this.a.or(r3_FrontiaQuery.a);
        return this;
    }

    public FrontiaQuery regEx(String r2_String, String r3_String) {
        this.a = this.a.regEx(r2_String, r3_String);
        return this;
    }

    public FrontiaQuery setLimit(int r2i) {
        this.a.setLimit(r2i);
        return this;
    }

    public FrontiaQuery setSkip(int r2i) {
        this.a.setSkip(r2i);
        return this;
    }

    public FrontiaQuery size(String r2_String, int r3i) {
        this.a.size(r2_String, r3i);
        return this;
    }

    public FrontiaQuery startsWith(String r2_String, String r3_String) {
        this.a = this.a.startsWith(r2_String, r3_String);
        return this;
    }

    public JSONObject toJSONObject() {
        return this.a.toJSONObject();
    }
}
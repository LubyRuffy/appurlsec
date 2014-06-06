package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private final String a;
    private final String b;
    private final long c;
    private final long d;
    private final long e;
    private final String f;
    private final Map<String, Item> g;


    public static class Builder {
        private final String a;
        private String b;
        private final long c;
        private long d;
        private long e;
        private String f;

        public Builder(String r4_String, long r5j) {
            this.b = null;
            this.d = 0;
            this.e = 0;
            this.f = null;
            if (TextUtils.isEmpty(r4_String)) {
                throw new IllegalArgumentException("orderId must not be empty or null");
            } else {
                this.a = r4_String;
                this.c = r5j;
            }
        }

        public Transaction build() {
            return new Transaction(null);
        }

        public com.google.analytics.tracking.android.Transaction.Builder setAffiliation(String r1_String) {
            this.b = r1_String;
            return this;
        }

        public com.google.analytics.tracking.android.Transaction.Builder setShippingCostInMicros(long r1j) {
            this.e = r1j;
            return this;
        }

        public com.google.analytics.tracking.android.Transaction.Builder setTotalTaxInMicros(long r1j) {
            this.d = r1j;
            return this;
        }
    }

    private Transaction(Builder r3_Builder) {
        this.a = r3_Builder.a;
        this.c = r3_Builder.c;
        this.b = r3_Builder.b;
        this.d = r3_Builder.d;
        this.e = r3_Builder.e;
        this.f = r3_Builder.f;
        this.g = new HashMap();
    }

    String a() {
        return this.f;
    }

    public void addItem(Item r3_Item) {
        this.g.put(r3_Item.getProductSKU(), r3_Item);
    }

    public String getAffiliation() {
        return this.b;
    }

    public List<Item> getItems() {
        return new ArrayList(this.g.values());
    }

    public long getShippingCostInMicros() {
        return this.e;
    }

    public long getTotalCostInMicros() {
        return this.c;
    }

    public long getTotalTaxInMicros() {
        return this.d;
    }

    public String getTransactionId() {
        return this.a;
    }
}
package com.google.analytics.tracking.android;

import android.text.TextUtils;

public class Item {
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final long e;


    public static class Builder {
        private final String a;
        private final long b;
        private final long c;
        private final String d;
        private String e;

        public Builder(String r3_String, String r4_String, long r5j, long r7j) {
            this.e = null;
            if (TextUtils.isEmpty(r3_String)) {
                throw new IllegalArgumentException("productSKU must not be empty or null");
            } else if (TextUtils.isEmpty(r4_String)) {
                throw new IllegalArgumentException("itemName must not be empty or null");
            } else {
                this.a = r3_String;
                this.d = r4_String;
                this.b = r5j;
                this.c = r7j;
            }
        }

        public Item build() {
            return new Item(null);
        }

        public com.google.analytics.tracking.android.Item.Builder setProductCategory(String r1_String) {
            this.e = r1_String;
            return this;
        }
    }

    private Item(Builder r3_Builder) {
        this.a = r3_Builder.a;
        this.d = r3_Builder.b;
        this.e = r3_Builder.c;
        this.b = r3_Builder.d;
        this.c = r3_Builder.e;
    }

    public long getItemPriceInMicros() {
        return this.d;
    }

    public long getItemQuantity() {
        return this.e;
    }

    public String getProductCategory() {
        return this.c;
    }

    public String getProductName() {
        return this.b;
    }

    public String getProductSKU() {
        return this.a;
    }
}
package com.aps;

import com.amap.api.location.LocationManagerProxy;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import qsbk.app.bean.Base;

// compiled from: Parser.java
public class k {


    // compiled from: Parser.java
    private class a extends DefaultHandler {
        public c a;
        private String c;

        private a() {
            this.a = new c();
            this.c = RContactStorage.PRIMARY_KEY;
        }

        public void characters(char[] r2_charA, int r3i, int r4i) {
            this.c = String.valueOf(r2_charA, r3i, r4i);
        }

        public void endElement(String r6_String, String r7_String, String r8_String) {
            if (r7_String.equals("retype")) {
                this.a.e(this.c);
            } else if (r7_String.equals("adcode")) {
                this.a.h(this.c);
            } else if (r7_String.equals("citycode")) {
                this.a.f(this.c);
            } else if (r7_String.equals("radius")) {
                try {
                    this.a.a(Float.valueOf(this.c).floatValue());
                } catch (Exception e) {
                    o.a(e);
                    this.a.a(3891.0f);
                }
            } else if (r7_String.equals("cenx")) {
                try {
                    this.c = n.a(Double.valueOf(this.c), "#.000000");
                    this.a.a(Double.valueOf(this.c).doubleValue());
                } catch (Exception e_2) {
                    o.a(e_2);
                    this.a.a(0.0d);
                }
            } else if (r7_String.equals("ceny")) {
                try {
                    this.c = n.a(Double.valueOf(this.c), "#.000000");
                    this.a.b(Double.valueOf(this.c).doubleValue());
                } catch (Exception e_3) {
                    o.a(e_3);
                    this.a.b(0.0d);
                }
            } else if (r7_String.equals(Constants.PARAM_APP_DESC)) {
                this.a.g(this.c);
            } else if (r7_String.equals("country")) {
                this.a.i(this.c);
            } else if (r7_String.equals("province")) {
                this.a.j(this.c);
            } else if (r7_String.equals("city")) {
                this.a.k(this.c);
            } else if (r7_String.equals("road")) {
                this.a.l(this.c);
            } else if (r7_String.equals("street")) {
                this.a.m(this.c);
            } else if (r7_String.equals("poiname")) {
                this.a.n(this.c);
            } else if (r7_String.equals("BIZ")) {
                if (this.a.l() == null) {
                    this.a.a(new JSONObject());
                }
                try {
                    this.a.l().put("BIZ", this.c);
                } catch (Exception e_4) {
                }
            } else if (r7_String.equals("flr")) {
                this.a.b(this.c);
            } else if (r7_String.equals("pid")) {
                this.a.a(this.c);
            } else if (r7_String.equals("apiTime")) {
                try {
                    this.a.a(Long.valueOf(this.c).longValue());
                } catch (Exception e_5) {
                    o.a(e_5);
                    this.a.a(0);
                }
            }
            if (this.a.l() == null) {
                this.a.a(new JSONObject());
            }
            try {
                if (r7_String.equals("eab")) {
                    this.a.l().put(r7_String, this.c);
                } else if (r7_String.equals("ctl")) {
                    this.a.l().put(r7_String, this.c);
                } else if (r7_String.equals(AdViewUtil.COUNTSUCCESS)) {
                    this.a.l().put(r7_String, this.c);
                } else {
                    if (r7_String.equals("spa")) {
                        this.a.l().put(r7_String, this.c);
                    }
                }
            } catch (Exception e_6) {
            }
        }

        public void startElement(String r2_String, String r3_String, String r4_String, Attributes r5_Attributes) {
            this.c = RContactStorage.PRIMARY_KEY;
        }
    }

    // compiled from: Parser.java
    private class b extends DefaultHandler {
        public String a;
        private boolean c;

        private b() {
            this.a = RContactStorage.PRIMARY_KEY;
            this.c = false;
        }

        public void characters(char[] r2_charA, int r3i, int r4i) {
            if (this.c) {
                this.a = String.valueOf(r2_charA, r3i, r4i);
            }
        }

        public void endElement(String r2_String, String r3_String, String r4_String) {
            if (r3_String.equals("sres")) {
                this.c = false;
            }
        }

        public void startElement(String r2_String, String r3_String, String r4_String, Attributes r5_Attributes) {
            if (r3_String.equals("sres")) {
                this.c = true;
            }
        }
    }

    protected k() {
    }

    String a(String r4_String) {
        if (r4_String == null || r4_String.length() == 0) {
            return null;
        }
        InputStream r0_InputStream;
        try {
            r0_InputStream = new ByteArrayInputStream(r4_String.getBytes(Base.UTF8));
        } catch (UnsupportedEncodingException e) {
            r0_InputStream = null;
        }
        DefaultHandler r2_DefaultHandler = new b(null);
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(r0_InputStream, r2_DefaultHandler);
            r0_InputStream.close();
        } catch (SAXException e_2) {
        } catch (Exception e_3) {
            o.a(e_3);
        }
        return r2_DefaultHandler.a;
    }

    c b(String r7_String) {
        if (r7_String == null || r7_String.length() == 0 || r7_String.contains("SuccessCode=\"0\"")) {
            return null;
        }
        InputStream r0_InputStream;
        try {
            r0_InputStream = new ByteArrayInputStream(r7_String.getBytes(Base.UTF8));
        } catch (UnsupportedEncodingException e) {
            r0_InputStream = null;
        }
        SAXParserFactory r2_SAXParserFactory = SAXParserFactory.newInstance();
        DefaultHandler r3_DefaultHandler = new a(null);
        try {
            r2_SAXParserFactory.newSAXParser().parse(r0_InputStream, r3_DefaultHandler);
            r0_InputStream.close();
        } catch (Exception e_2) {
        }
        r3_DefaultHandler.a.c(LocationManagerProxy.NETWORK_PROVIDER);
        if (r3_DefaultHandler.a.f() == 0) {
            r3_DefaultHandler.a.a(o.a());
        }
        return r3_DefaultHandler.a;
    }
}
package com.androidquery.util;

import android.util.Xml;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class XmlDom {
    private Element a;

    public XmlDom(InputStream r3_InputStream) throws SAXException {
        try {
            this.a = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(r3_InputStream).getDocumentElement();
        } catch (ParserConfigurationException e) {
        } catch (IOException e_2) {
            throw new SAXException(e_2);
        }
    }

    public XmlDom(String r2_String) throws SAXException {
        this(r2_String.getBytes());
    }

    public XmlDom(Element r1_Element) {
        this.a = r1_Element;
    }

    public XmlDom(byte[] r2_byteA) throws SAXException {
        this(new ByteArrayInputStream(r2_byteA));
    }

    private static XmlDom a(Node r3_Node, String r4_String, String r5_String, String r6_String) {
        if (r3_Node.getNodeType() != (short) 1) {
            return null;
        }
        Element r3_Element = (Element) r3_Node;
        if (r4_String != null && !r4_String.equals(r3_Element.getTagName())) {
            return null;
        }
        if (r5_String != null && !r3_Element.hasAttribute(r5_String)) {
            return null;
        }
        if (r6_String == null || r6_String.equals(r3_Element.getAttribute(r5_String))) {
            return new XmlDom(r3_Element);
        }
        return null;
    }

    private String a(Element r7_Element, int r8i) {
        String r1_String;
        try {
            String r1_String_2;
            XmlSerializer r2_XmlSerializer = Xml.newSerializer();
            Writer r3_Writer = new StringWriter();
            r2_XmlSerializer.setOutput(r3_Writer);
            r2_XmlSerializer.startDocument(AdViewNetFetchThread.NetEncoding, null);
            if (r8i > 0) {
                char[] r4_charA = new char[r8i];
                Arrays.fill(r4_charA, ' ');
                r1_String_2 = new String(r4_charA);
            } else {
                r1_String_2 = null;
            }
            a(this.a, r2_XmlSerializer, 0, r1_String_2);
            r2_XmlSerializer.endDocument();
            return r3_Writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String a(Node r3_Node) {
        String r0_String = null;
        switch (r3_Node.getNodeType()) {
            case XListViewFooter.STATE_NOMORE:
                r0_String = r3_Node.getNodeValue();
                if (r0_String != null) {
                    r0_String = r0_String.trim();
                }
                break;
            case XListViewFooter.STATE_NODATA:
                r0_String = r3_Node.getNodeValue();
                break;
        }
        return r0_String == null ? RContactStorage.PRIMARY_KEY : r0_String;
    }

    private static List<XmlDom> a(NodeList r3_NodeList, String r4_String, String r5_String, String r6_String) {
        List<XmlDom> r1_List_XmlDom = new ArrayList();
        int r0i = 0;
        while (r0i < r3_NodeList.getLength()) {
            XmlDom r2_XmlDom = a(r3_NodeList.item(r0i), r4_String, r5_String, r6_String);
            if (r2_XmlDom != null) {
                r1_List_XmlDom.add(r2_XmlDom);
            }
            r0i++;
        }
        return r1_List_XmlDom;
    }

    private void a(Element r8_Element, XmlSerializer r9_XmlSerializer, int r10i, String r11_String) throws Exception {
        int r1i;
        int r2i = 0;
        String r3_String = r8_Element.getTagName();
        a(r9_XmlSerializer, r10i, r11_String);
        r9_XmlSerializer.startTag(RContactStorage.PRIMARY_KEY, r3_String);
        if (r8_Element.hasAttributes()) {
            NamedNodeMap r4_NamedNodeMap = r8_Element.getAttributes();
            r1i = 0;
            while (r1i < r4_NamedNodeMap.getLength()) {
                Attr r0_Attr = (Attr) r4_NamedNodeMap.item(r1i);
                r9_XmlSerializer.attribute(RContactStorage.PRIMARY_KEY, r0_Attr.getName(), r0_Attr.getValue());
                r1i++;
            }
        }
        if (r8_Element.hasChildNodes()) {
            NodeList r4_NodeList = r8_Element.getChildNodes();
            r1i = 0;
            while (r2i < r4_NodeList.getLength()) {
                int r0i;
                Node r0_Node = r4_NodeList.item(r2i);
                switch (r0_Node.getNodeType()) {
                    case XListViewHeader.STATE_READY:
                        a((Element) r0_Node, r9_XmlSerializer, r10i + 1, r11_String);
                        r0i = r1i + 1;
                        r2i++;
                        r1i = r0i;
                        break;
                    case XListViewFooter.STATE_NOMORE:
                        r9_XmlSerializer.text(a(r0_Node));
                        r0i = r1i;
                        r2i++;
                        r1i = r0i;
                        break;
                    case XListViewFooter.STATE_NODATA:
                        r9_XmlSerializer.cdsect(a(r0_Node));
                        break;
                }
                r0i = r1i;
                r2i++;
                r1i = r0i;
            }
            if (r1i > 0) {
                a(r9_XmlSerializer, r10i, r11_String);
            }
        }
        r9_XmlSerializer.endTag(RContactStorage.PRIMARY_KEY, r3_String);
    }

    private void a(XmlSerializer r2_XmlSerializer, int r3i, String r4_String) throws Exception {
        if (r4_String != null) {
            r2_XmlSerializer.text("\n");
            int r0i = 0;
            while (r0i < r3i) {
                r2_XmlSerializer.text(r4_String);
                r0i++;
            }
        }
    }

    public String attr(String r2_String) {
        return this.a.getAttribute(r2_String);
    }

    public XmlDom child(String r2_String) {
        return child(r2_String, null, null);
    }

    public XmlDom child(String r3_String, String r4_String, String r5_String) {
        List r0_List = children(r3_String, r4_String, r5_String);
        return r0_List.size() == 0 ? null : (XmlDom) r0_List.get(0);
    }

    public List<XmlDom> children(String r2_String) {
        return children(r2_String, null, null);
    }

    public List<XmlDom> children(String r2_String, String r3_String, String r4_String) {
        return a(this.a.getChildNodes(), r2_String, r3_String, r4_String);
    }

    public Element getElement() {
        return this.a;
    }

    public XmlDom tag(String r4_String) {
        NodeList r2_NodeList = this.a.getElementsByTagName(r4_String);
        XmlDom r0_XmlDom = null;
        return (r2_NodeList == null || r2_NodeList.getLength() <= 0) ? r0_XmlDom : new XmlDom((Element) r2_NodeList.item(0));
    }

    public XmlDom tag(String r3_String, String r4_String, String r5_String) {
        List r0_List = tags(r3_String, r4_String, r5_String);
        return r0_List.size() == 0 ? null : (XmlDom) r0_List.get(0);
    }

    public List<XmlDom> tags(String r2_String) {
        return tags(r2_String, null, null);
    }

    public List<XmlDom> tags(String r3_String, String r4_String, String r5_String) {
        return a(this.a.getElementsByTagName(r3_String), null, r4_String, r5_String);
    }

    public String text() {
        int r0i = 0;
        NodeList r1_NodeList = this.a.getChildNodes();
        if (r1_NodeList.getLength() == 1) {
            return r1_NodeList.item(r0i).getNodeValue();
        }
        StringBuilder r2_StringBuilder = new StringBuilder();
        while (r0i < r1_NodeList.getLength()) {
            r2_StringBuilder.append(a(r1_NodeList.item(r0i)));
            r0i++;
        }
        return r2_StringBuilder.toString();
    }

    public String text(String r2_String) {
        XmlDom r0_XmlDom = child(r2_String);
        return r0_XmlDom == null ? null : r0_XmlDom.text();
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int r2i) {
        return a(this.a, r2i);
    }
}
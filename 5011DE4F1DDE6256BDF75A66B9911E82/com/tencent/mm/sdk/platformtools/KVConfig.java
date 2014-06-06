package com.tencent.mm.sdk.platformtools;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import qsbk.app.widget.listview.XListViewHeader;

public class KVConfig {
    private static boolean a;

    static {
        a = false;
    }

    private static void a(Map<String, String> r5_Map_String__String) {
        if (r5_Map_String__String == null || r5_Map_String__String.size() <= 0) {
            Log.v("MicroMsg.SDK.KVConfig", "empty values");
        } else {
            Iterator r2_Iterator = r5_Map_String__String.entrySet().iterator();
            while (r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                Log.v("MicroMsg.SDK.KVConfig", new StringBuilder("key=").append((String) r0_Entry.getKey()).append(" value=").append((String) r0_Entry.getValue()).toString());
            }
        }
    }

    private static void a(Map<String, String> r7_Map_String__String, String r8_String, Node r9_Node, int r10i) {
        int r1i = 0;
        if (r9_Node.getNodeName().equals("#text")) {
            r7_Map_String__String.put(r8_String, r9_Node.getNodeValue());
        } else if (r9_Node.getNodeName().equals("#cdata-section")) {
            r7_Map_String__String.put(r8_String, r9_Node.getNodeValue());
        } else {
            int r0i;
            String r2_String = r8_String + "." + r9_Node.getNodeName() + (r10i > 0 ? Integer.valueOf(r10i) : RContactStorage.PRIMARY_KEY);
            r7_Map_String__String.put(r2_String, r9_Node.getNodeValue());
            NamedNodeMap r3_NamedNodeMap = r9_Node.getAttributes();
            if (r3_NamedNodeMap != null) {
                r0i = 0;
                while (r0i < r3_NamedNodeMap.getLength()) {
                    Node r4_Node = r3_NamedNodeMap.item(r0i);
                    r7_Map_String__String.put(r2_String + ".$" + r4_Node.getNodeName(), r4_Node.getNodeValue());
                    r0i++;
                }
            }
            HashMap r3_HashMap = new HashMap();
            NodeList r4_NodeList = r9_Node.getChildNodes();
            while (r1i < r4_NodeList.getLength()) {
                Node r5_Node = r4_NodeList.item(r1i);
                r0i = Util.nullAsNil((Integer) r3_HashMap.get(r5_Node.getNodeName()));
                a(r7_Map_String__String, r2_String, r5_Node, r0i);
                r3_HashMap.put(r5_Node.getNodeName(), Integer.valueOf(r0i + 1));
                r1i++;
            }
        }
    }

    public static Map<String, String> parseIni(String r9_String) {
        if (r9_String == null || r9_String.length() <= 0) {
            return null;
        }
        Map<String, String> r0_Map_String__String = new HashMap();
        String[] r3_StringA = r9_String.split("\n");
        int r4i = r3_StringA.length;
        int r1i = 0;
        while (r1i < r4i) {
            String r5_String = r3_StringA[r1i];
            if (r5_String == null || r5_String.length() <= 0) {
                r1i++;
            } else {
                String[] r5_StringA = r5_String.trim().split("=", XListViewHeader.STATE_REFRESHING);
                if (r5_StringA == null || r5_StringA.length < 2) {
                    r1i++;
                } else {
                    String r6_String = r5_StringA[0];
                    Object r5_Object = r5_StringA[1];
                    if (r6_String == null || r6_String.length() <= 0 || (!r6_String.matches("^[a-zA-Z0-9_]*"))) {
                        r1i++;
                    } else {
                        r0_Map_String__String.put(r6_String, r5_Object);
                        r1i++;
                    }
                }
            }
        }
        if (!(a)) {
            return r0_Map_String__String;
        }
        a(r0_Map_String__String);
        return r0_Map_String__String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Map<String, String> parseXml(String r8_String, String r9_String, String r10_String) {
        /*
        r7 = 1;
        r6 = 0;
        r0 = 0;
        r1 = 60;
        r1 = r8.indexOf(r1);
        if (r1 >= 0) goto L_0x0013;
    L_0x000b:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "text not in xml format";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
    L_0x0012:
        return r0;
    L_0x0013:
        if (r1 <= 0) goto L_0x0028;
    L_0x0015:
        r2 = "MicroMsg.SDK.KVConfig";
        r3 = "fix xml header from + %d";
        r4 = new java.lang.Object[r7];
        r5 = java.lang.Integer.valueOf(r1);
        r4[r6] = r5;
        com.tencent.mm.sdk.platformtools.Log.w(r2, r3, r4);
        r8 = r8.substring(r1);
    L_0x0028:
        if (r8 == 0) goto L_0x0012;
    L_0x002a:
        r1 = r8.length();
        if (r1 <= 0) goto L_0x0012;
    L_0x0030:
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        r2 = r2.newDocumentBuilder();	 //Catch:{ ParserConfigurationException -> 0x0047 }
        if (r2 != 0) goto L_0x004c;
    L_0x003f:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "new Document Builder failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x0012;
    L_0x0047:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x004c:
        r3 = new org.xml.sax.InputSource;	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r4 = new java.io.ByteArrayInputStream;	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r5 = r8.getBytes();	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r4.<init>(r5);	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r3.<init>(r4);	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        if (r10 == 0) goto L_0x005f;
    L_0x005c:
        r3.setEncoding(r10);	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
    L_0x005f:
        r3 = r2.parse(r3);	 //Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r3.normalize();	 //Catch:{ DOMException -> 0x00d8, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
    L_0x0066:
        if (r3 != 0) goto L_0x0085;
    L_0x0068:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "new Document failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x0012;
    L_0x0070:
        r2 = move-exception;
        r3 = r0;
    L_0x0072:
        r2.printStackTrace();
        goto L_0x0066;
    L_0x0076:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x007b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x0085:
        r2 = r3.getDocumentElement();
        if (r2 != 0) goto L_0x0093;
    L_0x008b:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "getDocumentElement failed";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x0012;
    L_0x0093:
        if (r9 == 0) goto L_0x00ae;
    L_0x0095:
        r3 = r2.getNodeName();
        r3 = r9.equals(r3);
        if (r3 == 0) goto L_0x00ae;
    L_0x009f:
        r0 = "";
        a(r1, r0, r2, r6);
    L_0x00a4:
        r0 = a;
        if (r0 == 0) goto L_0x00ab;
    L_0x00a8:
        a(r1);
    L_0x00ab:
        r0 = r1;
        goto L_0x0012;
    L_0x00ae:
        r2 = r2.getElementsByTagName(r9);
        r3 = r2.getLength();
        if (r3 > 0) goto L_0x00c1;
    L_0x00b8:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "parse item null";
        com.tencent.mm.sdk.platformtools.Log.e(r1, r2);
        goto L_0x0012;
    L_0x00c1:
        r0 = r2.getLength();
        if (r0 <= r7) goto L_0x00ce;
    L_0x00c7:
        r0 = "MicroMsg.SDK.KVConfig";
        r3 = "parse items more than one";
        com.tencent.mm.sdk.platformtools.Log.w(r0, r3);
    L_0x00ce:
        r0 = "";
        r2 = r2.item(r6);
        a(r1, r0, r2, r6);
        goto L_0x00a4;
    L_0x00d8:
        r2 = move-exception;
        goto L_0x0072;
        */

    }
}
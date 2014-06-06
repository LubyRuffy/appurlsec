package com.qq.e.v2.util;

import android.util.Base64;
import java.io.File;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import qsbk.app.bean.Base;
import qsbk.app.widget.listview.XListViewHeader;

public class GDTST {
    private PublicKey a;
    private final boolean b;

    static final class a {
        public static final GDTST instance;

        static {
            instance = new GDTST();
        }
    }

    private GDTST() {
        boolean r0z;
        try {
            this.a = b("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKta2b5Vw5YkWHCAj4rJCwS227\r/35FZ29e4I6pS2B8zSq2RgBpXUuMg7oZF1Qt3x0iyg8PeyblyNeCRB6gIMehFThe\r1Y7m1FaQyaZp+CJYOTLM4/THKp9UndrEgJ/5a83vP1375YCV2lMvWARrNlBep4RN\rnESUJhQz58Gr/F39TwIDAQAB");
            r0z = true;
        } catch (Throwable th) {
            r0z = false;
        }
        this.b = r0z;
    }

    private String a(String r5_String) {
        if (this.a != null) {
            byte[] r0_byteA = Base64.decode(r5_String, 0);
            try {
                Cipher r1_Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                r1_Cipher.init(XListViewHeader.STATE_REFRESHING, this.a);
                return new String(r1_Cipher.doFinal(r0_byteA), Base.UTF8).trim();
            } catch (Throwable th) {
                GDTLogger.e("ErrorWhileVerifySigNature", th);
            }
        }
        return null;
    }

    private static PublicKey b(String r3_String) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(r3_String, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException e_2) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        } catch (NullPointerException e_3) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
    }

    public static GDTST getToolInstance() {
        return a.instance;
    }

    public boolean verify(String r5_String, String r6_String) {
        if (StringUtil.isEmpty(r6_String)) {
            return false;
        }
        if (!(this.b)) {
            return true;
        }
        String r1_String = a(r5_String);
        boolean r0z = r6_String.equals(r1_String);
        GDTLogger.d(new StringBuilder("Verify Result").append(r0z).append("src=").append(r6_String).append(" & target=").append(r1_String).toString());
        return r0z;
    }

    public boolean verifyFile(String r2_String, File r3_File) {
        return (r3_File == null || (!r3_File.exists())) ? false : verify(r2_String, Md5Util.encode(r3_File));
    }

    public boolean verifyString(String r2_String, String r3_String) {
        return verify(r2_String, Md5Util.encode(r3_String));
    }
}
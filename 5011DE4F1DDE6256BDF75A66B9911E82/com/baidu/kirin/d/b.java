package com.baidu.kirin.d;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static JSONObject a(Context r7_Context, String r8_String) {
        JSONObject r0_JSONObject = null;
        try {
            FileInputStream r1_FileInputStream = r7_Context.openFileInput(r8_String);
            if (r1_FileInputStream == null) {
                d.c("Invalid jsonobject file " + r8_String);
                return r0_JSONObject;
            } else {
                StringBuffer r2_StringBuffer = new StringBuffer();
                byte[] r3_byteA = new byte[4096];
                while (true) {
                    int r4i = r1_FileInputStream.read(r3_byteA);
                    if (r4i != -1) {
                        r2_StringBuffer.append(new String(r3_byteA, 0, r4i));
                    } else {
                        r1_FileInputStream.close();
                        if (r2_StringBuffer.length() != 0) {
                            try {
                                r0_JSONObject = new JSONObject(r2_StringBuffer.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return r0_JSONObject;
                        } else {
                            d.a(r8_String + " is empty! return null!");
                            return r0_JSONObject;
                        }
                    }
                }
            }
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
    }

    public static void a(Context r2_Context, String r3_String, JSONObject r4_JSONObject) {
        d.a("File is: " + r3_String + "; Content is: " + r4_JSONObject.toString());
        FileOutputStream r0_FileOutputStream = r2_Context.openFileOutput(r3_String, 0);
        r0_FileOutputStream.write(r4_JSONObject.toString().getBytes());
        r0_FileOutputStream.flush();
        r0_FileOutputStream.close();
    }
}
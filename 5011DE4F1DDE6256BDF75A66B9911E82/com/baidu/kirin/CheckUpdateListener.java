package com.baidu.kirin;

import com.baidu.kirin.objects.KirinCheckState;
import java.util.HashMap;

public interface CheckUpdateListener {
    public void checkUpdateResponse(KirinCheckState r1_KirinCheckState, HashMap<String, String> r2_HashMap_String__String);
}
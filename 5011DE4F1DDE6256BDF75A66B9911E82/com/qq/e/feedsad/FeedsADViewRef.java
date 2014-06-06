package com.qq.e.feedsad;

import android.app.Activity;
import android.view.View;
import java.util.Map;

public interface FeedsADViewRef {
    public Map<String, Object> getADInfo();

    public int getSuggestADPosition();

    public View getView(View r1_View, Activity r2_Activity);

    public void release();

    public void setADBackGroundColor(int r1i);

    public void setStyle(String r1_String);

    public void setStyleAndADBackGround(String r1_String, int r2i);
}
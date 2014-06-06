package com.qq.e.feeds;

import android.view.View;

public interface FeedsADViewRef {
    public void free();

    public int getSuggestADPosition();

    public View getView();

    public void onScrollIn();

    public void setADBackGroundColor(int r1i);

    public void setStyle(String r1_String);

    public void setStyleAndADBackGround(String r1_String, int r2i);
}
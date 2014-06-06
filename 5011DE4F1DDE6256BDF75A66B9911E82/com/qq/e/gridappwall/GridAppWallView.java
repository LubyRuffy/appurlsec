package com.qq.e.gridappwall;

import android.view.View;

public interface GridAppWallView {
    public GridAppWallListener getAdListener();

    public void setAdListener(GridAppWallListener r1_GridAppWallListener);

    public void show();

    public void showRelativeTo(int r1i, int r2i);

    public void showRelativeTo(View r1_View);
}
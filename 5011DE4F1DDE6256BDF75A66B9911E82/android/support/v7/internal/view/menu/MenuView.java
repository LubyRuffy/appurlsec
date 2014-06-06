package android.support.v7.internal.view.menu;

import android.graphics.drawable.Drawable;

public interface MenuView {

    public static interface ItemView {
        public MenuItemImpl getItemData();

        public void initialize(MenuItemImpl r1_MenuItemImpl, int r2i);

        public boolean prefersCondensedTitle();

        public void setCheckable(boolean r1z);

        public void setChecked(boolean r1z);

        public void setEnabled(boolean r1z);

        public void setIcon(Drawable r1_Drawable);

        public void setShortcut(boolean r1z, char r2c);

        public void setTitle(CharSequence r1_CharSequence);

        public boolean showsIcon();
    }

    public int getWindowAnimations();

    public void initialize(MenuBuilder r1_MenuBuilder);
}
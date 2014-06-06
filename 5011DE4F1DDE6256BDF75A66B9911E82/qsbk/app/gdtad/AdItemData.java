package qsbk.app.gdtad;

import com.qq.e.feedsad.FeedsADViewRef;

public class AdItemData {
    private FeedsADViewRef a;
    private String b;

    public String getName() {
        return this.b;
    }

    public FeedsADViewRef getView() {
        return this.a;
    }

    public void setName(String r1_String) {
        this.b = r1_String;
    }

    public void setView(FeedsADViewRef r1_FeedsADViewRef) {
        this.a = r1_FeedsADViewRef;
    }
}
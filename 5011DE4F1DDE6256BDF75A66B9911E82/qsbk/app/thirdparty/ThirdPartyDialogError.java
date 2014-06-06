package qsbk.app.thirdparty;

public class ThirdPartyDialogError extends Throwable {
    private int a;
    private String b;

    public ThirdPartyDialogError(String r1_String, int r2i, String r3_String) {
        super(r1_String);
        this.a = r2i;
        this.b = r3_String;
    }
}
package qsbk.app.utils;

import android.text.method.ReplacementTransformationMethod;

public class MobileTransformationMethod extends ReplacementTransformationMethod {
    protected char[] getOriginal() {
        char[] r0_charA = new char[1];
        r0_charA[0] = '\r';
        return r0_charA;
    }

    protected char[] getReplacement() {
        char[] r0_charA = new char[1];
        r0_charA[0] = '\ufeff';
        return r0_charA;
    }
}
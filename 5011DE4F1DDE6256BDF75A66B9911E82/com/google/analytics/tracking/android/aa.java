package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.MetaModel.Formatter;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.tauth.Constants;
import org.apache.cordova.Globalization;
import qsbk.app.database.QsbkDatabase;

// compiled from: MetaModelInitializer.java
class aa {
    private static final Formatter a;
    private static final Formatter b;

    static {
        a = new ab();
        b = new ac();
    }

    public static void set(MetaModel r5_MetaModel) {
        r5_MetaModel.addField(ModelFields.API_VERSION, "v", null, null);
        r5_MetaModel.addField("libraryVersion", "_v", null, null);
        r5_MetaModel.addField(ModelFields.ANONYMIZE_IP, "aip", "0", a);
        r5_MetaModel.addField(ModelFields.TRACKING_ID, "tid", null, null);
        r5_MetaModel.addField(ModelFields.HIT_TYPE, QsbkDatabase.T, null, null);
        r5_MetaModel.addField(ModelFields.SESSION_CONTROL, "sc", null, null);
        r5_MetaModel.addField("adSenseAdMobHitId", QsbkDatabase.A, null, null);
        r5_MetaModel.addField(Constants.PARAM_TITLE, "dt", null, null);
        r5_MetaModel.addField(ModelFields.REFERRER, "dr", null, null);
        r5_MetaModel.addField(ModelFields.LANGUAGE, "ul", null, null);
        r5_MetaModel.addField(ModelFields.ENCODING, "de", null, null);
        r5_MetaModel.addField(ModelFields.PAGE, "dp", null, null);
        r5_MetaModel.addField(ModelFields.SCREEN_COLORS, "sd", null, null);
        r5_MetaModel.addField(ModelFields.SCREEN_RESOLUTION, "sr", null, null);
        r5_MetaModel.addField(ModelFields.VIEWPORT_SIZE, "vp", null, null);
        r5_MetaModel.addField(ModelFields.JAVA_ENABLED, "je", "1", a);
        r5_MetaModel.addField(ModelFields.FLASH_VERSION, "fl", null, null);
        r5_MetaModel.addField(ModelFields.CLIENT_ID, "cid", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_NAME, "cn", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_SOURCE, "cs", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_MEDIUM, "cm", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_KEYWORD, "ck", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_CONTENT, "cc", null, null);
        r5_MetaModel.addField(ModelFields.CAMPAIGN_ID, "ci", null, null);
        r5_MetaModel.addField(ModelFields.GCLID, ModelFields.GCLID, null, null);
        r5_MetaModel.addField(ModelFields.DCLID, ModelFields.DCLID, null, null);
        r5_MetaModel.addField(ModelFields.GMOB_T, ModelFields.GMOB_T, null, null);
        r5_MetaModel.addField(ModelFields.EVENT_CATEGORY, "ec", null, null);
        r5_MetaModel.addField(ModelFields.EVENT_ACTION, "ea", null, null);
        r5_MetaModel.addField(ModelFields.EVENT_LABEL, "el", null, null);
        r5_MetaModel.addField(ModelFields.EVENT_VALUE, "ev", null, null);
        r5_MetaModel.addField(ModelFields.NON_INTERACTION, "ni", "0", a);
        r5_MetaModel.addField(ModelFields.SOCIAL_NETWORK, "sn", null, null);
        r5_MetaModel.addField(ModelFields.SOCIAL_ACTION, "sa", null, null);
        r5_MetaModel.addField(ModelFields.SOCIAL_TARGET, "st", null, null);
        r5_MetaModel.addField(Constants.PARAM_APPNAME, "an", null, null);
        r5_MetaModel.addField(ModelFields.APP_VERSION, "av", null, null);
        r5_MetaModel.addField(Constants.PARAM_COMMENT, "cd", null, null);
        r5_MetaModel.addField(ModelFields.APP_ID, "aid", null, null);
        r5_MetaModel.addField(ModelFields.APP_INSTALLER_ID, "aiid", null, null);
        r5_MetaModel.addField(ModelFields.TRANSACTION_ID, "ti", null, null);
        r5_MetaModel.addField(ModelFields.TRANSACTION_AFFILIATION, "ta", null, null);
        r5_MetaModel.addField(ModelFields.TRANSACTION_SHIPPING, "ts", null, null);
        r5_MetaModel.addField(ModelFields.TRANSACTION_TOTAL, LocaleUtil.TURKEY, null, null);
        r5_MetaModel.addField(ModelFields.TRANSACTION_TAX, "tt", null, null);
        r5_MetaModel.addField(Globalization.CURRENCYCODE, "cu", null, null);
        r5_MetaModel.addField(ModelFields.ITEM_PRICE, "ip", null, null);
        r5_MetaModel.addField(ModelFields.ITEM_CODE, "ic", null, null);
        r5_MetaModel.addField(ModelFields.ITEM_NAME, "in", null, null);
        r5_MetaModel.addField(ModelFields.ITEM_CATEGORY, "iv", null, null);
        r5_MetaModel.addField(ModelFields.ITEM_QUANTITY, "iq", null, null);
        r5_MetaModel.addField(ModelFields.EX_DESCRIPTION, "exd", null, null);
        r5_MetaModel.addField(ModelFields.EX_FATAL, "exf", "1", a);
        r5_MetaModel.addField(ModelFields.TIMING_VAR, "utv", null, null);
        r5_MetaModel.addField(ModelFields.TIMING_VALUE, "utt", null, null);
        r5_MetaModel.addField(ModelFields.TIMING_CATEGORY, "utc", null, null);
        r5_MetaModel.addField(ModelFields.TIMING_LABEL, "utl", null, null);
        r5_MetaModel.addField(ModelFields.SAMPLE_RATE, "sf", "100", b);
    }
}
package qsbk.app.utils;

import com.tencent.tauth.Constants;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class DataParse {

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[qsbk.app.utils.DataParse.StampContent.values().length];
            try {
                a[qsbk.app.utils.DataParse.StampContent.POLITICS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[qsbk.app.utils.DataParse.StampContent.VULGAR.ordinal()] = 2;
            } catch (NoSuchFieldError e_2) {
            }
            try {
                a[qsbk.app.utils.DataParse.StampContent.OLD.ordinal()] = 3;
            } catch (NoSuchFieldError e_3) {
            }
            try {
                a[qsbk.app.utils.DataParse.StampContent.UNGELIEVEABLE.ordinal()] = 4;
            } catch (NoSuchFieldError e_4) {
            }
            try {
                a[qsbk.app.utils.DataParse.StampContent.FUNNY.ordinal()] = 5;
            } catch (NoSuchFieldError e_5) {
            }
            a[qsbk.app.utils.DataParse.StampContent.SKIP.ordinal()] = 6;
        }
    }

    public static enum StampContent {
        NONE,
        POLITICS,
        VULGAR,
        OLD,
        UNGELIEVEABLE,
        FUNNY,
        SKIP;


        static {
            NONE = new qsbk.app.utils.DataParse.StampContent("NONE", 0);
            POLITICS = new qsbk.app.utils.DataParse.StampContent("POLITICS", 1);
            VULGAR = new qsbk.app.utils.DataParse.StampContent("VULGAR", 2);
            OLD = new qsbk.app.utils.DataParse.StampContent("OLD", 3);
            UNGELIEVEABLE = new qsbk.app.utils.DataParse.StampContent("UNGELIEVEABLE", 4);
            FUNNY = new qsbk.app.utils.DataParse.StampContent("FUNNY", 5);
            SKIP = new qsbk.app.utils.DataParse.StampContent("SKIP", 6);
            qsbk.app.utils.DataParse.StampContent[] r0_qsbk_app_utils_DataParse_StampContentA = new qsbk.app.utils.DataParse.StampContent[7];
            r0_qsbk_app_utils_DataParse_StampContentA[0] = NONE;
            r0_qsbk_app_utils_DataParse_StampContentA[1] = POLITICS;
            r0_qsbk_app_utils_DataParse_StampContentA[2] = VULGAR;
            r0_qsbk_app_utils_DataParse_StampContentA[3] = OLD;
            r0_qsbk_app_utils_DataParse_StampContentA[4] = UNGELIEVEABLE;
            r0_qsbk_app_utils_DataParse_StampContentA[5] = FUNNY;
            r0_qsbk_app_utils_DataParse_StampContentA[6] = SKIP;
            a = r0_qsbk_app_utils_DataParse_StampContentA;
        }
    }

    public static int result(StampContent r3_StampContent) {
        switch (AnonymousClass_1.a[r3_StampContent.ordinal()]) {
            case XListViewHeader.STATE_READY:
                return RequestListener.DEFAULT_LOADED_SIZE;
            case XListViewHeader.STATE_REFRESHING:
                return Constants.ERROR_UNKNOWN;
            case XListViewFooter.STATE_NOMORE:
                return Constants.ERROR_PARAM;
            case XListViewFooter.STATE_NODATA:
                return Constants.ERROR_IO_InterruptedIOException_ConnectTimeoutException;
            case ShareUtils.SHARE_SMS:
                return 1;
        }
        return 0;
    }
}
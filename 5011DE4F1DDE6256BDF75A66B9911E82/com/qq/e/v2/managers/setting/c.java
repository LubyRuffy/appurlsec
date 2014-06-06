package com.qq.e.v2.managers.setting;

import com.baidu.kirin.KirinConfig;
import com.qiubai.library.adview.util.AdViewUtil;
import com.qq.e.v2.constants.Constants.KEYS;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

public final class c extends b {
    public c() {
        a(KEYS.SDKServerReportSamplingRate, Integer.valueOf(1));
        a(KEYS.DownloaderCCTCount, Integer.valueOf(XListViewHeader.STATE_REFRESHING));
        a(KEYS.DOWNLOAD_AUTO_RECOVER, Boolean.valueOf(true));
        a(KEYS.DOWNLOAD_GiveupTime_InDay, Integer.valueOf(ShareUtils.SHARE_SMS));
        a(KEYS.BannerPageUrl, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/banner.html");
        a(KEYS.BannerAPKPopPageUrl, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/bannerapkpop.html");
        a(KEYS.Banner_RF, Integer.valueOf(30000));
        a(KEYS.BannerRollAnimation, Integer.valueOf(0));
        a(KEYS.SPLASH_TMPL, "<html><head><meta http-equiv='X-UA-Compatible' content='edge' /><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' /><meta name='format-detection' content='telephone=no' /><meta name='apple-mobile-web-app-capable' content='yes' /><style type='text/css'>*{padding:0;margin: 0;border: none;overflow: hidden;text-align:center} .icon_close{background:url(http://qzonestyle.gtimg.cn/open_proj/img/gdt/sdk_popup.png) no-repeat;background-size: 300px 140px; width: 29px; height: 30px;position: absolute;right:2px;top:2px;background-position: 0 -110px;text-indent: -9999px;overflow: hidden;  z-index: 100; }*{-webkit-tap-highlight-color:rgba(0,0,0,0);}</style><body style='overflow:hidden;background:#efefef'><img src='${src}' onload='imgCB.onImageOK()' onclick='imgCB.onImageClick()' onerror='imgCB.onImageFail()' style='height:100%'/><a class='icon_close' onclick='imgCB.onCloseClick()'></a></body></html>");
        a(KEYS.SPLASH_LOADTIMEOUT, Integer.valueOf(KirinConfig.CONNECT_TIME_OUT));
        a(KEYS.SPLASH_EXPOSURE_TIME, Integer.valueOf(KirinConfig.READ_TIME_OUT));
        a(KEYS.SPLASH_NETWORK_PERMISION, Integer.valueOf(AdViewUtil.NETWORK_TYPE_VPON));
        a(KEYS.SPLASH_MAX_REQUEST_NUM, Integer.valueOf(100));
        a(KEYS.GridPageUrl, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/gridappwall.html");
        a(KEYS.InterstitialPageURL, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/interstitial.html");
        a(KEYS.FeedsADURL, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/feeds.html");
        a(KEYS.FeedsADURL2, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/feeds2.html");
        a(KEYS.FeedsTplID, "FeedsTemplateA");
        a(KEYS.FeedsStyleID, "skin01");
        a(KEYS.FeedsADExposureTime, Integer.valueOf(500));
        a(KEYS.AppWallPageURL, "http://qzonestyle.gtimg.cn/qzone/biz/gdt/mob/sdk/v2/appwall.html");
    }
}
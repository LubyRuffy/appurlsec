package qsbk.app;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.utils.SharePreferenceUtils;

public class Constants {
    public static final String AD_KEY = "SDK20131629040836yiphlkzx6gsmufr";
    public static final String APPINFO = "http://m2.qiushibaike.com/appinfo";
    public static final String APP_ID = "wxfa4ebf0331513c10";
    public static final String APP_LOGS = "http://log.qiushibaike.com/applog";
    public static String ARATAR_URL = null;
    public static final String ARTICLE_CREATE = "http://m2.qiushibaike.com/article/create";
    public static final String ARTICLE_STATE_PENDING = "pending";
    public static final String ARTICLE_STATE_PRIVATE = "private";
    public static final String ARTICLE_STATE_PUBLISH = "publish";
    public static final String AUDIT = "http://insp.qiushibaike.com/review?sid=%1$d";
    public static final String COLLECT = "http://m2.qiushibaike.com/collect/%1$s";
    public static final String COLLECT_LIST = "http://m2.qiushibaike.com/collect/list";
    public static final String COMMENT = "http://m2.qiushibaike.com/article/%1$s/comments";
    public static final String COMMENT_CREATE = "http://m2.qiushibaike.com/article/%1$s/comment/create";
    public static final String CONFIG = "http://m2.qiushibaike.com/config";
    public static final String CONTENT_CACHE_PATH = "/content";
    public static final String CONTENT_DOMAINS = "http://m2.qiushibaike.com/";
    public static String CONTENT_IMAGE_URL = null;
    public static final int CommentCount = 50;
    public static final String DAY = "http://m2.qiushibaike.com/article/list/day";
    public static final String DELETE_CREATE = "http://m2.qiushibaike.com/user/my/articles/%1$s";
    public static final String FEEDBACK = "http://m2.qiushibaike.com/feedback";
    public static final int GA_INTERVAL = 30;
    public static final String HISTORY = "http://m2.qiushibaike.com/article/history";
    public static final String HOT_IMAGES = "http://m2.qiushibaike.com/article/list/imgrank";
    public static final String IMAGES = "http://m2.qiushibaike.com/article/list/images";
    public static final String IMG_CACHE_PATH_AVATAR = "/avatar";
    public static final String IMG_CACHE_PATH_MEDIUM = "/medium";
    public static final String IMG_CACHE_PATH_PRE = "/pre";
    public static String IMG_DOMAINS = null;
    public static final String IMG_DOMAIN_BACKUP = "http://img0.qiushibaike.com/";
    public static final String LATEST = "http://m2.qiushibaike.com/article/list/latest";
    public static final String LIKE = "http://m2.qiushibaike.com/user/my/like";
    public static final String LOGIN = "http://m2.qiushibaike.com/user/signin";
    public static final String MONTH = "http://m2.qiushibaike.com/article/list/month";
    public static final String MYCONTENTS = "http://m2.qiushibaike.com/user/my/articles";
    public static final String ONES_ARTICLES = "http://m2.qiushibaike.com/user/%s/articles";
    public static final String PARTICIPATE = "http://m2.qiushibaike.com/user/my/participate";
    public static final String PUSH_DOMAINS = "http://push.qiushibaike.com/push";
    public static final String REGISTER = "http://m2.qiushibaike.com/user/v2/signup";
    public static final String REPORT_ARTICLE = "http://m2.qiushibaike.com/article/%1$s/report";
    public static final String REPORT_COMMENT = "http://m2.qiushibaike.com/comment/%1$s/report";
    public static final String SHARE_URL = "http://share.qiushibaike.com/article/%1$s/share";
    public static final String SUGGEST = "http://m2.qiushibaike.com/article/list/suggest";
    public static final String THIRDPARTY_LOGIN = "http://m2.qiushibaike.com/user/v2/signin";
    public static final String UPDATE_AVATAR = "http://m2.qiushibaike.com/user/my/avatar";
    public static final long UPDATE_INTERVAL = 86400000;
    public static String UPDATE_URL = null;
    public static final String UPDATE_USERINFO = "http://m2.qiushibaike.com/user/my/edit";
    public static final String UPDATE_USERINFO_1 = "http://nearby.qiushibaike.com/user/%1$s/detail";
    public static final String USERINFO = "http://m2.qiushibaike.com/user/my/info";
    public static final String USER_AVAILABLE = "http://m2.qiushibaike.com/user/available";
    public static final String VERIFY = "http://m2.qiushibaike.com/user/verify";
    public static final long VERIFY_INTERVAL = 259200000;
    public static final String VOTE_DOMAINS = "http://vote.qiushibaike.com/";
    public static final String VOTE_QUEUE = "http://vote.qiushibaike.com/vote_queue";
    public static final String WEEK = "http://m2.qiushibaike.com/article/list/week";
    public static final int cacheDrawableNum = 20;
    public static String change = null;
    public static int localVersion = 0;
    public static String localVersionName = null;
    public static final int pageCount = 30;
    public static int serverVersion;
    public static String serviceVersionName;

    static {
        localVersion = 58;
        localVersionName = "2.8.8";
        serverVersion = 58;
        serviceVersionName = RContactStorage.PRIMARY_KEY;
        IMG_DOMAINS = "http://pic.qiushibaike.com/";
        CONTENT_IMAGE_URL = IMG_DOMAINS + "system/pictures/%1$s/%2$s/%3$s/%4$s";
        ARATAR_URL = IMG_DOMAINS + "system/avtnew/%1$s/%2$s/%3$s/%4$s";
        UPDATE_URL = RContactStorage.PRIMARY_KEY;
    }

    public static void updateImageDomains() {
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("image-domain");
        if (!TextUtils.isEmpty(r0_String)) {
            IMG_DOMAINS = r0_String;
        }
        ARATAR_URL = IMG_DOMAINS + "system/avtnew/%1$s/%2$s/%3$s/%4$s";
        CONTENT_IMAGE_URL = IMG_DOMAINS + "system/pictures/%1$s/%2$s/%3$s/%4$s";
    }
}
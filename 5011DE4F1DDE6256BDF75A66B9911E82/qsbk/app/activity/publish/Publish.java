package qsbk.app.activity.publish;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.conversation.RConversation;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.adapter.MyContentsAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.push.Utils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class Publish extends SecDefaultActivity implements OnClickListener {
    private Boolean A;
    private int B;
    private String C;
    private String D;
    private String E;
    EditText n;
    ImageView o;
    ImageButton p;
    ImageButton q;
    ProgressBar r;
    boolean s;
    LinearLayout t;
    Handler u;
    Handler v;
    Runnable w;
    private String x;
    private String y;
    private Boolean z;

    public Publish() {
        this.z = Boolean.valueOf(false);
        this.A = Boolean.valueOf(true);
        this.B = -1;
        this.C = RContactStorage.PRIMARY_KEY;
        this.D = null;
        this.s = false;
        this.E = "draftContent";
        this.u = new a(this);
        this.v = new Handler();
        this.w = new b(this);
    }

    private File a(Context r4_Context) {
        File r0_File = new File(Environment.getExternalStorageDirectory(), r4_Context.getPackageName());
        if (!r0_File.exists()) {
            r0_File.mkdir();
        }
        return new File(r0_File, "sendpic.jpg");
    }

    private void a(JSONObject r4_JSONObject) {
        try {
            JSONObject r0_JSONObject = r4_JSONObject.getJSONObject("article");
            r0_JSONObject.put(QsbkDatabase.STATE, MyContentsAdapter.PENDING);
            QsbkApp.newArticle = new Article(r0_JSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (QiushibaikeException e_2) {
            e_2.printStackTrace();
        }
    }

    private void b(boolean r4z) {
        if (r4z) {
            this.o.setVisibility(0);
            this.p.setVisibility(Base64.DONT_BREAK_LINES);
            this.q.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.o.setVisibility(Base64.DONT_BREAK_LINES);
            this.p.setVisibility(0);
            this.q.setVisibility(0);
        }
    }

    private boolean d() {
        return "article".equals(this.x);
    }

    private void e() {
        if (QsbkApp.currentDataSource == null || QsbkApp.currentSelectItem < 0 || QsbkApp.currentDataSource.size() <= QsbkApp.currentSelectItem) {
        } else {
            Article r0_Article = (Article) QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem);
            r0_Article.comment_count++;
        }
    }

    private void h() {
        this.o.setImageBitmap(null);
        QsbkApp r0_QsbkApp = (QsbkApp) getApplication();
        if (r0_QsbkApp.getWaitSendBitmap() == null || r0_QsbkApp.getWaitSendBitmap().isRecycled()) {
        } else {
            r0_QsbkApp.getWaitSendBitmap().recycle();
            r0_QsbkApp.setWaitSendBitmap(null);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public String getCustomerTitle() {
        return d() ? "\u7cd7\u4e8b\u6295\u7a3f" : "\u6dfb\u52a0\u8bc4\u8bba";
    }

    public HashMap<String, Object> getPostParams() {
        HashMap<String, Object> r0_HashMap_String__Object = new HashMap();
        r0_HashMap_String__Object.put(Utils.RESPONSE_CONTENT, this.n.getText().toString().trim());
        r0_HashMap_String__Object.put("anonymous", this.z);
        if (d()) {
            r0_HashMap_String__Object.put("allow_comment", this.A);
        }
        return r0_HashMap_String__Object;
    }

    public String getSendHint() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        return d() ? getResources().getString(R.string.send_succ) : "\u8bc4\u8bba\u56de\u590d\u6210\u529f!";
    }

    public String getUrl() {
        if (d()) {
            this.T.trackView("/\u53d1\u7cd7\u4e8b/");
        } else {
            this.T.trackView("/\u53d1\u8bc4\u8bba/");
        }
        if (d()) {
            return "http://m2.qiushibaike.com/article/create?imgsrc=" + this.B;
        }
        String r0_String = Constants.COMMENT_CREATE;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = this.y;
        return String.format(r0_String, r1_ObjectA);
    }

    public void initView() {
        this.t = (LinearLayout) findViewById(R.id.rightBtnLayout);
        this.R.setText(getCustomerTitle());
        if (UIHelper.isNightTheme()) {
            this.R.setTextColor(getResources().getColor(R.color.title_post_night));
        } else {
            this.R.setTextColor(getResources().getColor(R.color.title_post));
        }
        this.n = (EditText) findViewById(R.id.content);
        this.r = (ProgressBar) findViewById(R.id.topLoading);
        this.o = (ImageView) findViewById(R.id.addImage);
        this.o.setOnClickListener(this);
        this.p = (ImageButton) findViewById(R.id.addImageFromAlbum);
        this.p.setOnTouchListener(QsbkApp.TouchDark);
        this.p.setOnClickListener(this);
        this.q = (ImageButton) findViewById(R.id.addImageFromCamera);
        this.q.setOnTouchListener(QsbkApp.TouchDark);
        this.q.setOnClickListener(this);
        if (d()) {
            if (!TextUtils.isEmpty(this.E)) {
                CharSequence r0_CharSequence = SharePreferenceUtils.getSharePreferencesValue(this.E);
                if (!TextUtils.isEmpty(r0_CharSequence)) {
                    this.n.setText(r0_CharSequence);
                    this.n.setSelection(r0_CharSequence.length());
                    SharePreferenceUtils.remove(this.E);
                }
            }
            this.p.setVisibility(0);
            this.q.setVisibility(0);
            this.n.setHint(R.string.content_hint);
        } else {
            String r0_String = SharePreferenceUtils.getSharePreferencesValue(this.E);
            if (TextUtils.isEmpty(r0_String) || r0_String.length() <= 2) {
                SharePreferenceUtils.remove(this.E);
            } else {
                this.n.setText(r0_String);
                this.n.setSelection(r0_String.length());
                SharePreferenceUtils.remove(this.E);
            }
            this.p.setVisibility(Base64.DONT_BREAK_LINES);
            this.q.setVisibility(Base64.DONT_BREAK_LINES);
            this.n.setHint(R.string.comment_hint);
        }
        if (UIHelper.isNightTheme()) {
            this.P.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.P.setBackgroundResource(R.drawable.icon_close_large);
            findViewById(R.id.topbar_layout).setBackgroundDrawable(null);
        }
        this.P.setOnClickListener(new c(this));
        if (UIHelper.isNightTheme()) {
            this.Q.setBackgroundResource(R.drawable.icon_send_night);
        } else {
            this.Q.setBackgroundResource(R.drawable.icon_send);
        }
        this.Q.setOnClickListener(new e(this));
    }

    protected void onActivityResult(int r5i, int r6i, Intent r7_Intent) {
        if (r6i == -1) {
            if (r5i == 2) {
                if (r7_Intent != null) {
                    this.B = 0;
                    this.C = r7_Intent.getData().toString();
                }
            } else if (r5i == 1) {
                this.B = 1;
                this.C = a((Context)this).getPath();
            } else if (r5i == 3) {
                this.D = this.C;
            }
        }
        if (TextUtils.isEmpty(this.C) || r7_Intent == null || r5i == 3) {
            if (r5i == 3 && !TextUtils.isEmpty(this.D) && r6i == 0) {
                this.C = this.D;
            }
        } else {
            Intent r0_Intent = new Intent(this, Publish_Image.class);
            r0_Intent.putExtra("picpath", this.C);
            startActivityForResult(r0_Intent, XListViewFooter.STATE_NOMORE);
            if (r5i == 3 || !TextUtils.isEmpty(this.D) || r6i == 0) {
            } else {
                this.C = this.D;
            }
        }
    }

    public void onClick(View r4_View) {
        switch (r4_View.getId()) {
            case R.id.addImageFromAlbum:
                startAlbum();
                break;
            case R.id.addImageFromCamera:
                startCamera();
                break;
            case R.id.addImage:
                int r0i = R.array.Image_SelectWay;
                if (this.s) {
                    r0i = R.array.Image_EditWay;
                }
                new Builder(this).setTitle("\u53d1\u9001\u56fe\u7247").setItems(r0i, new h(this)).show();
                break;
        }
    }

    protected void onCreate(Bundle r5_Bundle) {
        super.onCreate(r5_Bundle);
        setContentView(R.layout.activity_publish_content);
        c();
        this.x = getIntent().getStringExtra(RConversation.COL_FLAG);
        if (!d()) {
            this.y = getIntent().getStringExtra("currentQsId");
            this.E = "draftCommentContent_" + this.y;
        }
        initView();
        if (d()) {
            this.v.postDelayed(this.w, Util.MILLSECONDS_OF_MINUTE);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        QsbkApp r0_QsbkApp = (QsbkApp) getApplication();
        if (r0_QsbkApp.getWaitSendBitmap() == null || r0_QsbkApp.getWaitSendBitmap().isRecycled()) {
        } else {
            r0_QsbkApp.getWaitSendBitmap().recycle();
            r0_QsbkApp.setWaitSendBitmap(null);
        }
    }

    protected void onPause() {
        this.v.removeCallbacks(this.w);
        String r0_String = this.n.getText().toString();
        String r1_String = SharePreferenceUtils.getSharePreferencesValue(this.E);
        if (r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals(r1_String)) {
            super.onPause();
        } else {
            SharePreferenceUtils.setSharePreferencesValue(this.E, r0_String);
            super.onPause();
        }
    }

    protected void onResume() {
        QsbkApp r0_QsbkApp = (QsbkApp) getApplication();
        if (r0_QsbkApp.getWaitSendBitmap() == null || r0_QsbkApp.getWaitSendBitmap().isRecycled()) {
            super.onResume();
        } else {
            b(true);
            this.o.setImageBitmap(r0_QsbkApp.getWaitSendBitmap());
            this.s = true;
            super.onResume();
        }
    }

    public void startAlbum() {
        Intent r0_Intent = new Intent("android.intent.action.GET_CONTENT");
        r0_Intent.addCategory("android.intent.category.OPENABLE");
        r0_Intent.setType("image/*");
        startActivityForResult(r0_Intent, XListViewHeader.STATE_REFRESHING);
    }

    public void startCamera() {
        Intent r0_Intent = new Intent("android.media.action.IMAGE_CAPTURE");
        r0_Intent.putExtra("output", Uri.fromFile(a((Context)this)));
        startActivityForResult(r0_Intent, 1);
    }

    public void submitContent() {
        this.r.setVisibility(0);
        this.t.setVisibility(Base64.DONT_BREAK_LINES);
        new g(this, "qbk-Publish").start();
    }
}
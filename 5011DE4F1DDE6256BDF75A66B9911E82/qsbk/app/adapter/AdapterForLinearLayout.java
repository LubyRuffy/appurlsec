package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.message.ChatMsgSource;
import qsbk.app.push.Utils;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.StringUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class AdapterForLinearLayout extends BaseAdapter {
    ColorStateList a;
    ColorStateList b;
    ColorStateList c;
    Handler d;
    private Context e;
    private LayoutInflater f;
    private LinkedList<JSONObject> g;
    private HashMap<Integer, JSONObject> h;
    private String i;

    class a implements OnLongClickListener {
        private int b;
        private JSONObject c;

        public a(int r2i, JSONObject r3_JSONObject) {
            this.b = r2i;
            this.c = r3_JSONObject;
        }

        public boolean onLongClick(View r6_View) {
            StringBuffer r1_StringBuffer = new StringBuffer("\u4e3e\u62a5" + this.b + "\u697c\uff1a");
            try {
                r1_StringBuffer.append(this.c.getString(Utils.RESPONSE_CONTENT));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Builder r0_Builder = new Builder(AdapterForLinearLayout.this.e).setTitle(r1_StringBuffer.toString());
            CharSequence[] r1_CharSequenceA = new CharSequence[4];
            r1_CharSequenceA[0] = "\u8fb1\u9a82";
            r1_CharSequenceA[1] = "\u8272\u60c5";
            r1_CharSequenceA[2] = "\u5e7f\u544a";
            r1_CharSequenceA[3] = "\u6d6a\u8d39\u697c\u5c42";
            r0_Builder.setItems(r1_CharSequenceA, new f(this)).setNegativeButton("\u53d6\u6d88", new e(this)).show();
            return false;
        }
    }

    class b implements OnClickListener {
        String a;
        String b;
        String c;

        public b(String r3_String, String r4_String, String r5_String) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.a = r3_String;
            this.b = r4_String;
            this.c = r5_String;
        }

        public void onClick(View r6_View) {
            if (QsbkApp.currentUser != null) {
                Intent r0_Intent = new Intent(AdapterForLinearLayout.this.e, OneProfileActivity.class);
                if (QsbkApp.currentUser.userId.equals(this.a)) {
                    r0_Intent.putExtra(OneProfileActivity.USER, QsbkApp.currentUser.toString());
                } else {
                    r0_Intent.putExtra(QsbkDatabase.USER_ID, this.a);
                    r0_Intent.putExtra(OneProfileActivity.USER_ICON_URL, this.c);
                    r0_Intent.putExtra(OneProfileActivity.USER_NAME, this.b);
                    r0_Intent.putExtra(OneProfileActivity.SELECTED_TAB_ID, XListViewHeader.STATE_REFRESHING);
                    r0_Intent.putExtra(OneProfileActivity.MSG_SOURCE, new ChatMsgSource(3, this.a, AdapterForLinearLayout.this.i).encodeToJsonObject().toString());
                }
                AdapterForLinearLayout.this.e.startActivity(r0_Intent);
            } else {
                Toast.makeText(AdapterForLinearLayout.this.e, "\u767b\u9646\u540e\u624d\u80fd\u53d1\u5c0f\u7eb8\u6761\u54e6", 1).show();
            }
        }
    }

    class c {
        TextView a;
        TextView b;
        TextView c;

        c() {
        }
    }

    public AdapterForLinearLayout(Context r3_Context, LinkedList<JSONObject> r4_LinkedList_JSONObject, HashMap<Integer, JSONObject> r5_HashMap_Integer__JSONObject) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new a(this);
        this.e = r3_Context;
        this.g = r4_LinkedList_JSONObject;
        this.h = r5_HashMap_Integer__JSONObject;
        this.f = LayoutInflater.from(r3_Context);
        Resources r0_Resources = this.e.getResources();
        this.a = r0_Resources.getColorStateList(R.color.user_anony);
        this.b = r0_Resources.getColorStateList(R.color.user_admin);
        this.c = r0_Resources.getColorStateList(R.color.user_current);
    }

    private boolean a(View r8_View, TextView r9_TextView, String r10_String, int r11i) {
        boolean r0z;
        try {
            Matcher r2_Matcher = Pattern.compile("[0-9]+[Ll\u697c]").matcher(r10_String);
            r10_String = r10_String != null ? r10_String.trim() : RContactStorage.PRIMARY_KEY;
            SpannableStringBuilder r3_SpannableStringBuilder = new SpannableStringBuilder(r10_String);
            if (r2_Matcher.find()) {
                String r0_String = r10_String.substring(r2_Matcher.start(), r2_Matcher.end());
                int r4i = Integer.valueOf(r0_String.substring(0, r0_String.length() - 1)).intValue();
                if (r4i < r11i) {
                    r3_SpannableStringBuilder.setSpan(new c(this, r4i, ((JSONObject) this.h.get(Integer.valueOf(r4i))).optString(Utils.RESPONSE_CONTENT)), r2_Matcher.start(), r2_Matcher.end(), 0);
                }
            }
            r9_TextView.setText(r3_SpannableStringBuilder);
            r9_TextView.setMovementMethod(LinkMovementMethod.getInstance());
            r0z = r2_Matcher.find();
        } catch (Exception e) {
            r9_TextView.setText(r10_String);
            r0z = false;
        }
        return r0z;
    }

    public int getCount() {
        return this.g.size();
    }

    public Object getItem(int r2i) {
        return (this.g == null || this.g.size() <= 0) ? null : this.g.get(r2i);
    }

    public long getItemId(int r3i) {
        return (long) r3i;
    }

    public View getView(int r7i, View r8_View, ViewGroup r9_ViewGroup) {
        c r1_c;
        if (r8_View == null) {
            c r0_c = new c();
            r8_View = this.f.inflate(R.layout.listitem_comment_row, null);
            r0_c = initHolder(r8_View, r0_c);
            r8_View.setTag(r0_c);
            r1_c = r0_c;
        } else {
            r1_c = (c) r8_View.getTag();
        }
        JSONObject r0_JSONObject = (JSONObject) this.g.get(r7i);
        int r2i = r0_JSONObject.optInt("floor");
        r8_View.setOnLongClickListener(new a(r2i, r0_JSONObject));
        r1_c.c.setOnLongClickListener(new a(r2i, r0_JSONObject));
        try {
            r1_c.a.setText(String.valueOf(r2i));
            String r2_String;
            String r3_String;
            if (a(r8_View, r1_c.c, StringUtils.replaceHtml(r0_JSONObject.getString(Utils.RESPONSE_CONTENT)), r2i)) {
                r2_String = r0_JSONObject.getString(OneProfileActivity.USER);
                if (r2_String == null || r2_String.equals("null") || r2_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r1_c.b.setTextColor(this.a);
                    r1_c.b.setText("\u533f\u540d\u7528\u6237");
                    return r8_View;
                } else {
                    r0_JSONObject = r0_JSONObject.getJSONObject(OneProfileActivity.USER);
                    r2_String = r0_JSONObject.getString(QsbkDatabase.ROLE);
                    r3_String = r0_JSONObject.getString(LocaleUtil.INDONESIAN);
                    if (QsbkApp.currentUser == null || (!r3_String.equals(QsbkApp.currentUser.userId))) {
                        if (!r2_String.equals("admin")) {
                            r1_c.b.setTextColor(this.b);
                        }
                        r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                        r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                        return r8_View;
                    } else {
                        r1_c.b.setTextColor(this.c);
                        if (r2_String.equals("admin")) {
                            r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                            r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                            return r8_View;
                        } else {
                            r1_c.b.setTextColor(this.b);
                            r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                            r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                            return r8_View;
                        }
                    }
                }
            } else {
                r2_String = r0_JSONObject.getString(OneProfileActivity.USER);
                if (r2_String == null || r2_String.equals("null") || r2_String.equals(RContactStorage.PRIMARY_KEY)) {
                    r1_c.b.setTextColor(this.a);
                    r1_c.b.setText("\u533f\u540d\u7528\u6237");
                    return r8_View;
                } else {
                    r0_JSONObject = r0_JSONObject.getJSONObject(OneProfileActivity.USER);
                    r2_String = r0_JSONObject.getString(QsbkDatabase.ROLE);
                    r3_String = r0_JSONObject.getString(LocaleUtil.INDONESIAN);
                    if (QsbkApp.currentUser == null || r3_String.equals(QsbkApp.currentUser.userId)) {
                        if (r2_String.equals("admin")) {
                            r1_c.b.setTextColor(this.b);
                        }
                        r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                        r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                        return r8_View;
                    } else {
                        r1_c.b.setTextColor(this.c);
                        if (r2_String.equals("admin")) {
                            r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                            r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                            return r8_View;
                        } else {
                            r1_c.b.setTextColor(this.b);
                            r1_c.b.setText(r0_JSONObject.getString(QsbkDatabase.LOGIN));
                            r1_c.b.setOnClickListener(new b(r3_String, r0_JSONObject.getString(QsbkDatabase.LOGIN), r0_JSONObject.getString(QsbkDatabase.ICON)));
                            return r8_View;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public c initHolder(View r3_View, c r4_c) {
        r4_c.a = (TextView) r3_View.findViewById(R.id.floor);
        r4_c.c = (TextView) r3_View.findViewById(R.id.content);
        r4_c.c.setTransformationMethod(new MobileTransformationMethod());
        r4_c.b = (TextView) r3_View.findViewById(R.id.userName);
        return r4_c;
    }

    public void reportComment(String r3_String, String r4_String) {
        new b(this, "qbk-AdptLinearLy1", r4_String, r3_String).start();
    }

    public void setArticleId(String r1_String) {
        this.i = r1_String;
    }
}
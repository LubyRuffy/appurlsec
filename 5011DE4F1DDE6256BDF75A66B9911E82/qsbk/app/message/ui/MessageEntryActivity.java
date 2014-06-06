package qsbk.app.message.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import org.apache.cordova.Globalization;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.widget.CircularImage;

public class MessageEntryActivity extends Activity {
    private String a;
    private String b;
    private String c;

    private void a() {
        Intent r0_Intent = new Intent(this, MessageSendActivity.class);
        r0_Intent.putExtra("userId", this.b);
        r0_Intent.putExtra("userName", this.a);
        r0_Intent.putExtra("userIcon", this.c);
        r0_Intent.putExtra(OneProfileActivity.SOURCE, "entry");
        startActivity(r0_Intent);
        overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        finish();
    }

    protected void onCreate(Bundle r7_Bundle) {
        super.onCreate(r7_Bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_messageentry);
        this.a = getIntent().getStringExtra("userName");
        this.b = getIntent().getStringExtra("userId");
        this.c = getIntent().getStringExtra("userIcon");
        ((TextView) findViewById(R.id.userName)).setText(this.a);
        findViewById(R.id.send).setOnTouchListener(QsbkApp.TouchDark);
        findViewById(R.id.send).setOnClickListener(new a(this));
        if (TextUtils.isEmpty(this.c) || this.c.equals("null")) {
            ((CircularImage) findViewById(R.id.userAvatar)).setImageResource(R.drawable.default_users_avatar);
        } else {
            String r0_String = Constants.ARATAR_URL;
            Object[] r1_ObjectA = new Object[4];
            r1_ObjectA[0] = Integer.valueOf(Integer.valueOf(this.b).intValue() / 10000);
            r1_ObjectA[1] = this.b;
            r1_ObjectA[2] = Globalization.MEDIUM;
            r1_ObjectA[3] = this.c;
            QsbkApp.getInstance().getAvatarWorker(this).loadImage(String.format(r0_String, r1_ObjectA), (CircularImage) findViewById(R.id.userAvatar));
        }
    }
}
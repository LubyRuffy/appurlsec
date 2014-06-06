package qsbk.app.activity;

import android.content.Intent;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.widget.TextView;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.Serializable;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;

public abstract class SingleEditTextBaseActivity extends EditInfoBaseActivity {
    protected TextView p;
    protected TextView q;
    protected EditText r;
    private String s;

    public abstract String getDefaultEditTextTips();

    public abstract String getDefaultInputTips();

    public int getLayout() {
        return R.layout.layout_edit_with_single_edittext;
    }

    public void handleIntent(Intent r3_Intent) {
        Serializable r0_Serializable = r3_Intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.s = r0_Serializable != null ? (String) r0_Serializable : RContactStorage.PRIMARY_KEY;
        this.r.setText(this.s);
        this.r.setSelection(this.r.length());
        this.p.setText(getDefaultInputTips());
        this.p.setVisibility(0);
    }

    public void init() {
        getWindow().setSoftInputMode(AdViewUtil.NETWORK_TYPE_WOOBOO);
        this.p = (TextView) findViewById(R.id.tips);
        this.r = (EditText) findViewById(R.id.edittext);
        this.q = (TextView) findViewById(R.id.left_count);
        if (maxLength() > 0) {
            InputFilter[] r0_InputFilterA = new InputFilter[1];
            r0_InputFilterA[0] = new LengthFilter(maxLength());
            this.r.setFilters(r0_InputFilterA);
        }
        this.r.addTextChangedListener(new cz(this));
    }

    public int maxLength() {
        return 0;
    }
}
package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;

public class DefaultNoContentView extends RelativeLayout {
    private ImageView a;
    private TextView b;

    public DefaultNoContentView(Context r1_Context) {
        super(r1_Context);
        a(r1_Context);
    }

    public DefaultNoContentView(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
        a(r1_Context);
    }

    public DefaultNoContentView(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        super(r1_Context, r2_AttributeSet, r3i);
        a(r1_Context);
    }

    private void a(Context r3_Context) {
        LayoutInflater.from(r3_Context).inflate(R.layout.default_no_content_view, this);
        this.b = (TextView) findViewById(R.id.text);
        this.a = (ImageView) findViewById(R.id.image);
    }

    public void setImageResource(int r2i) {
        this.a.setImageResource(r2i);
    }

    public void setText(String r2_String) {
        this.b.setText(r2_String);
    }
}
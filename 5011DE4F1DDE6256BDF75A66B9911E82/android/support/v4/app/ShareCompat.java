package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class ShareCompat {
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static a a;

    public static class IntentBuilder {
        private Activity a;
        private Intent b;
        private CharSequence c;
        private ArrayList<String> d;
        private ArrayList<String> e;
        private ArrayList<String> f;
        private ArrayList<Uri> g;

        private IntentBuilder(Activity r4_Activity) {
            this.a = r4_Activity;
            this.b = new Intent().setAction("android.intent.action.SEND");
            this.b.putExtra(EXTRA_CALLING_PACKAGE, r4_Activity.getPackageName());
            this.b.putExtra(EXTRA_CALLING_ACTIVITY, r4_Activity.getComponentName());
            this.b.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        }

        private void a(String r6_String, ArrayList<String> r7_ArrayList_String) {
            int r0i;
            Object r2_Object = this.b.getStringArrayExtra(r6_String);
            r0i = r2_Object != null ? r2_Object.length : 0;
            Object r3_Object = new Object[(r7_ArrayList_String.size() + r0i)];
            r7_ArrayList_String.toArray(r3_Object);
            if (r2_Object != null) {
                System.arraycopy(r2_Object, 0, r3_Object, r7_ArrayList_String.size(), r0i);
            }
            this.b.putExtra(r6_String, r3_Object);
        }

        private void a(String r6_String, String[] r7_StringA) {
            int r0i;
            Intent r2_Intent = getIntent();
            Object r3_Object = r2_Intent.getStringArrayExtra(r6_String);
            r0i = r3_Object != null ? r3_Object.length : 0;
            Object r4_Object = new Object[(r7_StringA.length + r0i)];
            if (r3_Object != null) {
                System.arraycopy(r3_Object, 0, r4_Object, 0, r0i);
            }
            System.arraycopy(r7_StringA, 0, r4_Object, r0i, r7_StringA.length);
            r2_Intent.putExtra(r6_String, r4_Object);
        }

        public static android.support.v4.app.ShareCompat.IntentBuilder from(Activity r1_Activity) {
            return new android.support.v4.app.ShareCompat.IntentBuilder(r1_Activity);
        }

        Activity a() {
            return this.a;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailBcc(String r2_String) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            this.f.add(r2_String);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailBcc(String[] r2_StringA) {
            a("android.intent.extra.BCC", r2_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailCc(String r2_String) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            this.e.add(r2_String);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailCc(String[] r2_StringA) {
            a("android.intent.extra.CC", r2_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailTo(String r2_String) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(r2_String);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addEmailTo(String[] r2_StringA) {
            a("android.intent.extra.EMAIL", r2_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder addStream(Uri r4_Uri) {
            Uri r0_Uri = (Uri) this.b.getParcelableExtra("android.intent.extra.STREAM");
            if (r0_Uri == null) {
                return setStream(r4_Uri);
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            if (r0_Uri != null) {
                this.b.removeExtra("android.intent.extra.STREAM");
                this.g.add(r0_Uri);
            }
            this.g.add(r4_Uri);
            return this;
        }

        public Intent createChooserIntent() {
            return Intent.createChooser(getIntent(), this.c);
        }

        public Intent getIntent() {
            int r1i;
            if (this.d != null) {
                a("android.intent.extra.EMAIL", this.d);
                this.d = null;
            }
            if (this.e != null) {
                a("android.intent.extra.CC", this.e);
                this.e = null;
            }
            if (this.f != null) {
                a("android.intent.extra.BCC", this.f);
                this.f = null;
            }
            r1i = (this.g == null || this.g.size() <= 1) ? 0 : 1;
            boolean r3z = this.b.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if (r1i == 0 && r3z) {
                this.b.setAction("android.intent.action.SEND");
                if (this.g == null || this.g.isEmpty()) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.b.putExtra("android.intent.extra.STREAM", (Parcelable) this.g.get(0));
                }
                this.g = null;
                if (r1i == 0 || r3z) {
                    return this.b;
                }
                this.b.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.g == null || this.g.isEmpty()) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.b.putParcelableArrayListExtra("android.intent.extra.STREAM", this.g);
                }
                return this.b;
            } else {
                if (r1i == 0 || r3z) {
                    return this.b;
                }
                this.b.setAction("android.intent.action.SEND_MULTIPLE");
                if (this.g == null || this.g.isEmpty()) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                } else {
                    this.b.putParcelableArrayListExtra("android.intent.extra.STREAM", this.g);
                }
                return this.b;
            }
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setChooserTitle(int r2i) {
            return setChooserTitle(this.a.getText(r2i));
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setChooserTitle(CharSequence r1_CharSequence) {
            this.c = r1_CharSequence;
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setEmailBcc(String[] r3_StringA) {
            this.b.putExtra("android.intent.extra.BCC", r3_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setEmailCc(String[] r3_StringA) {
            this.b.putExtra("android.intent.extra.CC", r3_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setEmailTo(String[] r3_StringA) {
            if (this.d != null) {
                this.d = null;
            }
            this.b.putExtra("android.intent.extra.EMAIL", r3_StringA);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setHtmlText(String r3_String) {
            this.b.putExtra(IntentCompat.EXTRA_HTML_TEXT, r3_String);
            if (!this.b.hasExtra("android.intent.extra.TEXT")) {
                setText(Html.fromHtml(r3_String));
            }
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setStream(Uri r3_Uri) {
            if (!this.b.getAction().equals("android.intent.action.SEND")) {
                this.b.setAction("android.intent.action.SEND");
            }
            this.g = null;
            this.b.putExtra("android.intent.extra.STREAM", r3_Uri);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setSubject(String r3_String) {
            this.b.putExtra("android.intent.extra.SUBJECT", r3_String);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setText(CharSequence r3_CharSequence) {
            this.b.putExtra("android.intent.extra.TEXT", r3_CharSequence);
            return this;
        }

        public android.support.v4.app.ShareCompat.IntentBuilder setType(String r2_String) {
            this.b.setType(r2_String);
            return this;
        }

        public void startChooser() {
            this.a.startActivity(createChooserIntent());
        }
    }

    public static class IntentReader {
        private Activity a;
        private Intent b;
        private String c;
        private ComponentName d;
        private ArrayList<Uri> e;

        private IntentReader(Activity r2_Activity) {
            this.a = r2_Activity;
            this.b = r2_Activity.getIntent();
            this.c = ShareCompat.getCallingPackage(r2_Activity);
            this.d = ShareCompat.getCallingActivity(r2_Activity);
        }

        public static android.support.v4.app.ShareCompat.IntentReader from(Activity r1_Activity) {
            return new android.support.v4.app.ShareCompat.IntentReader(r1_Activity);
        }

        public ComponentName getCallingActivity() {
            return this.d;
        }

        public Drawable getCallingActivityIcon() {
            if (this.d == null) {
                return null;
            }
            try {
                return this.a.getPackageManager().getActivityIcon(this.d);
            } catch (NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve icon for calling activity", e);
                return null;
            }
        }

        public Drawable getCallingApplicationIcon() {
            if (this.c == null) {
                return null;
            }
            try {
                return this.a.getPackageManager().getApplicationIcon(this.c);
            } catch (NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve icon for calling application", e);
                return null;
            }
        }

        public CharSequence getCallingApplicationLabel() {
            if (this.c == null) {
                return null;
            }
            PackageManager r1_PackageManager = this.a.getPackageManager();
            try {
                return r1_PackageManager.getApplicationLabel(r1_PackageManager.getApplicationInfo(this.c, 0));
            } catch (NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve label for calling application", e);
                return null;
            }
        }

        public String getCallingPackage() {
            return this.c;
        }

        public String[] getEmailBcc() {
            return this.b.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String[] getEmailCc() {
            return this.b.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailTo() {
            return this.b.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String getHtmlText() {
            String r1_String = this.b.getStringExtra(IntentCompat.EXTRA_HTML_TEXT);
            if (r1_String == null) {
                CharSequence r0_CharSequence = getText();
                if (r0_CharSequence instanceof Spanned) {
                    return Html.toHtml((Spanned) r0_CharSequence);
                }
                if (r0_CharSequence != null) {
                    return a.escapeHtml(r0_CharSequence);
                }
            }
            return r1_String;
        }

        public Uri getStream() {
            return (Uri) this.b.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int r4i) {
            if (this.e == null && isMultipleShare()) {
                this.e = this.b.getParcelableArrayListExtra("android.intent.extra.STREAM");
                if (this.e != null) {
                    return (Uri) this.e.get(r4i);
                }
                if (r4i == 0) {
                    return (Uri) this.b.getParcelableExtra("android.intent.extra.STREAM");
                }
                throw new IndexOutOfBoundsException("Stream items available: " + getStreamCount() + " index requested: " + r4i);
            } else {
                if (this.e != null) {
                    return (Uri) this.e.get(r4i);
                }
                if (r4i == 0) {
                    return (Uri) this.b.getParcelableExtra("android.intent.extra.STREAM");
                }
                throw new IndexOutOfBoundsException("Stream items available: " + getStreamCount() + " index requested: " + r4i);
            }
        }

        public int getStreamCount() {
            if (this.e == null && isMultipleShare()) {
                this.e = this.b.getParcelableArrayListExtra("android.intent.extra.STREAM");
                if (this.e != null) {
                    return this.e.size();
                }
                if (this.b.hasExtra("android.intent.extra.STREAM")) {
                    return 1;
                }
                return 0;
            } else {
                if (this.e != null) {
                    return this.e.size();
                }
                if (this.b.hasExtra("android.intent.extra.STREAM")) {
                    return 1;
                }
                return 0;
            }
        }

        public String getSubject() {
            return this.b.getStringExtra("android.intent.extra.SUBJECT");
        }

        public CharSequence getText() {
            return this.b.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getType() {
            return this.b.getType();
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.b.getAction());
        }

        public boolean isShareIntent() {
            String r0_String = this.b.getAction();
            return "android.intent.action.SEND".equals(r0_String) || "android.intent.action.SEND_MULTIPLE".equals(r0_String);
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.b.getAction());
        }
    }

    static interface a {
        public void configureMenuItem(MenuItem r1_MenuItem, android.support.v4.app.ShareCompat.IntentBuilder r2_android_support_v4_app_ShareCompat_IntentBuilder);

        public String escapeHtml(CharSequence r1_CharSequence);
    }

    static class b implements a {
        b() {
        }

        private static void a(StringBuilder r5_StringBuilder, CharSequence r6_CharSequence, int r7i, int r8i) {
            int r0i = r7i;
            while (r0i < r8i) {
                char r1c = r6_CharSequence.charAt(r0i);
                if (r1c == '<') {
                    r5_StringBuilder.append("&lt;");
                } else if (r1c == '>') {
                    r5_StringBuilder.append("&gt;");
                } else if (r1c == '&') {
                    r5_StringBuilder.append("&amp;");
                } else if (r1c > '~' || r1c < ' ') {
                    r5_StringBuilder.append("&#" + r1c + ";");
                } else if (r1c == ' ') {
                    while (r0i + 1 < r8i && r6_CharSequence.charAt(r0i + 1) == ' ') {
                        r5_StringBuilder.append("&nbsp;");
                        r0i++;
                    }
                    r5_StringBuilder.append(' ');
                } else {
                    r5_StringBuilder.append(r1c);
                }
                r0i++;
            }
        }

        public void configureMenuItem(MenuItem r2_MenuItem, android.support.v4.app.ShareCompat.IntentBuilder r3_android_support_v4_app_ShareCompat_IntentBuilder) {
            r2_MenuItem.setIntent(r3_android_support_v4_app_ShareCompat_IntentBuilder.createChooserIntent());
        }

        public String escapeHtml(CharSequence r4_CharSequence) {
            StringBuilder r0_StringBuilder = new StringBuilder();
            a(r0_StringBuilder, r4_CharSequence, 0, r4_CharSequence.length());
            return r0_StringBuilder.toString();
        }
    }

    static class c extends b {
        c() {
        }

        boolean a(MenuItem r2_MenuItem) {
            return !r2_MenuItem.hasSubMenu();
        }

        public void configureMenuItem(MenuItem r3_MenuItem, android.support.v4.app.ShareCompat.IntentBuilder r4_android_support_v4_app_ShareCompat_IntentBuilder) {
            ac.configureMenuItem(r3_MenuItem, r4_android_support_v4_app_ShareCompat_IntentBuilder.a(), r4_android_support_v4_app_ShareCompat_IntentBuilder.getIntent());
            if (a(r3_MenuItem)) {
                r3_MenuItem.setIntent(r4_android_support_v4_app_ShareCompat_IntentBuilder.createChooserIntent());
            }
        }
    }

    static class d extends c {
        d() {
        }

        boolean a(MenuItem r2_MenuItem) {
            return false;
        }

        public String escapeHtml(CharSequence r2_CharSequence) {
            return ad.escapeHtml(r2_CharSequence);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new d();
        } else if (VERSION.SDK_INT >= 14) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public static void configureMenuItem(Menu r3_Menu, int r4i, IntentBuilder r5_IntentBuilder) {
        MenuItem r0_MenuItem = r3_Menu.findItem(r4i);
        if (r0_MenuItem == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + r4i + " in the supplied menu");
        } else {
            configureMenuItem(r0_MenuItem, r5_IntentBuilder);
        }
    }

    public static void configureMenuItem(MenuItem r1_MenuItem, IntentBuilder r2_IntentBuilder) {
        a.configureMenuItem(r1_MenuItem, r2_IntentBuilder);
    }

    public static ComponentName getCallingActivity(Activity r2_Activity) {
        ComponentName r0_ComponentName = r2_Activity.getCallingActivity();
        return r0_ComponentName == null ? (ComponentName) r2_Activity.getIntent().getParcelableExtra(EXTRA_CALLING_ACTIVITY) : r0_ComponentName;
    }

    public static String getCallingPackage(Activity r2_Activity) {
        String r0_String = r2_Activity.getCallingPackage();
        return r0_String == null ? r2_Activity.getIntent().getStringExtra(EXTRA_CALLING_PACKAGE) : r0_String;
    }
}
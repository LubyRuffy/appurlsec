package com.androidquery.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.androidquery.AQuery;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Constants;
import org.apache.http.HttpRequest;
import qsbk.app.widget.listview.XListViewHeader;

public class GoogleHandle extends AccountHandle implements OnCancelListener, OnClickListener {
    private AccountManager a;
    private Account b;
    private String c;
    private Activity d;
    private String e;
    private Account[] f;
    private String g;

    private class a extends AsyncTask<String, String, Bundle> {
        private a() {
        }

        protected Bundle a_(String ... r9_StringA) {
            try {
                return (Bundle) GoogleHandle.this.getAuthToken(GoogleHandle.this.b, GoogleHandle.this.c, null, GoogleHandle.this.d, null, null).getResult();
            } catch (OperationCanceledException e) {
                return null;
            } catch (AuthenticatorException e_2) {
                AQUtility.debug(e_2);
                return null;
            } catch (Exception e_3) {
                AQUtility.debug(e_3);
                return null;
            }
        }

        protected void a_(Bundle r5_Bundle) {
            if (r5_Bundle == null || (!r5_Bundle.containsKey("authtoken"))) {
                GoogleHandle.this.a(GoogleHandle.this.d, AjaxStatus.AUTH_ERROR, "rejected");
            } else {
                GoogleHandle.this.g = r5_Bundle.getString("authtoken");
                GoogleHandle.this.a(GoogleHandle.this.d);
            }
        }

        protected /* synthetic */ Object doInBackground(Object ... r2_ObjectA) {
            return a((String[]) r2_ObjectA);
        }

        protected /* synthetic */ void onPostExecute(Object r1_Object) {
            a((Bundle) r1_Object);
        }
    }

    public GoogleHandle(Activity r2_Activity, String r3_String, String r4_String) {
        if (Constants.ACTIVE_ACCOUNT.equals(r4_String)) {
            r4_String = getActiveAccount(r2_Activity);
        }
        this.d = r2_Activity;
        this.c = r3_String.substring(XListViewHeader.STATE_REFRESHING);
        this.e = r4_String;
        this.a = AccountManager.get(r2_Activity);
    }

    private void a(Account r3_Account) {
        this.b = r3_Account;
        new a(null).execute(new String[0]);
    }

    private void b() {
        int r0i = 0;
        Builder r1_Builder = new Builder(this.d);
        this.f = this.a.getAccountsByType("com.google");
        int r2i = this.f.length;
        if (r2i == 1) {
            a(this.f[r0i]);
        } else {
            CharSequence[] r3_CharSequenceA = new CharSequence[r2i];
            while (r0i < r2i) {
                r3_CharSequenceA[r0i] = this.f[r0i].name;
                r0i++;
            }
            r1_Builder.setItems(r3_CharSequenceA, this);
            r1_Builder.setOnCancelListener(this);
            new AQuery(this.d).show(r1_Builder.create());
        }
    }

    public static String getActiveAccount(Context r3_Context) {
        return PreferenceManager.getDefaultSharedPreferences(r3_Context).getString(Constants.ACTIVE_ACCOUNT, null);
    }

    public static void setActiveAccount(Context r2_Context, String r3_String) {
        PreferenceManager.getDefaultSharedPreferences(r2_Context).edit().putString(Constants.ACTIVE_ACCOUNT, r3_String).commit();
    }

    protected void a() {
        if (this.e == null) {
            b();
        } else {
            Account[] r1_AccountA = this.a.getAccountsByType("com.google");
            int r0i = 0;
            while (r0i < r1_AccountA.length) {
                Account r2_Account = r1_AccountA[r0i];
                if (this.e.equals(r2_Account.name)) {
                    a(r2_Account);
                    return;
                } else {
                    r0i++;
                }
            }
        }
    }

    public void applyToken(AbstractAjaxCallback<?, ?> r4_AbstractAjaxCallback___, HttpRequest r5_HttpRequest) {
        r5_HttpRequest.addHeader("Authorization", new StringBuilder("GoogleLogin auth=").append(this.g).toString());
    }

    public boolean authenticated() {
        return this.g != null;
    }

    public boolean expired(AbstractAjaxCallback<?, ?> r3_AbstractAjaxCallback___, AjaxStatus r4_AjaxStatus) {
        int r0i = r4_AjaxStatus.getCode();
        return r0i == 401 || r0i == 403;
    }

    public String getCacheUrl(String r3_String) {
        return new StringBuilder(String.valueOf(r3_String)).append("#").append(this.g).toString();
    }

    public String getType() {
        return this.c;
    }

    public void onCancel(DialogInterface r4_DialogInterface) {
        a(this.d, AjaxStatus.AUTH_ERROR, "cancel");
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        Account r0_Account = this.f[r5i];
        AQUtility.debug("acc", r0_Account.name);
        setActiveAccount(this.d, r0_Account.name);
        a(r0_Account);
    }

    public boolean reauth(AbstractAjaxCallback<?, ?> r6_AbstractAjaxCallback___) {
        this.a.invalidateAuthToken(this.b.type, this.g);
        try {
            this.g = this.a.blockingGetAuthToken(this.b, this.c, true);
            AQUtility.debug("re token", this.g);
        } catch (Exception e) {
            AQUtility.debug(e);
            this.g = null;
        }
        return this.g != null;
    }
}
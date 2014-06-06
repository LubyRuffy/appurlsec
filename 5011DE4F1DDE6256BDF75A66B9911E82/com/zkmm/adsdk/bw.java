package com.zkmm.adsdk;

import android.app.Activity;
import android.widget.PopupWindow.OnDismissListener;
import java.io.File;
import qsbk.app.share.ShareUtils;

// compiled from: SourceFile
final class bw implements OnDismissListener {
    private /* synthetic */ AdDisplayer a;

    bw(AdDisplayer r1_AdDisplayer) {
        this.a = r1_AdDisplayer;
    }

    public final void onDismiss() {
        boolean r0z = false;
        File[] r1_FileA;
        boolean r2z;
        File r3_File;
        Activity r0_Activity;
        if (bj.o != null || this.a.u == null) {
            cs.a = false;
            if (!false) {
                s.a(new StringBuilder(String.valueOf(cs.c)).append("=").append(cs.d).toString(), new StringBuilder(String.valueOf(System.currentTimeMillis() - cs.b)).toString(), AdDisplayer.m);
                s.a(cs.d, new StringBuilder(String.valueOf(m.J)).append("=").append(m.K).append("=").append(cs.c).toString(), AdDisplayer.m);
            }
            this.a.s.removeMessages(ShareUtils.SHARE_COPY);
            if (this.a.c != null) {
                r1_FileA = new File(m.P).listFiles();
                if (r1_FileA == null || r1_FileA.length <= 0) {
                } else {
                    r2z = r1_FileA.length;
                    while (r0z < r2z) {
                        r3_File = r1_FileA[r0z];
                        if (!this.a.c.contains(r3_File.getAbsolutePath())) {
                            s.a(r3_File);
                        }
                        r0z++;
                    }
                }
            }
            if (this.a.o <= 0 || (!AdDisplayer.m instanceof Activity)) {
            } else {
                try {
                    r0_Activity = (Activity) AdDisplayer.m;
                    if (r0_Activity != null) {
                        r0_Activity.runOnUiThread(new bx(this, r0_Activity));
                    }
                } catch (Exception e) {
                }
            }
        } else {
            this.a.u.onAdDismiss();
            cs.a = false;
            if (false) {
                this.a.s.removeMessages(ShareUtils.SHARE_COPY);
                if (this.a.c != null) {
                    if (this.a.o <= 0 || AdDisplayer.m instanceof Activity) {
                    } else {
                        r0_Activity = (Activity) AdDisplayer.m;
                        if (r0_Activity != null) {
                        } else {
                            r0_Activity.runOnUiThread(new bx(this, r0_Activity));
                        }
                    }
                } else {
                    r1_FileA = new File(m.P).listFiles();
                    if (r1_FileA == null || r1_FileA.length <= 0) {
                        if (this.a.o <= 0 || AdDisplayer.m instanceof Activity) {
                        } else {
                            r0_Activity = (Activity) AdDisplayer.m;
                            if (r0_Activity != null) {
                                r0_Activity.runOnUiThread(new bx(this, r0_Activity));
                            }
                        }
                    } else {
                        r2z = r1_FileA.length;
                        while (r0z < r2z) {
                            r3_File = r1_FileA[r0z];
                            if (this.a.c.contains(r3_File.getAbsolutePath())) {
                                r0z++;
                            } else {
                                s.a(r3_File);
                                r0z++;
                            }
                        }
                        if (this.a.o <= 0 || AdDisplayer.m instanceof Activity) {
                        } else {
                            r0_Activity = (Activity) AdDisplayer.m;
                            if (r0_Activity != null) {
                            } else {
                                r0_Activity.runOnUiThread(new bx(this, r0_Activity));
                            }
                        }
                    }
                }
            } else {
                s.a(new StringBuilder(String.valueOf(cs.c)).append("=").append(cs.d).toString(), new StringBuilder(String.valueOf(System.currentTimeMillis() - cs.b)).toString(), AdDisplayer.m);
                s.a(cs.d, new StringBuilder(String.valueOf(m.J)).append("=").append(m.K).append("=").append(cs.c).toString(), AdDisplayer.m);
                this.a.s.removeMessages(ShareUtils.SHARE_COPY);
                if (this.a.c != null) {
                    r1_FileA = new File(m.P).listFiles();
                    if (r1_FileA == null || r1_FileA.length <= 0) {
                    } else {
                        r2z = r1_FileA.length;
                        while (r0z < r2z) {
                            r3_File = r1_FileA[r0z];
                            if (this.a.c.contains(r3_File.getAbsolutePath())) {
                                s.a(r3_File);
                            }
                            r0z++;
                        }
                    }
                }
                if (this.a.o <= 0 || AdDisplayer.m instanceof Activity) {
                } else {
                    r0_Activity = (Activity) AdDisplayer.m;
                    if (r0_Activity != null) {
                        r0_Activity.runOnUiThread(new bx(this, r0_Activity));
                    }
                }
            }
        }
    }
}
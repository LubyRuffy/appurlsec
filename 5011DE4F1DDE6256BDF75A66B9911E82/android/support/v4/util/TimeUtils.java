package android.support.v4.util;

import java.io.PrintWriter;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class TimeUtils {
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final Object a;
    private static char[] b;

    static {
        a = new Object();
        b = new char[24];
    }

    private static int a(int r1i, int r2i, boolean r3z, int r4i) {
        if (r1i <= 99) {
            if ((!r3z) || r4i < 3) {
                if (r1i <= 9) {
                    if ((!r3z) || r4i < 2) {
                        return (r3z || r1i > 0) ? r2i + 1 : 0;
                    }
                }
                return r2i + 2;
            }
        }
        return r2i + 3;
    }

    private static int a(long r18j, int r20i) {
        if (b.length < r20i) {
            b = new char[r20i];
        }
        char[] r2_charA = b;
        if (r18j == 0) {
            int r4i = r20i - 1;
            while (0 < r4i) {
                r2_charA[0] = ' ';
            }
            r2_charA[0] = '0';
            return 1;
        } else {
            char r4c;
            int r15i;
            int r13i;
            int r14i;
            int r12i;
            if (r18j > 0) {
                r4c = '+';
            } else {
                r18j = -r18j;
                r4c = '-';
            }
            int r16i = (int) (r18j % 1000);
            int r7i = (int) Math.floor((double) (r18j / 1000));
            int r3i = 0;
            int r6i = 0;
            int r5i = 0;
            if (r7i > 86400) {
                r3i = r7i / 86400;
                r7i -= 86400 * r3i;
            }
            if (r7i > 3600) {
                r6i = r7i / 3600;
                r15i = r6i;
                r6i = r7i - r6i * 3600;
            } else {
                r15i = r6i;
                r6i = r7i;
            }
            if (r6i > 60) {
                r5i = r6i / 60;
                r13i = r5i;
                r14i = r6i - r5i * 60;
            } else {
                r13i = r5i;
                r14i = r6i;
            }
            r6i = 0;
            if (r20i != 0) {
                r7i = a(r3i, 1, false, 0);
                r7i += a(r15i, 1, r7i > 0, XListViewHeader.STATE_REFRESHING);
                r7i += a(r13i, 1, r7i > 0, XListViewHeader.STATE_REFRESHING);
                r7i += a(r14i, 1, r7i > 0, XListViewHeader.STATE_REFRESHING);
                r5i = r6i;
                r6i = a(r16i, XListViewHeader.STATE_REFRESHING, true, r7i > 0 ? XListViewFooter.STATE_NOMORE : 0) + 1 + r7i;
                while (r6i < r20i) {
                    r2_charA[r5i] = ' ';
                    r6i++;
                    r5i++;
                }
            } else {
                r5i = 0;
            }
            r2_charA[r5i] = r4c;
            r5i++;
            r12i = r20i != 0 ? 1 : 0;
            int r9i = a(r2_charA, r3i, 'd', r5i, false, 0);
            r9i = a(r2_charA, r15i, 'h', r9i, r9i != r5i, r12i != 0 ? XListViewHeader.STATE_REFRESHING : 0);
            r9i = a(r2_charA, r13i, 'm', r9i, r9i != r5i, r12i != 0 ? XListViewHeader.STATE_REFRESHING : 0);
            int r8i = a(r2_charA, r14i, 's', r9i, r9i != r5i, r12i != 0 ? XListViewHeader.STATE_REFRESHING : 0);
            r4c = 'm';
            boolean r6z = true;
            if (r12i == 0 || r8i == r5i) {
                r7i = 0;
                r3i = a(r2_charA, r16i, r4c, r8i, r6z, r7i);
                r2_charA[r3i] = 's';
                return r3i + 1;
            } else {
                r7i = XListViewFooter.STATE_NOMORE;
                r3i = a(r2_charA, r16i, r4c, r8i, r6z, r7i);
                r2_charA[r3i] = 's';
                return r3i + 1;
            }
        }
    }

    private static int a(char[] r4_charA, int r5i, char r6c, int r7i, boolean r8z, int r9i) {
        if (!r8z && r5i <= 0) {
            return r7i;
        }
        int r0i;
        int r1i;
        if (((!r8z) || r9i < 3) && r5i <= 99) {
            r0i = r7i;
            r1i = r5i;
        } else {
            r1i = r5i / 100;
            r4_charA[r7i] = (char) (r1i + 48);
            r0i = r7i + 1;
            r1i = r5i - r1i * 100;
        }
        if (((!r8z) || r9i < 2) && r1i <= 9 && r7i == r0i) {
            r4_charA[r0i] = (char) (r1i + 48);
            r0i++;
            r4_charA[r0i] = r6c;
            return r0i + 1;
        } else {
            int r2i = r1i / 10;
            r4_charA[r0i] = (char) (r2i + 48);
            r0i++;
            r1i -= r2i * 10;
            r4_charA[r0i] = (char) (r1i + 48);
            r0i++;
            r4_charA[r0i] = r6c;
            return r0i + 1;
        }
    }

    public static void formatDuration(long r3j, long r5j, PrintWriter r7_PrintWriter) {
        if (r3j == 0) {
            r7_PrintWriter.print("--");
        } else {
            formatDuration(r3j - r5j, r7_PrintWriter, 0);
        }
    }

    public static void formatDuration(long r1j, PrintWriter r3_PrintWriter) {
        formatDuration(r1j, r3_PrintWriter, 0);
    }

    public static void formatDuration(long r5j, PrintWriter r7_PrintWriter, int r8i) {
        synchronized (a) {
            r7_PrintWriter.print(new String(b, 0, a(r5j, r8i)));
        }
    }

    public static void formatDuration(long r4j, StringBuilder r6_StringBuilder) {
        synchronized (a) {
            r6_StringBuilder.append(b, 0, a(r4j, 0));
        }
    }
}
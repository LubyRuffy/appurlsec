package qsbk.app.utils.image.issue;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import qsbk.app.Constants;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.utils.HttpUtils;

public class Analyzer {
    private static Analyzer a;
    private static final String b;

    public static interface Filter {
        public boolean filter(IssueBean r1_IssueBean);
    }

    public static final class InetAddressHelper {
        public static InetAddress getAddressByName(String r5_String) throws UnknownHostException {
            InetAddress r1_InetAddress = InetAddress.getByName(r5_String);
            if (!r1_InetAddress.isLoopbackAddress()) {
                return r1_InetAddress;
            }
            InetAddress r0_InetAddress;
            try {
                InetAddress r0_InetAddress_2;
                Enumeration r2_Enumeration = NetworkInterface.getNetworkInterfaces();
                while (r2_Enumeration.hasMoreElements()) {
                    Enumeration r3_Enumeration = ((NetworkInterface) r2_Enumeration.nextElement()).getInetAddresses();
                    while (r3_Enumeration.hasMoreElements()) {
                        r0_InetAddress_2 = (InetAddress) r3_Enumeration.nextElement();
                        if (r0_InetAddress_2.isLoopbackAddress() || (!r0_InetAddress_2 instanceof Inet4Address)) {
                        } else {
                            break;
                        }
                    }
                }
                r0_InetAddress_2 = r1_InetAddress;
                return r0_InetAddress_2;
            } catch (SocketException e) {
                return r1_InetAddress;
            }
        }

        public static String getHostAddress(String r3_String) throws UnknownHostException {
            InetAddress r0_InetAddress = getAddressByName(r3_String);
            if (r0_InetAddress != null) {
                return r0_InetAddress.getHostAddress();
            }
            throw new UnknownHostException("unknow host " + r3_String);
        }
    }

    class a implements qsbk.app.utils.image.issue.Analyzer.Filter {
        IOException a;
        long b;
        String c;

        public a(IOException r5_IOException, String r6_String, long r7j) {
            this.a = null;
            this.b = -1;
            this.c = null;
            this.b = r7j;
            this.c = r6_String;
            this.a = r5_IOException;
        }

        public boolean filter(IssueBean r2_IssueBean) {
            return !HttpUtils.netIsAvailable();
        }
    }

    static {
        b = Constants.IMG_DOMAINS;
    }

    private Analyzer() {
    }

    private IssueBean a(IOException r6_IOException, String r7_String, long r8j) {
        String r0_String;
        IssueBean r1_IssueBean = new IssueBean();
        r1_IssueBean.setTime(System.currentTimeMillis());
        r1_IssueBean.setIssueAddress(r7_String);
        r1_IssueBean.setMsg(r6_IOException.toString());
        r1_IssueBean.setLastSuccessTime(r8j);
        String r2_String = "qh_removed";
        try {
            r0_String = Constants.IMG_DOMAINS.replace(HttpUtils.http, RContactStorage.PRIMARY_KEY);
            if (r0_String.endsWith("/")) {
                r0_String = r0_String.substring(0, r0_String.length() - 1);
            }
            r0_String = InetAddressHelper.getHostAddress(r0_String);
        } catch (UnknownHostException e) {
            r0_String = EDIT_TYPE.TYPE_UNKNOWN;
        }
        r1_IssueBean.setCdnAddress(r2_String);
        r1_IssueBean.setIssueInetAddress(r0_String);
        return r1_IssueBean;
    }

    public static synchronized Analyzer getInstance() {
        Analyzer r0_Analyzer;
        synchronized (Analyzer.class) {
            if (a == null) {
                a = new Analyzer();
            }
            r0_Analyzer = a;
        }
        return r0_Analyzer;
    }

    public IssueBean getIssue(IOException r8_IOException, String r9_String, long r10j, String r12_String) {
        IssueBean r6_IssueBean = a(r8_IOException, r9_String, r10j);
        return (r6_IssueBean == null || (!new a(r8_IOException, r12_String, r10j).filter(r6_IssueBean))) ? r6_IssueBean : null;
    }

    public IssueBean getIssue(IOException r3_IOException, String r4_String, long r5j, Filter r7_Filter) {
        IssueBean r0_IssueBean = a(r3_IOException, r4_String, r5j);
        return (r7_Filter == null || (!r7_Filter.filter(r0_IssueBean))) ? r0_IssueBean : null;
    }
}
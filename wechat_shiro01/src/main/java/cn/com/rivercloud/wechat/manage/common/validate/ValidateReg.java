package cn.com.rivercloud.wechat.manage.common.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateReg {

    private static final String IPV4 = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final String IPV6 = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$";
    private static final String CIDR = "^(?!^255(\\.255){3})(?!^0)(?!^127(\\.0){2}(\\.1))(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\/(\\d{1}|[0-2]{1}\\d{1}|3[0-2])$";
    private static final String DOMAIN_DNS = "^(?=^.{3,255}$)(http(s)?:\\/\\/)?(www\\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\\d+)*(\\/\\w+\\.\\w+)*$";
    private static final String DOMAIN_IP_PORT = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\:([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])$";

    public static boolean isLineIP(String lineIP) {
        if (lineIP.indexOf(",") > -1) {
            String ips[] = lineIP.split(",");
            for (String ip : ips) {
                if (ip.indexOf("-") > -1) { //范围
                    String ipTmpArray[] = ip.split("-");
                    if (!isIp(ipTmpArray[0]) || !isIp(ipTmpArray[1])) {
                        return false;
                    }
                } else {
                    if (!isIp(ip) && !isCidr(ip)) {
                        return false;
                    }
                }
            }
        } else {
            if (lineIP.indexOf("-") > -1) { //范围
                String ipTmpArray[] = lineIP.split("-");
                if (!isIp(ipTmpArray[0]) || !isIp(ipTmpArray[1])) {
                    return false;
                }
            }
            if (!isIp(lineIP) && !isCidr(lineIP)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isLineDomain(String domainName) {
        if (domainName.indexOf(',') > -1){
            String domainArray[] = domainName.split(",");
            for (String domain : domainArray) {
                if (!validateDomainName(domain)) {
                    return false;
                }
            }
            return true;
        }
        return validateDomainName(domainName);
    }

    public static boolean isIp(String ip) {
        Pattern p = Pattern.compile(IPV4);
        Matcher m = p.matcher(ip);
        if (m.find()) {
            return true;
        }
        p = Pattern.compile(IPV6);
        m = p.matcher(ip);
        return m.find();
    }

    public static boolean isCidr(String ip) {
        Pattern p = Pattern.compile(CIDR);
        Matcher m = p.matcher(ip);
        return m.find();
    }

    private static boolean validateDomainName(String domainName) {
        Pattern p = Pattern.compile(DOMAIN_DNS);
        Matcher m = p.matcher(domainName);
        if (m.find()) {
            return true;
        }
        p = Pattern.compile(DOMAIN_IP_PORT);
        m = p.matcher(domainName);
        return m.find();
    }
}

package cn.com.rivercloud.wechat.user.common.util;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserLoginErrorCounts {

    private static final String SC_KEY_LOGIN_ERROR_CNTS = "-LOGIN-ERROR-COUNTS-";

    public static synchronized int getLoginErrorCounts(ServletContext sc, String username) {
        Map<String, Integer> counts = (Map<String, Integer>) sc.getAttribute(SC_KEY_LOGIN_ERROR_CNTS);
        if (Objects.isNull(counts) || counts.isEmpty()) {
            return 0;
        }
        return counts.get(username);
    }

    public static synchronized int updateLoginErrorCounts(ServletContext sc, String username, boolean clear) {
        Map<String, Integer> counts = (Map<String, Integer>) sc.getAttribute(SC_KEY_LOGIN_ERROR_CNTS);
        if (counts == null) {
            counts = new HashMap<String, Integer>();
            sc.setAttribute(SC_KEY_LOGIN_ERROR_CNTS, counts);
        }
        Integer cnt = counts.get(username);

        if (clear) {
            cnt = 0;
        } else if (cnt == null) {
            cnt = 1;
        } else {
            cnt++;
        }
        if (clear) {
            counts.remove(username);
        } else {
            counts.put(username, cnt);
        }
        return cnt;
    }
}

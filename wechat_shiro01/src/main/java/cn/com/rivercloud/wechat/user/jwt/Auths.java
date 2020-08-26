package cn.com.rivercloud.wechat.user.jwt;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class Auths {

    @Autowired
    JwtUtils jwtUtils;

    public long getRequestUserId(HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return 0;
        }
        String token = jwtUtils.getToken(request);
        if (Strings.isEmpty(token)) {
            return 0;
        }
        Claims claim = jwtUtils.getClaimByToken(token);
        return Long.valueOf(JSONUtil.toJsonStr(claim.getSubject()));
    }
}

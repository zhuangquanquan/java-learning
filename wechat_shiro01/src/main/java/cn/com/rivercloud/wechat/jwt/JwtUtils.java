package cn.com.rivercloud.wechat.jwt;

import cn.com.rivercloud.wechat.common.constant.TokenDateUnitConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix="jwt")
public class JwtUtils {

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(String uuid, long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * TokenDateUnitConstant.UNIT);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setId(uuid)
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        //添加cookie判断
        if (Strings.isEmpty(token) && request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            int length = cookies.length;
            for(int i = 0; i < length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(header)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    public void addCookie(HttpServletRequest request, HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(header, token);
        cookie.setPath(request.getContextPath() + "/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }


}

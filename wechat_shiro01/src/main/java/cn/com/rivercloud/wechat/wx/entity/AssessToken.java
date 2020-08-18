package cn.com.rivercloud.wechat.wx.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessToken {

    private String access_token;

    private String expires_in;

    private long expiresIn; //过期时间，两小时

    public AssessToken(String access_token, String expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.expiresIn = System.currentTimeMillis() + Long.valueOf(expires_in) * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresIn;
    }
}

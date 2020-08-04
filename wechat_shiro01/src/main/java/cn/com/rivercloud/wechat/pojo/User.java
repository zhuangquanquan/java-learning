package cn.com.rivercloud.wechat.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String userName;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private int state;
    private long lockedAt;
    private String description;
    private long createTime;
    private long updateTime;
    private String role;

}

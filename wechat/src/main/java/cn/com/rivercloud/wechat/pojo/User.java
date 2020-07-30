package cn.com.rivercloud.wechat.pojo;


import lombok.Data;

@Data
public class User {

    private long id;
    private String userName;
    private String password;
    //1. 客户经理
    //2. 客户
    private String type;
    private String phone;
    private String email;
    private int state;
    private long lockedAt;
    private String description;
    private long createTime;
    private long updateTime;
}

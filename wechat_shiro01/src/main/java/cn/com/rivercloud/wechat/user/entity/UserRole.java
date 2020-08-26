package cn.com.rivercloud.wechat.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wechat_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;

}

package cn.com.rivercloud.wechat.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wechat_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private long roleId;

    @TableField("permission_id")
    private long permissionId;


}

package cn.com.rivercloud.wechat.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wechat_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private long createTime;

    @TableField("update_time")
    private long updateTime;



}

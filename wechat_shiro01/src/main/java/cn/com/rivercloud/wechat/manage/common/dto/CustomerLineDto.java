package cn.com.rivercloud.wechat.manage.common.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CustomerLineDto {

    @Min(value = 1, message = "地市最小值为1")
    @Max(value = 11, message = "地市最大值为11")
    private int lineAddr;

    @NotBlank(message = "客户单位名称不能为空")
    private String realName;

    @NotBlank(message = "客户联系人不能为空")
    private String userContact;

    @NotBlank(message = "客户联系方式不能为空")
    @Pattern(regexp="(^(\\d{3,4}-)?\\d{7,8})$|(1[0-9]{10})$",message = "客户联系方式格式不正确")
    private String userPhone;

    @Email(message = "客户邮箱格式不正确")
    private String userEmail;

    @Min(value = 1, message = "专线服务套餐最小值为1")
    @Max(value = 6, message = "专线服务套餐最大值为6")
    private int lineServiceGroup;

    @NotBlank(message = "专线线路编号不能为空")
    private String lineNumber;

    @Min(value = 1, message = "专线带宽不能小于1Mbps")
    private long lineBandWidth;

    @NotBlank(message = "专线IP不能为空")
    private String lineIp;

    private String lineDomain;

}

package cn.com.rivercloud.wechat.manage.common.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CustomerDto {

    @Min(value = 1, message = "最小值为1")
    @Max(value = 11, message = "最大值为11")
    private int lineAddr;

    @NotBlank(message = "客户单位名称不能为空")
    private String realName;

    @NotBlank(message = "客户联系人不能为空")
    private String userContact;

    @NotBlank(message = "客户联系方式不能为空")
    @Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须是一个字母")
    private String userPhone;
    private String userEmail;
    private List<CustomerDto> customerDtoList;
}

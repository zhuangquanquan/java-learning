package cn.com.rivercloud.wechat.manage;

import cn.com.rivercloud.wechat.manage.common.dto.CustomerLineDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ManageTrialTests {


    /**
     url: http://192.168.2.7:8081/manage/trial/save
     method: post
     Context-Type: application/json
     body: {"lineAddr":1,"lineBandWidth":100,"lineDomain":"","lineIp":"1.1.1.1,1.1.1.3-1.1.1.5","lineNumber":"123","lineServiceGroup":1,"realName":"北京xx科技有限公司","userContact":"xw","userEmail":"11","userPhone":"13133333333"}
     */
    public static void main(String[] args) {
        CustomerLineDto customerLineDto = new CustomerLineDto();
        customerLineDto.setLineAddr(1);
        customerLineDto.setRealName("北京xx科技有限公司");
        customerLineDto.setUserContact("xw");
        customerLineDto.setUserPhone("13133333333");
        customerLineDto.setUserEmail("");

        customerLineDto.setLineServiceGroup(1);
        customerLineDto.setLineNumber("123");
        customerLineDto.setLineBandWidth(100);
        customerLineDto.setLineIp("1.1.1.1,1.1.1.3-1.1.1.5");
        customerLineDto.setLineDomain("");

        String s = JSONObject.toJSONString(customerLineDto);
        System.out.println(s);
    }
}

package cn.com.rivercloud.wechat.rest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateTests {

    private String username = "platformif";
    private String password = "928316b3f02de33de4bd78573b060c4a0419eed0";

    @Autowired
    RestTemplate client;

    public JSONObject testConn() {
        String url = "http://127.0.0.1:8090/api/v1/platformIf/auth";
        String jsonStr = "{\"username\": \""+username+"\", \"password\": \""+password+"\"}";
        HttpHeaders headers = new HttpHeaders();
        /*MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());*/
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(resStr);
        return jsonObject;
    }


    public void testOssimAttackLogList() {
        String protocol = "https";
        String ip = "10.2.10.130";
        int port = 8011;
        String path = "/ossim/security/log/attack/search";
        String url = protocol + "://" + ip + ":" + port + path;
        System.out.println(url);
        String jsonStr = "{\"endQueryTime\":1599128400552,\"beginQueryTime\":1598610000552,\"current\":1,\"pageSize\":100,\"ips\":[\"192.168.2.150\",\"192.168.2.152\"]}";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        System.out.println(resStr);

    }
}

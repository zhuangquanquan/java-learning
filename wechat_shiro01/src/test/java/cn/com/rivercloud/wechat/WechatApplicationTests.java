package cn.com.rivercloud.wechat;

import cn.com.rivercloud.wechat.rest.RestTemplateTests;
import cn.com.rivercloud.wechat.schedule.CronSchedulerJob;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
class WechatApplicationTests {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Autowired
    CronSchedulerJob cronSchedulerJob;

    @Autowired
    RestTemplateTests client;

    @Test
    void testDataSource() {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println(connection);
            //DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            //System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            //System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
            try {
                cronSchedulerJob.scheduleJobs();
            } catch (Exception e) {
                log.error("调度错误", e);
            }
            //关闭连接
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCronSchedulerJob() {
        try {
            cronSchedulerJob.scheduleJobs();
        } catch (Exception e) {
            log.error("调度错误", e);
        }
    }

    @Test
    void testRestTemplate_testConn() {
        JSONObject result = client.testConn();
        Assert.assertEquals(result.getBoolean("success"), true);
        System.out.println(result);
    }

    @Test
    void testRestTemplate_testAttackLogList() {
        client.testOssimAttackLogList();
    }

}

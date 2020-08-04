package cn.com.rivercloud.wechat;

import cn.com.rivercloud.wechat.jwt.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class WechatApplicationTests {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    @Autowired
    JwtUtils jwtUtils;

    @Test
    void contextLoads() {
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
            System.out.println(jwtUtils);
            //关闭连接
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

package com.xw.test;


import com.xw.dao.UserDao;
import com.xw.pojo.User;
import com.xw.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    private static Logger logger = Logger.getLogger(UserDao.class);

    public static void main(String[] args) {
        getUserList();
    }

    public static void getUserList() {
        logger.info("getUserList进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        HashMap<String, String> map = new HashMap<String, String>();
        //map.put("id", "1");
        map.put("username", "feng");
        map.put("password", "123");
        List<User> userList = mapper.getUserList(map);
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
        logger.info("getUserList结束方法......................");
    }

}

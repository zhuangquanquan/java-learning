package com.xw.test;


import com.xw.dao.UserDao;
import com.xw.pojo.User;
import com.xw.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    private static Logger logger = Logger.getLogger(UserDao.class);

    public static void main(String[] args) {
        getUserList();
    }

    //一级缓存
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
            System.out.println("user1:" + user.toString());
        }
        UserDao mapper2 = sqlSession.getMapper(UserDao.class);
        List<User> userList2 = mapper2.getUserList(map);
        for (User user2 : userList2) {
            System.out.println("user2:" + user2.toString());
        }
        sqlSession.close();
        logger.info("getUserList结束方法......................");
    }

}

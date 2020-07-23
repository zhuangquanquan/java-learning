package com.xw.test;


import org.apache.ibatis.session.SqlSession;
import com.xw.dao.UserDao;
import com.xw.pojo.User;
import com.xw.util.MybatisUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {

    private static Logger logger = Logger.getLogger(UserDao.class);

    public static void main(String[] args) {
        getUserList();
        page();
    }

    public static void getUserList() {
        logger.info("进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
        logger.info("结束方法......................");
    }

    public static void page() {
        logger.info("进入分页方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int current = 2;
        int pageSize = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("start", (current-1)*pageSize);
        map.put("pageSize", pageSize);
        List<User> userList = mapper.page(map);
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
        logger.info("结束分页方法......................");
    }
}

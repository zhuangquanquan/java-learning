package com.xw.test;


import org.apache.ibatis.session.SqlSession;
import com.xw.dao.UserDao;
import com.xw.pojo.User;
import com.xw.util.MybatisUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoTest {

    private static Logger logger = Logger.getLogger(UserDao.class);

    public static void main(String[] args) {
        getUserList();
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
}

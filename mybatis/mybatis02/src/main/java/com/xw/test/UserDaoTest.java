package com.xw.test;


import org.apache.ibatis.session.SqlSession;
import com.xw.dao.UserDao;
import com.xw.pojo.User;
import com.xw.util.MybatisUtils;

import java.util.List;

public class UserDaoTest {

    public static void main(String[] args) {
        getUserList();
    }

    public static void getUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
    }
}

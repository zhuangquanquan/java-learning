package com.kuang.test;

import com.kuang.dao.UserDao;
import com.kuang.pojo.User;
import com.kuang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

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

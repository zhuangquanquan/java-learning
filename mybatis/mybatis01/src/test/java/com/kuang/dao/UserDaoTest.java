package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void getUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
    }

    @Test
    public void getById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.getById(1);
        System.out.println(user.toString());
        sqlSession.close();
    }

    @Test
    public void insert() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int res = mapper.insert(new User(4, "aaaa", "111111"));
        System.out.println("insert:" + res);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int res = mapper.update(new User(4, "bbbb", "22222"));
        System.out.println("update:" + res);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delete() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int res = mapper.delete(4);
        System.out.println("delete:" + res);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getLikeUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getLikeUserList("ang");
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void getMapUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "kuang");
        List<User> userList = mapper.getMapUserList(map);
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }







}

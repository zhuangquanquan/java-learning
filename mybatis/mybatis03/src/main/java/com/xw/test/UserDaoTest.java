package com.xw.test;


import com.xw.dao.UserMapper;
import com.xw.pojo.User;
import com.xw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoTest {

    private static Logger logger = Logger.getLogger(UserDaoTest.class);

    public static void main(String[] args) {
        getUserList();
        getUserById();
        insert();
        update();
        delete();
    }

    public static void getUserList() {
        logger.info("getUserList进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        sqlSession.close();
        logger.info("getUserList结束方法......................");
    }

    public static void getUserById() {
        logger.info("getUserById进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user.toString());
        sqlSession.close();
        logger.info("getUserById结束方法......................");
    }

    public static void insert() {
        logger.info("insert进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.insert(new User(5, "king", "333331"));
        System.out.println("insert 结果: " + res);
        sqlSession.commit();
        sqlSession.close();
        logger.info("insert结束方法......................");
    }

    public static void update() {
        logger.info("update 进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.update(new User(5, "king1", "333331"));
        System.out.println("update 结果: " + res);
        sqlSession.commit();
        sqlSession.close();
        logger.info("update 结束方法......................");
    }

    public static void delete() {
        logger.info("delete 进入方法......................");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.delete(5);
        System.out.println("delete 结果: " + res);
        sqlSession.commit();
        sqlSession.close();
        logger.info("delete 结束方法......................");
    }


}

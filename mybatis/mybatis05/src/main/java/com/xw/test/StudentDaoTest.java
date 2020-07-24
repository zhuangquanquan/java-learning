package com.xw.test;


import com.xw.dao.TeacherMapper;
import com.xw.pojo.Teacher;
import com.xw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class StudentDaoTest {

    private static Logger logger = Logger.getLogger(StudentDaoTest.class);

    public static void main(String[] args) {
        //getTeacher();
        getTeacherStudents();
    }

    public static void getTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher("1");
        System.out.println(teacher);
        sqlSession.close();
    }

    public static void getTeacherStudents() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacherStudents("1");
        System.out.println(teacher);
        sqlSession.close();
    }



}

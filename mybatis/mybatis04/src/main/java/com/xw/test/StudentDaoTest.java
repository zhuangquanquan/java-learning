package com.xw.test;


import com.xw.dao.StudentMapper;
import com.xw.pojo.Student;
import com.xw.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class StudentDaoTest {

    private static Logger logger = Logger.getLogger(StudentDaoTest.class);

    public static void main(String[] args) {
        //getStudentList();
        getStudentsTeacher();
    }

    public static void getStudentList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudentList();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
        sqlSession.close();
    }

    public static void getStudentsTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudentsTeacher();
        for (Student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }



}

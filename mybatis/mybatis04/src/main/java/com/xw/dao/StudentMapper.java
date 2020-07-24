package com.xw.dao;

import com.xw.pojo.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> getStudentList();

    List<Student> getStudentsTeacher();
}

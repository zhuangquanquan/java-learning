package com.xw.dao;

import com.xw.pojo.Teacher;

public interface TeacherMapper {

    Teacher getTeacher(String id);

    Teacher getTeacherStudents(String id);
}

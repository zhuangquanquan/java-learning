多对一
```sql
DROP TABLE IF EXISTS `my_student`;
CREATE TABLE `my_student` (
  `id` varchar(10) NOT NULL COMMENT 'id',
  `name` varchar(128) NOT NULL COMMENT 'name',
  `tid` varchar(128) NOT NULL COMMENT 'tid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='my_student';

DROP TABLE IF EXISTS `my_teacher`;
CREATE TABLE `my_teacher` (
  `id` varchar(10) NOT NULL COMMENT 'id',
  `name` varchar(128) NOT NULL COMMENT 'name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='my_teacher';

INSERT INTO `my_student` VALUES ('1', '张三', '1');
INSERT INTO `my_student` VALUES ('2', '李四', '1');
INSERT INTO `my_student` VALUES ('3', '王五', '1');

INSERT INTO `my_teacher` VALUES ('1', '徐老师');
```

```xml
 <mappers>
        <mapper resource="mapper/StudentMapper.xml" />
        <mapper resource="mapper/TeacherMapper.xml" />

        <!-- 注意使用class，需要mapper.xml和实体类在同一个目录 -->
       <!--<mapper class="com.xw.dao.StudentMapper" />
        <mapper class="com.xw.dao.TeacherMapper" />-->
    </mappers>
```


```xml
多对一返回结果配置使用：association

<resultMap id="studentsTeacher" type="com.xw.pojo.Student" >
        <id property="id" column="sid" />
        <id property="name" column="sname" />
        <association property="teacher" javaType="com.xw.pojo.Teacher">
            <result property="id" column="tid" />
            <result property="name" column="tname" />
        </association>
    </resultMap>

    <select id="getStudentsTeacher" resultMap="studentsTeacher" >
       select s.id as sid, s.name as sname, t.id as tid, t.name as tname from my_student s, my_teacher t where s.tid = t.id;
    </select>
```
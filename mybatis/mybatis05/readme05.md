一对多
使用collection
```xml
<resultMap id="teacherStudents" type="com.xw.pojo.Teacher">
        <result property="id" column="tid" />
        <result property="name" column="tname" />
        <collection property="studentList" ofType="com.xw.pojo.Student">
            <result property="id" column="sid" />
            <result property="name" column="sname" />
            <result property="tid" column="stid" />
        </collection>
    </resultMap>

    <select id="getTeacherStudents" resultMap="teacherStudents">
        select s.id as sid, s.name as sname, s.tid as stid, t.id as tid, t.name as tname from my_student s, my_teacher t
        where s.tid = t.id and t.id = #{id}
    </select>
```
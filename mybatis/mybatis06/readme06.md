动态sql
```xml
 <select id="getUserList" parameterType="map" resultMap="userMap" >
        select * from my_user
        <where>
            <if test="id != null">
                id = #{id}
            </if>
            <if test="username != null">
                and username = #{username}
            </if>
        </where>

    </select>
```
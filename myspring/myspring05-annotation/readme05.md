###注解开发
#### 1. bean-autowired.xml
1) java类添加AutoWired注解,默认是按类型自动转配的
```java
public class User {

    @Autowired
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
```
2) 需要添加xmlns:context 和 开启注解
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 开启注解 -->
    <context:annotation-config/>

    <!-- bean配置，等价于 <bean id="user" class="com.xw.pojo.User" autowire="byType"/> -->
    <bean id="dog1" class="com.xw.pojo.Dog"/>
    <bean id="user" class="com.xw.pojo.UserAutowired"/>

</beans>

```
####2. bean-qualifier.xml
```java
public class UserQualifier {

    @Autowired
    @Qualifier("dog1")
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 开启注解 -->
    <context:annotation-config/>

    <bean id="dog1" class="com.xw.pojo.Dog"/>
    <bean id="dog2" class="com.xw.pojo.Dog"/>
    <bean id="user" class="com.xw.pojo.UserQualifier"/>

</beans>
```

####3. bean-resource.xml
结论：先进行byName查找，失败；再进行byType查找，成功。
多个使用
```java
public class UserResource {

    @Resource(name="dog1")
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
```


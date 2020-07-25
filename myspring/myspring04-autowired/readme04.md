###自动装配三种方式
1. 在xml中显示配置
2. 在java中显式配置
3. 隐式的bean发现机制和自动装配

1)bean-xml.xml: xml方式注入
2)bean-byName.xml:
类属性名称和bean id名称必须一致
```java
 private Cat cat;
```
```xml
  <bean id="cat" class="com.xw.pojo.Cat" />
 <bean id="cat1" class="com.xw.pojo.Cat" />
 <bean id="user" class="com.xw.pojo.User" autowire="byName">
    <property name="type" value="byName" />
 </bean>
``` 
3)bean-byType.xml:

```xml
 <bean id="cat1" class="com.xw.pojo.Cat" />
 <bean id="user" class="com.xw.pojo.User" autowire="byName">
    <property name="type" value="byName" />
 </bean>
```



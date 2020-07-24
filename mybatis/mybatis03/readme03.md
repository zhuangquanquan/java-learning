使用注解开发
```java
  @Select("select * from my_user where id=#{tid}")
  public User getUserById(@Param("tid") long id);
  
  当使用了@param()注解后，#{}里面的变量应该对应@param()里面的变量

```


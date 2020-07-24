缓存

###开机二级缓存:

mybatis-config.xml添加:
```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
    <setting name="logImpl" value="LOG4J"/>
</settings>
```
Mapper.xml文件添加,单位:秒:
```xml
 <cache eviction="FIFO" flushInterval="5000" size="512" readOnly="true" />
```


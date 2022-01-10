# Spring

> Spring使用的是基本的[JavaBean](https://baike.baidu.com/item/JavaBean/529577)来完成以前只可能由[EJB](https://baike.baidu.com/item/EJB/144195)完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。

## Spring 开发步骤

- 导入Spring依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.5.RELEASE</version>
    </dependency>
</dependencies>
```



- 创建所需bean
- 创建applicationContext.xml
- 在配置文件中进行配置
- 创建ApplicationContext对象并获取bean对象



### Spring配置文件

> 在Spring中常用applicationContext.xml 来表示Spring的配置文件



#### Bean

##### Bean实例化的三种方式

- 无参构造方法
- 工厂静态方法
- 工厂实例方法





> 

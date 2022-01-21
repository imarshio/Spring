# Spring

> `Spring`使用的是基本的[JavaBean](https://baike.baidu.com/item/JavaBean/529577)来完成以前只可能由[EJB](https://baike.baidu.com/item/EJB/144195)完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。
>
> Spring是一个轻量级的**控制反转**和**面向切面**的容器的框架。

参考：https://www.bilibili.com/video/BV1wi4y1P7Jm?p=5&spm_id_from=333.1007.top_right_bar_window_history.content.click

## Spring框架图

![img](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.bubuko.com%2Finfo%2F201912%2F20191215203642824082.png&refer=http%3A%2F%2Fimage.bubuko.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644468709&t=debf909e169b692277d269980268a587)

### Test

> 面向全过程的测试功能。

### Core Container

> 核心容器功能，

#### Beans

> 业务对象

#### Context

> 业务上下文

### 中间层

#### IoC

> 控制反转，`Inverse of Control`，指的是将数据的创建与管理交给Spring容器。

#### AOP

> 面向切面编程，指的是在无需更改业务代码的情况下实现对业务的增强。

### Data

> Spring提供的数据管理服务



### Web



## Spring 框架

### SpringIoC

> 指的是将数据的创建与管理交给Spring容器，接下来的项目将会演示Spring是如何管理对象的。
>
> - 如何将对象交给Spring容器
> - 如何从Spring容器获取对象
> - 创建对象的几种方式
>     - 基于XML
>     - 基于注解
> - Spring容器如何完成对对象属性赋值

#### 部署过程

##### 创建工程

| 创建maven工程，如下                                          |
| ------------------------------------------------------------ |
| ![image-20220121172054662](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220121172056.png) |

##### 添加依赖

> Spring IoC的核心组件为`Beans`、`core`、`context`、`aop`、`expression`。但是在context中已经包含了这些组件。
>
> ![image-20220111125629160](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220111125725.png)

```xml
<!-- 在pom文件中添加如下依赖 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.0.5.RELEASE</version>
</dependency>
```

##### 创建配置文件

> 在Spring中，我们默认的将配置文件命名为`applicationContext.xml`，且位于resource下面。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 标准模板 -->
</beans>
```

##### 配置实体类

> 在配置文件中进行配置实体类，将实体的创建交由容器管理，这里也是`IoC`的体现
>
> - **IoC**（`Inverse of Control`）:控制反转，将实体的创建交由Spring容器管理，通过Spring对象工厂完成对象的创建，而不是用户去创建，
> - **DI**（`Dependency Injection`）：依赖注入，在Spring容器完成对象创建的同时，依赖Spring容器完成对象属性的赋值

**默认使用无参构造**

```xml
<!-- 无参创建实例对象，  -->
<bean id="student" class="com.marshio.ioc.pojo.Student"/>
```

##### 获取对象

```sql
@Test
public void springIoCTest() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    //获取无参对象
    StudentIocDao studentDao = (StudentIocDao) applicationContext.getBean("studentDao1");
    studentDao.queryStudentOnIoc();

    //获取依赖注入含参数对象
    Student student = (Student) applicationContext.getBean("student");
    System.out.println(student.toString());
}
```



#### IoC的体现





```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--  -->
    <!--   * 常用  id:表示这个类的唯一标识，以后取对象都需要通过id来获取 -->
    

    <!--  使用静态工厂方法  -->
    <!--    <bean id="userDao" class="com.marshio.factory.StaticFactory" factory-method="getUserDao" />-->

    <!--  使用工厂方法  -->
    <!--    <bean id="factory" class="com.marshio.factory.DynamicFactory"/>-->
    <!--    <bean id="userDao" factory-bean="factory" factory-method="getUserDao"/>-->
</beans>
```



- 创建ApplicationContext对象并获取bean对象

    - 加载配置文件
    - 解析配置文件

    ```java
    //初始化Spring容器，即找到配置文件，并加载配置文件,在这里我们传入了配置文件的路径
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    ```

    ​	上面代码的实现`ClassPathXmlApplicationContext.class`如下

    ```java
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        // configLocation即我们传入的配置文件名称，把配置文件中的内容读取出来，存到一个Java对象中
        this(new String[]{configLocation}, true, (ApplicationContext)null);
    }
    ```

    ​	上面代码调用了`ClassPathXmlApplicationContext.class`的构造方法如下：

    ```java
    // f
    public ClassPathXmlApplicationContext(
        String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
        throws BeansException {
    
        super(parent);
        //这里调用了设置配置文件的路径方法
        setConfigLocations(configLocations);
        if (refresh) {
            refresh();
        }
    }
    
    //设置配置文件
    public void setConfigLocations(@Nullable String... locations) {
        if (locations != null) {
            Assert.noNullElements(locations, "Config locations must not be null");
            this.configLocations = new String[locations.length];
            for (int i = 0; i < locations.length; i++) {
                // 解析配置文件
                this.configLocations[i] = resolvePath(locations[i]).trim();
            }
        }
        else {
            this.configLocations = null;
        }
    }
    
    //解析路径
    protected String resolvePath(String path) {
        return getEnvironment().resolveRequiredPlaceholders(path);
    }
    ```


### SpringAOP



### Spring配置文件

> 在Spring中会用到很多的配置文件,可以参考 https://blog.csdn.net/liulei952413829/article/details/120522864，当然，配置文件是会涉及到版本冲突等问题的，这里还未收录。
>
> 如下
>
> `applicationContext.xml`
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
>     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>     xmlns:aop="http://www.springframework.org/schema/aop"
>     xmlns:context="http://www.springframework.org/schema/context"
>     xsi:schemaLocation="http://www.springframework.org/schema/beans
>     http://www.springframework.org/schema/beans/spring-beans.xsd
>     http://www.springframework.org/schema/aop
>     http://www.springframework.org/schema/aop/spring-aop.xsd
>     http://www.springframework.org/schema/context
>     http://www.springframework.org/schema/context/spring-context.xsd">
>  <!--  带有context、aop配置规范的xml  -->
> </beans>
> ```
>
> `web.xml`
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
>       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
>                    http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
>       version="4.0">
> 
> </web-app>
> ```
>
> `mybatis-config.xml`
>
> ```xml
> <?xml version="1.0" encoding="UTF-8" ?>
> <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
>      "http://mybatis.org/dtd/mybatis-3-config.dtd">
> <configuration>
>  <!-- mybatis-config的模板-->
> </configuration>
> ```
>
> `mapper.xml`
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <!DOCTYPE mapper
>      PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
>      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
> <!--mapper 相当于Dao层接口类的实现类,namespace 放的是实现的接口的路径-->
> <mapper namespace="com.marshio.dao.StudentDao">
> 
> </mapper>
> ```
>
> `log4j.properties`
>
> ```properties
> # 声明日志的输出级别及输出方式
> log4j.rootLogger=DEBUG,stdout
> # MyBatis logging configuration...
> log4j.logger.org.mybatis.example.BlogMapper=TRACE
> # Console output...
> log4j.appender.stdout=org.apache.log4j.ConsoleAppender
> log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
> # 定义日志的打印格式  %t 表示线程名称  %5p 日志级别 %msg日志信息
> log4j.appender.stdout.layout.ConversionPattern=[%t] %5p - %m%n
> ```
>
> 



#### Bean

##### Bean实例化的三种方式

- 无参构造方法
- 工厂静态方法
- 工厂实例方法


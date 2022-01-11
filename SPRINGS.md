# Spring

> Spring使用的是基本的[JavaBean](https://baike.baidu.com/item/JavaBean/529577)来完成以前只可能由[EJB](https://baike.baidu.com/item/EJB/144195)完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。

参考：https://www.bilibili.com/video/BV1wi4y1P7Jm?p=5&spm_id_from=333.1007.top_right_bar_window_history.content.click

## Spring框架图

![img](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.bubuko.com%2Finfo%2F201912%2F20191215203642824082.png&refer=http%3A%2F%2Fimage.bubuko.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644468709&t=debf909e169b692277d269980268a587)

### Test





### Core Container





### 中间层





### Data





### Web







## Spring 框架部署

- 创建Maven工程
    - Java
    - Web

- 添加Spring IoC依赖，核心组件为
    - beans
    - core
    - `context`
    - aop
    - expression

```xml
<!-- 注入spring依赖 
spring-context 包含spring所需的核心组件，包括core、context、aop、expression组件-->
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.5.RELEASE</version>
    </dependency>
</dependencies>
```

![image-20220111125629160](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220111125725.png)

- 创建Spring配置文件：applicationContext.xml

    > 通过配置文件让Spring知道容器该创建什么对象，给对象属性的赋值。
    >
    > 配置文件名称可以自定义，但是通常来说我们都使用applicationContext.xml。
    >
    > 配置文件位于resource下面

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
        <!-- 标准模板 -->
    </beans>
    ```

- 在配置文件中进行配置实体类，将实体的创建交由容器管理，这里也是`IoC`的体现

    - IoC（Inverse of Control）:控制反转，将实体的创建交由Spring容器管理，通过Spring对象工厂完成对象的创建，而不是用户去创建，
    - DI（Dependency Injection）：依赖注入，在Spring容器完成对象创建的同时，依赖Spring容器完成对象属性的赋值

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
    
        <!-- 通过bean将实体类配置给spring进行管理 -->
        <!--  默认使用无参构造 * 常用  id:表示这个类的唯一标识，以后取对象都需要通过id来获取 -->
        <bean id="user" class="com.marshio.pojo.User">
        	<!-- 给实体创建时赋初始值 -->    
            <property name="uNum" value="1001"/>
            <property name="uName" value="ms"/>
            <property name="uAge" value="1"/>
        </bean>
    
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

    



### Spring配置文件

> 在Spring中常用applicationContext.xml 来表示Spring的配置文件



#### Bean

##### Bean实例化的三种方式

- 无参构造方法
- 工厂静态方法
- 工厂实例方法





> 

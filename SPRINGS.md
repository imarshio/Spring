---
typora-copy-images-to: upload
---

# Spring

> `Spring`使用的是基本的[JavaBean](https://baike.baidu.com/item/JavaBean/529577)来完成以前只可能由[EJB](https://baike.baidu.com/item/EJB/144195)完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。
>
> Spring是一个轻量级的**控制反转**和**面向切面**的容器的框架。

参考：https://www.bilibili.com/video/BV1wi4y1P7Jm?p=5&spm_id_from=333.1007.top_right_bar_window_history.content.click

## Spring框架

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



## Spring Framework

### SpringIoC

> 指的是将数据的创建与管理交给Spring容器，接下来的项目将会演示Spring是如何管理对象的。
>
> - 如何将对象交给Spring容器
> - 如何从Spring容器获取对象
> - 创建对象的几种方式
>     - 基于XML，详情查看 [XML版]( # XML版)。
>     - 基于注解，详情查看 [注解版]( # 注解版)。
> - Spring容器如何完成对对象属性赋值

#### 部署

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

#### 使用

> 在配置文件中进行配置实体类，将实体的创建交由容器管理，这里也是`IoC`的体现
>
> - **IoC**（`Inverse of Control`）:控制反转，将实体的创建交由Spring容器管理，通过Spring对象工厂完成对象的创建，而不是用户去创建。
>
>     ```xml
>     <!-- 通过bean将实体类配置给Spring进行管理,id表示实体类的唯一表示-->
>     <bean id="student1" class="com.marshio.aop.pojo.Student"/>
>     ```
>
> - **DI**（`Dependency Injection`）：依赖注入，在Spring容器完成对象创建的同时，依赖Spring容器完成对象属性的赋值。
>
>     ```xml
>     <!-- 利用依赖注入给对象参数赋值   -->
>     <bean id="student2" class="com.marshio.aop.pojo.Student">
>         <!--简单类型 -->
>         <property name="stuNum" value="2017" />
>         <property name="stuName" value="mas" />
>     </bean>
>     ```

##### 实体类

```java
//创建实体类
public class Student {
    private int id;
    private String stuNum;
    private String stuName;
    private String stuSex;
    private int stuAge;
    private Date stuBirth;
    private Clazz clazz;
    private List<String> hobbies;
}
```

```java
public class User {
    private int uid;
    private String userNum;
    private String userName;
}
```

> 声明实体类之后，需要配置实体类将实体类交由Spring管理。
>
> 配置实体类有两种方式
>
> - XML版
> - 注解版

##### XML版

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 通过bean将实体类配置给Spring进行管理,id表示实体类的唯一表示-->
    <bean id="student1" class="com.marshio.aop.pojo.Student"/>
    
    <!-- 利用依赖注入给对象参数赋值   -->
    <bean id="student2" class="com.marshio.aop.pojo.Student">
        <!--简单类型 -->
        <property name="stuNum" value="2017" />
        <property name="stuName" value="mas" />
        <property name="stuAge" value="20" />
        <property name="stuSex" value="0" />
        <!--集合类型 -->
        <property name="hobbies">
            <list>
                <value>旅游</value>
                <value>电影</value>
                <value>Java</value>
            </list>
        </property>
    </bean>
</beans>
```



##### 注解版

> 注解即利用注解将对象交给Spring容器管理，在Spring中共有四种将对象交给Spring容器管理的注解。
>
> - Service：主要声明业务处理类，`Service`层。
> - Controller：主要声明控制器类，`servlet`、`controller`层
> - Repository：主要声明持久化配置类，`Dao`层
> - Component：除`controller`、`servlet`、`service`、`Dao`层之外的类使用此注解。
>
> 其他常用对象声明注解
>
> - Scope：类注解，相当于`bean`标签的`scope`属性，`@Scope("prototype")` 表示声明当前类为非单例模式
> - Lazy：类注解，声明一个单例类是否为懒汉模式，`@Lazy(true)` 表示声明为懒汉模式，默认为饿汉模式
> - PostConstruct：方法注解，声明该方法为类的初始化方法，在构造器之后执行，相当于`bean`的`init-method`属性
> - PreDestory：方法注解，声明该方法为类的销毁方法，对象从容器中释放之前，相当于`bean`的`destory-method`方法
> - Autowired：属性注解，方法注解，声明当前属性自动装配，默认为`byType`。
> - Resource：属性注解，

**更新配置文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 声明使用注解配置 -->
    <context:annotation-config/>

    <!-- 声明Spring工厂注解的扫描范围 -->
    <context:component-scan base-package="com.marshio.ioc.pojo"/>
</beans>
```



- **Service**

```java
```

-  **Controller**

```java
```

-  **Repository**

```java

```

-  **Component**

```java
//component相当于bean，value相当于id，可省略，默认为当前类名（首字母小写）
@Component(value = "user")
public class User {
    private int uid;
    private String userNum;
    private String userName;
}
```



#### 获取对象

```java
@Test
public void springIoCTest() {
    //获取配置文件，并对配置文件进行解析，通过解析可以获取对象的类名，参数，构造方法，初始值等
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    
    //通过解析获取XML配置的对象
    Student student1 = (Student) applicationContext.getBean("student1");
    System.out.println(student1.toString());

    //获取依赖注入含参数对象
    Student student2 = (Student) applicationContext.getBean("student2");
    System.out.println(student2.toString());
}
```

#### 源码解析

##### 加载配置文件

```java
//初始化Spring容器，即找到配置文件，并加载配置文件,在这里我们传入了配置文件的路径
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
```

##### 解析配置文件

如上代码`ClassPathXmlApplicationContext`构造如下

```java
public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
    // configLocation即我们传入的配置文件名称，这里又调用了另外一个构造函数，如下
    this(new String[]{configLocation}, true, (ApplicationContext)null);
}
//构造函数如下，三个参数，第一个是配置文件地址，第二个是是否刷新，第三个是配置文件的父级配置文件
public ClassPathXmlApplicationContext(
    String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
    throws BeansException {
	//这里parent为空
    super(parent);
    //这里调用了设置配置文件的路径方法，代码如下
    setConfigLocations(configLocations);
    if (refresh) {
        refresh();
    }
}

//设置配置文件，参数为配置文件地址
public void setConfigLocations(@Nullable String... locations) {
    if (locations != null) {
        Assert.noNullElements(locations, "Config locations must not be null");
        this.configLocations = new String[locations.length];
        //循环配置文件
        for (int i = 0; i < locations.length; i++) {
            // 解析配置文件，代码如下
            this.configLocations[i] = resolvePath(locations[i]).trim();
        }
    }
    else {
        this.configLocations = null;
    }
}

//解析配置文件
protected String resolvePath(String path) {
    //这里面是如何具体解析的，可以使用debug一步一步跟着解析
    return getEnvironment().resolveRequiredPlaceholders(path);
}
```

### 代理模式

> 又称中介模式，说代理可能不太懂，但是如果说中介模式的话，你应该就懂了，毕竟生活中的中介可是非常多的。
>
> 代理模式即在**不修改**被代理对象的基础上，通过扩展代理类，进行一些功能的附加与增强。
>
> 需要注意的是，**被代理对象与代理对象都需要实现或继承同一接口或类**。
>
> 代理模式分为**静态代理**和**动态代理**。

![image-20220124160407284](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220124160408.png)

> 那么为什么需要中介模式呢？
>
> - 职责清晰
> - 增强了可扩展性
> - 智能化，动态代理
>
> 当然，代理模式同样会带来一些问题。
>
> - 请求多了一层，降低处理速度，反应变慢
> - 复杂的逻辑

#### 静态代理

> 静态即不变，代理单一固定。类似婚介所、房产中介，他们都是固定的代理，只代理人的结婚这一方面。

举个:chestnut:

接口类	

```java
public interface StaticDao {
	//接口
    int insert();
    int delete();
}
```

被代理类

```java
//被代理类专注于做自己的事情
public class StudentDaoImpl implements StaticDao {
    @Override
    public int insert() {
        System.out.println("focus on insert into student ...");
        return 1;
    }
    @Override
    public int delete() {
        System.out.println("focus on delete from student ...");
        return 1;
    }
}
public class UserDaoImpl implements StaticDao {
    @Override
    public int insert() {
        System.out.println("focus on insert into user ...");
        return 0;
    }
    @Override
    public int delete() {
        System.out.println("focus on insert into user ...");
        return 0;
    }
}
```

代理类

```java
public class StaticProxy implements StaticDao {
    //获取被代理对象
    private static StaticDao staticDao;
    public StaticProxy(StaticDao real){
        staticDao = real;
    }
    @Override
    public int insert() {
        System.out.println("proxy working...");
        begin();//开启事务
        staticDao.insert();
        commit();//提交事务
        return 0;
    }
    @Override
    public int delete() {
        System.out.println("proxy working...");
        begin();
        staticDao.delete();
        commit();
        return 0;
    }
    public void begin(){
        System.out.println("开启事务。。。");
    }
    public void commit(){
        System.out.println("结束事务。。。");
    }
}
```

测试类

```java 
@Test
public void staticProxyTest(){
    StudentDaoImpl studentDao = new StudentDaoImpl();
    //这里我们只需要调用代理类即可
    StaticProxy proxy1 = new StaticProxy(studentDao);
    System.out.println(proxy1.insert());
    System.out.println(proxy1.delete());
    
    UserDaoImpl userDao = new UserDaoImpl();
    StaticProxy proxy2 = new StaticProxy(userDao);
    System.out.println(proxy2.insert());
    System.out.println(proxy2.delete());
}
```

以上就是静态代理的工作方式，虽然使用代理可以**降低系统的耦合性**，**增加扩展性**，被代理类只需要关注核心业务的实现，将**通用管理逻辑**与**业务逻辑**分离，**提高代码的复用性**。

但是静态代理只能为固定的一些类做代理，如果我们有很多对象需要被代理，那么就需要写很多个代理类，增加工作量以及代码体量。

#### 动态代理

> 为了解决以上问题，动态代理应运而生，动态代理几乎可以为所有的类产生代理类这样就避免了大量的代码逻辑。
>
> 动态代理有两种实现方式
>
> - JDK动态代理
> - CGLib动态代理
>
> ![image-20220124161217121](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220124161246.png)

##### JDK动态代理

> Java动态代理通过 获取**被代理对象实现的接口** 产生 **代理对象**。
>
> Java动态代理类位于java.lang.reflect包下，InvocationHandler。
>
> ```
> public Object invoke(Object proxy, Method method, Object[] args)
> ```
>
> 即此时是没有代理对象的，代理对象是动态产生的。而想要产生一个匹配的代理对象，需要提前准备好**接口**与**被代理对象**，然后JDK通过被代理对象获取其接口，然后再通过实现接口产生一个代理对象。

接口类

```
public interface StaticDao {
    int insert();
    int delete();
}
```

被代理对象

```java
public class StudentDaoImpl implements StaticDao {

    @Override
    public int insert() {
        System.out.println("focus on insert into student ...");
        return 1;
    }

    @Override
    public int delete() {
        System.out.println("focus on delete from student ...");
        return 1;
    }
}
public class UserDaoImpl implements StaticDao {
    @Override
    public int insert() {
        System.out.println("focus on insert into user ...");
        return 0;
    }

    @Override
    public int delete() {
        System.out.println("focus on insert into user ...");
        return 0;
    }
}
```

获取代理对象

```java
public class JDKDynamicProxy implements InvocationHandler {
    //代理对象
    private final Object real;
    public JDKDynamicProxy(Object obj){
        this.real = obj;
    }

    public Object getProxy(){
        //想要生成代理对象，就需要知道被代理对象的类加载器和接口类
        ClassLoader classLoader= real.getClass().getClassLoader();
        Class<?>[] interfaces = real.getClass().getInterfaces();
        /*
        * 我们需要使用Proxy的newProxyInstance方法，一共需要三个参数
        * 1、ClassLoader，类加载器
        * 2、Class<?>[] interfaces，接口类集合
        * 3、InvocationHandler，调用处理程序，即使用产生的代理对象调用方法时，用于拦截方法执行的处理器
        * */
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    /**
     * 调用方法，并做事务管理
     * @param proxy 代理类
     * @param meth 调用的方法（代理类）
     * @param args 调用方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method meth, Object[] args) throws Throwable {
        begin();
        //调用函数
        // 第一个参数：那个对象调用这个函数
        // 第二个参数，被调用的函数的参数
        Object invoke = meth.invoke(real, args);
        commit();
        return invoke;
    }

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }
}

```

测试

```java
public void JDKDynamicProxy() {
    StudentDaoImpl studentDao = new StudentDaoImpl();
    JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(studentDao);
    //获取代理对象
    StaticDao dao = (StaticDao) jdkDynamicProxy.getProxy();
    //代理对象调用方法，会被
    dao.insert();
    dao.delete();
}
```

> JDK动态代理有一个缺点就是被代理对象必须实现了一个接口。
>
> 不然会报错。

##### CGLib动态代理

> 为了纠正上面没实现接口就导致动态代理失效，所以CGLib动态代理应运而生。
>
> 原理相同，只是被代理类不需要实现接口，而是通过继承被代理对象来实现代理。
>
> 但是，CGLib动态代理不能为final类创建代理对象。

添加CGLib依赖

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```



接口类

```
public interface StaticDao {
    int insert();
    int delete();
}
```

被代理对象

```java
public class StudentDaoImpl implements StaticDao {

    @Override
    public int insert() {
        System.out.println("focus on insert into student ...");
        return 1;
    }

    @Override
    public int delete() {
        System.out.println("focus on delete from student ...");
        return 1;
    }
}
public class UserDaoImpl implements StaticDao {
    @Override
    public int insert() {
        System.out.println("focus on insert into user ...");
        return 0;
    }

    @Override
    public int delete() {
        System.out.println("focus on insert into user ...");
        return 0;
    }
}
```

获取代理对象

```java
public class CGLibDynamicProxy implements MethodInterceptor {

    private final Object real;
    public CGLibDynamicProxy(Object obj){
        this.real = obj;
    }

    public Object getProxy(){
        //与JDK类似,
        Enhancer enhancer = new Enhancer();
        //设置该类为其子类，被代理类为其父类
        enhancer.setSuperclass(real.getClass());
        //回调函数
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        //通过反射调用代理类
        // 第一个参数：那个对象调用这个函数
        // 第二个参数，被调用的函数的参数
        Object returnV = method.invoke(real, objects);
        commit();
        return returnV;
    }

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }
}
```



### SpringAOP

> 对代理模式有一个了解后，我们就可以正式进入Spring的AOP学习了。
>
> AOP：Aspect Oriented Programming 面向切面编程，指利用“横切技术”，对原有业务进行拦截，然后再拦截的横切面上添加特定的业务逻辑，对原有业务进行增强。
>
> 概括来说，就是在不改动原有代码的情况下，对业务进行增强。
>
> 利用的技术就是横切，将业务逻辑嵌入。
>
> ![1617008695615](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220124173614.png)

#### 部署

##### 创建工程

略

##### 添加AOP依赖

```xml
<!-- SpringAOP 指的是Spring面向切面编程，需要SpringIoC的支持-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.0.5.RELEASE</version>
</dependency>
<!--aspects依赖，即AOP的核心组件-->
<dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-aspects</artifactId>
     <version>5.2.13.RELEASE</version>
</dependency>
```

##### 创建配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--添加aop规范，才能使用aop相关规则-->
</beans>
```

#### 使用

##### 切面类

```java
public class TransactionManager {

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }
}
```

##### XML

> XML需要配置`applicationContext.xml`文件，在这里面配置AOP 的功能。
>
> 配置步骤
>
> - 创建切面类，在切面类中定义切点方法
> - 将切面类配置给Spring容器
> - 声明切入点
> - 配置AOP通知策略

```xml
<!--声明切面类，即切入点前后需要配置的类-->
<bean id="transactionManager" class="com.marshio.aop.utils.TransactionManager"/>

<!--配置增强功能（AOP）-->
<aop:config>
    <!--声明切入点（需要被事务管理的类），切入点是业务代码，比如一个提交业务，或者一个新增业务。-->
    <!--
       execution(* com.marshio.aop.dao.*.*(..))
       第一个参数 * 代表的是返回类型，不限制
       第二个参数 com.marshio.aop.dao.*.*(..) 代表的是dao包下面的所有类的所有方法的所有参数类型
    -->
    <aop:pointcut id="allDao" expression="execution(* com.marshio.aop.dao.*.*(..))"/>

    <!--声明切面类方法-->
    <aop:aspect ref="transactionManager" >
        <!--通知-->
        <aop:before method="begin" pointcut-ref="allDao"/>
        <aop:after method="commit" pointcut-ref="allDao"/>
    </aop:aspect>
</aop:config>
```

测试

```java
@Test
public void testXML() {
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    StaticDao userDao = (StaticDao) app.getBean("userDao");
    userDao.insert();
}
```

##### 切入点声明

```xml
<!--使用aop:pointcut标签声明切入点：切入点可以是一个方法-->
<aop:pointcut id="book_insert" expression="execution(* com.qfedu.dao.BookDAOImpl.insert())"/>

<!--BookDAOImpl类中所有无参数无返回值的方法-->
<aop:pointcut id="book_pc1" expression="execution(void com.qfedu.dao.BookDAOImpl.*())"/>

<!--BookDAOImpl类中所有无返回值的方法-->
<aop:pointcut id="book_pc2" expression="execution(void com.qfedu.dao.BookDAOImpl.*(..))"/>

<!--BookDAOImpl类中所有无参数的方法-->
<aop:pointcut id="book_pc3" expression="execution(* com.qfedu.dao.BookDAOImpl.*())"/>

<!--BookDAOImpl类中所有方法-->
<aop:pointcut id="book_pc4" expression="execution(* com.qfedu.dao.BookDAOImpl.*(..))"/>

<!--dao包中所有类中的所有方法-->
<aop:pointcut id="pc5" expression="execution(* com.qfedu.dao.*.*(..))"/>

<!--dao包中所有类中的insert方法-->
<aop:pointcut id="pc6" expression="execution(* com.qfedu.dao.*.insert(..))"/>

<!--所有类方法-->
<aop:pointcut id="pc7" expression="execution(* *(..))"/>
```

##### 通知策略

> AOP通知策略：就是声明将切面类中的切点方法如何植入到切入点
>
> - before
> - after
> - before-throwing
> - after-throwing
> - around

```xml
<bean id="transactionManager" class="com.marshio.aop.utils.TransactionManager"/>
<aop:config>
    <!--使用aop:pointcut标签声明切入点：切入点可以是一个方法-->
    <aop:pointcut id="book_insert" expression="execution(* com.qfedu.dao.BookDAOImpl.insert())"/>

    <aop:aspect ref="transactionManager">
        <!--aop:before 前置通知，切入到指定切入点之前-->
        <aop:before method="method1" pointcut-ref="book_insert"/>
        <!--aop:after 后置通知，切入到指定切入点之后-->
        <aop:after method="method2" pointcut-ref="book_insert"/>
        <!--aop:after-throwing 异常通知，切入点抛出异常之后-->
        <aop:after-throwing method="method3" pointcut-ref="book_insert"/>
        <!--aop:after-returning 方法返回值返回之后，对于一个Java方法而言return返回值也是方法的一部分
             因此“方法返回值返回之后”和“方法执行结束”是同一个时间点，随意after 和 after-returning根据配置
             的顺序决定执行顺序-->
        <aop:after-returning method="method4" pointcut-ref="book_insert"/>
        <aop:around method="method5" pointcut-ref="book_insert"/>
    </aop:aspect>
</aop:config>
```



##### 注解

**更新配置文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--添加context、aop规范-->

    <!-- 声明context使用注解配置 -->
    <context:annotation-config/>

    <!-- 声明Spring工厂注解的扫描范围 -->
    <context:component-scan base-package="com.marshio.aop.pojo"/>

    <!-- 基于注解配置的aop代理-->
    <aop:aspectj-autoproxy/>
</beans>
```

**使用**

```java
package com.marshio.aop.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author masuo
 * @data 25/1/2022 上午8:21
 * @Description 注解式事务管理 --- 虽然是注解类，但是步骤都是一样的
 * Component：表示将此类交给Spring容器管理，是一个对象的意思
 * Aspect：表示这个类是一个切面类
 */
@Component
@Aspect
public class TransactionManagerOnAnnotation {

    //声明切入点
    @Pointcut("execution(* com.marshio.aop.dao.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void begin() {
        System.out.println("开启事务...");
    }

    @After("pointCut()")
    public void commit() {
        System.out.println("提交事务...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrow() {
        System.out.println("事务回滚。。。");
    }

    @AfterReturning("pointCut()")
    public void afterReturn() {
        //AfterReturning == After , 因为方法返回值返回之后，对于一个Java方法而言return返回值也是方法的一部分
        System.out.println("提交事务。。。");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("");
        long begin = System.currentTimeMillis();
        Object o = null;
        try {
            o = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long commit = System.currentTimeMillis();
        System.out.println("time:"+(commit-begin));
        return o;
    }
}
```

**测试**

```java
public class AnnotationDemo {

    @Test
    public void testAnnotation() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        StaticDao userDao = (StaticDao) app.getBean("userDao");
        userDao.insert();
    }
}
```

## Spring MVC

> Spring MVC是一个基于Spring的实现了MVC设计模式轻量级Web框架，实现了Model，View，Controller分离，将web层进行解耦，把复杂的web应用分成逻辑清晰的几部分，简化开发，减少出错，方便组内开发人员之间的配合。

![image-20220125102832124](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220125102833.png)



## Spring文件模板

> `applicationContext.xml`
>
> ```xml
><?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
> xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>  xmlns:aop="http://www.springframework.org/schema/aop"
>  xmlns:context="http://www.springframework.org/schema/context"
>  xsi:schemaLocation="http://www.springframework.org/schema/beans
>     http://www.springframework.org/schema/beans/spring-beans.xsd
>     http://www.springframework.org/schema/aop
>     http://www.springframework.org/schema/aop/spring-aop.xsd
>     http://www.springframework.org/schema/context
>     http://www.springframework.org/schema/context/spring-context.xsd">
>    <!--  带有context、aop配置规范的xml  -->
>    </beans>
>    ```
>    
>  `web.xml`
> 
> ```xml
><?xml version="1.0" encoding="UTF-8"?>
> <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
>   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
>                 http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
>    version="4.0">
>    
>    </web-app>
>    ```
>    
> `mybatis-config.xml`
> 
> ```xml
><?xml version="1.0" encoding="UTF-8" ?>
> <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
>  "http://mybatis.org/dtd/mybatis-3-config.dtd">
> <configuration>
> <!-- mybatis-config的模板-->
> </configuration>
>    ```
> 
>  `mapper.xml`
> 
> ```xml
><?xml version="1.0" encoding="UTF-8"?>
> <!DOCTYPE mapper
>  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
>   "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
> <!--mapper 相当于Dao层接口类的实现类,namespace 放的是实现的接口的路径-->
> <mapper namespace="com.marshio.dao.StudentDao">
>    
>    </mapper>
> ```
> 
> `log4j.properties`
> 
> ```properties
># 声明日志的输出级别及输出方式
> log4j.rootLogger=DEBUG,stdout
># MyBatis logging configuration...
> log4j.logger.org.mybatis.example.BlogMapper=TRACE
> # Console output...
> log4j.appender.stdout=org.apache.log4j.ConsoleAppender
> log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
> # 定义日志的打印格式  %t 表示线程名称  %5p 日志级别 %msg日志信息
> log4j.appender.stdout.layout.ConversionPattern=[%t] %5p - %m%n
> ```
> 
> 




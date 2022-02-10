---
title: Mybatis

date:2022.01.25
---

# Mybatis

> 前身是`ibatis`，是一个基于Java的数据库**持久层**框架。
>
> 官网地址 ： [Mybatis.org](https://mybatis.org/mybatis-3/index.html) 
>
> 支持定制化`SQL`，存储过程以及高级映射。可以通过`XML`或注解来配置和映射原生信息（数据库的数据），将接口和Java的`POJO`映射成数据库中的记录。
>
> Mybatis主要使用`SqlSession`来进行事务管理，而`SqlSession`是由`SQLSessionFactory`生成的实例，`SQLSessionFactory`可以由`SQLSessionFactoryBuilder`生成，`SQLSessionFactoryBuilder`由`XML`或预定义的配置类的实例获取。
>
> 用xml文件构建`SqlSessionFactory`实例是非常简单的事情。推荐在这个配置中使用类路径资源（`classpath resource`)，但你可以使用任何Reader实例，包括用文件路径或`file://`开头的`url`创建的实例。Mybatis有一个实用类----`Resources`，它有很多方法，可以方便地从类路径及其它位置加载资源。
>
> POJO ：Plain Ordinary Java Object

## 架构

![mybatis-framework](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220125110404.png)

- API接口层：提供给外部使用的接口API，开发人员通过这些本地API来操纵数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。

- 数据处理层：负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等。它主要的目的是根据调用的请求完成一次数据库操作。

- 基础支撑层：负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存处理，这些都是共用的东西，将他们抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑。

## 组件

![image-20220125112455476](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220125112457.png)

| 组件               | 相关描述                                                     |
| ------------------ | ------------------------------------------------------------ |
| SqlSession         | Mybatis 提供的面向用户的 API，可通过它来执行命令（增、删、改、查），获取映射器示例和管理事务。 |
| Executor           | Mybatis 的 SQL 执行器，Mybatis 中对数据库所有的增、删、改、查操作都是由它完成的。 |
| Statement Handler  | 封装了对 JDBC Statement 对象的操作。                         |
| Parameter Handler  | 用于为 `PreparedStatement` 和 `CallableStatement` 对象参数占位符设置值。 |
| Result Set Handler | 封装了对 JDBC 中的 `ResultSet` 对象操作，将 SELECT 查询结果抓换成 Java 对象。 |
| Type Handler       | Mybatis 中的类型处理器，用于处理 Java 类型和 JDBC 类型之间的映射。 |
| Configuration      | Mybatis 的主要配置。 包含属性、设置、类型别名、类型处理器、对象工厂、环境配置和映射器等信息。 |
| Mapped Statement   | 用于描述 Mapper 中的 SQL 配置信息。 对 Mapper XML 配置文件中 "<select \| update \| delete \| insert>" 等标签，或者 @Select、@Update、@Delete、@Insert等注解配置信息的封装。 |

## 核心组件

### SqlSession Factory Builder

> 它会根据配置或者代码来生成 `SqISessionFactory`，采用的是分步构建的 **Builder 模式**。

### SqlSession Factory

> 依靠它来生成 `SqlSession`，使用的是**工厂模式**。

### SqlSession

> 一个既可以发送 SQL 执行返回结果，也可以获取 Mapper 的接 口。在现有的技术中， 一般我们会让其在业务逻辑代码中“消失”，而使用的是 Mybatis 提供的 SQL Mapper 接口编程技术，它能提高代码的可读性和可维护性。  

### SQL Mapper

> Mybatis 新设计存在的组件，它由一个 Java 接口和 XML 文件（或注解）构成，需要给出对应的 SQL 和映射规则。它负责发送SQL去执行， 并返回结果.



## 优点

- 简单
- 灵活度高，支持定制化SQL
- 降低代码的耦合度

## 工作流程

1. 





# 部署

## 添加依赖

```xml
<!-- 因为mybatis主要是做数据层持久化的，所以需要数据库，这里我们引入MySQL-CJ的依赖 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
<!-- 引入mybatis依赖 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.6</version>
</dependency>

<!-- 引入mybatis适配spring的依赖 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.3.2</version>
</dependency>
```

## 配置文件

> 我们需要在`resources`目录下创建一个名为`mybatis-config.xml`的文件，来配置数据库连接信息。

### 模板

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

</configuration>
```

### 设置连接信息

> 在Mybatis中可以通过`environments`标签来配置多个数据库连接信息，在Mybatis中数据库又称数据源（`dataSource`）。
>
> Mybatis通过`transactionManager`提供了全面的事务管理，支持自动事务提交，手动事务提交，手动事务回滚等操作。
>
> 
>
> 可以通过[Mybatis官网](https://mybatis.org/mybatis-3/configuration.html)查看如何配置数据库连接信息。
>
> - environments：支持多个数据源配置，每个数据源分开管理
>
> - environment：配置数据库连接信息，id属性为数据库连接的唯一标识
> - transaction Manager：配置数据库事务管理，`type`属性取值为`JDBC`、`MANAGED`
>     - JDBC：支持自动事务管理，手动事务管理，默认不会自动提交事务
>     - MANAGED：指将事务管理交由容器管理，本身不会对事务进行任何操作。且默认情况下会关闭数据库连接，可以通过设置`closeConnection` 为`false`来阻止关闭连接。
> - data source：配置数据源连接信息，type属性的取值为`POOLED`、`UNPOOLED`、`JNDI`、自定义数据源
>     - POOLED：启用mybatis自带连接池
>     - UNPOOLED：不启用连接池
>     - JNDI：第三方数据源连接信息
>     - 自定义数据源：如druid，

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="dev">

        <!-- 数据库配置信息    -->
        <environment id="dev">
            <transactionManager type="JDBC">
                <property name="closeConnection" value="false"/>
            </transactionManager>
            <!--数据库连接信息， type的取值为 POOLED 或 UNPOOLED 或 JNDI-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <!--在property标签内是不能使用&，需要使用&amp;来替换-->
                <property name="url" value="jdbc:mysql://192.168.64.134:3309/mas"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

当然还以简化`property`属性，方便管理，可以配置`properties`文件来管理连接数据库连接信息。

- `mybatis.properties`

```properties
# 配置mybatis DataSource
mybatis.driver=com.mysql.jdbc.Driver
mybatis.url=jdbc:mysql://192.168.64.134:3309/mas?useSSL=false&characterEncoding=utf-8
mybatis.username=root
mybatis.password=root
```

- `mysql.properties`

```properties
# 配置MySQL DataSource
mysql.driver=com.mysql.jdbc.Driver
mysql.url=jdbc:mysql://192.168.64.134:3309/mas?useSSL=false&characterEncoding=utf-8
mysql.username=root
mysql.password=root
```

此时，我们在配置数据库连接信息时，就可以将`propertise`文件配置进`mybatis-config.xml`文件，然后再通过取值符通过`key`取值即可，如下。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- 加载数据源配置信息 -->
    <properties resource="dataSource/mybatis.properties"/>

    <environments default="dev">
        <environment id="dev">
            <transactionManager type="MANAGED">
                <property name="closeConnection" value="false"/>
            </transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value=""/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

# CRUD

> 需要在建立完实体后，创建接口类，然后再创建映射文件实现接口功能。

## 数据库

```sql
CREATE TABLE IF NOT EXISTS `table_student`(
    `id` int(8) UNSIGNED AUTO_INCREMENT，
    `stu_num` int(8),
    `stu_name` varchar(16),
    `stu_sex` varchar(1),
    `stu_age` int(3),
    PRIMARY KEY (`id`)//设置主键为id
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

## 实体类

```java
public class Student {
    private int id;
    private String stuNum;
    private String stuName;
    private String stuSex;
    private int stuAge;
}
```



## 接口

```java
@Mapper
public interface StudentDao {
    //增
    int addStudent(Student student);
    //删
    int delStudent(String stuNum);
    //查
    List<Student> queryStudents();
    Student queryStudentByNum(String stuNum);
    Student queryStudentByNumWithAlias(String stuNum);
    //改
    int updateStudentById(String stuNum);
}
```

## 映射

`mapper.xml`模板

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--mapper文件负责联通业务与数据库，负责将数据从数据库取出来返回给业务层-->
<mapper namespace="com.marshio.mybatis.dao.StudentDao">
	<!--namespace是接口类，必须是接口类-->

</mapper>
```

### namespace

> ![image-20220125163749635](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220125163753.png)
>
> ![image-20220125163817667](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220125163819.png)
>
> 上面是官方原话以及翻译，总结就是
>
> - `namespace`属性尽量使用全限定名称
> - 使用短名称时，如果名称不是唯一的会报错

### insert

> - `id`：绑定对应DAO接口中的方法
>- `parameterType`：用以指定接口中对应方法的参数类型（可省略）
> - `useGeneratedKeys`：设置添加操作是否需要回填生成的主键
>- `keyProperty`：指定回填的id设置到参数对象中的哪个属性
> - `timeout`：设置此操作的超时时间，如果不设置则一直等待
>- `flushCache`：为true时会导致**增删改的SQL**语句被调用时刷新一级缓存和二级缓存，默认为true（增删改）

```xml
<!-- 插入无需设置返回类型 -->
<insert id="addStudent">
    insert into db_student (stu_num, stu_name, stu_age, stu_sex)
    VALUES (#{stuNum}, #{stuName}, #{stuAge}, #{stuSex})
</insert>

<!-- 主键回填，设置useGeneratedKeys为true，keyProperty为实体对象的ID属性名称 -->
<insert id="addStudentBacKey" useGeneratedKeys="true" keyProperty="id">
    insert into db_student (stu_num, stu_name, stu_age, stu_sex)
    VALUES (#{stuNum}, #{stuName}, #{stuAge}, #{stuSex})
</insert>
```

### delete

```xml
<!-- 无需设置返回类型 -->
<delete id="delStudent">
    delete
    from db_student
    where stu_num = #{stuNum}
</delete>
```

### update

```xml
<!--  -->
<update id="updateStudentNoResult">
    update db_student
    set stu_num=#{stuNum},
    stu_name=#{stuName},
    stu_sex=#{stuSex},
    stu_age=#{stuAge}
    where id = #{id}
</update>
```

### select

> - `id`： 指定绑定方法的方法名
> - `parameterType`：设置参数类型
>
> - `resultType`：指定当前`sql`返回数据封装的对象类型（实体类）
> - `resultMap`：指定从数据表到实体类的字段和属性的对应关系
> - `useCache`：指定此查询操作是否需要缓存
> - `timeout`：设置超时时间

```xml
<!-- 查看数据时需要设置返回的数据类型 -->
<!-- 除此之外，还需要将返回的数据字段与实体类属性字段一一映射 -->
<select id="queryStudents" resultType="student">
    select id id, stu_num stuNum, stu_name stuName, stu_age stuAge, stu_sex stuSex
    from db_student
</select>
```

> 上面方法，每次都需要写大量冗余字段，所以他还给了另外一种方式，如下。
>
> 注意，我这里的type是因为在`mybatis-config.xml`设置了`typeAliases`，所以我能直接使用类型别名。

```xml
<!-- 设置resultMap -->
<resultMap id="student" type="student">
    <id column="id" property="id"/>
    <result column="stu_num" property="stuNum"/>
    <result column="stu_name" property="stuName"/>
    <result column="stu_age" property="stuAge"/>
    <result column="stu_sex" property="stuSex"/>
</resultMap>

<!-- 此时我们可以设置resultMap来代替resultType -->
<select id="queryStudentByNum" resultMap="student">
    select id id, stu_num stuNum, stu_name stuName, stu_age stuAge, stu_sex stuSex
    from db_student
    where stu_num = #{stuNum}
</select>
```

### cache

> 设置当前数据库操作时的缓存属性设置
>
> 默认，之启动本地会话缓存。
>
> 想要开启全局的二级缓存则需要设置

```xml
<cache eviction="FIFO" //驱逐策略
       flushInterval="60000" //
       size="512" //缓存大小
       readOnly="true"/>//只读
```



### sql和include

```xml
<select id="queryStudentByNum" resultMap="student" >
    select id, stu_num, stu_name, stu_age, stu_sex
    from db_student
    where stu_num = #{stuNum}
</select>

//可以被替换为以下代码

<sql id="stuParams" >
    id, stu_num, stu_name, stu_age, stu_sex
</sql>
<!-- 此时我们可以设置resultMap来代替resultType -->
<select id="queryStudentByNum" resultMap="student" >
    select <include refid="stuParams"/>
    from db_student
    where stu_num = #{stuNum}
</select>
```



## 配置映射

> 在创建完映射之后，我们该如何使用这个映射文件呢？
>
> 我们需要将`mapper`文件在`mybatis-config.xml`文件中通过`mapper`标签显式的调用。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <environments default="dev">
        <environment id="dev">
           <...>
        </environment>
    </environments>

    <mappers>
        <!--配置映射-->
        <mapper resource="mapper.studentMapper.xml" />
    </mappers>
</configuration>
```

## 测试

```java
@Test
public void testMybatis() {
    //一步一步的构建StudentMapper
    try {
        //6、所以我们需要想办法构造InputStream
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //4、SqlSessionFactoryBuilder可以被new
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3、SqlSessionFactory也不能被new，只能被SqlSessionFactoryBuilder生成,
        // 5、但是builder需要传入一个参数
        SqlSessionFactory factory = builder.build(is);
        //1、在使用mybatis时，我们需要使用sqlSession来管理与数据库的连接
        //2、但是sqlSession不能被new，只能被sqlSessionFactory.open()生成
        SqlSession sqlSession = factory.openSession();

        //7、下面，我们来看看sqlSession能做些什么吧,可以看到他能提交事务，回滚事务等各种操作
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        int i = studentDao.addStudent(new Student("1000022", "111asas", "0", 100));
        System.out.println(i);

        //注意，如果不手动提交事务，数据库是不会被更改的
        sqlSession.commit();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

> 可以看到，每次如果都需要一步一步的构建持久层对象的话，那是费时又费力，所以我们需要将其过程封装成工具类，以后直接调用工具类即可。

## 封装工具类

> 实际业务场景中，我们需要大量的增删改查操作，如果像上面那样写代码的话，我们需要写很多重复冗余的代码，而这些代码功能都一样，都是为了获取`SQL Session`或`***Dao`，所以我们需要将公共代码封装起来，这样我们可以在使用的时候直接调用工具类，这样既减少了代码冗余，又利于代码阅读。

```java
package com.marshio.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author masuo
 * @data 25/1/2022 下午5:25
 * @Description 工具类
 */

public class MybatisUtil {
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param autoCommit 是否自动提交
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession(autoCommit);
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 无需手动提交的事务管理器
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSession(true);
    }

    /**
     * 获取接口类
     * @param clazz 接口类
     * @param <T> 返回的类的类型
     * @return <T> 泛型
     */
    public static <T> T getMapper(Class<? extends T> clazz) {
        SqlSession sqlSession = getSqlSession(true);
        return sqlSession.getMapper(clazz);
    }
}
```

## 工具类测试

```java 
public class StudentDaoTest {

    @Test
    public void addStudent() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int i = studentDao.addStudent(new Student("1000023", "mass", "0", 100));
        System.out.println(i);
    }

    @Test
    public void delStudent() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int i = studentDao.delStudent("1000023");
        System.out.println(i);
    }

    @Test
    public void queryStudents() {
        StudentDao mapper = MybatisUtil.getMapper(StudentDao.class);
        //设置页偏移量和页容量
        PageHelper.startPage(2,4);
        //查询到所需学生
        List<Student> students = mapper.queryStudents();
        //放入到pageInfo中,pageInfo中就包含数据及分页信息
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        //获取list
        List<Student> list = pageInfo.getList();

        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void queryStudentByNum() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNum("20223603");
        System.out.println(student.toString());
    }

    @Test
    public void queryStudentByNumWithAlias() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNumWithAlias("1000023");
        System.out.println(student.toString());
    }

    @Test
    public void updateStudentNoResult() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int student = studentDao.updateStudentNoResult(new Student(16,"20173605", "mass", "0", 100));
        System.out.println(student);
    }

    @Test
    public void updateStudentBack() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNumWithAlias("1000023");
        System.out.println(student.toString());
    }
}
```



# 动态SQL

> 上面我们进行了简单得CRUD操作，上卖弄的需求都很简单，但是当我们遇到较为复杂的需求时，简单得SQL语句已经不能应付了，因为上面的SQL语句都比较死板，所以我们需要较为灵活的SQL语句。

## 简介

> 动态SQL可以根据查询条件进行SQL的拼接。

## 使用

 **需求**

> 商品搜索，商品含有多个参数，筛选时默认筛选全部商品，但是可以添加筛选条件，就类似京东商城的商品筛选功能，条件不固定，参数范围不确定。

### 数据库

```sql
create table db_goods(
    `gid` int auto_increment primary key,
    `good_name` varchar(50) not null,
    `good_num` int(6) not null default 0,
    `good_price` int not null default 0,
    `good_brand` varchar(50) not null ,
    `good_unit` varchar(5) not null default '个',
    `good_shelfTime` DATETIME not null default NOW(),
    `good_desc` text
)engine=innodb charset=utf8;
```

### 接口层

```java
public interface GoodsDao {

    public int addGoods(Goods goods);
    public int delGoodsById(int gid);
    //无条件查询所有商品
    public List<Goods> queryGoods();
    //当我们需要带参查询时，有两种方法，一种是将参数放入Hash Map中
    public List<Goods> queryGoodsWithMap(Map<String, Object> params);
    //还有一种是构建一个参数类，将参数封装传递
    public List<Goods> queryGoodsWithParams(MemberSearchCondition params);
    
    public int updateGoods(Goods goods);
}
```

### 查询

> 传递参数有三种方式。
>
> 同样的，获取参数判断也有多种方式。
>
> - **if**：参数为K-V模式
> - **where**：参数为K-V模式
> - **trim**：参数为K-V模式
> - **foreach**：参数为数组

#### IF

> 如果想要直接使用if语句，那么就需要在条件前拼接一个语句，不然可能会导致后面的条件都为空，然后where字句会报错

```xml
<select id="queryGoodsWithMap" resultMap="goods" parameterType="java.util.Map">
    select gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc
    from db_goods
    where 1=1
    <if test="minPrice != null">
        # &gt; ==> 大于
        and good_price &gt; #{minPrice}
    </if>
    <if test="maxPrice != null">
        # &lt; ==> 小于
        and good_price &lt; #{maxPrice}
    </if>
    <if test="goodBrand != null">
        and good_brand = #{goodBrand}
    </if>
</select>
```

#### WHERE

> 在使用where标签时，会自动将第一个子句的and或or去掉

```xml
<select id="queryGoodsWithParams" resultMap="goods"
        parameterType="com.marshio.mybatis.dao.conditions.MemberSearchCondition">
    select gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc
    from db_goods
    <where>
        <if test="goodName != null">
            and good_name = #{goodName}
        </if>
        <if test="minPrice != null">
            and good_price &gt; #{minPrice}
        </if>
        <if test="maxPrice != null">
            and good_price &lt; #{maxPrice}
        </if>
        <if test="goodBrand != null">
            and good_brand = #{goodBrand}
        </if>
    </where>
</select>
```

#### TRIM

>  使用trim需要自己去除衔接符 （and/or）
>
> `prefix` : 前置语句 
>
> `prefixOverrides` : 需要被覆盖的前置衔接符
>
> `suffix` : 后置语句 
>
> `suffixOverrides` : 需要被覆盖的后置衔接符

```xml
<select id="queryGoodsWithTrim" resultMap="goods"
        parameterType="com.marshio.mybatis.dao.conditions.MemberSearchCondition">
    select gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc
    from db_goods
    <trim prefix="where" prefixOverrides="and | or" suffix="" suffixOverrides="">
        <if test="goodName != null">
            and good_name = #{goodName}
        </if>
        <if test="minPrice != null">
            and good_price &gt; #{minPrice}
        </if>
        <if test="maxPrice != null">
            and good_price &lt; #{maxPrice}
        </if>
        <if test="goodBrand != null">
            and good_brand = #{goodBrand}
        </if>
    </trim>
</select>
```

#### FOREACH

> 当我们在`where`字句需要用到`in`是，此时我们可以考虑使用`foreach`，foreach可以循环传递的参数去查找。

**参数构造**

```java
@Test
public void queryGoodsWithForEach() {
    GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
    List<String> brand = new ArrayList<>();
    brand.add("apple");
    brand.add("HP");
    List<Goods> goods = mapper.queryGoodsWithForEach(brand);
    for (Goods good : goods) {
        System.out.println(good.toString());
    }
}
```

**使用**

```xml
<select id="queryGoodsWithForEach" resultMap="goods" resultType="list">
    select
    gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc
    from db_goods
    where good_brand in
    # collection 说的是传递的参数的类型
    # item 是传递的参数别名
    # separator 分隔符，注意这个分隔符指的是sql语句里的分隔符，
    # open 开始符号，close 结束符号，当然这里也是sql语句里参数的开始结束符
    <foreach collection="list" item="brand" separator="," open="(" close=")">
        #{brand}
    </foreach>
</select>
```



## 模糊查询

> 在实际项目中我们还有许多查询是模糊查询，在MyBatis中的模糊查询是如何实现的呢？
>
> 那下面我们就以上面的数据为例，查询以“apple”开头的商品。

**接口层**

```java
/**
 * 模糊查询
 * 需要注意，在使用模糊查询的时候，我们需要使用${}注入参数，而不是#{}
 * 与此同时，在使用${}的时候，即使只有一个参数也需要使用@Param注解声明参数的key（非String不需要）,不然会报此参数没有getter和setter方法。
 * @param key 关键词
 * @return list
 */
List<Goods> queryGoodsByKeys(@Param("key") String key);
```

**映射层**

```xml
<select id="queryGoodsByKeys" parameterType="java.lang.String" resultMap="goods">
    select
    <include refid="params"/>
    from db_goods
    where good_brand like '${key}%'
</select>
```

**测试**

```java
@Test
public void queryGoodsByKeys() {
    GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
    List<Goods> goods = mapper.queryGoodsByKeys("app");
    for (Goods good : goods) {
        System.out.println(good.toString());
    }
}
```

### 差异

> 上面我们使用了`${}`来获取参数，那么`${}`和`#{}`的区别是什么呢？

|          | #{}                                    | ${}                       |
| -------- | -------------------------------------- | ------------------------- |
|          | 占位符                                 | 拼接符                    |
| 参数处理 | 预编译处理                             | 字符串替换                |
|          | 变量自动加单引号                       | 变量不加单引号            |
| 执行顺序 | 先完成SQL预编译，然后将参数放入到SQL中 | 先将参数放到SQL中，在编译 |
|          | 动态解析 -> 预编译 -> 执行             | 动态解析 -> 编译 -> 执行  |
| 安全性   | 安全，可以预防SQL注入                  | 不安全，不能防止SQL注入   |



# 事务管理

> Mybatis的`SqlSession`自带很强大的事务管理功能，其默认是需要手动事务提交的，即默认不自动提交事务。
>
> - 手动事务提交
> - 自动事务提交

## 手动事务

> 回顾一下SQL Session的生成过程，
>
> - 先生成`SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder()`.
>
> - 再生成`SqlSessionFactory factory = builder.build(inputStream)`.
>
> - 再生成`SqlSession sqlsession = factory.openSession()`，
>
> 在生成SqlSession的时候，有一个参数：`autoCommit`，如果你不填，它默认就是`false`，即不会自动提交，所以此时需要我们手动管理事务。
>
> ```java
> @Override
> public SqlSession openSession() {
>     return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
> }
> ```
>
> ![image-20220126094331813](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126094333.png)

### 提交事务

> 事务提交是非常简单的，如果你能保证自己的业务没有问题，`sql`语句也没有问题，那么你只需要执行`commit`方法即可。
>
> `sqlSession.commit()`.

```java
StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
studentDao.addStudent(new Student("1000024", "mass", "0", 100));
//提交事务
sqlSession.commit();
```



### 回滚事务

> 同事务提交，
>
> `sqlSession.rollback()`.

```java
StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
studentDao.addStudent(new Student("1000024", "mass", "0", 100));
//事务回滚
sqlSession.rollback();
```



## 自动事务

> 自动事务管理，我们只需要在`openSession()`时，传递`true`的参数.
>
> `openSession(true)`.

```java
StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
studentDao.addStudent(new Student("1000024", "mass", "0", 100));
```





# 配置文件

> Mybatis配置文件即`mybatis-config.xml`,用于配置Mybatis数据源及属性信息。
>
> 有如下标签
>
> - properties
> - setting
> - typeAliases
> - plugins
> - environments
> - mappers

## properties

> 用于加载mybatis将要用到的属性。
>
> 除了下面这两种之外，还有两种方式，但是不常用。

### 直接使用子标签

```properties
<properties>
    <property name="driver" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/database1"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</properties>
```

在子标签定义数据库连接信息之后就可以在`environment`标签中的`datasource`标签中使用了。

```xml
<dataSource type="POOLED">
    <property name="driver" value="${driver}"/>
    <!--在property标签内是不能使用&，需要使用&amp;来替换-->
    <property name="url" value="${url}"/>
    <property name="username" value="${username}"/>
    <property name="password" value="${password}"/>
</dataSource>
```



### 配置属性文件

#### 创建属性文件

> 因为mybatis有可能有多个属性文件（`**.properties`），所以可以建立一个`datasources`文件专门存放属性文件。
>
> 属性文件即数据库连接信息。

```properties
# 配置MySQL DataSource
mysql.driver=com.mysql.jdbc.Driver
mysql.url=jdbc:mysql://localhost:3306/db_1?useSSL=false&characterEncoding=utf-8
mysql.username=root
mysql.password=root
```

#### 加载属性文件

```xml
<properties resource="datasource/mysql.properties"/>
```

#### 加载连接属性

```xml
<!--配置属性文件之后，就可以用${key}来获取值-->
<dataSource type="POOLED">
    <property name="driver" value="${mybatis.driver}"/>
    <property name="url" value="${mybatis.url}"/>
    <property name="username" value="${mybatis.username}"/>
    <property name="password" value="${mybatis.password}"/>
</dataSource>
```



## settings

> 官方原话：
>
> These are extremely important tweaks that modify the way that MyBatis behaves at runtime. 
>
> 这些是极其重要的调整，将会影响MyBatis在运行时的行为方式。
>
> 由于参数太多，我暂时没有全部用到，我就用到啥写啥。后面再慢慢补。

### 参数值

| setting              | 描述             | 可用值        | 默认  |
| -------------------- | ---------------- | ------------- | ----- |
| cache Enabled        | 启动二级缓存     | true \| false | true  |
| lazy Loading Enabled | 懒加载、延时加载 | true \| false | false |
|                      |                  |               |       |

### 应用

```xml
<settings>
    <!-- 启动二级缓存-->
    <setting name="cacheEnabled" value="true"/>
    <!-- 启动延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>
</settings>
```





## typeAliases

> 官方原话
>
> A type alias is simply a shorter name for a Java type. It's only relevant to the XML configuration and simply exists to reduce redundant typing of fully qualified class names. 
>
> 类型别名只是 Java 类型的较短名称。它只与 XML 配置相关，只是用于减少完全限定类名的冗余类型。

```xml
<typeAliases>
    <typeAlias  alias="student" type="com.marshio.mybatis.pojo.Student"/>
</typeAliases>
```

配置`typeAliases`之后，就可以在`mapper`文件以及其他应用到的文件中使用别名来替代全限定名。

```xml
//使用全限定名
<select id="queryStudentByNum" resultType="com.marshio.mybatis.pojo.Student">
    select * from db_student where stu_num=#{stuNum}
</select>

//使用别名
<select id="queryStudentByNumWithAlias" resultType="student">
    select * from db_student where stu_num=#{stuNum}
</select>
```

## typeHandler

> 这个之后再说

## plugins

> MyBatis的强大之处还体现在它的可扩展性强，而plugins标签正是体现它的扩展性的。
>
> 官方原话
>
> Using plugins is pretty simple given the power they provide. Simply implement the Interceptor interface, being sure to specify the signatures you want to intercept.
>
> 使用插件非常简单，只需继承Interceptor接口。
>
> 所以在使用时，我们只需要给interceptor属性赋值一个继承了Interceptor的类。



```xml
<plugins>
    <plugin interceptor=""></plugin>
</plugins>
```

### 分页插件

> 分页插件能够很轻松的帮我们实现数据分页。

- 引入分页插件依赖

```xml
<!-- 分页插件依赖 -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.0</version>
</dependency>
```

- 配置分页插件

> 我们需要找到分页插件中继承了Interceptor接口的类。
>
> ![image-20220126133304530](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126133305.png)

```xml
//设置分页插件
<plugins>
    <plugin interceptor="com.github.pagehelper.PageInterceptor"/>
</plugins>
```

- 使用分页插件获取数据

```java
@Test
public void queryStudents() {
    StudentDao mapper = MybatisUtil.getMapper(StudentDao.class);
    //设置页偏移量和页容量
    PageHelper.startPage(2,4);
    //查询到所有的学生
    List<Student> students = mapper.queryStudents();
    //放入到pageInfo中,pageInfo中就包含数据及分页信息
    PageInfo<Student> pageInfo = new PageInfo<>(students);
    //获取list
    List<Student> list = pageInfo.getList();

    for (Student student : list) {
        System.out.println(student);
    }
}
```



## environments

> `environments`用来存放数据源相关信息。
>
> 官方原话
>
> ![image-20220126153930434](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126153933.png)
>
> 大概意思就是，使用`environments`你可以配置多个数据源，但是每个数据源需要一个对应的`Factory`。
>
> 其中有两个比较重要的子属性
>
> - `transactionManager`：事务管理器
> - `dataSource`：数据源配置信息

### transaction Manager

> MyBatis默认有两种事务管理方式
>
> - JDBC：这种配置只是简单地直接使用 JDBC 提交和回滚。它依赖于从 `dataSource` 检索到的连接来管理事务的范围
> - MANAGE：这个配置几乎什么都不做，如果你想要提交，或者回滚连接，你可以使用容器管理事务的整个生命周期

### data Source

> MyBatis对数据源默认有三种连接方式
>
> - POOLED：启用mybatis自带的数据库连接池![image-20220126155921093](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126155923.png)
> - UNPOOLED：不启用数据库连接池![image-20220126155938330](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126155939.png)
> - JNDI：使用第三方数据源![image-20220126155955027](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126155956.png)

举个:chestnut:

```xml
<environments default="dev1">

    <!-- 数据库信息配置方式1 -- 直接引用 -->
    <environment id="dev">
        <transactionManager type="JDBC">
            <property name="closeConnection" value="false"/>
        </transactionManager>
        
        <dataSource type="POOLED">
            <property name="driver" value="com.mysql.jdbc.Driver"/>
            <!--在property标签内是不能使用&，需要使用&amp;来替换-->
            <property name="url" value="jdbc:mysql://192.168.64.135:3306/db_1"/>
            <property name="username" value="root"/>
            <property name="password" value="root"/>
        </dataSource>
    </environment>

    <!-- 数据库信息配置方式2 -- properties标签 -->
    <environment id="dev2">
        <transactionManager type="MANAGED">
            <property name="closeConnection" value="false"/>
        </transactionManager>
        <dataSource type="POOLED">
            <property name="driver" value="${driver}"/>
            <property name="url" value="${url}"/>
            <property name="username" value="${username}"/>
            <property name="password" value="${password}"/>
        </dataSource>
    </environment>
    
    <!-- 数据库信息配置方式3 -- properties文件 -->
    <environment id="dev1">
        <transactionManager type="MANAGED">
            <property name="closeConnection" value="false"/>
        </transactionManager>
        <dataSource type="POOLED">
            <property name="driver" value="${mybatis.driver}"/>
            <property name="url" value="${mybatis.url}"/>
            <property name="username" value="${mybatis.username}"/>
            <property name="password" value="${mybatis.password}"/>
        </dataSource>
    </environment>

</environments>
```

## databaseIdProvider

> 

## mappers

> 官方原话
>
> ```markdown
>  But first, we need to tell MyBatis where to find them
> ```
>
> ![image-20220126160402302](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126160403.png)
>
> 就是告诉MyBatis去哪找到映射文件。
>
> mappers可以引入映射文件，被Spring容器扫描到并解析，相应的如果没有引入映射文件，那么则不能被扫描到，则不能完成功能。
>
> 一共有四种方式
>
> ![image-20220126160459467](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126160526.png)

### 类路径

```xml
<mappers>
    <mapper resource="mapper/studentMapper.xml" />
</mappers>
```

**项目结构**

![image-20220126160659519](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126160700.png)

### 全路径

```xml

```

### 映射接口类

```xml
<mappers>
    <!--  映射接口类 -->
    <mapper class="com.marshio.mybatis.mapper.StudentMapper"/>
</mappers>
```

**项目结构**

![image-20220126161926243](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126161927.png)

### 映射接口包

```xml
<mappers>
    <!--  映射接口包 -->
    <package name="com.marshio.mybatis.mapper"/>
</mappers>
```

**项目结构**

![image-20220126161950759](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126161952.png)









# Druid

> MyBatis作为一个ORM（对象关系映射，Object Relation Map）框架，再进行数据库操作时是需要和数据库进行连接的，MyBatis支持基于数据库连接池的创建方式。
>
> 当我们配置MyBatis数据源时，只要配置了`dateSource`标签的type属性为`POOLED`时，就可以使用MyBatis自带的数据库连接池管理连接。
>
> 如果我们想要使用第三方数据库连接池，则需要进行自定义配置。

**常见的第三方连接池**

- **DBCP**
- **C3P0**
- **Druid**
- **Hikari** 

| 功能            | DBCP                | Druid              | C3P0                               | Hikari                             |
| --------------- | ------------------- | ------------------ | ---------------------------------- | ---------------------------------- |
| 是否支持PSCache | 是                  | 是                 | 是                                 | 否                                 |
| 监控            | jmx                 | jmx/log/http       | jmx,log                            | jmx                                |
| 扩展性          | 弱                  | 好                 | 弱                                 | 弱                                 |
| SQL拦截及解析   | 无                  | 支持               | 无                                 | 无                                 |
| 代码            | 简单                | 中等               | 复杂                               | 简单                               |
| 更新时间        | 2015.8.6            | 2015.10.10         | 2015.12.09                         | 2015.12.3                          |
| 特点            | 依赖于common-pool   | 阿里开源，功能全面 | 历史久远，代码逻辑复杂，且不易维护 | 优化力度大，功能简单，起源于boneCP |
| 连接池管理      | LinkedBlockingDeque | 数组               |                                    | Threadlocal + CopyOnWriteArrayList |



## Druid架构

![druid-framework](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220126171209.png)

## Druid依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.10</version>
</dependency>
```

## Druid连接池工厂

> 在完成这一步之前，我们先来看一下MyBatis自带的数据库连接池是如何做的。
>
> - 首先，`PooledDataSourceFactory`是为MyBatis的数据库连接池提供支持的。
>
> ```java
> public class PooledDataSourceFactory extends UnpooledDataSourceFactory {
>     public PooledDataSourceFactory() {
>         //将PooledDataSource赋值给dataSource
>         this.dataSource = new PooledDataSource();
>     }
> }
> //PooledDataSource，不全
> public class PooledDataSource implements DataSource {
>     private final UnpooledDataSource dataSource;
>     public PooledDataSource() {
>         dataSource = new UnpooledDataSource();
>     }
>     //job here
>     //具体实现。。
> }
> ```
>
> 所以我们需要像上面一样完成他的功能，来代替它的工作。所以我们建立`DruidDataSourceFactory`类。

```java
public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory(){
        //将DruidDataSource赋值给dataSource，至于DruidDataSource干了什么，这个需要自己去看，因为太多了
        this.dataSource = new DruidDataSource();
    }
}
```

## 配置数据源为Druid

```xml
<environment id="dev">
    <transactionManager type="JDBC">
        <property name="closeConnection" value="false"/>
    </transactionManager>
    <!-- 这里我们将数据库连接池替换为DruidDataSourceFactory -->
    <dataSource type="com.marshio.druid.druid.DruidDataSourceFactory">
        <!-- 这里注意，使用Druid时，原driver的name变为driverClass，原url的name变为jdbcUrl-->
        <property name="driverClass" value="${druid.driver}"/>
        <property name="jdbcUrl" value="${druid.url}"/>
        <property name="username" value="${druid.username}"/>
        <property name="password" value="${druid.password}"/>
    </dataSource>
</environment>
```

# 日志

> MyBatis做为一个封装好的ORM框架，其运行过程我们没办法跟踪，为了让开发者了解MyBatis执行流程及每个执行步骤所完成的工作，MyBatis框架本身支持log4j日志框架，对运行的过程进行跟踪记录。我们只需对MyBatis进行相关的日志配置，就可以看到MyBatis运行过程中的日志信息。
>
> 当然MyBatis支持多种日志，下面暂时以log4j作测试，后面用到其他的会及时补充
>
> 

## 添加依赖

### Log4j

```xml
<!-- log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

> 同时需要添加日志配置文件`log4j.properties`。

```properties
# log4j.rootLogger日志输出类别和级别：只输出不低于该级别的日志信息DEBUG < INFO < WARN < ERROR < FATAL
# WARN：日志级别     CONSOLE：输出位置自己定义的一个名字       logfile：输出位置自己定义的一个名字
log4j.rootLogger=DEBUG,CONSOLE,logfile

# 配置CONSOLE输出到控制台
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.Target = System.out
log4j.appender.CONSOLE.Threshold = DEBUG
# 配置CONSOLE设置为自定义布局模式
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
# 配置CONSOLE日志的输出格式  [frame] 2019-08-22 22:52:12,000  %r耗费毫秒数 %p日志的优先级 %t线程名 %C所属类名通常为全类名 %L代码中的行号 %x线程相关联的NDC %m日志 %n换行
log4j.appender.CONSOLE.layout.ConversionPattern=[frame] %d{yyyy-MM-dd HH:mm:ss,SSS} - %-4r %-5p [%t] %C:%L %x - %m%n
log4j.appender.CONSOLE.Encoding=UTF-8

################
# 输出到日志文件中
################
# 配置logfile输出到文件中 文件大小到达指定尺寸的时候产生新的日志文件
log4j.appender.logfile=org.apache.log4j.RollingFileAppender
# 输出文件位置此为项目根目录下的logs文件夹中
log4j.appender.logfile.File=logs/root.log
# 后缀可以是KB,MB,GB达到该大小后创建新的日志文件
log4j.appender.logfile.MaxFileSize=10MB
log4j.appender.logfile.Threshold = DEBUG
# 配置logfile为自定义布局模式
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
log4j.appender.logfile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %F %p %m%n
# 设置滚定文件的最大值3 指可以产生root.log.1、root.log.2、root.log.3和root.log四个日志文件
log4j.appender.logfile.MaxBackupIndex=3
# 保存编码格式
log4j.appender.logfile.Encoding=UTF-8

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.Encoding=UTF-8
```

## 启用

> 上面的步骤保证我们已经可以使用日志的功能了，但是此时我们是不会输出完整的日志信息的，我们需要在MyBatis的配置文件中告诉MyBatis有日志配置文件可以使用了。

```xml
<settings>
    <setting name="logImpl" value="LOG4J"/>
</settings>
```

## 测试

```java
/**
     * 测试日志的功能
     * @param args 参数
     */
public static void main(String[] args) {
    SqlSession sqlSession = MybatisUtil.getSqlSession();
    GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);
    List<Goods> goods = mapper.queryGoods();
    logger.debug("first");
    logger.info("search all");
    Stream<Goods> stream = goods.stream();
    System.out.println(stream.count());
}
```

**输出如下**

```java
[main] DEBUG - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.sg :Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
[main] DEBUG - PooledDataSource forcefully closed/removed all connections.sg :PooledDataSource forcefully closed/removed all connections.
[main] DEBUG - PooledDataSource forcefully closed/removed all connections.sg :PooledDataSource forcefully closed/removed all connections.
[main] DEBUG - PooledDataSource forcefully closed/removed all connections.sg :PooledDataSource forcefully closed/removed all connections.
[main] DEBUG - PooledDataSource forcefully closed/removed all connections.sg :PooledDataSource forcefully closed/removed all connections.
[main] DEBUG - Opening JDBC Connectionsg :Opening JDBC Connection
[main] DEBUG - Created connection 1615056168.sg :Created connection 1615056168.
[main] DEBUG - ==>  Preparing: select gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc from db_goods sg :==>  Preparing: select gid,good_name,good_num,good_price,good_brand,good_unit,good_shelfTime,good_desc from db_goods 
[main] DEBUG - ==> Parameters: sg :==> Parameters: 
[main] DEBUG - <==      Total: 18sg :<==      Total: 18
[main] DEBUG - firstsg :first
[main]  INFO - search allsg :search all
18
```

# 缓存

> MyBatis是基于JDBC的封装，使数据库操作更加便捷，除此之外，还对其性能进行了优化。
>
> - 引入了缓存机制，提升检索效率
> - 引入延迟加载机制，减少对数据库不必要的访问

## 工作原理

> 缓存就是将读取到的数据放到内存中，因为内存读取速度比磁盘读取速度快。
>
> 如下图
>
> ![Redis](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220207160809.png)

## MyBatis的缓存

> MyBatis的缓存分为一级缓存和二级缓存。两者的区别就像局部变量和全局变量。

### 一级缓存

> 默认情况下是开启的。
>
> 一级缓存的缓存是对同一个SqlSession开放的，即如果我们使用同一个SqlSession对象多次调用同一个方法，往往只会执行一次SQL语句，因为在第一次查询后，MyBatis会将其放在缓存中，以后再查询时，如果没有明确的声明当前读，且缓存未超时的情况下，就会取出缓存中的数据。

#### 生命周期

> 缓存并不是一直都存在的，而是有时间限制的。那么，一级缓存的生命周期有多长呢？

- 一个`SQLSession`会话的创建，`SqlSession`对象中有一个`Executor`对象，`Executor`对象中有一个`PerpetualCache`对象（即一级缓存），会话结束的时候会释放以上所有对象。

```java
//SQLSession对象的创建
public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
    this.configuration = configuration;
    this.executor = executor;
    this.dirty = false;
    this.autoCommit = autoCommit;
}
//Executor对象的创建
protected BaseExecutor(Configuration configuration, Transaction transaction) {
    this.transaction = transaction;
    this.deferredLoads = new ConcurrentLinkedQueue<DeferredLoad>();
    //localCache即本地缓存，即一级缓存
    this.localCache = new PerpetualCache("LocalCache");
    this.localOutputParameterCache = new PerpetualCache("LocalOutputParameterCache");
    this.closed = false;
    this.configuration = configuration;
    this.wrapper = this;
}
```

- 执行SqlSession的`close()`方法，和上面不一样的是这个是主动关闭会话。
- 执行SqlSession的`clearCache()`方法，会清空缓存，但是一级缓存还能用。
- SqlSession执行了以下操作之一，都会清空`PerpetualCache`对象的数据。
    - update()
    - delete()
    - insert()



#### 查询一致性

> 一级缓存是有了，那么如何保证两次查询是一致的查询？
>
> 在MyBatis中，如果以下条件一致，那么就认为他们是相同的查询。
>
> - 传入的`statementId`
> - 查询时要求的结果集中的结果范围
> - 查询产生的最终`Sql`语句一致
> - 传递给Statement要设置的参数值

### 二级缓存

> 默认情况不开启。
>
> 二级缓存是全局缓存，是相对整个应用而言的缓存，建立在`mapper`上，。可以提高数据库查询的效率，以提高应用的性能。
>
> 想要开启二级缓存，需要一定的配置即数据处理，MyBatis要求返回的POJO必须是可序列化的，那么就需要POJO实现Serializable接口，需要在`mybatis-config.xml`文件开启`<cache/>`标签。
>
> ![img](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220207170251.png)
>
> 图源：https://www.cnblogs.com/happyflyingpig/p/7739749.html

#### 开启二级缓存

> 想要开启二级缓存，需要先开启**全局缓存**，在启用**映射文件的全局缓存**。
>
> 当然，全局缓存默认是开启的，所以其实也无需设置。

- `mybatis-config.xml`

```xml
<settings>
    <!--设置全局缓存的开启或关闭-->
    <setting name="cacheEnabled" value="true"/>
</settings>
```

- `***Mapper.xml`

```xml
<!--二级缓存的参数-->
<cache eviction="" flushInterval="" size="" readOnly="" blocking="" type=""/>
```

> 映射文件中的缓存标签有`eviction`、`flushInterval`、`size`、`readOnly`、`blocking`、`type`六个参数需要设置。比较常用的参数设置如下。

##### eviction

> 缓存回收策略，默认为LRU（最近最少使用算法）。可用回收算法如下

- **LRU**：最近最少使用的缓存会被回收，默认算法。
- **FIFO**：先进先出回收算法，先缓存的先被回收。
- **SOFT**：软引用回收策略，基于**垃圾回收器状态**和**软引用规则**回收对象。
- **WEAK**：弱引用回收策略，基于**垃圾回收器状态**和**弱引用规则**回收对象。

##### flushInterval

> 刷新时间间隔，默认不设值，此时只有调用方主动调用刷新才会刷新。
>
> 单位是毫秒。

##### size

> 缓存大小，默认是1024.

##### readOnly

> 缓存只读，默认是false。
>
> 为true时，是只读缓存，会向所有调用方返回相同的实例，且缓存不能被修改，这样提升了性能。
>
> 为false时，是读写缓存，此时返回的是缓存对象的副本（通过序列化），这样慢一些，但是更安全。

##### type

> 使用自定义的缓存类型或第三方缓存类型，如`Ehcache`、`redis`、`OScache`。
>
> 详情查看 [EhCache](# EhCache) 、

**注意**：二级缓存是事务性的，这意味着当`SqlSession`以`commit`结束或以`rollback`结束但是没有使用`flushCache = true`执行`insert`、`update`、`delete`时，它将被更新。

#### 特性

> 同一级缓存一样，二级缓存也是有生命周期的，还有一些其他属性。

- 映射文件中的所有`select`语句的结果都会被放到二级缓存中
- 映射文件中的所有`insert`、`update`、`delete`语句都会刷新二级缓存
- 默认使用LRU（最近最少使用回收算法）回收缓存
- 缓存不会对任何类型的基于时间的调度进行刷新（既没有刷新时间间隔）
- 二级缓存会存储列表或对象的1024个引用（不论查询结果返回什么）
- 二级缓存会被视为读写缓存，这意味着检索到的对象不是共享的，可以被调用者安全的修改，而不会干扰其他调用方或线程的其他潜在修改。

**注意：**缓存将只应用于有`cache`标签声明的映射文件，如果将映射文件与`Java API`共同使用，那么默认情况下被`cache`标签声明过的语句不会被缓存，需要使用注解`@CacheNamespaceRef`来表明需要被缓存的地方。

#### 异同

|      | 一级缓存  | 二级缓存            |
| ---- | --------- | ------------------- |
| 层   | Session级 | SqlSessionFactory级 |
| 默认 | 开启      | 关闭                |
|      |           |                     |



### EhCache

> 上面提到，MyBatis支持第三方缓存，这样增加了MyBatis的扩展性，也能提升应用的性能。
>
>  EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点.

#### 依赖

```xml
<!-- ehcache-core -->
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache-core</artifactId>
    <version>2.6.11</version>
</dependency>

<!-- mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### 配置

```xml
<ehcache>
    <!--表示硬盘上保存缓存的位置。默认是临时文件夹。-->
    <diskStore path="cache"/>
    <!--默认缓存配置，如果类没有做特定的设置，则使用这里配置的缓存属性。
       maxInMemory  - 设置缓存中允许保存的最大对象（pojo）数量
       eternal -设置对象是否永久保存，如果为true，则缓存中的数据永远不销毁，一直保存。
       timeToIdleSeconds - 设置空闲销毁时间。只有eternal为false时才起作用。表示从现在到上次访问时间如果超过这个值，则缓存数据销毁
       timeToLiveSeconds-设置活动销毁时间。表示从现在到缓存创建时间如果超过这个值，则缓存自动销毁
       overflowToDisk - 设置是否在超过保存数量时，将超出的部分保存到硬盘上。-->
    <defaultCache
            maxElementsInMemory="1500"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="300"
            overflowToDisk="true"/>
    <!-- 也可以通过name设置针对某个类的缓存配置-->
    <cache name="cn.sz.po.Emp"
           maxElementsInMemory="1000"
           eternal="true"
           timeToIdleSeconds="0"
           timeToLiveSeconds="0"
           overflowToDisk="false"/>
</ehcache>
<!--
属性说明：
l diskStore：指定数据在磁盘中的存储位置。
l defaultCache：当借助CacheManager.add("demoCache")创建Cache时，EhCache便会采用<defalutCache/>指定的的管理策略

以下属性是必须的：
l maxElementsInMemory - 在内存中缓存的element的最大数目
l maxElementsOnDisk - 在磁盘上缓存的element的最大数目，若是0表示无穷大
l eternal - 设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断
l overflowToDisk - 设定当内存缓存溢出的时候是否将过期的element缓存到磁盘上

以下属性是可选的：
l timeToIdleSeconds - 当缓存在EhCache中的数据前后两次访问的时间超过timeToIdleSeconds的属性取值时，这些数据便会删除，默认值是0,也就是可闲置时间无穷大
l timeToLiveSeconds - 缓存element的有效生命期，默认是0.,也就是element存活时间无穷大
 diskSpoolBufferSizeMB 这个参数设置DiskStore(磁盘缓存)的缓存区大小.默认是30MB.每个Cache都应该有自己的一个缓冲区.
l diskPersistent - 在VM重启的时候是否启用磁盘保存EhCache中的数据，默认是false。
l diskExpiryThreadIntervalSeconds - 磁盘缓存的清理线程运行间隔，默认是120秒。每个120s，相应的线程会进行一次EhCache中数据的清理工作
l memoryStoreEvictionPolicy - 当内存缓存达到最大，有新的element加入的时候， 移除缓存中element的策略。默认是LRU（最近最少使用），可选的有LFU（最不常使用）和FIFO（先进先出）
 -->
```



# 延时加载










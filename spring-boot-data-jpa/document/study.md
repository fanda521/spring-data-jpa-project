## 操作记录

## 1.关联说明

### 1.一对一（单向）

#### 1.关键实体

```java
People -> t_people  

idCard -> t_idcard 维护外键
@OneToOne(cascade = {CascadeType.PERSIST})
@JoinColumn(name = "t_people_id")//外键id
private People people;

```

#### 2.自动生成的表结构

```sql
CREATE TABLE `t_person` (
  `t_id` int(11) NOT NULL,
  `t_address` varchar(255) DEFAULT NULL,
  `t_age` int(11) DEFAULT NULL,
  `t_birthday` datetime(6) DEFAULT NULL,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_idcard` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_number` varchar(255) DEFAULT NULL,
  `t_people_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FK7gdvrysil6gxmt806ysqr8atn` (`t_people_id`),
  CONSTRAINT `FK7gdvrysil6gxmt806ysqr8atn` FOREIGN KEY (`t_people_id`) REFERENCES `t_people` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```



### 2.一对一（双向）

#### 1.关键实体

```java
People2 -> t_people2
@OneToOne(mappedBy = "people2")//负责维护外键的表对应的实体中，持有的本类类型属性的属性名字
    private IdCard2 idCard2;


idCard2 -> t_idcard2 维护外键

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "t_people_id")//外键id
    private People2 people2;
```



#### 2.自动生成的表结构

```sql
CREATE TABLE `t_people2` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_age` int(11) DEFAULT NULL,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `t_idcard2` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_number` varchar(255) DEFAULT NULL,
  `t_people_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FKrfj903v724ch0q2ex5u6m2min` (`t_people_id`),
  CONSTRAINT `FKrfj903v724ch0q2ex5u6m2min` FOREIGN KEY (`t_people_id`) REFERENCES `t_people2` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```



### 3.一对多（单向）

#### 1.主要实体

```java
Student -> t_student
@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
-----------------------------------
Project -> t_project

@ManyToOne()
    @JoinColumn(name = "t_student_id")
    private Student student;
#### 1
加了级联
测试一个student
多个project ,设置同一个student
会报错

解决方法
不用级联
先保存student，再保存project

#### 2
尝试加级联看看怎么才能可以同时插入多条project(student为同一个
解决方案
需要在test方法上加上下面连个注解
作用是使得代码在同一个事务中，同时自动提交
没有@Commit ,则不会提交会回滚
@Transactional
@Commit
```



#### 2.自动生成的表

```sql
CREATE TABLE `t_student` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `t_project` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_sub_name` varchar(255) DEFAULT NULL,
  `t_student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FKjbi0sj2aqjxfmj2tki0wv33xd` (`t_student_id`),
  CONSTRAINT `FKjbi0sj2aqjxfmj2tki0wv33xd` FOREIGN KEY (`t_student_id`) REFERENCES `t_student` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
```





### 4，一对多（双向）

#### 1.主要实体

```java
Student2 -> t_student2
@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @OneToMany(mappedBy = "student2")//维护外键的一方对应的实体类中的本类类型的属性字段名称
    private List<Project2> project2;
    
Project2 -> t_project2

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "t_sub_name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "t_student_id")
    private Student2 student2;


```



#### 2.自动生成的表结构

```sql
CREATE TABLE `t_student2` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `t_project2` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_sub_name` varchar(255) DEFAULT NULL,
  `t_student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `FKl48vus0ax7ywxcex420rik40` (`t_student_id`),
  CONSTRAINT `FKl48vus0ax7ywxcex420rik40` FOREIGN KEY (`t_student_id`) REFERENCES `t_student2` (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```



### 5.多对多（单向）

```
@ManyToMany
作用：用于映射多对多关系
属性：
cascade：配置级联操作。
fetch：配置是否采用延迟加载。
targetEntity：配置目标的实体类。映射多对多的时候不用写。

@JoinTable
作用：针对中间表的配置
属性：
nam：配置中间表的名称
joinColumns：中间表的外键字段关联当前实体类所对应表的主键字段
inverseJoinColumn：中间表的外键字段关联对方表的主键字段

@JoinColumn
作用：用于定义主键字段和外键字段的对应关系。
属性：
name：指定外键字段的名称
referencedColumnName：指定引用主表的主键字段名称
unique：是否唯一。默认值不唯一
nullable：是否允许为空。默认值允许。
insertable：是否允许插入。默认值允许。
updatable：是否允许更新。默认值允许。
columnDefinition：列的定义信息。
```



#### 1.主要实体

```java
@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;
--------------------------------------------------------------

@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="user_role_rel",//中间表的名称
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
            joinColumns={@JoinColumn(name="role_id",referencedColumnName="t_id")},
            //中间表user_role_rel的字段关联sys_user表的主键user_id
            inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="t_id")}
    )
    private List<SysUser> sysUserList;

###
```



#### 2.自动生成的表结构

```sql
CREATE TABLE `t_sys_user` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `t_sys_role` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `user_role_rel` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `FKm28g58dhcs5u9asuuww8ui43w` (`user_id`),
  KEY `FKi2omtqgkldjbgukc3ry5hsdf` (`role_id`),
  CONSTRAINT `FKi2omtqgkldjbgukc3ry5hsdf` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`t_id`),
  CONSTRAINT `FKm28g58dhcs5u9asuuww8ui43w` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


```

### 6.多对多（多向）

#### 1.主要的实体类

```java
@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @ManyToMany(mappedBy = "sysUserList2")//维护外键的表的对应实体中的属性字段名
    private List<SysRole2> sysRole2;


@Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="user_role_rel2",//中间表的名称
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
            joinColumns={@JoinColumn(name="role_id",referencedColumnName="t_id")},
            //中间表user_role_rel的字段关联sys_user表的主键user_id
            inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="t_id")}
    )
    private List<SysUser2> sysUserList2;


```



#### 2.自动生成的表结构

```sql



```


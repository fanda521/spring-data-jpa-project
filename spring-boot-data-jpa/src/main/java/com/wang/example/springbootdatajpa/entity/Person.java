package com.wang.example.springbootdatajpa.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author allen
 * @version 1.0
 * @date 2020-02-19 19:53
 */
@Entity//必须要加上这个注解，不然报错
@Table(name="t_person")
public class Person implements Serializable {
    @Id
    @Column(name = "t_id")
    @GeneratedValue
    private Integer id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_age")
    private Integer age;
    @Column(name = "t_address")
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "t_birthday")
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

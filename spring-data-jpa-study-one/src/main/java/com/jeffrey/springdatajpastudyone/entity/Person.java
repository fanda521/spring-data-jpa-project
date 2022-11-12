package com.jeffrey.springdatajpastudyone.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther Jeffrey
 * @date 2020/9/10 17:17
 */
@Entity
@Table(name = "jpa_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  name;
    private Integer age;
    @Temporal(TemporalType.DATE)
    private Date    birthday;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date    createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date    updateDate;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}

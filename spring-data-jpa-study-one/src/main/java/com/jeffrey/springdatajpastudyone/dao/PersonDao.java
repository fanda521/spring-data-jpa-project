package com.jeffrey.springdatajpastudyone.dao;

import com.jeffrey.springdatajpastudyone.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @auther Jeffrey
 * @date 2020/9/10 17:20
 */
public interface PersonDao extends JpaRepository<Person,Integer> {
    //1.And
    public Person findByAgeAndName(Integer age,String name);
    //2.Or
    public List<Person> findByAgeOrName(Integer age, String name);
    //3.Between
    List<Person> findByAgeBetween(Integer min,Integer max);
    //4.LessThan
    List<Person> findByAgeLessThan(Integer age);
    //5.GreaterThan
    List<Person> findByAgeGreaterThan(Integer age);
    //6.IsNull
    List<Person> findByCreateDateIsNull();
    //7.IsNotNull,NotNull
    List<Person> findByCreateDateNotNull();
    //8.Like
    List<Person> findByNameLike(String name);
}

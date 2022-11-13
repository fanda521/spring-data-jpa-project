package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/10/16 14:01
 * @description 根据方法名定义
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonMethodNameTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test01() {
        List<Person> lu = personRepository.findByNameLike("lu");
        System.out.println(lu);
    }

    @Test
    public void test02() {
        List<Person> lu = personRepository.findByNameNotLike("lu");
        System.out.println(lu);
    }

    @Test
    public void test03() {
        List<Person> lu = personRepository.findByNameStartingWith("lu");
        System.out.println(lu);
    }

    @Test
    public void test04() {
        List<Person> byNameEndWith = personRepository.findByNameEndingWith("2");
        System.out.println(byNameEndWith);
    }

    @Test
    public void test05() {
        List<Person> luck1 = personRepository.getByNameIsOrAgeEquals("luck1", 16);
        System.out.println(luck1);
    }

    @Test
    public void testBetween() {
        List<Person> luck1 = personRepository.getByAgeBetweenOrNameEquals(14, 24,"陈大大");
        System.out.println(luck1);
    }

    @Test
    public void testGreaterEqual() {
        List<Person> luck1 = personRepository.queryByAgeIsGreaterThanEqual(14);
        System.out.println(luck1);
    }

    @Test
    public void testDateBefore() {
        List<Person> luck1 = personRepository.queryByBirthdayBefore(new Date());
        System.out.println(luck1);
    }

    @Test
    public void testIn() {
        List<Person> luck1 = personRepository.getByAgeIn(Arrays.asList(1,12,16));
        System.out.println(luck1);
    }

    @Test
    public void testOrderBy() {
        List<Person> luck1 = personRepository.getByAgeInOrderByIdDesc(Arrays.asList(1,12,16));
        System.out.println(luck1);
    }

    @Test
    public void testIngoreCase() {
        List<Person> luck1 = personRepository.getByNameIgnoreCase("luck2");
        System.out.println(luck1);
    }

}

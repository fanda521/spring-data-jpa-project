package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.PersonService;
import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.entity.PersonVo;
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

    @Autowired
    private PersonService personService;

    @Test
    public void test01() {
        List<Person> lu = personRepository.findByNameLike("%" + "a" + "%");
        System.out.println(lu);
    }

    @Test
    public void test02() {
        List<Person> lu = personRepository.findByNameNotLike("%" + "a" + "%");
        System.out.println(lu);
    }

    @Test
    public void testLikeQuery() {
        List<Person> nameLike = personRepository.getNameLike("%" + "a" + "%");
        System.out.println(nameLike);
    }

    @Test
    public void testLikeQueryNative() {
        List<Person> nameLike = personRepository.getNameLikeNative("%" + "a" + "%");
        System.out.println(nameLike);
    }

    @Test
    public void testLikeEntityManagerNative() {
        List<Person> a = personService.getByNameLikeSql("a");
        System.out.println(a);
    }

    @Test
    public void testLikeEntityManagerNativeIndex() {
        List<Person> a = personService.getByNameLikeSqlIndex("a");
        System.out.println(a);
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

    /**
     * 使用personVo 接收结果
     * 结论：不行，只能是对应的表的实体类，打了注解的类
     *
     * 错误
     * org.springframework.core.convert.ConverterNotFoundException:
     * No converter found capable of converting from
     * type [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap]
     * to type [com.wang.example.springbootdatajpa.entity.PersonVo]
     */
    @Test
    public void testResultVo() {
        List<PersonVo> personVos = personRepository.selectAll();
        System.out.println(personVos);
    }

    /**
     * * 使用personVo 接收结果
     *      * 结论：不行，只能是对应的表的实体类，打了注解的类
     * org.springframework.core.convert.ConverterNotFoundException:
     * No converter found capable of converting from type
     * [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap]
     * to type [com.wang.example.springbootdatajpa.entity.PersonVo]
     */
    @Test
    public void testResultVoNative() {
        List<PersonVo> personVos = personRepository.selectAllNative();
        System.out.println(personVos);
    }

    /**
     * 用domain 接收部分字段
     * 结果：失败
     * 错误：org.springframework.core.convert.ConversionFailedException:
     * Failed to convert from type [java.lang.Object[]]
     * to type [@org.springframework.data.jpa.repository.Query com.wang.example.springbootdatajpa.entity.Person]
     * for value '{allan, 1, 2022-11-30 10:40:05.0}';
     * nested exception is org.springframework.core.convert.ConverterNotFoundException:
     * No converter found capable of converting from type [java.lang.String]
     * to type [@org.springframework.data.jpa.repository.Query com.wang.example.springbootdatajpa.entity.Person]
     */
    @Test
    public void testResultPartFields() {
        List<Person> people = personRepository.selectAllPartFields();
        System.out.println(people);
    }

    /**
     * 用domain 接收部分字段(native)
     * 结果：失败
     * 错误：
     * 2022-12-29 11:56:45.258  WARN 37704 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   :
     * SQL Error: 0, SQLState: S0022
     * 2022-12-29 11:56:45.259 ERROR 37704 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   :
     * Column 't_id' not found.
     *
     * org.springframework.dao.InvalidDataAccessResourceUsageException:
     * could not execute query; SQL [select t.t_name name,t.t_address address,t.t_birthday birthday from t_person t];
     * nested exception is org.hibernate.exception.SQLGrammarException: could not execute query
     */
    @Test
    public void testResultPartFieldsNative() {
        List<Person> people = personRepository.selectAllPartFieldsNative();
        System.out.println(people);
    }

}

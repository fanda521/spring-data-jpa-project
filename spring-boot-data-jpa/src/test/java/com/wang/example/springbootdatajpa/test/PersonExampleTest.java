package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/10/16 22:48
 * @description
 * 属性不支持嵌套或者分组约束，比如这样的查询 firstname = ?0 or (firstname = ?1 and lastname = ?2)
 * 灵活匹配只支持字符串类型，其他类型只支持精确匹配
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonExampleTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByExample() {
        Person person = new Person();
        person.setName("luck1");
        Example<Person> of = Example.of(person);
        List<Person> all =
                personRepository.findAll(of);
        System.out.println(all);
    }

    @Test
    public void findByExample2() {
        Person person = new Person();
        person.setName("luck1");
        Example<Person> of = Example.of(person);
        List<Person> all =
                personRepository.findAll(of, Sort.by("id").descending());
        System.out.println(all);
    }


    @Test
    public void findByExampleMatcher() {
        Person person = new Person();
        person.setName("luck2");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<Person> of = Example.of(person,exampleMatcher);
        List<Person> all =
                personRepository.findAll(of);
        System.out.println(all);
    }

    @Test
    public void findByExampleMatcher2() {
        Person person = new Person();
        person.setName("luck2");
        person.setAge(13);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withIgnorePaths("age")//忽略改字段的where 条件查询
                ;
        Example<Person> of = Example.of(person,exampleMatcher);
        List<Person> all =
                personRepository.findAll(of);
        System.out.println(all);
    }



}

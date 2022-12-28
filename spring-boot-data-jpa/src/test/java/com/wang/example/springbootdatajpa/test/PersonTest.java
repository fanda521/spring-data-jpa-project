package com.wang.example.springbootdatajpa.test;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author allen
 * @version 1.0
 * @date 2020-02-19 20:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test01() {
        List<Person> people = personRepository.selectAllPersons();
        System.out.println(people);
    }

    @Test
    public void test02() {
        List<Person> people = personRepository.selectAllPersons2();
        System.out.println(people);
    }
    @Test
    public  void testFindByName(){
        List<Person> personList = this.personRepository.findByName("王大大");
        personList.forEach(System.out::println);
    }

    @Test
    public  void testSaveOne(){
        Person p=new Person();
        p.setAddress("江西宜春");
        p.setAge(12);
        //p.setId(2);
        p.setBirthday(new Date());
        p.setName("陈大大");
        Person save = this.personRepository.save(p);
        System.out.println(save);
    }

    @Test
    public  void testFindById(){
        Optional<Person> byId = personRepository.findById(3);
        System.out.println(byId);
    }

}

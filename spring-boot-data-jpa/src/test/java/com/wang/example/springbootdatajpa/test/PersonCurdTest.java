package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/10/16 10:18
 * @description CrudRepository 的api
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonCurdTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() {
        Person person = new Person();
        person.setName("jeffrey");
        person.setAge(22);
        person.setBirthday(new Date());
        person.setAddress("江西南昌");

        System.out.println("before:" + person);
        Person save = personRepository.save(person);
        System.out.println("after:"+save);
    }

    @Test
    public void saveAll() {
        Person person = new Person();
        person.setName("jeffrey1");
        person.setAge(22);
        person.setBirthday(new Date());
        person.setAddress("江西南昌");

        Person person2 = new Person();
        person.setName("luck1");
        person.setAge(18);
        person.setBirthday(new Date());
        person.setAddress("江西南昌");

        ArrayList<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);

        System.out.println("before:" + people);
        List<Person> people1 = personRepository.saveAll(people);
        System.out.println("after:"+people1);
    }

    @Test
    public void findById() {
        Optional<Person> byId = personRepository.findById(4);
        Person person = byId.get();
        System.out.println(person);
    }

    @Test
    public void existsById() {
        boolean b = personRepository.existsById(4);
        System.out.println(b);
    }

    @Test
    public void findAll(){
        List<Person> all = personRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllById() {
        List<Person> allById = personRepository.findAllById(Arrays.asList(1, 4, 6));
        System.out.println(allById);
    }

    @Test
    public void count() {
        long count = personRepository.count();
        System.out.println(count);
    }

    @Test
    public void deleteById() {
        personRepository.deleteById(6);
    }

    @Test
    public void deleteAll() {
        ArrayList<Person> people = new ArrayList<>();
        Person person = new Person();
        person.setId(1);

        Person person1 = new Person();
        person1.setId(2);

        Person person2 = new Person();
        person2.setId(4);
        people.add(person);
        people.add(person1);
        people.add(person2);
        personRepository.deleteAll(people);
    }
}

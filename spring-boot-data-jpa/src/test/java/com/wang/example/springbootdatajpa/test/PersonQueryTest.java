package com.wang.example.springbootdatajpa.test;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/10/16 20:31
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonQueryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void selectByName() {
        List<Person> luck2 = personRepository.selectByName("luck2");
        System.out.println(luck2);
    }

    @Test
    public void selectByNameOrAge() {
        List<Person> luck1 = personRepository.selectByNameOrAge("luck1", 12);
        System.out.println(luck1);
    }

    @Test
    public void updatePerson() {
        Person person = new Person();
        person.setName("jack");
        person.setId(1);
        Integer integer = personRepository.updatePerson(person);
        System.out.println(integer);
    }
}

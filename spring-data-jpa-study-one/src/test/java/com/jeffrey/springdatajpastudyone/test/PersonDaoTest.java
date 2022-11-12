package com.jeffrey.springdatajpastudyone.test;

import com.jeffrey.springdatajpastudyone.dao.PersonDao;
import com.jeffrey.springdatajpastudyone.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @auther Jeffrey
 * @date 2020/9/18 14:53
 */
@SpringBootTest
public class PersonDaoTest {
    @Autowired
    private PersonDao personDao;

    @Test
    public void testInsert(){
        Person person = new Person();
        person.setAge(24);
        person.setBirthday(new Date());
        person.setName("jack");
        Person save = personDao.save(person);
        System.out.println(save);

    }

    @Test
    public void testSelect(){
        Optional<Person> byId = personDao.findById(2);
        System.out.println(byId);
    }

    @Test
    public void testUpdate(){
        Person person = new Person();
        person.setAge(23);
        person.setBirthday(new Date());
        person.setName("jack2");
        person.setId(4);
        personDao.save(person);
    }

    @Test
    public void testDelete(){
        Optional<Person> byId = personDao.findById(2);
        personDao.delete(byId.get());
    }


    /**
     * ////////////////////////方法名规则
     */

    /**
     * ok
     */
    @Test
    public void findByAgeAndName(){
        Person pe = personDao.findByAgeAndName(22, "jack");
        System.out.println(pe);
    }

    /**
     * ok
     */
    @Test
    public void findByAgeOrName(){
        List<Person> list = personDao.findByAgeOrName(22, "jack2");
        list.forEach(obj->{
            System.out.println(obj);
        });
    }

    /**
     * ok
     */
    @Test
    public void findByAgeBetween(){
        List<Person> list = personDao.findByAgeBetween(18, 24);
        list.forEach(obj->{
            System.out.println(obj);
        });
    }


    /**
     * ok
     */
    @Test
    public void findByAgeLessThan(){
        List<Person> list = personDao.findByAgeLessThan(23);
        list.forEach(obj->{
            System.out.println(obj);
        });
    }


    /**
     * ok
     */
    @Test
    public void findByAgeGreaterThan(){
        List<Person> list = personDao.findByAgeGreaterThan(23);
        list.forEach(obj->{
            System.out.println(obj);
        });
    }


    /**
     * ok
     */
    @Test
    public void findByCreateDateIsNull(){
        List<Person> list = personDao.findByCreateDateIsNull();
        list.forEach(obj->{
            System.out.println(obj);
        });
    }

    /**
     * ok
     */
    @Test
    public void findByCreateDateNotNull(){
        List<Person> list = personDao.findByCreateDateNotNull();
        list.forEach(obj->{
            System.out.println(obj);
        });
    }


    /**
     * ok
     */
    @Test
    public void findByNameLike(){
        List<Person> list = personDao.findByNameLike("%j%");
        list.forEach(obj->{
            System.out.println(obj);
        });
    }
























}
